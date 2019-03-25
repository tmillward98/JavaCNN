package cnn.layers.neurons;
import cnn.layers.fclayers.FLayer;

public class SigmoidNeuron extends Neuron {

	private FLayer layer;
	private double[] inputs;
	private double output = 0;
	private double[] weights;
	
	public SigmoidNeuron(int length, FLayer thisLayer) {
		layer = thisLayer;
		weights = new double[length];
		for(int i = 0; i < length; i++) 
			weights[i] = Math.random();
	}
	
	/**
	 * Performs Sigmoidal activation function on its given inputs
	 * Return new output
	 */
	public double forward() {
		for(int i = 0; i < inputs.length; i++) {
			output += inputs[i] * weights[i];
		}
		
		//Sigmoidal activation
		output = 1 / (1 + Math.exp(-output));
		
		return output;
	}
	
	public void getInput() {
		inputs = layer.getInput();
	}
	
}
