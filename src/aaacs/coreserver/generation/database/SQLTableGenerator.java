package aaacs.coreserver.generation.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;

import static aaacs.coreserver.commons.generation.Utilities.*;

/**
 * @author Ahmed A. Abd-Allah, Nov 8, 2006
 * 
 * Generates tables from XML descriptors of Core System entities.
 */
public class SQLTableGenerator extends Generator
{
	// ----- Static Members ---------------------------------------------------
	private static Vector<String[]> sequences = new Vector<String[]>();
	private static LinkedHashMap<String, String> comments = 
		new LinkedHashMap<String, String>();

	private static void loadMainDatabaseDescriptor(String mainDatabaseDescriptor)
		throws IOException, GenerationException
	{
		if (mainDatabaseDescriptor == null || mainDatabaseDescriptor.trim().equals(""))
			throw new GenerationException("Must configure location of main database descriptor.");

		File inputFile = new File(mainDatabaseDescriptor);
		if (inputFile == null || !inputFile.exists())
		{
			throw new GenerationException("File not found: " + inputFile.getAbsoluteFile());
		}
		Document document = validateFile(inputFile);

		if (document != null)
		{
			Node rootNode = document.getFirstChild();
			//traversePreOrder(rootNode);
			stripEmptyTextNodes(rootNode);
			//traversePreOrder(rootNode);

			try
			{
				if (!rootNode.getNodeName().equals("databaseMain"))
					throw new GenerationException("Expected a \"databaseMain\" element!");
			
				NodeList children = rootNode.getChildNodes();
				for (int i=0; i<children.getLength(); i++ ) 
				{
					Node child = children.item(i);
					String nodeName = child.getNodeName();
					if (nodeName.equals("sequences"))
					{
						NodeList seqNodes = child.getChildNodes();
						for (int j=0; j<seqNodes.getLength(); j++)
						{
							Node seq = seqNodes.item(j);
							String[] props = new String[4];

							NamedNodeMap map = seq.getAttributes();
							for (int k=0; k<map.getLength(); k++)
							{
								Node attr = map.item(k);
								if (attr.getNodeName().equals("name"))
									props[0] = attr.getNodeValue();
								else if (attr.getNodeName().equals("start"))
									props[1] = attr.getNodeValue();
								else if (attr.getNodeName().equals("increment"))
									props[2] = attr.getNodeValue();
							}
							if (seq.getChildNodes() != null)
								props[3] = sqlComment(seq.getFirstChild().getTextContent(),
									getCommentIndent(seq.getFirstChild()));
							else
								props[3] = null;
							sequences.add(props);
						}
					}
					else if (nodeName.equals("comments"))
					{
						NodeList commentNodes = child.getChildNodes();

						for (int j=0; j<commentNodes.getLength(); j++)
						{
							Node commentNode = commentNodes.item(j);
							int indent = getCommentIndent(commentNode);
							String name = getCommentName(commentNode);
							String comment = sqlComment(commentNode.getFirstChild().getTextContent(),
								indent);
							comments.put(name, comment);
						}
					}
				}
			}
			catch (Exception e)
			{
				throw new GenerationException(e);
			}
		}
		else
			throw new GenerationException("Invalid XML database main descriptor: " + inputFile.getName());
	}

