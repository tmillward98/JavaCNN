package cnn.layers.neurons;

public class ReLUNeuron extends Neuron {

	private double b1 = 0;
	
	public ReLUNeuron(int n) {
		weights = new double[n];
		changeInWeights = new double[weights.length];
		for(int i = 0; i < weights.length; i++) {
			changeInWeights[i] = 0;
			weights[i] = Math.random() * 0.001;
		}
	}
	
	public void receiveInput(double inputs) {
		System.out.println("Error, supposed to receive multiple inputs");
	}
	
	public void receiveInput(double[] inputs) {
		input = inputs;
	}
	
	public void updateWeights(double delta, double lr) {
		for(int i = 0; i < weights.length; i++) {
			changeInWeights[i] = input[i] * delta * lr;
			weights[i] = weights[i] + changeInWeights[i];
		}
	}
	
	
	/**
	 * Performs Sigmoidal activation function on its given inputs
	 * Return new output
	 */
	public double forward() {
		output = 0;
		
		for(int i = 0; i < input.length; i++) {
			output += input[i] * weights[i];
			//System.out.println(output);
		}
		
		if(output < 0) {
			output = output * 0.01;
		}
		
		return output;
	}
	
	public void calculateDerivative() {
		if(output > 0) {
			derivative = 1;
		}
		else {
			derivative = 0;
		}
	}
	
	public double getDerivative() {
		if(output > 0) {
			return 1;
		}
		else {
			return 0.01;
		}
	}
	
	
}
