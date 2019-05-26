package cnn.layers;

import java.util.ArrayList;
import cnn.layers.neurons.Neuron;

public abstract class Layer {

	protected double delta = 0;
	protected Layer nextLayer;
	protected Layer previousLayer;
	protected ArrayList<Neuron> neurons;
	protected ArrayList<double[][]> input;
	protected ArrayList<double[][]> output;
	protected ArrayList<Double> deltas;
	
	/**
	 * Each layer produces an output, then passes its output to the next layer
	 */
	public abstract void forwardPropagate();
	
	/**
	 * Used to train the network
	 * Error is iteratively adjusted at each layer, depending on the derivative of the layer
	 * This layer is abstract, as the output layer has a different approach to calculating the error
	 * @param deltas - Error from the previous layer, without derivative
	 * @param lr - The learning rate, static variable passed between layers
	 */
	public abstract void backwardPropagate(ArrayList<Double> deltas, double lr);
	
	/**
	 * Retrieves the output of the last layer
	 * @return
	 */
	public ArrayList<double[][]> getOutput(){
		return this.output;
	}
	
	/**
	 * Sets the inputs for the layer
	 * This is used in forward propagation
	 * @param inputs - Output from the previous layer
	 */
	public void setInput(ArrayList<double[][]> inputs) {
		this.input = inputs;
	}
	
	protected ArrayList<Double> weightedDeltas(){
		ArrayList<Double> wtDeltas = new ArrayList<Double>();
		
		for(int i = 0; i < deltas.size(); i++) {
			System.out.println("Before calculation: " + deltas.get(i));
			wtDeltas.add(deltas.get(i) * (output.get(0)[i][0] * (1 - output.get(0)[i][0])));
			System.out.println("Output: " + output.get(0)[i][0]);
			System.out.println("After calculation: " + wtDeltas.get(i));
		}
		System.out.println("Finished one layer");
		
		return wtDeltas;
	}
	
	/**
	 * Iteratively called at each layer to show the neuron an expected input, hence determining the number of weights at each layer
	 * @param c - Number of classes
	 * @param exampleInput - Example of input
	 * @param nextLayer - The layer next to the current layer
	 * @param previousLayer - The layer before the current layer
	 * @return
	 */
	public abstract ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nextLayer, Layer previousLayer);
	
	/**
	 * Removes x amount of neurons from the list of neurons
	 * @param amount - The amount of neurons to remove
	 */
	public void removeNeuron(int amount) {
		for(int i = 0; i < amount; i++) {
			neurons.remove(neurons.size() - 1);
		}
	}
	
	/**
	 * Returns the number of neurons in a layer
	 * @return
	 */
	public abstract int getCount();
}
