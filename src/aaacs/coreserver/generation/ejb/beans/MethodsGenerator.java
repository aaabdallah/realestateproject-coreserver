package aaacs.coreserver.generation.ejb.beans;

import static aaacs.coreserver.commons.generation.Utilities.goToNode;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.CodeFragmentsGenerator;
import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;
import aaacs.coreserver.generation.ejb.beans.method.CreateOperationGenerator;
import aaacs.coreserver.generation.ejb.beans.method.ParameterGenerator;
import aaacs.coreserver.generation.ejb.beans.method.ReadOperationGenerator;
import aaacs.coreserver.generation.ejb.beans.method.TaskGenerator;
import aaacs.coreserver.generation.ejb.beans.method.UpdateDeleteOperationGenerator;

/*
CHECK THE SCHEMA FILES FOR THE LATEST.

<methods>
	<method>
		<name></name>
		<tasks requiresLoggedInUser(default is true)>
			<task>
				<authorizations>
					<authorization>
						<userIdk> (default is "fromRequest")
						<actionName></actionName> (default is "fromRequest")
						<actionVersion></actionVersion> (")
						<objectGroup> (default is "null")
						<objectTable> (default is "null")
						<objectIdk> (default is "null")
						<objectParameters> (default is "null")
					</authorization>
				</authorizations>
				<inputParameters>
					<inputParameter displayName enResource type 
					[methodSuffix varName keyClasses valueClasses entityType entityVarName]>
						<validations>
							(can do any number of the following four:)
							<staticEntityValidator checkIfUserSuppliedIdk(default=true)>
							<staticFieldValidator>
							<parameterValidator>
							<customValidator>
						</validations>
					</inputParameter>
				</inputParameters>
				<execute>
					(choice between create, read, update, delete, and customCode)
					<read>
						<findMechanism>
							<fieldSet findMethod listVarName>
								(search fields reference)
							</fieldSet>
							<fields findMethod listVarName>
								<field><name>...</name><value>...</value></field>*
							</fields>
							<condition findMethod listVarName></condition>
							<query findMethod></query>
							<custom>
								<codeFragmentReference></codeFragmentReference>*
							</custom>
						</findMechanism>
						<errorChecks>
							<sizeCheck>
								<minimum>...</minimum>
								<maximum>...
								<specific>...
								<error source type request userIdk actionName actionVersion actionTime>
									<item><arg>[<arg>][<arg>]</item>*
								</error>
							</sizeCheck>
							<customCheck>
								<codeFragmentReference></codeFragmentReference>*
							</customCheck>
						</errorChecks>
					</read>
					<create>
						<dataSource>
							<singleFieldSet fieldsName entityType [entityName]>
							<multipleFieldSets fieldsName entityType [entityListName]>
							<singleEntity entityName> (use in conjunction with "entityType" in inputParameters)
							<multipleEntities entityListName>
							<custom>
								<codeFragmentReference></codeFragmentReference>*
							</custom>
						</dataSource>
						<errorChecks>
							<customCheck>
								<codeFragmentReference></codeFragmentReference>*
							</customCheck>
						</errorChecks>
					</create>
					<update>
						<dataSource>
							<singleFieldSet fieldsName entityType [entityName]>
							<multipleFieldSets fieldsName entityType [entityListName]>
							<singleEntity entityName> (use in conjunction with "entityType" in inputParameters)
							<multipleEntities entityListName>
							
							<bulkFromFieldSet fieldsName entityType>
							<bulkFromCondition fieldsName entityType>

							<custom>
								<codeFragmentReference></codeFragmentReference>*
							</custom>
						</dataSource>
						<errorChecks>
							<sizeCheck>...
							<customCheck>
								<codeFragmentReference></codeFragmentReference>*
							</customCheck>
						</errorChecks>
					</update>
					<delete>
						<dataSource>
							<singleFieldSet entityType [entityName]>
							<multipleFieldSets entityType [entityListName]>
							<singleEntity entityName> (use in conjunction with "entityType" in inputParameters)
							<multipleEntities entityListName>
							
							<bulkFromFieldSet entityType>
							<bulkFromCondition entityType>

							<custom>
								<codeFragmentReference></codeFragmentReference>*
							</custom>
						</dataSource>
						<errorChecks>
							<sizeCheck>...
							<customCheck>
								<codeFragmentReference></codeFragmentReference>*
							</customCheck>
						</errorChecks>
					</delete>
					<customCode>
						<codeFragmentReference></codeFragmentReference>*
					</customCode>

					<update>...
					<delete>...
					<customCode>...
				</execute>
				<outputParameters>
					<outputParameter name type methodSuffix(optional) varName(required)
						keyClasses valueClasses)>
						
					</outputParameter>
				</outputParameters>
			</task>
		</tasks>
		<codeFragments>
			<codeFragment></codeFragment>
		</codeFragments>
	</method>
</methods>
*/
public class MethodsGenerator extends Generator
{	
	public Map<String, List<TaskGenerator>> methodTasks =
		new LinkedHashMap<String, List<TaskGenerator>>();
	public List<String> methodsRequiringLoggedInUsers =
		new Vector<String>();
	public Map<String, Map<String, String>> methodProps = 
		new LinkedHashMap<String, Map<String,String>>();
	public Map<String, Map<String, String>> methodParameters = 
		new LinkedHashMap<String, Map<String,String>>();
	protected CodeFragmentsGenerator codeFragments = null;

