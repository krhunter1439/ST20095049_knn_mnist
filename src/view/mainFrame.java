package view;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import model.*;



@SuppressWarnings("unused")
public class mainFrame {
	
	public static JFrame currentMainFrame;
	public static JButton imageUploadButton = new JButton("IMAGE UPLOAD");
	public static JButton drawDigitButton = new JButton("DRAW DIGIT");
	public static JPanel appStartPanel = new JPanel(new CardLayout());
	public static Container cp;
     
    public static void loadWindow() {
    	
    	try {
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
	 		imageUploadButton.addActionListener(new ActionListener() {
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 				try {
		 				if (e.getActionCommand() == "IMAGE UPLOAD") {
		 					CardLayout cl = (CardLayout)(appStartPanel.getLayout());
		 					cl.show(appStartPanel, "Image Upload");
		 				}
	 				} catch (Exception ex) {
	 					ex.printStackTrace();
	 				}
	 		    }
	 		});
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

