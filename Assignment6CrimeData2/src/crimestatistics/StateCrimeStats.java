package crimestatistics;

/**
 * This class stores crime data for a state, including the state's name, population, 
 * total violent crimes, total non-vehicle theft crimes, the city in the state with the 
 * highest property crime rate, and that city's property crime rate. This class is Serializable.
 * @author Main
 *
 */
public class StateCrimeStats extends java.lang.Object implements java.io.Serializable
{
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds highest property crime city.
	 */
	private static String highestPropertyCrimeCity;
	/**
	 * Holds highest property crime rate.
	 */
	private static double highestPropertyCrimeRate;
	/**
	 * Holds non vehicle theft crime stats.
	 */
	private int nonVehicleTheftCrimes;
	/**
	 * Holds state name.
	 */
	private String stateName;
	/**
	 * Holds violent crime stats.
	 */
	private int violentCrimes;
	/**
	 * Holds population stats.
	 */
	private int population;
	
	/**
	 * StateCrimesStats constructor requiring state name, population, and pre-calculated crime statistics.
	 * @param stateNameIn - Name of the state
	 * @param populationIn - Population of the state
	 * @param violentCrimesIn - Total number of violent crimes in the state
	 * @param nonVehicleTheftCrimesIn - Total number of non-vehicle theft crimes in the state
	 * @param highestPropertyCrimeCityIn - City in the state with the highest property crime rate
	 * @param highestPropertyCrimeRateIn - Highest property crime rate in the state
	 */
	public StateCrimeStats(java.lang.String stateNameIn, int populationIn, int violentCrimesIn, 
			int nonVehicleTheftCrimesIn, java.lang.String highestPropertyCrimeCityIn, 
			double highestPropertyCrimeRateIn)
	{
		stateName = stateNameIn;
		population = populationIn;
		violentCrimes = violentCrimesIn;
		nonVehicleTheftCrimes = nonVehicleTheftCrimesIn;
		highestPropertyCrimeCity = highestPropertyCrimeCityIn;
		highestPropertyCrimeRate = highestPropertyCrimeRateIn;
	}
	/**
	 * Returns the name of the state for this object's crime statistics.
	 * @return Name of the state
	 */
	public java.lang.String getStateName()
	{
		return stateName;
	}
	/**
	 * Returns the population for this object's crime statistics.
	 * @return Population of the state
	 */
	public int getPopulation()
	{
		return population;
	}
	/**
	 * Returns the total number of violent crimes for this object's crime statistics.
	 * @return Total violent crimes in the state
	 */
	public int getViolentCrimes()
	{
		return violentCrimes;
	}
	/**
	 * Returns the total number of non-vehicle theft crimes for this object's crime statistics.
	 * @return Total non-vehicle theft crimes in the state
	 */
	public int getNonVehicleTheftCrimes()
	{
		return nonVehicleTheftCrimes;
	}
	/**
	 * Returns the name of the city in the state with the highest property crime rate.
	 * @return City with the highest property crime rate
	 */
	public static java.lang.String getHighestPropertyCrimeCity()
	{
		return highestPropertyCrimeCity;
	}
	/**
	 * Returns the highest property crime rate in the state.
	 * @return Highest property crime rate in the state
	 */
	public static double getHighestPropertyCrimeRate()
	{
		return highestPropertyCrimeRate;
	}
	/**
	 * Returns a String containing all state and crime information. String is in the format of 
	 * (values in caps): State: STATENAME
	 * Population: POPULATION

	 * Violent Crimes: VIOLENTCRIMES

	 * Non-Vehicle Theft Crimes: NONVEHICLETHEFTCRIMES

	 * Highest Property Crime Rate: HIGHESTPROPERTYCRIMECITY - HIGHESTPROPERTYCRIMERATE
	 * @overrides toString in class java.lang.Object
	 * @return String as described in this method's documentation
	 */
	public java.lang.String toString()
	{
		return String.format("State: %s\nPopulation: %d\nViolent Crimes: %d\n"
				+ "Non-Vehicle Theft Crimes: %d\nHighest Property Crime Rate: "
				+ "%s - %f", stateName, population, violentCrimes, nonVehicleTheftCrimes, 
				highestPropertyCrimeCity, highestPropertyCrimeRate);
	}
}