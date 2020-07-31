package aaacs.coreserver.ejb.entities;

import static aaacs.coreserver.commons.utilities.Scrambler.descrambleKeys;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import aaacs.coreserver.administration.Configurator;
import aaacs.coreserver.commons.validation.FieldValidator;
import aaacs.coreserver.commons.validation.Groups;
import aaacs.coreserver.commons.validation.MsgArgsPair;
import aaacs.coreserver.database.PrimaryKeyHolder;
import aaacs.coreserver.database.SelectBuilder;
import aaacs.coreserver.ejb.entities.support.CacheManager;
import aaacs.coreserver.ejb.entities.support.DefaultValuesManager;
import aaacs.coreserver.commons.exceptions.CSWrapperException;
import aaacs.coreserver.commons.exceptions.CoreServerException;
import aaacs.coreserver.commons.exceptions.ValidationException;


/**
 * @author Ahmed A. Abd-Allah
 * Created on Oct 29, 2006
 *
 * This is the base class that should be extended by any other entity in the Core
 * Server. It is designed to reduce the amount of coding involved for the entities.
 * This is done by placing a number of helper functions in this class that
 * its derived children can refer to. There are also some private data members
 * that are put here because they are common to all entities in this project.
 */
@MappedSuperclass
abstract public class BaseEntity extends PrimaryKeyHolder
{
	// + caching
	// + loads default values when desired
	// + standard columns
	// + field handlers, standard and extra
	// + create one
	// + delete one
	// + find one (by id)
	// + update one (by id)
	// + find by fields
	// + delete by fields
	// + update by fields

	// ----- Static members ---------------------------------------------------
	/**
	 * The following control formatting options when filling all fields into
	 * a hashtable (see getAllFields()). They result in different transformations
	 * on the returned data. For no transformations, pass in zero.
	 * Beside each possible option is a comment showing its "priority"; this
	 * is used to indicate which option wins in case they have conflicting effects
	 * These numbers are not found in the code - they're just here to give you
	 * an idea of how the control logic in the getAllFields functions should
	 * work with these options...
	 * FMT_NONE: no transformation, returns raw data
	 * FMT_STDJAVA: all user-class-based objects transformed to standard Java types
	 * (most likely Strings, Integers, Floats, etc.)
	 * FMT_STRINGS: all data transformed to strings
	 */
	static final public short FMT_NONE = 0x0; // NOTE: priority = 0
	static final public short FMT_STDJAVA = 0x1; // NOTE: priority = 50
	static final public short FMT_STRINGS = 0x2; // NOTE: priority = 100

	// I am unable to get positional parameters to work with S1AS+PostgreSQL... 
	// so we resort to a poor man's positional parameters.
	static final public String _PMPP_ = "__PMPPver100__";

	static public Map<String, List<MsgArgsPair>> validateIdk(Long idk)
	{
		return validateIdk(idk, true); // most cases.
	}

	static public Map<String, List<MsgArgsPair>> validateIdk(Long idk, boolean userSuppliedIdk)
	{
		if (userSuppliedIdk)
			return (new FieldValidator()).setName("idk").setAllowNullElement(false).
			setValidMinimum((double) KEY_HIGHEST_RESERVED+1).checkLong(idk);
		else
			return (new FieldValidator()).setName("idk").setAllowNullElement(false).
			setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(idk);
	}

	static public Map<String, List<MsgArgsPair>> validateGroups(Groups groups)
	{
		return (new FieldValidator()).setName("groups").setAllowNullElement(false).checkGroups(groups);
	}

	static public Map<String, List<MsgArgsPair>> validateMetaFlags(Long metaFlags)
	{
		return (new FieldValidator()).setName("metaFlags").setAllowNullElement(false).checkLong(metaFlags);
	}

	static public Map<String, List<MsgArgsPair>> validateVersion(Short version)
	{
		return (new FieldValidator()).setName("version").setAllowNullElement(false).setValidMinimum(1.0).checkShort(version);
	}

	static public Map<String, List<MsgArgsPair>> validateTimeCreated(Timestamp timeCreated)
	{
		return (new FieldValidator()).setName("timeCreated").setAllowNullElement(false).checkTimestamp(timeCreated);
	}

	static public Map<String, List<MsgArgsPair>> validateTimeLastModified(Timestamp timeLastModified)
	{
		return (new FieldValidator()).setName("timeLastModified").setAllowNullElement(false).checkTimestamp(timeLastModified);
	}

	// ----- Instance members -------------------------------------------------
	/**
	 * The standard columns that are expected in each table in the database.
	 */
	//Using an EmbeddedID is nice but the generated value is not returned properly
	//when the record is created in Sun 1 Application Server...? So we use
	//the underlying type (long) but we (could) limit access to it from the getters
	//and setters... not as elegant, but oh well. 2 days of trial and error
	//got nowhere... Unfortunately this means that we expose the underlyng
	//type of the primary key to the outside world.
	//@EmbeddedId private SimpleKey idkSimpleKey = new SimpleKey();

