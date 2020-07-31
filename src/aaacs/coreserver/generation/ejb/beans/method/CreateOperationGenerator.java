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

public class CreateOperationGenerator extends Generator
{
	private OperationErrorChecksGenerator oecGenerator = null;
	private List<CodeFragmentReference> dsCodeReferences = null; // data source custom code
	private String operation = null;
	private int taskCounter;
	private String methodDisplayName;
	private String loopTo = null;
	private String loopCtr = null;

	public CreateOperationGenerator(Node operationNode, int taskCounter,
		String methodDisplayName)
		throws GenerationException
	{
		this.taskCounter = taskCounter;
		this.methodDisplayName = methodDisplayName;
		extract(operationNode);
	}
	
	private void extractDataSource(Node cmNode)
		throws GenerationException
	{
		StringBuilder sbo = new StringBuilder("");
		String tabs = null;
		
		if (loopTo != null)
			sbo.append("\t\t\tfor (int " + loopCtr + " = 0; " + loopCtr + " < " + loopTo + "; " + loopCtr + "++)\n\t\t\t{\n");
		
		NodeList mechanismNodes = cmNode.getChildNodes();
		for (int k=0; k<mechanismNodes.getLength(); k++)
		{
			Node mechanismNode = mechanismNodes.item(k);
			String mechanismType = mechanismNode.getNodeName();

			if (loopTo == null)
				tabs = "\t\t\t";
			else
				tabs = "\t\t\t\t";

			if (mechanismType.equals("singleFieldSet"))
			{
				String fieldsName = getNodeAttribute(mechanismNode, "fieldsName");
				String entityType = getNodeAttribute(mechanismNode, "entityType");
				String entityName = getNodeAttribute(mechanismNode, "entityName");
				String listName = entityName.replaceAll("[.]get.*", "");
				String updateIfPossible = getNodeAttribute(mechanismNode, "updateIfPossible");
				String condition = getNodeAttribute(mechanismNode, "condition");
				if (entityName == null)
					entityName = Utilities.firstLetterToLowerCase(entityType) + taskCounter;
				
				String ct = (condition != null ? "\t" : ""); // condition tab
				
				sbo.append(
					(condition != null ? tabs + "if (" + condition + ")\n" + tabs + "{\n" : "") +
					tabs + ct + entityType + " " + 
					entityName + " = " +
					"new " + entityType + "();\n" +
					tabs + ct + entityName + 
					".setAllFields(" + fieldsName + ", true);\n" +
					(updateIfPossible.equals("true") ? 
						tabs + ct + "if (" + entityName + ".getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)\n" +
						(loopTo == null ? tabs + ct + "\t" + entityName + " = manager.merge(" + entityName + ");\n" 
							: tabs + ct + "\t" + listName + ".set(" + loopCtr + ",  manager.merge(" + entityName + "));\n" ) +
						tabs + ct + "else\n" +
						tabs + ct + "\tmanager.persist(" + entityName + ");\n"
						: 
						tabs + ct + "manager.persist(" + entityName + ");\n")
					);
				String synchronize = getNodeAttribute(mechanismNode, "synchronize");
				if (synchronize.equals("true"))
					sbo.append(tabs + ct + "manager.flush();\n");
				sbo.append((condition != null ? tabs + "}\n" : ""));
			}
			else if (mechanismType.equals("multipleFieldSet"))
			{
				String fieldsName = getNodeAttribute(mechanismNode, "fieldsName");
				String entityType = getNodeAttribute(mechanismNode, "entityType");
				String entityName = Utilities.firstLetterToLowerCase(entityType) + taskCounter;
				String entityListName = getNodeAttribute(mechanismNode, "entityListName");
				String updateIfPossible = getNodeAttribute(mechanismNode, "updateIfPossible");
				String condition = getNodeAttribute(mechanismNode, "condition");
				if (entityListName == null)
					entityListName = Utilities.firstLetterToLowerCase(entityType) + "List" + taskCounter;
				
				String ct = (condition != null ? "\t" : ""); // condition tab

				sbo.append(
					(condition != null ? tabs + "if (" + condition + ")\n" + tabs + "{\n" : "") +
					tabs + ct + "List<" + entityType + "> " + entityListName + " = new Vector<User>();\n" +
					tabs + ct + "for (Map singleFieldSet : " + fieldsName + ")\n" + tabs + "{\n" +
					tabs + ct + "\t" + entityType + " " + 
					entityName + " = " +
					"new " + entityType + "();\n" +
					tabs + ct + "\t" + entityName +
					".setAllFields(singleFieldSet, true);\n" +
					(updateIfPossible.equals("true") ? 
						tabs + ct + "\tif (" + entityName + ".getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)\n" +
						tabs + ct + "\t\t" + entityName + " = manager.merge(" + entityName + ");\n" +
						tabs + ct + "\telse\n" +
						tabs + ct + "\t\tmanager.persist(" + entityName + ");\n"
						: 
						tabs + ct + "\tmanager.persist(" + entityName + ");\n") +
					tabs + ct + "\t" + entityListName + ".add(" + entityName + ");\n" +
					tabs + ct + "}\n"
					);
				String synchronize = getNodeAttribute(mechanismNode, "synchronize");
				if (synchronize.equals("true"))
					sbo.append(tabs + ct + "manager.flush();\n");
				sbo.append((condition != null ? tabs + "}\n" : ""));
			}
			else if (mechanismType.equals("singleEntity"))
			{
				String entityName = getNodeAttribute(mechanismNode, "entityName");
				String listName = entityName.replaceAll("[.]get.*", "");
				String updateIfPossible = getNodeAttribute(mechanismNode, "updateIfPossible");
				String condition = getNodeAttribute(mechanismNode, "condition");

				String ct = (condition != null ? "\t" : ""); // condition tab
				
				
				sbo.append(
					(condition != null ? tabs + "if (" + condition + ")\n" + tabs + "{\n" : "") +
					(updateIfPossible.equals("true") ? 
						tabs + ct + "if (" + entityName + ".getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)\n" +
						(loopTo == null ? tabs + ct + "\t" + entityName + " = manager.merge(" + entityName + ");\n" 
							: tabs + ct + "\t" + listName + ".set(" + loopCtr + ",  manager.merge(" + entityName + "));\n" ) +
						tabs + ct + "else\n" +
						tabs + ct + "\tmanager.persist(" + entityName + ");\n"
						: 
						tabs + ct + "manager.persist(" + entityName + ");\n")
					);
				String synchronize = getNodeAttribute(mechanismNode, "synchronize");
				if (synchronize.equals("true"))
					sbo.append(tabs + ct + "manager.flush();\n");
				sbo.append((condition != null ? tabs + "}\n" : ""));
			}
			else if (mechanismType.equals("multipleEntities"))
			{
				String entityListName = getNodeAttribute(mechanismNode, "entityListName");
				String updateIfPossible = getNodeAttribute(mechanismNode, "updateIfPossible");
				String condition = getNodeAttribute(mechanismNode, "condition");

				String ct = (condition != null ? "\t" : ""); // condition tab

				sbo.append(
					(condition != null ? tabs + "if (" + condition + ")\n" + tabs + "{\n" : "") +
					tabs + ct + "for (BaseEntity entity : " + entityListName + ")\n" + tabs + "{\n" +
					(updateIfPossible.equals("true") ? 
						tabs + ct + "\tif (entity.getIdk() > PrimaryKeyHolder.KEY_HIGHEST_RESERVED)\n" +
						tabs + ct + "\t\tentity = manager.merge(entity);\n" +
						tabs + ct + "\telse\n" +
						tabs + ct + "\t\tmanager.persist(entity);\n"
						: 
						tabs + ct + "\tmanager.persist(entity);\n") +
					tabs + ct + "}\n"
					);
				String synchronize = getNodeAttribute(mechanismNode, "synchronize");
				if (synchronize.equals("true"))
					sbo.append(tabs + ct + "manager.flush();\n");
				sbo.append((condition != null ? tabs + "}\n" : ""));
			}
			else if (mechanismType.equals("setEntityProperty")) // not a data source but useful/common functionality
			{
				String name = getNodeAttribute(mechanismNode, "name");
				String property = getNodeAttribute(mechanismNode, "property");
				String value = getNodeAttribute(mechanismNode, "value");
				String condition = getNodeAttribute(mechanismNode, "condition");
				String cast = getNodeAttribute(mechanismNode, "cast");
				
				String ct = (condition != null ? "\t" : ""); // condition tab

				sbo.append(
					(condition != null ? tabs + "if (" + condition + ")\n" + tabs + "{\n" : "") +
					tabs + ct + 
						(cast != null ? "((" + cast + ") " + name + ")" : name) + 
					".set" + Utilities.firstLetterToUpperCase(property) +
					"(" + value + ");\n");
				sbo.append((condition != null ? tabs + "}\n" : ""));
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
		}

		if (loopTo != null)
			sbo.append("\t\t\t}\n");
		
		operation = sbo.toString();
	}

	private void extract(Node operationNode)
		throws GenerationException
	{
		loopTo = getNodeAttribute(operationNode, "loopTo");
		loopCtr = getNodeAttribute(operationNode, "loopCtr");

		NodeList children = operationNode.getChildNodes();
		for (int i=0; i<children.getLength(); i++)
		{
			Node child = children.item(i);
			String nodeName = child.getNodeName();

			if (nodeName.equals("dataSource"))
				extractDataSource(child);
			else if (nodeName.equals("errorChecks")) // note that only custom checks are applicable
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
			oecGenerator.write(file, "", "");
	}
}
