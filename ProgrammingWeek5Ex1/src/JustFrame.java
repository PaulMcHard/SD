/**
 A simple class to show a single frame
*/

//import java classes for GUI components
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class JustFrame extends JFrame
{  
    public JustFrame() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(300,300);
	setLocation(100,100);
	setTitle("Just a JFrame");
	JPanel myPanel = new JPanel();
	JButton butts = new JButton("hi");
	myPanel.setBackground(Color.MAGENTA);
	this.add(myPanel, BorderLayout.CENTER);
	this.add(butts, BorderLayout.SOUTH);
	
    }
}
