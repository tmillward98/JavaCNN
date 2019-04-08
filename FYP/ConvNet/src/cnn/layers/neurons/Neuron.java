package cnn.layers.neurons;

public abstract class Neuron {
	
	
	
	public abstract void updateWeights(double error, double lr);
	
	public abstract double forward();
	
	public abstract void receiveInput(double[] inputs);
	
	public abstract void receiveInput(double input);
	
	
}
