package cnn.layers.neurons;

import cnn.layers.fclayers.FLayer;

//Used in fully connected layer
//Takes an input from data, and forward propagates to next layer

public class InputNeuron extends Neuron {

	double w1;
	double b1;
	public double input;
	
	public InputNeuron() {
		w1 = Math.random();
		System.out.println(w1);
		b1 = Math.random();
		System.out.println(b1);
	}
	
	public void receiveInput(double inputs) {
		input = inputs;
	}
	
	public void receiveInput(double[] inputs) {
		System.out.println("Error, should only receive one input at input neuron.");
	}
	
	public double forward() {
		input = (input * w1) + b1;
		return input;
	}
	
}