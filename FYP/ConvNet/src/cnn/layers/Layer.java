package cnn.layers;

import java.util.ArrayList;

public abstract class Layer {

	private Layer previousLayer;
	private Layer nextLayer;
	
	//Each layer should call the previous layer to forward propogate its results, and likewise work backs to backwards propogate the error
	public abstract ArrayList<double[][]> forwardPropagate();
	//public abstract backPropogate(double[][] error);
	
	public abstract double[][] initaliseLayer(int c, double[][] sampleImage);
	
	public abstract void assignLayer(Layer prev, Layer next);
	
}
