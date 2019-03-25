package cnn.layers.fclayers;

import java.util.ArrayList;
import java.util.Arrays;
import cnn.layers.Layer;
import cnn.layers.neurons.InputNeuron;

/**
 * Receive input, flatten volume, pass to neurons
 * @author Tom
 *
 */
public class FCInputLayer extends FLayer {

	private Layer masterLayer;
	private FLayer nextLayer;
	double[] input;
	ArrayList<double[]> flatInput;
	double[] toPass;
	ArrayList<InputNeuron> neurons;
	
	FCInputLayer(){
		flatInput = new ArrayList<double[]>();
		neurons = new ArrayList<InputNeuron>();
	}
	
	public double[][] initaliseLayer(int c, double[][] sampleImage){
		createNeurons(sampleImage.length * sampleImage[0].length);
		return sampleImage;
	}
	
	public double[] forwardPass() {
		return input;
	}
	
	public double[] getInput() {
		return input;
	}
	
	/**
	 * Arbitrary function
	 * Assign previous layer, used for forward and backward propagation
	 */
	public void assignLayer(Layer prev, FLayer nl) {
		masterLayer = prev; nextLayer = nl;
	}
	
	/**
	 * Initialise the number of neurons (equal to pixels of feature maps)
	 * @param n - number to be created
	 */
	public void createNeurons(int n) {
		InputNeuron a = new InputNeuron(this);	
		for(int i = 0; i < n; i++)
			neurons.add(a);
	}

	/**
	 * Receive output from the previous layer, perform function, forward pass
	 */
	/**
	@Override
	public ArrayList<double[][]> forwardPropagate() {
		//input = previousLayer.forwardPropagate();
		//flattenInputs();
		for(int i = 0; i < neurons.size(); i++) {
			//neurons.get(i).receiveInput(flatInput[i]);
		}
		return null;
	}
*/
	/**
	 * Flatten the given input, ready for passing to neurons
	 */
	/**
	private void flattenInputs() {			
		for(int i = 0; i < input.size(); i++) {
			flatInput.add(Arrays.stream(input.get(i))
			        .flatMapToDouble(Arrays::stream)
			        .toArray());
		}
	}
	*/
	
}
