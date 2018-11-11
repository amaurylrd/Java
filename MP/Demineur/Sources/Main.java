import javax.swing.*;

/**
* @author abreudia_lerouxdu
* @version 0.1
*/
public class Main {
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	JFrame frame = new Menu("Minesweeper Menu", 1280, 1024);
            	frame.setVisible(true);
            }
        });
    }
}