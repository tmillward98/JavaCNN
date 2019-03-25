package cnn.layers;

import java.util.ArrayList;

public class PoolLayer extends Layer{
	
	Layer previousLayer;
	Layer nextLayer;
	ArrayList<double[][]> input;
	ArrayList<double[][]> output;
	
	private int poolD;
	private int stride;
	
	public double[][] initaliseLayer(int c, double[][] sampleImage){
		input = new ArrayList<double[][]>();
		input.add(sampleImage);
		maxPool();
		sampleImage = input.get(0);
		input = new ArrayList<double[][]>();
		return sampleImage;
	}
	
	public PoolLayer() {
		poolD = 2;
		stride = 2;
		output = new ArrayList<double[][]>();
	}
	
	public ArrayList<double[][]> forwardPropagate(){
		input = previousLayer.forwardPropagate();
		maxPool();
		return output;
	}
	
	public void assignLayer(Layer prev, Layer nl) {
		previousLayer = prev; nextLayer = nl;
	}
	
	private void maxPool() {
		double[][] result = new double[((input.get(0).length - poolD) / stride) + 1][((input.get(0)[0].length - poolD) / stride) + 1];
		double currentMax = 0;	
		
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
