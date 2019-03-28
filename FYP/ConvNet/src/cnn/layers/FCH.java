package cnn.layers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

import cnn.layers.neurons.Neuron;
import cnn.layers.neurons.SigmoidNeuron;

public class FCH extends Layer{
	protected Layer nextLayer;
	protected Layer previousLayer;
	private double[][] mapFlat;
	private double[] flatInputs;
	private ArrayList<double[][]> input;
	private ArrayList<double[][]> output;
	private ArrayList<Neuron> neurons;
	
	public FCH() {
		input = new ArrayList<double[][]>();
		output = new ArrayList<double[][]>();
		neurons = new ArrayList<Neuron>();
	}
	
	public ArrayList<double[][]> forwardPropagate(){
		input = previousLayer.forwardPropagate();
		flattenInputs();
		
		for(int i = 0; i < neurons.size(); i++) {
			neurons.get(i).receiveInput(flatInputs);
			mapFlat[i][0] = neurons.get(i).forward();
		}
		output.add(mapFlat);
		return output;
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
		
		return input;
	}
	
	private void createNeurons(int n, int w) {
		for (int i = 0; i < n; i++) {
			SigmoidNeuron a = new SigmoidNeuron(w);
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
