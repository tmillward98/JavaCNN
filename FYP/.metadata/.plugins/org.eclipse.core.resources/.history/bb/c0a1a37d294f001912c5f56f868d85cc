package cnn.layers.fclayers;
import cnn.layers.neurons.*;

import java.util.ArrayList;
import java.util.Random;

public class FCHiddenLayer extends FLayer {

	private double[] input;
	private double[] output;
	
	//Hidden layer computes output, forward pass
	ArrayList<Neuron> neurons;
	
	FCHiddenLayer(){
		
		Random randomnum = new Random();
		int a = 1 + randomnum.nextInt(100);
		output = new double[a];
		
	}
	
	public double[] forwardPass() {
		for(int i = 0; i < neurons.size(); i++) {
			output[i] = neurons.get(i).forward();
		}
		return output;
	}
	
	private void createNeurons() {
		for (int i = 0; i < output.length; i++) {
			SigmoidNeuron a = new SigmoidNeuron(output.length, this);
			neurons.add(a);
		}
	}
	
	public double[] getInput() {
		return input;
	}
	
}
