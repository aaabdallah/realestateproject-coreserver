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

import java.util.Calendar;
import aaacs.rex.ejb.beans.stateless.interfaces.AccessManager;

@Stateless
@Local(AccessManager.class)
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
public class AccessManagerBean extends BaseSessionBean implements AccessManager
{
	@PersistenceContext(unitName="CoreServer") private EntityManager manager;

	/*************************************************************************
	 * Method: "action.AddUser", v1 (3 tasks)
	 * Task 1: Create
	 *   Input: Map<String, Object> action.AddUser.UserFields (userFields)
	 *   Input: Map<String, Object> action.AddUser.UserRoleFields (userRoleFields)
	 *   Input: Map<String, Object> action.AddUser.UserProfileFields (userProfileFields)
	 * Task 2: Custom code
	 * Task 3: Update
	 *   Output: Map action.AddUser.User (user.getAllFields())
	 *   Output: Map action.AddUser.UserRole (userRole.getAllFields())
	 *   Output: Map action.AddUser.UserProfile (userProfile.getAllFields())
	 *************************************************************************/
	public List<ErrorReport> addUser(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Map<String, Object> userFields =
				inputParameters.getMap("action.AddUser.UserFields");
			User user = new User(userFields);
			vldResults = User.validateUser(user, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.AddUser.UserFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> userRoleFields =
				inputParameters.getMap("action.AddUser.UserRoleFields");
			UserRole userRole = new UserRole(userRoleFields);
			vldResults = UserRole.validateUserRole(userRole, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.AddUser.UserRoleFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> userProfileFields =
				inputParameters.getMap("action.AddUser.UserProfileFields");
			UserProfile userProfile = new UserProfile(userProfileFields);
			vldResults = UserProfile.validateUserProfile(userProfile, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.AddUser.UserProfileFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			manager.persist(user);
			manager.flush();
			userRole.setUserIdk(user.getIdk());
			manager.persist(userRole);
			userProfile.setUserIdk(user.getIdk());
			manager.persist(userProfile);

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			user.setSystemKey( Long.toString(user.getIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// Execute update operation...
			user = manager.merge(user);
			manager.flush();
			Integer affectedEntitiesCounter3 = 1;

			// Package output parameters...
			Class<?>[] keyClassesOut31 = {String.class};
			Class<?>[] valueClassesOut31 = {Object.class};
			outputParameters.setMap("action.AddUser.User", user.getAllFields(), keyClassesOut31, valueClassesOut31);
			Class<?>[] keyClassesOut32 = {String.class};
			Class<?>[] valueClassesOut32 = {Object.class};
			outputParameters.setMap("action.AddUser.UserRole", userRole.getAllFields(), keyClassesOut32, valueClassesOut32);
			Class<?>[] keyClassesOut33 = {String.class};
			Class<?>[] valueClassesOut33 = {Object.class};
			outputParameters.setMap("action.AddUser.UserProfile", userProfile.getAllFields(), keyClassesOut33, valueClassesOut33);

			// ----- End Task 3 -----------------------------------------------

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
	 * Method: "action.CreateUser", v1 (1 task)
	 * Task 1: Create
	 *   Input: Map<String, Object> action.CreateUser.UserFields (userFields)
	 *   Output: Map action.CreateUser.User (user.getAllFields())
	 *************************************************************************/
	public List<ErrorReport> createUser(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Map<String, Object> userFields =
				inputParameters.getMap("action.CreateUser.UserFields");
			User user = new User(userFields);
			vldResults = User.validateUser(user, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateUser.UserFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			manager.persist(user);

			// Package output parameters...
			Class<?>[] keyClassesOut11 = {String.class};
			Class<?>[] valueClassesOut11 = {Object.class};
			outputParameters.setMap("action.CreateUser.User", user.getAllFields(), keyClassesOut11, valueClassesOut11);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.CreateUsers", v1 (1 task)
	 * Task 1: Create
	 *   Input: List<Map<String, Object>> action.CreateUsers.UsersFields (usersFields)
	 *   Output: Map action.CreateUsers.Users (entitiesToMaps(userList))
	 *************************************************************************/
	public List<ErrorReport> createUsers(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			List<Map<String, Object>> usersFields =
				inputParameters.getList("action.CreateUsers.UsersFields");
			List<User> userList = new Vector<User>();
			for (Map<String, Object> oneFieldSet : usersFields)
			{
				User user = new User(oneFieldSet);
				userList.add(user);
			}
			for (User user : userList)
			{
				vldResults = User.validateUser(user, false, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateUsers.UsersFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			for (BaseEntity entity : userList)
			{
				manager.persist(entity);
			}

			// Package output parameters...
			Class<?>[] keyClassesOut11 = {Long.class, String.class};
			Class<?>[] valueClassesOut11 = {Map.class, Object.class};
			outputParameters.setMap("action.CreateUsers.Users", entitiesToMaps(userList), keyClassesOut11, valueClassesOut11);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.DeleteUsers", v1 (1 task)
	 * Task 1: Delete
	 *   Input: List<Map<String, Object>> action.DeleteUsers.UsersFields (usersFields)
	 *   Output: Integer action.DeleteUsers.DeletedUsers (affectedEntitiesCounter1)
	 *************************************************************************/
	public List<ErrorReport> deleteUsers(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			List<Map<String, Object>> usersFields =
				inputParameters.getList("action.DeleteUsers.UsersFields");
			List<User> userList = new Vector<User>();
			for (Map<String, Object> oneFieldSet : usersFields)
			{
				User user = new User(oneFieldSet);
				userList.add(user);
			}
			for (User user : userList)
			{
				vldResults = User.validateUser(user, true, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.DeleteUsers.UsersFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute delete operation...
			Integer affectedEntitiesCounter1 = 0;
			for (Object entity : userList)
			{
				entity = manager.merge(entity);
				manager.remove(entity);
				manager.flush();
				affectedEntitiesCounter1++;
			}

			// Package output parameters...
			outputParameters.setInteger("action.DeleteUsers.DeletedUsers", affectedEntitiesCounter1);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.DeleteUsersByFieldSet", v1 (1 task)
	 * Task 1: Delete
	 *   Input: Map<String, Object> action.DeleteUsersByFieldSet.UserFields (userFields)
	 *   Input: Map<String, Object> action.DeleteUsersByFieldSet.SearchFields (searchFields)
	 *   Output: Integer action.DeleteUsersByFieldSet.DeletedUsers (affectedEntitiesCounter1)
	 *************************************************************************/
	public List<ErrorReport> deleteUsersByFieldSet(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Map<String, Object> userFields =
				inputParameters.getMap("action.DeleteUsersByFieldSet.UserFields");
			User user = new User(userFields);
			vldResults = User.validateUser(user, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.DeleteUsersByFieldSet.UserFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> searchFields =
				inputParameters.getMap("action.DeleteUsersByFieldSet.SearchFields");
			Class<?>[] keyClassesIn12 = {String.class};
			Class<?>[] valueClassesIn12 = {Object.class};
			vldResults = inputParameters.clearChecks().setName("action.DeleteUsersByFieldSet.SearchFields").
				checkMap(searchFields, keyClassesIn12, valueClassesIn12);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute delete operation...
			Integer affectedEntitiesCounter1 = User.removeByFields(manager, searchFields);
			if (affectedEntitiesCounter1 != null && affectedEntitiesCounter1 != userFields.size())
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.DeletedLessThanExpected", request));
			if (errors.size() > 0) return errors;

			// Package output parameters...
			outputParameters.setInteger("action.DeleteUsersByFieldSet.DeletedUsers", affectedEntitiesCounter1);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.FindAllProfileDistricts", v1 (1 task)
	 * Task 1: Custom code
	 *   Output: List action.FindAllProfileDistricts.Districts (list)
	 *************************************************************************/
	public List<ErrorReport> findAllProfileDistricts(ActionRequest request, ActionResponse response)
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

			// --- BEGIN CUSTOM CODE ---
			List list = Country.findAllDistricts(manager, Country.Status.BLOCKEDFROMPROFILES.value, null);
			// --- END CUSTOM CODE ---
			// Package output parameters...
			if (list.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {null};
				Class<?>[] valueClassesOut11 = {Object.class};
				outputParameters.setList("action.FindAllProfileDistricts.Districts", list, keyClassesOut11, valueClassesOut11);
			}

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.FindUserProfilesByUserIdks", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.FindUserProfilesByUserIdks.UserIdks (userIdks)
	 *   Output: Map action.FindUserProfilesByUserIdks.UserProfiles (entitiesToMaps(userProfilesList))
	 *************************************************************************/
	public List<ErrorReport> findUserProfilesByUserIdks(ActionRequest request, ActionResponse response)
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
				null, "tUserProfiles",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Long[] userIdks =
				inputParameters.getLongArray("action.FindUserProfilesByUserIdks.UserIdks");
			vldResults = inputParameters.clearChecks().setName("action.FindUserProfilesByUserIdks.UserIdks").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLongArray(userIdks);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("\"userIdk\"", userIdks);

			List userProfilesList = UserProfile.findUserProfileByFields(manager, searchFields1);

			// Package output parameters...
			if (userProfilesList.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.FindUserProfilesByUserIdks.UserProfiles", entitiesToMaps(userProfilesList), keyClassesOut11, valueClassesOut11);
			}

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.FindUsersByIdks", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.FindUsersByIdks.Idks (idks)
	 *   Output: Map action.FindUsersByIdks.Users (entitiesToMaps(usersList))
	 *************************************************************************/
	public List<ErrorReport> findUsersByIdks(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Long[] idks =
				inputParameters.getLongArray("action.FindUsersByIdks.Idks");
			vldResults = inputParameters.clearChecks().setName("action.FindUsersByIdks.Idks").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLongArray(idks);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("idk", idks);

			List usersList = User.findUserByFields(manager, searchFields1);

			// Package output parameters...
			if (usersList.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.FindUsersByIdks.Users", entitiesToMaps(usersList), keyClassesOut11, valueClassesOut11);
			}

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.Login", v1 (4 tasks)
	 * Task 1: Read
	 *   Input: String action.Login.Username (username)
	 *   Input: String action.Login.Password (password)
	 * Task 2: Read
	 * Task 3: Read
	 * Task 4: Custom code
	 *   Output: Map action.Login.User (user.getAllFields())
	 *   Output: Map action.Login.UserRoles (entitiesToMaps(userRoleList))
	 *   Output: Map action.Login.UserProfile (entitiesToMaps(userProfilesList).values().iterator().next())
	 *************************************************************************/
	public List<ErrorReport> login(ActionRequest request, ActionResponse response)
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
			String username =
				inputParameters.getString("action.Login.Username");
			vldResults = User.validateUsername(username);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem("action.Login.Username", vldResults.get("Username")));
			String password =
				inputParameters.getString("action.Login.Password");
			vldResults = User.validatePassword(password);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem("action.Login.Password", vldResults.get("Password")));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			List userList = User.findUserByCondition(manager,
				" WHERE lower(username) = '" + username + "' AND password = '" + password + "'", null);
			if (userList != null && userList.size() != 1)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.Login.InvalidUsernamePassword", request).addItem("action.Login.Username", username)
					.addItem("action.Login.Password", password));
			// --- BEGIN CUSTOM CODE ---
			User user = null;
			if (userList != null && userList.size() > 0)
			{
				user = (User) userList.get(0);

				// check if the user is blocked
				if ((user.getStatus() & User.Status.BLOCKED.value) > 0)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS, 
						"action.Login.UserBlocked", request));
			}
			// --- END CUSTOM CODE ---

			if (errors.size() > 0) return errors;

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute read operation...
			List userRoleList = UserRole.findUserRoleByCondition(manager,
				" WHERE \"userIdk\" = '" + user.getIdk() + "'", null);
			if (userRoleList != null && userRoleList.size() < 1)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.Login.UserHasNoRoles", request));
			if (errors.size() > 0) return errors;

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// Execute read operation...
			Map<String, Object> searchFields3 = new LinkedHashMap<String, Object>();
			searchFields3.put("\"userIdk\"", user.getIdk());

			List userProfilesList = UserProfile.findUserProfileByFields(manager, searchFields3);

			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			response.setLoginToken( new LoginToken(user.getIdk()) );
			// in the future, perhaps we will need to load the user's permissions if 
			// the client's user interface is very fine-grained and dependent on
			// those permissions
		
			UserManager.addUser(user);
			// --- END CUSTOM CODE ---
			// Package output parameters...
			Class<?>[] keyClassesOut41 = {String.class};
			Class<?>[] valueClassesOut41 = {Object.class};
			outputParameters.setMap("action.Login.User", user.getAllFields(), keyClassesOut41, valueClassesOut41);
			Class<?>[] keyClassesOut42 = {Long.class, String.class};
			Class<?>[] valueClassesOut42 = {Map.class, Object.class};
			outputParameters.setMap("action.Login.UserRoles", entitiesToMaps(userRoleList), keyClassesOut42, valueClassesOut42);
			if (userProfilesList.size() > 0)
			{
				Class<?>[] keyClassesOut43 = {String.class};
				Class<?>[] valueClassesOut43 = {Object.class};
				outputParameters.setMap("action.Login.UserProfile", entitiesToMaps(userProfilesList).values().iterator().next(), keyClassesOut43, valueClassesOut43);
			}

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.Logout", v1 (1 task)
	 * Task 1: Custom code
	 *************************************************************************/
	public List<ErrorReport> logout(ActionRequest request, ActionResponse response)
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

			// --- BEGIN CUSTOM CODE ---
			UserManager.removeUser( request.getLoginToken().getUserIdk() );
			response.setLoginToken( new LoginToken() );
			// --- END CUSTOM CODE ---
			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.UpdateUser", v1 (1 task)
	 * Task 1: Update
	 *   Input: Map<String, Object> action.UpdateUser.UserFields (userFields)
	 *   Output: Map action.UpdateUser.User (user.getAllFields())
	 *************************************************************************/
	public List<ErrorReport> updateUser(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Map<String, Object> userFields =
				inputParameters.getMap("action.UpdateUser.UserFields");
			User user = new User(userFields);
			vldResults = User.validateUser(user, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateUser.UserFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			if (errors.size() > 0) return errors;

			// Execute update operation...
			user = manager.merge(user);
			manager.flush();
			Integer affectedEntitiesCounter1 = 1;

			// Package output parameters...
			Class<?>[] keyClassesOut11 = {String.class};
			Class<?>[] valueClassesOut11 = {Object.class};
			outputParameters.setMap("action.UpdateUser.User", user.getAllFields(), keyClassesOut11, valueClassesOut11);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.UpdateUserProfiles", v1 (1 task)
	 * Task 1: Update
	 *   Input: List<Map<String, Object>> action.UpdateUserProfiles.UserProfilesFields (userProfilesFields)
	 *   Output: Map action.UpdateUserProfiles.UserProfiles (entitiesToMaps(userProfileList))
	 *************************************************************************/
	public List<ErrorReport> updateUserProfiles(ActionRequest request, ActionResponse response)
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
				null, "tUserProfiles",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			List<Map<String, Object>> userProfilesFields =
				inputParameters.getList("action.UpdateUserProfiles.UserProfilesFields");
			List<UserProfile> userProfileList = new Vector<UserProfile>();
			for (Map<String, Object> oneFieldSet : userProfilesFields)
			{
				UserProfile userProfile = new UserProfile(oneFieldSet);
				userProfileList.add(userProfile);
			}
			for (UserProfile userProfile : userProfileList)
			{
				vldResults = UserProfile.validateUserProfile(userProfile, true, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateUserProfiles.UserProfilesFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute update operation...
			Integer affectedEntitiesCounter1 = 0;
			for (Object entity : userProfileList)
			{
				entity = manager.merge(entity);
				manager.flush();
				affectedEntitiesCounter1++;
			}

			// Package output parameters...
			Class<?>[] keyClassesOut11 = {Long.class, String.class};
			Class<?>[] valueClassesOut11 = {Map.class, Object.class};
			outputParameters.setMap("action.UpdateUserProfiles.UserProfiles", entitiesToMaps(userProfileList), keyClassesOut11, valueClassesOut11);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.UpdateUsers", v1 (1 task)
	 * Task 1: Update
	 *   Input: List<Map<String, Object>> action.UpdateUsers.UsersFields (usersFields)
	 *   Output: Map action.UpdateUsers.Users (entitiesToMaps(userList))
	 *************************************************************************/
	public List<ErrorReport> updateUsers(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			List<Map<String, Object>> usersFields =
				inputParameters.getList("action.UpdateUsers.UsersFields");
			List<User> userList = new Vector<User>();
			for (Map<String, Object> oneFieldSet : usersFields)
			{
				User user = new User(oneFieldSet);
				userList.add(user);
			}
			for (User user : userList)
			{
				vldResults = User.validateUser(user, true, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateUsers.UsersFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute update operation...
			Integer affectedEntitiesCounter1 = 0;
			for (Object entity : userList)
			{
				entity = manager.merge(entity);
				manager.flush();
				affectedEntitiesCounter1++;
			}

			// Package output parameters...
			Class<?>[] keyClassesOut11 = {Long.class, String.class};
			Class<?>[] valueClassesOut11 = {Map.class, Object.class};
			outputParameters.setMap("action.UpdateUsers.Users", entitiesToMaps(userList), keyClassesOut11, valueClassesOut11);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.UpdateUsersByFieldSet", v1 (1 task)
	 * Task 1: Update
	 *   Input: Map<String, Object> action.UpdateUsersByFieldSet.UserFields (userFields)
	 *   Input: Map<String, Object> action.UpdateUsersByFieldSet.SearchFields (searchFields)
	 *   Output: Integer action.UpdateUsersByFieldSet.UpdatedUsers (affectedEntitiesCounter1)
	 *************************************************************************/
	public List<ErrorReport> updateUsersByFieldSet(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Map<String, Object> userFields =
				inputParameters.getMap("action.UpdateUsersByFieldSet.UserFields");
			User user = new User(userFields);
			vldResults = User.validateUser(user, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateUsersByFieldSet.UserFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> searchFields =
				inputParameters.getMap("action.UpdateUsersByFieldSet.SearchFields");
			Class<?>[] keyClassesIn12 = {String.class};
			Class<?>[] valueClassesIn12 = {Object.class};
			vldResults = inputParameters.clearChecks().setName("action.UpdateUsersByFieldSet.SearchFields").
				checkMap(searchFields, keyClassesIn12, valueClassesIn12);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute update operation...
			Integer affectedEntitiesCounter1 = User.updateByFields(manager, userFields, searchFields);
			if (affectedEntitiesCounter1 != null && affectedEntitiesCounter1 != userFields.size())
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UpdatedLessThanExpected", request));
			if (errors.size() > 0) return errors;

			// Package output parameters...
			outputParameters.setInteger("action.UpdateUsersByFieldSet.UpdatedUsers", affectedEntitiesCounter1);

			// ----- End Task 1 -----------------------------------------------

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
	 * Method: "action.UpdateUserWithProfile", v1 (2 tasks)
	 * Task 1: Update
	 *   Input: Map<String, Object> action.UpdateUserWithProfile.UserFields (userFields)
	 *   Output: Map action.UpdateUserWithProfile.User (user.getAllFields())
	 * Task 2: Update
	 *   Input: Map<String, Object> action.UpdateUserWithProfile.UserProfileFields (userProfileFields)
	 *   Output: Map action.UpdateUserWithProfile.UserProfile (userProfile.getAllFields())
	 *************************************************************************/
	public List<ErrorReport> updateUserWithProfile(ActionRequest request, ActionResponse response)
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
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Map<String, Object> userFields =
				inputParameters.getMap("action.UpdateUserWithProfile.UserFields");
			User user = new User(userFields);
			vldResults = User.validateUser(user, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateUserWithProfile.UserFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			if (errors.size() > 0) return errors;

			// Execute update operation...
			user = manager.merge(user);
			manager.flush();
			Integer affectedEntitiesCounter1 = 1;

			// Package output parameters...
			Class<?>[] keyClassesOut11 = {String.class};
			Class<?>[] valueClassesOut11 = {Object.class};
			outputParameters.setMap("action.UpdateUserWithProfile.User", user.getAllFields(), keyClassesOut11, valueClassesOut11);

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Check authorizations...
			if (!isAuthorized(manager, request.getLoginToken().getUserIdk(),
				null, null,
				request.getActionName(), request.getActionVersion(),
				null, "tUserProfiles",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Map<String, Object> userProfileFields =
				inputParameters.getMap("action.UpdateUserWithProfile.UserProfileFields");
			UserProfile userProfile = new UserProfile(userProfileFields);
			vldResults = UserProfile.validateUserProfile(userProfile, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateUserWithProfile.UserProfileFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			if (errors.size() > 0) return errors;

			// Execute update operation...
			userProfile = manager.merge(userProfile);
			manager.flush();
			Integer affectedEntitiesCounter2 = 1;

			// Package output parameters...
			Class<?>[] keyClassesOut21 = {String.class};
			Class<?>[] valueClassesOut21 = {Object.class};
			outputParameters.setMap("action.UpdateUserWithProfile.UserProfile", userProfile.getAllFields(), keyClassesOut21, valueClassesOut21);

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

