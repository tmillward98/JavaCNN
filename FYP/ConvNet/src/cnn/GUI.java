package cnn;

import javax.swing.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GUI extends JFrame {
	
	//Network Variables
	static ArrayList<CNN> networks;
	static ArrayList<Integer> NetworkStructure;
	static ArrayList<Integer> FCStructure;
	static ArrayList<int[]> generations;
	float lr;
	int epochs, c;
	int gens;
	int steps;
	
	private JMenuBar menu;
	private JMenu file, help;
	
	private JButton convButton, reLUButton, poolButton, hiddenLayer;
	private JTextArea textArea;
	
	public GUI() {
		NetworkStructure = new ArrayList<Integer>();
		FCStructure = new ArrayList<Integer>();
		
		setSize(800,800);
		createView();	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

	}
	
	private void createView() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		menu = new JMenuBar();
		menu.setSize(new Dimension(800, 100));
		file = new JMenu("File");
		help = new JMenu("Help");
		menu.add(file);
		menu.add(help);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setPreferredSize(new Dimension(800,400));
		
		
		convButton = new JButton("Add Convolution Layer");
		convButton.addActionListener(new ConvButtonActionListener());
		
		reLUButton = new JButton("Add ReLU Layer");
		reLUButton.addActionListener(new ReLUButtonActionListener());
		
		poolButton = new JButton("Add Pooling Layer");
		poolButton.addActionListener(new PoolButtonActionListener());
		
		hiddenLayer = new JButton("Add Hidden Layer");
		hiddenLayer.addActionListener(new HiddenLayerActionListener());
		
		panel.add(menu);
		panel.add(textArea);
		panel.add(convButton);
		panel.add(reLUButton);
		panel.add(poolButton);
		panel.add(hiddenLayer);


	}
	
	private void addHidden() {
		FCStructure.add(4);
	}
	
	private void addConv() {
		NetworkStructure.add(1);
	}
	
	private void addReLU() {
		NetworkStructure.add(2);
	}
	
	private void addPool() {
		NetworkStructure.add(3);
	}
	
	//Called whenever a layer is added to update GUI
	private void updateView() {
		
		textArea.setText("");
		textArea.append("Input ->");
		
		for(int n : NetworkStructure) {
			switch(n) {
			
			case 1:
				textArea.append("Conv ->");
				break;
			
			case 2:
				textArea.append("Relu ->");
				break;
			
			case 3:
				textArea.append("Pool ->");
				break;
			
			}
		}
		
		textArea.append("FC Input ->");
		
		
		for(int n : FCStructure) {
			switch(n) {
			case 4:
				textArea.append("Hidden Layer ->");
				break;
			}
		}
		
		textArea.append("Output Layer");
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}
	
	private class ConvButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addConv();
			updateView();
		}
	}
	
	private class ReLUButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addReLU();
			updateView();
		}
	}
	
	private class PoolButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addPool();
			updateView();
		}
	}
	
	private class HiddenLayerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addHidden();
			updateView();
		}
	}
	
	/**
	 * Optimisation Section 
	 * Create a generation of network, with a randomly implemented change from a list of possible changes
	 * Eg. extra conv->relu, extra hidden layer
	 * Keep the best generation (lowest cost)
	 */
	private void createGenerations() {
		
		if (gens > 5) {
			gens = 5;
		}
		
		if(gens != 5) {
			Random rand = new Random();
			
			int num = 0;
			int previousNum = 0;
			
			for(int i = 0; i < gens; i++) {
				
				if(i != 0) {
					previousNum = num; 
					num = rand.nextInt(10);
					while(num == previousNum) {
						num = rand.nextInt(10);
					}
				}
				else {
					num = rand.nextInt(5);
				}
			}

			
			switch(num) {
			
			case 0:
				NetworkStructure.add(1);
				NetworkStructure.add(2);
				break;
				
			case 1:
				NetworkStructure.add(3);
				break;
				
			case 2:
				NetworkStructure.add(1);
				NetworkStructure.add(2);
				NetworkStructure.add(3);
				break;
				
			case 3:
				FCStructure.add(4);
				break;
			case 4:
				FCStructure.add(4);
				FCStructure.add(4);
				break;
				
			case 5:
				NetworkStructure.add(1);
				NetworkStructure.add(2);
				FCStructure.add(4);
			
			}
		}
		
		//Create networks with all possible options
		else {
			
		}
		
		for(int i = 0; i < networks.size(); i++) {
			networks.get(i).trainNetwork();
		}
		
		int currentIndex = 0;
		CNN a = networks.get(0);
		
		//Finally, only keep network with highest cost
		for(int i = 1; i < networks.size(); i++) {
			if(networks.get(i).getCost() < a.getCost()) {
				currentIndex = i;
				a = networks.get(i);
			}
		}
		
	}
	
}
