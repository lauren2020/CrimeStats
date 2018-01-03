import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import crimestatistics.StateCrimeController;

/**
 * Console based test driver for a StateCrimeController. Will ask for the name of a state and process and
 * store that state's StateCrimeStats. Running this and receiving expected output in no way guarantees the
 * complete correctness of your assignment. This only tests the high level functionality; students should create
 * their own tests for ensuring correctness for all individual methods in all classes.
 * @author Mr. Cavanaugh
 *
 */
public class CrimeDataDriver
{
	/**
	 * Entry point for test driver.
	 * @param args Not used
	 */
	public static void main(String[] args)
	{
		//Create new StateCrimeController
		StateCrimeController scc = new StateCrimeController();
		//Store user input
		String state;
		//Create Scanner for input
		Scanner input = new Scanner(System.in);
		//Request name of state to be processed
		System.out.printf("Enter state name: ");
		state = input.nextLine();
		
		//Process crimes of requested state
		try
		{
			if (scc.processStateCrimes(state)) //Processed data already exists and is loaded
			{
				System.out.println("Loading existing data...");
			}
			else //Processed data did not exist, has now been processed
			{
				System.out.println("New state data processed");
			}
		}
		catch (FileNotFoundException fnfe) //No crime data for requested state is present
		{
			System.out.printf("%s's crime data file not found\n", state);
			return; //Can't go on, terminate
		}
		catch (IOException ioe) //Something went wrong reading the existing data
		{
			System.out.println("Error reading ser file");
		}
		catch (ClassNotFoundException cnfe) //Attempted to read an unknown object
		{
			System.out.println("Error reading StateCrimeStats object");
		}
		
		//Output the crime statistics requested
		System.out.println(scc.getCurrentStateCrimeStats());
		
		//Store the crime statistics requested
		try
		{
			if (scc.storeCurrentStateCrimeStats()) //Stats successfully stored
			{
				System.out.printf("%s's crime statisitcs stored\n", state);
			}
			else //Stats already stored, did not write to file
			{
				System.out.printf("%s's crime statistics already stored\n", state);
			}
		}
		catch (IOException e) //Something went wrong writing to the file
		{
			System.out.println("Error writing to file");
		}
	}
}
