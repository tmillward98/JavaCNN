package cnn;

import java.util.ArrayList;
import cnn.layers.*;

public class CNN {

	private float learningRate;
	private int epochs;
	
	CNN(float lr, int e){
		learningRate = lr;
		epochs = e;
	}
	
	private static ArrayList<Layer> layers;
	
	/**
	 * Once network structure has been created, initial parameters must be set
	 * @param c - the number of classes
	 * @param sampleImage - Sample size of image, determines how many neurons are needed for the input layer of FC layer
	 */
	public void initaliseNetwork(int c, double[][] sampleImage) {
		
	}
	
	public ArrayList<Layer> returnStructure(){
		return layers;
	}
	
	//Let's make a CNN
	public static void main(String args[]) {
		
		InputLayer a = new InputLayer();
		ConvolutionLayer b = new ConvolutionLayer();
		RELULayer c = new RELULayer();
		PoolLayer d = new PoolLayer();
		FCLayer e = new FCLayer();
		
		layers = new ArrayList<Layer>();	
		a.loadImages("C:\\Users\\Tom\\Desktop\\mnistasjpg\\png");
		
		layers.add(a); layers.add(b); layers.add(c); layers.add(d); layers.add(e);
		
		for(int i = 0; i < layers.size(); i++) {
			if(i != 0) {
				layers.get(i).assignLayer(layers.get(i - 1), layers.get(i + 1));
			}
			else {
				layers.get(i).assignLayer(layers.get(0), layers.get(1));
			}
			
		}
		
		for(int i = 0; i < layers.size(); i++) {
			layers.get(i).forwardPropagate();
		}
		
	}
	
}
