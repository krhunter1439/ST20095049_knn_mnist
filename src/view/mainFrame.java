package view;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import model.*;
/* mainPanel: the UI for this java program uses the card layout format. this allows the user to easily switch between the 
 * two different options, either 1. imageUpload or 2. Draw Digit. Both of these panels also contain a back button if 
 * if the user is required to change between the two with ease */


@SuppressWarnings("unused")
public class mainFrame {
	
	public static JFrame currentMainFrame;
	public static JButton imageUploadButton = new JButton("IMAGE UPLOAD");
	public static JButton drawDigitButton = new JButton("DRAW DIGIT");
	public static JPanel appStartPanel = new JPanel(new CardLayout());
	public static Container cp;
     
    public static void loadWindow() {
    	
    	try {
    		// creating the layout of the main panel and creating the card layout format 
	    	currentMainFrame = new JFrame("START");
	    	currentMainFrame.setSize(600,600);
	    	currentMainFrame.setLayout(appStartPanel.getLayout()); 		
	 		DrawDidgit newDrawDidgitPanel = new DrawDidgit();
	 		JPanel currentDDPanel = newDrawDidgitPanel.createPanel(appStartPanel, currentMainFrame.getWidth(), currentMainFrame.getHeight());  
	 		
	 		ImageUpload newImageUploadPanel = new ImageUpload();
	 		JPanel currentIUPanel = newImageUploadPanel.createPanel(appStartPanel, currentMainFrame.getWidth(), currentMainFrame.getHeight());
	 		
	 		
	 		JPanel mainPanel = new JPanel();
	 		mainPanel.setLayout(new GridLayout(5,1)); 		
	 		JLabel accuracyLabel1 = new JLabel("PLEASE CHOOSE AN OPTION "); 		
	 		mainPanel.add(accuracyLabel1); 		
	 		mainPanel.add(imageUploadButton);
	 		mainPanel.add(drawDigitButton);
	 		//Action listner to open the Draw digit panel
	 		drawDigitButton.addActionListener(new ActionListener() {
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 				try {
		 				if (e.getActionCommand() == "DRAW DIGIT") {
		 					CardLayout cl = (CardLayout)(appStartPanel.getLayout());
		 					cl.show(appStartPanel, "Draw Digit");
		 				}
	 				} catch (Exception ex) {
	 					ex.printStackTrace();
	 				}
	 		    }
	 		});
	 		//Action listner to open the imageUpload pannel 
	 		imageUploadButton.addActionListener(new ActionListener() {
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 				try {
		 				if (e.getActionCommand() == "IMAGE UPLOAD") {
		 					CardLayout cl = (CardLayout)(appStartPanel.getLayout());
		 					cl.show(appStartPanel, "Image Upload");
		 					System.out.println("Image Upload has been selected");
		 				}
	 				} catch (Exception ex) {
	 					ex.printStackTrace();
	 				}
	 		    }
	 		}); 
	 		
	 		// the cards that are able to be displayed. they are added as needed 
	 		appStartPanel.add(mainPanel, "Main Panel");
	 		appStartPanel.add(currentDDPanel, "Draw Digit");
	 		appStartPanel.add(currentIUPanel, "Image Upload");
	 		currentMainFrame.add(appStartPanel);
	 		
	 		//Puts the window in the middle of the screen.
	 		currentMainFrame.setLocationRelativeTo(null);
	 		currentMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 		currentMainFrame.setVisible(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public static void main(String[] args) {
		loadWindow();
	}
}

