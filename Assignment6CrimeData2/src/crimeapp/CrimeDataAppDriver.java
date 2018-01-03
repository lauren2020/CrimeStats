package crimeapp;

import javax.swing.JFrame;

/**
 * Driver for the CrimeDataApp. Do not make changes.
 * @author Mr. Cavanaugh
 *
 */
public class CrimeDataAppDriver
{
	/**
	 * Width of the app.
	 */
	private static final int APP_WIDTH = 400;
	/**
	 * Height of the app.
	 */
	private static final int APP_HEIGHT = 400;
	
	/**
	 * Entry point of app execution. Creates CrimeDataApp, sets close operation, size, and visibility.
	 * @param args Not used
	 */
	public static void main(String[] args)
	{
		CrimeDataApp crimeFrame = new CrimeDataApp();
		crimeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crimeFrame.setSize(APP_WIDTH, APP_HEIGHT);
		crimeFrame.setResizable(false);
		crimeFrame.setVisible(true);
	}
}
