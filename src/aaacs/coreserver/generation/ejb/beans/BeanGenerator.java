package aaacs.coreserver.generation.ejb.beans;

import static aaacs.coreserver.commons.generation.Utilities.getCommentIndent;
import static aaacs.coreserver.commons.generation.Utilities.getCommentType;
import static aaacs.coreserver.commons.generation.Utilities.goToNode;
import static aaacs.coreserver.commons.generation.Utilities.javaComment;
import static aaacs.coreserver.commons.generation.Utilities.javaDocComment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.CodeFragmentsGenerator;
import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;

/*

<coreServerBean>

	<class>
		<comment></comment>
		<outputDirectory></outputDirectory>
		<package></package>
		<imports>
			<import></import>
		</imports>
		<name></name>
		<annotations>
			<beanType></beanType>
			<interfaces>
				<interface name type(local/remote)/>
			</interfaces>
			<transactionManagement type ctrTxAttr/>
			<ejbs>
				<name>
				<beanName>
				<beanInterface>
			</ejbs>
		</annotations>
	</class>
	
	<methods...> see MethodsGenerator
	
	<codeFragments>
		<codeFragment name type></codeFragment>
	</codeFragments>

</coreServerBean>

*/
public class BeanGenerator extends Generator
{
	// beanInterface to beanName
	private static Map<String, String> globalEjbs =
		new LinkedHashMap<String, String>();
	static
	{
		globalEjbs.put("aaacs.coreserver.ejb.beans.stateless.interfaces.CoreServerAdministrator.class",
			"CoreServerAdministratorBean");
	}

	public static void generateBeans(String beansOutputDirectory,
		String dispatcherClassFile, String dispatcherPackage,
		String actionPropertiesFile,
		LinkedHashMap<String, BeanGenerator> beanGenerators)
		throws IOException, GenerationException
	{
		if (beansOutputDirectory == null || beansOutputDirectory.trim().equals(""))
			throw new GenerationException("Must configure output directory " +
				"for Java beans generation.");
		if (actionPropertiesFile == null || actionPropertiesFile.trim().equals(""))
			throw new GenerationException("Must configure location of " +
				"action properties file.");

		// Generate individual bean classes
		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			System.out.printf("Generating bean for %s ...", className);
			beanGenerator.writeClass(beansOutputDirectory);
			beanGenerator.writeInterfaces(beansOutputDirectory + "/interfaces");
			System.out.printf(" done\n");
		}

		// Generate Dispatcher class
		PrintWriter dpFile = 
			new PrintWriter(new FileWriter(dispatcherClassFile, false));
		dpFile.println(
			"/* *************************************************************************** \n" +
			" * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * \n" +
			" * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * \n" +
			" * Copyright Ahmed A. Abd-Allah, 2006                                        * \n" +
			" * ***************************************************************************/\n");
		dpFile.print("package " + dispatcherPackage + ";\n\n");
		
