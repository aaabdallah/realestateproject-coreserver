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
public interface UserSearchManager
{
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
		throws CSWrapperException;

}

