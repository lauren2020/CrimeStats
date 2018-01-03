package crimestatistics;

/**
 * City crime stats class.
 * @author Main
 *
 */
public class CityCrimeStats extends java.lang.Object
{
	/**
	 * Index of assault in stats array.
	 */
	public static final int	ALT = 4;
	/**
	 * Index of arson in stats array.
	 */
	public static final int	ARS = 9;
	/**
	 * Index of burglary in stats array.
	 */
	public static final int	BUR = 6;
	/**
	 * Index of larceny in stats array.
	 */
	public static final int	LAR = 7;
	/**
	 * 	Index of murder in stats array.
	 */
	public static final int	MUR = 2;
	/**
	 * Number of entries in stats collection.
	 */
	public static final int	NUM_STATS = 10;
	/**
	 * Index of population in stats array.
	 */
	public static final int	POP = 0;
	/**
	 * Index of property crime in stats array.
	 */
	public static final int	PRO = 5;
	/**
	 * Index of robbery in stats array.
	 */
	public static final int	ROB = 3;
	/**
	 * Index of vehicle crime in stats array.
	 */
	public static final int	VEH = 8;
	/**
	 * Index of violent crimes in stats array.
	 */
	public static final int	VIO = 1;
	/**
	 * holds city name.
	 */
	private String cityName;
	/**
	 * holds array of stats.
	 */
	private int[] stats;
	
	/**
	 * CityCrimeData constructor, accepts name of the city and array of 
	 * crime statistics. Statistics in input array should be ordered as 
	 * determined by this class' static index values. The input array is 
	 * copied into a new array which is stored locally as stats.
	 * @param cityNameIn - Name of the city for this object's statistics
	 * @param statsIn - Population and crime statistics for this object's city
	 */
	public CityCrimeStats(java.lang.String cityNameIn, int[] statsIn)
	{
		cityName = cityNameIn;
		stats = statsIn;
	}
	/**
	 * Returns the name of the city for this object's crime statistics.
	 * @return Name of the city
	 */
	public java.lang.String getCityName()
	{
		return cityName;
	}
	/**
	 * Generates and returns a copy of the stats array.
	 * @return Copy of the stats array
	 */
	public int[] getStats()
	{
		int[] statsCopy = stats;
		return statsCopy;
	}
}
