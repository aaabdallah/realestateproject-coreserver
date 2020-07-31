package aaacs.coreserver.ejb.beans.stateless.interfaces;

import java.util.List;

import javax.ejb.Remote;

import aaacs.coreserver.ejb.entities.User;

@Remote public interface CoreServerAdministratorRemote 
{
	public String getSystemParameterValue(String parameterCategory, String parameterName);
	public User readAdministrator();
	public void updatePermissionStrengths();
	public List getAllDistricts();
	public void testMethod();
}
