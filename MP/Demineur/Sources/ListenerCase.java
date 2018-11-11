import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
* La classe <code>ListenerCase</code> param&egrave;tre l'affichage.
*
* @author abreudia_lerouxdu
* @version 0.4
*/
class ListenerCase implements MouseListener, Serializable {
	/**
   	* Constante  repr&eacute;sant la grille.
   	*/
	private Checkerboard grille;

	/**
	* Observe les cases
	*
	* @param grille la grille
	*/
	public ListenerCase(Checkerboard grille) {
		super();
		this.grille = grille;
	}

	/**
 	* Ecoute les <code>Case</code> ; apelle caseRender et lui envoie la 
 	* valeur de la case si celle-ci n'est pas d&eacute;couverte ou poss&eacute;dant un ?.
 	* G&eacute;re &eacute;galement si la case contient une bombe et compte le nombre de
 	* bombe restante.
 	*
    * @param e ActionEvent
 	*/
	@Override
	public void mouseClicked(MouseEvent evenement) {
		Case square = (Case) evenement.getSource(); 
		if (!(Case.loose) && !(Case.win)) {
			char value = square.getText().charAt(0);
			if (square.getText().length() == 2)
				value = square.getText().charAt(1);
			int n =	evenement.getButton();
			if (n == 1) {
				if (value == ' ' || value == '?') {
					try {
						square.caseRender(n);
					} catch (IOException event) {
                    	event.getMessage();
                	}
					if (square.getValue() == '0')
						this.grille.chainReaction(square);
					if ((this.grille.getBombActualNumber() == 0 && this.grille.getLockNumber()) || 
						this.grille.getSafeCase()) {
			 			Case.win = square.setWin();
			 			this.initInfo("Victoire");
			 		}
				}
			}
			else if (n == 3) {
				try {
					square.caseRender(n);
				} catch (IOException event) {
                    event.getMessage();
                }
                char bomb = 'X';
                String str = " "+'?';
                if (square.getText() != str) {
					if (square.getValue() == bomb) {
			 			if (square.isLock())
                			this.grille.calulateBombActualNumber(false);
                		else
                			this.grille.calulateBombActualNumber(true);
                		if (this.grille.getBombActualNumber() == 0 && this.grille.getLockNumber()) {
			 				Case.win = square.setWin();
			 				this.initInfo("Victoire");
			 			}
                	}
            	}
            }
            this.grille.setScore();
        }
		if (Case.loose && !(Case.done)) {
			this.grille.showBombs(square);
			this.initInfo("Défaite");
		}
	}

	/**
	* Affiche les information sur l'&eacute;tat de la partie 
	*
	* @param str d&eacute;signe le nom du pop-up. 
	*/
	private void initInfo(String str) {
		JOptionPane jop1 = new JOptionPane();
		jop1.showMessageDialog(null, this.grille.toString(), str, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(this.grille.toString());

	}
	public void mouseEntered(MouseEvent evenement) {}
	public void mouseExited(MouseEvent evenement) {}
	public void mousePressed(MouseEvent evenement) {}
	public void mouseReleased(MouseEvent evenement) {}
}