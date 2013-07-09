package imagegen.gui;

import imagegen.actions.Responder;
import imagegen.algorithms.AbstractAlgorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

/**
 * Aptly named class which creates the swing layouts and other elements needed
 * to display the program to the user. Also deals with the high-level
 * processing: saving images, drawing images to the screen etc.
 * 
 * @author Andrew
 */
public class GUI {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	/* Should be given to all components for I/O. */
	private Responder r;
	private ImagePanel imagePanel;
	/*
	 * For future use - undoing, seeing previous images etc. Maybe should be a
	 * list if we want to go back and forwards?
	 */
	private List<BufferedImage> history = new ArrayList<BufferedImage>();
	private int historyIndex = 0;
	JSlider sizeSlider;
	private static JTextArea textArea;

	private JPanel buttonPanel;

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Uses a BufferedImage to create a visual representation of the data that
	 * can be saved and drawn on the screen effectively. Also draws it (duh).
	 * 
	 * @param imgGen
	 *            Image Generator to use to draw the picture.
	 * @param pixelSize
	 *            1 = 1x1 pixels, 2 = 2x2 pixels etc.
	 */
	public synchronized void drawImage(AbstractAlgorithm imgGen, int pixelSize) {
		BufferedImage toDraw = imgGen.generate();
		int scaledWidth = toDraw.getWidth() * pixelSize;
		int scaledHeight = toDraw.getHeight() * pixelSize;
		BufferedImage scaled = new BufferedImage(scaledWidth, scaledHeight,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < scaledWidth; i++) {
			for (int j = 0; j < scaledHeight; j++) {
				scaled.setRGB(i, j, toDraw.getRGB(i / pixelSize, j / pixelSize));
			}
		}
		paintImage(scaled);
	}

	public synchronized void paintImage(BufferedImage toAdd) {
		imagePanel.updatePanel(toAdd);
		imagePanel.update(imagePanel.getGraphics());
		history.add(toAdd);
		historyIndex = history.size() - 1;
	}

	/**
	 * @return all images created.
	 */
	public List<BufferedImage> getHistory() {
		return history;
	}

	/**
	 * Slider corresponding to pixel size.
	 * 
	 * @return pixel slider.
	 */
	public JSlider getSlider() {
		return sizeSlider;
	}

	/**
	 * Small text area for displaying messages to the user.
	 * 
	 * @return text area
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * height of the panel used to display images (i.e. height of images we
	 * create!).
	 * 
	 * @return # of vertical pixels
	 */
	public int imageHeight() {
		return imagePanel.getHeight();
	}

	/**
	 * width of the panel used to display images (i.e. width of images we
	 * create!).
	 * 
	 * @return # of horizontal pixels
	 */
	public int imageWidth() {
		return imagePanel.getWidth();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		r = new Responder(this);

		frame = new JFrame();
		frame.setPreferredSize(new Dimension(1000, 800));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		buttonPanel.setPreferredSize(new Dimension(100, 500));
		buttonPanel.setMinimumSize(new Dimension(100, 500));
		frame.getContentPane().add(buttonPanel, BorderLayout.WEST);

		JButton btnMonotone = new JButton("Monotone");
		btnMonotone.addActionListener(r);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		btnMonotone.setAlignmentX(0.5f);
		buttonPanel.add(btnMonotone);

		JButton btnFullColour = new JButton("Full Colour");
		btnFullColour.addActionListener(r);
		btnFullColour.setAlignmentX(0.5f);
		buttonPanel.add(btnFullColour);

		JButton btnGreyScale = new JButton("Greyscale");
		btnGreyScale.addActionListener(r);
		btnGreyScale.setAlignmentX(0.5f);
		buttonPanel.add(btnGreyScale);

		JButton btnRorGorB = new JButton("R|G|B");
		btnRorGorB.addActionListener(r);
		btnRorGorB.setAlignmentX(0.5f);
		buttonPanel.add(btnRorGorB);

		JButton btnRandom = new JButton("Random");
		btnRandom.addActionListener(r);
		btnRandom.setAlignmentX(0.5f);
		buttonPanel.add(btnRandom);

		JButton btnSpots = new JButton("Spots");
		btnSpots.addActionListener(r);
		btnSpots.setAlignmentX(0.5f);
		buttonPanel.add(btnSpots);

		JButton btnSpots2 = new JButton("Spots 2");
		btnSpots2.addActionListener(r);
		btnSpots2.setAlignmentX(0.5f);
		buttonPanel.add(btnSpots2);

		JButton btnSpiral = new JButton("Spiral");
		btnSpiral.addActionListener(r);
		btnSpiral.setAlignmentX(0.5f);
		buttonPanel.add(btnSpiral);

		sizeSlider = new JSlider(1, 5, 1);
		sizeSlider.addChangeListener(r);
		sizeSlider.setMinimumSize(new Dimension(100, 23));
		sizeSlider.setMaximumSize(new Dimension(100, 23));
		sizeSlider.setPreferredSize(new Dimension(100, 23));
		buttonPanel.add(sizeSlider);

		JButton btnAv = new JButton("Average Image");
		btnAv.addActionListener(r);
		btnAv.setAlignmentX(0.5f);
		buttonPanel.add(btnAv);

		JButton btnHist = new JButton("History");
		btnHist.addActionListener(r);
		btnHist.setAlignmentX(0.5f);
		buttonPanel.add(btnHist);

		JButton btnSave = new JButton("Save Image");
		btnSave.addActionListener(r);
		btnSave.setAlignmentX(0.5f);
		buttonPanel.add(btnSave);

		JButton btnSaveAll = new JButton("Save All");
		btnSaveAll.addActionListener(r);
		btnSaveAll.setAlignmentX(0.5f);
		buttonPanel.add(btnSaveAll);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setMaximumSize(new Dimension(80, 30000));
		textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		textArea.setEditable(false);
		textArea.setColumns(7);
		textArea.setMinimumSize(new Dimension(80, 300));
		buttonPanel.add(textArea);

		JScrollPane textscrollPane = new JScrollPane(textArea);
		textscrollPane.setPreferredSize(new Dimension(82, 300));
		textscrollPane.setMinimumSize(new Dimension(80, 300));
		textscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textscrollPane.setAutoscrolls(true);
		buttonPanel.add(textscrollPane);

		imagePanel = new ImagePanel();
		imagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		imagePanel.setMaximumSize(new Dimension(2000, 2000));
		imagePanel.setMinimumSize(new Dimension(300, 300));
		imagePanel.setPreferredSize(new Dimension(2000, 2000));
		frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
		imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.pack();

		// Should be done last!
		r.setUp();
	}