	public MethodsGenerator(Node methodsNode)
		throws GenerationException
	{
		extractMethods(methodsNode);
	}

	private void extractMethod(Node methodNode)
		throws GenerationException
	{
		Map<String, String> props = new LinkedHashMap<String, String>();
		Node tasksNode = goToNode(methodNode, "tasks");
		String methodName = Utilities.goToNode(methodNode, "name").getTextContent();
		String displayName = 
			Utilities.getNodeAttribute(Utilities.goToNode(methodNode, "name"), 
				"displayName");
		if (displayName == null)
			displayName = "action." + Utilities.firstLetterToUpperCase(methodName);
		props.put("displayName", displayName);
		String enResource = 
			Utilities.getNodeAttribute(Utilities.goToNode(methodNode, "name"), 
				"enResource");
		props.put("enResource", enResource);
		props.put("version", 
			Utilities.getNodeAttribute(Utilities.goToNode(methodNode, "name"), "version"));
		
		Node signatureNode = goToNode(methodNode, "signature");
		if (signatureNode != null)
		{
			props.put("returnType", goToNode(signatureNode, "returns").getTextContent());
			Node parametersNode = goToNode(signatureNode, "parameters");
			if (parametersNode != null)
			{
				Map<String, String> parameters = new LinkedHashMap<String, String>();
				NodeList children = parametersNode.getChildNodes();
				for (int i=0; i<children.getLength(); i+=2)
				{
					// name, type (note they are flipped in the xml file)
					parameters.put(children.item(i+1).getTextContent(), 
						children.item(i).getTextContent());
				}
				methodParameters.put(methodName, parameters);
			}
		}
		if (Boolean.parseBoolean(Utilities.getNodeAttribute(tasksNode, "requiresLoggedInUser")))
			props.put("requiresLoggedInUser", "true");

		methodProps.put(methodName, props);
		
		if (Boolean.parseBoolean(Utilities.getNodeAttribute(tasksNode, "requiresLoggedInUser")))
			methodsRequiringLoggedInUsers.add(displayName);

		Node codeFragmentsNode = goToNode(methodNode, "codeFragments");
		codeFragments = new CodeFragmentsGenerator(codeFragmentsNode);

		Vector<TaskGenerator> tasks = new Vector<TaskGenerator>();

		NodeList taskNodes = tasksNode.getChildNodes();
		for (int i=0; i<taskNodes.getLength(); i++)
		{
			Node taskNode = taskNodes.item(i);
			TaskGenerator task = new TaskGenerator(taskNode, i+1, displayName);
			tasks.add(task);
		}
		methodTasks.put(methodName, tasks);
	}
	
