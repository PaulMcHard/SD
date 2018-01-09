package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Game {

	public static Button heightButton;
	public static Button weightButton;
	public static Button lengthButton;
	public static Button feroButton;
	public static Button intelButton;
	public static Button startButton;

	public Label gameText;
	public Label playerLabel;
	public TextField playerText;

	public static int[] idList = null;
	//public static ArrayList<String> nameList = new ArrayList<String>();
	public static String[] nameList = null;
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

	private boolean isHeightPressed;
	private boolean isWeightPressed;
	private boolean isLengthPressed;
	private boolean isFeroPressed;
	private boolean isIntelPressed;
	private boolean isInitialised = false;
	private boolean isGameDone = false;


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
				nameList = new String[rs.getInt(1)];
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
				//nameList.add(counter, name);
				nameList[counter] = name;
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
			dinoList.add(new Dinosaur(idList[i], nameList[i], heiList[i], weiList[i], lenList[i], ferList[i], itlList[i]));
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
		startMessage();
	}

	public boolean compareHeight(int i) {
		if (playerSet.get(i).getHeight() >= computerOneSet.get(i).getHeight()) {
			gameText.setText("Player Wins Round");
			return true;
		}
		else {
			gameText.setText("Player Loses Round");
			return false;
		}
	}
	public boolean compareWeight(int i) {
		if (playerSet.get(i).getWeight() >= computerOneSet.get(i).getWeight()) {
			gameText.setText("Player Wins Round");
			return true;
		}
		else {
			gameText.setText("Player Loses Round");
			return false;
		}
	}
	public boolean compareLength(int i) {
		if (playerSet.get(i).getLength() >= computerOneSet.get(i).getLength()) {
			gameText.setText("Player Wins Round");
			return true;
		}
		else {
			gameText.setText("Player Loses Round");
			return false;
		}
	}
	public boolean compareFerocity(int i) {
		if (playerSet.get(i).getFerocity() >= computerOneSet.get(i).getFerocity()) {
			gameText.setText("Player Wins Round");
			return true;
		}
		else {
			gameText.setText("Player Loses Round");
			return false;
		}
	}
	public boolean compareIntel(int i) {
		if (playerSet.get(i).getIntel() >= computerOneSet.get(i).getIntel()) {
			gameText.setText("Player Wins Round");
			return true;
		}
		else {
			gameText.setText("Player Loses Round");
			return false;
		}
	}

	public void heightButtonCLicked() {
		playerText.setText("Used Height!");
		isHeightPressed = true;
		runOneRound();
		isHeightPressed = false;

	}
	public void weightButtonCLicked() {
		playerText.setText("Used Weight!");
		isWeightPressed = true;
		runOneRound();
		isWeightPressed = false;

	}
	public void lengthButtonCLicked() {
		playerText.setText("Used Length!");
		isLengthPressed = true;
		runOneRound();
		isLengthPressed = false;

	}
	public void feroButtonCLicked() {
		playerText.setText("Used Ferocity!");
		isFeroPressed = true;
		runOneRound();
		isFeroPressed = false;

	}
	public void intelButtonCLicked() {
		playerText.setText("Used Intelligence!");
		isIntelPressed = true;
		runOneRound();
		isIntelPressed = false;
	}

	public void deckUpdate(boolean playerWin) {
		ArrayList<Dinosaur> changeSet = new ArrayList<Dinosaur>();
		if (playerWin) {
			gameText.setText(gameText.getText()+"\nComputer lost: "+computerOneSet.get(0).getName());
			playerSet.add(computerOneSet.get(0));
			computerOneSet.remove(0);
			gameText.setText(gameText.getText()+"\nComputer has " +computerOneSet.size()+" cards remaining!");
			gameText.setText(gameText.getText()+"\nPlayer has " +playerSet.size()+" cards remaining!");
			for (int i = 0; i < playerSet.size()-1; i++) {
				changeSet.add(playerSet.get(i+1));
			}
			changeSet.add(playerSet.get(0));
			playerSet.clear();
			playerSet.addAll(changeSet);

		}
		else {
			gameText.setText(gameText.getText()+"\nPlayer lost: "+playerSet.get(0).getName());
			computerOneSet.add(playerSet.get(0));
			playerSet.remove(0);
			gameText.setText(gameText.getText()+"\nComputer has " +computerOneSet.size()+" cards remaining!");
			gameText.setText(gameText.getText()+"\nPlayer has " +playerSet.size()+" cards remaining!");
			for (int i = 0; i < computerOneSet.size()-1; i++) {
				changeSet.add(computerOneSet.get(i+1));
			}
			changeSet.add(computerOneSet.get(0));
			computerOneSet.clear();
			computerOneSet.addAll(changeSet);
		}

	}

	public void runOneRound() {

		boolean doesPlayerWin = false;
		if(isInitialised) {
			playerLabel.setText("Player is using: "+playerSet.get(0).getName()+"\nDinosaur has: H:"
					+playerSet.get(0).getHeight()+" W:"
					+playerSet.get(0).getWeight()+" L:"
					+playerSet.get(0).getLength()+" F:"
					+playerSet.get(0).getFerocity()+" I:"
					+playerSet.get(0).getIntel());				

			if(isHeightPressed)	{ doesPlayerWin = this.compareHeight(0);}
			if(isWeightPressed)	{ doesPlayerWin = this.compareWeight(0);}
			if(isLengthPressed)	{ doesPlayerWin = this.compareLength(0);}
			if(isFeroPressed)	{ doesPlayerWin = this.compareFerocity(0);}
			if(isIntelPressed)	{ doesPlayerWin = this.compareIntel(0);}

			this.deckUpdate(doesPlayerWin);
			if(playerSet.size() <= 0 || computerOneSet.size() <= 0 ) {
				gameText.setText("Game Done");
				startButton.setText("Quit");
				endCase();
				if (computerOneSet.size() <= 0) {
				gameText.setText(gameText.getText()+"\nPlayer Wins");
				}
				else if (playerSet.size() <= 0){
					gameText.setText(gameText.getText()+"\nComputer Wins");
				}
			}
			playerLabel.setText("Player is using: "+playerSet.get(0).getName()+"\nDinosaur has: H:"+playerSet.get(0).getHeight()+" W:"+playerSet.get(0).getWeight()+" L:"+playerSet.get(0).getLength()+" F:"+playerSet.get(0).getFerocity()+" I:"+playerSet.get(0).getIntel());
		}

	}
	public void startMessage() {
		playerLabel.setText("Player is using: "+playerSet.get(0).getName()+"\nDinosaur has: H:"+playerSet.get(0).getHeight()+" W:"+playerSet.get(0).getWeight()+" L:"+playerSet.get(0).getLength()+" F:"+playerSet.get(0).getFerocity()+" I:"+playerSet.get(0).getIntel());	
		gameText.setText("Welcome to Top Trumps!\nThe aim of the game is to defeat the computer card by card,\nstat by stat!");
	}

	public void start() {
		if(isGameDone) {
			Main.closeButtonAction();
		}
		else {
		playerSet.clear();
		computerOneSet.clear();
		sqlLoader();
		cardMaker();
		dealCards();
		startMessage();
		isInitialised = true;
		}
	}
	public void endCase() {
		
		isGameDone = true;
	}

}
