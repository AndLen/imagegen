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
public class ImageSpiral extends ImageSpots2 {

	public ImageSpiral(AbstractColour colGen, int xSize, int ySize,
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
	/* Missed bottom of each spiral.*/
	private void spiral(final int xCo, final int yCo, BufferedImage completePic, int toSpread,
			int length) {
		if(length > 1) return;
//		if (r.nextFloat() > 0.9)
//			return;
		int x = xCo + length;
		int y = yCo;
		/* Does right side of spiral */
		for (int i = 0; i <= length; i++) {
			if (inBounds(x - i, y, completePic) && completedPixels[x - i][y] == false) {
				completePic.setRGB(x - i, y, toSpread);
				completedPixels[x - i][y] = true;
			}
		}
		x = xCo;
		y = yCo-length;
		/* Does top side of spiral */
		for (int i = 0; i <= length; i++) {
			if (inBounds(x, y - i, completePic) && completedPixels[x][y-i] == false) {
				completePic.setRGB(x, y - i, toSpread);
				completedPixels[x][y - i] = true;
			}
		}
		x = xCo-length;
		y = yCo;
		/* Does left side of spiral */
		for (int i = 0; i <= length; i++) {
			if (inBounds(x + i, y, completePic)&&completedPixels[x + i][y] == false) {
				completePic.setRGB(x + i, y, toSpread);
				completedPixels[x + i][y] = true;
			}
		}
		x = xCo;
		y = yCo+length;
		/* Does bottom side of spiral */

		for (int i = 0; i <= length; i++) {
			if (inBounds(x, y + i, completePic)&&completedPixels[x][y+i] == false) {
				completePic.setRGB(x, y + i, toSpread);
				completedPixels[x][y + i] = true;
			}
		}
		x++;
		y++;
		spiral(x, y, completePic, toSpread, length + 1);
	}
}
