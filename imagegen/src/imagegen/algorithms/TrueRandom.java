package imagegen.algorithms;

import imagegen.imageColours.AbstractColour;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A truly random image - no algorithm applied to the pixels given by AbstractColour.
 * @author Andrew
 *
 */
public class TrueRandom implements AbstractAlgorithm {
	private final AbstractColour colGen;
	private final int xSize, ySize;

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
	public TrueRandom(AbstractColour colGen, int xSize, int ySize) {
		this.colGen = colGen;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	@Override
	public BufferedImage generate() {
		BufferedImage completePic = new BufferedImage(xSize, ySize,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				Color newPixel = colGen.getPixel();
				completePic.setRGB(i, j, newPixel.getRGB());
			}
		}
		return completePic;
	}

}
