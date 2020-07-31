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
public interface PropertyUpdateManager
{
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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

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
		throws CSWrapperException;

}

