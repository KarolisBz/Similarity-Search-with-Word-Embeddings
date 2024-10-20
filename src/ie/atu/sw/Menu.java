// packages
package ie.atu.sw; // main package

// imports
import java.util.Scanner; // used to read in user input

/**
 * This class Menu is a interface tool.
 * It's used to prompt, retrieve and store values from the user.
 * This class uses Dr. John Healys sample code extensively so credits to him.
 */

public class Menu {
	// class fields
	private int optionSelected;
	Scanner scanner = new Scanner(System.in);
	
	// default constructor
	public Menu() {
		optionSelected = 6;
	}
	
	// methods
	/**
	 * Prompts the user with the starting Menu interface.
	 * This menu displays all available functionality the user can select and use.
	 * It stores the users response to the prompt as an Integer.
	 */
	public void promptMenu()
	{
		// This method works at O1 (constant time)
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*          Similarity Search with Word Embeddings          *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Embedding File");
		System.out.println("(2) Specify an Output File (default: ./out.txt)");
		System.out.println("(3) Enter a Word or Text");
		System.out.println("(4) Configure Options");
		System.out.println("(5) Output Order Options");
		System.out.println("(6) Change Comparing Algorithm");
		System.out.println("(7) Quit");
		
		//Output a menu of options and solicit text from the user
		System.out.print("Select Option [1-7]>: ");
		optionSelected = scanner.nextInt();
	}
	
	public void loadingVFX() throws InterruptedException
	{
		//You may want to include a progress meter in you assignment!
		// (O)n
		System.out.print(ConsoleColour.YELLOW);	//Change the colour of the console text
		int size = 100;							//The size of the meter. 100 equates to 100%
		for (int i =0 ; i < size ; i++) {		//The loop equates to a sequence of processing steps
			printProgress(i + 1, size); 		//After each (some) steps, update the progress meter
			Thread.sleep(10);					//Slows things down so the animation is visible 
		}
		
		// adding line break
		System.out.print("\n");
	}
	
	private static void printProgress(int index, int total) {
		if (index > total) return;	//Out of range
        int size = 50; 				//Must be less than console width
	    char done = '#';			//Change to whatever you like.
	    char todo = '=';			//Change to whatever you like.
	    
	    //Compute basic metrics for the meter
        int complete = (100 * index) / total;
        int completeLen = size * complete / 100;
        
        /*
         * A StringBuilder should be used for string concatenation inside a 
         * loop. However, as the number of loop iterations is small, using
         * the "+" operator may be more efficient as the instructions can
         * be optimized by the compiler. Either way, the performance overhead
         * will be marginal.  
         */
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // (O)n
        for (int i = 0; i < size; i++) {
        	sb.append((i < completeLen) ? done : todo);
        }
        
        /*
         * The line feed escape character "\r" returns the cursor to the 
         * start of the current line. Calling print(...) overwrites the
         * existing line and creates the illusion of an animation.
         */
        System.out.print("\r" + sb + "] " + complete + "%");
        
        //Once the meter reaches its max, move to a new line.
        if (done == total) System.out.println("\n");
    }
	
	/**
	 * Closes any running scanners, and any other objects that use up resources.
	 * using this function will terminate the usage of the menu Object.
	 */
	public void close()
	{
		// Closing scanner to free up resources
        scanner.close();
	}
	
	// getters and setters
	/**
	 * Returns the option selected
	 */
	public int getOptionSelected() {
		return optionSelected;
	}

	/**
	 * Sets option selected if the value passed is within range 1 and 7
	 */
	public void setOptionSelected(int optionSelected) {
		// only change if in-range of selectable options
		if (optionSelected > 1 && optionSelected < 7)
		{
			this.optionSelected = optionSelected;
		}
	}
	
}
