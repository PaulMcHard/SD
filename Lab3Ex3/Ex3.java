import FormatIO.*;

public class Ex3 {
	public static void main(String[] args) {

		//Declarations
		String first ="", last= "", processString;
		int day=0, month=0, year=0, processInt;
		Console console = new Console();

		console.println("Please enter your full name and your date of birth,"
				+ " ensuring DOB is numerical and a space between each entry"
				+ " with no other formatting: ");

		StringIn input = new StringIn(console.readLine());
		for (int i=1; i<6; i++) {
			if (i<=2) {
				processString = input.readWord();
				switch (i) {

					case 1 :	first = processString;
								//System.err.println(first);
								break;
					case 2: 	last = processString;
								//System.err.println(last);
								break;	
					default: break;

				}

			}
			else {
				processInt = input.readInt();
				switch (i) {
				
					case 3:		day = processInt;
								//System.err.println(day); 
								break;
					case 4:		month = processInt;
								//System.err.println(month);
								break;
					case 5:		year = processInt;
								//System.err.println(year);
								break;
					default: break;
				}
			}
		}
		
	String output = String.format("%-12s%n%n%-12s%n%n%02d/%d/%d", first,last,month,day,year); //small thing I noticed is \n is sufficient for output to console but need %n for file output
	console.println(output);
	FileOut Lab3Out = new FileOut("Lab3personnel.txt");
	Lab3Out.println(output);
	}
}
