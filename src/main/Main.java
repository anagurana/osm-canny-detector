package main;

import model.Canny;
import model.dicomRead;

public class Main {

	public static void main(String[] args) {
		dicomRead dcm = new dicomRead();
		Canny canny = new Canny();
		//show interface, get the path, get settings
		var imageOriginal = dcm.readImage("");
		var processedImage = canny.applyCanny(imageOriginal);
		
		//show two images 
		show(imageOriginal, processedImage);
		
		
	}

}
