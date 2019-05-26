package cnn.layers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;

import cnn.layers.neurons.*;

public class FCI extends Layer {
	
	protected Layer nextLayer;
	protected Layer previousLayer;
	
	private double[] flatInputs;
	private double[][] mapFlat;
	//private ArrayList<double[][]> input;
	//private ArrayList<double[][]> output;
	//private ArrayList<Neuron> neurons;
		
	/**
	 * Do neuron stuff
	 * Return ArrayList with double ArrayList with Array Mx1
	 */
	public void forwardPropagate(){
		
		flattenInputs();
		//normaliseData();
		input.clear();
		
		output = new ArrayList<double[][]>();
		
		/**
		for(int i = 0; i < neurons.size(); i++) {
			neurons.get(i).receiveInput(flatInputs);
			flatInputs = neurons.get(i).inputForward();
		}
		**/
		
		for(int i = 0; i < flatInputs.length; i++) {
			mapFlat[i][0] = flatInputs[i] / 10000;
			//System.out.println(mapFlat[i][0]);
		}
		
		input.add(mapFlat);	
		output.add(mapFlat);
		nextLayer.setInput(output);
	}

	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		nextLayer = nl;
		previousLayer = pl;
		
		input = exampleInput;
		flattenInputs();
		
		neurons = new ArrayList<Neuron>();
		createNeurons(1);
		input = new ArrayList<double[][]>();
		
		for(int i = 0; i < flatInputs.length; i++) {
			mapFlat[i][0] = flatInputs[i];
		}
		
		input.add(mapFlat);		
		mapFlat = null;
		return input;
	}
	
	public void backwardPropagate(ArrayList<Double> delta, double lr) {
		previousLayer.backwardPropagate(deltas, lr);
	}

	private void normaliseData() {
		double min = flatInputs[0];
		double max = flatInputs[0];
		
		for(double i : flatInputs) {
			if(i < min) {
				min = i;
			}
			else if(i > max) {
				max = i;
			}
		}
		
		for(int i = 0; i < flatInputs.length; i++) {
			flatInputs[i] = (flatInputs[i] - min) *(255/(max - min));
		}
		
		
	}
	
	private void flattenInputs() {	
		ArrayList<double[]> flatInput = new ArrayList<double[]>();
		for(int i = 0; i < input.size(); i++) {
			flatInput.add(Arrays.stream(input.get(i))
			        .flatMapToDouble(Arrays::stream)
			        .toArray());
		}
		flatInputs = new double[flatInput.get(0).length];
		flatInputs = flatInput.get(0);
		for(int i = 1; i < flatInput.size(); i++) {
			flatInputs = DoubleStream.concat(Arrays.stream(flatInputs), Arrays.stream(flatInput.get(i))).toArray();
		}
		
		mapFlat = new double[flatInputs.length][1];
		
	}
	
	public void createNeurons(int n) {
			InputNeuron a = new InputNeuron(flatInputs.length);	
			neurons.add(a);
		}
	
	public int getCount() {
		return neurons.size();
	}

}
