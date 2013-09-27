/**
 * 
 */
package imagegen.algorithms;

import java.awt.image.BufferedImage;

import javax.swing.JTextArea;

import imagegen.imageColours.AbstractColour;

/**
 * @author Andrew
 * 
 */
public class ImageDiamond extends ImageSpots {

	public ImageDiamond(AbstractColour colGen, int xSize, int ySize,
			JTextArea textArea) {
		super(colGen, xSize, ySize, textArea);
	}

	protected void spots(int x, int y, BufferedImage completePic, int toSpread) {
		spiral(x, y, completePic, toSpread, 1);
	}

	/**
	 * @param x
	 *            Centre
	 * @param y
	 *            Centre
	 * @param completePic
	 * @param toSpread
	 * @param length
	 */
	private void spiral(final int xCo, final int yCo,
			BufferedImage completePic, int toSpread, final int length) {
		if (adjustableRandom.nextDouble() < 0.5)
			return;
		int x = xCo + length;
		int y = yCo;
		/* Does right to top edge of diamond */
		for (int i = 0; i <= length; i++,x--,y--) {
			if (inBounds(x, y, completePic) && completedPixels[x][y] == false) {
				completePic.setRGB(x, y, toSpread);
				completedPixels[x][y] = true;
			}
		}
		/* Does top to left edge  of diamond */
		for (int i = 0; i <= length; i++,x--,y++) {
			if (inBounds(x, y, completePic) && completedPixels[x][y] == false) {
				completePic.setRGB(x, y, toSpread);
				completedPixels[x][y] = true;
			}
		}
		/* Does left to bottom edge of diamond */
		for (int i = 0; i <= length; i++,x++,y++) {
			if (inBounds(x, y, completePic) && completedPixels[x][y] == false) {
				completePic.setRGB(x, y, toSpread);
				completedPixels[x][y] = true;
			}
		}
		/* Does bottom to right edge of diamond */
		for (int i = 0; i <= length; i++,x++,y--) {
			if (inBounds(x, y, completePic) && completedPixels[x][y] == false) {
				completePic.setRGB(x, y, toSpread);
				completedPixels[x][y] = true;
			}
		}
		spiral(xCo, yCo, completePic, toSpread, length + 4);
	}
}
