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
			name = "List<District>",
			entities =
			{
				@EntityResult
				(
					entityClass = District.class,
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
						@FieldResult(name = "cityIdk", column = "cityIdk"),
						@FieldResult(name = "status", column = "status")
					}
				)
			}
		)
	}
)
@Entity(name="District") @Table(name="\"tDistricts\"")
public class District extends BaseEntity implements Serializable
{
	// The different statuses a location may have.
	public enum Status
	{
		BLOCKEDFROMPROPERTIES ((short) 0x1),
		BLOCKEDFROMPROFILES ((short) 0x2);

		public static Short[] allValues = null;
		static
		{
			allValues = new Short[Status.values().length];
			int i = 0;
			for (Status v : Status.values())
			{
				allValues[i] = v.value;
				i++;
			}
		}
		public static Short allBits = 0;
		static
		{
			for (Status v : Status.values())
				allBits = (short)(allBits | v.value);
		}

		public final Short value;
		Status(Short p) { value = p; }
	}

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
	public static final String entityTableName = "\"tDistricts\"";

	static public District findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (District) BaseEntity.findByIdk(manager, District.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findDistrictByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<District>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findDistrictByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<District>");
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

	static public Map<String, List<MsgArgsPair>> validateDistrict(District district,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return district.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateName(String name)
	{
		return (new FieldValidator()).clearChecks().setName("name").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(name);
	}

	static public Map<String, List<MsgArgsPair>> validateCityIdk(Long cityIdk)
	{
		return (new FieldValidator()).clearChecks().setName("cityIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(cityIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateStatus(Short status)
	{
		return (new FieldValidator()).clearChecks().setName("status").setAllowNullElement(false).setValidChoices((Object[]) Status.allValues).setUseValidChoicesAs("bitMasks").checkShort(status);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"name\"") private String name = null;
	@Basic @Column(name="\"cityIdk\"") private Long cityIdk = null;
	@Basic @Column(name="\"status\"") private Short status = null;

	public District() {}
	public District(Map<String, Object> fields)
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

	public Long getCityIdk()
	{
		return cityIdk;
	}
	public void setCityIdk(Long inputParameter)
	{
		modify();
		cityIdk = inputParameter;
	}

	public Short getStatus()
	{
		return status;
	}
	public void setStatus(Short inputParameter)
	{
		modify();
		status = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("cityIdkMsg",
			(String) City.cacheLookup(cityIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "name", name);
		collectField(format, dateFormat, fields, "cityIdk", cityIdk);
		collectField(format, dateFormat, fields, "status", status);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("name"))
			setName( (String) fields.get("name") );
		if (fields.containsKey("cityIdk"))
			setCityIdk( (Long) fields.get("cityIdk") );
		if (fields.containsKey("status"))
			setStatus( ((Number) fields.get("status")).shortValue() );
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
				(specificFields != null && !exclude && specificFields.contains("cityIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("cityIdk")))
			{
				result = 
					validator.clearChecks().setName("cityIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(cityIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("status")) ||
				(specificFields != null && exclude && !specificFields.contains("status")))
			{
				result = 
					validator.clearChecks().setName("status").setAllowNullElement(false).setValidChoices((Object[]) Status.allValues).setUseValidChoicesAs("bitMasks").checkShort(status);
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