	public static void generateDatabaseFiles(String mainDatabaseDescriptor,
		String outputDirectory, String outputCreateDatabaseFile,
		LinkedHashMap<String, SQLTableGenerator> tableGenerators)
		throws IOException, GenerationException
	{
		if (mainDatabaseDescriptor == null || mainDatabaseDescriptor.trim().equals(""))
			throw new GenerationException("Must configure location of main database descriptor.");

		loadMainDatabaseDescriptor(mainDatabaseDescriptor);
		PrintWriter file = 
			new PrintWriter(new FileWriter(outputDirectory + "/" + outputCreateDatabaseFile, 
				false));
		
		if (comments.containsKey("createDatabaseFileHeaderComment"))
			file.printf(comments.get("createDatabaseFileHeaderComment") + "\n");
		file.println();

		file.printf(
			"----- Begin transaction for deletion actions ----------------------------------\n" +
			"--BEGIN TRANSACTION;\n" +
			"--SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;\n\n");

		file.printf(
			"----- Delete tables -----------------------------------------------------------\n");
		String dropTablesQueries = "";
		for (String tableName : tableGenerators.keySet())
			dropTablesQueries = "DROP TABLE \"" + tableName + "\" CASCADE;\n" + dropTablesQueries;
		file.printf(dropTablesQueries + "\n");

		if (sequences.size() > 0)
		{
			file.printf(
				"----- Delete sequences --------------------------------------------------------\n");
			for (int i=sequences.size()-1; i>=0; i--)
			{
				file.printf("DROP SEQUENCE \"%s\" CASCADE;\n", sequences.get(i)[0]);
			}
		}
		
		file.printf("\n--COMMIT TRANSACTION;\n\n");

		file.printf(
			"----- Begin transaction for creation actions ----------------------------------\n" +
			"BEGIN TRANSACTION;\n" +
			"SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;\n");

		if (sequences.size() > 0)
		{
			file.printf(
				"----- Create sequences --------------------------------------------------------\n");
			for (String seq[] : sequences)
			{
				if (seq[3] != null)
					file.printf(seq[3] + "\n");
				file.printf("CREATE SEQUENCE \"%s\" START %s INCREMENT %s;\n", 
					seq[0], seq[1], seq[2]);
			}
			file.println();
		}
		
		file.printf(
			"----- Create tables -----------------------------------------------------------\n");
		for (String tableName : tableGenerators.keySet())
		{
			SQLTableGenerator tableGenerator = tableGenerators.get(tableName);
			System.out.printf("Generating CREATE TABLE for %s ...", tableName);			
			tableGenerator.writeTable(file);
			System.out.printf(" done\n");
		}

		file.printf("COMMIT TRANSACTION;\n\n\n");

		file.printf(
			"----- Begin transaction for populating tables ---------------------------------\n" +
			"BEGIN TRANSACTION;\n" +
			"SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;\n\n");
		file.printf(
			"----- Prepare to store some temporary values ----------------------------------\n" +
			"CREATE TABLE t_tempo\n(\n\tname VARCHAR(300) PRIMARY KEY,\n\tvalue INT8\n);\n" +
			"CREATE OR REPLACE FUNCTION setValue(VARCHAR(300), INT8) returns VARCHAR(300) AS\n" +
			"'insert into t_tempo (name, value) values ($1, $2);\n" +
			"select $1 as ignored_return_value;' LANGUAGE SQL;\n" +
			"CREATE OR REPLACE FUNCTION getValue(VARCHAR(300)) returns INT8 AS\n" +
			"'select value from t_tempo where name = $1;' LANGUAGE SQL;\n");

		for (String tableName : tableGenerators.keySet())
		{
			SQLTableGenerator tableGenerator = tableGenerators.get(tableName);
			System.out.printf("Generating INSERTs for %s ...", tableName);
			if (tableGenerator.inserts.size() > 0)
				file.printf("\n----- Populate " + tableName + " -----\n");

			for (LinkedHashMap<String,String> insColumnValues : tableGenerator.inserts)
			{
				int size = insColumnValues.size();
				if (insColumnValues.containsKey("__COMMENT__"))
				{
					file.printf(insColumnValues.get("__COMMENT__") + "\n");
					size--; // real size
				}
				if (insColumnValues.containsKey("__STORE__"))
					size--;
				String columns = "";
				String values = "";
				int i=0;
				for (String column : insColumnValues.keySet())
				{
					if (column.equals("__COMMENT__") || column.equals("__STORE__"))
						continue;
					String value = insColumnValues.get(column);
					if (value.startsWith("'FKREF:"))
						value = value.replaceFirst("'FKREF:", "getValue('").concat(")");
					columns += '"' + column + '"' + (i < size-1 ? ", " : "");
					values += value + (i < size-1 ? ", " : "");
					i++;
				}
				if (columns.length() > 0)
				file.printf("INSERT INTO \"%s\" (%s)\n\tvalues (%s);\n", 
					tableName, columns, values);
				if (insColumnValues.containsKey("__STORE__"))
				{
					file.printf("SELECT setValue('%s', CAST (currval('qIDK') AS INT8));\n",
						insColumnValues.get("__STORE__"));
				}
			}
			System.out.printf(" done\n");
		}

		file.printf(
			"\n----- Remove all temporary values ---------------------------------------------\n" +
			"DROP FUNCTION getValue(VARCHAR(100));\nDROP FUNCTION setValue(VARCHAR(100), INT8);\n" +
			"DROP TABLE t_tempo;\n\n");

		file.printf(
			"----- Insert any circular foreign key constraints now -------------------------\n");

		for (String tableName : tableGenerators.keySet())
		{
			SQLTableGenerator tableGenerator = tableGenerators.get(tableName);
			tableGenerator.writeReverseForeignKeys(file);
		}

		file.printf("COMMIT TRANSACTION;\n\n");
		
		file.printf("VACUUM FULL ANALYZE;\n");

		file.close();
		System.out.printf("Done generating SQL.\n\n");
	}

