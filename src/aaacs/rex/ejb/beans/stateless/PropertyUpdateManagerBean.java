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
import aaacs.rex.ejb.beans.stateless.interfaces.PropertyUpdateManager;

@Stateless
@Local(PropertyUpdateManager.class)
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
public class PropertyUpdateManagerBean extends BaseSessionBean implements PropertyUpdateManager
{
	@PersistenceContext(unitName="CoreServer") private EntityManager manager;

	/*************************************************************************
	 * Method: "action.UpdateApartmentForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateApartmentForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateApartmentForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateApartmentForRentalSale.ExtendedPropertyFields (apartmentFields)
	 *   Input: Map<String, Object> action.UpdateApartmentForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateApartmentForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateApartmentForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateApartmentForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateApartmentForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateApartmentForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateApartmentForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateApartmentForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateApartmentForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateApartmentForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> apartmentFields =
				inputParameters.getMap("action.UpdateApartmentForRentalSale.ExtendedPropertyFields");
			Apartment apartment = new Apartment(apartmentFields);
			vldResults = Apartment.validateApartment(apartment, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateApartmentForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.UpdateApartmentForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateApartmentForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateApartmentForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateApartmentForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateApartmentForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateApartmentForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateApartmentForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateApartmentForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateApartmentForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateApartmentForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			residentialFeatures.setCreatedByIdk(userIdk);
			if (residentialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				residentialFeatures = manager.merge(residentialFeatures);
			else
				manager.persist(residentialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			apartment.setPropertyIdk(property.getIdk());
			apartment.setResidentialFeaturesIdk(residentialFeatures.getIdk());
			if (apartment.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				apartment = manager.merge(apartment);
			else
				manager.persist(apartment);

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

	/*************************************************************************
	 * Method: "action.UpdateEmptyLotForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateEmptyLotForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateEmptyLotForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateEmptyLotForRentalSale.ExtendedPropertyFields (emptyLotFields)
	 *   Input: Map<String, Object> action.UpdateEmptyLotForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateEmptyLotForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateEmptyLotForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateEmptyLotForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateEmptyLotForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateEmptyLotForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateEmptyLotForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateEmptyLotForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateEmptyLotForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> emptyLotFields =
				inputParameters.getMap("action.UpdateEmptyLotForRentalSale.ExtendedPropertyFields");
			EmptyLot emptyLot = new EmptyLot(emptyLotFields);
			vldResults = EmptyLot.validateEmptyLot(emptyLot, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateEmptyLotForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateEmptyLotForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateEmptyLotForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateEmptyLotForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateEmptyLotForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateEmptyLotForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateEmptyLotForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateEmptyLotForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateEmptyLotForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			emptyLot.setPropertyIdk(property.getIdk());
			if (emptyLot.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				emptyLot = manager.merge(emptyLot);
			else
				manager.persist(emptyLot);

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

	/*************************************************************************
	 * Method: "action.UpdateFarmLotForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateFarmLotForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateFarmLotForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateFarmLotForRentalSale.ExtendedPropertyFields (farmLotFields)
	 *   Input: Map<String, Object> action.UpdateFarmLotForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateFarmLotForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateFarmLotForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateFarmLotForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateFarmLotForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateFarmLotForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateFarmLotForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateFarmLotForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFarmLotForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> farmLotFields =
				inputParameters.getMap("action.UpdateFarmLotForRentalSale.ExtendedPropertyFields");
			FarmLot farmLot = new FarmLot(farmLotFields);
			vldResults = FarmLot.validateFarmLot(farmLot, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFarmLotForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateFarmLotForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFarmLotForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateFarmLotForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFarmLotForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateFarmLotForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateFarmLotForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateFarmLotForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateFarmLotForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			farmLot.setPropertyIdk(property.getIdk());
			if (farmLot.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				farmLot = manager.merge(farmLot);
			else
				manager.persist(farmLot);

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

	/*************************************************************************
	 * Method: "action.UpdateFloorUnitForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateFloorUnitForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateFloorUnitForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateFloorUnitForRentalSale.ExtendedPropertyFields (floorUnitFields)
	 *   Input: Map<String, Object> action.UpdateFloorUnitForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateFloorUnitForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateFloorUnitForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateFloorUnitForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateFloorUnitForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateFloorUnitForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateFloorUnitForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateFloorUnitForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateFloorUnitForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFloorUnitForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> floorUnitFields =
				inputParameters.getMap("action.UpdateFloorUnitForRentalSale.ExtendedPropertyFields");
			FloorUnit floorUnit = new FloorUnit(floorUnitFields);
			vldResults = FloorUnit.validateFloorUnit(floorUnit, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFloorUnitForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.UpdateFloorUnitForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFloorUnitForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateFloorUnitForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFloorUnitForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateFloorUnitForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateFloorUnitForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateFloorUnitForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateFloorUnitForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateFloorUnitForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateFloorUnitForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			residentialFeatures.setCreatedByIdk(userIdk);
			if (residentialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				residentialFeatures = manager.merge(residentialFeatures);
			else
				manager.persist(residentialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			floorUnit.setPropertyIdk(property.getIdk());
			floorUnit.setResidentialFeaturesIdk(residentialFeatures.getIdk());
			if (floorUnit.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				floorUnit = manager.merge(floorUnit);
			else
				manager.persist(floorUnit);

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

	/*************************************************************************
	 * Method: "action.UpdateHouseForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateHouseForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateHouseForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateHouseForRentalSale.ExtendedPropertyFields (houseFields)
	 *   Input: Map<String, Object> action.UpdateHouseForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateHouseForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateHouseForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateHouseForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateHouseForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateHouseForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateHouseForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateHouseForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateHouseForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateHouseForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> houseFields =
				inputParameters.getMap("action.UpdateHouseForRentalSale.ExtendedPropertyFields");
			House house = new House(houseFields);
			vldResults = House.validateHouse(house, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateHouseForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.UpdateHouseForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateHouseForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateHouseForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateHouseForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateHouseForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateHouseForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateHouseForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateHouseForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateHouseForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateHouseForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			residentialFeatures.setCreatedByIdk(userIdk);
			if (residentialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				residentialFeatures = manager.merge(residentialFeatures);
			else
				manager.persist(residentialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			house.setPropertyIdk(property.getIdk());
			house.setResidentialFeaturesIdk(residentialFeatures.getIdk());
			if (house.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				house = manager.merge(house);
			else
				manager.persist(house);

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

	/*************************************************************************
	 * Method: "action.UpdateMultiUnitBuildingForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateMultiUnitBuildingForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.ExtendedPropertyFields (multiUnitBuildingFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitBuildingForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateMultiUnitBuildingForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateMultiUnitBuildingForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateMultiUnitBuildingForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitBuildingForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> multiUnitBuildingFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.ExtendedPropertyFields");
			MultiUnitBuilding multiUnitBuilding = new MultiUnitBuilding(multiUnitBuildingFields);
			vldResults = MultiUnitBuilding.validateMultiUnitBuilding(multiUnitBuilding, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitBuildingForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitBuildingForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitBuildingForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitBuildingForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitBuildingForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateMultiUnitBuildingForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateMultiUnitBuildingForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateMultiUnitBuildingForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			residentialFeatures.setCreatedByIdk(userIdk);
			if (residentialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				residentialFeatures = manager.merge(residentialFeatures);
			else
				manager.persist(residentialFeatures);
			manager.flush();
			commercialFeatures.setCreatedByIdk(userIdk);
			if (commercialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				commercialFeatures = manager.merge(commercialFeatures);
			else
				manager.persist(commercialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			multiUnitBuilding.setPropertyIdk(property.getIdk());
			multiUnitBuilding.setUnitResidentialFeaturesIdk(residentialFeatures.getIdk());
			multiUnitBuilding.setUnitCommercialFeaturesIdk(commercialFeatures.getIdk());
			if (multiUnitBuilding.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				multiUnitBuilding = manager.merge(multiUnitBuilding);
			else
				manager.persist(multiUnitBuilding);

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

	/*************************************************************************
	 * Method: "action.UpdateMultiUnitCompoundForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateMultiUnitCompoundForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.ExtendedPropertyFields (multiUnitCompoundFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateMultiUnitCompoundForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateMultiUnitCompoundForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateMultiUnitCompoundForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateMultiUnitCompoundForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitCompoundForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> multiUnitCompoundFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.ExtendedPropertyFields");
			MultiUnitCompound multiUnitCompound = new MultiUnitCompound(multiUnitCompoundFields);
			vldResults = MultiUnitCompound.validateMultiUnitCompound(multiUnitCompound, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitCompoundForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitCompoundForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitCompoundForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitCompoundForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateMultiUnitCompoundForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateMultiUnitCompoundForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateMultiUnitCompoundForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateMultiUnitCompoundForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			residentialFeatures.setCreatedByIdk(userIdk);
			if (residentialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				residentialFeatures = manager.merge(residentialFeatures);
			else
				manager.persist(residentialFeatures);
			manager.flush();
			commercialFeatures.setCreatedByIdk(userIdk);
			if (commercialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				commercialFeatures = manager.merge(commercialFeatures);
			else
				manager.persist(commercialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			multiUnitCompound.setPropertyIdk(property.getIdk());
			multiUnitCompound.setUnitResidentialFeaturesIdk(residentialFeatures.getIdk());
			multiUnitCompound.setUnitCommercialFeaturesIdk(commercialFeatures.getIdk());
			if (multiUnitCompound.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				multiUnitCompound = manager.merge(multiUnitCompound);
			else
				manager.persist(multiUnitCompound);

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

	/*************************************************************************
	 * Method: "action.UpdateOfficeForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateOfficeForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateOfficeForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateOfficeForRentalSale.ExtendedPropertyFields (officeFields)
	 *   Input: Map<String, Object> action.UpdateOfficeForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateOfficeForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateOfficeForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateOfficeForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateOfficeForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateOfficeForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateOfficeForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateOfficeForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateOfficeForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateOfficeForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> officeFields =
				inputParameters.getMap("action.UpdateOfficeForRentalSale.ExtendedPropertyFields");
			Office office = new Office(officeFields);
			vldResults = Office.validateOffice(office, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateOfficeForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.UpdateOfficeForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateOfficeForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateOfficeForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateOfficeForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateOfficeForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateOfficeForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateOfficeForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateOfficeForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateOfficeForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateOfficeForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			commercialFeatures.setCreatedByIdk(userIdk);
			if (commercialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				commercialFeatures = manager.merge(commercialFeatures);
			else
				manager.persist(commercialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			office.setPropertyIdk(property.getIdk());
			office.setCommercialFeaturesIdk(commercialFeatures.getIdk());
			if (office.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				office = manager.merge(office);
			else
				manager.persist(office);

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

	/*************************************************************************
	 * Method: "action.UpdateStoreForRentalSale", v1 (1 task)
	 * Task 1: Create
	 *   Input: Long action.UpdateStoreForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.UpdateStoreForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.UpdateStoreForRentalSale.ExtendedPropertyFields (storeFields)
	 *   Input: Map<String, Object> action.UpdateStoreForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.UpdateStoreForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.UpdateStoreForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.UpdateStoreForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.UpdateStoreForRentalSale.SaleFields (saleFields)
	 *************************************************************************/
	public List<ErrorReport> updateStoreForRentalSale(ActionRequest request, ActionResponse response)
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
			Long userIdk =
				inputParameters.getLong("action.UpdateStoreForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.UpdateStoreForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.UpdateStoreForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateStoreForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> storeFields =
				inputParameters.getMap("action.UpdateStoreForRentalSale.ExtendedPropertyFields");
			Store store = new Store(storeFields);
			vldResults = Store.validateStore(store, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateStoreForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.UpdateStoreForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateStoreForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.UpdateStoreForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateStoreForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.UpdateStoreForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, true, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.UpdateStoreForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.UpdateStoreForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateStoreForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.UpdateStoreForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.UpdateStoreForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// Execute create operation...
			if (rentalFields != null)
			{
				if (rental.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					rental = manager.merge(rental);
				else
					manager.persist(rental);
			}
			if (saleFields != null)
			{
				if (sale.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					sale = manager.merge(sale);
				else
					manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			commercialFeatures.setCreatedByIdk(userIdk);
			if (commercialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				commercialFeatures = manager.merge(commercialFeatures);
			else
				manager.persist(commercialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			if (property.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				property = manager.merge(property);
			else
				manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields == null)
			{
				propertyListing.setForRentIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (saleFields == null)
			{
				propertyListing.setForSaleIdk(BaseEntity.KEY_UNINITIALIZED);
			}
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			if (propertyListing.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				propertyListing = manager.merge(propertyListing);
			else
				manager.persist(propertyListing);
			store.setPropertyIdk(property.getIdk());
			store.setCommercialFeaturesIdk(commercialFeatures.getIdk());
			if (store.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				store = manager.merge(store);
			else
				manager.persist(store);

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

