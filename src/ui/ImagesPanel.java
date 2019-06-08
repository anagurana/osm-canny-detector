package ui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ImagesPanel extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel    	  before;
	private JPanel    	  after;
	
	private GridLayout	layout;
	
	public ImagesPanel(BufferedImage source, BufferedImage edges)  {
		try {
			init(source, edges);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void init(BufferedImage sourceImage, BufferedImage edgesImage) throws IOException, Exception {
		/* 
		 * Frame configuration
		 */
		
		this.setTitle("Displaying images");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setBounds(0, 0, (int) (screenSize.width/2), (int) (screenSize.height/2));
		this.setMinimumSize(new Dimension(screenSize.width/2, screenSize.height/2));
		
		
		
		/*
		 * Panels
		 */
		
		this.before = new JPanel();
		this.after = new JPanel();
		
		TitledBorder befBorder = new TitledBorder("Before");
		befBorder.setTitleJustification(TitledBorder.CENTER);
		this.before.setBorder(befBorder);
		
		TitledBorder aftBorder = new TitledBorder("After");
		aftBorder.setTitleJustification(TitledBorder.CENTER);
		this.after.setBorder(aftBorder);
		
		DisplayImage(this.before, resize(sourceImage,400,450));
		DisplayImage(this.after, resize(edgesImage,400,450));
		
		configureLayout();

		this.setVisible(true);
		
	}
	/**
	 * Displays the image on specific JPanel
	 * @param jp JPanel 
	 * @param img The image that we want to display
	 */
	 private void DisplayImage(JPanel jp, BufferedImage img) throws IOException, Exception {
	        try {
	            ImageIcon imageicon=new ImageIcon(img);
	            
	            JLabel label=new JLabel(imageicon);
	            label.setBounds(0, 0, 100, 100);
	            jp.add(label);
	        } catch (Exception ex) {
	            throw new Exception();
	        }
	    }
	
	 /**
	  * Resizing the image
	  * @param inputImage
	  * @param scaledWidth
	  * @param scaledHeight
	  * @return resized image
	  */

	public static BufferedImage resize(BufferedImage inputImage, int scaledWidth, int scaledHeight) {
		 
	        // creates output image
	        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
	        outputImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
	 
	        // scales the input image to the output image
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
	        g2d.dispose();
	        return outputImage;
	
	    }	
	/**
	 * layout : 2 columns 
	 */
	private void configureLayout() {
		
		this.layout = new GridLayout(1,0);	
		this.setLayout(this.layout);
		
		this.add(this.before);
		this.add(this.after);
	}
	
}
