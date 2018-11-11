import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
* La classe <code>Game</code>.
*
* @author abreudia_lerouxdu
* @version 0.1
*/
public class Game {
	/**
	* Constructeur de l'objet <code>Game</code>
    *
    * @param rowNumber le nombre de lignes
    * @param colNumber le nombre de colonnes
    * @param bombNumber le nombre de bombes
    * @param frame la vue
	*/
    public Game(int rowNumber, int columnNumber, int bombNumber, MinesweeperView frame) {
        Score panScore = new Score(rowNumber*columnNumber, bombNumber);
        Checkerboard panGrid = new Checkerboard(rowNumber, columnNumber, bombNumber, panScore);  
        int[] gridef = {rowNumber, columnNumber, panGrid.getBombInitialNumber()};
        frame.add(panGrid);
        frame.add(panScore);
        this.initComponents(frame, gridef, panGrid, panScore);
        this.initBackgroundImage(frame);
        frame.addWindowListener(new ListenerWindow(panGrid));
    }

    /**
	* Constructeur surcharg&eacute;e de l'objet <code>Game</code>
    *
    * @param x la grille
    * @param frame la vue
	*/
    public Game(Checkerboard x, MinesweeperView frame) {
        int rowNumber = x.getRowNumber();
        int columnNumber = x.getColumnNumber();
        int bombNumber = x.getBombInitialNumber();
        Checkerboard panGrid = x;
        Score panScore = panGrid.getScore();
        int[] gridef = {rowNumber, columnNumber, bombNumber};
        frame.add(panGrid);
        frame.add(panScore);
        this.initComponents(frame, gridef, panGrid, panScore);
        this.initBackgroundImage(frame);
        frame.addWindowListener(new ListenerWindow(panGrid));

    }

    /**
    * Configure et initialise les composants de la fen&ecirc;tre.
    *
    * @param frame la vue
    * @param gridef le tableau
    * @param panGrid la grille
    * @param panScore le score
    */
    private void initComponents(MinesweeperView frame, int[] gridef, Checkerboard panGrid, Score panScore) {
        frame.add(new JButton());
        String[] table = {"Retour et sauvegarder", "Quitter et sauvegarder", "Rejouer"};
        for (int i = 0 ; i < 3 ; i++) {
            JButton btn = new JButton(table[i]);
            btn.setSize(200, 50);
            btn.setLocation(953, 470+60*i);
            btn.addActionListener(new ListenerGame(frame, panGrid, panScore, table, gridef));
            frame.add(btn);
        }
    }

    /**
    * Place un fond de fen&ecirc;tre.
    *
    * @return boolean vrai si l'image est appliq&eacute;e
    */
    private boolean initBackgroundImage(MinesweeperView frame) {
        try {
            int width = (int) frame.getDim().getWidth();
            int height = (int) frame.getDim().getHeight();
            Background image = new Background(new File("../Asset/explosion.jpg"), 0, 0, width, height);
            frame.add(image);
        } catch (IOException event) {
            System.err.println("Image Not Found");
            return false;
        }
        return true;
    }
}