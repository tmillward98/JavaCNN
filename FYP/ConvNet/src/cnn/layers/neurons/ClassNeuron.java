package cnn.layers.neurons;

import cnn.layers.fclayers.FLayer;

public class ClassNeuron extends Neuron {

	int vectorLength;
	String label;
	public double[] input;
	double[] output;
	double[] weights;
	double rc = 0;
	
	public void receiveInput(double inputs) {
		System.out.println("Error, should receive multiple inputs at class layer.");
	}
	
	public void receiveInput(double[] inputs) {
		input = inputs;
	}
	
	public double forward() {
		softmax();
		return rc;
	}
	
	/**
	 * Class neuron which produces a score output that said image belongs to a class
	 * @param l - class label
	 * @param exampleInput - Denotes how long the vector of weights should be 
	 */
	public ClassNeuron(String l, int n) {
		label = l;
		weights = new double[n];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = Math.random();
		}
	}

	private void softmax() {
		for(int i = 0; i < input.length; i++) {
			input[i] = input[i] * weights[i];
			rc += input[i];
		}
	}
	
	
}