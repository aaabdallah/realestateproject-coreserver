package aaacs.coreserver.generation.ejb.beans.method;

import static aaacs.coreserver.commons.generation.Utilities.getNodeAttribute;
import static aaacs.coreserver.commons.generation.Utilities.goToNode;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.CodeFragmentReference;
import aaacs.coreserver.commons.generation.CodeFragmentsGenerator;
import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;

public class ParameterGenerator extends Generator
{
	private List<CodeFragmentReference> cupaCodeReferences = null;
	private Map<String, List<CodeFragmentReference>> cvCodeReferences = null;
	private boolean isInput = true;
	private int taskCounter = 0;
	private int parameterCounter = 0;
	public String displayName = null;
	public String enResource = null;
	public String type = null;
	private String varName = null;
	public String userVarName = null;
	private String methodSuffix = null;
	private String keyClasses = null;
	private String valueClasses = null;
	private List<String> validations = null;
	private String condition = null;
	private String methodDisplayName = null;
	private String entityType = null;
	private String entityVarName = null;
	private String userEntityVarName = null;
	private boolean complexType = false; // i.e. requiring the "checkArray/List/Map" methods
	
	public ParameterGenerator(Node parameterNode, boolean input, 
		int taskCounter, int parameterCounter, String methodDisplayName)
	{
		isInput = input;
		this.taskCounter = taskCounter;
		this.parameterCounter = parameterCounter;
		this.methodDisplayName = methodDisplayName;
		varName = "task" + taskCounter + "param" + parameterCounter;
		userVarName = varName;
		entityVarName = varName + entityType;
		userEntityVarName = entityVarName;
		if (!parameterNode.getNodeName().equals("customParameter"))
			extract(parameterNode);
		else
		{
			// custom parameter; get code references
			NodeList codeFragmentNodes = parameterNode.getChildNodes();
			cupaCodeReferences = new Vector<CodeFragmentReference>();
			for (int i=0; i<codeFragmentNodes.getLength(); i++)
			{
				Node cfrNode = codeFragmentNodes.item(i);
				CodeFragmentReference cfr = 
					new CodeFragmentReference(
						cfrNode.getTextContent(),
						getNodeAttribute(cfrNode, "baseIndent"),
						getNodeAttribute(cfrNode, "keepExtraIndents"),
						getNodeAttribute(cfrNode, "startComment"),
						getNodeAttribute(cfrNode, "endComment"));
				cupaCodeReferences.add(cfr);
			}
		}
	}
	
