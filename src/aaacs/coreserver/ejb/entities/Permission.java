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

import java.sql.Timestamp;
import aaacs.coreserver.database.PrimaryKeyHolder;

/**
 * Core Server component for managing access permissions
 */
@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<Permission>",
			entities =
			{
				@EntityResult
				(
					entityClass = Permission.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "actionName", column = "actionName"),
						@FieldResult(name = "actionVersion", column = "actionVersion"),
						@FieldResult(name = "assignerIdk", column = "assignerIdk"),
						@FieldResult(name = "assignerRankIdk", column = "assignerRankIdk"),
						@FieldResult(name = "assigneeIdk", column = "assigneeIdk"),
						@FieldResult(name = "assigneeRankIdk", column = "assigneeRankIdk"),
						@FieldResult(name = "assigneeRoleIdk", column = "assigneeRoleIdk"),
						@FieldResult(name = "objectTableName", column = "objectTableName"),
						@FieldResult(name = "objectGroupIdk", column = "objectGroupIdk"),
						@FieldResult(name = "objectIdk", column = "objectIdk"),
						@FieldResult(name = "objectConstraint", column = "objectConstraint"),
						@FieldResult(name = "status", column = "status"),
						@FieldResult(name = "dependsUponIdk", column = "dependsUponIdk"),
						@FieldResult(name = "description", column = "description"),
						@FieldResult(name = "strength", column = "strength"),
						@FieldResult(name = "validityStartTime", column = "validityStartTime"),
						@FieldResult(name = "validityStopTime", column = "validityStopTime")
					}
				)
			}
		)
	}
)
@Entity(name="Permission") @Table(name="\"tPermissions\"")
public class Permission extends BaseEntity implements Serializable
{
	// ----- Permission statuses ----------------------------------------------
	// The precedence hierarchy is as follows: DENIED > GRANTED
	// where precedence means which is stronger in case there is a
	// permissions conflict (all other things being equal).
	public enum Status
	{
		GRANTED ((short) 0),
		DENIED ((short) 1);

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

		public final Short value;
		Status(Short p) { value = p; }
	}

	// a simple marker to represent a 'wild-card' object table name
	public enum TableName
	{
		MATCH_ALL_TABLE_NAMES ( "*");

		public static String[] allValues = null;
		static
		{
			allValues = new String[TableName.values().length];
			int i = 0;
			for (TableName v : TableName.values())
			{
				allValues[i] = v.value;
				i++;
			}
		}

		public final String value;
		TableName(String p) { value = p; }
	}

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
	public static final String entityTableName = "\"tPermissions\"";

