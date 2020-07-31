package aaacs.rex.client;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.Format;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import aaacs.coreserver.commons.utilities.Scrambler;
import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.communication.Parameters;
import aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator;
import aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministratorRemote;
import aaacs.rex.ejb.beans.stateless.interfaces.Dispatcher;

public class JavaClient 
{
	private static Context establishContext()
		throws NamingException, SQLException
	{
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ResourceBundle res = null;

		// read in properties file (note the US locale is assumed only for the first one)
		res = ResourceBundle.getBundle("aaacs.rex.client.resources.configuration", Locale.US);

		// ht.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		ht.put(Context.INITIAL_CONTEXT_FACTORY, res.getString("connection.InitialContextFactory"));
		ht.put(Context.URL_PKG_PREFIXES, res.getString("connection.urlPkgPrefixes"));
		ht.put(Context.PROVIDER_URL, res.getString("connection.ProviderURL"));

		// create context for JNDI lookups for home objects
		Context ctx = new InitialContext();
		/*
		com.sun.gjc.spi.DataSource ds = (com.sun.gjc.spi.DataSource) ctx.lookup("jdbc/PostgreSQL");
		Connection con = ds.getConnection();
		DatabaseMetaData dbmd = con.getMetaData();
		System.out.println( "Transaction Isolation CSLogLevel: " + dbmd.getDefaultTransactionIsolation() );
		con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		dbmd = con.getMetaData();
		System.out.println( "Transaction Isolation CSLogLevel: " + dbmd.getDefaultTransactionIsolation() );
		 */
		return ctx;
	}

	static <T> void fromArrayToCollection(T[] a, Collection<T> c) 
	{
		for (T o : a) { 
			c.add(o);
		}
	}
	static void test()
	{
		//Object[] oa = new Object[100];
		String[] sa = new String[100];
		Collection<Object> oc = new ArrayList<Object>();
		//Collection<String> sc = new ArrayList<String>();
		
		fromArrayToCollection( sa, oc );
		// fromArrayToCollection( oa, sc );
	}
	
	static String getEnvironment()
	{
		String result = "ENVIRONMENT\n===========\n";
		
		Map<String, String> env = System.getenv();
		for (String n : env.keySet())
		{
			result += "Env: " + n + "<----->" + env.get(n) + "\n";
		}
		return result + "\n";
	}
	
	static String getProperties()
	{
		String result = "PROPERTIES\n==========\n";
		
		Properties props = System.getProperties();
		Enumeration e = props.propertyNames();
		
		while (e.hasMoreElements())
		{
			String propName = (String) e.nextElement();
			result += "Prop: " + propName + "<----->" + props.getProperty(propName) + "\n";
		}
		return result + "\n";
	}

	public static void doMessageTest()
	{
		ResourceBundle res = null;

		res = ResourceBundle.getBundle("aaacs.rex.client.resources.configuration", Locale.US);
		// string.FileSearchResults
		MessageFormat formatter = 
			new MessageFormat(res.getString("string.FileSearchResults"), Locale.US);
		
		Object[] args1 = { new Integer(3), 100, "somedirectory" };
		//Object[] args2 = { new Integer(3), 100 };
		//Integer[] x = {3};
		String output = formatter.format(args1);
		System.out.println(output);

		Format[] formats = formatter.getFormats();
		System.out.println(formats);
		for (Format format : formats)
			System.out.println("Format: " + format);
		
		/*output = formatter.format(args2);
		System.out.println(output);		
		output = formatter.format(x);
		System.out.println(output);*/		
	}

