package model;

import java.awt.image.*;
import java.util.*;
import java.io.*; 
//import view.*;

public class MNISTDataItemLoader extends MNISTDataItem {

	private ImageController tempImageController = null;
	// import MNIST images
	String train_label_filename = "train-labels.idx1-ubyte";
	String train_image_filename = "train-images.idx3-ubyte";
	
	FileInputStream in_stream_labels = null;
	FileInputStream in_stream_images = null;
	
	DataInputStream dataStreamLables = null; 
	DataInputStream dataStreamImages = null; 
	
	private MNISTDataItem[] currentDataItems = null;
	private int recognizedDigit;
	
	public ImageController getImageController() {
		return this.tempImageController;
	}
	
	public void setImageController (ImageController providedIC) {
		this.tempImageController = providedIC;
	}
	
	public MNISTDataItem[] getDIArray() {
		return this.currentDataItems;
	}
	
	public void setDIArray(MNISTDataItem[] providedDIArray) {
		this.currentDataItems = providedDIArray;
	}	
	//creating and populating the array 
	public void loadItemArray() { 
		System.out.println("start of MNIST KNN");
		System.out.println("load array");
		
		try {
			in_stream_labels = new FileInputStream(new File(train_label_filename));
			in_stream_images = new FileInputStream(new File(train_image_filename));
	
			dataStreamLables = new DataInputStream(in_stream_labels);
			dataStreamImages = new DataInputStream(in_stream_images); 
			
			int labelStartCode = dataStreamLables.readInt(); 
			int labelCount = dataStreamLables.readInt();
			int imageStartCode = dataStreamImages.readInt();
			int imageCount = dataStreamImages.readInt();
			int imageHeight = dataStreamImages.readInt(); 
			int imageWidth = dataStreamImages.readInt();
			
			currentDataItems = new MNISTDataItem[labelCount]; 
			
			int imageSize = imageHeight * imageWidth; 
			byte[] labelData = new byte[labelCount];
			byte[] imageData = new byte[imageSize * imageCount];
			BufferedImage tempImage;
			
			dataStreamLables.read(labelData);
			dataStreamImages.read(imageData);
			
			for (int currentRecord = 0; currentRecord < labelCount; currentRecord++) {
				int currentLabel = labelData[currentRecord];				
				MNISTDataItem newlabeledimage = new MNISTDataItem();
				newlabeledimage.setMNISTlbl(currentLabel);			
				tempImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);				
				int[][] imageDataArray = new int[imageWidth][imageHeight];
				for (int row = 0; row < imageHeight; row++) {
					for(int column = 0; column < imageWidth; column++) { 
						imageDataArray[column][row] = imageData[(currentRecord * imageSize)+((row*imageWidth) + column)] | 0xFF000000;
						tempImage.setRGB(column, row, imageDataArray[column][row]);
					}
					
				}
				
				newlabeledimage.setMNISTbuffimage(tempImage); 
				currentDataItems[currentRecord] = newlabeledimage;
			}
			if (in_stream_labels != null) {
				in_stream_labels.close();
			}
			if (in_stream_images != null) {
				in_stream_images.close();
			}			
		} catch (FileNotFoundException fn) {
			fn.printStackTrace();	   
		} catch (IOException e)	{
		   
		   e.printStackTrace();
	   }
	
	}
	
	// ADD IN ARRAY SORT !!!!! BY KNN DISTANCE VALUE , FUNCTIONS // BUBBLE SOrt ????? 
	
	public void sortArray() {  
		System.out.println("array sort ");
	    int n = currentDataItems.length;  
	    MNISTDataItem tempDI = null;  
	    for(int i = 0; i < n; i++){  
	    	for(int j = 1; j < (n-i); j++){  
	    		if(currentDataItems[j - 1].getKnnDistanceValue() > currentDataItems[j].getKnnDistanceValue()){                       
                   tempDI = currentDataItems[j - 1];  
                   currentDataItems[j - 1] = currentDataItems[j];  
                   currentDataItems[j] = tempDI;  
	    		}                  
	    	}  
	    }	
	}
 
	public void computingEcludianDidst() throws NullPointerException {
		System.out.println("start Distance");
		MNISTDataItem[] processedMDIArray = new MNISTDataItem[this.getDIArray().length];
		MNISTDataItem[]  tempDataArray = this.getDIArray();
		BufferedImage ComparisonImage= tempImageController.getImage();
		if (ComparisonImage != null) {
			int currentImageWidth = ComparisonImage.getWidth();
			int currentImageHeight = ComparisonImage.getHeight();
			double mseValue = 0.0;
			double squareSum = 0.0; 
			for(int i =0; i < tempDataArray.length; i++) {
				MNISTDataItem currentComparisonMNISTDataItem = tempDataArray[i]; 
				BufferedImage currentComparisonImage = currentComparisonMNISTDataItem.getMNISTbuffimage();
				for (int y = 0; y < currentImageHeight; y++) {
					for(int x = 0; x < currentImageWidth; x++) {
					int imagetoComparePixels = ComparisonImage.getRGB(x, y);
				    int currentComparisonImagePixelValue =  currentComparisonImage.getRGB(x, y); 
				    squareSum += (Math.pow((imagetoComparePixels - currentComparisonImagePixelValue), 2.0));
				    
					}
				}
				mseValue = squareSum / (currentComparisonImage.getWidth() * currentComparisonImage.getHeight());
				currentComparisonMNISTDataItem.setKnnDistanceValue(mseValue);
				processedMDIArray[i] = currentComparisonMNISTDataItem;
				
			}
			this.setDIArray(processedMDIArray);			
			System.out.println("end Distance");
		}
	}
	
	public double getConfidence( int k ) {   
		
		System.out.println("start knn");
		int maxCountLabel = 0; 
	    int maxCount = 0;
	    try {
	    	this.sortArray(); 
	    	int differenceCount = 1 ;  
	    	
	    	for(int firstCount = 0; firstCount < k; firstCount++ ) {
	    		MNISTDataItem LIatIndex = get(firstCount);
	    		MNISTDataItem currentLabel = currentDataItems[firstCount];
	    		System.out.println("first Count");
	    		
	    	}
	    	
	    	for(int secondCount = 0; secondCount < k; secondCount++ ) {
	    		if (secondCount > 0  ) {
	    			MNISTDataItem objectAtCurrentIndex = currentDataItems[secondCount];
	    			MNISTDataItem objectAtPreviousIndex = currentDataItems[secondCount - 1]; 
	    			if (objectAtCurrentIndex.getMNISTlbl()  !=  objectAtPreviousIndex.getMNISTlbl() ) {
	    				differenceCount++; 
	    				System.out.println("second Count");
	    			}
	    		}
	    	}
	    	int[] labelTrackingArray = new int[differenceCount]; 
	    	int[] countTrackingArray = new int[differenceCount];
	    	double[] distanceTrackingArray = new double[differenceCount]; 
	    	int count = 1; 
	    	int internalDifferenceCount = 0; 
	    	for (int thirdCount = 0; thirdCount < k; thirdCount++) {
	    		if(internalDifferenceCount <= differenceCount) {
	    			int trackedLabel = currentDataItems[thirdCount].getMNISTlbl();
	    			double trackedDistance = currentDataItems[thirdCount].getKnnDistanceValue(); 
	    			if (thirdCount > 0) {
	    				MNISTDataItem selectedObjectAtCurrentIndex = currentDataItems[thirdCount] ;
	    				MNISTDataItem selectedObjectAtPreviousIndex = currentDataItems[thirdCount - 1];
	    				if(selectedObjectAtCurrentIndex.getMNISTlbl() == selectedObjectAtPreviousIndex.getMNISTlbl()) {
	    					count++;
	    				}
	    				else {
	    					trackedLabel = selectedObjectAtCurrentIndex.getMNISTlbl();
	    					trackedDistance = selectedObjectAtCurrentIndex.getKnnDistanceValue();
	    					count = 1; 
	    					internalDifferenceCount++;
	    				}
	    				
	    			}
	    			
	    			labelTrackingArray[internalDifferenceCount] = trackedLabel;
	    			countTrackingArray[internalDifferenceCount] = count;
	    			distanceTrackingArray[internalDifferenceCount] = trackedDistance; 
	    			}
	    	}
	    	System.out.println("third Count");
	    	 for (int fourthCount = 0; fourthCount < differenceCount; fourthCount++) {
	    		 if(fourthCount > 0) {
	    			 if(distanceTrackingArray[fourthCount - 1] < distanceTrackingArray[fourthCount]) {
	    				 if(countTrackingArray[fourthCount] >= countTrackingArray[fourthCount - 1]) {
	    					 maxCountLabel = labelTrackingArray[fourthCount - 1]; 
	    					 maxCount = countTrackingArray[fourthCount - 1];
	    				 }
	    			 }
	    			 else {
	    				 maxCountLabel = labelTrackingArray[fourthCount];
	    				 maxCount = countTrackingArray[fourthCount];
	    			 }
	    		 }
	    			 else {
	    				 maxCountLabel = labelTrackingArray[fourthCount]; 
	    				 maxCount = countTrackingArray[fourthCount];
	    				 }
	    		 }
	    	 System.out.println("fourth count");
	    	 } catch (Exception e) {
	    		 e.printStackTrace();
	    	 }
	    	this.setRecogniseDidgit(maxCountLabel);
		    double accuracyratio = ((double) maxCount / k ) * 100; 
			return accuracyratio;	
		}
	
		private MNISTDataItem get(int firstCount) {
		// TODO Auto-generated method stub
		return null;
	}
