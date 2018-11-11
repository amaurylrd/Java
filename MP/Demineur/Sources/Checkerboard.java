import java.awt.*;
import javax.swing.*;
import java.lang.Math;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
* La classe <code>Checkerboard</code> g&eacute;n&egrave;re une repr&eacute;sentation d'une grile de d&eacute;mineur.
*
* @author abreudia_lerouxdu
* @version 0.6
*/
public class Checkerboard extends JPanel  {
	/**
    * Constante pour define du gris.
    */
    private final Color GRIS = new Color(216,216,195);
    /**
    * Constante pour define du dor&eacute;.
    */
    private final Color GOLD = new Color(255,190,0);
    /**
   	* Constante pour repr&eacute;senter une mine.
   	*/
	private final char BOMB = 'X';
    /**
    * Constante pour repr&eacute;senter la taille d'un c&ocirc;t&eacute;.
    */
    private final int BORDER = 20;
    /**
    * Constante pour repr&eacute;senter l'espacement.
    */
    private final int SPACE = BORDER+3;
    /**
    * Constante pour repr&eacute;senter la taille d'un c&ocirc;t&eacute;.
    */
    private final int POS_X = 35+150;
    /**
    * Constante d&eacute;signe l'origine X.
    */
    private final int POS_Y = 25+100;
	/**
   	* Constante d&eacute;signe l'origine Y.
   	*/
	private int bombInitialNumber;
    /**
    * Constante d&eacute;signe le nombre actuel de bombe.
    */
    private  int bombActualNumber;
	/**
   	* Variable globale qui d&eacute;signe le nombre de lignes.
   	*/
	private int m;
	/**
   	* Variable globale qui d&eacute;signe le nombre de colonnes.
   	*/
	private int p;
    /**
    * R&eacute;f&eacute;rence &agrave; la grille de <code>Case</code>.
    */
    private Case[][] board;
    /**
    * Variable <code>Score</code>.
    */
    private Score ta;

	/**
   	* Constructeur de l'objet <code>Checkerboard</code> ; cr&eacute;ation des variables priv&eacute;es.
   	*
   	* @param rowNumber le nombre de lignes (entre 4 et 30).
   	* @param columnNumber le nombre de colonnes (entre 4 et 30).
   	* @param bombNumber le nombre de mines (entre 23% et 48%).
   	*/
	public Checkerboard(int rowNumber, int columnNumber, int bombNumber, Score myScore) {
		super();
        if (columnNumber < 4 || columnNumber > 30)
            throw new RuntimeException("Mauvaise valeur de columnNumber");
        if (rowNumber < 4 || rowNumber > 30)
            throw new RuntimeException("Mauvaise valeur de rowNumber");
        this.bombInitialNumber = bombNumber;
        this.bombActualNumber = bombNumber;
        this.board = new Case[rowNumber][columnNumber];
        this.m = rowNumber;
        this.p = columnNumber;
        this.setLocation(POS_X, POS_Y);
        this.setSize(columnNumber*SPACE+4, rowNumber*SPACE+4);
        this.setBackground(GRIS);
        this.setLayout(null);
        this.ta = myScore;
        checkerboardFill(); 
    }

    /**
    * Retourne le <code>Score</code> priv&eacute;e.
	*
    * @return le pannel Score
    */
    public Score getScore(){
        return this.ta;
    }

    /**
    * Retourne le nombre de lignes.
    *
    * @return rowNumber
    */
    public int getRowNumber(){
        return this.m;
    }

    /**
    * Retourne le nombre de colonnes.
    *
    * @return colNumber
    */
    public int getColumnNumber(){
        return this.p;
    }

    /**
    * Retourne le nombre de bombes.
    *
    * @return BombInitalNumber
    */
    public int getBombInitialNumber(){
        return this.bombInitialNumber;
    }

    /**
    * Appel &agrave; setCount pour modifier le panneau des scores.
    */
    public void setScore() {
        this.ta.setCount(this.board);

    }

