package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Images extends JFrame {
	
	private Container  	  panel;
	private JPanel    	  before;
	private JPanel    	  after;
	
	private GridLayout	layout;
	
	public Images(BufferedImage source, BufferedImage edges)  {
		try {
			init(source, edges);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		this.setBounds(0, 0, (int) (screenSize.width/1.5), (int) (screenSize.height/1.5));
		this.setMinimumSize(new Dimension(screenSize.width/2, screenSize.height/2));
		
		this.panel = this.getContentPane();
		
		/*
		 * Panels
		 */
		
		this.before = new JPanel();
		this.after = new JPanel();
		
		TitledBorder befBorder = new TitledBorder("Before");
		befBorder.setTitleJustification(befBorder.CENTER);
		this.before.setBorder(befBorder);
		
		TitledBorder aftBorder = new TitledBorder("After");
		aftBorder.setTitleJustification(aftBorder.CENTER);
		this.after.setBorder(aftBorder);

		/*var image = ImageIO.read(new File("C:\\Users\\asyag\\Desktop\\study\\donwloads\\1.jpg"));
		
		DisplayImage(this.before, resize(image,400,500));
		DisplayImage(this.after, resize(image,400,500));*/
		
		DisplayImage(this.before, resize(sourceImage,400,500));
		DisplayImage(this.after, resize(edgesImage,400,500));
		//DisplayImage(this.before, dicomRead.readImage("C:\\Users\\asyag\\Desktop\\study\\OSM\\2 projekt\\SecondProject\\in.dcm") );
		
		configureLayout();

		this.setVisible(true);
		
	}
	
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
	/* 
	 * layout : 2 columns 
	 */
	private void configureLayout() {
		
		this.layout = new GridLayout(1,0);	
		this.setLayout(this.layout);
		
		this.add(this.before);
		this.add(this.after);
	}
	
}
