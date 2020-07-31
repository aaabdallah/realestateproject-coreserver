package aaacs.coreserver.generation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRegexParser
{
	public static LinkedHashMap<String, String> scratchPad = 
		new LinkedHashMap<String, String>();
	public static int fileCounter = 0;
	public static int changeCounter = 0;

	public static long getFileSize(String fileName)
	{
		File file = new File(fileName);
		return file.length();
	}

	public static String processOneLine_PRODUCESAMELINE(String oneLine)
	{
		try
		{
			return oneLine;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	// other processOneLine's are found commented after the class
	public static String processOneLine(String oneLine)
	{
		String result = oneLine;

		try
		{
			Pattern pattern = Pattern.compile("^\\s*IDK '101'\\s*$");
			Matcher matcher = pattern.matcher(oneLine);

			if (matcher.find())
			{
				result =
					"\t\t\tIDK '101'\n\t\t\t</columnValues>\n\t\t</insert>\n" +
					"\t\t<insert>\n\t\t\t<columnValues>\n\t\t\tIDK '102'\n\t\t\t</columnValues>\n\t\t</insert>\n" +
					"\t\t<insert>\n\t\t\t<columnValues>\n\t\t\tIDK '103'\n\t\t\t</columnValues>\n\t\t</insert>\n" +
					"\t\t<insert>\n\t\t\t<columnValues>\n\t\t\tIDK '901'\n\t\t\t</columnValues>\n\t\t</insert>\n" +
					"\t\t<insert>\n\t\t\t<columnValues>\n\t\t\tIDK '902'\n\t\t\t</columnValues>\n\t\t</insert>\n" +
					"\t\t<insert>\n\t\t\t<columnValues>\n\t\t\tIDK '903'";
				changeCounter++;
			}

			return result;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void processFile(File inputFile, File outputFile)
	{
		String inputFileName = inputFile.getAbsolutePath();
		String outputFileName = outputFile.getAbsolutePath();
		try
		{
			BufferedReader inFile = new BufferedReader(new FileReader(inputFileName));
			PrintWriter outFile;
			String temporaryOutputFileName;

			System.out.println(inputFile.getName() + " file size: " + getFileSize(inputFileName));
			
			if (outputFileName.equals(inputFileName))
				temporaryOutputFileName = "__tempo__.txt";
			else
				temporaryOutputFileName = outputFileName;
			
			outFile = new PrintWriter(temporaryOutputFileName);

			String oneLine = null;
			scratchPad.clear();
			while (inFile.ready())
			{
				oneLine = inFile.readLine();

				outFile.println(processOneLine(oneLine));
			}
			inFile.close();
			outFile.close();
			fileCounter++;

			if ( (new File(temporaryOutputFileName)).length() == 0 )
				throw new RuntimeException("OUTPUT FILE " + temporaryOutputFileName +
					" LENGTH IS ZERO!!!");

			if (outputFileName.equals(inputFileName))
			{
				File tempo = new File("__tempo__.txt");
				File oldFile = new File(outputFileName);
				oldFile.delete();
				oldFile = new File(outputFileName);
				tempo.renameTo(oldFile);
			}
			System.out.printf(outputFile.getName() + " output file size: %d\n\n", getFileSize(outputFileName));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args)
	{
		File directory = new File("conf/generation/descriptors/entities/transformations");
		
		File files[] = directory.listFiles();
		
		for (File oneFile : files)
		{
			processFile(oneFile, oneFile);
		}
		
		System.out.printf("Processed %d files, made %d changes\n", fileCounter, changeCounter);
	}
}

/*
public static String processOneLine(String oneLine)
{
	String result = oneLine;

	try
	{
		Pattern javaFieldStart = Pattern.compile("<javaFieldDescription.*type=\"(.*)\".*");
		Pattern validationStart = Pattern.compile("<validation");
		Pattern javaFieldEnd = Pattern.compile("</javaFieldDescription");
		Matcher matcherJavaFieldStart = javaFieldStart.matcher(oneLine);
		Matcher matcherValidation = validationStart.matcher(oneLine);
		Matcher matcherJavaFieldEnd = javaFieldEnd.matcher(oneLine);
		
		if (matcherJavaFieldStart.find())
		{
			scratchPad.put("type", matcherJavaFieldStart.group(1));
		}
		else if (matcherValidation.find())
		{
			scratchPad.remove("type"); // if there is validation already, then add nothing
		}
		else if (matcherJavaFieldEnd.find() &&
			scratchPad.containsKey("type"))
		{
			String type = scratchPad.get("type");
			String typeU = Utilities.firstLetterToUpperCase(type);
			if (typeU.equals("Int")) typeU = "Integer";
			
			result = "\t\t\t\t<validation>\n\t\t\t\t\t<" + typeU +">\n" +
				"\t\t\t\t\t\t<allowNull>false</allowNull>\n";
			if (type.equals("String"))
				result += "\t\t\t\t\t\t<minLength>0</minLength>\n" +
				"\t\t\t\t\t\t<maxLength>100</maxLength>\n";
			else if (type.equals("byte") ||
				type.equals("short") ||
				type.equals("int") ||
				type.equals("float") ||
				type.equals("double"))
				result += "\t\t\t\t\t\t<min>1</min>\n" +
				"\t\t\t\t\t\t<max>100</max>\n";
			else if (type.equals("Timestamp") ||
				type.equals("Date") ||
				type.equals("boolean"))
				result += "";
			else if (type.equals("long"))
				result += "\t\t\t\t\t\t<min>KEY_LOWEST_RESERVED</min>\n";
			else
				throw new RuntimeException("Unrecognized Type!");
			// System.out.println(tempo);
			result += "\t\t\t\t\t</" + typeU + 
			">\n\t\t\t\t</validation>\n\t\t\t</javaFieldDescription>";

			scratchPad.remove("type");
		}

		return result;
	}
	catch (Exception e)
	{
		throw new RuntimeException(e);
	}
}

*/

/*
public static String processOneLine(String oneLine)
{
	String result = oneLine;

	try
	{
		Pattern tableName = Pattern.compile("<name>(.*)</name>");
		Matcher matcherTableName = tableName.matcher(oneLine);
		Pattern columnDescriptionStart = Pattern.compile("<columnDescription.*name=\"(.*)\".*type=\"(.*)\"");
		Matcher matcherColumnDescriptionStart = columnDescriptionStart.matcher(oneLine);
		Pattern attributesEnd = Pattern.compile("</attributes");
		Matcher matcherAttributesEnd = attributesEnd.matcher(oneLine);
		
		if (matcherTableName.find() && !scratchPad.containsKey("__TABLE__"))
		{
			scratchPad.put("__TABLE__", matcherTableName.group(1));
		}
		else if (matcherColumnDescriptionStart.find())
		{
			scratchPad.put(matcherColumnDescriptionStart.group(1), 
				matcherColumnDescriptionStart.group(2));
		}
		else if (matcherAttributesEnd.find())
		{
			PrintWriter outFile = 
				new PrintWriter( new FileWriter("conf/generation/output/tempo.txt", true) );
			outFile.printf("%s\n", scratchPad.get("__TABLE__"));
			
			outFile.printf("\t<populate>\n\t\t<defaults>\n\t\t\t<columnValues>\n");
			for (String name : scratchPad.keySet())
			{
				if (!name.equals("__TABLE__"))
				{
					String type = scratchPad.get(name);
					if (name.endsWith("IDK"))
						outFile.printf("\t\t\t%s '1'\n", name);
					else if (type.startsWith("VARCHAR"))
						outFile.printf("\t\t\t%s 'string.Default'\n", name);
					else if (type.startsWith("TIMESTAMP"))
						outFile.printf("\t\t\t%s '1000-01-01 00:00:00'\n", name);
					else if (type.startsWith("DATE"))
						outFile.printf("\t\t\t%s '1000-01-01'\n", name);
					else if (type.startsWith("INT") || 
						type.startsWith("REAL") || type.startsWith("DOUBLE"))
						outFile.printf("\t\t\t%s '0'\n", name);
					else if (type.startsWith("BOOLEAN"))
						outFile.printf("\t\t\t%s 'false'\n", name);
					else
						throw new RuntimeException("Unrecognized SQL type!");
				}
			}
			outFile.printf("\t\t\t</columnValues>\n\t\t</defaults>\n");
			outFile.printf("\t\t<insert>\n\t\t\t<columnValues>\n");
			outFile.printf("\t\t\tIDK '1'\n");
			for (String name : scratchPad.keySet())
			{
				if (!name.equals("__TABLE__"))
				{
					String type = scratchPad.get(name);
					if (type.startsWith("VARCHAR"))
						outFile.printf("\t\t\t%s 'string.Unset'\n", name);
				}
			}
			outFile.printf("\t\t\t</columnValues>\n\t\t</insert>\n");
			outFile.printf("\t\t<insert>\n\t\t\t<columnValues>\n");
			outFile.printf("\t\t\tIDK '101'\n");
			outFile.printf("\t\t\t</columnValues>\n\t\t</insert>\n");
			outFile.printf("\t</populate>\n");
			outFile.println();
			outFile.close();
			scratchPad.clear();
		}

		return result;
	}
	catch (Exception e)
	{
		throw new RuntimeException(e);
	}
}

*/