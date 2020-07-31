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

import aaacs.coreserver.ejb.entities.support.CacheManager;
import javax.persistence.ColumnResult;

@SqlResultSetMappings
(
	value =
	{
		@SqlResultSetMapping
		(
			name = "List<Country>",
			entities =
			{
				@EntityResult
				(
					entityClass = Country.class,
					fields = 
					{
						@FieldResult(name = "idk", column = "idk"),
						@FieldResult(name = "lockingVersion", column = "lockingVersion"),
						@FieldResult(name = "groups", column = "groups"),
						@FieldResult(name = "metaFlags", column = "metaFlags"),
						@FieldResult(name = "version", column = "version"),
						@FieldResult(name = "timeCreated", column = "timeCreated"),
						@FieldResult(name = "timeLastModified", column = "timeLastModified"),
						@FieldResult(name = "name", column = "name"),
						@FieldResult(name = "status", column = "status")
					}
				)
			}
		),
		@SqlResultSetMapping
		(
			name = "List<CountryRegionCityDistrict>",
			columns = 
			{
				@ColumnResult (name = "countryIdk"),
				@ColumnResult (name = "countryName"),
				@ColumnResult (name = "regionIdk"),
				@ColumnResult (name = "regionName"),
				@ColumnResult (name = "cityIdk"),
				@ColumnResult (name = "cityName"),
				@ColumnResult (name = "districtIdk"),
				@ColumnResult (name = "districtName")
			}
		)
	}
)
@Entity(name="Country") @Table(name="\"tCountries\"")
public class Country extends BaseEntity implements Serializable
{
	// The different statuses a location may have.
	public enum Status
	{
		BLOCKEDFROMPROPERTIES ((short) 0x1),
		BLOCKEDFROMPROFILES ((short) 0x2);

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
	public static final String entityTableName = "\"tCountries\"";

	static public Country findByIdk(EntityManager manager, long inputIdk)
	{
		try { return (Country) BaseEntity.findByIdk(manager, Country.class, inputIdk); }
		catch (Exception e) { return null; }
	}