	private void extractValidations(Node validationsNode)
	{
		NodeList validationNodes = validationsNode.getChildNodes();
		validations = new Vector<String>();
		cvCodeReferences = new HashMap<String, List<CodeFragmentReference>>();
		String v = null;
		String tabs = null;

		for (int i=0; i<validationNodes.getLength(); i++)
		{
			Node validationNode = validationNodes.item(i);
			String nodeName = validationNode.getNodeName();

			tabs = "\t\t\t";
			if (nodeName.equals("staticEntityListValidator"))
			{
				// node text content is irrelevant
				boolean checkIfUserSuppliedIdk = Boolean.parseBoolean(getNodeAttribute(validationNode, "checkIfUserSuppliedIdk"));
				String validationCondition = getNodeAttribute(validationNode, "condition");
				String oneEntityVar = Utilities.firstLetterToLowerCase(entityType);
				
				if (validationCondition != null)
					tabs += "\t";

				v = (validationCondition != null ? "\t\t\tif (" + validationCondition + ")\n\t\t\t{\n" : "") + 
					tabs + "for (" + entityType + " " + oneEntityVar + " : " + userEntityVarName + ")\n" + tabs + "{\n" +
					tabs + "\tvldResults = " + entityType + ".validate" + entityType + "(" + 
					oneEntityVar + ", " + checkIfUserSuppliedIdk + ", null, false);\n" +
					tabs + "\tif (vldResults != null)\n" + tabs + "\t{\n" +
					tabs + "\t\tint cntr = 0;\n" + 
					tabs + "\t\tMap<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();\n" +
					tabs + "\t\tfor (String field : vldResults.keySet())\n" +
					tabs + "\t\t\ttempo.put(\"" + displayName + "\" + \"[\" + cntr++ + \"]\" + \"(\" + field + \")\", " +
					"vldResults.get(field));\n" +
					tabs + "\t\tvldResults = tempo;\n" +
					tabs + "\t\terrors.add(new ErrorReport(\"error.System.CoreServer\", ErrorReport.Source.PARAMETER,\n" +
					tabs + "\t\t\t\"action.InvalidParameter\", request).\n" +
					tabs + "\t\t\taddItem(vldResults));\n" + tabs + "\t}\n" +  
					tabs + "}\n" +
					(validationCondition != null ? "\t\t\t}\n" : "");
			}
			else if (nodeName.equals("staticEntityValidator"))
			{
				// node text content is irrelevant
				boolean checkIfUserSuppliedIdk = Boolean.parseBoolean(getNodeAttribute(validationNode, "checkIfUserSuppliedIdk"));
				String validationCondition = getNodeAttribute(validationNode, "condition");
				
				if (validationCondition != null)
					tabs += "\t";

				v = (validationCondition != null ? "\t\t\tif (" + validationCondition + ")\n\t\t\t{\n" : "") +
					tabs + "vldResults = " + entityType + ".validate" + entityType + "(" + 
					userEntityVarName + ", " + checkIfUserSuppliedIdk + ", null, false);\n" +
					tabs + "if (vldResults != null)\n" + tabs + "{\n" +
					tabs + "\tMap<String, List<MsgArgsPair>> tempo = new LinkedHashMap<String, List<MsgArgsPair>>();\n" +
					tabs + "\tfor (String field : vldResults.keySet())\n" +
					tabs + "\t\ttempo.put(\"" + displayName + "\" + \"(\" + field + \")\", " +
					"vldResults.get(field));\n" +
					tabs + "\tvldResults = tempo;\n" +
					tabs + "\terrors.add(new ErrorReport(\"error.System.CoreServer\", ErrorReport.Source.PARAMETER,\n" +
					tabs + "\t\t\"action.InvalidParameter\", request).\n" +
					tabs + "\t\taddItem(vldResults));\n" +  
					tabs + "}\n" +
					(validationCondition != null ? "\t\t\t}\n" : "");
			}
			else if (nodeName.equals("staticFieldValidator"))
			{
				String validationCondition = getNodeAttribute(validationNode, "condition");

				if (validationCondition != null)
					tabs += "\t";

				v = validationNode.getTextContent().trim(); // e.g: User.validateUsername
				String fieldName = v.replaceAll("^.*\\.validate(.*)", "$1");
				v = (validationCondition != null ? "\t\t\tif (" + validationCondition + ")\n\t\t\t{\n" : "") +
					tabs + "vldResults = " + v + "(" + userVarName + ");\n" +
					tabs + "if (vldResults != null)\n" +
					tabs + "\terrors.add(new ErrorReport(\"error.System.CoreServer\", ErrorReport.Source.PARAMETER,\n" +
					tabs + "\t\t\"action.InvalidParameter\", request).\n" +
					tabs + "\t\taddItem(\"" + displayName + 
					"\", vldResults.get(\"" + fieldName + "\")));\n" +
					(validationCondition != null ? "\t\t\t}\n" : "");
			}
			else if (nodeName.equals("parameterValidator"))
			{
				String validationCondition = getNodeAttribute(validationNode, "condition");

				if (validationCondition != null)
					tabs += "\t";

				v = validationNode.getTextContent().trim(); // e.g. setValidMinimum((double) 3).setValidMaximum((double) 400)
				String keyVals = "";
				if (complexType)
				{
					String erasedType = type.replaceAll("^([^<]*)<.*$", "$1");
					String keyValSuffix = "In" + taskCounter + parameterCounter;
					keyVals = tabs + "Class<?>[] keyClasses" + keyValSuffix + " = " +
						"{" + keyClasses + "};\n" +
						tabs + "Class<?>[] valueClasses" + keyValSuffix + " = " +
						"{" + valueClasses + "};\n";

					v = (validationCondition != null ? "\t\t\tif (" + validationCondition + ")\n\t\t\t{\n" : "") +
						keyVals + tabs + "vldResults = inputParameters.clearChecks().setName(\"" + displayName + "\").\n" + 
						(!v.equals("") ? tabs + "\t" + v + ".\n" : "") +
						tabs + "\tcheck" + erasedType + "(" + userVarName + 
							", keyClasses" + keyValSuffix + ", valueClasses" + keyValSuffix + 
							");\n" + 
						tabs + "if (vldResults != null)\n" +
						tabs + "\terrors.add(new ErrorReport(\"error.System.CoreServer\", ErrorReport.Source.PARAMETER,\n" +
						tabs + "\t\t\"action.InvalidParameter\", request).\n" +
						tabs + "\t\taddItem(vldResults));\n" +
						(validationCondition != null ? "\t\t\t}\n" : "");
				}
				else
				{
					v = (validationCondition != null ? "\t\t\tif (" + validationCondition + ")\n\t\t\t{\n" : "") +
						tabs + "vldResults = inputParameters.clearChecks().setName(\"" + displayName + "\").\n" + 
						(!v.equals("") ? tabs + "\t" + v + ".\n" : "") +
						tabs + "\tcheck" + methodSuffix + "(" + userVarName + ");\n" + 
						tabs + "if (vldResults != null)\n" +
						tabs + "\terrors.add(new ErrorReport(\"error.System.CoreServer\", ErrorReport.Source.PARAMETER,\n" +
						tabs + "\t\t\"action.InvalidParameter\", request).\n" +
						tabs + "\t\taddItem(vldResults));\n" +
						(validationCondition != null ? "\t\t\t}\n" : "");
				}
			}
			else if (nodeName.equals("customValidator"))
			{
				NodeList codeFragmentNodes = validationNode.getChildNodes();
				List<CodeFragmentReference> codeReferences = new Vector<CodeFragmentReference>();
				for (int j=0; j<codeFragmentNodes.getLength(); j++)
				{
					Node cfrNode = codeFragmentNodes.item(j);
					CodeFragmentReference cfr = 
						new CodeFragmentReference(
							cfrNode.getTextContent(),
							getNodeAttribute(cfrNode, "baseIndent"),
							getNodeAttribute(cfrNode, "keepExtraIndents"),
							getNodeAttribute(cfrNode, "startComment"),
							getNodeAttribute(cfrNode, "endComment"));
					codeReferences.add(cfr);
				}
				v = "__CUSTOM__:" + i;
				cvCodeReferences.put(v, codeReferences);
			}
			validations.add(v);
		}
	}

