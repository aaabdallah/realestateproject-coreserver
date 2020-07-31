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
			name = "List<FloorUnit>",
			entities =
			{
				@EntityResult
				(
					entityClass = FloorUnit.class,
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
						@FieldResult(name = "floorUnitSize", column = "floorUnitSize"),
						@FieldResult(name = "floorUnitAreaUnitTypeIdk", column = "floorUnitAreaUnitTypeIdk"),
						@FieldResult(name = "floorUnitSizeApproximated", column = "floorUnitSizeApproximated"),
						@FieldResult(name = "foundationTypeIdk", column = "foundationTypeIdk"),
						@FieldResult(name = "exteriorTypeIdk", column = "exteriorTypeIdk"),
						@FieldResult(name = "exteriorPrimaryColorIdk", column = "exteriorPrimaryColorIdk"),
						@FieldResult(name = "exteriorSecondaryColorIdk", column = "exteriorSecondaryColorIdk"),
						@FieldResult(name = "interiorPrimaryColorIdk", column = "interiorPrimaryColorIdk"),
						@FieldResult(name = "interiorSecondaryColorIdk", column = "interiorSecondaryColorIdk"),
						@FieldResult(name = "unitFloor", column = "unitFloor"),
						@FieldResult(name = "totalFloorsInBuilding", column = "totalFloorsInBuilding"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "residentialFeaturesIdk", column = "residentialFeaturesIdk")
					}
				)
			}
		)
	}
)
@Entity(name="FloorUnit") @Table(name="\"tFloorUnits\"")
public class FloorUnit extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tFloorUnits\"";

	static public FloorUnit findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (FloorUnit) BaseEntity.findByIdk(manager, FloorUnit.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findFloorUnitByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<FloorUnit>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findFloorUnitByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<FloorUnit>");
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

	static public Map<String, List<MsgArgsPair>> validateFloorUnit(FloorUnit floorUnit,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return floorUnit.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePropertyIdk(Long propertyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("propertyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateFloorUnitSize(Integer floorUnitSize)
	{
		return (new FieldValidator()).clearChecks().setName("floorUnitSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(floorUnitSize);
	}

	static public Map<String, List<MsgArgsPair>> validateFloorUnitAreaUnitTypeIdk(Long floorUnitAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("floorUnitAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(floorUnitAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateFloorUnitSizeApproximated(Boolean floorUnitSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("floorUnitSizeApproximated").setAllowNullElement(false).checkBoolean(floorUnitSizeApproximated);
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

	static public Map<String, List<MsgArgsPair>> validateUnitFloor(Short unitFloor)
	{
		return (new FieldValidator()).clearChecks().setName("unitFloor").setValidMinimum((double) 0).checkShort(unitFloor);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalFloorsInBuilding(Short totalFloorsInBuilding)
	{
		return (new FieldValidator()).clearChecks().setName("totalFloorsInBuilding").setValidMinimum((double) 0).checkShort(totalFloorsInBuilding);
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

		if (searchFields.get("floorUnitSizeMinimum") != null && searchFields.get("floorUnitSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"floorUnitSize\" >= " + searchFields.get("floorUnitSizeMinimum"), true);
		if (searchFields.get("floorUnitSizeMaximum") != null && searchFields.get("floorUnitSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"floorUnitSize\" <= " + searchFields.get("floorUnitSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check floorUnitAreaUnitTypeIdk by itself; only use it with floorUnitSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("unitFloorMinimum") != null && searchFields.get("unitFloorMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"unitFloor\" >= " + searchFields.get("unitFloorMinimum"), true);
		if (searchFields.get("unitFloorMaximum") != null && searchFields.get("unitFloorMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"unitFloor\" <= " + searchFields.get("unitFloorMaximum"), true);
		if (searchFields.get("totalFloorsInBuildingMaximum") != null && searchFields.get("totalFloorsInBuildingMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalFloorsInBuilding\" <= " + searchFields.get("totalFloorsInBuildingMaximum"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"floorUnitSize\"") private Integer floorUnitSize = null;
	@Basic @Column(name="\"floorUnitAreaUnitTypeIdk\"") private Long floorUnitAreaUnitTypeIdk = null;
	@Basic @Column(name="\"floorUnitSizeApproximated\"") private Boolean floorUnitSizeApproximated = null;
	@Basic @Column(name="\"foundationTypeIdk\"") private Long foundationTypeIdk = null;
	@Basic @Column(name="\"exteriorTypeIdk\"") private Long exteriorTypeIdk = null;
	@Basic @Column(name="\"exteriorPrimaryColorIdk\"") private Long exteriorPrimaryColorIdk = null;
	@Basic @Column(name="\"exteriorSecondaryColorIdk\"") private Long exteriorSecondaryColorIdk = null;
	@Basic @Column(name="\"interiorPrimaryColorIdk\"") private Long interiorPrimaryColorIdk = null;
	@Basic @Column(name="\"interiorSecondaryColorIdk\"") private Long interiorSecondaryColorIdk = null;
	@Basic @Column(name="\"unitFloor\"") private Short unitFloor = null;
	@Basic @Column(name="\"totalFloorsInBuilding\"") private Short totalFloorsInBuilding = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"residentialFeaturesIdk\"") private Long residentialFeaturesIdk = null;

	public FloorUnit() {}
	public FloorUnit(Map<String, Object> fields)
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

	public Integer getFloorUnitSize()
	{
		return floorUnitSize;
	}
	public void setFloorUnitSize(Integer inputParameter)
	{
		modify();
		floorUnitSize = inputParameter;
	}

	public Long getFloorUnitAreaUnitTypeIdk()
	{
		return floorUnitAreaUnitTypeIdk;
	}
	public void setFloorUnitAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		floorUnitAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getFloorUnitSizeApproximated()
	{
		return floorUnitSizeApproximated;
	}
	public void setFloorUnitSizeApproximated(Boolean inputParameter)
	{
		modify();
		floorUnitSizeApproximated = inputParameter;
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

	public Short getUnitFloor()
	{
		return unitFloor;
	}
	public void setUnitFloor(Short inputParameter)
	{
		modify();
		unitFloor = inputParameter;
	}

	public Short getTotalFloorsInBuilding()
	{
		return totalFloorsInBuilding;
	}
	public void setTotalFloorsInBuilding(Short inputParameter)
	{
		modify();
		totalFloorsInBuilding = inputParameter;
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
		fields.put("floorUnitAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(floorUnitAreaUnitTypeIdk).get("name"));
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
		collectField(format, dateFormat, fields, "floorUnitSize", floorUnitSize);
		collectField(format, dateFormat, fields, "floorUnitAreaUnitTypeIdk", floorUnitAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "floorUnitSizeApproximated", floorUnitSizeApproximated);
		collectField(format, dateFormat, fields, "foundationTypeIdk", foundationTypeIdk);
		collectField(format, dateFormat, fields, "exteriorTypeIdk", exteriorTypeIdk);
		collectField(format, dateFormat, fields, "exteriorPrimaryColorIdk", exteriorPrimaryColorIdk);
		collectField(format, dateFormat, fields, "exteriorSecondaryColorIdk", exteriorSecondaryColorIdk);
		collectField(format, dateFormat, fields, "interiorPrimaryColorIdk", interiorPrimaryColorIdk);
		collectField(format, dateFormat, fields, "interiorSecondaryColorIdk", interiorSecondaryColorIdk);
		collectField(format, dateFormat, fields, "unitFloor", unitFloor);
		collectField(format, dateFormat, fields, "totalFloorsInBuilding", totalFloorsInBuilding);
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
		if (fields.containsKey("floorUnitSize"))
			setFloorUnitSize( (Integer) fields.get("floorUnitSize") );
		if (fields.containsKey("floorUnitAreaUnitTypeIdk"))
			setFloorUnitAreaUnitTypeIdk( (Long) fields.get("floorUnitAreaUnitTypeIdk") );
		if (fields.containsKey("floorUnitSizeApproximated"))
			setFloorUnitSizeApproximated( (Boolean) fields.get("floorUnitSizeApproximated") );
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
		if (fields.containsKey("unitFloor"))
			setUnitFloor( ((Number) fields.get("unitFloor")).shortValue() );
		if (fields.containsKey("totalFloorsInBuilding"))
			setTotalFloorsInBuilding( ((Number) fields.get("totalFloorsInBuilding")).shortValue() );
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
				(specificFields != null && !exclude && specificFields.contains("floorUnitSize")) ||
				(specificFields != null && exclude && !specificFields.contains("floorUnitSize")))
			{
				result = 
					validator.clearChecks().setName("floorUnitSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(floorUnitSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("floorUnitAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("floorUnitAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("floorUnitAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(floorUnitAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("floorUnitSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("floorUnitSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("floorUnitSizeApproximated").setAllowNullElement(false).checkBoolean(floorUnitSizeApproximated);
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
				(specificFields != null && !exclude && specificFields.contains("unitFloor")) ||
				(specificFields != null && exclude && !specificFields.contains("unitFloor")))
			{
				result = 
					validator.clearChecks().setName("unitFloor").setValidMinimum((double) 0).checkShort(unitFloor);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalFloorsInBuilding")) ||
				(specificFields != null && exclude && !specificFields.contains("totalFloorsInBuilding")))
			{
				result = 
					validator.clearChecks().setName("totalFloorsInBuilding").setValidMinimum((double) 0).checkShort(totalFloorsInBuilding);
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

