package cnn.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
		ArrayList<BufferedImage> loadedImages = new ArrayList<BufferedImage>();
		
		try(Stream<Path> paths = Files.walk(Paths.get(directory))) {
			paths
				.filter(Files::isRegularFile)
				//.forEach(loadedImages.add());
				//.forEach(loadedImages.add(loadImage()));
				.forEach(System.out::println);
				
			
		} catch (IOException e) {
			
		}
		
		
		return loadedImages;
	} 
	
	public static void main(String[] args) {
		String test = "C:\\Users\\Tom\\Pictures";
		ImageLoader loader = new ImageLoader();
		loader.massLoad(test);

	}
	
}
