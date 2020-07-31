package aaacs.coreserver.ejb.entities.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import aaacs.coreserver.administration.Configurator;
import aaacs.coreserver.database.DatabaseWrapper;
import aaacs.coreserver.database.PrimaryKeyHolder;
import aaacs.coreserver.commons.exceptions.CoreServerException;

/**
 * @author Ahmed A. Abd-Allah
 * Created on Nov 1, 2006
 * 
 * Allows easy access to the default values stored in the database per table
 * (see createDatabase.sql and the SQL population files for an explanation). 
 */
public class DefaultValuesManager extends PrimaryKeyHolder
{
	// ----- Static members ---------------------------------------------------
	/**
	 * Serialization Version Number
	 */
	private static final long serialVersionUID = 1000L;
	/**
	 * Stores tables for quick lookup if need be...USE WITH CAUTION; if the
	 * table is too big or is updated too often, this shortcut will turn into
	 * a major performance drain.
	 * It is a hashtable of table names to rows (as hashtables). Each
	 * row is a hashtable of columns (as strings) to values (as objects).
	 */
	private static 
		Map<String, Map<String, Object>> allDefaultValues = 
		new Hashtable<String, Map<String, Object>>();

	/**
	 * Helper function to cache and thereby speed up the lookup of certain records. It
	 * need be called only once per table. The big assumption here is that that table
	 * does not change much.
	 * This needs to be synchronized to assure the correct loading of all records in case
	 * of multiple clients being concurrently instantiated and used. There is a
	 * quick check at the beginning of this function though to keep the waiting to a
	 * minimum.
	 */
	private static synchronized void loadDefaults(String inTableName) 
		throws CoreServerException
	{
		// Do a quick check if the table has already been loaded
		Map<String, Object> defaults = allDefaultValues.get(inTableName);
		
		if (defaults != null)
			return;

		// The defaults have not been loaded for this table yet...so load them
		(Configurator.getMainLogger()).trace("Entered loadDefaults for " + inTableName);

		Connection connection = null;
		PreparedStatement pstmt = null;
		// Prepare an empty placeholder row
		defaults = new Hashtable<String, Object>();
		
		try
		{
			connection = DatabaseWrapper.getConnection();

			pstmt = connection.prepareStatement("select * from " + inTableName + " where idk = " +
				KEY_DEFAULT_VALUES_RECORD);

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String columnName = null;
			if (rs.next())
			{
				int i=1;

				// load the row with the result set's current row's names/values
				while (i<=columnCount)
				{
					columnName = rsmd.getColumnName(i);
					/*
					 * THIS COMMENT BLOCKS OUT SCRAMBLING AT THE DEFAULTS LEVEL.
					// the following is a little hack... see createDatabase.sql for reasoning
					if (columnName.endsWith("idk")) // we do this to select what to scramble or not
												   // ...see the PrimaryKeyHolder class for details.
						defaults.put(columnName, simpleScramble(rs.getLong(columnName)));
					else
					{
						defaults.put(columnName, getObject(rs, rsmd, i));
						// defaults.put(columnName, rs.getObject(columnName));
						
						//System.out.println("Defaults: columnName: " + columnName + 
						//	"<-->" + rs.getObject(columnName).getClass() + 
						//	"<-->" + rs.getObject(columnName));
						 
					}
					*/
					
					if (columnName.equals("idk") ||
						columnName.equals("lockingversion") ||
						columnName.equals("timecreated") ||
						columnName.equals("timelastmodified"))
					{
						// don't add these fields: they should never have defaults
					}
					else if (rs.getObject(columnName) != null)
						defaults.put(columnName, getObject(rs, rsmd, i));
					i++;
				}

				// place the defaults into the hashtable of all bean defaults keyed to the table name
				allDefaultValues.put(inTableName, defaults);
			}
			else
			{
				(Configurator.getMainLogger()).error("database.NoDefaultValues");
				throw new CoreServerException("database.NoDefaultValues");
			}

			return;
		}
		catch (Exception e)
		{
			allDefaultValues.remove(inTableName);
			if (e.getClass().equals(CoreServerException.class))
				throw (CoreServerException) e;
			throw new CoreServerException("defaultValues.UnableToLoadDefaults");
		}
		finally
		{
			try { if (pstmt != null) pstmt.close(); }
			catch (Exception e) {}
			try { if (connection != null) connection.close(); }
			catch (Exception e) {}			
		}
	}

	/**
	 * A story...
	 * Sometime in 2003: PostgreSQL's JDBC 2 driver does not work properly since
	 * shorts get upgraded to ints when you call "getObject()" on a ResultSet.
	 * Later in 2003: PostgreSQL's JDBC 3 driver works just fine
	 * Sometime in 2006: The JDBC driver has the same old problem. Tried to set the
	 * TypeMap to deal with "SMALLINT" as Short but didn't work.
	 * 
	 * Hence, this lousy helper method. Notice that the metadata *is* correct.
	 * Oddly enough the default conversion results are *not*.... strange.
	 * 
	 * @param rs the resultSet to retrieve from
	 * @param rsmd that resultSet's metadata
	 * @param columnIndex the index to get
	 * @return the hopefully correct Object
	 * @throws SQLException
	 */
	private static Object getObject(ResultSet rs, ResultSetMetaData rsmd, int columnIndex)
		throws SQLException
	{
		if (rsmd.getColumnType(columnIndex) == java.sql.Types.TINYINT)
			return rs.getByte(columnIndex);
		else if (rsmd.getColumnType(columnIndex) == java.sql.Types.SMALLINT)
			return rs.getShort(columnIndex);
		else if (rsmd.getColumnType(columnIndex) == java.sql.Types.INTEGER)
			return rs.getInt(columnIndex);
		else if (rsmd.getColumnType(columnIndex) == java.sql.Types.BIGINT)
			return rs.getLong(columnIndex);
		return rs.getObject(columnIndex); // Default behavior for other types seems to be OK...hopefully...
	}

	/**
	 * Force a particular table out of the default values set. 
	 * @param inTableName table name
	 */
	public static void invalidateDefaults(String inTableName)
	{
		allDefaultValues.remove(inTableName);
	}

	public static Map<String, Object> getDefaults(String inTableName)
		throws CoreServerException
	{
		Map<String, Object> defaults = 
			allDefaultValues.get(inTableName);
		
		if (defaults == null) // if null, then load first
		{
			loadDefaults(inTableName);
			defaults = allDefaultValues.get(inTableName);
		}

		return defaults;
	}
}
