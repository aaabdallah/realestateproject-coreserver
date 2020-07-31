/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.rex.ejb.beans.stateless.interfaces;

import javax.ejb.Local;
import aaacs.coreserver.ejb.beans.support.ActionRequest;
import aaacs.coreserver.ejb.beans.support.ActionResponse;
import aaacs.coreserver.ejb.beans.support.ErrorReport;
import aaacs.coreserver.exceptions.CSWrapperException;

@Local
public interface AccessManager
{
	/*************************************************************************
	 * Method: getUserProfiles (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.GetUserProfiles.UserIdks (userIdks)
	 *   Output: Map action.GetUserProfiles.UserProfiles (entitiesToMaps(userProfilesList))
	 *************************************************************************/
	public List<ErrorReport> getUserProfiles(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: getUsers (1 task)
	 * Task 1: Read
	 *   Input: Long[] action.GetUsers.Idks (idks)
	 *   Output: Map action.GetUsers.UserProfiles (entitiesToMaps(userProfilesList))
	 *************************************************************************/
	public List<ErrorReport> getUsers(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

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
		throws CSWrapperException;

	/*************************************************************************
	 * Method: logout (1 task)
	 * Task 1: Custom code
	 *************************************************************************/
	public List<ErrorReport> logout(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

}

