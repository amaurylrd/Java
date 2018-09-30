import java.awt.Dimension;

public class Frame extends javax.swing.JFrame {
    private int width;
    private int height;
    private String difficulty;

    public Frame(int fenWidth, int fenHeight) {
        super();
        this.width = fenWidth;
        this.height = fenHeight;
        Dimension dimension = new Dimension(fenWidth, fenHeight);
        this.initRules();
        this.setDisplay(dimension);
        this.initComponents(dimension);
        this.pack();
    }

    @Overload
    public Frame() {
        super();
        int width = getToolkit().getScreenSize().width;
        int height = getToolkit().getScreenSize().height;
        this.width = width;
        this.height = height;
        Dimension dimension = new Dimension(width, height);
        this.initRules();
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setDisplay(dimension);
        this.initComponents(dimension);
        this.pack();
    }

    private void initRules() {
        this.setLayout(new java.awt.BorderLayout());
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setIgnoreRepaint(false);
    }

    public void setDisplay(Dimension dimension) {
        this.setBounds(0, 0, dimension.width, dimension.height);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
    }

    private void initComponents(Dimension dimension) {
        this.add(new Menu(this), java.awt.BorderLayout.CENTER);
    }

    public Dimension getDimension() {
        return new Dimension(this.width, this.height);
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
        this.setDisplay(new Dimension(width, height));
        while (!this.getSize().equals(this.getDimension())) {
            this.setDimension(width, height);
        }
    }

    private void removeComponents() {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    public void setDifficulty(String degree) {
        this.difficulty = degree;
    }

    public String getDifficulty() {
        return this.difficulty == null ? "Normal" : this.difficulty;
    }

    public void runGame() {
        this.removeComponents();
        this.setFocusable(true);
        this.requestFocus();
        java.io.File file = new java.io.File("./Save/sauvegarde.dat");
        if (file.exists() && !file.isDirectory()) {
            //la sauvegarde
        }
        else 
            this.add(new Game(this), java.awt.BorderLayout.CENTER);
    }
}