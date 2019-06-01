package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controler.Controller;

public class Parameters extends JFrame implements ActionListener{
	
	private JLabel tresholdDown;
	private JLabel tresholdUp;
	private JLabel radius;
	private JLabel width;
	
	private JSlider treshDown;
	private JSlider treshUp;
	private JSlider kerRadius;
	private JSlider kerWidth;
	
	private JCheckBox contrastNormalized; 
	
	private JButton open;
	private JButton apply;
	
	private JTextField path;
	private JTextField valueTD;
	private JTextField valueTU;
	private JTextField valueKR;
	private JTextField valueKW;
	
	private Container  panel;
	private GridLayout layout;
	
	public Parameters() throws HeadlessException {

		init();
	}
		
	public String getPath() {
		return path.getText().toString();
	}
	

	public float getTreshDown() {
		return treshDown.getValue()/1000f;
	}

	public float getTreshUp() {
		return treshUp.getValue()/1000f;
	}
	
	public float getKerRadius() {
		return kerRadius.getValue()/1000f;
	}
	
	public int getKerWidth() {
		return kerWidth.getValue();
	}
	

	public JCheckBox getContrastNormalized() {
		return contrastNormalized;
	}

	private void init() {
		
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
		 * Labels 
		 */
		this.tresholdDown = new JLabel("Treshold Down");
		this.tresholdUp = new JLabel("Treshold Up");
		this.radius = new JLabel("Kernel radius");
		this.width = new JLabel("Kernel width");

		/*
		 * Editing fields
		 */
		this.path = new JTextField("");
		this.valueTD = new JTextField("2.500");
		this.valueTU = new JTextField("7.500");
		this.valueKR = new JTextField("2.000");
		this.valueKW = new JTextField("16");
		
		JSlider treshDown = new JSlider(0, 10000, 3000);
		JSlider treshUp = new JSlider(0, 20000, 5000);
		JSlider kerRadius = new JSlider(1000, 17000, 2000);
		JSlider kerWidth = new JSlider(2, 42, 16);
		
		this.treshDown = treshDown;
		this.treshUp = treshUp;
		this.kerRadius = kerRadius;
		this.kerWidth = kerWidth;
		
		/*
		 * Showing the slider value 
		 */
		
		treshDown.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	            valueTD.setText("" + getTreshDown());
	         }
	      });
		
		treshUp.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	            valueTU.setText("" + getTreshUp());
	         }
	      });
		
		kerRadius.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	            valueKR.setText("" + getKerRadius());
	         }
	      });
		
		kerWidth.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	            valueKW.setText("" + getKerWidth());
	         }
	      });
		
		/*
		 * Blocking the user input
		 */
		valueTD.setEditable(false);
		valueTU.setEditable(false);
		valueKR.setEditable(false);
		valueKW.setEditable(false);
		path.setEditable(false);
		
		
		/*
		 * Adding ticks into sliders
		 */
		treshDown.setMajorTickSpacing(2000);
		treshDown.setMinorTickSpacing(1000);
		treshDown.setPaintTicks(true);
		
		treshUp.setMajorTickSpacing(4000);
		treshUp.setMinorTickSpacing(1000);
		treshUp.setPaintTicks(true);
		
		kerRadius.setMajorTickSpacing(4000);
		kerRadius.setMinorTickSpacing(1000);
		kerRadius.setPaintTicks(true);
		
		kerWidth.setMajorTickSpacing(5);
		kerWidth.setMinorTickSpacing(1);
		kerWidth.setPaintTicks(true);
		
		/*
		 * Labeling positions in sliders
		 */
		
		treshDown.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionTD = new Hashtable<Integer, JLabel>();
		positionTD.put(0, new JLabel("0"));
		positionTD.put(2000, new JLabel("2"));
		positionTD.put(4000, new JLabel("4"));
		positionTD.put(6000, new JLabel("6"));
		positionTD.put(8000, new JLabel("8"));
		positionTD.put(10000, new JLabel("10"));
		treshDown.setLabelTable(positionTD); 
		
		treshUp.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionTU = new Hashtable<Integer, JLabel>();
		positionTU.put(0, new JLabel("0"));
		positionTU.put(4000, new JLabel("4"));
		positionTU.put(8000, new JLabel("8"));
		positionTU.put(12000, new JLabel("12"));
		positionTU.put(16000, new JLabel("16"));
		positionTU.put(20000, new JLabel("20"));
		treshUp.setLabelTable(positionTU);		
		
		kerRadius.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionR = new Hashtable<Integer, JLabel>();
		positionR.put(1000, new JLabel("1"));
		positionR.put(5000, new JLabel("5"));
		positionR.put(9000, new JLabel("9"));
		positionR.put(13000, new JLabel("13"));
		positionR.put(17000, new JLabel("17"));
		kerRadius.setLabelTable(positionR); 
		
		kerWidth.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionW = new Hashtable<Integer, JLabel>();
		positionW.put(2, new JLabel("2"));
		positionW.put(12, new JLabel("12"));
		positionW.put(22, new JLabel("22"));
		positionW.put(32, new JLabel("32"));
		positionW.put(42, new JLabel("42"));
		kerWidth.setLabelTable(positionW); 
		
		/*
		 * Buttons
		 */
		this.contrastNormalized = new JCheckBox("Contrast normalized"); 
		
		this.apply = new JButton("Apply");
		this.open = new JButton("Open");
		
		this.apply.setBounds(100, 100, 140, 40);
		this.open.setBounds(100, 100, 140, 40);
		
		this.apply.addActionListener(this);
		this.open.addActionListener(this);
		
		configureLayout();
		
		this.setVisible(true);
	}

	public void setListener(ActionListener Listener) {
		//this.uiListener = uiListener;
		this.apply.addActionListener(this);
		this.apply.setActionCommand("Apply");
		this.open.addActionListener(this);
		this.open.setActionCommand("Open");
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if (action.equals("Open")) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open");
			int result = fileChooser.showOpenDialog(null);	
			if (result == JFileChooser.APPROVE_OPTION) {
				this.path.setText(fileChooser.getSelectedFile().getAbsolutePath());
			
			}
		} 
		else if (action.equals("Apply")) {
				var Controller = new Controller(this);
		}
			
	}
	
	
	private void configureLayout() {
			
		this.layout = new GridLayout(6, 4);
		this.setLayout(this.layout);
		
		this.add(this.path);
		this.add(this.open);
		this.add(new JLabel(""));
		this.add(new JLabel(""));

		this.add(this.tresholdDown);
		this.add(this.treshDown);
		this.add(this.valueTD);
		this.add(this.apply);

		this.add(this.tresholdUp);
		this.add(this.treshUp);
		this.add(this.valueTU);
		this.add(new JLabel(""));
		
		this.add(this.radius);
		this.add(this.kerRadius);
		this.add(this.valueKR);
		this.add(new JLabel(""));
		
		this.add(this.width);
		this.add(this.kerWidth);
		this.add(this.valueKW);
		this.add(new JLabel(""));
		
		this.add(this.contrastNormalized);

	}
}	

