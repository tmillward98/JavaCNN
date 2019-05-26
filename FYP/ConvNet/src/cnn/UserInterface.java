package cnn;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.util.Pair;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JScrollPane;

public class UserInterface {

	private JFrame frmNeuralNetworkTestbed;

	private String directory;
	private double lr = 0.05;
	private int epochs = 750;
	private int classes = 10;
	static ArrayList<CNN> networks;
	static ArrayList<Integer> NetworkStructure;
	static ArrayList<Integer> FCStructure;
	static ArrayList<int[]> generations;
	static ArrayList<Pair<Integer, Double>> costData;
	
	Label lrLabel;
	Label epochsLabel;
	Label prediction;
	JTextArea panel;
	JPanel inputImage;
	JTextArea panel2;
	JLabel imgL;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frmNeuralNetworkTestbed.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		
		networks = new ArrayList<CNN>();
		NetworkStructure = new ArrayList<Integer>();
		FCStructure = new ArrayList<Integer>();
		costData = new ArrayList<Pair<Integer, Double>>();
		
		NetworkStructure.add(0);
		NetworkStructure.add(1);
		NetworkStructure.add(2);
		FCStructure.add(3);
		FCStructure.add(5);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNeuralNetworkTestbed = new JFrame();
		frmNeuralNetworkTestbed.setTitle("Neural Network Testbed");
		frmNeuralNetworkTestbed.setBounds(100, 100, 887, 774);
		frmNeuralNetworkTestbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNeuralNetworkTestbed.getContentPane().setLayout(null);
		
		

		
		//GraphPanel graphPanel = new GraphPanel();
		
		
		JButton open = new JButton();
		JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("E:\\University\\Year 3\\Final Year Project\\Dataset"));
	    chooser.setDialogTitle("Select dataset");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    if(chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
	    	this.directory = chooser.getSelectedFile().getAbsolutePath();
	    }
		
		JFXPanel panel_1 = new JFXPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(20, 54, 519, 387);
		frmNeuralNetworkTestbed.getContentPane().add(panel_1);
	    
	    System.out.println(directory);
		
		JButton btnAddConvolutionalLayer = new JButton("Add Convolutional Layer");
		btnAddConvolutionalLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NetworkStructure.add(0);
				updateNetwork();
			}
		});
		btnAddConvolutionalLayer.setBounds(20, 579, 203, 66);
		frmNeuralNetworkTestbed.getContentPane().add(btnAddConvolutionalLayer);
		
		JButton btnAddReluLayer = new JButton("Add ReLU Layer");
		btnAddReluLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NetworkStructure.add(1);
				updateNetwork();
			}
		});
		btnAddReluLayer.setBounds(233, 579, 203, 66);
		frmNeuralNetworkTestbed.getContentPane().add(btnAddReluLayer);
		
		JButton btnAddPoolLayer = new JButton("Add Pool Layer");
		btnAddPoolLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NetworkStructure.add(2);
				updateNetwork();
			}
		});
		btnAddPoolLayer.setBounds(446, 579, 203, 66);
		frmNeuralNetworkTestbed.getContentPane().add(btnAddPoolLayer);
		
		JButton btnAddHiddenLayer = new JButton("Add Hidden Layer");
		btnAddHiddenLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FCStructure.add(5);
				updateNetwork();
			}
		});
		btnAddHiddenLayer.setBounds(659, 579, 203, 66);
		frmNeuralNetworkTestbed.getContentPane().add(btnAddHiddenLayer);
		
		JButton btnTrainNetwork = new JButton("Train Network");
		btnTrainNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(NetworkStructure.isEmpty() || FCStructure.isEmpty()) {
					JOptionPane.showMessageDialog(frmNeuralNetworkTestbed, "You need to add layers");
				}
				else {
					if(!networks.isEmpty())
						networks.clear();
					if(!costData.isEmpty())
						costData.clear();
					
					
					ArrayList<Integer> completeStructure = new ArrayList<Integer>();
					
					for(int n : NetworkStructure) {
						completeStructure.add(n);
					}
					for(int n : FCStructure) {
						completeStructure.add(n);
					}
					
					int[] structure = new int[completeStructure.size()];

					for(int i = 0; i < completeStructure.size(); i++) {
						structure[i] = completeStructure.get(i);
					}
					
					
					CNN a = new CNN((float)lr, epochs, classes, directory, directory + "/class.txt", structure);
					networks.add(a);
				}
				
				for(int i = 0; i < epochs; i++) {
					networks.get(0).trainNetwork();
					if(i % 9 == 0) {
						costData.add(new Pair<Integer, Double>(i, networks.get(0).getCost()));
						updateFX(panel_1);
						System.out.println("");
					}
				}

				
				networks.get(0).validateNetwork();
				String results = networks.get(0).getValid();
				updateValidation(results);

			}
		});
		btnTrainNetwork.setBounds(329, 656, 203, 66);
		frmNeuralNetworkTestbed.getContentPane().add(btnTrainNetwork);
		
		JButton btnTestNetwork = new JButton("Test Network");
		btnTestNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton open = new JButton();
				JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("E:\\University\\Year 3\\Final Year Project\\Dataset"));
			    chooser.setDialogTitle("Select image");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    if(chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			    	//Load image
			    	loadImage(chooser.getSelectedFile().getAbsoluteFile().toString());
			    	int result = networks.get(0).testNetwork(chooser.getSelectedFile().getAbsolutePath()); 
			    	updateResultLabel(result);
			    }
			}
		});
		btnTestNetwork.setBounds(567, 375, 224, 66);
		frmNeuralNetworkTestbed.getContentPane().add(btnTestNetwork);
		
		panel = new JTextArea();
		panel.setEditable(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 465, 842, 103);
		frmNeuralNetworkTestbed.getContentPane().add(panel);
		
		if(!NetworkStructure.isEmpty() && !FCStructure.isEmpty()) {
			panel.append("Input->");
			
			for(int n : NetworkStructure) {
				switch(n) {
				
				case 0:
					panel.append("Conv ->");
					break;
				
				case 1:
					panel.append("Relu ->");
					break;
				
				case 2:
					panel.append("Pool ->");
					break;
				
				}
			}
			
			panel.append("FC Input ->");
			
			
			for(int n : FCStructure) {
				switch(n) {
				case 5:
					panel.append("Hidden Layer ->");
					break;
				}
			}
			
			panel.append("Output Layer");
			
		}
		
		createLabels();
		
		lrLabel.setBounds(20, 32, 62, 22);
		frmNeuralNetworkTestbed.getContentPane().add(lrLabel);
		
		epochsLabel.setBounds(88, 32, 93, 22);
		frmNeuralNetworkTestbed.getContentPane().add(epochsLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1214, 21);
		frmNeuralNetworkTestbed.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmChangeEpochs = new JMenuItem("Change epochs");
		mntmChangeEpochs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int newEpochs = Integer.parseInt(JOptionPane.showInputDialog(frmNeuralNetworkTestbed,"Number of Epochs:"));	
				
				if(newEpochs != epochs) {
					System.out.println("UPDATED");
					epochs = newEpochs;
					updateLabels();
				}
			}
		});
		mnFile.add(mntmChangeEpochs);
		
		JMenuItem mntmChangeLearningRate = new JMenuItem("Change learning rate");
		mntmChangeLearningRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double newLR = Double.parseDouble(JOptionPane.showInputDialog(frmNeuralNetworkTestbed,"Learning Rate:"));	
				if(newLR != lr) {
					System.out.println("UPDATED");
					lr = newLR;
					updateLabels();
				}
			}
		});
		mnFile.add(mntmChangeLearningRate);
		
		inputImage = new JPanel();
		inputImage.setBackground(Color.WHITE);
		inputImage.setBounds(567, 192, 224, 150);
		frmNeuralNetworkTestbed.getContentPane().add(inputImage);
		imgL = new JLabel();
		imgL.setBackground(Color.GRAY);
		imgL.setSize(150,150);
		inputImage.add(imgL);
		
		
		prediction = new Label("Result:");
		prediction.setAlignment(Label.CENTER);
		prediction.setBounds(567, 348, 224, 22);
		frmNeuralNetworkTestbed.getContentPane().add(prediction);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(567, 32, 224, 150);
		frmNeuralNetworkTestbed.getContentPane().add(scrollPane);
		
		panel2 = new JTextArea();
		scrollPane.setViewportView(panel2);
		panel2.setBackground(Color.WHITE);
		panel2.setEditable(false);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(panel_1);
			}
		});
		
	}
	
	private void loadImage(String dir) {
		try {
			BufferedImage img = ImageIO.read(new File(dir));
			Image i = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			imgL.setIcon(new ImageIcon(i));
			imgL.setSize(150, 150);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	private void createLabels() {		
		epochsLabel = new Label("Epochs: " + epochs);
		lrLabel = new Label("LR: " + lr);
	}
	
	private void updateLabels() {
		epochsLabel.setText("Epochs: " + epochs);
		lrLabel.setText("LR: " + lr);
	}
	
	private void updateResultLabel(int result) {
		prediction.setText("Result: " + result);
	}
	
	private void updateNetwork() {
		if(!NetworkStructure.isEmpty() || !FCStructure.isEmpty()) {
			panel.setText("");
			panel.append("Input->");
			
			for(int n : NetworkStructure) {
				switch(n) {
				
				case 0:
					panel.append("Conv ->");
					break;
				
				case 1:
					panel.append("Relu ->");
					break;
				
				case 2:
					panel.append("Pool ->");
					break;
				
				}
			}
			
			panel.append("FC Input ->");
			
			
			for(int n : FCStructure) {
				switch(n) {
				case 5:
					panel.append("Hidden Layer ->");
					break;
				}
			}
			
			panel.append("Output Layer");
			
		}
	}
	
	private void updateValidation(String results) {
		panel2.setText("");
		panel2.append(results);
	}
	
	/**
	 * As we cannot modify the nodes of the root, we will need to create a new scene with the gathered data.
	 * We therefore recreate a new graph and add each new point
	 * @param fxPanel
	 */
	private static void updateFX(JFXPanel fxPanel) {
		Group root = new Group();
		Scene scene = new Scene(root);
		
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Epoch");
	
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Avg. Cost");
		
		LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
		chart.setTitle("Cost over training");
		
		XYChart.Series series = new XYChart.Series();
		
		for(int i = 0; i < costData.size(); i++) {
			series.getData().add(new XYChart.Data(costData.get(i).getKey(), costData.get(i).getValue()));
		}
		
		chart.getData().add(series);
		root.getChildren().add(chart);
		fxPanel.setScene(scene);
		
	}
	
	private static void initFX(JFXPanel fxPanel) {
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}
	
	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root);
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Epoch");
	
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Avg. Cost");
		
		LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
		chart.setTitle("Cost over training");
		
		root.getChildren().add(chart);
		
		
		return scene;
	}
}
