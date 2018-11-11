import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JSlider;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
* La classe <code>Menu</code> param&egrave;tre l'affichage du menu.
*
* @author abreudia_lerouxdu
* @verssion 0.2
*/
public class Menu extends JFrame {
    /**
    * La taille de la fen&ecirc;tre.
    */
    private Dimension dim;
    /**
    * Le nom de la fen&ecirc;tre.
    */
    private String n;
    /**
    * Les valeurs des Sliders
    */
    private int[] tab = new int[4];

    /**
    * Constructeur de l'objet <code>MenuView</code>.
    *
    * @param name nom de la fen&ecirc;tre
    * @param fenWidth largeur de la fen&ecirc;tre
    * @param fenHeight hauteur de la fen&ecirc;tre
    */
    public Menu(String name, int fenWidth, int fenHeight) {
        super(name);
        this.n = name;
        this.dim = new Dimension(fenWidth, fenHeight);
        this.initParam(this.dim);
        this.initComponents();
    }

    /**
    * Getter pour atteindre la dimension.
    *
    * @return Dimension la dimension
    */
    public Dimension getDim() {
        return this.dim;
    }

    /**
    * Getter pour atteindre le nom.
    *
    * @return String le nom
    */
    public String getName() {
        return this.n;
    }

    /**
    * Configure et initialise les param&egrave;tres de la fen&ecirc;tre.
    */
    private void initParam(Dimension dim) {
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.pack();
    }

    /**
    * Configure et initialise les composants de la fen&ecirc;tre.
    */
    private void initComponents() {
        this.add(new JButton());
        String[] table = {"JOUER", "REPRENDRE", "QUITTER", "lignes", "colonnes", "bombes"};
        for (int i = 0 ; i < 3 ; i++) {
            JButton btn = new JButton(table[i]);
            btn.setSize(250, 60);
            btn.setBorderPainted(false);
            btn.setLocation(625, 400+90*i);
            btn.addActionListener(new ListenerMenu(this, table));
            this.add(btn, BorderLayout.CENTER);
        }
        this.initOptions();
        this.initBackgroundImage();
    }

    /**
    * Configure et initialise les composants s&eacute;lectionnables de la fen&ecirc;tre.
    */
    private void initOptions() {
        int[] tmp = this.setMax();
        JSlider[] sliderTab = {new JSlider(JSlider.HORIZONTAL, 4, 30, 17), new JSlider(JSlider.HORIZONTAL, 4, 30, 17), new JSlider(JSlider.HORIZONTAL, tmp[0], tmp[1], tmp[2])};
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        Hashtable<Integer, JLabel> labelTable1 = new Hashtable<Integer, JLabel>();
        String[] table = {"Lignes : ", "Colonnes : ", "Bombes : "};
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(6, 1));
        pan.setLocation(920, 400);
        pan.setSize(300, 240);
        for (int i = 0 ; i < 3 ; i++) {
            JLabel label;
            if (i != 2) {
                labelTable.put(4, new JLabel("4"));
                labelTable.put(30, new JLabel("30"));
                labelTable.put(17, new JLabel("17"));
                sliderTab[i].setLabelTable(labelTable);
                label = new JLabel(table[i]+17);
                sliderTab[i].addChangeListener(new ListenerSlider(table, label, this, i));
            }
            else {
                label = new JLabel(table[i]+tmp[i]);
                sliderTab[i].addChangeListener(new ListenerSlider(table, label, this, i, sliderTab[i]));
            }
            pan.add(label);
            this.initVal();
            sliderTab[i].setPaintLabels(true);
            pan.add(sliderTab[i], BorderLayout.CENTER);
        }
        pan.setBorder(BorderFactory.createTitledBorder("Options"));
        this.add(pan, BorderLayout.CENTER);
    }

    /**
    * Place un fond de fen&ecirc;tre.
    *
    * @return boolean vrai si l'image est appliqu&eacute;e
    */
    private boolean initBackgroundImage() {
        try {
            int width = (int) this.dim.getWidth();
            int height = (int) this.dim.getHeight();
            Background image = new Background(new File("../Asset/explosion.jpg"), 0, 0, width, height);
            this.add(image);
        } catch (IOException event) {
            System.err.println("Image Not Found");
            return false;
        }
        return true;
    }

    /**
    * Initialise les valeurs.
    */
     private void initVal() {
        if (this.getVal(0) == 0)
            this.setVal(0, 17);
        if (this.getVal(1) == 0)
            this.setVal(1, 17);
        this.setVal(2, 102);
    }


    /**
    * getter permettant d'atteindre tab[]
    *
    * @return int la valeur &agrave; l'indice 
    */
    public int getVal(int i) {
        return this.tab[i];
    }

    /**
    * Actualise tab[].
    */
    public void setVal(int i, int val) {
        this.tab[i] =  val;
    }

    /**
    * D&eacute;finit le nombre de bombes en fonction de la largeur et longueur choisies.
    *
    * @return int[] tableau des bornes et de la moyenne
    */
    public int[] setMax() {
        int rowLim = this.getVal(0);
        if (rowLim == 0)
            rowLim = 17;
        int colLim = this.getVal(1);
        if (colLim == 0)
            colLim = 17;
        int max = rowLim * colLim;
        int minLim = (int) (0.23 * (double) max); 
        int maxLim = (int) (0.48 * (double) max);
        int[] res = {minLim, maxLim, (minLim + maxLim)/2};
        return res;
    }
}