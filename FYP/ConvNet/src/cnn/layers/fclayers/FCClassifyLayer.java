package cnn.layers.fclayers;

import java.util.ArrayList;

import cnn.layers.Layer;
import cnn.layers.neurons.Neuron;

public class FCClassifyLayer extends FLayer{
	
	private int classes;
	private FLayer previousLayer;
	private double[] input;
	
	private double[] output;
	private ArrayList<Neuron> classNeurons;
	
	private double sum = 0;

	public void receiveInput(double[] inputs) {
		input = inputs;
	}
	
	public void initaliseLayer(int n, FLayer prev, FLayer next){
		classes = n;
		createNeurons(classes);
		previousLayer = prev;
	}

	public void forwardPass(){
		softmax();
	}
	
	private double[] softmax() {
		for(int i = 0; i < classNeurons.size(); i++) {
			output[i] = classNeurons.get(i).forward();
			sum += output[i];
		}
		
		for(int i = 0; i < classNeurons.size(); i++) {
			output[i] = output[i] / sum;
		}
		
		
		return output;
	}
	
	private void createNeurons(int n) {
		for(int i = 0; i < n; i++) {
			//ClassNeuron a = new ClassNeuron();
		}
	}
	
}