package cnn.data;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Preprocessing {

	
	/**
	 * Receives an image as an input, separates the image into its specific channels at each pixel (RGB + ALPHA)
	 * Note, it is essential that all images are uniform (of the same size) in order for this network to function
	 * @param image
	 * @return Array of RGBA values
	 */
	
	private int[][][] channelSplitter(BufferedImage image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		//Declares array of size equal to the height and width for the image
		int[][][] RGBAValues = new int[width][height][4];
		
		//Loop for each pixel, mapping the RGBA values at each pixel
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				RGBAValues[x][y][0] = new Color(image.getRGB(x, y)).getAlpha();
				RGBAValues[x][y][1] = new Color(image.getRGB(x, y)).getRed();
				RGBAValues[x][y][2] = new Color(image.getRGB(x, y)).getBlue();
				RGBAValues[x][y][3] = new Color(image.getRGB(x, y)).getGreen();
			}
		}
		
		return RGBAValues;
	}
	
	
	public static void main(String[] args) {
		

		

	}

}
