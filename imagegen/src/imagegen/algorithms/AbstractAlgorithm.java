package imagegen.algorithms;


import java.awt.image.BufferedImage;

/**
 * Provides the requirements for an Image Algorithm that generates an image
 * given an AbstractColour object.
 * 
 * @author Andrew
 */
public interface AbstractAlgorithm {
	public static double RANDOM_FACTOR = 1;

	/**
	 * Creates an image using this algorithm. Will use the AbstractColour given
	 * by the constructor to generate each pixel as it progresses.
	 * 
	 * @return Image result.
	 */
	public  BufferedImage generate();

}
