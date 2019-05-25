package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.dcm4che3.imageio.plugins.dcm.*;

public class dicomRead {

	public dicomRead() {
	
		
	}
	
	public static BufferedImage readImage(String path ) {
		BufferedImage myJpegImage=null;
		File file = new File(path);
	        Iterator<ImageReader> iterator =ImageIO.getImageReadersByFormatName("DICOM");
	        while (iterator.hasNext()) {
	            ImageReader imageReader = (ImageReader) iterator.next();
	            DicomImageReadParam dicomImageReadParam = (DicomImageReadParam) imageReader.getDefaultReadParam();
	            try {
	                ImageInputStream iis = ImageIO.createImageInputStream(file);
	                imageReader.setInput(iis,false);
	                myJpegImage = imageReader.read(0, dicomImageReadParam);
	                iis.close();
	                if(myJpegImage == null){
	                    System.out.println("Could not read image!!");
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
			
		}
	        return myJpegImage;
	}


}
