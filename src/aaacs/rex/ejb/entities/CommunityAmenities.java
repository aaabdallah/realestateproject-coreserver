/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.rex.ejb.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import aaacs.coreserver.database.SelectBuilder;
import aaacs.coreserver.ejb.entities.BaseEntity;
import aaacs.coreserver.commons.exceptions.CoreServerException;

import aaacs.coreserver.commons.utilities.ArrayHelper;

import aaacs.coreserver.commons.validation.FieldValidator;
import aaacs.coreserver.commons.validation.MsgArgsPair;


@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<CommunityAmenities>",
			entities =
			{
				@EntityResult
				(
					entityClass = CommunityAmenities.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "createdByIdk", column = "createdByIdk"),
						@FieldResult(name = "referenceHandle", column = "referenceHandle"),
						@FieldResult(name = "communityName", column = "communityName"),
						@FieldResult(name = "managedCommunity", column = "managedCommunity"),
						@FieldResult(name = "walled", column = "walled"),
						@FieldResult(name = "secureGates", column = "secureGates"),
						@FieldResult(name = "masjids", column = "masjids"),
						@FieldResult(name = "restaurants", column = "restaurants"),
						@FieldResult(name = "cafes", column = "cafes"),
						@FieldResult(name = "convenienceStores", column = "convenienceStores"),
						@FieldResult(name = "supermarkets", column = "supermarkets"),
						@FieldResult(name = "selfServeLaundromats", column = "selfServeLaundromats"),
						@FieldResult(name = "dryCleaners", column = "dryCleaners"),
						@FieldResult(name = "otherStores", column = "otherStores"),
						@FieldResult(name = "malePools", column = "malePools"),
						@FieldResult(name = "femalePools", column = "femalePools"),
						@FieldResult(name = "mixedPools", column = "mixedPools"),
						@FieldResult(name = "maleGymnasiums", column = "maleGymnasiums"),
						@FieldResult(name = "femaleGymnasiums", column = "femaleGymnasiums"),
						@FieldResult(name = "mixedGymnasiums", column = "mixedGymnasiums"),
						@FieldResult(name = "soccerFields", column = "soccerFields"),
						@FieldResult(name = "otherFields", column = "otherFields"),
						@FieldResult(name = "basketballCourts", column = "basketballCourts"),
						@FieldResult(name = "tennisCourts", column = "tennisCourts"),
						@FieldResult(name = "parks", column = "parks"),
						@FieldResult(name = "playgrounds", column = "playgrounds"),
						@FieldResult(name = "businessCenters", column = "businessCenters"),
						@FieldResult(name = "internetCenters", column = "internetCenters"),
						@FieldResult(name = "socialCenters", column = "socialCenters"),
						@FieldResult(name = "organizedWomensActivities", column = "organizedWomensActivities"),
						@FieldResult(name = "organizedChildrensActivities", column = "organizedChildrensActivities"),
						@FieldResult(name = "hospitals", column = "hospitals"),
						@FieldResult(name = "clinics", column = "clinics"),
						@FieldResult(name = "doctors", column = "doctors"),
						@FieldResult(name = "daycareCenters", column = "daycareCenters"),
						@FieldResult(name = "elementarySchools", column = "elementarySchools"),
						@FieldResult(name = "middleSchools", column = "middleSchools"),
						@FieldResult(name = "highSchools", column = "highSchools"),
						@FieldResult(name = "securityCenters", column = "securityCenters"),
						@FieldResult(name = "other", column = "other")
					}
				)
			}
		)
	}
)
@Entity(name="CommunityAmenities") @Table(name="\"tCommunityAmenities\"")
public class CommunityAmenities extends BaseEntity implements Serializable
{
	/**
	 * Serialization Version Number
	 */
	private static final long serialVersionUID = 1000L;
	/**
	 * Resources file
	 */
	public static final String entityResourceFile = "src.aaacs.rex.resources.entitynames";
	/**
	 * Entity table name
	 */
	public static final String entityTableName = "\"tCommunityAmenities\"";

	static public CommunityAmenities findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (CommunityAmenities) BaseEntity.findByIdk(manager, CommunityAmenities.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findCommunityAmenitiesByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<CommunityAmenities>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findCommunityAmenitiesByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<CommunityAmenities>");
	}

