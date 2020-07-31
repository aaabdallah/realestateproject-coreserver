package aaacs.coreserver.generation.dtos;

import static aaacs.coreserver.commons.generation.Utilities.goToNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;
import aaacs.coreserver.generation.ejb.entities.EntityGenerator;

/**
 * @author Ahmed A. Abd-Allah, Nov 12, 2006
 * 
 * Generates Java code from XML descriptors of Core System entities.
 * 
 * This particular generator is really an extension of EntityGenerator.
 */
public class DTOGenerator extends Generator
{
	public static void generateDTOs(String outputDirectory,
		String defaultDTOPackage, String defaultDTOImports,
		LinkedHashMap<String, EntityGenerator> entityGenerators)
		throws IOException, GenerationException
	{
		if (outputDirectory == null || outputDirectory.trim().equals(""))
			throw new GenerationException("Must configure output directory " +
					"for Java DTO's generation.");

		for (String className : entityGenerators.keySet())
		{
			EntityGenerator entityGenerator = entityGenerators.get(className);
			System.out.printf("Generating DTO for %s ...", className);

			PrintWriter file = 
				new PrintWriter(new FileWriter(outputDirectory + "/" + className + "DTO.java", 
					false));
			
			file.println(
				"/* *************************************************************************** \n" +
				" * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * \n" +
				" * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * \n" +
				" * Copyright Ahmed A. Abd-Allah, 2006-2007                                   * \n" +
				" * ***************************************************************************/\n");

			if (defaultDTOPackage != null)
				file.println("package " + defaultDTOPackage + ";");
			if (defaultDTOImports != null)
				file.println(defaultDTOImports + "\n");
			file.printf("public class %s extends BaseDTO implements Serializable\n{\n", className + "DTO");

			// ----- enums -----
			LinkedHashMap<String, LinkedHashMap<String, String>> enums =
				entityGenerator.enums;
			if (enums.size() > 0)
			{
				for (String enumNameType : enums.keySet())
				{
					String enumName = enumNameType.substring(0, enumNameType.indexOf(' '));
					String enumType = enumNameType.substring(enumNameType.indexOf(' ')+1);
					boolean bitMask = false;
					
					if (enumType.endsWith(" false"))
					{
						enumType = enumType.replaceAll(" false", "");
					}
					else if (enumType.endsWith(" true"))
					{
						enumType = enumType.replaceAll(" true", "");
						bitMask = true;
					}

					LinkedHashMap<String, String> constants = enums.get(enumNameType);
					if (constants.containsKey("__COMMENT__"))
						file.println(constants.get("__COMMENT__"));
					file.print("\tpublic enum " + enumName + "\n\t{\n");
					String castType = "";
					if (enumType.equals("Byte") || enumType.equals("Short") ||
						enumType.equals("Long"))
						castType = "(" + enumType.toLowerCase() + ")";
					else if (enumType.equals("Integer"))
						castType = "(int)";
					boolean doneOne = false;
					for (String cName : constants.keySet())
					{
						if (cName.equals("__COMMENT__")) continue;
						if (doneOne) file.print(",\n");
						String cValue = constants.get(cName);
						file.print("\t\t" + cName + " (" + castType + " " + cValue + ")");	
						doneOne = true;
					}
					file.print(";\n\n");
					
					file.print("\t\tpublic static " + enumType + "[] allValues = null;\n");
					file.print("\t\tstatic\n\t\t{\n\t\t\tallValues = ");
					file.print("new " + enumType + "[" + enumName + ".values().length];\n");
					file.print("\t\t\tint i = 0;\n" +
						"\t\t\tfor (" + enumName + " v : " + enumName + ".values())\n" +
						"\t\t\t{\n\t\t\t\tallValues[i] = v.value;\n\t\t\t\ti++;\n\t\t\t}\n\t\t}\n");
					if (bitMask)
						file.print("\t\tpublic static " + enumType + " allBits = 0;\n\t\tstatic\n\t\t{\n" +
							"\t\t\tfor (" + enumName + " v : " + enumName + ".values())\n" +
							"\t\t\t\tallBits = " + castType + "(allBits | v.value);\n\t\t}\n\n");
					else
						file.println();
					file.print(
						"\t\tpublic final " + enumType + " value;\n" +
						"\t\t" + enumName + "(" + enumType + " p) { value = p; }\n\t}\n\n");
				}
			}

			file.printf(			
				"\t/**\n\t * Serialization Version Number\n\t */\n" +
				"\tprivate static final long serialVersionUID = 1000L;\n\n");

			// Class attributes
			file.printf(
				"\tstatic public Map<Long, %sDTO> convertMaps(Map<Long, Map<String, Object>> maps)\n\t{\n" +
				"\t\tif (maps == null) return null;\n" +
				"\t\tMap<Long, %sDTO> dtos = new LinkedHashMap<Long, %sDTO>();\n" +
				"\t\tfor (Long key : maps.keySet())\n" + 
				"\t\t\tdtos.put(key, new %sDTO(maps.get(key)));\n" +
				"\t\treturn dtos;\n\t}\n\n", className, className, className, className);

			// Instance attributes
			file.printf("\t// ----- Instance Members -----\n");
			LinkedHashMap<String, LinkedHashMap<String, Object>> attributes =
				entityGenerator.attributes;

			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				if (attrProps.containsKey("comment"))
					file.printf("%s\n", attrProps.get("comment"));
				file.printf("\tprivate %s %s = %s;\n", 
					attrProps.get("type"), attrName,
					(attrProps.get("dtoDefaultValue") != null ? attrProps.get("dtoDefaultValue") : "null"));
				
				if (attrProps.get("messageResourceLookupMechanism") != null)
				{
					file.printf("\tprivate String %sMsg = null;\n", 
						attrName);
				}
			}

			file.printf(
				"\n\tpublic %s() {}\n", className + "DTO");
			file.printf(
				"\tpublic %s(Map<String, Object> fields) { loadFieldsFromMap(fields); }\n", 
				className + "DTO");
			file.printf(
				"\tpublic %s(%s sourceDTO) { this(sourceDTO.toMap()); }\n\n", 
				className + "DTO", className + "DTO");

			file.printf("\t// ----- Getters & Setters -----\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String attrType = (String) attrProps.get("type");
				file.printf("\tpublic %s get%s()\n\t{\n\t\treturn %s;\n\t}\n",
					attrType, Utilities.firstLetterToUpperCase(attrName), attrName);			
				file.printf("\tpublic void set%s(%s inputParameter)\n\t{\n" +
						// "\t\tmodify();\n" +
						"\t\t%s = inputParameter;\n\t}\n\n",
					Utilities.firstLetterToUpperCase(attrName), attrType, attrName);			

				if (attrProps.get("messageResourceLookupMechanism") != null)
				{
					file.printf("\tpublic String get%sMsg()\n\t{\n\t\treturn %sMsg;\n\t}\n",
						Utilities.firstLetterToUpperCase(attrName), attrName);
					file.printf("\tpublic void set%sMsg(String inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%sMsg = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), attrName);			
				}
			}

			boolean lookupHeader = false;
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				if (entityGenerator.cached == true && 
					attrProps.containsKey("dtoCacheLookup") &&
					((String) attrProps.get("dtoCacheLookup")).equals("true"))
				{
					if (!lookupHeader)
					{
						file.printf("\t// ----- Cache lookups -----\n");
						lookupHeader = true;
					}

					//String attrType = (String) attrProps.get("type");
					file.printf("\tpublic Map<String, Long> getEvery%sInCache()\n\t{\n" +
						"\t\tFacesContext context = FacesContext.getCurrentInstance();\n" +
						"\t\tMap<Long, Map<String, Object>> table = lookupTableFromCache(\"\\\"%s\\\"\", null);\n" +
						"\t\tMap<String, Long> mappedValues = new java.util.TreeMap<String, Long>();\n" +
						"\t\tfor (Long idk : table.keySet())\n\t\t{\n" +
						"\t\t\tmappedValues.put(localizeMessage(context,table.get(idk).get(\"%s\").toString()), idk);\n\t\t}\n" +
						"\t\t// The values are stored according to alphabetical order. This can be manipulated in\n" +
						"\t\t// the properties file to give the desired order by putting in bogus span tags, e.g.\n" +
						"\t\t// <span class=\"1\"/> before the first desired element, and so on.\n" +
						"\t\t// We need to remove these bogus tags manually because of a bug in JSF: selectItems\n" +
						"\t\t// automatically escapes the values, hence these little ordering tags end up displaying.\n" +
						"\t\tMap<String, Long> cleanedUpMappedValues = new LinkedHashMap<String, Long>();\n" +
						"\t\tfor (String label : mappedValues.keySet())\n\t\t{\n" +
						"\t\t\tLong value = mappedValues.get(label);\n" +
						"\t\t\tlabel = label.replaceFirst(\"<span class[^>]*>\", \"\");\n" +
						"\t\t\tcleanedUpMappedValues.put(label, value);\n\t\t}\n" +						
						"\t\treturn cleanedUpMappedValues;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName),
						goToNode(entityGenerator.rootNode, "table.name").getTextContent(),
						attrName);
				}
			}

			file.printf("\t// ----- Validators -----\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String validator = (String) attrProps.get("dtoValidator");
				if (validator != null)
					file.print(validator);
			}

			file.printf("\t	// ----- Load and store from/to maps -----\n");
			file.print("\tpublic void loadExtraFieldsFromMap(Map<String, Object> map)\n\t{\n" +
				"\t\t// destructively modifies map on purpose; see BaseDTO\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String typeCast;
				if (attrProps.get("type").equals("String"))
					typeCast = "(String)";
				else if (attrProps.get("type").equals("int"))
					typeCast = "(Integer)";
				else
					typeCast = "(" + Utilities.firstLetterToUpperCase((String) attrProps.get("type")) + ")";
				
				file.printf(
					"\t\tif (map.containsKey(\"%s\"))\n\t\t{\n" +
					"\t\t\t%s = %s map.get(\"%s\");\n" +
					"\t\t\tmap.remove(\"%s\");\n\t\t}\n",
					attrName,
					attrName, 
					typeCast, 
					attrName,
					attrName);

				if (attrProps.get("messageResourceLookupMechanism") != null)
				{
					file.printf(
						"\t\tif (map.containsKey(\"%sMsg\"))\n\t\t{\n" +
						"\t\t\t%sMsg = (String) map.get(\"%sMsg\");\n" +
						"\t\t\tmap.remove(\"%sMsg\");\n\t\t}\n",
						attrName,
						attrName, 
						attrName,
						attrName);
				}
			}
			file.print("\t}\n\n");
			
			file.print("\tpublic void storeExtraFieldsToMap(Map<String, Object> map)\n\t{\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String typeCast;
				if (attrProps.get("type").equals("String"))
					typeCast = "";
				else if (attrProps.get("type").equals("int"))
					typeCast = "(Integer) ";
				else
					typeCast = "(" + Utilities.firstLetterToUpperCase((String) attrProps.get("type")) + ") ";
				file.printf("\t\tif (%s != null)\n" +
					"\t\t\tmap.put(\"%s\", %s%s);\n",
					attrName,
					attrName, 
					typeCast,
					attrName);

				if (attrProps.get("messageResourceLookupMechanism") != null)
				{
					file.printf("\t\tif (%sMsg != null)\n" +
						"\t\t\tmap.put(\"%sMsg\", %sMsg);\n",
						attrName,
						attrName, 
						attrName);
				}
			}
			file.print("\t}\n\n");

			file.print("\tpublic void resetFields()\n\t{\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				file.printf("\t\t%s = null;\n", attrName);
			}
			file.print("\t}\n\n");

			// End of class
			file.println("}\n");
			
			file.close();

			System.out.printf(" done\n");
		}

