package imagegen.imageColours;

import java.awt.Color;
import java.util.Random;

/**
 * Provides a framework for colour implementations - all children should be able
 * to return a pixel each time one is requested, so must track all internal data
 * themselves. An AbstractColour may currently be used for more than one picture!
 * FUTURE: Change this? Might be problematic for some algorithms the way it is.
 * 
 * @author Andrew
 */
public abstract class AbstractColour {
	/**
	 * Used in generation of images.
	 */
	Random r;

	/**
	 * Super constructor.
	 */
	public AbstractColour() {
		r = new Random();
	}

	/**
	 * @return Color object representing the pixel.
	 */
	public abstract Color getPixel();
}
