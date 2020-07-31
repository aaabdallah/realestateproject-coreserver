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
			name = "List<WantedListing>",
			entities =
			{
				@EntityResult
				(
					entityClass = WantedListing.class,
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
						@FieldResult(name = "timePublicAccessStarts", column = "timePublicAccessStarts"),
						@FieldResult(name = "timePublicAccessEnds", column = "timePublicAccessEnds"),
						@FieldResult(name = "forSaleIdk", column = "forSaleIdk"),
						@FieldResult(name = "forRentIdk", column = "forRentIdk"),
						@FieldResult(name = "numberOfViews", column = "numberOfViews"),
						@FieldResult(name = "status", column = "status"),
						@FieldResult(name = "listerNotes", column = "listerNotes"),
						@FieldResult(name = "systemNotes", column = "systemNotes")
					}
				)
			}
		)
	}
)
@Entity(name="WantedListing") @Table(name="\"tWantedListings\"")
public class WantedListing extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tWantedListings\"";

	static public WantedListing findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (WantedListing) BaseEntity.findByIdk(manager, WantedListing.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findWantedListingByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<WantedListing>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findWantedListingByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<WantedListing>");
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

	static public Map<String, List<MsgArgsPair>> validateWantedListing(WantedListing wantedListing,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return wantedListing.validateAllFields(userSuppliedIdk, specificFields, exclude);
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

	static public Map<String, List<MsgArgsPair>> validateNumberOfViews(Integer numberOfViews)
	{
		return (new FieldValidator()).clearChecks().setName("numberOfViews").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(numberOfViews);
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

	// ----- Instance Members -----
	@Basic @Column(name="\"propertyIdk\"") private Long propertyIdk = null;
	@Basic @Column(name="\"timePublicAccessStarts\"") private Timestamp timePublicAccessStarts = null;
	@Basic @Column(name="\"timePublicAccessEnds\"") private Timestamp timePublicAccessEnds = null;
	@Basic @Column(name="\"forSaleIdk\"") private Long forSaleIdk = null;
	@Basic @Column(name="\"forRentIdk\"") private Long forRentIdk = null;
	@Basic @Column(name="\"numberOfViews\"") private Integer numberOfViews = null;
	@Basic @Column(name="\"status\"") private Short status = null;
	@Basic @Column(name="\"listerNotes\"") private String listerNotes = null;
	@Basic @Column(name="\"systemNotes\"") private String systemNotes = null;

	public WantedListing() {}
	public WantedListing(Map<String, Object> fields)
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

	public Integer getNumberOfViews()
	{
		return numberOfViews;
	}
	public void setNumberOfViews(Integer inputParameter)
	{
		modify();
		numberOfViews = inputParameter;
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
		collectField(format, dateFormat, fields, "propertyIdk", propertyIdk);
		collectField(format, dateFormat, fields, "timePublicAccessStarts", timePublicAccessStarts);
		collectField(format, dateFormat, fields, "timePublicAccessEnds", timePublicAccessEnds);
		collectField(format, dateFormat, fields, "forSaleIdk", forSaleIdk);
		collectField(format, dateFormat, fields, "forRentIdk", forRentIdk);
		collectField(format, dateFormat, fields, "numberOfViews", numberOfViews);
		collectField(format, dateFormat, fields, "status", status);
		collectField(format, dateFormat, fields, "listerNotes", listerNotes);
		collectField(format, dateFormat, fields, "systemNotes", systemNotes);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

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
		if (fields.containsKey("numberOfViews"))
			setNumberOfViews( (Integer) fields.get("numberOfViews") );
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
				(specificFields != null && !exclude && specificFields.contains("numberOfViews")) ||
				(specificFields != null && exclude && !specificFields.contains("numberOfViews")))
			{
				result = 
					validator.clearChecks().setName("numberOfViews").setAllowNullElement(false).setValidMinimum((double) 0).checkInteger(numberOfViews);
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