	// ----- Instance Members -------------------------------------------------
	/**
	 * Information extracted from the passed in nodes.
	 */
	enum TableConstraint { UNIQUE, FOREIGNKEY };
	private String tableName = null;
	private String tableComment = null;
	private Vector<String> uniqueColumns = new Vector<String>();
	private Vector<String[]> foreignKeys = new Vector<String[]>();
	private Vector<String[]> reverseForeignKeys = new Vector<String[]>();
	private LinkedHashMap<String, LinkedHashMap<String, Object>> attributes =
		new LinkedHashMap<String, LinkedHashMap<String, Object>>();
	private Vector<LinkedHashMap<String, String>> inserts = 
		new Vector<LinkedHashMap<String, String>>();
	
	/**
	 * Information passed into the constructor.
	 */
	private int tableCounter = 0;

	public SQLTableGenerator(int counter, Node rootNode)
		throws GenerationException
	{
		if (rootNode == null)
			throw new GenerationException("Element for entity root node should not be null");
		
		Node tableNode = goToNode(rootNode, "table");
		Node attributesNode = goToNode(rootNode, "attributes");
		Node populateNode = goToNode(rootNode, "populate");
		if (tableNode == null)
			throw new GenerationException("Element for \"table\" should not be null");
		if (attributesNode == null)
			throw new GenerationException("Element for \"attributes\" should not be null");

		tableCounter = counter;

		extractTableInformation(tableNode);
		extractAttributesInformation(attributesNode);
		if (populateNode != null)
			extractPopulateInformation(populateNode);
	}

	public String getTableName()
	{
		return tableName;
	}

	protected void setTableName(String tableName)
	{
		this.tableName = tableName;
	}
	
