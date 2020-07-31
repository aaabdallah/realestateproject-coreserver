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
			name = "List<PropertyLister>",
			entities =
			{
				@EntityResult
				(
					entityClass = PropertyLister.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "listingIdk", column = "listingIdk"),
						@FieldResult(name = "listerIdk", column = "listerIdk"),
						@FieldResult(name = "listerTypeIdk", column = "listerTypeIdk"),
						@FieldResult(name = "commissionTypeIdk", column = "commissionTypeIdk"),
						@FieldResult(name = "commissionAmount", column = "commissionAmount"),
						@FieldResult(name = "listerNotes", column = "listerNotes"),
						@FieldResult(name = "systemNotes", column = "systemNotes")
					}
				)
			}
		)
	}
)
@Entity(name="PropertyLister") @Table(name="\"tPropertyListers\"")
public class PropertyLister extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tPropertyListers\"";

	static public PropertyLister findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (PropertyLister) BaseEntity.findByIdk(manager, PropertyLister.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findPropertyListerByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<PropertyLister>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findPropertyListerByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<PropertyLister>");
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

	static public Map<String, List<MsgArgsPair>> validatePropertyLister(PropertyLister propertyLister,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return propertyLister.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateListingIdk(Long listingIdk)
	{
		return (new FieldValidator()).clearChecks().setName("listingIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(listingIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateListerIdk(Long listerIdk)
	{
		return (new FieldValidator()).clearChecks().setName("listerIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(listerIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateListerTypeIdk(Long listerTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("listerTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(listerTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCommissionTypeIdk(Long commissionTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("commissionTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(commissionTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCommissionAmount(Float commissionAmount)
	{
		return (new FieldValidator()).clearChecks().setName("commissionAmount").setValidMinimum((double) 0).checkFloat(commissionAmount);
	}

	static public Map<String, List<MsgArgsPair>> validateListerNotes(String listerNotes)
	{
		return (new FieldValidator()).clearChecks().setName("listerNotes").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(listerNotes);
	}

	static public Map<String, List<MsgArgsPair>> validateSystemNotes(String systemNotes)
	{
		return (new FieldValidator()).clearChecks().setName("systemNotes").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(systemNotes);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"listingIdk\"") private Long listingIdk = null;
	@Basic @Column(name="\"listerIdk\"") private Long listerIdk = null;
	@Basic @Column(name="\"listerTypeIdk\"") private Long listerTypeIdk = null;
	@Basic @Column(name="\"commissionTypeIdk\"") private Long commissionTypeIdk = null;
	@Basic @Column(name="\"commissionAmount\"") private Float commissionAmount = null;
	@Basic @Column(name="\"listerNotes\"") private String listerNotes = null;
	@Basic @Column(name="\"systemNotes\"") private String systemNotes = null;

	public PropertyLister() {}
	public PropertyLister(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Long getListingIdk()
	{
		return listingIdk;
	}
	public void setListingIdk(Long inputParameter)
	{
		modify();
		listingIdk = inputParameter;
	}

	public Long getListerIdk()
	{
		return listerIdk;
	}
	public void setListerIdk(Long inputParameter)
	{
		modify();
		listerIdk = inputParameter;
	}

	public Long getListerTypeIdk()
	{
		return listerTypeIdk;
	}
	public void setListerTypeIdk(Long inputParameter)
	{
		modify();
		listerTypeIdk = inputParameter;
	}

	public Long getCommissionTypeIdk()
	{
		return commissionTypeIdk;
	}
	public void setCommissionTypeIdk(Long inputParameter)
	{
		modify();
		commissionTypeIdk = inputParameter;
	}

	public Float getCommissionAmount()
	{
		return commissionAmount;
	}
	public void setCommissionAmount(Float inputParameter)
	{
		modify();
		commissionAmount = inputParameter;
	}

	public String getListerNotes()
	{
		return listerNotes;
	}
	public void setListerNotes(String inputParameter)
	{
		modify();
		listerNotes = inputParameter;
	}

	public String getSystemNotes()
	{
		return systemNotes;
	}
	public void setSystemNotes(String inputParameter)
	{
		modify();
		systemNotes = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("listerTypeIdkMsg",
			(String) PropertyOption.cacheLookup(listerTypeIdk).get("name"));
		fields.put("commissionTypeIdkMsg",
			(String) PropertyOption.cacheLookup(commissionTypeIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "listingIdk", listingIdk);
		collectField(format, dateFormat, fields, "listerIdk", listerIdk);
		collectField(format, dateFormat, fields, "listerTypeIdk", listerTypeIdk);
		collectField(format, dateFormat, fields, "commissionTypeIdk", commissionTypeIdk);
		collectField(format, dateFormat, fields, "commissionAmount", commissionAmount);
		collectField(format, dateFormat, fields, "listerNotes", listerNotes);
		collectField(format, dateFormat, fields, "systemNotes", systemNotes);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("listingIdk"))
			setListingIdk( (Long) fields.get("listingIdk") );
		if (fields.containsKey("listerIdk"))
			setListerIdk( (Long) fields.get("listerIdk") );
		if (fields.containsKey("listerTypeIdk"))
			setListerTypeIdk( (Long) fields.get("listerTypeIdk") );
		if (fields.containsKey("commissionTypeIdk"))
			setCommissionTypeIdk( (Long) fields.get("commissionTypeIdk") );
		if (fields.containsKey("commissionAmount"))
			setCommissionAmount( (Float) fields.get("commissionAmount") );
		if (fields.containsKey("listerNotes"))
			setListerNotes( (String) fields.get("listerNotes") );
		if (fields.containsKey("systemNotes"))
			setSystemNotes( (String) fields.get("systemNotes") );
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
				(specificFields != null && !exclude && specificFields.contains("listingIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("listingIdk")))
			{
				result = 
					validator.clearChecks().setName("listingIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(listingIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("listerIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("listerIdk")))
			{
				result = 
					validator.clearChecks().setName("listerIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(listerIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("listerTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("listerTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("listerTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(listerTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("commissionTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("commissionTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("commissionTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(commissionTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("commissionAmount")) ||
				(specificFields != null && exclude && !specificFields.contains("commissionAmount")))
			{
				result = 
					validator.clearChecks().setName("commissionAmount").setValidMinimum((double) 0).checkFloat(commissionAmount);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("listerNotes")) ||
				(specificFields != null && exclude && !specificFields.contains("listerNotes")))
			{
				result = 
					validator.clearChecks().setName("listerNotes").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(listerNotes);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("systemNotes")) ||
				(specificFields != null && exclude && !specificFields.contains("systemNotes")))
			{
				result = 
					validator.clearChecks().setName("systemNotes").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(systemNotes);
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

