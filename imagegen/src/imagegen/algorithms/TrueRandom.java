package imagegen.algorithms;

import imagegen.imageColours.AbstractColour;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;

/**
 * A truly random image - no algorithm applied to the pixels given by AbstractColour.
 * @author Andrew
 *
 */
public class TrueRandom extends AbstractAlgorithm {

	public TrueRandom(AbstractColour colGen, int xSize, int ySize,
			JTextArea textArea) {
		super(colGen, xSize, ySize, textArea);
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
