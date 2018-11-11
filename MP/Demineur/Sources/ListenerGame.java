import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;


/**
* La classe <code>ListenerGame</code> &eacute;coute les <code>JButton</code>.
*
* @author abreudia_lerouxdu
* @version 0.2
*/
public class ListenerGame implements ActionListener {
	/**
    * Variable static tableau de <code>String</code>.
    */
    private static String[] table;
    /**
    * Tableau de String.
    */
    private final Score score;
    /**
    * R&eacute;f&eacute;rence au <code>Score</code>.
    */
    private final Checkerboard grid;
   	/**
    * R&eacute;f&eacute;rence au <code>Checkerboard</code>..
    */
   	private final MinesweeperView f;
   	/**
   	* Tableau qui definit un <code>Checkerboard</code>.
   	*/
    private  int[] gridef;

    /**
	* Constructeur de l'objet <code>ListenerGame</code>.
	*
	* @param frame la vue
	* @param myGrid la grille
	* @param myScore le score
	* @param myTable un tableau de String
	* @param gridef structure d'un <code>Checkerboard</code>
	*/
	public ListenerGame(MinesweeperView frame, Checkerboard myGrid, Score myScore, String[] myTable, int[] gridef) {
	   	this.f = frame;
	   	this.grid = myGrid;
	   	this.table = myTable;
	   	this.score = myScore;
        this.gridef = gridef;
    }

    /**
    * Ecoute les <code>JButton</code>.
    *
    * @param e ActionEvent
    */
	@Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        JButton b = (JButton) e.getSource();
    	if (str == this.table[1]) {           
    		Serialization.sove(this.grid);
            this.f.dispose();
    		System.exit(0);
    	}
        else if (str == this.table[0]) {
            Serialization.sove(this.grid);
        	JFrame frame = new Menu(this.f.getName(), (int) this.f.getDim().getWidth(), (int) this.f.getDim().getHeight());
        	this.f.dispose();
            frame.setVisible(true);
        }
        else if (str == this.table[2]) {
            MinesweeperView mw = new MinesweeperView(this.f.getName(), this.f.getDim(), this.gridef[0], this.gridef[1], this.gridef[2]);
            this.f.dispose();
            mw.setVisible(true);
        }
  	} 
}