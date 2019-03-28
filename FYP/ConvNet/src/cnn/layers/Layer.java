package cnn.layers;

import java.util.ArrayList;

public abstract class Layer {

	protected Layer nextLayer;
	protected Layer previousLayer;
	
	//Each layer should call the previous layer to forward propogate its results, and likewise work backs to backwards propogate the error
	public abstract ArrayList<double[][]> forwardPropagate();
	//public abstract backPropogate(double[][] error);
	
	public abstract ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nextLayer, Layer previousLayer);
	
	public abstract int getCount();
}
