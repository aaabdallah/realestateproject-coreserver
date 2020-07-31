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
			name = "List<FarmLot>",
			entities =
			{
				@EntityResult
				(
					entityClass = FarmLot.class,
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
						@FieldResult(name = "pavedRoadAccessible", column = "pavedRoadAccessible"),
						@FieldResult(name = "electricity", column = "electricity"),
						@FieldResult(name = "water", column = "water"),
						@FieldResult(name = "phoneConnection", column = "phoneConnection"),
						@FieldResult(name = "postalService", column = "postalService"),
						@FieldResult(name = "irrigationTypeIdk", column = "irrigationTypeIdk"),
						@FieldResult(name = "irrigationOperational", column = "irrigationOperational"),
						@FieldResult(name = "planted", column = "planted"),
						@FieldResult(name = "suitableCrops", column = "suitableCrops"),
						@FieldResult(name = "soilComposition", column = "soilComposition"),
						@FieldResult(name = "requiresExtensiveClearing", column = "requiresExtensiveClearing"),
						@FieldResult(name = "income", column = "income"),
						@FieldResult(name = "currencyIdk", column = "currencyIdk"),
						@FieldResult(name = "description", column = "description")
					}
				)
			}
		)
	}
)
@Entity(name="FarmLot") @Table(name="\"tFarmLots\"")
public class FarmLot extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tFarmLots\"";

	static public FarmLot findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (FarmLot) BaseEntity.findByIdk(manager, FarmLot.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findFarmLotByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<FarmLot>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findFarmLotByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<FarmLot>");
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

	static public Map<String, List<MsgArgsPair>> validateFarmLot(FarmLot farmLot,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return farmLot.validateAllFields(userSuppliedIdk, specificFields, exclude);
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

	static public Map<String, List<MsgArgsPair>> validatePavedRoadAccessible(Boolean pavedRoadAccessible)
	{
		return (new FieldValidator()).clearChecks().setName("pavedRoadAccessible").checkBoolean(pavedRoadAccessible);
	}

	static public Map<String, List<MsgArgsPair>> validateElectricity(Boolean electricity)
	{
		return (new FieldValidator()).clearChecks().setName("electricity").checkBoolean(electricity);
	}

	static public Map<String, List<MsgArgsPair>> validateWater(Boolean water)
	{
		return (new FieldValidator()).clearChecks().setName("water").checkBoolean(water);
	}

	static public Map<String, List<MsgArgsPair>> validatePhoneConnection(Boolean phoneConnection)
	{
		return (new FieldValidator()).clearChecks().setName("phoneConnection").checkBoolean(phoneConnection);
	}

	static public Map<String, List<MsgArgsPair>> validatePostalService(Boolean postalService)
	{
		return (new FieldValidator()).clearChecks().setName("postalService").checkBoolean(postalService);
	}

	static public Map<String, List<MsgArgsPair>> validateIrrigationTypeIdk(Long irrigationTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("irrigationTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(irrigationTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateIrrigationOperational(Boolean irrigationOperational)
	{
		return (new FieldValidator()).clearChecks().setName("irrigationOperational").checkBoolean(irrigationOperational);
	}

	static public Map<String, List<MsgArgsPair>> validatePlanted(Boolean planted)
	{
		return (new FieldValidator()).clearChecks().setName("planted").checkBoolean(planted);
	}

	static public Map<String, List<MsgArgsPair>> validateSuitableCrops(String suitableCrops)
	{
		return (new FieldValidator()).clearChecks().setName("suitableCrops").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(suitableCrops);
	}

	static public Map<String, List<MsgArgsPair>> validateSoilComposition(String soilComposition)
	{
		return (new FieldValidator()).clearChecks().setName("soilComposition").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(soilComposition);
	}

	static public Map<String, List<MsgArgsPair>> validateRequiresExtensiveClearing(Boolean requiresExtensiveClearing)
	{
		return (new FieldValidator()).clearChecks().setName("requiresExtensiveClearing").checkBoolean(requiresExtensiveClearing);
	}

	static public Map<String, List<MsgArgsPair>> validateIncome(Integer income)
	{
		return (new FieldValidator()).clearChecks().setName("income").setValidMinimum((double) 0).checkInteger(income);
	}

	static public Map<String, List<MsgArgsPair>> validateCurrencyIdk(Long currencyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("currencyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(currencyIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateDescription(String description)
	{
		return (new FieldValidator()).clearChecks().setName("description").setValidMinimum((double) 0).setValidMaximum((double) 2000).checkString(description);
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
		if (searchFields.get("electricity") != null && searchFields.get("electricity").toString().length() > 0 &&
			searchFields.get("electricity").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"electricity\" = true", true);
		if (searchFields.get("water") != null && searchFields.get("water").toString().length() > 0 &&
			searchFields.get("water").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"water\" = true", true);
		if (searchFields.get("phoneConnection") != null && searchFields.get("phoneConnection").toString().length() > 0 &&
			searchFields.get("phoneConnection").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"phoneConnection\" = true", true);
		if (searchFields.get("irrigationTypeIdk") != null && ((Long[]) searchFields.get("irrigationTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"irrigationTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("irrigationTypeIdk"), "", "") + ")", true);
		if (searchFields.get("irrigationOperational") != null && searchFields.get("irrigationOperational").toString().length() > 0 &&
			searchFields.get("irrigationOperational").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"irrigationOperational\" = true", true);
		if (searchFields.get("planted") != null && searchFields.get("planted").toString().length() > 0 &&
			searchFields.get("planted").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"planted\" = true", true);
		if (searchFields.get("suitableCrops") != null && searchFields.get("suitableCrops").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"suitableCrops\" LIKE $$%" + searchFields.get("suitableCrops") + "%$$", true);
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
	@Basic @Column(name="\"pavedRoadAccessible\"") private Boolean pavedRoadAccessible = null;
	@Basic @Column(name="\"electricity\"") private Boolean electricity = null;
	@Basic @Column(name="\"water\"") private Boolean water = null;
	@Basic @Column(name="\"phoneConnection\"") private Boolean phoneConnection = null;
	@Basic @Column(name="\"postalService\"") private Boolean postalService = null;
	@Basic @Column(name="\"irrigationTypeIdk\"") private Long irrigationTypeIdk = null;
	@Basic @Column(name="\"irrigationOperational\"") private Boolean irrigationOperational = null;
	@Basic @Column(name="\"planted\"") private Boolean planted = null;
	@Basic @Column(name="\"suitableCrops\"") private String suitableCrops = null;
	@Basic @Column(name="\"soilComposition\"") private String soilComposition = null;
	@Basic @Column(name="\"requiresExtensiveClearing\"") private Boolean requiresExtensiveClearing = null;
	@Basic @Column(name="\"income\"") private Integer income = null;
	@Basic @Column(name="\"currencyIdk\"") private Long currencyIdk = null;
	@Basic @Column(name="\"description\"") private String description = null;

	public FarmLot() {}
	public FarmLot(Map<String, Object> fields)
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

	public Boolean getPavedRoadAccessible()
	{
		return pavedRoadAccessible;
	}
	public void setPavedRoadAccessible(Boolean inputParameter)
	{
		modify();
		pavedRoadAccessible = inputParameter;
	}

	public Boolean getElectricity()
	{
		return electricity;
	}
	public void setElectricity(Boolean inputParameter)
	{
		modify();
		electricity = inputParameter;
	}

	public Boolean getWater()
	{
		return water;
	}
	public void setWater(Boolean inputParameter)
	{
		modify();
		water = inputParameter;
	}

	public Boolean getPhoneConnection()
	{
		return phoneConnection;
	}
	public void setPhoneConnection(Boolean inputParameter)
	{
		modify();
		phoneConnection = inputParameter;
	}

	public Boolean getPostalService()
	{
		return postalService;
	}
	public void setPostalService(Boolean inputParameter)
	{
		modify();
		postalService = inputParameter;
	}

	public Long getIrrigationTypeIdk()
	{
		return irrigationTypeIdk;
	}
	public void setIrrigationTypeIdk(Long inputParameter)
	{
		modify();
		irrigationTypeIdk = inputParameter;
	}

	public Boolean getIrrigationOperational()
	{
		return irrigationOperational;
	}
	public void setIrrigationOperational(Boolean inputParameter)
	{
		modify();
		irrigationOperational = inputParameter;
	}

	public Boolean getPlanted()
	{
		return planted;
	}
	public void setPlanted(Boolean inputParameter)
	{
		modify();
		planted = inputParameter;
	}

	public String getSuitableCrops()
	{
		return suitableCrops;
	}
	public void setSuitableCrops(String inputParameter)
	{
		modify();
		suitableCrops = inputParameter;
	}

	public String getSoilComposition()
	{
		return soilComposition;
	}
	public void setSoilComposition(String inputParameter)
	{
		modify();
		soilComposition = inputParameter;
	}

	public Boolean getRequiresExtensiveClearing()
	{
		return requiresExtensiveClearing;
	}
	public void setRequiresExtensiveClearing(Boolean inputParameter)
	{
		modify();
		requiresExtensiveClearing = inputParameter;
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

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String inputParameter)
	{
		modify();
		description = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("landAreaUnitTypeIdkMsg",
			(String) PropertyOption.cacheLookup(landAreaUnitTypeIdk).get("name"));
		fields.put("irrigationTypeIdkMsg",
			(String) PropertyOption.cacheLookup(irrigationTypeIdk).get("name"));
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
		collectField(format, dateFormat, fields, "pavedRoadAccessible", pavedRoadAccessible);
		collectField(format, dateFormat, fields, "electricity", electricity);
		collectField(format, dateFormat, fields, "water", water);
		collectField(format, dateFormat, fields, "phoneConnection", phoneConnection);
		collectField(format, dateFormat, fields, "postalService", postalService);
		collectField(format, dateFormat, fields, "irrigationTypeIdk", irrigationTypeIdk);
		collectField(format, dateFormat, fields, "irrigationOperational", irrigationOperational);
		collectField(format, dateFormat, fields, "planted", planted);
		collectField(format, dateFormat, fields, "suitableCrops", suitableCrops);
		collectField(format, dateFormat, fields, "soilComposition", soilComposition);
		collectField(format, dateFormat, fields, "requiresExtensiveClearing", requiresExtensiveClearing);
		collectField(format, dateFormat, fields, "income", income);
		collectField(format, dateFormat, fields, "currencyIdk", currencyIdk);
		collectField(format, dateFormat, fields, "description", description);
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
		if (fields.containsKey("pavedRoadAccessible"))
			setPavedRoadAccessible( (Boolean) fields.get("pavedRoadAccessible") );
		if (fields.containsKey("electricity"))
			setElectricity( (Boolean) fields.get("electricity") );
		if (fields.containsKey("water"))
			setWater( (Boolean) fields.get("water") );
		if (fields.containsKey("phoneConnection"))
			setPhoneConnection( (Boolean) fields.get("phoneConnection") );
		if (fields.containsKey("postalService"))
			setPostalService( (Boolean) fields.get("postalService") );
		if (fields.containsKey("irrigationTypeIdk"))
			setIrrigationTypeIdk( (Long) fields.get("irrigationTypeIdk") );
		if (fields.containsKey("irrigationOperational"))
			setIrrigationOperational( (Boolean) fields.get("irrigationOperational") );
		if (fields.containsKey("planted"))
			setPlanted( (Boolean) fields.get("planted") );
		if (fields.containsKey("suitableCrops"))
			setSuitableCrops( (String) fields.get("suitableCrops") );
		if (fields.containsKey("soilComposition"))
			setSoilComposition( (String) fields.get("soilComposition") );
		if (fields.containsKey("requiresExtensiveClearing"))
			setRequiresExtensiveClearing( (Boolean) fields.get("requiresExtensiveClearing") );
		if (fields.containsKey("income"))
			setIncome( (Integer) fields.get("income") );
		if (fields.containsKey("currencyIdk"))
			setCurrencyIdk( (Long) fields.get("currencyIdk") );
		if (fields.containsKey("description"))
			setDescription( (String) fields.get("description") );
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
				(specificFields != null && !exclude && specificFields.contains("pavedRoadAccessible")) ||
				(specificFields != null && exclude && !specificFields.contains("pavedRoadAccessible")))
			{
				result = 
					validator.clearChecks().setName("pavedRoadAccessible").checkBoolean(pavedRoadAccessible);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("electricity")) ||
				(specificFields != null && exclude && !specificFields.contains("electricity")))
			{
				result = 
					validator.clearChecks().setName("electricity").checkBoolean(electricity);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("water")) ||
				(specificFields != null && exclude && !specificFields.contains("water")))
			{
				result = 
					validator.clearChecks().setName("water").checkBoolean(water);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("phoneConnection")) ||
				(specificFields != null && exclude && !specificFields.contains("phoneConnection")))
			{
				result = 
					validator.clearChecks().setName("phoneConnection").checkBoolean(phoneConnection);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("postalService")) ||
				(specificFields != null && exclude && !specificFields.contains("postalService")))
			{
				result = 
					validator.clearChecks().setName("postalService").checkBoolean(postalService);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("irrigationTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("irrigationTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("irrigationTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(irrigationTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("irrigationOperational")) ||
				(specificFields != null && exclude && !specificFields.contains("irrigationOperational")))
			{
				result = 
					validator.clearChecks().setName("irrigationOperational").checkBoolean(irrigationOperational);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("planted")) ||
				(specificFields != null && exclude && !specificFields.contains("planted")))
			{
				result = 
					validator.clearChecks().setName("planted").checkBoolean(planted);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("suitableCrops")) ||
				(specificFields != null && exclude && !specificFields.contains("suitableCrops")))
			{
				result = 
					validator.clearChecks().setName("suitableCrops").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(suitableCrops);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("soilComposition")) ||
				(specificFields != null && exclude && !specificFields.contains("soilComposition")))
			{
				result = 
					validator.clearChecks().setName("soilComposition").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(soilComposition);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("requiresExtensiveClearing")) ||
				(specificFields != null && exclude && !specificFields.contains("requiresExtensiveClearing")))
			{
				result = 
					validator.clearChecks().setName("requiresExtensiveClearing").checkBoolean(requiresExtensiveClearing);
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

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("description")) ||
				(specificFields != null && exclude && !specificFields.contains("description")))
			{
				result = 
					validator.clearChecks().setName("description").setValidMinimum((double) 0).setValidMaximum((double) 2000).checkString(description);
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