		dpFile.print(
			"import java.util.List;\n" +
			"import javax.naming.InitialContext;\n" +
			"import javax.ejb.EJB;\n" +
			"import javax.ejb.EJBs;\n" +
			"import javax.ejb.Remote;\n" +
			"import javax.ejb.Stateless;\n" +
			"import javax.ejb.TransactionManagement;\n" +
			"import aaacs.coreserver.ejb.beans.stateless.CoreServerDispatcherBean;\n" +
			"import aaacs.coreserver.commons.communication.ActionRequest;\n" +
			"import aaacs.coreserver.commons.communication.ActionResponse;\n" +
			"import aaacs.coreserver.commons.communication.ErrorReport;\n" +
			"import aaacs.coreserver.commons.exceptions.CoreServerException;\n" +
			"import " + dispatcherPackage + ".interfaces.Dispatcher;\n");
		
		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			dpFile.print("import " + beanGenerator.classPackage + 
				".interfaces." + beanGenerator.localInterface + ";\n");
			//System.out.printf("Generating dispatcher calls for %s ...", className);
			//beanGenerator.writeDispatcherCalls(actionPropsFile);
			//System.out.printf(" done\n");
		}
		dpFile.println();
		dpFile.print(
			"@Stateless\n@Remote(Dispatcher.class)\n" +
			"@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)\n" + 
			"@EJBs\n(\n\tvalue =\n\t{\n");
		boolean foundOne = false;
		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			if (foundOne) dpFile.print(",\n");
			dpFile.printf("\t\t@EJB(name=\"ejb/%s\",\n" +
				"\t\t\tbeanInterface=%s.interfaces.%s.class,\n" +
				"\t\t\tbeanName=\"%sBean\")", 
				beanGenerator.localInterface, beanGenerator.classPackage,
				beanGenerator.localInterface, beanGenerator.localInterface);
			foundOne = true;
		}
		dpFile.print(
			"\n\t}\n)\n");
		dpFile.print("public class DispatcherBean extends CoreServerDispatcherBean implements Dispatcher\n" +
			"{\n\tpublic List<ErrorReport> " +
			"dispatch(ActionRequest request, ActionResponse response)\n" +
			"\t\tthrows CoreServerException\n\t{\n");
		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			dpFile.print("\t\t" + beanGenerator.localInterface + " " +
				Utilities.firstLetterToLowerCase(beanGenerator.localInterface) +
				" = null;\n");
		}
		dpFile.println();
		dpFile.print("\t\ttry\n\t\t{\n\t\t\tInitialContext ic = new InitialContext();\n");
		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			dpFile.print("\t\t\t" +
				Utilities.firstLetterToLowerCase(beanGenerator.localInterface) + " = (" +
				beanGenerator.localInterface + ") ic.lookup(\"java:comp/env/ejb/" +
				beanGenerator.localInterface + "\");\n");
		}
		dpFile.print("\t\t}\n\t\tcatch (Exception e)\n\t\t{\n" +
			"\t\t\tthrow new CoreServerException(\"jndi.LookupError\", e);\n\t\t}\n\n");
		foundOne = false;
		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			for (String methodName : beanGenerator.methodsGenerator.methodProps.keySet())
			{
				String returnType = beanGenerator.methodsGenerator.methodProps.get(methodName).get("returnType");
				if (returnType != null)
					continue;
				dpFile.printf("\t\t" + (foundOne ? "else " : "") +
					"if (request.getActionName().equals(\"%s\") &&\n" +
					"\t\t\trequest.getActionVersion() == %s)\n" +
					"\t\t\treturn %s.%s(request, response);\n",
					beanGenerator.methodsGenerator.methodProps.get(methodName).get("displayName"),
					beanGenerator.methodsGenerator.methodProps.get(methodName).get("version"),
					Utilities.firstLetterToLowerCase(beanGenerator.localInterface),
					methodName);
				foundOne = true;
			}
		}
		dpFile.print("\t\telse\n\t\t{\n\t\t\tObject args[] = " +
			"{ request.getActionName(), request.getActionVersion() };\n" +
			"\t\t\tthrow new CoreServerException(\"action.UnableToDispatchAction\", args);\n" +
			"\t\t}\n\t}\n\n\tpublic boolean actionRequiresLoggedInUser(ActionRequest request)\n" +
			"\t{\n\t\ttry\n\t\t{\n");
		foundOne = false;
		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			for (String methodName : beanGenerator.methodsGenerator.methodProps.keySet())
			{
				String returnType = beanGenerator.methodsGenerator.methodProps.get(methodName).get("returnType");
				if (returnType != null)
					continue;
				if (beanGenerator.methodsGenerator.methodProps.get(methodName).
					get("requiresLoggedInUser") == null)
				{
					dpFile.printf("\t\t\t" + (foundOne ? "else " : "") +
						"if (request.getActionName().equals(\"%s\") &&\n" +
						"\t\t\t\trequest.getActionVersion() == %s)\n" +
						"\t\t\t\treturn false;\n",
						beanGenerator.methodsGenerator.methodProps.get(methodName).get("displayName"),
						beanGenerator.methodsGenerator.methodProps.get(methodName).get("version"));
					foundOne = true;
				}
			}
		}
		
		dpFile.print("\t\t}\n\t\tcatch (Exception e) {}\n" +
			"\t\treturn true; // assume the worst: force the tighter check\n\t}\n");
		dpFile.print("}\n");
		dpFile.close();

		// Generate action resources
		PrintWriter actionPropsFile = 
			new PrintWriter(new FileWriter(actionPropertiesFile, false));
		
		actionPropsFile.print(
"# -----------------------------------------------------------------------------\n" +
"# THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT EDIT LEST YOU LOSE YOUR CHANGES.\n" +
"# Configuration file for the Core Server\n" +
"# Copyright (C) Ahmed A. Abd-Allah, 2006\n" +
"#\n" +
"# Contains: text related to the acceptable actions for the Core Server. If none,\n" + 
"# leave the file blank, but make sure the file *is* there.\n" +
"#\n" +
"# -----------------------------------------------------------------------------\n\n");

		for (String className : beanGenerators.keySet())
		{
			BeanGenerator beanGenerator = beanGenerators.get(className);
			System.out.printf("Generating action resources for %s ...", className);
			beanGenerator.writeActionResources(actionPropsFile);
			System.out.printf(" done\n");
		}
		actionPropsFile.close();
		
		//PrintWriter resFile = new PrintWriter(
		System.out.printf("Done generating Java beans.\n\n");
	}

	
	private Node rootNode = null;
	private String classDirectory = null;
	private String className = null;
	private String classComment = null;
	private String classPackage = null;
	private String classImports = null;
	private String beanType = null;
	private String localInterface = null;
	private String remoteInterface = null;
	private String transactionManagementType = null;
	private String containerTxAttr = null;
	private Vector<String[]> ejbs = new Vector<String[]>();
	private MethodsGenerator methodsGenerator = null;
	protected CodeFragmentsGenerator codeFragments = null;

	public BeanGenerator(String defaultPackage, String defaultImports, Node inRootNode)
		throws GenerationException
	{
		if (inRootNode == null)
			throw new GenerationException("Element for bean root node should not be null");
		rootNode = inRootNode;

		Node codeFragmentsNode = goToNode(rootNode, "codeFragments");
		codeFragments = new CodeFragmentsGenerator(codeFragmentsNode);

		Node classNode = goToNode(rootNode, "class");
		Node methodsNode = goToNode(rootNode, "methods");
		//Node codeFragmentsNode = goToNode(rootNode, "codeFragments");

		classPackage = defaultPackage;
		classImports = defaultImports;
		extractClassInformation(classNode);
		if (methodsNode != null)
			methodsGenerator = new MethodsGenerator(methodsNode);
		// extractAttributesInformation(attributesNode, true, true);
	}

	public String getClassName()
	{
		return className;
	}

	private void extractAnnotations(Node annotationsNode)
	{
		NodeList annoNodes = annotationsNode.getChildNodes();
		
		for (int i=0; i<annoNodes.getLength(); i++)
		{
			Node annoNode = annoNodes.item(i);
			String annoNodeName = annoNode.getNodeName();
			
			if (annoNodeName.equals("beanType"))
				beanType = "@" + annoNode.getTextContent();
			else if (annoNodeName.equals("interfaces"))
			{
				NodeList interfaceNodes = annoNode.getChildNodes();
				
				for (int j=0; j<interfaceNodes.getLength(); j++)
				{
					Node interfaceNode = interfaceNodes.item(j);
					if (Utilities.getNodeAttribute(interfaceNode, "type").equals("Local"))
					{
						localInterface = Utilities.getNodeAttribute(interfaceNode, "name");
						globalEjbs.put(classPackage + ".interfaces." + localInterface + ".class", className);
					}
					if (Utilities.getNodeAttribute(interfaceNode, "type").equals("Remote"))
					{
						remoteInterface = Utilities.getNodeAttribute(interfaceNode, "name");
						globalEjbs.put(classPackage + ".interfaces." + remoteInterface + ".class", className);
					}
				}
			}
			else if (annoNodeName.equals("transactionManagement"))
			{
				transactionManagementType = Utilities.getNodeAttribute(annoNode, "type");
				containerTxAttr = Utilities.getNodeAttribute(annoNode, "ctrTxAttr");
			}
			else if (annoNodeName.equals("ejbs"))
			{
				NodeList ejbNodes = annoNode.getChildNodes();
				
				for (int j=0; j<ejbNodes.getLength(); j++)
				{
					Node ejbNode = ejbNodes.item(j);
					String[] ejbInfo = new String[3];
					ejbInfo[0] = Utilities.goToNode(ejbNode, "name").getTextContent();
					ejbInfo[1] = Utilities.goToNode(ejbNode, "beanInterface").getTextContent();
					ejbInfo[2] = Utilities.goToNode(ejbNode, "beanName").getTextContent();
					ejbs.add(ejbInfo);
				}
			}
		}
	}

	private void extractClassInformation(Node classNode)
		throws GenerationException
	{
		if (!classNode.getNodeName().equals("class"))
			throw new GenerationException("Expected a \"class\" element!");

		NodeList children = classNode.getChildNodes();

		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			String childName = child.getNodeName();

			// Get class name
			if (childName.equals("name"))
				className = child.getTextContent();
			else if (childName.equals("outputDirectory"))
				classDirectory = child.getTextContent();
			else if (childName.equals("package"))
				classPackage = child.getTextContent();
			else if (childName.equals("imports"))
			{
				NodeList importNodes = child.getChildNodes();
				for (int j=0; j<importNodes.getLength(); j++)
				{
					Node importNode = importNodes.item(j);
					classImports += "\nimport " + importNode.getTextContent() + ";";	
				}				
			}
			else if (childName.equals("annotations"))
			{
				extractAnnotations(child);
			}
			else if (childName.equals("comment"))
			{
				if (getCommentType(child).equals("JavaDoc"))
					classComment = javaDocComment(child.getTextContent(), getCommentIndent(child));
				else
					classComment = javaComment(child.getTextContent(), getCommentIndent(child));
			}
			else
				throw new GenerationException("Unknown child of \"class\" element");
		}
	}

	public void writeActionResources(PrintWriter file)
	{
		if (methodsGenerator != null)
			methodsGenerator.writeActionResources(file);
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
			" * Copyright Ahmed A. Abd-Allah, 2006-2007                                   * \n" +
			" * ***************************************************************************/\n");
	
		if (classPackage != null)
			file.println("package " + classPackage + ";");
		if (classImports != null)
			file.println(classImports);
		if (localInterface != null)
			file.println("import " + classPackage + ".interfaces." + localInterface +";");
		if (remoteInterface != null)
			file.println("import " + classPackage + ".interfaces." + remoteInterface + ";");
		file.println();
		if (classComment != null)
			file.println(classComment);
	
		file.print(beanType + "\n");
		if (localInterface != null)
			file.print("@Local(" + localInterface + ".class)\n");
		if (remoteInterface != null)
			file.print("@Remote(" + remoteInterface + ".class)\n");
		file.printf("@TransactionManagement(javax.ejb.TransactionManagementType.%s)\n",
			transactionManagementType.toUpperCase());
		file.printf("@TransactionAttribute(javax.ejb.TransactionAttributeType.%s)\n",
			containerTxAttr.toUpperCase());
		
		if (globalEjbs.size() > 0 || ejbs.size() > 0)
		{
			file.print("@EJBs\n(\n\tvalue = \n\t{");
			boolean foundOne = false;
			for (String beanInterface : globalEjbs.keySet())
			{
				String beanName = globalEjbs.get(beanInterface);
				if (foundOne) file.print(",\n");
				else file.print("\n");
				
				String interfaceName = beanInterface.replaceAll("^.*\\.([^\\.]*)\\.class$", "$1");
				file.printf("\t\t@EJB(name=\"ejb/%s\",\n" +
					"\t\t\tbeanInterface=%s,\n" +
					"\t\t\tbeanName=\"%s\")", interfaceName, beanInterface, beanName);
				foundOne = true;
			}
			for (String[] ejbInfo : ejbs)
			{
				if (foundOne) file.print(",\n");
				else file.print("\n");
				
				file.printf("\t\t@EJB(name=\"%s\",\n" +
					"\t\t\tbeanInterface=%s,\n" +
					"\t\t\tbeanName=\"%s\")", ejbInfo[0], ejbInfo[1], ejbInfo[2]);
				foundOne = true;				
			}
			file.print("\n\t}\n)\n");
		}
		
		file.printf("public class %s extends BaseSessionBean implements ", className);
		if (localInterface != null)
			file.printf(localInterface);
		if (remoteInterface != null)
			if (localInterface != null) file.print(", " + remoteInterface);
			else file.print(remoteInterface);
		file.print("\n{\n");
		file.print("\t@PersistenceContext(unitName=\"CoreServer\") private EntityManager manager;\n\n");
	
		if (methodsGenerator != null)
			methodsGenerator.writeMethods(file);
	
		// End of class
		file.println("}\n");
		
		file.close();
	
	}

	public void writeInterfaces(String outputDirectory)
		throws IOException
	{
		if (classDirectory != null)
			outputDirectory = classDirectory;
		
		// Business interfaces (local and remote)
		String[] bInterfaces = { localInterface, remoteInterface };
		String[] bInterfaceTypes = { "Local", "Remote" };
	
		int i = 0;
		for (String bInterface : bInterfaces)
		{
			if (bInterface == null)
				continue;
			
			PrintWriter file = 
				new PrintWriter(new FileWriter(outputDirectory + "/" + bInterface + ".java", 
					false));
	
			file.println(
				"/* *************************************************************************** \n" +
				" * THIS FILE WAS AUTOMATICALLY GENERATED. DO NOT MODIFY IT DIRECTLY OR ELSE  * \n" +
				" * YOUR CHANGES WILL BE OVERWRITTEN AND LOST.                                * \n" +
				" * Copyright Ahmed A. Abd-Allah, 2006                                        * \n" +
				" * ***************************************************************************/\n");
		
			if (classPackage != null)
				file.println("package " + classPackage + ".interfaces;\n");
			file.println(
				"import java.util.List;\n" +
				"import javax.ejb." + bInterfaceTypes[i] + ";\n" +
				"import aaacs.coreserver.commons.communication.ActionRequest;\n" +
				"import aaacs.coreserver.commons.communication.ActionResponse;\n" +
				"import aaacs.coreserver.commons.communication.ErrorReport;\n" +
				"import aaacs.coreserver.commons.exceptions.CSWrapperException;\n");

			file.println("@" + bInterfaceTypes[i]);
			file.printf("public interface %s\n{\n", bInterface);

			if (methodsGenerator != null)
			{
				methodsGenerator.writeInterfaceMethods(file);
			}
	
			// End of class
			file.println("}\n");
		
			file.close();
			i++;
		}
	}

}

