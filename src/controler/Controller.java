package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.CannyEdgeDetector;
import model.DisplayImages;
import model.Parameters;
import ui.ImagesPanel;
import ui.ParametersPanel;

import org.dcm4che2.io.*;
import org.dcm4che2.data.*;
import org.dcm4che2.util.*;

import java.io.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;



	/**
	 * Class that connects UI and Data
	 */

public class Controller {
	
	private ImagesPanel images;
	private ParametersPanel parametersView;
	private Parameters parameterValues;
	
	public UiActionListener uiListener;
	

	public Controller(ParametersPanel parametersView, Parameters parameterValues) {
		
		this.parametersView = parametersView;
		this.parameterValues = parameterValues;
		
		this.uiListener = new UiActionListener();
		this.parametersView.setUiListener(this.uiListener);
	
		/*
		 * Showing the sliders values and applying them to Parameters 
		 */
		
		parametersView.valueTD.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	        	 parametersView.setTextTD("" + parametersView.getValueTD()); 
	        	 parameterValues.setTresholdDown(parametersView.getValueTD());
	        	 
	        	 if (parametersView.getValueTU()<parametersView.getValueTD())
		        	 parametersView.valueTD.setValue((int)(parametersView.getValueTU()*1000));
	         }
	      });
		
		parametersView.valueTU.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	        	 parametersView.setTextTU("" + parametersView.getValueTU()); 
	        	 parameterValues.setTresholdUp(parametersView.getValueTU());
	        	 
	        	 if(parametersView.getValueTU()<parametersView.getValueTD())
	        	 parametersView.valueTU.setValue((int)(parametersView.getValueTD()*1000));
	         }
	      });
		
		parametersView.valueKR.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	        	parametersView.setTextKR("" + parametersView.getValueKR()); 
	        	parameterValues.setKerRadius(parametersView.getValueKR());
	         }
	      });
		
		parametersView.valueKW.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent ce) {
	        	parametersView.setTextKW("" + parametersView.getValueKW()); 
		        parameterValues.setKerWidth(parametersView.getValueKW());
	         }
	      });
	}
	
	public class UiActionListener implements ActionListener {
		
		/**
		 * Event codes
		 */
		public static final String OPEN_FILE = "OPEN_FILE";
		public static final String APPLY_VALUES = "APPLY_VALUES";
		public static final String CONTRAST_CHECKED = "CONTRAST_CHECKED";
		
		
		/**
		 * Method that catch events and decyding about further performance
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
				
			switch (e.getActionCommand()) {
			
				case OPEN_FILE:
					openFile();	
					
					break;

				case APPLY_VALUES:
					applyValues();
					break;
					
				case CONTRAST_CHECKED:
					contrastCheckes();
					break;
				
					}
			}
		}

		/**
		 * Opening the file after clicking on OPEN button
		 */
		void openFile() {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open");
			
			/*
			 * Limiting only to DICOM format
			 */
			FileNameExtensionFilter dicomFilter = new FileNameExtensionFilter("DICOM Files", "dcm");
			fileChooser.setFileFilter(dicomFilter);
			int result = fileChooser.showOpenDialog(null);	
			if (result == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().getAbsolutePath().endsWith(".dcm")) {
			parametersView.setPath(fileChooser.getSelectedFile().getAbsolutePath());
			parameterValues.setPath(fileChooser.getSelectedFile().getAbsolutePath());
			parametersView.apply.setEnabled(true);
			
			this.images =  new ImagesPanel();
			this.images.setVisible(false);
			
			} else if (result == JFileChooser.CANCEL_OPTION) {}
			else {
				JOptionPane.showMessageDialog(Controller.this.parametersView, "Niepoprawny format");
			}
		}

		/**
		 * Applying the parameters and showing the result after clicking on APPLY button
		 */
		void applyValues() {
			
			CannyEdgeDetector filter = new CannyEdgeDetector();
			 
			 /*
			  * Adjusting parameters as desired
			  */
			 filter.setTresholdDown(parameterValues.getTresholdDown());
			 filter.setTresholdUp(parameterValues.getTresholdUp());
			 filter.setGaussianKernelRadius(parameterValues.getKerRadius());
			 filter.setGaussianKernelWidth(parameterValues.getKerWidth());
			 filter.setContrastNormalized(parameterValues.isContrastNormalized());
			 
			 /*
			  * Applying it to an image
			  */
			 BufferedImage image = null;
			 try {
				 
				image = ImageIO.read(new File(parameterValues.getPath()));
				filter.setSourceImage(image); 
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(Controller.this.parametersView, "Error while reading DICOM image data");
				System.exit(-1);
			}
			 
			 BufferedImage source = filter.getSourceImage();
			 filter.process();
			 BufferedImage edges = filter.getEdgesImage();
			 
			 /*
			  * Displaying the images
			  */
			 new DisplayImages();
			 this.images.setSourceImg(DisplayImages.DisplayImage(source));
			 this.images.setEdgesImg(DisplayImages.DisplayImage(edges));
			 this.images.setVisible(true);
			 }

		
		void contrastCheckes() {
			parameterValues.setContrastNormalized(parametersView.getContrastNormalized().isSelected());
		}
		
}
