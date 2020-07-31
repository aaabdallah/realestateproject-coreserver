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
			name = "List<MultiUnitCompound>",
			entities =
			{
				@EntityResult
				(
					entityClass = MultiUnitCompound.class,
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
						@FieldResult(name = "residentialUnitSize", column = "residentialUnitSize"),
						@FieldResult(name = "residentialUnitAreaUnitTypeIdk", column = "residentialUnitAreaUnitTypeIdk"),
						@FieldResult(name = "residentialUnitSizeApproximated", column = "residentialUnitSizeApproximated"),
						@FieldResult(name = "commercialUnitSize", column = "commercialUnitSize"),
						@FieldResult(name = "commercialUnitAreaUnitTypeIdk", column = "commercialUnitAreaUnitTypeIdk"),
						@FieldResult(name = "commercialUnitSizeApproximated", column = "commercialUnitSizeApproximated"),
						@FieldResult(name = "totalResidentialUnits", column = "totalResidentialUnits"),
						@FieldResult(name = "totalCommercialUnits", column = "totalCommercialUnits"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "unitResidentialFeaturesIdk", column = "unitResidentialFeaturesIdk"),
						@FieldResult(name = "unitCommercialFeaturesIdk", column = "unitCommercialFeaturesIdk"),
						@FieldResult(name = "income", column = "income"),
						@FieldResult(name = "currencyIdk", column = "currencyIdk")
					}
				)
			}
		)
	}
)
@Entity(name="MultiUnitCompound") @Table(name="\"tMultiUnitCompounds\"")
public class MultiUnitCompound extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tMultiUnitCompounds\"";

	static public MultiUnitCompound findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (MultiUnitCompound) BaseEntity.findByIdk(manager, MultiUnitCompound.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findMultiUnitCompoundByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<MultiUnitCompound>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findMultiUnitCompoundByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<MultiUnitCompound>");
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

	static public Map<String, List<MsgArgsPair>> validateMultiUnitCompound(MultiUnitCompound multiUnitCompound,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return multiUnitCompound.validateAllFields(userSuppliedIdk, specificFields, exclude);
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

	static public Map<String, List<MsgArgsPair>> validateResidentialUnitSize(Integer residentialUnitSize)
	{
		return (new FieldValidator()).clearChecks().setName("residentialUnitSize").setValidMinimum((double) 0).checkInteger(residentialUnitSize);
	}

	static public Map<String, List<MsgArgsPair>> validateResidentialUnitAreaUnitTypeIdk(Long residentialUnitAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("residentialUnitAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(residentialUnitAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateResidentialUnitSizeApproximated(Boolean residentialUnitSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("residentialUnitSizeApproximated").checkBoolean(residentialUnitSizeApproximated);
	}

	static public Map<String, List<MsgArgsPair>> validateCommercialUnitSize(Integer commercialUnitSize)
	{
		return (new FieldValidator()).clearChecks().setName("commercialUnitSize").setValidMinimum((double) 0).checkInteger(commercialUnitSize);
	}

	static public Map<String, List<MsgArgsPair>> validateCommercialUnitAreaUnitTypeIdk(Long commercialUnitAreaUnitTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("commercialUnitAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(commercialUnitAreaUnitTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCommercialUnitSizeApproximated(Boolean commercialUnitSizeApproximated)
	{
		return (new FieldValidator()).clearChecks().setName("commercialUnitSizeApproximated").checkBoolean(commercialUnitSizeApproximated);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalResidentialUnits(Short totalResidentialUnits)
	{
		return (new FieldValidator()).clearChecks().setName("totalResidentialUnits").setValidMinimum((double) 0).checkShort(totalResidentialUnits);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalCommercialUnits(Short totalCommercialUnits)
	{
		return (new FieldValidator()).clearChecks().setName("totalCommercialUnits").setValidMinimum((double) 0).checkShort(totalCommercialUnits);
	}

	static public Map<String, List<MsgArgsPair>> validateDescription(String description)
	{
		return (new FieldValidator()).clearChecks().setName("description").setValidMinimum((double) 0).setValidMaximum((double) 2000).checkString(description);
	}

	static public Map<String, List<MsgArgsPair>> validateUnitResidentialFeaturesIdk(Long unitResidentialFeaturesIdk)
	{
		return (new FieldValidator()).clearChecks().setName("unitResidentialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(unitResidentialFeaturesIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateUnitCommercialFeaturesIdk(Long unitCommercialFeaturesIdk)
	{
		return (new FieldValidator()).clearChecks().setName("unitCommercialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(unitCommercialFeaturesIdk);
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

		if (searchFields.get("landSizeMinimum") != null && searchFields.get("landSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"landSize\" >= " + searchFields.get("landSizeMinimum"), true);
		if (searchFields.get("landSizeMaximum") != null && searchFields.get("landSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"landSize\" <= " + searchFields.get("landSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check landAreaUnitTypeIdk by itself; only use it with landSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("residentialUnitSizeMinimum") != null && searchFields.get("residentialUnitSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"residentialUnitSize\" >= " + searchFields.get("residentialUnitSizeMinimum"), true);
		if (searchFields.get("residentialUnitSizeMaximum") != null && searchFields.get("residentialUnitSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"residentialUnitSize\" <= " + searchFields.get("residentialUnitSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check residentialUnitAreaUnitTypeIdk by itself; only use it with residentialUnitSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("commercialUnitSizeMinimum") != null && searchFields.get("commercialUnitSizeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"commercialUnitSize\" >= " + searchFields.get("commercialUnitSizeMinimum"), true);
		if (searchFields.get("commercialUnitSizeMaximum") != null && searchFields.get("commercialUnitSizeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"commercialUnitSize\" <= " + searchFields.get("commercialUnitSizeMaximum"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check commercialUnitAreaUnitTypeIdk by itself; only use it with commercialUnitSize
		// --- END CUSTOM CODE ---
		if (searchFields.get("totalResidentialUnitsMinimum") != null && searchFields.get("totalResidentialUnitsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalResidentialUnits\" >= " + searchFields.get("totalResidentialUnitsMinimum"), true);
		if (searchFields.get("totalResidentialUnitsMaximum") != null && searchFields.get("totalResidentialUnitsMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalResidentialUnits\" <= " + searchFields.get("totalResidentialUnitsMaximum"), true);
		if (searchFields.get("totalCommercialUnitsMinimum") != null && searchFields.get("totalCommercialUnitsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalCommercialUnits\" >= " + searchFields.get("totalCommercialUnitsMinimum"), true);
		if (searchFields.get("totalCommercialUnitsMaximum") != null && searchFields.get("totalCommercialUnitsMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"totalCommercialUnits\" <= " + searchFields.get("totalCommercialUnitsMaximum"), true);
		if (searchFields.get("incomeMinimum") != null && searchFields.get("incomeMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"income\" >= " + searchFields.get("incomeMinimum"), true);
		if (searchFields.get("incomeMaximum") != null && searchFields.get("incomeMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"income\" <= " + searchFields.get("incomeMaximum"), true);
		if (searchFields.get("currencyIdk") != null && searchFields.get("currencyIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"currencyIdk\" = " + searchFields.get("currencyIdk"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"landSize\"") private Integer landSize = null;
	@Basic @Column(name="\"landAreaUnitTypeIdk\"") private Long landAreaUnitTypeIdk = null;
	@Basic @Column(name="\"landSizeApproximated\"") private Boolean landSizeApproximated = null;
	@Basic @Column(name="\"residentialUnitSize\"") private Integer residentialUnitSize = null;
	@Basic @Column(name="\"residentialUnitAreaUnitTypeIdk\"") private Long residentialUnitAreaUnitTypeIdk = null;
	@Basic @Column(name="\"residentialUnitSizeApproximated\"") private Boolean residentialUnitSizeApproximated = null;
	@Basic @Column(name="\"commercialUnitSize\"") private Integer commercialUnitSize = null;
	@Basic @Column(name="\"commercialUnitAreaUnitTypeIdk\"") private Long commercialUnitAreaUnitTypeIdk = null;
	@Basic @Column(name="\"commercialUnitSizeApproximated\"") private Boolean commercialUnitSizeApproximated = null;
	@Basic @Column(name="\"totalResidentialUnits\"") private Short totalResidentialUnits = null;
	@Basic @Column(name="\"totalCommercialUnits\"") private Short totalCommercialUnits = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"unitResidentialFeaturesIdk\"") private Long unitResidentialFeaturesIdk = null;
	@Basic @Column(name="\"unitCommercialFeaturesIdk\"") private Long unitCommercialFeaturesIdk = null;
	@Basic @Column(name="\"income\"") private Integer income = null;
	@Basic @Column(name="\"currencyIdk\"") private Long currencyIdk = null;

	public MultiUnitCompound() {}
	public MultiUnitCompound(Map<String, Object> fields)
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

	public Integer getResidentialUnitSize()
	{
		return residentialUnitSize;
	}
	public void setResidentialUnitSize(Integer inputParameter)
	{
		modify();
		residentialUnitSize = inputParameter;
	}

	public Long getResidentialUnitAreaUnitTypeIdk()
	{
		return residentialUnitAreaUnitTypeIdk;
	}
	public void setResidentialUnitAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		residentialUnitAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getResidentialUnitSizeApproximated()
	{
		return residentialUnitSizeApproximated;
	}
	public void setResidentialUnitSizeApproximated(Boolean inputParameter)
	{
		modify();
		residentialUnitSizeApproximated = inputParameter;
	}

	public Integer getCommercialUnitSize()
	{
		return commercialUnitSize;
	}
	public void setCommercialUnitSize(Integer inputParameter)
	{
		modify();
		commercialUnitSize = inputParameter;
	}

	public Long getCommercialUnitAreaUnitTypeIdk()
	{
		return commercialUnitAreaUnitTypeIdk;
	}
	public void setCommercialUnitAreaUnitTypeIdk(Long inputParameter)
	{
		modify();
		commercialUnitAreaUnitTypeIdk = inputParameter;
	}

	public Boolean getCommercialUnitSizeApproximated()
	{
		return commercialUnitSizeApproximated;
	}
	public void setCommercialUnitSizeApproximated(Boolean inputParameter)
	{
		modify();
		commercialUnitSizeApproximated = inputParameter;
	}

	public Short getTotalResidentialUnits()
	{
		return totalResidentialUnits;
	}
	public void setTotalResidentialUnits(Short inputParameter)
	{
		modify();
		totalResidentialUnits = inputParameter;
	}

	public Short getTotalCommercialUnits()
	{
		return totalCommercialUnits;
	}
	public void setTotalCommercialUnits(Short inputParameter)
	{
		modify();
		totalCommercialUnits = inputParameter;
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

	public Long getUnitResidentialFeaturesIdk()
	{
		return unitResidentialFeaturesIdk;
	}
	public void setUnitResidentialFeaturesIdk(Long inputParameter)
	{
		modify();
		unitResidentialFeaturesIdk = inputParameter;
	}

	public Long getUnitCommercialFeaturesIdk()
	{
		return unitCommercialFeaturesIdk;
	}
	public void setUnitCommercialFeaturesIdk(Long inputParameter)
	{
		modify();
		unitCommercialFeaturesIdk = inputParameter;
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
		fields.put("landAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(landAreaUnitTypeIdk).get("name"));
		fields.put("residentialUnitAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(residentialUnitAreaUnitTypeIdk).get("name"));
		fields.put("commercialUnitAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(commercialUnitAreaUnitTypeIdk).get("name"));
		fields.put("currencyIdkMsg",
			(String) Currency.cacheLookup(currencyIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "propertyIdk", propertyIdk);
		collectField(format, dateFormat, fields, "landSize", landSize);
		collectField(format, dateFormat, fields, "landAreaUnitTypeIdk", landAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "landSizeApproximated", landSizeApproximated);
		collectField(format, dateFormat, fields, "residentialUnitSize", residentialUnitSize);
		collectField(format, dateFormat, fields, "residentialUnitAreaUnitTypeIdk", residentialUnitAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "residentialUnitSizeApproximated", residentialUnitSizeApproximated);
		collectField(format, dateFormat, fields, "commercialUnitSize", commercialUnitSize);
		collectField(format, dateFormat, fields, "commercialUnitAreaUnitTypeIdk", commercialUnitAreaUnitTypeIdk);
		collectField(format, dateFormat, fields, "commercialUnitSizeApproximated", commercialUnitSizeApproximated);
		collectField(format, dateFormat, fields, "totalResidentialUnits", totalResidentialUnits);
		collectField(format, dateFormat, fields, "totalCommercialUnits", totalCommercialUnits);
		collectField(format, dateFormat, fields, "description", description);
		collectField(format, dateFormat, fields, "unitResidentialFeaturesIdk", unitResidentialFeaturesIdk);
		collectField(format, dateFormat, fields, "unitCommercialFeaturesIdk", unitCommercialFeaturesIdk);
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
		if (fields.containsKey("landSize"))
			setLandSize( (Integer) fields.get("landSize") );
		if (fields.containsKey("landAreaUnitTypeIdk"))
			setLandAreaUnitTypeIdk( (Long) fields.get("landAreaUnitTypeIdk") );
		if (fields.containsKey("landSizeApproximated"))
			setLandSizeApproximated( (Boolean) fields.get("landSizeApproximated") );
		if (fields.containsKey("residentialUnitSize"))
			setResidentialUnitSize( (Integer) fields.get("residentialUnitSize") );
		if (fields.containsKey("residentialUnitAreaUnitTypeIdk"))
			setResidentialUnitAreaUnitTypeIdk( (Long) fields.get("residentialUnitAreaUnitTypeIdk") );
		if (fields.containsKey("residentialUnitSizeApproximated"))
			setResidentialUnitSizeApproximated( (Boolean) fields.get("residentialUnitSizeApproximated") );
		if (fields.containsKey("commercialUnitSize"))
			setCommercialUnitSize( (Integer) fields.get("commercialUnitSize") );
		if (fields.containsKey("commercialUnitAreaUnitTypeIdk"))
			setCommercialUnitAreaUnitTypeIdk( (Long) fields.get("commercialUnitAreaUnitTypeIdk") );
		if (fields.containsKey("commercialUnitSizeApproximated"))
			setCommercialUnitSizeApproximated( (Boolean) fields.get("commercialUnitSizeApproximated") );
		if (fields.containsKey("totalResidentialUnits"))
			setTotalResidentialUnits( ((Number) fields.get("totalResidentialUnits")).shortValue() );
		if (fields.containsKey("totalCommercialUnits"))
			setTotalCommercialUnits( ((Number) fields.get("totalCommercialUnits")).shortValue() );
		if (fields.containsKey("description"))
			setDescription( (String) fields.get("description") );
		if (fields.containsKey("unitResidentialFeaturesIdk"))
			setUnitResidentialFeaturesIdk( (Long) fields.get("unitResidentialFeaturesIdk") );
		if (fields.containsKey("unitCommercialFeaturesIdk"))
			setUnitCommercialFeaturesIdk( (Long) fields.get("unitCommercialFeaturesIdk") );
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
				(specificFields != null && !exclude && specificFields.contains("residentialUnitSize")) ||
				(specificFields != null && exclude && !specificFields.contains("residentialUnitSize")))
			{
				result = 
					validator.clearChecks().setName("residentialUnitSize").setValidMinimum((double) 0).checkInteger(residentialUnitSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("residentialUnitAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("residentialUnitAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("residentialUnitAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(residentialUnitAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("residentialUnitSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("residentialUnitSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("residentialUnitSizeApproximated").checkBoolean(residentialUnitSizeApproximated);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("commercialUnitSize")) ||
				(specificFields != null && exclude && !specificFields.contains("commercialUnitSize")))
			{
				result = 
					validator.clearChecks().setName("commercialUnitSize").setValidMinimum((double) 0).checkInteger(commercialUnitSize);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("commercialUnitAreaUnitTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("commercialUnitAreaUnitTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("commercialUnitAreaUnitTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(commercialUnitAreaUnitTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("commercialUnitSizeApproximated")) ||
				(specificFields != null && exclude && !specificFields.contains("commercialUnitSizeApproximated")))
			{
				result = 
					validator.clearChecks().setName("commercialUnitSizeApproximated").checkBoolean(commercialUnitSizeApproximated);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalResidentialUnits")) ||
				(specificFields != null && exclude && !specificFields.contains("totalResidentialUnits")))
			{
				result = 
					validator.clearChecks().setName("totalResidentialUnits").setValidMinimum((double) 0).checkShort(totalResidentialUnits);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalCommercialUnits")) ||
				(specificFields != null && exclude && !specificFields.contains("totalCommercialUnits")))
			{
				result = 
					validator.clearChecks().setName("totalCommercialUnits").setValidMinimum((double) 0).checkShort(totalCommercialUnits);
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
				(specificFields != null && !exclude && specificFields.contains("unitResidentialFeaturesIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("unitResidentialFeaturesIdk")))
			{
				result = 
					validator.clearChecks().setName("unitResidentialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(unitResidentialFeaturesIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("unitCommercialFeaturesIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("unitCommercialFeaturesIdk")))
			{
				result = 
					validator.clearChecks().setName("unitCommercialFeaturesIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(unitCommercialFeaturesIdk);
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

