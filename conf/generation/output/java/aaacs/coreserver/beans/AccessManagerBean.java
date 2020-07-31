/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.rex.ejb.beans.stateless;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import aaacs.coreserver.ejb.beans.BaseSessionBean;
import aaacs.coreserver.ejb.beans.support.ActionRequest;
import aaacs.coreserver.ejb.beans.support.ActionResponse;
import aaacs.coreserver.ejb.beans.support.ErrorReport;
import aaacs.coreserver.ejb.beans.support.LoginToken;
import aaacs.coreserver.ejb.beans.support.Parameters;
import aaacs.coreserver.exceptions.CSWrapperException;

import aaacs.rex.ejb.beans.stateless.interfaces.AccessManager;
import aaacs.rex.ejb.beans.stateless.interfaces.AccessManagerR;

@Stateless
@Local(AccessManager.class)
@Remote(AccessManagerR.class)
@TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
@TransactionAttribute(javax.ejb.TransactionAttributeType.REQUIRED)
@EJBs
(
	value = 
	{
		@EJB(name="ejb/CoreServerAdministrator",
			beanInterface=aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator.class,
			beanName="CoreServerAdministratorBean"),
		@EJB(name="ejb/AccessManager",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.AccessManager.class,
			beanName="AccessManagerBean"),
		@EJB(name="ejb/AccessManagerR",
			beanInterface=aaacs.rex.ejb.beans.stateless.interfaces.AccessManagerR.class,
			beanName="AccessManagerBean")
	}
)
public class AccessManagerBean extends BaseSessionBean implements AccessManager, AccessManagerR
{
	/*************************************************************************
	 * Method: getUserProfiles (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.GetUserProfiles.UserIdks (userIdks)
	 *   Output: Map action.GetUserProfiles.UserProfiles (entitiesToMaps(userProfilesList))
	 *************************************************************************/
	public List<ErrorReport> getUserProfiles(ActionRequest request, ActionResponse response)
		throws CSWrapperException
	{
		List<ErrorReport> errors = new Vector<ErrorReport>();
		Map<String, List<String>> vldResults = null;
		Parameters ps = request.getParameters();
		Parameters returnParameters = new Parameters();
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// Check authorizations...
			if (!isAuthorized(manager, request.getLoginToken().getUserIdk(),
				request.getActionName(), request.getActionVersion(),
				null, "tUserProfiles",
				null, null))
				errors.add(new ErrorReport(ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Long[] userIdks =
				request.getParameters().getLongArray("action.GetUserProfiles.UserIdks");
			vldResults = ps.clearChecks().setName("action.GetUserProfiles.UserIdks").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLongArray(userIdks);
			if (vldResults != null)
				errors.add(new ErrorReport(ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("userIdk", userIdks);

			List userProfilesList = UserProfile.findUserProfileByFields(manager, searchFields1);

			// Package output parameters...
			if (userProfilesList.size() > 0)
			{
				Class<?>[] keyClasses11 = {Long.class, String.class};
				Class<?>[] valueClasses11 = {Map.class, Object.class};
				returnParameters.setMap("action.GetUserProfiles.UserProfiles", entitiesToMaps(userProfilesList), keyClasses11, valueClasses11);
			}

			// ----- End Task 1 -----------------------------------------------

			if (errors.size() > 0) return errors;
			response.setParameters(returnParameters);
			return null;
		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

	/*************************************************************************
	 * Method: getUsers (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.GetUsers.Idks (idks)
	 *   Output: Map action.GetUsers.UserProfiles (entitiesToMaps(userProfilesList))
	 *************************************************************************/
	public List<ErrorReport> getUsers(ActionRequest request, ActionResponse response)
		throws CSWrapperException
	{
		List<ErrorReport> errors = new Vector<ErrorReport>();
		Map<String, List<String>> vldResults = null;
		Parameters ps = request.getParameters();
		Parameters returnParameters = new Parameters();
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// Check authorizations...
			if (!isAuthorized(manager, request.getLoginToken().getUserIdk(),
				request.getActionName(), request.getActionVersion(),
				null, "tUsers",
				null, null))
				errors.add(new ErrorReport(ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Long[] idks =
				request.getParameters().getLongArray("action.GetUsers.Idks");
			vldResults = ps.clearChecks().setName("action.GetUsers.Idks").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLongArray(idks);
			if (vldResults != null)
				errors.add(new ErrorReport(ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			vldResults = ps.clearChecks().setName("action.GetUsers.Idks").
				checkLongArray(idks);
			if (vldResults != null)
				errors.add(new ErrorReport(ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("idk", idks);

			List userProfilesList = User.findUserByFields(manager, searchFields1);

			// Package output parameters...
			if (userProfilesList.size() > 0)
			{
				Class<?>[] keyClasses11 = {Long.class, String.class};
				Class<?>[] valueClasses11 = {Map.class, Object.class};
				returnParameters.setMap("action.GetUsers.UserProfiles", entitiesToMaps(userProfilesList), keyClasses11, valueClasses11);
			}

			// ----- End Task 1 -----------------------------------------------

			if (errors.size() > 0) return errors;
			response.setParameters(returnParameters);
			return null;
		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

	/*************************************************************************
	 * Method: login (3 tasks)
	 * Task 1: Read
	 *   Input: String action.Login.Username (username)
	 *   Input: String action.Login.Password (password)
	 * Task 2: Read
	 * Task 3: Custom code
	 *   Output: Map action.Login.User (user.getAllFields())
	 *   Output: Map action.Login.UserRoles (entitiesToMaps(userRoleList))
	 *************************************************************************/
	public List<ErrorReport> login(ActionRequest request, ActionResponse response)
		throws CSWrapperException
	{
		List<ErrorReport> errors = new Vector<ErrorReport>();
		Map<String, List<String>> vldResults = null;
		Parameters ps = request.getParameters();
		Parameters returnParameters = new Parameters();
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// Extract and check input parameters...
			String username =
				request.getParameters().getString("action.Login.Username");
			vldResults = User.validateUsername(username);
			if (vldResults != null)
				errors.add(new ErrorReport(ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem("action.Login.Username", vldResults.get("username")));
			String password =
				request.getParameters().getString("action.Login.Password");
			vldResults = User.validatePassword(password);
			if (vldResults != null)
				errors.add(new ErrorReport(ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem("action.Login.Password", vldResults.get("password")));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			List userList = User.findUserByCondition(manager,
				" WHERE lower(username) = '" + username + "' AND password = '" + password + "'", null);
			if (userList != null && userList.size() != 1)
				errors.add(new ErrorReport(ErrorReport.Source.DATARESULTS,
					"action.Login.InvalidUsernamePassword", request).addItem("action.Login.Username", username)
					.addItem("action.Login.Password", password));
			// --- BEGIN CUSTOM CODE ---
			User user = null;
			if (userList != null && userList.size() > 0)
			{
				user = (User) userList.get(0);

				// check if the user is blocked
				if ((user.getStatus() & User.Status.BLOCKED.value) > 0)
					errors.add(new ErrorReport(ErrorReport.Source.DATARESULTS, 
						"action.Login.UserBlocked", request));
			}
			// --- END CUSTOM CODE ---

			if (errors.size() > 0) return errors;

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute read operation...
			List userRoleList = UserRole.findUserRoleByCondition(manager,
				" WHERE userIdk = '" + user.getIdk() + "'", null);
			if (userRoleList != null && userRoleList.size() < 1)
				errors.add(new ErrorReport(ErrorReport.Source.DATARESULTS,
					"action.Login.UserHasNoRoles", request));
			if (errors.size() > 0) return errors;

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			response.setLoginToken( new LoginToken(user.getIdk()) );
			// in the future, perhaps we will need to load the user's permissions if 
			// the client's user interface is very fine-grained and dependent on
			// those permissions
		
			UserManager.addUser(user);
			// --- END CUSTOM CODE ---
			// Package output parameters...
			Class<?>[] keyClasses31 = {String.class};
			Class<?>[] valueClasses31 = {Object.class};
			returnParameters.setMap("action.Login.User", user.getAllFields(), keyClasses31, valueClasses31);
			Class<?>[] keyClasses32 = {Long.class, String.class};
			Class<?>[] valueClasses32 = {Map.class, Object.class};
			returnParameters.setMap("action.Login.UserRoles", entitiesToMaps(userRoleList), keyClasses32, valueClasses32);

			// ----- End Task 3 -----------------------------------------------

			if (errors.size() > 0) return errors;
			response.setParameters(returnParameters);
			return null;
		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

	/*************************************************************************
	 * Method: logout (1 task)
	 * Task 1: Custom code
	 *************************************************************************/
	public List<ErrorReport> logout(ActionRequest request, ActionResponse response)
		throws CSWrapperException
	{
		List<ErrorReport> errors = new Vector<ErrorReport>();
		Map<String, List<String>> vldResults = null;
		Parameters ps = request.getParameters();
		Parameters returnParameters = new Parameters();
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			UserManager.remove( request.getLoginToken().getUserIdk() );
			response.setLoginToken( new LoginToken() );
			// --- END CUSTOM CODE ---
			// ----- End Task 1 -----------------------------------------------

			if (errors.size() > 0) return errors;
			response.setParameters(returnParameters);
			return null;
		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

}

