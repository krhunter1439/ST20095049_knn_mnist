package view;

import javax.swing.*;
import model.*;
import java.awt.*; 
import java.awt.event.*;
import java.io.*;

public class ImageUpload {
	
	public File selectedFile = null;
	public String selectedFilePath;
	public ImageController ICObject;
	public MNISTDataItemLoader DILoader;
	
    
	public ImageUpload() {}
	
	public JPanel createPanel(JPanel parentPanel, int frameWidth, int frameHeight) {
		JPanel imageUploadPanel = new JPanel();		
		
		imageUploadPanel.setBounds(0, 0, frameWidth, frameHeight);
		imageUploadPanel.setLayout(new BorderLayout());
		
		JButton backButton = new JButton("Back");		
		JButton openFileButton = new JButton("Open File");
		JButton AnalyseButton = new JButton("Analyse Image");
				
		JPanel statusPanel = new JPanel(); 
		JLabel numberLabel = new JLabel();
		JLabel accuracyLabel = new JLabel();  
	
		
		 
		statusPanel.add(numberLabel); 
		statusPanel.add(accuracyLabel);
		
		JTextField filelocationTextFeild = new JTextField();
		
		imageUploadPanel.add(backButton, BorderLayout.WEST);
		imageUploadPanel.add(filelocationTextFeild, BorderLayout.NORTH);
		imageUploadPanel.add(statusPanel, BorderLayout.CENTER); 
		imageUploadPanel.add(AnalyseButton, BorderLayout.EAST);
		imageUploadPanel.add(openFileButton, BorderLayout.SOUTH); 
		

		AnalyseButton.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (selectedFilePath != null)  {
						ImageFileHandler currentfileHandler = new ImageFileHandler(selectedFilePath); 
				        currentfileHandler.readFile(); 
				        ICObject = new ImageController();
				        ICObject.setImage(currentfileHandler.getImage());
				        ICObject.convertRGBToGrayscale(ICObject.getImage());
				        ICObject.resizeGreyScaleImage(28, 28);
				        DILoader = new MNISTDataItemLoader();
				        DILoader.setImageController(ICObject);
				        DILoader.loadItemArray();
				      DILoader.computingEcludianDidst();
				   
				        int recognizedDigit = DILoader.getRecogniseDidgit();
				       double confidenceMeasure = DILoader.getConfidence(3); 
				        numberLabel.setText("Number: " + recognizedDigit);
				        accuracyLabel.setText("Accuracy: %" + confidenceMeasure );
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	    	}
		});
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
 					CardLayout cl = (CardLayout)(parentPanel.getLayout());
 					cl.show(parentPanel, "Main Panel");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		    }
		});
		
		//filelocationTextFeild.addActionListener();
		
		openFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
	 				if (e.getActionCommand() == "Open File") {
	 					JFileChooser filePicker = new JFileChooser();
		      			int isFileSelected = filePicker.showOpenDialog(imageUploadPanel);
		      			if (isFileSelected == JFileChooser.APPROVE_OPTION) {
		      				selectedFile = filePicker.getSelectedFile(); 
		      				selectedFilePath = selectedFile.getAbsolutePath(); 
		      				filelocationTextFeild.setText(selectedFilePath);
		      				// add in another button to send selected file to be compared against the dataset. 
		      				
		      			}
	 				}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		    }
		});
		
		
		
		return imageUploadPanel;
	}
}
