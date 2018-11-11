import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
* La classe <code>Score</code>.
*
* @author abreudia_lerouxdu
* @version 0.2
*/
public class Score extends JPanel {
    /**
    * Stock les JLabels.
    */
    protected JLabel[] liste = new JLabel[3];
    /**
    * Constante qui d&eacute;signe la valeur &agrave; afficher.
    */
    private final String[] table = {" Score : ", " Nombre (relatif) de bombes : ", " Nombre de cases d"+'\u00E9'+"couvertes : "};
    /**
    * Variable qui d&eacute;signe le score.
    */
    private int score;
    /**
    * Le nombre de casses.
    */
    private int nombre_cases;
    /**
    * Le nombre de bombes.
    */
    private int nombre_bombes;
    /**
    * Le nombre de coups.
    */
    private int nombre_coup;

    /**
    * Constructeur de l'objet <code>Score</code> ; cr&eacute;ation des variables priv&eacute;es.
    *
    * @param nbCases le nombre de cases
    * @prama nbBombes le nombre de bombes
    */
    public Score(int nbCases, int nbBombes) {
        super();
        this.score = 0;
        this.setSize(320, 334);
        this.setLocation(895, 125);
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(new Color(155, 155, 194));
        this.nombre_cases = nbCases-nbBombes;
        this.nombre_bombes = nbBombes;
        this.nombre_coup = 0;
        this.addJLabel();
    }

    /**
    * Ajoute les JLabels au panneau.
    */
    private void addJLabel() {
        int tmp = 0;
        for (int i = 0 ; i < 3 ; i++) {
            if ( i == 2)
                this.liste[i] = new JLabel(this.table[i]+tmp+" sur "+nombre_cases, SwingConstants.CENTER);
            else if (i == 1)
                this.liste[i] = new JLabel(this.table[i]+nombre_bombes, SwingConstants.CENTER);
            else
                this.liste[i] = new JLabel(this.table[i]+tmp, SwingConstants.CENTER);
            this.liste[i].setVerticalAlignment(JLabel.TOP);
            this.liste[i].setHorizontalAlignment(JLabel.LEFT);
            this.add(this.liste[i], BorderLayout.CENTER);
        }
    }

    /**
    * Actualise les valeurs des JLabels.
    *
    * @param i indice du Jlabel.
    * @param k le nombre actualiser de case,bombe ou scrore.
    */
    public void setCount(Case[][] board) {
        this.nombre_coup++;
        int nbStars = 0;
        int nbVisibleCases = 0;
        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
            	if (board[i][j].isLock())
                	nbStars++; 
                if (board[i][j].getState())
                    nbVisibleCases++;            
            }
        }
        nbStars = this.nombre_bombes - nbStars;
        this.liste[1].setText(this.table[1]+nbStars);
        this.liste[2].setText(this.table[2]+nbVisibleCases+" sur "+this.nombre_cases);
        this.liste[0].setText(this.table[0]+this.nombre_coup);        
    }
}