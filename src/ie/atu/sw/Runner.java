// packages
package ie.atu.sw; // main package

//imports
import java.util.Scanner; // used to read in user input
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is the main class where execution of code starts
 */

public class Runner {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Main Variables
		// objects
		Menu menuObj = new Menu();
		FileHandler fileHandlerObj = new FileHandler();
		Scanner scanner = new Scanner(System.in);
		// local Variables
		String stringToCompare;
		int matchNum = 25; // 25 by default
		int algorithmSelected = 1;
		Map<String, double[]> wordMap = new HashMap<String, double[]>();
		
		// prompting interface, and getting back the option selected value (O1)
		menuObj.promptMenu();
		
		// entering menu loop if they have not selected to quit application (O)n unless goes into a case with another loop then (O)n2, or if 3 loops inside one another then (O)n3
		while (menuObj.getOptionSelected() != 7)
		{
			switch(menuObj.getOptionSelected())
			{
				case 1: // specify embedding File
					System.out.print("Specify Embedding File Location: ");
					fileHandlerObj.setEmbeddingFilePath(scanner.nextLine());
					
					// filling up Hashmap from file
					// this is a (O)n2 operation 
					fileHandlerObj.populateFromFile(wordMap);
					break;
					
				case 2: // specify an Output File (default: ./out.txt)
					System.out.print("Specify Output File Location: ");
					fileHandlerObj.setOutputFilePath(scanner.nextLine());
					break;
					
				case 3: // Enter a Word or Text
					// don't allow them to do this if they haven't selected embeddings file path
					if (fileHandlerObj.getEmbeddingFilePath().equals("nil")) {
						System.out.println("\n ---You must specify a valid embeddings file before comparing---");
						System.out.println(" ---Press enter to continue--- \n");
						scanner.nextLine();
						break;
					}
					
					System.out.print("Enter a Word or text: ");
					stringToCompare = scanner.nextLine().toLowerCase(); // turning input lower case so it's case insensitive 
					
					// Splicing key into set
					String[] splicedCompareString = stringToCompare.split(" ");
					
					// checking if key/keys are valid
					boolean isValidSet = false;
					if (splicedCompareString.length == 1 && wordMap.containsKey(stringToCompare))
					{
						isValidSet = true;
					}
					else // the else below is (O)n
					{
						for (int i = 0; i < splicedCompareString.length; i++)
						{
							if (wordMap.containsKey(splicedCompareString[i]))
							{
								isValidSet = true; // at least 1 word is a key
								break;
							}
						}
					}
					
					// only let code continue if at least 1 key was found
					if (isValidSet)
					{
						// comparing words
						List<EntryWrapper> sortedKeys = new ArrayList<EntryWrapper>(); // we will use this list to sort the result later
						
						// looping through each word in the short sentence, or single word and comparing similarity (O)n2
						for (int compareIndex = 0; compareIndex < splicedCompareString.length; compareIndex++)
						{
							if (wordMap.containsKey(splicedCompareString[compareIndex]))
							{
								// fetching current user inputted word to compare
								double[] selectedWordVectors = wordMap.get(splicedCompareString[compareIndex]);
								
								// looping through whole hashmap keyset (O)n
								for (String key : wordMap.keySet()) {
									if (!splicedCompareString[compareIndex].equals(key)) // makes sure not to compare against itself
									{
										double[] vectors = wordMap.get(key);
										
										// choosing comparing function to use with improved switch statement from class
										double similarity = switch(algorithmSelected) {
											case 1 -> ComparingVectorHandler.CosineDistance(selectedWordVectors, vectors);
											case 2 -> ComparingVectorHandler.EuclideanDistance(vectors, selectedWordVectors);
											case 3 -> ComparingVectorHandler.DotProduct(selectedWordVectors, vectors);
											default -> {
												throw new IllegalArgumentException("Unexpected value: " + algorithmSelected);
											}
										};
		
										 // adding to ArrayList with similarity value
								        sortedKeys.add(new EntryWrapper(key, similarity));
									} 
							    }	
							}
						}
						
						// creating a loading vfx
						menuObj.loadingVFX();
						
						// sorting compared words
						Collections.sort(sortedKeys);
						
						// outputting to file
						fileHandlerObj.outputToFile(sortedKeys, matchNum);
					}
					else // else tell them their sentence is not valid
					{
						System.out.println("The word / sentence you entered could not be found in the embeddings file, please enter a new word");
					}
					break;
					
				case 4: // Configure Options
					System.out.print("Enter the number of high scoring matches, n, to report (-1 = max file size): ");
					matchNum = scanner.nextInt();
					
					if (matchNum == -1)
					{
						matchNum = fileHandlerObj.getFileSize()-1;
					}
					
					// clearing scanner buffer
					scanner.nextLine();	
					break;
					
				case 5: //  change output order
					// allow to choose from different comparing algorithms
					// allow to sort from highest to lowest, from lowest to highest
					int optionSelected;

					System.out.print("Enter (1 = output in descending order), (2 = output in ascending order): ");
					optionSelected = scanner.nextInt();
					EntryWrapper.descendingOrder = optionSelected == 1; // defaults to ascending if not valid option
					
					// clearing scanner buffer
					scanner.nextLine();	
					break;
					
				case 6: // change comparing algorithm
					System.out.print("You can change the comparing algorithm by entering one of the following numbers");
					System.out.print("(1 = CosineDistance), (2 = EuclideanDistance), (3 = DotProduct): ");
					algorithmSelected = scanner.nextInt();

					// ensuring a valid option has been selected (O)n
					while(algorithmSelected > 3 || algorithmSelected < 1)
					{
						System.out.print("invalid option, select new option: ");
						algorithmSelected = scanner.nextInt();
					}
					
					// clearing scanner buffer
					scanner.nextLine();	
					break;
					
				default:
					menuObj.setOptionSelected(0); // resets the loop to the start
			}
			
			// prompting interface, and getting back the option selected value
			menuObj.promptMenu();
		}
		
		// we are done with scanner, closing to save resources
		scanner.close();
		menuObj.close();
	}
}


