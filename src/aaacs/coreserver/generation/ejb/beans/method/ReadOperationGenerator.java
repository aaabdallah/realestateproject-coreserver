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

public class ReadOperationGenerator extends Generator
{
	private OperationErrorChecksGenerator oecGenerator = null;
	private List<CodeFragmentReference> fmCodeReferences = null;
	private String operation = null;
	private String listVarName = null;
	private String entityName = null;
	private int taskCounter;
	private String methodDisplayName;

	public ReadOperationGenerator(Node operationNode, int taskCounter,
		String methodDisplayName)
		throws GenerationException
	{
		this.taskCounter = taskCounter;
		this.listVarName = "list" + taskCounter;
		this.entityName = "var" + taskCounter;
		this.methodDisplayName = methodDisplayName;
		extract(operationNode);
	}
	
	private void extractFindMechanism(Node fmNode)
		throws GenerationException
	{
		Node mechanismNode = fmNode.getFirstChild();
		String mechanismType = mechanismNode.getNodeName();
	
		StringBuilder sbo = new StringBuilder("");
		if (mechanismType.equals("idk"))
		{
			String entityType = getNodeAttribute(mechanismNode, "entityType");
			String idkVarName = getNodeAttribute(mechanismNode, "idkVarName");
			
			if (getNodeAttribute(mechanismNode, "entityName") != null)
				entityName = getNodeAttribute(mechanismNode, "entityName");
			
			sbo.append("\t\t\t" + entityType + " " + entityName + " = " + entityType + ".findByIdk(manager, " + idkVarName + ");\n");
		}
		else if (mechanismType.equals("fields"))
		{
			String findMethod = getNodeAttribute(mechanismNode, "findMethod");
			if (getNodeAttribute(mechanismNode, "listVarName") != null)
				listVarName = getNodeAttribute(mechanismNode, "listVarName");

			sbo.append("\t\t\tMap<String, Object> searchFields" + taskCounter + 
				" = new LinkedHashMap<String, Object>();\n");
			NodeList fieldNodes = mechanismNode.getChildNodes();
			for (int i=0; i<fieldNodes.getLength(); i++)
			{
				Node fieldNode = fieldNodes.item(i);
				String fName = Utilities.goToNode(fieldNode, "name").getTextContent();
				String fValue = Utilities.goToNode(fieldNode, "value").getTextContent();
				sbo.append("\t\t\tsearchFields" + taskCounter + ".put(\"" + fName + "\", " + fValue + ");\n");
			}
			sbo.append('\n');
			sbo.append("\t\t\tList " + listVarName + " = " + findMethod + "(manager, searchFields" + taskCounter + ");\n");
		}
		else if (mechanismType.equals("fieldSet"))
		{
			String findMethod = getNodeAttribute(mechanismNode, "findMethod");
			if (getNodeAttribute(mechanismNode, "listVarName") != null)
				listVarName = getNodeAttribute(mechanismNode, "listVarName");

			sbo.append("\t\t\tList " + listVarName + " = " + findMethod + "(manager, " +
				mechanismNode.getTextContent().trim() + ");\n");
		}
		else if (mechanismType.equals("condition"))
		{
			String findMethod = getNodeAttribute(mechanismNode, "findMethod");
			if (getNodeAttribute(mechanismNode, "listVarName") != null)
				listVarName = getNodeAttribute(mechanismNode, "listVarName");
			sbo.append("\t\t\tList " + listVarName + " = " + findMethod + "(manager,\n" +
				"\t\t\t\t" + mechanismNode.getTextContent().trim().replaceAll("\n", "\n\t\t\t\t") + ", null);\n");
		}
		else if (mechanismType.equals("custom"))
		{
			NodeList codeFragmentNodes = mechanismNode.getChildNodes();
			fmCodeReferences = new Vector<CodeFragmentReference>();
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
				fmCodeReferences.add(cfr);
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
			
			if (nodeName.equals("findMechanism"))
				extractFindMechanism(child);
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
			oecGenerator.write(file, listVarName, listVarName + ".size()");
	}
}
