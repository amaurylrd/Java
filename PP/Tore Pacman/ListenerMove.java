import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ListenerMove implements KeyListener {
    private Game g;
    private boolean isPressed;

    public ListenerMove(Game game) {
        this.g = game;
        this.isPressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (this.g.getState() && !this.isPressed) {
            this.isPressed = true;
            if (k == KeyEvent.VK_UP)
               this.g.move('U');
            else if (k == KeyEvent.VK_DOWN)
                this.g.move('D'); 
            else if (k == KeyEvent.VK_LEFT)
                this.g.move('L'); 
            else if (k ==KeyEvent.VK_RIGHT)
                this.g.move('R');
            e.consume();
        }
    }

    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (this.g.getState()) {
            if (k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN || k == KeyEvent.VK_LEFT || k ==KeyEvent.VK_RIGHT)
                this.g.render();
            this.isPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {}
}