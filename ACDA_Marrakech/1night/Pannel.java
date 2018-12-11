import java.awt.Color;

public class Pannel extends javax.swing.JPanel
{
	private Grid grid;
	private java.awt.CardLayout layout = new java.awt.CardLayout();
	private Frame frame;

	private static final Color SEE_THROUGH = new Color(255, 255, 255, 0);

	public Pannel(Frame f, Grid g)
	{
		super();
		this.grid = g;
		this.setBackground(SEE_THROUGH);
		this.frame=f;
		this.addComponents();
	}

	public void addComponents()
	{
		int xx = (int) (this.frame.getWidth() * 0.75);
		int width = (int) (this.frame.getWidth() * 0.25);
		int height = (int) (this.frame.getHeight() * 0.6);
		this.setBounds(xx, 0, width, height);
		this.setOpaque(true);
		this.setLayout(this.layout);

		AfficheDe de = new AfficheDe(this.grid);
		this.add("1", de);
		de.setBackground(SEE_THROUGH);

		javax.swing.JPanel pan = new javax.swing.JPanel();
		pan.setLayout(null);
		pan.setBackground(SEE_THROUGH);
		
		int y = 70;
		final int OFFSET = 70;
		String[] strings = {"GAUCHE", "DEVANT", "DROITE", "LANCER LE DE"};
		Color[] colors = {Color.BLUE, Color.BLACK, Color.RED};
		int range = strings.length;
		Button[] buttons = new Button[range];
		for (int i = 0 ; i < range ; i++)
		{
			buttons[i] = new Button(strings[i]);
			buttons[i].setBounds(0, y + i*OFFSET, 220, 50);
			if (strings[i].equals("LANCER LE DE"))
				buttons[i].setEnabled(false);
			else
				buttons[i].setForeground(colors[i]);
			buttons[i].addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					String string = e.getActionCommand();
					if (string.equals("LANCER LE DE"))
					{
						layout.next(Pannel.this);
						frame.repaint(xx, 0, width, height);
						Thread th = new Thread(new Affichage(de));
						th.start();
						String pressed = strings[0];
						for (int ii = 1 ; ii < range-1 ; ii++)
							if (!buttons[ii].isEnabled())
								pressed = strings[ii];
						de.donnerBouton(pressed);
					}
					else
					{
						for (int ii = 0 ; ii < range ; ii++)
							buttons[ii].setEnabled(true);
						Button btn = (Button) e.getSource();
						btn.setEnabled(false);
						buttons[range-1].setEnabled(true);
					}
				}
			});
			pan.add(buttons[i]);
		}
		this.add("0", pan);
		this.layout.show(this, "1");
	}

	public void changeLayout()
	{
		int xx = (int) (this.frame.getWidth() * 0.75);
		int width = (int) (this.frame.getWidth() * 0.25);
		int height = (int) (this.frame.getHeight() * 0.6);
		this.layout.next(this);
		this.frame.repaint(xx, 0, width, height);
	}
}