	// As of Feb 26, 2008, the following auto-generation annotations (SequenceGenerator
	// and GeneratedValue) do not work with the new Toplink Essentials jar file. Reverted
	// to the 'mid' version - not the 'old' version.
	// Specifically: Oracle TopLink Essentials - 9.1 (Build b36-rc)
	@Id 
	@SequenceGenerator(name="qidk", sequenceName="qidk") 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="qidk")
	@Column(updatable=false, name="\"idk\"") private Long idk = KEY_UNINITIALIZED;
	@Version @Column(name="\"lockingVersion\"") private Integer lockingVersion; // container managed. 
	//@Basic private String groups = (new Groups()).toString();
	@Embedded private Groups groups = new Groups();
	@Basic @Column(name="\"metaFlags\"") private Long metaFlags = (long) 0;
	@Basic @Column(name="\"version\"") private Short version = 1;
	@Basic @Column(insertable=false, updatable=false, name="\"timeCreated\"") private Timestamp timeCreated = null;
	@Basic @Column(name="\"timeLastModified\"") private Timestamp timeLastModified = null;

	/**
	 * The "dirty" bit: has the bean been modified since being loaded?
	 */
	@Transient private boolean modified = false;
	/**
	 * The validation bit: has the bean been validated since being created, 
	 * loaded, or modified?
	 */
	@Transient private boolean validated = false;
	/**
	 * Controls whether validation is enabled for the entity or not. 
	 */
	@Transient private boolean validating = true;
	/**
	 * Controls whether to check if the IDK is a user supplied one during
	 * validation.
	 */
	@Transient private boolean checkUserSuppliedIdk = false;
	/**
	 * Controls whether to look up the message resource keys for specific fields
	 * (usually idk fields).
	 */
	@Transient private boolean lookupMessageResourceKeys = false;
	/**
	 * Controls what fields to specify during validation.
	 */
	@Transient private List<String> specificValidationFields = null;
	/**
	 * How to use the specified validation fields: exclude or include?
	 */
	@Transient private boolean excludeValidationFields = false;
	/**
	 * Contains the results of the last validation attempt.
	 */
	@Transient private Map<String, List<MsgArgsPair>> validationResults = null;

	public BaseEntity()
	{
		// don't call modify here because perhaps the entity is not ready for
		// responding to a modification notice...
		modified = true;
		validated = false;
		
		// it's an open question whether allowing validation to be turned off
		// globally so easily is a wise decision.
		validating = Configurator.getBasicValidationEnabled();
		//(Configurator.getMainLogger()).trace("Entered constructor on behalf of " + this.getClass().getName());
	}

	/**
	 * @return the table associated with that bean.
	 */
	abstract public String getEntityTableName();
	/**
	 * @return the resources file associated with that bean.
	 */
	abstract public String getEntityResourceFile();
	
	// This is abstract to allow derived classes to hook into it whatever they want
	// when the modified bit is set to true
	abstract protected void onModify();
	protected void modify()
	{
		modified = true;
		validated = false;
		onModify();
	}
	public boolean getModified() { return modified; }
	public boolean getValidated() { return validated; }
	public boolean getLookupMessageResourceKeys() { return lookupMessageResourceKeys; }

	public boolean getValidating() { return validating; }
	public boolean getCheckUserSuppliedIdk() { return checkUserSuppliedIdk; }
	public List<String> getSpecificValidationFields() { return specificValidationFields; }
	public boolean getExcludeValidationFields() { return excludeValidationFields; }
	public Map<String, List<MsgArgsPair>> getValidationResults() { return validationResults; }

	public void setLookupMessageResourceKeys(boolean lookupMessageResourceKeys) { this.lookupMessageResourceKeys = lookupMessageResourceKeys; }
	public void setValidating(boolean validate) {this.validating = validate; }
	public void setCheckUserSuppliedIdk(boolean checkUserSuppliedIdk) { this.checkUserSuppliedIdk = checkUserSuppliedIdk; }
	public void setSpecificValidationFields(List<String> fields) { specificValidationFields = fields; }
	public void setExcludeValidationFields(boolean exclude) { excludeValidationFields = exclude; }
	protected void setValidationResults(Map<String, List<MsgArgsPair>> validationResults)
	{ this.validationResults = validationResults; }

	public Long getIdk() { return idk; } // a much simpler world post-scrambling-removal.
	public Integer getLockingVersion() { return lockingVersion; }
	public Groups getGroups() { return groups; }
	public Long getMetaFlags() { return metaFlags; }
	public Timestamp getTimeCreated() {	return timeCreated; }
	public Timestamp getTimeLastModified() { return timeLastModified; }
	public Short getVersion() { return version; }

	public void setIdk(Long inIdk) 
		throws CoreServerException
	{ 
		if (!isUserSuppliedPrimaryKey(inIdk))
		{
			Object args[] = { inIdk };
			throw new CoreServerException("database.InvalidUnscrambledKey", args);
		}
		modify();
		idk = inIdk; 
	}
	// We do not set modified to true since lockingVersion is an EJB system property
	public void setLockingVersion(Integer lockingVersion) { this.lockingVersion = lockingVersion; }
	public void setGroups(Groups g) { modify(); groups = g; }
	public void setMetaFlags(Long m) { modify(); metaFlags = m; }
	public void setTimeCreated(Timestamp tc) { /*modify(); timeCreated = tc;*/ } // never change
	public void setTimeLastModified(Timestamp tlm) { modify(); timeLastModified = tlm; }
	public void setVersion(Short s)	{ modify(); version = s; }

	// ------------------------------------------------------------------------
	// ----- Methods for getting the entity's attributes ----------------------
	// ------------------------------------------------------------------------

	/**
	 * @param format Controls formatting transformations on the data (whether to generate
	 * native objects or convert everything to Strings or convert only server-specific types
	 * into ordinary Strings - i.e. generate standard Java types from all fields). 
	 * Other transformations possible: lookup the ID's or not given a particular locale - 
	 * not implemented!
	 * @param fields A hashtable to store the standard base attributes of the entity. 
	 * The hashtable data may undergo some transformations based on the 
	 * <code>format</code> input paramete.
	 * 
	 */
	private void getBaseFields(short format, DateFormat dateFormat, 
		Hashtable<String, Object> fields) 
	{
		if ((format & FMT_STRINGS) > 0)
			fields.put("idk", idk.toString());
		else
			fields.put("idk", idk);

		if ((format & FMT_STRINGS) > 0)
			fields.put("lockingVersion", lockingVersion.toString());
		else
			fields.put("lockingVersion", lockingVersion);

		if (groups != null)
		{
			if ((format & FMT_STRINGS) > 0 || (format & FMT_STDJAVA) > 0)
				fields.put("groups", groups.toString());
			else
				fields.put("groups", groups);
		}

		if ((format & FMT_STRINGS) > 0)
			fields.put("metaFlags", metaFlags.toString());
		else
			fields.put("metaFlags", metaFlags);

		if ((format & FMT_STRINGS) > 0)
			fields.put("version", version.toString());
		else
			fields.put("version", version);

		if (timeCreated != null)
		{
			if ((format & FMT_STRINGS) > 0)
				fields.put("timeCreated", dateFormat.format(timeCreated));
			else
				fields.put("timeCreated", timeCreated);
		}

		if (timeLastModified != null)
		{
			if ((format & FMT_STRINGS) > 0)
				fields.put("timeLastModified", dateFormat.format(timeLastModified));
			else
				fields.put("timeLastModified", timeLastModified);
		}
	}	

	/**
	 * Helper function for use in derived entities to collect their extra fields
	 * into a hashtable of fields.
	 * @param format the formatting transformation to use
	 * @param dateFormat the date/time format to use
	 * @param fields the hashtable to store the new field in
	 * @param fieldName the field name
	 * @param field the field value
	 */
	protected void collectField(short format, DateFormat dateFormat,
		Hashtable<String,Object> fields, String fieldName, Object field)
	{
		if (field != null)
		{				
			if (field.getClass().equals(String.class))
				fields.put(fieldName, field);
			else if (field.getClass().equals(Timestamp.class) ||
					 field.getClass().equals(java.sql.Date.class) ||
					 field.getClass().equals(java.util.Date.class))
			{
				if ((format & FMT_STRINGS) > 0)
					fields.put(fieldName, dateFormat.format(field));
				else
					fields.put(fieldName, field);					
			}
			else if (field.getClass().equals(Short.class) ||
					 field.getClass().equals(Integer.class) ||
					 field.getClass().equals(Long.class) ||
					 field.getClass().equals(Float.class) ||
					 field.getClass().equals(Double.class) ||
					 field.getClass().equals(Boolean.class))
			{
				if ((format & FMT_STRINGS) > 0)
					fields.put(fieldName, field.toString());
				else
					fields.put(fieldName, field);					
			}
			else
			{
				// MAKE SURE ANY CUSTOM TYPES YOU USE AS AN EJB PROPERTY
				// (I.E. TABLE COLUMN) HAS A LEGITIMATE "toString()" FUNCTION!!!!
				if ((format & FMT_STRINGS) > 0 || (format & FMT_STDJAVA) > 0)
					fields.put(fieldName, field.toString());
				else
					fields.put(fieldName, field);					
			}
		}
	}

	/**
	 * The derived class should implement this to get the extra fields and store them
	 * into the passed in hashtable.
	 * @see #getAllFields()
	 */
	abstract protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields);

	/**
	 * The derived class should implement this to add any message resources for idk
	 * attributes to the passed in hashtable.
	 * @see #getAllFields()
	 */
	abstract protected void getMessageResources(Hashtable<String, Object> fields);
	
	/**
	 * @param format Controls formatting transformations on the data (whether to generate
	 * native objects or convert everything to Strings or convert only server-specific types
	 * into ordinary Strings - i.e. generate standard Java types from all fields). 
	 * Other transformations possible: lookup the ID's or not given a particular locale - 
	 * not implemented!
	 * @return A hashtable containing all the attributes of the entity. The hashtable data
	 * may undergo some transformations based on the <code>format</code> input parameter.
	 * Idk's that have message resources associated with them have their message resource
	 * lookup values added to the hashtable with the string "Msg" prepended to the
	 * key (the name of the attribute in lower case). 
	 */
	public Map<String, Object> getAllFields(short format) 
		throws CoreServerException
	{
		Hashtable<String, Object> fields = new Hashtable<String, Object>();

		try
		{
			DateFormat dateFormat = 
				DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);

			getBaseFields(format, dateFormat, fields);
			getExtraFields(format, dateFormat, fields);
			getMessageResources(fields);
			return fields;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Object args[] = { this.getClass().getName() };
			throw new CoreServerException("baseEntity.DTO.NativeToDTOFailure", args, e);
		}
	}

	/**
	 * Gets all the fields as Java standard types.
	 */
	public Map<String, Object> getAllFields()
		throws CoreServerException
	{ 
		return getAllFields(FMT_STDJAVA);
	}
	
	// ------------------------------------------------------------------------
	// ----- Methods for validating the entity's attributes -------------------
	// ------------------------------------------------------------------------
	/**
	 * Validate the base fields of any entity. The results are passed back as a
	 * LinkedHashMap (Strings to Strings), where the keys represent the offending 
	 * attributes or column names, and the values are the reasons for invalidation.
	 * This method should be called by derived classes when they attempt to 
	 * validating their fields in general.
	 * 
	 * The parameter specificFields can be used to either exclude or include
	 * fields to validate. If it is null, all fields are validated. The decision
	 * to include or exclude is controlled by the boolean exclude, false meaning
	 * to include, true meaning to exclude.
	 * 
	 * If there are no errors, the return result is null.
	 * 
	 * @param userSuppliedIDK special case: check IDK Primary Key for being user supplied?
	 * @param specificFields fields to validate
	 * @param exclude exclude the specific fields, or include them
	 * @return null if properly valid, otherwise a LinkedHashMap of fields-to-reasons
	 */
	private Map<String, List<MsgArgsPair>> validateBaseFields(boolean userSuppliedIdk,
		List<String> specificFields, boolean exclude)
	{
		Map<String, List<MsgArgsPair>> result = null;
		FieldValidator fieldValidator = new FieldValidator();
		Map<String, List<MsgArgsPair>> results = 
			new LinkedHashMap<String, List<MsgArgsPair>>();
		
		if (validating)
		{
			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("idk")) ||
				(specificFields != null && exclude && !specificFields.contains("idk")))
			{
				if (userSuppliedIdk)
					result = fieldValidator.clearChecks().setName("idk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED+1).checkLong(idk);
				else
					result = fieldValidator.clearChecks().setName("idk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(idk);

				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("groups")) ||
				(specificFields != null && exclude && !specificFields.contains("groups")))
			{
				result = fieldValidator.clearChecks().setName("groups").setAllowNullElement(false).checkGroups(groups);
				if (result != null)
					results.putAll(result);
			}
			
			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("metaFlags")) ||
				(specificFields != null && exclude && !specificFields.contains("metaFlags")))
			{
				result = fieldValidator.clearChecks().setName("metaFlags").setAllowNullElement(false).checkLong(metaFlags);
				if (result != null)
					results.putAll(result);
			}
			
			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("version")) ||
				(specificFields != null && exclude && !specificFields.contains("version")))
			{
				result = fieldValidator.clearChecks().setName("version").setAllowNullElement(false).setValidMinimum(1.0).checkShort(version);
				if (result != null)
					results.putAll(result);
			}
			// No need to validate timeCreated or timeLastModified since they
			// are generated by the system and not touchable by the user.
			// Likewise for lockingVersion.
		}

		if (results.isEmpty())
			return null;
		return results;
	}

	/**
	 * The derived class should implement this to return a null Hashtable if the
	 * extra fields are valid. Otherwise it should return a hashtable whose keys are the
	 * parameters that are invalid, and whose values are Strings describing the precise
	 * error.
	 * 
	 * @param specificFields fields to validate
	 * @param exclude exclude the specific fields, or include them
	 * @return a hashtable of validation error strings. Null actually indicates success here.
	 * The keys to the errors are the parameter names.
	 */
	protected abstract Map<String, List<MsgArgsPair>> validateExtraFields(
		List<String> specificFields, boolean exclude);

	/**
	 * Validates all the fields in the entity.
	 * @see #validateBaseFields(boolean)
	 * @see #validateExtraFields()
	 * @param userSuppliedIDK indicates whether to check for a user supplied IDK or not
	 * @param specificFields fields to validate
	 * @param exclude exclude the specific fields, or include them
	 * @return a hashtable containing all the results - null if no errors.
	 */
	public Map<String, List<MsgArgsPair>> validateAllFields(boolean userSuppliedIDK,
		List<String> specificFields, boolean exclude)
	{
		if (!validating) return null;

		Map<String, List<MsgArgsPair>> baseResults = 
			validateBaseFields(userSuppliedIDK, specificFields, exclude);
		Map<String, List<MsgArgsPair>> extraResults = 
			validateExtraFields(specificFields, exclude);

		Map<String, List<MsgArgsPair>> allResults = 
			new LinkedHashMap<String, List<MsgArgsPair>>();
		if (baseResults != null && baseResults.size() > 0)
			allResults.putAll(baseResults);
		if (extraResults != null && extraResults.size() > 0)
			allResults.putAll(extraResults);
		
		if (allResults.size() > 0)
			return allResults;
		validated = true;
		return null;
	}

	// ------------------------------------------------------------------------
	// ----- Methods for loading objects from hashtables ----------------------
	// ------------------------------------------------------------------------
	/**
	 * @param fields A map containing none, some, or all of the base 
	 * fields (id, groups, etc.). This function will set as many of the fields 
	 * as are found in the hashtable; no more and no less. The idea is that 
	 * the user can take advantage of any default values that are being 
	 * generated at the database layer to avoid having to set every field in a 
	 * bean (some beans have 50+ fields...). The fields in the hashtable are 
	 * all assumed to be Java SDK types (i.e. String, Short, Integer, etc.).
	 * 
	 * This method should be called from every derived bean's own 
	 * <code>loadAllFields</code> method.
	 */
	private void loadBaseFields(Map<String, Object> fields)
		throws CoreServerException
	{
		// the modified bit is set in the individual setters
		if (fields == null || fields.isEmpty())
			return;
		
		if (fields.containsKey("idk"))
			setIdk( (Long) fields.get("idk") );
		/* LEFT IN FOR HISTORICAL JUST-IN-CASE. But scrambling is no longer
		 * done at the Core Server layer.
		{
			Object value = fields.get("idk");
			//This code is PURPOSELY COMMENTED OUT. Primary keys should be
			//passed in as scrambled strings, period.
			//if (value.getClass().equals(Long.class) )
			//	setIdk(new PrimaryKeyHolder((PrimaryKeyHolder) value));
			
			if (value.getClass().equals(String.class))
				setIdk((String) value);
		}
		*/
		if (fields.containsKey("lockingVersion"))
			setLockingVersion( (Integer) fields.get("lockingVersion") );
		if (fields.containsKey("groups"))
		{
			Object value = fields.get("groups");

			if (value.getClass().equals(Groups.class))
				setGroups(new Groups((Groups) value));
			else
				setGroups(new Groups((String) fields.get("groups")));
		}
		if (fields.containsKey("metaFlags"))
			setMetaFlags( (Long) fields.get("metaFlags") );
		if (fields.containsKey("version"))
			setVersion( ((Number) fields.get("version")).shortValue() );

		// timeCreated is purposely ignored
		// timeLastModified is set right before persisting/updating
	}

	/**
	 * The derived class should implement this to load all *or some* of the fields from
	 * a hashtable. Fields that are missing from the <code>fields</code> input should
	 * be left <strong>untouched</strong>.
	 * @see #loadBaseFields(Map)
	 */
	protected abstract void loadExtraFields(Map<String, Object> fields);

	/**
	 * Loads all fields from the passed in hashtable. NOTE THAT IF THE HASHTABLE IS
	 * MISSING SOME EXTRA FIELDS, THAT IS OK: IT IS USEFUL FUNCTIONALITY.
	 * @see #loadBaseFields(Map)
	 * @param fields the hashtable of column names (Strings) to values (Objects)
	 * @throws CoreServerException
	 */
	private void loadAllFields(Map<String, Object> fields)
		throws CoreServerException
	{
		loadBaseFields(fields);
		loadExtraFields(fields);
	}

	/**
	 * Helper method for setting all the fields of a particular entity. This is
	 * different from {@link #loadAllFields(Hashtable)} because it includes dealing
	 * with default values.
	 * 
	 * Thus, there are two possible sources for the values to use: the default values 
	 * for that table, and the <code>inputFields</code> parameter. This is
	 * useful for filling in the entity quickly before persisting it.
	 * 
	 * There are four cases to consider:
	 * <br/>
	 * 1. <code>inputFields</code> is null, <code>useDefaults</code> is false:
	 * do nothing.
	 * 2. <code>inputFields</code> is null, <code>useDefaults</code> is true: 
	 * use the default values.
	 * 3. <code>inputFields</code> is not null, <code>useDefaults</code> is false:
	 * write whatever fields are in the <code>inputFields</code> and leave the 
	 * rest of the fields alone
	 * 4. <code>inputFields</code> is not null, <code>useDefaults</code> is true:
	 * write whatever fields are in the <code>inputFields</code> and fill the 
	 * rest of the fields from the default values
	 * 	 
	 * @param inputFields an input hashtable of fields to use to fill in the
	 * entity's fields
	 * @param useDefaults whether to resort to the defaults or not
	 * @throws CoreServerException
	 */
	public void setAllFields(Map<String, Object> inputFields, 
		boolean useDefaults)
		throws CoreServerException
	{
		try
		{
			// IMPORTANT: It is the derived bean's responsibility to make sure that its extra
			// fields are set properly before getting to this point; i.e. before calling this
			// method. Of course, leaving fields unset on purpose, and relying on the
			// parameter "inputFields" and the default values is fine.
			if (inputFields == null && !useDefaults )
			{
				// Here the assumption is that the data members of the bean do contain the
				// latest valid values.
			}
			else if (inputFields == null && useDefaults )
			{
				// Here the assumption is that the user wants to fill in everything from the
				// default values, overriding the current fields in the entity in the process.
				Map<String, Object> defaults = 
					DefaultValuesManager.getDefaults(getEntityTableName());
	
				// Ok, now we can do what we planned: copy the values from the defaults
				// into the bean's data members
				loadAllFields(defaults);
			}
			else if (inputFields != null && !useDefaults)
			{
				loadAllFields(inputFields);
			}
			else // if (inputFields != null && useDefaults)
			{
				// Here the assumption is that the user wants to quickly set everything from
				// the passed in hashtable "inputFields". This entails loading the bean's data
				// members first from the hashtable 
				
				// But before doing all this: we need to worry about a possible wrinkle: default
				// values. If the "inputFields" parameter is missing values (as we expect the user
				// to occasionally do - perhaps often in some bean's cases), then we need to fill
				// in the default values. NOTE THAT THIS STEP WILL ALTER THE INPUT PARAMETER
				// "inputFields".
				Map<String, Object> defaults = 
					DefaultValuesManager.getDefaults(getEntityTableName());
				for (String key : defaults.keySet())
				{
					if (!inputFields.containsKey(key))
						inputFields.put(key, defaults.get(key));
				}
	
				// Ok, now we can do what we planned: copy the values from the "inputFields"
				// into the bean's data members
				loadAllFields(inputFields);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Object args[] = { this.getClass().getName() };
			throw new CoreServerException("baseEntity.Fill.UnableToSetAllFields", args, e); 
		}
	}

	// ------------------------------------------------------------------------
	// ----- Methods and helper methods for finding objects -------------------
	// ------------------------------------------------------------------------
	
	static public String conditionFromFields(Map<String,Object> searchFields)
	{
		StringBuilder sb = new StringBuilder(64);
		boolean foundField = false, foundChoice = false;
		Object[] choices = null;
		
		if (searchFields != null && searchFields.size() > 0)
		{
			sb.append(" (");
			for (String name : searchFields.keySet())
			{
				if (foundField)
					sb.append(" AND ");
				if (searchFields.get(name).getClass().isArray())
				{
					sb.append(name + " IN (");
	
					choices = (Object[]) searchFields.get(name);
					foundChoice = false;
					for (Object choice : choices)
					{
						if (foundChoice) sb.append(", '" + choice.toString() + "'");
						else sb.append("'" + choice.toString() + "'");
						foundChoice = true;
					}
					sb.append(") ");
				}
				else
					sb.append(name + " = '" + searchFields.get(name).toString() + "' ");
				foundField = true;
			}
			sb.append(") ");
		}
		return sb.toString();
	}

	static public Object findByIdk(EntityManager manager, Class entityClass, Long inputIdk)
		throws CoreServerException
	{
		return manager.find((Class<?>) entityClass, inputIdk);
	}

	static public List findByFields(EntityManager manager, String[] tableNames, 
		String[] columnNames, Map<String,Object> searchFields, 
		String sqlResultSetMapping)
	{
		return findByFields(true, manager, tableNames, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findByFields(boolean maskReservedIdks, 
		EntityManager manager, String[] tableNames, 
		String[] columnNames, Map<String,Object> searchFields, 
		String sqlResultSetMapping)
	{
		if (manager == null)
			return null;

		String tables = "";
		boolean tFound = false;
		for (String tableName : tableNames)
		{
			if (tFound) tables += ", ";
			tables += tableName;
			tFound = true;
		}			
		
		String columns = "*";
		if (columnNames != null && columnNames.length > 0)
		{
			columns = "";
			boolean cFound = false;
			for (String columnName : columnNames)
			{
				if (cFound) columns += ", ";
				columns += columnName;
				cFound = true;
			}			
		}

		String findString = "SELECT " + columns + " FROM " + tables;

		if (maskReservedIdks)
			findString += " WHERE idk > " + KEY_HIGHEST_RESERVED;
		if (searchFields != null && !searchFields.isEmpty())
			findString += (maskReservedIdks ? " AND " + conditionFromFields(searchFields) : 
				" WHERE " + conditionFromFields(searchFields));
		
		findString = descrambleKeys(findString);

		// System.out.println("\n\n\n\tQuery: " + findString + "\n\n\n");

		Query query = null;
		if (sqlResultSetMapping != null)
			query = manager.createNativeQuery(findString, sqlResultSetMapping);
		else
			query = manager.createNativeQuery(findString);
		
		return query.getResultList();
	}

	static public List findByCondition(EntityManager manager, String[] tableNames,
		String[] columnNames, String condition, List<Object> conditionParameters, 
		String sqlResultSetMapping)
	{
		return findByCondition(true, manager, tableNames, columnNames, condition,
			conditionParameters, sqlResultSetMapping);
	}

	static public List findByCondition(boolean maskReservedIdks,
		EntityManager manager, String[] tableNames,
		String[] columnNames, String condition, List<Object> conditionParameters, 
		String sqlResultSetMapping)
	{
		if (manager == null)
			return null;

		String tables = "";
		boolean tFound = false;
		for (String tableName : tableNames)
		{
			if (tFound) tables += ", ";
			tables += tableName;
			tFound = true;
		}			
		
		String columns = "*";
		if (columnNames != null && columnNames.length > 0)
		{
			columns = "";
			boolean cFound = false;
			for (String columnName : columnNames)
			{
				if (cFound) columns += ", ";
				columns += columnName;
				cFound = true;
			}			
		}

		String findString = "SELECT " + columns + " FROM " + tables;

		if (maskReservedIdks)
			findString += " WHERE idk > " + KEY_HIGHEST_RESERVED;
		if (condition != null && !condition.trim().equals(""))
		{
			condition = 
				condition.replaceFirst("^\\s*[wW][hH][eE][rR][eE]\\s", "");
			findString += (maskReservedIdks ? " AND " + condition.trim() : 
				" WHERE " + condition.trim());
		}

		if (conditionParameters != null && !conditionParameters.isEmpty())
		{
			for (Object value : conditionParameters)
				findString = findString.replaceFirst(_PMPP_, "'" + value.toString() + "'");				
		}

		findString = descrambleKeys(findString);

		// System.out.println("\n\n\nFindString:\n\n" + findString + "\n\n\n");
		Query query = null;

		if (sqlResultSetMapping != null)
			query = manager.createNativeQuery(findString, sqlResultSetMapping);
		else
			query = manager.createNativeQuery(findString);

		return query.getResultList();
	}

	static public List findByQuery(EntityManager manager, String queryString, 
		List<Object> conditionParameters, String sqlResultSetMapping)
	{
		return findByQuery(true, manager, queryString, conditionParameters,
			sqlResultSetMapping);
	}
	
	static public List findByQuery(boolean maskReservedIdks,
		EntityManager manager, String queryString, 
		List<Object> conditionParameters, String sqlResultSetMapping)
	{
		if (manager == null)
			return null;

		if (queryString == null || queryString.trim().equals("") ||
			!queryString.matches("^\\s*[sS][eE][lL][eE][cC][tT]\\s.*"))
			return null;

		if (maskReservedIdks)
		{
			if (queryString.matches("^\\s*[sS][eE][lL][eE][cC][tT]\\s+.*\\s+[fF][rR][oO][mM]\\s+[^\\s]+\\s+[wW][hH][eE][rR][eE].*"))
			{
				queryString = 
					queryString.replaceFirst("\\s*[sS][eE][lL][eE][cC][tT]\\s+([^\\s]+)\\s+[fF][rR][oO][mM]\\s+([^\\s]+)\\s+[wW][hH][eE][rR][eE](.*)", 
					"SELECT $1 FROM $2 WHERE idk > " + KEY_HIGHEST_RESERVED +
					" AND $3");
			}
			else if (queryString.matches("^\\s*[sS][eE][lL][eE][cC][tT]\\s+.*\\s+[fF][rR][oO][mM]\\s+[^\\s]+\\s*"))
			{
				queryString = queryString + " WHERE idk > " + KEY_HIGHEST_RESERVED;
			}
		}

		if (conditionParameters != null && !conditionParameters.isEmpty())
		{
			for (Object value : conditionParameters)
				queryString = queryString.replaceFirst(_PMPP_, "'" + value.toString() + "'");				
		}

		queryString = descrambleKeys(queryString);

		Query query = null;
		if (sqlResultSetMapping != null)
			query = manager.createNativeQuery(queryString, sqlResultSetMapping);
		else
			query = manager.createNativeQuery(queryString);
		
		return query.getResultList();		
	}
	
	static public List findByQuery(boolean maskReservedIdks,
		EntityManager manager, SelectBuilder selectBuilder, 
		List<Object> conditionParameters, String sqlResultSetMapping)
	{
		if (manager == null)
			return null;

		if (selectBuilder == null)
			return null;

		if (maskReservedIdks)
			selectBuilder.addWhere("idk > " + KEY_HIGHEST_RESERVED, true, true);
		
		String queryString = selectBuilder.toString();

		if (conditionParameters != null && !conditionParameters.isEmpty())
		{
			for (Object value : conditionParameters)
				queryString = queryString.replaceFirst(_PMPP_, "'" + value.toString() + "'");				
		}

		queryString = descrambleKeys(queryString);

		Query query = null;
		if (sqlResultSetMapping != null)
			query = manager.createNativeQuery(queryString, sqlResultSetMapping);
		else
			query = manager.createNativeQuery(queryString);
		
		return query.getResultList();
	}
	
	// ------------------------------------------------------------------------
	// ----- Methods for removing objects -------------------------------------
	// ------------------------------------------------------------------------

	/**
	 * The following method is a BULK removal operation. It does not update
	 * the persistence context, so what is in RAM will not necessarily be
	 * synchronized with what is in the database after this operation during
	 * that context. The following quote, though originally intended for the
	 * persistence API query language, is applicable even when using SQL:
	 * 
	 * "Caution should be used when executing bulk update or delete operations 
	 * because they may result in inconsistencies between the database and the 
	 * entities in the active persistence context. In general, bulk update and 
	 * delete operations should only be performed within a separate transaction 
	 * or at the beginning of a transaction (before entities have been accessed 
	 * whose state might be affected by such operations)." --p 104, Java
	 * Persistence API, version 3.0
	 */
	static protected int removeByFields(EntityManager manager, String tableName,
		Map<String,Object> searchFields)
	{
		return removeByFields(true, manager, tableName, searchFields);
	}
	
	static protected int removeByFields(boolean maskReservedIdks,
		EntityManager manager, String tableName,
		Map<String,Object> searchFields)
	{
		if (manager == null)
			return 0;

		String deleteString = "DELETE FROM " + tableName;

		if (maskReservedIdks)
			deleteString += " WHERE idk > " + KEY_HIGHEST_RESERVED;
		if (searchFields != null && !searchFields.isEmpty())
			deleteString += (maskReservedIdks ? " AND " + conditionFromFields(searchFields) : 
				" WHERE " + conditionFromFields(searchFields));

		deleteString = descrambleKeys(deleteString);
		
		Query query = manager.createNativeQuery(deleteString);
		
		return query.executeUpdate();
	}

	/**
	 * The following method is a BULK removal operation. It does not update
	 * the persistence context, so what is in RAM will not necessarily be
	 * synchronized with what is in the database after this operation during
	 * that context. The following quote, though originally intended for the
	 * persistence API query language, is applicable even when using SQL:
	 * 
	 * "Caution should be used when executing bulk update or delete operations 
	 * because they may result in inconsistencies between the database and the 
	 * entities in the active persistence context. In general, bulk update and 
	 * delete operations should only be performed within a separate transaction 
	 * or at the beginning of a transaction (before entities have been accessed 
	 * whose state might be affected by such operations)." --p 104, Java
	 * Persistence API, version 3.0
	 */
	static protected int removeByCondition(EntityManager manager, String tableName,
		String condition, List<Object> conditionParameters)
	{
		return removeByCondition(true, manager, tableName, condition,
			conditionParameters);
	}
	
	static protected int removeByCondition(boolean maskReservedIdks,
		EntityManager manager, String tableName,
		String condition, List<Object> conditionParameters)
	{
		if (manager == null)
			return 0;

		String deleteString = "DELETE FROM " + tableName;		

		if (maskReservedIdks)
			deleteString += " WHERE idk > " + KEY_HIGHEST_RESERVED;
		if (condition != null && !condition.trim().equals(""))
		{
			condition = 
				condition.replaceFirst("^\\s*[wW][hH][eE][rR][eE]\\s", "");
			deleteString += (maskReservedIdks ? " AND " + condition.trim() : 
				" WHERE " + condition.trim());
		}

		if (conditionParameters != null && !conditionParameters.isEmpty())
		{
			for (Object value : conditionParameters)
				deleteString = deleteString.replaceFirst(_PMPP_, "'" + value.toString() + "'");				
		}

		deleteString = descrambleKeys(deleteString);
		
		Query query = manager.createNativeQuery(deleteString);
		
		return query.executeUpdate();
	}

	// ------------------------------------------------------------------------
	// ----- Methods for updating objects -------------------------------------
	// ------------------------------------------------------------------------

	/**
	 * The following method is a BULK update operation. It does not update
	 * the persistence context, so what is in RAM will not necessarily be
	 * synchronized with what is in the database after this operation during
	 * that context. The following quote, though originally intended for the
	 * persistence API query language, is applicable even when using SQL:
	 * 
	 * "Caution should be used when executing bulk update or delete operations 
	 * because they may result in inconsistencies between the database and the 
	 * entities in the active persistence context. In general, bulk update and 
	 * delete operations should only be performed within a separate transaction 
	 * or at the beginning of a transaction (before entities have been accessed 
	 * whose state might be affected by such operations)." --p 104, Java
	 * Persistence API, version 3.0
	 * 
	 * One more note: this method is inherently unsafe because it does not
	 * guarantee validated data in the <code>fieldsToSet</code>. Hence, this
	 * method is not to be called unless the user has already validated that
	 * parameter.
	 */
	static protected int updateByFields(EntityManager manager, String tableName,
		Map<String,Object> fieldsToSet, Map<String,Object> searchFields)
	{
		return updateByFields(true, manager, tableName, fieldsToSet, searchFields);
	}
	
	static protected int updateByFields(boolean maskReservedIdks,
		EntityManager manager, String tableName,
		Map<String,Object> fieldsToSet, Map<String,Object> searchFields)
	{
		if (manager == null || fieldsToSet == null || fieldsToSet.size() == 0)
			return 0;

		String updateString = "UPDATE " + tableName + " SET ";		
		int k=1;

		for (String key : fieldsToSet.keySet())
		{
			if (k != 1)
				updateString += ", ";
			updateString = updateString + key + " = '" + fieldsToSet.get(key).toString() + "' ";
			k++;
		}

		if (maskReservedIdks)
			updateString += " WHERE idk > " + KEY_HIGHEST_RESERVED;
		if (searchFields != null && !searchFields.isEmpty())
			updateString += (maskReservedIdks ? " AND " + conditionFromFields(searchFields) : 
				" WHERE " + conditionFromFields(searchFields));

		updateString = descrambleKeys(updateString);
		
		Query query = manager.createNativeQuery(updateString);
		
		return query.executeUpdate();
	}

	/**
	 * The following method is a BULK update operation. It does not update
	 * the persistence context, so what is in RAM will not necessarily be
	 * synchronized with what is in the database after this operation during
	 * that context. The following quote, though originally intended for the
	 * persistence API query language, is applicable even when using SQL:
	 * 
	 * "Caution should be used when executing bulk update or delete operations 
	 * because they may result in inconsistencies between the database and the 
	 * entities in the active persistence context. In general, bulk update and 
	 * delete operations should only be performed within a separate transaction 
	 * or at the beginning of a transaction (before entities have been accessed 
	 * whose state might be affected by such operations)." --p 104, Java
	 * Persistence API, version 3.0
	 * 
	 * One more note: this method is inherently unsafe because it does not
	 * guarantee validated data in the <code>fieldsToSet</code>. Hence, this
	 * method is not to be called unless the user has already validated that
	 * parameter.
	 */
	static protected int updateByCondition(EntityManager manager, String tableName,
		LinkedHashMap<String,Object> fieldsToSet, 
		String condition, List<Object> conditionParameters)
	{
		return updateByCondition(true, manager, tableName, fieldsToSet, condition,
			conditionParameters);
	}

	static protected int updateByCondition(boolean maskReservedIdks,
		EntityManager manager, String tableName,
		LinkedHashMap<String,Object> fieldsToSet, 
		String condition, List<Object> conditionParameters)
	{
		if (manager == null || fieldsToSet == null || fieldsToSet.size() == 0)
			return 0;

		String updateString = "UPDATE " + tableName + " SET ";		
		int k=1;

		for (String key : fieldsToSet.keySet())
		{
			if (k != 1)
				updateString += ", ";
			updateString = updateString + key + " = '" + fieldsToSet.get(key).toString() + "' ";
			k++;
		}

		if (maskReservedIdks)
			updateString += " WHERE idk > " + KEY_HIGHEST_RESERVED;
		if (condition != null && !condition.trim().equals(""))
		{
			condition = 
				condition.replaceFirst("^\\s*[wW][hH][eE][rR][eE]\\s", "");
			updateString += (maskReservedIdks ? " AND " + condition.trim() : 
				" WHERE " + condition.trim());
		}

		if (conditionParameters != null && !conditionParameters.isEmpty())
		{
			for (Object value : conditionParameters)
				updateString = updateString.replaceFirst(_PMPP_, "'" + value.toString() + "'");				
		}

		updateString = descrambleKeys(updateString);
		
		Query query = manager.createNativeQuery(updateString);
		
		return query.executeUpdate();
	}

	// ------------------------------------------------------------------------
	// ----- Entity lifecycle callback methods --------------------------------
	// ------------------------------------------------------------------------
	@PrePersist @PreUpdate public void prePersistUpdate()
	{
		if (!validated)
		{
			validationResults = validateAllFields(checkUserSuppliedIdk, 
				specificValidationFields, excludeValidationFields); 
			if (validationResults != null)
			{
				Object args[] = { this.getClass().getName() };
				ValidationException ve = 
					new ValidationException(this.getClass().getName(),
					getEntityResourceFile(),
					"baseEntity.Validation.Invalid", 
					args, validationResults);
				
				System.out.println("LAST SECOND VALIDATION FAILURE");
				for (String fieldName : validationResults.keySet())
				{
					String s = "\n\n\nFor field: " + fieldName + "\n";
					List<MsgArgsPair> results = validationResults.get(fieldName);
					for (MsgArgsPair result : results)
					{
						s += "\t--> >>>" + result.getMsg() + " (Args: " + result.getArgs() + ")<<<\n";
					}
					System.out.println(s);
				}
				/*throw new ValidationException(this.getClass().getName(),
					getEntityResourceFile(),
					"baseEntity.Validation.Invalid", 
					args, validationResults);*/
				// According to the EJB spec, and painfully experienced here, persistence
				// lifecycle methods can only throw runtime exceptions. Moreover, once
				// the exception is thrown, the container will cause the transaction to
				// rollback without trying to persist/update anything else, so while
				// that is good, the downside is that even if many objects have validation
				// errors, only the first one will be caught in the transaction before
				// it aborts. The user might have to conceivably keep trying over and over
				// again, slowly fixing errors as they come to light. Therefore, we cannot
				// rely on this architectural mechanism to provide the user with a
				// comprehensive list of all the validation errors. On the other hand,
				// what this *is* is a last-minute check to hopefully minimize bad data
				// entering the database. That is the bottom line.
				// Update: This last-minute check has proven to be invaluable.
				throw new CSWrapperException(ve);
			}
		}
		setTimeLastModified(new Timestamp(System.currentTimeMillis()));
	}
	
	@PostPersist @PostUpdate @PostRemove public void postPersistUpdateRemove()
	{
		modified = false;
		CacheManager.invalidateCachedTable(getEntityTableName());
	}
}
