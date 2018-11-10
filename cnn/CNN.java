package cnn;
import java.util.ArrayList;
import cnn.objects.Layer;

/**
 * This is the base layer of the convolutional neural network. 
 * Essentially, this object will handle the layers, and the order in which they are processed.
 * 
 * Calculations for the layers will be handled within the layer, and passed back to the neural network in the end 
 * in order to determine the overall learning rate.
 * 
 * Using the data returned, the network can then further experiment with the number of neurons/layers, randomising the amount used,
 * thus being able to determine the optimal number of layers/neurons per layer to achieve a better learning rate.
 * 
 * Effectively learning how to learn.
 * 
 * @author Tom
 *
 */


public class CNN {
	
	private int epochs;
	private int layers;
	private int seed;
	
	private ArrayList<Layer> networkLayers = new ArrayList<Layer>();
	
	//Null constructor
	CNN(){
		epochs = 100;
		layers = 3;
		seed = 1234;
	}
	
	/**
	 * Constructor
	 * @param e - no. of epochs
	 * @param l - no. of layers 
	 * @param s - random seed
	 */
	CNN(int e, int l, int s){
		epochs = e;
		layers = l;
		seed = s;
	}
	

	
	
	
	
}
