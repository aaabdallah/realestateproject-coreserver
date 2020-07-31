package aaacs.coreserver.administration;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServlet;

import aaacs.coreserver.commons.logging.CSLogLevel;
import aaacs.coreserver.commons.logging.CSLogger;
import aaacs.coreserver.database.DatabaseWrapper;
import aaacs.coreserver.ejb.beans.BaseSessionBean;
import aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator;
import aaacs.coreserver.commons.communication.ActionCommunication;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.communication.LoginToken;
import aaacs.coreserver.ejb.entities.User;
import aaacs.coreserver.ejb.entities.support.CacheManager;
import aaacs.coreserver.commons.exceptions.CoreServerException;

/**
 * @author Ahmed A. Abd-Allah
 * Created on Oct 29, 2006
 *
 * This class is meant to be instantiated only <strong>once</strong>. It serves 
 * as the configurator for the Core Server. It should be loaded before any 
 * other class in the system is instantiated.
 */
public class Configurator extends HttpServlet
{
	/**
	 * Serialization Version Number
	 */
	private static final long serialVersionUID = 1000L;
	private static boolean started = false;
	private static CSLogger mainLogger = null, consoleLogger = null;
	private static int debugLevel = 0;
	private static boolean basicValidationEnabled = true;
	private static String systemAccessLevel = null;
	// This is the default locale for the Core Server. It has NO relationship
	// to the locale of whatever client contacts it. This locale should only
	// be used for server-accessible messages and logs.
	private static Locale defaultLocale = Locale.US; 

	private static String defaultExceptionsResources = null;
	private static String defaultActionResources = null;

