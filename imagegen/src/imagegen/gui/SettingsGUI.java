package imagegen.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsGUI implements ChangeListener {
	private final GUI gui;
	private JSlider sizeSlider;
	private JFrame frame;

	public SettingsGUI(GUI g) {
		frame = new JFrame();
		JPanel panel = new JPanel();
		frame.add(panel);
		this.gui = g;
		sizeSlider = new JSlider(1, 10, 1);
		sizeSlider.addChangeListener(this);
		JLabel sizeLabel = new JLabel("Picture Size");
		panel.add(sizeLabel);
		panel.add(sizeSlider);
		frame.pack();
	}

	public void showSettings() {
		frame.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		 JSlider source = (JSlider) e.getSource();
		 if(source == sizeSlider){
			 gui.updateSize(source.getValue());
		 }
		
	}
}
