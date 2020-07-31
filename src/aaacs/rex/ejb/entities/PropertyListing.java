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

import java.sql.Timestamp;

@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<PropertyListing>",
			entities =
			{
				@EntityResult
				(
					entityClass = PropertyListing.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "summary", column = "summary"),
						@FieldResult(name = "userKey", column = "userKey"),
						@FieldResult(name = "systemKey", column = "systemKey"),
						@FieldResult(name = "propertyIdk", column = "propertyIdk"),
						@FieldResult(name = "timePublicAccessStarts", column = "timePublicAccessStarts"),
						@FieldResult(name = "timePublicAccessEnds", column = "timePublicAccessEnds"),
						@FieldResult(name = "forSaleIdk", column = "forSaleIdk"),
						@FieldResult(name = "forRentIdk", column = "forRentIdk"),
						@FieldResult(name = "direct", column = "direct"),
						@FieldResult(name = "numberOfViews", column = "numberOfViews"),
						@FieldResult(name = "includedNotIncludedItems", column = "includedNotIncludedItems"),
						@FieldResult(name = "status", column = "status"),
						@FieldResult(name = "listerNotes", column = "listerNotes"),
						@FieldResult(name = "systemNotes", column = "systemNotes")
					}
				)
			}
		)
	}
)
@Entity(name="PropertyListing") @Table(name="\"tPropertyListings\"")
public class PropertyListing extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tPropertyListings\"";

	static public PropertyListing findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (PropertyListing) BaseEntity.findByIdk(manager, PropertyListing.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findPropertyListingByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<PropertyListing>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findPropertyListingByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<PropertyListing>");
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

	static public Map<String, List<MsgArgsPair>> validatePropertyListing(PropertyListing propertyListing,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return propertyListing.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateSummary(String summary)
	{
		return (new FieldValidator()).clearChecks().setName("summary").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 1000).checkString(summary);
	}

	static public Map<String, List<MsgArgsPair>> validateUserKey(String userKey)
	{
		return (new FieldValidator()).clearChecks().setName("userKey").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(userKey);
	}

	static public Map<String, List<MsgArgsPair>> validateSystemKey(String systemKey)
	{
		return (new FieldValidator()).clearChecks().setName("systemKey").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(systemKey);
	}

	static public Map<String, List<MsgArgsPair>> validatePropertyIdk(Long propertyIdk)
	{
		return (new FieldValidator()).clearChecks().setName("propertyIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(propertyIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateTimePublicAccessStarts(Timestamp timePublicAccessStarts)
	{
		return (new FieldValidator()).clearChecks().setName("timePublicAccessStarts").setAllowNullElement(false).checkTimestamp(timePublicAccessStarts);
	}

	static public Map<String, List<MsgArgsPair>> validateTimePublicAccessEnds(Timestamp timePublicAccessEnds)
	{
		return (new FieldValidator()).clearChecks().setName("timePublicAccessEnds").setAllowNullElement(false).checkTimestamp(timePublicAccessEnds);
	}

	static public Map<String, List<MsgArgsPair>> validateForSaleIdk(Long forSaleIdk)
	{
		return (new FieldValidator()).clearChecks().setName("forSaleIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(forSaleIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateForRentIdk(Long forRentIdk)
	{
		return (new FieldValidator()).clearChecks().setName("forRentIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(forRentIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateDirect(Boolean direct)
	{
		return (new FieldValidator()).clearChecks().setName("direct").checkBoolean(direct);
	}

	static public Map<String, List<MsgArgsPair>> validateNumberOfViews(Integer numberOfViews)
	{
		return (new FieldValidator()).clearChecks().setName("numberOfViews").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(numberOfViews);
	}

	static public Map<String, List<MsgArgsPair>> validateIncludedNotIncludedItems(String includedNotIncludedItems)
	{
		return (new FieldValidator()).clearChecks().setName("includedNotIncludedItems").setValidMinimum((double) 0).setValidMaximum((double) 500).checkString(includedNotIncludedItems);
	}

	static public Map<String, List<MsgArgsPair>> validateStatus(Short status)
	{
		return (new FieldValidator()).clearChecks().setName("status").setAllowNullElement(false).checkShort(status);
	}

	static public Map<String, List<MsgArgsPair>> validateListerNotes(String listerNotes)
	{
		return (new FieldValidator()).clearChecks().setName("listerNotes").setValidMinimum((double) 0).setValidMaximum((double) 500).checkString(listerNotes);
	}

	static public Map<String, List<MsgArgsPair>> validateSystemNotes(String systemNotes)
	{
		return (new FieldValidator()).clearChecks().setName("systemNotes").setValidMinimum((double) 0).setValidMaximum((double) 500).checkString(systemNotes);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("summary") != null && searchFields.get("summary").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"summary\" LIKE $$%" + searchFields.get("summary") + "%$$", true);
		if (searchFields.get("userKey") != null && searchFields.get("userKey").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"userKey\" LIKE $$%" + searchFields.get("userKey") + "%$$", true);
		if (searchFields.get("systemKey") != null && searchFields.get("systemKey").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"systemKey\" LIKE $$%" + searchFields.get("systemKey") + "%$$", true);
		// --- BEGIN CUSTOM CODE ---
		if (searchFields.get("timePublicAccessStartsMinimum") != null && searchFields.get("timePublicAccessStartsMinimum").toString().length() > 0)
			selectBuilder.addWhere("CAST (" + entityTableName + ".\"timePublicAccessStarts\" AS DATE) >= " + 
				"DATE '" + searchFields.get("timePublicAccessStartsMinimum") + "'", true);
		if (searchFields.get("timePublicAccessStartsMaximum") != null && searchFields.get("timePublicAccessStartsMaximum").toString().length() > 0)
			selectBuilder.addWhere("CAST (" + entityTableName + ".\"timePublicAccessStarts\" AS DATE) <= " + 
				"DATE '" + searchFields.get("timePublicAccessStartsMaximum") + "'", true);
		// --- END CUSTOM CODE ---
		// --- BEGIN CUSTOM CODE ---
		if (searchFields.get("timePublicAccessEndsMinimum") != null && searchFields.get("timePublicAccessEndsMinimum").toString().length() > 0)
			selectBuilder.addWhere("CAST (" + entityTableName + ".\"timePublicAccessEnds\" AS DATE) >= " + 
				"DATE '" + searchFields.get("timePublicAccessEndsMinimum") + "'", true);
		if (searchFields.get("timePublicAccessEndsMaximum") != null && searchFields.get("timePublicAccessEndsMaximum").toString().length() > 0)
			selectBuilder.addWhere("CAST (" + entityTableName + ".\"timePublicAccessEnds\" AS DATE) <= " + 
				"DATE '" + searchFields.get("timePublicAccessEndsMaximum") + "'", true);
		// --- END CUSTOM CODE ---
		if (searchFields.get("direct") != null && searchFields.get("direct").toString().length() > 0 &&
			searchFields.get("direct").equals(false))
			selectBuilder.addWhere(entityTableName + ".\"direct\" = false", true);
		if (searchFields.get("direct") != null && searchFields.get("direct").toString().length() > 0 &&
			searchFields.get("direct").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"direct\" = true", true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"summary\"") private String summary = null;
	@Basic @Column(name="\"userKey\"") private String userKey = null;
	@Basic @Column(name="\"systemKey\"") private String systemKey = null;
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"timePublicAccessStarts\"") private Timestamp timePublicAccessStarts = null;
	@Basic @Column(name="\"timePublicAccessEnds\"") private Timestamp timePublicAccessEnds = null;
	@Basic @Column(name="\"forSaleIdk\"") private Long forSaleIdk = null;
	@Basic @Column(name="\"forRentIdk\"") private Long forRentIdk = null;
	@Basic @Column(name="\"direct\"") private Boolean direct = null;
	@Basic @Column(name="\"numberOfViews\"") private Integer numberOfViews = null;
	@Basic @Column(name="\"includedNotIncludedItems\"") private String includedNotIncludedItems = null;
	@Basic @Column(name="\"status\"") private Short status = null;
	@Basic @Column(name="\"listerNotes\"") private String listerNotes = null;
	@Basic @Column(name="\"systemNotes\"") private String systemNotes = null;

	public PropertyListing() {}
	public PropertyListing(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public String getSummary()
	{
		return summary;
	}
	public void setSummary(String inputParameter)
	{
		modify();
		summary = inputParameter;
	}

	public String getUserKey()
	{
		return userKey;
	}
	public void setUserKey(String inputParameter)
	{
		modify();
		userKey = inputParameter;
	}

	public String getSystemKey()
	{
		return systemKey;
	}
	public void setSystemKey(String inputParameter)
	{
		modify();
		systemKey = inputParameter;
	}

	public Long getPropertyIdk()
	{
		return propertyIdk;
	}
	public void setPropertyIdk(Long inputParameter)
	{
		modify();
		propertyIdk = inputParameter;
	}

	public Timestamp getTimePublicAccessStarts()
	{
		return timePublicAccessStarts;
	}
	public void setTimePublicAccessStarts(Timestamp inputParameter)
	{
		modify();
		timePublicAccessStarts = inputParameter;
	}

	public Timestamp getTimePublicAccessEnds()
	{
		return timePublicAccessEnds;
	}
	public void setTimePublicAccessEnds(Timestamp inputParameter)
	{
		modify();
		timePublicAccessEnds = inputParameter;
	}

	public Long getForSaleIdk()
	{
		return forSaleIdk;
	}
	public void setForSaleIdk(Long inputParameter)
	{
		modify();
		forSaleIdk = inputParameter;
	}

	public Long getForRentIdk()
	{
		return forRentIdk;
	}
	public void setForRentIdk(Long inputParameter)
	{
		modify();
		forRentIdk = inputParameter;
	}

	public Boolean getDirect()
	{
		return direct;
	}
	public void setDirect(Boolean inputParameter)
	{
		modify();
		direct = inputParameter;
	}

	public Integer getNumberOfViews()
	{
		return numberOfViews;
	}
	public void setNumberOfViews(Integer inputParameter)
	{
		modify();
		numberOfViews = inputParameter;
	}

	public String getIncludedNotIncludedItems()
	{
		return includedNotIncludedItems;
	}
	public void setIncludedNotIncludedItems(String inputParameter)
	{
		modify();
		includedNotIncludedItems = inputParameter;
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
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "summary", summary);
		collectField(format, dateFormat, fields, "userKey", userKey);
		collectField(format, dateFormat, fields, "systemKey", systemKey);
		collectField(format, dateFormat, fields, "propertyIdk", propertyIdk);
		collectField(format, dateFormat, fields, "timePublicAccessStarts", timePublicAccessStarts);
		collectField(format, dateFormat, fields, "timePublicAccessEnds", timePublicAccessEnds);
		collectField(format, dateFormat, fields, "forSaleIdk", forSaleIdk);
		collectField(format, dateFormat, fields, "forRentIdk", forRentIdk);
		collectField(format, dateFormat, fields, "direct", direct);
		collectField(format, dateFormat, fields, "numberOfViews", numberOfViews);
		collectField(format, dateFormat, fields, "includedNotIncludedItems", includedNotIncludedItems);
		collectField(format, dateFormat, fields, "status", status);
		collectField(format, dateFormat, fields, "listerNotes", listerNotes);
		collectField(format, dateFormat, fields, "systemNotes", systemNotes);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("summary"))
			setSummary( (String) fields.get("summary") );
		if (fields.containsKey("userKey"))
			setUserKey( (String) fields.get("userKey") );
		if (fields.containsKey("systemKey"))
			setSystemKey( (String) fields.get("systemKey") );
		if (fields.containsKey("propertyIdk"))
			setPropertyIdk( (Long) fields.get("propertyIdk") );
		if (fields.containsKey("timePublicAccessStarts"))
			setTimePublicAccessStarts( (Timestamp) fields.get("timePublicAccessStarts") );
		if (fields.containsKey("timePublicAccessEnds"))
			setTimePublicAccessEnds( (Timestamp) fields.get("timePublicAccessEnds") );
		if (fields.containsKey("forSaleIdk"))
			setForSaleIdk( (Long) fields.get("forSaleIdk") );
		if (fields.containsKey("forRentIdk"))
			setForRentIdk( (Long) fields.get("forRentIdk") );
		if (fields.containsKey("direct"))
			setDirect( (Boolean) fields.get("direct") );
		if (fields.containsKey("numberOfViews"))
			setNumberOfViews( (Integer) fields.get("numberOfViews") );
		if (fields.containsKey("includedNotIncludedItems"))
			setIncludedNotIncludedItems( (String) fields.get("includedNotIncludedItems") );
		if (fields.containsKey("status"))
			setStatus( ((Number) fields.get("status")).shortValue() );
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
				(specificFields != null && !exclude && specificFields.contains("summary")) ||
				(specificFields != null && exclude && !specificFields.contains("summary")))
			{
				result = 
					validator.clearChecks().setName("summary").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 1000).checkString(summary);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("userKey")) ||
				(specificFields != null && exclude && !specificFields.contains("userKey")))
			{
				result = 
					validator.clearChecks().setName("userKey").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(userKey);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("systemKey")) ||
				(specificFields != null && exclude && !specificFields.contains("systemKey")))
			{
				result = 
					validator.clearChecks().setName("systemKey").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(systemKey);
				if (result != null)
					results.putAll(result);
			}

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
				(specificFields != null && !exclude && specificFields.contains("timePublicAccessStarts")) ||
				(specificFields != null && exclude && !specificFields.contains("timePublicAccessStarts")))
			{
				result = 
					validator.clearChecks().setName("timePublicAccessStarts").setAllowNullElement(false).checkTimestamp(timePublicAccessStarts);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("timePublicAccessEnds")) ||
				(specificFields != null && exclude && !specificFields.contains("timePublicAccessEnds")))
			{
				result = 
					validator.clearChecks().setName("timePublicAccessEnds").setAllowNullElement(false).checkTimestamp(timePublicAccessEnds);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("forSaleIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("forSaleIdk")))
			{
				result = 
					validator.clearChecks().setName("forSaleIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(forSaleIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("forRentIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("forRentIdk")))
			{
				result = 
					validator.clearChecks().setName("forRentIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(forRentIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("direct")) ||
				(specificFields != null && exclude && !specificFields.contains("direct")))
			{
				result = 
					validator.clearChecks().setName("direct").checkBoolean(direct);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("numberOfViews")) ||
				(specificFields != null && exclude && !specificFields.contains("numberOfViews")))
			{
				result = 
					validator.clearChecks().setName("numberOfViews").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(numberOfViews);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("includedNotIncludedItems")) ||
				(specificFields != null && exclude && !specificFields.contains("includedNotIncludedItems")))
			{
				result = 
					validator.clearChecks().setName("includedNotIncludedItems").setValidMinimum((double) 0).setValidMaximum((double) 500).checkString(includedNotIncludedItems);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("status")) ||
				(specificFields != null && exclude && !specificFields.contains("status")))
			{
				result = 
					validator.clearChecks().setName("status").setAllowNullElement(false).checkShort(status);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("listerNotes")) ||
				(specificFields != null && exclude && !specificFields.contains("listerNotes")))
			{
				result = 
					validator.clearChecks().setName("listerNotes").setValidMinimum((double) 0).setValidMaximum((double) 500).checkString(listerNotes);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("systemNotes")) ||
				(specificFields != null && exclude && !specificFields.contains("systemNotes")))
			{
				result = 
					validator.clearChecks().setName("systemNotes").setValidMinimum((double) 0).setValidMaximum((double) 500).checkString(systemNotes);
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

