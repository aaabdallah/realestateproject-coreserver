package aaacs.coreserver.generation.ejb.beans.method;

import java.io.PrintWriter;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.Generator;

public class AuthorizationGenerator extends Generator
{
	public String userIdk = "request.getLoginToken().getUserIdk()";
	public String userRankIdk = "null";
	public String userRoles = "null";
	public String actionName = "request.getActionName()";
	public String actionVersion = "request.getActionVersion()";
	public String objectGroup = null;
	public String objectTable = null;
	public String objectIdk = null;
	public String objectParameters = null;
	
	public AuthorizationGenerator(Node authorizationNode)
	{
		extract(authorizationNode);
	}
	
	private void extract(Node authorizationNode)
	{
		NodeList children = authorizationNode.getChildNodes();
		for (int i=0; i<children.getLength(); i++)
		{
			Node child = children.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.equals("userIdk"))
				userIdk = child.getTextContent();
			else if (nodeName.equals("userRankIdk"))
				userRankIdk = child.getTextContent();
			else if (nodeName.equals("userRoles"))
				userRoles = child.getTextContent();
			else if (nodeName.equals("actionName"))
				actionName = child.getTextContent();
			else if (nodeName.equals("actionVersion"))
				actionVersion = child.getTextContent();
			else if (nodeName.equals("objectGroup"))
				objectGroup = child.getTextContent();
			else if (nodeName.equals("objectTable"))
				objectTable = child.getTextContent();
			else if (nodeName.equals("objectIdk"))
				objectIdk = child.getTextContent();
			else if (nodeName.equals("objectParameters"))
				objectParameters = child.getTextContent();
		}
	}
	
	public void write(PrintWriter file)
	{
		file.printf("\t\t\tif (!isAuthorized(manager, %s,\n" +
			"\t\t\t\t%s, %s,\n" +
			"\t\t\t\t%s, %s,\n" +
			"\t\t\t\t%s, %s,\n" +
			"\t\t\t\t%s, %s))\n" +
			"\t\t\t\terrors.add(new ErrorReport(\"error.System.CoreServer\", ErrorReport.Source.DATARESULTS,\n" +
			"\t\t\t\t\t\"action.UserUnauthorized\"",
			userIdk, userRankIdk, userRoles, actionName, actionVersion, objectGroup,
			objectTable, objectIdk, objectParameters);
		String actionTime = "0";
		if (userIdk.startsWith("request") || 
			actionName.startsWith("request") || 
			actionVersion.startsWith("request"))
			actionTime = "request.getTimeReceived()";
		file.printf(", %s,\n\t\t\t\t\t%s, %s,\n\t\t\t\t\t%s", userIdk, actionName, actionVersion, actionTime);
		file.print("));\n\n");
	}
}
