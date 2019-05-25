package model;

import java.awt.image.BufferedImage;

public class Canny {

	
	//fields 
	private BufferedImage sourceImage;
	private BufferedImage edgesImage;
	
	private float gaussianKernelRadius;
	private float lowThreshold;
	private float highThreshold;
	private int gaussianKernelWidth;

	
	public BufferedImage applyCanny(BufferedImage original) {
		
		//detect edges and return
		
		BufferedImage result = original;
		
		return result;
	}
	
	
}