	synchronized private static void start()
	{
		int progressMarker = 1000;
		ResourceBundle res = null;

		if (started)
			return;

		try
		{
			// read in properties file (note the US locale is assumed only for the first one)
			res = ResourceBundle.getBundle("aaacs.coreserver.resources.configuration", Locale.US);
			// set the default locale after reading in the initial property file.
			Locale.setDefault(new Locale(res.getString("global.LocaleLanguage"), 
										 res.getString("global.LocaleCountry"),
										 res.getString("global.LocaleVariant")));
			defaultLocale = Locale.getDefault();
			ActionCommunication.setDefaultLocale(defaultLocale);
			progressMarker = 2000;

			// ----------------------------------------------------------------
			// Set the locations of other resources
			defaultExceptionsResources = res.getString("exceptions.Resources");
			ErrorReport.setDefaultExceptionsResources(defaultExceptionsResources);

			defaultActionResources = res.getString("action.Resources");
			ErrorReport.setDefaultActionResources(defaultActionResources);

			progressMarker = 2500;
			
			// ----------------------------------------------------------------			
			// Set up console logger: this should always be the first step after reading
			// in the main configuration file
			consoleLogger = new CSLogger(res.getString("logging.ConsoleLoggerName"), 
										 res.getString("logging.ConsoleLoggerMessageResources"));
			// Initialize the new levels to use instead of Java's default levels
			// CSLogLevel.initializeNewLevels(res.getString("logging.LogLevelsResources"));

			consoleLogger.setUseParentHandlers(false);
			// close and remove all existing handlers (this is necessary to 
			// avoid problems with redeployment into a continuously running container)
			Handler handlers[] = consoleLogger.getHandlers();
			if (handlers != null && handlers.length > 0)
			{
				for (int i=0; i<handlers.length; i++)
				{
					handlers[i].close();
					consoleLogger.removeHandler(handlers[i]);
				}
			}
			Handler ch = new ConsoleHandler(); 

			ch.setEncoding(res.getString("global.Encoding"));
			ch.setFormatter(new SimpleFormatter());
			consoleLogger.addHandler(ch);

			consoleLogger.setLevel(CSLogLevel.parse(res.getString("logging.ConsoleLoggerDefaultLevel")));

			progressMarker = 3000;

			// ----------------------------------------------------------------	
			setDebugLevel(Integer.parseInt(res.getString("global.CodeDebugLevel")));
			progressMarker = 3250;
			
			// ----------------------------------------------------------------			
			setBasicValidationEnabled(Boolean.valueOf(res.getString("global.BasicValidationEnabled")).booleanValue());
			progressMarker = 3500;
			
			// ----------------------------------------------------------------			
			// set the current database
			if (!DatabaseWrapper.setDatabaseParameters(res.getString("database.Database"),
				res.getString("database.DataSourceJNDIName")) )
			{
				throw new CoreServerException("database.UnableToConfigure");
			}
			progressMarker = 3750;
			
			// ----------------------------------------------------------------			
			// Prepare cacheable tables manager
			CacheManager.addCacheableTables(res.getString("cache.systemTables"));
			
			progressMarker = 3900;

			// ----------------------------------------------------------------			
			// Set up main logger
			mainLogger = new CSLogger(res.getString("logging.MainLoggerName"), 
									  res.getString("logging.MainLoggerMessageResources"));

			if (debugLevel >= 10)
				mainLogger.setUseParentHandlers(true); // echo all main log entries to console
			else
				mainLogger.setUseParentHandlers(false);

			// close and remove all existing handlers (this is necessary to 
			// avoid problems with redeployment into a continuously running container)
			handlers = mainLogger.getHandlers();
			if (handlers != null && handlers.length > 0)
			{
				for (int i=0; i<handlers.length; i++)
				{
					handlers[i].close();
					mainLogger.removeHandler(handlers[i]);
				}
			}
			Handler fh = new FileHandler(res.getString("logging.MainLoggerPath"),
				Integer.parseInt(res.getString("logging.MainLoggerLogSize")),
				Integer.parseInt(res.getString("logging.MainLoggerLogsToRotate")),
				true);

			fh.setEncoding(res.getString("global.Encoding"));
			fh.setFormatter(new SimpleFormatter());
			mainLogger.addHandler(fh);

			mainLogger.setLevel(CSLogLevel.parse(res.getString("logging.MainLoggerDefaultLevel")));
			mainLogger.information("startup.StartLog");

			progressMarker = 4000;

			// ----------------------------------------------------------------			
			// Load certain system parameters from the database			
			CoreServerAdministrator csa =
				(CoreServerAdministrator) 
				BaseSessionBean.lookup("java:comp/env/ejb/CoreServerAdministrator");
			LoginToken.setInactivityTimeout(Integer.parseInt(
				csa.getSystemParameterValue("systemParameter.AccessControl", 
					"systemParameter.SystemAccessTimeout")));
			setSystemAccessLevel(
				csa.getSystemParameterValue("systemParameter.AccessControl", 
					"systemParameter.SystemAccessLevel"));
			ErrorReport.setReportExceptions(Boolean.parseBoolean(
				csa.getSystemParameterValue("systemParameter.ErrorReports", 
					"systemParameter.ErrorReports.ReportExceptions")));

			progressMarker = 5000;
			
			// ----------------------------------------------------------------			
			// Load administrator rank from the database
			User administrator = csa.readAdministrator();
			if (administrator == null)
				throw new CoreServerException("startup.UnableToIdentifyAdministrator");
			UserManager.setAdministrator(administrator);

			progressMarker = 5500;

			
			// ----------------------------------------------------------------			
			// Update the permissions in the database
			String updatePermissions = res.getString("startup.UpdatePermissions");
			if (updatePermissions.equalsIgnoreCase("true"))
			{
				csa.updatePermissionStrengths();
			}

			progressMarker = 100000;

			// ----------------------------------------------------------------			
			// Signal that the System has started...
			consoleLogger.information("startup.Success");

			started = true;
			
			// Is it important to load the built-in administrator's ID?
		}
		catch (Exception e)
		{
			switch (progressMarker)
			{
				case 1000:
					// This message is hardcoded since the error occurred before
					// a console logger could be created.
					System.out.println("\nFATAL ERROR: Problem reading configuration file.\n");
					break;
				case 2000:
					// This message is hardcoded since the error occurred before
					// a console logger could be created.
					System.out.println("\nFATAL ERROR: Problem reading other resource files.\n");
					break;
				case 2500:
					// This message is hardcoded since the error occurred before
					// a console logger could be created.
					System.out.println("\nFATAL ERROR: Unable to configure console logger.");
					break;
				case 3000:
					consoleLogger.fatalError("startup.UnableToParseDebugLevel");
					break;
				case 3250:
					consoleLogger.fatalError("startup.UnableToParseBasicValidationEnabled");
					break;
				case 3500:
					consoleLogger.fatalError("startup.UnableToConfigureDatabase");
					break;
				case 3750:
					consoleLogger.fatalError("startup.UnableToInitializeCachedTables");
					break;
				case 3900:
					consoleLogger.fatalError("startup.UnableToSetupMainLog");
					break;					
				case 4000:
					consoleLogger.fatalError("startup.UnableToLoadSystemParameters");
					break;
				case 5000:
					consoleLogger.fatalError("startup.UnableToIdentifyAdministrator");
					break;
				case 5500:
					consoleLogger.fatalError("startup.UnableToUpdatePermissions");
					break;
				case 100000:
				default:
					consoleLogger.fatalError("startup.UnknownStartupFailure");
					break;
			}
			e.printStackTrace();
			started = false;
		}
	}
	
