package cnn.layers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

import cnn.layers.neurons.Neuron;
import cnn.layers.neurons.ReLUNeuron;

public class HiddenReLU extends Layer{

	protected Layer nextLayer;
	protected Layer previousLayer;
	private double[][] mapFlat;
	private double[] flatInputs;
	
	/**
	 * ReLU Layer Constructor
	 * Initialise ArrayLists
	 */
	public HiddenReLU() {
		input = new ArrayList<double[][]>();
		output = new ArrayList<double[][]>();
		neurons = new ArrayList<Neuron>();
	}
	
	/**
	 * Perform neuron wise operations
	 */
	public void forwardPropagate(){
		output = new ArrayList<double[][]>();
		flattenInputs();
		
		double[][] toOutput = new double[neurons.size()][1];
		
		for(int i = 0; i < neurons.size(); i++) {
			neurons.get(i).receiveInput(flatInputs);
			toOutput[i][0] = neurons.get(i).forward();
		}
		
		output.add(toOutput);
		nextLayer.setInput(output);
	}
	
	public void backwardPropagate(ArrayList<Double> prevDelta, double lr) {
		deltas = prevDelta;
		
		//Start by applying derivative for this layer, hence giving us the detlas
		for(int i = 0; i < deltas.size(); i++) {
			deltas.set(i, deltas.get(i) * neurons.get(i).getDerivative());
		}
		
		//Repeat process and calculate error for the previous layer without derivative
		ArrayList<Double> errors = new ArrayList<Double>();
		
		for(int i = 0; i < flatInputs.length; i++) {
			double error = 0.0;
			for(int j = 0; j < neurons.size(); j++) {
				
				error += neurons.get(j).getWeight(i) * deltas.get(j);
				
			}
			errors.add(error);
		}
		
		previousLayer.backwardPropagate(errors, lr);
	
		
		for(int i = 0; i < neurons.size(); i++) {
			neurons.get(i).updateWeights(deltas.get(i), lr);
		}
	}
	
	public int getCount() {
		return neurons.size();
	}

	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		nextLayer = nl;
		previousLayer = pl;
		
		neurons = new ArrayList<Neuron>();
		input = exampleInput;
		
		//Create a random number of neurons
		createNeurons(new Random().nextInt(100), input.get(0).length);
		
		forwardPropagate();
		
		return output;
	}
	
	private void createNeurons(int n, int w) {
		for (int i = 0; i < n; i++) {
			ReLUNeuron a = new ReLUNeuron(w);
			neurons.add(a);
		}
	}

	private void flattenInputs() {	
		ArrayList<double[]> flatInput = new ArrayList<double[]>();
		for(int i = 0; i < input.size(); i++) {
			flatInput.add(Arrays.stream(input.get(i))
			        .flatMapToDouble(Arrays::stream)
			        .toArray());
		}
		flatInputs = flatInput.get(0);
		for(int i = 1; i < flatInput.size(); i++) {
			flatInputs = DoubleStream.concat(Arrays.stream(flatInputs), Arrays.stream(flatInput.get(i))).toArray();
		}
		
		mapFlat = new double[flatInputs.length][1];
		
	}

	
}
