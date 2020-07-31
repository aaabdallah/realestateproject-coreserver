package aaacs.coreserver.ejb.beans;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import aaacs.coreserver.administration.Configurator;
import aaacs.coreserver.administration.UserManager;
import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.communication.LoginToken;
import aaacs.coreserver.commons.exceptions.CoreServerException;
import aaacs.coreserver.database.PrimaryKeyHolder;
import aaacs.coreserver.ejb.entities.Action;
import aaacs.coreserver.ejb.entities.BaseEntity;
import aaacs.coreserver.ejb.entities.Permission;
import aaacs.coreserver.ejb.entities.User;
import aaacs.coreserver.ejb.entities.UserRole;
import aaacs.coreserver.ejb.entities.support.FieldsConstraint;

/**
 * @author Ahmed A. Abd-Allah
 * Created on Nov 15, 2006
 *
 * This is the base class for all session beans in the Core Server, stateless and
 * stateful. It contains some helpful methods that are useful to all session beans.
 */
public class BaseSessionBean
{
	/**
	 * This method should always be called from the derived session beans prior 
	 * to actually executing any action. Depending on what this method returns, 
	 * the action should either proceed or be halted.
	 * This method also attempts to fill in the passed in response object with
	 * fields that depend on the request. Depending on how far this method gets,
	 * the response will only be partially filled in, to what extent is variable.
	 * The affected fields of the response: locale, login token, and transmission
	 * counter.
	 * 
	 * @param request the action request object
	 * @param response the (matching) response object
	 * @param userIsLoggedIn whether to treat the user of the action as logged in or not

	 * @return an ErrorReport indicating the status of the check - null indicates success.
	 */
	protected ErrorReport checkBeforeExecuteAction(ActionRequest request, 
		ActionResponse response, boolean userIsLoggedIn)
	{
		ErrorReport error = 
			new ErrorReport("error.System.CoreServer", ErrorReport.Source.ACTIONFORMAT, "action.PreActionError");

		try
		{
			// Check if the request is null
			if (request == null)
			{
				response.setLocale(Configurator.getDefaultLocale());
				return error.addItem("action.NullInvalidActionRequest");
			}

			// Set the response to match the request locale-wise
			response.setLocale(request.getLocale());

			// Immediately try to at least deal with the response login token so that
			// errors will not lead to an invalid login token being sent back
			try
			{
				LoginToken token = request.getLoginToken();
				response.setLoginToken(token);
				
				// IMPORTANT: "userIsLoggedIn" will only be true for actions that REQUIRE
				// the user to be logged in. So even if the user *is* logged in but s/he
				// invokes an action that does not require a logged in user, this snippet
				// of code below will not be executed.
				if (userIsLoggedIn)
				{
					// Check if the token is well formatted
					//if (!token.isWellFormatted())
					if (!PrimaryKeyHolder.isUserSuppliedPrimaryKey(token.getUserIdk()) ||
						!token.isValidVersion())
					{
						response.setLoginToken(new LoginToken()); // send back a cancelled login token
						return error.addItem("action.InvalidLoginToken");
					}
					error.setUserIdk(token.getUserIdk());

					// Check if the user is recognized
					long userIdk = token.getUserIdk();
					User user = UserManager.getUser(userIdk);
					if (user == null)
					{
						response.setLoginToken(new LoginToken()); // send back a cancelled login token
						return error.addItem("action.UserNotLoggedIn");
					}
					// For code that checks against the database see commented code after this method

					// Check if the token is expired
					if (!token.isActive())
					{
						UserManager.removeUser(userIdk);
						response.setLoginToken(new LoginToken()); // send back a cancelled login token
						return error.addItem("action.LoginTokenExpired");
					}

					// Check if the system is accessible to logged in users
					if (!Configurator.isSystemAccessible(user))
						return error.addItem("action.SystemBlocked");
					
					// Check if the user is blocked
					if ((user.getStatus() & User.Status.BLOCKED.value) > 0)
						return error.addItem("action.UserBlocked");

					// Refresh the login token
					response.setLoginToken(token.refreshTokenTime());
				}
				else
				{
					// Refresh the login token - it might be that this is an
					// action that does not require a logged in user, but nonetheless
					// the user is logged in. It seems unfair to penalize him/her
					// and not refresh the token as a result.
					if (!token.isCancelled())
						response.setLoginToken(token.refreshTokenTime());
					// Check if the system is accessible to guests
					if (!Configurator.isSystemAccessible(null))
						return error.addItem("action.SystemBlocked");				
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			// The order of checks is approximately from quickest check to slowest

			// Check if the request has a well-formatted name & version
			if (!request.hasWellFormattedActionNameVersion())
				return error.addItem("action.IncompleteActionRequest");
			
			error.setActionName(request.getActionName());
			error.setActionVersion(request.getActionVersion());
			error.setActionTime(System.currentTimeMillis());

			// Check if the requested action/version are recognized
			String actionName = request.getActionName();
			short actionVersion = request.getActionVersion();
			Hashtable<String, Object> searchParameters = new Hashtable<String, Object>();
			searchParameters.put("name", actionName);
			searchParameters.put("actionVersion", actionVersion);
			Map<Long, Map<String, Object>> records = 
				Action.cacheLookup(searchParameters);
			if (records == null || records.size() <= 0)
				return error.addItem("action.UnrecognizedAction");

			// indicate the *request* has been xmitted. The receiver of the
			// response needs to correctly deal with this (yet another
			// reason to include a delegate layer in the application...)
			response.incrementTxCounter(); 
			return null; // All checks passed
		}
		catch (Exception e)
		{
			pst(e);
			return error.addItem("action.UnknownPreActionError");
			// throw new CoreServerException("action.UnknownPreActionError", e);
		}
	}
	/* In the previous method:
	 * If checking against the database is desired, here is the code:
	 * But notice it contains a dependency on a DERIVED class... it
	 * works but it is not good design.
	CoreServerAdministrator csa =
		(CoreServerAdministrator) 
		lookup("aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator");
	User user = csa.readUser(userIdk);
	if (user == null)
		return "action.UserNonexistent";
	 */

	/**
	 * This method is for checking if the user (assignee) has permission to access
	 * an action on an object. Read the method's comments to understand it.
	 * @param manager the entity manager to use
	 * @param assigneeIdk the user/assignee to check for
	 * @param actionName action name
	 * @param actionVersion action version
	 * @param objectTableName the object's table name
	 * @param objectIdk the object's primary key
	 * @param objectGroup the group
	 * @param objectParameters the object's parameters (checked against the permission's constraints)
	 * @return true if authorized, false if not
	 * @throws CoreServerException
	 */
	protected boolean isAuthorized(EntityManager manager,
		Long assigneeIdk, Long assigneeRankIdk, List<UserRole> assigneeUserRoles,
		String actionName, Short actionVersion, 
		Long objectGroupIdk, String objectTableName, 
		Long objectIdk, Hashtable<String, Object> objectParameters)
		throws CoreServerException
	{
		List permissions;
		Vector<Long> assigneeRoleIdks = new Vector<Long>();

		try
		{
			// This method should always be called in the context of an
			// already running transaction, to be sure of the latest
			// Permissions in the database. We cannot check explicitly
			// however since this method might be called from within a
			// container-managed transaction bean. So it is the caller's
			// responsibility.
			//if (!transactionInProgress(txInProgress))
			//	throw new CoreServerException("permission.AuthorizationCheckWithoutTransaction");
			
			// If the assignee is a specific user, make sure that we don't proceed unless we
			// have his/her real rank and roles. The passed in information is ignored.
			if (assigneeIdk != null && assigneeIdk > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
			{
				assigneeRankIdk = User.findByIdk(manager, assigneeIdk).getRankIdk();
				assigneeUserRoles = 
					UserRole.findUserRoleByCondition(manager, " WHERE \"userIdk\" = '" + assigneeIdk + "'", null);
			}
			else
			{
				// If the assignee in question is unknown (e.g. -1 in the case of Actions
				// initiated for guest users), then assume that the permission being sought
				// is a global one for *all* users.
				assigneeIdk = PrimaryKeyHolder.KEY_MATCHES_USER_KEYS;
				
				// In such a case, if the assigneeRankIdk also came in unknown, assume
				// that what is meant are permissions where the rank is supposed to match
				// all users (all ranks in other words)
				if (assigneeRankIdk == null || assigneeRankIdk < PrimaryKeyHolder.KEY_LOWEST_RESERVED)
					assigneeRankIdk = PrimaryKeyHolder.KEY_MATCHES_USER_KEYS;

				// Similarly for the user roles
				if (assigneeUserRoles == null || assigneeUserRoles.isEmpty())
				{
					assigneeUserRoles = new Vector<UserRole>();
					UserRole userRole = new UserRole();
					userRole.setRoleIdk(PrimaryKeyHolder.KEY_MATCHES_USER_KEYS);
					assigneeUserRoles.add(userRole);
				}
			}
			
			// By this point, all three should be NON-null: assigneeIdk, assigneeRankIdk, assigneeUserRoles
			
			if (assigneeUserRoles.isEmpty())
				throw new CoreServerException("permission.UserHasNoRoles");
			else
			{
				for (Object userRole : assigneeUserRoles)
					assigneeRoleIdks.add( ((UserRole) userRole).getRoleIdk() );
			}

			/*
			if (Configurator.getDebugLevel() == 10)
			{
				System.out.println("Attempting to find matching permissions for: ");
				System.out.println("Assignee: " + assigneeIdk);
				System.out.println("Assignee rank: " + assigneeRankIdk);
				System.out.println("Action name: " + actionName);
				System.out.println("Action version: " + actionVersion);
				System.out.println("Object table: " + objectTableName);
				System.out.println("Object ID: " + objectIdk);
				System.out.println("Object group ID: " + objectGroupIdk);
				Iterator<UserRole> mappingsIterator = assigneeUserRoleMappings.iterator();
				while (mappingsIterator.hasNext())
					System.out.println("Assignee role: " + 
					((UserRole) mappingsIterator.next()).getRoleIdk());
			}
			*/

			permissions = Permission.findByRelevantFields(manager, assigneeIdk, assigneeRankIdk, assigneeRoleIdks, actionName,
				actionVersion, objectGroupIdk, objectTableName, objectIdk); 

			/*
			if (Configurator.getDebugLevel() == 10)
			{
				if (permissions.isEmpty())
					System.out.println("Empty set of permissions when finding by core fields");
				else
					System.out.println("Found a set of permissions of size: " + permissions.size());
			}
			*/

			// Assume we have a set of records (each record being a particular permission
			// that matches the input parameters).  The order of precedence can be summarized
			// as being "most specific match wins".
			// The algorithm for checking the permissions embodies a certain method for
			// resolving any conflicts between the stored permissions. This algorithm is OPEN
			// TO DEBATE and may need to be changed. See the class PermissionBean for how
			// the priorityLevel of permissions is computed based on the permission's other
			// attributes.

			
			// ----------------------------------------------------------------
			// Everything from this line to the next line is commented out since EJB 3.0
			// supports the "ORDER BY" clause... right?
			/*
			Iterator resultsIterator = permissions.iterator();

			// Use a treemap to sort the permissions in descending order (the comparator
			// is built to sort in descending order).
			// Note that the SQL query that is being used does have an "ORDER BY" clause in 
			// it, but this is not guaranteed to be supported within the EJB specification: 
			// we do not know the order of the *Collection* that the findByCoreFields method 
			// returns. Too bad, eh? 
			TreeMap tree = new TreeMap(new PermissionLocalComparator());

			while (resultsIterator.hasNext())
			{
				PermissionLocal permissionLocal = (PermissionLocal) resultsIterator.next();
				tree.put(permissionLocal, "irrelevant");
			}
			*/
			// If we found matching permissions, they should be ordered properly in the
			// tree. Take the closest matches with identical matching levels, and return with
			// the most pessimistic status - i.e. if one says DENIED and 99 say GRANTED,
			// return DENIED.
			if (!permissions.isEmpty())
			{
				/*
				if (Configurator.getDebugLevel() == 10)
				{
					for (Object oPMP : permissions)
					{
						Permission permission = (Permission) oPMP;
						System.out.println(" ---- Permission ---- ");
						System.out.println(" Strength: " + permission.getStrength());
						System.out.println(" Idk: " + permission.getIdk());
						System.out.println(" AssignerRankIdk: " + permission.getAssignerRankIdk());
						System.out.println(" AssigneeRankIdk: " + permission.getAssigneeRankIdk());
						System.out.println(" AssigneeRoleIdk: " + permission.getAssigneeRoleIdk());
						System.out.println(" ActionName: " + permission.getActionName());
						System.out.println(" ObjectIdk: " + permission.getObjectIdk());
						System.out.println(" ObjectConstraint: " + permission.getObjectConstraint());
						System.out.println(" Status: " + permission.getStatus());
					}
				}
				*/

				// prepare to store the priority level of the highest priority permission 
				// matching the passed in parameters...
				long highestStrength = -1; 

				// in the following loop, we will first look for the first matching permission.
				// Matching in the sense that it matches the parameters passed into this function
				// (the actionName, objectIdk, etc.). Note that matching also includes the constraint
				// but since the constraint cannot be checked at the database level, we have to
				// include code to check the permission's constraint.
				// After we find our first matching permission, store that permission's priority
				// level - it becomes our 'reference' highestStrength.
				// After we have found our highestStrength, then as we loop through the
				// permissions, we check the current permission's priority level against the
				// reference. If it is not the same, then we exit - recall the tree is sorted.
				// If it is the same, then check if that permission is constrained - if so,
				// confirm that the object matches the constraint, otherwise ignore the permission.
				// If not constrained or if constrained with a matching object, then check that
				// permission if it is granted or denied, along with its ancestors if any.
				for (Object oPMP : permissions)
				//while (keysIterator.hasNext())
				{
					Permission possibleMatchingPermission = (Permission) oPMP;
					FieldsConstraint objectConstraint = 
						new FieldsConstraint(possibleMatchingPermission.getObjectConstraint());

					if (highestStrength == -1) // we haven't found a single matching permission yet
					{					   
						if (objectConstraint.isValid()) // constraint on permission is valid, so check it
						{
							// ...check it against the passed in hashtable of objectParameters
							if (objectConstraint.evaluate(objectParameters))
							{
								// if it evaluates true, then we found our first matching permission 
								highestStrength = possibleMatchingPermission.getStrength();
							}
						}
						else // constraint on the permission is empty or uninitialized...so it matches
						{
							highestStrength = possibleMatchingPermission.getStrength();
						}
					}
					// System.out.println("1000 " + highestStrength);					
					// if after checking the current possible matching permission, we still
					// don't have a match, then go on to the next permission
					if (highestStrength == -1)
					{
						continue;
					}
					// System.out.println("2000 " + highestStrength);					

					// if the current permission in the set has a different (lower) priority from 
					// the highest matching priority, we can stop checking: all the remaining 
					// permissions are of lower priority (remember, the tree's sorted).
					if ( possibleMatchingPermission.getStrength() != highestStrength )
					{
						break;
					}
					// System.out.println("3000 " + highestStrength + " " + possibleMatchingPermission.getStrength());
					// if we reach here, then we have already found at least one permission that
					// matches, so we have a valid highestStrength. We now check the current
					// permission at hand: is it constrained? If yes, then does the object
					// passed in match that permission - if not then ignore that permission,
					// otherwise check that permission. If no, then check that permission likewise.
					if (!objectConstraint.isValid() ||
						(objectConstraint.isValid() && objectConstraint.evaluate(objectParameters)))
					{
						if( possibleMatchingPermission.getStatus().shortValue() == Permission.Status.GRANTED.value.shortValue() )
						{
							// need to check the closest match's ancestor(s) permission (if any)
							// (will use the variable possibleMatchingPermission to traverse the chain of ancestors here)
							while ( 
								PrimaryKeyHolder.isUserSuppliedPrimaryKey(possibleMatchingPermission.getDependsUponIdk()) )
							{
								Permission ancestorPermission = 
									Permission.findByIdk(manager, possibleMatchingPermission.getDependsUponIdk() );
							
								// If a single ancestor represents a non-granted permission, then
								// deny the request.
								if (ancestorPermission.getStatus() != Permission.Status.DENIED.value)
								{
									//System.out.println("3500: RETURNING FALSE");
									return false;
								}
								// Proceed to check the ancestor's ancestor...
								possibleMatchingPermission = ancestorPermission;
							}
						}
						else
						{
							//System.out.println("3750: RETURNING FALSE");
							return false;
						}
					}
					//System.out.println("4000 " + highestStrength + " " + possibleMatchingPermission.getStrength());
				}
				
				//System.out.println("5000 " + highestStrength);
				if (highestStrength == -1) // we never found a single matching permission
					return false;

				// If we reach this stage, then we had a non-empty tree of permissions,
				// and the relevant ones (those that matched at a particular shared priority level)
				// and their ancestors all signified GRANTED as their status.
				return true;
			}
			else
			{
				if (Configurator.getDebugLevel() == 10)
				{
					System.out.println("Empty tree of permissions...");				
				}
			}
			return false;
		}
		catch (Exception e)
		{
			if (e instanceof CoreServerException)
				throw (CoreServerException) e;
			throw new CoreServerException("permission.UnknownPermissionLookupError", e);
		}
	}

	// ------------------------------------------------------------------------
	// ----- JNDI lookup helper methods ---------------------------------------
	// ------------------------------------------------------------------------	
	/**
	 * Exception-masking version of looking up JNDI names from the default
	 * container provided initial context for beans.
	 * Obviously if the lookup fails, you'll quickly get a NullPointerException
	 * which is a RuntimeException and hence doesn't need to be declared as
	 * part of the method's header. Since the NullPointerException is
	 * practically guaranteed to be caused by a bad <code>name</code> to
	 * lookup, there's little harm in masking the NameNotFoundException... 
	 * @param name the JNDI name to lookup
	 * @return the object found, or null if not found
	 */
	public static Object lookup(String name)
	{
		try { Object bean = InitialContext.doLookup(name); return bean; }
		catch (Exception e) { return null; }
	}

	// ------------------------------------------------------------------------
	// ----- Database & Transaction helper methods ----------------------------
	// ------------------------------------------------------------------------	
	/**
	 * Checks if a transaction is active, or in the process of being committed.
	 * @param userTran the transaction to check
	 * @return true if the transaction is in progress
	 */
	public boolean transactionInProgress(UserTransaction userTran)
	{
		try 
		{
			if (userTran != null &&
				(userTran.getStatus() == javax.transaction.Status.STATUS_ACTIVE ||
				userTran.getStatus() == javax.transaction.Status.STATUS_PREPARING ||
				userTran.getStatus() == javax.transaction.Status.STATUS_PREPARED ||
				userTran.getStatus() == javax.transaction.Status.STATUS_COMMITTING ||
				userTran.getStatus() == javax.transaction.Status.STATUS_COMMITTED ||
				userTran.getStatus() == javax.transaction.Status.STATUS_MARKED_ROLLBACK))
				
				return true;
		}
		catch (Exception e) {}

		return false;
	}

	// ------------------------------------------------------------------------
	// ----- Methods for handling entities ------------------------------------
	// ------------------------------------------------------------------------

	/**
	 * @return a map of the entities, keyed by IDK (long), where each entity has
	 * been converted to a map itself (column-value / String-Object).
	 */
	protected Map<Long, Map<String, Object>> entitiesToMaps(List list)
		throws CoreServerException
	{
		if (list == null || list.size() == 0)
			return null;
		LinkedHashMap<Long, Map<String, Object>> entities =
			new LinkedHashMap<Long, Map<String,Object>>();

		for (Object o : list)
		{
			BaseEntity be = (BaseEntity) o;
			entities.put(be.getIdk(), be.getAllFields());
		}
		return entities;
	}

	// ------------------------------------------------------------------------
	// ----- Debugging helper methods -----------------------------------------
	// ------------------------------------------------------------------------
	/**
	 * Turns on/off the debugging methods.
	 */
	private boolean debugging = true;
	protected boolean getDebugging() { return debugging; }
	protected void setDebugging(boolean inDebugging) { debugging = inDebugging; }

	protected void msg(String string) { if (debugging) System.out.println("\n\n\n" + string + "\n\n\n\n"); }
	protected void pst(Throwable t) { if (debugging) t.printStackTrace(); }
}
