package cnn.layers;

import java.util.ArrayList;
import java.util.Arrays;
import cnn.layers.fclayers.*;
import java.util.stream.*;

/**
 * Layer which contains all information on the fully connected structure
 * Makes use of arbitrary functions previous provided
 * Ultimately produces a classification vector
 * @author Tom
 *
 */
public class FCLayer extends Layer {

	private Layer previousLayer;
	private ArrayList<double[][]> input;
	private double[] flatInputs;
	private ArrayList<double[][]> output;
	
	private ArrayList<FLayer> layers;
	
	private double sc = 0;
	private double[] results;
	
	/**
	 * FC LAYER
	 * FINAL LAYER SHOULD BE EQUAL TO NUMBER OF CLASSES WE WISH TO IDENTIFY (EG. EACH NEURON OUTPUTS PROBABILITY)
	 * P(C|X)
	 * 
	 * FIRST LAYER TAKES INPUTS (NUMBER OF FEATURE MAPS)
	 * APPLY VECTOR OF WEIGHTS (EG. EACH NEURON HAS A VECTOR OF WEIGHTS = SIZE OF INPUT)
	 * WEIGHTS ARE INITIALISED AS RANDOM
	 * 
	 * EVERY OUTPUT OF EVERY NEURON IS THEN PASSED TO EVERY NEURON IN NEXT LAYER
	 * WEIGHTS/BIAS APPLIED/ACTIVATION FUNCTIONS APPLIED
	 * 
	 * FINAL LAYER CLASSIFIES IMAGE BASED ON INPUTS (FEATURE MAPS THROUGH NEURONS)
	 * THIS PRODUCES A PROBABILITY
	 * 
	 * OUTPUT SHOULD BE A C x 1 VECTOR WITH NORMALISED PROBABILITIES FROM ALL NEURONS (ALL CLASSES)
	 * 
	 * ERROR DETERMINED BY SUM OF ERROR FROM VECTORS
	 * BACKPROPOGATE, ADJUST WEIGHTS
	 * 
	 * eg. output produces vector
	 * Element wise value multiplication e^ci / sum of C values
	 * 
	 * Fully connected denotes the output of every neuron should go to every neuron in the next layer
	 * 
	 */
	
	public FCLayer() {
	}
	
	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		previousLayer = pl;

		//Create input neurons based on size of input
		FCInputLayer a = new FCInputLayer();
		
		//Random sigmoid layer
		FCHiddenLayer b = new FCHiddenLayer();
		
		
		//Create class neurons based on how many classes there are
		FCClassifyLayer d = new FCClassifyLayer();
		
		layers.add(a);
		layers.add(b);
		layers.add(d);	
		
		return exampleInput;
	}
	
	public int getCount() {
		return 1;
	}
	
	/**
	 * Retrieve scores from each class neuron, normalise probabilities in the range of 0-1
	 */
	private void softmax() {	
		//Get class layer output
		
		for(double n : results) 
			n = n / sc;
	}
	
	public void assignLayer(Layer prev, Layer nl) {
		previousLayer = prev; 
	}
	
	public ArrayList<double[][]> forwardPropagate(){
		input = previousLayer.forwardPropagate();
		flattenInputs();
		//do neuron stuff, return vector of outputs
		//begin back propagation based on error of result
		return input;
	}
	
	private void flattenInputs() {	
		ArrayList<double[]> flatInput = new ArrayList<double[]>();
		for(int i = 0; i < input.size(); i++) {
			flatInput.add(Arrays.stream(input.get(i))
			        .flatMapToDouble(Arrays::stream)
			        .toArray());
		}
		flatInputs = flatInput.get(0);
		for(int i = 1; i < flatInput.size(); i++) {
			flatInputs = DoubleStream.concat(Arrays.stream(flatInputs), Arrays.stream(flatInput.get(i))).toArray();
		}
		
	}
	
}
