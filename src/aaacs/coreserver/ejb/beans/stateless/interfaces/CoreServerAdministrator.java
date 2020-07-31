package aaacs.coreserver.ejb.beans.stateless.interfaces;

import java.util.List;

import javax.ejb.Local;

import aaacs.coreserver.ejb.entities.User;

@Local public interface CoreServerAdministrator 
{
	public String getSystemParameterValue(String parameterCategory, String parameterName);
	public User readAdministrator();
	public void updatePermissionStrengths();
	public List getAllDistricts();
	public void testMethod();
}
