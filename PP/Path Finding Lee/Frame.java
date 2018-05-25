import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
    private Dimension dim;

    public Frame(String name, int fenWidth, int fenHeight) {
        super(name);
        this.dim = new Dimension(fenWidth, fenHeight);
        this.initParam(this.dim);
        this.initComponents();
    }

    private void initParam(Dimension dim) {
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.pack();
    }

    public void initComponents() {
    	this.add(new Grid(this), BorderLayout.CENTER);
    }

    public int getHeight() {
        return (int) this.dim.getHeight();
    }

    public int getWidth() {
        return (int) this.dim.getWidth();
    }
}