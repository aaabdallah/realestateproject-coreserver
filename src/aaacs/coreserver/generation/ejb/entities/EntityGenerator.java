package aaacs.coreserver.generation.ejb.entities;

import static aaacs.coreserver.commons.generation.Utilities.firstLetterToUpperCase;
import static aaacs.coreserver.commons.generation.Utilities.getCommentIndent;
import static aaacs.coreserver.commons.generation.Utilities.getCommentType;
import static aaacs.coreserver.commons.generation.Utilities.getNodeAttribute;
import static aaacs.coreserver.commons.generation.Utilities.getNodeNumber;
import static aaacs.coreserver.commons.generation.Utilities.getNodeText;
import static aaacs.coreserver.commons.generation.Utilities.goToNode;
import static aaacs.coreserver.commons.generation.Utilities.javaComment;
import static aaacs.coreserver.commons.generation.Utilities.javaDocComment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

import javax.persistence.Column;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.CodeFragmentsGenerator;
import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;

/**
 * @author Ahmed A. Abd-Allah, Nov 12, 2006
 * 
 * Generates Java code from XML descriptors of Core System entities.
 */
public class EntityGenerator extends Generator
{
	public static void generateEntities(String outputDirectory,
		LinkedHashMap<String, EntityGenerator> entityGenerators)
		throws IOException, GenerationException
	{
		if (outputDirectory == null || outputDirectory.trim().equals(""))
			throw new GenerationException("Must configure output directory " +
					"for Java entities generation.");

		for (String className : entityGenerators.keySet())
		{
			EntityGenerator classGenerator = entityGenerators.get(className);
			System.out.printf("Generating entity for %s ...", className);
			classGenerator.writeClass(outputDirectory);
			System.out.printf(" done\n");
		}

		for (String className : entityGenerators.keySet())
		{
			EntityGenerator classGenerator = entityGenerators.get(className);
			System.out.printf("Generating resources for %s ...", className);
			writeResources(classGenerator);
			System.out.printf(" done\n");
		}
		//PrintWriter resFile = new PrintWriter(
		System.out.printf("Done generating Java entities.\n\n");
	}

	public static void writeResources(EntityGenerator classGenerator)
		throws IOException
	{
		PrintWriter resFile = 
			new PrintWriter(new FileWriter(classGenerator.classResourcesFile, 
				true));
		resFile.print(
			"# ----- Entity & field names for " + englishize(classGenerator.className) + " -----\n"
			);
		resFile.println(classGenerator.className + "=" + englishize(classGenerator.className));
		for (String attrName : classGenerator.attributes.keySet())
		{
			resFile.println(classGenerator.className + "." + attrName + "=" + englishize(attrName));
		}
		resFile.println();
		resFile.close();
	}
	
	private static String englishize(String attrName)
	{
		if (attrName == null) return null;

		attrName = attrName.trim();
		if (attrName.length() == 0)
			return "";
		else if (attrName.length() == 1)
			return "" + Character.toUpperCase(attrName.charAt(0));
		attrName = Character.toUpperCase(attrName.charAt(0)) + attrName.substring(1).replaceAll("([A-Z])", " $1");
		return attrName;
	}

	public Node rootNode = null;
	public boolean cached = false;
	public boolean searchable = false;
	private String classDirectory = null;
	private String classResourcesFile = null;
	private String className = null;
	private String classComment = null;
	private String classAnnotations = "";
	private String classPackage = null;
	private String classImports = null;
	private boolean standardSQLResultSetMapping = false;
	public LinkedHashMap<String, LinkedHashMap<String, Object>> attributes =
		new LinkedHashMap<String, LinkedHashMap<String, Object>>();
	public LinkedHashMap<String, LinkedHashMap<String, String>> enums =
		new LinkedHashMap<String, LinkedHashMap<String, String>>();
	private LinkedHashMap<String, LinkedHashMap<String, String>> cacheSearchParameters =
		new LinkedHashMap<String, LinkedHashMap<String, String>>();
	private CodeFragmentsGenerator codeFragments = null;
	private SQLResultSetMappingGenerator sqlResultMappingGenerator = null;

	public EntityGenerator(String defaultResourcesFile, String defaultPackage, 
		String defaultImports,  Node inRootNode)
		throws GenerationException
	{
		if (inRootNode == null)
			throw new GenerationException(
				"Element for entity root node should not be null");
		rootNode = inRootNode;
		
		if (goToNode(rootNode, "cache") != null)
			cached = true;
		Node classNode = goToNode(rootNode, "class");
		Node attributesNode = goToNode(rootNode, "attributes");
		Node codeFragmentsNode = goToNode(rootNode, "codeFragments");

		if (classNode == null)
			throw new GenerationException(
				"Element for \"class\" should not be null");
		if (attributesNode == null)
			throw new GenerationException(
				"Element for \"attributes\" should not be null");

		classPackage = defaultPackage;
		classImports = defaultImports;
		classResourcesFile = defaultResourcesFile;
		extractClassInformation(classNode);
		extractAttributesInformation(attributesNode, true, true);
		codeFragments = new CodeFragmentsGenerator(codeFragmentsNode);
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	private void extractClassAnnotations(Node annotationsNode)
		throws GenerationException
	{
		NodeList children = annotationsNode.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++)
		{
			Node child = children.item(i);
			String childName = child.getNodeName();
	
			// Get class name
			if (childName.equals("entity"))
				classAnnotations += 
				"@Entity(name=\"" +
				goToNode(rootNode, "class.name").getTextContent() +
				"\")" +
				" @Table(name=\"\\\"" +
				goToNode(rootNode, "table.name").getTextContent() +
				"\\\"\")";
			else if (childName.equals("sqlResultSetMappings"))
			{
				if (goToNode(child, "standardMapping") != null)
					standardSQLResultSetMapping = true;
				sqlResultMappingGenerator = new SQLResultSetMappingGenerator(child);
			}
		}
	}
	
