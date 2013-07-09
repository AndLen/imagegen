package imagegen.gui;

import imagegen.actions.HistoryResponder;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;

/**
 * Provides a pop-up GUI for history manipulation.
 * 
 * @author Andrew
 * 
 */
public class HistoryGUI {
	private JFrame frame;
	private GUI g;

	public HistoryGUI(List<BufferedImage> history, GUI g) {
		this.g = g;
		frame = new JFrame();
		int width = history.size();
		width += (10 - history.size() % 10); // rounds UP to the nearest 10.
		System.out.println("Width: " + width);
		/*
		 * Figure out how big to make our window (going to max width before
		 * getting taller)
		 */
		frame.setPreferredSize(new Dimension(1020, (int)((width * 10.2) + 2)));
		System.out.println(frame.getPreferredSize());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		/* For each image, draw a 10% scale of it. */
		for (BufferedImage image : history) {
			ImagePanel imagePanel = new ImagePanel();
			imagePanel.setMinimumSize(imagePanel.getPreferredSize());
			imagePanel.updatePanel(resize(image, 100,100));
			frame.getContentPane().add(imagePanel);
		}
		// Pretty up and display
		frame.pack();
		frame.addMouseListener(new HistoryResponder(this));
		frame.setVisible(true);

	}

	/**
	 * http://www.javalobby.org/articles/ultimate-image/#11 Resizes a buffered
	 * image to a scaled size.
	 * 
	 * @param img
	 * @param newW
	 * @param newH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return dimg;
	}

	public void showImageAt(int imageX, int imageY) {
		System.out.println("Image index: " + imageY * 10 + imageX);
		g.goToImage(imageY * 10 + imageX);

	}
}
