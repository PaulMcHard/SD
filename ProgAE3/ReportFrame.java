import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Class to define window in which attendance report is displayed.
 */
public class ReportFrame extends JFrame {

	//Instance Variables
	private FitnessProgram progIn;
	private FitnessClass[] classes;
	private JTextArea reportArea;

	//Constructor for report frame
	public ReportFrame(FitnessProgram fpIn) {
		progIn = fpIn;
		progIn.sortAttendance(progIn.getArray());
		classes = removeNullClasses(progIn);
		reportArea = new JTextArea();
		add(reportArea,BorderLayout.CENTER);
		reportArea.setText(genReport());
		reportArea.setEditable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Attendance Report");
		setSize(630, 250);
		setResizable(false);
		setVisible(true);
	}

	/* Removes vacant classes from the Fitness Program, which occupy the "Available" timeslots.
	 * These classes have no purpose in the attendance report, so are removed.
	 */
	private FitnessClass[] removeNullClasses(FitnessProgram fpIn) {
		FitnessClass[] noNullClasses;
		ArrayList<FitnessClass> flexClasses = new ArrayList<FitnessClass>();
		for(int i = 0; i< fpIn.getArray().length; i++) {   
			if(!(fpIn.getArray()[i].getID() == null) ) {
				flexClasses.add(fpIn.getArray()[i]);
			}
		}
		noNullClasses = new FitnessClass[flexClasses.size()];
		for(int j = 0; j < flexClasses.size(); j++) {
			noNullClasses[j]=flexClasses.get(j);	
		}
		return noNullClasses;
	}

	public String genReport(){
		//generates a formatted report of attendances, also handles calculation of overall average, efficient as overall av. attendance not needed outside this report.
		String output = new String();
		double ovrAvg = 0;
		output+=String.format("\r\n%10s\t%10s\t%10s\t%20s\t%20s\r\n\r\n", "ID", "Class", "Tutor", "Attendances", "Average Attendance");
		output+="===============================================================\r\n\r\n";
		for(int i=0; i<classes.length;i++) {
			String attendances = "";
			for(int j = 0; j< classes[i].weeksRecorded; j++) {
				attendances +=classes[i].attendance[j]+" ";
			}
			ovrAvg+=classes[i].avAttendance();
			output+=String.format("%10s\t%10s\t%10s\t%20s\t%10.2f\r\n", classes[i].getID(), classes[i].getName(), classes[i].getInstructor(), attendances, classes[i].avAttendance());
		}
		ovrAvg/=classes.length;
		output+=String.format("\r\n\t\t\tOverall Average : %2.2f", ovrAvg);
		return output;
	}
}