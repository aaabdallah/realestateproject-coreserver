package aaacs.coreserver.generation.ejb;

import static aaacs.coreserver.commons.generation.Utilities.getNodeAttribute;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;

public class ErrorReportGenerator extends Generator
{
	private String usage = null;
	private String userVar = null;
	private String system = null;
	private String source = null;
	private String type = null;
	private boolean useInternalTypePrefix = true;
	private String typePrefix = "";
	private String request = null;
	private String userIdk = null;
	private String actionName = null;
	private String actionVersion = null;
	private String actionTime = null;
	private List<String> items = null;
	
	public ErrorReportGenerator(Node errorNode, String typePrefix)
	{
		if (typePrefix != null)
			this.typePrefix = typePrefix;
		extract(errorNode);
	}
	
	private void extract(Node errorNode)
	{
		usage = getNodeAttribute(errorNode, "usage");
		userVar = getNodeAttribute(errorNode, "userVar");
		system = getNodeAttribute(errorNode, "system");
		source = getNodeAttribute(errorNode, "source");
		type = getNodeAttribute(errorNode, "type");
		request = getNodeAttribute(errorNode, "request");
		useInternalTypePrefix = 
			Boolean.parseBoolean(getNodeAttribute(errorNode, "useInternalTypePrefix"));
		if (request == null)
		{
			userIdk = getNodeAttribute(errorNode, "userIdk");
			actionName = getNodeAttribute(errorNode, "actionName");
			actionVersion = getNodeAttribute(errorNode, "actionVersion");
			actionTime = getNodeAttribute(errorNode, "actionTime");
		}
		
		NodeList itemNodes = errorNode.getChildNodes();
		if (itemNodes.getLength() > 0)
			items = new Vector<String>();
		for (int i=0; i<itemNodes.getLength(); i++)
		{
			Node itemNode = itemNodes.item(i);
			String itemArgs = "";

			NodeList argNodes = itemNode.getChildNodes();
			boolean foundArg = false;
			for (int j=0; j<argNodes.getLength(); j++)
			{
				if (foundArg)
					itemArgs += ", ";
				itemArgs += argNodes.item(j).getTextContent();
				foundArg = true;
			}
			items.add(itemArgs);
		}
	}
	
	public void write(PrintWriter file, int baseIndent)
	{
		// return( new ErrorReport(...).addItem(...)
		// var = new ErrorReport(...).addItem(...)
		// errors.add( new ErrorReport(...).addItem(...)
		StringBuilder error = 
			new StringBuilder("new ErrorReport(\"error.System." + system + "\", ErrorReport.Source." + source + ",\n\t" +
			"\"" + 
			((useInternalTypePrefix && !typePrefix.equals("")) ? (typePrefix + ".") : "") 
			+ type + "\"");
		if (request != null)
			error.append(", " + request);
		else if (userIdk != null || actionName != null || actionVersion != null || actionTime != null)
		{
			error.append(", " + userIdk + ",\n\t" + actionName + ", " + actionVersion +
				",\n\t" + actionTime);
		}
		error.append(")");
		
		if (items != null)
		{
			boolean addedItem = false;
			for (String itemCall : items)
			{
				if (addedItem) error.append("\n\t");
				error.append(".addItem(" + itemCall + ")");
				addedItem = true;
			}
		}

		if (usage.equals("addToErrors"))
		{
			error.insert(0, userVar + ".add(");
			error.append(");");
		}
		else if (usage.equals("assign"))
		{
			error.insert(0, userVar + " = ");
			error.append(";");
		}
		else if (usage.equals("return"))
		{
			error.insert(0, "return ");
			error.append(";");
		}
		
		file.print(Utilities.javaCodeFragment(error.toString(), baseIndent, true, null, null));
		file.print("\n");
	}
}
