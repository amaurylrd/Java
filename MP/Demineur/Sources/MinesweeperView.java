import java.awt.*;
import javax.swing.JFrame;

/**
* La classe <code>MinesweeperView</code> param&egrave;tre l'affichage.
*
* @author abreudia_lerouxdu
* @version 0.1
*/
public class MinesweeperView extends JFrame {
    /**
    * Le nom de la fen&ecirc;tre.
    */
    private String n;
    /**
    * La dimension de la fen&ecirc;tre.
    */
    private Dimension dim;

    /**
    * Constructeur de l'objet <code>MinesweeperView</code>.
    *
    * @param name nom de la fen&ecirc;tre
    * @param fenSizeX largeur de la fen&ecirc;tre
    * @param fenSizeY hauteur de la fen&ecirc;tre
    */
    public MinesweeperView(String name, Dimension dim, int rowNumber, int columnNumber, int bombNumber) {
        super(name);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.pack();
        this.dim = dim;
        this.n = name;
        Game g = new Game(rowNumber, columnNumber, bombNumber, this);
    }

    /**
    * Constructeur surcharg&eacute; de l'objet <code>MinesweeperView</code>.
    *
    * @param name nom de la fen&ecirc;tre
    * @param fenSizeX largeur de la fen&ecirc;tre
    * @param fenSizeY hauteur de la fen&ecirc;tre
    */
    public MinesweeperView(String name, Dimension dim, Checkerboard x) {
        super(name);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.pack();
        this.dim = dim;
        this.n = name;
        Game g = new Game(x, this);
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
}