package interfaces;

import org.bytedeco.javacpp.opencv_core.IplImage;

/**
 * Simple way to change the image of the component from camera.
 * 
 * @author Jordan Aranda Tejada
 */
public interface CameraObserver {

	/**
	 * Changes the image of the component to reflect the new image from camera.
	 * 
	 * @param newImage
	 *            The new image to set
	 */
	public void changeImage(IplImage newImage);
}