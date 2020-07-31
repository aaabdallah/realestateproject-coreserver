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
 * Core Server component for managing basic
 * user information. Extra attributes beyond those here should be
 * added to a separate domain-specific table (e.g. tUserProfiles).
 */
@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<User>",
			entities =
			{
				@EntityResult
				(
					entityClass = User.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "testUser", column = "testUser"),
						@FieldResult(name = "username", column = "username"),
						@FieldResult(name = "password", column = "password"),
						@FieldResult(name = "rankIdk", column = "rankIdk"),
						@FieldResult(name = "status", column = "status"),
						@FieldResult(name = "notes", column = "notes"),
						@FieldResult(name = "systemKey", column = "systemKey")
					}
				)
			}
		)
	}
)
@Entity(name="User") @Table(name="\"tUsers\"")
public class User extends BaseEntity implements Serializable
{
	// The different statuses a user may have.
	public enum Status
	{
		BLOCKED ((short) 0x1),
		HIDDEN ((short) 0x2);

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

	// Regular expressions for usernames and passwords (need constants because
	// these regular expressions are referenced in other parts of the system,
	// especially validation)
	public enum UPRegex
	{
		USERNAME ( "^[\\w\\.%+-]+$|^[\\w\\.%+-]+@[\\w\\.]+\\.(com|COM|gov|GOV|net|NET|org|ORG|info|INFO|biz|BIZ)(\\.[a-zA-Z][a-zA-Z])?$"),
		PASSWORD ( "^[\\w\\.@\\$%#+-]+$");

		public static String[] allValues = null;
		static
		{
			allValues = new String[UPRegex.values().length];
			int i = 0;
			for (UPRegex v : UPRegex.values())
			{
				allValues[i] = v.value;
				i++;
			}
		}

		public final String value;
		UPRegex(String p) { value = p; }
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
	public static final String entityTableName = "\"tUsers\"";

