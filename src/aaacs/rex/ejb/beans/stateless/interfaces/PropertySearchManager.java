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
public interface PropertySearchManager
{
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
		throws CSWrapperException;

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
		throws CSWrapperException;

	/*************************************************************************
	 * Method: "action.DeactivateListing", v1 (3 tasks)
	 * Task 1: Read
	 *   Input: Long action.DeactivateListing.PropertyListingIdk (propertyListingIdk)
	 * Task 2: Custom code
	 * Task 3: Update
	 *************************************************************************/
	public List<ErrorReport> deactivateListing(ActionRequest request, ActionResponse response)
		throws CSWrapperException;

}

