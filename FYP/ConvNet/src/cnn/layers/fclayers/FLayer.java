package cnn.layers.fclayers;

import java.util.ArrayList;

public abstract class FLayer {
	
	public abstract void forwardPass();
	
	public abstract void initaliseLayer(int n, FLayer prev, FLayer next);
	
	public abstract void receiveInput(double[] inputs);
	
	//public abstract double backPropagate();
	
	//public abstract void setInputs();
	
}