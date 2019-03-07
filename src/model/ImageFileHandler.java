package model;

import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class ImageFileHandler extends FileHandler {

	int width;
	int height;
	String image_type;
	BufferedImage img;
	
	public ImageFileHandler() {}
	
	public ImageFileHandler(String file_name) {
		super(file_name);
		img = null;
	}
	
	@Override
	public void readFile() throws IOException {
		if (fp.isFile() && fp.exists()) {
			img = ImageIO.read(fp);
		}
	}

	@Override
	public void writeFile(String file_name) throws IOException {
		// TODO Auto-generated method stub

	}
	
	public void displayImage() {
		//Display loaded image in a frame
		JLabel picLabel = new JLabel(new ImageIcon(img));
		JPanel newPanel = new JPanel();
		newPanel.add(picLabel);
		JFrame newFrame = new JFrame();
		newFrame.setSize(new Dimension(img.getWidth(), img.getHeight()));
		newFrame.add(newPanel);
		newFrame.setVisible(true);
	}

	public BufferedImage getImage() {
		return this.img;
	}
	
	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFile(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSourceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSourceName(String source_Name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDestinationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDestinationName(String dest_Name) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFileSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFileSize(int fileSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIsDirectory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIsDirectory(int DirectoryValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFileType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFileType(String fileType) {
		// TODO Auto-generated method stub

	}

}
