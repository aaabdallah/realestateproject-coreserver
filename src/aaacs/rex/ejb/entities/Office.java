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
			name = "List<Office>",
			entities =
			{
				@EntityResult
				(
					entityClass = Office.class,
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
						@FieldResult(name = "officeSize", column = "officeSize"),
						@FieldResult(name = "officeAreaUnitTypeIdk", column = "officeAreaUnitTypeIdk"),
						@FieldResult(name = "officeSizeApproximated", column = "officeSizeApproximated"),
						@FieldResult(name = "intendedBusinessTypeIdk", column = "intendedBusinessTypeIdk"),
						@FieldResult(name = "officeNumber", column = "officeNumber"),
						@FieldResult(name = "officeFloor", column = "officeFloor"),
						@FieldResult(name = "totalFloorsInBuilding", column = "totalFloorsInBuilding"),
						@FieldResult(name = "totalOfficesInFloor", column = "totalOfficesInFloor"),
						@FieldResult(name = "totalOfficesInBuilding", column = "totalOfficesInBuilding"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "commercialFeaturesIdk", column = "commercialFeaturesIdk"),
						@FieldResult(name = "income", column = "income"),
						@FieldResult(name = "currencyIdk", column = "currencyIdk")
					}
				)
			}
		)
	}
)
@Entity(name="Office") @Table(name="\"tOffices\"")
public class Office extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tOffices\"";

	static public Office findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Office) BaseEntity.findByIdk(manager, Office.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findOfficeByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Office>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findOfficeByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Office>");
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

	static public Map<String, List<MsgArgsPair>> validateOffice(Office office,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return office.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePropertyIdk(Long propertyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("propertyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateOfficeSize(Integer officeSize)
	{
		return (new FieldValidator()).clearChecks().setName("officeSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(officeSize);
	}

	static public Map<String, List<MsgArgsPair>> validateOfficeAreaUnitTypeIdk(Long officeAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("officeAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(officeAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateOfficeSizeApproximated(Boolean officeSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("officeSizeApproximated").setAllowNullElement(false).checkBoolean(officeSizeApproximated);
	}

	static public Map<String, List<MsgArgsPair>> validateIntendedBusinessTypeIdk(Long intendedBusinessTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("intendedBusinessTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(intendedBusinessTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateOfficeNumber(String officeNumber)
	{
		return (new FieldValidator()).clearChecks().setName("officeNumber").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(officeNumber);
	}

	static public Map<String, List<MsgArgsPair>> validateOfficeFloor(Short officeFloor)
	{
		return (new FieldValidator()).clearChecks().setName("officeFloor").setValidMinimum((double) 0).checkShort(officeFloor);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalFloorsInBuilding(Short totalFloorsInBuilding)
	{
		return (new FieldValidator()).clearChecks().setName("totalFloorsInBuilding").setValidMinimum((double) 0).checkShort(totalFloorsInBuilding);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalOfficesInFloor(Short totalOfficesInFloor)
	{
		return (new FieldValidator()).clearChecks().setName("totalOfficesInFloor").setValidMinimum((double) 0).checkShort(totalOfficesInFloor);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalOfficesInBuilding(Short totalOfficesInBuilding)
	{
		return (new FieldValidator()).clearChecks().setName("totalOfficesInBuilding").setValidMinimum((double) 0).checkShort(totalOfficesInBuilding);
	}

	static public Map<String, List<MsgArgsPair>> validateDescription(String description)
	{
		return (new FieldValidator()).clearChecks().setName("description").setValidMinimum((double) 0).setValidMaximum((double) 2000).checkString(description);
	}

	static public Map<String, List<MsgArgsPair>> validateCommercialFeaturesIdk(Long commercialFeaturesIdk)
	{
		return (new FieldValidator()).clearChecks().setName("commercialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(commercialFeaturesIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateIncome(Integer income)
	{
		return (new FieldValidator()).clearChecks().setName("income").setValidMinimum((double) 0).checkInteger(income);
	}

	static public Map<String, List<MsgArgsPair>> validateCurrencyIdk(Long currencyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("currencyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(currencyIdk);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("officeSizeMinimum") != null && searchFields.get("officeSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"officeSize\" >= " + searchFields.get("officeSizeMinimum"), true);
		if (searchFields.get("officeSizeMaximum") != null && searchFields.get("officeSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"officeSize\" <= " + searchFields.get("officeSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check officeAreaUnitTypeIdk by itself; only use it with officeSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("intendedBusinessTypeIdk") != null && ((Long[]) searchFields.get("intendedBusinessTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"intendedBusinessTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("intendedBusinessTypeIdk"), "", "") + ")", true);
		if (searchFields.get("officeFloorMinimum") != null && searchFields.get("officeFloorMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"officeFloor\" >= " + searchFields.get("officeFloorMinimum"), true);
		if (searchFields.get("officeFloorMaximum") != null && searchFields.get("officeFloorMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"officeFloor\" <= " + searchFields.get("officeFloorMaximum"), true);
		if (searchFields.get("totalFloorsInBuildingMaximum") != null && searchFields.get("totalFloorsInBuildingMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalFloorsInBuilding\" <= " + searchFields.get("totalFloorsInBuildingMaximum"), true);
		if (searchFields.get("totalOfficesInBuildingMaximum") != null && searchFields.get("totalOfficesInBuildingMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalOfficesInBuilding\" <= " + searchFields.get("totalOfficesInBuildingMaximum"), true);
		if (searchFields.get("incomeMinimum") != null && searchFields.get("incomeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"income\" >= " + searchFields.get("incomeMinimum"), true);
		if (searchFields.get("incomeMaximum") != null && searchFields.get("incomeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"income\" <= " + searchFields.get("incomeMaximum"), true);
		if (searchFields.get("currencyIdk") != null && searchFields.get("currencyIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"currencyIdk\" = " + searchFields.get("currencyIdk"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"officeSize\"") private Integer officeSize = null;
	@Basic @Column(name="\"officeAreaUnitTypeIdk\"") private Long officeAreaUnitTypeIdk = null;
	@Basic @Column(name="\"officeSizeApproximated\"") private Boolean officeSizeApproximated = null;
	@Basic @Column(name="\"intendedBusinessTypeIdk\"") private Long intendedBusinessTypeIdk = null;
	@Basic @Column(name="\"officeNumber\"") private String officeNumber = null;
	@Basic @Column(name="\"officeFloor\"") private Short officeFloor = null;
	@Basic @Column(name="\"totalFloorsInBuilding\"") private Short totalFloorsInBuilding = null;
	@Basic @Column(name="\"totalOfficesInFloor\"") private Short totalOfficesInFloor = null;
	@Basic @Column(name="\"totalOfficesInBuilding\"") private Short totalOfficesInBuilding = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"commercialFeaturesIdk\"") private Long commercialFeaturesIdk = null;
	@Basic @Column(name="\"income\"") private Integer income = null;
	@Basic @Column(name="\"currencyIdk\"") private Long currencyIdk = null;

	public Office() {}
	public Office(Map<String, Object> fields)
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

	public Integer getOfficeSize()
	{
		return officeSize;
	}
	public void setOfficeSize(Integer inputParameter)
	{
		modify();
		officeSize = inputParameter;
	}

	public Long getOfficeAreaUnitTypeIdk()
	{
		return officeAreaUnitTypeIdk;
	}
	public void setOfficeAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		officeAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getOfficeSizeApproximated()
	{
		return officeSizeApproximated;
	}
	public void setOfficeSizeApproximated(Boolean inputParameter)
	{
		modify();
		officeSizeApproximated = inputParameter;
	}

	public Long getIntendedBusinessTypeIdk()
	{
		return intendedBusinessTypeIdk;
	}
	public void setIntendedBusinessTypeIdk(Long inputParameter)
	{
		modify();
		intendedBusinessTypeIdk = inputParameter;
	}

	public String getOfficeNumber()
	{
		return officeNumber;
	}
	public void setOfficeNumber(String inputParameter)
	{
		modify();
		officeNumber = inputParameter;
	}

	public Short getOfficeFloor()
	{
		return officeFloor;
	}
	public void setOfficeFloor(Short inputParameter)
	{
		modify();
		officeFloor = inputParameter;
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

	public Short getTotalOfficesInFloor()
	{
		return totalOfficesInFloor;
	}
	public void setTotalOfficesInFloor(Short inputParameter)
	{
		modify();
		totalOfficesInFloor = inputParameter;
	}

	public Short getTotalOfficesInBuilding()
	{
		return totalOfficesInBuilding;
	}
	public void setTotalOfficesInBuilding(Short inputParameter)
	{
		modify();
		totalOfficesInBuilding = inputParameter;
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

	public Long getCommercialFeaturesIdk()
	{
		return commercialFeaturesIdk;
	}
	public void setCommercialFeaturesIdk(Long inputParameter)
	{
		modify();
		commercialFeaturesIdk = inputParameter;
	}

	public Integer getIncome()
	{
		return income;
	}
	public void setIncome(Integer inputParameter)
	{
		modify();
		income = inputParameter;
	}

	public Long getCurrencyIdk()
	{
		return currencyIdk;
	}
	public void setCurrencyIdk(Long inputParameter)
	{
		modify();
		currencyIdk = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("officeAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(officeAreaUnitTypeIdk).get("name"));
		fields.put("intendedBusinessTypeIdkMsg",
			(String) PropertyOption.cacheLookup(intendedBusinessTypeIdk).get("name"));
		fields.put("currencyIdkMsg",
			(String) Currency.cacheLookup(currencyIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "propertyIdk", propertyIdk);
		collectField(format, dateFormat, fields, "officeSize", officeSize);
		collectField(format, dateFormat, fields, "officeAreaUnitTypeIdk", officeAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "officeSizeApproximated", officeSizeApproximated);
		collectField(format, dateFormat, fields, "intendedBusinessTypeIdk", intendedBusinessTypeIdk);
		collectField(format, dateFormat, fields, "officeNumber", officeNumber);
		collectField(format, dateFormat, fields, "officeFloor", officeFloor);
		collectField(format, dateFormat, fields, "totalFloorsInBuilding", totalFloorsInBuilding);
		collectField(format, dateFormat, fields, "totalOfficesInFloor", totalOfficesInFloor);
		collectField(format, dateFormat, fields, "totalOfficesInBuilding", totalOfficesInBuilding);
		collectField(format, dateFormat, fields, "description", description);
		collectField(format, dateFormat, fields, "commercialFeaturesIdk", commercialFeaturesIdk);
		collectField(format, dateFormat, fields, "income", income);
		collectField(format, dateFormat, fields, "currencyIdk", currencyIdk);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("propertyIdk"))
			setPropertyIdk( (Long) fields.get("propertyIdk") );
		if (fields.containsKey("officeSize"))
			setOfficeSize( (Integer) fields.get("officeSize") );
		if (fields.containsKey("officeAreaUnitTypeIdk"))
			setOfficeAreaUnitTypeIdk( (Long) fields.get("officeAreaUnitTypeIdk") );
		if (fields.containsKey("officeSizeApproximated"))
			setOfficeSizeApproximated( (Boolean) fields.get("officeSizeApproximated") );
		if (fields.containsKey("intendedBusinessTypeIdk"))
			setIntendedBusinessTypeIdk( (Long) fields.get("intendedBusinessTypeIdk") );
		if (fields.containsKey("officeNumber"))
			setOfficeNumber( (String) fields.get("officeNumber") );
		if (fields.containsKey("officeFloor"))
			setOfficeFloor( ((Number) fields.get("officeFloor")).shortValue() );
		if (fields.containsKey("totalFloorsInBuilding"))
			setTotalFloorsInBuilding( ((Number) fields.get("totalFloorsInBuilding")).shortValue() );
		if (fields.containsKey("totalOfficesInFloor"))
			setTotalOfficesInFloor( ((Number) fields.get("totalOfficesInFloor")).shortValue() );
		if (fields.containsKey("totalOfficesInBuilding"))
			setTotalOfficesInBuilding( ((Number) fields.get("totalOfficesInBuilding")).shortValue() );
		if (fields.containsKey("description"))
			setDescription( (String) fields.get("description") );
		if (fields.containsKey("commercialFeaturesIdk"))
			setCommercialFeaturesIdk( (Long) fields.get("commercialFeaturesIdk") );
		if (fields.containsKey("income"))
			setIncome( (Integer) fields.get("income") );
		if (fields.containsKey("currencyIdk"))
			setCurrencyIdk( (Long) fields.get("currencyIdk") );
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
				(specificFields != null && !exclude && specificFields.contains("officeSize")) ||
				(specificFields != null && exclude && !specificFields.contains("officeSize")))
			{
				result = 
					validator.clearChecks().setName("officeSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(officeSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("officeAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("officeAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("officeAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(officeAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("officeSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("officeSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("officeSizeApproximated").setAllowNullElement(false).checkBoolean(officeSizeApproximated);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("intendedBusinessTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("intendedBusinessTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("intendedBusinessTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(intendedBusinessTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("officeNumber")) ||
				(specificFields != null && exclude && !specificFields.contains("officeNumber")))
			{
				result = 
					validator.clearChecks().setName("officeNumber").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(officeNumber);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("officeFloor")) ||
				(specificFields != null && exclude && !specificFields.contains("officeFloor")))
			{
				result = 
					validator.clearChecks().setName("officeFloor").setValidMinimum((double) 0).checkShort(officeFloor);
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
				(specificFields != null && !exclude && specificFields.contains("totalOfficesInFloor")) ||
				(specificFields != null && exclude && !specificFields.contains("totalOfficesInFloor")))
			{
				result = 
					validator.clearChecks().setName("totalOfficesInFloor").setValidMinimum((double) 0).checkShort(totalOfficesInFloor);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalOfficesInBuilding")) ||
				(specificFields != null && exclude && !specificFields.contains("totalOfficesInBuilding")))
			{
				result = 
					validator.clearChecks().setName("totalOfficesInBuilding").setValidMinimum((double) 0).checkShort(totalOfficesInBuilding);
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
				(specificFields != null && !exclude && specificFields.contains("commercialFeaturesIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("commercialFeaturesIdk")))
			{
				result = 
					validator.clearChecks().setName("commercialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(commercialFeaturesIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("income")) ||
				(specificFields != null && exclude && !specificFields.contains("income")))
			{
				result = 
					validator.clearChecks().setName("income").setValidMinimum((double) 0).checkInteger(income);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("currencyIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("currencyIdk")))
			{
				result = 
					validator.clearChecks().setName("currencyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(currencyIdk);
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

