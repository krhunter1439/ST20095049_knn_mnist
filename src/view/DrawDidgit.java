package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField; 
import model.*;

public class DrawDidgit {
	
	
	
		public DrawDidgit() {}
	public JPanel createPanel(JPanel parentPanel, int frameWidth, int frameHeight) {// Layout DESIGN 
				
		JPanel drawDigitPanel = new JPanel();		
		
		drawDigitPanel.setBounds(0, 0, frameWidth, frameHeight);
		drawDigitPanel.setLayout(new BorderLayout());
		
		JPanel statusPanel = new JPanel(); 
		JLabel numberLabel = new JLabel("Number: ");
		JLabel accuracyLabel = new JLabel("Accuracy:  "); 
		 
		statusPanel.add(numberLabel); 
		statusPanel.add(accuracyLabel);
		
		JTextField filelocationTextFeild = new JTextField(); 
		
		JButton backButton = new JButton("Back");
		JButton recogniseDidgitButton = new JButton("Recognise ");
		//recogniseDidgitButton.setActionCommand("Open File");
		
		drawDigitPanel.add(backButton, BorderLayout.WEST);
		drawDigitPanel.add(filelocationTextFeild, BorderLayout.NORTH);
		drawDigitPanel.add(statusPanel, BorderLayout.CENTER); 
		drawDigitPanel.add(recogniseDidgitButton, BorderLayout.SOUTH);
		drawDigitPanel.setVisible(true);		
		
		backButton.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				try {
	 				if (e.getActionCommand() == "Back") {
	 					CardLayout cl = (CardLayout)(parentPanel.getLayout());
	 					cl.show(parentPanel, "Main Panel");
	 				}
 				} catch (Exception ex) {
 					ex.printStackTrace();
 				}
 		    }
 		});
		
		return drawDigitPanel;
	}
	
	
}