	synchronized public static void stop()
	{
		int progressMarker = 1000;
		ResourceBundle res = null;

		if (!started)
			return;

		try
		{
			// read in properties file (note the US locale is assumed only for the first one)
			try { res = ResourceBundle.getBundle("aaacs.coreserver.resources.configuration", Locale.US); }
			catch (Exception erb) {} // ignore resource errors
			if (res != null)
			{
				try { res = ResourceBundle.getBundle(res.getString("logging.ConsoleLoggerMessageResources")); }
				catch (Exception erb) {} // ignore resource errors				
			}

			// ----------------------------------------------------------------			
			// Signal that the System has shutdown in the main log
			// THIS STEP SHOULD PROBABLY REMAIN NEXT-TO-LAST.
			mainLogger.information("shutdown.EndLog");
			
			// close main log handlers
			Handler handlers[] = mainLogger.getHandlers();
			if (handlers != null && handlers.length > 0)
			{
				for (int i=0; i<handlers.length; i++)
				{
					handlers[i].close();
					mainLogger.removeHandler(handlers[i]);
				}
			}
			progressMarker = 9500;

			// ----------------------------------------------------------------			
			// Signal that the System has shutdown in the console log.
			// THIS STEP SHOULD DEFINITELY REMAIN LAST.
			consoleLogger.information("shutdown.Success");
			// close console log handlers
			handlers = consoleLogger.getHandlers();
			if (handlers != null && handlers.length > 0)
			{
				for (int i=0; i<handlers.length; i++)
				{
					handlers[i].close();
					consoleLogger.removeHandler(handlers[i]);
				}
			}
			progressMarker = 100000;

			// DO NOT ADD ANY ACTIONS AFTER THIS: THE CONSOLE LOG SHOULD BE THE LAST
			// TO GO...
			started = false;
		}
		catch (Exception e)
		{
			switch (progressMarker)
			{
				case 1000:
					consoleLogger.fatalError("shutdown.UnableToCloseMainLog");
					break;
				case 9500:
					// We use System.out.println because the console logger may
					// or may not be operational at this point.
					if (res != null)
						System.out.println(res.getString("shutdown.UnableToCloseConsoleLog"));
					else
						System.out.println("FATAL ERROR: Unable to properly shutdown console log.");
					break;
				default:
					if (res != null)
						System.out.println(res.getString("shutdown.UnknownShutdownFailure"));
					else
						System.out.println("FATAL ERROR: The system experienced an unknown failure during shutdown.");
					break;
			}
		}
	}

	public static void restart()
	{
		stop();
		start();
	}

	public static boolean isStarted()
	{
		return started;
	}
	
	public static CSLogger getMainLogger()
	{
		return mainLogger;
	}
	
	public static CSLogger getConsoleLogger()
	{
		return consoleLogger;
	}

	public static int getDebugLevel()
	{
		return debugLevel;
	}

	public static void setDebugLevel(int i)
	{
		if (i < 0)
			debugLevel = 0;
		else if (i > 10)
			debugLevel = 10;
		else
			debugLevel = i;
	}

	public static boolean getBasicValidationEnabled() { return basicValidationEnabled; }
	private static void setBasicValidationEnabled(boolean b) { basicValidationEnabled = b; }
	
	/**
	 * This method is not meant to take the place of Permission's which in theory
	 * could give us system accessibility options that are very detailed. All this
	 * method is intended to do is prevent system-wide access to all users over all
	 * actions. The only exception are administrators.
	 * @param userIdk the user Idk to check. Null means treat the user as a guest.
	 * @return true if system is accessible to that user
	 */
	public static boolean isSystemAccessible(User user)
	{
		// Note that guests only get the following check (guests are user==null)
		if (systemAccessLevel.equals("systemOption.SystemAccessLevel.Open.Value"))
			return true;

		if (user != null && // check as if s/he is a logged in user
			user.getRankIdk() == UserManager.getAdministrator().getRankIdk())
			return true;

		return false; 
	}	

	public static String getSystemAccessLevel() { return systemAccessLevel; }
	private static void setSystemAccessLevel(String inputLevel) 
	{ 
		systemAccessLevel = inputLevel; 
	}
	
	public static Locale getDefaultLocale() { return defaultLocale; }
	public static void setDefaultLocale(Locale l)
	{
		if (l != null)
			defaultLocale = l;
	}
	
	public static String getDefaultExceptionsResources()
	{
		return defaultExceptionsResources;
	}
	public static String getDefaultActionResources()
	{
		return defaultActionResources;
	}

	// ----- Instance Members -------------------------------------------------
	// Only one instance of this servlet is ever instantiated.

	public Configurator()
	{
	}
	
	public void init()
	{
		Configurator.start();
	}
	
	public void destroy()
	{
		Configurator.stop();
	}
	
/*	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
			
	}*/
}
