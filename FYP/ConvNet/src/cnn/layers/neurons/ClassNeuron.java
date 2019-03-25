package cnn.layers.neurons;

import cnn.layers.fclayers.FLayer;

public class ClassNeuron extends Neuron {

	private FLayer layer;
	int vectorLength;
	String label;
	double[] input;
	double[] output;
	double[] weights;
	double rc = 0;
	
	
	
	public double forward() {
		logisticRegression();
		return rc;
	}
	
	
	/**
	 * Class neuron which produces a score output that said image belongs to a class
	 * @param l - class label
	 * @param exampleInput - Denotes how long the vector of weights should be 
	 */
	public ClassNeuron(String l, double[] exampleInput, FLayer thisLayer) {
		label = l;
		layer = thisLayer;
		weights = new double[exampleInput.length];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = Math.random();
		}
	}
	
	private void logisticRegression() {
		for(int i = 0; i < input.length; i++) {
			output[i] = input[i] * weights[i];
			rc += output[i];
		}
	}
	
	protected void getInput() {
		input = layer.getInput();
	}
	
}