	private void extract(Node parameterNode)
	{
		NamedNodeMap attributes = parameterNode.getAttributes();
		for (int i=0; i<attributes.getLength(); i++)
		{
			Node attribute = attributes.item(i);
			String attrName = attribute.getNodeName();
			
			if (attrName.equals("displayName"))
				displayName = methodDisplayName + "." + attribute.getTextContent();
			else if (attrName.equals("enResource"))
				enResource = attribute.getTextContent();
			else if (attrName.equals("type"))
				type = attribute.getTextContent();
			else if (attrName.equals("varName"))
				userVarName = attribute.getTextContent();
			else if (attrName.equals("entityType")) // convert it to this entity
				entityType = attribute.getTextContent();
			else if (attrName.equals("entityVarName")) // with this explicit name
				userEntityVarName = attribute.getTextContent();
			else if (attrName.equals("methodSuffix"))
				methodSuffix = attribute.getTextContent();
			else if (attrName.equals("keyClasses"))
				keyClasses = (attribute.getTextContent().trim().
					replaceAll("\\s+", ".class, ") + ".class").replaceAll("null\\.class", "null");
			else if (attrName.equals("valueClasses"))
				valueClasses = (attribute.getTextContent().trim().
					replaceAll("\\s+", ".class, ") + ".class").replaceAll("null\\.class", "null");
			else if (attrName.equals("condition"))
				condition = attribute.getTextContent();
		}
		if (methodSuffix == null)
			methodSuffix = guessMethodSuffix(type);
		complexType = isComplexType(type);
		
		if (isInput)
		{
			Node validationsNode = goToNode(parameterNode, "validations");
			if (validationsNode != null)
				extractValidations(validationsNode);
		}
	}
	
	private boolean isSupportedBasicType(String type)
	{
		if (type.matches("(Byte)|(Short)|(Integer)|(Long)|(Float)|(Double)|" +
			"(Boolean)|(String)|(Date)|(Timestamp)"))
			return true;
		return false;
	}
	
