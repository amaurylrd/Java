import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
    private Dimension dim;

    public Frame(String name, int fenWidth, int fenHeight) {
        super(name);
        Dimension dim = new Dimension(fenWidth, fenHeight);
        this.dim = dim;
        this.initParam(dim);
        this.initComponents(dim);
        this.pack();
    }

    private void initParam(Dimension dim) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void initComponents(Dimension dim) {
        Menu m = new Menu(this);
        m.setSize(dim);
        m.setPreferredSize(dim);
        this.add(m, BorderLayout.CENTER);
    }

    private void removeComponents() {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    public void runGame() {
        this.removeComponents();
        Game g = new Game(this.dim);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new ListenerMove(g));
        this.add(g, BorderLayout.CENTER);
    }
}