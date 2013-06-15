package imagegen.imageColours;

import java.awt.Color;

/**
 * generates pure black and pure white pixels, giving a barcode or newspaper
 * look. Can give some of the most interesting pictures, as it allows the
 * algorithms to work their magic without overlapping colours.
 * 
 * @author Andrew
 */
public class Monotone extends AbstractColour {

	@Override
	public Color getPixel() {
		if (r.nextFloat() < 0.5)
			return Color.black;
		else
			return Color.white;
	}

}
