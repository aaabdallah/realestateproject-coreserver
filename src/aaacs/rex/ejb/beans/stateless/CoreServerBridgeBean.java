/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006-2007                                   * 
 * ***************************************************************************/

package aaacs.rex.ejb.beans.stateless;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import aaacs.coreserver.administration.UserManager;
import aaacs.coreserver.database.PrimaryKeyHolder;
import aaacs.coreserver.database.SelectBuilder;
import aaacs.coreserver.ejb.beans.BaseSessionBean;
import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.communication.LoginToken;
import aaacs.coreserver.commons.communication.Parameters;
import aaacs.coreserver.commons.exceptions.CSWrapperException;

import aaacs.coreserver.ejb.entities.*;
import aaacs.rex.ejb.entities.*;

import aaacs.coreserver.commons.validation.MsgArgsPair;

import aaacs.coreserver.ejb.entities.support.CacheManager;
import aaacs.rex.ejb.beans.stateless.interfaces.CoreServerBridge;

@Stateless
@Local(CoreServerBridge.class)
@TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
@TransactionAttribute(javax.ejb.TransactionAttributeType.REQUIRED)
@EJBs
(
	value = 
	{
		@EJB(name="ejb/CoreServerAdministrator",
			beanInterface=aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator.class,
			beanName="CoreServerAdministratorBean"),
		@EJB(name="ejb/CoreServerBridge",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.CoreServerBridge.class,
			beanName="CoreServerBridgeBean"),
		@EJB(name="ejb/AccessManager",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.AccessManager.class,
			beanName="AccessManagerBean"),
		@EJB(name="ejb/PropertyManager",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.PropertyManager.class,
			beanName="PropertyManagerBean"),
		@EJB(name="ejb/PropertySearchManager",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.PropertySearchManager.class,
			beanName="PropertySearchManagerBean"),
		@EJB(name="ejb/PropertyUpdateManager",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.PropertyUpdateManager.class,
			beanName="PropertyUpdateManagerBean"),
		@EJB(name="ejb/UserSearchManager",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.UserSearchManager.class,
			beanName="UserSearchManagerBean")
	}
)
public class CoreServerBridgeBean extends BaseSessionBean implements CoreServerBridge
{
	@PersistenceContext(unitName="CoreServer") private EntityManager manager;

	/*************************************************************************
	 * Method: "action.LookupTableFromCache", v1 (2 tasks)
	 * Task 1: Undetermined
	 *   Input: String action.LookupTableFromCache.TableName (tableName)
	 *   Input: Map<String, Object> action.LookupTableFromCache.SearchParameters (searchParameters)
	 * Task 2: Custom code
	 *   Output: Map action.LookupTableFromCache.Table (results)
	 *************************************************************************/
	public List<ErrorReport> lookupTableFromCache(ActionRequest request, ActionResponse response)
		throws CSWrapperException
	{
		List<ErrorReport> errors = new Vector<ErrorReport>();
		Map<String, List<MsgArgsPair>> vldResults = null;
		Parameters inputParameters = request.getParameters();
		Parameters outputParameters = new Parameters();
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// Check authorizations...
			if (!isAuthorized(manager, request.getLoginToken().getUserIdk(),
				null, null,
				request.getActionName(), request.getActionVersion(),
				null, null,
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			String tableName =
				inputParameters.getString("action.LookupTableFromCache.TableName");
			vldResults = inputParameters.clearChecks().setName("action.LookupTableFromCache.TableName").
				setValidMinimum((double) 1).
				checkString(tableName);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> searchParameters =
				inputParameters.getMap("action.LookupTableFromCache.SearchParameters");
			Class<?>[] keyClassesIn12 = {String.class};
			Class<?>[] valueClassesIn12 = {Object.class};
			vldResults = inputParameters.clearChecks().setName("action.LookupTableFromCache.SearchParameters").
				setAllowNullStructure(true).
				checkMap(searchParameters, keyClassesIn12, valueClassesIn12);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			Map<Long, Map<String, Object>> results = CacheManager.findByFields(tableName, searchParameters, 0);
			// --- END CUSTOM CODE ---
			// Package output parameters...
			Class<?>[] keyClassesOut21 = {Long.class, String.class};
			Class<?>[] valueClassesOut21 = {Map.class, Object.class};
			outputParameters.setMap("action.LookupTableFromCache.Table", results, keyClassesOut21, valueClassesOut21);

			// ----- End Task 2 -----------------------------------------------

			if (errors.size() > 0) return errors;
			response.setParameters(outputParameters);
			return null;
		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

	/*************************************************************************
	 * Method: "action.LookupTableColumnFromCache", v1 (2 tasks)
	 * Task 1: Undetermined
	 *   Input: String action.LookupTableColumnFromCache.TableName (tableName)
	 *   Input: String action.LookupTableColumnFromCache.ColumnName (columnName)
	 *   Input: Map<String, Object> action.LookupTableColumnFromCache.SearchParameters (searchParameters)
	 * Task 2: Custom code
	 *   Output: Array action.LookupTableColumnFromCache.Column (results)
	 *************************************************************************/
	public List<ErrorReport> lookupTableColumnFromCache(ActionRequest request, ActionResponse response)
		throws CSWrapperException
	{
		List<ErrorReport> errors = new Vector<ErrorReport>();
		Map<String, List<MsgArgsPair>> vldResults = null;
		Parameters inputParameters = request.getParameters();
		Parameters outputParameters = new Parameters();
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// Check authorizations...
			if (!isAuthorized(manager, request.getLoginToken().getUserIdk(),
				null, null,
				request.getActionName(), request.getActionVersion(),
				null, null,
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			String tableName =
				inputParameters.getString("action.LookupTableColumnFromCache.TableName");
			vldResults = inputParameters.clearChecks().setName("action.LookupTableColumnFromCache.TableName").
				setValidMinimum((double) 1).
				checkString(tableName);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			String columnName =
				inputParameters.getString("action.LookupTableColumnFromCache.ColumnName");
			vldResults = inputParameters.clearChecks().setName("action.LookupTableColumnFromCache.ColumnName").
				setValidMinimum((double) 1).
				checkString(columnName);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> searchParameters =
				inputParameters.getMap("action.LookupTableColumnFromCache.SearchParameters");
			Class<?>[] keyClassesIn13 = {String.class};
			Class<?>[] valueClassesIn13 = {Object.class};
			vldResults = inputParameters.clearChecks().setName("action.LookupTableColumnFromCache.SearchParameters").
				setAllowNullStructure(true).
				checkMap(searchParameters, keyClassesIn13, valueClassesIn13);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			Object[] results = CacheManager.getColumn(tableName, columnName, searchParameters);
			// --- END CUSTOM CODE ---
			// Package output parameters...
			Class<?>[] keyClassesOut21 = {null};
			Class<?>[] valueClassesOut21 = {Object.class};
			outputParameters.setArray("action.LookupTableColumnFromCache.Column", results, keyClassesOut21, valueClassesOut21);

			// ----- End Task 2 -----------------------------------------------

			if (errors.size() > 0) return errors;
			response.setParameters(outputParameters);
			return null;
		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

}

