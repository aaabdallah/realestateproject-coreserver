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
			name = "List<Rental>",
			entities =
			{
				@EntityResult
				(
					entityClass = Rental.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "periodMonths", column = "periodMonths"),
						@FieldResult(name = "rentForPeriod", column = "rentForPeriod"),
						@FieldResult(name = "minimumRenewalTermMonths", column = "minimumRenewalTermMonths"),
						@FieldResult(name = "securityDeposit", column = "securityDeposit"),
						@FieldResult(name = "currencyIdk", column = "currencyIdk")
					}
				)
			}
		)
	}
)
@Entity(name="Rental") @Table(name="\"tRentals\"")
public class Rental extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tRentals\"";

	static public Rental findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Rental) BaseEntity.findByIdk(manager, Rental.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findRentalByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Rental>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findRentalByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Rental>");
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

	static public Map<String, List<MsgArgsPair>> validateRental(Rental rental,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return rental.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePeriodMonths(Short periodMonths)
	{
		return (new FieldValidator()).clearChecks().setName("periodMonths").setAllowNullElement(false).setValidMinimum((double) 1).checkShort(periodMonths);
	}

	static public Map<String, List<MsgArgsPair>> validateRentForPeriod(Float rentForPeriod)
	{
		return (new FieldValidator()).clearChecks().setName("rentForPeriod").setMaxFracLength(2).checkFloat(rentForPeriod);
	}

	static public Map<String, List<MsgArgsPair>> validateMinimumRenewalTermMonths(Short minimumRenewalTermMonths)
	{
		return (new FieldValidator()).clearChecks().setName("minimumRenewalTermMonths").setValidMinimum((double) 1).checkShort(minimumRenewalTermMonths);
	}

	static public Map<String, List<MsgArgsPair>> validateSecurityDeposit(Float securityDeposit)
	{
		return (new FieldValidator()).clearChecks().setName("securityDeposit").setMaxFracLength(2).checkFloat(securityDeposit);
	}

	static public Map<String, List<MsgArgsPair>> validateCurrencyIdk(Long currencyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("currencyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(currencyIdk);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		// --- BEGIN CUSTOM CODE ---
		// Don't check periodMonths by itself; only use it with rentForPeriod
		// --- END CUSTOM CODE ---
		// --- BEGIN CUSTOM CODE ---
		if (searchFields.get("periodMonths") != null && searchFields.get("periodMonths").toString().length() > 0)
		{
			// multiply by 0.95 to allow for floating point rounding errors
			if (searchFields.get("rentForPeriodMinimum") != null && searchFields.get("rentForPeriodMinimum").toString().length() > 0)
				selectBuilder.addWhere(
					"((" + searchFields.get("rentForPeriodMinimum") + "/" + searchFields.get("periodMonths") + ") * 0.95) " +
					"<= (" + entityTableName + ".\"rentForPeriod\" / " + entityTableName + ".\"periodMonths\")", true); 
			// multiply by 1.05 to allow for floating point rounding errors
			if (searchFields.get("rentForPeriodMaximum") != null && searchFields.get("rentForPeriodMaximum").toString().length() > 0)
				selectBuilder.addWhere(
					"(" + entityTableName + ".\"rentForPeriod\" / " + entityTableName + ".\"periodMonths\") " +
					"<= ((" + searchFields.get("rentForPeriodMaximum") + "/" + searchFields.get("periodMonths") + ") * 1.05)", true);
		}
		// --- END CUSTOM CODE ---
		if (searchFields.get("currencyIdk") != null && searchFields.get("currencyIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"currencyIdk\" = " + searchFields.get("currencyIdk"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"periodMonths\"") private Short periodMonths = null;
	@Basic @Column(name="\"rentForPeriod\"") private Float rentForPeriod = null;
	@Basic @Column(name="\"minimumRenewalTermMonths\"") private Short minimumRenewalTermMonths = null;
	@Basic @Column(name="\"securityDeposit\"") private Float securityDeposit = null;
	@Basic @Column(name="\"currencyIdk\"") private Long currencyIdk = null;

	public Rental() {}
	public Rental(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Short getPeriodMonths()
	{
		return periodMonths;
	}
	public void setPeriodMonths(Short inputParameter)
	{
		modify();
		periodMonths = inputParameter;
	}

	public Float getRentForPeriod()
	{
		return rentForPeriod;
	}
	public void setRentForPeriod(Float inputParameter)
	{
		modify();
		rentForPeriod = inputParameter;
	}

	public Short getMinimumRenewalTermMonths()
	{
		return minimumRenewalTermMonths;
	}
	public void setMinimumRenewalTermMonths(Short inputParameter)
	{
		modify();
		minimumRenewalTermMonths = inputParameter;
	}

	public Float getSecurityDeposit()
	{
		return securityDeposit;
	}
	public void setSecurityDeposit(Float inputParameter)
	{
		modify();
		securityDeposit = inputParameter;
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
		fields.put("currencyIdkMsg",
			(String) Currency.cacheLookup(currencyIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "periodMonths", periodMonths);
		collectField(format, dateFormat, fields, "rentForPeriod", rentForPeriod);
		collectField(format, dateFormat, fields, "minimumRenewalTermMonths", minimumRenewalTermMonths);
		collectField(format, dateFormat, fields, "securityDeposit", securityDeposit);
		collectField(format, dateFormat, fields, "currencyIdk", currencyIdk);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("periodMonths"))
			setPeriodMonths( ((Number) fields.get("periodMonths")).shortValue() );
		if (fields.containsKey("rentForPeriod"))
			setRentForPeriod( (Float) fields.get("rentForPeriod") );
		if (fields.containsKey("minimumRenewalTermMonths"))
			setMinimumRenewalTermMonths( ((Number) fields.get("minimumRenewalTermMonths")).shortValue() );
		if (fields.containsKey("securityDeposit"))
			setSecurityDeposit( (Float) fields.get("securityDeposit") );
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
				(specificFields != null && !exclude && specificFields.contains("periodMonths")) ||
				(specificFields != null && exclude && !specificFields.contains("periodMonths")))
			{
				result = 
					validator.clearChecks().setName("periodMonths").setAllowNullElement(false).setValidMinimum((double) 1).checkShort(periodMonths);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("rentForPeriod")) ||
				(specificFields != null && exclude && !specificFields.contains("rentForPeriod")))
			{
				result = 
					validator.clearChecks().setName("rentForPeriod").setMaxFracLength(2).checkFloat(rentForPeriod);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("minimumRenewalTermMonths")) ||
				(specificFields != null && exclude && !specificFields.contains("minimumRenewalTermMonths")))
			{
				result = 
					validator.clearChecks().setName("minimumRenewalTermMonths").setValidMinimum((double) 1).checkShort(minimumRenewalTermMonths);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("securityDeposit")) ||
				(specificFields != null && exclude && !specificFields.contains("securityDeposit")))
			{
				result = 
					validator.clearChecks().setName("securityDeposit").setMaxFracLength(2).checkFloat(securityDeposit);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("currencyIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("currencyIdk")))
			{
				result = 
					validator.clearChecks().setName("currencyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(currencyIdk);
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

