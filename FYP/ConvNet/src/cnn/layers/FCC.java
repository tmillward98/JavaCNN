package cnn.layers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;

import cnn.layers.neurons.*;

public class FCC extends Layer {
	
	protected Layer nextLayer;
	protected Layer previousLayer;
	private double[][] mapFlat;
	private double[] flatInputs;
	private ArrayList<double[][]> input;
	private ArrayList<double[][]> output;
	private ArrayList<Neuron> neurons;
	
	public ArrayList<double[][]> forwardPropagate(){
		output = new ArrayList<double[][]>();
		input = previousLayer.forwardPropagate();
		flattenInputs();
		
		for(int i = 0; i < neurons.size(); i++) {
			neurons.get(i).receiveInput(flatInputs);
			mapFlat[i][0] = neurons.get(i).forward();
		}
		output.add(mapFlat);
		//return output
		return output;
	}

	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		nextLayer = nl;
		previousLayer = pl;
		neurons = new ArrayList<Neuron>();
		
		input = exampleInput;
		
		//Create a random number of neurons
		createNeurons(c, input.get(0).length);
		
		return input;
	}
	
	private void createNeurons(int n, int w) {
		for (int i = 0; i < n; i++) {
			ClassNeuron a = new ClassNeuron("Class " + i, w);
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
		
		mapFlat = new double[neurons.size()][1];
		
	}

	public int getCount() {
		return neurons.size();
	}
}
