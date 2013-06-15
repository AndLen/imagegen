package imagegen.imageColours;

import java.awt.Color;

/**
 * Simply returns a colour with random R,G,B values (0 - 255 inclusive)
 * 
 * @author Andrew
 */
public class FullColour extends AbstractColour {

	@Override
	public Color getPixel() {
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}

}