/*
		
		this.sortArray(); 
		int[] labelArray = new int [k];
		MNISTDataItem[] tempMDIArray = this.getDIArray();
		for(int internalCount = 0; internalCount < k; internalCount++) { 
			MNISTDataItem lblIndex = tempMDIArray[internalCount];
			labelArray[internalCount] = lblIndex.getMNISTlbl();
		}
		int maxCount =  0; 
		int maxCountLabel = 0; 
		int [][ ] countTrackingArray = new int[9][1];
		for (int selectedNumber = 1; selectedNumber <= 9; selectedNumber++) {
			int totalCount = 0; 
			for(int actualCount = 0; actualCount < labelArray.length; actualCount++) {
				if (labelArray[actualCount] == selectedNumber) {
					totalCount = totalCount + 1;
					
				}
			}
			countTrackingArray[(selectedNumber-1) ][0] = totalCount;			
		}
		for (int iteratedNumber =  0; iteratedNumber < 9; iteratedNumber++) {
			if (maxCount < countTrackingArray[iteratedNumber][0]) {
				maxCount = countTrackingArray[iteratedNumber][0]; 
				maxCountLabel  = iteratedNumber + 1; 
			}
		}	
		setRecogniseDidgit(maxCountLabel);
		double accuracyratio = ((double) maxCount / k) * 100; 
		return accuracyratio;
		
	}	
	*/
	public int getRecogniseDidgit() {
		return this.recognizedDigit;
	}
		public void setRecogniseDidgit( int suppliedDidgit) {
		this.recognizedDigit = suppliedDidgit;
		System.out.println("end KNN");
	}

}
