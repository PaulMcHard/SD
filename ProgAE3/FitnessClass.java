/** Defines an object representing a single fitness class
 */
public class FitnessClass implements Comparable<FitnessClass> {

	//Class constants
	public final int weeksRecorded = 5; //allows for simple change to attendance monitoring.
	private final String noInstructor = "[No Instructor]";
	
	//Instance Variables
	private String classID;
	private String name;
	private String instructor;
	private int startTime;
	public int[] attendance;
	public boolean isVacant;

	//Constructoe used for instantiating classes from ClassesIn file.
	public FitnessClass(String IDIn, String nameIn, String instIn, int timeIn) {
		this.classID = IDIn;
		this.name = nameIn;
		this.instructor = instIn;
		this.startTime = timeIn;
		this.isVacant = false;
		this.attendance = new int[weeksRecorded];
	}
	//default Constructor which is left empty.
	public FitnessClass() {
		this.instructor = noInstructor;
		this.attendance = new int[weeksRecorded];
	}

	//constructor for adding new classes using GUI add function
	public FitnessClass(String IDIn, String nameIn, String instIn) {
		this.classID = IDIn;
		this.name = nameIn;
		this.instructor = instIn;
		this.isVacant = false;
		this.attendance = new int[weeksRecorded];
		for (int i = 0; i < weeksRecorded; i++) {
			this.attendance[i]=0;
		}
	}

	//Calculates the average attendance, returns as a double.
	public double avAttendance() {
		double average = 0;
		for (int i = 0; i< attendance.length; i++) {
			average+=attendance[i];
		}
		average/=weeksRecorded;
		return average;
	}

	//compares two classes based on average attendance. If attendance is equal, goes by alphabetical order.
	public int compareTo(FitnessClass other) {
		double thisAv = this.avAttendance();
		double otherAv = other.avAttendance();
		//return 1 if average outright higher
		if(thisAv >= otherAv) {
			return 1;
		}
		//if average attendances match, go by alphabetical order.
		else if(this.avAttendance() == other.avAttendance() && this.classID.charAt(0) < other.classID.charAt(0)) {
			return 1;
		}
		//else return 0;
		else {
			return 0;
		}
	}

	//Accessor Methods
	public String getID() {return this.classID; }
	public String getName() {return this.name; }
	public String getInstructor() {return this.instructor; }
	public int getStartTime() {return this.startTime; }

	//Mutator Methods
	public void setID(String IDIn) {this.classID = IDIn;}
	public void setName(String nameIn) {this.name = nameIn;}
	public void setInstructor(String instIn) {this.instructor = instIn;}
	public void setStartTime(int timeIn) {this.startTime = timeIn;}
	public void setVacancy(boolean vacIn) {this.isVacant = vacIn;}

}	