	static public User findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (User) BaseEntity.findByIdk(manager, User.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findUserByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<User>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findUserByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<User>");
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

	static public Map<String, List<MsgArgsPair>> validateUser(User user,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return user.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateTestUser(Boolean testUser)
	{
		return (new FieldValidator()).clearChecks().setName("testUser").setAllowNullElement(false).checkBoolean(testUser);
	}

	static public Map<String, List<MsgArgsPair>> validateUsername(String username)
	{
		return (new FieldValidator()).clearChecks().setName("username").setAllowNullElement(false).setValidMinimum((double) 8).setValidMaximum((double) 100).setValidRegex(UPRegex.USERNAME.value).checkString(username);
	}

	static public Map<String, List<MsgArgsPair>> validatePassword(String password)
	{
		return (new FieldValidator()).clearChecks().setName("password").setAllowNullElement(false).setValidMinimum((double) 8).setValidMaximum((double) 100).setValidRegex(UPRegex.PASSWORD.value).checkString(password);
	}

	static public Map<String, List<MsgArgsPair>> validateRankIdk(Long rankIdk)
	{
		return (new FieldValidator()).clearChecks().setName("rankIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(rankIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateStatus(Short status)
	{
		return (new FieldValidator()).clearChecks().setName("status").setAllowNullElement(false).setValidChoices((Object[]) Status.allValues).setUseValidChoicesAs("bitMasks").checkShort(status);
	}

	static public Map<String, List<MsgArgsPair>> validateNotes(String notes)
	{
		return (new FieldValidator()).clearChecks().setName("notes").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(notes);
	}

	static public Map<String, List<MsgArgsPair>> validateSystemKey(String systemKey)
	{
		return (new FieldValidator()).clearChecks().setName("systemKey").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(systemKey);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("testUser") != null && searchFields.get("testUser").toString().length() > 0 &&
			searchFields.get("testUser").equals(false))
			selectBuilder.addWhere(entityTableName + ".\"testUser\" = false", true);
		if (searchFields.get("testUser") != null && searchFields.get("testUser").toString().length() > 0 &&
			searchFields.get("testUser").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"testUser\" = true", true);
		if (searchFields.get("username") != null && searchFields.get("username").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"username\" LIKE $$%" + searchFields.get("username") + "%$$", true);
		if (searchFields.get("rankIdk") != null && ((Long[]) searchFields.get("rankIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"rankIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("rankIdk"), "", "") + ")", true);
		if (searchFields.get("status") != null && ((Short[]) searchFields.get("status")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"status\" IN (" +
				ArrayHelper.toString((Short[]) searchFields.get("status"), "", "") + ")", true);
		if (searchFields.get("notes") != null && searchFields.get("notes").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"notes\" LIKE $$%" + searchFields.get("notes") + "%$$", true);
		if (searchFields.get("systemKey") != null && searchFields.get("systemKey").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"systemKey\" LIKE $$%" + searchFields.get("systemKey") + "%$$", true);
	}

	// ----- Instance Members -----
	/**
	 * This should not be part of the user's status, since it is faster
	 * to check against a boolean than do a bit comparison.
	 */
	@Basic @Column(name="\"testUser\"") private Boolean testUser = null;
	@Basic @Column(name="\"username\"") private String username = null;
	@Basic @Column(name="\"password\"") private String password = null;
	@Basic @Column(name="\"rankIdk\"") private Long rankIdk = null;
	@Basic @Column(name="\"status\"") private Short status = null;
	@Basic @Column(name="\"notes\"") private String notes = null;
	@Basic @Column(name="\"systemKey\"") private String systemKey = null;

	public User() {}
	public User(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Boolean getTestUser()
	{
		return testUser;
	}
	public void setTestUser(Boolean inputParameter)
	{
		modify();
		testUser = inputParameter;
	}

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String inputParameter)
	{
		modify();
		username = inputParameter;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String inputParameter)
	{
		modify();
		password = inputParameter;
	}

	public Long getRankIdk()
	{
		return rankIdk;
	}
	public void setRankIdk(Long inputParameter)
	{
		modify();
		rankIdk = inputParameter;
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

	public String getNotes()
	{
		return notes;
	}
	public void setNotes(String inputParameter)
	{
		modify();
		notes = inputParameter;
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

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("rankIdkMsg",
			(String) Rank.cacheLookup(rankIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "testUser", testUser);
		collectField(format, dateFormat, fields, "username", username);
		collectField(format, dateFormat, fields, "password", password);
		collectField(format, dateFormat, fields, "rankIdk", rankIdk);
		collectField(format, dateFormat, fields, "status", status);
		collectField(format, dateFormat, fields, "notes", notes);
		collectField(format, dateFormat, fields, "systemKey", systemKey);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("testUser"))
			setTestUser( (Boolean) fields.get("testUser") );
		if (fields.containsKey("username"))
			setUsername( (String) fields.get("username") );
		if (fields.containsKey("password"))
			setPassword( (String) fields.get("password") );
		if (fields.containsKey("rankIdk"))
			setRankIdk( (Long) fields.get("rankIdk") );
		if (fields.containsKey("status"))
			setStatus( ((Number) fields.get("status")).shortValue() );
		if (fields.containsKey("notes"))
			setNotes( (String) fields.get("notes") );
		if (fields.containsKey("systemKey"))
			setSystemKey( (String) fields.get("systemKey") );
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
				(specificFields != null && !exclude && specificFields.contains("testUser")) ||
				(specificFields != null && exclude && !specificFields.contains("testUser")))
			{
				result = 
					validator.clearChecks().setName("testUser").setAllowNullElement(false).checkBoolean(testUser);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("username")) ||
				(specificFields != null && exclude && !specificFields.contains("username")))
			{
				result = 
					validator.clearChecks().setName("username").setAllowNullElement(false).setValidMinimum((double) 8).setValidMaximum((double) 100).setValidRegex(UPRegex.USERNAME.value).checkString(username);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("password")) ||
				(specificFields != null && exclude && !specificFields.contains("password")))
			{
				result = 
					validator.clearChecks().setName("password").setAllowNullElement(false).setValidMinimum((double) 8).setValidMaximum((double) 100).setValidRegex(UPRegex.PASSWORD.value).checkString(password);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("rankIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("rankIdk")))
			{
				result = 
					validator.clearChecks().setName("rankIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(rankIdk);
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

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("notes")) ||
				(specificFields != null && exclude && !specificFields.contains("notes")))
			{
				result = 
					validator.clearChecks().setName("notes").setValidMinimum((double) 0).setValidMaximum((double) 200).checkString(notes);
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

		}

		if (results.isEmpty())
			return null;
		return results;
	}

	protected void onModify()
	{
	}

}

