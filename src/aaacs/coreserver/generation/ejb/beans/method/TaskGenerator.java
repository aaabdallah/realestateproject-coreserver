package aaacs.coreserver.generation.ejb.beans.method;

import static aaacs.coreserver.commons.generation.Utilities.getNodeAttribute;
import static aaacs.coreserver.commons.generation.Utilities.goToNode;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.CodeFragmentReference;
import aaacs.coreserver.commons.generation.CodeFragmentsGenerator;
import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;

public class TaskGenerator extends Generator
{
	private List<AuthorizationGenerator> authorizations = new Vector<AuthorizationGenerator>();
	public List<ParameterGenerator> inputParameters = new Vector<ParameterGenerator>();
	public List<ParameterGenerator> outputParameters = new Vector<ParameterGenerator>();
	public Object operation = null;
	public List<CodeFragmentReference> cuopCodeReferences = null; // custom operation
	private int taskCounter;
	private String methodDisplayName = null;
	
	public TaskGenerator(Node taskNode, int taskCounter, String methodDisplayName)
		throws GenerationException
	{
		this.taskCounter = taskCounter;
		this.methodDisplayName = methodDisplayName;
		extract(taskNode);
	}
	
	private void extract(Node taskNode)
		throws GenerationException
	{
		Node authorizationsNode = goToNode(taskNode, "authorizations");
		if (authorizationsNode != null)
		{
			NodeList authorizationNodes = authorizationsNode.getChildNodes();
			for (int i=0; i<authorizationNodes.getLength(); i++)
			{
				Node authorizationNode = authorizationNodes.item(i);
				authorizations.add(new AuthorizationGenerator(authorizationNode));
			}
		}
		
		Node inputParametersNode = goToNode(taskNode, "inputParameters");
		if (inputParametersNode != null)
		{
			NodeList inputParameterNodes = inputParametersNode.getChildNodes();
			for (int i=0; i<inputParameterNodes.getLength(); i++)
			{
				Node inputParameterNode = inputParameterNodes.item(i);
				inputParameters.add(
					new ParameterGenerator(inputParameterNode, true, taskCounter, i+1, methodDisplayName));
			}
		}
		
		Node executeNode = goToNode(taskNode, "execute");
		if (executeNode != null)
		{
			Node operationNode = executeNode.getFirstChild();
			String nodeName = operationNode.getNodeName();
			if (nodeName.equals("read"))
				operation = new ReadOperationGenerator(operationNode, taskCounter, methodDisplayName);
			else if (nodeName.equals("create"))
				operation = new CreateOperationGenerator(operationNode, taskCounter, methodDisplayName);
			else if (nodeName.equals("update"))
				operation = new UpdateDeleteOperationGenerator(operationNode, taskCounter, methodDisplayName);
			else if (nodeName.equals("delete"))
				operation = new UpdateDeleteOperationGenerator(operationNode, taskCounter, methodDisplayName);
			else if (nodeName.equals("customCode"))
			{
				NodeList codeFragmentNodes = operationNode.getChildNodes();
				cuopCodeReferences = new Vector<CodeFragmentReference>();
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
					cuopCodeReferences.add(cfr);
				}
			}
		}

		Node outputParametersNode = goToNode(taskNode, "outputParameters");
		if (outputParametersNode != null)
		{
			NodeList outputParameterNodes = outputParametersNode.getChildNodes();
			for (int i=0; i<outputParameterNodes.getLength(); i++)
			{
				Node outputParameterNode = outputParameterNodes.item(i);
				outputParameters.add(
					new ParameterGenerator(outputParameterNode, false, taskCounter, i+1, methodDisplayName));
			}
		}		
	}

	public void write(PrintWriter file)
		throws GenerationException
	{
		if (authorizations.size() > 0)
		{
			file.print("\t\t\t// Check authorizations...\n");
			for (AuthorizationGenerator a : authorizations)
				a.write(file);
			file.print("\t\t\tif (errors.size() > 0) return errors;\n\n");
		}

		if (inputParameters.size() > 0)
		{
			file.print("\t\t\t// Extract and check input parameters...\n");
			for (ParameterGenerator p : inputParameters)
				p.write(file);
			file.print("\t\t\tif (errors.size() > 0) return errors;\n\n");
		}

		if (operation != null)
		{
			if (operation instanceof CreateOperationGenerator)
			{
				file.print("\t\t\t// Execute create operation...\n");
				((CreateOperationGenerator) operation).write(file);
			}
			if (operation instanceof ReadOperationGenerator)
			{
				file.print("\t\t\t// Execute read operation...\n");
				((ReadOperationGenerator) operation).write(file);
			}
			if (operation instanceof UpdateDeleteOperationGenerator)
			{
				if ( ((UpdateDeleteOperationGenerator) operation).isUpdate )
					file.print("\t\t\t// Execute update operation...\n");
				else
					file.print("\t\t\t// Execute delete operation...\n");
				((UpdateDeleteOperationGenerator) operation).write(file);
			}
			file.print("\n");
		}
		else if (cuopCodeReferences != null)
		{
			for (CodeFragmentReference cfr : cuopCodeReferences)
			{
				file.print(CodeFragmentsGenerator.getCodeFragment(
					cfr.reference, cfr.baseIndent, cfr.keepExtraIndents, 
					cfr.startComment, cfr.endComment));
			}
		}

		if (outputParameters.size() > 0)
		{
			file.print("\t\t\t// Package output parameters...\n");
			for (ParameterGenerator p : outputParameters)
				p.write(file);
			file.print("\n");
		}
	}
}
