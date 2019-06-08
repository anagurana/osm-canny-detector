package main;

import java.io.IOException;

import javax.swing.SwingUtilities;

import controler.Controller;
import model.Parameters;
import ui.ParametersPanel;

/**
 * 
 * The starting point of the application
 *
 */
public class Main {

	public static void main(String[] args) throws IOException, Exception {
		
		Runnable swingThread = new Runnable() {
	
		@Override
		public void run() {
			var parametersPanel = new ParametersPanel(); 
			var parameters = new Parameters();
			new Controller(parametersPanel, parameters);	
			} 
		};
	
	SwingUtilities.invokeLater(swingThread);
	}
	
}
