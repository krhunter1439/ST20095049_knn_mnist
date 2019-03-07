package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageController {
	
	private BufferedImage bufImg = null;
	private BufferedImage grayscaleImg = null;
	//private BufferedImage edgeImg = null;
	
	public BufferedImage getImage() {
		return bufImg;
	}
	
	public void setImage(BufferedImage providedImage) {
		this.bufImg = providedImage;
	}
	
	
	/*
	public BufferedImage getEdgeImage() {
		if(this.edgeImg == null && this.bufImg != null) {
			return computeImageEdges(this.bufImg);
		}
		else {
			return this.edgeImg;
		}
	}
	*/
	public BufferedImage convertRGBToGrayscale(BufferedImage img) { 
		System.out.println("image has arrrived to be processed");
		// TODO Auto-generated method stub
		// converting image to greyscale first before resizing it to maintain accuracy 
		this.grayscaleImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				
				int rgbvalue = img.getRGB(x,y);

				int alpha = (rgbvalue >> 24) & 0xff;
				int red = (rgbvalue >> 16) & 0xff;
				int green = (rgbvalue >> 8) & 0xff;
				int blue = (rgbvalue) & 0xff;
				
				// grayscale = ( (0.3 * R) + (0.59 * G) + (0.11 * B) ).				
				int grayscale = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
				int new_pixel_value = 0xFF000000 | ( grayscale << 16 ) |
						(grayscale << 8 ) |
						(grayscale);
				
				this.grayscaleImg.setRGB(x, y, new_pixel_value); 
				System.out.println("image converted to grey scale");
				
			}
		}
		return this.grayscaleImg;
	}
	// resizing the image after it has been convrted from RGB to greyscale to improve accuracy as pixel distortion will be less 
	public void resizeGreyScaleImage (int newWidth, int newHeight ) {
		BufferedImage slectedImage = null;
		BufferedImage tempImage = null;
		try { 
			slectedImage = this.getImage();
			java.awt.Image imageForResize = slectedImage.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
			tempImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graph2d = tempImage.createGraphics();
			graph2d.drawImage(imageForResize, 0, 0, null);
			graph2d.dispose();
			this.setImage(tempImage);
			System.out.println("image has been resized ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	public BufferedImage computeImageEdges(BufferedImage img) {
		// TODO Auto-generated method stub
		
		this.edgeImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int row = 0; row < img.getHeight(); row++) {
			for(int col = 0; col < img.getWidth(); col++) {
				
				int new_pixel_value  = 0;
				
				if (col-1 < 0 || row - 1 < 0 || col+1>=img.getWidth() || row+1 >= img.getHeight()){
					new_pixel_value = 0xFF000000;
					this.edgeImg.setRGB(col, row, new_pixel_value);
					continue;
				}
				
				int p1 = img.getRGB(col-1,row-1) & 0xff;
				int p2 = img.getRGB(col,row-1) & 0xff;
				int p3 = img.getRGB(col+1,row-1) & 0xff;
				
				int p4 = img.getRGB(col-1,row) & 0xff;
				int p5 = img.getRGB(col,row) & 0xff;
				int p6 = img.getRGB(col+1,row) & 0xff;
				
				int p7 = img.getRGB(col-1,row+1) & 0xff;
				int p8 = img.getRGB(col,row+1) & 0xff;
				int p9 = img.getRGB(col+1,row+1) & 0xff;
				
				int pixel_value = Math.abs((p1 + 2 * p2 + p3) - (p7 + 2*p8 + p9)) + Math.abs((p3 + 2 * p6 + p9) - (p1 + 2*p4 + p7));
				
				new_pixel_value = 0xFF000000 | ( pixel_value << 16 ) |
						(pixel_value << 8 ) |
						(pixel_value);
				
				this.edgeImg.setRGB(col, row, new_pixel_value);
				
			}
		}
		return this.edgeImg;	   
	}
	   */
	   //Euclidian distance: p1=(w1,x1,y1,z1), p2(w2,x2,y2,z2):
	   //Euc. Dist. (p1,p2) = SQRT((w1-w2)^2+(x1-x2)^2+(y1-y2)^2+(z1-z2)^2)
	   
	
	   }
	

	

