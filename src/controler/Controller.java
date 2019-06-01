package controler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.CannyEdgeDetector;
import model.dicomRead;
import ui.Images;
import ui.Parameters;

/**
 * Klasa laczaca UI i dane
 */

public class Controller {
	
	CannyEdgeDetector filter;
	Images images;
	Parameters parameters;
	//dicomRead read;

	public Controller(Parameters par) {
		
		this.parameters = par;
		
		 CannyEdgeDetector detector = new CannyEdgeDetector();
		 
		 //adjust its parameters as desired
		 detector.setTresholdDown(parameters.getTreshDown());
		 detector.setTresholdUp(parameters.getTreshUp());
		 //apply it to an image
		 try {
			detector.setSourceImage(ImageIO.read(new File(parameters.getPath())));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 //detector.setSourceImage(read.readImage(parameters.getPath()));
		 BufferedImage source = detector.getSourceImage();
		 detector.process();
		 BufferedImage edges = detector.getEdgesImage();
		 
		 try {
			this.images =  new Images(source,edges);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
