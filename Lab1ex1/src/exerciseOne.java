import FormatIO.*;
public class exerciseOne {
	public static void main(String[] arg) {
	
		Console con = new Console();
	    con.println("Hello World");
	    FileOut fout = new FileOut("greeting.txt");
	    fout.println("Hi Paul");
	}
}
