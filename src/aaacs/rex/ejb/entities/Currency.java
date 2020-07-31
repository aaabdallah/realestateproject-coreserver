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

import aaacs.coreserver.ejb.entities.support.CacheManager;

@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<Currency>",
			entities =
			{
				@EntityResult
				(
					entityClass = Currency.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "name", column = "name"),
						@FieldResult(name = "plural", column = "plural"),
						@FieldResult(name = "symbol", column = "symbol"),
						@FieldResult(name = "prefix", column = "prefix"),
						@FieldResult(name = "suffix", column = "suffix")
					}
				)
			}
		)
	}
)
@Entity(name="Currency") @Table(name="\"tCurrencies\"")
public class Currency extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tCurrencies\"";

	static public Currency findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Currency) BaseEntity.findByIdk(manager, Currency.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findCurrencyByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Currency>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findCurrencyByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Currency>");
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

	static public Map<String, Object> cacheLookup(long inIdk)
	{
		try
		{
			return CacheManager.findByIdk(entityTableName,
				inIdk);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	static public Map<Long, Map<String, Object>> cacheLookup(
		Map<String, Object> searchParameters)
	{
		try
		{
			return CacheManager.findByFields(entityTableName,
				searchParameters, 0);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	static public Map<String, List<MsgArgsPair>> validateCurrency(Currency currency,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return currency.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateName(String name)
	{
		return (new FieldValidator()).clearChecks().setName("name").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(name);
	}

	static public Map<String, List<MsgArgsPair>> validatePlural(String plural)
	{
		return (new FieldValidator()).clearChecks().setName("plural").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(plural);
	}

	static public Map<String, List<MsgArgsPair>> validateSymbol(String symbol)
	{
		return (new FieldValidator()).clearChecks().setName("symbol").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(symbol);
	}

	static public Map<String, List<MsgArgsPair>> validatePrefix(String prefix)
	{
		return (new FieldValidator()).clearChecks().setName("prefix").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(prefix);
	}

	static public Map<String, List<MsgArgsPair>> validateSuffix(String suffix)
	{
		return (new FieldValidator()).clearChecks().setName("suffix").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(suffix);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"name\"") private String name = null;
	@Basic @Column(name="\"plural\"") private String plural = null;
	@Basic @Column(name="\"symbol\"") private String symbol = null;
	@Basic @Column(name="\"prefix\"") private String prefix = null;
	@Basic @Column(name="\"suffix\"") private String suffix = null;

	public Currency() {}
	public Currency(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public String getName()
	{
		return name;
	}
	public void setName(String inputParameter)
	{
		modify();
		name = inputParameter;
	}

	public String getPlural()
	{
		return plural;
	}
	public void setPlural(String inputParameter)
	{
		modify();
		plural = inputParameter;
	}

	public String getSymbol()
	{
		return symbol;
	}
	public void setSymbol(String inputParameter)
	{
		modify();
		symbol = inputParameter;
	}

	public String getPrefix()
	{
		return prefix;
	}
	public void setPrefix(String inputParameter)
	{
		modify();
		prefix = inputParameter;
	}

	public String getSuffix()
	{
		return suffix;
	}
	public void setSuffix(String inputParameter)
	{
		modify();
		suffix = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "name", name);
		collectField(format, dateFormat, fields, "plural", plural);
		collectField(format, dateFormat, fields, "symbol", symbol);
		collectField(format, dateFormat, fields, "prefix", prefix);
		collectField(format, dateFormat, fields, "suffix", suffix);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("name"))
			setName( (String) fields.get("name") );
		if (fields.containsKey("plural"))
			setPlural( (String) fields.get("plural") );
		if (fields.containsKey("symbol"))
			setSymbol( (String) fields.get("symbol") );
		if (fields.containsKey("prefix"))
			setPrefix( (String) fields.get("prefix") );
		if (fields.containsKey("suffix"))
			setSuffix( (String) fields.get("suffix") );
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
				(specificFields != null && !exclude && specificFields.contains("name")) ||
				(specificFields != null && exclude && !specificFields.contains("name")))
			{
				result = 
					validator.clearChecks().setName("name").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(name);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("plural")) ||
				(specificFields != null && exclude && !specificFields.contains("plural")))
			{
				result = 
					validator.clearChecks().setName("plural").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(plural);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("symbol")) ||
				(specificFields != null && exclude && !specificFields.contains("symbol")))
			{
				result = 
					validator.clearChecks().setName("symbol").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(symbol);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("prefix")) ||
				(specificFields != null && exclude && !specificFields.contains("prefix")))
			{
				result = 
					validator.clearChecks().setName("prefix").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(prefix);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("suffix")) ||
				(specificFields != null && exclude && !specificFields.contains("suffix")))
			{
				result = 
					validator.clearChecks().setName("suffix").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(suffix);
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

