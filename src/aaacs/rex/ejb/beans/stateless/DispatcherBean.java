/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.rex.ejb.beans.stateless;

import java.util.List;
import javax.naming.InitialContext;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import aaacs.coreserver.ejb.beans.stateless.CoreServerDispatcherBean;
import aaacs.coreserver.commons.communication.ActionRequest;
import aaacs.coreserver.commons.communication.ActionResponse;
import aaacs.coreserver.commons.communication.ErrorReport;
import aaacs.coreserver.commons.exceptions.CoreServerException;
import aaacs.rex.ejb.beans.stateless.interfaces.Dispatcher;
import aaacs.rex.ejb.beans.stateless.interfaces.CoreServerBridge;
import aaacs.rex.ejb.beans.stateless.interfaces.AccessManager;
import aaacs.rex.ejb.beans.stateless.interfaces.PropertyManager;
import aaacs.rex.ejb.beans.stateless.interfaces.PropertySearchManager;
import aaacs.rex.ejb.beans.stateless.interfaces.PropertyUpdateManager;
import aaacs.rex.ejb.beans.stateless.interfaces.UserSearchManager;

@Stateless
@Remote(Dispatcher.class)
@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
@EJBs
(
	value =
	{
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
public class DispatcherBean extends CoreServerDispatcherBean implements Dispatcher
{
	public List<ErrorReport> dispatch(ActionRequest request, ActionResponse response)
		throws CoreServerException
	{
		CoreServerBridge coreServerBridge = null;
		AccessManager accessManager = null;
		PropertyManager propertyManager = null;
		PropertySearchManager propertySearchManager = null;
		PropertyUpdateManager propertyUpdateManager = null;
		UserSearchManager userSearchManager = null;

		try
		{
			InitialContext ic = new InitialContext();
			coreServerBridge = (CoreServerBridge) ic.lookup("java:comp/env/ejb/CoreServerBridge");
			accessManager = (AccessManager) ic.lookup("java:comp/env/ejb/AccessManager");
			propertyManager = (PropertyManager) ic.lookup("java:comp/env/ejb/PropertyManager");
			propertySearchManager = (PropertySearchManager) ic.lookup("java:comp/env/ejb/PropertySearchManager");
			propertyUpdateManager = (PropertyUpdateManager) ic.lookup("java:comp/env/ejb/PropertyUpdateManager");
			userSearchManager = (UserSearchManager) ic.lookup("java:comp/env/ejb/UserSearchManager");
		}
		catch (Exception e)
		{
			throw new CoreServerException("jndi.LookupError", e);
		}

		if (request.getActionName().equals("action.LookupTableFromCache") &&
			request.getActionVersion() == 1)
			return coreServerBridge.lookupTableFromCache(request, response);
		else if (request.getActionName().equals("action.LookupTableColumnFromCache") &&
			request.getActionVersion() == 1)
			return coreServerBridge.lookupTableColumnFromCache(request, response);
		else if (request.getActionName().equals("action.AddUser") &&
			request.getActionVersion() == 1)
			return accessManager.addUser(request, response);
		else if (request.getActionName().equals("action.CreateUser") &&
			request.getActionVersion() == 1)
			return accessManager.createUser(request, response);
		else if (request.getActionName().equals("action.CreateUsers") &&
			request.getActionVersion() == 1)
			return accessManager.createUsers(request, response);
		else if (request.getActionName().equals("action.DeleteUsers") &&
			request.getActionVersion() == 1)
			return accessManager.deleteUsers(request, response);
		else if (request.getActionName().equals("action.DeleteUsersByFieldSet") &&
			request.getActionVersion() == 1)
			return accessManager.deleteUsersByFieldSet(request, response);
		else if (request.getActionName().equals("action.FindAllProfileDistricts") &&
			request.getActionVersion() == 1)
			return accessManager.findAllProfileDistricts(request, response);
		else if (request.getActionName().equals("action.FindUserProfilesByUserIdks") &&
			request.getActionVersion() == 1)
			return accessManager.findUserProfilesByUserIdks(request, response);
		else if (request.getActionName().equals("action.FindUsersByIdks") &&
			request.getActionVersion() == 1)
			return accessManager.findUsersByIdks(request, response);
		else if (request.getActionName().equals("action.Login") &&
			request.getActionVersion() == 1)
			return accessManager.login(request, response);
		else if (request.getActionName().equals("action.Logout") &&
			request.getActionVersion() == 1)
			return accessManager.logout(request, response);
		else if (request.getActionName().equals("action.UpdateUser") &&
			request.getActionVersion() == 1)
			return accessManager.updateUser(request, response);
		else if (request.getActionName().equals("action.UpdateUserProfiles") &&
			request.getActionVersion() == 1)
			return accessManager.updateUserProfiles(request, response);
		else if (request.getActionName().equals("action.UpdateUsers") &&
			request.getActionVersion() == 1)
			return accessManager.updateUsers(request, response);
		else if (request.getActionName().equals("action.UpdateUsersByFieldSet") &&
			request.getActionVersion() == 1)
			return accessManager.updateUsersByFieldSet(request, response);
		else if (request.getActionName().equals("action.UpdateUserWithProfile") &&
			request.getActionVersion() == 1)
			return accessManager.updateUserWithProfile(request, response);
		else if (request.getActionName().equals("action.CreateApartmentForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createApartmentForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateEmptyLotForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createEmptyLotForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateFarmLotForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createFarmLotForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateHouseForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createHouseForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateMultiUnitBuildingForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createMultiUnitBuildingForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateMultiUnitCompoundForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createMultiUnitCompoundForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateOfficeForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createOfficeForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateFloorUnitForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createFloorUnitForRentalSale(request, response);
		else if (request.getActionName().equals("action.CreateStoreForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createStoreForRentalSale(request, response);
		else if (request.getActionName().equals("action.FindAllPropertyDistricts") &&
			request.getActionVersion() == 1)
			return propertyManager.findAllPropertyDistricts(request, response);
		else if (request.getActionName().equals("action.FindPropertyDistricts") &&
			request.getActionVersion() == 1)
			return propertyManager.findPropertyDistricts(request, response);
		else if (request.getActionName().equals("action.FindCommercialFeaturesByCreator") &&
			request.getActionVersion() == 1)
			return propertyManager.findCommercialFeaturesByCreator(request, response);
		else if (request.getActionName().equals("action.FindCommunityAmenitiesByCreator") &&
			request.getActionVersion() == 1)
			return propertyManager.findCommunityAmenitiesByCreator(request, response);
		else if (request.getActionName().equals("action.FindPropertyOptionsByCategory") &&
			request.getActionVersion() == 1)
			return propertyManager.findPropertyOptionsByCategory(request, response);
		else if (request.getActionName().equals("action.FindPropertyOptionByCategoryName") &&
			request.getActionVersion() == 1)
			return propertyManager.findPropertyOptionByCategoryName(request, response);
		else if (request.getActionName().equals("action.FindResidentialFeaturesByCreator") &&
			request.getActionVersion() == 1)
			return propertyManager.findResidentialFeaturesByCreator(request, response);
		else if (request.getActionName().equals("action.CreatePropertiesForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyManager.createPropertiesForRentalSale(request, response);
		else if (request.getActionName().equals("action.SearchProperties") &&
			request.getActionVersion() == 1)
			return propertySearchManager.searchProperties(request, response);
		else if (request.getActionName().equals("action.RetrieveProperty") &&
			request.getActionVersion() == 1)
			return propertySearchManager.retrieveProperty(request, response);
		else if (request.getActionName().equals("action.DeactivateListing") &&
			request.getActionVersion() == 1)
			return propertySearchManager.deactivateListing(request, response);
		else if (request.getActionName().equals("action.UpdateApartmentForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateApartmentForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateEmptyLotForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateEmptyLotForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateFarmLotForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateFarmLotForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateFloorUnitForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateFloorUnitForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateHouseForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateHouseForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateMultiUnitBuildingForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateMultiUnitBuildingForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateMultiUnitCompoundForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateMultiUnitCompoundForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateOfficeForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateOfficeForRentalSale(request, response);
		else if (request.getActionName().equals("action.UpdateStoreForRentalSale") &&
			request.getActionVersion() == 1)
			return propertyUpdateManager.updateStoreForRentalSale(request, response);
		else if (request.getActionName().equals("action.SearchUsers") &&
			request.getActionVersion() == 1)
			return userSearchManager.searchUsers(request, response);
		else
		{
			Object args[] = { request.getActionName(), request.getActionVersion() };
			throw new CoreServerException("action.UnableToDispatchAction", args);
		}
	}

	public boolean actionRequiresLoggedInUser(ActionRequest request)
	{
		try
		{
			if (request.getActionName().equals("action.LookupTableFromCache") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.LookupTableColumnFromCache") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindAllProfileDistricts") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.Login") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindAllPropertyDistricts") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindPropertyDistricts") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindCommercialFeaturesByCreator") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindCommunityAmenitiesByCreator") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindPropertyOptionsByCategory") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindPropertyOptionByCategoryName") &&
				request.getActionVersion() == 1)
				return false;
			else if (request.getActionName().equals("action.FindResidentialFeaturesByCreator") &&
				request.getActionVersion() == 1)
				return false;
		}
		catch (Exception e) {}
		return true; // assume the worst: force the tighter check
	}
}
