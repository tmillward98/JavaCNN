package cnn.objects;
import java.util.ArrayList;

public abstract class Layer {
	private ArrayList<Neuron> currentLayer;
	
	protected int getLayerSize() {
		return currentLayer.size();
	}
	
}
