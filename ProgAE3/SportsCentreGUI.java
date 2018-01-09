import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Defines a GUI that displays details of a FitnessProgram object
 * and contains buttons enabling access to the required functionality.
 */
public class SportsCentreGUI extends JFrame implements ActionListener {

	/** GUI JButtons */
	private JButton closeButton, attendanceButton;
	private JButton addButton, deleteButton;

	/** GUI JTextFields */
	private JTextField idIn, classIn, tutorIn;

	/** Display of class timetable */
	private JTextArea display;

	/** Display of attendance information */
	private ReportFrame report;

	/** Names of input text files */
	private final String classesInFile = "ClassesIn.txt";
	private final String classesOutFile = "ClassesOut.txt";
	private final String attendancesFile = "AttendancesIn.txt";

	/** Arrays of Classes and Attendances */
	private ArrayList<FitnessClass> classes;
	private FitnessProgram progList;
	/**
	 * Constructor for AssEx3GUI class
	 */
	public SportsCentreGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Boyd-Orr Sports Centre");
		setSize(800, 300);
		display = new JTextArea();
		display.setFont(new Font("Courier", Font.PLAIN, 14));
		add(display, BorderLayout.CENTER);
		layoutTop();
		layoutBottom();
		initLadiesDay();
		initAttendances();
		updateDisplay();
		display.setEditable(false);
	}

	/**
	 * Creates the FitnessProgram list ordered by start time
	 * using data from the file ClassesIn.txt
	 * @throws FileNotFoundException 
	 */
	public void initLadiesDay(){
		//load files into an arrayList of classes to allow variant array size (ie. changing number of file input classes)
		classes = new ArrayList<FitnessClass>();
		String readLine; 
		try {
			//file read in
			FileReader fileIn = new FileReader(classesInFile);
			BufferedReader readIn = new BufferedReader(fileIn);
			//while loop repeats until all lines of file have been read.
			while((readLine = readIn.readLine()) != null) {
				//separate the read in line into a String array, each element of the array is split by a space
				String[] wordSet = readLine.split(" ");
				FitnessClass classIn = new FitnessClass();
				for (int j = 0; j < wordSet.length; j++) {
					//switch case works because of the standard system of "ID NAME TUTOR STARTTIME" used in classesIn file.
					switch (j) {
					case 0: classIn.setID(wordSet[j]);
					break;
					case 1: classIn.setName(wordSet[j]);
					break;
					case 2: classIn.setInstructor(wordSet[j]);
					break;
					//Use Scanner in this manner to extract integer value from the read in string.
					case 3: Scanner in = new Scanner(wordSet[j]).useDelimiter("[^0-9]+");
					classIn.setStartTime(in.nextInt());
					break;
					}
				}
				//add the created class into the ArrayList
				classes.add(classIn);
			}
			//pass the arraylist into the contsructor of the FitnessProgram.
			progList = new FitnessProgram(classes);
		}
		catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		catch (IOException io) {
			io.printStackTrace();
		}

	}

	/**
	 * Initialises the attendances using data
	 * from the file AttendancesIn.txt
	 */
	public void initAttendances() {
		String readLine; 
		FitnessClass activeClass = null; //pointer for class we're actively assigning attendance for.
		//could just put 5 but for sake of rigidity and ease of further alteration, accessing the class constant works well.
		try {
			FileReader fileIn = new FileReader(attendancesFile);
			BufferedReader readIn = new BufferedReader(fileIn);
			while((readLine = readIn.readLine()) != null) {
				//need to remove instances where more than one space is used to separate information.
				while (readLine.contains("  ")) {
					readLine = readLine.replace("  ", " ");
				}
				String[] wordSet = readLine.split(" ");
				for (int i = 0; i < wordSet.length; i++) {
					if(i == 0) { //first item in the String array is the ID, which must be matched appropriately
						activeClass = progList.matchID(wordSet[i]);
						if (activeClass == null) { //error message on attendance ID
							JOptionPane.showMessageDialog(null, "no class ID match found for ID "+wordSet[i]+"!");
						}
					}
					else {
						//once ID match has happened, remainder of line is attendance values, which can be put into the attendance int array for the class
						Scanner in = new Scanner(wordSet[i]).useDelimiter("[^0-9]+");
						activeClass.attendance[i-1] = in.nextInt();
					}
				}
			}
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates timetable display and adds it to GUI
	 */
	public void updateDisplay() {
		display.setFont( new Font("monospaced", Font.PLAIN, 12) );
		String output ="9-10\t\t10-11\t\t11-12\t\t12-13\t\t13-14\t\t14-15\t\t15-16\n";
		for( int i = 0; i < progList.getArray().length; i++) {
			if (progList.getArray()[i].isVacant) {
				output+="Available\t";
			}
			else {
				output+=String.format("%-9s\t", progList.getArray()[i].getName());
			}
		}
		output += "\n";
		for( int j = 0; j < progList.getArray().length; j++) {
			if (progList.getArray()[j].isVacant) {
				output+="\t\t";
			}
			else {
				output+=String.format("%-9s\t", progList.getArray()[j].getInstructor());
			}
		}
		display.setText(output);
	}

	/**
	 * adds buttons to top of GUI
	 */
	public void layoutTop() {
		JPanel top = new JPanel();
		closeButton = new JButton("Save and Exit");
		closeButton.addActionListener(this);
		top.add(closeButton);
		attendanceButton = new JButton("View Attendances");
		attendanceButton.addActionListener(this);
		top.add(attendanceButton);
		add(top, BorderLayout.NORTH);
	}

	/**
	 * adds labels, text fields and buttons to bottom of GUI
	 */
	public void layoutBottom() {
		// instantiate panel for bottom of display
		JPanel bottom = new JPanel(new GridLayout(3, 3));

		// add upper label, text field and button
		JLabel idLabel = new JLabel("Enter Class Id");
		bottom.add(idLabel);
		idIn = new JTextField();
		bottom.add(idIn);
		JPanel panel1 = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		panel1.add(addButton);
		bottom.add(panel1);

		// add middle label, text field and button
		JLabel nmeLabel = new JLabel("Enter Class Name");
		bottom.add(nmeLabel);
		classIn = new JTextField();
		bottom.add(classIn);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		panel2.add(deleteButton);
		bottom.add(panel2);

		// add lower label text field and button
		JLabel tutLabel = new JLabel("Enter Tutor Name");
		bottom.add(tutLabel);
		tutorIn = new JTextField();
		bottom.add(tutorIn);

		add(bottom, BorderLayout.SOUTH);
	}

	/**
	 * Processes adding a class
	 */
	public void processAdding() {
		if(classes.size() == progList.getArray().length) {
			//progList will always be full of all 7 timeslots, using this equality allows variance in schedule length.
			JOptionPane.showMessageDialog(null, "All Timeslots occupied!");
			clearInputs();
		}
		//get the data in textboxes
		String addID = removeSpaces(idIn.getText());
		String addName = removeSpaces(classIn.getText());
		String addTutor = removeSpaces(tutorIn.getText());
		//check input is valid
		if(checkAddInput(addID, addName, addTutor)) {
			//create the class to be added
			FitnessClass addClass = new FitnessClass(addID, addName, addTutor);
			//find the first available timeslot, assign it as new class's start time and add class to arrayList
			for(int i = 0; i < progList.getArray().length; i++ ) {
				if(progList.getArray()[i].isVacant) {
					addClass.setStartTime(i+9);
					classes.add(addClass);
					// update the programme and display and clear the inputs.
					updateProgramme();
					return;
				}
			}
		}
	}

	/**
	 * Processes deleting a class
	 */
	public void processDeletion() {
		//only require the ID for deletion
		String deleteID = removeSpaces(idIn.getText());
		//check input is valid
		if(checkDeleteInput(deleteID)) {
			//find class in the arrayList and remove it.
			for (int i = 0; i < classes.size(); i++) {
				if(deleteID.equals(classes.get(i).getID())){
					classes.remove(i);
					updateProgramme();
					return;
				}

			}
		}
	}

	/**
	 * Instantiates a new window and displays the attendance report
	 */
	public void displayReport() {
		report = new ReportFrame(progList);
	}

	/**
	 * Writes lines to file representing class name, 
	 * tutor and start time and then exits from the program
	 */
	public void processSaveAndClose() {
		//create a string containing programme in current state
		String output = new String();
		for(int i =0; i < progList.getArray().length; i++) {
			//ommit empty programme slots
			if(!progList.getArray()[i].isVacant) {
				output+=String.format("%s %s %s %2d\n", progList.getArray()[i].getID(), progList.getArray()[i].getName(), progList.getArray()[i].getInstructor(), progList.getArray()[i].getStartTime() );
			}
		}
		try {
			//output the string to file.
			FileWriter classesOut = new FileWriter(classesOutFile);
			classesOut.write(output);
			classesOut.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		//close programme
		System.exit(0);
	}

	/**
	 * Process button clicks.
	 * @param ae the ActionEvent
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == attendanceButton) {
			displayReport();
		}
		if (ae.getSource() == addButton) {
			processAdding();
		}
		if (ae.getSource() == deleteButton) {
			processDeletion();
		}
		if(ae.getSource() == closeButton) {
			processSaveAndClose();
		}
	}

	private boolean checkAddInput(String idIn, String classIn, String tutorIn) {
		//check input is valid for addition, no input box can be empty and ID must not already exist
		if((idIn.equals("") || classIn.equals("") || tutorIn.equals(""))) {
			JOptionPane.showMessageDialog(null,"At least one input box is empty!");
			return false;
		}
		for(int i = 0; i < progList.getArray().length; i++) {
			if(idIn.equals(progList.getArray()[i].getID())) {
				JOptionPane.showMessageDialog(null,"ID already exists!");
				return false;
			}
		}
		return true;
	}
	private boolean checkDeleteInput(String idIn) {
		//check input for deletion, ID box cannot be empty and must match an existing ID.
		if(!(idIn.equals(""))) {
			for(int i = 0; i < progList.getArray().length; i++) {
				if(idIn.equals(progList.getArray()[i].getID())) {
					return true;
				}
			}
			JOptionPane.showMessageDialog(null,"No matching class found!");
			return false;
		}
		JOptionPane.showMessageDialog(null, "ID of class for deletion cannot be blank!");
		return false;
	}

	private String removeSpaces(String in) { //removes spaces put in input text bars
		while (in.contains(" ")) {
			in = in.replace(" ", "");
		}
		return in;
	}

	private void updateProgramme() {
		progList.setProgList(classes);
		updateDisplay();
		clearInputs();
	}

	private void clearInputs() {
		idIn.setText("");
		classIn.setText("");
		tutorIn.setText("");
	}
}
