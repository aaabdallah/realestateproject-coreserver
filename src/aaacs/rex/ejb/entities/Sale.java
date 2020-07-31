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
			name = "List<Sale>",
			entities =
			{
				@EntityResult
				(
					entityClass = Sale.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "priceTypeIdk", column = "priceTypeIdk"),
						@FieldResult(name = "bidTypeIdk", column = "bidTypeIdk"),
						@FieldResult(name = "cashPrice", column = "cashPrice"),
						@FieldResult(name = "bid", column = "bid"),
						@FieldResult(name = "pricePerTypeIdk", column = "pricePerTypeIdk"),
						@FieldResult(name = "installmentsAcceptable", column = "installmentsAcceptable"),
						@FieldResult(name = "installmentsPrice", column = "installmentsPrice"),
						@FieldResult(name = "downPayment", column = "downPayment"),
						@FieldResult(name = "currencyIdk", column = "currencyIdk")
					}
				)
			}
		)
	}
)
@Entity(name="Sale") @Table(name="\"tSales\"")
public class Sale extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tSales\"";

	static public Sale findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Sale) BaseEntity.findByIdk(manager, Sale.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findSaleByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Sale>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findSaleByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Sale>");
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

	static public Map<String, List<MsgArgsPair>> validateSale(Sale sale,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return sale.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validatePriceTypeIdk(Long priceTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("priceTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(priceTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateBidTypeIdk(Long bidTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("bidTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(bidTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCashPrice(Float cashPrice)
	{
		return (new FieldValidator()).clearChecks().setName("cashPrice").setMaxFracLength(2).checkFloat(cashPrice);
	}

	static public Map<String, List<MsgArgsPair>> validateBid(Float bid)
	{
		return (new FieldValidator()).clearChecks().setName("bid").setMaxFracLength(2).checkFloat(bid);
	}

	static public Map<String, List<MsgArgsPair>> validatePricePerTypeIdk(Long pricePerTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("pricePerTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(pricePerTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateInstallmentsAcceptable(Boolean installmentsAcceptable)
	{
		return (new FieldValidator()).clearChecks().setName("installmentsAcceptable").checkBoolean(installmentsAcceptable);
	}

	static public Map<String, List<MsgArgsPair>> validateInstallmentsPrice(Float installmentsPrice)
	{
		return (new FieldValidator()).clearChecks().setName("installmentsPrice").setMaxFracLength(2).checkFloat(installmentsPrice);
	}

	static public Map<String, List<MsgArgsPair>> validateDownPayment(Float downPayment)
	{
		return (new FieldValidator()).clearChecks().setName("downPayment").setMaxFracLength(2).checkFloat(downPayment);
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

		if (searchFields.get("priceTypeIdk") != null && searchFields.get("priceTypeIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"priceTypeIdk\" = " + searchFields.get("priceTypeIdk"), true);
		if (searchFields.get("bidTypeIdk") != null && searchFields.get("bidTypeIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"bidTypeIdk\" = " + searchFields.get("bidTypeIdk"), true);
		// --- BEGIN CUSTOM CODE ---
		// Don't check pricePerTypeIdk: needs to be done in conjunction with cashPrice.
		// --- END CUSTOM CODE ---
		// --- BEGIN CUSTOM CODE ---
		// Don't check pricePerTypeIdk: needs to be done in conjunction with cashPrice.
		// --- END CUSTOM CODE ---
		// --- BEGIN CUSTOM CODE ---
		// Don't check pricePerTypeIdk: needs to be done in conjunction with cashPrice.
		// --- END CUSTOM CODE ---
		if (searchFields.get("installmentsAcceptable") != null && searchFields.get("installmentsAcceptable").toString().length() > 0 &&
			searchFields.get("installmentsAcceptable").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"installmentsAcceptable\" = true", true);
		if (searchFields.get("currencyIdk") != null && searchFields.get("currencyIdk").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"currencyIdk\" = " + searchFields.get("currencyIdk"), true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"priceTypeIdk\"") private Long priceTypeIdk = null;
	@Basic @Column(name="\"bidTypeIdk\"") private Long bidTypeIdk = null;
	@Basic @Column(name="\"cashPrice\"") private Float cashPrice = null;
	@Basic @Column(name="\"bid\"") private Float bid = null;
	@Basic @Column(name="\"pricePerTypeIdk\"") private Long pricePerTypeIdk = null;
	@Basic @Column(name="\"installmentsAcceptable\"") private Boolean installmentsAcceptable = null;
	@Basic @Column(name="\"installmentsPrice\"") private Float installmentsPrice = null;
	@Basic @Column(name="\"downPayment\"") private Float downPayment = null;
	@Basic @Column(name="\"currencyIdk\"") private Long currencyIdk = null;

	public Sale() {}
	public Sale(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Long getPriceTypeIdk()
	{
		return priceTypeIdk;
	}
	public void setPriceTypeIdk(Long inputParameter)
	{
		modify();
		priceTypeIdk = inputParameter;
	}

	public Long getBidTypeIdk()
	{
		return bidTypeIdk;
	}
	public void setBidTypeIdk(Long inputParameter)
	{
		modify();
		bidTypeIdk = inputParameter;
	}

	public Float getCashPrice()
	{
		return cashPrice;
	}
	public void setCashPrice(Float inputParameter)
	{
		modify();
		cashPrice = inputParameter;
	}

	public Float getBid()
	{
		return bid;
	}
	public void setBid(Float inputParameter)
	{
		modify();
		bid = inputParameter;
	}

	public Long getPricePerTypeIdk()
	{
		return pricePerTypeIdk;
	}
	public void setPricePerTypeIdk(Long inputParameter)
	{
		modify();
		pricePerTypeIdk = inputParameter;
	}

	public Boolean getInstallmentsAcceptable()
	{
		return installmentsAcceptable;
	}
	public void setInstallmentsAcceptable(Boolean inputParameter)
	{
		modify();
		installmentsAcceptable = inputParameter;
	}

	public Float getInstallmentsPrice()
	{
		return installmentsPrice;
	}
	public void setInstallmentsPrice(Float inputParameter)
	{
		modify();
		installmentsPrice = inputParameter;
	}

	public Float getDownPayment()
	{
		return downPayment;
	}
	public void setDownPayment(Float inputParameter)
	{
		modify();
		downPayment = inputParameter;
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
		fields.put("priceTypeIdkMsg",
			(String) PropertyOption.cacheLookup(priceTypeIdk).get("name"));
		fields.put("bidTypeIdkMsg",
			(String) PropertyOption.cacheLookup(bidTypeIdk).get("name"));
		fields.put("pricePerTypeIdkMsg",
			(String) PropertyOption.cacheLookup(pricePerTypeIdk).get("name"));
		fields.put("currencyIdkMsg",
			(String) Currency.cacheLookup(currencyIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "priceTypeIdk", priceTypeIdk);
		collectField(format, dateFormat, fields, "bidTypeIdk", bidTypeIdk);
		collectField(format, dateFormat, fields, "cashPrice", cashPrice);
		collectField(format, dateFormat, fields, "bid", bid);
		collectField(format, dateFormat, fields, "pricePerTypeIdk", pricePerTypeIdk);
		collectField(format, dateFormat, fields, "installmentsAcceptable", installmentsAcceptable);
		collectField(format, dateFormat, fields, "installmentsPrice", installmentsPrice);
		collectField(format, dateFormat, fields, "downPayment", downPayment);
		collectField(format, dateFormat, fields, "currencyIdk", currencyIdk);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("priceTypeIdk"))
			setPriceTypeIdk( (Long) fields.get("priceTypeIdk") );
		if (fields.containsKey("bidTypeIdk"))
			setBidTypeIdk( (Long) fields.get("bidTypeIdk") );
		if (fields.containsKey("cashPrice"))
			setCashPrice( (Float) fields.get("cashPrice") );
		if (fields.containsKey("bid"))
			setBid( (Float) fields.get("bid") );
		if (fields.containsKey("pricePerTypeIdk"))
			setPricePerTypeIdk( (Long) fields.get("pricePerTypeIdk") );
		if (fields.containsKey("installmentsAcceptable"))
			setInstallmentsAcceptable( (Boolean) fields.get("installmentsAcceptable") );
		if (fields.containsKey("installmentsPrice"))
			setInstallmentsPrice( (Float) fields.get("installmentsPrice") );
		if (fields.containsKey("downPayment"))
			setDownPayment( (Float) fields.get("downPayment") );
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
				(specificFields != null && !exclude && specificFields.contains("priceTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("priceTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("priceTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(priceTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("bidTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("bidTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("bidTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(bidTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("cashPrice")) ||
				(specificFields != null && exclude && !specificFields.contains("cashPrice")))
			{
				result = 
					validator.clearChecks().setName("cashPrice").setMaxFracLength(2).checkFloat(cashPrice);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("bid")) ||
				(specificFields != null && exclude && !specificFields.contains("bid")))
			{
				result = 
					validator.clearChecks().setName("bid").setMaxFracLength(2).checkFloat(bid);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("pricePerTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("pricePerTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("pricePerTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(pricePerTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("installmentsAcceptable")) ||
				(specificFields != null && exclude && !specificFields.contains("installmentsAcceptable")))
			{
				result = 
					validator.clearChecks().setName("installmentsAcceptable").checkBoolean(installmentsAcceptable);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("installmentsPrice")) ||
				(specificFields != null && exclude && !specificFields.contains("installmentsPrice")))
			{
				result = 
					validator.clearChecks().setName("installmentsPrice").setMaxFracLength(2).checkFloat(installmentsPrice);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("downPayment")) ||
				(specificFields != null && exclude && !specificFields.contains("downPayment")))
			{
				result = 
					validator.clearChecks().setName("downPayment").setMaxFracLength(2).checkFloat(downPayment);
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