	private void extractMethods(Node rootNode)
		throws GenerationException
	{
		if (!rootNode.getNodeName().equals("methods"))
			throw new GenerationException("Expected a \"methods\" element!" +
				" (found: " + rootNode.getNodeName() + ")");
	
		NodeList methodNodes = rootNode.getChildNodes();
		for (int i=0; i<methodNodes.getLength(); i++)
		{
			Node methodNode = methodNodes.item(i);

			extractMethod(methodNode);
		}
	}

	public void writeActionResources(PrintWriter file)
	{
		if (methodTasks.size() == 0)
			return;
		
		for (String methodName : methodTasks.keySet())
		{
			// Print method name
			String displayName = methodProps.get(methodName).get("displayName");
			String enResource = methodProps.get(methodName).get("enResource");
			String actionVersion = methodProps.get(methodName).get("version");
			file.println("# Method: " + methodName + ", v" + actionVersion);
			file.println(displayName + "=" + enResource);

			int t=1;
			List<TaskGenerator> tasks = methodTasks.get(methodName);
			// Print input/output parameter names
			for (TaskGenerator task : tasks)
			{
				if (task.inputParameters.size() > 0)
					file.printf("# Task %d input parameters\n", t);
				for (ParameterGenerator input : task.inputParameters)
					file.println(input.displayName + "=" + input.enResource);
				if (task.outputParameters.size() > 0)
					file.printf("# Task %d output parameters\n", t);
				for (ParameterGenerator output : task.outputParameters)
					file.println(output.displayName + "=" + output.enResource);
				t++;
			}
			file.println();
		}
	}
	
	public void writeMethods(PrintWriter file)
		throws GenerationException
	{
		if (methodTasks.size() == 0)
			return;
		
		for (String methodName : methodTasks.keySet())
		{
			List<TaskGenerator> tasks = methodTasks.get(methodName);
			file.printf(
				"\t/*************************************************************************\n" + 
				"\t * Method: \"%s\", v%s (%d task%s)\n", 
				methodProps.get(methodName).get("displayName"),
				methodProps.get(methodName).get("version"),
				tasks.size(),
				(tasks.size() > 1 ? "s" : ""));
			int t = 1;
			for (TaskGenerator task : tasks)
			{
				String taskOperation = "Undetermined";
				if (task.operation instanceof ReadOperationGenerator)
					taskOperation = "Read";
				else if (task.operation instanceof CreateOperationGenerator)
					taskOperation = "Create";
				else if (task.operation instanceof UpdateDeleteOperationGenerator)
				{
					if (((UpdateDeleteOperationGenerator) task.operation).isUpdate)
						taskOperation = "Update";
					else
						taskOperation = "Delete";
				}
				else if (task.cuopCodeReferences != null)
					taskOperation = "Custom code";
				file.printf("\t * Task %d: %s\n", t, taskOperation);
				for (ParameterGenerator input : task.inputParameters)
				{
					file.printf("\t *   Input: %s %s (%s)\n", input.type, input.displayName, 
						input.userVarName);
				}
				for (ParameterGenerator input : task.outputParameters)
				{
					file.printf("\t *   Output: %s %s (%s)\n", input.type, input.displayName, 
						input.userVarName);
				}
				t++;
			}
			file.print(
				"\t *************************************************************************/\n");
			
			// The returnType is used to turn *off* code generation practically: it signifies this is
			// not a regular bean method for communicating with the delegates. However, all exceptions
			// are stuffed into CSWrapperExceptions nonetheless.
			String returnType = methodProps.get(methodName).get("returnType");
			if (returnType != null)
			{
				file.printf("\tpublic %s %s(", returnType, methodName);
				Map<String, String> parameters = methodParameters.get(methodName);
				if (parameters != null)
				{
					boolean foundOne = false;
					for (String name : parameters.keySet())
					{
						file.printf("%s%s %s", (foundOne ? ", " : ""), parameters.get(name), name);
						foundOne = true;
					}
				}
				file.printf(")\n\t\tthrows CSWrapperException\n\t{\n");
			}
			else
			{
				file.printf("\tpublic List<ErrorReport> %s(ActionRequest request, ActionResponse response)\n" +
					"\t\tthrows CSWrapperException\n\t{\n", methodName);
				file.print("\t\tList<ErrorReport> errors = new Vector<ErrorReport>();\n" +
					"\t\tMap<String, List<MsgArgsPair>> vldResults = null;\n" +
					"\t\tParameters inputParameters = request.getParameters();\n" +
					"\t\tParameters outputParameters = new Parameters();\n");
			}
			file.print("\t\ttry\n\t\t{\n");
			t = 1;
			for (TaskGenerator task : tasks)
			{
				//file.print("\t\t\t// ----------------------------------------------------------------\n");
				file.printf("\t\t\t// ----- Begin Task %d ---------------------------------------------\n", t);
				task.write(file);
				file.printf("\t\t\t// ----- End Task %d -----------------------------------------------\n\n", t);
				t++;
			}
			if (returnType == null)
			{
				file.print(
					"\t\t\tif (errors.size() > 0) return errors;\n" +
					"\t\t\tresponse.setParameters(outputParameters);\n" +
					"\t\t\treturn null;\n");
			}
			file.print("\t\t}\n\t\tcatch (Exception e)\n\t\t{\n\t\t\tthrow new CSWrapperException(e);\n\t\t}\n");
			file.print("\t}\n\n");
		}
	}

