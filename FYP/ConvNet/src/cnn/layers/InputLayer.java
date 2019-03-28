package cnn.layers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FilenameFilter;
import javax.imageio.ImageIO;


public class InputLayer extends Layer {
	
	private ArrayList<double[][]> input;
	private Layer nextLayer;
	
	public int getCount() {
		return 1;
	}
	
	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		nextLayer = nl;
		return exampleInput;
	}
	
	public ArrayList<double[][]> forwardPropagate(){
		return input;
	}

	public void loadImages(String directory) {
		
		input = new ArrayList<double[][]>();
		
		final File dir = new File(directory);
		final String EXTENSIONS[] = new String[] {"jpg", "png", "bmp"};
		
	    final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

	        @Override
	        public boolean accept(final File dir, final String name) {
	            for (final String ext : EXTENSIONS) {
	                if (name.endsWith("." + ext)) {
	                    return (true);
	                }
	            }
	            return (false);
	        }
	    };
	    
	    ArrayList<BufferedImage> loadedImages = new ArrayList<BufferedImage>();
	    BufferedImage img = null;
	    
	    for(final File f: dir.listFiles(IMAGE_FILTER)) {
	    	try {
	    		img = ImageIO.read(f);
	    		loadedImages.add(img);
	    		
	    	}
	    	catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }
		
		
		//Convert images to pixel array
		double[][] temp = new double[loadedImages.get(0).getWidth()][loadedImages.get(0).getHeight()];
		for(int i = 0; i < loadedImages.size(); i++) {	
			for(int x = 0; x < loadedImages.get(0).getWidth(); x ++) {
				for(int y = 0; y < loadedImages.get(0).getHeight(); y++) {
					temp[x][y] = loadedImages.get(i).getRGB(x, y);
				}
			}	
			input.add(temp);
		}
	}
}