	/**
 	* Construit des objets <code>Case</code> &agrave; partir des valeurs du plateau.
 	*/
	private void checkerboardFill() {
        char[][] table = this.checkerboardGeneration();
        for (int i = 0 ; i < this.m ; i++) {
            for (int j = 0 ; j < this.p ; j++) {
                char val = table[i][j];
                Case square = new Case(val);
                square.setSize(BORDER, BORDER);
                square.setLocation(j*SPACE+3, i*SPACE+3);
                square.addMouseListener(new ListenerCase(this));
                this.add(square);
                this.board[i][j] = square;
            }
        }
    }

    /**
    * Affiche toutes les mines de la grille en cas de d&eacute;faite.
    *
    * @param l'indice cliqu&eacute;
    */
    public void showBombs(Case square) {
        for (int i = 0 ; i < this.m ; i++) {
            for (int j = 0 ; j < this.p ; j++) {
                Case val = this.board[i][j];
                char bomb = 'X';
                if (val.getValue() == bomb) {
                    if (square != val) {
                        if (val.isLock())
                            val.setIcon(square.BOMB_3);
                        else
                            val.setIcon(square.BOMB_2);
                        val.setBackground(GOLD);
                        val.setFont(square.f);
                    }
                }
            }
        }
        square.done = true;
    }

    /**
    * Parcourt la grille jusqu'&agrave; l'objet d'appel et initialise la r&eacute;cursivit&eacute;.
    *
    * @param square r&eacute;f&eacute;rence &agrave; l'objet <code>Case</code>
    */
    public void chainReaction(Case square) {
        for (int i = 0 ; i < this.m ; i++) {
            for (int j = 0 ; j < this.p ; j++) {
                Case val = this.board[i][j];
                if (val.equals(square))
                    this.setNeighboursNull(i, j);
            }
        }        
    }

    /**
    * Algorythme de propagation aux voisins non-r&eacute;l&eacute;v&eacute;s.
    *
    * @param i indice de lignes
    * @param j indice de colonnes
    */
    private void setNeighboursNull(int i, int j) {
        for (int xoff = -1 ; xoff <= 1 ; xoff++) {
            for (int yoff = -1 ; yoff <= 1 ; yoff++) {
                int ioff = i + xoff;
                int joff = j + yoff;
                if (ioff > -1 && ioff < this.m && joff > -1 && joff < this.p) {
                    Case newVal = this.board[ioff][joff];
                    if (!(newVal.getState())) {
                        this.setToVisible(newVal);
                        if (newVal.getValue() == '0')
                            this.setNeighboursNull(ioff, joff);
                    }
                }
            }
        }
    }

    /**
    * Change l'apparence des cases.
    *
    * @param square r&eacute;f&eacute;rence &agrave; l'objet <code>Case</code>;
    */
    private void setToVisible(Case square) {
        square.setFont(square.f);
        square.setBackground(square.LIGHT_GRAY);
        if (square.getValue() == '0')
            square.setText("  ");
        else {
            char c = square.getValue();
            square.setCaseColor(c);
            square.setText(" "+square.getValue());
        }
    }

    /**
 	* Place un nombre de mines et attribue une valeurs coh&eacute;rantes aux restes des cases.
 	*
 	* @return le damier de d&eacute;but de partie
 	*/
    private char[][] checkerboardGeneration() {
        int bombNumber = this.bombInitialNumber;
        char[][] checkerboard = new char[this.m][this.p];
        while (bombNumber != 0) {
            int ioff = this.randomInt(0, m-1);
            int yoff = this.randomInt(0, p-1);
            if (checkerboard[ioff][yoff] != BOMB) {
                checkerboard[ioff][yoff] = BOMB;
                bombNumber--;
            }
        }
        gridShow(checkerboard);
        for (int i = 0 ; i < this.m ; i++) {
        	for (int j = 0 ; j < this.p ; j++) {
        		if (checkerboard[i][j] != BOMB)
        			checkerboard[i][j] = this.setNeighboursValues(checkerboard, i, j);
        	}
        }
        gridShow(checkerboard);
        return checkerboard;
    }

