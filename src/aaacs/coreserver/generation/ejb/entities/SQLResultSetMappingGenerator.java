package aaacs.coreserver.generation.ejb.entities;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;

/**
 * @author Abu Abd-Allah
 * The main EntityGenerator class was getting too big... should have split it up
 * in this fashion from the beginning.
 */
/*
<sqlResultSetMappings>
	<standardMapping/>
	<sqlResultSetMapping name="blahblah">
		<entities>
			<entity class="blahblah">
				<fields>
					<field name="blah" column="blah"/>
					<field name="blah" column="blah"/>
					<field name="blah" column="blah"/>
				</fields>
			</entity>
			<entity class="blahblah">
				<fields>
					<field name="blah" column="blah"/>
					<field name="blah" column="blah"/>
					<field name="blah" column="blah"/>
				</fields>
			</entity>
		</entities>
		<columns>
			<column name="blah"/>
			<column name="blah"/>
		</columns>
	</sqlResultSetMapping>
	<sqlResultSetMapping name="blahblah">
		<columns>
			<column name="blah"/>
			<column name="blah"/>
		</columns>
	</sqlResultSetMapping>
</sqlResultSetMappings>
 */
public class SQLResultSetMappingGenerator extends Generator
{
	private Vector<String> mappingNames = new Vector<String>();

	private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> entityMappings =
		new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>();

	private LinkedHashMap<String, Vector<String>> columnsMappings =
		new LinkedHashMap<String, Vector<String>>();

	public SQLResultSetMappingGenerator(Node rootNode)
		throws GenerationException
	{
		extractMappings(rootNode);
	}
	
	public boolean hasMappings()
	{
		return mappingNames.size() > 0;
	}

	private void extractEntities(String mappingName, Node entitiesNode)
		throws GenerationException
	{
		if (entitiesNode == null) return;
		if (!entitiesNode.getNodeName().equals("entities"))
			throw new GenerationException("Expected an \"entities\" element!" +
				" (found: " + entitiesNode.getNodeName() + ")");
		
		NodeList entityNodes = entitiesNode.getChildNodes();
		LinkedHashMap<String, LinkedHashMap<String,String>> classMappings =
			new LinkedHashMap<String, LinkedHashMap<String,String>>();
		for (int i=0; i<entityNodes.getLength(); i++)
		{
			Node entityNode = entityNodes.item(i);
			String classAttr = Utilities.getNodeAttribute(entityNode, "class");
			
			Node fieldsNode = entityNode.getFirstChild();
			NodeList fieldNodes = fieldsNode.getChildNodes();
			LinkedHashMap<String, String> fieldMappings = new LinkedHashMap<String, String>();
			for (int j=0; j<fieldNodes.getLength(); j++)
			{
				Node fieldNode = fieldNodes.item(j);
				String name = Utilities.getNodeAttribute(fieldNode, "name");
				String column = Utilities.getNodeAttribute(fieldNode, "column");
				fieldMappings.put(name, column);
			}
			classMappings.put(classAttr, fieldMappings);
		}
		entityMappings.put(mappingName, classMappings);
	}
	
	private void extractColumns(String mappingName, Node columnsNode)
		throws GenerationException
	{
		if (columnsNode == null) return;
		if (!columnsNode.getNodeName().equals("columns"))
			throw new GenerationException("Expected a \"columns\" element!" +
				" (found: " + columnsNode.getNodeName() + ")");		

		NodeList columnNodes = columnsNode.getChildNodes();
		Vector<String> columnMappings = new Vector<String>();

		for (int i=0; i<columnNodes.getLength(); i++)
		{
			Node columnNode = columnNodes.item(i);
			String name = Utilities.getNodeAttribute(columnNode, "name");
			columnMappings.add(name);
		}
		columnsMappings.put(mappingName, columnMappings);
	}

	private void extractMappings(Node rootNode)
		throws GenerationException
	{
		if (!rootNode.getNodeName().equals("sqlResultSetMappings"))
			throw new GenerationException("Expected a \"sqlResultSetMappings\" element!" +
				" (found: " + rootNode.getNodeName() + ")");
	
		NodeList mappingNodes = rootNode.getChildNodes();
		for (int i=0; i<mappingNodes.getLength(); i++)
		{
			Node mappingNode = mappingNodes.item(i);

			if (mappingNode.getNodeName().equals("standardMapping"))
			{
				
			}
			else if (mappingNode.getNodeName().equals("sqlResultSetMapping"))
			{
				String mappingName = Utilities.getNodeAttribute(mappingNode, "name");
				mappingNames.add(mappingName);
				extractEntities(mappingName, Utilities.goToNode(mappingNode, "entities"));
				extractColumns(mappingName, Utilities.goToNode(mappingNode, "columns"));
			}
		}
	}
		
	public void writeMappings(PrintWriter file)
	{
		if (mappingNames.size() == 0)
			return;

		boolean foundOneMapping = false;
		for (String mappingName : mappingNames)
		{
			if (foundOneMapping)
				file.print(",\n");
			LinkedHashMap<String, LinkedHashMap<String,String>> classMappings =
				entityMappings.get(mappingName);
			Vector<String> columnMappings = columnsMappings.get(mappingName);
			
			file.printf("\t\t@SqlResultSetMapping\n\t\t(\n\t\t\tname = \"%s\",\n",
				mappingName);
			if (classMappings != null)
			{
				file.printf("\t\t\tentities =\n\t\t\t{\n");
				boolean foundOneClassMapping = false;
				for (String classAttr : classMappings.keySet())
				{
					if (foundOneClassMapping)
						file.print(",\n");
					file.printf("\t\t\t\t@EntityResult\n\t\t\t\t(\n" +
					"\t\t\t\t\tentityClass = %s,\n" +
					"\t\t\t\t\tfields = \n\t\t\t\t\t{\n", classAttr);
					
					LinkedHashMap<String, String> fieldMappings = 
						classMappings.get(classAttr);
					boolean foundOneFieldMapping = false;
					for (String name : fieldMappings.keySet())
					{
						if (foundOneFieldMapping)
							file.print(",\n");
						file.printf("\t\t\t\t\t\t@FieldResult(name = \"%s\", column = \"%s\")",
							name, fieldMappings.get(name));
						foundOneFieldMapping = true;
					}
					file.print("\n\t\t\t\t\t}\n\t\t\t\t)");
					foundOneClassMapping = true;
				}
				file.print("\n\t\t\t}");
				if (columnMappings != null)
					file.print(",\n");
				else
					file.print("\n");
			}
			if (columnMappings != null)
			{
				file.print("\t\t\tcolumns = \n\t\t\t{\n");
				boolean foundOneColumn = false;
				for (String column : columnMappings)
				{
					if (foundOneColumn)
						file.print(",\n");
					file.printf("\t\t\t\t@ColumnResult (name = \"%s\")", column);
					foundOneColumn = true;
				}
				file.print("\n\t\t\t}\n");
			}
			file.print("\t\t)");
			foundOneMapping = true;
		}
		file.print("\n");
	}
}
