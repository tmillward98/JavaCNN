package cnn.layers;

import java.util.ArrayList;
import java.util.Arrays;

import cnn.layers.neurons.ConvNeuron;

/**
 * Convolutional layer handles all things related to convolution neurons
 * Each neuron should have its own kernel and generate a suitable output
 * These outputs are stored within an ArrayList as output, and passed to the next layer
 * @author Tom
 *
 */
public class ConvolutionLayer extends Layer {

	private int conD;
	private int stride;
	private int k;
	private ArrayList<double[][]> input;
	private ArrayList<double[][]> output;
	private ArrayList<ConvNeuron> neurons;
	private Layer previousLayer;
	private Layer nextLayer;
	
	public int getCount() {
		return k;
	}
	
	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		nextLayer = nl;
		previousLayer = pl;
		input = exampleInput;
		convolve();
		return output;
	}
	
	/**
	 * Retrieve previous layers output
	 * Do calculations
	 * Pass output forward
	 */
	public ArrayList<double[][]> forwardPropagate(){
		input = previousLayer.forwardPropagate();
		convolve();
		return output;
	}
		
	/**
	 * Constructor
	 * Hard-coded values for stride and conD (3x3 matrix with stride 1) with k feature kernels
	 */
	public ConvolutionLayer(){
		output = new ArrayList<double[][]>();
		neurons = new ArrayList<ConvNeuron>();
		conD = 3;
		stride = 1;
		k = 10;
		
		for(int n = 0; n < k; n++) {
			ConvNeuron a = new ConvNeuron(conD, stride);
			neurons.add(a);
		}	
	}
	
	/**
	 * Perform convolution on each image with each neuron
	 * eg. 1 images computes 4 feature masks
	 */
	private void convolve() {
		
		output = new ArrayList<double[][]>();
		
		if(input.get(0).length % conD != 0 || input.get(0)[0].length != 0) {
			for(double[][] n : input) {
				n = padImage(n);
			}
		}
		
		for(int i = 0; i < input.size(); i++) {
			for(ConvNeuron n : neurons) {
				output.add(n.convolveImage(input.get(i)));
			}	
		}
	}
		
	/**
	 * If convoluting the image would go out of bounds, instead pad image with 0 
	 * @param channel
	 * @return
	 */
	public double[][] padImage(double channel[][]) {
			double[][] newArray = new double[channel.length + (conD - (channel.length % conD))][channel[0].length + (conD - (channel[0].length % conD))];
			for(int i = 0; i < (channel.length + (channel.length % conD)); i++) {
				for (int j = 0; j < (channel[0].length + (channel[0].length % conD)); j++) {	
					newArray[i][j] = 0;
				}
			}	
			
			for(int i = 0; i < channel.length; i++) {
				for(int j = 0; j < channel[0].length; j++) {
					newArray[i][j] = channel[i][j];
				}
			}
			return newArray;
	}

	
}
