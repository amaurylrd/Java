import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.io.*;

/**
* La classe <code>ListenerMenu</code> &eacute;coute les <code>JButton</code> du Menu.
*
* @author abreudia_lerouxdu
* @version 0.2
*/
public class ListenerMenu implements ActionListener {
    /**
    * R&eacute;f&eacute;rence au parent.
    */
    private Menu f;
    /**
    * Variable tableau de chaînes de caract&egrave;res.
    */
    private String[] t;
    /**
    * Tableau static pour contenir les valeurs choisies.
    */
    private static int[] val = new int[3];

    public ListenerMenu(Menu frame, String[] table) {
        this.f = frame;
        this.t = table;
    }

    /**
    * Ecoute les <code>JButton</code>.
    *
    * @param e ActionEvent
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        Dimension dim = this.f.getDim();
        if (str == this.t[0]) {
            JFrame frame = new MinesweeperView(this.f.getName(), this.f.getDim(), this.f.getVal(0), this.f.getVal(1), this.f.getVal(2));
            this.f.dispose();
            frame.setVisible(true);
        }
        else if (str == this.t[1]) {
            File file = new File("../Save/sauvegarde.dat"); 
            if (file.exists() && !file.isDirectory())
                Serialization.luc(this.f.getName(), this.f.getDim(), this.f);
        }
        else if (str == this.t[2]) {
            this.f.dispose();
            System.exit(0);
        }
    }
}