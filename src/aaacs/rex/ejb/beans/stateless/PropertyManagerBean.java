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
import java.util.Calendar;
import aaacs.rex.ejb.beans.stateless.interfaces.PropertyManager;

@Stateless
@Local(PropertyManager.class)
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
public class PropertyManagerBean extends BaseSessionBean implements PropertyManager
{
	@PersistenceContext(unitName="CoreServer") private EntityManager manager;

	/*************************************************************************
	 * Method: "action.GeneratePropertyLister", v1 (1 task)
	 * Task 1: Custom code
	 *************************************************************************/
	public PropertyLister generatePropertyLister(EntityManager manager, Long userIdk, PropertyListing propertyListing)
		throws CSWrapperException
	{
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			manager.flush(); // to forcibly update propertyListing with the new idk
			PropertyLister propertyLister = new PropertyLister();
			propertyLister.setAllFields(null, true);
			propertyLister.setListerIdk(userIdk);
			propertyLister.setListingIdk(propertyListing.getIdk());
			propertyLister.setCommissionAmount(100.0F);
			
			LinkedHashMap<String, Object> searchFields = new LinkedHashMap<String, Object>();
			searchFields.put("category", "commissionType");
			searchFields.put("name", "commissionType.Percentage");
			List list = PropertyOption.findPropertyOptionByFields(manager, searchFields);
			propertyLister.setCommissionTypeIdk(((PropertyOption) list.get(0)).getIdk());
			
			searchFields.clear();
			searchFields.put("category", "propertyListerType");
			searchFields.put("name", "propertyListerType.Creator");
			list = PropertyOption.findPropertyOptionByFields(manager, searchFields);
			propertyLister.setListerTypeIdk(((PropertyOption) list.get(0)).getIdk());
			return propertyLister;
			// --- END CUSTOM CODE ---
			// ----- End Task 1 -----------------------------------------------

		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

	/*************************************************************************
	 * Method: "action.CreateApartmentForRentalSale", v1 (2 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateApartmentForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateApartmentForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateApartmentForRentalSale.ExtendedPropertyFields (apartmentFields)
	 *   Input: Map<String, Object> action.CreateApartmentForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateApartmentForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateApartmentForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateApartmentForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateApartmentForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateApartmentForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 *************************************************************************/
	public List<ErrorReport> createApartmentForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateApartmentForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateApartmentForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateApartmentForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateApartmentForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> apartmentFields =
				inputParameters.getMap("action.CreateApartmentForRentalSale.ExtendedPropertyFields");
			Apartment apartment = new Apartment(apartmentFields);
			vldResults = Apartment.validateApartment(apartment, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateApartmentForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.CreateApartmentForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateApartmentForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateApartmentForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateApartmentForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateApartmentForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateApartmentForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateApartmentForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateApartmentForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateApartmentForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateApartmentForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateApartmentForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
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
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			apartment.setPropertyIdk(property.getIdk());
			apartment.setResidentialFeaturesIdk(residentialFeatures.getIdk());
			manager.persist(apartment);
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			manager.persist(propertyLister);

			// ----- End Task 2 -----------------------------------------------

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
	 * Method: "action.CreateEmptyLotForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateEmptyLotForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateEmptyLotForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateEmptyLotForRentalSale.ExtendedPropertyFields (emptyLotFields)
	 *   Input: Map<String, Object> action.CreateEmptyLotForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateEmptyLotForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateEmptyLotForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateEmptyLotForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateEmptyLotForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createEmptyLotForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateEmptyLotForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateEmptyLotForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateEmptyLotForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateEmptyLotForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> emptyLotFields =
				inputParameters.getMap("action.CreateEmptyLotForRentalSale.ExtendedPropertyFields");
			EmptyLot emptyLot = new EmptyLot(emptyLotFields);
			vldResults = EmptyLot.validateEmptyLot(emptyLot, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateEmptyLotForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateEmptyLotForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateEmptyLotForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateEmptyLotForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateEmptyLotForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateEmptyLotForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateEmptyLotForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateEmptyLotForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateEmptyLotForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateEmptyLotForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
				manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			emptyLot.setPropertyIdk(property.getIdk());
			manager.persist(emptyLot);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.CreateFarmLotForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateFarmLotForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateFarmLotForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateFarmLotForRentalSale.ExtendedPropertyFields (farmLotFields)
	 *   Input: Map<String, Object> action.CreateFarmLotForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateFarmLotForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateFarmLotForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateFarmLotForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateFarmLotForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createFarmLotForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateFarmLotForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateFarmLotForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateFarmLotForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFarmLotForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> farmLotFields =
				inputParameters.getMap("action.CreateFarmLotForRentalSale.ExtendedPropertyFields");
			FarmLot farmLot = new FarmLot(farmLotFields);
			vldResults = FarmLot.validateFarmLot(farmLot, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFarmLotForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateFarmLotForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFarmLotForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateFarmLotForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFarmLotForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateFarmLotForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateFarmLotForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateFarmLotForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateFarmLotForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateFarmLotForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
				manager.persist(sale);
			}
			communityAmenities.setCreatedByIdk(userIdk);
			if (communityAmenities.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				communityAmenities = manager.merge(communityAmenities);
			else
				manager.persist(communityAmenities);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			farmLot.setPropertyIdk(property.getIdk());
			manager.persist(farmLot);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.CreateHouseForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateHouseForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateHouseForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateHouseForRentalSale.ExtendedPropertyFields (houseFields)
	 *   Input: Map<String, Object> action.CreateHouseForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateHouseForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateHouseForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateHouseForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateHouseForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateHouseForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createHouseForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateHouseForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateHouseForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateHouseForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateHouseForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> houseFields =
				inputParameters.getMap("action.CreateHouseForRentalSale.ExtendedPropertyFields");
			House house = new House(houseFields);
			vldResults = House.validateHouse(house, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateHouseForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.CreateHouseForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateHouseForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateHouseForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateHouseForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateHouseForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateHouseForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateHouseForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateHouseForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateHouseForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateHouseForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateHouseForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
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
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			house.setPropertyIdk(property.getIdk());
			house.setResidentialFeaturesIdk(residentialFeatures.getIdk());
			manager.persist(house);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.CreateMultiUnitBuildingForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateMultiUnitBuildingForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.ExtendedPropertyFields (multiUnitBuildingFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitBuildingForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateMultiUnitBuildingForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createMultiUnitBuildingForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateMultiUnitBuildingForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateMultiUnitBuildingForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitBuildingForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> multiUnitBuildingFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.ExtendedPropertyFields");
			MultiUnitBuilding multiUnitBuilding = new MultiUnitBuilding(multiUnitBuildingFields);
			vldResults = MultiUnitBuilding.validateMultiUnitBuilding(multiUnitBuilding, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitBuildingForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitBuildingForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitBuildingForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitBuildingForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitBuildingForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateMultiUnitBuildingForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateMultiUnitBuildingForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateMultiUnitBuildingForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateMultiUnitBuildingForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
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
			commercialFeatures.setCreatedByIdk(userIdk);
			if (commercialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				commercialFeatures = manager.merge(commercialFeatures);
			else
				manager.persist(commercialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			multiUnitBuilding.setPropertyIdk(property.getIdk());
			multiUnitBuilding.setUnitResidentialFeaturesIdk(residentialFeatures.getIdk());
			multiUnitBuilding.setUnitCommercialFeaturesIdk(commercialFeatures.getIdk());
			manager.persist(multiUnitBuilding);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.CreateMultiUnitCompoundForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateMultiUnitCompoundForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.ExtendedPropertyFields (multiUnitCompoundFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateMultiUnitCompoundForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateMultiUnitCompoundForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createMultiUnitCompoundForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateMultiUnitCompoundForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateMultiUnitCompoundForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitCompoundForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> multiUnitCompoundFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.ExtendedPropertyFields");
			MultiUnitCompound multiUnitCompound = new MultiUnitCompound(multiUnitCompoundFields);
			vldResults = MultiUnitCompound.validateMultiUnitCompound(multiUnitCompound, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitCompoundForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitCompoundForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitCompoundForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitCompoundForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateMultiUnitCompoundForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateMultiUnitCompoundForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateMultiUnitCompoundForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateMultiUnitCompoundForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateMultiUnitCompoundForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
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
			commercialFeatures.setCreatedByIdk(userIdk);
			if (commercialFeatures.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				commercialFeatures = manager.merge(commercialFeatures);
			else
				manager.persist(commercialFeatures);
			manager.flush();
			property.setCommunityAmenitiesIdk(communityAmenities.getIdk());
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			multiUnitCompound.setPropertyIdk(property.getIdk());
			multiUnitCompound.setUnitResidentialFeaturesIdk(residentialFeatures.getIdk());
			multiUnitCompound.setUnitCommercialFeaturesIdk(commercialFeatures.getIdk());
			manager.persist(multiUnitCompound);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.CreateOfficeForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateOfficeForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateOfficeForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateOfficeForRentalSale.ExtendedPropertyFields (officeFields)
	 *   Input: Map<String, Object> action.CreateOfficeForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateOfficeForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateOfficeForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateOfficeForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateOfficeForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateOfficeForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createOfficeForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateOfficeForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateOfficeForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateOfficeForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateOfficeForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> officeFields =
				inputParameters.getMap("action.CreateOfficeForRentalSale.ExtendedPropertyFields");
			Office office = new Office(officeFields);
			vldResults = Office.validateOffice(office, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateOfficeForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.CreateOfficeForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateOfficeForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateOfficeForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateOfficeForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateOfficeForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateOfficeForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateOfficeForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateOfficeForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateOfficeForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateOfficeForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateOfficeForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
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
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			office.setPropertyIdk(property.getIdk());
			office.setCommercialFeaturesIdk(commercialFeatures.getIdk());
			manager.persist(office);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.CreateFloorUnitForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateFloorUnitForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateFloorUnitForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateFloorUnitForRentalSale.ExtendedPropertyFields (flooUnitFields)
	 *   Input: Map<String, Object> action.CreateFloorUnitForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateFloorUnitForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateFloorUnitForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateFloorUnitForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateFloorUnitForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateFloorUnitForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createFloorUnitForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateFloorUnitForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateFloorUnitForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateFloorUnitForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFloorUnitForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> flooUnitFields =
				inputParameters.getMap("action.CreateFloorUnitForRentalSale.ExtendedPropertyFields");
			FloorUnit floorUnit = new FloorUnit(flooUnitFields);
			vldResults = FloorUnit.validateFloorUnit(floorUnit, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFloorUnitForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesFields =
				inputParameters.getMap("action.CreateFloorUnitForRentalSale.ResidentialFeaturesFields");
			ResidentialFeatures residentialFeatures = new ResidentialFeatures(residentialFeaturesFields);
			vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFloorUnitForRentalSale.ResidentialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateFloorUnitForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFloorUnitForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateFloorUnitForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateFloorUnitForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateFloorUnitForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateFloorUnitForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateFloorUnitForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateFloorUnitForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateFloorUnitForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
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
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			floorUnit.setPropertyIdk(property.getIdk());
			floorUnit.setResidentialFeaturesIdk(residentialFeatures.getIdk());
			manager.persist(floorUnit);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.CreateStoreForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreateStoreForRentalSale.UserIdk (userIdk)
	 *   Input: Map<String, Object> action.CreateStoreForRentalSale.PropertyFields (propertyFields)
	 *   Input: Map<String, Object> action.CreateStoreForRentalSale.ExtendedPropertyFields (storeFields)
	 *   Input: Map<String, Object> action.CreateStoreForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: Map<String, Object> action.CreateStoreForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: Map<String, Object> action.CreateStoreForRentalSale.PropertyListingFields (propertyListingFields)
	 *   Input: Map<String, Object> action.CreateStoreForRentalSale.RentalFields (rentalFields)
	 *   Input: Map<String, Object> action.CreateStoreForRentalSale.SaleFields (saleFields)
	 *   Output: String action.CreateStoreForRentalSale.SystemKey (propertyListing.getSystemKey())
	 * Task 2: Create
	 * Task 3: Custom code
	 * Task 4: Create
	 *************************************************************************/
	public List<ErrorReport> createStoreForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreateStoreForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreateStoreForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			Map<String, Object> propertyFields =
				inputParameters.getMap("action.CreateStoreForRentalSale.PropertyFields");
			Property property = new Property(propertyFields);
			vldResults = Property.validateProperty(property, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateStoreForRentalSale.PropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> storeFields =
				inputParameters.getMap("action.CreateStoreForRentalSale.ExtendedPropertyFields");
			Store store = new Store(storeFields);
			vldResults = Store.validateStore(store, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateStoreForRentalSale.ExtendedPropertyFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesFields =
				inputParameters.getMap("action.CreateStoreForRentalSale.CommercialFeaturesFields");
			CommercialFeatures commercialFeatures = new CommercialFeatures(commercialFeaturesFields);
			vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateStoreForRentalSale.CommercialFeaturesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesFields =
				inputParameters.getMap("action.CreateStoreForRentalSale.CommunityAmenitiesFields");
			CommunityAmenities communityAmenities = new CommunityAmenities(communityAmenitiesFields);
			vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateStoreForRentalSale.CommunityAmenitiesFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> propertyListingFields =
				inputParameters.getMap("action.CreateStoreForRentalSale.PropertyListingFields");
			PropertyListing propertyListing = new PropertyListing(propertyListingFields);
			vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
			if (vldResults != null)
			{
				Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
				for (String field : vldResults.keySet())
					tempo.put("action.CreateStoreForRentalSale.PropertyListingFields" + "(" + field + ")", vldResults.get(field));
				vldResults = tempo;
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			}
			Map<String, Object> rentalFields =
				inputParameters.getMap("action.CreateStoreForRentalSale.RentalFields");
			Rental rental = new Rental(rentalFields);
			if (rentalFields != null)
			{
				vldResults = Rental.validateRental(rental, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateStoreForRentalSale.RentalFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			Map<String, Object> saleFields =
				inputParameters.getMap("action.CreateStoreForRentalSale.SaleFields");
			Sale sale = new Sale(saleFields);
			if (saleFields != null)
			{
				vldResults = Sale.validateSale(sale, false, null, false);
				if (vldResults != null)
				{
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreateStoreForRentalSale.SaleFields" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			propertyListing.setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
				Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
			// --- END CUSTOM CODE ---
			// Package output parameters...
			outputParameters.setString("action.CreateStoreForRentalSale.SystemKey", propertyListing.getSystemKey());

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// Execute create operation...
			if (rentalFields != null)
			{
				manager.persist(rental);
			}
			if (saleFields != null)
			{
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
			manager.persist(property);
			manager.flush();
			propertyListing.setPropertyIdk(property.getIdk());
			if (rentalFields != null)
			{
				propertyListing.setForRentIdk(rental.getIdk());
			}
			if (saleFields != null)
			{
				propertyListing.setForSaleIdk(sale.getIdk());
			}
			manager.persist(propertyListing);
			store.setPropertyIdk(property.getIdk());
			store.setCommercialFeaturesIdk(commercialFeatures.getIdk());
			manager.persist(store);

			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListing);
			// --- END CUSTOM CODE ---
			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Execute create operation...
			manager.persist(propertyLister);

			// ----- End Task 4 -----------------------------------------------

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
	 * Method: "action.FindAllPropertyDistricts", v1 (1 task)
	 * Task 1: Custom code
	 *   Output: List action.FindAllPropertyDistricts.Districts (list)
	 *************************************************************************/
	public List<ErrorReport> findAllPropertyDistricts(ActionRequest request, ActionResponse response)
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

			// --- BEGIN CUSTOM CODE ---
			List list = Country.findAllDistricts(manager, Country.Status.BLOCKEDFROMPROPERTIES.value, null);
			// --- END CUSTOM CODE ---
			// Package output parameters...
			if (list.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {null};
				Class<?>[] valueClassesOut11 = {Object.class};
				outputParameters.setList("action.FindAllPropertyDistricts.Districts", list, keyClassesOut11, valueClassesOut11);
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

	/*************************************************************************
	 * Method: "action.FindPropertyDistricts", v1 (1 task)
	 * Task 1: Custom code
	 *   Input: String action.FindPropertyDistricts.Country (country)
	 *   Input: String action.FindPropertyDistricts.Region (region)
	 *   Input: String action.FindPropertyDistricts.City (city)
	 *   Input: String action.FindPropertyDistricts.District (district)
	 *   Output: List action.FindPropertyDistricts.Districts (list)
	 *************************************************************************/
	public List<ErrorReport> findPropertyDistricts(ActionRequest request, ActionResponse response)
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
			String country =
				inputParameters.getString("action.FindPropertyDistricts.Country");
			if (country != null)
			{
				vldResults = Country.validateName(country);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem("action.FindPropertyDistricts.Country", vldResults.get("Name")));
			}
			String region =
				inputParameters.getString("action.FindPropertyDistricts.Region");
			if (region != null)
			{
				vldResults = Region.validateName(region);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem("action.FindPropertyDistricts.Region", vldResults.get("Name")));
			}
			String city =
				inputParameters.getString("action.FindPropertyDistricts.City");
			if (city != null)
			{
				vldResults = City.validateName(city);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem("action.FindPropertyDistricts.City", vldResults.get("Name")));
			}
			String district =
				inputParameters.getString("action.FindPropertyDistricts.District");
			if (district != null)
			{
				vldResults = District.validateName(district);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem("action.FindPropertyDistricts.District", vldResults.get("Name")));
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			List list = Country.findDistricts(manager, Country.Status.BLOCKEDFROMPROPERTIES.value, null, 
				country, region, city, district);
			// --- END CUSTOM CODE ---
			// Package output parameters...
			if (list.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {null};
				Class<?>[] valueClassesOut11 = {Object.class};
				outputParameters.setList("action.FindPropertyDistricts.Districts", list, keyClassesOut11, valueClassesOut11);
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

	/*************************************************************************
	 * Method: "action.FindCommercialFeaturesByCreator", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long action.FindCommercialFeaturesByCreator.CreatedByIdk (createdByIdk)
	 *   Output: Map action.FindCommercialFeaturesByCreator.CommercialFeatures (entitiesToMaps(commercialFeaturesList))
	 *************************************************************************/
	public List<ErrorReport> findCommercialFeaturesByCreator(ActionRequest request, ActionResponse response)
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
				null, "tCommercialFeatures",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Long createdByIdk =
				inputParameters.getLong("action.FindCommercialFeaturesByCreator.CreatedByIdk");
			vldResults = inputParameters.clearChecks().setName("action.FindCommercialFeaturesByCreator.CreatedByIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(createdByIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("\"createdByIdk\"", createdByIdk);

			List commercialFeaturesList = CommercialFeatures.findCommercialFeaturesByFields(manager, searchFields1);

			// Package output parameters...
			if (commercialFeaturesList.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.FindCommercialFeaturesByCreator.CommercialFeatures", entitiesToMaps(commercialFeaturesList), keyClassesOut11, valueClassesOut11);
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

	/*************************************************************************
	 * Method: "action.FindCommunityAmenitiesByCreator", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long action.FindCommunityAmenitiesByCreator.CreatedByIdk (createdByIdk)
	 *   Output: Map action.FindCommunityAmenitiesByCreator.CommunityAmenities (entitiesToMaps(communityAmenitiesList))
	 *************************************************************************/
	public List<ErrorReport> findCommunityAmenitiesByCreator(ActionRequest request, ActionResponse response)
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
				null, "tCommunityAmenities",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Long createdByIdk =
				inputParameters.getLong("action.FindCommunityAmenitiesByCreator.CreatedByIdk");
			vldResults = inputParameters.clearChecks().setName("action.FindCommunityAmenitiesByCreator.CreatedByIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(createdByIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("\"createdByIdk\"", createdByIdk);

			List communityAmenitiesList = CommunityAmenities.findCommunityAmenitiesByFields(manager, searchFields1);

			// Package output parameters...
			if (communityAmenitiesList.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.FindCommunityAmenitiesByCreator.CommunityAmenities", entitiesToMaps(communityAmenitiesList), keyClassesOut11, valueClassesOut11);
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

	/*************************************************************************
	 * Method: "action.FindPropertyOptionsByCategory", v1 (1 task)
	 * Task 1: Read
	 *   Input: String action.FindPropertyOptionsByCategory.Category (category)
	 *   Output: Map action.FindPropertyOptionsByCategory.PropertyOptions (entitiesToMaps(propertyOptionList))
	 *************************************************************************/
	public List<ErrorReport> findPropertyOptionsByCategory(ActionRequest request, ActionResponse response)
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
				null, "tPropertyOptions",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			String category =
				inputParameters.getString("action.FindPropertyOptionsByCategory.Category");
			vldResults = inputParameters.clearChecks().setName("action.FindPropertyOptionsByCategory.Category").
				setValidMinimum((double) 1).
				checkString(category);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("category", category);

			List propertyOptionList = PropertyOption.findPropertyOptionByFields(manager, searchFields1);

			// Package output parameters...
			if (propertyOptionList.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.FindPropertyOptionsByCategory.PropertyOptions", entitiesToMaps(propertyOptionList), keyClassesOut11, valueClassesOut11);
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

	/*************************************************************************
	 * Method: "action.FindPropertyOptionByCategoryName", v1 (1 task)
	 * Task 1: Read
	 *   Input: String action.FindPropertyOptionByCategoryName.Category (category)
	 *   Input: String action.FindPropertyOptionByCategoryName.Name (name)
	 *   Output: Map action.FindPropertyOptionByCategoryName.PropertyOption (((PropertyOption) propertyOptionList.get(0)).getAllFields())
	 *************************************************************************/
	public List<ErrorReport> findPropertyOptionByCategoryName(ActionRequest request, ActionResponse response)
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
				null, "tPropertyOptions",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			String category =
				inputParameters.getString("action.FindPropertyOptionByCategoryName.Category");
			vldResults = inputParameters.clearChecks().setName("action.FindPropertyOptionByCategoryName.Category").
				setValidMinimum((double) 1).
				checkString(category);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			String name =
				inputParameters.getString("action.FindPropertyOptionByCategoryName.Name");
			vldResults = inputParameters.clearChecks().setName("action.FindPropertyOptionByCategoryName.Name").
				setValidMinimum((double) 1).
				checkString(name);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("category", category);
			searchFields1.put("name", name);

			List propertyOptionList = PropertyOption.findPropertyOptionByFields(manager, searchFields1);

			// Package output parameters...
			if (propertyOptionList.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {String.class};
				Class<?>[] valueClassesOut11 = {Object.class};
				outputParameters.setMap("action.FindPropertyOptionByCategoryName.PropertyOption", ((PropertyOption) propertyOptionList.get(0)).getAllFields(), keyClassesOut11, valueClassesOut11);
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

	/*************************************************************************
	 * Method: "action.FindResidentialFeaturesByCreator", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long action.FindResidentialFeaturesByCreator.CreatedByIdk (createdByIdk)
	 *   Output: Map action.FindResidentialFeaturesByCreator.ResidentialFeatures (entitiesToMaps(residentialFeaturesList))
	 *************************************************************************/
	public List<ErrorReport> findResidentialFeaturesByCreator(ActionRequest request, ActionResponse response)
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
				null, "tResidentialFeatures",
				null, null))
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.DATARESULTS,
					"action.UserUnauthorized", request.getLoginToken().getUserIdk(),
					request.getActionName(), request.getActionVersion(),
					request.getTimeReceived()));

			if (errors.size() > 0) return errors;

			// Extract and check input parameters...
			Long createdByIdk =
				inputParameters.getLong("action.FindResidentialFeaturesByCreator.CreatedByIdk");
			vldResults = inputParameters.clearChecks().setName("action.FindResidentialFeaturesByCreator.CreatedByIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(createdByIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
			searchFields1.put("\"createdByIdk\"", createdByIdk);

			List residentialFeaturesList = ResidentialFeatures.findResidentialFeaturesByFields(manager, searchFields1);

			// Package output parameters...
			if (residentialFeaturesList.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.FindResidentialFeaturesByCreator.ResidentialFeatures", entitiesToMaps(residentialFeaturesList), keyClassesOut11, valueClassesOut11);
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

	/*************************************************************************
	 * Method: "action.CreatePropertiesForRentalSale", v1 (4 tasks)
	 * Task 1: Custom code
	 *   Input: Long action.CreatePropertiesForRentalSale.UserIdk (userIdk)
	 *   Input: List<String> action.CreatePropertiesForRentalSale.PropertiesTypes (propertiesTypes)
	 *   Input: List<Map<String, Object>> action.CreatePropertiesForRentalSale.PropertiesFields (propertiesFields)
	 *   Input: List<Map<String, Object>> action.CreatePropertiesForRentalSale.CommercialFeaturesFields (commercialFeaturesFields)
	 *   Input: List<Map<String, Object>> action.CreatePropertiesForRentalSale.ResidentialFeaturesFields (residentialFeaturesFields)
	 *   Input: List<Map<String, Object>> action.CreatePropertiesForRentalSale.CommunityAmenitiesFields (communityAmenitiesFields)
	 *   Input: List<Map<String, Object>> action.CreatePropertiesForRentalSale.PropertyListingFields (propertyListingsFields)
	 *   Input: List<Map<String, Object>> action.CreatePropertiesForRentalSale.RentalFields (rentalsFields)
	 *   Input: List<Map<String, Object>> action.CreatePropertiesForRentalSale.SaleFields (salesFields)
	 * Task 2: Custom code
	 * Task 3: Create
	 * Task 4: Undetermined
	 *   Output: List action.CreatePropertiesForRentalSale.SystemKeys (systemKeys)
	 *************************************************************************/
	public List<ErrorReport> createPropertiesForRentalSale(ActionRequest request, ActionResponse response)
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
				inputParameters.getLong("action.CreatePropertiesForRentalSale.UserIdk");
			vldResults = inputParameters.clearChecks().setName("action.CreatePropertiesForRentalSale.UserIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(userIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			List<String> propertiesTypes =
				inputParameters.getList("action.CreatePropertiesForRentalSale.PropertiesTypes");
			List<Map<String, Object>> propertiesFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.PropertiesFields");
			List<Property> propertiesList = new Vector<Property>();
			for (Map<String, Object> oneFieldSet : propertiesFields)
			{
				Property property = new Property(oneFieldSet);
				propertiesList.add(property);
			}
			for (Property property : propertiesList)
			{
				vldResults = Property.validateProperty(property, false, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreatePropertiesForRentalSale.PropertiesFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			List<Map<String, Object>> commercialFeaturesFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.CommercialFeaturesFields");
			List<CommercialFeatures> commercialFeaturesList = new Vector<CommercialFeatures>();
			for (Map<String, Object> oneFieldSet : commercialFeaturesFields)
			{
				CommercialFeatures commercialFeatures = new CommercialFeatures(oneFieldSet);
				commercialFeaturesList.add(commercialFeatures);
			}
			for (CommercialFeatures commercialFeatures : commercialFeaturesList)
			{
				vldResults = CommercialFeatures.validateCommercialFeatures(commercialFeatures, false, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreatePropertiesForRentalSale.CommercialFeaturesFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			List<Map<String, Object>> residentialFeaturesFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.ResidentialFeaturesFields");
			List<ResidentialFeatures> residentialFeaturesList = new Vector<ResidentialFeatures>();
			for (Map<String, Object> oneFieldSet : residentialFeaturesFields)
			{
				ResidentialFeatures residentialFeatures = new ResidentialFeatures(oneFieldSet);
				residentialFeaturesList.add(residentialFeatures);
			}
			for (ResidentialFeatures residentialFeatures : residentialFeaturesList)
			{
				vldResults = ResidentialFeatures.validateResidentialFeatures(residentialFeatures, false, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreatePropertiesForRentalSale.ResidentialFeaturesFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			List<Map<String, Object>> communityAmenitiesFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.CommunityAmenitiesFields");
			List<CommunityAmenities> communityAmenitiesList = new Vector<CommunityAmenities>();
			for (Map<String, Object> oneFieldSet : communityAmenitiesFields)
			{
				CommunityAmenities communityAmenities = new CommunityAmenities(oneFieldSet);
				communityAmenitiesList.add(communityAmenities);
			}
			for (CommunityAmenities communityAmenities : communityAmenitiesList)
			{
				vldResults = CommunityAmenities.validateCommunityAmenities(communityAmenities, false, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreatePropertiesForRentalSale.CommunityAmenitiesFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			List<Map<String, Object>> propertyListingsFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.PropertyListingFields");
			List<PropertyListing> propertyListingsList = new Vector<PropertyListing>();
			for (Map<String, Object> oneFieldSet : propertyListingsFields)
			{
				PropertyListing propertyListing = new PropertyListing(oneFieldSet);
				propertyListingsList.add(propertyListing);
			}
			for (PropertyListing propertyListing : propertyListingsList)
			{
				vldResults = PropertyListing.validatePropertyListing(propertyListing, false, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreatePropertiesForRentalSale.PropertyListingFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			List<Map<String, Object>> rentalsFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.RentalFields");
			List<Rental> rentalsList = new Vector<Rental>();
			for (Map<String, Object> oneFieldSet : rentalsFields)
			{
				Rental rental = new Rental(oneFieldSet);
				rentalsList.add(rental);
			}
			if (rentalsFields != null)
			{
				for (Rental rental : rentalsList)
				{
					vldResults = Rental.validateRental(rental, false, null, false);
					if (vldResults != null)
					{
						int cntr = 0;
						Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
						for (String field : vldResults.keySet())
							tempo.put("action.CreatePropertiesForRentalSale.RentalFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
						vldResults = tempo;
						errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
							"action.InvalidParameter", request).
							addItem(vldResults));
					}
				}
			}
			List<Map<String, Object>> salesFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.SaleFields");
			List<Sale> salesList = new Vector<Sale>();
			for (Map<String, Object> oneFieldSet : salesFields)
			{
				Sale sale = new Sale(oneFieldSet);
				salesList.add(sale);
			}
			if (salesFields != null)
			{
				for (Sale sale : salesList)
				{
					vldResults = Sale.validateSale(sale, false, null, false);
					if (vldResults != null)
					{
						int cntr = 0;
						Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
						for (String field : vldResults.keySet())
							tempo.put("action.CreatePropertiesForRentalSale.SaleFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
						vldResults = tempo;
						errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
							"action.InvalidParameter", request).
							addItem(vldResults));
					}
				}
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			Calendar calendar = Calendar.getInstance();
			calendar.set(2008, 0, 1, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			List<String> systemKeys = new Vector<String>();
			// --- END CUSTOM CODE ---
			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			List<Map<String, Object>> extendedPropertiesFields =
				inputParameters.getList("action.CreatePropertiesForRentalSale.ExtendedPropertiesFields");
			List<BaseEntity> extendedPropertiesList = new Vector<BaseEntity>();
			int c = 0;
			for (Map<String, Object> oneFieldSet : extendedPropertiesFields)
			{
				String propertyType = propertiesTypes.get(c);
				if (propertyType.equals("propertyType.Apartment"))
				{
					Apartment ep = new Apartment(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.EmptyLot"))
				{
					EmptyLot ep = new EmptyLot(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.FarmLot"))
				{
					FarmLot ep = new FarmLot(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.FloorUnit"))
				{
					FloorUnit ep = new FloorUnit(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.House"))
				{
					House ep = new House(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.MultiUnitBuilding"))
				{
					MultiUnitBuilding ep = new MultiUnitBuilding(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.MultiUnitCompound"))
				{
					MultiUnitCompound ep = new MultiUnitCompound(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.Office"))
				{
					Office ep = new Office(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				else if (propertyType.equals("propertyType.Store"))
				{
					Store ep = new Store(oneFieldSet);
					extendedPropertiesList.add(ep);
				}
				c++;
			}
			for (BaseEntity extendedProperty : extendedPropertiesList)
			{
				vldResults = extendedProperty.validateAllFields(false, null, false);
				if (vldResults != null)
				{
					int cntr = 0;
					Map<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();
					for (String field : vldResults.keySet())
						tempo.put("action.CreatePropertiesForRentalSale.ExtendedPropertiesFields" + "[" + cntr++ + "]" + "(" + field + ")", vldResults.get(field));
					vldResults = tempo;
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
				}
			}
			// --- END CUSTOM CODE ---
			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// Execute create operation...
			for (int i = 0; i < propertiesList.size(); i++)
			{
				// --- BEGIN CUSTOM CODE ---
				boolean useResidentialFeatures = false, useCommercialFeatures = true;
				if (propertiesTypes.get(i).equals("propertyType.Apartment") || 
					propertiesTypes.get(i).equals("propertyType.FloorUnit") ||
					propertiesTypes.get(i).equals("propertyType.House") ||
					propertiesTypes.get(i).equals("propertyType.MultiUnitBuilding") ||
					propertiesTypes.get(i).equals("propertyType.MultiUnitCompound"))
					useResidentialFeatures = true;
				if (propertiesTypes.get(i).equals("propertyType.MultiUnitBuilding") || 
					propertiesTypes.get(i).equals("propertyType.MultiUnitCompound") ||
					propertiesTypes.get(i).equals("propertyType.Office") ||
					propertiesTypes.get(i).equals("propertyType.Store"))
					useCommercialFeatures = true;
				// --- END CUSTOM CODE ---
				if (rentalsFields != null && rentalsFields.size() > 0 && rentalsFields.get(i) != null)
				{
					manager.persist(rentalsList.get(i));
				}
				if (salesFields != null && salesFields.size() > 0 && salesFields.get(i) != null)
				{
					manager.persist(salesList.get(i));
				}
				communityAmenitiesList.get(i).setCreatedByIdk(userIdk);
				if (communityAmenitiesList.get(i).getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
					communityAmenitiesList.set(i,  manager.merge(communityAmenitiesList.get(i)));
				else
					manager.persist(communityAmenitiesList.get(i));
				if (useResidentialFeatures)
				{
					residentialFeaturesList.get(i).setCreatedByIdk(userIdk);
				}
				if (useResidentialFeatures)
				{
					if (residentialFeaturesList.get(i).getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
						residentialFeaturesList.set(i,  manager.merge(residentialFeaturesList.get(i)));
					else
						manager.persist(residentialFeaturesList.get(i));
				}
				if (useCommercialFeatures)
				{
					commercialFeaturesList.get(i).setCreatedByIdk(userIdk);
				}
				if (useCommercialFeatures)
				{
					if (commercialFeaturesList.get(i).getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
						commercialFeaturesList.set(i,  manager.merge(commercialFeaturesList.get(i)));
					else
						manager.persist(commercialFeaturesList.get(i));
					manager.flush();
				}
				propertiesList.get(i).setCommunityAmenitiesIdk(communityAmenitiesList.get(i).getIdk());
				manager.persist(propertiesList.get(i));
				manager.flush();
				propertyListingsList.get(i).setPropertyIdk(propertiesList.get(i).getIdk());
				if (rentalsFields != null && rentalsFields.size() > 0 && rentalsFields.get(i) != null && Boolean.TRUE.equals(rentalsFields.get(i).get("_confirmed_")))
				{
					propertyListingsList.get(i).setForRentIdk(rentalsList.get(i).getIdk());
				}
				if (salesFields != null && salesFields.size() > 0 && salesFields.get(i) != null && Boolean.TRUE.equals(salesFields.get(i).get("_confirmed_")))
				{
					propertyListingsList.get(i).setForSaleIdk(salesList.get(i).getIdk());
				}
				manager.persist(propertyListingsList.get(i));
				if (propertiesTypes.get(i).equals("propertyType.Apartment"))
				{
					((Apartment) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.EmptyLot"))
				{
					((EmptyLot) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.FarmLot"))
				{
					((FarmLot) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.FloorUnit"))
				{
					((FloorUnit) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.House"))
				{
					((House) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.MultiUnitBuilding"))
				{
					((MultiUnitBuilding) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.MultiUnitCompound"))
				{
					((MultiUnitCompound) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.Office"))
				{
					((Office) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.Store"))
				{
					((Store) extendedPropertiesList.get(i)).setPropertyIdk(propertiesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.Apartment"))
				{
					((Apartment) extendedPropertiesList.get(i)).setResidentialFeaturesIdk(residentialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.FloorUnit"))
				{
					((FloorUnit) extendedPropertiesList.get(i)).setResidentialFeaturesIdk(residentialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.House"))
				{
					((House) extendedPropertiesList.get(i)).setResidentialFeaturesIdk(residentialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.MultiUnitBuilding"))
				{
					((MultiUnitBuilding) extendedPropertiesList.get(i)).setUnitResidentialFeaturesIdk(residentialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.MultiUnitCompound"))
				{
					((MultiUnitCompound) extendedPropertiesList.get(i)).setUnitResidentialFeaturesIdk(residentialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.MultiUnitBuilding"))
				{
					((MultiUnitBuilding) extendedPropertiesList.get(i)).setUnitCommercialFeaturesIdk(commercialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.MultiUnitCompound"))
				{
					((MultiUnitCompound) extendedPropertiesList.get(i)).setUnitCommercialFeaturesIdk(commercialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.Office"))
				{
					((Office) extendedPropertiesList.get(i)).setCommercialFeaturesIdk(commercialFeaturesList.get(i).getIdk());
				}
				if (propertiesTypes.get(i).equals("propertyType.Store"))
				{
					((Store) extendedPropertiesList.get(i)).setCommercialFeaturesIdk(commercialFeaturesList.get(i).getIdk());
				}
				manager.persist(extendedPropertiesList.get(i));
				// --- BEGIN CUSTOM CODE ---
				propertyListingsList.get(i).setSystemKey( Long.toString(request.getLoginToken().getUserIdk(), Character.MAX_RADIX).toUpperCase() +
					Long.toString(System.currentTimeMillis() - calendar.getTimeInMillis(), Character.MAX_RADIX).toUpperCase() );
				systemKeys.add(propertyListingsList.get(i).getSystemKey());
				Thread.sleep(50);
				// --- END CUSTOM CODE ---
				// --- BEGIN CUSTOM CODE ---
				PropertyLister propertyLister = generatePropertyLister(manager, userIdk, propertyListingsList.get(i));
				// --- END CUSTOM CODE ---
				manager.persist(propertyLister);
			}

			// ----- End Task 3 -----------------------------------------------

			// ----- Begin Task 4 ---------------------------------------------
			// Package output parameters...
			if (systemKeys.size() > 0)
			{
				Class<?>[] keyClassesOut41 = {null};
				Class<?>[] valueClassesOut41 = {String.class};
				outputParameters.setList("action.CreatePropertiesForRentalSale.SystemKeys", systemKeys, keyClassesOut41, valueClassesOut41);
			}

			// ----- End Task 4 -----------------------------------------------

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

