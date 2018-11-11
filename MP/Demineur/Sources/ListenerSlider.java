import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
* La classe <code>ListenerSlider</code> &eacute;coute les <code>JSlider</code>.
*
* @author abreudia_lerouxdu
* @verssion 0.1
*/
public class ListenerSlider implements ChangeListener {
    /**
    * R&eacute;f&eacute;rence au <code>Menu</code>.
    */
    private Menu f;
    /**
    * L'indice du JSlider.
    */
    private int i;
    /**
    * Variable static <code>JSlider</code>.
    */
    private static JSlider s;
    /**
    * Variable <code>JLabel</code>.
    */
    private JLabel l;
    /**
    * Tableau de <code>String</code>.
    */
    private String[] t;

    /**
    * Constructeur de l'objet <code>ListenerSlider</code>.
    *
    * @param table tableau de String
    * @param label element &agrave; sensibilis&eacute;
    * @param frame R&eacute;f&eacute;rence au Menu
    * @param index l'indice de JSlider
    */
    public ListenerSlider(String[] table, JLabel label, Menu frame, int index) {
        this.f = frame;
        this.i = index;
        this.l = label;
        this.t = table;
    }

    /**
    * Constructeur surcharg&eacute; de l'objet <code>ListenerSlider</code>.
    *
    * @param table tableau de String
    * @param label element &agrave; sensibilis&eacute;
    * @param frame R&eacute;f&eacute;rence au Menu
    * @param index l'indice de JSlider
    * @param slider JSlider d&eacute;pendant
    */
    public ListenerSlider(String[] table, JLabel label, Menu frame, int index, JSlider slider) {
        this.f = frame;
        this.i = index;
        this.s = slider;
        this.l = label;
        this.t = table;
    }

    /**
    * &Eacute;coute les <code>JSlider</code>.
    *
    * @param e ChangeEvent
    */
    public void stateChanged(ChangeEvent e) {
        JSlider j = (JSlider) e.getSource();
        this.f.setVal(this.i, j.getValue());
        int[] tmp = this.f.setMax();
        if (this.i != 2) {
            this.s.setMinimum(tmp[0]);
            this.s.setMaximum(tmp[1]);
            this.s.setValue(tmp[2]);
        }
        this.l.setText(t[this.i]+j.getValue());
    }
}