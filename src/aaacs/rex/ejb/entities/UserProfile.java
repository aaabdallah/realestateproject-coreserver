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

import java.sql.Date;
import aaacs.coreserver.ejb.entities.support.CacheManager;

@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<UserProfile>",
			entities =
			{
				@EntityResult
				(
					entityClass = UserProfile.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "userIdk", column = "userIdk"),
						@FieldResult(name = "titleIdk", column = "titleIdk"),
						@FieldResult(name = "firstName", column = "firstName"),
						@FieldResult(name = "secondName", column = "secondName"),
						@FieldResult(name = "thirdName", column = "thirdName"),
						@FieldResult(name = "fourthName", column = "fourthName"),
						@FieldResult(name = "dateOfBirth", column = "dateOfBirth"),
						@FieldResult(name = "streetAddress1", column = "streetAddress1"),
						@FieldResult(name = "streetAddress2", column = "streetAddress2"),
						@FieldResult(name = "districtIdk", column = "districtIdk"),
						@FieldResult(name = "cityIdk", column = "cityIdk"),
						@FieldResult(name = "regionIdk", column = "regionIdk"),
						@FieldResult(name = "countryIdk", column = "countryIdk"),
						@FieldResult(name = "postalCode", column = "postalCode"),
						@FieldResult(name = "postOfficeBox", column = "postOfficeBox"),
						@FieldResult(name = "telephone", column = "telephone"),
						@FieldResult(name = "mobile", column = "mobile"),
						@FieldResult(name = "fax", column = "fax"),
						@FieldResult(name = "email", column = "email"),
						@FieldResult(name = "occupationIdk", column = "occupationIdk"),
						@FieldResult(name = "organization", column = "organization"),
						@FieldResult(name = "organizationPosition", column = "organizationPosition"),
						@FieldResult(name = "displayCustomLogo", column = "displayCustomLogo"),
						@FieldResult(name = "displayCustomOrganization", column = "displayCustomOrganization")
					}
				)
			}
		)
	}
)
@Entity(name="UserProfile") @Table(name="\"tUserProfiles\"")
public class UserProfile extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tUserProfiles\"";

	static public UserProfile findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (UserProfile) BaseEntity.findByIdk(manager, UserProfile.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findUserProfileByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<UserProfile>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findUserProfileByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<UserProfile>");
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

	static public Map<String, List<MsgArgsPair>> validateUserProfile(UserProfile userProfile,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return userProfile.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateUserIdk(Long userIdk)
	{
		return (new FieldValidator()).clearChecks().setName("userIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(userIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateTitleIdk(Long titleIdk)
	{
		return (new FieldValidator()).clearChecks().setName("titleIdk").setAllowNullElement(false).setValidChoices(CacheManager.getLongColumn("\"tTitles\"", "idk", null)).setUseValidChoicesAs("containingSet").checkLong(titleIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateFirstName(String firstName)
	{
		return (new FieldValidator()).clearChecks().setName("firstName").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(firstName);
	}

	static public Map<String, List<MsgArgsPair>> validateSecondName(String secondName)
	{
		return (new FieldValidator()).clearChecks().setName("secondName").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(secondName);
	}

	static public Map<String, List<MsgArgsPair>> validateThirdName(String thirdName)
	{
		return (new FieldValidator()).clearChecks().setName("thirdName").setValidMaximum((double) 100).checkString(thirdName);
	}

	static public Map<String, List<MsgArgsPair>> validateFourthName(String fourthName)
	{
		return (new FieldValidator()).clearChecks().setName("fourthName").setValidMaximum((double) 100).checkString(fourthName);
	}

	static public Map<String, List<MsgArgsPair>> validateDateOfBirth(Date dateOfBirth)
	{
		return (new FieldValidator()).clearChecks().setName("dateOfBirth").checkDate(dateOfBirth);
	}

	static public Map<String, List<MsgArgsPair>> validateStreetAddress1(String streetAddress1)
	{
		return (new FieldValidator()).clearChecks().setName("streetAddress1").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress1);
	}

	static public Map<String, List<MsgArgsPair>> validateStreetAddress2(String streetAddress2)
	{
		return (new FieldValidator()).clearChecks().setName("streetAddress2").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress2);
	}

	static public Map<String, List<MsgArgsPair>> validateDistrictIdk(Long districtIdk)
	{
		return (new FieldValidator()).clearChecks().setName("districtIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(districtIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCityIdk(Long cityIdk)
	{
		return (new FieldValidator()).clearChecks().setName("cityIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(cityIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateRegionIdk(Long regionIdk)
	{
		return (new FieldValidator()).clearChecks().setName("regionIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(regionIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCountryIdk(Long countryIdk)
	{
		return (new FieldValidator()).clearChecks().setName("countryIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(countryIdk);
	}

	static public Map<String, List<MsgArgsPair>> validatePostalCode(String postalCode)
	{
		return (new FieldValidator()).clearChecks().setName("postalCode").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(postalCode);
	}

	static public Map<String, List<MsgArgsPair>> validatePostOfficeBox(String postOfficeBox)
	{
		return (new FieldValidator()).clearChecks().setName("postOfficeBox").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(postOfficeBox);
	}

	static public Map<String, List<MsgArgsPair>> validateTelephone(String telephone)
	{
		return (new FieldValidator()).clearChecks().setName("telephone").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(telephone);
	}

	static public Map<String, List<MsgArgsPair>> validateMobile(String mobile)
	{
		return (new FieldValidator()).clearChecks().setName("mobile").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(mobile);
	}

	static public Map<String, List<MsgArgsPair>> validateFax(String fax)
	{
		return (new FieldValidator()).clearChecks().setName("fax").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(fax);
	}

	static public Map<String, List<MsgArgsPair>> validateEmail(String email)
	{
		return (new FieldValidator()).clearChecks().setName("email").setAllowNullElement(false).setValidMinimum((double) 7).setValidMaximum((double) 100).setValidRegex("^[\\w\\.%+-]+@[\\w\\.]+\\.(com|COM|gov|GOV|net|NET|org|ORG|info|INFO|biz|BIZ)(\\.[a-zA-Z][a-zA-Z])?$").checkString(email);
	}

	static public Map<String, List<MsgArgsPair>> validateOccupationIdk(Long occupationIdk)
	{
		return (new FieldValidator()).clearChecks().setName("occupationIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(occupationIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateOrganization(String organization)
	{
		return (new FieldValidator()).clearChecks().setName("organization").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 150).checkString(organization);
	}

	static public Map<String, List<MsgArgsPair>> validateOrganizationPosition(String organizationPosition)
	{
		return (new FieldValidator()).clearChecks().setName("organizationPosition").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(organizationPosition);
	}

	static public Map<String, List<MsgArgsPair>> validateDisplayCustomLogo(Boolean displayCustomLogo)
	{
		return (new FieldValidator()).clearChecks().setName("displayCustomLogo").checkBoolean(displayCustomLogo);
	}

	static public Map<String, List<MsgArgsPair>> validateDisplayCustomOrganization(Boolean displayCustomOrganization)
	{
		return (new FieldValidator()).clearChecks().setName("displayCustomOrganization").checkBoolean(displayCustomOrganization);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("userIdkMinimum") != null && searchFields.get("userIdkMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"userIdk\" >= " + searchFields.get("userIdkMinimum"), true);
		if (searchFields.get("userIdkMaximum") != null && searchFields.get("userIdkMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"userIdk\" <= " + searchFields.get("userIdkMaximum"), true);
		if (searchFields.get("firstName") != null && searchFields.get("firstName").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"firstName\" LIKE $$%" + searchFields.get("firstName") + "%$$", true);
		if (searchFields.get("secondName") != null && searchFields.get("secondName").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"secondName\" LIKE $$%" + searchFields.get("secondName") + "%$$", true);
		if (searchFields.get("thirdName") != null && searchFields.get("thirdName").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"thirdName\" LIKE $$%" + searchFields.get("thirdName") + "%$$", true);
		if (searchFields.get("fourthName") != null && searchFields.get("fourthName").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"fourthName\" LIKE $$%" + searchFields.get("fourthName") + "%$$", true);
		if (searchFields.get("districtIdk") != null && ((Long[]) searchFields.get("districtIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"districtIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("districtIdk"), "", "") + ")", true);
		if (searchFields.get("cityIdk") != null && ((Long[]) searchFields.get("cityIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"cityIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("cityIdk"), "", "") + ")", true);
		if (searchFields.get("regionIdk") != null && ((Long[]) searchFields.get("regionIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"regionIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("regionIdk"), "", "") + ")", true);
		if (searchFields.get("countryIdk") != null && ((Long[]) searchFields.get("countryIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"countryIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("countryIdk"), "", "") + ")", true);
		if (searchFields.get("telephone") != null && searchFields.get("telephone").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"telephone\" LIKE $$%" + searchFields.get("telephone") + "%$$", true);
		if (searchFields.get("mobile") != null && searchFields.get("mobile").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"mobile\" LIKE $$%" + searchFields.get("mobile") + "%$$", true);
		if (searchFields.get("email") != null && searchFields.get("email").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"email\" LIKE $$%" + searchFields.get("email") + "%$$", true);
		if (searchFields.get("organization") != null && searchFields.get("organization").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"organization\" LIKE $$%" + searchFields.get("organization") + "%$$", true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"userIdk\"") private Long userIdk = null;
	@Basic @Column(name="\"titleIdk\"") private Long titleIdk = null;
	@Basic @Column(name="\"firstName\"") private String firstName = null;
	@Basic @Column(name="\"secondName\"") private String secondName = null;
	@Basic @Column(name="\"thirdName\"") private String thirdName = null;
	@Basic @Column(name="\"fourthName\"") private String fourthName = null;
	@Basic @Column(name="\"dateOfBirth\"") private Date dateOfBirth = null;
	@Basic @Column(name="\"streetAddress1\"") private String streetAddress1 = null;
	@Basic @Column(name="\"streetAddress2\"") private String streetAddress2 = null;
	@Basic @Column(name="\"districtIdk\"") private Long districtIdk = null;
	@Basic @Column(name="\"cityIdk\"") private Long cityIdk = null;
	@Basic @Column(name="\"regionIdk\"") private Long regionIdk = null;
	@Basic @Column(name="\"countryIdk\"") private Long countryIdk = null;
	@Basic @Column(name="\"postalCode\"") private String postalCode = null;
	@Basic @Column(name="\"postOfficeBox\"") private String postOfficeBox = null;
	@Basic @Column(name="\"telephone\"") private String telephone = null;
	@Basic @Column(name="\"mobile\"") private String mobile = null;
	@Basic @Column(name="\"fax\"") private String fax = null;
	@Basic @Column(name="\"email\"") private String email = null;
	@Basic @Column(name="\"occupationIdk\"") private Long occupationIdk = null;
	@Basic @Column(name="\"organization\"") private String organization = null;
	@Basic @Column(name="\"organizationPosition\"") private String organizationPosition = null;
	@Basic @Column(name="\"displayCustomLogo\"") private Boolean displayCustomLogo = null;
	@Basic @Column(name="\"displayCustomOrganization\"") private Boolean displayCustomOrganization = null;

	public UserProfile() {}
	public UserProfile(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Long getUserIdk()
	{
		return userIdk;
	}
	public void setUserIdk(Long inputParameter)
	{
		modify();
		userIdk = inputParameter;
	}

	public Long getTitleIdk()
	{
		return titleIdk;
	}
	public void setTitleIdk(Long inputParameter)
	{
		modify();
		titleIdk = inputParameter;
	}

	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String inputParameter)
	{
		modify();
		firstName = inputParameter;
	}

	public String getSecondName()
	{
		return secondName;
	}
	public void setSecondName(String inputParameter)
	{
		modify();
		secondName = inputParameter;
	}

	public String getThirdName()
	{
		return thirdName;
	}
	public void setThirdName(String inputParameter)
	{
		modify();
		thirdName = inputParameter;
	}

	public String getFourthName()
	{
		return fourthName;
	}
	public void setFourthName(String inputParameter)
	{
		modify();
		fourthName = inputParameter;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(Date inputParameter)
	{
		modify();
		dateOfBirth = inputParameter;
	}

	public String getStreetAddress1()
	{
		return streetAddress1;
	}
	public void setStreetAddress1(String inputParameter)
	{
		modify();
		streetAddress1 = inputParameter;
	}

	public String getStreetAddress2()
	{
		return streetAddress2;
	}
	public void setStreetAddress2(String inputParameter)
	{
		modify();
		streetAddress2 = inputParameter;
	}

	public Long getDistrictIdk()
	{
		return districtIdk;
	}
	public void setDistrictIdk(Long inputParameter)
	{
		modify();
		districtIdk = inputParameter;
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

	public Long getRegionIdk()
	{
		return regionIdk;
	}
	public void setRegionIdk(Long inputParameter)
	{
		modify();
		regionIdk = inputParameter;
	}

	public Long getCountryIdk()
	{
		return countryIdk;
	}
	public void setCountryIdk(Long inputParameter)
	{
		modify();
		countryIdk = inputParameter;
	}

	public String getPostalCode()
	{
		return postalCode;
	}
	public void setPostalCode(String inputParameter)
	{
		modify();
		postalCode = inputParameter;
	}

	public String getPostOfficeBox()
	{
		return postOfficeBox;
	}
	public void setPostOfficeBox(String inputParameter)
	{
		modify();
		postOfficeBox = inputParameter;
	}

	public String getTelephone()
	{
		return telephone;
	}
	public void setTelephone(String inputParameter)
	{
		modify();
		telephone = inputParameter;
	}

	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String inputParameter)
	{
		modify();
		mobile = inputParameter;
	}

	public String getFax()
	{
		return fax;
	}
	public void setFax(String inputParameter)
	{
		modify();
		fax = inputParameter;
	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String inputParameter)
	{
		modify();
		email = inputParameter;
	}

	public Long getOccupationIdk()
	{
		return occupationIdk;
	}
	public void setOccupationIdk(Long inputParameter)
	{
		modify();
		occupationIdk = inputParameter;
	}

	public String getOrganization()
	{
		return organization;
	}
	public void setOrganization(String inputParameter)
	{
		modify();
		organization = inputParameter;
	}

	public String getOrganizationPosition()
	{
		return organizationPosition;
	}
	public void setOrganizationPosition(String inputParameter)
	{
		modify();
		organizationPosition = inputParameter;
	}

	public Boolean getDisplayCustomLogo()
	{
		return displayCustomLogo;
	}
	public void setDisplayCustomLogo(Boolean inputParameter)
	{
		modify();
		displayCustomLogo = inputParameter;
	}

	public Boolean getDisplayCustomOrganization()
	{
		return displayCustomOrganization;
	}
	public void setDisplayCustomOrganization(Boolean inputParameter)
	{
		modify();
		displayCustomOrganization = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("titleIdkMsg",
			(String) Title.cacheLookup(titleIdk).get("name"));
		fields.put("districtIdkMsg",
			(String) District.cacheLookup(districtIdk).get("name"));
		fields.put("cityIdkMsg",
			(String) City.cacheLookup(cityIdk).get("name"));
		fields.put("regionIdkMsg",
			(String) Region.cacheLookup(regionIdk).get("name"));
		fields.put("countryIdkMsg",
			(String) Country.cacheLookup(countryIdk).get("name"));
		fields.put("occupationIdkMsg",
			(String) Occupation.cacheLookup(occupationIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "userIdk", userIdk);
		collectField(format, dateFormat, fields, "titleIdk", titleIdk);
		collectField(format, dateFormat, fields, "firstName", firstName);
		collectField(format, dateFormat, fields, "secondName", secondName);
		collectField(format, dateFormat, fields, "thirdName", thirdName);
		collectField(format, dateFormat, fields, "fourthName", fourthName);
		collectField(format, dateFormat, fields, "dateOfBirth", dateOfBirth);
		collectField(format, dateFormat, fields, "streetAddress1", streetAddress1);
		collectField(format, dateFormat, fields, "streetAddress2", streetAddress2);
		collectField(format, dateFormat, fields, "districtIdk", districtIdk);
		collectField(format, dateFormat, fields, "cityIdk", cityIdk);
		collectField(format, dateFormat, fields, "regionIdk", regionIdk);
		collectField(format, dateFormat, fields, "countryIdk", countryIdk);
		collectField(format, dateFormat, fields, "postalCode", postalCode);
		collectField(format, dateFormat, fields, "postOfficeBox", postOfficeBox);
		collectField(format, dateFormat, fields, "telephone", telephone);
		collectField(format, dateFormat, fields, "mobile", mobile);
		collectField(format, dateFormat, fields, "fax", fax);
		collectField(format, dateFormat, fields, "email", email);
		collectField(format, dateFormat, fields, "occupationIdk", occupationIdk);
		collectField(format, dateFormat, fields, "organization", organization);
		collectField(format, dateFormat, fields, "organizationPosition", organizationPosition);
		collectField(format, dateFormat, fields, "displayCustomLogo", displayCustomLogo);
		collectField(format, dateFormat, fields, "displayCustomOrganization", displayCustomOrganization);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("userIdk"))
			setUserIdk( (Long) fields.get("userIdk") );
		if (fields.containsKey("titleIdk"))
			setTitleIdk( (Long) fields.get("titleIdk") );
		if (fields.containsKey("firstName"))
			setFirstName( (String) fields.get("firstName") );
		if (fields.containsKey("secondName"))
			setSecondName( (String) fields.get("secondName") );
		if (fields.containsKey("thirdName"))
			setThirdName( (String) fields.get("thirdName") );
		if (fields.containsKey("fourthName"))
			setFourthName( (String) fields.get("fourthName") );
		if (fields.containsKey("dateOfBirth"))
			setDateOfBirth( (Date) fields.get("dateOfBirth") );
		if (fields.containsKey("streetAddress1"))
			setStreetAddress1( (String) fields.get("streetAddress1") );
		if (fields.containsKey("streetAddress2"))
			setStreetAddress2( (String) fields.get("streetAddress2") );
		if (fields.containsKey("districtIdk"))
			setDistrictIdk( (Long) fields.get("districtIdk") );
		if (fields.containsKey("cityIdk"))
			setCityIdk( (Long) fields.get("cityIdk") );
		if (fields.containsKey("regionIdk"))
			setRegionIdk( (Long) fields.get("regionIdk") );
		if (fields.containsKey("countryIdk"))
			setCountryIdk( (Long) fields.get("countryIdk") );
		if (fields.containsKey("postalCode"))
			setPostalCode( (String) fields.get("postalCode") );
		if (fields.containsKey("postOfficeBox"))
			setPostOfficeBox( (String) fields.get("postOfficeBox") );
		if (fields.containsKey("telephone"))
			setTelephone( (String) fields.get("telephone") );
		if (fields.containsKey("mobile"))
			setMobile( (String) fields.get("mobile") );
		if (fields.containsKey("fax"))
			setFax( (String) fields.get("fax") );
		if (fields.containsKey("email"))
			setEmail( (String) fields.get("email") );
		if (fields.containsKey("occupationIdk"))
			setOccupationIdk( (Long) fields.get("occupationIdk") );
		if (fields.containsKey("organization"))
			setOrganization( (String) fields.get("organization") );
		if (fields.containsKey("organizationPosition"))
			setOrganizationPosition( (String) fields.get("organizationPosition") );
		if (fields.containsKey("displayCustomLogo"))
			setDisplayCustomLogo( (Boolean) fields.get("displayCustomLogo") );
		if (fields.containsKey("displayCustomOrganization"))
			setDisplayCustomOrganization( (Boolean) fields.get("displayCustomOrganization") );
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
				(specificFields != null && !exclude && specificFields.contains("userIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("userIdk")))
			{
				result = 
					validator.clearChecks().setName("userIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(userIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("titleIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("titleIdk")))
			{
				result = 
					validator.clearChecks().setName("titleIdk").setAllowNullElement(false).setValidChoices(CacheManager.getLongColumn("\"tTitles\"", "idk", null)).setUseValidChoicesAs("containingSet").checkLong(titleIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("firstName")) ||
				(specificFields != null && exclude && !specificFields.contains("firstName")))
			{
				result = 
					validator.clearChecks().setName("firstName").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(firstName);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("secondName")) ||
				(specificFields != null && exclude && !specificFields.contains("secondName")))
			{
				result = 
					validator.clearChecks().setName("secondName").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(secondName);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("thirdName")) ||
				(specificFields != null && exclude && !specificFields.contains("thirdName")))
			{
				result = 
					validator.clearChecks().setName("thirdName").setValidMaximum((double) 100).checkString(thirdName);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("fourthName")) ||
				(specificFields != null && exclude && !specificFields.contains("fourthName")))
			{
				result = 
					validator.clearChecks().setName("fourthName").setValidMaximum((double) 100).checkString(fourthName);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("dateOfBirth")) ||
				(specificFields != null && exclude && !specificFields.contains("dateOfBirth")))
			{
				result = 
					validator.clearChecks().setName("dateOfBirth").checkDate(dateOfBirth);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("streetAddress1")) ||
				(specificFields != null && exclude && !specificFields.contains("streetAddress1")))
			{
				result = 
					validator.clearChecks().setName("streetAddress1").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress1);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("streetAddress2")) ||
				(specificFields != null && exclude && !specificFields.contains("streetAddress2")))
			{
				result = 
					validator.clearChecks().setName("streetAddress2").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(streetAddress2);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("districtIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("districtIdk")))
			{
				result = 
					validator.clearChecks().setName("districtIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(districtIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("cityIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("cityIdk")))
			{
				result = 
					validator.clearChecks().setName("cityIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(cityIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("regionIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("regionIdk")))
			{
				result = 
					validator.clearChecks().setName("regionIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(regionIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("countryIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("countryIdk")))
			{
				result = 
					validator.clearChecks().setName("countryIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(countryIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("postalCode")) ||
				(specificFields != null && exclude && !specificFields.contains("postalCode")))
			{
				result = 
					validator.clearChecks().setName("postalCode").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(postalCode);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("postOfficeBox")) ||
				(specificFields != null && exclude && !specificFields.contains("postOfficeBox")))
			{
				result = 
					validator.clearChecks().setName("postOfficeBox").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(postOfficeBox);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("telephone")) ||
				(specificFields != null && exclude && !specificFields.contains("telephone")))
			{
				result = 
					validator.clearChecks().setName("telephone").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(telephone);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("mobile")) ||
				(specificFields != null && exclude && !specificFields.contains("mobile")))
			{
				result = 
					validator.clearChecks().setName("mobile").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(mobile);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("fax")) ||
				(specificFields != null && exclude && !specificFields.contains("fax")))
			{
				result = 
					validator.clearChecks().setName("fax").setValidMinimum((double) 0).setValidMaximum((double) 50).checkString(fax);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("email")) ||
				(specificFields != null && exclude && !specificFields.contains("email")))
			{
				result = 
					validator.clearChecks().setName("email").setAllowNullElement(false).setValidMinimum((double) 7).setValidMaximum((double) 100).setValidRegex("^[\\w\\.%+-]+@[\\w\\.]+\\.(com|COM|gov|GOV|net|NET|org|ORG|info|INFO|biz|BIZ)(\\.[a-zA-Z][a-zA-Z])?$").checkString(email);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("occupationIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("occupationIdk")))
			{
				result = 
					validator.clearChecks().setName("occupationIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(occupationIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("organization")) ||
				(specificFields != null && exclude && !specificFields.contains("organization")))
			{
				result = 
					validator.clearChecks().setName("organization").setAllowNullElement(false).setValidMinimum((double) 0).setValidMaximum((double) 150).checkString(organization);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("organizationPosition")) ||
				(specificFields != null && exclude && !specificFields.contains("organizationPosition")))
			{
				result = 
					validator.clearChecks().setName("organizationPosition").setValidMinimum((double) 0).setValidMaximum((double) 100).checkString(organizationPosition);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("displayCustomLogo")) ||
				(specificFields != null && exclude && !specificFields.contains("displayCustomLogo")))
			{
				result = 
					validator.clearChecks().setName("displayCustomLogo").checkBoolean(displayCustomLogo);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("displayCustomOrganization")) ||
				(specificFields != null && exclude && !specificFields.contains("displayCustomOrganization")))
			{
				result = 
					validator.clearChecks().setName("displayCustomOrganization").checkBoolean(displayCustomOrganization);
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

