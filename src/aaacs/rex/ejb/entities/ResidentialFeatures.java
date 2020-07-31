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
			name = "List<ResidentialFeatures>",
			entities =
			{
				@EntityResult
				(
					entityClass = ResidentialFeatures.class,
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
						@FieldResult(name = "finishingTypeIdk", column = "finishingTypeIdk"),
						@FieldResult(name = "privateEntrance", column = "privateEntrance"),
						@FieldResult(name = "bedrooms", column = "bedrooms"),
						@FieldResult(name = "bathrooms", column = "bathrooms"),
						@FieldResult(name = "kitchens", column = "kitchens"),
						@FieldResult(name = "livingRooms", column = "livingRooms"),
						@FieldResult(name = "diningRooms", column = "diningRooms"),
						@FieldResult(name = "familyRooms", column = "familyRooms"),
						@FieldResult(name = "lofts", column = "lofts"),
						@FieldResult(name = "internalServantRooms", column = "internalServantRooms"),
						@FieldResult(name = "externalServantRooms", column = "externalServantRooms"),
						@FieldResult(name = "highCeilings", column = "highCeilings"),
						@FieldResult(name = "roofTypeIdk", column = "roofTypeIdk"),
						@FieldResult(name = "liveableRoof", column = "liveableRoof"),
						@FieldResult(name = "basement", column = "basement"),
						@FieldResult(name = "frontYardSizeSquareMeters", column = "frontYardSizeSquareMeters"),
						@FieldResult(name = "frontYardGrass", column = "frontYardGrass"),
						@FieldResult(name = "backYardSizeSquareMeters", column = "backYardSizeSquareMeters"),
						@FieldResult(name = "backYardGrass", column = "backYardGrass"),
						@FieldResult(name = "balconies", column = "balconies"),
						@FieldResult(name = "pools", column = "pools"),
						@FieldResult(name = "porches", column = "porches"),
						@FieldResult(name = "patios", column = "patios"),
						@FieldResult(name = "refrigerators", column = "refrigerators"),
						@FieldResult(name = "stoves", column = "stoves"),
						@FieldResult(name = "ovens", column = "ovens"),
						@FieldResult(name = "carpetted", column = "carpetted"),
						@FieldResult(name = "tiled", column = "tiled"),
						@FieldResult(name = "curtains", column = "curtains"),
						@FieldResult(name = "walkinClosets", column = "walkinClosets"),
						@FieldResult(name = "furnished", column = "furnished"),
						@FieldResult(name = "fireplaces", column = "fireplaces"),
						@FieldResult(name = "builtInGas", column = "builtInGas"),
						@FieldResult(name = "phoneConnection", column = "phoneConnection"),
						@FieldResult(name = "installedPhones", column = "installedPhones"),
						@FieldResult(name = "internetAccessTypeIdk", column = "internetAccessTypeIdk"),
						@FieldResult(name = "laundryHookup", column = "laundryHookup"),
						@FieldResult(name = "coolingTypeIdk", column = "coolingTypeIdk"),
						@FieldResult(name = "splitACUnitsInstalled", column = "splitACUnitsInstalled"),
						@FieldResult(name = "wallACUnitsInstalled", column = "wallACUnitsInstalled"),
						@FieldResult(name = "ceilingFansInstalled", column = "ceilingFansInstalled"),
						@FieldResult(name = "heatingTypeIdk", column = "heatingTypeIdk"),
						@FieldResult(name = "waterSupplyTypeIdk", column = "waterSupplyTypeIdk"),
						@FieldResult(name = "roofWaterTanks", column = "roofWaterTanks"),
						@FieldResult(name = "waterPumpOnPremises", column = "waterPumpOnPremises"),
						@FieldResult(name = "totalUnitsSharingWaterTanks", column = "totalUnitsSharingWaterTanks"),
						@FieldResult(name = "totalUnitsSharingWaterPump", column = "totalUnitsSharingWaterPump"),
						@FieldResult(name = "sewageDisposalTypeIdk", column = "sewageDisposalTypeIdk"),
						@FieldResult(name = "septicTankOnPremises", column = "septicTankOnPremises"),
						@FieldResult(name = "totalUnitsSharingSepticTank", column = "totalUnitsSharingSepticTank"),
						@FieldResult(name = "septicTankLinkedToCity", column = "septicTankLinkedToCity"),
						@FieldResult(name = "garageCarCapacity", column = "garageCarCapacity"),
						@FieldResult(name = "detachedGarage", column = "detachedGarage"),
						@FieldResult(name = "secureParking", column = "secureParking"),
						@FieldResult(name = "reservedParkingSpaces", column = "reservedParkingSpaces")
					}
				)
			}
		)
	}
)
@Entity(name="ResidentialFeatures") @Table(name="\"tResidentialFeatures\"")
public class ResidentialFeatures extends BaseEntity implements Serializable
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
	public static final String entityTableName = "\"tResidentialFeatures\"";

	static public ResidentialFeatures findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (ResidentialFeatures) BaseEntity.findByIdk(manager, ResidentialFeatures.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findResidentialFeaturesByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<ResidentialFeatures>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findResidentialFeaturesByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<ResidentialFeatures>");
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

	static public Map<String, List<MsgArgsPair>> validateResidentialFeatures(ResidentialFeatures residentialFeatures,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return residentialFeatures.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateCreatedByIdk(Long createdByIdk)
	{
		return (new FieldValidator()).clearChecks().setName("createdByIdk").setAllowNullElement(false).setValidMinimum((double) KEY_HIGHEST_RESERVED + 1).checkLong(createdByIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateReferenceHandle(String referenceHandle)
	{
		return (new FieldValidator()).clearChecks().setName("referenceHandle").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(referenceHandle);
	}

	static public Map<String, List<MsgArgsPair>> validateFinishingTypeIdk(Long finishingTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("finishingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(finishingTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validatePrivateEntrance(Boolean privateEntrance)
	{
		return (new FieldValidator()).clearChecks().setName("privateEntrance").checkBoolean(privateEntrance);
	}

	static public Map<String, List<MsgArgsPair>> validateBedrooms(Short bedrooms)
	{
		return (new FieldValidator()).clearChecks().setName("bedrooms").setValidMinimum((double) 0).checkShort(bedrooms);
	}

	static public Map<String, List<MsgArgsPair>> validateBathrooms(Float bathrooms)
	{
		return (new FieldValidator()).clearChecks().setName("bathrooms").setValidMinimum((double) 0).setValidRegex("[0-9]([0-9])?(\\.[05])?").checkFloat(bathrooms);
	}

	static public Map<String, List<MsgArgsPair>> validateKitchens(Short kitchens)
	{
		return (new FieldValidator()).clearChecks().setName("kitchens").setValidMinimum((double) 0).checkShort(kitchens);
	}

	static public Map<String, List<MsgArgsPair>> validateLivingRooms(Short livingRooms)
	{
		return (new FieldValidator()).clearChecks().setName("livingRooms").setValidMinimum((double) 0).checkShort(livingRooms);
	}

	static public Map<String, List<MsgArgsPair>> validateDiningRooms(Short diningRooms)
	{
		return (new FieldValidator()).clearChecks().setName("diningRooms").setValidMinimum((double) 0).checkShort(diningRooms);
	}

	static public Map<String, List<MsgArgsPair>> validateFamilyRooms(Short familyRooms)
	{
		return (new FieldValidator()).clearChecks().setName("familyRooms").setValidMinimum((double) 0).checkShort(familyRooms);
	}

	static public Map<String, List<MsgArgsPair>> validateLofts(Short lofts)
	{
		return (new FieldValidator()).clearChecks().setName("lofts").setValidMinimum((double) 0).checkShort(lofts);
	}

	static public Map<String, List<MsgArgsPair>> validateInternalServantRooms(Short internalServantRooms)
	{
		return (new FieldValidator()).clearChecks().setName("internalServantRooms").setValidMinimum((double) 0).checkShort(internalServantRooms);
	}

	static public Map<String, List<MsgArgsPair>> validateExternalServantRooms(Short externalServantRooms)
	{
		return (new FieldValidator()).clearChecks().setName("externalServantRooms").setValidMinimum((double) 0).checkShort(externalServantRooms);
	}

	static public Map<String, List<MsgArgsPair>> validateHighCeilings(Boolean highCeilings)
	{
		return (new FieldValidator()).clearChecks().setName("highCeilings").checkBoolean(highCeilings);
	}

	static public Map<String, List<MsgArgsPair>> validateRoofTypeIdk(Long roofTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("roofTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(roofTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateLiveableRoof(Boolean liveableRoof)
	{
		return (new FieldValidator()).clearChecks().setName("liveableRoof").checkBoolean(liveableRoof);
	}

	static public Map<String, List<MsgArgsPair>> validateBasement(Boolean basement)
	{
		return (new FieldValidator()).clearChecks().setName("basement").checkBoolean(basement);
	}

	static public Map<String, List<MsgArgsPair>> validateFrontYardSizeSquareMeters(Short frontYardSizeSquareMeters)
	{
		return (new FieldValidator()).clearChecks().setName("frontYardSizeSquareMeters").setValidMinimum((double) 0).checkShort(frontYardSizeSquareMeters);
	}

	static public Map<String, List<MsgArgsPair>> validateFrontYardGrass(Boolean frontYardGrass)
	{
		return (new FieldValidator()).clearChecks().setName("frontYardGrass").checkBoolean(frontYardGrass);
	}

	static public Map<String, List<MsgArgsPair>> validateBackYardSizeSquareMeters(Short backYardSizeSquareMeters)
	{
		return (new FieldValidator()).clearChecks().setName("backYardSizeSquareMeters").setValidMinimum((double) 0).checkShort(backYardSizeSquareMeters);
	}

	static public Map<String, List<MsgArgsPair>> validateBackYardGrass(Boolean backYardGrass)
	{
		return (new FieldValidator()).clearChecks().setName("backYardGrass").checkBoolean(backYardGrass);
	}

	static public Map<String, List<MsgArgsPair>> validateBalconies(Short balconies)
	{
		return (new FieldValidator()).clearChecks().setName("balconies").setValidMinimum((double) 0).checkShort(balconies);
	}

	static public Map<String, List<MsgArgsPair>> validatePools(Short pools)
	{
		return (new FieldValidator()).clearChecks().setName("pools").setValidMinimum((double) 0).checkShort(pools);
	}

	static public Map<String, List<MsgArgsPair>> validatePorches(Short porches)
	{
		return (new FieldValidator()).clearChecks().setName("porches").setValidMinimum((double) 0).checkShort(porches);
	}

	static public Map<String, List<MsgArgsPair>> validatePatios(Short patios)
	{
		return (new FieldValidator()).clearChecks().setName("patios").setValidMinimum((double) 0).checkShort(patios);
	}

	static public Map<String, List<MsgArgsPair>> validateRefrigerators(Short refrigerators)
	{
		return (new FieldValidator()).clearChecks().setName("refrigerators").setValidMinimum((double) 0).checkShort(refrigerators);
	}

	static public Map<String, List<MsgArgsPair>> validateStoves(Short stoves)
	{
		return (new FieldValidator()).clearChecks().setName("stoves").setValidMinimum((double) 0).checkShort(stoves);
	}

	static public Map<String, List<MsgArgsPair>> validateOvens(Short ovens)
	{
		return (new FieldValidator()).clearChecks().setName("ovens").setValidMinimum((double) 0).checkShort(ovens);
	}

	static public Map<String, List<MsgArgsPair>> validateCarpetted(Boolean carpetted)
	{
		return (new FieldValidator()).clearChecks().setName("carpetted").checkBoolean(carpetted);
	}

	static public Map<String, List<MsgArgsPair>> validateTiled(Boolean tiled)
	{
		return (new FieldValidator()).clearChecks().setName("tiled").checkBoolean(tiled);
	}

	static public Map<String, List<MsgArgsPair>> validateCurtains(Boolean curtains)
	{
		return (new FieldValidator()).clearChecks().setName("curtains").checkBoolean(curtains);
	}

	static public Map<String, List<MsgArgsPair>> validateWalkinClosets(Boolean walkinClosets)
	{
		return (new FieldValidator()).clearChecks().setName("walkinClosets").checkBoolean(walkinClosets);
	}

	static public Map<String, List<MsgArgsPair>> validateFurnished(Boolean furnished)
	{
		return (new FieldValidator()).clearChecks().setName("furnished").checkBoolean(furnished);
	}

	static public Map<String, List<MsgArgsPair>> validateFireplaces(Short fireplaces)
	{
		return (new FieldValidator()).clearChecks().setName("fireplaces").setValidMinimum((double) 0).checkShort(fireplaces);
	}

	static public Map<String, List<MsgArgsPair>> validateBuiltInGas(Boolean builtInGas)
	{
		return (new FieldValidator()).clearChecks().setName("builtInGas").checkBoolean(builtInGas);
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

	static public Map<String, List<MsgArgsPair>> validateLaundryHookup(Boolean laundryHookup)
	{
		return (new FieldValidator()).clearChecks().setName("laundryHookup").checkBoolean(laundryHookup);
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

	static public Map<String, List<MsgArgsPair>> validateHeatingTypeIdk(Long heatingTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("heatingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(heatingTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateWaterSupplyTypeIdk(Long waterSupplyTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("waterSupplyTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(waterSupplyTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateRoofWaterTanks(Short roofWaterTanks)
	{
		return (new FieldValidator()).clearChecks().setName("roofWaterTanks").setValidMinimum((double) 0).checkShort(roofWaterTanks);
	}

	static public Map<String, List<MsgArgsPair>> validateWaterPumpOnPremises(Boolean waterPumpOnPremises)
	{
		return (new FieldValidator()).clearChecks().setName("waterPumpOnPremises").checkBoolean(waterPumpOnPremises);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalUnitsSharingWaterTanks(Short totalUnitsSharingWaterTanks)
	{
		return (new FieldValidator()).clearChecks().setName("totalUnitsSharingWaterTanks").setValidMinimum((double) 0).checkShort(totalUnitsSharingWaterTanks);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalUnitsSharingWaterPump(Short totalUnitsSharingWaterPump)
	{
		return (new FieldValidator()).clearChecks().setName("totalUnitsSharingWaterPump").setValidMinimum((double) 0).checkShort(totalUnitsSharingWaterPump);
	}

	static public Map<String, List<MsgArgsPair>> validateSewageDisposalTypeIdk(Long sewageDisposalTypeIdk)
	{
		return (new FieldValidator()).clearChecks().setName("sewageDisposalTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(sewageDisposalTypeIdk);
	}

	static public Map<String, List<MsgArgsPair>> validateSepticTankOnPremises(Boolean septicTankOnPremises)
	{
		return (new FieldValidator()).clearChecks().setName("septicTankOnPremises").checkBoolean(septicTankOnPremises);
	}

	static public Map<String, List<MsgArgsPair>> validateTotalUnitsSharingSepticTank(Short totalUnitsSharingSepticTank)
	{
		return (new FieldValidator()).clearChecks().setName("totalUnitsSharingSepticTank").setValidMinimum((double) 0).checkShort(totalUnitsSharingSepticTank);
	}

	static public Map<String, List<MsgArgsPair>> validateSepticTankLinkedToCity(Boolean septicTankLinkedToCity)
	{
		return (new FieldValidator()).clearChecks().setName("septicTankLinkedToCity").checkBoolean(septicTankLinkedToCity);
	}

	static public Map<String, List<MsgArgsPair>> validateGarageCarCapacity(Short garageCarCapacity)
	{
		return (new FieldValidator()).clearChecks().setName("garageCarCapacity").setValidMinimum((double) 0).checkShort(garageCarCapacity);
	}

	static public Map<String, List<MsgArgsPair>> validateDetachedGarage(Boolean detachedGarage)
	{
		return (new FieldValidator()).clearChecks().setName("detachedGarage").checkBoolean(detachedGarage);
	}

	static public Map<String, List<MsgArgsPair>> validateSecureParking(Boolean secureParking)
	{
		return (new FieldValidator()).clearChecks().setName("secureParking").checkBoolean(secureParking);
	}

	static public Map<String, List<MsgArgsPair>> validateReservedParkingSpaces(Short reservedParkingSpaces)
	{
		return (new FieldValidator()).clearChecks().setName("reservedParkingSpaces").setValidMinimum((double) 0).checkShort(reservedParkingSpaces);
	}

	// For constructing SQL fragments suitable for searching for instances/rows of this entity
	static public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)
	{
		if (searchFields == null || searchFields.isEmpty()) return;

		selectBuilder.addFrom(entityTableName);

		if (searchFields.get("finishingTypeIdk") != null && ((Long[]) searchFields.get("finishingTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"finishingTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("finishingTypeIdk"), "", "") + ")", true);
		if (searchFields.get("privateEntrance") != null && searchFields.get("privateEntrance").toString().length() > 0 &&
			searchFields.get("privateEntrance").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"privateEntrance\" = true", true);
		if (searchFields.get("bedroomsMinimum") != null && searchFields.get("bedroomsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"bedrooms\" >= " + searchFields.get("bedroomsMinimum"), true);
		if (searchFields.get("bedroomsMaximum") != null && searchFields.get("bedroomsMaximum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"bedrooms\" <= " + searchFields.get("bedroomsMaximum"), true);
		if (searchFields.get("bathroomsMinimum") != null && searchFields.get("bathroomsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"bathrooms\" >= " + searchFields.get("bathroomsMinimum"), true);
		if (searchFields.get("kitchensMinimum") != null && searchFields.get("kitchensMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"kitchens\" >= " + searchFields.get("kitchensMinimum"), true);
		if (searchFields.get("livingRoomsMinimum") != null && searchFields.get("livingRoomsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"livingRooms\" >= " + searchFields.get("livingRoomsMinimum"), true);
		if (searchFields.get("diningRoomsMinimum") != null && searchFields.get("diningRoomsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"diningRooms\" >= " + searchFields.get("diningRoomsMinimum"), true);
		if (searchFields.get("familyRoomsMinimum") != null && searchFields.get("familyRoomsMinimum").toString().length() > 0)
			selectBuilder.addWhere(entityTableName + ".\"familyRooms\" >= " + searchFields.get("familyRoomsMinimum"), true);
		if (searchFields.get("basement") != null && searchFields.get("basement").toString().length() > 0 &&
			searchFields.get("basement").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"basement\" = true", true);
		if (searchFields.get("frontYardSizeSquareMeters") != null && searchFields.get("frontYardSizeSquareMeters").toString().length() > 0 &&
			searchFields.get("frontYardSizeSquareMeters").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"frontYardSizeSquareMeters\" > 0", true);
		if (searchFields.get("backYardSizeSquareMeters") != null && searchFields.get("backYardSizeSquareMeters").toString().length() > 0 &&
			searchFields.get("backYardSizeSquareMeters").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"backYardSizeSquareMeters\" > 0", true);
		if (searchFields.get("balconies") != null && searchFields.get("balconies").toString().length() > 0 &&
			searchFields.get("balconies").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"balconies\" > 0", true);
		if (searchFields.get("pools") != null && searchFields.get("pools").toString().length() > 0 &&
			searchFields.get("pools").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"pools\" > 0", true);
		if (searchFields.get("refrigerators") != null && searchFields.get("refrigerators").toString().length() > 0 &&
			searchFields.get("refrigerators").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"refrigerators\" > 0", true);
		if (searchFields.get("carpetted") != null && searchFields.get("carpetted").toString().length() > 0 &&
			searchFields.get("carpetted").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"carpetted\" = true", true);
		if (searchFields.get("tiled") != null && searchFields.get("tiled").toString().length() > 0 &&
			searchFields.get("tiled").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"tiled\" = true", true);
		if (searchFields.get("furnished") != null && searchFields.get("furnished").toString().length() > 0 &&
			searchFields.get("furnished").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"furnished\" = true", true);
		if (searchFields.get("builtInGas") != null && searchFields.get("builtInGas").toString().length() > 0 &&
			searchFields.get("builtInGas").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"builtInGas\" = true", true);
		if (searchFields.get("phoneConnection") != null && searchFields.get("phoneConnection").toString().length() > 0 &&
			searchFields.get("phoneConnection").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"phoneConnection\" = true", true);
		if (searchFields.get("coolingTypeIdk") != null && ((Long[]) searchFields.get("coolingTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"coolingTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("coolingTypeIdk"), "", "") + ")", true);
		if (searchFields.get("heatingTypeIdk") != null && ((Long[]) searchFields.get("heatingTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"heatingTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("heatingTypeIdk"), "", "") + ")", true);
		if (searchFields.get("waterSupplyTypeIdk") != null && ((Long[]) searchFields.get("waterSupplyTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"waterSupplyTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("waterSupplyTypeIdk"), "", "") + ")", true);
		if (searchFields.get("sewageDisposalTypeIdk") != null && ((Long[]) searchFields.get("sewageDisposalTypeIdk")).length > 0)
			selectBuilder.addWhere(entityTableName + ".\"sewageDisposalTypeIdk\" IN (" +
				ArrayHelper.toString((Long[]) searchFields.get("sewageDisposalTypeIdk"), "", "") + ")", true);
		if (searchFields.get("garageCarCapacity") != null && searchFields.get("garageCarCapacity").toString().length() > 0 &&
			searchFields.get("garageCarCapacity").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"garageCarCapacity\" > 0", true);
		if (searchFields.get("secureParking") != null && searchFields.get("secureParking").toString().length() > 0 &&
			searchFields.get("secureParking").equals(true))
			selectBuilder.addWhere(entityTableName + ".\"secureParking\" = true", true);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"createdByIdk\"") private Long createdByIdk = null;
	@Basic @Column(name="\"referenceHandle\"") private String referenceHandle = null;
	@Basic @Column(name="\"finishingTypeIdk\"") private Long finishingTypeIdk = null;
	@Basic @Column(name="\"privateEntrance\"") private Boolean privateEntrance = null;
	@Basic @Column(name="\"bedrooms\"") private Short bedrooms = null;
	@Basic @Column(name="\"bathrooms\"") private Float bathrooms = null;
	@Basic @Column(name="\"kitchens\"") private Short kitchens = null;
	@Basic @Column(name="\"livingRooms\"") private Short livingRooms = null;
	@Basic @Column(name="\"diningRooms\"") private Short diningRooms = null;
	@Basic @Column(name="\"familyRooms\"") private Short familyRooms = null;
	@Basic @Column(name="\"lofts\"") private Short lofts = null;
	@Basic @Column(name="\"internalServantRooms\"") private Short internalServantRooms = null;
	@Basic @Column(name="\"externalServantRooms\"") private Short externalServantRooms = null;
	@Basic @Column(name="\"highCeilings\"") private Boolean highCeilings = null;
	@Basic @Column(name="\"roofTypeIdk\"") private Long roofTypeIdk = null;
	@Basic @Column(name="\"liveableRoof\"") private Boolean liveableRoof = null;
	@Basic @Column(name="\"basement\"") private Boolean basement = null;
	@Basic @Column(name="\"frontYardSizeSquareMeters\"") private Short frontYardSizeSquareMeters = null;
	@Basic @Column(name="\"frontYardGrass\"") private Boolean frontYardGrass = null;
	@Basic @Column(name="\"backYardSizeSquareMeters\"") private Short backYardSizeSquareMeters = null;
	@Basic @Column(name="\"backYardGrass\"") private Boolean backYardGrass = null;
	@Basic @Column(name="\"balconies\"") private Short balconies = null;
	@Basic @Column(name="\"pools\"") private Short pools = null;
	@Basic @Column(name="\"porches\"") private Short porches = null;
	@Basic @Column(name="\"patios\"") private Short patios = null;
	@Basic @Column(name="\"refrigerators\"") private Short refrigerators = null;
	@Basic @Column(name="\"stoves\"") private Short stoves = null;
	@Basic @Column(name="\"ovens\"") private Short ovens = null;
	@Basic @Column(name="\"carpetted\"") private Boolean carpetted = null;
	@Basic @Column(name="\"tiled\"") private Boolean tiled = null;
	@Basic @Column(name="\"curtains\"") private Boolean curtains = null;
	@Basic @Column(name="\"walkinClosets\"") private Boolean walkinClosets = null;
	@Basic @Column(name="\"furnished\"") private Boolean furnished = null;
	@Basic @Column(name="\"fireplaces\"") private Short fireplaces = null;
	@Basic @Column(name="\"builtInGas\"") private Boolean builtInGas = null;
	@Basic @Column(name="\"phoneConnection\"") private Boolean phoneConnection = null;
	@Basic @Column(name="\"installedPhones\"") private Short installedPhones = null;
	@Basic @Column(name="\"internetAccessTypeIdk\"") private Long internetAccessTypeIdk = null;
	@Basic @Column(name="\"laundryHookup\"") private Boolean laundryHookup = null;
	@Basic @Column(name="\"coolingTypeIdk\"") private Long coolingTypeIdk = null;
	@Basic @Column(name="\"splitACUnitsInstalled\"") private Short splitACUnitsInstalled = null;
	@Basic @Column(name="\"wallACUnitsInstalled\"") private Short wallACUnitsInstalled = null;
	@Basic @Column(name="\"ceilingFansInstalled\"") private Short ceilingFansInstalled = null;
	@Basic @Column(name="\"heatingTypeIdk\"") private Long heatingTypeIdk = null;
	@Basic @Column(name="\"waterSupplyTypeIdk\"") private Long waterSupplyTypeIdk = null;
	@Basic @Column(name="\"roofWaterTanks\"") private Short roofWaterTanks = null;
	@Basic @Column(name="\"waterPumpOnPremises\"") private Boolean waterPumpOnPremises = null;
	@Basic @Column(name="\"totalUnitsSharingWaterTanks\"") private Short totalUnitsSharingWaterTanks = null;
	@Basic @Column(name="\"totalUnitsSharingWaterPump\"") private Short totalUnitsSharingWaterPump = null;
	@Basic @Column(name="\"sewageDisposalTypeIdk\"") private Long sewageDisposalTypeIdk = null;
	@Basic @Column(name="\"septicTankOnPremises\"") private Boolean septicTankOnPremises = null;
	@Basic @Column(name="\"totalUnitsSharingSepticTank\"") private Short totalUnitsSharingSepticTank = null;
	@Basic @Column(name="\"septicTankLinkedToCity\"") private Boolean septicTankLinkedToCity = null;
	@Basic @Column(name="\"garageCarCapacity\"") private Short garageCarCapacity = null;
	@Basic @Column(name="\"detachedGarage\"") private Boolean detachedGarage = null;
	@Basic @Column(name="\"secureParking\"") private Boolean secureParking = null;
	@Basic @Column(name="\"reservedParkingSpaces\"") private Short reservedParkingSpaces = null;

	public ResidentialFeatures() {}
	public ResidentialFeatures(Map<String, Object> fields)
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

	public Long getFinishingTypeIdk()
	{
		return finishingTypeIdk;
	}
	public void setFinishingTypeIdk(Long inputParameter)
	{
		modify();
		finishingTypeIdk = inputParameter;
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

	public Short getBedrooms()
	{
		return bedrooms;
	}
	public void setBedrooms(Short inputParameter)
	{
		modify();
		bedrooms = inputParameter;
	}

	public Float getBathrooms()
	{
		return bathrooms;
	}
	public void setBathrooms(Float inputParameter)
	{
		modify();
		bathrooms = inputParameter;
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

	public Short getLivingRooms()
	{
		return livingRooms;
	}
	public void setLivingRooms(Short inputParameter)
	{
		modify();
		livingRooms = inputParameter;
	}

	public Short getDiningRooms()
	{
		return diningRooms;
	}
	public void setDiningRooms(Short inputParameter)
	{
		modify();
		diningRooms = inputParameter;
	}

	public Short getFamilyRooms()
	{
		return familyRooms;
	}
	public void setFamilyRooms(Short inputParameter)
	{
		modify();
		familyRooms = inputParameter;
	}

	public Short getLofts()
	{
		return lofts;
	}
	public void setLofts(Short inputParameter)
	{
		modify();
		lofts = inputParameter;
	}

	public Short getInternalServantRooms()
	{
		return internalServantRooms;
	}
	public void setInternalServantRooms(Short inputParameter)
	{
		modify();
		internalServantRooms = inputParameter;
	}

	public Short getExternalServantRooms()
	{
		return externalServantRooms;
	}
	public void setExternalServantRooms(Short inputParameter)
	{
		modify();
		externalServantRooms = inputParameter;
	}

	public Boolean getHighCeilings()
	{
		return highCeilings;
	}
	public void setHighCeilings(Boolean inputParameter)
	{
		modify();
		highCeilings = inputParameter;
	}

	public Long getRoofTypeIdk()
	{
		return roofTypeIdk;
	}
	public void setRoofTypeIdk(Long inputParameter)
	{
		modify();
		roofTypeIdk = inputParameter;
	}

	public Boolean getLiveableRoof()
	{
		return liveableRoof;
	}
	public void setLiveableRoof(Boolean inputParameter)
	{
		modify();
		liveableRoof = inputParameter;
	}

	public Boolean getBasement()
	{
		return basement;
	}
	public void setBasement(Boolean inputParameter)
	{
		modify();
		basement = inputParameter;
	}

	public Short getFrontYardSizeSquareMeters()
	{
		return frontYardSizeSquareMeters;
	}
	public void setFrontYardSizeSquareMeters(Short inputParameter)
	{
		modify();
		frontYardSizeSquareMeters = inputParameter;
	}

	public Boolean getFrontYardGrass()
	{
		return frontYardGrass;
	}
	public void setFrontYardGrass(Boolean inputParameter)
	{
		modify();
		frontYardGrass = inputParameter;
	}

	public Short getBackYardSizeSquareMeters()
	{
		return backYardSizeSquareMeters;
	}
	public void setBackYardSizeSquareMeters(Short inputParameter)
	{
		modify();
		backYardSizeSquareMeters = inputParameter;
	}

	public Boolean getBackYardGrass()
	{
		return backYardGrass;
	}
	public void setBackYardGrass(Boolean inputParameter)
	{
		modify();
		backYardGrass = inputParameter;
	}

	public Short getBalconies()
	{
		return balconies;
	}
	public void setBalconies(Short inputParameter)
	{
		modify();
		balconies = inputParameter;
	}

	public Short getPools()
	{
		return pools;
	}
	public void setPools(Short inputParameter)
	{
		modify();
		pools = inputParameter;
	}

	public Short getPorches()
	{
		return porches;
	}
	public void setPorches(Short inputParameter)
	{
		modify();
		porches = inputParameter;
	}

	public Short getPatios()
	{
		return patios;
	}
	public void setPatios(Short inputParameter)
	{
		modify();
		patios = inputParameter;
	}

	public Short getRefrigerators()
	{
		return refrigerators;
	}
	public void setRefrigerators(Short inputParameter)
	{
		modify();
		refrigerators = inputParameter;
	}

	public Short getStoves()
	{
		return stoves;
	}
	public void setStoves(Short inputParameter)
	{
		modify();
		stoves = inputParameter;
	}

	public Short getOvens()
	{
		return ovens;
	}
	public void setOvens(Short inputParameter)
	{
		modify();
		ovens = inputParameter;
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

	public Boolean getCurtains()
	{
		return curtains;
	}
	public void setCurtains(Boolean inputParameter)
	{
		modify();
		curtains = inputParameter;
	}

	public Boolean getWalkinClosets()
	{
		return walkinClosets;
	}
	public void setWalkinClosets(Boolean inputParameter)
	{
		modify();
		walkinClosets = inputParameter;
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

	public Short getFireplaces()
	{
		return fireplaces;
	}
	public void setFireplaces(Short inputParameter)
	{
		modify();
		fireplaces = inputParameter;
	}

	public Boolean getBuiltInGas()
	{
		return builtInGas;
	}
	public void setBuiltInGas(Boolean inputParameter)
	{
		modify();
		builtInGas = inputParameter;
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

	public Boolean getLaundryHookup()
	{
		return laundryHookup;
	}
	public void setLaundryHookup(Boolean inputParameter)
	{
		modify();
		laundryHookup = inputParameter;
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

	public Long getHeatingTypeIdk()
	{
		return heatingTypeIdk;
	}
	public void setHeatingTypeIdk(Long inputParameter)
	{
		modify();
		heatingTypeIdk = inputParameter;
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

	public Short getRoofWaterTanks()
	{
		return roofWaterTanks;
	}
	public void setRoofWaterTanks(Short inputParameter)
	{
		modify();
		roofWaterTanks = inputParameter;
	}

	public Boolean getWaterPumpOnPremises()
	{
		return waterPumpOnPremises;
	}
	public void setWaterPumpOnPremises(Boolean inputParameter)
	{
		modify();
		waterPumpOnPremises = inputParameter;
	}

	public Short getTotalUnitsSharingWaterTanks()
	{
		return totalUnitsSharingWaterTanks;
	}
	public void setTotalUnitsSharingWaterTanks(Short inputParameter)
	{
		modify();
		totalUnitsSharingWaterTanks = inputParameter;
	}

	public Short getTotalUnitsSharingWaterPump()
	{
		return totalUnitsSharingWaterPump;
	}
	public void setTotalUnitsSharingWaterPump(Short inputParameter)
	{
		modify();
		totalUnitsSharingWaterPump = inputParameter;
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

	public Boolean getSepticTankOnPremises()
	{
		return septicTankOnPremises;
	}
	public void setSepticTankOnPremises(Boolean inputParameter)
	{
		modify();
		septicTankOnPremises = inputParameter;
	}

	public Short getTotalUnitsSharingSepticTank()
	{
		return totalUnitsSharingSepticTank;
	}
	public void setTotalUnitsSharingSepticTank(Short inputParameter)
	{
		modify();
		totalUnitsSharingSepticTank = inputParameter;
	}

	public Boolean getSepticTankLinkedToCity()
	{
		return septicTankLinkedToCity;
	}
	public void setSepticTankLinkedToCity(Boolean inputParameter)
	{
		modify();
		septicTankLinkedToCity = inputParameter;
	}

	public Short getGarageCarCapacity()
	{
		return garageCarCapacity;
	}
	public void setGarageCarCapacity(Short inputParameter)
	{
		modify();
		garageCarCapacity = inputParameter;
	}

	public Boolean getDetachedGarage()
	{
		return detachedGarage;
	}
	public void setDetachedGarage(Boolean inputParameter)
	{
		modify();
		detachedGarage = inputParameter;
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

	public Short getReservedParkingSpaces()
	{
		return reservedParkingSpaces;
	}
	public void setReservedParkingSpaces(Short inputParameter)
	{
		modify();
		reservedParkingSpaces = inputParameter;
	}

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
		fields.put("finishingTypeIdkMsg",
			(String) PropertyOption.cacheLookup(finishingTypeIdk).get("name"));
		fields.put("roofTypeIdkMsg",
			(String) PropertyOption.cacheLookup(roofTypeIdk).get("name"));
		fields.put("internetAccessTypeIdkMsg",
			(String) PropertyOption.cacheLookup(internetAccessTypeIdk).get("name"));
		fields.put("coolingTypeIdkMsg",
			(String) PropertyOption.cacheLookup(coolingTypeIdk).get("name"));
		fields.put("heatingTypeIdkMsg",
			(String) PropertyOption.cacheLookup(heatingTypeIdk).get("name"));
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
		collectField(format, dateFormat, fields, "finishingTypeIdk", finishingTypeIdk);
		collectField(format, dateFormat, fields, "privateEntrance", privateEntrance);
		collectField(format, dateFormat, fields, "bedrooms", bedrooms);
		collectField(format, dateFormat, fields, "bathrooms", bathrooms);
		collectField(format, dateFormat, fields, "kitchens", kitchens);
		collectField(format, dateFormat, fields, "livingRooms", livingRooms);
		collectField(format, dateFormat, fields, "diningRooms", diningRooms);
		collectField(format, dateFormat, fields, "familyRooms", familyRooms);
		collectField(format, dateFormat, fields, "lofts", lofts);
		collectField(format, dateFormat, fields, "internalServantRooms", internalServantRooms);
		collectField(format, dateFormat, fields, "externalServantRooms", externalServantRooms);
		collectField(format, dateFormat, fields, "highCeilings", highCeilings);
		collectField(format, dateFormat, fields, "roofTypeIdk", roofTypeIdk);
		collectField(format, dateFormat, fields, "liveableRoof", liveableRoof);
		collectField(format, dateFormat, fields, "basement", basement);
		collectField(format, dateFormat, fields, "frontYardSizeSquareMeters", frontYardSizeSquareMeters);
		collectField(format, dateFormat, fields, "frontYardGrass", frontYardGrass);
		collectField(format, dateFormat, fields, "backYardSizeSquareMeters", backYardSizeSquareMeters);
		collectField(format, dateFormat, fields, "backYardGrass", backYardGrass);
		collectField(format, dateFormat, fields, "balconies", balconies);
		collectField(format, dateFormat, fields, "pools", pools);
		collectField(format, dateFormat, fields, "porches", porches);
		collectField(format, dateFormat, fields, "patios", patios);
		collectField(format, dateFormat, fields, "refrigerators", refrigerators);
		collectField(format, dateFormat, fields, "stoves", stoves);
		collectField(format, dateFormat, fields, "ovens", ovens);
		collectField(format, dateFormat, fields, "carpetted", carpetted);
		collectField(format, dateFormat, fields, "tiled", tiled);
		collectField(format, dateFormat, fields, "curtains", curtains);
		collectField(format, dateFormat, fields, "walkinClosets", walkinClosets);
		collectField(format, dateFormat, fields, "furnished", furnished);
		collectField(format, dateFormat, fields, "fireplaces", fireplaces);
		collectField(format, dateFormat, fields, "builtInGas", builtInGas);
		collectField(format, dateFormat, fields, "phoneConnection", phoneConnection);
		collectField(format, dateFormat, fields, "installedPhones", installedPhones);
		collectField(format, dateFormat, fields, "internetAccessTypeIdk", internetAccessTypeIdk);
		collectField(format, dateFormat, fields, "laundryHookup", laundryHookup);
		collectField(format, dateFormat, fields, "coolingTypeIdk", coolingTypeIdk);
		collectField(format, dateFormat, fields, "splitACUnitsInstalled", splitACUnitsInstalled);
		collectField(format, dateFormat, fields, "wallACUnitsInstalled", wallACUnitsInstalled);
		collectField(format, dateFormat, fields, "ceilingFansInstalled", ceilingFansInstalled);
		collectField(format, dateFormat, fields, "heatingTypeIdk", heatingTypeIdk);
		collectField(format, dateFormat, fields, "waterSupplyTypeIdk", waterSupplyTypeIdk);
		collectField(format, dateFormat, fields, "roofWaterTanks", roofWaterTanks);
		collectField(format, dateFormat, fields, "waterPumpOnPremises", waterPumpOnPremises);
		collectField(format, dateFormat, fields, "totalUnitsSharingWaterTanks", totalUnitsSharingWaterTanks);
		collectField(format, dateFormat, fields, "totalUnitsSharingWaterPump", totalUnitsSharingWaterPump);
		collectField(format, dateFormat, fields, "sewageDisposalTypeIdk", sewageDisposalTypeIdk);
		collectField(format, dateFormat, fields, "septicTankOnPremises", septicTankOnPremises);
		collectField(format, dateFormat, fields, "totalUnitsSharingSepticTank", totalUnitsSharingSepticTank);
		collectField(format, dateFormat, fields, "septicTankLinkedToCity", septicTankLinkedToCity);
		collectField(format, dateFormat, fields, "garageCarCapacity", garageCarCapacity);
		collectField(format, dateFormat, fields, "detachedGarage", detachedGarage);
		collectField(format, dateFormat, fields, "secureParking", secureParking);
		collectField(format, dateFormat, fields, "reservedParkingSpaces", reservedParkingSpaces);
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
		if (fields.containsKey("finishingTypeIdk"))
			setFinishingTypeIdk( (Long) fields.get("finishingTypeIdk") );
		if (fields.containsKey("privateEntrance"))
			setPrivateEntrance( (Boolean) fields.get("privateEntrance") );
		if (fields.containsKey("bedrooms"))
			setBedrooms( ((Number) fields.get("bedrooms")).shortValue() );
		if (fields.containsKey("bathrooms"))
			setBathrooms( (Float) fields.get("bathrooms") );
		if (fields.containsKey("kitchens"))
			setKitchens( ((Number) fields.get("kitchens")).shortValue() );
		if (fields.containsKey("livingRooms"))
			setLivingRooms( ((Number) fields.get("livingRooms")).shortValue() );
		if (fields.containsKey("diningRooms"))
			setDiningRooms( ((Number) fields.get("diningRooms")).shortValue() );
		if (fields.containsKey("familyRooms"))
			setFamilyRooms( ((Number) fields.get("familyRooms")).shortValue() );
		if (fields.containsKey("lofts"))
			setLofts( ((Number) fields.get("lofts")).shortValue() );
		if (fields.containsKey("internalServantRooms"))
			setInternalServantRooms( ((Number) fields.get("internalServantRooms")).shortValue() );
		if (fields.containsKey("externalServantRooms"))
			setExternalServantRooms( ((Number) fields.get("externalServantRooms")).shortValue() );
		if (fields.containsKey("highCeilings"))
			setHighCeilings( (Boolean) fields.get("highCeilings") );
		if (fields.containsKey("roofTypeIdk"))
			setRoofTypeIdk( (Long) fields.get("roofTypeIdk") );
		if (fields.containsKey("liveableRoof"))
			setLiveableRoof( (Boolean) fields.get("liveableRoof") );
		if (fields.containsKey("basement"))
			setBasement( (Boolean) fields.get("basement") );
		if (fields.containsKey("frontYardSizeSquareMeters"))
			setFrontYardSizeSquareMeters( ((Number) fields.get("frontYardSizeSquareMeters")).shortValue() );
		if (fields.containsKey("frontYardGrass"))
			setFrontYardGrass( (Boolean) fields.get("frontYardGrass") );
		if (fields.containsKey("backYardSizeSquareMeters"))
			setBackYardSizeSquareMeters( ((Number) fields.get("backYardSizeSquareMeters")).shortValue() );
		if (fields.containsKey("backYardGrass"))
			setBackYardGrass( (Boolean) fields.get("backYardGrass") );
		if (fields.containsKey("balconies"))
			setBalconies( ((Number) fields.get("balconies")).shortValue() );
		if (fields.containsKey("pools"))
			setPools( ((Number) fields.get("pools")).shortValue() );
		if (fields.containsKey("porches"))
			setPorches( ((Number) fields.get("porches")).shortValue() );
		if (fields.containsKey("patios"))
			setPatios( ((Number) fields.get("patios")).shortValue() );
		if (fields.containsKey("refrigerators"))
			setRefrigerators( ((Number) fields.get("refrigerators")).shortValue() );
		if (fields.containsKey("stoves"))
			setStoves( ((Number) fields.get("stoves")).shortValue() );
		if (fields.containsKey("ovens"))
			setOvens( ((Number) fields.get("ovens")).shortValue() );
		if (fields.containsKey("carpetted"))
			setCarpetted( (Boolean) fields.get("carpetted") );
		if (fields.containsKey("tiled"))
			setTiled( (Boolean) fields.get("tiled") );
		if (fields.containsKey("curtains"))
			setCurtains( (Boolean) fields.get("curtains") );
		if (fields.containsKey("walkinClosets"))
			setWalkinClosets( (Boolean) fields.get("walkinClosets") );
		if (fields.containsKey("furnished"))
			setFurnished( (Boolean) fields.get("furnished") );
		if (fields.containsKey("fireplaces"))
			setFireplaces( ((Number) fields.get("fireplaces")).shortValue() );
		if (fields.containsKey("builtInGas"))
			setBuiltInGas( (Boolean) fields.get("builtInGas") );
		if (fields.containsKey("phoneConnection"))
			setPhoneConnection( (Boolean) fields.get("phoneConnection") );
		if (fields.containsKey("installedPhones"))
			setInstalledPhones( ((Number) fields.get("installedPhones")).shortValue() );
		if (fields.containsKey("internetAccessTypeIdk"))
			setInternetAccessTypeIdk( (Long) fields.get("internetAccessTypeIdk") );
		if (fields.containsKey("laundryHookup"))
			setLaundryHookup( (Boolean) fields.get("laundryHookup") );
		if (fields.containsKey("coolingTypeIdk"))
			setCoolingTypeIdk( (Long) fields.get("coolingTypeIdk") );
		if (fields.containsKey("splitACUnitsInstalled"))
			setSplitACUnitsInstalled( ((Number) fields.get("splitACUnitsInstalled")).shortValue() );
		if (fields.containsKey("wallACUnitsInstalled"))
			setWallACUnitsInstalled( ((Number) fields.get("wallACUnitsInstalled")).shortValue() );
		if (fields.containsKey("ceilingFansInstalled"))
			setCeilingFansInstalled( ((Number) fields.get("ceilingFansInstalled")).shortValue() );
		if (fields.containsKey("heatingTypeIdk"))
			setHeatingTypeIdk( (Long) fields.get("heatingTypeIdk") );
		if (fields.containsKey("waterSupplyTypeIdk"))
			setWaterSupplyTypeIdk( (Long) fields.get("waterSupplyTypeIdk") );
		if (fields.containsKey("roofWaterTanks"))
			setRoofWaterTanks( ((Number) fields.get("roofWaterTanks")).shortValue() );
		if (fields.containsKey("waterPumpOnPremises"))
			setWaterPumpOnPremises( (Boolean) fields.get("waterPumpOnPremises") );
		if (fields.containsKey("totalUnitsSharingWaterTanks"))
			setTotalUnitsSharingWaterTanks( ((Number) fields.get("totalUnitsSharingWaterTanks")).shortValue() );
		if (fields.containsKey("totalUnitsSharingWaterPump"))
			setTotalUnitsSharingWaterPump( ((Number) fields.get("totalUnitsSharingWaterPump")).shortValue() );
		if (fields.containsKey("sewageDisposalTypeIdk"))
			setSewageDisposalTypeIdk( (Long) fields.get("sewageDisposalTypeIdk") );
		if (fields.containsKey("septicTankOnPremises"))
			setSepticTankOnPremises( (Boolean) fields.get("septicTankOnPremises") );
		if (fields.containsKey("totalUnitsSharingSepticTank"))
			setTotalUnitsSharingSepticTank( ((Number) fields.get("totalUnitsSharingSepticTank")).shortValue() );
		if (fields.containsKey("septicTankLinkedToCity"))
			setSepticTankLinkedToCity( (Boolean) fields.get("septicTankLinkedToCity") );
		if (fields.containsKey("garageCarCapacity"))
			setGarageCarCapacity( ((Number) fields.get("garageCarCapacity")).shortValue() );
		if (fields.containsKey("detachedGarage"))
			setDetachedGarage( (Boolean) fields.get("detachedGarage") );
		if (fields.containsKey("secureParking"))
			setSecureParking( (Boolean) fields.get("secureParking") );
		if (fields.containsKey("reservedParkingSpaces"))
			setReservedParkingSpaces( ((Number) fields.get("reservedParkingSpaces")).shortValue() );
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
				(specificFields != null && !exclude && specificFields.contains("finishingTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("finishingTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("finishingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(finishingTypeIdk);
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
				(specificFields != null && !exclude && specificFields.contains("bedrooms")) ||
				(specificFields != null && exclude && !specificFields.contains("bedrooms")))
			{
				result = 
					validator.clearChecks().setName("bedrooms").setValidMinimum((double) 0).checkShort(bedrooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("bathrooms")) ||
				(specificFields != null && exclude && !specificFields.contains("bathrooms")))
			{
				result = 
					validator.clearChecks().setName("bathrooms").setValidMinimum((double) 0).setValidRegex("[0-9]([0-9])?(\\.[05])?").checkFloat(bathrooms);
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
				(specificFields != null && !exclude && specificFields.contains("livingRooms")) ||
				(specificFields != null && exclude && !specificFields.contains("livingRooms")))
			{
				result = 
					validator.clearChecks().setName("livingRooms").setValidMinimum((double) 0).checkShort(livingRooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("diningRooms")) ||
				(specificFields != null && exclude && !specificFields.contains("diningRooms")))
			{
				result = 
					validator.clearChecks().setName("diningRooms").setValidMinimum((double) 0).checkShort(diningRooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("familyRooms")) ||
				(specificFields != null && exclude && !specificFields.contains("familyRooms")))
			{
				result = 
					validator.clearChecks().setName("familyRooms").setValidMinimum((double) 0).checkShort(familyRooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("lofts")) ||
				(specificFields != null && exclude && !specificFields.contains("lofts")))
			{
				result = 
					validator.clearChecks().setName("lofts").setValidMinimum((double) 0).checkShort(lofts);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("internalServantRooms")) ||
				(specificFields != null && exclude && !specificFields.contains("internalServantRooms")))
			{
				result = 
					validator.clearChecks().setName("internalServantRooms").setValidMinimum((double) 0).checkShort(internalServantRooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("externalServantRooms")) ||
				(specificFields != null && exclude && !specificFields.contains("externalServantRooms")))
			{
				result = 
					validator.clearChecks().setName("externalServantRooms").setValidMinimum((double) 0).checkShort(externalServantRooms);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("highCeilings")) ||
				(specificFields != null && exclude && !specificFields.contains("highCeilings")))
			{
				result = 
					validator.clearChecks().setName("highCeilings").checkBoolean(highCeilings);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("roofTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("roofTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("roofTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(roofTypeIdk);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("liveableRoof")) ||
				(specificFields != null && exclude && !specificFields.contains("liveableRoof")))
			{
				result = 
					validator.clearChecks().setName("liveableRoof").checkBoolean(liveableRoof);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("basement")) ||
				(specificFields != null && exclude && !specificFields.contains("basement")))
			{
				result = 
					validator.clearChecks().setName("basement").checkBoolean(basement);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("frontYardSizeSquareMeters")) ||
				(specificFields != null && exclude && !specificFields.contains("frontYardSizeSquareMeters")))
			{
				result = 
					validator.clearChecks().setName("frontYardSizeSquareMeters").setValidMinimum((double) 0).checkShort(frontYardSizeSquareMeters);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("frontYardGrass")) ||
				(specificFields != null && exclude && !specificFields.contains("frontYardGrass")))
			{
				result = 
					validator.clearChecks().setName("frontYardGrass").checkBoolean(frontYardGrass);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("backYardSizeSquareMeters")) ||
				(specificFields != null && exclude && !specificFields.contains("backYardSizeSquareMeters")))
			{
				result = 
					validator.clearChecks().setName("backYardSizeSquareMeters").setValidMinimum((double) 0).checkShort(backYardSizeSquareMeters);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("backYardGrass")) ||
				(specificFields != null && exclude && !specificFields.contains("backYardGrass")))
			{
				result = 
					validator.clearChecks().setName("backYardGrass").checkBoolean(backYardGrass);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("balconies")) ||
				(specificFields != null && exclude && !specificFields.contains("balconies")))
			{
				result = 
					validator.clearChecks().setName("balconies").setValidMinimum((double) 0).checkShort(balconies);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("pools")) ||
				(specificFields != null && exclude && !specificFields.contains("pools")))
			{
				result = 
					validator.clearChecks().setName("pools").setValidMinimum((double) 0).checkShort(pools);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("porches")) ||
				(specificFields != null && exclude && !specificFields.contains("porches")))
			{
				result = 
					validator.clearChecks().setName("porches").setValidMinimum((double) 0).checkShort(porches);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("patios")) ||
				(specificFields != null && exclude && !specificFields.contains("patios")))
			{
				result = 
					validator.clearChecks().setName("patios").setValidMinimum((double) 0).checkShort(patios);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("refrigerators")) ||
				(specificFields != null && exclude && !specificFields.contains("refrigerators")))
			{
				result = 
					validator.clearChecks().setName("refrigerators").setValidMinimum((double) 0).checkShort(refrigerators);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("stoves")) ||
				(specificFields != null && exclude && !specificFields.contains("stoves")))
			{
				result = 
					validator.clearChecks().setName("stoves").setValidMinimum((double) 0).checkShort(stoves);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("ovens")) ||
				(specificFields != null && exclude && !specificFields.contains("ovens")))
			{
				result = 
					validator.clearChecks().setName("ovens").setValidMinimum((double) 0).checkShort(ovens);
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
				(specificFields != null && !exclude && specificFields.contains("curtains")) ||
				(specificFields != null && exclude && !specificFields.contains("curtains")))
			{
				result = 
					validator.clearChecks().setName("curtains").checkBoolean(curtains);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("walkinClosets")) ||
				(specificFields != null && exclude && !specificFields.contains("walkinClosets")))
			{
				result = 
					validator.clearChecks().setName("walkinClosets").checkBoolean(walkinClosets);
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
				(specificFields != null && !exclude && specificFields.contains("fireplaces")) ||
				(specificFields != null && exclude && !specificFields.contains("fireplaces")))
			{
				result = 
					validator.clearChecks().setName("fireplaces").setValidMinimum((double) 0).checkShort(fireplaces);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("builtInGas")) ||
				(specificFields != null && exclude && !specificFields.contains("builtInGas")))
			{
				result = 
					validator.clearChecks().setName("builtInGas").checkBoolean(builtInGas);
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
				(specificFields != null && !exclude && specificFields.contains("laundryHookup")) ||
				(specificFields != null && exclude && !specificFields.contains("laundryHookup")))
			{
				result = 
					validator.clearChecks().setName("laundryHookup").checkBoolean(laundryHookup);
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
				(specificFields != null && !exclude && specificFields.contains("heatingTypeIdk")) ||
				(specificFields != null && exclude && !specificFields.contains("heatingTypeIdk")))
			{
				result = 
					validator.clearChecks().setName("heatingTypeIdk").setAllowNullElement(false).setValidMinimum((double) KEY_LOWEST_RESERVED).checkLong(heatingTypeIdk);
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
				(specificFields != null && !exclude && specificFields.contains("roofWaterTanks")) ||
				(specificFields != null && exclude && !specificFields.contains("roofWaterTanks")))
			{
				result = 
					validator.clearChecks().setName("roofWaterTanks").setValidMinimum((double) 0).checkShort(roofWaterTanks);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("waterPumpOnPremises")) ||
				(specificFields != null && exclude && !specificFields.contains("waterPumpOnPremises")))
			{
				result = 
					validator.clearChecks().setName("waterPumpOnPremises").checkBoolean(waterPumpOnPremises);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalUnitsSharingWaterTanks")) ||
				(specificFields != null && exclude && !specificFields.contains("totalUnitsSharingWaterTanks")))
			{
				result = 
					validator.clearChecks().setName("totalUnitsSharingWaterTanks").setValidMinimum((double) 0).checkShort(totalUnitsSharingWaterTanks);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalUnitsSharingWaterPump")) ||
				(specificFields != null && exclude && !specificFields.contains("totalUnitsSharingWaterPump")))
			{
				result = 
					validator.clearChecks().setName("totalUnitsSharingWaterPump").setValidMinimum((double) 0).checkShort(totalUnitsSharingWaterPump);
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
				(specificFields != null && !exclude && specificFields.contains("septicTankOnPremises")) ||
				(specificFields != null && exclude && !specificFields.contains("septicTankOnPremises")))
			{
				result = 
					validator.clearChecks().setName("septicTankOnPremises").checkBoolean(septicTankOnPremises);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("totalUnitsSharingSepticTank")) ||
				(specificFields != null && exclude && !specificFields.contains("totalUnitsSharingSepticTank")))
			{
				result = 
					validator.clearChecks().setName("totalUnitsSharingSepticTank").setValidMinimum((double) 0).checkShort(totalUnitsSharingSepticTank);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("septicTankLinkedToCity")) ||
				(specificFields != null && exclude && !specificFields.contains("septicTankLinkedToCity")))
			{
				result = 
					validator.clearChecks().setName("septicTankLinkedToCity").checkBoolean(septicTankLinkedToCity);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("garageCarCapacity")) ||
				(specificFields != null && exclude && !specificFields.contains("garageCarCapacity")))
			{
				result = 
					validator.clearChecks().setName("garageCarCapacity").setValidMinimum((double) 0).checkShort(garageCarCapacity);
				if (result != null)
					results.putAll(result);
			}

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("detachedGarage")) ||
				(specificFields != null && exclude && !specificFields.contains("detachedGarage")))
			{
				result = 
					validator.clearChecks().setName("detachedGarage").checkBoolean(detachedGarage);
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

			if ((specificFields == null) ||
				(specificFields != null && !exclude && specificFields.contains("reservedParkingSpaces")) ||
				(specificFields != null && exclude && !specificFields.contains("reservedParkingSpaces")))
			{
				result = 
					validator.clearChecks().setName("reservedParkingSpaces").setValidMinimum((double) 0).checkShort(reservedParkingSpaces);
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