    /**
    * G&eacute;n&egrave;re un entier al&eacute;atoire entre deux bornes.
    * 
    * @param min limite inf&eacute;rieure
    * @param max limite sup&eacute;rieur
    * @return entier quelconque
    */
    private static int randomInt(int min, int max) {
    	int range = (max - min) + 1;
    	return (int) (Math.random() * range) + min;
    }

    /**
    * Gestion et convertion pour remplir le tableau.
    *
    * @param tab composant repr&eacute;sentant la grille de d&eacute;part
    * @param i indice de lignes
    * @param j indice de colonnes
    * @return la nouvelle valeur &agrave; attribuer
    */
    private char setNeighboursValues(char[][] tab, int i, int j) {
    	int bombCount = this.getNeighboursCount(tab, i, j);
    	char c = (char) (bombCount + '0');
    	return c;
    }

    /**
    * Algorythme qui incr&eacute;mente un compteur en fonction des voisins de l'indice.
    *
    * @param tab composant repr&eacute;sentant la grille de d&eacute;part
    * @param i indice de lignes
    * @param j indice de colonnes
    * @return la nouvelle valeur &agrave; attribuer
    */
    private int getNeighboursCount(char[][] tab, int i, int j) {
    	int cmp = 0;
		for (int xoff = -1 ; xoff <= 1 ; xoff++) {
			for (int yoff = -1 ; yoff <= 1 ; yoff++) {
				int ioff = i + xoff;
				int joff = j + yoff;
				if (ioff > -1 && ioff < this.m && joff > -1 && joff < this.p)
					if (tab[ioff][joff] == BOMB)
						cmp++;
			}
		}
		return cmp;
	}

	/**
    * G&egrave;re l'affichage sur la sortie standard d'un tableau.
    *
    * @param tab le tableau
    */
  	private static void gridShow(char[][] tab) {
        for (int i = 0 ; i < tab.length ; i++) {
            for (int j = 0 ; j < tab[i].length ; j++) {
            	System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
    * Renvoie le nombre actuel de mines.
    * 
    * @return bombActualNumber
    */
    public int getBombActualNumber() {
        return this.bombActualNumber;
    }

    /**
    * M&eacute;thode qui incr&eacute;ment ou d&eacute;cr&eacute;mente bombActualNumber.
    *
    * @param option qui indique ou l'incr&eacute;mentation ou la d&eacute;cr&eacute;mentation
    */
    public void calulateBombActualNumber(boolean option) {
        if (option)
            this.bombActualNumber++;
        else
            this.bombActualNumber--;
    }

    /**
    * V&eacute;rifie que le nombre de cases marqu&eacute;es correspond au nombre intial de mines.
    * 
    * @return vrai ou faux
    */
    public boolean getLockNumber() {
        int cmp = 0;
        for (int i = 0 ; i < this.m ; i++) {
            for (int j = 0 ; j < this.p ; j++) {
                Case val = this.board[i][j];
                if (val.isLock())
                    cmp++;
            }
        }
        return cmp == this.bombInitialNumber ? true : false;
    }

    /**
    * V&eacute;rifie le nombre de cases chiffr&eacute;es.
    * 
    * @return vrai ou faux
    */
    public boolean getSafeCase() {
        int cmp = 0;
        for (int i = 0 ; i < this.m ; i++) {
            for (int j = 0 ; j < this.p ; j++) {
                Case val = this.board[i][j];
                if (val.getState()) {
                    char c = val.getValue();
                    char bomb = 'X';
                    if (c != bomb)
                        cmp++;
                }
            }
        }
        int total = this.m * this.p - this.bombInitialNumber;
        return cmp == total ? true : false;
    }
    
    /**
    * toString
    */    
    @Override
    public String toString() {
        String state = "En cours";
        if (Case.win)
        	state = "Victoire";
        if (Case.loose)
        	state = "Défaite";
        return "Etat de la partie : "+state;
    }
}