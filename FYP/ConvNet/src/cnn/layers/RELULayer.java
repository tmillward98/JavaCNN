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
	
	public void setInput(ArrayList<double[][]> inputs) {
		input = inputs;
	}
	
	public void backwardPropagate(double delta, double lr) {
		System.out.println("Reached ReLU layer");
		previousLayer.backwardPropagate(delta, lr);
	}
	
	public void forwardPropagate(){
		LeakyRELU();
		nextLayer.setInput(input);
	}
	
	private void LeakyRELU() {
		for(double[][] n : input) 
			for(int i = 0; i < n.length; i++) 
				for(int j = 0; j < n[0].length; j++) 
					if(n[i][j] <= 0) 
						n[i][j] = n[i][j] * 0.01;
	}
}