	public void writeInterfaceMethods(PrintWriter file)
	{
		if (methodTasks.size() == 0)
			return;
		
		for (String methodName : methodTasks.keySet())
		{
			// The returnType is used to turn *off* code generation practically: it signifies this is
			// not a regular bean method for communicating with the delegates. However, all exceptions
			// are stuffed into CSWrapperExceptions nonetheless.
			String returnType = methodProps.get(methodName).get("returnType");
			if (returnType != null)
				continue;

			List<TaskGenerator> tasks = methodTasks.get(methodName);
			file.printf(
				"\t/*************************************************************************\n" + 
				"\t * Method: \"%s\", v%s (%d task%s)\n", 
				methodProps.get(methodName).get("displayName"),
				methodProps.get(methodName).get("version"),
				tasks.size(),
				(tasks.size() > 1 ? "s" : ""));
			int t = 1;
			for (TaskGenerator task : tasks)
			{
				String taskOperation = "Undetermined";
				if (task.operation instanceof ReadOperationGenerator)
					taskOperation = "Read";
				else if (task.operation instanceof CreateOperationGenerator)
					taskOperation = "Create";
				else if (task.operation instanceof UpdateDeleteOperationGenerator)
					taskOperation = "Update";
				else if (task.cuopCodeReferences != null)
					taskOperation = "Custom code";
				file.printf("\t * Task %d: %s\n", t, taskOperation);
				for (ParameterGenerator input : task.inputParameters)
				{
					file.printf("\t *   Input: %s %s (%s)\n", input.type, input.displayName, 
						input.userVarName);
				}
				for (ParameterGenerator input : task.outputParameters)
				{
					file.printf("\t *   Output: %s %s (%s)\n", input.type, input.displayName, 
						input.userVarName);
				}
				t++;
			}
			file.print(
				"\t *************************************************************************/\n");
			file.printf("\tpublic List<ErrorReport> %s(ActionRequest request, ActionResponse response)\n" +
				"\t\tthrows CSWrapperException;\n\n", methodName);
		}
	}
}
