package aaacs.coreserver.administration;

import java.util.TreeMap;

import aaacs.coreserver.database.PrimaryKeyHolder;
import aaacs.coreserver.ejb.entities.User;

/**
 * @author Ahmed A. Abd-Allah
 * Created on Nov 26, 2006
 * 
 * This class is for tracking and controlling currently logged in users. Actions
 * to the set of users are synchronized so it is thread-safe.
 */
public class UserManager
{
	// TODO: store the login tokens of each user as well
	// TODO: instead of storing the whole user, what about a subset of their info?
	private static User administrator = null;
	private static TreeMap<Long, User> users = new TreeMap<Long, User>();
	
	public static synchronized void addUser(User user)
	{
		if (user != null && PrimaryKeyHolder.isUserSuppliedPrimaryKey(user.getIdk())) 
			users.put(user.getIdk(), user);
	}
	
	public static synchronized User removeUser(long userIdk)
	{
		return users.remove(userIdk);
	}
	
	public static synchronized void updateUser(User user)
	{
		if (user != null && users.containsKey(user.getIdk()))
		{
			removeUser(user.getIdk());
			addUser(user);
		}
	}
	
	public static synchronized User getUser(long userIdk)
	{
		return users.get(userIdk);
	}
	
	public static synchronized User getAdministrator()
	{
		return administrator;
	}
	
	public static synchronized void setAdministrator(User user)
	{
		administrator = user;
	}
}
