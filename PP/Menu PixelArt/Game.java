public class Game extends javax.swing.JPanel {
	private Frame frame;

	public Game(Frame frame) {
		super();
		this.frame = frame;
		this.initRules(frame.getDimension());
		this.initComponents();
	}

	private void initRules(java.awt.Dimension dimension) {
		this.setLayout(null);
		this.setOpaque(true);
		this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.repaint();
	}

	@Override
    public void paintComponent(java.awt.Graphics g) {
    	this.setSize(this.frame.getDimension());
        this.setPreferredSize(this.frame.getDimension());
     	java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        g2.setColor(java.awt.Color.BLACK);
        g2.fillRect(0, 0, this.getSize().width, this.getSize().height);
        /*try {
        	java.awt.Image image = javax.imageio.ImageIO.read(new java.io.File("./Assets/Backgrounds/Menu/1600x900(0).png"));
           	g2.drawImage(image, 0, 0, this);
        } catch (java.io.IOException exception) {
            System.err.println(exception.getMessage());
        }*/
    }

    private void initComponents() {
    	//Boss boss = new Boss(this);
    	//java.awt.Dimension dim = new java.awt.Dimension(100, 100);
    	//boss.setSize(dim);
        //boss.setPreferredSize(dim);
        //this.add(boss);
    }
}