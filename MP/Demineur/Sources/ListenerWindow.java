import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Commentaire
*/
public class ListenerWindow implements WindowListener {
	/**
	* Commentaire
	*/
	private Checkerboard g;
	
	/**
	* Commentaire
	*/	
	public  ListenerWindow(Checkerboard pangrid){
		this.g = pangrid;
	}

	/**
	* Commentaire
	*/
	@Override
	public void windowClosing(WindowEvent e){
		MinesweeperView f = (MinesweeperView) e.getSource();
		Serialization.sove(this.g);
	}

	public void windowClosed(WindowEvent evenement){}
	public void windowDeiconified(WindowEvent evenement){}
	public void windowIconified(WindowEvent evenement){}
	public void windowOpened(WindowEvent evenement){}
	public void windowDeactivated(WindowEvent evenement){}
	public void windowActivated(WindowEvent evenement){}
}