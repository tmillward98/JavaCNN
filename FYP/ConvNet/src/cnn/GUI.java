package cnn;

import javax.swing.*;

import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.event.*;

import java.awt.*;

public class GUI extends Application {
	
	float lr;
	int epochs;
	int c;
	
	@Override
	public void start(Stage stage) {
		
	}
	
	 public static void main(String args[]) {
		 
		 //
		 JFrame frame  = new JFrame("Convolutional Neural Network");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(800, 600);
	     
	     //Creating the MenuBar and adding components
	     JMenuBar mb = new JMenuBar();
	     JMenu m1 = new JMenu("File");
	     JMenu m2 = new JMenu("Help");
	     mb.add(m1);
	     mb.add(m2);
	     JMenuItem m11 = new JMenuItem("Settings");
	     JMenuItem m22 = new JMenuItem("Save as");


	     TextInputDialog td = new TextInputDialog("Enter Epochs");
	     EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
	            public void handle(ActionEvent e) 
	            { 
	                // show the text input dialog 
	                td.show(); 
	            } 
	        }; 
	        
	        
		     m1.add(m11);
		     m1.add(m22);
	     
	     //Adding Components to the frame.
	     frame.getContentPane().add(BorderLayout.NORTH, mb);
	     frame.setVisible(true);

	 }
	
	
}