	private void extractTableConstraints(Node node)
		throws GenerationException
	{
		NodeList children = node.getChildNodes();

		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();

			if (childName.equals("unique"))
			{
				uniqueColumns.add(child.getFirstChild().getTextContent());
			}
			else if (childName.equals("foreignKey"))
			{
				String[] fk = new String[3];
				// referringColumns
				fk[0] = child.getFirstChild().getFirstChild().getTextContent();
				// referredTable
				fk[1] = child.getFirstChild().getNextSibling().getFirstChild().getTextContent();
				// referredColumns
				fk[2] = child.getLastChild().getTextContent();
				foreignKeys.add(fk);
			}
			else if (childName.equals("reverseForeignKey"))
			{
				String[] rfk = new String[3];
				// referringTable
				rfk[0] = child.getFirstChild().getFirstChild().getTextContent();
				// referringColumns
				rfk[1] = child.getFirstChild().getNextSibling().getFirstChild().getTextContent();
				// referredColumns
				rfk[2] = child.getLastChild().getTextContent();
				reverseForeignKeys.add(rfk);
			}
			else
				throw new GenerationException("Unknown constraint: " + childName);
		}
	}

	private void extractTableInformation(Node tableNode)
		throws GenerationException
	{
		if (!tableNode.getNodeName().equals("table"))
			throw new GenerationException("Expected a \"table\" element!");

		NodeList children = tableNode.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();

			// Get table name
			if (childName.equals("name"))
				tableName = child.getTextContent();
			else if (childName.equals("constraints"))
			{
				extractTableConstraints(child);
			}
			else if (childName.equals("comment"))
			{
				tableComment = sqlComment(child.getFirstChild().getTextContent(), 
					getCommentIndent(child));				
			}
			else
				throw new GenerationException("Unknown child of \"table\" element");
		}
	}

	private void extractAttribute(Node node)
		throws GenerationException
	{
		if (!node.getNodeName().equals("attribute"))
			throw new GenerationException("Expected an \"attribute\" element!");
	
		String attributeName = null;
		LinkedHashMap<String, Object> attributeProps = new LinkedHashMap<String, Object>();
		
		NodeList children = node.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();
	
			if (childName.equals("columnDescription"))
			{
				NamedNodeMap map = child.getAttributes();
				for (int j=0; j<map.getLength(); j++)
				{
					Node attr = map.item(j);
					if (attr.getNodeName().equals("name"))
						attributeName = attr.getNodeValue(); 
					else if (attr.getNodeName().equals("type"))
						attributeProps.put("type", attr.getNodeValue());
					//System.out.println(attr.getNodeName() + " " + attr.getNodeValue());
				}

				NodeList cdChildren = child.getChildNodes();
				for (int j=0; j<cdChildren.getLength(); j++ ) 
				{
					Node cdChild = cdChildren.item(j);
					String cdChildName = cdChild.getNodeName();
					
					if (cdChildName.equals("default"))
						attributeProps.put("default", 
							cdChild.getFirstChild().getTextContent());
					else if (cdChildName.equals("primaryKey"))
						attributeProps.put("primaryKey", true);
					else if (cdChildName.equals("notNull"))
						attributeProps.put("notNull", true);
					else if (cdChildName.equals("unique"))
						attributeProps.put("unique", true);
					else if (cdChildName.equals("comment"))
					{
						attributeProps.put("comment", 
							sqlComment(cdChild.getFirstChild().getTextContent(), 
							getCommentIndent(cdChild)));
					}
				}
			}
			else if (childName.equals("javaFieldDescription") ||
				childName.equals("searchCriteria"))
			{
				// ignore.
			}
			else
				throw new GenerationException("Unknown child of \"attribute\" element");
		}
		attributes.put(attributeName, attributeProps);
	}

	private void extractAttributesInformation(Node attributesNode)
		throws GenerationException
	{
		if (!attributesNode.getNodeName().equals("attributes"))
			throw new GenerationException("Expected an \"attributes\" element!");
	
		NodeList children = attributesNode.getChildNodes();
		
		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();
	
			if (childName.equals("attribute"))
				extractAttribute(child);
			else if (childName.equals("includeAttributes"))
			{
				if (getNodeAttribute(child, "useInSQL").equals("true"))
				{
					Document document = 
						validateFile(new File(child.getFirstChild().getTextContent()));
					if (document != null)
					{
						stripEmptyTextNodes(document.getFirstChild());
						extractAttributesInformation(document.getFirstChild());
					}
					else
						throw new GenerationException("Unable to load included attributes");
				}
			}
			else
				throw new GenerationException("Unknown child of \"attributes\" element: " +
					childName);
		}
	}

	private void extractPopulateInformation(Node populateNode)
		throws GenerationException
	{
		if (!populateNode.getNodeName().equals("populate"))
			throw new GenerationException("Expected a \"populate\" element!");
	
		NodeList children = populateNode.getChildNodes();
		LinkedHashMap<String, String> defColumnValues = null;
		LinkedHashMap<String, String> insColumnValues = null;
		for (int i=0; i<children.getLength(); i++ ) 
		{
			Node child = children.item(i);
			String childName = child.getNodeName();

			if (childName.equals("defaults"))
			{
				NodeList defChildren = child.getChildNodes();
				for (int j=0; j<defChildren.getLength(); j++)
				{
					Node defChild = defChildren.item(j);
					String defChildName = defChild.getNodeName();
					
					if (defChildName.equals("columnValues"))
					{
						defColumnValues =
							splitColumnValues(defChild.getFirstChild().getTextContent());
					}
				}
			}
			else if (childName.equals("insert"))
			{
				String insComment = null;
				String insStore = null;
				
				NodeList insChildren = child.getChildNodes();
				for (int j=0; j<insChildren.getLength(); j++)
				{
					Node insChild = insChildren.item(j);
					String insChildName = insChild.getNodeName();

					if (insChildName.equals("columnValues"))
					{
						insColumnValues =
							splitColumnValues(insChild.getFirstChild().getTextContent());
					}
					else if (insChildName.equals("comment"))
					{
						insComment =
							sqlComment(insChild.getFirstChild().getTextContent(), 
								getCommentIndent(insChild));
					}
					else if (insChildName.equals("store"))
					{
						insStore = insChild.getFirstChild().getTextContent();
					}
				}
				if (insComment != null)
					insColumnValues.put("__COMMENT__", insComment);
				if (insStore != null)
					insColumnValues.put("__STORE__", insStore);
				for (String defKey : defColumnValues.keySet())
				{ // stuff the insert with default values if missing
					if (!insColumnValues.containsKey(defKey))
						insColumnValues.put(defKey, defColumnValues.get(defKey));
				}
				inserts.add(insColumnValues);
			}
			else
				throw new GenerationException("Unknown child of \"populate\" element");
		}
	}

	public void writeTable(PrintWriter file)
		throws IOException, GenerationException
	{
		int i, size;
		
		// Table header
		if (tableComment != null)
			file.printf("%s\n", tableComment);
		file.printf("CREATE TABLE \"%s\"\n(\n", tableName);

		// Table attributes
		i = 0;
		size = attributes.size();
		file.printf("\t-- Columns\n");
		for (String attrName : attributes.keySet())
		{
			if (i != 0)
				file.printf(",\n");
				
			LinkedHashMap<String, Object> attrProps = attributes.get(attrName);

			if (attrProps.containsKey("comment"))
			{
				file.printf("%s\n", attrProps.get("comment"));
			}
			file.printf("\t\"%s\" %s", attrName, attrProps.get("type"));
			if (attrProps.containsKey("primaryKey"))
				file.printf(" PRIMARY KEY");
			else 
			{
				if (attrProps.containsKey("notNull"))
					file.printf(" NOT NULL");
				if (attrProps.containsKey("unique"))
					file.printf(" UNIQUE");
			}
			if (attrProps.containsKey("default"))
				file.printf(" DEFAULT %s", attrProps.get("default"));
			i++;
		}
		
		// Table uniqueness constraints
		i = 0;
		size = uniqueColumns.size();
		if (size > 0)
			file.printf(",\n\n\t-- Uniqueness Constraints\n");
		for (String columnGroup : uniqueColumns)
		{
			if (i != 0) 
				file.printf(",\n"); 

			file.printf("\tCONSTRAINT \"uq%03d%s\" UNIQUE(%s)", tableCounter,
				packStringList(columnGroup), 
				whitespaceToCommas( quoteSubStrings(columnGroup) ));
			i++;
		}

		// Table foreign key constraints
		i = 0;
		size = foreignKeys.size();
		if (size > 0)
			file.printf(",\n\n\t-- Foreign Key References\n");
		for (String[] foreignKeyGroup : foreignKeys)
		{
			if (i != 0) 
				file.printf(",\n"); 

			file.printf("\tCONSTRAINT \"fk%03d%s\" FOREIGN KEY (%s) REFERENCES \"%s\"(%s)",
				tableCounter, packStringList(foreignKeyGroup[0]),
				whitespaceToCommas( quoteSubStrings(foreignKeyGroup[0]) ), 
				foreignKeyGroup[1], 
				whitespaceToCommas( quoteSubStrings(foreignKeyGroup[2])) );
			i++;
		}

		// Table footer
		file.printf("\n);\n\n");
	}
	
	public void writeReverseForeignKeys(PrintWriter file)
		throws IOException, GenerationException
	{
		int size;
	
		// Reverse foreign key constraints
		size = reverseForeignKeys.size();
		if (size > 0)
		{
			for (String[] reverseForeignKeyGroup : reverseForeignKeys)
			{
				//ALTER TABLE tOrganizations ADD CONSTRAINT fk120MainContactIDK FOREIGN KEY (mainContactIDK) REFERENCES tUsers(IDK);

				file.printf("ALTER TABLE \"%s\" ADD CONSTRAINT \"fk%03d%s\" FOREIGN KEY (%s) " +
					"REFERENCES \"%s\"(%s);\n", 
					reverseForeignKeyGroup[0], tableCounter,
					packStringList(reverseForeignKeyGroup[1]),
					whitespaceToCommas( quoteSubStrings(reverseForeignKeyGroup[1]) ),
					tableName, whitespaceToCommas( quoteSubStrings(reverseForeignKeyGroup[2])) );
			}
			file.println();
		}
	}
}
