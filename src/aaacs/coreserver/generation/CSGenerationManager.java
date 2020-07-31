package aaacs.coreserver.generation;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import aaacs.coreserver.commons.generation.GenerationException;
import aaacs.coreserver.commons.generation.Generator;
import aaacs.coreserver.commons.generation.Utilities;
import aaacs.coreserver.generation.database.SQLTableGenerator;
import aaacs.coreserver.generation.dtos.DTOGenerator;
import aaacs.coreserver.generation.ejb.beans.BeanGenerator;
import aaacs.coreserver.generation.ejb.entities.EntityGenerator;

/**
 * @author Ahmed A. Abd-Allah, Nov 7, 2006
 * 
 * Main class for generating code for the Core Server.
 */
public class CSGenerationManager extends Generator
{
	public static Properties configuration = new Properties();
	public static LinkedHashMap<String, SQLTableGenerator> tableGenerators = 
		new LinkedHashMap<String, SQLTableGenerator>();
	public static LinkedHashMap<String, EntityGenerator> entityGenerators = 
		new LinkedHashMap<String, EntityGenerator>();
	public static LinkedHashMap<String, BeanGenerator> beanGenerators = 
		new LinkedHashMap<String, BeanGenerator>();

	public static void loadCoreServerBean(Node rootNode)
		throws GenerationException
	{
		try
		{
			if (!rootNode.getNodeName().equals("coreServerBean"))
				throw new GenerationException("Expected a \"coreServerBean\" element!");
	
			BeanGenerator beanGenerator =
				new BeanGenerator(
					configuration.getProperty("javaBeanDefaultPackage"), 
					configuration.getProperty("javaBeanDefaultImports"), 
					rootNode);
	
			beanGenerators.put(beanGenerator.getClassName(), beanGenerator);
		}
		catch (Exception e)
		{
			throw new GenerationException(e);
		}
	}
	
	public static void loadCoreServerEntity(int counter, Node rootNode)
		throws GenerationException
	{
		try
		{
			if (!rootNode.getNodeName().equals("coreServerEntity"))
				throw new GenerationException("Expected a \"coreServerEntity\" element!");

			SQLTableGenerator tableGenerator =
				new SQLTableGenerator(counter, rootNode);
			EntityGenerator classGenerator =
				new EntityGenerator(
					configuration.getProperty("javaEntityDefaultResourcesFile"),
					configuration.getProperty("javaEntityDefaultPackage"), 
					configuration.getProperty("javaEntityDefaultImports"), 
					rootNode);

			tableGenerators.put(tableGenerator.getTableName(), tableGenerator);
			entityGenerators.put(classGenerator.getClassName(), classGenerator);
		}
		catch (Exception e)
		{
			throw new GenerationException(e);
		}
	}

