import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.*;
import java.awt.Dimension;
import java.io.File;

/**
* Classe <code>Serialization</code> compos&eacute;e de m&eacute;thodes d&eacute;vou&eacute;es à l'&eacute;criture et la lecture d'un objet dans un fichier 
*/
public class Serialization {
	/**
	* Sauvegarde la grille dans un fichier
	*
	* @param g la grille
	*/
	public static void sove(Checkerboard g) {
		if (!Case.done && (Case.win || Case.loose)) {
			try {
			    FileOutputStream fileOut = new FileOutputStream("../Save/sauvegarde.dat");
			    ObjectOutputStream out = new ObjectOutputStream(fileOut);
			    out.writeObject(g);
			    out.close();
			    fileOut.close();
		    } catch (IOException i) {
		        i.printStackTrace();
		    }
		}
	}

	/**
	* Lecture de la grille dans un fichier 
	*
	* @param name le nom de la fen&ecirc;tre
	* @param dim la dimension de la fen&ecirc;tre
	* @param f la vue <code>Menu</code>
	*/
	public static void luc(String name, Dimension dim, Menu f) {
		try {
            FileInputStream fileIn = new FileInputStream("../Save/sauvegarde.dat");
            ObjectInputStream In = new ObjectInputStream(fileIn);
            try {
                Checkerboard x = (Checkerboard) In.readObject();
                JFrame frame = new MinesweeperView(name, dim, x);
                f.dispose();
                frame.setVisible(true);
                In.close();
                fileIn.close();
            } catch(ClassNotFoundException i) {
                System.err.println("Checkerboard n'existe pas");
            }
        } catch (IOException i) {  
            i.printStackTrace();
        }
        new File("../Save/sauvegarde.dat").delete();
	}
}