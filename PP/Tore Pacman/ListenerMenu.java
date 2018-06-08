import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListenerMenu implements ActionListener {
    private Frame f;
    private String[] t;

    public ListenerMenu(Frame frame, String[] table) {
        this.f = frame;
        this.t = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str == this.t[0]) {
           this.f.runGame();
        }
        else {
            this.f.dispose();
            System.exit(0);
        }
    }
}