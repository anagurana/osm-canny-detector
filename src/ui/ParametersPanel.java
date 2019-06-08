package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controler.Controller;
import controler.Controller.UiActionListener;

public class ParametersPanel extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel tresholdDown;
	private JLabel tresholdUp;
	private JLabel radius;
	private JLabel width;
	
	public JSlider valueTD;
	public JSlider valueTU;
	public JSlider valueKR;
	public JSlider valueKW;
	
	public JCheckBox contrastNormalized; 
	
	private JButton open;
	public JButton apply;
	
	private JTextField path;
	private JTextField textTD;
	private JTextField textTU;
	private JTextField textKR;
	private JTextField textKW;
	
	private GridLayout layout;

	ActionListener uiListener;
	
	public ParametersPanel() throws HeadlessException {

		init();
	}

	private void init() {
		
		/*
		 * Frame configuration
		 */
		setLookAndFeel("Nimbus");
		this.setTitle("Displaying images");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setBounds(0, 0, (int) (screenSize.width/3), (int) (screenSize.height/3));
		this.setMinimumSize(new Dimension(screenSize.width/3, screenSize.height/3));
		
		
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
		this.textTD = new JTextField("2.500");
		this.textTU = new JTextField("7.500");
		this.textKR = new JTextField("2.000");
		this.textKW = new JTextField("16");
		
		this.valueTD = new JSlider(0, 10000, 2500);
		this.valueTU = new JSlider(0, 20000, 7500);
		this.valueKR = new JSlider(1000, 17000, 2000);
		this.valueKW = new JSlider(2, 42, 16);

		
		/*
		 * Blocking the user input
		 */
		textTD.setEditable(false);
		textTU.setEditable(false);
		textKR.setEditable(false);
		textKW.setEditable(false);
		path.setEditable(false);
		
		
		/*
		 * Adding ticks into sliders
		 */
		valueTD.setMajorTickSpacing(2000);
		valueTD.setMinorTickSpacing(1000);
		valueTD.setPaintTicks(true);
		
		valueTU.setMajorTickSpacing(4000);
		valueTU.setMinorTickSpacing(1000);
		valueTU.setPaintTicks(true);
		
		valueKR.setMajorTickSpacing(4000);
		valueKR.setMinorTickSpacing(1000);
		valueKR.setPaintTicks(true);
		
		valueKW.setMajorTickSpacing(5);
		valueKW.setMinorTickSpacing(1);
		valueKW.setPaintTicks(true);
		
		/*
		 * Labeling positions in sliders
		 */
		
		valueTD.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionTD = new Hashtable<Integer, JLabel>();
		positionTD.put(0, new JLabel("0"));
		positionTD.put(2000, new JLabel("2"));
		positionTD.put(4000, new JLabel("4"));
		positionTD.put(6000, new JLabel("6"));
		positionTD.put(8000, new JLabel("8"));
		positionTD.put(10000, new JLabel("10"));
		valueTD.setLabelTable(positionTD); 
		
		valueTU.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionTU = new Hashtable<Integer, JLabel>();
		positionTU.put(0, new JLabel("0"));
		positionTU.put(4000, new JLabel("4"));
		positionTU.put(8000, new JLabel("8"));
		positionTU.put(12000, new JLabel("12"));
		positionTU.put(16000, new JLabel("16"));
		positionTU.put(20000, new JLabel("20"));
		valueTU.setLabelTable(positionTU);		
		
		valueKR.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionR = new Hashtable<Integer, JLabel>();
		positionR.put(1000, new JLabel("1"));
		positionR.put(5000, new JLabel("5"));
		positionR.put(9000, new JLabel("9"));
		positionR.put(13000, new JLabel("13"));
		positionR.put(17000, new JLabel("17"));
		valueKR.setLabelTable(positionR); 
		
		valueKW.setPaintLabels(true);
		Hashtable<Integer, JLabel> positionW = new Hashtable<Integer, JLabel>();
		positionW.put(2, new JLabel("2"));
		positionW.put(12, new JLabel("12"));
		positionW.put(22, new JLabel("22"));
		positionW.put(32, new JLabel("32"));
		positionW.put(42, new JLabel("42"));
		valueKW.setLabelTable(positionW); 
		
		/*
		 * Buttons
		 */
		this.contrastNormalized = new JCheckBox("Contrast normalized"); 
		
		
		this.apply = new JButton("Apply");
		this.apply.setEnabled(false);
		this.open = new JButton("Open");
		
		this.apply.setBounds(100, 100, 140, 40);
		this.open.setBounds(100, 100, 140, 40);
		
		
		this.apply.setActionCommand(Controller.UiActionListener.APPLY_VALUES);
		this.open.setActionCommand(Controller.UiActionListener.OPEN_FILE);
		this.contrastNormalized.setActionCommand(Controller.UiActionListener.CONTRAST_CHECKED);
		
		configureLayout();
		
		this.setVisible(true);
	}
	
	/**
	 * 
	 * Adding ActionListener to ParametersPanel
	 */
	public void setUiListener(UiActionListener uiListener) {
		
		this.uiListener = uiListener;
		this.apply.addActionListener(this.uiListener);
		this.open.addActionListener(this.uiListener);
		this.contrastNormalized.addActionListener(this.uiListener);
	}
	
	
		/**
		 * Layout configuration	
		 */
	private void configureLayout() {
			
		this.layout = new GridLayout(6, 3);
		this.setLayout(this.layout);
		
		this.add(this.path);
		this.add(this.open);
		this.add(new JLabel(""));
		
		this.add(this.tresholdDown);
		this.add(this.valueTD);
		this.add(this.textTD);

		this.add(this.tresholdUp);
		this.add(this.valueTU);
		this.add(this.textTU);
		
		this.add(this.radius);
		this.add(this.valueKR);
		this.add(this.textKR);
		
		this.add(this.width);
		this.add(this.valueKW);
		this.add(this.textKW);
		
		this.add(this.contrastNormalized);
		this.add(new JLabel(""));
		this.add(this.apply);

	}
	
	/**
	 * Appearance of widgets 
	 */
	private void setLookAndFeel(String lookAndFeel) {
			
			try {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if (lookAndFeel.equals(info.getName())) {
			            UIManager.setLookAndFeel(info.getClassName());
			            break;
			        }
			    }
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Failed to set " + lookAndFeel + " look and feel. Using the default option.");
			}
			
		}
		
	/*
	 * Getters and setters
	 */
	

	public float getValueTD() {
		return valueTD.getValue()/1000f;
	}

	public float getValueTU() {
		return valueTU.getValue()/1000f;
	}
	
	public float getValueKR() {
		return valueKR.getValue()/1000f;
	}
	
	public int getValueKW() {
		return valueKW.getValue();
	}
	

	public JCheckBox getContrastNormalized() {
		return contrastNormalized;
	}
	
	
	public void setTextTD(String valueTD) {
		this.textTD.setText(valueTD);;
	}
	
	public void setTextTU(String valueTU) {
		this.textTU.setText(valueTU);
	}

	public void setTextKR(String valueKR) {
		this.textKR.setText(valueKR);
	}

	public void setTextKW(String valueKW) {
		this.textKW.setText(valueKW);
	}
	
	public void setPath(String absolutePath) {
		this.path.setText(absolutePath);
		
	}
}	