	public static void testScrambling()
	{
		System.out.println(Scrambler.descrambleKeys(
			"One possible key is SKPFPZT5AM3ZH4HS0, but the following " +
			"is not a key\n namely SKPFPZT5AM3ZH4HSJ, however here are " +
			"other\n ones SKPFN89S7YSM06JGG SKPFP4V7YVOGTIMM8 \n" +
			"and here is another false SKPFN89S7YSM06JG one"
			));
		
		long l = 945345345L;
		//String scrambled = Long.toString(Long.reverse(l), Character.MAX_RADIX);
		//long unscrambled = Long.reverse(Long.parseLong(scrambled, Character.MAX_RADIX));
		//System.out.println(unscrambled);
		Random rand = new Random(l);
		rand.nextInt(25);
		
		long beginTime = 0, endTime = 0;
		
		rand = new Random(1000);
		l = Math.abs(rand.nextLong()) + 1;
		String scrambled1 = Scrambler.scramble(10000001L);
		long unscrambled1 = Scrambler.descramble(scrambled1);
		System.out.println(scrambled1 + " " + unscrambled1);
		scrambled1 = Scrambler.scramble(10000002L);
		unscrambled1 = Scrambler.descramble(scrambled1);
		System.out.println(scrambled1 + " " + unscrambled1);
		scrambled1 = Scrambler.scramble(10000003L);
		unscrambled1 = Scrambler.descramble(scrambled1);
		System.out.println(scrambled1 + " " + unscrambled1);
		scrambled1 = Scrambler.scramble(9999);
		unscrambled1 = Scrambler.descramble(scrambled1);
		System.out.println(scrambled1 + " " + unscrambled1);
		scrambled1 = Scrambler.scramble(10000);
		unscrambled1 = Scrambler.descramble(scrambled1);
		System.out.println(scrambled1 + " " + unscrambled1);
		beginTime = System.currentTimeMillis();
		for (long i = 0; i < 1000000L; i++)
		{
			l = Math.abs(rand.nextLong()) + 1;
			scrambled1 = Scrambler.scramble(l);
			unscrambled1 = Scrambler.descramble(scrambled1);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Your way: " + (endTime-beginTime));
		
		rand = new Random(1000);
		l = Math.abs(rand.nextLong()) + 1;
		String scrambled2 = Long.toString(Long.reverse(10000001L), Character.MAX_RADIX);
		long unscrambled2 = Long.reverse(Long.parseLong(scrambled2, Character.MAX_RADIX));
		System.out.println(scrambled2 + " " + unscrambled2);
		scrambled2 = Long.toString(Long.reverse(10000002L), Character.MAX_RADIX);
		unscrambled2 = Long.reverse(Long.parseLong(scrambled2, Character.MAX_RADIX));
		System.out.println(scrambled2 + " " + unscrambled2);
		scrambled2 = Long.toString(Long.reverse(10000003L), Character.MAX_RADIX);
		unscrambled2 = Long.reverse(Long.parseLong(scrambled2, Character.MAX_RADIX));
		System.out.println(scrambled2 + " " + unscrambled2);
		beginTime = System.currentTimeMillis();
		for (long i = 0; i < 1000000L; i++)
		{
			l = Math.abs(rand.nextLong()) + 1;
			scrambled2 = Long.toString(Long.reverse(l), Character.MAX_RADIX);
			unscrambled2 = Long.reverse(Long.parseLong(scrambled2, Character.MAX_RADIX));
		}
		endTime = System.currentTimeMillis();
		System.out.println("Better way: " + (endTime-beginTime));
	}
	
	public static void testDataStructures()
	{
		Random rand = null;
		long beginTime = 0, endTime = 0, limit = 10000000L, clearValue = 1000000L;
		int i = 0;
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Vector<Integer> vector = new Vector<Integer>();
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		
		
		rand = new Random(1000);
		beginTime = System.currentTimeMillis();
		for (i = 1; i <= limit; i++)
		{
			arrayList.add(Math.abs(rand.nextInt()));
			if ((i % clearValue) == 0)
				arrayList.clear();
		}
		endTime = System.currentTimeMillis();
		System.out.println("ArrayList: " + (endTime-beginTime));
		
		rand = new Random(1000);
		beginTime = System.currentTimeMillis();
		for (i = 1; i <= limit; i++)
		{
			linkedList.add(Math.abs(rand.nextInt()));
			if ((i % clearValue) == 0)
				linkedList.clear();
		}
		endTime = System.currentTimeMillis();
		System.out.println("Linked List: " + (endTime-beginTime));

		rand = new Random(1000);
		beginTime = System.currentTimeMillis();
		for (i = 1; i <= limit; i++)
		{
			vector.add(Math.abs(rand.nextInt()));
			if ((i % clearValue) == 0)
				vector.clear();
		}
		endTime = System.currentTimeMillis();
		System.out.println("Vector: " + (endTime-beginTime));
	}
	
	public static String printTree(Context c,Context subContext, String contextName, String prefix) 
	{
		String result = "";
		
		try
		{
			//result += contextName + "\n";//System.out.println(contextName);
	
			NamingEnumeration ne = c.listBindings(contextName);
	
			while (ne.hasMore()) 
			{
				Binding b = (Binding)ne.next(); //"\n<<<<<NAME-OBJECT>>>>>\n" +
				result +=  "\t" + prefix + b.getName() + " ==> \n\t\t" + b.getObject().getClass() + "\n";//System.out.println(prefix + b.getName() );
	
				if (b.getObject() instanceof Context)
					result += printTree(subContext,(Context)b.getObject(),b.getName(),"--\\" + prefix) +"\n";
			}
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	//@EJB private static StatelessTest statelessTest;
	public static void main(String[] args)
		throws NamingException, SQLException
	{
		System.out.println("Starting JavaClient");

		System.out.println("Starting scenarios");
		try
		{
			Context ctx = establishContext();
			System.out.println("Established context");
			/*System.out.println(printTree(ctx, ctx, "", ""));*/
			//SystemAdministrator sysAdmin = 
			//	(SystemAdministrator) ctx.lookup("aaacs.rex.ejb.beans.stateless.interfaces.SystemAdministrator");
			// Scenario -0.5
			Dispatcher dispatcher = 
				(Dispatcher) ctx.lookup("aaacs.rex.ejb.beans.stateless.interfaces.Dispatcher");
			CoreServerAdministratorRemote csa = 
				(CoreServerAdministratorRemote) ctx.lookup("aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministratorRemote");
			
			csa.testMethod();
			/*
			List list = csa.getAllDistricts();
			System.out.println("List size: " + list.size());

			int i = 0;
			for (Object o : list)
			{
				Object[] columns = (Object[]) o;
				System.out.print(i++ + "\t");
				for (Object column : columns)
					System.out.print(column + " ");
				System.out.println();
			}
			*/
			if (true) 
				return;

			//Dispatcher dispatcher = 
			//	(Dispatcher) ctx.lookup("aaacs.rex.ejb.beans.stateless.interfaces.Dispatcher");
			//DerivedDispatcher derivedDispatcher = 
			//	(DerivedDispatcher) ctx.lookup("aaacs.rex.ejb.beans.stateless.interfaces.DerivedDispatcher");

			//System.out.println( dispatcher.getSimpleSecret() );
			
			//if (true) return;
			
			// Scenario 0
			/*
			List users = sysAdmin.getUsers();
			for (Object user : users)
			{
				System.out.println("Username/Password: " + ((User) user).getUsername() +
					"/" + ((User) user).getPassword());
			}

			String[] usernames = sysAdmin.getUsernames();
			for (String username : usernames)
			{
				System.out.println("Username: " + username);
			}
			//if (true) return; 
			*/

			// Scenario 1
			
			// sysAdmin.createSystemOption(("sp" + Math.random()).substring(0, 8));
			
			
			// Scenario 2
			/*
			LinkedHashMap<String, Object> searchFields = new LinkedHashMap<String, Object>();
			searchFields.put("description", "Bogusity");
			sysAdmin.findSystemOptions(searchFields);
			*/
			
			// Scenario 3
			/*
			System.out.println( 
				sysAdmin.getSystemParameter("systemParameter.AccessControl",
				"systemParameter.SystemAccessTimeout") );
			*/
			//System.out.println( "TIMEOUT: " + statelessTest.getSystemAccessTimeout());
			
			// Scenario 4
			/*
			// sysAdmin.removeSystemOption("sp0.3749");
			*/

			// Scenario 5
			/*
			LinkedHashMap<String, Object> searchFields = new LinkedHashMap<String, Object>();
			searchFields.put("category", "Bogusity");
			LinkedHashMap<String, Object> fieldsToSet = new LinkedHashMap<String, Object>();
			fieldsToSet.put("value", "BOGUS VALUE");
			System.out.println("Updated: " + sysAdmin.bulkUpdateSystemOptions(fieldsToSet, searchFields));
			*/

			// Scenario 6
			Hashtable<String, Object> oneUser = null;
			
			Parameters parameters = new Parameters();
			parameters.setString("action.Login.Username", "aaabdallah@gmail.com");
			parameters.setString("action.Login.Password", "tempo999");
			ActionRequest request = new ActionRequest(Locale.US, null, parameters,
				"action.Login", (short) 1);
			ActionResponse response = dispatcher.executeAction(request);
			if (response.hasErrorReports())
			{
				List<ErrorReport> errors = response.getErrorReports();
				for (ErrorReport error : errors)
				{
					//System.out.println("SIZE: " + error.getMessageExceptions().size());
					//Exception e = error.getMessageExceptions().get(0);
					//e.printStackTrace();
					System.out.println(error.format(Locale.US));
				}
			}
			else
			{
				Parameters results = response.getParameters();
				Hashtable<String, Object> user = 
					(Hashtable<String, Object>) results.getMap("action.Login.User");
				oneUser = user;
				LinkedHashMap<Long, Hashtable<String, Object>> userRoles =
					(LinkedHashMap<Long, Hashtable<String, Object>>) results.getMap("action.Login.UserRoles");
				System.out.println("User:");
				for (String name : user.keySet())
					System.out.println("Field: " + name + " ---> " + user.get(name));
				for (Long idk : userRoles.keySet())
				{
					System.out.println("\nRole:");
					Hashtable<String, Object> role = (Hashtable<String, Object>) userRoles.get(idk);
					for (String name : role.keySet())
						System.out.println("Field: " + name + " ---> " + role.get(name));					
				}
			}

			parameters.clearParameters();
			Long[] userIdks = { (Long) oneUser.get("idk") };
			parameters.setLongArray("action.FindUserProfilesByUserIdks.UserIdks", userIdks);
			request = new ActionRequest(Locale.US, response.getLoginToken(), parameters,
				"action.FindUserProfilesByUserIdks", (short) 1);
			response = dispatcher.executeAction(request);
			if (response.hasErrorReports())
			{
				List<ErrorReport> errors = response.getErrorReports();
				for (ErrorReport error : errors)
				{
					//System.out.println("SIZE: " + error.getMessageExceptions().size());
					//Exception e = error.getMessageExceptions().get(0);
					//e.printStackTrace();
					System.out.println(error.format(Locale.US));
				}
			}
			else
			{
				Parameters results = response.getParameters();
				Map profiles = results.getMap("action.GetUserProfiles.UserProfiles");
				for (Object o : profiles.keySet())
				{
					Long idk = (Long) o;
					System.out.println("\nProfile:");
					Map<String, Object> profile = (Map<String, Object>) profiles.get(idk);
					for (String name : profile.keySet())
						System.out.println("Field: " + name + " ---> " + profile.get(name));					
				}
			}

		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
