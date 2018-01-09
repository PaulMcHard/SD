import FormatIO.*;
public class Ex1 {

	public static Console console = new Console();

	public static void main(String[] args) {
		Worker w = new Worker();
		w.getInput(console);
		w.getCost(console);
	}
}