	static public List findByCondition(EntityManager manager, String[] columnNames,
		String condition, Vector<Object> conditionParameters, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, columnNames,
			condition, conditionParameters, sqlResultSetMapping);
	}

	static public int updateByFields(EntityManager manager, Map<String,Object> fieldsToSet,
		Map<String,Object> searchFields)
	{
		return BaseEntity.updateByFields(manager, entityTableName, 
			fieldsToSet, searchFields);
	}

	static public int updateByCondition(EntityManager manager, LinkedHashMap<String,Object> fieldsToSet,
		String condition, Vector<Object> conditionParameters)
	{
		return BaseEntity.updateByCondition(manager, entityTableName, fieldsToSet,
			condition, conditionParameters);
	}

	static public int removeByFields(EntityManager manager, Map<String,Object> searchFields)
	{
		return BaseEntity.removeByFields(manager, entityTableName, 
			searchFields);
	}

	static public int removeByCondition(EntityManager manager, String condition,
		Vector<Object> conditionParameters)
	{
		return BaseEntity.removeByCondition(manager, entityTableName, 
			condition, conditionParameters);
	}

	static public Map<String, List<MsgArgsPair>> validateCommunityAmenities(CommunityAmenities communityAmenities,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return communityAmenities.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateCreatedByIdk(Long createdByIdk)
	{
		return (new FieldValidator()).clearChecks().setName("createdByIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(createdByIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateReferenceHandle(String referenceHandle)
	{
		return (new FieldValidator()).clearChecks().setName("referenceHandle").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(referenceHandle);
	}

	static public Map<String, List<MsgArgsPair>> validateCommunityName(String communityName)
	{
		return (new FieldValidator()).clearChecks().setName("communityName").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(communityName);
	}

	static public Map<String, List<MsgArgsPair>> validateManagedCommunity(Boolean managedCommunity)
	{
		return (new FieldValidator()).clearChecks().setName("managedCommunity").checkBoolean(managedCommunity);
	}

	static public Map<String, List<MsgArgsPair>> validateWalled(Boolean walled)
	{
		return (new FieldValidator()).clearChecks().setName("walled").checkBoolean(walled);
	}

	static public Map<String, List<MsgArgsPair>> validateSecureGates(Boolean secureGates)
	{
		return (new FieldValidator()).clearChecks().setName("secureGates").checkBoolean(secureGates);
	}

	static public Map<String, List<MsgArgsPair>> validateMasjids(Short masjids)
	{
		return (new FieldValidator()).clearChecks().setName("masjids").setValidMinimum((double) 0).checkShort(masjids);
	}

	static public Map<String, List<MsgArgsPair>> validateRestaurants(Short restaurants)
	{
		return (new FieldValidator()).clearChecks().setName("restaurants").setValidMinimum((double) 0).checkShort(restaurants);
	}

	static public Map<String, List<MsgArgsPair>> validateCafes(Short cafes)
	{
		return (new FieldValidator()).clearChecks().setName("cafes").setValidMinimum((double) 0).checkShort(cafes);
	}

	static public Map<String, List<MsgArgsPair>> validateConvenienceStores(Short convenienceStores)
	{
		return (new FieldValidator()).clearChecks().setName("convenienceStores").setValidMinimum((double) 0).checkShort(convenienceStores);
	}

	static public Map<String, List<MsgArgsPair>> validateSupermarkets(Short supermarkets)
	{
		return (new FieldValidator()).clearChecks().setName("supermarkets").setValidMinimum((double) 0).checkShort(supermarkets);
	}

	static public Map<String, List<MsgArgsPair>> validateSelfServeLaundromats(Short selfServeLaundromats)
	{
		return (new FieldValidator()).clearChecks().setName("selfServeLaundromats").setValidMinimum((double) 0).checkShort(selfServeLaundromats);
	}

	static public Map<String, List<MsgArgsPair>> validateDryCleaners(Short dryCleaners)
	{
		return (new FieldValidator()).clearChecks().setName("dryCleaners").setValidMinimum((double) 0).checkShort(dryCleaners);
	}

	static public Map<String, List<MsgArgsPair>> validateOtherStores(Short otherStores)
	{
		return (new FieldValidator()).clearChecks().setName("otherStores").setValidMinimum((double) 0).checkShort(otherStores);
	}

	static public Map<String, List<MsgArgsPair>> validateMalePools(Short malePools)
	{
		return (new FieldValidator()).clearChecks().setName("malePools").setValidMinimum((double) 0).checkShort(malePools);
	}

	static public Map<String, List<MsgArgsPair>> validateFemalePools(Short femalePools)
	{
		return (new FieldValidator()).clearChecks().setName("femalePools").setValidMinimum((double) 0).checkShort(femalePools);
	}

	static public Map<String, List<MsgArgsPair>> validateMixedPools(Short mixedPools)
	{
		return (new FieldValidator()).clearChecks().setName("mixedPools").setValidMinimum((double) 0).checkShort(mixedPools);
	}

	static public Map<String, List<MsgArgsPair>> validateMaleGymnasiums(Short maleGymnasiums)
	{
		return (new FieldValidator()).clearChecks().setName("maleGymnasiums").setValidMinimum((double) 0).checkShort(maleGymnasiums);
	}

	static public Map<String, List<MsgArgsPair>> validateFemaleGymnasiums(Short femaleGymnasiums)
	{
		return (new FieldValidator()).clearChecks().setName("femaleGymnasiums").setValidMinimum((double) 0).checkShort(femaleGymnasiums);
	}

	static public Map<String, List<MsgArgsPair>> validateMixedGymnasiums(Short mixedGymnasiums)
	{
		return (new FieldValidator()).clearChecks().setName("mixedGymnasiums").setValidMinimum((double) 0).checkShort(mixedGymnasiums);
	}

	static public Map<String, List<MsgArgsPair>> validateSoccerFields(Short soccerFields)
	{
		return (new FieldValidator()).clearChecks().setName("soccerFields").setValidMinimum((double) 0).checkShort(soccerFields);
	}

	static public Map<String, List<MsgArgsPair>> validateOtherFields(Short otherFields)
	{
		return (new FieldValidator()).clearChecks().setName("otherFields").setValidMinimum((double) 0).checkShort(otherFields);
	}

	static public Map<String, List<MsgArgsPair>> validateBasketballCourts(Short basketballCourts)
	{
		return (new FieldValidator()).clearChecks().setName("basketballCourts").setValidMinimum((double) 0).checkShort(basketballCourts);
	}

	static public Map<String, List<MsgArgsPair>> validateTennisCourts(Short tennisCourts)
	{
		return (new FieldValidator()).clearChecks().setName("tennisCourts").setValidMinimum((double) 0).checkShort(tennisCourts);
	}

	static public Map<String, List<MsgArgsPair>> validateParks(Short parks)
	{
		return (new FieldValidator()).clearChecks().setName("parks").setValidMinimum((double) 0).checkShort(parks);
	}

	static public Map<String, List<MsgArgsPair>> validatePlaygrounds(Short playgrounds)
	{
		return (new FieldValidator()).clearChecks().setName("playgrounds").setValidMinimum((double) 0).checkShort(playgrounds);
	}

	static public Map<String, List<MsgArgsPair>> validateBusinessCenters(Short businessCenters)
	{
		return (new FieldValidator()).clearChecks().setName("businessCenters").setValidMinimum((double) 0).checkShort(businessCenters);
	}

	static public Map<String, List<MsgArgsPair>> validateInternetCenters(Short internetCenters)
	{
		return (new FieldValidator()).clearChecks().setName("internetCenters").setValidMinimum((double) 0).checkShort(internetCenters);
	}

	static public Map<String, List<MsgArgsPair>> validateSocialCenters(Short socialCenters)
	{
		return (new FieldValidator()).clearChecks().setName("socialCenters").setValidMinimum((double) 0).checkShort(socialCenters);
	}

	static public Map<String, List<MsgArgsPair>> validateOrganizedWomensActivities(Boolean organizedWomensActivities)
	{
		return (new FieldValidator()).clearChecks().setName("organizedWomensActivities").checkBoolean(organizedWomensActivities);
	}

	static public Map<String, List<MsgArgsPair>> validateOrganizedChildrensActivities(Boolean organizedChildrensActivities)
	{
		return (new FieldValidator()).clearChecks().setName("organizedChildrensActivities").checkBoolean(organizedChildrensActivities);
	}

	static public Map<String, List<MsgArgsPair>> validateHospitals(Short hospitals)
	{
		return (new FieldValidator()).clearChecks().setName("hospitals").setValidMinimum((double) 0).checkShort(hospitals);
	}

	static public Map<String, List<MsgArgsPair>> validateClinics(Short clinics)
	{
		return (new FieldValidator()).clearChecks().setName("clinics").setValidMinimum((double) 0).checkShort(clinics);
	}

	static public Map<String, List<MsgArgsPair>> validateDoctors(Short doctors)
	{
		return (new FieldValidator()).clearChecks().setName("doctors").setValidMinimum((double) 0).checkShort(doctors);
	}

	static public Map<String, List<MsgArgsPair>> validateDaycareCenters(Short daycareCenters)
	{
		return (new FieldValidator()).clearChecks().setName("daycareCenters").setValidMinimum((double) 0).checkShort(daycareCenters);
	}

	static public Map<String, List<MsgArgsPair>> validateElementarySchools(Short elementarySchools)
	{
		return (new FieldValidator()).clearChecks().setName("elementarySchools").setValidMinimum((double) 0).checkShort(elementarySchools);
	}

	static public Map<String, List<MsgArgsPair>> validateMiddleSchools(Short middleSchools)
	{
		return (new FieldValidator()).clearChecks().setName("middleSchools").setValidMinimum((double) 0).checkShort(middleSchools);
	}

	static public Map<String, List<MsgArgsPair>> validateHighSchools(Short highSchools)
	{
		return (new FieldValidator()).clearChecks().setName("highSchools").setValidMinimum((double) 0).checkShort(highSchools);
	}

	static public Map<String, List<MsgArgsPair>> validateSecurityCenters(Short securityCenters)
	{
		return (new FieldValidator()).clearChecks().setName("securityCenters").setValidMinimum((double) 0).checkShort(securityCenters);
	}

	static public Map<String, List<MsgArgsPair>> validateOther(String other)
	{
		return (new FieldValidator()).clearChecks().setName("other").setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(other);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("communityName") != null && searchFields.get("communityName").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"communityName\" LIKE $$%" + searchFields.get("communityName") + "%$$", true);
		if (searchFields.get("managedCommunity") != null && searchFields.get("managedCommunity").toString().length() > 0 &&
			searchFields.get("managedCommunity").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"managedCommunity\" = true", true);
		if (searchFields.get("walled") != null && searchFields.get("walled").toString().length() > 0 &&
			searchFields.get("walled").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"walled\" = true", true);
		if (searchFields.get("secureGates") != null && searchFields.get("secureGates").toString().length() > 0 &&
			searchFields.get("secureGates").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"secureGates\" = true", true);
		if (searchFields.get("masjids") != null && searchFields.get("masjids").toString().length() > 0 &&
			searchFields.get("masjids").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"masjids\" > 0", true);
		if (searchFields.get("restaurants") != null && searchFields.get("restaurants").toString().length() > 0 &&
			searchFields.get("restaurants").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"restaurants\" > 0", true);
		if (searchFields.get("cafes") != null && searchFields.get("cafes").toString().length() > 0 &&
			searchFields.get("cafes").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"cafes\" > 0", true);
		if (searchFields.get("convenienceStores") != null && searchFields.get("convenienceStores").toString().length() > 0 &&
			searchFields.get("convenienceStores").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"convenienceStores\" > 0", true);
		if (searchFields.get("supermarkets") != null && searchFields.get("supermarkets").toString().length() > 0 &&
			searchFields.get("supermarkets").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"supermarkets\" > 0", true);
		if (searchFields.get("selfServeLaundromats") != null && searchFields.get("selfServeLaundromats").toString().length() > 0 &&
			searchFields.get("selfServeLaundromats").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"selfServeLaundromats\" > 0", true);
		if (searchFields.get("dryCleaners") != null && searchFields.get("dryCleaners").toString().length() > 0 &&
			searchFields.get("dryCleaners").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"dryCleaners\" > 0", true);
		if (searchFields.get("malePools") != null && searchFields.get("malePools").toString().length() > 0 &&
			searchFields.get("malePools").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"malePools\" > 0", true);
		if (searchFields.get("femalePools") != null && searchFields.get("femalePools").toString().length() > 0 &&
			searchFields.get("femalePools").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"femalePools\" > 0", true);
		if (searchFields.get("maleGymnasiums") != null && searchFields.get("maleGymnasiums").toString().length() > 0 &&
			searchFields.get("maleGymnasiums").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"maleGymnasiums\" > 0", true);
		if (searchFields.get("femaleGymnasiums") != null && searchFields.get("femaleGymnasiums").toString().length() > 0 &&
			searchFields.get("femaleGymnasiums").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"femaleGymnasiums\" > 0", true);
		if (searchFields.get("soccerFields") != null && searchFields.get("soccerFields").toString().length() > 0 &&
			searchFields.get("soccerFields").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"soccerFields\" > 0", true);
		if (searchFields.get("otherFields") != null && searchFields.get("otherFields").toString().length() > 0 &&
			searchFields.get("otherFields").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"otherFields\" > 0", true);
		if (searchFields.get("basketballCourts") != null && searchFields.get("basketballCourts").toString().length() > 0 &&
			searchFields.get("basketballCourts").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"basketballCourts\" > 0", true);
		if (searchFields.get("tennisCourts") != null && searchFields.get("tennisCourts").toString().length() > 0 &&
			searchFields.get("tennisCourts").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"tennisCourts\" > 0", true);
		if (searchFields.get("parks") != null && searchFields.get("parks").toString().length() > 0 &&
			searchFields.get("parks").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"parks\" > 0", true);
		if (searchFields.get("playgrounds") != null && searchFields.get("playgrounds").toString().length() > 0 &&
			searchFields.get("playgrounds").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"playgrounds\" > 0", true);
		if (searchFields.get("businessCenters") != null && searchFields.get("businessCenters").toString().length() > 0 &&
			searchFields.get("businessCenters").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"businessCenters\" > 0", true);
		if (searchFields.get("internetCenters") != null && searchFields.get("internetCenters").toString().length() > 0 &&
			searchFields.get("internetCenters").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"internetCenters\" > 0", true);
		if (searchFields.get("socialCenters") != null && searchFields.get("socialCenters").toString().length() > 0 &&
			searchFields.get("socialCenters").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"socialCenters\" > 0", true);
		if (searchFields.get("organizedWomensActivities") != null && searchFields.get("organizedWomensActivities").toString().length() > 0 &&
			searchFields.get("organizedWomensActivities").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"organizedWomensActivities\" = true", true);
		if (searchFields.get("organizedChildrensActivities") != null && searchFields.get("organizedChildrensActivities").toString().length() > 0 &&
			searchFields.get("organizedChildrensActivities").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"organizedChildrensActivities\" = true", true);
		if (searchFields.get("hospitals") != null && searchFields.get("hospitals").toString().length() > 0 &&
			searchFields.get("hospitals").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"hospitals\" > 0", true);
		if (searchFields.get("clinics") != null && searchFields.get("clinics").toString().length() > 0 &&
			searchFields.get("clinics").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"clinics\" > 0", true);
		if (searchFields.get("doctors") != null && searchFields.get("doctors").toString().length() > 0 &&
			searchFields.get("doctors").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"doctors\" > 0", true);
		if (searchFields.get("daycareCenters") != null && searchFields.get("daycareCenters").toString().length() > 0 &&
			searchFields.get("daycareCenters").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"daycareCenters\" > 0", true);
		if (searchFields.get("elementarySchools") != null && searchFields.get("elementarySchools").toString().length() > 0 &&
			searchFields.get("elementarySchools").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"elementarySchools\" > 0", true);
		if (searchFields.get("middleSchools") != null && searchFields.get("middleSchools").toString().length() > 0 &&
			searchFields.get("middleSchools").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"middleSchools\" > 0", true);
		if (searchFields.get("highSchools") != null && searchFields.get("highSchools").toString().length() > 0 &&
			searchFields.get("highSchools").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"highSchools\" > 0", true);
		if (searchFields.get("securityCenters") != null && searchFields.get("securityCenters").toString().length() > 0 &&
			searchFields.get("securityCenters").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"securityCenters\" > 0", true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"createdByIdk\"") private Long createdByIdk = null;
	@Basic @Column(name="\"referenceHandle\"") private String referenceHandle = null;
	@Basic @Column(name="\"communityName\"") private String communityName = null;
	@Basic @Column(name="\"managedCommunity\"") private Boolean managedCommunity = null;
	@Basic @Column(name="\"walled\"") private Boolean walled = null;
	@Basic @Column(name="\"secureGates\"") private Boolean secureGates = null;
	@Basic @Column(name="\"masjids\"") private Short masjids = null;
	@Basic @Column(name="\"restaurants\"") private Short restaurants = null;
	@Basic @Column(name="\"cafes\"") private Short cafes = null;
	@Basic @Column(name="\"convenienceStores\"") private Short convenienceStores = null;
	@Basic @Column(name="\"supermarkets\"") private Short supermarkets = null;
	@Basic @Column(name="\"selfServeLaundromats\"") private Short selfServeLaundromats = null;
	@Basic @Column(name="\"dryCleaners\"") private Short dryCleaners = null;
	@Basic @Column(name="\"otherStores\"") private Short otherStores = null;
	@Basic @Column(name="\"malePools\"") private Short malePools = null;
	@Basic @Column(name="\"femalePools\"") private Short femalePools = null;
	@Basic @Column(name="\"mixedPools\"") private Short mixedPools = null;
	@Basic @Column(name="\"maleGymnasiums\"") private Short maleGymnasiums = null;
	@Basic @Column(name="\"femaleGymnasiums\"") private Short femaleGymnasiums = null;
	@Basic @Column(name="\"mixedGymnasiums\"") private Short mixedGymnasiums = null;
	@Basic @Column(name="\"soccerFields\"") private Short soccerFields = null;
	@Basic @Column(name="\"otherFields\"") private Short otherFields = null;
	@Basic @Column(name="\"basketballCourts\"") private Short basketballCourts = null;
	@Basic @Column(name="\"tennisCourts\"") private Short tennisCourts = null;
	@Basic @Column(name="\"parks\"") private Short parks = null;
	@Basic @Column(name="\"playgrounds\"") private Short playgrounds = null;
	@Basic @Column(name="\"businessCenters\"") private Short businessCenters = null;
	@Basic @Column(name="\"internetCenters\"") private Short internetCenters = null;
	@Basic @Column(name="\"socialCenters\"") private Short socialCenters = null;
	@Basic @Column(name="\"organizedWomensActivities\"") private Boolean organizedWomensActivities = null;
	@Basic @Column(name="\"organizedChildrensActivities\"") private Boolean organizedChildrensActivities = null;
	@Basic @Column(name="\"hospitals\"") private Short hospitals = null;
	@Basic @Column(name="\"clinics\"") private Short clinics = null;
	@Basic @Column(name="\"doctors\"") private Short doctors = null;
	@Basic @Column(name="\"daycareCenters\"") private Short daycareCenters = null;
	@Basic @Column(name="\"elementarySchools\"") private Short elementarySchools = null;
	@Basic @Column(name="\"middleSchools\"") private Short middleSchools = null;
	@Basic @Column(name="\"highSchools\"") private Short highSchools = null;
	@Basic @Column(name="\"securityCenters\"") private Short securityCenters = null;
	@Basic @Column(name="\"other\"") private String other = null;

	public CommunityAmenities() {}
	public CommunityAmenities(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Long getCreatedByIdk()
	{
		return createdByIdk;
	}
	public void setCreatedByIdk(Long inputParameter)
	{
		modify();
		createdByIdk = inputParameter;
	}

	public String getReferenceHandle()
	{
		return referenceHandle;
	}
	public void setReferenceHandle(String inputParameter)
	{
		modify();
		referenceHandle = inputParameter;
	}

	public String getCommunityName()
	{
		return communityName;
	}
	public void setCommunityName(String inputParameter)
	{
		modify();
		communityName = inputParameter;
	}

	public Boolean getManagedCommunity()
	{
		return managedCommunity;
	}
	public void setManagedCommunity(Boolean inputParameter)
	{
		modify();
		managedCommunity = inputParameter;
	}

	public Boolean getWalled()
	{
		return walled;
	}
	public void setWalled(Boolean inputParameter)
	{
		modify();
		walled = inputParameter;
	}

	public Boolean getSecureGates()
	{
		return secureGates;
	}
	public void setSecureGates(Boolean inputParameter)
	{
		modify();
		secureGates = inputParameter;
	}

	public Short getMasjids()
	{
		return masjids;
	}
	public void setMasjids(Short inputParameter)
	{
		modify();
		masjids = inputParameter;
	}

	public Short getRestaurants()
	{
		return restaurants;
	}
	public void setRestaurants(Short inputParameter)
	{
		modify();
		restaurants = inputParameter;
	}

	public Short getCafes()
	{
		return cafes;
	}
	public void setCafes(Short inputParameter)
	{
		modify();
		cafes = inputParameter;
	}

	public Short getConvenienceStores()
	{
		return convenienceStores;
	}
	public void setConvenienceStores(Short inputParameter)
	{
		modify();
		convenienceStores = inputParameter;
	}

	public Short getSupermarkets()
	{
		return supermarkets;
	}
	public void setSupermarkets(Short inputParameter)
	{
		modify();
		supermarkets = inputParameter;
	}

	public Short getSelfServeLaundromats()
	{
		return selfServeLaundromats;
	}
	public void setSelfServeLaundromats(Short inputParameter)
	{
		modify();
		selfServeLaundromats = inputParameter;
	}

	public Short getDryCleaners()
	{
		return dryCleaners;
	}
	public void setDryCleaners(Short inputParameter)
	{
		modify();
		dryCleaners = inputParameter;
	}

	public Short getOtherStores()
	{
		return otherStores;
	}
	public void setOtherStores(Short inputParameter)
	{
		modify();
		otherStores = inputParameter;
	}

	public Short getMalePools()
	{
		return malePools;
	}
	public void setMalePools(Short inputParameter)
	{
		modify();
		malePools = inputParameter;
	}

	public Short getFemalePools()
	{
		return femalePools;
	}
	public void setFemalePools(Short inputParameter)
	{
		modify();
		femalePools = inputParameter;
	}

	public Short getMixedPools()
	{
		return mixedPools;
	}
	public void setMixedPools(Short inputParameter)
	{
		modify();
		mixedPools = inputParameter;
	}

	public Short getMaleGymnasiums()
	{
		return maleGymnasiums;
	}
	public void setMaleGymnasiums(Short inputParameter)
	{
		modify();
		maleGymnasiums = inputParameter;
	}

	public Short getFemaleGymnasiums()
	{
		return femaleGymnasiums;
	}
	public void setFemaleGymnasiums(Short inputParameter)
	{
		modify();
		femaleGymnasiums = inputParameter;
	}

	public Short getMixedGymnasiums()
	{
		return mixedGymnasiums;
	}
	public void setMixedGymnasiums(Short inputParameter)
	{
		modify();
		mixedGymnasiums = inputParameter;
	}

	public Short getSoccerFields()
	{
		return soccerFields;
	}
	public void setSoccerFields(Short inputParameter)
	{
		modify();
		soccerFields = inputParameter;
	}

	public Short getOtherFields()
	{
		return otherFields;
	}
	public void setOtherFields(Short inputParameter)
	{
		modify();
		otherFields = inputParameter;
	}

	public Short getBasketballCourts()
	{
		return basketballCourts;
	}
	public void setBasketballCourts(Short inputParameter)
	{
		modify();
		basketballCourts = inputParameter;
	}

	public Short getTennisCourts()
	{
		return tennisCourts;
	}
	public void setTennisCourts(Short inputParameter)
	{
		modify();
		tennisCourts = inputParameter;
	}

	public Short getParks()
	{
		return parks;
	}
	public void setParks(Short inputParameter)
	{
		modify();
		parks = inputParameter;
	}

	public Short getPlaygrounds()
	{
		return playgrounds;
	}
	public void setPlaygrounds(Short inputParameter)
	{
		modify();
		playgrounds = inputParameter;
	}

	public Short getBusinessCenters()
	{
		return businessCenters;
	}
	public void setBusinessCenters(Short inputParameter)
	{
		modify();
		businessCenters = inputParameter;
	}

	public Short getInternetCenters()
	{
		return internetCenters;
	}
	public void setInternetCenters(Short inputParameter)
	{
		modify();
		internetCenters = inputParameter;
	}

	public Short getSocialCenters()
	{
		return socialCenters;
	}
	public void setSocialCenters(Short inputParameter)
	{
		modify();
		socialCenters = inputParameter;
	}

	public Boolean getOrganizedWomensActivities()
	{
		return organizedWomensActivities;
	}
	public void setOrganizedWomensActivities(Boolean inputParameter)
	{
		modify();
		organizedWomensActivities = inputParameter;
	}

	public Boolean getOrganizedChildrensActivities()
	{
		return organizedChildrensActivities;
	}
	public void setOrganizedChildrensActivities(Boolean inputParameter)
	{
		modify();
		organizedChildrensActivities = inputParameter;
	}

	public Short getHospitals()
	{
		return hospitals;
	}
	public void setHospitals(Short inputParameter)
	{
		modify();
		hospitals = inputParameter;
	}

	public Short getClinics()
	{
		return clinics;
	}
	public void setClinics(Short inputParameter)
	{
		modify();
		clinics = inputParameter;
	}

	public Short getDoctors()
	{
		return doctors;
	}
	public void setDoctors(Short inputParameter)
	{
		modify();
		doctors = inputParameter;
	}

	public Short getDaycareCenters()
	{
		return daycareCenters;
	}
	public void setDaycareCenters(Short inputParameter)
	{
		modify();
		daycareCenters = inputParameter;
	}

	public Short getElementarySchools()
	{
		return elementarySchools;
	}
	public void setElementarySchools(Short inputParameter)
	{
		modify();
		elementarySchools = inputParameter;
	}

	public Short getMiddleSchools()
	{
		return middleSchools;
	}
	public void setMiddleSchools(Short inputParameter)
	{
		modify();
		middleSchools = inputParameter;
	}

	public Short getHighSchools()
	{
		return highSchools;
	}
	public void setHighSchools(Short inputParameter)
	{
		modify();
		highSchools = inputParameter;
	}

	public Short getSecurityCenters()
	{
		return securityCenters;
	}
	public void setSecurityCenters(Short inputParameter)
	{
		modify();
		securityCenters = inputParameter;
	}

	public String getOther()
	{
		return other;
	}
	public void setOther(String inputParameter)
	{
		modify();
		other = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "createdByIdk", createdByIdk);
		collectField(format, dateFormat, fields, "referenceHandle", referenceHandle);
		collectField(format, dateFormat, fields, "communityName", communityName);
		collectField(format, dateFormat, fields, "managedCommunity", managedCommunity);
		collectField(format, dateFormat, fields, "walled", walled);
		collectField(format, dateFormat, fields, "secureGates", secureGates);
		collectField(format, dateFormat, fields, "masjids", masjids);
		collectField(format, dateFormat, fields, "restaurants", restaurants);
		collectField(format, dateFormat, fields, "cafes", cafes);
		collectField(format, dateFormat, fields, "convenienceStores", convenienceStores);
		collectField(format, dateFormat, fields, "supermarkets", supermarkets);
		collectField(format, dateFormat, fields, "selfServeLaundromats", selfServeLaundromats);
		collectField(format, dateFormat, fields, "dryCleaners", dryCleaners);
		collectField(format, dateFormat, fields, "otherStores", otherStores);
		collectField(format, dateFormat, fields, "malePools", malePools);
		collectField(format, dateFormat, fields, "femalePools", femalePools);
		collectField(format, dateFormat, fields, "mixedPools", mixedPools);
		collectField(format, dateFormat, fields, "maleGymnasiums", maleGymnasiums);
		collectField(format, dateFormat, fields, "femaleGymnasiums", femaleGymnasiums);
		collectField(format, dateFormat, fields, "mixedGymnasiums", mixedGymnasiums);
		collectField(format, dateFormat, fields, "soccerFields", soccerFields);
		collectField(format, dateFormat, fields, "otherFields", otherFields);
		collectField(format, dateFormat, fields, "basketballCourts", basketballCourts);
		collectField(format, dateFormat, fields, "tennisCourts", tennisCourts);
		collectField(format, dateFormat, fields, "parks", parks);
		collectField(format, dateFormat, fields, "playgrounds", playgrounds);
		collectField(format, dateFormat, fields, "businessCenters", businessCenters);
		collectField(format, dateFormat, fields, "internetCenters", internetCenters);
		collectField(format, dateFormat, fields, "socialCenters", socialCenters);
		collectField(format, dateFormat, fields, "organizedWomensActivities", organizedWomensActivities);
		collectField(format, dateFormat, fields, "organizedChildrensActivities", organizedChildrensActivities);
		collectField(format, dateFormat, fields, "hospitals", hospitals);
		collectField(format, dateFormat, fields, "clinics", clinics);
		collectField(format, dateFormat, fields, "doctors", doctors);
		collectField(format, dateFormat, fields, "daycareCenters", daycareCenters);
		collectField(format, dateFormat, fields, "elementarySchools", elementarySchools);
		collectField(format, dateFormat, fields, "middleSchools", middleSchools);
		collectField(format, dateFormat, fields, "highSchools", highSchools);
		collectField(format, dateFormat, fields, "securityCenters", securityCenters);
		collectField(format, dateFormat, fields, "other", other);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("createdByIdk"))
			setCreatedByIdk( (Long) fields.get("createdByIdk") );
		if (fields.containsKey("referenceHandle"))
			setReferenceHandle( (String) fields.get("referenceHandle") );
		if (fields.containsKey("communityName"))
			setCommunityName( (String) fields.get("communityName") );
		if (fields.containsKey("managedCommunity"))
			setManagedCommunity( (Boolean) fields.get("managedCommunity") );
		if (fields.containsKey("walled"))
			setWalled( (Boolean) fields.get("walled") );
		if (fields.containsKey("secureGates"))
			setSecureGates( (Boolean) fields.get("secureGates") );
		if (fields.containsKey("masjids"))
			setMasjids( ((Number) fields.get("masjids")).shortValue() );
		if (fields.containsKey("restaurants"))
			setRestaurants( ((Number) fields.get("restaurants")).shortValue() );
		if (fields.containsKey("cafes"))
			setCafes( ((Number) fields.get("cafes")).shortValue() );
		if (fields.containsKey("convenienceStores"))
			setConvenienceStores( ((Number) fields.get("convenienceStores")).shortValue() );
		if (fields.containsKey("supermarkets"))
			setSupermarkets( ((Number) fields.get("supermarkets")).shortValue() );
		if (fields.containsKey("selfServeLaundromats"))
			setSelfServeLaundromats( ((Number) fields.get("selfServeLaundromats")).shortValue() );
		if (fields.containsKey("dryCleaners"))
			setDryCleaners( ((Number) fields.get("dryCleaners")).shortValue() );
		if (fields.containsKey("otherStores"))
			setOtherStores( ((Number) fields.get("otherStores")).shortValue() );
		if (fields.containsKey("malePools"))
			setMalePools( ((Number) fields.get("malePools")).shortValue() );
		if (fields.containsKey("femalePools"))
			setFemalePools( ((Number) fields.get("femalePools")).shortValue() );
		if (fields.containsKey("mixedPools"))
			setMixedPools( ((Number) fields.get("mixedPools")).shortValue() );
		if (fields.containsKey("maleGymnasiums"))
			setMaleGymnasiums( ((Number) fields.get("maleGymnasiums")).shortValue() );
		if (fields.containsKey("femaleGymnasiums"))
			setFemaleGymnasiums( ((Number) fields.get("femaleGymnasiums")).shortValue() );
		if (fields.containsKey("mixedGymnasiums"))
			setMixedGymnasiums( ((Number) fields.get("mixedGymnasiums")).shortValue() );
		if (fields.containsKey("soccerFields"))
			setSoccerFields( ((Number) fields.get("soccerFields")).shortValue() );
		if (fields.containsKey("otherFields"))
			setOtherFields( ((Number) fields.get("otherFields")).shortValue() );
		if (fields.containsKey("basketballCourts"))
			setBasketballCourts( ((Number) fields.get("basketballCourts")).shortValue() );
		if (fields.containsKey("tennisCourts"))
			setTennisCourts( ((Number) fields.get("tennisCourts")).shortValue() );
		if (fields.containsKey("parks"))
			setParks( ((Number) fields.get("parks")).shortValue() );
		if (fields.containsKey("playgrounds"))
			setPlaygrounds( ((Number) fields.get("playgrounds")).shortValue() );
		if (fields.containsKey("businessCenters"))
			setBusinessCenters( ((Number) fields.get("businessCenters")).shortValue() );
		if (fields.containsKey("internetCenters"))
			setInternetCenters( ((Number) fields.get("internetCenters")).shortValue() );
		if (fields.containsKey("socialCenters"))
			setSocialCenters( ((Number) fields.get("socialCenters")).shortValue() );
		if (fields.containsKey("organizedWomensActivities"))
			setOrganizedWomensActivities( (Boolean) fields.get("organizedWomensActivities") );
		if (fields.containsKey("organizedChildrensActivities"))
			setOrganizedChildrensActivities( (Boolean) fields.get("organizedChildrensActivities") );
		if (fields.containsKey("hospitals"))
			setHospitals( ((Number) fields.get("hospitals")).shortValue() );
		if (fields.containsKey("clinics"))
			setClinics( ((Number) fields.get("clinics")).shortValue() );
		if (fields.containsKey("doctors"))
			setDoctors( ((Number) fields.get("doctors")).shortValue() );
		if (fields.containsKey("daycareCenters"))
			setDaycareCenters( ((Number) fields.get("daycareCenters")).shortValue() );
		if (fields.containsKey("elementarySchools"))
			setElementarySchools( ((Number) fields.get("elementarySchools")).shortValue() );
		if (fields.containsKey("middleSchools"))
			setMiddleSchools( ((Number) fields.get("middleSchools")).shortValue() );
		if (fields.containsKey("highSchools"))
			setHighSchools( ((Number) fields.get("highSchools")).shortValue() );
		if (fields.containsKey("securityCenters"))
			setSecurityCenters( ((Number) fields.get("securityCenters")).shortValue() );
		if (fields.containsKey("other"))
			setOther( (String) fields.get("other") );
		return;
	}

	public Map<String, List<MsgArgsPair>> validateExtraFields(List<String> specificFields, boolean exclude)
	{
		Map<String, List<MsgArgsPair>> result = null;
		LinkedHashMap<String, List<MsgArgsPair>> results = new LinkedHashMap<String, List<MsgArgsPair>>();
		FieldValidator validator = new FieldValidator();

		if (getValidating())
		{
			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("createdByIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("createdByIdk")))
			{
				result = 
					validator.clearChecks().setName("createdByIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(createdByIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("referenceHandle")) ||
				(specificFields != null && exclude && !specificFields.contains("referenceHandle")))
			{
				result = 
					validator.clearChecks().setName("referenceHandle").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(referenceHandle);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("communityName")) ||
				(specificFields != null && exclude && !specificFields.contains("communityName")))
			{
				result = 
					validator.clearChecks().setName("communityName").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(communityName);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("managedCommunity")) ||
				(specificFields != null && exclude && !specificFields.contains("managedCommunity")))
			{
				result = 
					validator.clearChecks().setName("managedCommunity").checkBoolean(managedCommunity);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("walled")) ||
				(specificFields != null && exclude && !specificFields.contains("walled")))
			{
				result = 
					validator.clearChecks().setName("walled").checkBoolean(walled);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("secureGates")) ||
				(specificFields != null && exclude && !specificFields.contains("secureGates")))
			{
				result = 
					validator.clearChecks().setName("secureGates").checkBoolean(secureGates);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("masjids")) ||
				(specificFields != null && exclude && !specificFields.contains("masjids")))
			{
				result = 
					validator.clearChecks().setName("masjids").setValidMinimum((double) 0).checkShort(masjids);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("restaurants")) ||
				(specificFields != null && exclude && !specificFields.contains("restaurants")))
			{
				result = 
					validator.clearChecks().setName("restaurants").setValidMinimum((double) 0).checkShort(restaurants);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("cafes")) ||
				(specificFields != null && exclude && !specificFields.contains("cafes")))
			{
				result = 
					validator.clearChecks().setName("cafes").setValidMinimum((double) 0).checkShort(cafes);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("convenienceStores")) ||
				(specificFields != null && exclude && !specificFields.contains("convenienceStores")))
			{
				result = 
					validator.clearChecks().setName("convenienceStores").setValidMinimum((double) 0).checkShort(convenienceStores);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("supermarkets")) ||
				(specificFields != null && exclude && !specificFields.contains("supermarkets")))
			{
				result = 
					validator.clearChecks().setName("supermarkets").setValidMinimum((double) 0).checkShort(supermarkets);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("selfServeLaundromats")) ||
				(specificFields != null && exclude && !specificFields.contains("selfServeLaundromats")))
			{
				result = 
					validator.clearChecks().setName("selfServeLaundromats").setValidMinimum((double) 0).checkShort(selfServeLaundromats);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("dryCleaners")) ||
				(specificFields != null && exclude && !specificFields.contains("dryCleaners")))
			{
				result = 
					validator.clearChecks().setName("dryCleaners").setValidMinimum((double) 0).checkShort(dryCleaners);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("otherStores")) ||
				(specificFields != null && exclude && !specificFields.contains("otherStores")))
			{
				result = 
					validator.clearChecks().setName("otherStores").setValidMinimum((double) 0).checkShort(otherStores);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("malePools")) ||
				(specificFields != null && exclude && !specificFields.contains("malePools")))
			{
				result = 
					validator.clearChecks().setName("malePools").setValidMinimum((double) 0).checkShort(malePools);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("femalePools")) ||
				(specificFields != null && exclude && !specificFields.contains("femalePools")))
			{
				result = 
					validator.clearChecks().setName("femalePools").setValidMinimum((double) 0).checkShort(femalePools);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("mixedPools")) ||
				(specificFields != null && exclude && !specificFields.contains("mixedPools")))
			{
				result = 
					validator.clearChecks().setName("mixedPools").setValidMinimum((double) 0).checkShort(mixedPools);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("maleGymnasiums")) ||
				(specificFields != null && exclude && !specificFields.contains("maleGymnasiums")))
			{
				result = 
					validator.clearChecks().setName("maleGymnasiums").setValidMinimum((double) 0).checkShort(maleGymnasiums);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("femaleGymnasiums")) ||
				(specificFields != null && exclude && !specificFields.contains("femaleGymnasiums")))
			{
				result = 
					validator.clearChecks().setName("femaleGymnasiums").setValidMinimum((double) 0).checkShort(femaleGymnasiums);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("mixedGymnasiums")) ||
				(specificFields != null && exclude && !specificFields.contains("mixedGymnasiums")))
			{
				result = 
					validator.clearChecks().setName("mixedGymnasiums").setValidMinimum((double) 0).checkShort(mixedGymnasiums);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("soccerFields")) ||
				(specificFields != null && exclude && !specificFields.contains("soccerFields")))
			{
				result = 
					validator.clearChecks().setName("soccerFields").setValidMinimum((double) 0).checkShort(soccerFields);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("otherFields")) ||
				(specificFields != null && exclude && !specificFields.contains("otherFields")))
			{
				result = 
					validator.clearChecks().setName("otherFields").setValidMinimum((double) 0).checkShort(otherFields);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("basketballCourts")) ||
				(specificFields != null && exclude && !specificFields.contains("basketballCourts")))
			{
				result = 
					validator.clearChecks().setName("basketballCourts").setValidMinimum((double) 0).checkShort(basketballCourts);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("tennisCourts")) ||
				(specificFields != null && exclude && !specificFields.contains("tennisCourts")))
			{
				result = 
					validator.clearChecks().setName("tennisCourts").setValidMinimum((double) 0).checkShort(tennisCourts);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("parks")) ||
				(specificFields != null && exclude && !specificFields.contains("parks")))
			{
				result = 
					validator.clearChecks().setName("parks").setValidMinimum((double) 0).checkShort(parks);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("playgrounds")) ||
				(specificFields != null && exclude && !specificFields.contains("playgrounds")))
			{
				result = 
					validator.clearChecks().setName("playgrounds").setValidMinimum((double) 0).checkShort(playgrounds);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("businessCenters")) ||
				(specificFields != null && exclude && !specificFields.contains("businessCenters")))
			{
				result = 
					validator.clearChecks().setName("businessCenters").setValidMinimum((double) 0).checkShort(businessCenters);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("internetCenters")) ||
				(specificFields != null && exclude && !specificFields.contains("internetCenters")))
			{
				result = 
					validator.clearChecks().setName("internetCenters").setValidMinimum((double) 0).checkShort(internetCenters);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("socialCenters")) ||
				(specificFields != null && exclude && !specificFields.contains("socialCenters")))
			{
				result = 
					validator.clearChecks().setName("socialCenters").setValidMinimum((double) 0).checkShort(socialCenters);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("organizedWomensActivities")) ||
				(specificFields != null && exclude && !specificFields.contains("organizedWomensActivities")))
			{
				result = 
					validator.clearChecks().setName("organizedWomensActivities").checkBoolean(organizedWomensActivities);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("organizedChildrensActivities")) ||
				(specificFields != null && exclude && !specificFields.contains("organizedChildrensActivities")))
			{
				result = 
					validator.clearChecks().setName("organizedChildrensActivities").checkBoolean(organizedChildrensActivities);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("hospitals")) ||
				(specificFields != null && exclude && !specificFields.contains("hospitals")))
			{
				result = 
					validator.clearChecks().setName("hospitals").setValidMinimum((double) 0).checkShort(hospitals);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("clinics")) ||
				(specificFields != null && exclude && !specificFields.contains("clinics")))
			{
				result = 
					validator.clearChecks().setName("clinics").setValidMinimum((double) 0).checkShort(clinics);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("doctors")) ||
				(specificFields != null && exclude && !specificFields.contains("doctors")))
			{
				result = 
					validator.clearChecks().setName("doctors").setValidMinimum((double) 0).checkShort(doctors);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("daycareCenters")) ||
				(specificFields != null && exclude && !specificFields.contains("daycareCenters")))
			{
				result = 
					validator.clearChecks().setName("daycareCenters").setValidMinimum((double) 0).checkShort(daycareCenters);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("elementarySchools")) ||
				(specificFields != null && exclude && !specificFields.contains("elementarySchools")))
			{
				result = 
					validator.clearChecks().setName("elementarySchools").setValidMinimum((double) 0).checkShort(elementarySchools);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("middleSchools")) ||
				(specificFields != null && exclude && !specificFields.contains("middleSchools")))
			{
				result = 
					validator.clearChecks().setName("middleSchools").setValidMinimum((double) 0).checkShort(middleSchools);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("highSchools")) ||
				(specificFields != null && exclude && !specificFields.contains("highSchools")))
			{
				result = 
					validator.clearChecks().setName("highSchools").setValidMinimum((double) 0).checkShort(highSchools);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("securityCenters")) ||
				(specificFields != null && exclude && !specificFields.contains("securityCenters")))
			{
				result = 
					validator.clearChecks().setName("securityCenters").setValidMinimum((double) 0).checkShort(securityCenters);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("other")) ||
				(specificFields != null && exclude && !specificFields.contains("other")))
			{
				result = 
					validator.clearChecks().setName("other").setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(other);
				if (result != null)
					results.putAll(result);
			}

		}

		if (results.isEmpty())
			return null;
		return results;
	}

	protected void onModify()
	{
	}

}

