package cnn.layers.neurons;

public class SigmoidNeuron extends Neuron {

	private double[] input;
	private double output = 0;
	private double[] weights;
	
	public SigmoidNeuron(int n) {
		weights = new double[n];
		for(int i = 0; i < weights.length; i++) {
			weights[i] = Math.random();
		}
	}
	
	public void receiveInput(double inputs) {
		System.out.println("Error, supposed to receive multiple inputs");
	}
	
	public void receiveInput(double[] inputs) {
		input = inputs;
	}
	
	public void updateWeights(double error, double lr) {
		for(int i = 0; i < weights.length; i++) {
			weights[i] = (error / output) * lr;
		}
	}
	
	/**
	 * Performs Sigmoidal activation function on its given inputs
	 * Return new output
	 */
	public double forward() {
		for(int i = 0; i < input.length; i++) {
			output += input[i] * weights[i];
		}
		output = 1 / (1 + Math.exp(-output));
		
		return output;
	}
	
}
