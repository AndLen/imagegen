package imagegen.imageColours;

import java.awt.Color;

/**
 * Returns a grey pixel - that is, one with the same value for each of R, G and
 * B components. Note that grey includes white or black.
 * 
 * @author Andrew
 * 
 */
public class GreyScale extends AbstractColour {

	@Override
	public Color getPixel() {
		int colVal = r.nextInt(256);
		return new Color(colVal, colVal, colVal);
	}

}