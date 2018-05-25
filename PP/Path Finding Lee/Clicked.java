import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Clicked implements MouseListener {
	private Grid g;
	
	public Clicked(Grid grid) {
		super();
		this.g = grid;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Cell elem = (Cell) e.getSource();
		if (!elem.getState()) {
			int n =	e.getButton();
			if (n == 1)
				elem.setRender();
			else if (n == 3)
				this.g.initNodeChain();
		}
		else
			this.g.refreshComponents();
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}