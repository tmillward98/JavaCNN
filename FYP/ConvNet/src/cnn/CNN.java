package cnn;

import java.util.ArrayList;
import cnn.layers.*;
import javafx.util.Pair;

public class CNN {

	private float learningRate;
	private int epochs;
	
	private ArrayList<Pair<double[][], Integer>> trainingSet;
	
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
	
	//H(p,q) = -sigma p(x) log q(x)
	public static double crossEntropy(double p, double q) {
		return (p * Math.log(q));
	}

	
	
	
	//Let's make a CNN
	public static void main(String args[]) {
		
		
		double[] expectedOutput = new double[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		
		InputLayer a = new InputLayer("C:\\Users\\Tom\\Desktop\\Data\\New folder");
		ConvolutionLayer b = new ConvolutionLayer();
		RELULayer c = new RELULayer();
		PoolLayer d = new PoolLayer();
		FCI e = new FCI();
		FCH f = new FCH();
		FCC g = new FCC();
		
		layers = new ArrayList<Layer>();	
		//a.loadImages("E:\\University\\Year 3\\Final Year Project\\Dataset\\Train_UQcUa52\\Images\\a");
		
		layers.add(a); layers.add(b); layers.add(c); layers.add(d); layers.add(e);
		layers.add(f); layers.add(g);
		
		ArrayList<double[][]> example = layers.get(0).getOutput();
		
		for(int i = 0; i < layers.size(); i ++) {
			if(i == 0) {
				example = layers.get(i).initialiseLayer(10, example, layers.get(i+1), null);
			}
			else if (i == layers.size()-1) {
				example = layers.get(i).initialiseLayer(10, example, null, layers.get(i-1));
			}
			else {
				example = layers.get(i).initialiseLayer(10, example, layers.get(i+1), layers.get(i-1));
			}

		}
	
		double error = 0;
		double rc = 0;
		
		for(int i = 0; i < layers.size(); i++) {
			layers.get(i).forwardPropagate();
		}
		

		
		output = layers.get(layers.size() - 1).getOutput();
		
		//output = layers.get(layers.size()-1).getOutput();
		
		for(int i = 0; i < output.get(0).length; i++) {
			rc += Math.exp(output.get(0)[i][0]);
		}
		
		for(int i = 0; i < output.get(0).length; i++) {
			output.get(0)[i][0] = Math.exp(output.get(0)[i][0] ) / rc;
			System.out.println("Final Output: " + output.get(0)[i][0]);
			error += crossEntropy(expectedOutput[i], output.get(0)[i][0]);
		}
		error = -error;
		System.out.println("Loss: " + error);
		
		layers.get(layers.size() - 1).backwardPropagate(error, 0.05);
		
		for(int i = 0; i < layers.size(); i++) {
			layers.get(i).forwardPropagate();
		}
		
		output = layers.get(layers.size() - 1).getOutput();
		
		//output = layers.get(layers.size()-1).getOutput();
		
		for(int i = 0; i < output.get(0).length; i++) {
			rc += Math.exp(output.get(0)[i][0]);
		}
		
		for(int i = 0; i < output.get(0).length; i++) {
			output.get(0)[i][0] = Math.exp(output.get(0)[i][0] ) / rc;
			System.out.println("Final Output: " + output.get(0)[i][0]);
			error += crossEntropy(expectedOutput[i], output.get(0)[i][0]);
		}
		error = -error;
		System.out.println("Loss: " + error);
		
		
		
		//for(int i = 0; i < layers.size(); i++) {
		//	System.out.println(layers.get(i).getCount());
		//	layers.get(i).forwardPropagate();
		//}
		
	}
	
}