	static public Permission findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Permission) BaseEntity.findByIdk(manager, Permission.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findPermissionByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Permission>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findPermissionByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Permission>");
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

	// --- BEGIN CUSTOM CODE ---
	static public List findByRelevantFields(EntityManager manager,
		long inAssigneeIdk, long inAssigneeRankIdk, 
		Vector<Long> inAssigneeRoleIdks, String inActionName, short inActionVersion) 
		throws CoreServerException
	{
		return findByRelevantFields(manager, inAssigneeIdk, inAssigneeRankIdk,
			inAssigneeRoleIdks, inActionName, inActionVersion,
			PrimaryKeyHolder.KEY_MATCHES_USER_KEYS,
			Permission.TableName.MATCH_ALL_TABLE_NAMES.value,
			PrimaryKeyHolder.KEY_MATCHES_USER_KEYS);
	}
	
	/**
	 * Finds permissions using the fields that are used to determine the applicability
	 * of a permission. See the {@link #computeStrength()} method to understand
	 * what fields are used.
	 * In theory we could fill in all the parameters with default arguments (except
	 * the entity manager). However, we force the user to explicitly identify the
	 * assignee, assignee rank, action name, and action version since these are more
	 * important than the other parameters. It is allowed to send in null for the
	 * other parameters - they will be filled in with defaults - however, it is best
	 * to be careful with that.
	 * 
	 * @param manager the entity manager to use for persistence [required]
	 * @param inAssigneeIdk the user to check against [required]
	 * @param inAssigneeRankIdk the user's rank [required]
	 * @param inAssigneeRoleIdks the user's roles [optional]
	 * @param inActionName the action name [required]
	 * @param inActionVersion the action version [required]
	 * @param inObjectTableName the object's table name [optional]
	 * @param inObjectIdk the object's Idk [optional]
	 * @param inObjectGroup the object's group [optional]
	 */
	static public List findByRelevantFields(EntityManager manager,
		Long inAssigneeIdk, Long inAssigneeRankIdk, 
		Vector<Long> inAssigneeRoleIdks, String inActionName, Short inActionVersion, 
		Long inObjectGroupIdk, String inObjectTableName, Long inObjectIdk) 
		throws CoreServerException
	{
		if ((inAssigneeIdk == null ||
			inAssigneeIdk < PrimaryKeyHolder.KEY_LOWEST_RESERVED) ||
			(inAssigneeRankIdk == null ||
			inAssigneeRankIdk < PrimaryKeyHolder.KEY_LOWEST_RESERVED) ||
			inActionName == null || (inActionVersion == null || inActionVersion < 1))
			throw new CoreServerException("permission.MissingRelevantFields");

		if (inObjectTableName == null)
			inObjectTableName = Permission.TableName.MATCH_ALL_TABLE_NAMES.value;
		if (inObjectGroupIdk == null || inObjectGroupIdk < PrimaryKeyHolder.KEY_LOWEST_RESERVED)
			inObjectGroupIdk = PrimaryKeyHolder.KEY_MATCHES_USER_KEYS;
		if (inObjectIdk == null || inObjectIdk < PrimaryKeyHolder.KEY_LOWEST_RESERVED)
			inObjectIdk = PrimaryKeyHolder.KEY_MATCHES_USER_KEYS;

		Vector<Object> parameters = new Vector<Object>();
		String condition = "where (\"assigneeIdk\" = " + _PMPP_ + " OR \"assigneeIdk\" = " + _PMPP_ + ") and " +
			"(\"assigneeRankIdk\" = " + _PMPP_ + " OR \"assigneeRankIdk\" = " + _PMPP_ + ") and " +
			"(\"actionName\" = " + _PMPP_ + " OR \"actionName\" = " + _PMPP_ + ") and " +
			"(\"actionVersion\" = " + _PMPP_ + " OR \"actionVersion\" = " + _PMPP_ + ") and " +
			"(\"objectTableName\" = " + _PMPP_ + " OR \"objectTableName\" = " + _PMPP_ + ") and " +
			"(\"objectIdk\" = " + _PMPP_ + " OR \"objectIdk\" = " + _PMPP_ + ") and " +
			"(\"objectGroupIdk\" = " + _PMPP_ + " OR \"objectGroupIdk\" = " + _PMPP_ + ") ";

		parameters.add(inAssigneeIdk);		
		parameters.add(PrimaryKeyHolder.KEY_MATCHES_USER_KEYS);

		parameters.add(inAssigneeRankIdk);		
		parameters.add(PrimaryKeyHolder.KEY_MATCHES_USER_KEYS);

		parameters.add(inActionName);
		parameters.add(Action.ActionName.MATCH_ALL_ACTION_NAMES.value);
		parameters.add(new Short(inActionVersion));
		parameters.add(new Short(Action.ActionVersion.MATCH_ALL_ACTION_VERSIONS.value));

		parameters.add(inObjectTableName);
		parameters.add(Permission.TableName.MATCH_ALL_TABLE_NAMES.value);

		parameters.add(inObjectIdk);
		parameters.add(PrimaryKeyHolder.KEY_MATCHES_USER_KEYS);
		
		parameters.add(inObjectGroupIdk);
		parameters.add(PrimaryKeyHolder.KEY_MATCHES_USER_KEYS);

		condition += "and (";
		if (inAssigneeRoleIdks != null && inAssigneeRoleIdks.size() > 0)
		{
			condition += "\"assigneeRoleIdk\" IN (";

			boolean processedOne = false;
			for (Long roleIdk : inAssigneeRoleIdks)
			{
				if (processedOne)
					condition = condition + ",";
				condition = condition + " " + _PMPP_;
				parameters.add(roleIdk);
				processedOne = true;
			}
			condition += ") OR ";
		}

		condition = condition + " \"assigneeRoleIdk\" = " + _PMPP_ + ")";
		parameters.add(PrimaryKeyHolder.KEY_MATCHES_USER_KEYS);
		
		condition = condition + " ORDER BY strength DESC";

		return findPermissionByCondition(manager, condition, parameters);
	}
	// --- END CUSTOM CODE ---

	static public Map<String, List<MsgArgsPair>> validatePermission(Permission permission,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return permission.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateActionName(String actionName)
	{
		return (new FieldValidator()).clearChecks().setName("actionName").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(actionName);
	}

	static public Map<String, List<MsgArgsPair>> validateActionVersion(Short actionVersion)
	{
		return (new FieldValidator()).clearChecks().setName("actionVersion").setAllowNullElement(false).setValidMinimum((double) (short) Action.ActionVersion.MATCH_ALL_ACTION_VERSIONS.value).checkShort(actionVersion);
	}

	static public Map<String, List<MsgArgsPair>> validateAssignerIdk(Long assignerIdk)
	{
		return (new FieldValidator()).clearChecks().setName("assignerIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(assignerIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateAssignerRankIdk(Long assignerRankIdk)
	{
		return (new FieldValidator()).clearChecks().setName("assignerRankIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(assignerRankIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateAssigneeIdk(Long assigneeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("assigneeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(assigneeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateAssigneeRankIdk(Long assigneeRankIdk)
	{
		return (new FieldValidator()).clearChecks().setName("assigneeRankIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(assigneeRankIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateAssigneeRoleIdk(Long assigneeRoleIdk)
	{
		return (new FieldValidator()).clearChecks().setName("assigneeRoleIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(assigneeRoleIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateObjectTableName(String objectTableName)
	{
		return (new FieldValidator()).clearChecks().setName("objectTableName").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(objectTableName);
	}

	static public Map<String, List<MsgArgsPair>> validateObjectGroupIdk(Long objectGroupIdk)
	{
		return (new FieldValidator()).clearChecks().setName("objectGroupIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(objectGroupIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateObjectIdk(Long objectIdk)
	{
		return (new FieldValidator()).clearChecks().setName("objectIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(objectIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateObjectConstraint(String objectConstraint)
	{
		return (new FieldValidator()).clearChecks().setName("objectConstraint").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(objectConstraint);
	}

	static public Map<String, List<MsgArgsPair>> validateStatus(Short status)
	{
		return (new FieldValidator()).clearChecks().setName("status").setAllowNullElement(false).checkShort(status);
	}

	static public Map<String, List<MsgArgsPair>> validateDependsUponIdk(Long dependsUponIdk)
	{
		return (new FieldValidator()).clearChecks().setName("dependsUponIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(dependsUponIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateDescription(String description)
	{
		return (new FieldValidator()).clearChecks().setName("description").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(description);
	}

	static public Map<String, List<MsgArgsPair>> validateStrength(Long strength)
	{
		return (new FieldValidator()).clearChecks().setName("strength").setAllowNullElement(false).checkLong(strength);
	}

	static public Map<String, List<MsgArgsPair>> validateValidityStartTime(Timestamp validityStartTime)
	{
		return (new FieldValidator()).clearChecks().setName("validityStartTime").setAllowNullElement(false).checkTimestamp(validityStartTime);
	}

	static public Map<String, List<MsgArgsPair>> validateValidityStopTime(Timestamp validityStopTime)
	{
		return (new FieldValidator()).clearChecks().setName("validityStopTime").setAllowNullElement(false).checkTimestamp(validityStopTime);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"actionName\"") private String actionName = null;
	@Basic @Column(name="\"actionVersion\"") private Short actionVersion = null;
	@Basic @Column(name="\"assignerIdk\"") private Long assignerIdk = null;
	@Basic @Column(name="\"assignerRankIdk\"") private Long assignerRankIdk = null;
	@Basic @Column(name="\"assigneeIdk\"") private Long assigneeIdk = null;
	@Basic @Column(name="\"assigneeRankIdk\"") private Long assigneeRankIdk = null;
	@Basic @Column(name="\"assigneeRoleIdk\"") private Long assigneeRoleIdk = null;
	@Basic @Column(name="\"objectTableName\"") private String objectTableName = null;
	@Basic @Column(name="\"objectGroupIdk\"") private Long objectGroupIdk = null;
	@Basic @Column(name="\"objectIdk\"") private Long objectIdk = null;
	@Basic @Column(name="\"objectConstraint\"") private String objectConstraint = null;
	@Basic @Column(name="\"status\"") private Short status = null;
	@Basic @Column(name="\"dependsUponIdk\"") private Long dependsUponIdk = null;
	@Basic @Column(name="\"description\"") private String description = null;
	@Basic @Column(name="\"strength\"") private Long strength = null;
	@Basic @Column(name="\"validityStartTime\"") private Timestamp validityStartTime = null;
	@Basic @Column(name="\"validityStopTime\"") private Timestamp validityStopTime = null;

	public Permission() {}
	public Permission(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public String getActionName()
	{
		return actionName;
	}
	public void setActionName(String inputParameter)
	{
		modify();
		actionName = inputParameter;
	}

	public Short getActionVersion()
	{
		return actionVersion;
	}
	public void setActionVersion(Short inputParameter)
	{
		modify();
		actionVersion = inputParameter;
	}

	public Long getAssignerIdk()
	{
		return assignerIdk;
	}
	public void setAssignerIdk(Long inputParameter)
	{
		modify();
		assignerIdk = inputParameter;
	}

	public Long getAssignerRankIdk()
	{
		return assignerRankIdk;
	}
	public void setAssignerRankIdk(Long inputParameter)
	{
		modify();
		assignerRankIdk = inputParameter;
	}

	public Long getAssigneeIdk()
	{
		return assigneeIdk;
	}
	public void setAssigneeIdk(Long inputParameter)
	{
		modify();
		assigneeIdk = inputParameter;
	}

	public Long getAssigneeRankIdk()
	{
		return assigneeRankIdk;
	}
	public void setAssigneeRankIdk(Long inputParameter)
	{
		modify();
		assigneeRankIdk = inputParameter;
	}

	public Long getAssigneeRoleIdk()
	{
		return assigneeRoleIdk;
	}
	public void setAssigneeRoleIdk(Long inputParameter)
	{
		modify();
		assigneeRoleIdk = inputParameter;
	}

	public String getObjectTableName()
	{
		return objectTableName;
	}
	public void setObjectTableName(String inputParameter)
	{
		modify();
		objectTableName = inputParameter;
	}

	public Long getObjectGroupIdk()
	{
		return objectGroupIdk;
	}
	public void setObjectGroupIdk(Long inputParameter)
	{
		modify();
		objectGroupIdk = inputParameter;
	}

	public Long getObjectIdk()
	{
		return objectIdk;
	}
	public void setObjectIdk(Long inputParameter)
	{
		modify();
		objectIdk = inputParameter;
	}

	public String getObjectConstraint()
	{
		return objectConstraint;
	}
	public void setObjectConstraint(String inputParameter)
	{
		modify();
		objectConstraint = inputParameter;
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

	public Long getDependsUponIdk()
	{
		return dependsUponIdk;
	}
	public void setDependsUponIdk(Long inputParameter)
	{
		modify();
		dependsUponIdk = inputParameter;
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

	public Long getStrength()
	{
		return strength;
	}
	public void setStrength(Long inputParameter)
	{
		modify();
		strength = inputParameter;
	}

	public Timestamp getValidityStartTime()
	{
		return validityStartTime;
	}
	public void setValidityStartTime(Timestamp inputParameter)
	{
		modify();
		validityStartTime = inputParameter;
	}

	public Timestamp getValidityStopTime()
	{
		return validityStopTime;
	}
	public void setValidityStopTime(Timestamp inputParameter)
	{
		modify();
		validityStopTime = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("assignerRankIdkMsg",
			(String) Rank.cacheLookup(assignerRankIdk).get("name"));
		fields.put("assigneeRankIdkMsg",
			(String) Rank.cacheLookup(assigneeRankIdk).get("name"));
		fields.put("assigneeRoleIdkMsg",
			(String) Role.cacheLookup(assigneeRoleIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "actionName", actionName);
		collectField(format, dateFormat, fields, "actionVersion", actionVersion);
		collectField(format, dateFormat, fields, "assignerIdk", assignerIdk);
		collectField(format, dateFormat, fields, "assignerRankIdk", assignerRankIdk);
		collectField(format, dateFormat, fields, "assigneeIdk", assigneeIdk);
		collectField(format, dateFormat, fields, "assigneeRankIdk", assigneeRankIdk);
		collectField(format, dateFormat, fields, "assigneeRoleIdk", assigneeRoleIdk);
		collectField(format, dateFormat, fields, "objectTableName", objectTableName);
		collectField(format, dateFormat, fields, "objectGroupIdk", objectGroupIdk);
		collectField(format, dateFormat, fields, "objectIdk", objectIdk);
		collectField(format, dateFormat, fields, "objectConstraint", objectConstraint);
		collectField(format, dateFormat, fields, "status", status);
		collectField(format, dateFormat, fields, "dependsUponIdk", dependsUponIdk);
		collectField(format, dateFormat, fields, "description", description);
		collectField(format, dateFormat, fields, "strength", strength);
		collectField(format, dateFormat, fields, "validityStartTime", validityStartTime);
		collectField(format, dateFormat, fields, "validityStopTime", validityStopTime);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("actionName"))
			setActionName( (String) fields.get("actionName") );
		if (fields.containsKey("actionVersion"))
			setActionVersion( ((Number) fields.get("actionVersion")).shortValue() );
		if (fields.containsKey("assignerIdk"))
			setAssignerIdk( (Long) fields.get("assignerIdk") );
		if (fields.containsKey("assignerRankIdk"))
			setAssignerRankIdk( (Long) fields.get("assignerRankIdk") );
		if (fields.containsKey("assigneeIdk"))
			setAssigneeIdk( (Long) fields.get("assigneeIdk") );
		if (fields.containsKey("assigneeRankIdk"))
			setAssigneeRankIdk( (Long) fields.get("assigneeRankIdk") );
		if (fields.containsKey("assigneeRoleIdk"))
			setAssigneeRoleIdk( (Long) fields.get("assigneeRoleIdk") );
		if (fields.containsKey("objectTableName"))
			setObjectTableName( (String) fields.get("objectTableName") );
		if (fields.containsKey("objectGroupIdk"))
			setObjectGroupIdk( (Long) fields.get("objectGroupIdk") );
		if (fields.containsKey("objectIdk"))
			setObjectIdk( (Long) fields.get("objectIdk") );
		if (fields.containsKey("objectConstraint"))
			setObjectConstraint( (String) fields.get("objectConstraint") );
		if (fields.containsKey("status"))
			setStatus( ((Number) fields.get("status")).shortValue() );
		if (fields.containsKey("dependsUponIdk"))
			setDependsUponIdk( (Long) fields.get("dependsUponIdk") );
		if (fields.containsKey("description"))
			setDescription( (String) fields.get("description") );
		if (fields.containsKey("strength"))
			setStrength( (Long) fields.get("strength") );
		if (fields.containsKey("validityStartTime"))
			setValidityStartTime( (Timestamp) fields.get("validityStartTime") );
		if (fields.containsKey("validityStopTime"))
			setValidityStopTime( (Timestamp) fields.get("validityStopTime") );
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
				(specificFields != null && !exclude && specificFields.contains("actionName")) ||
				(specificFields != null && exclude && !specificFields.contains("actionName")))
			{
				result = 
					validator.clearChecks().setName("actionName").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(actionName);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("actionVersion")) ||
				(specificFields != null && exclude && !specificFields.contains("actionVersion")))
			{
				result = 
					validator.clearChecks().setName("actionVersion").setAllowNullElement(false).setValidMinimum((double) (short) Action.ActionVersion.MATCH_ALL_ACTION_VERSIONS.value).checkShort(actionVersion);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("assignerIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("assignerIdk")))
			{
				result = 
					validator.clearChecks().setName("assignerIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(assignerIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("assignerRankIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("assignerRankIdk")))
			{
				result = 
					validator.clearChecks().setName("assignerRankIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(assignerRankIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("assigneeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("assigneeIdk")))
			{
				result = 
					validator.clearChecks().setName("assigneeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(assigneeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("assigneeRankIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("assigneeRankIdk")))
			{
				result = 
					validator.clearChecks().setName("assigneeRankIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(assigneeRankIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("assigneeRoleIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("assigneeRoleIdk")))
			{
				result = 
					validator.clearChecks().setName("assigneeRoleIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(assigneeRoleIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("objectTableName")) ||
				(specificFields != null && exclude && !specificFields.contains("objectTableName")))
			{
				result = 
					validator.clearChecks().setName("objectTableName").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(objectTableName);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("objectGroupIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("objectGroupIdk")))
			{
				result = 
					validator.clearChecks().setName("objectGroupIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(objectGroupIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("objectIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("objectIdk")))
			{
				result = 
					validator.clearChecks().setName("objectIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(objectIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("objectConstraint")) ||
				(specificFields != null && exclude && !specificFields.contains("objectConstraint")))
			{
				result = 
					validator.clearChecks().setName("objectConstraint").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 300).checkString(objectConstraint);
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
				(specificFields != null && !exclude && specificFields.contains("dependsUponIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("dependsUponIdk")))
			{
				result = 
					validator.clearChecks().setName("dependsUponIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(dependsUponIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("description")) ||
				(specificFields != null && exclude && !specificFields.contains("description")))
			{
				result = 
					validator.clearChecks().setName("description").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(description);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("strength")) ||
				(specificFields != null && exclude && !specificFields.contains("strength")))
			{
				result = 
					validator.clearChecks().setName("strength").setAllowNullElement(false).checkLong(strength);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("validityStartTime")) ||
				(specificFields != null && exclude && !specificFields.contains("validityStartTime")))
			{
				result = 
					validator.clearChecks().setName("validityStartTime").setAllowNullElement(false).checkTimestamp(validityStartTime);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("validityStopTime")) ||
				(specificFields != null && exclude && !specificFields.contains("validityStopTime")))
			{
				result = 
					validator.clearChecks().setName("validityStopTime").setAllowNullElement(false).checkTimestamp(validityStopTime);
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
		// --- BEGIN CUSTOM CODE ---
		strength = computeStrength();
		// --- END CUSTOM CODE ---

	}

	// --- BEGIN CUSTOM CODE ---
	public long computeStrength()
	{
		// The strength is a convenient shorthand that indicates how strong a
		// particular permission is compared to other permissions. The strength
		// is computed as follows. We look at each of the following attributes
		// and assign it a number, then sum them up but in different positions
		// within a single number (i.e. the units, the tens, the hundreds, etc.).
		// The most important component of a permission is its assigner's rank.
		// The rest of the attributes follow, and their order is obviously significant.
		// The choice of this particular order is intuitive! Arguments can be made
		// for other orders. If the order is changed, all permissions in the database
		// need to be updated to reflect the new order.
		// There are a total of nine attributes that contribute to the strength
		// of a permission: 
		//
		// Category: Assigner Properties
		// 1. assigner rank: short

		// Category: Assignee Properties
		// 2. assignee: specific beats match all-uninit
		// 3. assignee rank: specific beats match all-uninit
		// 4. assignee role: specific beats match all-uninit

		// Category: Action Properties
		// 5. action: specific beats not (based on name and version)

		// Category: Object Properties
		// 6. object: specific beats match all-uninit
		// 7. table: specific beats not
		// 8. object group: specific beats match all-uninit (note that groups may cross tables)

		// Category: Access Properties
		// 9. status: DENIED beats GRANTED
	
		long l = 0;
		try
		{
			if (System.currentTimeMillis() < validityStartTime.getTime() ||
				System.currentTimeMillis() > validityStopTime.getTime())
				return 0;

			l = ((Short) Rank.cacheLookup(assignerRankIdk).get("priority")) * 100000000L;
	
			if (isUserSuppliedPrimaryKey(assigneeIdk))
				l = l + 10000000L;
	
			if (isUserSuppliedPrimaryKey(assigneeRankIdk))
				l = l + 1000000L;
	
			if (isUserSuppliedPrimaryKey(assigneeRoleIdk))
				l = l + 100000L;
	
			if (!actionName.equals(Action.ActionName.MATCH_ALL_ACTION_NAMES.value) &&
				actionVersion != Action.ActionVersion.MATCH_ALL_ACTION_VERSIONS.value)
				l = l + 40000L;
			else if (!actionName.equals(Action.ActionName.MATCH_ALL_ACTION_NAMES.value) && 
				actionVersion == Action.ActionVersion.MATCH_ALL_ACTION_VERSIONS.value)
				l = l + 30000L;
			else if (actionName.equals(Action.ActionName.MATCH_ALL_ACTION_NAMES.value) && 
				actionVersion != Action.ActionVersion.MATCH_ALL_ACTION_VERSIONS.value)
				l = l + 20000L;
	
			if ( isUserSuppliedPrimaryKey(objectIdk) )
				l = l + 1000L;
	
			if ( !objectTableName.equals(Permission.TableName.MATCH_ALL_TABLE_NAMES.value) )
				l = l + 100L;
	
			if ( isUserSuppliedPrimaryKey(objectGroupIdk) )
				l = l + 10L;
	
			if ( status == Status.DENIED.value )
				l = l + 1L;

			return l;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	// --- END CUSTOM CODE ---

}

