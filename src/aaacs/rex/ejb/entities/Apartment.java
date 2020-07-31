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
			name = "List<Apartment>",
			entities =
			{
				@EntityResult
				(
					entityClass = Apartment.class,
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
						@FieldResult(name = "apartmentSize", column = "apartmentSize"),
						@FieldResult(name = "apartmentAreaUnitTypeIdk", column = "apartmentAreaUnitTypeIdk"),
						@FieldResult(name = "apartmentSizeApproximated", column = "apartmentSizeApproximated"),
						@FieldResult(name = "apartmentNumber", column = "apartmentNumber"),
						@FieldResult(name = "apartmentFloor", column = "apartmentFloor"),
						@FieldResult(name = "totalFloorsInBuilding", column = "totalFloorsInBuilding"),
						@FieldResult(name = "totalApartmentsInFloor", column = "totalApartmentsInFloor"),
						@FieldResult(name = "totalApartmentsInBuilding", column = "totalApartmentsInBuilding"),
						@FieldResult(name = "interiorPrimaryColorIdk", column = "interiorPrimaryColorIdk"),
						@FieldResult(name = "interiorSecondaryColorIdk", column = "interiorSecondaryColorIdk"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "residentialFeaturesIdk", column = "residentialFeaturesIdk")
					}
				)
			}
		)
	}
)
@Entity(name="Apartment") @Table(name="\"tApartments\"")
public class Apartment extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tApartments\"";

	static public Apartment findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Apartment) BaseEntity.findByIdk(manager, Apartment.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findApartmentByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Apartment>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findApartmentByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Apartment>");
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

	static public Map<String, List<MsgArgsPair>> validateApartment(Apartment apartment,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return apartment.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePropertyIdk(Long propertyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("propertyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateApartmentSize(Integer apartmentSize)
	{
		return (new FieldValidator()).clearChecks().setName("apartmentSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(apartmentSize);
	}

	static public Map<String, List<MsgArgsPair>> validateApartmentAreaUnitTypeIdk(Long apartmentAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("apartmentAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(apartmentAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateApartmentSizeApproximated(Boolean apartmentSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("apartmentSizeApproximated").setAllowNullElement(false).checkBoolean(apartmentSizeApproximated);
	}

	static public Map<String, List<MsgArgsPair>> validateApartmentNumber(String apartmentNumber)
	{
		return (new FieldValidator()).clearChecks().setName("apartmentNumber").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(apartmentNumber);
	}

	static public Map<String, List<MsgArgsPair>> validateApartmentFloor(Short apartmentFloor)
	{
		return (new FieldValidator()).clearChecks().setName("apartmentFloor").setValidMinimum((double) 0).checkShort(apartmentFloor);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalFloorsInBuilding(Short totalFloorsInBuilding)
	{
		return (new FieldValidator()).clearChecks().setName("totalFloorsInBuilding").setValidMinimum((double) 0).checkShort(totalFloorsInBuilding);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalApartmentsInFloor(Short totalApartmentsInFloor)
	{
		return (new FieldValidator()).clearChecks().setName("totalApartmentsInFloor").setValidMinimum((double) 0).checkShort(totalApartmentsInFloor);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalApartmentsInBuilding(Short totalApartmentsInBuilding)
	{
		return (new FieldValidator()).clearChecks().setName("totalApartmentsInBuilding").setValidMinimum((double) 0).checkShort(totalApartmentsInBuilding);
	}

	static public Map<String, List<MsgArgsPair>> validateInteriorPrimaryColorIdk(Long interiorPrimaryColorIdk)
	{
		return (new FieldValidator()).clearChecks().setName("interiorPrimaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(interiorPrimaryColorIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateInteriorSecondaryColorIdk(Long interiorSecondaryColorIdk)
	{
		return (new FieldValidator()).clearChecks().setName("interiorSecondaryColorIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(interiorSecondaryColorIdk);
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

		if (searchFields.get("apartmentSizeMinimum") != null && searchFields.get("apartmentSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"apartmentSize\" >= " + searchFields.get("apartmentSizeMinimum"), true);
		if (searchFields.get("apartmentSizeMaximum") != null && searchFields.get("apartmentSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"apartmentSize\" <= " + searchFields.get("apartmentSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check apartmentAreaUnitTypeIdk by itself; only use it with apartmentSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("apartmentFloorMinimum") != null && searchFields.get("apartmentFloorMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"apartmentFloor\" >= " + searchFields.get("apartmentFloorMinimum"), true);
		if (searchFields.get("apartmentFloorMaximum") != null && searchFields.get("apartmentFloorMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"apartmentFloor\" <= " + searchFields.get("apartmentFloorMaximum"), true);
		if (searchFields.get("totalFloorsInBuildingMaximum") != null && searchFields.get("totalFloorsInBuildingMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalFloorsInBuilding\" <= " + searchFields.get("totalFloorsInBuildingMaximum"), true);
		if (searchFields.get("totalApartmentsInBuildingMaximum") != null && searchFields.get("totalApartmentsInBuildingMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalApartmentsInBuilding\" <= " + searchFields.get("totalApartmentsInBuildingMaximum"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"apartmentSize\"") private Integer apartmentSize = null;
	@Basic @Column(name="\"apartmentAreaUnitTypeIdk\"") private Long apartmentAreaUnitTypeIdk = null;
	@Basic @Column(name="\"apartmentSizeApproximated\"") private Boolean apartmentSizeApproximated = null;
	@Basic @Column(name="\"apartmentNumber\"") private String apartmentNumber = null;
	@Basic @Column(name="\"apartmentFloor\"") private Short apartmentFloor = null;
	@Basic @Column(name="\"totalFloorsInBuilding\"") private Short totalFloorsInBuilding = null;
	@Basic @Column(name="\"totalApartmentsInFloor\"") private Short totalApartmentsInFloor = null;
	@Basic @Column(name="\"totalApartmentsInBuilding\"") private Short totalApartmentsInBuilding = null;
	@Basic @Column(name="\"interiorPrimaryColorIdk\"") private Long interiorPrimaryColorIdk = null;
	@Basic @Column(name="\"interiorSecondaryColorIdk\"") private Long interiorSecondaryColorIdk = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"residentialFeaturesIdk\"") private Long residentialFeaturesIdk = null;

	public Apartment() {}
	public Apartment(Map<String, Object> fields)
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

	public Integer getApartmentSize()
	{
		return apartmentSize;
	}
	public void setApartmentSize(Integer inputParameter)
	{
		modify();
		apartmentSize = inputParameter;
	}

	public Long getApartmentAreaUnitTypeIdk()
	{
		return apartmentAreaUnitTypeIdk;
	}
	public void setApartmentAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		apartmentAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getApartmentSizeApproximated()
	{
		return apartmentSizeApproximated;
	}
	public void setApartmentSizeApproximated(Boolean inputParameter)
	{
		modify();
		apartmentSizeApproximated = inputParameter;
	}

	public String getApartmentNumber()
	{
		return apartmentNumber;
	}
	public void setApartmentNumber(String inputParameter)
	{
		modify();
		apartmentNumber = inputParameter;
	}

	public Short getApartmentFloor()
	{
		return apartmentFloor;
	}
	public void setApartmentFloor(Short inputParameter)
	{
		modify();
		apartmentFloor = inputParameter;
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

	public Short getTotalApartmentsInFloor()
	{
		return totalApartmentsInFloor;
	}
	public void setTotalApartmentsInFloor(Short inputParameter)
	{
		modify();
		totalApartmentsInFloor = inputParameter;
	}

	public Short getTotalApartmentsInBuilding()
	{
		return totalApartmentsInBuilding;
	}
	public void setTotalApartmentsInBuilding(Short inputParameter)
	{
		modify();
		totalApartmentsInBuilding = inputParameter;
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
		fields.put("apartmentAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(apartmentAreaUnitTypeIdk).get("name"));
		fields.put("interiorPrimaryColorIdkMsg",
			(String) PropertyOption.cacheLookup(interiorPrimaryColorIdk).get("name"));
		fields.put("interiorSecondaryColorIdkMsg",
			(String) PropertyOption.cacheLookup(interiorSecondaryColorIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "propertyIdk", propertyIdk);
		collectField(format, dateFormat, fields, "apartmentSize", apartmentSize);
		collectField(format, dateFormat, fields, "apartmentAreaUnitTypeIdk", apartmentAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "apartmentSizeApproximated", apartmentSizeApproximated);
		collectField(format, dateFormat, fields, "apartmentNumber", apartmentNumber);
		collectField(format, dateFormat, fields, "apartmentFloor", apartmentFloor);
		collectField(format, dateFormat, fields, "totalFloorsInBuilding", totalFloorsInBuilding);
		collectField(format, dateFormat, fields, "totalApartmentsInFloor", totalApartmentsInFloor);
		collectField(format, dateFormat, fields, "totalApartmentsInBuilding", totalApartmentsInBuilding);
		collectField(format, dateFormat, fields, "interiorPrimaryColorIdk", interiorPrimaryColorIdk);
		collectField(format, dateFormat, fields, "interiorSecondaryColorIdk", interiorSecondaryColorIdk);
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
		if (fields.containsKey("apartmentSize"))
			setApartmentSize( (Integer) fields.get("apartmentSize") );
		if (fields.containsKey("apartmentAreaUnitTypeIdk"))
			setApartmentAreaUnitTypeIdk( (Long) fields.get("apartmentAreaUnitTypeIdk") );
		if (fields.containsKey("apartmentSizeApproximated"))
			setApartmentSizeApproximated( (Boolean) fields.get("apartmentSizeApproximated") );
		if (fields.containsKey("apartmentNumber"))
			setApartmentNumber( (String) fields.get("apartmentNumber") );
		if (fields.containsKey("apartmentFloor"))
			setApartmentFloor( ((Number) fields.get("apartmentFloor")).shortValue() );
		if (fields.containsKey("totalFloorsInBuilding"))
			setTotalFloorsInBuilding( ((Number) fields.get("totalFloorsInBuilding")).shortValue() );
		if (fields.containsKey("totalApartmentsInFloor"))
			setTotalApartmentsInFloor( ((Number) fields.get("totalApartmentsInFloor")).shortValue() );
		if (fields.containsKey("totalApartmentsInBuilding"))
			setTotalApartmentsInBuilding( ((Number) fields.get("totalApartmentsInBuilding")).shortValue() );
		if (fields.containsKey("interiorPrimaryColorIdk"))
			setInteriorPrimaryColorIdk( (Long) fields.get("interiorPrimaryColorIdk") );
		if (fields.containsKey("interiorSecondaryColorIdk"))
			setInteriorSecondaryColorIdk( (Long) fields.get("interiorSecondaryColorIdk") );
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
				(specificFields != null && !exclude && specificFields.contains("apartmentSize")) ||
				(specificFields != null && exclude && !specificFields.contains("apartmentSize")))
			{
				result = 
					validator.clearChecks().setName("apartmentSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(apartmentSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("apartmentAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("apartmentAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("apartmentAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(apartmentAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("apartmentSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("apartmentSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("apartmentSizeApproximated").setAllowNullElement(false).checkBoolean(apartmentSizeApproximated);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("apartmentNumber")) ||
				(specificFields != null && exclude && !specificFields.contains("apartmentNumber")))
			{
				result = 
					validator.clearChecks().setName("apartmentNumber").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(apartmentNumber);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("apartmentFloor")) ||
				(specificFields != null && exclude && !specificFields.contains("apartmentFloor")))
			{
				result = 
					validator.clearChecks().setName("apartmentFloor").setValidMinimum((double) 0).checkShort(apartmentFloor);
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
				(specificFields != null && !exclude && specificFields.contains("totalApartmentsInFloor")) ||
				(specificFields != null && exclude && !specificFields.contains("totalApartmentsInFloor")))
			{
				result = 
					validator.clearChecks().setName("totalApartmentsInFloor").setValidMinimum((double) 0).checkShort(totalApartmentsInFloor);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalApartmentsInBuilding")) ||
				(specificFields != null && exclude && !specificFields.contains("totalApartmentsInBuilding")))
			{
				result = 
					validator.clearChecks().setName("totalApartmentsInBuilding").setValidMinimum((double) 0).checkShort(totalApartmentsInBuilding);
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

