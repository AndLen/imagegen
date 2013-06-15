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
public class ImageSpots extends AbstractImage {
	Random r;
	boolean[][] completedPixels;

	/**
	 * See interface.
	 */
	public ImageSpots(AbstractColour colGen, int xSize, int ySize,
			JTextArea textArea) {
		super(colGen, xSize, ySize, textArea);
		r = new Random();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see imagegen.algorithms.AbstractImage#generate()
	 */
	@Override
	public BufferedImage generate() {
		BufferedImage completePic = new BufferedImage(xSize, ySize,
				BufferedImage.TYPE_INT_RGB);
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
		for (float rand = r.nextFloat(); rand < 0.9; rand = r.nextFloat()) {
			if (rand < 0.225) {
				i--;
			} else if (rand < 0.450) {
				i++;
			} else if (rand < 0.675) {
				j--;
			} else {
				j++;
			}
			if (inBounds(i, j, completePic) && completedPixels[i][j]==false) {
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