	public static void loadEntityFile(int counter, String entityFileName)
		throws GenerationException
	{
		File inputFile = new File(configuration.getProperty("xmlEntitiesDirectory").trim() + 
			"/" + entityFileName);
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
			loadCoreServerEntity(counter, rootNode);
		}
		else
			throw new GenerationException("Invalid XML entity file: " + inputFile.getName());
	}

	public static void loadBeanFile(String beanFileName)
		throws GenerationException
	{
		File inputFile = new File(configuration.getProperty("xmlBeansDirectory").trim() + "/" + beanFileName);
		if (inputFile == null || !inputFile.exists())
		{
			throw new GenerationException("File not found: " + inputFile.getAbsoluteFile());
		}
		Document document = validateFile(inputFile);

		if (document != null)
		{
			Node rootNode = document.getFirstChild();
			// traversePreOrder(rootNode);
			stripEmptyTextNodes(rootNode);
			// traversePreOrder(rootNode);
			loadCoreServerBean(rootNode);
		}
		else
			throw new GenerationException("Invalid XML bean file: " + inputFile.getName());
	}

	/*
	 * This method is full of holes and needs to be cleaned up: entities and beans
	 * can specify particular locations. Best to eliminate this method, and move deletion
	 * to local points.
	 */
	public static void initializeFiles(String[] filesToDelete)
	{
		boolean generateDatabase = Boolean.parseBoolean(configuration.getProperty("generateDatabase"));
		boolean generateEntities = Boolean.parseBoolean(configuration.getProperty("generateEntities"));
		boolean generateBeans = Boolean.parseBoolean(configuration.getProperty("generateBeans"));
		boolean generateDTOs = Boolean.parseBoolean(configuration.getProperty("generateDTOs"));
		boolean generateSearchDTOs = Boolean.parseBoolean(configuration.getProperty("generateSearchDTOs"));

		String outputDatabaseDirectory = 
			configuration.getProperty("outputDatabaseDirectory");
		String outputCreateDatabaseFile = 
			configuration.getProperty("outputCreateDatabaseFile");

		if (generateDatabase)
		{
			// delete the existing SQL script for creating the database
			if (outputCreateDatabaseFile != null && outputDatabaseDirectory != null)
				new File(outputDatabaseDirectory + "/" + outputCreateDatabaseFile).delete();
		}

		if (generateEntities)
		{
			// delete all the existing Java entity files
			String outputJavaEntitiesDirectory = 
				configuration.getProperty("outputJavaEntitiesDirectory");
			String[] xmlEntityFiles = 
				configuration.getProperty("xmlEntityFiles").trim().replaceAll("\\s+", " ").split(" ");			

			for (String xmlEntityFile : xmlEntityFiles)
			{
				xmlEntityFile = xmlEntityFile.replaceAll("xml$", "java");
				File f = new File(outputJavaEntitiesDirectory + "/" +
					xmlEntityFile);
				if (f.exists())
				{
					System.out.println("Deleting: " + f.getName());
					f.delete();
				}
			}
			/*
			File[] javaEntityFiles = (new File(outputJavaEntitiesDirectory)).listFiles();
			for (File javaEntityFile : javaEntityFiles)
			{
				javaEntityFile.delete();
			}
			*/
			File resFile = new File("src/aaacs/coreserver/resources/entityNames.properties");
			resFile.delete();
			resFile = new File("src/aaacs/rex/resources/entityNames.properties");
			resFile.delete();
		}

		if (generateBeans)
		{
			// delete all the existing Java bean files
			String outputJavaBeansDirectory = 
				configuration.getProperty("outputJavaBeansDirectory");
			String[] xmlBeanFiles = 
				configuration.getProperty("xmlBeanFiles").trim().replaceAll("\\s+", " ").split(" ");			
			
			for (String xmlBeanFile : xmlBeanFiles)
			{
				xmlBeanFile = xmlBeanFile.replaceAll("xml$", "java");
				File f = new File(outputJavaBeansDirectory + "/" +
					xmlBeanFile);
				if (f.exists())
				{
					System.out.println("Deleting: " + f.getName());
					f.delete();
				}
			}
			/*
			File[] javaBeanFiles = (new File(outputJavaBeansDirectory)).listFiles();
			for (File javaBeanFile : javaBeanFiles)
			{
				if (!javaBeanFile.isDirectory())
					javaBeanFile.delete();
			}
			*/
		}
		
		if (generateDTOs)
		{
			// delete all the existing DTOs except those to ignore explicitly
			String outputDTODirectory = configuration.getProperty("outputJavaDTOsDirectory");
			String filesToIgnore[] = 
				configuration.getProperty("permanentDTOFiles").trim().replaceAll("\\s+", " ").split(" ");
			Utilities.deleteAllFiles( new File(outputDTODirectory), true, true, filesToIgnore);
		}

		if (generateSearchDTOs)
		{
			// delete all the existing Search DTOs except those to ignore explicitly
			String outputSearchDTODirectory = configuration.getProperty("outputJavaSearchDTOsDirectory");
			String filesToIgnore[] = 
				configuration.getProperty("permanentSearchDTOFiles").trim().replaceAll("\\s+", " ").split(" ");
			Utilities.deleteAllFiles( new File(outputSearchDTODirectory), true, true, filesToIgnore);
		}
	}
	
	public static void littleTest()
	{
		try
		{
			//System.out.print(Utilities.sqlComment("line 1", 0));
			//Document document = validateFile(new File("conf/generation/descriptors/databaseMain.xml"));
			/*
			LinkedHashMap<String, String> columnValues = 
				Utilities.splitColumnValues("name 'A'\n\tbao 'B'\n\tcIDK cValue");
			for (String key : columnValues.keySet())
			{
				System.out.println("Column: [" + key + "] --> Value: [" + columnValues.get(key) + "]");
			}
			if ("abc".matches("^[\\w\\.@\\?\\$%&#]+$"))
			//if ("a".matches("^[\\w\\.]+$|^[\\w\\.]+@[\\w\\.]+\\.(com|net|org|info)(\\.[a-z][a-z])*$"))
				System.out.println("Yup");
			else
				System.out.println("Nope");
			*/
		}
		catch (Exception e)
		{
			System.out.println("Little Test Failure");
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		try
		{
			if (false)
			{
				return;
			}
			configuration.loadFromXML(new FileInputStream("conf/generation/generationConfiguration.xml"));
			String[] filesToDelete = 
				configuration.getProperty("initialFilesToDelete").trim().replaceAll("\\s+", " ").split(" ");
			initializeFiles(filesToDelete);

			boolean generateDatabase = Boolean.parseBoolean(configuration.getProperty("generateDatabase"));
			boolean generateEntities = Boolean.parseBoolean(configuration.getProperty("generateEntities"));
			boolean generateBeans = Boolean.parseBoolean(configuration.getProperty("generateBeans"));
			boolean generateDTOs = Boolean.parseBoolean(configuration.getProperty("generateDTOs"));
			boolean generateSearchDTOs = Boolean.parseBoolean(configuration.getProperty("generateSearchDTOs"));

			if (generateDatabase || generateEntities)
			{
				int entityCounter = Integer.parseInt(configuration.getProperty("entityStartingCounter"));
				int entityCounterIncrement = Integer.parseInt(configuration.getProperty("entityCounterIncrement"));
				
				String[] xmlEntityFiles = 
					configuration.getProperty("xmlEntityFiles").trim().replaceAll("\\s+", " ").split(" ");			
	
				int counter = 0;
				for (String xmlEntityFile : xmlEntityFiles)
				{
					System.out.printf("%d. Loading %s ...", counter+1, xmlEntityFile);
					loadEntityFile( entityCounter, xmlEntityFile );
					System.out.printf(" done\n");
					entityCounter += entityCounterIncrement;
					counter++;
				}
				System.out.printf("Done loading %d entities.\n\n", counter);
			
				if (generateDatabase)
					SQLTableGenerator.generateDatabaseFiles(
						configuration.getProperty("databaseMainDescriptor"),
						configuration.getProperty("outputDatabaseDirectory"),
						configuration.getProperty("outputCreateDatabaseFile"),
						tableGenerators);

				if (generateEntities)
					EntityGenerator.generateEntities(
						configuration.getProperty("outputJavaEntitiesDirectory"),
						entityGenerators);
			}
			
			if (generateBeans)
			{
				String[] xmlBeanFiles = 
					configuration.getProperty("xmlBeanFiles").trim().replaceAll("\\s+", " ").split(" ");			
				
				int counter = 0;
				for (String xmlBeanFile : xmlBeanFiles)
				{
					System.out.printf("%d. Loading %s ...", counter+1, xmlBeanFile);
					loadBeanFile( xmlBeanFile );
					System.out.printf(" done\n");
					counter++;
				}
				System.out.printf("Done loading %d beans.\n\n", counter);
			
				if (generateBeans)
				{
					File file = new File(configuration.getProperty("dispatcherClassFile"));
					file.delete();
					file = new File(configuration.getProperty("actionPropertiesFile"));
					file.delete();
					BeanGenerator.generateBeans(
						configuration.getProperty("outputJavaBeansDirectory"),
						configuration.getProperty("dispatcherClassFile"),
						configuration.getProperty("dispatcherPackage"),
						configuration.getProperty("actionPropertiesFile"),
						beanGenerators);
				}
			}
			
			if (generateDTOs)
			{
				DTOGenerator.generateDTOs(configuration.getProperty("outputJavaDTOsDirectory"), 
					configuration.getProperty("javaDTODefaultPackage"), 
					configuration.getProperty("javaDTODefaultImports"),
					entityGenerators);
			}
			
			if (generateSearchDTOs)
			{
				DTOGenerator.generateSearchDTOs(configuration.getProperty("outputJavaSearchDTOsDirectory"), 
					configuration.getProperty("javaSearchDTODefaultPackage"), 
					configuration.getProperty("javaSearchDTODefaultImports"),
					entityGenerators);				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
