package aaacs.coreserver.generation.ejb.beans.method;

import static aaacs.coreserver.commons.generation.Utilities.getNodeAttribute;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.CodeFragmentReference;
import aaacs.coreserver.commons.generation.CodeFragmentsGenerator;
import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;

public class UpdateDeleteOperationGenerator extends Generator
{
	private OperationErrorChecksGenerator oecGenerator = null;
	private List<CodeFragmentReference> dsCodeReferences = null; // data source custom code
	private String operation = null;
	private int taskCounter;
	private String methodDisplayName;
	public final boolean isUpdate;

	public UpdateDeleteOperationGenerator(Node operationNode, int taskCounter,
		String methodDisplayName)
		throws GenerationException
	{
		this.taskCounter = taskCounter;
		this.methodDisplayName = methodDisplayName;
		if (operationNode.getNodeName().equals("update"))
			this.isUpdate = true;
		else
			this.isUpdate = false;
		extract(operationNode);
	}
	
	private void extractDataSource(Node cmNode)
		throws GenerationException
	{
		Node mechanismNode = cmNode.getFirstChild();
		String mechanismType = mechanismNode.getNodeName();
		StringBuilder sbo = new StringBuilder("");
		String aec = "affectedEntitiesCounter" + taskCounter;

		if (mechanismType.equals("bulkFromFieldSet"))
		{
			String fieldsName = getNodeAttribute(mechanismNode, "fieldsName");
			String entityType = getNodeAttribute(mechanismNode, "entityType");
			String searchFields = mechanismNode.getTextContent().trim();

			if (isUpdate)
				sbo.append("\t\t\tInteger " + aec + " = " + entityType + 
					".updateByFields(manager, " + fieldsName + ", " + searchFields + ");\n");
			else
				sbo.append("\t\t\tInteger " + aec + " = " + entityType + 
					".removeByFields(manager, " + searchFields + ");\n");

			String synchronize = getNodeAttribute(mechanismNode, "synchronize");
			if (synchronize.equals("true"))
				sbo.append("\t\t\tmanager.flush();\n");
		}
		else if (mechanismType.equals("bulkFromCondition"))
		{
			String fieldsName = getNodeAttribute(mechanismNode, "fieldsName");
			String entityType = getNodeAttribute(mechanismNode, "entityType");
			String condition = mechanismNode.getTextContent().trim();

			if (isUpdate)
				sbo.append("\t\t\tInteger " + aec + " = " + entityType + 
					".updateByCondition(manager, " + fieldsName + ", " + 
					condition + ", null);\n");
			else
				sbo.append("\t\t\tInteger " + aec + " = " + entityType + 
					".removeByCondition(manager, " + condition + ", null);\n");
			String synchronize = getNodeAttribute(mechanismNode, "synchronize");
			if (synchronize.equals("true"))
				sbo.append("\t\t\tmanager.flush();\n");
		}
		else if (mechanismType.equals("singleFieldSet"))
		{
			String fieldsName = getNodeAttribute(mechanismNode, "fieldsName");
			String entityType = getNodeAttribute(mechanismNode, "entityType");
			String entityName = getNodeAttribute(mechanismNode, "entityName");
			if (entityName == null)
				entityName = Utilities.firstLetterToLowerCase(entityType) + taskCounter;
			
			sbo.append("\t\t\t" + entityType + " " + 
				entityName + " = " +
				"new " + entityType + "();\n" +
				"\t\t\t" + entityName + 
				".setAllFields(" + fieldsName + ", true);\n" +
				"\t\t\t" + entityName + " = manager.merge(" + entityName + ");\n" +
				(isUpdate ? "" : "\t\t\tmanager.remove(" + entityName + ");\n") +
				"\t\t\tmanager.flush();\n" +
				"\t\t\tInteger " + aec + " = 1;\n"
				);
		}
		else if (mechanismType.equals("multipleFieldSet"))
		{
			String fieldsName = getNodeAttribute(mechanismNode, "fieldsName");
			String entityType = getNodeAttribute(mechanismNode, "entityType");
			String entityName = Utilities.firstLetterToLowerCase(entityType) + taskCounter;
			String entityListName = getNodeAttribute(mechanismNode, "entityListName");
			if (entityListName == null)
				entityListName = Utilities.firstLetterToLowerCase(entityType) + "List" + taskCounter;
			
			sbo.append(
				"\t\t\tList<" + entityType + "> " + entityListName + " = new Vector<User>();\n" +
				"\t\t\tInteger " + aec + " = 0;\n" + 
				"\t\t\tfor (Map singleFieldSet : " + fieldsName + ")\n\t\t\t{\n" +
				"\t\t\t\t" + entityType + " " + 
				entityName + " = " +
				"new " + entityType + "();\n" +
				"\t\t\t\t" + entityName +
				".setAllFields(singleFieldSet, true);\n" +
				"\t\t\t\t" + entityName + " = manager.merge(" + entityName + ");\n" +
				(isUpdate ? 
					"\t\t\t\t" + entityListName + ".add(" + entityName + ");\n" : 
					"\t\t\t\tmanager.remove(" + entityName + ");\n") +
				"\t\t\t\tmanager.flush();\n" + 
				"\t\t\t\t" + aec + "++;\n" +
				"\t\t\t}\n"
				);
		}
		else if (mechanismType.equals("singleEntity"))
		{
			String entityName = getNodeAttribute(mechanismNode, "entityName");
			
			sbo.append(
				"\t\t\t" + entityName + " = manager.merge(" + entityName + ");\n" +
				(isUpdate ? "" : "\t\t\tmanager.remove(" + entityName + ");\n") +
				"\t\t\tmanager.flush();\n" + 
				"\t\t\tInteger " + aec + " = 1;\n"
				);
		}
		else if (mechanismType.equals("multipleEntities"))
		{
			String entityListName = getNodeAttribute(mechanismNode, "entityListName");
			
			sbo.append(
				"\t\t\tInteger " + aec + " = 0;\n" + 
				"\t\t\tfor (Object entity : " + entityListName + ")\n\t\t\t{\n" +
				"\t\t\t\tentity = manager.merge(entity);\n" +
				(isUpdate ? "" : "\t\t\t\tmanager.remove(entity);\n") +
				"\t\t\t\tmanager.flush();\n" +
				"\t\t\t\t" + aec + "++;\n" +
				"\t\t\t}\n"
				);
		}
		else if (mechanismType.equals("setEntityProperty")) // not a data source but useful/common functionality
		{
			String name = getNodeAttribute(mechanismNode, "name");
			String property = getNodeAttribute(mechanismNode, "property");
			String value = getNodeAttribute(mechanismNode, "value");
			String condition = getNodeAttribute(mechanismNode, "condition");
			
			sbo.append(
				(condition != null ? "\t\t\tif (" + condition + ")\n\t\t\t{\n" : "") +
				"\t\t\t" + (condition != null ? "\t" : "") + name + ".set" + Utilities.firstLetterToUpperCase(property) +
				"(" + value + ");\n");
			sbo.append((condition != null ? "\t\t\t}\n" : ""));
		}
		else if (mechanismType.equals("custom"))
		{
			NodeList codeFragmentNodes = mechanismNode.getChildNodes();
			dsCodeReferences = new Vector<CodeFragmentReference>();
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
				dsCodeReferences.add(cfr);
				sbo.append(CodeFragmentsGenerator.getCodeFragment(
					cfr.reference, cfr.baseIndent, cfr.keepExtraIndents, 
					cfr.startComment, cfr.endComment));
			}
		}
		operation = sbo.toString();
	}
	
	private void extract(Node operationNode)
		throws GenerationException
	{
		NodeList children = operationNode.getChildNodes();
		for (int i=0; i<children.getLength(); i++)
		{
			Node child = children.item(i);
			String nodeName = child.getNodeName();

			if (nodeName.equals("dataSource"))
				extractDataSource(child);
			else if (nodeName.equals("errorChecks"))
				oecGenerator = 
					new OperationErrorChecksGenerator(child, taskCounter, methodDisplayName);
		}
	}
	
	public void write(PrintWriter file)
		throws GenerationException
	{
		if (operation != null)
		{
			file.print(operation);
		}
		if (oecGenerator != null)
			oecGenerator.write(file, "affectedEntitiesCounter" + taskCounter, 
				"affectedEntitiesCounter" + taskCounter);
	}
}