		System.out.printf("Done generating Java DTO's.\n\n");
	}

	public static void generateSearchDTOs(String outputDirectory,
		String defaultDTOPackage, String defaultDTOImports,
		LinkedHashMap<String, EntityGenerator> entityGenerators)
		throws IOException, GenerationException
	{
		if (outputDirectory == null || outputDirectory.trim().equals(""))
			throw new GenerationException("Must configure output directory " +
					"for Java Search DTO's generation.");

		for (String className : entityGenerators.keySet())
		{
			EntityGenerator entityGenerator = entityGenerators.get(className);
			if (!entityGenerator.searchable)
				continue;
			
			System.out.printf("Generating Search DTO for %s ...", className);

			PrintWriter file = 
				new PrintWriter(new FileWriter(outputDirectory + "/" + className + "SearchDTO.java", 
					false));
			
			file.println(
				"/* *************************************************************************** \n" +
				" * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * \n" +
				" * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * \n" +
				" * Copyright Ahmed A. Abd-Allah, 2006-2007                                   * \n" +
				" * ***************************************************************************/\n");

			if (defaultDTOPackage != null)
				file.println("package " + defaultDTOPackage + ";");
			if (defaultDTOImports != null)
				file.println(defaultDTOImports + "\n");
			file.printf("public class %s extends BaseSearchDTO implements Serializable\n{\n", 
				className + "SearchDTO");

			// ----- enums -----
			LinkedHashMap<String, LinkedHashMap<String, String>> enums =
				entityGenerator.enums;
			if (enums.size() > 0)
			{
				for (String enumNameType : enums.keySet())
				{
					String enumName = enumNameType.substring(0, enumNameType.indexOf(' '));
					String enumType = enumNameType.substring(enumNameType.indexOf(' ')+1);
					boolean bitMask = false;
					
					if (enumType.endsWith(" false"))
					{
						enumType = enumType.replaceAll(" false", "");
					}
					else if (enumType.endsWith(" true"))
					{
						enumType = enumType.replaceAll(" true", "");
						bitMask = true;
					}

					LinkedHashMap<String, String> constants = enums.get(enumNameType);
					if (constants.containsKey("__COMMENT__"))
						file.println(constants.get("__COMMENT__"));
					file.print("\tpublic enum " + enumName + "\n\t{\n");
					String castType = "";
					if (enumType.equals("Byte") || enumType.equals("Short") ||
						enumType.equals("Long"))
						castType = "(" + enumType.toLowerCase() + ")";
					else if (enumType.equals("Integer"))
						castType = "(int)";
					boolean doneOne = false;
					for (String cName : constants.keySet())
					{
						if (cName.equals("__COMMENT__")) continue;
						if (doneOne) file.print(",\n");
						String cValue = constants.get(cName);
						file.print("\t\t" + cName + " (" + castType + " " + cValue + ")");	
						doneOne = true;
					}
					file.print(";\n\n");
					
					file.print("\t\tpublic static " + enumType + "[] allValues = null;\n");
					file.print("\t\tstatic\n\t\t{\n\t\t\tallValues = ");
					file.print("new " + enumType + "[" + enumName + ".values().length];\n");
					file.print("\t\t\tint i = 0;\n" +
						"\t\t\tfor (" + enumName + " v : " + enumName + ".values())\n" +
						"\t\t\t{\n\t\t\t\tallValues[i] = v.value;\n\t\t\t\ti++;\n\t\t\t}\n\t\t}\n");
					if (bitMask)
						file.print("\t\tpublic static " + enumType + " allBits = 0;\n\t\tstatic\n\t\t{\n" +
							"\t\t\tfor (" + enumName + " v : " + enumName + ".values())\n" +
							"\t\t\t\tallBits = " + castType + "(allBits | v.value);\n\t\t}\n\n");
					else
						file.println();
					file.print(
						"\t\tpublic final " + enumType + " value;\n" +
						"\t\t" + enumName + "(" + enumType + " p) { value = p; }\n\t}\n\n");
				}
			}

			file.printf(			
				"\t/**\n\t * Serialization Version Number\n\t */\n" +
				"\tprivate static final long serialVersionUID = 1000L;\n\n");

			// Class attributes
			file.printf(
				"\tstatic public Map<Long, %sSearchDTO> convertMaps(Map<Long, Map<String, Object>> maps)\n\t{\n" +
				"\t\tif (maps == null) return null;\n" +
				"\t\tMap<Long, %sSearchDTO> dtos = new LinkedHashMap<Long, %sSearchDTO>();\n" +
				"\t\tfor (Long key : maps.keySet())\n" + 
				"\t\t\tdtos.put(key, new %sSearchDTO(maps.get(key)));\n" +
				"\t\treturn dtos;\n\t}\n\n", className, className, className, className);

			// Instance attributes
			file.printf("\t// ----- Instance Members -----\n");
			LinkedHashMap<String, LinkedHashMap<String, Object>> attributes =
				entityGenerator.attributes;

			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				if (attrProps.containsKey("comment"))
					file.printf("%s\n", attrProps.get("comment"));

				String searchCriteria = (String) attrProps.get("searchCriteria");
				String searchCriteriaType = (String) attrProps.get("searchCriteriaType");

				if (searchCriteria == null)
					continue;
				else if (searchCriteria.equals("options"))
					file.printf("\tprivate %s[] %s = null;\n", searchCriteriaType, attrName);
				else if (searchCriteria.equals("equals"))
				{
					String defaultValue = (String) attrProps.get("searchDefaultValue");
					file.printf("\tprivate %s %s = %s;\n", searchCriteriaType, attrName,
						defaultValue != null ? defaultValue : "null");
				}
				else if (searchCriteria.equals("min"))
					file.printf("\tprivate %s %sMinimum = null;\n", searchCriteriaType, attrName);
				else if (searchCriteria.equals("max"))
					file.printf("\tprivate %s %sMaximum = null;\n", searchCriteriaType, attrName);
				else if (searchCriteria.equals("minmax"))
				{
					file.printf("\tprivate %s %sMinimum = null;\n", searchCriteriaType, attrName);
					file.printf("\tprivate %s %sMaximum = null;\n", searchCriteriaType, attrName);
				}
				else if (searchCriteria.equals("boolean"))
				{
					String defaultValue = (String) attrProps.get("searchDefaultValue");
					file.printf("\tprivate Boolean %s = %s;\n", attrName,
						defaultValue != null ? defaultValue : "null");
				}
				else if (searchCriteria.equals("substring"))
					file.printf("\tprivate String %s = null;\n", attrName);
			}

			file.printf(
				"\n\tpublic %s() {}\n", className + "SearchDTO");
			file.printf(
				"\tpublic %s(Map<String, Object> fields) { loadFieldsFromMap(fields); }\n", 
				className + "SearchDTO");
			file.printf(
				"\tpublic %s(%s sourceDTO) { this(sourceDTO.toMap()); }\n\n", 
				className + "SearchDTO", className + "SearchDTO");

			file.printf("\t// ----- Getters & Setters -----\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String searchCriteria = (String) attrProps.get("searchCriteria");
				String searchCriteriaType = (String) attrProps.get("searchCriteriaType");
				
				if (searchCriteria == null)
					continue;
				else if (searchCriteria.equals("options"))
				{
					file.printf("\tpublic %s[] get%s()\n\t{\n\t\treturn %s;\n\t}\n",
						searchCriteriaType, Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%s(%s[] inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%s = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), searchCriteriaType, attrName);			

				}
				else if (searchCriteria.equals("equals"))
				{
					file.printf("\tpublic %s get%s()\n\t{\n\t\treturn %s;\n\t}\n",
						searchCriteriaType, Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%s(%s inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%s = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), searchCriteriaType, attrName);			
				}
				else if (searchCriteria.equals("min"))
				{
					file.printf("\tpublic %s get%sMinimum()\n\t{\n\t\treturn %sMinimum;\n\t}\n",
						searchCriteriaType, Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%sMinimum(%s inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%sMinimum = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), searchCriteriaType, attrName);			
				}
				else if (searchCriteria.equals("max"))
				{
					file.printf("\tpublic %s get%sMaximum()\n\t{\n\t\treturn %sMaximum;\n\t}\n",
						searchCriteriaType, Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%sMaximum(%s inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%sMaximum = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), searchCriteriaType, attrName);			
				}
				else if (searchCriteria.equals("minmax"))
				{
					file.printf("\tpublic %s get%sMinimum()\n\t{\n\t\treturn %sMinimum;\n\t}\n",
						searchCriteriaType, Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%sMinimum(%s inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%sMinimum = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), searchCriteriaType, attrName);			
					file.printf("\tpublic %s get%sMaximum()\n\t{\n\t\treturn %sMaximum;\n\t}\n",
						searchCriteriaType, Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%sMaximum(%s inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%sMaximum = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), searchCriteriaType, attrName);			
				}
				else if (searchCriteria.equals("boolean"))
				{
					file.printf("\tpublic Boolean get%s()\n\t{\n\t\treturn %s;\n\t}\n",
						Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%s(Boolean inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%s = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), attrName);			
				}
				else if (searchCriteria.equals("substring"))
				{
					file.printf("\tpublic String get%s()\n\t{\n\t\treturn %s;\n\t}\n",
						Utilities.firstLetterToUpperCase(attrName), attrName);			
					file.printf("\tpublic void set%s(String inputParameter)\n\t{\n" +
							// "\t\tmodify();\n" +
							"\t\t%s = inputParameter;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName), attrName);			
				}
			}

			boolean lookupHeader = false;
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				if (entityGenerator.cached == true && 
					attrProps.containsKey("dtoCacheLookup") &&
					((String) attrProps.get("dtoCacheLookup")).equals("true"))
				{
					if (!lookupHeader)
					{
						file.printf("\t// ----- Cache lookups -----\n");
						lookupHeader = true;
					}

					//String attrType = (String) attrProps.get("type");
					file.printf("\tpublic Map<String, Long> getEvery%sInCache()\n\t{\n" +
						"\t\tFacesContext context = FacesContext.getCurrentInstance();\n" +
						"\t\tMap<Long, Map<String, Object>> table = lookupTableFromCache(\"%s\", null);\n" +
						"\t\tMap<String, Long> mappedValues = new java.util.TreeMap<String, Long>();\n" +
						"\t\tfor (Long idk : table.keySet())\n\t\t{\n" +
						"\t\t\tmappedValues.put(localizeMessage(context,table.get(idk).get(\"%s\").toString()), idk);\n\t\t}\n" +
						"\t\treturn mappedValues;\n\t}\n\n",
						Utilities.firstLetterToUpperCase(attrName),
						goToNode(entityGenerator.rootNode, "table.name").getTextContent(),
						attrName);
				}
			}

			file.printf("\t	// ----- Load and store from/to maps -----\n");
			file.print("\tpublic void loadExtraFieldsFromMap(Map<String, Object> map)\n\t{\n" +
				"\t\t// destructively modifies map on purpose; see BaseDTO\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String searchCriteria = (String) attrProps.get("searchCriteria");
				String searchCriteriaType = (String) attrProps.get("searchCriteriaType");

				String typeCast;
				if (searchCriteriaType == null)
					typeCast = "";
				else if (searchCriteriaType.equals("int"))
					typeCast = "Integer";
				else
					typeCast = Utilities.firstLetterToUpperCase(searchCriteriaType);

				if (searchCriteria == null)
					continue;
				else if (searchCriteria.equals("options"))
				{
					file.printf(
						"\t\tif (map.containsKey(\"%s\"))\n\t\t{\n" +
						"\t\t\t%s = (%s[]) map.get(\"%s\");\n" +
						"\t\t\tmap.remove(\"%s\");\n\t\t}\n",
						attrName,
						attrName, 
						typeCast, 
						attrName,
						attrName);
				}
				else if (searchCriteria.equals("equals"))
				{
					file.printf(
						"\t\tif (map.containsKey(\"%s\"))\n\t\t{\n" +
						"\t\t\t%s = (%s) map.get(\"%s\");\n" +
						"\t\t\tmap.remove(\"%s\");\n\t\t}\n",
						attrName,
						attrName, 
						typeCast, 
						attrName,
						attrName);
				}
				else if (searchCriteria.equals("min"))
				{
					file.printf(
						"\t\tif (map.containsKey(\"%sMinimum\"))\n\t\t{\n" +
						"\t\t\t%sMinimum = (%s) map.get(\"%sMinimum\");\n" +
						"\t\t\tmap.remove(\"%sMinimum\");\n\t\t}\n",
						attrName,
						attrName, 
						typeCast, 
						attrName,
						attrName);
				}
				else if (searchCriteria.equals("max"))
				{
					file.printf(
						"\t\tif (map.containsKey(\"%sMaximum\"))\n\t\t{\n" +
						"\t\t\t%sMaximum = (%s) map.get(\"%sMaximum\");\n" +
						"\t\t\tmap.remove(\"%sMaximum\");\n\t\t}\n",
						attrName,
						attrName, 
						typeCast, 
						attrName,
						attrName);
				}
				else if (searchCriteria.equals("minmax"))
				{
					file.printf(
						"\t\tif (map.containsKey(\"%sMinimum\"))\n\t\t{\n" +
						"\t\t\t%sMinimum = (%s) map.get(\"%sMinimum\");\n" +
						"\t\t\tmap.remove(\"%sMinimum\");\n\t\t}\n",
						attrName,
						attrName, 
						typeCast, 
						attrName,
						attrName);
					file.printf(
						"\t\tif (map.containsKey(\"%sMaximum\"))\n\t\t{\n" +
						"\t\t\t%sMaximum = (%s) map.get(\"%sMaximum\");\n" +
						"\t\t\tmap.remove(\"%sMaximum\");\n\t\t}\n",
						attrName,
						attrName, 
						typeCast, 
						attrName,
						attrName);
				}
				else if (searchCriteria.equals("boolean"))
				{
					file.printf(
						"\t\tif (map.containsKey(\"%s\"))\n\t\t{\n" +
						"\t\t\t%s = (Boolean) map.get(\"%s\");\n" +
						"\t\t\tmap.remove(\"%s\");\n\t\t}\n",
						attrName,
						attrName, 
						attrName,
						attrName);
				}
				else if (searchCriteria.equals("substring"))
				{
					file.printf(
						"\t\tif (map.containsKey(\"%s\"))\n\t\t{\n" +
						"\t\t\t%s = (String) map.get(\"%s\");\n" +
						"\t\t\tmap.remove(\"%s\");\n\t\t}\n",
						attrName,
						attrName, 
						attrName,
						attrName);
				}
			}
			file.print("\t}\n\n");
			
			file.print("\tpublic void storeExtraFieldsToMap(Map<String, Object> map)\n\t{\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String searchCriteria = (String) attrProps.get("searchCriteria");
				// String searchCriteriaType = (String) attrProps.get("searchCriteriaType");

				if (searchCriteria == null)
					continue;
				else if (searchCriteria.equals("options"))
				{
					file.printf("\t\tif (%s != null)\n" +
						"\t\t\tmap.put(\"%s\", %s);\n",
						attrName,
						attrName, 
						attrName);
				}
				else if (searchCriteria.equals("equals"))
				{
					file.printf("\t\tif (%s != null)\n" +
						"\t\t\tmap.put(\"%s\", %s);\n",
						attrName,
						attrName, 
						attrName);
				}
				else if (searchCriteria.equals("min"))
				{
					file.printf("\t\tif (%sMinimum != null)\n" +
						"\t\t\tmap.put(\"%sMinimum\", %sMinimum);\n",
						attrName,
						attrName, 
						attrName);
				}
				else if (searchCriteria.equals("max"))
				{
					file.printf("\t\tif (%sMaximum != null)\n" +
						"\t\t\tmap.put(\"%sMaximum\", %sMaximum);\n",
						attrName,
						attrName, 
						attrName);
				}
				else if (searchCriteria.equals("minmax"))
				{
					file.printf("\t\tif (%sMinimum != null)\n" +
						"\t\t\tmap.put(\"%sMinimum\", %sMinimum);\n",
						attrName,
						attrName, 
						attrName);
					file.printf("\t\tif (%sMaximum != null)\n" +
						"\t\t\tmap.put(\"%sMaximum\", %sMaximum);\n",
						attrName,
						attrName, 
						attrName);
				}
				else if (searchCriteria.equals("boolean"))
				{
					file.printf("\t\tif (%s != null)\n" +
						"\t\t\tmap.put(\"%s\", %s);\n",
						attrName,
						attrName, 
						attrName);
				}
				else if (searchCriteria.equals("substring"))
				{
					file.printf("\t\tif (%s != null)\n" +
						"\t\t\tmap.put(\"%s\", %s);\n",
						attrName,
						attrName, 
						attrName);
				}
			}
			file.print("\t}\n\n");

			file.print("\tpublic void resetFields()\n\t{\n");
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				String searchCriteria = (String) attrProps.get("searchCriteria");

				if (searchCriteria == null)
					continue;
				else if (searchCriteria.equals("options"))
				{
					file.printf("\t\t%s = null;\n", attrName);
				}
				else if (searchCriteria.equals("equals"))
				{
					file.printf("\t\t%s = null;\n", attrName);
				}
				else if (searchCriteria.equals("min"))
				{
					file.printf("\t\t%sMinimum = null;\n", attrName);
				}
				else if (searchCriteria.equals("max"))
				{
					file.printf("\t\t%sMaximum = null;\n", attrName);
				}
				else if (searchCriteria.equals("minmax"))
				{
					file.printf("\t\t%sMinimum = null;\n", attrName);
					file.printf("\t\t%sMaximum = null;\n", attrName);
				}
				else if (searchCriteria.equals("boolean"))
				{
					file.printf("\t\t%s = null;\n", attrName);
				}
				else if (searchCriteria.equals("substring"))
				{
					file.printf("\t\t%s = null;\n", attrName);
				}
			}
			file.print("\t}\n\n");

			// End of class
			file.println("}\n");
			
			file.close();

			System.out.printf(" done\n");
		}

		System.out.printf("Done generating Java DTO's.\n\n");
	}

	public DTOGenerator()
	{
	}
}
