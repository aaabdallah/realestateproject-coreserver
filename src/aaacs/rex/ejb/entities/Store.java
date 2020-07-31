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
			name = "List<Store>",
			entities =
			{
				@EntityResult
				(
					entityClass = Store.class,
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
						@FieldResult(name = "storeSize", column = "storeSize"),
						@FieldResult(name = "storeAreaUnitTypeIdk", column = "storeAreaUnitTypeIdk"),
						@FieldResult(name = "storeSizeApproximated", column = "storeSizeApproximated"),
						@FieldResult(name = "intendedBusinessTypeIdk", column = "intendedBusinessTypeIdk"),
						@FieldResult(name = "storeNumber", column = "storeNumber"),
						@FieldResult(name = "storeFloor", column = "storeFloor"),
						@FieldResult(name = "totalFloorsInBuilding", column = "totalFloorsInBuilding"),
						@FieldResult(name = "totalStoresInFloor", column = "totalStoresInFloor"),
						@FieldResult(name = "totalStoresInBuilding", column = "totalStoresInBuilding"),
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
@Entity(name="Store") @Table(name="\"tStores\"")
public class Store extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tStores\"";

	static public Store findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Store) BaseEntity.findByIdk(manager, Store.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findStoreByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Store>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findStoreByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Store>");
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

	static public Map<String, List<MsgArgsPair>> validateStore(Store store,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return store.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePropertyIdk(Long propertyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("propertyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateStoreSize(Integer storeSize)
	{
		return (new FieldValidator()).clearChecks().setName("storeSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(storeSize);
	}

	static public Map<String, List<MsgArgsPair>> validateStoreAreaUnitTypeIdk(Long storeAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("storeAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(storeAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateStoreSizeApproximated(Boolean storeSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("storeSizeApproximated").setAllowNullElement(false).checkBoolean(storeSizeApproximated);
	}

	static public Map<String, List<MsgArgsPair>> validateIntendedBusinessTypeIdk(Long intendedBusinessTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("intendedBusinessTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(intendedBusinessTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateStoreNumber(String storeNumber)
	{
		return (new FieldValidator()).clearChecks().setName("storeNumber").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(storeNumber);
	}

	static public Map<String, List<MsgArgsPair>> validateStoreFloor(Short storeFloor)
	{
		return (new FieldValidator()).clearChecks().setName("storeFloor").setValidMinimum((double) 0).checkShort(storeFloor);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalFloorsInBuilding(Short totalFloorsInBuilding)
	{
		return (new FieldValidator()).clearChecks().setName("totalFloorsInBuilding").setValidMinimum((double) 0).checkShort(totalFloorsInBuilding);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalStoresInFloor(Short totalStoresInFloor)
	{
		return (new FieldValidator()).clearChecks().setName("totalStoresInFloor").setValidMinimum((double) 0).checkShort(totalStoresInFloor);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalStoresInBuilding(Short totalStoresInBuilding)
	{
		return (new FieldValidator()).clearChecks().setName("totalStoresInBuilding").setValidMinimum((double) 0).checkShort(totalStoresInBuilding);
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

		if (searchFields.get("storeSizeMinimum") != null && searchFields.get("storeSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"storeSize\" >= " + searchFields.get("storeSizeMinimum"), true);
		if (searchFields.get("storeSizeMaximum") != null && searchFields.get("storeSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"storeSize\" <= " + searchFields.get("storeSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check storeAreaUnitTypeIdk by itself; only use it with storeSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("intendedBusinessTypeIdk") != null && ((Long[]) searchFields.get("intendedBusinessTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"intendedBusinessTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("intendedBusinessTypeIdk"), "", "") + ")", true);
		if (searchFields.get("storeFloorMinimum") != null && searchFields.get("storeFloorMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"storeFloor\" >= " + searchFields.get("storeFloorMinimum"), true);
		if (searchFields.get("storeFloorMaximum") != null && searchFields.get("storeFloorMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"storeFloor\" <= " + searchFields.get("storeFloorMaximum"), true);
		if (searchFields.get("totalFloorsInBuildingMaximum") != null && searchFields.get("totalFloorsInBuildingMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalFloorsInBuilding\" <= " + searchFields.get("totalFloorsInBuildingMaximum"), true);
		if (searchFields.get("totalStoresInBuildingMaximum") != null && searchFields.get("totalStoresInBuildingMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalStoresInBuilding\" <= " + searchFields.get("totalStoresInBuildingMaximum"), true);
		if (searchFields.get("incomeMinimum") != null && searchFields.get("incomeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"income\" >= " + searchFields.get("incomeMinimum"), true);
		if (searchFields.get("incomeMaximum") != null && searchFields.get("incomeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"income\" <= " + searchFields.get("incomeMaximum"), true);
		if (searchFields.get("currencyIdk") != null && searchFields.get("currencyIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"currencyIdk\" = " + searchFields.get("currencyIdk"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"storeSize\"") private Integer storeSize = null;
	@Basic @Column(name="\"storeAreaUnitTypeIdk\"") private Long storeAreaUnitTypeIdk = null;
	@Basic @Column(name="\"storeSizeApproximated\"") private Boolean storeSizeApproximated = null;
	@Basic @Column(name="\"intendedBusinessTypeIdk\"") private Long intendedBusinessTypeIdk = null;
	@Basic @Column(name="\"storeNumber\"") private String storeNumber = null;
	@Basic @Column(name="\"storeFloor\"") private Short storeFloor = null;
	@Basic @Column(name="\"totalFloorsInBuilding\"") private Short totalFloorsInBuilding = null;
	@Basic @Column(name="\"totalStoresInFloor\"") private Short totalStoresInFloor = null;
	@Basic @Column(name="\"totalStoresInBuilding\"") private Short totalStoresInBuilding = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"commercialFeaturesIdk\"") private Long commercialFeaturesIdk = null;
	@Basic @Column(name="\"income\"") private Integer income = null;
	@Basic @Column(name="\"currencyIdk\"") private Long currencyIdk = null;

	public Store() {}
	public Store(Map<String, Object> fields)
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

	public Integer getStoreSize()
	{
		return storeSize;
	}
	public void setStoreSize(Integer inputParameter)
	{
		modify();
		storeSize = inputParameter;
	}

	public Long getStoreAreaUnitTypeIdk()
	{
		return storeAreaUnitTypeIdk;
	}
	public void setStoreAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		storeAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getStoreSizeApproximated()
	{
		return storeSizeApproximated;
	}
	public void setStoreSizeApproximated(Boolean inputParameter)
	{
		modify();
		storeSizeApproximated = inputParameter;
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

	public String getStoreNumber()
	{
		return storeNumber;
	}
	public void setStoreNumber(String inputParameter)
	{
		modify();
		storeNumber = inputParameter;
	}

	public Short getStoreFloor()
	{
		return storeFloor;
	}
	public void setStoreFloor(Short inputParameter)
	{
		modify();
		storeFloor = inputParameter;
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

	public Short getTotalStoresInFloor()
	{
		return totalStoresInFloor;
	}
	public void setTotalStoresInFloor(Short inputParameter)
	{
		modify();
		totalStoresInFloor = inputParameter;
	}

	public Short getTotalStoresInBuilding()
	{
		return totalStoresInBuilding;
	}
	public void setTotalStoresInBuilding(Short inputParameter)
	{
		modify();
		totalStoresInBuilding = inputParameter;
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
		fields.put("storeAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(storeAreaUnitTypeIdk).get("name"));
		fields.put("intendedBusinessTypeIdkMsg",
			(String) PropertyOption.cacheLookup(intendedBusinessTypeIdk).get("name"));
		fields.put("currencyIdkMsg",
			(String) Currency.cacheLookup(currencyIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "propertyIdk", propertyIdk);
		collectField(format, dateFormat, fields, "storeSize", storeSize);
		collectField(format, dateFormat, fields, "storeAreaUnitTypeIdk", storeAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "storeSizeApproximated", storeSizeApproximated);
		collectField(format, dateFormat, fields, "intendedBusinessTypeIdk", intendedBusinessTypeIdk);
		collectField(format, dateFormat, fields, "storeNumber", storeNumber);
		collectField(format, dateFormat, fields, "storeFloor", storeFloor);
		collectField(format, dateFormat, fields, "totalFloorsInBuilding", totalFloorsInBuilding);
		collectField(format, dateFormat, fields, "totalStoresInFloor", totalStoresInFloor);
		collectField(format, dateFormat, fields, "totalStoresInBuilding", totalStoresInBuilding);
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
		if (fields.containsKey("storeSize"))
			setStoreSize( (Integer) fields.get("storeSize") );
		if (fields.containsKey("storeAreaUnitTypeIdk"))
			setStoreAreaUnitTypeIdk( (Long) fields.get("storeAreaUnitTypeIdk") );
		if (fields.containsKey("storeSizeApproximated"))
			setStoreSizeApproximated( (Boolean) fields.get("storeSizeApproximated") );
		if (fields.containsKey("intendedBusinessTypeIdk"))
			setIntendedBusinessTypeIdk( (Long) fields.get("intendedBusinessTypeIdk") );
		if (fields.containsKey("storeNumber"))
			setStoreNumber( (String) fields.get("storeNumber") );
		if (fields.containsKey("storeFloor"))
			setStoreFloor( ((Number) fields.get("storeFloor")).shortValue() );
		if (fields.containsKey("totalFloorsInBuilding"))
			setTotalFloorsInBuilding( ((Number) fields.get("totalFloorsInBuilding")).shortValue() );
		if (fields.containsKey("totalStoresInFloor"))
			setTotalStoresInFloor( ((Number) fields.get("totalStoresInFloor")).shortValue() );
		if (fields.containsKey("totalStoresInBuilding"))
			setTotalStoresInBuilding( ((Number) fields.get("totalStoresInBuilding")).shortValue() );
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
				(specificFields != null && !exclude && specificFields.contains("storeSize")) ||
				(specificFields != null && exclude && !specificFields.contains("storeSize")))
			{
				result = 
					validator.clearChecks().setName("storeSize").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(storeSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("storeAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("storeAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("storeAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(storeAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("storeSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("storeSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("storeSizeApproximated").setAllowNullElement(false).checkBoolean(storeSizeApproximated);
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
				(specificFields != null && !exclude && specificFields.contains("storeNumber")) ||
				(specificFields != null && exclude && !specificFields.contains("storeNumber")))
			{
				result = 
					validator.clearChecks().setName("storeNumber").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(storeNumber);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("storeFloor")) ||
				(specificFields != null && exclude && !specificFields.contains("storeFloor")))
			{
				result = 
					validator.clearChecks().setName("storeFloor").setValidMinimum((double) 0).checkShort(storeFloor);
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
				(specificFields != null && !exclude && specificFields.contains("totalStoresInFloor")) ||
				(specificFields != null && exclude && !specificFields.contains("totalStoresInFloor")))
			{
				result = 
					validator.clearChecks().setName("totalStoresInFloor").setValidMinimum((double) 0).checkShort(totalStoresInFloor);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalStoresInBuilding")) ||
				(specificFields != null && exclude && !specificFields.contains("totalStoresInBuilding")))
			{
				result = 
					validator.clearChecks().setName("totalStoresInBuilding").setValidMinimum((double) 0).checkShort(totalStoresInBuilding);
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

