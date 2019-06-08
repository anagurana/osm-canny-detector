package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 
 * The class that process the images to proper size and transform into labels
 *
 */
public class DisplayImages {
	
	/**
	 * Returns the labels, that will be used in Panels to set the images
	 * @param img The immage that we want to show in the panel
	 * @return Labels
	 */
	 public static JLabel DisplayImage(BufferedImage img) {
	       		img = resize(img,400, 450);
	            ImageIcon imageicon=new ImageIcon(img);
	            JLabel label=new JLabel(imageicon);
	            label.setBounds(0, 0, 100, 100);
				return label;    	
	    }
	
	 
	 /**
	  * Resizing the image
	  * @param inputImage
	  * @param scaledWidth
	  * @param scaledHeight
	  * @return resized image
	  */
	 private static BufferedImage resize(BufferedImage inputImage, int scaledWidth, int scaledHeight) {
		 
	        /*
	         * creates output image
	         */
	        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
	        outputImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
	 
	        /*
	         *  scales the input image to the output image
	         */
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
	        g2d.dispose();
	        return outputImage;
	
	    }	
}