	static public List findCountryByFields(EntityManager manager,
		Map<String,Object> searchFields)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, null,
			searchFields, "List<Country>");
	}

	static public List findByFields(EntityManager manager, String[] columnNames,
		Map<String,Object> searchFields, String sqlResultSetMapping)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByFields(manager, tables, columnNames,
			searchFields, sqlResultSetMapping);
	}

	static public List findCountryByCondition(EntityManager manager,
		String condition, Vector<Object> conditionParameters)
	{
		String[] tables = { entityTableName };
		return BaseEntity.findByCondition(manager, tables, null,
			condition, conditionParameters, "List<Country>");
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

	static public Map<String, Object> cacheLookup(long inIdk)
	{
		try
		{
			return CacheManager.findByIdk(entityTableName,
				inIdk);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	static public Map<Long, Map<String, Object>> cacheLookup(
		Map<String, Object> searchParameters)
	{
		try
		{
			return CacheManager.findByFields(entityTableName,
				searchParameters, 0);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	// --- BEGIN CUSTOM CODE ---
	static public List findAllDistricts(EntityManager manager, Short excludeMask, Short includeMask)
	{
		String[] tables = { "\"tCountries\"", "\"tRegions\"", "\"tCities\"", "\"tDistricts\"" };
		String[] columnNames = 
			{ "\"tCountries\".idk AS \"countryIdk\"", "\"tCountries\".name AS \"countryName\"", 
			"\"tRegions\".idk AS \"regionIdk\"", "\"tRegions\".name AS \"regionName\"", 
			"\"tCities\".idk AS \"cityIdk\"", "\"tCities\".name AS \"cityName\"", 
			"\"tDistricts\".idk AS \"districtIdk\"", "\"tDistricts\".name AS \"districtName\"" };
		String condition = 
			"\"tCountries\".idk > 999 and \"tRegions\".idk > 999 and " +
			"\"tCities\".idk > 999 and \"tDistricts\".idk > 999 and " + 
			(excludeMask != null 
				? 
				"(\"tCountries\".status & " + excludeMask + ") = 0 and " +
				"(\"tRegions\".status & " + excludeMask + ") = 0 and " +
				"(\"tCountries\".status & " + excludeMask + ") = 0 and " +
				"(\"tRegions\".status & " + excludeMask + ") = 0 and " 
				: 
				" ") +
			(includeMask != null 
				?
				"(\"tCountries\".status & " + includeMask + ") = " + includeMask + " and " +
				"(\"tRegions\".status & " + includeMask + ") = " + includeMask + " and " +
				"(\"tCities\".status & " + includeMask + ") = " + includeMask + " and " +
				"(\"tDistricts\".status & " + includeMask + ") = " + includeMask + " and "
				: 
				" ") +
			"\"tRegions\".\"countryIdk\" = \"tCountries\".idk and " +
			"\"tCities\".\"regionIdk\" = \"tRegions\".idk and " +
			"\"tDistricts\".\"cityIdk\" = \"tCities\".idk";
		return BaseEntity.findByCondition(false, manager, tables, columnNames, 
			condition, null, "List<CountryRegionCityDistrict>");
	}
	// --- END CUSTOM CODE ---

	// --- BEGIN CUSTOM CODE ---
	static public List findDistricts(EntityManager manager, Short excludeMask, Short includeMask,
		String country, String region, String city, String district)
	{
		String[] tables = { "\"tCountries\"", "\"tRegions\"", "\"tCities\"", "\"tDistricts\"" };
		String[] columnNames = 
			{ "\"tCountries\".idk AS \"countryIdk\"", "\"tCountries\".name AS \"countryName\"", 
			"\"tRegions\".idk AS \"regionIdk\"", "\"tRegions\".name AS \"regionName\"", 
			"\"tCities\".idk AS \"cityIdk\"", "\"tCities\".name AS \"cityName\"", 
			"\"tDistricts\".idk AS \"districtIdk\"", "\"tDistricts\".name AS \"districtName\"" };
		String condition = 
			"\"tCountries\".idk > 999 and \"tRegions\".idk > 999 and " +
			"\"tCities\".idk > 999 and \"tDistricts\".idk > 999 and " + 
			(excludeMask != null 
				? 
				"(\"tCountries\".status & " + excludeMask + ") = 0 and " +
				"(\"tRegions\".status & " + excludeMask + ") = 0 and " +
				"(\"tCountries\".status & " + excludeMask + ") = 0 and " +
				"(\"tRegions\".status & " + excludeMask + ") = 0 and " 
				: 
				" ") +
			(includeMask != null 
				?
				"(\"tCountries\".status & " + includeMask + ") = " + includeMask + " and " +
				"(\"tRegions\".status & " + includeMask + ") = " + includeMask + " and " +
				"(\"tCities\".status & " + includeMask + ") = " + includeMask + " and " +
				"(\"tDistricts\".status & " + includeMask + ") = " + includeMask + " and "
				: 
				" ") +
			(country != null
				?
				"\"tCountries\".name = '" + country + "' and "
				:
				" ") +
			(region != null
				?
				"\"tRegions\".name = '" + region + "' and "
				:
				" ") +
			(city != null
				?
				"\"tCities\".name = '" + city + "' and "
				:
				" ") +
			(district != null
				?
				"\"tDistricts\".name = '" + district + "' and "
				:
				" ") +
			"\"tRegions\".\"countryIdk\" = \"tCountries\".idk and " +
			"\"tCities\".\"regionIdk\" = \"tRegions\".idk and " +
			"\"tDistricts\".\"cityIdk\" = \"tCities\".idk";
		return BaseEntity.findByCondition(false, manager, tables, columnNames, 
			condition, null, "List<CountryRegionCityDistrict>");
	}
	// --- END CUSTOM CODE ---

	static public Map<String, List<MsgArgsPair>> validateCountry(Country country,
		boolean userSuppliedIdk, List<String> specificFields, boolean exclude)
	{
		return country.validateAllFields(userSuppliedIdk, specificFields, exclude);
	}

	static public Map<String, List<MsgArgsPair>> validateName(String name)
	{
		return (new FieldValidator()).clearChecks().setName("name").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(name);
	}

	static public Map<String, List<MsgArgsPair>> validateStatus(Short status)
	{
		return (new FieldValidator()).clearChecks().setName("status").setAllowNullElement(false).setValidChoices((Object[]) Status.allValues).setUseValidChoicesAs("bitMasks").checkShort(status);
	}

	// ----- Instance Members -----
	@Basic @Column(name="\"name\"") private String name = null;
	@Basic @Column(name="\"status\"") private Short status = null;

	public Country() {}
	public Country(Map<String, Object> fields)
		throws CoreServerException
	{
		setAllFields(fields, true);
	}

	public String getEntityTableName() { return entityTableName; }

	public String getEntityResourceFile() { return entityResourceFile; }

	// ----- Getters & Setters -----
	public String getName()
	{
		return name;
	}
	public void setName(String inputParameter)
	{
		modify();
		name = inputParameter;
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

	protected void getMessageResources(Hashtable<String, Object> fields)
	{
	}

	protected void getExtraFields(short format, DateFormat dateFormat,
		Hashtable<String, Object> fields)
	{
		collectField(format, dateFormat, fields, "name", name);
		collectField(format, dateFormat, fields, "status", status);
	}

	protected void loadExtraFields(Map<String, Object> fields)
	{
		modify();

		if (fields == null || fields.isEmpty())
			return;

		if (fields.containsKey("name"))
			setName( (String) fields.get("name") );
		if (fields.containsKey("status"))
			setStatus( ((Number) fields.get("status")).shortValue() );
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
				(specificFields != null && !exclude && specificFields.contains("name")) ||
				(specificFields != null && exclude && !specificFields.contains("name")))
			{
				result = 
					validator.clearChecks().setName("name").setAllowNullElement(false).setValidMinimum((double) 1).setValidMaximum((double) 100).checkString(name);
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

		}

		if (results.isEmpty())
			return null;
		return results;
	}

	protected void onModify()
	{
	}

}

