package aaacs.coreserver.ejb.beans.stateless;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import aaacs.coreserver.database.PrimaryKeyHolder;
import aaacs.coreserver.ejb.beans.BaseSessionBean;
import aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator;
import aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministratorRemote;
import aaacs.coreserver.ejb.entities.Permission;
import aaacs.coreserver.ejb.entities.SystemOption;
import aaacs.coreserver.ejb.entities.SystemParameter;
import aaacs.coreserver.ejb.entities.User;
import aaacs.coreserver.ejb.entities.support.CacheManager;
import aaacs.rex.ejb.entities.Apartment;
import aaacs.rex.ejb.entities.Country;

/**
 * @author Abu Abd-Allah
 * 
 * This bean is for use by other beans and components in the (extended) project.
 * It contains simple methods that do not adhere to the ActionRequest/ActionResponse
 * pattern. It has a couple of useful methods for the Configurator notably.
 */
@Stateless
@Local(CoreServerAdministrator.class)
@Remote(CoreServerAdministratorRemote.class)
@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
@EJBs
(
	value=
	{
		@EJB(name="ejb/CoreServerAdministrator",
			beanInterface=aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator.class,
			beanName="CoreServerAdministratorBean")
	}
)
public class CoreServerAdministratorBean extends BaseSessionBean implements CoreServerAdministrator
{
	@PersistenceContext(unitName="CoreServer") private EntityManager manager;
	@Resource private UserTransaction userTran;

	/**
	 * Gets a system parameter value from the database, potentially looking up its
	 * value from the system options if it refers to them.
	 * @param parameterCategory parameter category
	 * @param parameterName parameter name
	 * @return value if found, null otherwise
	 */
	public String getSystemParameterValue(String parameterCategory, String parameterName)
	{
		if (parameterCategory != null && parameterName != null)
		{
			try
			{
				String condition = " WHERE category = '" + parameterCategory + 
					"' AND name = '" + parameterName + "'";

				userTran.begin();

				List results = SystemParameter.findSystemParameterByCondition(manager, condition, null);

				userTran.commit();

				if (results != null && results.size() > 0)
				{
					SystemParameter sysParam = (SystemParameter) results.get(0);
					
					//System.out.println("\n\n\n\t =====> " + sysParam.getSystemOptionIdk() + " " + sysParam.getValue());

					if (PrimaryKeyHolder.isUserSuppliedPrimaryKey(sysParam.getSystemOptionIdk()))
					{
						SystemOption sysOption = new SystemOption();

						/*
						Hashtable<String, Object> ht = 
							SystemOption.cacheLookup(PrimaryKeyHolder.simpleScramble(sysParam.getSystemOptionIdk()));
						
						if (ht == null)
							System.out.println("\n\n\n\t =====> Cache lookup gave null result");
						else
						{
							for (String key : ht.keySet())
								System.out.println("\n\n\n\t\t --> key: " + 
									key + "<-->" + ht.get(key).getClass() + "<-->" + ht.get(key));
						}
						*/
						sysOption.setAllFields(
							SystemOption.cacheLookup(sysParam.getSystemOptionIdk()), false);

						//System.out.println("\n\n\n\t =====> " + sysOption.getCategory() + " " + sysOption.getName());

						return sysOption.getValue();
					}
					return sysParam.getValue();
				}
			}
			catch (Exception e)
			{
				pst(e);
				try { if (transactionInProgress(userTran)) userTran.rollback(); }
				catch (Exception rbe) { pst(rbe); }
			}		
		}
		return null;
	}
	
	public User readAdministrator()
	{
		try
		{
			User user = null;
			userTran.begin();
			
			user = (User) User.findUserByCondition(manager, 
				" WHERE lower(username) = 'administrator'", 
				null).get(0);
			userTran.commit();
			return user;
		}
		catch (Exception e)
		{
			pst(e);
			try { if (transactionInProgress(userTran)) userTran.rollback(); }
			catch (Exception rbe) { pst(rbe); }
		}
		return null;
	}
	
	public void updatePermissionStrengths()
	{
		try
		{
			userTran.begin();
			
			List list = Permission.findPermissionByCondition(manager, null, null);
			// By "modifying" the permission, it forces recomputing the strength
			for (Object o : list)
				((Permission) o).setAssigneeIdk(((Permission) o).getAssigneeIdk());
			userTran.commit();
		}
		catch (Exception e)
		{
			pst(e);
			try { if (transactionInProgress(userTran)) userTran.rollback(); }
			catch (Exception rbe) { pst(rbe); }
		}
	}
	
	public <T> T[] lookupTableColumnFromCache(String tableName,
		String columnName, Map<String, Object> inParameters)
	{
		return CacheManager.getColumn(tableName, columnName, inParameters);
	}

	public List getAllDistricts()
	{
		try
		{
			userTran.begin();
			
			List list = 
				Country.findAllDistricts(manager, null, null); 
			userTran.commit();
			return list;
		}
		catch (Exception e)
		{
			pst(e);
			try { if (transactionInProgress(userTran)) userTran.rollback(); }
			catch (Exception rbe) { pst(rbe); }
		}
		return null;
	}
	
	public void testMethod()
	{
/*		Connection conn = null;
		Jdbc3PoolingDataSource source = null;
		try
		{
			source = new Jdbc3PoolingDataSource();
			source.setDataSourceName("My PostgreSQL database");
			source.setServerName("localhost");
			source.setDatabaseName("rep");
			source.setUser("postgres");
			source.setPassword("postgres");
			source.setMaxConnections(10);
			
			conn = source.getConnection();
			System.out.println("Connection validity: " + conn.isValid(5));
			Properties props = conn.getClientInfo();
			for (Object op : props.keySet())
			{
				System.out.println("Connection property: " + op.toString() + 
					props.getProperty(op.toString()));
			}
			source.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (conn != null) 
				try {conn.close();} 
				catch (Exception ee) {ee.printStackTrace();}
			if (source != null) 
				try {source.close();} 
				catch (Exception ee) {ee.printStackTrace();}
		}
*/
		try
		{
			userTran.begin();
			Apartment apartment = new Apartment();
			apartment.setPropertyIdk(1L);
			apartment.setInteriorPrimaryColorIdk(1L);
			apartment.setInteriorSecondaryColorIdk(1L);
			apartment.setResidentialFeaturesIdk(1L);
			apartment.setValidating(false);
			manager.persist(apartment);
			userTran.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
