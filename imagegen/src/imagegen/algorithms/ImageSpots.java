package imagegen.algorithms;

import imagegen.imageColours.AbstractColour;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JTextArea;

/**
 * Creates "spots" of each colour around each pixel. This is achieved through
 * iteration: for each pixel, spread to a random direction until a random float
 * > 0.8 is achieved (see spots class).
 * 
 * @author Andrew
 */
public class ImageSpots implements AbstractAlgorithm {
	final Random random;
	// Special implementation that allows the user to weight how "random" an
	// image (how many iterations, essentially). Should not be used if we want
	// 0 <= value <= 1 && uniformly distributed in this range! Assumptions
	// cannot be made about the value going over a certain factor or you risk a
	// stack overflow.
	final Random adjustableRandom;
	// If a pixel has already been coloured - we don't want to overwrite or else
	// we lose patterns
	boolean[][] completedPixels;

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
	public ImageSpots(AbstractColour colGen, int xSize, int ySize,
			JTextArea textArea) {
		this.colGen = colGen;
		this.xSize = xSize;
		this.ySize = ySize;
		this.textArea = textArea;

		random = new Random();
		adjustableRandom = new Random() {
			private static final long serialVersionUID = 1L;

			public double nextDouble() {
				return super.nextDouble() * RANDOM_FACTOR;
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see imagegen.algorithms.AbstractAlgorithm#generate()
	 */
	@Override
	public BufferedImage generate() {
		BufferedImage completePic = new BufferedImage(xSize, ySize,
				BufferedImage.TYPE_INT_RGB);
		// initialises to false
		completedPixels = new boolean[xSize][ySize];
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				// spots() may have got here first!
				if (completedPixels[i][j] == false) {
					Color newPixel = colGen.getPixel();
					completePic.setRGB(i, j, newPixel.getRGB());
					completedPixels[i][j] = true;
					this.spots(i, j, completePic, newPixel.getRGB());
				}

			}
		}
		return completePic;
	}

	/**
	 * Creates the spot effect around a given co-ordinate.
	 * 
	 * @param i
	 *            x-coord to start at
	 * @param j
	 *            y-coord to start at
	 * @param completePic
	 *            Picture so far
	 * @param toSpread
	 *            RGB colour representation of the pixel we're on.
	 */
	protected void spots(int i, int j, BufferedImage completePic, int toSpread) {
		// 10% chance to stop, 90% chance to keep going on each iteration
		for (double rand = random.nextDouble(); rand < 0.9; rand = random
				.nextDouble()) {
			if (rand < 0.225) {
				i--;
			} else if (rand < 0.450) {
				i++;
			} else if (rand < 0.675) {
				j--;
			} else {
				j++;
			}
			if (inBounds(i, j, completePic) && completedPixels[i][j] == false) {
				completePic.setRGB(i, j, toSpread);
				completedPixels[i][j] = true;
			}
		}
	}

	protected boolean inBounds(int i, int j, BufferedImage completePic) {
		return (i > 0 && j > 0 && i < completePic.getWidth() && j < completePic
				.getHeight());
	}

}
