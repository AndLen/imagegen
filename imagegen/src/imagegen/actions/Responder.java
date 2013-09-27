package imagegen.actions;

import imagegen.algorithms.*;
import imagegen.gui.GUI;
import imagegen.imageColours.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Responds to user input by making appropriate method calls.
 * 
 * @author Andrew
 */
public class Responder implements ActionListener {

	private AbstractColour colourType;
	private GUI gui;
	private String imageType;

	/**
	 * @param gui
	 *            To draw to!
	 */
	public Responder(GUI gui) {
		this.gui = gui;
	}

	/*
	 * 
	 * @author Andrew & Michael Baird
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();

		new Thread(new Runnable() {
			public void run() {
				buttonPerformed(actionCommand);
			}
		}).start();
	}

	/*
	 * @author Andrew
	 */
	public void buttonPerformed(String actionCommand) {
		/* Run through possible commands, updating variables where relevant. */
		if (actionCommand.equals("Monotone")) {
			colourType = new Monotone();
		} else if (actionCommand.equals("Full Colour")) {
			colourType = new FullColour();
		} else if (actionCommand.equals("Greyscale")) {
			colourType = new GreyScale();
		} else if (actionCommand.equals("R|G|B")) {
			colourType = new RorGorB();
		} else if (actionCommand.equals("Random")) {
			imageType = "Random";
		} else if (actionCommand.equals("Spots")) {
			imageType = "Spots";
		} else if (actionCommand.equals("Spots 2")) {
			imageType = "Spots 2";
		} else if (actionCommand.equals("Diamond")) {
			imageType = "Spiral";
		} else if (actionCommand.equals("Settings")) {
			gui.showSettings();
			return;
		} else if (actionCommand.equals("Average Image")) {
			gui.averageImages();
			return;
		} else if (actionCommand.equals("Save Image")) {
			gui.saveImage();
			return;
		} else if (actionCommand.equals("Save All")) {
			gui.saveAllImages();
			return;
		} else if (actionCommand.equals("History")) {
			gui.historyGUI();
			return;
		}
		render();

	}

	public void render() {
		AbstractAlgorithm imgGen;
		/*
		 * Now generate the appropriate image! Done separate to above so will
		 * re-draw whenever any button is pressed.
		 */
		double scaleSize = gui.getScaleSize();
		int width = (int) (gui.imageWidth() / scaleSize), height = (int) (gui
				.imageHeight() / scaleSize);
		if (imageType.equals("Spots")) {
			imgGen = new ImageSpots(colourType, width, height,
					gui.getTextArea());
		} else if (imageType.equals("Spots 2")) {
			imgGen = new ImageSpots2(colourType, width, height,
					gui.getTextArea());
		} else if (imageType.equals("Spiral")) {
			imgGen = new ImageDiamond(colourType, width, height,
					gui.getTextArea());
		} else {
			imgGen = new TrueRandom(colourType, width, height);
		}
		gui.drawImage(imgGen, scaleSize);
	}

	/**
	 * Effectively a secondary constructor that should only be called once the
	 * GUI is initialised properly. If it isn't called, variables won't be
	 * initialised correctly.
	 */
	public void setUp() {
		colourType = new Monotone();
		imageType = "Random";
	}

}