	private void extractClassEnums(Node enumsNode)
		throws GenerationException
	{
		NodeList children = enumsNode.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++)
		{
			Node enumNode = children.item(i);
			String enumName = getNodeAttribute(enumNode, "name");
			String enumValueType = getNodeAttribute(enumNode, "type");
			String enumBitMask = getNodeAttribute(enumNode, "bitMask");

			LinkedHashMap<String, String> oneEnum = new LinkedHashMap<String, String>();

			NodeList enumValueNodes = enumNode.getChildNodes();
			for (int j=0; j<enumValueNodes.getLength(); j++)
			{
				Node enumValueNode = enumValueNodes.item(j);
				if ( enumValueNode.getNodeName().equals("constant") )
				{
					String constantName = getNodeAttribute(enumValueNode, "name");
					String valueName = getNodeAttribute(enumValueNode, "value");
					oneEnum.put(constantName, valueName);
				}
				else if ( enumValueNode.getNodeName().equals("comment") )
				{
					if (getCommentType(enumValueNode).equals("JavaDoc"))
						oneEnum.put("__COMMENT__", javaDocComment(enumValueNode.getTextContent(), 
							getCommentIndent(enumValueNode)));
					else if (getCommentType(enumValueNode).equals("Java"))
						oneEnum.put("__COMMENT__", javaComment(enumValueNode.getTextContent(), 
							getCommentIndent(enumValueNode)));
				}
			}
			enums.put(enumName + " " + enumValueType + " " + enumBitMask, oneEnum);
		}
	}

	private void extractClassInformation(Node classNode)
		throws GenerationException
	{
		if (!classNode.getNodeName().equals("class"))
			throw new GenerationException("Expected a \"class\" element!");
	
		NodeList children = classNode.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();
	
			// Get class name
			if (childName.equals("name"))
				className = child.getTextContent();
			else if (childName.equals("outputDirectory"))
				classDirectory = child.getTextContent();
			else if (childName.equals("resourcesFile"))
				classResourcesFile = child.getTextContent();
			else if (childName.equals("package"))
				classPackage = child.getTextContent();
			else if (childName.equals("import"))
				classImports += "\nimport " + child.getTextContent() + ";";
			else if (childName.equals("annotations"))
			{
				extractClassAnnotations(child);
			}
			else if (childName.equals("enums"))
			{
				extractClassEnums(child);
			}
			else if (childName.equals("comment"))
			{
				if (getCommentType(child).equals("JavaDoc"))
					classComment = javaDocComment(child.getTextContent(), 
						getCommentIndent(child));
				else
					classComment = javaComment(child.getTextContent(), 
						getCommentIndent(child));				
			}
			else
				throw new GenerationException("Unknown child of \"class\" element");
		}
	}

	private String extractAttributeValidationChoices(String attributeName,
		String validationType, Node choiceNode)
	{
		if (choiceNode == null)
			return null;

		String typeOfChoice = choiceNode.getFirstChild().getNodeName();
		if (typeOfChoice.equals("static"))
			return choiceNode.getFirstChild().getTextContent();
		else if (typeOfChoice.equals("cache"))
		{
			String table = "\\\"" + goToNode(choiceNode, "cache.table").getTextContent().trim() + "\\\"";
			String column = goToNode(choiceNode, "cache.attribute").getTextContent().trim();
			Node constraintsNode = goToNode(choiceNode, "cache.constraints");
			
			if (constraintsNode != null)
			{
				NodeList constraints = constraintsNode.getChildNodes();
				LinkedHashMap<String, String> nameValues = null;
				if (constraints.getLength() > 0)
					nameValues = new LinkedHashMap<String, String>();

				for (int i=0; i<constraints.getLength(); i++ ) 
				{
					Node constraint = constraints.item(i);
					String name = getNodeText(goToNode(constraint, "name"));
					String value = getNodeText(goToNode(constraint, "value"));

					nameValues.put(name, value);
				}
				cacheSearchParameters.put(attributeName, nameValues);
				return "CacheManager.get" + validationType + "Column(\"" + table + "\", \"" +
					column + "\", searchParameters)";
			}
			
			return "CacheManager.get" + validationType + "Column(\"" + table + "\", \"" +
				column + "\", null)";
		}
		else if (typeOfChoice.equals("database"))
			return "null /*DATABASE VALIDATION NOT SUPPORTED YET*/";
		return "/*NOT SUPPORTED YET*/";
	}
	
	private void extractAttributeValidation(String attributeName, 
		Node validationNode, LinkedHashMap<String, Object> attributeProps)
		throws GenerationException
	{
		if (validationNode == null) // no validation.
			return;
			
		if (!validationNode.getNodeName().equals("validation"))
			throw new GenerationException("Expected a \"validation\" element!" +
					" (found: " + validationNode.getNodeName() + ")");
		
		String validationString = ".clearChecks().setName(\"" + attributeName + "\")";
		String validationType = validationNode.getFirstChild().getNodeName();
		
		if (validationType.equals("Byte") || validationType.equals("Short") ||
			validationType.equals("Integer") || validationType.equals("Long"))
		{
			String useInvalidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices"), "useChoicesAs");
			String useValidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".validChoices"), "useChoicesAs");
			if (useInvalidChoicesAs != null) useInvalidChoicesAs = "\"" + useInvalidChoicesAs + "\"";
			if (useValidChoicesAs != null) useValidChoicesAs = "\"" + useValidChoicesAs + "\"";
			String castType = "double";

			if (getNodeText(goToNode(validationNode, validationType + ".allowNull")) != null) 
				validationString += ".setAllowNullElement(" + 
					getNodeText(goToNode(validationNode, validationType + ".allowNull")) + ")";
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.min")) != null) 
				validationString += ".setInvalidMinimum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.min")) + ")";
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.max")) != null) 
				validationString += ".setInvalidMaximum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.max")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) != null) 
				validationString += ".setInvalidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices")) != null) 
				validationString += ".setInvalidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".unacceptableValues.invalidChoices")) + ")";
			if (useInvalidChoicesAs != null) 
				validationString += ".setUseInvalidChoicesAs(" + useInvalidChoicesAs + ")";
			
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".min")) != null) 
				validationString += ".setValidMinimum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".min")) + ")";
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".max")) != null) 
				validationString += ".setValidMaximum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".max")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".regex")) != null) 
				validationString += ".setValidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".validChoices")) != null) 
				validationString += ".setValidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".validChoices")) + ")";
			if (useValidChoicesAs != null) 
				validationString += ".setUseValidChoicesAs(" + useValidChoicesAs + ")";
		}
		else if (validationType.equals("String"))
		{
			String useInvalidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices"), "useChoicesAs");
			String useValidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".validChoices"), "useChoicesAs");
			if (useInvalidChoicesAs != null) useInvalidChoicesAs = "\"" + useInvalidChoicesAs + "\"";
			if (useValidChoicesAs != null) useValidChoicesAs = "\"" + useValidChoicesAs + "\"";

			if (getNodeText(goToNode(validationNode, validationType + ".allowNull")) != null) 
				validationString += ".setAllowNullElement(" + 
					getNodeText(goToNode(validationNode, validationType + ".allowNull")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.minLength")) != null) 
				validationString += ".setInvalidMinimum((double) " + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.minLength")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.maxLength")) != null) 
				validationString += ".setInvalidMaximum((double) " + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.maxLength")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) != null) 
				validationString += ".setInvalidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices")) != null) 
				validationString += ".setInvalidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".unacceptableValues.invalidChoices")) + ")";
			if (useInvalidChoicesAs != null) 
				validationString += ".setUseInvalidChoicesAs(" + useInvalidChoicesAs + ")";

			if (getNodeText(goToNode(validationNode, validationType + ".minLength")) != null) 
				validationString += ".setValidMinimum((double) " + 
					getNodeText(goToNode(validationNode, validationType + ".minLength")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".maxLength")) != null) 
				validationString += ".setValidMaximum((double) " + 
					getNodeText(goToNode(validationNode, validationType + ".maxLength")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".regex")) != null) 
				validationString += ".setValidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".validChoices")) != null) 
				validationString += ".setValidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".validChoices")) + ")";
			if (useValidChoicesAs != null) 
				validationString += ".setUseValidChoicesAs(" + useValidChoicesAs + ")";
		}
		else if (validationType.equals("Float") || validationType.equals("Double"))
		{
			String useInvalidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices"), "useChoicesAs");
			String useValidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".validChoices"), "useChoicesAs");
			if (useInvalidChoicesAs != null) useInvalidChoicesAs = "\"" + useInvalidChoicesAs + "\"";
			if (useValidChoicesAs != null) useValidChoicesAs = "\"" + useValidChoicesAs + "\"";
			String castType = "double"; 

			if (getNodeText(goToNode(validationNode, validationType + ".allowNull")) != null) 
				validationString += ".setAllowNullElement(" + 
					getNodeText(goToNode(validationNode, validationType + ".allowNull")) + ")";
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.min")) != null) 
				validationString += ".setInvalidMinimum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.min")) + ")";
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.max")) != null) 
				validationString += ".setInvalidMaximum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".unacceptableValues.max")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) != null) 
				validationString += ".setInvalidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices")) != null) 
				validationString += ".setInvalidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".unacceptableValues.invalidChoices")) + ")";
			if (useInvalidChoicesAs != null) 
				validationString += ".setUseInvalidChoicesAs(" + useInvalidChoicesAs + ")";
			
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".min")) != null) 
				validationString += ".setValidMinimum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".min")) + ")";
			if (getNodeNumber(castType, goToNode(validationNode, validationType + ".max")) != null) 
				validationString += ".setValidMaximum(" + 
					getNodeNumber(castType, goToNode(validationNode, validationType + ".max")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".maxFracLength")) != null)
				validationString += ".setMaxFracLength(" +
					getNodeText(goToNode(validationNode, validationType + ".maxFracLength")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".regex")) != null) 
				validationString += ".setValidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".validChoices")) != null) 
				validationString += ".setValidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".validChoices")) + ")";
			if (useValidChoicesAs != null) 
				validationString += ".setUseValidChoicesAs(" + useValidChoicesAs + ")";
		}
		else if (validationType.equals("Date") || validationType.equals("Timestamp"))
		{
			String useInvalidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices"), "useChoicesAs");
			String useValidChoicesAs = getNodeAttribute(goToNode(validationNode, validationType + 
				".validChoices"), "useChoicesAs");
			if (useInvalidChoicesAs != null) useInvalidChoicesAs = "\"" + useInvalidChoicesAs + "\"";
			if (useValidChoicesAs != null) useValidChoicesAs = "\"" + useValidChoicesAs + "\"";

			if (getNodeText(goToNode(validationNode, validationType + ".allowNull")) != null) 
				validationString += ".setAllowNullElement(" + 
					getNodeText(goToNode(validationNode, validationType + ".allowNull")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.earliest")) != null) 
				validationString += ".setInvalidEarliest(" + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.earliest")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.latest")) != null) 
				validationString += ".setInvalidLatest(" + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.latest")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) != null) 
				validationString += ".setInvalidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".unacceptableValues.regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices")) != null) 
				validationString += ".setInvalidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".unacceptableValues.invalidChoices")) + ")";
			if (useInvalidChoicesAs != null) 
				validationString += ".setUseInvalidChoicesAs(" + useInvalidChoicesAs + ")";

			if (getNodeText(goToNode(validationNode, validationType + ".earliest")) != null) 
				validationString += ".setValidEarliest(" + 
					getNodeText(goToNode(validationNode, validationType + ".earliest")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".latest")) != null) 
				validationString += ".setValidLatest(" + 
					getNodeText(goToNode(validationNode, validationType + ".latest")) + ")";
			if (getNodeText(goToNode(validationNode, validationType + ".regex")) != null) 
				validationString += ".setValidRegex(" + 
					getNodeText(goToNode(validationNode, validationType + ".regex")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".validChoices")) != null) 
				validationString += ".setValidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".validChoices")) + ")";
			if (useValidChoicesAs != null) 
				validationString += ".setUseValidChoicesAs(" + useValidChoicesAs + ")";
		}
		else if (validationType.equals("Boolean"))
		{
			if (getNodeText(goToNode(validationNode, validationType + ".allowNull")) != null) 
				validationString += ".setAllowNullElement(" + 
					getNodeText(goToNode(validationNode, validationType + ".allowNull")) + ")";
			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".unacceptableValues.invalidChoices")) != null) 
				validationString += ".setInvalidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".unacceptableValues.invalidChoices")) + ")";

			if (extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
				".validChoices")) != null) 
				validationString += ".setValidChoices(" + 
					extractAttributeValidationChoices(attributeName, validationType, goToNode(validationNode, validationType + 
					".validChoices")) + ")";
		}
		else if (validationType.equals("Groups"))
		{
			if (getNodeText(goToNode(validationNode, validationType + ".allowNull")) != null) 
				validationString += ".setAllowNullElement(" + 
					getNodeText(goToNode(validationNode, validationType + ".allowNull")) + ")";
		}

		attributeProps.put("validation", "validator" + validationString + ".check" + validationType + "(" +
			attributeName + ")"
			);
		attributeProps.put("staticValidator", "\tstatic public Map<String, List<MsgArgsPair>> validate" + 
			Utilities.firstLetterToUpperCase(attributeName) + "(" + validationType + " " + attributeName + ")\n\t{\n" +
			"\t\treturn (new FieldValidator())" + validationString + ".check" + validationType + "(" + attributeName + ");\n\t}\n\n");

		// DTO validators: some of them are simply too tied to the CoreServer to support
		// validation at the DTO level, notably validation that relies on database
		// assumptions such reserved primary keys. Hence there are some attributes
		// whose DTO validators simply do NOT validate.
		if (attributeName.toLowerCase().endsWith("idk") || // see directly above for rationale
			attributeName.toLowerCase().equals("actionversion")) // used in PermissionDTO; cannot be done because it relies on Action.ActionVersion.MATCH_ALL_ACTION_VERSIONS
		{
			attributeProps.put("dtoValidator", "\tpublic void validate" + Utilities.firstLetterToUpperCase(attributeName) + 
				"(FacesContext context, UIComponent component, Object oValue)\n\t{\n" + 
				"\t}\n\n");
		}
		else
		{
			attributeProps.put("dtoValidator", "\tpublic void validate" + Utilities.firstLetterToUpperCase(attributeName) + 
				"(FacesContext context, UIComponent component, Object oValue)\n\t{\n" + 
				"\t\tif (!getValidating()) return;\n" +
				"\t\tMap<String, List<MsgArgsPair>> results =\n\t\t\t(new FieldValidator())" + 
					// validationString + ".check" + validationType + "(" + attributeName + ");\n" +
					validationString + ".check" + validationType + "( (" + validationType + ") oValue);\n" +
				"\t\tif (results != null && results.get(\"" + attributeName + "\").size() >= 1)\n\t\t{\n" +
				"\t\t\t((UIInput) component).setValid(false);\n" +
				"\t\t\tfor (MsgArgsPair vResult : results.get(\"" + attributeName + "\"))\n\t\t\t{\n" +
				"\t\t\t\tcontext.addMessage(component.getClientId(context),\n" +
				"\t\t\t\t\tnew FacesMessage( localizeErrorMessage(context,\n" +
				"\t\t\t\t\t\tcomponent.getParent().getId() + Utilities.firstLetterToUpperCase(component.getId()), vResult) ));\n" +
				"\t\t\t}\n\t\t}\n" +
				"\t}\n");
		}
	}
	
	private void extractAttributeMessageResource(String attributeName, 
		Node messageResourceNode, LinkedHashMap<String, Object> attributeProps)
		throws GenerationException
	{
		if (messageResourceNode == null) // no message resource related information.
			return;
			
		if (!messageResourceNode.getNodeName().equals("messageResource"))
			throw new GenerationException("Expected a \"messageResource\" element!" +
					" (found: " + messageResourceNode.getNodeName() + ")");
		
		String lookupMechanism = getNodeAttribute(messageResourceNode, "lookupMechanism");
		if (lookupMechanism != null)
			attributeProps.put("messageResourceLookupMechanism", lookupMechanism);
		
		if (lookupMechanism != null && !lookupMechanism.equals("cacheLookup"))
			throw new GenerationException("Unrecognized lookupMechanism for an attribute's message resource");

		String source = getNodeAttribute(messageResourceNode, "source");
		if (source != null)
			attributeProps.put("messageResourceSource", source);

		String key = getNodeAttribute(messageResourceNode, "key");
		if (key != null)
			attributeProps.put("messageResourceKey", key);
	}
	
	private void extractAttributeDTOInformation(String attributeName, 
		Node dtoNode, LinkedHashMap<String, Object> attributeProps)
		throws GenerationException
	{
		if (dtoNode == null) // no DTO-related information.
			return;
			
		if (!dtoNode.getNodeName().equals("dto"))
			throw new GenerationException("Expected a \"dto\" element!" +
					" (found: " + dtoNode.getNodeName() + ")");
		
		String cacheLookup = getNodeAttribute(dtoNode, "cacheLookup");
		if (cacheLookup != null && cacheLookup.equalsIgnoreCase("true"))
			attributeProps.put("dtoCacheLookup", "true");

		String defaultValue = getNodeAttribute(dtoNode, "defaultValue");
		if (defaultValue != null)
			attributeProps.put("dtoDefaultValue", defaultValue);
	}

	private void extractSearchCriteria(String attributeName, 
		Node searchCriteriaNode, LinkedHashMap<String, Object> attributeProps)
		throws GenerationException
	{
		if (searchCriteriaNode == null) // no search criteria
			return;

		if (!searchCriteriaNode.getNodeName().equals("searchCriteria"))
			throw new GenerationException("Expected a \"searchCriteria\" element!" +
					" (found: " + searchCriteriaNode.getNodeName() + ")");
		
		NodeList children = searchCriteriaNode.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++)
		{
			Node child = children.item(i);
			String childName = child.getNodeName();

			attributeProps.put("searchCriteria", childName);
			if (childName.equals("options") ||
				childName.equals("equals") ||
				childName.equals("min") ||
				childName.equals("max") ||
				childName.equals("minmax"))
			{
				attributeProps.put("searchCriteriaType", getNodeAttribute(child, "type"));
				if (childName.equals("min") || childName.equals("minmax"))
					attributeProps.put("searchCriteriaIncludeMin", getNodeAttribute(child, "includeMin"));
				if (childName.equals("max") || childName.equals("minmax"))
					attributeProps.put("searchCriteriaIncludeMax", getNodeAttribute(child, "includeMax"));
				if (childName.equals("equals"))
					attributeProps.put("searchDefaultValue", getNodeAttribute(child, "defaultValue"));
			}
			else if (childName.equals("boolean"))
			{
				attributeProps.put("searchCriteriaTrueMeaning", getNodeAttribute(child, "trueMeaning"));
				attributeProps.put("searchCriteriaFalseMeaning", getNodeAttribute(child, "falseMeaning"));
				attributeProps.put("searchDefaultValue", getNodeAttribute(child, "defaultValue"));
			}
			else if (childName.equals("substring"))
			{
			}
			else
				throw new GenerationException("Unknown child of \"searchCriteria\" element");
			attributeProps.put("customSearchConstraint", getNodeAttribute(child, "customSearchConstraint"));
			searchable = true; // flag this entity as searchable (for SearchDTO generation purposes)
		}
	}
	
	private void extractAttribute(Node node, boolean useInAttributes,
		boolean useInMapping)
		throws GenerationException
	{
		if (!node.getNodeName().equals("attribute"))
			throw new GenerationException("Expected an \"attribute\" element!");
	
		String attributeName = null;
		LinkedHashMap<String, Object> attributeProps = new LinkedHashMap<String, Object>();
		
		NodeList children = node.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();
	
			if (childName.equals("columnDescription"))
			{
				attributeProps.put("sqlName", getNodeAttribute(child, "name"));
			}
			else if (childName.equals("javaFieldDescription"))
			{
				attributeName = getNodeAttribute(child, "name");
				String sqlAttributeName = (String) attributeProps.get("sqlName");
				if (!sqlAttributeName.equals(attributeName)) // used to be case insensitive; changed to force exact name.
					throw new GenerationException("SQL column name and Java field name do not match: " +
						attributeName + " " + sqlAttributeName); 
				attributeProps.put("type", getNodeAttribute(child, "type"));
				
				String defaultValue = getNodeAttribute(child, "defaultValue");
				if (defaultValue != null)
					attributeProps.put("defaultValue", defaultValue);
				
				Node commentNode = goToNode(child, "comment");
				if (commentNode != null)
				{
					if (getCommentType(commentNode).equals("JavaDoc"))
						attributeProps.put("comment", 
							javaDocComment(commentNode.getTextContent(), 
							getCommentIndent(commentNode)));
					else
						attributeProps.put("comment", 
							javaComment(commentNode.getTextContent(), 
							getCommentIndent(commentNode)));
				}
				
				Node attributeAnnotations = goToNode(child, "annotations");
				if (attributeAnnotations != null)
				{
					NodeList annoChildren = attributeAnnotations.getChildNodes();
					for (int j=0; j<annoChildren.getLength(); j++ )
					{
						Node annoChild = annoChildren.item(j);
						String annoChildName = annoChild.getNodeName();
						if (annoChildName.equals("basic"))
							attributeProps.put("annotationBasic", true);
					}
				}
				
				Node validationNode = goToNode(child, "validation");
				extractAttributeValidation(attributeName, validationNode, attributeProps);
				
				Node messageResourceNode = goToNode(child, "messageResource");
				extractAttributeMessageResource(attributeName, messageResourceNode, attributeProps);

				Node dtoNode = goToNode(child, "dto");
				extractAttributeDTOInformation(attributeName, dtoNode, attributeProps);
			}
			else if (childName.equals("searchCriteria"))
			{
				extractSearchCriteria(attributeName, child, attributeProps);
			}
			else
				throw new GenerationException("Unknown child of \"attribute\" element");
		}
		attributeProps.put("useInAttributes", useInAttributes);
		attributeProps.put("useInMapping", useInMapping);
		attributes.put(attributeName, attributeProps);
	}

	private void extractAttributesInformation(Node attributesNode, boolean useInAttributes,
		boolean useInMapping)
		throws GenerationException
	{
		if (!attributesNode.getNodeName().equals("attributes"))
			throw new GenerationException("Expected an \"attributes\" element!");
	
		NodeList children = attributesNode.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();
	
			if (childName.equals("attribute"))
				extractAttribute(child, useInAttributes, useInMapping);
			else if (childName.equals("includeAttributes"))
			{
				boolean incAttrsUseInAttributes = false, 
					incAttrsUseInMapping = false;

				if (getNodeAttribute(child, "useInJavaAttributes").equals("true"))
					incAttrsUseInAttributes = true;
				if (getNodeAttribute(child, "useInJavaMapping").equals("true"))
					incAttrsUseInMapping = true;
				Document document = 
					validateFile(new File(child.getFirstChild().getTextContent()));
				if (document != null)
				{
					stripEmptyTextNodes(document.getFirstChild());
					extractAttributesInformation(document.getFirstChild(),
						incAttrsUseInAttributes, incAttrsUseInMapping);
				}
				else
					throw new GenerationException("Unable to load included attributes");
			}
			else
				throw new GenerationException("Unknown child of \"attributes\" element: " +
					childName);
		}
	}
	
	public void writeClass(String outputDirectory)
		throws IOException, GenerationException
	{
		if (classDirectory != null)
			outputDirectory = classDirectory;
			
		PrintWriter file = 
			new PrintWriter(new FileWriter(outputDirectory + "/" + className + ".java", 
				false));
		
		file.println(
			"/* *************************************************************************** \n" +
			" * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * \n" +
			" * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * \n" +
			" * Copyright Ahmed A. Abd-Allah, 2006                                        * \n" +
			" * ***************************************************************************/\n");

		if (classPackage != null)
			file.println("package " + classPackage + ";");
		if (classImports != null)
			file.println(classImports + "\n");
		if (classComment != null)
			file.println(classComment);
		if (standardSQLResultSetMapping || 
			(sqlResultMappingGenerator != null && sqlResultMappingGenerator.hasMappings()))
		{
			file.print("@SqlResultSetMappings\n(\n\tvalue =\n\t{\n");
			if (standardSQLResultSetMapping)
			{
				file.printf("\t\t@SqlResultSetMapping\n\t\t(\n\t\t\tname = \"List<%s>\",\n" +
					"\t\t\tentities =\n\t\t\t{\n\t\t\t\t@EntityResult\n\t\t\t\t(\n" +
					"\t\t\t\t\tentityClass = %s.class,\n" +
					"\t\t\t\t\tfields = \n\t\t\t\t\t{\n", className, className);
				boolean foundOneMapping = false;
				for (String attrName : attributes.keySet())
				{
					if (foundOneMapping)
						file.print(",\n");
					LinkedHashMap<String, Object> attrProps = attributes.get(attrName);
					if ( !(Boolean) attrProps.get("useInMapping") )
						continue;
					String sqlName = (String) attrProps.get("sqlName");
					file.printf("\t\t\t\t\t\t@FieldResult(name = \"%s\", column = \"%s\")",
						attrName, sqlName); // sqlName.tlc
					foundOneMapping = true;
				}
				file.print("\n\t\t\t\t\t}\n\t\t\t\t)\n\t\t\t}\n\t\t)");
				if (sqlResultMappingGenerator != null &&
					sqlResultMappingGenerator.hasMappings())
					file.print(",\n");
				else
					file.print("\n");
			}
			if (sqlResultMappingGenerator != null && sqlResultMappingGenerator.hasMappings())
				sqlResultMappingGenerator.writeMappings(file);
			file.print("\t}\n)\n");
		}
		if (classAnnotations.trim().length() > 0)
			file.println(classAnnotations);
		file.printf("public class %s extends BaseEntity implements Serializable\n{\n", className);

		// ----- enums -----
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
			"\tprivate static final long serialVersionUID = 1000L;\n");
		file.printf("\t/**\n\t * Resources file\n\t */\n" +
			"\tpublic static final String entityResourceFile = \"%s\";\n", 
			classResourcesFile.replaceFirst(".properties$", "").replaceAll("/", "."));
		file.printf("\t/**\n\t * Entity table name\n\t */\n" +
			"\tpublic static final String entityTableName = \"\\\"%s\\\"\";\n\n", 
			goToNode(rootNode, "table.name").getTextContent());		
		
		file.printf(
		"\tstatic public %s findByIdk(EntityManager manager, long inputIdk)\n" +
		"\t{\n\t\ttry { return (%s) BaseEntity.findByIdk(manager, " +
		"%s.class, inputIdk); }\n" +
		"\t\tcatch (Exception e) { return null; }\n" +
		"\t}\n\n" +

		"\tstatic public List find%sByFields(EntityManager manager,\n" +
		"\t\tMap<String,Object> searchFields)\n\t{\n" +
		"\t\tString[] tables = { entityTableName };\n" +
		"\t\treturn BaseEntity.findByFields(manager, tables, null,\n" + 
		"\t\t\tsearchFields, \"List<%s>\");\n\t}\n\n" +
		
		"\tstatic public List findByFields(EntityManager manager, String[] columnNames,\n" +
		"\t\tMap<String,Object> searchFields, String sqlResultSetMapping)\n\t{\n" +
		"\t\tString[] tables = { entityTableName };\n" +
		"\t\treturn BaseEntity.findByFields(manager, tables, columnNames,\n" + 
		"\t\t\tsearchFields, sqlResultSetMapping);\n\t}\n\n" +
		
		"\tstatic public List find%sByCondition(EntityManager manager,\n" +
		"\t\tString condition, Vector<Object> conditionParameters)\n\t{\n" +
		"\t\tString[] tables = { entityTableName };\n" +		
		"\t\treturn BaseEntity.findByCondition(manager, tables, null,\n" +
		"\t\t\tcondition, conditionParameters, \"List<%s>\");\n\t}\n\n" +
		
		"\tstatic public List findByCondition(EntityManager manager, String[] columnNames,\n" +
		"\t\tString condition, Vector<Object> conditionParameters, String sqlResultSetMapping)\n\t{\n" +
		"\t\tString[] tables = { entityTableName };\n" +		
		"\t\treturn BaseEntity.findByCondition(manager, tables, columnNames,\n" +
		"\t\t\tcondition, conditionParameters, sqlResultSetMapping);\n\t}\n\n" +
		
		"\tstatic public int updateByFields(EntityManager manager, Map<String,Object> fieldsToSet,\n" + 
		"\t\tMap<String,Object> searchFields)\n\t{\n" +
		"\t\treturn BaseEntity.updateByFields(manager, entityTableName, " +
		"\n\t\t\tfieldsToSet, searchFields);\n\t}\n\n" + 

		"\tstatic public int updateByCondition(EntityManager manager, LinkedHashMap<String,Object> fieldsToSet,\n" + 
		"\t\tString condition, Vector<Object> conditionParameters)\n\t{\n" +
		"\t\treturn BaseEntity.updateByCondition(manager, entityTableName, fieldsToSet," +
		"\n\t\t\tcondition, conditionParameters);\n\t}\n\n" +

		"\tstatic public int removeByFields(EntityManager manager, Map<String,Object> searchFields)\n\t{\n" + 
		"\t\treturn BaseEntity.removeByFields(manager, entityTableName, " +
		"\n\t\t\tsearchFields);\n\t}\n\n" + 

		"\tstatic public int removeByCondition(EntityManager manager, String condition,\n" +
		"\t\tVector<Object> conditionParameters)\n\t{\n" +
		"\t\treturn BaseEntity.removeByCondition(manager, entityTableName, " +
		"\n\t\t\tcondition, conditionParameters);\n\t}\n\n", 
		className, className, className, className, className, className, className, 
		className, className, className, className, className);

		if (cached)
		{
			file.print(
				"\tstatic public Map<String, Object> cacheLookup(long inIdk)\n" +
				"\t{\n\t\ttry\n\t\t{\n" +
				"\t\t\treturn CacheManager.findByIdk(entityTableName,\n" +
				"\t\t\t\tinIdk);\n\t\t}\n\t\tcatch (Exception e)\n\t\t{\n" +
				"\t\t\treturn null;\n\t\t}\n\t}\n\n"
				);
			file.print(
				"\tstatic public Map<Long, Map<String, Object>> cacheLookup(\n" +
				"\t\tMap<String, Object> searchParameters)\n\t{\n\t\ttry\n\t\t{\n" +
				"\t\t\treturn CacheManager.findByFields(entityTableName,\n" +
				"\t\t\t\tsearchParameters, 0);\n\t\t}\n\t\tcatch (Exception e)\n\t\t{\n" +
				"\t\t\treturn null;\n\t\t}\n\t}\n\n"
				);
		}
		codeFragments.insertCodeFragments(file, "entity", CodeFragmentsGenerator.CIP_STATICMETHOD);

		// Static validator for a single entity
		file.print("\tstatic public Map<String, List<MsgArgsPair>> validate" + className + 
			"(" + className + " " + Utilities.firstLetterToLowerCase(className) + ",\n" +
			"\t\tboolean userSuppliedIdk, List<String> specificFields, boolean exclude)\n\t{\n" +
			"\t\treturn " + Utilities.firstLetterToLowerCase(className) + 
			".validateAllFields(userSuppliedIdk, specificFields, exclude);\n\t}\n\n");

		// Static validator helper methods
		for (String attrName : attributes.keySet())
		{
			LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

			if ( !(Boolean) attrProps.get("useInAttributes") )
				continue;

			file.print(attrProps.get("staticValidator"));
		}
		
		// if searchable, then helper method for SQL search fragments
		if (searchable)
		{
			file.printf("\t// For constructing SQL fragments suitable for searching for instances/rows of this entity\n");
			file.print("\tstatic public void applySearchFields(Map<String, Object> searchFields, SelectBuilder selectBuilder)\n" +
				"\t{\n\t\tif (searchFields == null || searchFields.isEmpty()) return;\n\n");
			file.println("\t\tselectBuilder.addFrom(entityTableName);\n");
			
			for (String attrName : attributes.keySet())
			{
				LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

				if ( !(Boolean) attrProps.get("useInAttributes") )
					continue;

				if (attrProps.containsKey("searchCriteria"))
				{
					String searchCriteria = (String) attrProps.get("searchCriteria");
					String searchCriteriaType = (String) attrProps.get("searchCriteriaType");
					String searchCriteriaIncludeMin = (String) attrProps.get("searchCriteriaIncludeMin");
					String searchCriteriaIncludeMax = (String) attrProps.get("searchCriteriaIncludeMax");
					String customSearchConstraint = (String) attrProps.get("customSearchConstraint");
					boolean quote = false;
					if (searchCriteriaType != null && searchCriteriaType.equals("String"))
						quote = true;
					
					// (options | equals | min | max | minmax | boolean | substring)
					if (searchCriteria.equals("equals"))
					{
						if (customSearchConstraint != null)
							file.print(CodeFragmentsGenerator.getCodeFragment(customSearchConstraint, null, null, null, null));
						else
							file.printf("\t\tif (searchFields.get(\"%s\") != null && searchFields.get(\"%s\").toString().length() > 0)\n" +
								"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" = \" + %ssearchFields.get(\"%s\")%s, true);\n",
								attrName, attrName, attrName, 
								(quote ? "\"$$\" + " : ""),
								attrName,
								(quote ? " + \"$$\"" : ""));
					}
					else if (searchCriteria.equals("options"))
					{
						if (customSearchConstraint != null)
							file.print(CodeFragmentsGenerator.getCodeFragment(customSearchConstraint, null, null, null, null));
						else
							file.printf("\t\tif (searchFields.get(\"%s\") != null && ((%s[]) searchFields.get(\"%s\")).length > 0)\n" +
								"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" IN (\" +\n" +
								"\t\t\t\tArrayHelper.toString((%s[]) searchFields.get(\"%s\"), %s, %s) + \")\", true);\n",
								attrName, searchCriteriaType, attrName, attrName, 
								searchCriteriaType, attrName,
								(quote ? "\"$$\"" : "\"\""),
								(quote ? "\"$$\"" : "\"\""));
					}
					else if (searchCriteria.equals("min"))
					{
						if (customSearchConstraint != null)
							file.print(CodeFragmentsGenerator.getCodeFragment(customSearchConstraint, null, null, null, null));
						else
							file.printf("\t\tif (searchFields.get(\"%sMinimum\") != null && searchFields.get(\"%sMinimum\").toString().length() > 0)\n" +
								"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" %s \" + searchFields.get(\"%sMinimum\"), true);\n", 
								attrName, attrName, attrName,
								searchCriteriaIncludeMin.equals("true") ? ">=" : ">",
								attrName);
					}
					else if (searchCriteria.equals("max"))
					{
						if (customSearchConstraint != null)
							file.print(CodeFragmentsGenerator.getCodeFragment(customSearchConstraint, null, null, null, null));
						else
							file.printf("\t\tif (searchFields.get(\"%sMaximum\") != null && searchFields.get(\"%sMaximum\").toString().length() > 0)\n" +
								"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" %s \" + searchFields.get(\"%sMaximum\"), true);\n", 
								attrName, attrName, attrName,
								searchCriteriaIncludeMax.equals("true") ? "<=" : "<",
								attrName);
					}
					else if (searchCriteria.equals("minmax"))
					{
						if (customSearchConstraint != null)
							file.print(CodeFragmentsGenerator.getCodeFragment(customSearchConstraint, null, null, null, null));
						else
						{
							file.printf("\t\tif (searchFields.get(\"%sMinimum\") != null && searchFields.get(\"%sMinimum\").toString().length() > 0)\n" +
								"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" %s \" + searchFields.get(\"%sMinimum\"), true);\n", 
								attrName, attrName, attrName,
								searchCriteriaIncludeMin.equals("true") ? ">=" : ">",
								attrName);
							file.printf("\t\tif (searchFields.get(\"%sMaximum\") != null && searchFields.get(\"%sMaximum\").toString().length() > 0)\n" +
								"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" %s \" + searchFields.get(\"%sMaximum\"), true);\n", 
								attrName, attrName, attrName,
								searchCriteriaIncludeMax.equals("true") ? "<=" : "<",
								attrName);
						}
					}
					else if (searchCriteria.equals("boolean"))
					{
						// these meanings can be: "ignore" or an expression
						String trueMeaning = (String) attrProps.get("searchCriteriaTrueMeaning");
						String falseMeaning = (String) attrProps.get("searchCriteriaFalseMeaning");
						if (customSearchConstraint != null)
							file.print(CodeFragmentsGenerator.getCodeFragment(customSearchConstraint, null, null, null, null));
						else
						{
							if (!falseMeaning.equals("ignore"))
							{
								file.printf("\t\tif (searchFields.get(\"%s\") != null && searchFields.get(\"%s\").toString().length() > 0 &&\n" +
									"\t\t\tsearchFields.get(\"%s\").equals(false))\n" +
									"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" %s\", true);\n",
									attrName, attrName, attrName, 
									attrName, falseMeaning);
							}
							if (!trueMeaning.equals("ignore"))
							{
								file.printf("\t\tif (searchFields.get(\"%s\") != null && searchFields.get(\"%s\").toString().length() > 0 &&\n" +
									"\t\t\tsearchFields.get(\"%s\").equals(true))\n" +
									"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" %s\", true);\n",
									attrName, attrName, attrName, 
									attrName, trueMeaning);
							}
						}
					}
					else if (searchCriteria.equals("substring"))
					{
						if (customSearchConstraint != null)
							file.print(CodeFragmentsGenerator.getCodeFragment(customSearchConstraint, null, null, null, null));
						else
							file.printf("\t\tif (searchFields.get(\"%s\") != null && searchFields.get(\"%s\").toString().length() > 0)\n" +
								"\t\t\tselectBuilder.addWhere(entityTableName + \".\\\"%s\\\" LIKE $$%%\" + searchFields.get(\"%s\") + \"%%$$\", true);\n",
								attrName, attrName, attrName, 
								attrName);
					}
				}
				//attrProps.get("type"), attrName);
			}
			file.println("\t}\n");
		}
		
		// Instance attributes
		file.printf("\t// ----- Instance Members -----\n");
		for (String attrName : attributes.keySet())
		{
			LinkedHashMap<String, Object> attrProps = attributes.get(attrName);
			String attributeAnnotations = "";

			if ( !(Boolean) attrProps.get("useInAttributes") )
				continue;

			if (attrProps.containsKey("comment"))
				file.printf("%s\n", attrProps.get("comment"));
			if (attrProps.containsKey("annotationBasic"))
				attributeAnnotations += ("@Basic @Column(name=\"\\\"" + attrName + "\\\"\")");
			file.printf("\t%s private %s %s = %s;\n", attributeAnnotations,
				attrProps.get("type"), attrName,
				(attrProps.get("defaultValue") != null ? attrProps.get("defaultValue") : "null"));
		}
		
		file.printf(
			"\n\tpublic %s() {}\n" +
			"\tpublic %s(Map<String, Object> fields)\n\t\tthrows CoreServerException\n\t{\n" +
			"\t\tsetAllFields(fields, true);\n" +
			"\t}\n\n", className, className );

		file.printf(
			"\tpublic String getEntityTableName() { return entityTableName; }\n\n"
			);
		file.printf(
			"\tpublic String getEntityResourceFile() { return entityResourceFile; }\n\n"
			);
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
					"\t\tmodify();\n\t\t%s = inputParameter;\n\t}\n\n",
				Utilities.firstLetterToUpperCase(attrName), attrType, attrName);			
		}

		// getMessageResources()
		file.printf("\tprotected void getMessageResources(Hashtable<String, Object> fields)\n\t{\n");
		/*protected void getMessageResources(Hashtable<String, Object> fields)
		{
			String name;
			
			name = (String) PropertyOption.cacheLookup(apartmentAreaUnitTypeIdk).get("name");
			fields.put("_mr_apartmentareaunittypeidk", name);
		}*/
		for (String attrName : attributes.keySet())
		{
			LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

			if ( !(Boolean) attrProps.get("useInAttributes") )
				continue;

			String lookupMechanism = (String) attrProps.get("messageResourceLookupMechanism");
			if (lookupMechanism == null)
				continue;
			
			if (lookupMechanism.equals("cacheLookup"))
			{
				String source = (String) attrProps.get("messageResourceSource");
				String key = (String) attrProps.get("messageResourceKey");

				file.printf(
					"\t\tfields.put(\"%sMsg\",\n" +
					"\t\t\t(String) %s.cacheLookup(%s).get(\"%s\"));\n",
					attrName, source, attrName, key);
			}
		}

		file.print("\t}\n\n");

		// getExtraFields()
		file.printf("\tprotected void getExtraFields(short format, DateFormat dateFormat,\n" +
			"\t\tHashtable<String, Object> fields)\n\t{\n");
		for (String attrName : attributes.keySet())
		{
			LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

			if ( !(Boolean) attrProps.get("useInAttributes") )
				continue;

			file.printf(
				"\t\tcollectField(format, dateFormat, fields, \"%s\", %s);\n",
				attrName, attrName);

		}
		file.print("\t}\n\n");

		// loadExtraFields
		file.print("\tprotected void loadExtraFields(Map<String, Object> fields)\n" +
			"\t{\n\t\tmodify();\n\n" +
			"\t\tif (fields == null || fields.isEmpty())\n\t\t\treturn;\n\n"
			);
		for (String attrName : attributes.keySet())
		{
			LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

			if ( !(Boolean) attrProps.get("useInAttributes") )
				continue;

			String attrType = (String) attrProps.get("type");
			String castType = firstLetterToUpperCase(attrType);
			if (castType.equals("Int")) castType = "Integer";
			file.print("\t\tif (fields.containsKey(\"" + attrName + "\"))\n" +
				"\t\t\tset" + firstLetterToUpperCase(attrName) + "( (" + 
				(castType.equals("Short") || castType.equals("Byte") ? "(Number" : castType) + // *Most* JDBC drivers return an Integer for Short and Byte 
				") fields.get(\"" + attrName + "\")" +
				(castType.equals("Short") || castType.equals("Byte") ? ")." + castType.toLowerCase() + "Value()" : "") +
				" );\n");
		}
		file.print("\t\treturn;\n\t}\n\n");
		
		// validateExtraFields
		file.print(
			"\tpublic Map<String, List<MsgArgsPair>> " +
			"validateExtraFields(List<String> specificFields, boolean exclude)\n" +
			"\t{\n\t\tMap<String, List<MsgArgsPair>> result = null;\n" +
			"\t\tLinkedHashMap<String, List<MsgArgsPair>> results = " +
			"new LinkedHashMap<String, List<MsgArgsPair>>();\n" +
			"\t\tFieldValidator validator = new FieldValidator();\n\n" + 
			"\t\tif (getValidating())\n\t\t{\n");
		for (String attrName : attributes.keySet())
		{
			LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

			if ( !(Boolean) attrProps.get("useInAttributes") )
				continue;

			if (attrProps.containsKey("validation"))
			{
				file.print("\t\t\tif ((specificFields == null) ||\n" +
					"\t\t\t\t(specificFields != null && !exclude && specificFields.contains(\"" + attrName + "\")) ||\n" +
					"\t\t\t\t(specificFields != null && exclude && !specificFields.contains(\"" + attrName + "\")))\n" +
					"\t\t\t{\n");
				if (cacheSearchParameters.containsKey(attrName))
				{
					file.print("\t\t\t\tHashtable<String, Object> searchParameters = new Hashtable<String, Object>();\n");
					LinkedHashMap<String, String> nameValues = cacheSearchParameters.get(attrName);
					for (String name : nameValues.keySet())
					{
						file.print("\t\t\t\tsearchParameters.put(\"" + name + 
							"\", \"" + nameValues.get(name) + "\");\n");
					}
				}
				// The code looks nicer this way, but ends up creating and destroying a FieldValidator per
				// field, which is very bad memory-wise.
				/*file.print(
					"\t\t\t\tresult = validate" + firstLetterToUpperCase(attrName) + 
					"(" + attrName + ");\n" + 
					"\t\t\t\tif (result != null)\n\t\t\t\t\tresults.putAll(result);\n" + 
					"\t\t\t}\n\n");*/
				
				file.print(
					"\t\t\t\tresult = \n\t\t\t\t\t" + 
					attrProps.get("validation") + ";\n" + 
					"\t\t\t\tif (result != null)\n\t\t\t\t\tresults.putAll(result);\n" + 
					"\t\t\t}\n\n");
			}
		}		
		file.print(
			"\t\t}\n\n\t\tif (results.isEmpty())\n\t\t\treturn null;\n" +
			"\t\treturn results;\n\t}\n\n");

		// onModify
		file.print("\tprotected void onModify()\n\t{\n");
		codeFragments.insertCodeFragments(file, "entity", CodeFragmentsGenerator.CIP_ONMODIFY);
		file.print("\t}\n\n");

		codeFragments.insertCodeFragments(file, "entity", CodeFragmentsGenerator.CIP_METHOD);

		// End of class
		file.println("}\n");
		
		file.close();
	}
}
