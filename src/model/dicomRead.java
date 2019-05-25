package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.dcm4che2.io.DicomInputStream;


import org.dcm4che2.data.DicomObject;
import org.dcm4che3.data.Tag;
import org.dcm4che2.data.*;
import org.dcm4che2.media.*;

import org.dcm4che2.image.OverlayUtils;
import org.dcm4che2.tool.dcmqr.DcmQR;
import com.sun.image.codec.jpeg.JPEGCodec;	 
import com.sun.image.codec.jpeg.JPEGImageEncoder;
s
public class dicomRead {

	public dicomRead() {
		
	}
	
	public BufferedImage  readImage(String path ) {
		//read dicom image
		path = "C:\\Users\\asyag\\Desktop\\study\\OSM\\2 projekt\\SecondProject\\in.dcm";
		BufferedImage bufferImg = null;
		try {
			FileInputStream fis = new FileInputStream(path);
			var dis = new DicomInputStream(fis);
			
			
			DicomObject obj = dis.readDicomObject();
		    Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
		    ImageReader reader = iter.next();

		    String overlay = obj.getString(Tag.OverlayData);
		    
		    if(overlay != null && overlay.length() > 0) {
		        bufferImg = OverlayUtils.extractOverlay(obj, Tag.OverlayData, reader, "FFFFFF");
		    }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//convert dicom to bufferedImage and return
		return bufferImg;
	}

}
