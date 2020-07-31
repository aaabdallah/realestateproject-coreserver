package aaacs.coreserver.ejb.entities.support;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import aaacs.coreserver.administration.Configurator;
import aaacs.coreserver.database.DatabaseWrapper;
import aaacs.coreserver.database.PrimaryKeyHolder;
import aaacs.coreserver.commons.exceptions.CoreServerException;

/**
 * @author Ahmed A. Abd-Allah
 * Created on Nov 1, 2006
 * 
 * Caches tables in RAM from the database. This should only be used with reasonably
 * small, relatively unchanging tables for simple lookups. Be careful...
 * The cache is a lazy loader; it only caches a table when a value is requested
 * from a cacheable table.
 */
public class CacheManager extends PrimaryKeyHolder
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
	 * It is a hashtable of table names to treemaps, where each treemap is
	 * a map of primary keys (as longs) to rows (as hashtables). Each
	 * row is a hashtable of columns (as strings) to values (as objects).
	 */
	private static 
		Map<String, Map<Long, Map<String, Object>>> tablesCache = 
		new Hashtable<String, Map<Long, Map<String, Object>>>();
	private static List<String> allowedCacheableTables = new Vector<String>();

	/**
	 * Load the names of the cacheable tables. Does not load the tables themselves
	 * now - lazy fetching policy.
	 * @param resourceFileName the properties file that contains the table names
	 * in "tableName=true" or "tableName=false" format. True means cache, false
	 * is not (the default).
	 */
	public static void addCacheableTables(String resourceFileName)
	{
		ResourceBundle cacheResources = ResourceBundle.getBundle(resourceFileName, Locale.US);

		Enumeration<String> keys = cacheResources.getKeys();
		
		while (keys.hasMoreElements())
		{
			String key = keys.nextElement();
			String value = cacheResources.getString(key);
			if (value.equals("true"))
				allowedCacheableTables.add(key);
		}
	}

	/**
	 * Helper function to cache and thereby speed up the lookup of certain records. It
	 * need be called only once per table. The big assumption here is that that table
	 * does not change much.
	 * This needs to be synchronized to assure the correct loading of all records in case
	 * of multiple clients being concurrently instantiated and used. There is a
	 * quick check at the beginning of this function though to keep the waiting to a
	 * minimum.
	 */
	private static synchronized void cacheTable(String inTableName) 
		throws CoreServerException
	{
		if (!allowedCacheableTables.contains(inTableName))
			throw new CoreServerException("cache.TableUnauthorizedForCache");
	
		// Do a quick check if the table has already been loaded
		Map<Long, Map<String, Object>> table = tablesCache.get(inTableName);
		
		if (table != null)
			return;

		// The table has not been cached yet...so cache it now
		(Configurator.getMainLogger()).trace("Entered cacheTable for " + inTableName);

		Connection connection = null;
		PreparedStatement pstmt = null;
		// Prepare an empty placeholder table
		table = new TreeMap<Long, Map<String, Object>>();
		
		try
		{
			//BaseEntity.findByCondition(manager, tableName, condition, conditionParameters, sqlResultSetMapping)
			// ...but I'm too lazy to convert this code to use the persistence layer, so there you go...
			connection = DatabaseWrapper.getConnection();
			/*
			Map<String, Class<?>> map = new HashMap<String, Class<?>>();
			map.put("SMALLINT", Short.class);
			connection.setTypeMap(map);
			*/

			pstmt = connection.prepareStatement("select * from " + inTableName + " where idk > " +
				KEY_HIGHEST_RESERVED);

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String columnName = null;
			
			/*
			String tempo = "";
			for (int j=1; j<=columnCount; j++)
				tempo += rsmd.getColumnName(j) + " " + rsmd.getColumnType(j) + "\n";
			System.out.println(tempo);
			*/
			
			while (rs.next())
			{
				Map<String, Object> row = new Hashtable<String, Object>();
				int i=1;

				// load the row with the result set's current row's names/values
				while (i<=columnCount)
				{					
					columnName = rsmd.getColumnName(i);
					
					/*
					 * THIS COMMENT BLOCKS OUT SCRAMBLING AT THE CACHE LEVEL.
					// the following is a little hack... see createDatabase.sql for reasoning
					if (columnName.endsWith("idk")) // we do this to select what to scramble or not
												   // ...see the PrimaryKeyHolder class for details.
						row.put(columnName, simpleScramble(rs.getLong(columnName)));
					else
					{
						row.put(columnName, getObject(rs, rsmd, i));
						// row.put(columnName, rs.getObject(columnName));
						
						//System.out.println("cacheTable: columnName: " + columnName + 
						//	"<-->" + rs.getObject(columnName).getClass() + 
						//	"<-->" + rs.getObject(columnName));
						 
					}
					*/
					
					if (rs.getObject(columnName) != null)
						row.put(columnName, getObject(rs, rsmd, i));
					i++;
				}
				
				// place that row into the cached table keyed by its ID
				table.put(rs.getLong("idk"), row);
			}

			// Add the table to the cached set
			tablesCache.put(inTableName, table);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			(Configurator.getMainLogger()).error("Could not cache " + inTableName, e);
			tablesCache.remove(inTableName);
			throw new CoreServerException("cache.UnableToCacheTable", e);
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
	 * Force a particular table out of the cached set. 
	 * @param inTableName table name
	 */
	public static void invalidateCachedTable(String inTableName)
	{
		tablesCache.remove(inTableName);
	}

	/*
	public static void invalidateAllCachedTables()
	{
		if (Configurator.getDebugLevel() == 10)
			tablesCache.clear();
	}
	*/

	/**
	 * Searches the cache for an entire record/object/row in a specific table
	 * given its primary key.
	 * 
	 * @param inTableName the table to search in
	 * @param inIdk the primary key to use for searching in the table
	 * @return a hashtable of columns to values (strings to values). Null means not found
	 */
	public static Map<String, Object> findByIdk(String inTableName, Long inIdk)
		throws CoreServerException
	{
		if (!allowedCacheableTables.contains(inTableName))
			throw new CoreServerException("cache.TableUnauthorizedForCache");

		Map<Long, Map<String, Object>> table = tablesCache.get(inTableName);
		
		if (table == null) // if null, then load first
		{
			cacheTable(inTableName);
			table = tablesCache.get(inTableName);
		}

		return table.get(inIdk);
	}
	
	/**
	 * Retrieves all the records for a cached table that meet the parameter-value pairs
	 * stuffed in <code>inParameters</code>.
	 * 
	 * @param inTableName
	 * @param inParameters
	 * @param maximum the maximum number of records to return. Setting this to zero or less 
	 * means return as many as there are found.
	 * @return any matching records
	 * @throws NBServerException
	 */
	public static Map<Long, Map<String, Object>> findByFields(String inTableName,
		Map<String, Object> inParameters, int maximum)
		throws CoreServerException
	{
		if (!allowedCacheableTables.contains(inTableName))
			throw new CoreServerException("cache.TableUnauthorizedForCache");

		Map<Long, Map<String, Object>> table = tablesCache.get(inTableName);
		
		if (table == null) // if null, then load first
		{
			cacheTable(inTableName);
			table = tablesCache.get(inTableName);
		}

		if (inParameters == null || inParameters.isEmpty())
			return table;

		Set<Long> tableKeys = table.keySet();
		Iterator<Long> keyIterator = tableKeys.iterator();
		Map<Long, Map<String, Object>> matchingRows = 
			new TreeMap<Long, Map<String, Object>>();
		Long resultIdk = null;
		Map<String, Object> oneRow = null;
		String parameterValue = null;
		boolean match = true;
		int count = 0;
		
		// O(n^2) so use sparingly....cannot optimize because we cannot predict which
		// fields will be searched by....unless we index each field somehow.
		while (keyIterator.hasNext())
		{
			resultIdk = keyIterator.next();
			oneRow = table.get(resultIdk);
			match = true;
			for (String parameterName : inParameters.keySet())
			{
				parameterValue = inParameters.get(parameterName).toString();

				if (!parameterValue.equals(oneRow.get(parameterName).toString()))
				{
					match = false;
					break;
				}
			}
			if (match == true)
			{
				matchingRows.put(resultIdk, oneRow);
				count++;
				if (maximum > 0 && count >= maximum)
					return matchingRows;
			}
		}
		if (matchingRows.isEmpty())
			return null;
		return matchingRows;
	}

	public static Byte[] getByteColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Byte[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Byte[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Byte) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}

	public static Short[] getShortColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Short[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Short[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Short) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}

	public static Integer[] getIntegerColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Integer[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Integer[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Integer) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}

	public static Long[] getLongColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Long[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Long[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Long) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}
	
	public static Float[] getFloatColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Float[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Float[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Float) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}
	
	public static Double[] getDoubleColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Double[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Double[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Double) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}
	
	public static String[] getStringColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		String[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new String[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (String) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}

	public static Date[] getDateColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Date[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Date[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Date) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}
	
	public static Timestamp[] getTimestampColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Timestamp[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Timestamp[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Timestamp) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}

	public static Boolean[] getBooleanColumn(String tableName,
		String columnName, Hashtable<String, Object> inParameters)
	{
		Boolean[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = new Boolean[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (Boolean) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}	

	/*public static void test()
	{
		String[] strings = null;
		
		strings = getColumn("tTitles", "name", null);
	}*/

	public static <T> T[] getColumn(String tableName,
		String columnName, Map<String, Object> inParameters)
	{
		T[] values = null;

		try
		{
			Map<Long, Map<String,Object>> records =
				findByFields(tableName, inParameters, 0);

			if (records.size() == 0) return null;

			values = (T[]) new Object[ records.size() ];
			int i = 0;
			for ( Long idk : records.keySet() )
			{
				Map<String, Object> oneRow = records.get(idk);
				values[i++] = (T) oneRow.get(columnName);
			}
			return values;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Cache error", e);
		}
	}	
}
