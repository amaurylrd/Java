import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Frame("Pacman", 680, 824);
                frame.setVisible(true);
            }
        });
    }
}