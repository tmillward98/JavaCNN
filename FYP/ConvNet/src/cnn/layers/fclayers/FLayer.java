package cnn.layers.fclayers;

import java.util.ArrayList;

public abstract class FLayer {

	FLayer previousLayer;
	
	public abstract double[] forwardPass();
	//public abstract double backPropagate();
	
	public abstract double[] getInput();
}