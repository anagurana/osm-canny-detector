package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 
 * The frame that shows 2 images aside
 *
 */
public class ImagesPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel    	before;
	private JPanel    	after;
	private JLabel 		sourceImg;
	private JLabel 		edgesImg;
	
	private GridLayout	layout;
	
	public ImagesPanel()  {
		 
			init();
	}

	/**
	 * Initialization of Images Frame
	 */
	private void init()  {
		/* 
		 * Frame configuration
		 */
		
		this.setTitle("Displaying images");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setBounds(0, 0, (int) (screenSize.width/2), (int) (screenSize.height/2));
		this.setMinimumSize(new Dimension(screenSize.width/2, screenSize.height/2));
		
		this.setLocation(getWidth(), getHeight()/2);
		
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
		
		configureLayout();

		this.setVisible(true);
		
	}

	/**
	 * Adding source image to panel
	 * @param sourceImg Label from DisplayImages
	 */
	public void setSourceImg(JLabel sourceImg) {
		this.sourceImg = sourceImg;
		this.before.removeAll();
		this.before.add(this.sourceImg);
	}


	/**
	 * Adding edges image to panel
	 * @param edgesImg Label from DisplayImage
	 */
	public void setEdgesImg(JLabel edgesImg) {
		this.edgesImg = edgesImg;
		this.after.removeAll();
		this.after.add(this.edgesImg);
	}

	/**
	 * Layout : 2 columns 
	 */
	private void configureLayout() {
		
		this.layout = new GridLayout(1,0);	
		this.setLayout(this.layout);
		
		this.add(this.before);
		this.add(this.after);
	}
	
}
