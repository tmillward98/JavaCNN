package cnn.layers.fclayers;

import java.util.ArrayList;

import cnn.layers.Layer;
import cnn.layers.neurons.Neuron;

public class FCClassifyLayer extends FLayer{

	//////////VARIABLES\\\\\\\\\\
	
	private int classes;
	private Layer previousLayer;
	private Layer nextLayer;
	private double[] input;
	
	private double[] output;
	private ArrayList<Neuron> classNeurons;
	
	private double[] outputVector;
	private double sum = 0;
	
	//////////FUNCTIONS\\\\\\\\\\
	
	public double[][] initaliseLayer(int c, double[][] sampleImage){
		classes = c;
		
		return sampleImage;
	}
	
	public void assignLayer(Layer prev, Layer nl) {
		previousLayer = prev; nextLayer = nl;
	}

	public double[] forwardPass(){
		softmax();
		return outputVector;
		
	}
	
	private double[] softmax() {
		for(int i = 0; i < classNeurons.size(); i++) {
			outputVector[i] = classNeurons.get(i).forward();
			sum += outputVector[i];
		}
		
		for(int i = 0; i < classNeurons.size(); i++) {
			outputVector[i] = outputVector[i] / sum;
		}
		
		
		return outputVector;
	}
	
	private void createNeurons() {
		for(int i = 0; i < classes; i++) {
			//ClassNeuron a = new ClassNeuron();
		}
	}
	
	public double[] getInput() {
		return input;
	}
	
}