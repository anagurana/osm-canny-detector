package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.dicomRead;

public class Images extends JFrame {
	
	private BufferedImage original;
	private BufferedImage result;
	private Container  	  panel;
	private JPanel    	  before;
	private JPanel    	  after;
	private JLabel 		ori;
	private JLabel 		canny;
	
	private GridBagLayout 	   layout;
	private GridBagConstraints layoutConstraints;
	
	public Images() throws IOException, Exception {
		init();
	}
	
	
	 private void DisplayImage(JPanel jp, BufferedImage img) throws IOException, Exception {
	        try {
	            //Image image=ImageIO.read(this.getClass().getResource(url));
	            ImageIcon imageicon=new ImageIcon(img);
	            JLabel label=new JLabel(imageicon);
	            jp.add(label);
	        } catch (Exception ex) {
	            throw new Exception();
	        }
	    }
	
	private void init() throws IOException, Exception {
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

		DisplayImage(this.before, dicomRead.readImage("C:\\Users\\asyag\\Desktop\\study\\OSM\\2 projekt\\SecondProject\\in.dcm") );
		
		configureLayout();

		this.setVisible(true);
		
	}
	
	
	/* 
	 * layout : 2 columns 
	 */
	private void configureLayout() {
		
		this.layout = new GridBagLayout();		
		this.panel.setLayout(layout);
		
		this.layoutConstraints = new GridBagConstraints();
			
		this.layoutConstraints.fill = GridBagConstraints.BOTH;
		this.layoutConstraints.gridwidth = 1;
		this.layoutConstraints.gridheight = 2;
		this.layoutConstraints.gridx = 0;
		this.layoutConstraints.gridy = 0;
		this.layoutConstraints.weightx = 1;
		this.layoutConstraints.weighty = 0.5;
		this.panel.add(this.before, this.layoutConstraints);	
		
		this.layoutConstraints.fill = GridBagConstraints.BOTH;
		this.layoutConstraints.gridwidth = 1;
		this.layoutConstraints.gridheight = 2;
		this.layoutConstraints.gridx = 1;
		this.layoutConstraints.gridy = 0;
		this.layoutConstraints.weightx = 1;
		this.layoutConstraints.weighty = 0.5;
		this.panel.add(this.after, this.layoutConstraints);		
	}

	public JPanel getBefore() {
		return before;
	}

	public JPanel getAfter() {
		return after;
	}
	
}
