package aaacs.coreserver.database;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;

import aaacs.coreserver.administration.Configurator;


/**
 * @author Ahmed A. Abd-Allah
 * Created on Oct 29, 2006
 *
 * This class should be used to isolate any database-dependent code. It also
 * has code that is particular to all databases (e.g. getConnection).
 */
public class DatabaseWrapper
{
	// ----- Supported Databases ----------------------------------------------
	public static final String DB_POSTGRESQL = "PostgreSQL";
	
	// ----- Current database -------------------------------------------------
	private static String currentDatabase = null;
	private static String databaseJNDIName = null; 

	public static String getCurrentDatabase()
	{
		return currentDatabase;
	}

	public static String getDatabaseJNDIName()
	{
		return databaseJNDIName;
	}

	public static boolean setDatabaseParameters(String inCurrentDatabase, String inDatabaseJNDIName)
	{
		if (isSupported(inCurrentDatabase))
		{
			currentDatabase = inCurrentDatabase;
			databaseJNDIName = inDatabaseJNDIName;
			return true;
		}
		return false;
	}
	
	public static boolean isSupported(String database)
	{
		if (database != null)
		{
			if (database.equalsIgnoreCase(DB_POSTGRESQL))
				return true;
		}
		return false;		
	}
	
	/*
	public static PrimaryKeyHolder getLastPrimaryKeyGenerated(Connection connection, String tableName)
		throws CoreServerException
	{
		Statement stmt = null;
		CSLogger mainLogger = Configurator.getMainLogger();

		try
		{
			if (currentDatabase.equalsIgnoreCase(DB_POSTGRESQL))
			{
				stmt = connection.createStatement();
				//stmt.executeQuery("select currval('" + tableName + "_id_seq')");
				stmt.executeQuery("select currval('s_global_id_seq')");

				ResultSet resultSet = stmt.getResultSet();
				if (resultSet.first())
				{
					return PrimaryKeyHolder.getFromResultSet(resultSet, "currval");
				}
				throw new CoreServerException("No primary key found in result set.", false, null);
			}
			throw new CoreServerException("Unrecognized database type.", false, null);
		}
		catch (Exception e)
		{
			mainLogger.error("database.UnableToGetLastPrimaryKeyGenerated", e);
			throw new CoreServerException("database.UnableToGetLastPrimaryKeyGenerated", true, e);
		}
		finally
		{
			try { if (stmt != null) stmt.close(); }
			catch (Exception e) {}
		}
	}
	*/

	public static Connection getConnection() throws Exception
	{
		try
		{
			Context ctx = new InitialContext();
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(getDatabaseJNDIName());
			Connection connection = ds.getConnection();
			//connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			//connection.setAutoCommit(false);
			return connection;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			(Configurator.getMainLogger()).fatalError("database.UnableToEstablishConnection", e);
			throw e;
		}
	}

	/**
	 * 
	 */
	public DatabaseWrapper(String database)
	{
	}
}
