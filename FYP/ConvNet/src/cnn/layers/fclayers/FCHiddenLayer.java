package cnn.layers.fclayers;
import cnn.layers.neurons.*;

import java.util.ArrayList;
import java.util.Random;

public class FCHiddenLayer extends FLayer {

	private double[] input;
	private double[] output;
	
	private FLayer previousLayer;
	private FLayer nextLayer;
	
	ArrayList<Neuron> neurons;
	
	public void initaliseLayer(int n, FLayer prev, FLayer next){
		previousLayer = prev;
		nextLayer = next;
		createNeurons(n);
	}

	public void receiveInput(double[] inputs) {
		input = inputs;
	}
	
	public void forwardPass() {
		//Do neuron stuff
		nextLayer.receiveInput(input);
		nextLayer.forwardPass();
	}
	
	private void createNeurons(int n) {
		for (int i = 0; i < n; i++) {
			SigmoidNeuron a = new SigmoidNeuron(n);
			neurons.add(a);
		}
	}
}
