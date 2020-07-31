package aaacs.coreserver.generation.ejb.beans.method;

import static aaacs.coreserver.commons.generation.Utilities.getNodeAttribute;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.CodeFragmentReference;
import aaacs.coreserver.commons.generation.CodeFragmentsGenerator;
import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.generation.ejb.ErrorReportGenerator;

public class OperationErrorChecksGenerator
{
	private String methodDisplayName;
	protected int taskCounter;
	private Map<String, List<CodeFragmentReference>> ecCodeReferences = null; // errchkcode
	private List<String> errorCheckIfs = null;
	private List<ErrorReportGenerator> errorCheckReports = null;

	public OperationErrorChecksGenerator(Node ecNode, int taskCounter, 
		String methodDisplayName)
	{
		this.taskCounter = taskCounter;
		this.methodDisplayName = methodDisplayName;
		extract(ecNode);
	}
	
	private void extract(Node ecNode)
	{
		NodeList errorCheckNodes = ecNode.getChildNodes();
		errorCheckIfs = new Vector<String>();
		errorCheckReports = new Vector<ErrorReportGenerator>();
		ecCodeReferences = new HashMap<String, List<CodeFragmentReference>>();

		for (int i=0; i<errorCheckNodes.getLength(); i++)
		{
			Node errorCheckNode = errorCheckNodes.item(i);
			String nodeName = errorCheckNode.getNodeName();
			
			if (nodeName.equals("sizeCheck"))   // if statement + errorReport /// list of references
			{
				Node typeOfSizeCheckNode = errorCheckNode.getFirstChild();
				String typeOfSizeCheck = typeOfSizeCheckNode.getNodeName();
				if (typeOfSizeCheck.equals("minimum")) typeOfSizeCheck = " < ";
				else if (typeOfSizeCheck.equals("maximum")) typeOfSizeCheck = " > ";
				else if (typeOfSizeCheck.equals("specific")) typeOfSizeCheck = " != ";
				
				errorCheckIfs.add("\t\t\tif (_NULLCHECKVAR_ != null && _SIZECHECKVAR_" + 
					typeOfSizeCheck + typeOfSizeCheckNode.getTextContent() + ")\n");
				errorCheckReports.add(
					new ErrorReportGenerator(errorCheckNode.getLastChild(), methodDisplayName));
			}
			else
			if (nodeName.equals("customCheck"))
			{
				NodeList codeFragmentNodes = errorCheckNode.getChildNodes();
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
				errorCheckIfs.add("__CUSTOM__:" + i);
				errorCheckReports.add(null);
				ecCodeReferences.put("__CUSTOM__:" + i, codeReferences);
			}
		}
	}
	
	/*
	 * A really bad kludge in nullCheckVar & sizeCheckVar: I thought the errorChecks
	 * in all the operations were close enough to merge into one class. I guess they
	 * are sufficiently different that I should have thought about it more. So this is
	 * really bad admittedly.
	 */
	public void write(PrintWriter file, String nullCheckVar, String sizeCheckVar)
		throws GenerationException
	{
		if (errorCheckIfs != null)
		{
			int i = 0;
			for (String ec : errorCheckIfs)
			{
				if (ec.startsWith("__CUSTOM__:"))
				{
					List<CodeFragmentReference> codeReferences = ecCodeReferences.get(ec);
					for (CodeFragmentReference cfr : codeReferences)
					{
						file.print(CodeFragmentsGenerator.getCodeFragment(
							cfr.reference, cfr.baseIndent, cfr.keepExtraIndents, 
							cfr.startComment, cfr.endComment));
						file.print("\n");
					}
				}
				else
				{
					file.print(ec.replaceAll("_NULLCHECKVAR_", nullCheckVar).replaceAll("_SIZECHECKVAR_", sizeCheckVar));
					ErrorReportGenerator report = errorCheckReports.get(i);
					report.write(file, 4);
				}
				i++;
			}
			file.print("\t\t\tif (errors.size() > 0) return errors;\n");
		}
	}
}
