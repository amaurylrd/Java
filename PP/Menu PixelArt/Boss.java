import javax.swing.Timer;

public class Boss extends javax.swing.JPanel {
	private Game game;
	private java.util.List<String> url;
	private String path;
	private Timer animation;
	private int x;
	private int y;

	public Boss(Game game) {
		super();
		this.game = game;
		this.x = 500;
		this.y = 700;
		this.routes();
		this.initRules();
		this.initComponents();
	}

	private void initRules() {
		this.setLayout(null);
		this.setOpaque(true);
	}

	@Override
    public void paintComponent(java.awt.Graphics g) {
     	java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        try {
        	java.awt.Image image = javax.imageio.ImageIO.read(new java.io.File("./Assets/"+this.path+".png"));
           	g2.drawImage(image, this.x, this.y, this);
        } catch (java.io.IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void initComponents() {
    }

    @Usage(example="1280x720(4)")
	private void routes() {
		this.url = new java.util.ArrayList<>();
		int frames = 4;
		for (int i = 0 ; i < frames ; i++) {
			this.url.add("pixil-frame-"+i);
		}
		this.path = this.url.get(0);
		this.animation = new Timer(300, new java.awt.event.ActionListener() {
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
}