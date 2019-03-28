package cnn.layers;

import java.util.ArrayList;

public class RELULayer extends Layer {

	private ArrayList<double[][]> input;
	private Layer previousLayer;
	private Layer nextLayer;
	
	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		nextLayer = nl;
		previousLayer = pl;
		input = exampleInput;
		LeakyRELU();
		return input;
	}
	
	public int getCount() {
		return 1;
	}
	
	public ArrayList<double[][]> forwardPropagate(){
		input = previousLayer.forwardPropagate();
		LeakyRELU();
		return input;
	}
	
	private void LeakyRELU() {
		for(double[][] n : input) 
			for(int i = 0; i < n.length; i++) 
				for(int j = 0; j < n[0].length; j++) 
					if(n[i][j] <= 0) 
						n[i][j] = n[i][j] * 0.01;
	}
}

