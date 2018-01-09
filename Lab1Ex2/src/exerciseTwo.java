import FormatIO.*; 
public class exerciseTwo {
	public static void main (String[] arg) {
		
		Console con = new Console();
		FileIn fin = new FileIn("..\\Lab1ex1\\greeting.txt");
		String word = "";
		word =fin.readWord();
		con.println(word);
	}

}
