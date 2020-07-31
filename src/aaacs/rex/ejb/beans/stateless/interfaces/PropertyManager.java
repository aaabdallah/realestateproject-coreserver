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
public interface PropertyManager
{
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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindAllPropertyDistricts", v1 (1 task)
	 * Task 1: Custom code
	 *   Output: List action.FindAllPropertyDistricts.Districts (list)
	 *************************************************************************/
	public List<ErrorReport> findAllPropertyDistricts(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

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
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindCommercialFeaturesByCreator", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long action.FindCommercialFeaturesByCreator.CreatedByIdk (createdByIdk)
	 *   Output: Map action.FindCommercialFeaturesByCreator.CommercialFeatures (entitiesToMaps(commercialFeaturesList))
	 *************************************************************************/
	public List<ErrorReport> findCommercialFeaturesByCreator(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindCommunityAmenitiesByCreator", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long action.FindCommunityAmenitiesByCreator.CreatedByIdk (createdByIdk)
	 *   Output: Map action.FindCommunityAmenitiesByCreator.CommunityAmenities (entitiesToMaps(communityAmenitiesList))
	 *************************************************************************/
	public List<ErrorReport> findCommunityAmenitiesByCreator(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindPropertyOptionsByCategory", v1 (1 task)
	 * Task 1: Read
	 *   Input: String action.FindPropertyOptionsByCategory.Category (category)
	 *   Output: Map action.FindPropertyOptionsByCategory.PropertyOptions (entitiesToMaps(propertyOptionList))
	 *************************************************************************/
	public List<ErrorReport> findPropertyOptionsByCategory(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindPropertyOptionByCategoryName", v1 (1 task)
	 * Task 1: Read
	 *   Input: String action.FindPropertyOptionByCategoryName.Category (category)
	 *   Input: String action.FindPropertyOptionByCategoryName.Name (name)
	 *   Output: Map action.FindPropertyOptionByCategoryName.PropertyOption (((PropertyOption) propertyOptionList.get(0)).getAllFields())
	 *************************************************************************/
	public List<ErrorReport> findPropertyOptionByCategoryName(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.FindResidentialFeaturesByCreator", v1 (1 task)
	 * Task 1: Read
	 *   Input: Long action.FindResidentialFeaturesByCreator.CreatedByIdk (createdByIdk)
	 *   Output: Map action.FindResidentialFeaturesByCreator.ResidentialFeatures (entitiesToMaps(residentialFeaturesList))
	 *************************************************************************/
	public List<ErrorReport> findResidentialFeaturesByCreator(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

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
		throws CSWrapperException;

}

