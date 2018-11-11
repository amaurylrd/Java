import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
* La classe <code>Case</code> repr&eacute;sente une casse min&eacute;e ou non.
*
* @author abreudia_lerouxdu
* @version 0.4
*/
public class Case extends JLabel implements Serializable {
    /**
    * Constante pour define du vert.
    */
    protected final Color GREEN = new Color(107, 142, 35);
    /**
    * Constante pour define du vert.
    */
    protected final Color BLUE = new Color(0, 0, 205);
    /**
    * Constante pour define du fushia.
    */
    protected final Color PINK = new Color(121, 28, 248);
    /**
    * Constante pour define une nuance de gris.
    */
    protected final Color LIGHT_GRAY = new Color(200, 200, 200);
    /**
    * Constante pour define une nuance de gris.
    */
    protected final Color DARK_GRAY = new Color(184, 184, 184);
    /**
    * Constante pour define du noir.
    */
    protected final Color BLACK = Color.BLACK;
    /**
    * Constante pour define du rouge.
    */
    protected final Color RED = new Color(255, 105, 72);
    /**
    * Constante pour define la police et sa taille
    */
    protected final Font f = new Font("SANS_SERIF", Font.BOLD, 13);
    /**
    * Rendu d'une bombe.
    */
    protected final ImageIcon BOMB_2 = new ImageIcon("../Asset/2.png");
    /**
    * Rendu d'une bombe d&eacute;min&eacute;v&egrave;e.
    */
    protected final ImageIcon BOMB_3 = new ImageIcon("../Asset/3.png"); 
    /**
    * La valeur cach&eacute;e d'une case.
    */
    private char hiddenValue;
    /**
    * Gestion de l'&eacute;v&egrave;nement "la partie est perdue".
    */
   	public static boolean loose;
    /**
    * Gestion de l'&eacute;v&egrave;nement "la partie est gagn&eacute;e"..
    */
   	public static boolean win;
    /**
    * Gestion de fin de partie.
    */
    public static boolean done;

   
    /**
    * Constructeur de l'objet <code>Case</code> ; cr&eacute;ation des variables globales.
    *
    * @param c la valeur cach&eacute;e (entre '0' et '8' ou 'X')
    */
    public Case(char c) {
        super(""+' ');
        this.win = false;
        this.loose = false;
        this.done = false;
        if (c < '0' || c > '8')
            if (c != 'X')
                throw new RuntimeException("Mauvaise valeur de c");
        this.hiddenValue = c;
        this.setOpaque(true);
        this.setBackground(DARK_GRAY);
    }

    /**
    * Lecture de la valeur cach&eacute;e d'une case.
    *
    * @return la valeur la case
    */
    public char getValue() {
        return this.hiddenValue;
    }

    /**
    * Renvoie l'&eacute;tat d'une case.
    *
    * @return le succ&egrave;s "la casse est d&eacute;couverte"
    */
    public boolean getState() {
        return this.getBackground() == LIGHT_GRAY ? true : false;
 
    }

    /**
    * Gestion des &eacute;v&egrave;nements de fin de partie.
    *
    * @return Si la case est &eacute;toil&eacute; ou non.
    */
    public boolean isLock() {
        String star = " "+'\u2605';
        return this.getText() == star ? true : false;
        
    }

    /**
    * Gestion des &eacute;v&egrave;nements de fin de partie.
    *
    * @return Si la case est ? ou non.
    */
    public boolean isPoint() {
        String point = " "+'?';
        return this.getText() == point ? true : false;
        
    }

    /**
    * Gestion des cases en fonction du clic.
    *
    * @param btn la d&eacute;finition d'un clic
    * @throw FileNotFoundException si BOMB_2 n'&eacute;xiste pas
    */
    public void caseRender(int btn) throws FileNotFoundException {
     	this.setFont(this.f);
    	char c = this.hiddenValue;
    	if (btn == 1) {
    		if (c != 'X') {
    			this.setBackground(LIGHT_GRAY);
    			this.setCaseColor(c);
    			this.setText(" "+c);
    		}
    		else {
    			this.setForeground(BLACK);
    			this.loose = setLoose();
    			this.setBackground(RED);
                this.setIcon(BOMB_2);
    		}
    		if (c == '0')
    			this.setText("  ");
    	}
    	else if (btn == 3) {
    		if (this.getText().length() == 1) {
    			this.setForeground(PINK);
    			this.setText(" "+'\u2605');
    		}
    		else {
    			char value = this.getText().charAt(1);
    			if (value == '\u2605') {
    				this.setForeground(PINK);
    				this.setText(" "+'?');
    			}
    			else if (value == '?')
    			 	this.setText(""+' '); 
    		}
    	}
    }

    /**
    * Gestion de la couleur affect&eacute;e u chiffre d'une case.
    */
    protected void setCaseColor(char c) {
    	if (c == '1' || c == '4' || c == '7')
    		this.setForeground(BLUE);
    	else if ((int) c % 3 == 0)
    		this.setForeground(Color.RED);
    	else
    		this.setForeground(GREEN);
    }

    /**
    * Gestion des &eacute;v&egrave;nements de fin de partie.
    *
    * @return le succ&egrave;s "la partie est perdue"
    */
    public static boolean setLoose() {
    	return true;
    }

    /**
    * Gestion des &eacute;v&egrave;nements de fin de partie.
    *
    * @return le succ&egrave;s "la partie est gagn&eacute;e"
    */
    public static boolean setWin() {
    	return true;
    }

    @Override
    public String toString() {
        String res = ""+this.hiddenValue;
        if (this.isLock())
            res = ""+'!';
        if (this.isPoint())
            res = ""+'?';
        return res;
    }
}