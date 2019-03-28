package cnn.layers.fclayers;

import java.util.ArrayList;
import java.util.Arrays;
import cnn.layers.Layer;
import cnn.layers.neurons.InputNeuron;
import cnn.layers.neurons.Neuron;

/**
 * Receive input, flatten volume, pass to neurons
 * @author Tom
 *
 */
public class FCInputLayer extends FLayer {

	private FLayer nextLayer;
	double[] input;
	double[] output;
	ArrayList<Neuron> neurons;
	
	public FCInputLayer(){
		neurons = new ArrayList<Neuron>();
	}
	
	public void initaliseLayer(int n, FLayer prev, FLayer next){
		createNeurons(n);
		nextLayer = next;
	}
	
	public void receiveInput(double[] inputs) {
		input = inputs;
	}
	
	public void forwardPass() {
		//do neuron stuff
		for(int i = 0; i < neurons.size(); i++) {
			neurons.get(i).receiveInput(input[i]);
			output[i] = neurons.get(i).forward();
		}
		nextLayer.receiveInput(input);
		nextLayer.forwardPass();
	}
	
	/**
	 * Initialise the number of neurons (equal to pixels of feature maps)
	 * @param n - number to be created
	 */
	public void createNeurons(int n) {
		for(int i = 0; i < n; i++) {
			InputNeuron a = new InputNeuron();	
			neurons.add(a);
		}
	}
}
