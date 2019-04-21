package cnn.layers.neurons;

import java.util.ArrayList;

//Used in fully connected layer
//Takes an input from data, and forward propagates to next layer

public class InputNeuron extends Neuron {

	double b1;
	//public double input;
	public double output;
	
	public InputNeuron(int n) {
		b1 = Math.random();
		weights = new double[n];
		changeInWeights = new double[n];
		for(int i = 0; i < weights.length; i++) {
			changeInWeights[i] = 0;
			weights[i] = Math.random();
		}
	}
	
	public void updateWeights(ArrayList<Double> deltas, double lr) {
		for(int i = 0; i < weights.length; i++) {
			changeInWeights[i] = deltas.get(i) * lr + changeInWeights[i];
			weights[i] = weights[i] + changeInWeights[i];
		}
	}
	
	public void updateWeights(double delta, double lr) {
		changeInWeights[0] = delta * lr + changeInWeights[0];
		weights[0] = weights [0] + changeInWeights[0];
	}
	
	public void receiveInput(double inputs) {
		input[0] = inputs;
	}
	
	public void receiveInput(double[] inputs) {
		input = inputs;
	}
	
	public double forward() {
		output = (input[0] * weights[0]) + b1;
		return output;
	}
	
	public double getDerivative() {
		return 1;
	}
	
}
