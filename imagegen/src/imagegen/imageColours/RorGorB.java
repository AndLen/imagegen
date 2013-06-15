package imagegen.imageColours;

import java.awt.Color;

/**
 * Generates only pure Red or Green or Blue pixels. For example, a red
 * pixel would correspond to a value of 0 to 256 on the Red component and 0 on
 * the Green and Blue ones.
 * 
 * @author Andrew
 */
public class RorGorB extends AbstractColour {

	@Override
	public Color getPixel() {
		int random = r.nextInt(3);
		if (random == 0) {
			return new Color(r.nextInt(256), 0, 0);
		} else if (random == 1) {
			return new Color(0, r.nextInt(256), 0);
		} else {
			return new Color(0, 0, r.nextInt(256));
		}
	}

}
