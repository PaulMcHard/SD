import java.util.*;

/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */

public class FitnessProgram {
	//Class Constant and Instantance variables
	private final int numberClasses = 7;
	private FitnessClass[] inList;
	private FitnessClass[] progList;

	public FitnessProgram(ArrayList<FitnessClass> inArray){
		setProgList(inArray);
	}
	
	public void sortStartTime(FitnessClass[] list) {
		//bubble sort on start Time
		FitnessClass temp;
		for(int i=0; i < list.length; i++){  
			for(int j=1; j < (list.length-i); j++){  
				if(list[j-1].getStartTime() > list[j].getStartTime()){   
					temp = list[j-1];  
					list[j-1] = list[j];  
					list[j] = temp;  
				}  

			}  
		}
	}

	public void sortAttendance(FitnessClass[] list) {
		//bubble sort on attendance
		FitnessClass temp;
		for(int i=0; i < list.length; i++){  
			for(int j=1; j < (list.length-i); j++){  
				if(list[j].compareTo(list[j-1]) == 1){   
					temp = list[j-1];  
					list[j-1] = list[j];  
					list[j] = temp;  
				}  

			}  
		}
	}

	/** setProgList performs everything required to instantiate a Fitness Programme, hence constructor calls this method.
	 * By having it as a method allows the Programme to be updated in event of an addition or deletion in the schedule,
	 * as opposed to reconstructing the FitnessProgramme object, saving unncessary work.
	 */
	public void setProgList(ArrayList<FitnessClass> inArray) {
		//change incident arrayList into a fixed array
		inList = new FitnessClass[inArray.size()];
		for(int i = 0; i < inList.length; i++) {
			inList[i] = inArray.get(i);
		}
		//sort list by start time
		sortStartTime(inList);
		//instantiate the programme list, which is fixed 7 classes long (variable by changing class constant)
		progList = new FitnessClass[numberClasses];
		//add programmes to array bt start time, or create a vacant class in empty timeslots.
		for(int i = 0; i< progList.length; i++) {
			boolean classOccupied = false;
			for(int j =0; j< inList.length; j++) {
				if(inList[j].getStartTime() == i+9) {
					progList[i]=inList[j];
					classOccupied = true;
				}
			}
			if(!classOccupied) {
				FitnessClass addClass = new FitnessClass();
				addClass.setVacancy(true);
				addClass.setStartTime(i+9);
				progList[i] = addClass;
			}
		}
	}

	//accessor method for the array
	public FitnessClass[] getArray() {
		return progList;
	}

	//locates a class in the array by ID
	public FitnessClass matchID(String IDIn) {
		for (int j = 0; j < progList.length; j++) {
			if(IDIn.equals(progList[j].getID())){
				return progList[j];
			}
		}
		//If a match cannot be found
		return null;
	}

}