	/**
	 * Saves the last generated image (The one currently displayed). Asks the
	 * user for a location to save it, and saves it as a .png (as lossless).
	 * FUTURE: Offer to save it as a jpeg for online sharing?
	 */
	public void saveImage() {
		if (history.isEmpty()) {
			System.out.println("No image to save");
			return;
		}
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Choose an Image");
		jfc.showDialog(null, "Done");
		jfc.setVisible(true);
		File file = jfc.getSelectedFile();
		if (file == null)
			return;
		String filePath = file.getAbsolutePath();
		if (!filePath.endsWith(".png")) {
			filePath = filePath + ".png";
		}
		File outputFile = new File(filePath);
		try {
			ImageIO.write(history.get(historyIndex), "png", outputFile);
		} catch (IOException e) {
			System.out.println("Failed to write to file :(");
		}
	}

	public void prevImage() {
		if (historyIndex > 0) {
			historyIndex--;
			imagePanel.updatePanel(history.get(historyIndex));
			imagePanel.update(imagePanel.getGraphics());
		} else {
			textArea.append("No images left!\n");
		}

	}

	public void nextImage() {
		if (historyIndex >= history.size() - 1) {
			textArea.append("No images left!\n");
		} else {
			historyIndex++;
			imagePanel.updatePanel(history.get(historyIndex));
			imagePanel.update(imagePanel.getGraphics());
		}
	}
	
	public void goToImage(int index){
		if(index < 0 || index >= history.size()) return;
		historyIndex = index;
		imagePanel.updatePanel(history.get(historyIndex));
		imagePanel.update(imagePanel.getGraphics());
	}
	
	public void historyGUI(){
		new HistoryGUI(history,this);
	}

	public synchronized void saveAllImages() {
		if (history.isEmpty()) {
			System.out.println("No images to save");
			return;
		}
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new java.io.File("."));
		jfc.setDialogTitle("Choose a Directory");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// disable the "All files" option.
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.showDialog(null, "Done");
		jfc.setVisible(true);
		String outDir = jfc.getCurrentDirectory().getAbsolutePath();

		for (int i = 0; i < history.size(); i++) {
			try {
				ImageIO.write(history.get(i), "png", new File(outDir + "\\"
						+ "imgGen" + i + ".png"));
			} catch (IOException e) {
				System.out.println("Failed to write to file :(");
			} finally {
				textArea.append(i + 1 + " of " + history.size() + "done\n");
			}
		}

	}

	public synchronized void averageImages() {
		if (history.size() < 1) {
			textArea.append("No images!\n");
			return;
		}
		int[] smallest = smallestIndices();
		int xSize = smallest[0];
		int ySize = smallest[1];
		int size = history.size();
		BufferedImage result = new BufferedImage(xSize, ySize,
				BufferedImage.TYPE_INT_RGB);
		/*
		 * Skips extra pixels (when an image smaller than others) rather than
		 * averaging those...hrm..
		 */
		List<Integer> printed = new ArrayList<Integer>();
		for (int i = 0; i < xSize; i++) {
			printProgress(i, xSize, printed);
			for (int j = 0; j < ySize; j++) {
				int red = 0;
				int blue = 0;
				int green = 0;
				for (BufferedImage b : history) {

					Color pixel = new Color(b.getRGB(i, j));
					red += pixel.getRed();
					blue += pixel.getBlue();
					green += pixel.getGreen();
				}
				Color newColour = new Color(red / size, blue / size, green
						/ size);
				result.setRGB(i, j, newColour.getRGB());
			}
		}
		textArea.append(100 + " % done\n");
		paintImage(result);
	}

	/**
	 * Can be used to print the current progress to the text pane. Will only
	 * print in 10% increments.
	 * 
	 * @param current
	 * @param total
	 * @param printed
	 *            List of percentages printed thus far.
	 */
	public static void printProgress(int current, int total,
			List<Integer> printed) {
		int percDone = (int) ((double) current / total * 100);
		// Print progress every 10%
		if (percDone % 10 == 0 && !printed.contains(percDone)) {
			textArea.append(percDone + " % done\n");
			printed.add(percDone);
		}
	}

	private int[] smallestIndices() {
		int[] smallest = new int[] { 10000, 10000 };

		for (BufferedImage b : history) {
			if (b.getWidth() < smallest[0])
				smallest[0] = b.getWidth();
			if (b.getHeight() < smallest[1])
				smallest[1] = b.getHeight();

		}
		return smallest;
	}
}
