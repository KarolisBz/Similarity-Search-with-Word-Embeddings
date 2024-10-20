// packages
package ie.atu.sw;

// imports
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * This class handles operations that require writing, reading from a file.
 * It also stores relevant data about the status of these operations.
 */

public class FileHandler {
	// class fields
	private String embeddingFilePath, outputFilePath;
	private File outputFile, embaddingFile;
	private int fileSize = 0;
	
	// default constructor with some default values
	public FileHandler() {
		// some default values to speed up testing
		embeddingFilePath = "nil";
		outputFilePath = "./out.txt"; // default value is set to improve testing times
		// it's not hardcoded as it can be changed
		
		embaddingFile = new File(embeddingFilePath);
		outputFile = new File(outputFilePath);
	}
	
	// methods
	/**
	 * Outputs List data of value EntryWrapper into the current outputFile
	 * @param sortedValues
	 * @param matchNum
	 * @throws IOException
	 */
	public void outputToFile(List<EntryWrapper> sortedValues, int matchNum) throws IOException
	{
		// Method variables
		FileWriter writingTarget;
		
		// creating a new file to write into
		if (!outputFile.exists())
		{
			outputFile.createNewFile();
		}
		
		writingTarget = new FileWriter(outputFile);
		
		// looping through sorted array list n amount of times, writing to a file (O)n
		for (int i = 0; i < matchNum; i++)
		{
			EntryWrapper keyWrapper = sortedValues.get(i); // O1
			try {
				writingTarget.write(keyWrapper.key + " - Similarity Score: " + keyWrapper.value + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// closing file writer to save resource
		writingTarget.close();
	}
	
	/**
	 * Adds all entries from a file into the hashmap if in correct format (String, Double[])
	 * @param wordMap
	 * @throws IOException
	 */
	public void populateFromFile(Map<String, double[]> wordMap) throws IOException
	{
		// reading file and moving contents into Hashmap at (O)n2 speed
		Scanner reader = new Scanner(embaddingFile);
		
		// clearing the old data
		wordMap.clear();
		 
		// (O)n2
	     while (reader.hasNextLine()) {
	    	 String data = reader.nextLine();
	    	// turning to lower case so it's case insensitive 
			 String word = data.split(",")[0].toLowerCase();
			 
			 // creating double array to hold the words values
			 double[] associatedData = new double[50];
			 for (int i = 1; i < 50; i++)
			 {
				 associatedData[i - 1] = Double.valueOf(data.split(",")[i]);
			 }
			 
			 wordMap.put(word, associatedData);
			 fileSize++;
	     }  
	     
	     reader.close();
	}
	
	// getters and setters (O1)
	public String getEmbeddingFilePath() {
		return embeddingFilePath;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}
	
	public int getFileSize() {
		return fileSize;
	}

	public void setEmbeddingFilePath(String embeddingFilePath) {
		this.embeddingFilePath = embeddingFilePath;
		embaddingFile = new File(embeddingFilePath);
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
		outputFile = new File(outputFilePath);
	}
}
