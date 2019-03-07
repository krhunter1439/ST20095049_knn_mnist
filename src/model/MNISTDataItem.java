package model;

import java.awt.image.*;
import java.io.*;

public class MNISTDataItem {
	
	private int MNISTlbl; 
	private BufferedImage MNISTbuffimage;
	private double KnnDistanceValue; 
	
	
	public MNISTDataItem() {	}
	 
	public int getMNISTlbl() {
		return this.MNISTlbl;
	}
	
	public void setMNISTlbl(int suppliedLbl) {
		this.MNISTlbl = suppliedLbl;
	}
	
	public BufferedImage getMNISTbuffimage()  {
		return this.MNISTbuffimage;
	}

	public void setMNISTbuffimage(BufferedImage tempimage) {
		this.MNISTbuffimage = tempimage;
	}
	
	public double getKnnDistanceValue() {
		return KnnDistanceValue;
	} 
	
	public void setKnnDistanceValue(double tempValue) {
		this.KnnDistanceValue = tempValue;
	}
}

