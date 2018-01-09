import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/** 
 * Programming AE2
 * Class to display cipher GUI and listen for events
 */
public class CipherGUI extends JFrame implements ActionListener
{
	//instance variables which are the components
	private JPanel top, bottom, middle;
	private JButton monoButton, vigenereButton;
	private JTextField keyField, messageField;
	private JLabel keyLabel, messageLabel;

	//application instance variables
	//including the 'core' part of the textfile filename
	//some way of indicating whether encoding or decoding is to be done
	private MonoCipher mcipher;
	private VCipher vcipher;

	//Keyword, filename and last letter of filename are variables that must be accessible by multiple methods, so are declared here.
	private String key;
	private String fileIn;
	private String fileOut;
	private char ender;
	/**
	 * The constructor adds all the components to the frame
	 */
	public CipherGUI()
	{
		this.setSize(400,150);
		this.setLocation(100,100);
		this.setTitle("Cipher GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.layoutComponents();
	}

	/**
	 * Helper method to add components to the frame
	 */
	public void layoutComponents()
	{
		//top panel is yellow and contains a text field of 10 characters
		top = new JPanel();
		top.setBackground(Color.yellow);
		keyLabel = new JLabel("Keyword : ");
		top.add(keyLabel);
		keyField = new JTextField(10);
		top.add(keyField);
		this.add(top,BorderLayout.NORTH);

		//middle panel is yellow and contains a text field of 10 characters
		middle = new JPanel();
		middle.setBackground(Color.yellow);
		messageLabel = new JLabel("Message file : ");
		middle.add(messageLabel);
		messageField = new JTextField(10);
		middle.add(messageField);
		this.add(middle,BorderLayout.CENTER);

		//bottom panel is green and contains 2 buttons

		bottom = new JPanel();
		bottom.setBackground(Color.green);
		//create mono button and add it to the top panel
		monoButton = new JButton("Process Mono Cipher");
		monoButton.addActionListener(this);
		bottom.add(monoButton);
		//create vigenere button and add it to the top panel
		vigenereButton = new JButton("Process Vigenere Cipher");
		vigenereButton.addActionListener(this);
		bottom.add(vigenereButton);
		//add the top panel
		this.add(bottom,BorderLayout.SOUTH);
	}

	/**
	 * Listen for and react to button press events
	 * (use helper methods below)
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e) {
		boolean success = false;
		//getKeyword and processFileName both return boolean values if input is valid/invalid, as well as handling actions on respective inputs.
		//Thus they can be used in an if statement as we only move forward with processing file if both inputs are valid.
		if(getKeyword() && processFileName()) {
			if (e.getSource() == monoButton) {
				success = processFile(false); //process file using mono cipher, returns true if no errors encountered.
			}
			else if(e.getSource() == vigenereButton) {
				success = processFile(true); //similarly process file using vigenere cipher.
			}
			if (success == true) {
				//Notify user that process has been completed sucessfully. Warnings of error are handled by relevant methods.
				JOptionPane.showMessageDialog(null, "File successfully processed.");
			}
			System.exit(0);
		}
	}


	/** 
	 * Obtains cipher keyword
	 * If the keyword is invalid, a message is produced
	 * @return whether a valid keyword was entered
	 */
	private boolean getKeyword()
	{
		key = keyField.getText();
		//check the keyword is not empty, contains only capital letters and contains no repeated characters.
		if(key.isEmpty() || !key.equals(key.toUpperCase()) || !checkRepeated(key) ) {
			JOptionPane.showMessageDialog(null, "Require a valid keyword");
			keyField.setText("");
			return false;
		}
		return true;  
	}
	
	/**  checks if a character is repeated in the keyword.
	 *  Uses a nested loop to check if a given character in the keyword appears anywhere else in the keyword.
	 *  returns false if a repeat is found.
	 */
	private boolean checkRepeated(String keyword) {
		char [] keyIn = new char[keyword.length()]; 
		for(int i=0; i < keyword.length(); i++) {
			keyIn[i] =keyword.charAt(i);
			for(int j=0; j<i; j++) {
				if(keyIn[i] == keyIn[j]) {
					return false;
				}
			}
		}
		return true;
	}

	/** 
	 * Obtains filename from GUI
	 * The details of the filename and the type of coding are extracted
	 * If the filename is invalid, a message is produced 
	 * The details obtained from the filename must be remembered
	 * @return whether a valid filename was entered
	 */
	private boolean processFileName()
	{
		fileIn = messageField.getText();
		ender = fileIn.charAt(fileIn.length() - 1);
		fileOut = fileIn.substring(0, fileIn.length()-1);
		if(ender == 'P') {
			fileOut+="C";
			return true;
		}
		if(ender == 'C') {
			fileOut+="D";
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Require a P or C file excluding .txt extension.");
			messageField.setText("");
			return false;
		}

	}

	/** 
	 * Reads the input text file character by character
	 * Each character is encoded or decoded as appropriate
	 * and written to the output text file
	 * @param vigenere whether the encoding is Vigenere (true) or Mono (false)
	 * @return whether the I/O operations were successful 
	 */
	private boolean processFile(boolean vigenere)
	{
		try {
			FileReader readFile = new FileReader(fileIn+".txt");
			Scanner scan = new Scanner(readFile);
			String readLine;
			FileWriter writeFile = new FileWriter(fileOut+".txt");
			writeFile.flush(); //clears target file of any current contents.
			LetterFrequencies freqCounter = new LetterFrequencies();
			if(!vigenere) {
				mcipher = new MonoCipher(key);
			}
			else {
				vcipher = new VCipher(key);
			}
			while(scan.hasNextLine()) {
				readLine = scan.nextLine();
				char[] charsIN = new char[readLine.length()];
				char[] charsOUT = new char[readLine.length()];
				StringBuilder outstring = new StringBuilder();
				if (!vigenere) {
					for(int i=0; i<readLine.length(); i++) {
						charsIN[i]= readLine.charAt(i);
						if (ender == 'P') {
							charsOUT[i]=mcipher.encode(charsIN[i]);
						}
						else if(ender == 'C') {
							charsOUT[i]=mcipher.decode(charsIN[i]);
						}
						freqCounter.addChar(charsOUT[i]);
						outstring.append(charsOUT[i]);
					} 
					//System.out.println(readLine);
					//System.out.println(outstring);
					writeFile.write(outstring+"\n");
				}

				else {
					for(int i=0; i<readLine.length(); i++) {
						charsIN[i]= readLine.charAt(i);
						if (ender == 'P') {
							charsOUT[i]=vcipher.encode(charsIN[i]);
						}
						else if(ender == 'C') {
							charsOUT[i]=vcipher.decode(charsIN[i]);
						}
						freqCounter.addChar(charsOUT[i]);
						outstring.append(charsOUT[i]);
					} 
					//System.out.println(readLine);
					System.err.println(outstring);
					writeFile.write(outstring+"\n");
				}

			}    
			FileWriter freqReport = new FileWriter((fileIn.substring(0, fileIn.length()-1))+"F.txt");
			freqReport.write(freqCounter.getReport());
			writeFile.close(); 
			freqReport.close();
		} catch (FileNotFoundException fnf) {
			JOptionPane.showMessageDialog(null, "File Could Not Be Found. Make sure it is in src directory.");
			messageField.setText("");
			fnf.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
}
