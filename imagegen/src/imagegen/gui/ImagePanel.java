package imagegen.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Simple JPanel used to draw images to.
 * 
 * @author Andrew
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Image to draw!
	 */
	private BufferedImage image;

	/**
	 * Create the panel.
	 */
	public ImagePanel() {

	}

	/**
	 * Used to set the panel up if there's no image to display (i.e. when
	 * program launched).
	 */
	public void initialise() {
		image = new BufferedImage(this.getWidth(), this.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		int white = Color.white.getRGB();
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				image.setRGB(i, j, white);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (image == null)
			initialise();
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * Simply stores the image in the program. Will be re-drawn when Swing gets
	 * round to it, of course.
	 * 
	 * @param image
	 *            Image to draw
	 */
	public void updatePanel(BufferedImage image) {
		this.image = image;
	}

}
