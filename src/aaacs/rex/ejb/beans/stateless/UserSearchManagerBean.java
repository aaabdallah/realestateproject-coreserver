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
import aaacs.rex.ejb.beans.stateless.interfaces.UserSearchManager;

@Stateless
@Local(UserSearchManager.class)
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
public class UserSearchManagerBean extends BaseSessionBean implements UserSearchManager
{
	@PersistenceContext(unitName="CoreServer") private EntityManager manager;

	/*************************************************************************
	 * Method: "action.SearchUsers", v1 (1 task)
	 * Task 1: Custom code
	 *   Input: String action.SearchUsers.SortOrder (sortOrder)
	 *   Input: Map<String, Object> action.SearchUsers.UserSearchFields (userSearchFields)
	 *   Input: Map<String, Object> action.SearchUsers.UserProfileSearchFields (userProfileSearchFields)
	 *   Input: Map<String, Object> action.SearchUsers.UserRankFields (userRankSearchFields)
	 *   Input: Map<String, Object> action.SearchUsers.UserRoleFields (userRoleSearchFields)
	 *   Output: Map action.SearchUsers.Users (userRows)
	 *   Output: Map action.SearchUsers.UserProfiles (userProfileRows)
	 *   Output: Map action.SearchUsers.UserRanks (userRankRows)
	 *   Output: Map action.SearchUsers.UserRoles (userRolesRows)
	 *************************************************************************/
	public List<ErrorReport> searchUsers(ActionRequest request, ActionResponse response)
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
			String sortOrder =
				inputParameters.getString("action.SearchUsers.SortOrder");
			Map<String, Object> userSearchFields =
				inputParameters.getMap("action.SearchUsers.UserSearchFields");
			if (userSearchFields != null)
			{
				Class<?>[] keyClassesIn12 = {String.class};
				Class<?>[] valueClassesIn12 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchUsers.UserSearchFields").
					checkMap(userSearchFields, keyClassesIn12, valueClassesIn12);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> userProfileSearchFields =
				inputParameters.getMap("action.SearchUsers.UserProfileSearchFields");
			if (userProfileSearchFields != null)
			{
				Class<?>[] keyClassesIn13 = {String.class};
				Class<?>[] valueClassesIn13 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchUsers.UserProfileSearchFields").
					checkMap(userProfileSearchFields, keyClassesIn13, valueClassesIn13);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> userRankSearchFields =
				inputParameters.getMap("action.SearchUsers.UserRankFields");
			if (userRankSearchFields != null)
			{
				Class<?>[] keyClassesIn14 = {String.class};
				Class<?>[] valueClassesIn14 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchUsers.UserRankFields").
					checkMap(userRankSearchFields, keyClassesIn14, valueClassesIn14);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> userRoleSearchFields =
				inputParameters.getMap("action.SearchUsers.UserRoleFields");
			if (userRoleSearchFields != null)
			{
				Class<?>[] keyClassesIn15 = {String.class};
				Class<?>[] valueClassesIn15 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchUsers.UserRoleFields").
					checkMap(userRoleSearchFields, keyClassesIn15, valueClassesIn15);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			SelectBuilder sbUsers = new SelectBuilder();
			List userResults = null;

			sbUsers.addSelect("\"tUsers\".\"timeCreated\" AS \"userTimeCreated\", \"tUsers\".\"timeLastModified\" AS \"userTimeLastModified\"");
			sbUsers.addSelect("\"tUsers\".idk AS \"userIdk\", username, password, status, \"rankIdk\", notes, \"systemKey\", \"testUser\"");
			sbUsers.addFrom("\"tUsers\"");
			if (userSearchFields != null)
				User.applySearchFields(userSearchFields, sbUsers);
			sbUsers.addWhere("(\"tUsers\".\"metaFlags\" & 1) = 0", true);
			
			sbUsers.addSelect("\"tUserProfiles\".idk AS \"userProfileIdk\"");
			sbUsers.addSelect("\"titleIdk\", \"firstName\", \"secondName\", \"thirdName\", \"fourthName\", \"dateOfBirth\"");
			sbUsers.addSelect("\"streetAddress1\", \"streetAddress2\", \"districtIdk\", \"cityIdk\", \"regionIdk\", \"countryIdk\"");
			sbUsers.addSelect("\"postalCode\", \"postOfficeBox\", \"telephone\", \"mobile\", \"fax\", \"email\"");
			sbUsers.addSelect("\"occupationIdk\", \"organization\", \"organizationPosition\", \"displayCustomLogo\", \"displayCustomOrganization\"");
			sbUsers.addFrom("\"tUserProfiles\"");
			if (userProfileSearchFields != null)
				UserProfile.applySearchFields(userProfileSearchFields, sbUsers);
			sbUsers.addWhere("\"tUsers\".idk > 999 AND \"tUsers\".idk = \"tUserProfiles\".\"userIdk\"", true);

			// rank, roles
			sbUsers.addSelect("\"tRanks\".name AS \"rankName\", \"tRanks\".description AS \"rankDescription\", priority");
			sbUsers.addFrom("\"tRanks\"");
			if (userRankSearchFields != null)
				Rank.applySearchFields(userRankSearchFields, sbUsers);
			sbUsers.addWhere("\"tUsers\".\"rankIdk\" = \"tRanks\".\"idk\"", true);

			sbUsers.addSelect("\"tUsersRoles\".\"roleIdk\" AS \"roleIdk\", \"tRoles\".name AS \"roleName\", \"tRoles\".description AS \"roleDescription\"");
			sbUsers.addFrom("\"tRoles\", \"tUsersRoles\"");
			if (userRoleSearchFields != null)
				Role.applySearchFields(userRoleSearchFields, sbUsers);
			sbUsers.addWhere("\"tUsers\".idk = \"tUsersRoles\".\"userIdk\" AND \"tRoles\".idk = \"tUsersRoles\".\"roleIdk\"", true);

			if (sortOrder == null || sortOrder.equalsIgnoreCase("tlmdes"))
				sbUsers.addOrder("\"userTimeLastModified\" DESC");
			else if (sortOrder.equalsIgnoreCase("tlmasc"))
				sbUsers.addOrder("\"userTimeLastModified\" ASC");
			else if (sortOrder.equalsIgnoreCase("tcdes"))
				sbUsers.addOrder("\"userTimeCreated\" DESC");
			else if (sortOrder.equalsIgnoreCase("tcasc"))
				sbUsers.addOrder("\"userTimeCreated\" ASC");

			System.out.println("\n\n\n" + sbUsers.toString() + "\n\n\n\n");
			
			userResults = BaseEntity.findByQuery(false, manager, sbUsers, null, null);

			Map<Long, Map<String,Object>> userRows = new LinkedHashMap<Long, Map<String,Object>>();
			Map<Long, Map<String,Object>> userProfileRows = new LinkedHashMap<Long, Map<String,Object>>();
			Map<Long, Map<String,Object>> userRankRows = new LinkedHashMap<Long, Map<String,Object>>();
			Map<Long, Map<Long, Map<String,Object>>> userRolesRows = new LinkedHashMap<Long, Map<Long, Map<String,Object>>>();
			if (userResults != null && userResults.size() > 0)
			{
				List<String> columnNames = null;
				columnNames = sbUsers.getColumnNames(true);

				for (Object entry : userResults)
				{
					List rowValues = (List) entry;
					Long rowKey = null;
					
					if (rowValues.size() != columnNames.size())
						throw new java.lang.IndexOutOfBoundsException("Row size does not match column names size");
					
					LinkedHashMap<String, Object> rowMap = new LinkedHashMap<String, Object>();
					for (int i=0; i<columnNames.size(); i++)
					{
						if (columnNames.get(i).equals("userIdk"))
							rowKey = (Long) rowValues.get(i);
						rowMap.put(columnNames.get(i), rowValues.get(i));
					}

					// Pull out the role first
					Map<String,Object> userRoleRow = (new Role(rowMap)).getAllFields();
					userRoleRow.put("idk", rowMap.get("roleIdk"));
					userRoleRow.put("name", rowMap.get("roleName"));
					userRoleRow.put("description", rowMap.get("roleDescription"));

					// Has this user has already been seen (in a different role)?
					if (userRows.containsKey(rowKey))
					{
						// Then just add the new role
						Map<Long, Map<String, Object>> existingRoles = userRolesRows.get(rowKey);
						existingRoles.put((Long) rowMap.get("roleIdk"), userRoleRow);
					}
					else
					{
						// Otherwise add the new user, user profile, rank, and (first) role (of possibly many)
						Map<String,Object> userRow = (new User(rowMap)).getAllFields();
						userRow.put("idk", rowMap.get("userIdk"));
						userRow.put("timeCreated", rowMap.get("userTimeCreated"));
						userRow.put("timeLastModified", rowMap.get("userTimeLastModified"));
						
						Map<String,Object> userProfileRow = (new UserProfile(rowMap)).getAllFields();
						userProfileRow.put("idk", rowMap.get("userProfileIdk"));
						
						Map<String,Object> userRankRow = new LinkedHashMap<String, Object>();
						userRankRow.put("idk", rowMap.get("rankIdk"));
						userRankRow.put("name", rowMap.get("rankName"));
						userRankRow.put("description", rowMap.get("rankDescription"));
						userRankRow.put("priority", ((Number) rowMap.get("priority")).shortValue());
						
						userRows.put(rowKey, userRow);
						userProfileRows.put(rowKey, userProfileRow);
						userRankRows.put(rowKey, userRankRow);
						
						Map<Long, Map<String, Object>> newRoles = new LinkedHashMap<Long, Map<String,Object>>();
						newRoles.put((Long) rowMap.get("roleIdk"), userRoleRow);
						userRolesRows.put(rowKey, newRoles);
					}
				}
			}

			if (userRows != null)
			{
				for (Long key : userRows.keySet())
				{
					StringBuffer values = new StringBuffer("");
					values.append("\n\nUser: " + key + "\n");
					Map<String, Object> row = userRows.get(key);
					
					for (String name : row.keySet())
						values.append("\t" + name + " = " + row.get(name) + "\n");
					System.out.println(values.toString() + "\n\n\n\n");
				}
			}
			// --- END CUSTOM CODE ---
			// Package output parameters...
			if (userRows != null && userRows.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.SearchUsers.Users", userRows, keyClassesOut11, valueClassesOut11);
			}
			if (userProfileRows != null && userProfileRows.size() > 0)
			{
				Class<?>[] keyClassesOut12 = {Long.class, String.class};
				Class<?>[] valueClassesOut12 = {Map.class, Object.class};
				outputParameters.setMap("action.SearchUsers.UserProfiles", userProfileRows, keyClassesOut12, valueClassesOut12);
			}
			if (userRankRows != null && userRankRows.size() > 0)
			{
				Class<?>[] keyClassesOut13 = {Long.class, String.class};
				Class<?>[] valueClassesOut13 = {Map.class, Object.class};
				outputParameters.setMap("action.SearchUsers.UserRanks", userRankRows, keyClassesOut13, valueClassesOut13);
			}
			if (userRolesRows != null && userRolesRows.size() > 0)
			{
				Class<?>[] keyClassesOut14 = {Long.class, Long.class, String.class};
				Class<?>[] valueClassesOut14 = {Map.class, Map.class, Object.class};
				outputParameters.setMap("action.SearchUsers.UserRoles", userRolesRows, keyClassesOut14, valueClassesOut14);
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

}

