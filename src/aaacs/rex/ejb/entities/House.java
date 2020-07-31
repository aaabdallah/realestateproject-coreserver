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
			name = "List<House>",
			entities =
			{
				@EntityResult
				(
					entityClass = House.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "propertyIdk", column = "propertyIdk"),
						@FieldResult(name = "landSize", column = "landSize"),
						@FieldResult(name = "landAreaUnitTypeIdk", column = "landAreaUnitTypeIdk"),
						@FieldResult(name = "landSizeApproximated", column = "landSizeApproximated"),
						@FieldResult(name = "houseSize", column = "houseSize"),
						@FieldResult(name = "houseAreaUnitTypeIdk", column = "houseAreaUnitTypeIdk"),
						@FieldResult(name = "houseSizeApproximated", column = "houseSizeApproximated"),
						@FieldResult(name = "houseStyleIdk", column = "houseStyleIdk"),
						@FieldResult(name = "foundationTypeIdk", column = "foundationTypeIdk"),
						@FieldResult(name = "exteriorTypeIdk", column = "exteriorTypeIdk"),
						@FieldResult(name = "exteriorPrimaryColorIdk", column = "exteriorPrimaryColorIdk"),
						@FieldResult(name = "exteriorSecondaryColorIdk", column = "exteriorSecondaryColorIdk"),
						@FieldResult(name = "interiorPrimaryColorIdk", column = "interiorPrimaryColorIdk"),
						@FieldResult(name = "interiorSecondaryColorIdk", column = "interiorSecondaryColorIdk"),
						@FieldResult(name = "floors", column = "floors"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "residentialFeaturesIdk", column = "residentialFeaturesIdk")
					}
				)
			}
		)
	}
)
@Entity(name="House") @Table(name="\"tHouses\"")
public class House extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tHouses\"";

	static public House findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (House) BaseEntity.findByIdk(manager, House.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findHouseByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<House>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findHouseByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<House>");
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

	static public Map<String, List<MsgArgsPair>> validateHouse(House house,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return house.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePropertyIdk(Long propertyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("propertyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateLandSize(Integer landSize)
	{
		return (new FieldValidator()).clearChecks().setName("landSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(landSize);
	}

	static public Map<String, List<MsgArgsPair>> validateLandAreaUnitTypeIdk(Long landAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("landAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(landAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateLandSizeApproximated(Boolean landSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("landSizeApproximated").setAllowNullElement(false).checkBoolean(landSizeApproximated);
	}

	static public Map<String, List<MsgArgsPair>> validateHouseSize(Integer houseSize)
	{
		return (new FieldValidator()).clearChecks().setName("houseSize").setValidMinimum((double) 0).checkInteger(houseSize);
	}

	static public Map<String, List<MsgArgsPair>> validateHouseAreaUnitTypeIdk(Long houseAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("houseAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(houseAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateHouseSizeApproximated(Boolean houseSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("houseSizeApproximated").checkBoolean(houseSizeApproximated);
	}

	static public Map<String, List<MsgArgsPair>> validateHouseStyleIdk(Long houseStyleIdk)
	{
		return (new FieldValidator()).clearChecks().setName("houseStyleIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(houseStyleIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateFoundationTypeIdk(Long foundationTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("foundationTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(foundationTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateExteriorTypeIdk(Long exteriorTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("exteriorTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(exteriorTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateExteriorPrimaryColorIdk(Long exteriorPrimaryColorIdk)
	{
		return (new FieldValidator()).clearChecks().setName("exteriorPrimaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(exteriorPrimaryColorIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateExteriorSecondaryColorIdk(Long exteriorSecondaryColorIdk)
	{
		return (new FieldValidator()).clearChecks().setName("exteriorSecondaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(exteriorSecondaryColorIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateInteriorPrimaryColorIdk(Long interiorPrimaryColorIdk)
	{
		return (new FieldValidator()).clearChecks().setName("interiorPrimaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(interiorPrimaryColorIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateInteriorSecondaryColorIdk(Long interiorSecondaryColorIdk)
	{
		return (new FieldValidator()).clearChecks().setName("interiorSecondaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(interiorSecondaryColorIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateFloors(Short floors)
	{
		return (new FieldValidator()).clearChecks().setName("floors").setValidMinimum((double) 0).checkShort(floors);
	}

	static public Map<String, List<MsgArgsPair>> validateDescription(String description)
	{
		return (new FieldValidator()).clearChecks().setName("description").setValidMinimum((double) 0).setValidMaximum((double) 2000).checkString(description);
	}

	static public Map<String, List<MsgArgsPair>> validateResidentialFeaturesIdk(Long residentialFeaturesIdk)
	{
		return (new FieldValidator()).clearChecks().setName("residentialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(residentialFeaturesIdk);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("landSizeMinimum") != null && searchFields.get("landSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"landSize\" >= " + searchFields.get("landSizeMinimum"), true);
		if (searchFields.get("landSizeMaximum") != null && searchFields.get("landSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"landSize\" <= " + searchFields.get("landSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check landAreaUnitTypeIdk by itself; only use it with landSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("houseSizeMinimum") != null && searchFields.get("houseSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"houseSize\" >= " + searchFields.get("houseSizeMinimum"), true);
		if (searchFields.get("houseSizeMaximum") != null && searchFields.get("houseSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"houseSize\" <= " + searchFields.get("houseSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check houseAreaUnitTypeIdk by itself; only use it with houseSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("houseStyleIdk") != null && ((Long[]) searchFields.get("houseStyleIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"houseStyleIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("houseStyleIdk"), "", "") + ")", true);
		if (searchFields.get("floorsMinimum") != null && searchFields.get("floorsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"floors\" >= " + searchFields.get("floorsMinimum"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"landSize\"") private Integer landSize = null;
	@Basic @Column(name="\"landAreaUnitTypeIdk\"") private Long landAreaUnitTypeIdk = null;
	@Basic @Column(name="\"landSizeApproximated\"") private Boolean landSizeApproximated = null;
	@Basic @Column(name="\"houseSize\"") private Integer houseSize = null;
	@Basic @Column(name="\"houseAreaUnitTypeIdk\"") private Long houseAreaUnitTypeIdk = null;
	@Basic @Column(name="\"houseSizeApproximated\"") private Boolean houseSizeApproximated = null;
	@Basic @Column(name="\"houseStyleIdk\"") private Long houseStyleIdk = null;
	@Basic @Column(name="\"foundationTypeIdk\"") private Long foundationTypeIdk = null;
	@Basic @Column(name="\"exteriorTypeIdk\"") private Long exteriorTypeIdk = null;
	@Basic @Column(name="\"exteriorPrimaryColorIdk\"") private Long exteriorPrimaryColorIdk = null;
	@Basic @Column(name="\"exteriorSecondaryColorIdk\"") private Long exteriorSecondaryColorIdk = null;
	@Basic @Column(name="\"interiorPrimaryColorIdk\"") private Long interiorPrimaryColorIdk = null;
	@Basic @Column(name="\"interiorSecondaryColorIdk\"") private Long interiorSecondaryColorIdk = null;
	@Basic @Column(name="\"floors\"") private Short floors = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"residentialFeaturesIdk\"") private Long residentialFeaturesIdk = null;

	public House() {}
	public House(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Long getPropertyIdk()
	{
		return propertyIdk;
	}
	public void setPropertyIdk(Long inputParameter)
	{
		modify();
		propertyIdk = inputParameter;
	}

	public Integer getLandSize()
	{
		return landSize;
	}
	public void setLandSize(Integer inputParameter)
	{
		modify();
		landSize = inputParameter;
	}

	public Long getLandAreaUnitTypeIdk()
	{
		return landAreaUnitTypeIdk;
	}
	public void setLandAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		landAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getLandSizeApproximated()
	{
		return landSizeApproximated;
	}
	public void setLandSizeApproximated(Boolean inputParameter)
	{
		modify();
		landSizeApproximated = inputParameter;
	}

	public Integer getHouseSize()
	{
		return houseSize;
	}
	public void setHouseSize(Integer inputParameter)
	{
		modify();
		houseSize = inputParameter;
	}

	public Long getHouseAreaUnitTypeIdk()
	{
		return houseAreaUnitTypeIdk;
	}
	public void setHouseAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		houseAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getHouseSizeApproximated()
	{
		return houseSizeApproximated;
	}
	public void setHouseSizeApproximated(Boolean inputParameter)
	{
		modify();
		houseSizeApproximated = inputParameter;
	}

	public Long getHouseStyleIdk()
	{
		return houseStyleIdk;
	}
	public void setHouseStyleIdk(Long inputParameter)
	{
		modify();
		houseStyleIdk = inputParameter;
	}

	public Long getFoundationTypeIdk()
	{
		return foundationTypeIdk;
	}
	public void setFoundationTypeIdk(Long inputParameter)
	{
		modify();
		foundationTypeIdk = inputParameter;
	}

	public Long getExteriorTypeIdk()
	{
		return exteriorTypeIdk;
	}
	public void setExteriorTypeIdk(Long inputParameter)
	{
		modify();
		exteriorTypeIdk = inputParameter;
	}

	public Long getExteriorPrimaryColorIdk()
	{
		return exteriorPrimaryColorIdk;
	}
	public void setExteriorPrimaryColorIdk(Long inputParameter)
	{
		modify();
		exteriorPrimaryColorIdk = inputParameter;
	}

	public Long getExteriorSecondaryColorIdk()
	{
		return exteriorSecondaryColorIdk;
	}
	public void setExteriorSecondaryColorIdk(Long inputParameter)
	{
		modify();
		exteriorSecondaryColorIdk = inputParameter;
	}

	public Long getInteriorPrimaryColorIdk()
	{
		return interiorPrimaryColorIdk;
	}
	public void setInteriorPrimaryColorIdk(Long inputParameter)
	{
		modify();
		interiorPrimaryColorIdk = inputParameter;
	}

	public Long getInteriorSecondaryColorIdk()
	{
		return interiorSecondaryColorIdk;
	}
	public void setInteriorSecondaryColorIdk(Long inputParameter)
	{
		modify();
		interiorSecondaryColorIdk = inputParameter;
	}

	public Short getFloors()
	{
		return floors;
	}
	public void setFloors(Short inputParameter)
	{
		modify();
		floors = inputParameter;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String inputParameter)
	{
		modify();
		description = inputParameter;
	}

	public Long getResidentialFeaturesIdk()
	{
		return residentialFeaturesIdk;
	}
	public void setResidentialFeaturesIdk(Long inputParameter)
	{
		modify();
		residentialFeaturesIdk = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("landAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(landAreaUnitTypeIdk).get("name"));
		fields.put("houseAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(houseAreaUnitTypeIdk).get("name"));
		fields.put("houseStyleIdkMsg",
			(String) PropertyOption.cacheLookup(houseStyleIdk).get("name"));
		fields.put("foundationTypeIdkMsg",
			(String) PropertyOption.cacheLookup(foundationTypeIdk).get("name"));
		fields.put("exteriorTypeIdkMsg",
			(String) PropertyOption.cacheLookup(exteriorTypeIdk).get("name"));
		fields.put("exteriorPrimaryColorIdkMsg",
			(String) PropertyOption.cacheLookup(exteriorPrimaryColorIdk).get("name"));
		fields.put("exteriorSecondaryColorIdkMsg",
			(String) PropertyOption.cacheLookup(exteriorSecondaryColorIdk).get("name"));
		fields.put("interiorPrimaryColorIdkMsg",
			(String) PropertyOption.cacheLookup(interiorPrimaryColorIdk).get("name"));
		fields.put("interiorSecondaryColorIdkMsg",
			(String) PropertyOption.cacheLookup(interiorSecondaryColorIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "propertyIdk", propertyIdk);
		collectField(format, dateFormat, fields, "landSize", landSize);
		collectField(format, dateFormat, fields, "landAreaUnitTypeIdk", landAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "landSizeApproximated", landSizeApproximated);
		collectField(format, dateFormat, fields, "houseSize", houseSize);
		collectField(format, dateFormat, fields, "houseAreaUnitTypeIdk", houseAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "houseSizeApproximated", houseSizeApproximated);
		collectField(format, dateFormat, fields, "houseStyleIdk", houseStyleIdk);
		collectField(format, dateFormat, fields, "foundationTypeIdk", foundationTypeIdk);
		collectField(format, dateFormat, fields, "exteriorTypeIdk", exteriorTypeIdk);
		collectField(format, dateFormat, fields, "exteriorPrimaryColorIdk", exteriorPrimaryColorIdk);
		collectField(format, dateFormat, fields, "exteriorSecondaryColorIdk", exteriorSecondaryColorIdk);
		collectField(format, dateFormat, fields, "interiorPrimaryColorIdk", interiorPrimaryColorIdk);
		collectField(format, dateFormat, fields, "interiorSecondaryColorIdk", interiorSecondaryColorIdk);
		collectField(format, dateFormat, fields, "floors", floors);
		collectField(format, dateFormat, fields, "description", description);
		collectField(format, dateFormat, fields, "residentialFeaturesIdk", residentialFeaturesIdk);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("propertyIdk"))
			setPropertyIdk( (Long) fields.get("propertyIdk") );
		if (fields.containsKey("landSize"))
			setLandSize( (Integer) fields.get("landSize") );
		if (fields.containsKey("landAreaUnitTypeIdk"))
			setLandAreaUnitTypeIdk( (Long) fields.get("landAreaUnitTypeIdk") );
		if (fields.containsKey("landSizeApproximated"))
			setLandSizeApproximated( (Boolean) fields.get("landSizeApproximated") );
		if (fields.containsKey("houseSize"))
			setHouseSize( (Integer) fields.get("houseSize") );
		if (fields.containsKey("houseAreaUnitTypeIdk"))
			setHouseAreaUnitTypeIdk( (Long) fields.get("houseAreaUnitTypeIdk") );
		if (fields.containsKey("houseSizeApproximated"))
			setHouseSizeApproximated( (Boolean) fields.get("houseSizeApproximated") );
		if (fields.containsKey("houseStyleIdk"))
			setHouseStyleIdk( (Long) fields.get("houseStyleIdk") );
		if (fields.containsKey("foundationTypeIdk"))
			setFoundationTypeIdk( (Long) fields.get("foundationTypeIdk") );
		if (fields.containsKey("exteriorTypeIdk"))
			setExteriorTypeIdk( (Long) fields.get("exteriorTypeIdk") );
		if (fields.containsKey("exteriorPrimaryColorIdk"))
			setExteriorPrimaryColorIdk( (Long) fields.get("exteriorPrimaryColorIdk") );
		if (fields.containsKey("exteriorSecondaryColorIdk"))
			setExteriorSecondaryColorIdk( (Long) fields.get("exteriorSecondaryColorIdk") );
		if (fields.containsKey("interiorPrimaryColorIdk"))
			setInteriorPrimaryColorIdk( (Long) fields.get("interiorPrimaryColorIdk") );
		if (fields.containsKey("interiorSecondaryColorIdk"))
			setInteriorSecondaryColorIdk( (Long) fields.get("interiorSecondaryColorIdk") );
		if (fields.containsKey("floors"))
			setFloors( ((Number) fields.get("floors")).shortValue() );
		if (fields.containsKey("description"))
			setDescription( (String) fields.get("description") );
		if (fields.containsKey("residentialFeaturesIdk"))
			setResidentialFeaturesIdk( (Long) fields.get("residentialFeaturesIdk") );
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
				(specificFields != null && !exclude && specificFields.contains("propertyIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("propertyIdk")))
			{
				result = 
					validator.clearChecks().setName("propertyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("landSize")) ||
				(specificFields != null && exclude && !specificFields.contains("landSize")))
			{
				result = 
					validator.clearChecks().setName("landSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(landSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("landAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("landAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("landAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(landAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("landSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("landSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("landSizeApproximated").setAllowNullElement(false).checkBoolean(landSizeApproximated);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("houseSize")) ||
				(specificFields != null && exclude && !specificFields.contains("houseSize")))
			{
				result = 
					validator.clearChecks().setName("houseSize").setValidMinimum((double) 0).checkInteger(houseSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("houseAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("houseAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("houseAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(houseAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("houseSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("houseSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("houseSizeApproximated").checkBoolean(houseSizeApproximated);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("houseStyleIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("houseStyleIdk")))
			{
				result = 
					validator.clearChecks().setName("houseStyleIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(houseStyleIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("foundationTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("foundationTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("foundationTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(foundationTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("exteriorTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("exteriorTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("exteriorTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(exteriorTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("exteriorPrimaryColorIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("exteriorPrimaryColorIdk")))
			{
				result = 
					validator.clearChecks().setName("exteriorPrimaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(exteriorPrimaryColorIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("exteriorSecondaryColorIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("exteriorSecondaryColorIdk")))
			{
				result = 
					validator.clearChecks().setName("exteriorSecondaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(exteriorSecondaryColorIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("interiorPrimaryColorIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("interiorPrimaryColorIdk")))
			{
				result = 
					validator.clearChecks().setName("interiorPrimaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(interiorPrimaryColorIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("interiorSecondaryColorIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("interiorSecondaryColorIdk")))
			{
				result = 
					validator.clearChecks().setName("interiorSecondaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(interiorSecondaryColorIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("floors")) ||
				(specificFields != null && exclude && !specificFields.contains("floors")))
			{
				result = 
					validator.clearChecks().setName("floors").setValidMinimum((double) 0).checkShort(floors);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("description")) ||
				(specificFields != null && exclude && !specificFields.contains("description")))
			{
				result = 
					validator.clearChecks().setName("description").setValidMinimum((double) 0).setValidMaximum((double) 2000).checkString(description);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("residentialFeaturesIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("residentialFeaturesIdk")))
			{
				result = 
					validator.clearChecks().setName("residentialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(residentialFeaturesIdk);
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

