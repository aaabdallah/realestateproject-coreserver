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
			name = "List<Property>",
			entities =
			{
				@EntityResult
				(
					entityClass = Property.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "propertyTypeIdk", column = "propertyTypeIdk"),
						@FieldResult(name = "constructionTypeIdk", column = "constructionTypeIdk"),
						@FieldResult(name = "zoningTypeIdk", column = "zoningTypeIdk"),
						@FieldResult(name = "streetAddress1", column = "streetAddress1"),
						@FieldResult(name = "streetAddress2", column = "streetAddress2"),
						@FieldResult(name = "districtIdk", column = "districtIdk"),
						@FieldResult(name = "cityIdk", column = "cityIdk"),
						@FieldResult(name = "regionIdk", column = "regionIdk"),
						@FieldResult(name = "countryIdk", column = "countryIdk"),
						@FieldResult(name = "postalCode", column = "postalCode"),
						@FieldResult(name = "gridN1", column = "gridN1"),
						@FieldResult(name = "gridN2", column = "gridN2"),
						@FieldResult(name = "gridN3", column = "gridN3"),
						@FieldResult(name = "gridN4", column = "gridN4"),
						@FieldResult(name = "gridN5", column = "gridN5"),
						@FieldResult(name = "gridN6", column = "gridN6"),
						@FieldResult(name = "gridN7", column = "gridN7"),
						@FieldResult(name = "gridN8", column = "gridN8"),
						@FieldResult(name = "gridW1", column = "gridW1"),
						@FieldResult(name = "gridW2", column = "gridW2"),
						@FieldResult(name = "gridW3", column = "gridW3"),
						@FieldResult(name = "gridW4", column = "gridW4"),
						@FieldResult(name = "gridW5", column = "gridW5"),
						@FieldResult(name = "gridW6", column = "gridW6"),
						@FieldResult(name = "gridW7", column = "gridW7"),
						@FieldResult(name = "gridW8", column = "gridW8"),
						@FieldResult(name = "directions", column = "directions"),
						@FieldResult(name = "nearestIntersection", column = "nearestIntersection"),
						@FieldResult(name = "directionOfFrontIdk", column = "directionOfFrontIdk"),
						@FieldResult(name = "directlyNorth", column = "directlyNorth"),
						@FieldResult(name = "directlySouth", column = "directlySouth"),
						@FieldResult(name = "directlyEast", column = "directlyEast"),
						@FieldResult(name = "directlyWest", column = "directlyWest"),
						@FieldResult(name = "yearBuilt", column = "yearBuilt"),
						@FieldResult(name = "occupied", column = "occupied"),
						@FieldResult(name = "generalConditionIdk", column = "generalConditionIdk"),
						@FieldResult(name = "photosLocation", column = "photosLocation"),
						@FieldResult(name = "walkingDistanceAmenitiesIdk", column = "walkingDistanceAmenitiesIdk"),
						@FieldResult(name = "communityAmenitiesIdk", column = "communityAmenitiesIdk")
					}
				)
			}
		)
	}
)
@Entity(name="Property") @Table(name="\"tProperties\"")
public class Property extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tProperties\"";

	static public Property findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Property) BaseEntity.findByIdk(manager, Property.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findPropertyByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Property>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findPropertyByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Property>");
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

	static public Map<String, List<MsgArgsPair>> validateProperty(Property property,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return property.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePropertyTypeIdk(Long propertyTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("propertyTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateConstructionTypeIdk(Long constructionTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("constructionTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(constructionTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateZoningTypeIdk(Long zoningTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("zoningTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(zoningTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateStreetAddress1(String streetAddress1)
	{
		return (new FieldValidator()).clearChecks().setName("streetAddress1").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress1);
	}

	static public Map<String, List<MsgArgsPair>> validateStreetAddress2(String streetAddress2)
	{
		return (new FieldValidator()).clearChecks().setName("streetAddress2").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress2);
	}

	static public Map<String, List<MsgArgsPair>> validateDistrictIdk(Long districtIdk)
	{
		return (new FieldValidator()).clearChecks().setName("districtIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(districtIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCityIdk(Long cityIdk)
	{
		return (new FieldValidator()).clearChecks().setName("cityIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(cityIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateRegionIdk(Long regionIdk)
	{
		return (new FieldValidator()).clearChecks().setName("regionIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(regionIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCountryIdk(Long countryIdk)
	{
		return (new FieldValidator()).clearChecks().setName("countryIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(countryIdk);
	}

	static public Map<String, List<MsgArgsPair>> validatePostalCode(String postalCode)
	{
		return (new FieldValidator()).clearChecks().setName("postalCode").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(postalCode);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN1(Short gridN1)
	{
		return (new FieldValidator()).clearChecks().setName("gridN1").checkShort(gridN1);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN2(Short gridN2)
	{
		return (new FieldValidator()).clearChecks().setName("gridN2").checkShort(gridN2);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN3(Short gridN3)
	{
		return (new FieldValidator()).clearChecks().setName("gridN3").checkShort(gridN3);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN4(Short gridN4)
	{
		return (new FieldValidator()).clearChecks().setName("gridN4").checkShort(gridN4);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN5(Short gridN5)
	{
		return (new FieldValidator()).clearChecks().setName("gridN5").checkShort(gridN5);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN6(Short gridN6)
	{
		return (new FieldValidator()).clearChecks().setName("gridN6").checkShort(gridN6);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN7(Short gridN7)
	{
		return (new FieldValidator()).clearChecks().setName("gridN7").checkShort(gridN7);
	}

	static public Map<String, List<MsgArgsPair>> validateGridN8(Short gridN8)
	{
		return (new FieldValidator()).clearChecks().setName("gridN8").checkShort(gridN8);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW1(String gridW1)
	{
		return (new FieldValidator()).clearChecks().setName("gridW1").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW1);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW2(String gridW2)
	{
		return (new FieldValidator()).clearChecks().setName("gridW2").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW2);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW3(String gridW3)
	{
		return (new FieldValidator()).clearChecks().setName("gridW3").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW3);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW4(String gridW4)
	{
		return (new FieldValidator()).clearChecks().setName("gridW4").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW4);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW5(String gridW5)
	{
		return (new FieldValidator()).clearChecks().setName("gridW5").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW5);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW6(String gridW6)
	{
		return (new FieldValidator()).clearChecks().setName("gridW6").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW6);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW7(String gridW7)
	{
		return (new FieldValidator()).clearChecks().setName("gridW7").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW7);
	}

	static public Map<String, List<MsgArgsPair>> validateGridW8(String gridW8)
	{
		return (new FieldValidator()).clearChecks().setName("gridW8").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW8);
	}

	static public Map<String, List<MsgArgsPair>> validateDirections(String directions)
	{
		return (new FieldValidator()).clearChecks().setName("directions").setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(directions);
	}

	static public Map<String, List<MsgArgsPair>> validateNearestIntersection(String nearestIntersection)
	{
		return (new FieldValidator()).clearChecks().setName("nearestIntersection").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(nearestIntersection);
	}

	static public Map<String, List<MsgArgsPair>> validateDirectionOfFrontIdk(Long directionOfFrontIdk)
	{
		return (new FieldValidator()).clearChecks().setName("directionOfFrontIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(directionOfFrontIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateDirectlyNorth(String directlyNorth)
	{
		return (new FieldValidator()).clearChecks().setName("directlyNorth").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlyNorth);
	}

	static public Map<String, List<MsgArgsPair>> validateDirectlySouth(String directlySouth)
	{
		return (new FieldValidator()).clearChecks().setName("directlySouth").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlySouth);
	}

	static public Map<String, List<MsgArgsPair>> validateDirectlyEast(String directlyEast)
	{
		return (new FieldValidator()).clearChecks().setName("directlyEast").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlyEast);
	}

	static public Map<String, List<MsgArgsPair>> validateDirectlyWest(String directlyWest)
	{
		return (new FieldValidator()).clearChecks().setName("directlyWest").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlyWest);
	}

	static public Map<String, List<MsgArgsPair>> validateYearBuilt(Short yearBuilt)
	{
		return (new FieldValidator()).clearChecks().setName("yearBuilt").setValidMinimum((double) 1900).checkShort(yearBuilt);
	}

	static public Map<String, List<MsgArgsPair>> validateOccupied(Boolean occupied)
	{
		return (new FieldValidator()).clearChecks().setName("occupied").checkBoolean(occupied);
	}

	static public Map<String, List<MsgArgsPair>> validateGeneralConditionIdk(Long generalConditionIdk)
	{
		return (new FieldValidator()).clearChecks().setName("generalConditionIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(generalConditionIdk);
	}

	static public Map<String, List<MsgArgsPair>> validatePhotosLocation(String photosLocation)
	{
		return (new FieldValidator()).clearChecks().setName("photosLocation").setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(photosLocation);
	}

	static public Map<String, List<MsgArgsPair>> validateWalkingDistanceAmenitiesIdk(Long walkingDistanceAmenitiesIdk)
	{
		return (new FieldValidator()).clearChecks().setName("walkingDistanceAmenitiesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(walkingDistanceAmenitiesIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCommunityAmenitiesIdk(Long communityAmenitiesIdk)
	{
		return (new FieldValidator()).clearChecks().setName("communityAmenitiesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(communityAmenitiesIdk);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("propertyTypeIdk") != null && searchFields.get("propertyTypeIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"propertyTypeIdk\" = " + searchFields.get("propertyTypeIdk"), true);
		if (searchFields.get("zoningTypeIdk") != null && ((Long[]) searchFields.get("zoningTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"zoningTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("zoningTypeIdk"), "", "") + ")", true);
		if (searchFields.get("districtIdk") != null && ((Long[]) searchFields.get("districtIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"districtIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("districtIdk"), "", "") + ")", true);
		if (searchFields.get("cityIdk") != null && ((Long[]) searchFields.get("cityIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"cityIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("cityIdk"), "", "") + ")", true);
		if (searchFields.get("regionIdk") != null && ((Long[]) searchFields.get("regionIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"regionIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("regionIdk"), "", "") + ")", true);
		if (searchFields.get("countryIdk") != null && ((Long[]) searchFields.get("countryIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"countryIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("countryIdk"), "", "") + ")", true);
		if (searchFields.get("gridN1") != null && searchFields.get("gridN1").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN1\" = " + searchFields.get("gridN1"), true);
		if (searchFields.get("gridN2") != null && searchFields.get("gridN2").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN2\" = " + searchFields.get("gridN2"), true);
		if (searchFields.get("gridN3") != null && searchFields.get("gridN3").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN3\" = " + searchFields.get("gridN3"), true);
		if (searchFields.get("gridN4") != null && searchFields.get("gridN4").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN4\" = " + searchFields.get("gridN4"), true);
		if (searchFields.get("gridN5") != null && searchFields.get("gridN5").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN5\" = " + searchFields.get("gridN5"), true);
		if (searchFields.get("gridN6") != null && searchFields.get("gridN6").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN6\" = " + searchFields.get("gridN6"), true);
		if (searchFields.get("gridN7") != null && searchFields.get("gridN7").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN7\" = " + searchFields.get("gridN7"), true);
		if (searchFields.get("gridN8") != null && searchFields.get("gridN8").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridN8\" = " + searchFields.get("gridN8"), true);
		if (searchFields.get("gridW1") != null && searchFields.get("gridW1").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW1\" LIKE $$%" + searchFields.get("gridW1") + "%$$", true);
		if (searchFields.get("gridW2") != null && searchFields.get("gridW2").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW2\" LIKE $$%" + searchFields.get("gridW2") + "%$$", true);
		if (searchFields.get("gridW3") != null && searchFields.get("gridW3").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW3\" LIKE $$%" + searchFields.get("gridW3") + "%$$", true);
		if (searchFields.get("gridW4") != null && searchFields.get("gridW4").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW4\" LIKE $$%" + searchFields.get("gridW4") + "%$$", true);
		if (searchFields.get("gridW5") != null && searchFields.get("gridW5").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW5\" LIKE $$%" + searchFields.get("gridW5") + "%$$", true);
		if (searchFields.get("gridW6") != null && searchFields.get("gridW6").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW6\" LIKE $$%" + searchFields.get("gridW6") + "%$$", true);
		if (searchFields.get("gridW7") != null && searchFields.get("gridW7").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW7\" LIKE $$%" + searchFields.get("gridW7") + "%$$", true);
		if (searchFields.get("gridW8") != null && searchFields.get("gridW8").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"gridW8\" LIKE $$%" + searchFields.get("gridW8") + "%$$", true);
		if (searchFields.get("yearBuiltMinimum") != null && searchFields.get("yearBuiltMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"yearBuilt\" >= " + searchFields.get("yearBuiltMinimum"), true);
		if (searchFields.get("generalConditionIdk") != null && ((Long[]) searchFields.get("generalConditionIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"generalConditionIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("generalConditionIdk"), "", "") + ")", true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyTypeIdk\"") private Long propertyTypeIdk = null;
	@Basic @Column(name="\"constructionTypeIdk\"") private Long constructionTypeIdk = null;
	@Basic @Column(name="\"zoningTypeIdk\"") private Long zoningTypeIdk = null;
	@Basic @Column(name="\"streetAddress1\"") private String streetAddress1 = null;
	@Basic @Column(name="\"streetAddress2\"") private String streetAddress2 = null;
	@Basic @Column(name="\"districtIdk\"") private Long districtIdk = null;
	@Basic @Column(name="\"cityIdk\"") private Long cityIdk = null;
	@Basic @Column(name="\"regionIdk\"") private Long regionIdk = null;
	@Basic @Column(name="\"countryIdk\"") private Long countryIdk = null;
	@Basic @Column(name="\"postalCode\"") private String postalCode = null;
	@Basic @Column(name="\"gridN1\"") private Short gridN1 = null;
	@Basic @Column(name="\"gridN2\"") private Short gridN2 = null;
	@Basic @Column(name="\"gridN3\"") private Short gridN3 = null;
	@Basic @Column(name="\"gridN4\"") private Short gridN4 = null;
	@Basic @Column(name="\"gridN5\"") private Short gridN5 = null;
	@Basic @Column(name="\"gridN6\"") private Short gridN6 = null;
	@Basic @Column(name="\"gridN7\"") private Short gridN7 = null;
	@Basic @Column(name="\"gridN8\"") private Short gridN8 = null;
	@Basic @Column(name="\"gridW1\"") private String gridW1 = null;
	@Basic @Column(name="\"gridW2\"") private String gridW2 = null;
	@Basic @Column(name="\"gridW3\"") private String gridW3 = null;
	@Basic @Column(name="\"gridW4\"") private String gridW4 = null;
	@Basic @Column(name="\"gridW5\"") private String gridW5 = null;
	@Basic @Column(name="\"gridW6\"") private String gridW6 = null;
	@Basic @Column(name="\"gridW7\"") private String gridW7 = null;
	@Basic @Column(name="\"gridW8\"") private String gridW8 = null;
	@Basic @Column(name="\"directions\"") private String directions = null;
	@Basic @Column(name="\"nearestIntersection\"") private String nearestIntersection = null;
	@Basic @Column(name="\"directionOfFrontIdk\"") private Long directionOfFrontIdk = null;
	@Basic @Column(name="\"directlyNorth\"") private String directlyNorth = null;
	@Basic @Column(name="\"directlySouth\"") private String directlySouth = null;
	@Basic @Column(name="\"directlyEast\"") private String directlyEast = null;
	@Basic @Column(name="\"directlyWest\"") private String directlyWest = null;
	@Basic @Column(name="\"yearBuilt\"") private Short yearBuilt = null;
	@Basic @Column(name="\"occupied\"") private Boolean occupied = null;
	@Basic @Column(name="\"generalConditionIdk\"") private Long generalConditionIdk = null;
	@Basic @Column(name="\"photosLocation\"") private String photosLocation = null;
	@Basic @Column(name="\"walkingDistanceAmenitiesIdk\"") private Long walkingDistanceAmenitiesIdk = null;
	@Basic @Column(name="\"communityAmenitiesIdk\"") private Long communityAmenitiesIdk = null;

	public Property() {}
	public Property(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Long getPropertyTypeIdk()
	{
		return propertyTypeIdk;
	}
	public void setPropertyTypeIdk(Long inputParameter)
	{
		modify();
		propertyTypeIdk = inputParameter;
	}

	public Long getConstructionTypeIdk()
	{
		return constructionTypeIdk;
	}
	public void setConstructionTypeIdk(Long inputParameter)
	{
		modify();
		constructionTypeIdk = inputParameter;
	}

	public Long getZoningTypeIdk()
	{
		return zoningTypeIdk;
	}
	public void setZoningTypeIdk(Long inputParameter)
	{
		modify();
		zoningTypeIdk = inputParameter;
	}

	public String getStreetAddress1()
	{
		return streetAddress1;
	}
	public void setStreetAddress1(String inputParameter)
	{
		modify();
		streetAddress1 = inputParameter;
	}

	public String getStreetAddress2()
	{
		return streetAddress2;
	}
	public void setStreetAddress2(String inputParameter)
	{
		modify();
		streetAddress2 = inputParameter;
	}

	public Long getDistrictIdk()
	{
		return districtIdk;
	}
	public void setDistrictIdk(Long inputParameter)
	{
		modify();
		districtIdk = inputParameter;
	}

	public Long getCityIdk()
	{
		return cityIdk;
	}
	public void setCityIdk(Long inputParameter)
	{
		modify();
		cityIdk = inputParameter;
	}

	public Long getRegionIdk()
	{
		return regionIdk;
	}
	public void setRegionIdk(Long inputParameter)
	{
		modify();
		regionIdk = inputParameter;
	}

	public Long getCountryIdk()
	{
		return countryIdk;
	}
	public void setCountryIdk(Long inputParameter)
	{
		modify();
		countryIdk = inputParameter;
	}

	public String getPostalCode()
	{
		return postalCode;
	}
	public void setPostalCode(String inputParameter)
	{
		modify();
		postalCode = inputParameter;
	}

	public Short getGridN1()
	{
		return gridN1;
	}
	public void setGridN1(Short inputParameter)
	{
		modify();
		gridN1 = inputParameter;
	}

	public Short getGridN2()
	{
		return gridN2;
	}
	public void setGridN2(Short inputParameter)
	{
		modify();
		gridN2 = inputParameter;
	}

	public Short getGridN3()
	{
		return gridN3;
	}
	public void setGridN3(Short inputParameter)
	{
		modify();
		gridN3 = inputParameter;
	}

	public Short getGridN4()
	{
		return gridN4;
	}
	public void setGridN4(Short inputParameter)
	{
		modify();
		gridN4 = inputParameter;
	}

	public Short getGridN5()
	{
		return gridN5;
	}
	public void setGridN5(Short inputParameter)
	{
		modify();
		gridN5 = inputParameter;
	}

	public Short getGridN6()
	{
		return gridN6;
	}
	public void setGridN6(Short inputParameter)
	{
		modify();
		gridN6 = inputParameter;
	}

	public Short getGridN7()
	{
		return gridN7;
	}
	public void setGridN7(Short inputParameter)
	{
		modify();
		gridN7 = inputParameter;
	}

	public Short getGridN8()
	{
		return gridN8;
	}
	public void setGridN8(Short inputParameter)
	{
		modify();
		gridN8 = inputParameter;
	}

	public String getGridW1()
	{
		return gridW1;
	}
	public void setGridW1(String inputParameter)
	{
		modify();
		gridW1 = inputParameter;
	}

	public String getGridW2()
	{
		return gridW2;
	}
	public void setGridW2(String inputParameter)
	{
		modify();
		gridW2 = inputParameter;
	}

	public String getGridW3()
	{
		return gridW3;
	}
	public void setGridW3(String inputParameter)
	{
		modify();
		gridW3 = inputParameter;
	}

	public String getGridW4()
	{
		return gridW4;
	}
	public void setGridW4(String inputParameter)
	{
		modify();
		gridW4 = inputParameter;
	}

	public String getGridW5()
	{
		return gridW5;
	}
	public void setGridW5(String inputParameter)
	{
		modify();
		gridW5 = inputParameter;
	}

	public String getGridW6()
	{
		return gridW6;
	}
	public void setGridW6(String inputParameter)
	{
		modify();
		gridW6 = inputParameter;
	}

	public String getGridW7()
	{
		return gridW7;
	}
	public void setGridW7(String inputParameter)
	{
		modify();
		gridW7 = inputParameter;
	}

	public String getGridW8()
	{
		return gridW8;
	}
	public void setGridW8(String inputParameter)
	{
		modify();
		gridW8 = inputParameter;
	}

	public String getDirections()
	{
		return directions;
	}
	public void setDirections(String inputParameter)
	{
		modify();
		directions = inputParameter;
	}

	public String getNearestIntersection()
	{
		return nearestIntersection;
	}
	public void setNearestIntersection(String inputParameter)
	{
		modify();
		nearestIntersection = inputParameter;
	}

	public Long getDirectionOfFrontIdk()
	{
		return directionOfFrontIdk;
	}
	public void setDirectionOfFrontIdk(Long inputParameter)
	{
		modify();
		directionOfFrontIdk = inputParameter;
	}

	public String getDirectlyNorth()
	{
		return directlyNorth;
	}
	public void setDirectlyNorth(String inputParameter)
	{
		modify();
		directlyNorth = inputParameter;
	}

	public String getDirectlySouth()
	{
		return directlySouth;
	}
	public void setDirectlySouth(String inputParameter)
	{
		modify();
		directlySouth = inputParameter;
	}

	public String getDirectlyEast()
	{
		return directlyEast;
	}
	public void setDirectlyEast(String inputParameter)
	{
		modify();
		directlyEast = inputParameter;
	}

	public String getDirectlyWest()
	{
		return directlyWest;
	}
	public void setDirectlyWest(String inputParameter)
	{
		modify();
		directlyWest = inputParameter;
	}

	public Short getYearBuilt()
	{
		return yearBuilt;
	}
	public void setYearBuilt(Short inputParameter)
	{
		modify();
		yearBuilt = inputParameter;
	}

	public Boolean getOccupied()
	{
		return occupied;
	}
	public void setOccupied(Boolean inputParameter)
	{
		modify();
		occupied = inputParameter;
	}

	public Long getGeneralConditionIdk()
	{
		return generalConditionIdk;
	}
	public void setGeneralConditionIdk(Long inputParameter)
	{
		modify();
		generalConditionIdk = inputParameter;
	}

	public String getPhotosLocation()
	{
		return photosLocation;
	}
	public void setPhotosLocation(String inputParameter)
	{
		modify();
		photosLocation = inputParameter;
	}

	public Long getWalkingDistanceAmenitiesIdk()
	{
		return walkingDistanceAmenitiesIdk;
	}
	public void setWalkingDistanceAmenitiesIdk(Long inputParameter)
	{
		modify();
		walkingDistanceAmenitiesIdk = inputParameter;
	}

	public Long getCommunityAmenitiesIdk()
	{
		return communityAmenitiesIdk;
	}
	public void setCommunityAmenitiesIdk(Long inputParameter)
	{
		modify();
		communityAmenitiesIdk = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("propertyTypeIdkMsg",
			(String) PropertyOption.cacheLookup(propertyTypeIdk).get("name"));
		fields.put("constructionTypeIdkMsg",
			(String) PropertyOption.cacheLookup(constructionTypeIdk).get("name"));
		fields.put("zoningTypeIdkMsg",
			(String) PropertyOption.cacheLookup(zoningTypeIdk).get("name"));
		fields.put("districtIdkMsg",
			(String) District.cacheLookup(districtIdk).get("name"));
		fields.put("cityIdkMsg",
			(String) City.cacheLookup(cityIdk).get("name"));
		fields.put("regionIdkMsg",
			(String) Region.cacheLookup(regionIdk).get("name"));
		fields.put("countryIdkMsg",
			(String) Country.cacheLookup(countryIdk).get("name"));
		fields.put("directionOfFrontIdkMsg",
			(String) PropertyOption.cacheLookup(directionOfFrontIdk).get("name"));
		fields.put("generalConditionIdkMsg",
			(String) PropertyOption.cacheLookup(generalConditionIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "propertyTypeIdk", propertyTypeIdk);
		collectField(format, dateFormat, fields, "constructionTypeIdk", constructionTypeIdk);
		collectField(format, dateFormat, fields, "zoningTypeIdk", zoningTypeIdk);
		collectField(format, dateFormat, fields, "streetAddress1", streetAddress1);
		collectField(format, dateFormat, fields, "streetAddress2", streetAddress2);
		collectField(format, dateFormat, fields, "districtIdk", districtIdk);
		collectField(format, dateFormat, fields, "cityIdk", cityIdk);
		collectField(format, dateFormat, fields, "regionIdk", regionIdk);
		collectField(format, dateFormat, fields, "countryIdk", countryIdk);
		collectField(format, dateFormat, fields, "postalCode", postalCode);
		collectField(format, dateFormat, fields, "gridN1", gridN1);
		collectField(format, dateFormat, fields, "gridN2", gridN2);
		collectField(format, dateFormat, fields, "gridN3", gridN3);
		collectField(format, dateFormat, fields, "gridN4", gridN4);
		collectField(format, dateFormat, fields, "gridN5", gridN5);
		collectField(format, dateFormat, fields, "gridN6", gridN6);
		collectField(format, dateFormat, fields, "gridN7", gridN7);
		collectField(format, dateFormat, fields, "gridN8", gridN8);
		collectField(format, dateFormat, fields, "gridW1", gridW1);
		collectField(format, dateFormat, fields, "gridW2", gridW2);
		collectField(format, dateFormat, fields, "gridW3", gridW3);
		collectField(format, dateFormat, fields, "gridW4", gridW4);
		collectField(format, dateFormat, fields, "gridW5", gridW5);
		collectField(format, dateFormat, fields, "gridW6", gridW6);
		collectField(format, dateFormat, fields, "gridW7", gridW7);
		collectField(format, dateFormat, fields, "gridW8", gridW8);
		collectField(format, dateFormat, fields, "directions", directions);
		collectField(format, dateFormat, fields, "nearestIntersection", nearestIntersection);
		collectField(format, dateFormat, fields, "directionOfFrontIdk", directionOfFrontIdk);
		collectField(format, dateFormat, fields, "directlyNorth", directlyNorth);
		collectField(format, dateFormat, fields, "directlySouth", directlySouth);
		collectField(format, dateFormat, fields, "directlyEast", directlyEast);
		collectField(format, dateFormat, fields, "directlyWest", directlyWest);
		collectField(format, dateFormat, fields, "yearBuilt", yearBuilt);
		collectField(format, dateFormat, fields, "occupied", occupied);
		collectField(format, dateFormat, fields, "generalConditionIdk", generalConditionIdk);
		collectField(format, dateFormat, fields, "photosLocation", photosLocation);
		collectField(format, dateFormat, fields, "walkingDistanceAmenitiesIdk", walkingDistanceAmenitiesIdk);
		collectField(format, dateFormat, fields, "communityAmenitiesIdk", communityAmenitiesIdk);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("propertyTypeIdk"))
			setPropertyTypeIdk( (Long) fields.get("propertyTypeIdk") );
		if (fields.containsKey("constructionTypeIdk"))
			setConstructionTypeIdk( (Long) fields.get("constructionTypeIdk") );
		if (fields.containsKey("zoningTypeIdk"))
			setZoningTypeIdk( (Long) fields.get("zoningTypeIdk") );
		if (fields.containsKey("streetAddress1"))
			setStreetAddress1( (String) fields.get("streetAddress1") );
		if (fields.containsKey("streetAddress2"))
			setStreetAddress2( (String) fields.get("streetAddress2") );
		if (fields.containsKey("districtIdk"))
			setDistrictIdk( (Long) fields.get("districtIdk") );
		if (fields.containsKey("cityIdk"))
			setCityIdk( (Long) fields.get("cityIdk") );
		if (fields.containsKey("regionIdk"))
			setRegionIdk( (Long) fields.get("regionIdk") );
		if (fields.containsKey("countryIdk"))
			setCountryIdk( (Long) fields.get("countryIdk") );
		if (fields.containsKey("postalCode"))
			setPostalCode( (String) fields.get("postalCode") );
		if (fields.containsKey("gridN1"))
			setGridN1( ((Number) fields.get("gridN1")).shortValue() );
		if (fields.containsKey("gridN2"))
			setGridN2( ((Number) fields.get("gridN2")).shortValue() );
		if (fields.containsKey("gridN3"))
			setGridN3( ((Number) fields.get("gridN3")).shortValue() );
		if (fields.containsKey("gridN4"))
			setGridN4( ((Number) fields.get("gridN4")).shortValue() );
		if (fields.containsKey("gridN5"))
			setGridN5( ((Number) fields.get("gridN5")).shortValue() );
		if (fields.containsKey("gridN6"))
			setGridN6( ((Number) fields.get("gridN6")).shortValue() );
		if (fields.containsKey("gridN7"))
			setGridN7( ((Number) fields.get("gridN7")).shortValue() );
		if (fields.containsKey("gridN8"))
			setGridN8( ((Number) fields.get("gridN8")).shortValue() );
		if (fields.containsKey("gridW1"))
			setGridW1( (String) fields.get("gridW1") );
		if (fields.containsKey("gridW2"))
			setGridW2( (String) fields.get("gridW2") );
		if (fields.containsKey("gridW3"))
			setGridW3( (String) fields.get("gridW3") );
		if (fields.containsKey("gridW4"))
			setGridW4( (String) fields.get("gridW4") );
		if (fields.containsKey("gridW5"))
			setGridW5( (String) fields.get("gridW5") );
		if (fields.containsKey("gridW6"))
			setGridW6( (String) fields.get("gridW6") );
		if (fields.containsKey("gridW7"))
			setGridW7( (String) fields.get("gridW7") );
		if (fields.containsKey("gridW8"))
			setGridW8( (String) fields.get("gridW8") );
		if (fields.containsKey("directions"))
			setDirections( (String) fields.get("directions") );
		if (fields.containsKey("nearestIntersection"))
			setNearestIntersection( (String) fields.get("nearestIntersection") );
		if (fields.containsKey("directionOfFrontIdk"))
			setDirectionOfFrontIdk( (Long) fields.get("directionOfFrontIdk") );
		if (fields.containsKey("directlyNorth"))
			setDirectlyNorth( (String) fields.get("directlyNorth") );
		if (fields.containsKey("directlySouth"))
			setDirectlySouth( (String) fields.get("directlySouth") );
		if (fields.containsKey("directlyEast"))
			setDirectlyEast( (String) fields.get("directlyEast") );
		if (fields.containsKey("directlyWest"))
			setDirectlyWest( (String) fields.get("directlyWest") );
		if (fields.containsKey("yearBuilt"))
			setYearBuilt( ((Number) fields.get("yearBuilt")).shortValue() );
		if (fields.containsKey("occupied"))
			setOccupied( (Boolean) fields.get("occupied") );
		if (fields.containsKey("generalConditionIdk"))
			setGeneralConditionIdk( (Long) fields.get("generalConditionIdk") );
		if (fields.containsKey("photosLocation"))
			setPhotosLocation( (String) fields.get("photosLocation") );
		if (fields.containsKey("walkingDistanceAmenitiesIdk"))
			setWalkingDistanceAmenitiesIdk( (Long) fields.get("walkingDistanceAmenitiesIdk") );
		if (fields.containsKey("communityAmenitiesIdk"))
			setCommunityAmenitiesIdk( (Long) fields.get("communityAmenitiesIdk") );
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
				(specificFields != null && !exclude && specificFields.contains("propertyTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("propertyTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("propertyTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("constructionTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("constructionTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("constructionTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(constructionTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("zoningTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("zoningTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("zoningTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(zoningTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("streetAddress1")) ||
				(specificFields != null && exclude && !specificFields.contains("streetAddress1")))
			{
				result = 
					validator.clearChecks().setName("streetAddress1").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress1);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("streetAddress2")) ||
				(specificFields != null && exclude && !specificFields.contains("streetAddress2")))
			{
				result = 
					validator.clearChecks().setName("streetAddress2").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress2);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("districtIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("districtIdk")))
			{
				result = 
					validator.clearChecks().setName("districtIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(districtIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("cityIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("cityIdk")))
			{
				result = 
					validator.clearChecks().setName("cityIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(cityIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("regionIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("regionIdk")))
			{
				result = 
					validator.clearChecks().setName("regionIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(regionIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("countryIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("countryIdk")))
			{
				result = 
					validator.clearChecks().setName("countryIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(countryIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("postalCode")) ||
				(specificFields != null && exclude && !specificFields.contains("postalCode")))
			{
				result = 
					validator.clearChecks().setName("postalCode").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(postalCode);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN1")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN1")))
			{
				result = 
					validator.clearChecks().setName("gridN1").checkShort(gridN1);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN2")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN2")))
			{
				result = 
					validator.clearChecks().setName("gridN2").checkShort(gridN2);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN3")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN3")))
			{
				result = 
					validator.clearChecks().setName("gridN3").checkShort(gridN3);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN4")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN4")))
			{
				result = 
					validator.clearChecks().setName("gridN4").checkShort(gridN4);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN5")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN5")))
			{
				result = 
					validator.clearChecks().setName("gridN5").checkShort(gridN5);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN6")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN6")))
			{
				result = 
					validator.clearChecks().setName("gridN6").checkShort(gridN6);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN7")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN7")))
			{
				result = 
					validator.clearChecks().setName("gridN7").checkShort(gridN7);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridN8")) ||
				(specificFields != null && exclude && !specificFields.contains("gridN8")))
			{
				result = 
					validator.clearChecks().setName("gridN8").checkShort(gridN8);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW1")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW1")))
			{
				result = 
					validator.clearChecks().setName("gridW1").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW1);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW2")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW2")))
			{
				result = 
					validator.clearChecks().setName("gridW2").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW2);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW3")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW3")))
			{
				result = 
					validator.clearChecks().setName("gridW3").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW3);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW4")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW4")))
			{
				result = 
					validator.clearChecks().setName("gridW4").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW4);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW5")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW5")))
			{
				result = 
					validator.clearChecks().setName("gridW5").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW5);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW6")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW6")))
			{
				result = 
					validator.clearChecks().setName("gridW6").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW6);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW7")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW7")))
			{
				result = 
					validator.clearChecks().setName("gridW7").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW7);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("gridW8")) ||
				(specificFields != null && exclude && !specificFields.contains("gridW8")))
			{
				result = 
					validator.clearChecks().setName("gridW8").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(gridW8);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("directions")) ||
				(specificFields != null && exclude && !specificFields.contains("directions")))
			{
				result = 
					validator.clearChecks().setName("directions").setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(directions);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("nearestIntersection")) ||
				(specificFields != null && exclude && !specificFields.contains("nearestIntersection")))
			{
				result = 
					validator.clearChecks().setName("nearestIntersection").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(nearestIntersection);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("directionOfFrontIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("directionOfFrontIdk")))
			{
				result = 
					validator.clearChecks().setName("directionOfFrontIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(directionOfFrontIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("directlyNorth")) ||
				(specificFields != null && exclude && !specificFields.contains("directlyNorth")))
			{
				result = 
					validator.clearChecks().setName("directlyNorth").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlyNorth);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("directlySouth")) ||
				(specificFields != null && exclude && !specificFields.contains("directlySouth")))
			{
				result = 
					validator.clearChecks().setName("directlySouth").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlySouth);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("directlyEast")) ||
				(specificFields != null && exclude && !specificFields.contains("directlyEast")))
			{
				result = 
					validator.clearChecks().setName("directlyEast").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlyEast);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("directlyWest")) ||
				(specificFields != null && exclude && !specificFields.contains("directlyWest")))
			{
				result = 
					validator.clearChecks().setName("directlyWest").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(directlyWest);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("yearBuilt")) ||
				(specificFields != null && exclude && !specificFields.contains("yearBuilt")))
			{
				result = 
					validator.clearChecks().setName("yearBuilt").setValidMinimum((double) 1900).checkShort(yearBuilt);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("occupied")) ||
				(specificFields != null && exclude && !specificFields.contains("occupied")))
			{
				result = 
					validator.clearChecks().setName("occupied").checkBoolean(occupied);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("generalConditionIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("generalConditionIdk")))
			{
				result = 
					validator.clearChecks().setName("generalConditionIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(generalConditionIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("photosLocation")) ||
				(specificFields != null && exclude && !specificFields.contains("photosLocation")))
			{
				result = 
					validator.clearChecks().setName("photosLocation").setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(photosLocation);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("walkingDistanceAmenitiesIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("walkingDistanceAmenitiesIdk")))
			{
				result = 
					validator.clearChecks().setName("walkingDistanceAmenitiesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(walkingDistanceAmenitiesIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("communityAmenitiesIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("communityAmenitiesIdk")))
			{
				result = 
					validator.clearChecks().setName("communityAmenitiesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(communityAmenitiesIdk);
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

