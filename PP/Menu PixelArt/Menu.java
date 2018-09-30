import javax.swing.Timer;

public class Menu extends javax.swing.JPanel {
	private Frame frame;
	private int x;
	private int y;
	private int offset;
	private float alpha;
	private java.util.List<String> url;
	private String path;
    protected Timer transition;
    private Timer animation;

	public Menu(Frame frame) {
		this.frame = frame;
		this.alpha = 1.0f;
		this.routes();
		this.initRules(frame.getDimension());
		this.initComponents();
		this.transition = new Timer(24, new java.awt.event.ActionListener() {
			@Override
    		public void actionPerformed(java.awt.event.ActionEvent event) {
    			removeComponents();
    			fade();
    		}
		});
	}

	@Usage(example="1280x720(4)")
	private void routes() {
		this.url = new java.util.ArrayList<>();
		int width = this.frame.getDimension().width;
		int height = this.frame.getDimension().height;
		int frames = 5;
		for (int i = 0 ; i < frames ; i++) {
			this.url.add(width+"x"+height+"("+i+")");
		}
		this.path = this.url.get(0);
		this.animation = new Timer(110, new java.awt.event.ActionListener() {
			@Override
    		public void actionPerformed(java.awt.event.ActionEvent event) {
    			parseFrames();
    		}
		});
		this.animation.start();
	}

	private void parseFrames() {
		for (int i = 0 ; i < this.url.size() ; i++) {
			if (this.url.get(i).equals(this.path)) {
				int index = i + 1;
				if (index == this.url.size()) {
					index = 0;
				}
				this.path = this.url.get(index);
				break;
			}
		}
		repaint();
	}

	private void initRules(java.awt.Dimension dimension) {
		this.setLayout(null);
		this.setOpaque(true);
		this.setSize(dimension);
        this.setPreferredSize(dimension);
		this.x = (int) (dimension.width * 0.8);
		this.y = (int) (dimension.height * 0.4);
		this.offset = (int) (dimension.height / 7);
	}

	private void initComponents() {
		String[] table = {"NOUVEAU", "JOUER", "OPTIONS", "QUITTER"};
		int x = this.x;
		int y = this.y;
		int offset = this.offset;
		for (int i = 0 ; i < table.length ; i++) {
			Button button = new Button(this, table[i], x, y+offset*i);
			button.addActionListener(new ListenerMenu(this.frame, this, table));
        }
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
    	this.setSize(this.frame.getDimension());
        this.setPreferredSize(this.frame.getDimension());
     	java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        g2.setColor(java.awt.Color.BLACK);
        g2.fillRect(0, 0, this.getSize().width, this.getSize().height);
        try {
        	java.awt.Image image = javax.imageio.ImageIO.read(new java.io.File("./Assets/Backgrounds/Menu/"+this.path+".png"));
          	g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, this.alpha));
           	g2.drawImage(image, 0, 0, this);
        } catch (java.io.IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void removeComponents() {
        this.removeAll();
        this.repaint();
    }

    private void fade() {
        this.alpha = this.alpha - 0.04f;
        if (this.alpha < 0.0f) {
            this.alpha = 0.0f;
            this.transition.stop();
            this.transition = null;
            this.frame.runGame();
        }
        this.repaint();
    }
	
	public void optionsMenu() {
    	this.removeComponents();
    	this.rewriteComponents();
    }

    private void rewriteComponents() {
    	String[] table = {this.frame.getDimension().width+"x"+this.frame.getDimension().height, this.frame.getDifficulty(), "RETOUR", "SAUVER", "D\u00c9FAUT"};
		int x = this.x;
		int y = (int) (this.frame.getDimension().height * 0.3);
		int offset = this.offset;
		for (int i = 0 ; i < table.length ; i++) {
            Button button = new Button(this, table[i], x, y+offset*i);
            if (i == 0 || i == 1)
            	button.setColor(new java.awt.Color(69, 69, 69));
            button.addActionListener(new ListenerOptions(this.frame, this, table));
        }
    }

    public void resizeComponents() {
    	this.removeComponents();
    	this.initRules(this.frame.getDimension());
    	this.initComponents();
    	this.animation.stop();
    	this.routes();
    }
}