	private boolean isComplexType(String type)
	{
		if (isSupportedBasicType(type))
			return false;
		else if (type.startsWith("Map"))
			return true;
		else if (type.startsWith("List"))
		{
			String innerObject = type.replaceAll("^List<", "").replaceAll(">$", "");
			if (isSupportedBasicType(innerObject))
				return false;
			else
				return true; // (complicated) object list
		}
		else if (type.endsWith("[]"))
		{
			String arrayType = type.substring(0, type.length()-2);
			if (isSupportedBasicType(arrayType))
				return false;
			else
				return true; // (complicated) object array
		}
		return true; // too difficult 	
	}

	private String guessMethodSuffix(String type)
	{
		if (isSupportedBasicType(type))
			return type;
		else if (type.startsWith("Map"))
			return "Map";
		else if (type.startsWith("List"))
		{
			String innerObject = type.replaceAll("^List<", "").replaceAll(">$", "");
			/*if (isSupportedBasicType(innerObject))
				return innerObject + "List";
			else*/
				return "List"; // (complicated) object list
		}
		else if (type.endsWith("[]"))
		{
			String arrayType = type.substring(0, type.length()-2);
			if (isSupportedBasicType(arrayType))
				return arrayType + "Array";
			else
				return "Array"; // (complicated) object array
		}
		else
			return "UNKNOWNSUFFIX"; // too difficult to guess: user must use "methodSuffix" attribute
	}

	public void write(PrintWriter file)
		throws GenerationException
	{
		if (cupaCodeReferences != null)
		{
			for (CodeFragmentReference cfr : cupaCodeReferences)
			{
				file.print(CodeFragmentsGenerator.getCodeFragment(
					cfr.reference, cfr.baseIndent, cfr.keepExtraIndents, 
					cfr.startComment, cfr.endComment));
			}
		}
		else
		{
			if (isInput)
			{
				file.printf("\t\t\t%s %s =\n\t\t\t\tinputParameters.get%s(" +
					"\"%s\");\n", type, userVarName, methodSuffix, displayName);
				if (entityType != null)
				{
					// currently support either one entity
					if (type.trim().replaceAll("\\s", "").equals("Map<String,Object>"))
						file.printf("\t\t\t%s %s = new %s(%s);\n", 
							entityType, userEntityVarName, entityType, userVarName);
					// or a list of entities
					else
					{
						String oneEntityVar = Utilities.firstLetterToLowerCase(entityType);
						file.printf("\t\t\tList<%s> %s = new Vector<%s>();\n", 
							entityType, userEntityVarName, entityType);
						file.printf("\t\t\tfor (Map<String, Object> oneFieldSet : %s)\n\t\t\t{\n",
							userVarName);
						file.printf("\t\t\t\t%s %s = new %s(oneFieldSet);\n", entityType, 
							oneEntityVar, entityType);
						file.printf("\t\t\t\t%s.add(%s);\n\t\t\t}\n", userEntityVarName, oneEntityVar);
					}
				}
				for (String v : validations)
				{
					if (v.startsWith("__CUSTOM__:"))
					{
						List<CodeFragmentReference> codeReferences = cvCodeReferences.get(v);
						for (CodeFragmentReference cfr : codeReferences)
						{
							file.print(CodeFragmentsGenerator.getCodeFragment(
								cfr.reference, cfr.baseIndent, cfr.keepExtraIndents, 
								cfr.startComment, cfr.endComment));
							file.print("\n");
						}
					}
					else
						file.print(v);
				}
			}
			else
			{
				String tabs = "\t\t\t";
				if (condition != null)
				{
					tabs = "\t\t\t\t";
					file.print("\t\t\tif (" + condition.trim() + ")\n\t\t\t{\n");
				}
				
				if (complexType)
				{
					String erasedType = type.replaceAll("^([^<]*)<.*$", "$1");
					String keyValSuffix = "Out" + taskCounter + parameterCounter;
					file.print(tabs + "Class<?>[] keyClasses" + keyValSuffix + " = " +
					"{" + keyClasses + "};\n" +
					tabs + "Class<?>[] valueClasses" + keyValSuffix + " = " +
					"{" + valueClasses + "};\n");
					file.printf(tabs + "outputParameters.set%s(\"%s\", %s, %s, %s);\n",
						erasedType, displayName, userVarName, 
						"keyClasses" + keyValSuffix,
						"valueClasses" + keyValSuffix);
				}
				else
				{
					file.printf(tabs + "outputParameters.set%s(\"%s\", %s);\n",
						methodSuffix, displayName, userVarName);
				}
				
				if (condition != null)
					file.print("\t\t\t}\n");
			}
		}
	}
}
