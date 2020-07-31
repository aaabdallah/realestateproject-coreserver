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
			name = "List<CommercialFeatures>",
			entities =
			{
				@EntityResult
				(
					entityClass = CommercialFeatures.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "createdByIdk", column = "createdByIdk"),
						@FieldResult(name = "referenceHandle", column = "referenceHandle"),
						@FieldResult(name = "privateEntrance", column = "privateEntrance"),
						@FieldResult(name = "offices", column = "offices"),
						@FieldResult(name = "meetingRooms", column = "meetingRooms"),
						@FieldResult(name = "cubicles", column = "cubicles"),
						@FieldResult(name = "reception", column = "reception"),
						@FieldResult(name = "restrooms", column = "restrooms"),
						@FieldResult(name = "kitchens", column = "kitchens"),
						@FieldResult(name = "cafeterias", column = "cafeterias"),
						@FieldResult(name = "deskCapacity", column = "deskCapacity"),
						@FieldResult(name = "desksInstalled", column = "desksInstalled"),
						@FieldResult(name = "carpetted", column = "carpetted"),
						@FieldResult(name = "tiled", column = "tiled"),
						@FieldResult(name = "furnished", column = "furnished"),
						@FieldResult(name = "phoneConnection", column = "phoneConnection"),
						@FieldResult(name = "installedPhones", column = "installedPhones"),
						@FieldResult(name = "internetAccessTypeIdk", column = "internetAccessTypeIdk"),
						@FieldResult(name = "heatingTypeIdk", column = "heatingTypeIdk"),
						@FieldResult(name = "coolingTypeIdk", column = "coolingTypeIdk"),
						@FieldResult(name = "splitACUnitsInstalled", column = "splitACUnitsInstalled"),
						@FieldResult(name = "wallACUnitsInstalled", column = "wallACUnitsInstalled"),
						@FieldResult(name = "ceilingFansInstalled", column = "ceilingFansInstalled"),
						@FieldResult(name = "waterSupplyTypeIdk", column = "waterSupplyTypeIdk"),
						@FieldResult(name = "sewageDisposalTypeIdk", column = "sewageDisposalTypeIdk"),
						@FieldResult(name = "reservedParkingSpaces", column = "reservedParkingSpaces"),
						@FieldResult(name = "secureParking", column = "secureParking")
					}
				)
			}
		)
	}
)
@Entity(name="CommercialFeatures") @Table(name="\"tCommercialFeatures\"")
public class CommercialFeatures extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tCommercialFeatures\"";

	static public CommercialFeatures findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (CommercialFeatures) BaseEntity.findByIdk(manager, CommercialFeatures.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findCommercialFeaturesByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<CommercialFeatures>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findCommercialFeaturesByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<CommercialFeatures>");
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

	static public Map<String, List<MsgArgsPair>> validateCommercialFeatures(CommercialFeatures commercialFeatures,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return commercialFeatures.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateCreatedByIdk(Long createdByIdk)
	{
		return (new FieldValidator()).clearChecks().setName("createdByIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(createdByIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateReferenceHandle(String referenceHandle)
	{
		return (new FieldValidator()).clearChecks().setName("referenceHandle").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(referenceHandle);
	}

	static public Map<String, List<MsgArgsPair>> validatePrivateEntrance(Boolean privateEntrance)
	{
		return (new FieldValidator()).clearChecks().setName("privateEntrance").checkBoolean(privateEntrance);
	}

	static public Map<String, List<MsgArgsPair>> validateOffices(Short offices)
	{
		return (new FieldValidator()).clearChecks().setName("offices").setValidMinimum((double) 0).checkShort(offices);
	}

	static public Map<String, List<MsgArgsPair>> validateMeetingRooms(Short meetingRooms)
	{
		return (new FieldValidator()).clearChecks().setName("meetingRooms").setValidMinimum((double) 0).checkShort(meetingRooms);
	}

	static public Map<String, List<MsgArgsPair>> validateCubicles(Short cubicles)
	{
		return (new FieldValidator()).clearChecks().setName("cubicles").setValidMinimum((double) 0).checkShort(cubicles);
	}

	static public Map<String, List<MsgArgsPair>> validateReception(Boolean reception)
	{
		return (new FieldValidator()).clearChecks().setName("reception").checkBoolean(reception);
	}

	static public Map<String, List<MsgArgsPair>> validateRestrooms(Short restrooms)
	{
		return (new FieldValidator()).clearChecks().setName("restrooms").setValidMinimum((double) 0).checkShort(restrooms);
	}

	static public Map<String, List<MsgArgsPair>> validateKitchens(Short kitchens)
	{
		return (new FieldValidator()).clearChecks().setName("kitchens").setValidMinimum((double) 0).checkShort(kitchens);
	}

	static public Map<String, List<MsgArgsPair>> validateCafeterias(Short cafeterias)
	{
		return (new FieldValidator()).clearChecks().setName("cafeterias").setValidMinimum((double) 0).checkShort(cafeterias);
	}

	static public Map<String, List<MsgArgsPair>> validateDeskCapacity(Short deskCapacity)
	{
		return (new FieldValidator()).clearChecks().setName("deskCapacity").setValidMinimum((double) 0).checkShort(deskCapacity);
	}

	static public Map<String, List<MsgArgsPair>> validateDesksInstalled(Short desksInstalled)
	{
		return (new FieldValidator()).clearChecks().setName("desksInstalled").setValidMinimum((double) 0).checkShort(desksInstalled);
	}

	static public Map<String, List<MsgArgsPair>> validateCarpetted(Boolean carpetted)
	{
		return (new FieldValidator()).clearChecks().setName("carpetted").checkBoolean(carpetted);
	}

	static public Map<String, List<MsgArgsPair>> validateTiled(Boolean tiled)
	{
		return (new FieldValidator()).clearChecks().setName("tiled").checkBoolean(tiled);
	}

	static public Map<String, List<MsgArgsPair>> validateFurnished(Boolean furnished)
	{
		return (new FieldValidator()).clearChecks().setName("furnished").checkBoolean(furnished);
	}

	static public Map<String, List<MsgArgsPair>> validatePhoneConnection(Boolean phoneConnection)
	{
		return (new FieldValidator()).clearChecks().setName("phoneConnection").checkBoolean(phoneConnection);
	}

	static public Map<String, List<MsgArgsPair>> validateInstalledPhones(Short installedPhones)
	{
		return (new FieldValidator()).clearChecks().setName("installedPhones").setValidMinimum((double) 0).checkShort(installedPhones);
	}

	static public Map<String, List<MsgArgsPair>> validateInternetAccessTypeIdk(Long internetAccessTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("internetAccessTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(internetAccessTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateHeatingTypeIdk(Long heatingTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("heatingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(heatingTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateCoolingTypeIdk(Long coolingTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("coolingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(coolingTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateSplitACUnitsInstalled(Short splitACUnitsInstalled)
	{
		return (new FieldValidator()).clearChecks().setName("splitACUnitsInstalled").setValidMinimum((double) 0).checkShort(splitACUnitsInstalled);
	}

	static public Map<String, List<MsgArgsPair>> validateWallACUnitsInstalled(Short wallACUnitsInstalled)
	{
		return (new FieldValidator()).clearChecks().setName("wallACUnitsInstalled").setValidMinimum((double) 0).checkShort(wallACUnitsInstalled);
	}

	static public Map<String, List<MsgArgsPair>> validateCeilingFansInstalled(Short ceilingFansInstalled)
	{
		return (new FieldValidator()).clearChecks().setName("ceilingFansInstalled").setValidMinimum((double) 0).checkShort(ceilingFansInstalled);
	}

	static public Map<String, List<MsgArgsPair>> validateWaterSupplyTypeIdk(Long waterSupplyTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("waterSupplyTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(waterSupplyTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateSewageDisposalTypeIdk(Long sewageDisposalTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("sewageDisposalTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(sewageDisposalTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateReservedParkingSpaces(Short reservedParkingSpaces)
	{
		return (new FieldValidator()).clearChecks().setName("reservedParkingSpaces").setValidMinimum((double) 0).checkShort(reservedParkingSpaces);
	}

	static public Map<String, List<MsgArgsPair>> validateSecureParking(Boolean secureParking)
	{
		return (new FieldValidator()).clearChecks().setName("secureParking").checkBoolean(secureParking);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("privateEntrance") != null && searchFields.get("privateEntrance").toString().length() > 0 &&
			searchFields.get("privateEntrance").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"privateEntrance\" = true", true);
		if (searchFields.get("officesMinimum") != null && searchFields.get("officesMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"offices\" >= " + searchFields.get("officesMinimum"), true);
		if (searchFields.get("officesMaximum") != null && searchFields.get("officesMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"offices\" <= " + searchFields.get("officesMaximum"), true);
		if (searchFields.get("meetingRoomsMinimum") != null && searchFields.get("meetingRoomsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"meetingRooms\" >= " + searchFields.get("meetingRoomsMinimum"), true);
		if (searchFields.get("cubiclesMinimum") != null && searchFields.get("cubiclesMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"cubicles\" >= " + searchFields.get("cubiclesMinimum"), true);
		if (searchFields.get("reception") != null && searchFields.get("reception").toString().length() > 0 &&
			searchFields.get("reception").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"reception\" = true", true);
		if (searchFields.get("restroomsMinimum") != null && searchFields.get("restroomsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"restrooms\" >= " + searchFields.get("restroomsMinimum"), true);
		if (searchFields.get("kitchensMinimum") != null && searchFields.get("kitchensMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"kitchens\" >= " + searchFields.get("kitchensMinimum"), true);
		if (searchFields.get("cafeterias") != null && searchFields.get("cafeterias").toString().length() > 0 &&
			searchFields.get("cafeterias").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"cafeterias\" > 0", true);
		if (searchFields.get("desksInstalled") != null && searchFields.get("desksInstalled").toString().length() > 0 &&
			searchFields.get("desksInstalled").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"desksInstalled\" > 0", true);
		if (searchFields.get("carpetted") != null && searchFields.get("carpetted").toString().length() > 0 &&
			searchFields.get("carpetted").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"carpetted\" = true", true);
		if (searchFields.get("tiled") != null && searchFields.get("tiled").toString().length() > 0 &&
			searchFields.get("tiled").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"tiled\" = true", true);
		if (searchFields.get("furnished") != null && searchFields.get("furnished").toString().length() > 0 &&
			searchFields.get("furnished").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"furnished\" = true", true);
		if (searchFields.get("phoneConnection") != null && searchFields.get("phoneConnection").toString().length() > 0 &&
			searchFields.get("phoneConnection").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"phoneConnection\" = true", true);
		if (searchFields.get("heatingTypeIdk") != null && ((Long[]) searchFields.get("heatingTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"heatingTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("heatingTypeIdk"), "", "") + ")", true);
		if (searchFields.get("coolingTypeIdk") != null && ((Long[]) searchFields.get("coolingTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"coolingTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("coolingTypeIdk"), "", "") + ")", true);
		if (searchFields.get("waterSupplyTypeIdk") != null && ((Long[]) searchFields.get("waterSupplyTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"waterSupplyTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("waterSupplyTypeIdk"), "", "") + ")", true);
		if (searchFields.get("sewageDisposalTypeIdk") != null && ((Long[]) searchFields.get("sewageDisposalTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"sewageDisposalTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("sewageDisposalTypeIdk"), "", "") + ")", true);
		if (searchFields.get("reservedParkingSpaces") != null && searchFields.get("reservedParkingSpaces").toString().length() > 0 &&
			searchFields.get("reservedParkingSpaces").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"reservedParkingSpaces\" > 0", true);
		if (searchFields.get("secureParking") != null && searchFields.get("secureParking").toString().length() > 0 &&
			searchFields.get("secureParking").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"secureParking\" = true", true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"createdByIdk\"") private Long createdByIdk = null;
	@Basic @Column(name="\"referenceHandle\"") private String referenceHandle = null;
	@Basic @Column(name="\"privateEntrance\"") private Boolean privateEntrance = null;
	@Basic @Column(name="\"offices\"") private Short offices = null;
	@Basic @Column(name="\"meetingRooms\"") private Short meetingRooms = null;
	@Basic @Column(name="\"cubicles\"") private Short cubicles = null;
	@Basic @Column(name="\"reception\"") private Boolean reception = null;
	@Basic @Column(name="\"restrooms\"") private Short restrooms = null;
	@Basic @Column(name="\"kitchens\"") private Short kitchens = null;
	@Basic @Column(name="\"cafeterias\"") private Short cafeterias = null;
	@Basic @Column(name="\"deskCapacity\"") private Short deskCapacity = null;
	@Basic @Column(name="\"desksInstalled\"") private Short desksInstalled = null;
	@Basic @Column(name="\"carpetted\"") private Boolean carpetted = null;
	@Basic @Column(name="\"tiled\"") private Boolean tiled = null;
	@Basic @Column(name="\"furnished\"") private Boolean furnished = null;
	@Basic @Column(name="\"phoneConnection\"") private Boolean phoneConnection = null;
	@Basic @Column(name="\"installedPhones\"") private Short installedPhones = null;
	@Basic @Column(name="\"internetAccessTypeIdk\"") private Long internetAccessTypeIdk = null;
	@Basic @Column(name="\"heatingTypeIdk\"") private Long heatingTypeIdk = null;
	@Basic @Column(name="\"coolingTypeIdk\"") private Long coolingTypeIdk = null;
	@Basic @Column(name="\"splitACUnitsInstalled\"") private Short splitACUnitsInstalled = null;
	@Basic @Column(name="\"wallACUnitsInstalled\"") private Short wallACUnitsInstalled = null;
	@Basic @Column(name="\"ceilingFansInstalled\"") private Short ceilingFansInstalled = null;
	@Basic @Column(name="\"waterSupplyTypeIdk\"") private Long waterSupplyTypeIdk = null;
	@Basic @Column(name="\"sewageDisposalTypeIdk\"") private Long sewageDisposalTypeIdk = null;
	@Basic @Column(name="\"reservedParkingSpaces\"") private Short reservedParkingSpaces = null;
	@Basic @Column(name="\"secureParking\"") private Boolean secureParking = null;

	public CommercialFeatures() {}
	public CommercialFeatures(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public Long getCreatedByIdk()
	{
		return createdByIdk;
	}
	public void setCreatedByIdk(Long inputParameter)
	{
		modify();
		createdByIdk = inputParameter;
	}

	public String getReferenceHandle()
	{
		return referenceHandle;
	}
	public void setReferenceHandle(String inputParameter)
	{
		modify();
		referenceHandle = inputParameter;
	}

	public Boolean getPrivateEntrance()
	{
		return privateEntrance;
	}
	public void setPrivateEntrance(Boolean inputParameter)
	{
		modify();
		privateEntrance = inputParameter;
	}

	public Short getOffices()
	{
		return offices;
	}
	public void setOffices(Short inputParameter)
	{
		modify();
		offices = inputParameter;
	}

	public Short getMeetingRooms()
	{
		return meetingRooms;
	}
	public void setMeetingRooms(Short inputParameter)
	{
		modify();
		meetingRooms = inputParameter;
	}

	public Short getCubicles()
	{
		return cubicles;
	}
	public void setCubicles(Short inputParameter)
	{
		modify();
		cubicles = inputParameter;
	}

	public Boolean getReception()
	{
		return reception;
	}
	public void setReception(Boolean inputParameter)
	{
		modify();
		reception = inputParameter;
	}

	public Short getRestrooms()
	{
		return restrooms;
	}
	public void setRestrooms(Short inputParameter)
	{
		modify();
		restrooms = inputParameter;
	}

	public Short getKitchens()
	{
		return kitchens;
	}
	public void setKitchens(Short inputParameter)
	{
		modify();
		kitchens = inputParameter;
	}

	public Short getCafeterias()
	{
		return cafeterias;
	}
	public void setCafeterias(Short inputParameter)
	{
		modify();
		cafeterias = inputParameter;
	}

	public Short getDeskCapacity()
	{
		return deskCapacity;
	}
	public void setDeskCapacity(Short inputParameter)
	{
		modify();
		deskCapacity = inputParameter;
	}

	public Short getDesksInstalled()
	{
		return desksInstalled;
	}
	public void setDesksInstalled(Short inputParameter)
	{
		modify();
		desksInstalled = inputParameter;
	}

	public Boolean getCarpetted()
	{
		return carpetted;
	}
	public void setCarpetted(Boolean inputParameter)
	{
		modify();
		carpetted = inputParameter;
	}

	public Boolean getTiled()
	{
		return tiled;
	}
	public void setTiled(Boolean inputParameter)
	{
		modify();
		tiled = inputParameter;
	}

	public Boolean getFurnished()
	{
		return furnished;
	}
	public void setFurnished(Boolean inputParameter)
	{
		modify();
		furnished = inputParameter;
	}

	public Boolean getPhoneConnection()
	{
		return phoneConnection;
	}
	public void setPhoneConnection(Boolean inputParameter)
	{
		modify();
		phoneConnection = inputParameter;
	}

	public Short getInstalledPhones()
	{
		return installedPhones;
	}
	public void setInstalledPhones(Short inputParameter)
	{
		modify();
		installedPhones = inputParameter;
	}

	public Long getInternetAccessTypeIdk()
	{
		return internetAccessTypeIdk;
	}
	public void setInternetAccessTypeIdk(Long inputParameter)
	{
		modify();
		internetAccessTypeIdk = inputParameter;
	}

	public Long getHeatingTypeIdk()
	{
		return heatingTypeIdk;
	}
	public void setHeatingTypeIdk(Long inputParameter)
	{
		modify();
		heatingTypeIdk = inputParameter;
	}

	public Long getCoolingTypeIdk()
	{
		return coolingTypeIdk;
	}
	public void setCoolingTypeIdk(Long inputParameter)
	{
		modify();
		coolingTypeIdk = inputParameter;
	}

	public Short getSplitACUnitsInstalled()
	{
		return splitACUnitsInstalled;
	}
	public void setSplitACUnitsInstalled(Short inputParameter)
	{
		modify();
		splitACUnitsInstalled = inputParameter;
	}

	public Short getWallACUnitsInstalled()
	{
		return wallACUnitsInstalled;
	}
	public void setWallACUnitsInstalled(Short inputParameter)
	{
		modify();
		wallACUnitsInstalled = inputParameter;
	}

	public Short getCeilingFansInstalled()
	{
		return ceilingFansInstalled;
	}
	public void setCeilingFansInstalled(Short inputParameter)
	{
		modify();
		ceilingFansInstalled = inputParameter;
	}

	public Long getWaterSupplyTypeIdk()
	{
		return waterSupplyTypeIdk;
	}
	public void setWaterSupplyTypeIdk(Long inputParameter)
	{
		modify();
		waterSupplyTypeIdk = inputParameter;
	}

	public Long getSewageDisposalTypeIdk()
	{
		return sewageDisposalTypeIdk;
	}
	public void setSewageDisposalTypeIdk(Long inputParameter)
	{
		modify();
		sewageDisposalTypeIdk = inputParameter;
	}

	public Short getReservedParkingSpaces()
	{
		return reservedParkingSpaces;
	}
	public void setReservedParkingSpaces(Short inputParameter)
	{
		modify();
		reservedParkingSpaces = inputParameter;
	}

	public Boolean getSecureParking()
	{
		return secureParking;
	}
	public void setSecureParking(Boolean inputParameter)
	{
		modify();
		secureParking = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("internetAccessTypeIdkMsg",
			(String) PropertyOption.cacheLookup(internetAccessTypeIdk).get("name"));
		fields.put("heatingTypeIdkMsg",
			(String) PropertyOption.cacheLookup(heatingTypeIdk).get("name"));
		fields.put("coolingTypeIdkMsg",
			(String) PropertyOption.cacheLookup(coolingTypeIdk).get("name"));
		fields.put("waterSupplyTypeIdkMsg",
			(String) PropertyOption.cacheLookup(waterSupplyTypeIdk).get("name"));
		fields.put("sewageDisposalTypeIdkMsg",
			(String) PropertyOption.cacheLookup(sewageDisposalTypeIdk).get("name"));
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "createdByIdk", createdByIdk);
		collectField(format, dateFormat, fields, "referenceHandle", referenceHandle);
		collectField(format, dateFormat, fields, "privateEntrance", privateEntrance);
		collectField(format, dateFormat, fields, "offices", offices);
		collectField(format, dateFormat, fields, "meetingRooms", meetingRooms);
		collectField(format, dateFormat, fields, "cubicles", cubicles);
		collectField(format, dateFormat, fields, "reception", reception);
		collectField(format, dateFormat, fields, "restrooms", restrooms);
		collectField(format, dateFormat, fields, "kitchens", kitchens);
		collectField(format, dateFormat, fields, "cafeterias", cafeterias);
		collectField(format, dateFormat, fields, "deskCapacity", deskCapacity);
		collectField(format, dateFormat, fields, "desksInstalled", desksInstalled);
		collectField(format, dateFormat, fields, "carpetted", carpetted);
		collectField(format, dateFormat, fields, "tiled", tiled);
		collectField(format, dateFormat, fields, "furnished", furnished);
		collectField(format, dateFormat, fields, "phoneConnection", phoneConnection);
		collectField(format, dateFormat, fields, "installedPhones", installedPhones);
		collectField(format, dateFormat, fields, "internetAccessTypeIdk", internetAccessTypeIdk);
		collectField(format, dateFormat, fields, "heatingTypeIdk", heatingTypeIdk);
		collectField(format, dateFormat, fields, "coolingTypeIdk", coolingTypeIdk);
		collectField(format, dateFormat, fields, "splitACUnitsInstalled", splitACUnitsInstalled);
		collectField(format, dateFormat, fields, "wallACUnitsInstalled", wallACUnitsInstalled);
		collectField(format, dateFormat, fields, "ceilingFansInstalled", ceilingFansInstalled);
		collectField(format, dateFormat, fields, "waterSupplyTypeIdk", waterSupplyTypeIdk);
		collectField(format, dateFormat, fields, "sewageDisposalTypeIdk", sewageDisposalTypeIdk);
		collectField(format, dateFormat, fields, "reservedParkingSpaces", reservedParkingSpaces);
		collectField(format, dateFormat, fields, "secureParking", secureParking);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("createdByIdk"))
			setCreatedByIdk( (Long) fields.get("createdByIdk") );
		if (fields.containsKey("referenceHandle"))
			setReferenceHandle( (String) fields.get("referenceHandle") );
		if (fields.containsKey("privateEntrance"))
			setPrivateEntrance( (Boolean) fields.get("privateEntrance") );
		if (fields.containsKey("offices"))
			setOffices( ((Number) fields.get("offices")).shortValue() );
		if (fields.containsKey("meetingRooms"))
			setMeetingRooms( ((Number) fields.get("meetingRooms")).shortValue() );
		if (fields.containsKey("cubicles"))
			setCubicles( ((Number) fields.get("cubicles")).shortValue() );
		if (fields.containsKey("reception"))
			setReception( (Boolean) fields.get("reception") );
		if (fields.containsKey("restrooms"))
			setRestrooms( ((Number) fields.get("restrooms")).shortValue() );
		if (fields.containsKey("kitchens"))
			setKitchens( ((Number) fields.get("kitchens")).shortValue() );
		if (fields.containsKey("cafeterias"))
			setCafeterias( ((Number) fields.get("cafeterias")).shortValue() );
		if (fields.containsKey("deskCapacity"))
			setDeskCapacity( ((Number) fields.get("deskCapacity")).shortValue() );
		if (fields.containsKey("desksInstalled"))
			setDesksInstalled( ((Number) fields.get("desksInstalled")).shortValue() );
		if (fields.containsKey("carpetted"))
			setCarpetted( (Boolean) fields.get("carpetted") );
		if (fields.containsKey("tiled"))
			setTiled( (Boolean) fields.get("tiled") );
		if (fields.containsKey("furnished"))
			setFurnished( (Boolean) fields.get("furnished") );
		if (fields.containsKey("phoneConnection"))
			setPhoneConnection( (Boolean) fields.get("phoneConnection") );
		if (fields.containsKey("installedPhones"))
			setInstalledPhones( ((Number) fields.get("installedPhones")).shortValue() );
		if (fields.containsKey("internetAccessTypeIdk"))
			setInternetAccessTypeIdk( (Long) fields.get("internetAccessTypeIdk") );
		if (fields.containsKey("heatingTypeIdk"))
			setHeatingTypeIdk( (Long) fields.get("heatingTypeIdk") );
		if (fields.containsKey("coolingTypeIdk"))
			setCoolingTypeIdk( (Long) fields.get("coolingTypeIdk") );
		if (fields.containsKey("splitACUnitsInstalled"))
			setSplitACUnitsInstalled( ((Number) fields.get("splitACUnitsInstalled")).shortValue() );
		if (fields.containsKey("wallACUnitsInstalled"))
			setWallACUnitsInstalled( ((Number) fields.get("wallACUnitsInstalled")).shortValue() );
		if (fields.containsKey("ceilingFansInstalled"))
			setCeilingFansInstalled( ((Number) fields.get("ceilingFansInstalled")).shortValue() );
		if (fields.containsKey("waterSupplyTypeIdk"))
			setWaterSupplyTypeIdk( (Long) fields.get("waterSupplyTypeIdk") );
		if (fields.containsKey("sewageDisposalTypeIdk"))
			setSewageDisposalTypeIdk( (Long) fields.get("sewageDisposalTypeIdk") );
		if (fields.containsKey("reservedParkingSpaces"))
			setReservedParkingSpaces( ((Number) fields.get("reservedParkingSpaces")).shortValue() );
		if (fields.containsKey("secureParking"))
			setSecureParking( (Boolean) fields.get("secureParking") );
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
				(specificFields != null && !exclude && specificFields.contains("createdByIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("createdByIdk")))
			{
				result = 
					validator.clearChecks().setName("createdByIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(createdByIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("referenceHandle")) ||
				(specificFields != null && exclude && !specificFields.contains("referenceHandle")))
			{
				result = 
					validator.clearChecks().setName("referenceHandle").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(referenceHandle);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("privateEntrance")) ||
				(specificFields != null && exclude && !specificFields.contains("privateEntrance")))
			{
				result = 
					validator.clearChecks().setName("privateEntrance").checkBoolean(privateEntrance);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("offices")) ||
				(specificFields != null && exclude && !specificFields.contains("offices")))
			{
				result = 
					validator.clearChecks().setName("offices").setValidMinimum((double) 0).checkShort(offices);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("meetingRooms")) ||
				(specificFields != null && exclude && !specificFields.contains("meetingRooms")))
			{
				result = 
					validator.clearChecks().setName("meetingRooms").setValidMinimum((double) 0).checkShort(meetingRooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("cubicles")) ||
				(specificFields != null && exclude && !specificFields.contains("cubicles")))
			{
				result = 
					validator.clearChecks().setName("cubicles").setValidMinimum((double) 0).checkShort(cubicles);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("reception")) ||
				(specificFields != null && exclude && !specificFields.contains("reception")))
			{
				result = 
					validator.clearChecks().setName("reception").checkBoolean(reception);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("restrooms")) ||
				(specificFields != null && exclude && !specificFields.contains("restrooms")))
			{
				result = 
					validator.clearChecks().setName("restrooms").setValidMinimum((double) 0).checkShort(restrooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("kitchens")) ||
				(specificFields != null && exclude && !specificFields.contains("kitchens")))
			{
				result = 
					validator.clearChecks().setName("kitchens").setValidMinimum((double) 0).checkShort(kitchens);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("cafeterias")) ||
				(specificFields != null && exclude && !specificFields.contains("cafeterias")))
			{
				result = 
					validator.clearChecks().setName("cafeterias").setValidMinimum((double) 0).checkShort(cafeterias);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("deskCapacity")) ||
				(specificFields != null && exclude && !specificFields.contains("deskCapacity")))
			{
				result = 
					validator.clearChecks().setName("deskCapacity").setValidMinimum((double) 0).checkShort(deskCapacity);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("desksInstalled")) ||
				(specificFields != null && exclude && !specificFields.contains("desksInstalled")))
			{
				result = 
					validator.clearChecks().setName("desksInstalled").setValidMinimum((double) 0).checkShort(desksInstalled);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("carpetted")) ||
				(specificFields != null && exclude && !specificFields.contains("carpetted")))
			{
				result = 
					validator.clearChecks().setName("carpetted").checkBoolean(carpetted);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("tiled")) ||
				(specificFields != null && exclude && !specificFields.contains("tiled")))
			{
				result = 
					validator.clearChecks().setName("tiled").checkBoolean(tiled);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("furnished")) ||
				(specificFields != null && exclude && !specificFields.contains("furnished")))
			{
				result = 
					validator.clearChecks().setName("furnished").checkBoolean(furnished);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("phoneConnection")) ||
				(specificFields != null && exclude && !specificFields.contains("phoneConnection")))
			{
				result = 
					validator.clearChecks().setName("phoneConnection").checkBoolean(phoneConnection);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("installedPhones")) ||
				(specificFields != null && exclude && !specificFields.contains("installedPhones")))
			{
				result = 
					validator.clearChecks().setName("installedPhones").setValidMinimum((double) 0).checkShort(installedPhones);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("internetAccessTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("internetAccessTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("internetAccessTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(internetAccessTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("heatingTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("heatingTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("heatingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(heatingTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("coolingTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("coolingTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("coolingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(coolingTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("splitACUnitsInstalled")) ||
				(specificFields != null && exclude && !specificFields.contains("splitACUnitsInstalled")))
			{
				result = 
					validator.clearChecks().setName("splitACUnitsInstalled").setValidMinimum((double) 0).checkShort(splitACUnitsInstalled);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("wallACUnitsInstalled")) ||
				(specificFields != null && exclude && !specificFields.contains("wallACUnitsInstalled")))
			{
				result = 
					validator.clearChecks().setName("wallACUnitsInstalled").setValidMinimum((double) 0).checkShort(wallACUnitsInstalled);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("ceilingFansInstalled")) ||
				(specificFields != null && exclude && !specificFields.contains("ceilingFansInstalled")))
			{
				result = 
					validator.clearChecks().setName("ceilingFansInstalled").setValidMinimum((double) 0).checkShort(ceilingFansInstalled);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("waterSupplyTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("waterSupplyTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("waterSupplyTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(waterSupplyTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("sewageDisposalTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("sewageDisposalTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("sewageDisposalTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(sewageDisposalTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("reservedParkingSpaces")) ||
				(specificFields != null && exclude && !specificFields.contains("reservedParkingSpaces")))
			{
				result = 
					validator.clearChecks().setName("reservedParkingSpaces").setValidMinimum((double) 0).checkShort(reservedParkingSpaces);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("secureParking")) ||
				(specificFields != null && exclude && !specificFields.contains("secureParking")))
			{
				result = 
					validator.clearChecks().setName("secureParking").checkBoolean(secureParking);
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

