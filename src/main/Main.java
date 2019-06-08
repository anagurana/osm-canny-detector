package main;

import java.io.IOException;

import controler.Controller;
import model.Parameters;
import ui.ParametersPanel;

public class Main {

	public static void main(String[] args) throws IOException, Exception {
		
		var parametersPanel = new ParametersPanel(); 
		var parameters = new Parameters(); 
		new Controller(parametersPanel, parameters);	
	}

}
