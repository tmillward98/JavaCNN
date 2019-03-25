package cnn.layers.neurons;

import cnn.layers.fclayers.FLayer;

//Used in fully connected layer
//Takes an input from data, and forward propagates to next layer

public class InputNeuron extends Neuron {

	FLayer layer;
	double w1;
	double b1;
	double[] input;
	
	public InputNeuron(FLayer l) {
		layer = l;
		w1 = Math.random();
		b1 = Math.random();
	}
	
	public double forward() {
		input[0] = (input[0] * w1) + b1;
		return input[0];
	}
	
	public void receiveInput(double a) {
		input[0] = a;
	}
	
	protected void getInput() {
		input = layer.getInput();
	}
	
}
