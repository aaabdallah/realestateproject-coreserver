/* *************************************************************************** 
 * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * 
 * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * 
 * Copyright Ahmed A. Abd-Allah, 2006                                        * 
 * ***************************************************************************/

package aaacs.coreserver.ejb.entities;

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


/**
 * Core Server component for tracking system statuses
 */
@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<SystemStatus>",
			entities =
			{
				@EntityResult
				(
					entityClass = SystemStatus.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "category", column = "category"),
						@FieldResult(name = "name", column = "name"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "value", column = "value")
					}
				)
			}
		)
	}
)
@Entity(name="SystemStatus") @Table(name="\"tSystemStatus\"")
public class SystemStatus extends BaseEntity implements Serializable
{
	/**
	 * Serialization Version Number
	 */
	private static final long serialVersionUID = 1000L;
	/**
	 * Resources file
	 */
	public static final String entityResourceFile = "src.aaacs.coreserver.resources.entityNames";
	/**
	 * Entity table name
	 */
	public static final String entityTableName = "\"tSystemStatus\"";

	static public SystemStatus findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (SystemStatus) BaseEntity.findByIdk(manager, SystemStatus.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findSystemStatusByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<SystemStatus>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findSystemStatusByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<SystemStatus>");
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

	static public Map<String, List<MsgArgsPair>> validateSystemStatus(SystemStatus systemStatus,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return systemStatus.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateCategory(String category)
	{
		return (new FieldValidator()).clearChecks().setName("category").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(category);
	}

	static public Map<String, List<MsgArgsPair>> validateName(String name)
	{
		return (new FieldValidator()).clearChecks().setName("name").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(name);
	}

	static public Map<String, List<MsgArgsPair>> validateDescription(String description)
	{
		return (new FieldValidator()).clearChecks().setName("description").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(description);
	}

	static public Map<String, List<MsgArgsPair>> validateValue(String value)
	{
		return (new FieldValidator()).clearChecks().setName("value").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(value);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"category\"") private String category = null;
	@Basic @Column(name="\"name\"") private String name = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"value\"") private String value = null;

	public SystemStatus() {}
	public SystemStatus(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String inputParameter)
	{
		modify();
		category = inputParameter;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String inputParameter)
	{
		modify();
		name = inputParameter;
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

	public String getValue()
	{
		return value;
	}
	public void setValue(String inputParameter)
	{
		modify();
		value = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "category", category);
		collectField(format, dateFormat, fields, "name", name);
		collectField(format, dateFormat, fields, "description", description);
		collectField(format, dateFormat, fields, "value", value);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("category"))
			setCategory( (String) fields.get("category") );
		if (fields.containsKey("name"))
			setName( (String) fields.get("name") );
		if (fields.containsKey("description"))
			setDescription( (String) fields.get("description") );
		if (fields.containsKey("value"))
			setValue( (String) fields.get("value") );
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
				(specificFields != null && !exclude && specificFields.contains("category")) ||
				(specificFields != null && exclude && !specificFields.contains("category")))
			{
				result = 
					validator.clearChecks().setName("category").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(category);
				if (result != null)
					results.putAll(result);
			}

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
				(specificFields != null && !exclude && specificFields.contains("description")) ||
				(specificFields != null && exclude && !specificFields.contains("description")))
			{
				result = 
					validator.clearChecks().setName("description").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(description);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("value")) ||
				(specificFields != null && exclude && !specificFields.contains("value")))
			{
				result = 
					validator.clearChecks().setName("value").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(value);
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

