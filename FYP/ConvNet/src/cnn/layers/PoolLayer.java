package cnn.layers;

import java.util.ArrayList;

public class PoolLayer extends Layer{
	
	Layer previousLayer;
	Layer nextLayer;
	ArrayList<double[][]> input;
	ArrayList<double[][]> output;
	
	private int poolD;
	private int stride;
	
	public PoolLayer() {
		poolD = 2;
		stride = 2;
		output = new ArrayList<double[][]>();
	}
	
	public int getCount() {
		return 1;
	}
	
	public ArrayList<double[][]> initialiseLayer(int c, ArrayList<double[][]> exampleInput, Layer nl, Layer pl) {
		nextLayer = nl;
		previousLayer = pl;
		input = exampleInput;
		maxPool();
		return output;
	}
	
	public ArrayList<double[][]> forwardPropagate(){
		input = previousLayer.forwardPropagate();
		maxPool();
		return output;
	}
	
	private void maxPool() {
		double[][] result = new double[((input.get(0).length - poolD) / stride) + 1][((input.get(0)[0].length - poolD) / stride) + 1];
		double currentMax = 0;	
		output = new ArrayList<double[][]>();
		
		for(double[][] n : input) {
			for(int i = 0; i <= input.get(0).length - poolD; i+= stride) {
				for (int j = 0; j <= input.get(0)[0].length - poolD; j+= stride) {
					currentMax = n[i][j];		
					for(int x = 0; x < poolD; x++) {
						for (int y = 0; y < poolD; y++) {
							if(currentMax < n[i + x][j + y]) {
								currentMax = n[i + x][j + y];
							}
						}
					}
					result[i / stride][j / stride] = currentMax;
					currentMax = 0;
				}
				output.add(result);
			}
		}
	}
	

}
