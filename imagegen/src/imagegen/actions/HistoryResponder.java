package imagegen.actions;

import imagegen.gui.HistoryGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HistoryResponder implements MouseListener {
	HistoryGUI g;
	public HistoryResponder(HistoryGUI g){
		this.g = g;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Width: " + e.getX() + "Height: " + e.getY());
		int imageX = e.getX()/102;
		int imageY = e.getY()/102;
		System.out.println("Image Width: " + imageX + "Image Height: " + imageY);
		g.showImageAt(imageX, imageY);
		
		
	}

}
