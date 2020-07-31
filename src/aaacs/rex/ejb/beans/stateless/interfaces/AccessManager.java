/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.rex.ejb.beans.stateless.interfaces;

import java.util.List;
import javax.ejb.Local;
import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.exceptions.CSWrapperException;

@Local
public interface AccessManager
{
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
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.CreateUser", v1 (1 task)
	 * Task 1: Create
	 *   Input: Map<String, Object> action.CreateUser.UserFields (userFields)
	 *   Output: Map action.CreateUser.User (user.getAllFields())
	 *************************************************************************/
	public List<ErrorReport> createUser(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.CreateUsers", v1 (1 task)
	 * Task 1: Create
	 *   Input: List<Map<String, Object>> action.CreateUsers.UsersFields (usersFields)
	 *   Output: Map action.CreateUsers.Users (entitiesToMaps(userList))
	 *************************************************************************/
	public List<ErrorReport> createUsers(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.DeleteUsers", v1 (1 task)
	 * Task 1: Update
	 *   Input: List<Map<String, Object>> action.DeleteUsers.UsersFields (usersFields)
	 *   Output: Integer action.DeleteUsers.DeletedUsers (affectedEntitiesCounter1)
	 *************************************************************************/
	public List<ErrorReport> deleteUsers(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.DeleteUsersByFieldSet", v1 (1 task)
	 * Task 1: Update
	 *   Input: Map<String, Object> action.DeleteUsersByFieldSet.UserFields (userFields)
	 *   Input: Map<String, Object> action.DeleteUsersByFieldSet.SearchFields (searchFields)
	 *   Output: Integer action.DeleteUsersByFieldSet.DeletedUsers (affectedEntitiesCounter1)
	 *************************************************************************/
	public List<ErrorReport> deleteUsersByFieldSet(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindAllProfileDistricts", v1 (1 task)
	 * Task 1: Custom code
	 *   Output: List action.FindAllProfileDistricts.Districts (list)
	 *************************************************************************/
	public List<ErrorReport> findAllProfileDistricts(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindUserProfilesByUserIdks", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.FindUserProfilesByUserIdks.UserIdks (userIdks)
	 *   Output: Map action.FindUserProfilesByUserIdks.UserProfiles (entitiesToMaps(userProfilesList))
	 *************************************************************************/
	public List<ErrorReport> findUserProfilesByUserIdks(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindUsersByIdks", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.FindUsersByIdks.Idks (idks)
	 *   Output: Map action.FindUsersByIdks.Users (entitiesToMaps(usersList))
	 *************************************************************************/
	public List<ErrorReport> findUsersByIdks(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

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
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.Logout", v1 (1 task)
	 * Task 1: Custom code
	 *************************************************************************/
	public List<ErrorReport> logout(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.UpdateUser", v1 (1 task)
	 * Task 1: Update
	 *   Input: Map<String, Object> action.UpdateUser.UserFields (userFields)
	 *   Output: Map action.UpdateUser.User (user.getAllFields())
	 *************************************************************************/
	public List<ErrorReport> updateUser(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.UpdateUserProfiles", v1 (1 task)
	 * Task 1: Update
	 *   Input: List<Map<String, Object>> action.UpdateUserProfiles.UserProfilesFields (userProfilesFields)
	 *   Output: Map action.UpdateUserProfiles.UserProfiles (entitiesToMaps(userProfileList))
	 *************************************************************************/
	public List<ErrorReport> updateUserProfiles(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.UpdateUsers", v1 (1 task)
	 * Task 1: Update
	 *   Input: List<Map<String, Object>> action.UpdateUsers.UsersFields (usersFields)
	 *   Output: Map action.UpdateUsers.Users (entitiesToMaps(userList))
	 *************************************************************************/
	public List<ErrorReport> updateUsers(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.UpdateUsersByFieldSet", v1 (1 task)
	 * Task 1: Update
	 *   Input: Map<String, Object> action.UpdateUsersByFieldSet.UserFields (userFields)
	 *   Input: Map<String, Object> action.UpdateUsersByFieldSet.SearchFields (searchFields)
	 *   Output: Integer action.UpdateUsersByFieldSet.UpdatedUsers (affectedEntitiesCounter1)
	 *************************************************************************/
	public List<ErrorReport> updateUsersByFieldSet(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

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
		throws CSWrapperException;

}

