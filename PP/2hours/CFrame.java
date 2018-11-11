public class CFrame extends javax.swing.JFrame
{
	public CFrame()
	{
		super();
		this.init();
		this.handler();
		this.pack();
		this.run();
	}

	private void init()
	{
		int width = getToolkit().getScreenSize().width;
		int height = getToolkit().getScreenSize().height;
		java.awt.Dimension dimension = new java.awt.Dimension(width, height);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setIgnoreRepaint(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

	private void handler()
	{
		this.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        this.getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction()
        {
        	@Override
        	public void actionPerformed(java.awt.event.ActionEvent e)
        	{
                dispose();
                System.exit(0);
            }
        });
	}

	private void run()
	{
		Game g = new Game(this);
	}
}