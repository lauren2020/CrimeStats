package crimeapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;










import crimestatistics.StateCrimeController;

/**
 * Swing GUI based application for using a StateCrimeController. User can select a state from the drop down
 * menu and request to process that state's crime data if available. Once processed, the data can be stored
 * for retrieval.
 * @author Mr. Cavanaugh
 *
 */
public class CrimeDataApp extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Height of the text area console.
	 */
	private static final int CONSOLE_HEIGHT = 20;
	/**
	 * Width of the text area console.
	 */
	private static final int CONSOLE_WIDTH = 30;
	
	/**
	 * Text label for the state drop-down box.
	 */
	private JLabel stateLabel;
	/**
	 * Drop-down box for selecting state to process.
	 */
	private JComboBox<String> stateChoice;
	
	/**
	 * Button to process selected state's data.
	 */
	private JButton process;
	/**
	 * Button to save the last processed data.
	 */
	private JButton save;
	
	/**
	 * Scroll pane to hold the console, allows scrolling.
	 */
	private JScrollPane consolePane;
	/**
	 * Console is where all text output will appear. No output should be done to System.out.
	 */
	private JTextArea console;
	
	/**
	 * The StateCrimeController that handles the processing and saving of crime data.
	 */
	private StateCrimeController scc;
	
	/**
	 * Creates the frame for the CrimeDataApp. Instantiates all swing components, adds listener,
	 * and adds them to the frame.
	 */
	public CrimeDataApp()
	{
		//Call on JFrame constructor
		super("Crime Data Application");
		
		//Create and set a FlowLayout
		setLayout(new FlowLayout());
		
		//Create an instance of the ActionListener for the buttons
		CrimeDataAppActionListener listener = new CrimeDataAppActionListener();
		
		//Label for the state select drop-down box
		stateLabel = new JLabel("Select State");
		//Options for the drop-down box
		String[] states = {"SELECT", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
			"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
			"Iowa", "Kansas", "Kentucky", "Lousiana", "Maine", "Maryland", "Massachusetts", "Michigan",
			"Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
			"New Jersey", "New Mexico", "New York", "North Coralina", "North Dakota", "Ohia", "Oklahoma",
			"Oregon", "Pennsylvania", "Rhose Island", "South Coralina", "South Dakota", "Tnnessee",
			"Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
		//Create the drop-down box with array of state names
		stateChoice = new JComboBox<String>(states);
		
		//Create the process button and add listener
		process = new JButton("Process");
		process.addActionListener(listener);
		//Create the save button and add listener
		save = new JButton("Save");
		save.addActionListener(listener);
		
		//Text Area is used for all text output, no output should go to System.out
		console = new JTextArea("REQUESTED DATA OUTPUT HERE\n", CONSOLE_HEIGHT, CONSOLE_WIDTH);
		//Console is only used for output, set to not editiable so user cannot manipulate it
		console.setEditable(false);
		//Put the console in a scroll pane so as text is appended it can be scrolled
		consolePane = new JScrollPane(console);
		
		//Add all the components in the order they are to appear in the frame
		add(stateLabel);
		add(stateChoice);
		add(process);
		add(save);
		add(consolePane);
		
		//Create the StateCrimeController to be used for the application
		scc = new StateCrimeController();
	}
	
	/**
	 * ActionListener to handle the events caused by pressing the Process and Save buttons.
	 * @author Mr. Cavanaugh
	 *
	 */
	private class CrimeDataAppActionListener implements ActionListener
	{
		/**
		 * Method that is automatically called upon when an ActionEvent occurs. Pressing buttons causes
		 * an ActionEvent, and that event will be passed to this method. The method must first determine
		 * which button triggered the event, and perform the associated action.<p><p>
		 * 
		 * When the process button is pressed the StateCrimeController will be used to attempt to process
		 * the selected state's crime data. All output is appended to the console text area; use the same
		 * output as demonstrated in the text based CrimeDataDriver. If the default selection of the
		 * drop-down box is currently selected, the String, "PLEASE SELECT STATE", will be appended to the
		 * console.<p><p>
		 * 
		 * When the save button is pressed the StateCrimeController will be used to attempt to save the
		 * last state's data processed. All output is appended to the console text area; use the same
		 * output as demonstrated in the text based CrimeDataDriver. If save is pressed before any
		 * crime data has been processed, the String, "No processed data to store", will be appended to the
		 * console.<p><p>
		 * 
		 * All output to the console is preceded and ended with a newline character for readability.
		 * 
		 * @param event The ActionEvent that was triggered
		 */
		public void actionPerformed(ActionEvent event)
		{
			//YOU DO THIS!!
			String state = (String) stateChoice.getSelectedItem();
			StateCrimeController sccNew = new StateCrimeController();
			//String lastState = null;
			//String appendToConsole;
			//Determine which button is being pressed using the event's getSource() method
			if (event.getSource() == process)
			{
				if (!state.equals("SELECT"))
				{
					try 
					{
						state = (String) stateChoice.getSelectedItem();
						String title;
						String end;
						//StateCrimeController sccNew = new StateCrimeController();
						//sccNew.processStateCrimes(state);
						if (sccNew.processStateCrimes(state))
						{
							title = "Loading existing data...";
						}
						else 
						{
							title = "New state data processed";
						}
						if (scc.storeCurrentStateCrimeStats())
						{
							end = String.format("%s's crime statisitcs stored\n", state);
						}
						else
						{
							end = String.format("%s's crime statistics already stored\n", 
									state);
						}
						scc = sccNew;
						//lastState = state;
						console.append(String.format("%s\n%s\n%s\n\n", title, 
								sccNew.getCurrentStateCrimeStats(), end));
					}
					catch (FileNotFoundException fnfe)
					{
						console.append(String.format("%s's crime data file not found\n\n", 
								state));
						return;
					}
					catch (IOException ioe) 
					{
						System.out.println("Error reading ser file\n\n");
					}
					catch (ClassNotFoundException cnfe)
					{
						System.out.println("Error reading StateCrimeStats object\n\n");
					}
				}
				else
				{
					console.append("PLEASE SELECT STATE\n\n");
				}
			}
			else if (event.getSource() == save)
			{
				if (state != null)
				{
					try
					{
						if (scc.stored())
						{
							console.append(String.format("%s's crime statistics already "
									+ "stored\n\n", state));
						}
						else
						{
							scc.storeCurrentStateCrimeStats();
							console.append(String.format("%s's crime statisitcs "
									+ "stored\n\n", state));
						}
					}
					catch (IOException e)
					{
						console.append("Error writing to file\n\n");
					}
				}
				else
				{
					console.append("No processed data to store\n\n");
				}
			}
			//Don't modify this! This should remain and be the last thing done in the actionPeformed method,
			//it handles the auto-scrolling of the console
			try
			{
				console.setCaretPosition(console.getLineStartOffset(console.getLineCount() - 1));
			}
			catch (BadLocationException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
