package cnn.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

/**
 * This objects handles all of the image loading aspects, i.e. loading a particular image or set
 * of images into an array for the network to use. 
 * 
 * Particularly, this class should be useful for both single images and loading training sets.
 * 
 * @author Tom
 *
 */

public class ImageLoader {

	
	/**
	 * Single image load function
	 * @param directory - passed where the image is saved
	 * @return - returns the loaded image
	 */
	BufferedImage loadImage(String directory) {
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(directory + ".jpg"));
		} catch (IOException e) {
			
		}
		
		return img;
	}
	
	/**
	 * Mass loads all of the images within a given directory and returns them
	 * as an ArrayList. 
	 * @param directory - working directory
	 * @return
	 */
	
	ArrayList<BufferedImage> massLoad(String directory){
		
		/**
		 * This sets the program to read the console output, and store it within a string.
		 */
		ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
		PrintStream reader = new PrintStream(consoleOutput);
		PrintStream old = System.out;
		System.setOut(reader);
		
		ArrayList<BufferedImage> loadedImages = new ArrayList<BufferedImage>();
		
		try(Stream<Path> paths = Files.walk(Paths.get(directory))) {
			paths
				.filter(Files::isRegularFile)
				.forEach(System.out::println);
			
			
			
		} catch (IOException e) {
			
		}
		
		System.out.flush();
		System.setOut(old);
		
		String[] directories = consoleOutput.toString().split("\n");
		
		for(int x = 0; x < directories.length; x++) {
			if(directories[x].contains(".jpg") == true) {
				System.out.println("Image loaded: " + directories[x]);
				loadedImages.add(loadImage(directories[x]));
			}
		}
		
		return loadedImages;
	} 
}


/**	
	//TEST FUNCTIONS
	public static void main(String[] args) {
		
		//MASS LOAD FUNCTIONALITY TEST
		String test = "C:\\Users\\Tom\\Pictures";
		ImageLoader loader = new ImageLoader();
		loader.massLoad(test);

	}
	
}
**/