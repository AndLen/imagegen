package imagegen.algorithms;

import imagegen.imageColours.AbstractColour;

import java.awt.image.BufferedImage;

import javax.swing.JTextArea;

/**
 * Provides the requirements for an Image Algorithm that
 * generates an image given an AbstractColour object.
 * @author Andrew 
 */
public abstract class AbstractAlgorithm {
	protected AbstractColour colGen;
	protected int xSize, ySize;
	protected JTextArea textArea;

	/**
	 * @param colGen
	 *            to use in the algorithm.
	 * @param xSize
	 *            width of image (# of pixels)
	 * @param ySize
	 *            height of image (# of pixels)
	 * @param textArea
	 *            To display % progress etc.
	 */
	public AbstractAlgorithm(AbstractColour colGen, int xSize, int ySize, JTextArea textArea) {
		this.colGen = colGen;
		this.xSize = xSize;
		this.ySize = ySize;
		this.textArea = textArea;
	}

	/**
	 * Creates an image using this algorithm. Will use the AbstractColour given by
	 * the constructor to generate each pixel as it progresses.
	 * 
	 * @return Image result.
	 */
	public abstract BufferedImage generate();

}
