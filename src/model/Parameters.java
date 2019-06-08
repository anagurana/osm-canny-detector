package model;

/**
 * 
 * The class that represents the set of parameters for Canny Edge Detector 
 *
 */
public class Parameters {
	
	private float tresholdDown;
	private float tresholdUp;
	private float kerRadius;
	private int kerWidth;
	private boolean contrastNormalized; 
	
	private String path;

	/**
	 * Constructor with default parameters
	 */
	 public Parameters() {
	        this.tresholdDown = 2.5f;
	        this.tresholdUp = 7.5f;
	        this.kerRadius = 2f;
	        this.kerWidth = 16;
	        this.contrastNormalized = false;
	    }
	
	public float getTresholdDown() {
		return tresholdDown;
	}

	public void setTresholdDown(float treshDown) {
		this.tresholdDown = treshDown;
	}

	public float getTresholdUp() {
		return tresholdUp;
	}

	public void setTresholdUp(float treshUp) {
		this.tresholdUp = treshUp;
	}

	public float getKerRadius() {
		return kerRadius;
	}

	public void setKerRadius(float kerRadius) {
		this.kerRadius = kerRadius;
	}

	public int getKerWidth() {
		return kerWidth;
	}

	public void setKerWidth(int kerWidth) {
		this.kerWidth = kerWidth;
	}

	public boolean isContrastNormalized() {
		return contrastNormalized;
	}

	public void setContrastNormalized(boolean contrastNormalized) {
		this.contrastNormalized = contrastNormalized;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
