package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import FormatIO.Console;

public class Game {

	public static int[] idList = null;
	public static ArrayList<String> nameList = new ArrayList<String>();
	public static int[] heiList = null;
	public static int[] weiList = null;
	public static int[] lenList = null;
	public static int[] ferList = null;
	public static int[] itlList = null;
	public static ArrayList<Dinosaur> dinoList = new ArrayList<Dinosaur>();
	public static ArrayList<Dinosaur> playerSet = new ArrayList<Dinosaur>();
	public static ArrayList<Dinosaur> computerOneSet = new ArrayList<Dinosaur>();
	public static ArrayList<Dinosaur> computerTwoSet = new ArrayList<Dinosaur>();
	public static ArrayList<Dinosaur> computerThreeSet = new ArrayList<Dinosaur>();

	public Console gameWindow = new Console("Game Window");

	public void sqlLoader() {
		try {
			Connection c = null;
			Statement stmt = null;
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) FROM DINODECK;" );

			while(rs.next()) {
				idList= new int[rs.getInt(1)];
				heiList= new int[rs.getInt(1)];
				weiList= new int[rs.getInt(1)];
				lenList= new int[rs.getInt(1)];
				ferList= new int[rs.getInt(1)];
				itlList= new int[rs.getInt(1)];
			}

			stmt = c.createStatement();
			rs = stmt.executeQuery( "SELECT * FROM DINODECK;" );


			int counter = 0;
			while ( rs.next() ) {
				int id = rs.getInt("dinoid");
				idList[counter] = id;
				String  name = rs.getString("name");
				nameList.add(counter, name);
				int height  = rs.getInt("height");
				heiList[counter] = height;
				int weight  = rs.getInt("weight");
				weiList[counter] = weight;
				int length = rs.getInt("length");
				lenList[counter] = length;
				int ferocity  =  rs.getInt("ferocity");
				ferList[counter] = ferocity;
				int intel = rs.getInt("intelligence");
				itlList[counter] = intel;
				counter++;
			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}

	public void cardMaker() { //generates cardset


		for (int i = 0; i<idList.length; i++) {
			dinoList.add(new Dinosaur(idList[i], nameList.get(i), heiList[i], weiList[i], lenList[i], ferList[i], itlList[i]));
			//System.out.println("Entry #"+dinoList.get(i).getID()+" - "+dinoList.get(i).getName()+" has been generated.");

		}
	}

	public void dealCards() {
		//basic attempt, deals to only two players
		Collections.shuffle(dinoList);
		for (int i = 0; i<(idList.length); i++) {
			//System.out.println(dinoList.get(i).getName());
			if ((i % 2 == 0)) {
				playerSet.add(dinoList.get(i));
			}
			else {
				computerOneSet.add(dinoList.get(i));
			}	
		}
		
		//System.out.println("Player has "+playerSet.size()+" cards. Computer has "+computerOneSet.size());
		int counter;
		if (playerSet.size() > computerOneSet.size()) {
			counter = computerOneSet.size();
		}
		else {
			counter = playerSet.size();
		}
		for(int i = 0; i<counter; i++) {
			//getClass().System.out.println("Player has "+playerSet.get(i).getName());
			//System.out.println("Computer has "+computerOneSet.get(i).getName());
		}
		
	}

	public boolean compareHeight(int i) {
		if (playerSet.get(i).getHeight() >= computerOneSet.get(i).getHeight()) {
			gameWindow.println("Player Wins Round");
			return true;
		}
		else {
			gameWindow.println("Player Loses Round");
			return false;
		}
	}
	public boolean compareWeight(int i) {
		if (playerSet.get(i).getWeight() >= computerOneSet.get(i).getWeight()) {
			gameWindow.println("Player Wins Round");
			return true;
		}
		else {
			gameWindow.println("Player Loses Round");
			return false;
		}
	}
	public boolean compareLength(int i) {
		if (playerSet.get(i).getLength() >= computerOneSet.get(i).getLength()) {
			gameWindow.println("Player Wins Round");
			return true;
		}
		else {
			gameWindow.println("Player Loses Round");
			return false;
		}
	}
	public boolean compareFerocity(int i) {
		if (playerSet.get(i).getFerocity() >= computerOneSet.get(i).getFerocity()) {
			gameWindow.println("Player Wins Round");
			return true;
		}
		else {
			gameWindow.println("Player Loses Round");
			return false;
		}
	}
	public boolean compareIntel(int i) {
		if (playerSet.get(i).getIntel() >= computerOneSet.get(i).getIntel()) {
			gameWindow.println("Player Wins Round");
			return true;
		}
		else {
			gameWindow.println("Player Loses Round");
			return false;
		}
	}

	public void deckUpdate(boolean playerWin) {
		ArrayList<Dinosaur> changeSet = new ArrayList<Dinosaur>();
		if (playerWin) {
			gameWindow.println("Computer lost: "+computerOneSet.get(0).getName());
			computerOneSet.remove(0);
			gameWindow.println("Computer has " +computerOneSet.size()+" cards remaining!");
			for (int i = 0; i < playerSet.size()-1; i++) {
				changeSet.add(playerSet.get(i+1));
				//System.out.println("Card "+i+": "+playerSet.get(i).getName()+" -> "+changeSet.get(i).getName());
			}
			changeSet.add(playerSet.get(0));
			//System.out.println("Card "+(changeSet.size()-1)+": "+changeSet.get((changeSet.size()-1)).getName());
			playerSet.clear();
			playerSet.addAll(changeSet);
			
		}
		else {
			gameWindow.println("Player lost: "+playerSet.get(0).getName());
			playerSet.remove(0);
			gameWindow.println("Player has " +playerSet.size()+" cards remaining!");
			for (int i = 0; i < computerOneSet.size()-1; i++) {
				changeSet.add(computerOneSet.get(i+1));
				//System.out.println("Card "+i+": "+computerOneSet.get(i).getName()+" -> "+changeSet.get(i).getName());
			}
			changeSet.add(computerOneSet.get(0));
			//System.out.println("Card "+(changeSet.size()-1)+": "+changeSet.get((changeSet.size()-1)).getName());
			computerOneSet.clear();
			computerOneSet.addAll(changeSet);
		}
		
	}
	
	public void runRound() {
		boolean doesPlayerWin = false;
		boolean inputValid;
		String choice;
		while (computerOneSet.size() > 0 && playerSet.size() > 0) {
			gameWindow.print("Player is using: "+playerSet.get(0).getName()+"\nComputer is using: "+computerOneSet.get(0).getName()+"\nWhat would you like to pick?");
			do{
				choice = gameWindow.readWord();
				if (choice.equals("h") || choice.equals("w") || choice.equals("l") || choice.equals("f") || choice.equals("i")) {
					switch (choice) {
					case "h" : doesPlayerWin = this.compareHeight(0); break;
					case "w" : doesPlayerWin = this.compareWeight(0); break;
					case "l" : doesPlayerWin = this.compareLength(0); break;
					case "f" : doesPlayerWin = this.compareFerocity(0); break;
					case "i" : doesPlayerWin = this.compareIntel(0); break;
					}
					inputValid = true;
				}
				else { gameWindow.println("Please enter a valid input:");
				inputValid = false;
				}
			} while (inputValid == false);
			this.deckUpdate(doesPlayerWin);
		}
		gameWindow.println("Game Done");
		if (computerOneSet.size() == 0) {
			gameWindow.println("Player Wins");
		}
		else {
			gameWindow.println("Computer Wins");
		}
	}
}
