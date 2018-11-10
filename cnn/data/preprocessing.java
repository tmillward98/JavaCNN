package cnn.data;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Preprocessing {

	
	/**
	 * Receives an image as an input, separates the image into its specific channels at each pixel (RGB + ALPHA)
	 * Note, it is essential that all images are uniform (of the same size) in order for this network to function
	 * @param image
	 * @return Array of RGBA values
	 */
	
	private int[][][] channelSplitter(BufferedImage image, int height, int width) {
		
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
	
	
	/**
	 * Used to resize any passed images to ensure they're uniform.
	 * Makes use of fast scaling, as quality is less important than speed here
	 * @param image - Image to be resized
	 * @param h - New height
	 * @param w - New width
	 * @return
	 */
	private BufferedImage resizeImage(BufferedImage image, int h, int w) {
		image.getScaledInstance(w, h, Image.SCALE_FAST);
		return image;
	}
	
	
	public static void main(String[] args) {
		

		

	}

}
