package cnn.layers.neurons;

public abstract class Neuron {
	
	public abstract double forward();
	
	public abstract void receiveInput(double[] inputs);
	
	public abstract void receiveInput(double input);
	
	
}
