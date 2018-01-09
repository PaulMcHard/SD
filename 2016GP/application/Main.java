package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import FormatIO.Console;
public class Main {

	public static void main(String[] args) throws Exception{
		Game game = new Game();
		game.sqlLoader(); //script for SQL loader has unnecessay bits still in it, need to go fix later
		game.cardMaker();
		game.dealCards();
		game.runRound();
	}
}

