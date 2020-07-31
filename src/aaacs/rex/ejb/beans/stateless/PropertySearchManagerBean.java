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
import aaacs.rex.ejb.beans.stateless.interfaces.PropertySearchManager;

@Stateless
@Local(PropertySearchManager.class)
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
public class PropertySearchManagerBean extends BaseSessionBean implements PropertySearchManager
{
	@PersistenceContext(unitName="CoreServer") private EntityManager manager;

	/*************************************************************************
	 * Method: "action.ApplySearchPropertiesConstraints", v1 (1 task)
	 * Task 1: Custom code
	 *************************************************************************/
	public void applySearchPropertiesConstraints(SelectBuilder selectBuilder, Map<String, Object> propertySearchFields, Map<String, Object> communityAmenitiesSearchFields, Map<String, Object> propertyTypeSearchFields, Map<String, Object> residentialFeaturesSearchFields, Map<String, Object> commercialFeaturesSearchFields)
		throws CSWrapperException
	{
		try
		{
			// ----- Begin Task 1 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			selectBuilder.addSelect("\"tProperties\".idk AS \"propertyIdk\", \"propertyTypeIdk\"");
			selectBuilder.addSelect("\"tProperties\".\"countryIdk\" AS \"countryIdk\", \"tProperties\".\"regionIdk\" AS \"regionIdk\", " + 
				"\"tProperties\".\"cityIdk\" AS \"cityIdk\", \"tProperties\".\"districtIdk\" AS \"districtIdk\"");
			selectBuilder.addSelect("\"yearBuilt\", \"generalConditionIdk\", \"communityAmenitiesIdk\"");
			selectBuilder.addSelect("\"directlyNorth\", \"directlySouth\", \"directlyEast\", \"directlyWest\"");
			selectBuilder.addFrom("\"tProperties\"");
			selectBuilder.addWhere("\"tProperties\".idk = \"tPropertyListings\".\"propertyIdk\"", true);
			if (propertySearchFields != null)
				Property.applySearchFields(propertySearchFields, selectBuilder);
		
			selectBuilder.addSelect("\"communityName\", \"managedCommunity\"");
			selectBuilder.addFrom("\"tCommunityAmenities\"");
			selectBuilder.addWhere("\"tCommunityAmenities\".idk = \"tProperties\".\"communityAmenitiesIdk\"", true);
			if (communityAmenitiesSearchFields != null)
				CommunityAmenities.applySearchFields(communityAmenitiesSearchFields, selectBuilder);

			if (propertySearchFields != null)
			{
				Long propertyTypeIdk = (Long) propertySearchFields.get("propertyTypeIdk");
				if (propertyTypeIdk != null)
				{
					Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
					searchFields1.put("category", "propertyType");

					List propertyOptionsList = PropertyOption.findPropertyOptionByFields(manager, searchFields1);
					String propertyType = "";
					for (Object option : propertyOptionsList)
					{
						PropertyOption propertyOption = (PropertyOption) option;
						if (propertyOption.getIdk().equals(propertyTypeIdk))
						{
							propertyType = propertyOption.getName();
							break;
						}
					}
					
					if (propertyType.equals(""))
					{
						// throw new IllegalArgumentException("Unrecognized property type");
					}
					else if (propertyType.equals("propertyType.Apartment"))
					{
						selectBuilder.addSelect("\"tApartments\".idk AS \"apartmentIdk\", \"apartmentSize\", \"apartmentAreaUnitTypeIdk\", \"apartmentFloor\"");
						selectBuilder.addFrom("\"tApartments\"");
						selectBuilder.addWhere("\"tApartments\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							Apartment.applySearchFields(propertyTypeSearchFields, selectBuilder);
						
						selectBuilder.addSelect("\"tResidentialFeatures\".idk AS \"residentialFeaturesIdk\", " +
							"bedrooms, bathrooms, furnished");
						selectBuilder.addFrom("\"tResidentialFeatures\"");
						selectBuilder.addWhere("\"tResidentialFeatures\".idk = \"tApartments\".\"residentialFeaturesIdk\"", true);
						if (residentialFeaturesSearchFields != null)
							ResidentialFeatures.applySearchFields(residentialFeaturesSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.EmptyLot"))
					{
						selectBuilder.addSelect("\"tEmptyLots\".idk AS \"emptyLotIdk\", \"landSize\", \"landAreaUnitTypeIdk\", \"landTypeIdk\"");
						selectBuilder.addFrom("\"tEmptyLots\"");
						selectBuilder.addWhere("\"tEmptyLots\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							EmptyLot.applySearchFields(propertyTypeSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.FarmLot"))
					{
						selectBuilder.addSelect("\"tFarmLots\".idk AS \"farmLotIdk\", \"landSize\", \"landAreaUnitTypeIdk\", \"irrigationTypeIdk\", \"suitableCrops\"");
						selectBuilder.addFrom("\"tFarmLots\"");
						selectBuilder.addWhere("\"tFarmLots\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							FarmLot.applySearchFields(propertyTypeSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.FloorUnit"))
					{
						selectBuilder.addSelect("\"tFloorUnits\".idk AS \"floorUnitIdk\", \"floorUnitSize\", \"floorUnitAreaUnitTypeIdk\", \"unitFloor\"");
						selectBuilder.addFrom("\"tFloorUnits\"");
						selectBuilder.addWhere("\"tFloorUnits\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							FloorUnit.applySearchFields(propertyTypeSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tResidentialFeatures\".idk AS \"residentialFeaturesIdk\", " +
							"bedrooms, bathrooms, furnished");
						selectBuilder.addFrom("\"tResidentialFeatures\"");
						selectBuilder.addWhere("\"tResidentialFeatures\".idk = \"tFloorUnits\".\"residentialFeaturesIdk\"", true);
						if (residentialFeaturesSearchFields != null)
							ResidentialFeatures.applySearchFields(residentialFeaturesSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.House"))
					{
						selectBuilder.addSelect("\"tHouses\".idk AS \"houseIdk\", \"landSize\", \"landAreaUnitTypeIdk\", \"houseSize\", \"houseAreaUnitTypeIdk\", floors");
						selectBuilder.addFrom("\"tHouses\"");
						selectBuilder.addWhere("\"tHouses\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							House.applySearchFields(propertyTypeSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tResidentialFeatures\".idk AS \"residentialFeaturesIdk\", " +
							"bedrooms, bathrooms, furnished");
						selectBuilder.addFrom("\"tResidentialFeatures\"");
						selectBuilder.addWhere("\"tResidentialFeatures\".idk = \"tHouses\".\"residentialFeaturesIdk\"", true);
						if (residentialFeaturesSearchFields != null)
							ResidentialFeatures.applySearchFields(residentialFeaturesSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.MultiUnitBuilding"))
					{
						selectBuilder.addSelect("\"tMultiUnitBuildings\".idk AS \"multiUnitBuildingIdk\", " +
							"\"landSize\", \"landAreaUnitTypeIdk\", \"buildingSize\", \"buildingAreaUnitTypeIdk\", " + 
							"\"residentialUnitSize\", \"residentialUnitAreaUnitTypeIdk\", \"commercialUnitSize\", \"commercialUnitAreaUnitTypeIdk\", " +
							"\"totalFloorsInBuilding\", \"totalResidentialUnits\", \"totalCommercialUnits\"");
						selectBuilder.addFrom("\"tMultiUnitBuildings\"");
						selectBuilder.addWhere("\"tMultiUnitBuildings\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							MultiUnitBuilding.applySearchFields(propertyTypeSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tResidentialFeatures\".idk AS \"residentialFeaturesIdk\", " +
							"bedrooms, bathrooms, \"tResidentialFeatures\".furnished AS \"residentialUnitFurnished\"");
						selectBuilder.addFrom("\"tResidentialFeatures\"");
						selectBuilder.addWhere("\"tResidentialFeatures\".idk = \"tMultiUnitBuildings\".\"unitResidentialFeaturesIdk\"", true);
						if (residentialFeaturesSearchFields != null)
							ResidentialFeatures.applySearchFields(residentialFeaturesSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tCommercialFeatures\".idk AS \"commercialFeaturesIdk\", " +
							"offices, \"tCommercialFeatures\".furnished AS \"commercialUnitFurnished\"");
						selectBuilder.addFrom("\"tCommercialFeatures\"");
						selectBuilder.addWhere("\"tCommercialFeatures\".idk = \"tMultiUnitBuildings\".\"unitCommercialFeaturesIdk\"", true);
						if (commercialFeaturesSearchFields != null)
							CommercialFeatures.applySearchFields(commercialFeaturesSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.MultiUnitCompound"))
					{
						selectBuilder.addSelect("\"tMultiUnitCompounds\".idk AS \"multiUnitCompoundIdk\", " +
							"\"landSize\", \"landAreaUnitTypeIdk\", " + 
							"\"residentialUnitSize\", \"residentialUnitAreaUnitTypeIdk\", \"commercialUnitSize\", \"commercialUnitAreaUnitTypeIdk\", " +
							"\"totalResidentialUnits\", \"totalCommercialUnits\"");
						selectBuilder.addFrom("\"tMultiUnitCompounds\"");
						selectBuilder.addWhere("\"tMultiUnitCompounds\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							MultiUnitCompound.applySearchFields(propertyTypeSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tResidentialFeatures\".idk AS \"residentialFeaturesIdk\", " +
							"bedrooms, bathrooms, \"tResidentialFeatures\".furnished AS \"residentialUnitFurnished\"");
						selectBuilder.addFrom("\"tResidentialFeatures\"");
						selectBuilder.addWhere("\"tResidentialFeatures\".idk = \"tMultiUnitCompounds\".\"unitResidentialFeaturesIdk\"", true);
						if (residentialFeaturesSearchFields != null)
							ResidentialFeatures.applySearchFields(residentialFeaturesSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tCommercialFeatures\".idk AS \"commercialFeaturesIdk\", " +
							"offices, \"tCommercialFeatures\".furnished AS \"commercialUnitFurnished\"");
						selectBuilder.addFrom("\"tCommercialFeatures\"");
						selectBuilder.addWhere("\"tCommercialFeatures\".idk = \"tMultiUnitCompounds\".\"unitCommercialFeaturesIdk\"", true);
						if (commercialFeaturesSearchFields != null)
							CommercialFeatures.applySearchFields(commercialFeaturesSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.Office"))
					{
						selectBuilder.addSelect("\"tOffices\".idk AS \"officeIdk\", \"officeSize\", \"officeAreaUnitTypeIdk\", \"officeFloor\", \"intendedBusinessTypeIdk\"");
						selectBuilder.addFrom("\"tOffices\"");
						selectBuilder.addWhere("\"tOffices\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							Office.applySearchFields(propertyTypeSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tCommercialFeatures\".idk AS \"commercialFeaturesIdk\", " +
							"offices, furnished");
						selectBuilder.addFrom("\"tCommercialFeatures\"");
						selectBuilder.addWhere("\"tCommercialFeatures\".idk = \"tOffices\".\"commercialFeaturesIdk\"", true);
						if (commercialFeaturesSearchFields != null)
							CommercialFeatures.applySearchFields(commercialFeaturesSearchFields, selectBuilder);
					}
					else if (propertyType.equals("propertyType.Store"))
					{
						selectBuilder.addSelect("\"tStores\".idk AS \"storeIdk\", \"storeSize\", \"storeAreaUnitTypeIdk\", \"storeFloor\", \"intendedBusinessTypeIdk\"");
						selectBuilder.addFrom("\"tStores\"");
						selectBuilder.addWhere("\"tStores\".\"propertyIdk\" = \"tProperties\".idk", true);
						if (propertyTypeSearchFields != null)
							Store.applySearchFields(propertyTypeSearchFields, selectBuilder);
			
						selectBuilder.addSelect("\"tCommercialFeatures\".idk AS \"commercialFeaturesIdk\", " +
							"offices, furnished");
						selectBuilder.addFrom("\"tCommercialFeatures\"");
						selectBuilder.addWhere("\"tCommercialFeatures\".idk = \"tStores\".\"commercialFeaturesIdk\"", true);
						if (commercialFeaturesSearchFields != null)
							CommercialFeatures.applySearchFields(commercialFeaturesSearchFields, selectBuilder);
					}
				}
				//else
				//	throw new IllegalArgumentException("Property type must be specified");
			}
			// --- END CUSTOM CODE ---
			// ----- End Task 1 -----------------------------------------------

		}
		catch (Exception e)
		{
			throw new CSWrapperException(e);
		}
	}

	/*************************************************************************
	 * Method: "action.SearchProperties", v1 (1 task)
	 * Task 1: Custom code
	 *   Input: String action.SearchProperties.PropertyListingType (propertyListingType)
	 *   Input: String action.SearchProperties.SortOrder (sortOrder)
	 *   Input: String action.SearchProperties.DateRange (dateRange)
	 *   Input: String action.SearchProperties.Username (username)
	 *   Input: Boolean action.SearchProperties.TestUserProperties (testUserProperties)
	 *   Input: String action.SearchProperties.Telephone (telephone)
	 *   Input: String action.SearchProperties.Mobile (mobile)
	 *   Input: Map<String, Object> action.SearchProperties.PropertySearchFields (propertySearchFields)
	 *   Input: Map<String, Object> action.SearchProperties.PropertyListingSearchFields (propertyListingSearchFields)
	 *   Input: Map<String, Object> action.SearchProperties.RentalSearchFields (rentalSearchFields)
	 *   Input: Map<String, Object> action.SearchProperties.SaleSearchFields (saleSearchFields)
	 *   Input: Map<String, Object> action.SearchProperties.PropertyTypeSearchFields (propertyTypeSearchFields)
	 *   Input: Map<String, Object> action.SearchProperties.ResidentialFeaturesSearchFields (residentialFeaturesSearchFields)
	 *   Input: Map<String, Object> action.SearchProperties.CommercialFeaturesSearchFields (commercialFeaturesSearchFields)
	 *   Input: Map<String, Object> action.SearchProperties.CommunityAmenitiesSearchFields (communityAmenitiesSearchFields)
	 *   Output: Map action.SearchProperties.Rentals (rentalRows)
	 *   Output: Map action.SearchProperties.Sales (salesRows)
	 *************************************************************************/
	public List<ErrorReport> searchProperties(ActionRequest request, ActionResponse response)
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
			String propertyListingType =
				inputParameters.getString("action.SearchProperties.PropertyListingType");
			String sortOrder =
				inputParameters.getString("action.SearchProperties.SortOrder");
			String dateRange =
				inputParameters.getString("action.SearchProperties.DateRange");
			String username =
				inputParameters.getString("action.SearchProperties.Username");
			if (username != null && !username.trim().equals(""))
			{
				vldResults = User.validateUsername(username);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem("action.SearchProperties.Username", vldResults.get("Username")));
			}
			Boolean testUserProperties =
				inputParameters.getBoolean("action.SearchProperties.TestUserProperties");
			String telephone =
				inputParameters.getString("action.SearchProperties.Telephone");
			if (telephone != null && !telephone.trim().equals(""))
			{
				vldResults = UserProfile.validateTelephone(telephone);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem("action.SearchProperties.Telephone", vldResults.get("Telephone")));
			}
			String mobile =
				inputParameters.getString("action.SearchProperties.Mobile");
			if (mobile != null && !mobile.trim().equals(""))
			{
				vldResults = UserProfile.validateMobile(mobile);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem("action.SearchProperties.Mobile", vldResults.get("Mobile")));
			}
			Map<String, Object> propertySearchFields =
				inputParameters.getMap("action.SearchProperties.PropertySearchFields");
			if (propertySearchFields != null)
			{
				Class<?>[] keyClassesIn18 = {String.class};
				Class<?>[] valueClassesIn18 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.PropertySearchFields").
					checkMap(propertySearchFields, keyClassesIn18, valueClassesIn18);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> propertyListingSearchFields =
				inputParameters.getMap("action.SearchProperties.PropertyListingSearchFields");
			if (propertyListingSearchFields != null)
			{
				Class<?>[] keyClassesIn19 = {String.class};
				Class<?>[] valueClassesIn19 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.PropertyListingSearchFields").
					checkMap(propertyListingSearchFields, keyClassesIn19, valueClassesIn19);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> rentalSearchFields =
				inputParameters.getMap("action.SearchProperties.RentalSearchFields");
			if (rentalSearchFields != null)
			{
				Class<?>[] keyClassesIn110 = {String.class};
				Class<?>[] valueClassesIn110 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.RentalSearchFields").
					checkMap(rentalSearchFields, keyClassesIn110, valueClassesIn110);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> saleSearchFields =
				inputParameters.getMap("action.SearchProperties.SaleSearchFields");
			if (saleSearchFields != null)
			{
				Class<?>[] keyClassesIn111 = {String.class};
				Class<?>[] valueClassesIn111 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.SaleSearchFields").
					checkMap(saleSearchFields, keyClassesIn111, valueClassesIn111);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> propertyTypeSearchFields =
				inputParameters.getMap("action.SearchProperties.PropertyTypeSearchFields");
			if (propertyTypeSearchFields != null)
			{
				Class<?>[] keyClassesIn112 = {String.class};
				Class<?>[] valueClassesIn112 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.PropertyTypeSearchFields").
					checkMap(propertyTypeSearchFields, keyClassesIn112, valueClassesIn112);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> residentialFeaturesSearchFields =
				inputParameters.getMap("action.SearchProperties.ResidentialFeaturesSearchFields");
			if (residentialFeaturesSearchFields != null)
			{
				Class<?>[] keyClassesIn113 = {String.class};
				Class<?>[] valueClassesIn113 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.ResidentialFeaturesSearchFields").
					checkMap(residentialFeaturesSearchFields, keyClassesIn113, valueClassesIn113);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> commercialFeaturesSearchFields =
				inputParameters.getMap("action.SearchProperties.CommercialFeaturesSearchFields");
			if (commercialFeaturesSearchFields != null)
			{
				Class<?>[] keyClassesIn114 = {String.class};
				Class<?>[] valueClassesIn114 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.CommercialFeaturesSearchFields").
					checkMap(commercialFeaturesSearchFields, keyClassesIn114, valueClassesIn114);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			Map<String, Object> communityAmenitiesSearchFields =
				inputParameters.getMap("action.SearchProperties.CommunityAmenitiesSearchFields");
			if (communityAmenitiesSearchFields != null)
			{
				Class<?>[] keyClassesIn115 = {String.class};
				Class<?>[] valueClassesIn115 = {Object.class};
				vldResults = inputParameters.clearChecks().setName("action.SearchProperties.CommunityAmenitiesSearchFields").
					checkMap(communityAmenitiesSearchFields, keyClassesIn115, valueClassesIn115);
				if (vldResults != null)
					errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
						"action.InvalidParameter", request).
						addItem(vldResults));
			}
			if (errors.size() > 0) return errors;

			// --- BEGIN CUSTOM CODE ---
			boolean searchRentals = true, searchSales = true;
			
			if (propertyListingType != null)
			{
				searchRentals = propertyListingType.indexOf("rental") >= 0;
				searchSales = propertyListingType.indexOf("sale") >= 0;
			}
			
			SelectBuilder sbRentals = null, sbSales = null;
			List rentalResults = null, salesResults = null;

			// if rentals are sought, prepare the query
			if (searchRentals)
			{
				sbRentals = new SelectBuilder();
				sbRentals.addSelect("\"tRentals\".idk AS \"rentalIdk\", \"periodMonths\", \"rentForPeriod\", \"tRentals\".\"currencyIdk\" AS \"rentalCurrencyIdk\"");
				sbRentals.addFrom("\"tRentals\"");
				if (rentalSearchFields != null)
					Rental.applySearchFields(rentalSearchFields, sbRentals);
				
				sbRentals.addSelect("\"tPropertyListings\".idk AS \"propertyListingIdk\", \"tPropertyListings\".\"propertyIdk\" AS \"propertyIdk\"");
				sbRentals.addSelect("\"tPropertyListings\".summary AS \"listingSummary\", \"tPropertyListings\".\"systemKey\", \"userKey\", direct");
				sbRentals.addSelect("\"timePublicAccessStarts\", \"timePublicAccessEnds\", (CURRENT_DATE > \"timePublicAccessEnds\") AS expired");
				sbRentals.addSelect("\"tPropertyListings\".\"timeLastModified\" AS \"timeLastModified\"");
				sbRentals.addFrom("\"tPropertyListings\"");
				sbRentals.addWhere("(\"tPropertyListings\".\"metaFlags\" & 1) = 0", true);

				sbRentals.addWhere("\"tRentals\".idk > 999 AND \"tRentals\".idk = \"tPropertyListings\".\"forRentIdk\"", true);
				if (dateRange == null || dateRange.equals("unexpired"))
					sbRentals.addWhere("CAST (\"timePublicAccessStarts\" AS DATE) <= CURRENT_DATE AND CURRENT_DATE <= \"timePublicAccessEnds\"", true);
				else if (dateRange.equals("expired"))
					sbRentals.addWhere("CURRENT_DATE > \"timePublicAccessEnds\"", true);
					
				if (sortOrder == null || sortOrder.equalsIgnoreCase("datedes"))
					sbRentals.addOrder("\"timeLastModified\" DESC");
				else if (sortOrder.equalsIgnoreCase("dateasc"))
					sbRentals.addOrder("\"timeLastModified\" ASC");
				else if (sortOrder.equalsIgnoreCase("pricedes"))
					sbRentals.addOrder("(\"rentForPeriod\" / \"periodMonths\") DESC");
				else if (sortOrder.equalsIgnoreCase("priceasc"))
					sbRentals.addOrder("(\"rentForPeriod\" / \"periodMonths\") ASC");
					
				if (propertyListingSearchFields != null)
					PropertyListing.applySearchFields(propertyListingSearchFields, sbRentals);

				LinkedHashMap<String, Object> searchFields = new LinkedHashMap<String, Object>();
				searchFields.put("category", "propertyListerType");
				searchFields.put("name", "propertyListerType.Creator");
				Map<Long, Map<String, Object>> propertyOptions = PropertyOption.cacheLookup(searchFields);
				Long creatorPropertyListerTypeIdk = propertyOptions.keySet().iterator().next();
				
				sbRentals.addSelect("\"tPropertyListers\".\"listerIdk\" AS \"propertyListerIdk\"");
				sbRentals.addFrom("\"tPropertyListers\"");
				sbRentals.addWhere("\"tPropertyListers\".\"listingIdk\" = \"tPropertyListings\".idk", true);
				sbRentals.addWhere("\"tPropertyListers\".\"listerTypeIdk\" = " + creatorPropertyListerTypeIdk.longValue(), true);

				if (testUserProperties != null)
				{
					sbRentals.addFrom("\"tUsers\"");
					sbRentals.addWhere("\"tUsers\".\"testUser\" = $$" + testUserProperties + "$$", true);
					sbRentals.addWhere("\"tPropertyListers\".\"listerIdk\" = \"tUsers\".idk", true);
				}
				if (username != null && !username.trim().equals(""))
				{
					sbRentals.addFrom("\"tUsers\"");
					sbRentals.addWhere("\"tUsers\".username = $$" + username + "$$", true);
					sbRentals.addWhere("\"tPropertyListers\".\"listerIdk\" = \"tUsers\".idk", true);
					// already added above.
					// sbRentals.addWhere("\"tPropertyListers\".\"listingIdk\" = \"tPropertyListings\".idk", true);
				}

				sbRentals.addSelect("\"tUserProfiles\".organization AS organization, \"tUserProfiles\".telephone AS telephone, " +
					"\"tUserProfiles\".mobile AS mobile");
				sbRentals.addFrom("\"tUserProfiles\"");
				sbRentals.addWhere("\"tUserProfiles\".\"userIdk\" = \"tPropertyListers\".\"listerIdk\"", true);

				if (telephone != null && !telephone.trim().equals(""))
					sbRentals.addWhere("\"tUserProfiles\".telephone = $$" + telephone + "$$", true);
				if (mobile != null && !mobile.trim().equals(""))
					sbRentals.addWhere("\"tUserProfiles\".mobile = $$" + mobile + "$$", true);

				applySearchPropertiesConstraints(sbRentals, propertySearchFields, communityAmenitiesSearchFields, 
					propertyTypeSearchFields, residentialFeaturesSearchFields, commercialFeaturesSearchFields);

				System.out.println("\n\n\n" + sbRentals.toString() + "\n\n\n\n");
			
				rentalResults = BaseEntity.findByQuery(false, manager, sbRentals, null, null);
			}
			
			// if sales are sought, prepare the query
			if (searchSales)
			{
				sbSales = new SelectBuilder();
				sbSales.addSelect("\"tSales\".idk AS \"saleIdk\", \"bidTypeIdk\", bid, \"priceTypeIdk\", \"cashPrice\", \"pricePerTypeIdk\", \"installmentsAcceptable\", \"installmentsPrice\", \"tSales\".\"currencyIdk\" AS \"saleCurrencyIdk\"");
				sbSales.addFrom("\"tSales\"");
				if (saleSearchFields != null)
					Sale.applySearchFields(saleSearchFields, sbSales);

				// --- Begin special code to handle searching by square meter for empty lots
				Map<String, Object> searchFields1 = new LinkedHashMap<String, Object>();
				String propertyType = null;
				if (propertySearchFields != null)
				{
					Long propertyTypeIdk = (Long) propertySearchFields.get("propertyTypeIdk");
					searchFields1.put("idk", propertyTypeIdk);
					propertyType = (String) PropertyOption.cacheLookup(searchFields1).get(propertyTypeIdk).get("name");
				}
				
				if (propertyType != null && propertyType.equals("propertyType.EmptyLot"))
				{	
					Long pricePerTypeIdk = null;
					if (saleSearchFields != null)
						pricePerTypeIdk = (Long) saleSearchFields.get("pricePerTypeIdk");
					if (pricePerTypeIdk != null)
					{
						String pricePerType = (String) PropertyOption.cacheLookup(pricePerTypeIdk).get("name");
						
						searchFields1.clear();
						searchFields1.put("name", "pricePerType.SquareMeter");
						Long pricePerTypeSquareMeterIdk = PropertyOption.cacheLookup(searchFields1).keySet().iterator().next();
						
						searchFields1.clear();
						searchFields1.put("name", "pricePerType.Property");
						Long pricePerTypePropertyIdk = PropertyOption.cacheLookup(searchFields1).keySet().iterator().next();
	
						if (pricePerType.equals("pricePerType.Property"))
						{
							if (saleSearchFields.get("cashPriceMinimum") != null && saleSearchFields.get("cashPriceMinimum").toString().length() > 0)
							{
								sbSales.addWhere(
									// Compare srch_total_price with db_total_price
									"((\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypePropertyIdk +
										" AND " +
										"(\"tSales\".\"cashPrice\" >= " + saleSearchFields.get("cashPriceMinimum") +
											" OR " +
											"\"tSales\".bid >= " + saleSearchFields.get("cashPriceMinimum") + "))" +
									" OR " +
									// Compare srch_total_price with db_price_sqm * size
									"(\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypeSquareMeterIdk +
										" AND ((\"tSales\".\"cashPrice\" * \"tEmptyLots\".\"landSize\") >= " + saleSearchFields.get("cashPriceMinimum") +
											" OR " + 
											"(\"tSales\".bid * \"tEmptyLots\".\"landSize\") >= " + saleSearchFields.get("cashPriceMinimum") + ")" +
										"))", 
									true);
							}
							if (saleSearchFields.get("cashPriceMaximum") != null && saleSearchFields.get("cashPriceMaximum").toString().length() > 0)
							{
								sbSales.addWhere(
									// Compare srch_total_price with db_total_price
									"((\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypePropertyIdk +
										" AND " +
										"(\"tSales\".\"cashPrice\" <= " + saleSearchFields.get("cashPriceMaximum") +
											" OR " +
											"\"tSales\".bid <= " + saleSearchFields.get("cashPriceMaximum") + "))" +
									" OR " +
									// Compare srch_total_price with db_price_sqm * size
									"(\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypeSquareMeterIdk +
										" AND ((\"tSales\".\"cashPrice\" * \"tEmptyLots\".\"landSize\") <= " + saleSearchFields.get("cashPriceMaximum") +
											" OR " +
											"(\"tSales\".bid * \"tEmptyLots\".\"landSize\") <= " + saleSearchFields.get("cashPriceMaximum") + ")" +
										"))", 
									true);
							}
						}
						else if (pricePerType.equals("pricePerType.SquareMeter"))
						{
							if (saleSearchFields.get("cashPriceMinimum") != null && saleSearchFields.get("cashPriceMinimum").toString().length() > 0)
							{
								sbSales.addWhere(
									// Compare srch_total_price with db_total_price
									"((\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypePropertyIdk +
										" AND ((\"tSales\".\"cashPrice\" / \"tEmptyLots\".\"landSize\") >= " + saleSearchFields.get("cashPriceMinimum") +
											" OR " +
											"(\"tSales\".bid / \"tEmptyLots\".\"landSize\") >= " + saleSearchFields.get("cashPriceMinimum") + ")" +
										")" +
									" OR " +
									// Compare srch_total_price with db_price_sqm * size
									"(\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypeSquareMeterIdk +
										" AND (\"tSales\".\"cashPrice\" >= " + saleSearchFields.get("cashPriceMinimum") +
											" OR " +
											"\"tSales\".bid >= " + saleSearchFields.get("cashPriceMinimum") + ")" +
										"))", 
									true);
							}
							if (saleSearchFields.get("cashPriceMaximum") != null && saleSearchFields.get("cashPriceMaximum").toString().length() > 0)
							{
								sbSales.addWhere(
									// Compare srch_total_price with db_total_price
									"((\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypePropertyIdk +
										" AND ((\"tSales\".\"cashPrice\" / \"tEmptyLots\".\"landSize\") <= " + saleSearchFields.get("cashPriceMaximum") +
											" OR " +
											"(\"tSales\".bid / \"tEmptyLots\".\"landSize\") <= " + saleSearchFields.get("cashPriceMaximum") + ")" +
										")" +
									" OR " +
									// Compare srch_total_price with db_price_sqm * size
									"(\"tSales\".\"pricePerTypeIdk\" = " + pricePerTypeSquareMeterIdk +
										" AND (\"tSales\".\"cashPrice\" <= " + saleSearchFields.get("cashPriceMaximum") +
											" OR " +
											"\"tSales\".bid <= " + saleSearchFields.get("cashPriceMaximum") + ")" +
										"))", 
									true);
							}
						}
					}
				}
				else // all other property types
				{
					if (saleSearchFields != null)
					{
						if (saleSearchFields.get("cashPriceMinimum") != null && saleSearchFields.get("cashPriceMinimum").toString().length() > 0)
							sbSales.addWhere(
								"((\"tSales\".\"cashPrice\" >= " + saleSearchFields.get("cashPriceMinimum") + ")" +
								" OR " +
								"(\"tSales\".bid >= " + saleSearchFields.get("cashPriceMinimum") + "))", 
								true);
						if (saleSearchFields.get("cashPriceMaximum") != null && saleSearchFields.get("cashPriceMaximum").toString().length() > 0)
							sbSales.addWhere(
								"((\"tSales\".\"cashPrice\" <= " + saleSearchFields.get("cashPriceMaximum") + ")" +
								" OR " +
								"(\"tSales\".bid <= " + saleSearchFields.get("cashPriceMaximum") + "))",
								true);
					}
				}
				// --- End special code to handle searching by square meter for empty lots

				sbSales.addSelect("\"tPropertyListings\".idk AS \"propertyListingIdk\", \"tPropertyListings\".\"propertyIdk\" AS \"propertyIdk\"");
				sbSales.addSelect("\"tPropertyListings\".summary AS \"listingSummary\", \"tPropertyListings\".\"systemKey\", \"userKey\", direct");
				sbSales.addSelect("\"timePublicAccessStarts\", \"timePublicAccessEnds\", (CURRENT_DATE > \"timePublicAccessEnds\") AS expired");
				sbSales.addSelect("\"tPropertyListings\".\"timeLastModified\" AS \"timeLastModified\"");
				sbSales.addFrom("\"tPropertyListings\"");
				sbSales.addWhere("(\"tPropertyListings\".\"metaFlags\" & 1) = 0", true);

				sbSales.addWhere("\"tSales\".idk > 999 AND \"tSales\".idk = \"tPropertyListings\".\"forSaleIdk\"", true);
				if (dateRange == null || dateRange.equals("unexpired"))
					sbSales.addWhere("CAST (\"timePublicAccessStarts\" AS DATE) <= CURRENT_DATE AND CURRENT_DATE <= \"timePublicAccessEnds\"", true);
				else if (dateRange.equals("expired"))
					sbSales.addWhere("CURRENT_DATE > \"timePublicAccessEnds\"", true);

				if (sortOrder == null || sortOrder.equalsIgnoreCase("datedes"))
					sbSales.addOrder("\"timeLastModified\" DESC");
				else if (sortOrder.equalsIgnoreCase("dateasc"))
					sbSales.addOrder("\"timeLastModified\" ASC");
				else if (sortOrder.equalsIgnoreCase("pricedes"))
					sbSales.addOrder("\"cashPrice\" DESC");
				else if (sortOrder.equalsIgnoreCase("priceasc"))
					sbSales.addOrder("\"cashPrice\" ASC");

				if (propertyListingSearchFields != null)
					PropertyListing.applySearchFields(propertyListingSearchFields, sbSales);

				LinkedHashMap<String, Object> searchFields = new LinkedHashMap<String, Object>();
				searchFields.put("category", "propertyListerType");
				searchFields.put("name", "propertyListerType.Creator");
				Map<Long, Map<String, Object>> propertyOptions = PropertyOption.cacheLookup(searchFields);
				Long creatorPropertyListerTypeIdk = propertyOptions.keySet().iterator().next();
				
				sbSales.addSelect("\"tPropertyListers\".\"listerIdk\" AS \"propertyListerIdk\"");
				sbSales.addFrom("\"tPropertyListers\"");
				sbSales.addWhere("\"tPropertyListers\".\"listingIdk\" = \"tPropertyListings\".idk", true);
				sbSales.addWhere("\"tPropertyListers\".\"listerTypeIdk\" = " + creatorPropertyListerTypeIdk.longValue(), true);

				if (testUserProperties != null)
				{
					sbSales.addFrom("\"tUsers\"");
					sbSales.addWhere("\"tUsers\".\"testUser\" = $$" + testUserProperties + "$$", true);
					sbSales.addWhere("\"tPropertyListers\".\"listerIdk\" = \"tUsers\".idk", true);
				}
				if (username != null && !username.trim().equals(""))
				{
					sbSales.addFrom("\"tUsers\"");
					sbSales.addWhere("\"tUsers\".username = $$" + username + "$$", true);
					sbSales.addWhere("\"tPropertyListers\".\"listerIdk\" = \"tUsers\".idk", true);
					// already added above.
					// sbSales.addWhere("\"tPropertyListers\".\"listingIdk\" = \"tPropertyListings\".idk", true);
				}

				sbSales.addSelect("\"tUserProfiles\".organization AS organization, \"tUserProfiles\".telephone AS telephone, " +
					"\"tUserProfiles\".mobile AS mobile");
				sbSales.addFrom("\"tUserProfiles\"");
				sbSales.addWhere("\"tUserProfiles\".\"userIdk\" = \"tPropertyListers\".\"listerIdk\"", true);

				if (telephone != null && !telephone.trim().equals(""))
					sbSales.addWhere("\"tUserProfiles\".telephone = $$" + telephone + "$$", true);
				if (mobile != null && !mobile.trim().equals(""))
					sbSales.addWhere("\"tUserProfiles\".mobile = $$" + mobile + "$$", true);

				applySearchPropertiesConstraints(sbSales, propertySearchFields, communityAmenitiesSearchFields, 
					propertyTypeSearchFields, residentialFeaturesSearchFields, commercialFeaturesSearchFields);	

				System.out.println("\n\n\n" + sbSales.toString() + "\n\n\n\n");
			
				salesResults = BaseEntity.findByQuery(false, manager, sbSales, null, null);
			}
			
			// TBD: find the property listers in a separate query - may be more than one per property
			
			Map<Long, Map<String,Object>> rentalRows = new LinkedHashMap<Long, Map<String,Object>>();
			if (rentalResults != null && rentalResults.size() > 0)
			{
				List<String> columnNames = null;
				columnNames = sbRentals.getColumnNames(true);

				for (Object entry : rentalResults)
				{
					List rowValues = (List) entry;
					Long rowKey = null;
					
					if (rowValues.size() != columnNames.size())
						throw new java.lang.IndexOutOfBoundsException("Row size does not match column names size");
					LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>();
					for (int i=0; i<rowValues.size(); i++)
					{
						// System.out.println("Rental result column + value: " + columnNames.get(i) + " " + rowValues.get(i));
						row.put(columnNames.get(i), rowValues.get(i));
						if (columnNames.get(i).equals("propertyListingIdk"))
							rowKey = (Long) rowValues.get(i);
						
						if (columnNames.get(i).equals("propertyTypeIdk"))
							row.put( "propertyTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("landTypeIdk"))
							row.put( "landTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("countryIdk"))
							row.put( "countryName", Country.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("intendedBusinessTypeIdk"))
							row.put( "intendedBusinessTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("irrigationTypeIdk"))
							row.put( "irrigationTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("regionIdk"))
							row.put( "regionName", Region.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("cityIdk"))
							row.put( "cityName", City.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("districtIdk"))
							row.put( "districtName", District.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("generalConditionIdk"))
							row.put( "generalConditionDescription", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("rentalCurrencyIdk"))
							row.put( "rentalCurrencySymbol", Currency.cacheLookup((Long) rowValues.get(i)).get("symbol") );
						else if (columnNames.get(i).equals("saleCurrencyIdk"))
							row.put( "saleCurrencySymbol", Currency.cacheLookup((Long) rowValues.get(i)).get("symbol") );
						else if (columnNames.get(i).endsWith("AreaUnitTypeIdk"))
							row.put( columnNames.get(i).replaceAll("Idk", "Name"), PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
					}

					rentalRows.put(rowKey, row);
				}
			}
			
			Map<Long, Map<String,Object>> salesRows = new LinkedHashMap<Long, Map<String,Object>>();
			if (salesResults != null && salesResults.size() > 0)
			{
				List<String> columnNames = null;
				columnNames = sbSales.getColumnNames(true);
				
				for (Object entry : salesResults)
				{
					List rowValues = (List) entry;
					Long rowKey = null;
					
					if (rowValues.size() != columnNames.size())
						throw new java.lang.IndexOutOfBoundsException("Row size does not match column names size");
					LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>();
					for (int i=0; i<rowValues.size(); i++)
					{
						// System.out.println("Sales result column + value: " + columnNames.get(i) + " " + rowValues.get(i));
						row.put(columnNames.get(i), rowValues.get(i));
						if (columnNames.get(i).equals("propertyListingIdk"))
							rowKey = (Long) rowValues.get(i);

						if (columnNames.get(i).equals("propertyTypeIdk"))
							row.put( "propertyTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("pricePerTypeIdk"))
							row.put( "pricePerTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("priceTypeIdk"))
							row.put( "priceTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("bidTypeIdk"))
							row.put( "bidTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("landTypeIdk"))
							row.put( "landTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("countryIdk"))
							row.put( "countryName", Country.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("intendedBusinessTypeIdk"))
							row.put( "intendedBusinessTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("irrigationTypeIdk"))
							row.put( "irrigationTypeName", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("regionIdk"))
							row.put( "regionName", Region.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("cityIdk"))
							row.put( "cityName", City.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("districtIdk"))
							row.put( "districtName", District.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("generalConditionIdk"))
							row.put( "generalConditionDescription", PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
						else if (columnNames.get(i).equals("rentalCurrencyIdk"))
							row.put( "rentalCurrencySymbol", Currency.cacheLookup((Long) rowValues.get(i)).get("symbol") );
						else if (columnNames.get(i).equals("saleCurrencyIdk"))
							row.put( "saleCurrencySymbol", Currency.cacheLookup((Long) rowValues.get(i)).get("symbol") );
						else if (columnNames.get(i).endsWith("AreaUnitTypeIdk"))
							row.put( columnNames.get(i).replaceAll("Idk", "Name"), PropertyOption.cacheLookup((Long) rowValues.get(i)).get("name") );
					}
					salesRows.put(rowKey, row);
				}
			}

			/*if (rentalRows != null)
			{
				for (Long key : rentalRows.keySet())
				{
					StringBuffer values = new StringBuffer("");
					values.append("\n\nFor rent: " + key + "\n");
					Map<String, Object> row = rentalRows.get(key);
					
					for (String name : row.keySet())
						values.append("\t" + name + " = " + row.get(name) + "\n");
					System.out.println(values.toString() + "\n\n\n\n");
				}
			}
			if (salesRows != null)
			{
				for (Long key : salesRows.keySet())
				{
					StringBuffer values = new StringBuffer("");
					values.append("\n\nFor sale: " + key + "\n");
					Map<String, Object> row = salesRows.get(key);
					
					for (String name : row.keySet())
						values.append("\t" + name + " = " + row.get(name) + "\n");
					System.out.println(values.toString() + "\n\n\n\n");
				}
			}
			*/
			// --- END CUSTOM CODE ---
			// Package output parameters...
			if (rentalRows != null && rentalRows.size() > 0)
			{
				Class<?>[] keyClassesOut11 = {Long.class, String.class};
				Class<?>[] valueClassesOut11 = {Map.class, Object.class};
				outputParameters.setMap("action.SearchProperties.Rentals", rentalRows, keyClassesOut11, valueClassesOut11);
			}
			if (salesRows != null && salesRows.size() > 0)
			{
				Class<?>[] keyClassesOut12 = {Long.class, String.class};
				Class<?>[] valueClassesOut12 = {Map.class, Object.class};
				outputParameters.setMap("action.SearchProperties.Sales", salesRows, keyClassesOut12, valueClassesOut12);
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
	 * Method: "action.RetrieveProperty", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long action.RetrieveProperty.PropertyListingIdk (propertyListingIdk)
	 *   Output: Map action.RetrieveProperty.PropertyListing (propertyListing.getAllFields())
	 *   Output: Map action.RetrieveProperty.Property (property.getAllFields())
	 *   Output: Map action.RetrieveProperty.PropertyLister (propertyLister.getAllFields())
	 *   Output: Map action.RetrieveProperty.PropertyListerUser (propertyListerUser.getAllFields())
	 *   Output: Map action.RetrieveProperty.PropertyListerUserProfile (propertyListerUserProfile.getAllFields())
	 *   Output: Map action.RetrieveProperty.Sale (sale.getAllFields())
	 *   Output: Map action.RetrieveProperty.Rental (rental.getAllFields())
	 *   Output: Map action.RetrieveProperty.CommunityAmenities (communityAmenities.getAllFields())
	 *   Output: String action.RetrieveProperty.PropertyType (propertyType)
	 *   Output: Map action.RetrieveProperty.ExtendedProperty (extendedProperty.getAllFields())
	 *   Output: Map action.RetrieveProperty.ResidentialFeatures (residentialFeatures.getAllFields())
	 *   Output: Map action.RetrieveProperty.CommercialFeatures (commercialFeatures.getAllFields())
	 *************************************************************************/
	public List<ErrorReport> retrieveProperty(ActionRequest request, ActionResponse response)
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
			Long propertyListingIdk =
				inputParameters.getLong("action.RetrieveProperty.PropertyListingIdk");
			vldResults = inputParameters.clearChecks().setName("action.RetrieveProperty.PropertyListingIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(propertyListingIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			// --- BEGIN CUSTOM CODE ---
			PropertyListing propertyListing = PropertyListing.findByIdk(manager, propertyListingIdk);
			
			Property property = Property.findByIdk(manager, propertyListing.getPropertyIdk());
	
			// get the creator only for now
			LinkedHashMap<String, Object> searchFields = new LinkedHashMap<String, Object>();
			searchFields.put("category", "propertyListerType");
			searchFields.put("name", "propertyListerType.Creator");
			Map<Long, Map<String, Object>> propertyOptions = PropertyOption.cacheLookup(searchFields);
			Long creatorPropertyListerTypeIdk = propertyOptions.keySet().iterator().next();
	
			searchFields.clear();
			searchFields.put("\"listingIdk\"", propertyListingIdk);
			searchFields.put("\"listerTypeIdk\"", creatorPropertyListerTypeIdk);
			PropertyLister propertyLister = 
				(PropertyLister) ((List) PropertyLister.findPropertyListerByFields(manager, searchFields)).get(0);
			
			User propertyListerUser = User.findByIdk(manager, propertyLister.getListerIdk());
			searchFields.clear();
			searchFields.put("\"userIdk\"", propertyListerUser.getIdk());
			UserProfile propertyListerUserProfile = (UserProfile) ((List) UserProfile.findUserProfileByFields(manager, searchFields)).get(0);
	
			// update the number of views			
			if (request.getLoginToken().getUserIdk() != propertyListerUser.getIdk())
			{
				int numberOfViews = (propertyListing.getNumberOfViews() != null ?
					propertyListing.getNumberOfViews() : 0);
				propertyListing.setNumberOfViews(numberOfViews + 1);
				propertyListing = manager.merge(propertyListing);
				manager.flush();
			}
			
			Sale sale = null;
			Rental rental = null;
			if (propertyListing.getForSaleIdk() != null && propertyListing.getForSaleIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				sale = Sale.findByIdk(manager, propertyListing.getForSaleIdk());
			if (propertyListing.getForRentIdk() != null && propertyListing.getForRentIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)
				rental = Rental.findByIdk(manager, propertyListing.getForRentIdk());
	
			CommunityAmenities communityAmenities = CommunityAmenities.findByIdk(manager, property.getCommunityAmenitiesIdk());
			
			String propertyType = (String) PropertyOption.cacheLookup(property.getPropertyTypeIdk()).get("name");
			
			BaseEntity extendedProperty = null;
			ResidentialFeatures residentialFeatures = null;
			CommercialFeatures commercialFeatures = null;
			
			if (propertyType == null || propertyType.equals(""))
			{
				throw new IllegalArgumentException("Unrecognized property type: " + propertyType);
			}
			else if (propertyType.equals("propertyType.Apartment"))
			{
				extendedProperty = 
					(BaseEntity) Apartment.findApartmentByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0);
				residentialFeatures = 
					ResidentialFeatures.findByIdk(manager, ((Apartment) extendedProperty).getResidentialFeaturesIdk());
				// need to return to caller more than idk's; need to return the keys to be used for message resources
				// otherwise the caller will have to make extra calls to database anyway...? or perhaps the caller
				// will simply use the cache... in which case, we can just use the cache here also, and save that
				// second call, even if it is only to the cache.
				// another problem: where to put all of these keys? should the entity support them? should idk values
				// always be shadowed with fillable String values? when it comes time to send the data to a caller
				// the shadow values would be filled in, "fillInIdkStrings" which would use tPropertyOptions, and other
				// tables to fill in those values...?
				// Update: added code elsewhere to automatically append resource string equivalents of property options
			}
			else if (propertyType.equals("propertyType.EmptyLot"))
			{
				extendedProperty = 
					(BaseEntity) EmptyLot.findEmptyLotByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
			}
			else if (propertyType.equals("propertyType.FarmLot"))
			{
				extendedProperty = 
					(BaseEntity) FarmLot.findFarmLotByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
			}
			else if (propertyType.equals("propertyType.FloorUnit"))
			{
				extendedProperty = 
					(BaseEntity) FloorUnit.findFloorUnitByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
				residentialFeatures = 
					ResidentialFeatures.findByIdk(manager, ((FloorUnit) extendedProperty).getResidentialFeaturesIdk());
			}
			else if (propertyType.equals("propertyType.House"))
			{
				extendedProperty = 
					(BaseEntity) House.findHouseByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
				residentialFeatures = 
					ResidentialFeatures.findByIdk(manager, ((House) extendedProperty).getResidentialFeaturesIdk());
			}
			else if (propertyType.equals("propertyType.MultiUnitBuilding"))
			{
				extendedProperty = 
					(BaseEntity) MultiUnitBuilding.findMultiUnitBuildingByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
				residentialFeatures = 
					ResidentialFeatures.findByIdk(manager, ((MultiUnitBuilding) extendedProperty).getUnitResidentialFeaturesIdk());
				commercialFeatures = 
					CommercialFeatures.findByIdk(manager, ((MultiUnitBuilding) extendedProperty).getUnitCommercialFeaturesIdk());
			}
			else if (propertyType.equals("propertyType.MultiUnitCompound"))
			{
				extendedProperty = 
					(BaseEntity) MultiUnitCompound.findMultiUnitCompoundByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
				residentialFeatures = 
					ResidentialFeatures.findByIdk(manager, ((MultiUnitCompound) extendedProperty).getUnitResidentialFeaturesIdk());
				commercialFeatures = 
					CommercialFeatures.findByIdk(manager, ((MultiUnitCompound) extendedProperty).getUnitCommercialFeaturesIdk());
			}
			else if (propertyType.equals("propertyType.Office"))
			{
				extendedProperty = 
					(BaseEntity) Office.findOfficeByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
				commercialFeatures = 
					CommercialFeatures.findByIdk(manager, ((Office) extendedProperty).getCommercialFeaturesIdk());
			}
			else if (propertyType.equals("propertyType.Store"))
			{
				extendedProperty = 
					(BaseEntity) Store.findStoreByCondition(manager, "\"propertyIdk\" = " + property.getIdk(), null).get(0); 
				commercialFeatures = 
					CommercialFeatures.findByIdk(manager, ((Store) extendedProperty).getCommercialFeaturesIdk());
			}
			// --- END CUSTOM CODE ---

			// Package output parameters...
			Class<?>[] keyClassesOut11 = {String.class};
			Class<?>[] valueClassesOut11 = {Object.class};
			outputParameters.setMap("action.RetrieveProperty.PropertyListing", propertyListing.getAllFields(), keyClassesOut11, valueClassesOut11);
			Class<?>[] keyClassesOut12 = {String.class};
			Class<?>[] valueClassesOut12 = {Object.class};
			outputParameters.setMap("action.RetrieveProperty.Property", property.getAllFields(), keyClassesOut12, valueClassesOut12);
			Class<?>[] keyClassesOut13 = {String.class};
			Class<?>[] valueClassesOut13 = {Object.class};
			outputParameters.setMap("action.RetrieveProperty.PropertyLister", propertyLister.getAllFields(), keyClassesOut13, valueClassesOut13);
			Class<?>[] keyClassesOut14 = {String.class};
			Class<?>[] valueClassesOut14 = {Object.class};
			outputParameters.setMap("action.RetrieveProperty.PropertyListerUser", propertyListerUser.getAllFields(), keyClassesOut14, valueClassesOut14);
			Class<?>[] keyClassesOut15 = {String.class};
			Class<?>[] valueClassesOut15 = {Object.class};
			outputParameters.setMap("action.RetrieveProperty.PropertyListerUserProfile", propertyListerUserProfile.getAllFields(), keyClassesOut15, valueClassesOut15);
			if (sale != null)
			{
				Class<?>[] keyClassesOut16 = {String.class};
				Class<?>[] valueClassesOut16 = {Object.class};
				outputParameters.setMap("action.RetrieveProperty.Sale", sale.getAllFields(), keyClassesOut16, valueClassesOut16);
			}
			if (rental != null)
			{
				Class<?>[] keyClassesOut17 = {String.class};
				Class<?>[] valueClassesOut17 = {Object.class};
				outputParameters.setMap("action.RetrieveProperty.Rental", rental.getAllFields(), keyClassesOut17, valueClassesOut17);
			}
			if (communityAmenities != null)
			{
				Class<?>[] keyClassesOut18 = {String.class};
				Class<?>[] valueClassesOut18 = {Object.class};
				outputParameters.setMap("action.RetrieveProperty.CommunityAmenities", communityAmenities.getAllFields(), keyClassesOut18, valueClassesOut18);
			}
			outputParameters.setString("action.RetrieveProperty.PropertyType", propertyType);
			Class<?>[] keyClassesOut110 = {String.class};
			Class<?>[] valueClassesOut110 = {Object.class};
			outputParameters.setMap("action.RetrieveProperty.ExtendedProperty", extendedProperty.getAllFields(), keyClassesOut110, valueClassesOut110);
			if (residentialFeatures != null)
			{
				Class<?>[] keyClassesOut111 = {String.class};
				Class<?>[] valueClassesOut111 = {Object.class};
				outputParameters.setMap("action.RetrieveProperty.ResidentialFeatures", residentialFeatures.getAllFields(), keyClassesOut111, valueClassesOut111);
			}
			if (commercialFeatures != null)
			{
				Class<?>[] keyClassesOut112 = {String.class};
				Class<?>[] valueClassesOut112 = {Object.class};
				outputParameters.setMap("action.RetrieveProperty.CommercialFeatures", commercialFeatures.getAllFields(), keyClassesOut112, valueClassesOut112);
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
	 * Method: "action.DeactivateListing", v1 (3 tasks)
	 * Task 1: Read
	 *   Input: Long action.DeactivateListing.PropertyListingIdk (propertyListingIdk)
	 * Task 2: Custom code
	 * Task 3: Update
	 *************************************************************************/
	public List<ErrorReport> deactivateListing(ActionRequest request, ActionResponse response)
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
			Long propertyListingIdk =
				inputParameters.getLong("action.DeactivateListing.PropertyListingIdk");
			vldResults = inputParameters.clearChecks().setName("action.DeactivateListing.PropertyListingIdk").
				setValidMinimum((double) PrimaryKeyHolder.KEY_HIGHEST_RESERVED + 1).
				checkLong(propertyListingIdk);
			if (vldResults != null)
				errors.add(new ErrorReport("error.System.CoreServer", ErrorReport.Source.PARAMETER,
					"action.InvalidParameter", request).
					addItem(vldResults));
			if (errors.size() > 0) return errors;

			// Execute read operation...
			PropertyListing propertyListing = PropertyListing.findByIdk(manager, propertyListingIdk);

			// ----- End Task 1 -----------------------------------------------

			// ----- Begin Task 2 ---------------------------------------------
			// --- BEGIN CUSTOM CODE ---
			propertyListing.setMetaFlags( propertyListing.getMetaFlags() | (long) 1);
			// --- END CUSTOM CODE ---
			// ----- End Task 2 -----------------------------------------------

			// ----- Begin Task 3 ---------------------------------------------
			// Execute update operation...
			propertyListing = manager.merge(propertyListing);
			manager.flush();
			Integer affectedEntitiesCounter3 = 1;

			// ----- End Task 3 -----------------------------------------------

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

