public class Content extends javax.swing.JPanel 
{
	private int x;
	private int y;
	private int pattern;
	private java.util.List<Rectangle> shapes;
	private boolean next;
	private final java.awt.Image SPACESHIP = java.awt.Toolkit.getDefaultToolkit().getImage("./spaceship2.png");
	private final int MAX = 1;
	private int index;
	private javax.swing.Timer transition;

	public Content(Game g)
	{
		super();
		int width = g.getWidth();
		int height = g.getHeight();
		this.setSize(width, height);
		this.x = (int) width/2;
		this.y = (int) height/2;
		this.handler();
		this.init();
		this.inputs();
	}

	private void handler()
	{
		this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, 0), "Spacebar");
        this.getActionMap().put("Spacebar", new javax.swing.AbstractAction()
        {
        	@Override
        	public void actionPerformed(java.awt.event.ActionEvent e)
        	{    
               transition.start();
            }
        });
	}

	private void init()
	{
		this.transition = new javax.swing.Timer(1124, new java.awt.event.ActionListener()
		{
			@Override
    		public void actionPerformed(java.awt.event.ActionEvent event)
    		{
    			System.out.println("nouveau pattern");
    			shapes.clear();
				index = rand(MAX);
    		}
		});
		this.next = true;
		this.index = -1;
		this.shapes = new java.util.ArrayList<>();
	}

	private void inputs()
	{
		java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
     	this.setCursor(tk.createCustomCursor(tk.getImage("") , new java.awt.Point(0, 0), ""));
		this.addMouseMotionListener(new java.awt.event.MouseMotionListener()
		{
			@Override
    		public void mouseDragged(java.awt.event.MouseEvent e) {
    			actualize(e);
    		}

    		@Override
    		public void mouseMoved(java.awt.event.MouseEvent e)
    		{
    			actualize(e);
    		}

    		private void actualize(java.awt.event.MouseEvent e)
    		{
    			for (int i = 0; i < shapes.size(); i++)
    			{
    				Rectangle r = shapes.get(i);
    				if (r.isInside(x, y))
    				{
    					transition.stop();
            			transition = null;
    					System.out.println("perdu");
    				}
    			}
    			x = e.getX();
    			y = e.getY();
    			repaint();
    		}
    	});
	}

	@Override
    public void paintComponent(java.awt.Graphics g)
    {
     	java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        if (this.isOpaque())
        {
        	int width = this.getWidth();
        	int height = this.getHeight();
        	g2.setColor(java.awt.Color.BLACK);
        	g2.fillRect(0, 0, width, height);
        	if (this.index == 0)
				this.DrawP1(g2, width, height);

        	g2.drawImage(SPACESHIP, this.x, this.y, this);
		}
		g2.setColor(this.getForeground());
	}

	private void DrawP1(java.awt.Graphics2D g2, int w, int h)
	{
		int size = (int) h/3;
		if (this.next)
		{
			this.shapes.add(new Rectangle(0, 0, w, size));
			this.shapes.add(new Rectangle(0, h-size, w, size));
			this.next = false;
        }
        g2.setColor(java.awt.Color.WHITE);
        g2.fillRect(0, 0, w, size);

       	g2.setColor(java.awt.Color.WHITE);
       	g2.fillRect(0, h-size, w, size);
	}

	private int rand(int max)
	{
       	int n = (int) (Math.random() * max);
		return n;
	}
}