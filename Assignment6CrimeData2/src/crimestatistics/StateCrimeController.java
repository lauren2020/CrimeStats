// Name : Lauren Shultz
// Class : 850
// Program # : 6
// Due Date : December 7, 2017
//
// Honor Pledge: On my honor as a student of the University
// of Nebraska at Omaha, I have neither given nor received
// unauthorized help on this homework assignment.
//
// NAME: Lauren Shultz
// EMAIL: lshultz@unomaha.edu
// NUID: 043
// Colleagues: Patrick Cavanaugh pcavanaugh.uno@gmail.com, Suganya Chandrababu schandrababu@unomaha.edu 
// This program looks for selected state crime data and outputs for the user either by processing it
// if it hasn't been stored or opening a saved serialized file.
package crimestatistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class assists in reading, processing, and storing crime data by state that is stored in a csv file.
 * @author Main
 *
 */
public class StateCrimeController extends java.lang.Object
{
	/**
	 * Crime rate multiplier.
	 */
	public static final int	CRIME_RATE_MULTIPLIER = 100000;
	/**
	 * Holds name index.
	 */
	public static final int	NAM = 0;
	/**
	 * Holds population index.
	 */
	public static final int	POP = 1;
	/**
	 * Holds pviolent crimes index.
	 */
	public static final int	VIO = 2;
	/**
	 * Holds murder index.
	 */
	public static final int	MUR = 3;
	/**
	 * Holds robbery index.
	 */
	public static final int	ROB = 4;
	/**
	 * Holds assult index.
	 */
	public static final int	ASS = 5;
	/**
	 * Holds property crime index.
	 */
	public static final int	PRO = 6;
	/**
	 * Holds burglary index.
	 */
	public static final int	BUR = 7;
	/**
	 * Holds larsony index.
	 */
	public static final int	LAR = 8;
	/**
	 * Holds population index.
	 */
	public static final int	VEH = 9;
	/**
	 * Holds arson index.
	 */
	public static final int	ARS = 10;
	/**
	 * holds population stats.
	 */
	private int population;
	/**
	 * holds ciolent crimes stats.
	 */
	private int violentCrimes;
	/**
	 * holds robbery stats.
	 */
	private int robbery;
	/**
	 * holds burglary stats.
	 */
	private int burglary;
	/**
	 * holds larsony stats.
	 */
	private int larsony;
	/**
	 * holds non vehile theft crime stats.
	 */
	private int nonVehicleTheftCrimes;
	/**
	 * holds highest property crime city.
	 */
	private String highestPropertyCrimeCity;
	/**
	 * holds highest property crime rate.
	 */
	private double highestPropertyCrimeRate;
	/**
	 * holds State name with spaces removed.
	 */
	private String sTATE;
	/**
	 * holds current state crime stats.
	 */
	private StateCrimeStats currentStateCrimeStats;
	/**
	 * Constructor method.
	 */
	public StateCrimeController()
	{
		
	}
	/**
	 * Processes the crime data for a given state if it is not already stored. Checks if a 
	 * .ser file exists for the given state. The .ser file is in the form of "STATE.ser", 
	 * where STATE is the name of the state passed with all spaces removed. Spaces can be 
	 * removed from a String using the String method replaceAll: state.replaceAll("\\s", "") 
	 * will return a new String which is identical to the calling String with space characters removed.
	 * 
	 * If the .ser file already exists for the state, it will not re-process the file, instead, 
	 * it will read the StateCrimeStats Serialized object from the file and set currentStateCrimeStats 
	 * to the object read, and return true to indicate the processed data already existed.

	 * If the .ser file does not exist, it will attempt to open a .csv file in the form of "STATE.csv", 
	 * where STATE is the name of the state passed with all spaces removed. This file will then be processed, 
	 * creating a new StateCrimeStats object which is stored as a member of this class, overwriting any 
	 * previous StateCrimeStats object stored. Once processed, false is returned to indicate the processed 
	 * data did not previously exist and had to be created.

	 * This method is intended to be an entry point for all actions described in its documentation. Helper 
	 * methods should be created to decompose the work.
	 * 
	 * @param stateIn the current state
	 * @return True if the processed data already existed, false if it did not and had to be created
	 * @throws java.io.FileNotFoundException - Thrown if a .csv file name after the given state does not exist
	 * @throws java.io.IOException - Thrown if there was an error reading from an existing .ser file
	 * @throws java.lang.ClassNotFoundException - Thrown if the type of the object read from the 
	 * .ser file is unknown
	 */
	public boolean processStateCrimes(java.lang.String stateIn) throws java.io.FileNotFoundException, 
		java.io.IOException, java.lang.ClassNotFoundException
	{
		sTATE = stateIn.replaceAll("\\s", "");
		if (stored())
		{
			readObject();
			return true;
		}
		else
		{
			openCSV();
			storeCurrentStateCrimeStats();
			return false;
		}
	}
	/**
	 * read the StateCrimeStats Serialized object from the file.
	 * I recieved help with my ObjectInputStream commands from Suganya Chandrababu schandrababu@unomaha.edu
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void readObject() throws IOException, ClassNotFoundException
	{
		String stateSer = String.format("%s.ser", sTATE);
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(stateSer));
		currentStateCrimeStats = (StateCrimeStats) input.readObject();
		input.close();
	}
	/**
	 * Sets the current state crime stats.
	 * @param currentStats current city information read from file.
	 */
	public void setCurrentStateCrimeStats(ArrayList<String> currentStats)
	{
		tokenizeCityStats(currentStats);
		nonVehicleTheftCrimes = burglary + robbery + larsony;
		currentStateCrimeStats =  new StateCrimeStats(sTATE, population, violentCrimes, 
				nonVehicleTheftCrimes, highestPropertyCrimeCity, highestPropertyCrimeRate);
	}
	/**
	 * Tokenize the current stats.
	 * I recieved help in getting my CityCrimeRate from Patrick Cavanaugh pcavanaugh.uno@gmail.com
	 * @param currentStats holds current stats
	 */
	public void tokenizeCityStats(ArrayList<String> currentStats)
	{
		int length = currentStats.size();
		ArrayList<String> cityStats = new ArrayList<String>();
		for (int j = 1; j < length; j++)
		{
			StringTokenizer stt = new StringTokenizer(currentStats.get(j), ",");
			while (stt.hasMoreTokens())
			{
				cityStats.add(stt.nextToken());
			}
			double cityCrimeRate = ((double) Integer.parseInt(cityStats.get(PRO)) 
					/ (double) Integer.parseInt(cityStats.get(POP))) * CRIME_RATE_MULTIPLIER;
			if (cityCrimeRate > highestPropertyCrimeRate)
			{
				highestPropertyCrimeRate = cityCrimeRate;
				highestPropertyCrimeCity = cityStats.get(NAM);
			}
			population += Integer.parseInt(cityStats.get(POP));
			violentCrimes += Integer.parseInt(cityStats.get(VIO));
			robbery += Integer.parseInt(cityStats.get(ROB));
			burglary += Integer.parseInt(cityStats.get(BUR));
			larsony += Integer.parseInt(cityStats.get(LAR));
			cityStats.clear();
		}
	}
	/**
	 * attempt to open a .csv file in the form of "STATE.csv".
	 * @throws FileNotFoundException 
	 */
	public void openCSV() throws FileNotFoundException
	{
		String stateCSV = String.format("%s.csv", sTATE);
		ArrayList<String> currentStats = new ArrayList<String>();
		Scanner input = new Scanner(new File(stateCSV));
		while (input.hasNextLine())
		{
			currentStats.add((String) input.nextLine());
		}
		input.close();
		setCurrentStateCrimeStats(currentStats);
	}
	/**
	 * stored returns true if .ser file exists, false if not.
	 * @return true if .ser file exists, false if not
	 */
	public boolean stored()
	{
		String stateSer = String.format("%s.ser", sTATE);
		File stateSerFile = new File(stateSer);
		return stateSerFile.exists();
	}
	/**
	 * Method deletes current ser file.
	 */
	public void delete()
	{
		String stateSer = String.format("%s.ser", sTATE);
		File stateSerFile = new File(stateSer);
		stateSerFile.delete();
	}
	/**
	 * Returns the StateCrimeStats of the most recent state requested.
	 * @return StateCrimeStats of most recent state; null if no stats have yet been requested
	 */
	public StateCrimeStats getCurrentStateCrimeStats()
	{
		return currentStateCrimeStats;
	}
	/**
	 * Ensures the most recently requested StateCrimeStats is stored into a Serialized file name "STATE.ser", 
	 * where STATE is the name of the state with all spaces removed. If the Serialized file already exists, 
	 * it will immediately return false to indicate the file was not saved. If the file does not exist it 
	 * will be written to the Serialized file and return true to indicate the file was saved.
	 * @return True if the file was saved, false if the file was not saved due to it already existing 
	 * or currentStateCrimeStats is null, meaning no stats have been processed.
	 * @throws java.io.IOException - Thrown if the Serialized file could not be written
	 */
	public boolean storeCurrentStateCrimeStats() throws java.io.IOException
	{
		String stateSER = String.format("%s.ser", sTATE);
		File stateSerFile = new File(stateSER);
		if (stored())
		{
			return false;
		}
		else
		{
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(stateSerFile));
			output.writeObject(getCurrentStateCrimeStats());
			output.close();
			return true;
		}
	}
}
