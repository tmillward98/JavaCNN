package cnn;

import java.util.ArrayList;
import cnn.layers.*;

public class CNN {

	private float learningRate;
	private int epochs;
	
	private static ArrayList<double[][]> output;
	
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
		FCI e = new FCI();
		FCH f = new FCH();
		FCC g = new FCC();
		
		layers = new ArrayList<Layer>();	
		a.loadImages("C:\\Users\\Tom\\Desktop\\mnistasjpg\\png");
		
		layers.add(a); layers.add(b); layers.add(c); layers.add(d); layers.add(e);
		layers.add(f); layers.add(g);
		
		ArrayList<double[][]> example = layers.get(0).forwardPropagate();
		
		for(int i = 0; i < layers.size(); i ++) {
			if(i == 0) {
				example = layers.get(i).initialiseLayer(4, example, layers.get(i+1), null);
			}
			else if (i == layers.size()-1) {
				example = layers.get(i).initialiseLayer(4, example, null, layers.get(i-1));
			}
			else {
				example = layers.get(i).initialiseLayer(4, example, layers.get(i+1), layers.get(i-1));
			}

		}
	
		
		output = layers.get(layers.size()-1).forwardPropagate();
		
		for(int i = 0; i < output.get(0).length; i++) {
			System.out.println(output.get(0)[i][0]);
		}
		
		//for(int i = 0; i < layers.size(); i++) {
		//	System.out.println(layers.get(i).getCount());
		//	layers.get(i).forwardPropagate();
		//}
		
	}
	
}