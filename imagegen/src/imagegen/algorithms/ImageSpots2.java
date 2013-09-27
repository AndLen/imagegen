package imagegen.algorithms;

import imagegen.imageColours.AbstractColour;

import java.awt.image.BufferedImage;

import javax.swing.JTextArea;

/**
 * Very similar algorithm to ImageSpots but with a larger number of iterations
 * giving quite a different result!
 * 
 * @author Andrew
 * @see imagegen.algorithms.ImageSpots
 */
public class ImageSpots2 extends ImageSpots {

	/**
	 * See interface.
	 */
	public ImageSpots2(AbstractColour colGen, int xSize, int ySize,
			JTextArea textArea) {
		super(colGen, xSize, ySize, textArea);
	}

	/*
	 * (non-Javadoc) Like parent, but continues to spread for many more
	 * iterations, and can move on diagonals.
	 * 
	 * @see imagegen.algorithms.ImageSpots#spots(int, int,
	 * java.awt.image.BufferedImage, int)
	 */
	@Override
	protected void spots(int i, int j, BufferedImage completePic, int toSpread) {
		int originalI = i;
		int originalJ = j;
		do {
			double rand = random.nextDouble();
			if (rand < 0.125) {
				i--;
			} else if (rand < 0.25) {
				i++;
			} else if (rand < 0.375) {
				j--;
			} else if (rand < 0.5) {
				j++;
			} else if (rand < 0.625) {
				i--;
				j--;
			} else if (rand < 0.75) {
				i--;
				j++;
			} else if (rand < 0.875) {
				i++;
				j--;
			} else {
				i++;
				j++;
			}
			if (inBounds(i, j, completePic) && completedPixels[i][j] == false) {
				completePic.setRGB(i, j, toSpread);
				completedPixels[i][j] = true;
			}
		} while (i != originalI && j != originalJ);
	}

}
