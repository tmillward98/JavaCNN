package cnn.layers;

import java.util.ArrayList;

public class RELULayer extends Layer {

	private ArrayList<double[][]> input;
	private Layer previousLayer;
	private Layer nextLayer;
	
	public double[][] initaliseLayer(int c, double[][] sampleImage){
		//Do nothing..
		return sampleImage;
	}
	
	
	
	public void assignLayer(Layer prev, Layer nl) {
		previousLayer = prev; nextLayer = nl;
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


