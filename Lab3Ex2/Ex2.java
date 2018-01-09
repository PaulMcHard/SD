import FormatIO.*;

public class Ex2 {
	public static void main(String[] args) {
		
		/*
		 * Pretty basic needs a Word Word Number Word
		 * structure so susceptible to breaking easily.
		 */
		
		//Declarations
		String input, wordOne, wordTwo, wordFour; 
		double numberThree;
		Console console = new Console("Primary Console", 64,64);
		
		input = console.readLine();		//read input from console
		StringIn sin = new StringIn(input); //turn input to StrignIn
		
		//Use readWord/Double to process each part of the sentence
		wordOne = sin.readWord(); 
		wordTwo = sin.readWord();
		numberThree = sin.readDouble();
		wordFour = sin.readWord();
		
		//Output them using System.err
		System.err.println(wordOne);
		System.err.println(wordTwo);
		System.err.println(numberThree);
		System.err.println(wordFour);
		String numberString = "" +numberThree; //turns it back to a string from double
		System.err.println(numberString);
	}
}
