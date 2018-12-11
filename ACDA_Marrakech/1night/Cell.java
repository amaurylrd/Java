import java.awt.Color;

public class Cell extends javax.swing.JLabel
{
	private static final Color WHITE = new Color(255, 255, 255, 180);
	private static boolean bool;
	private Color tmp;
	private Grid grid;
	private int value;
	private int x;
	private int y;

	private java.awt.event.MouseAdapter carpetListener = new java.awt.event.MouseAdapter()
	{
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e)
		{
			if (!bool)
				grid.clean(x, y);
			else
				grid.clean2(x, y);
		}
	};

 	public Cell(Grid g, int val, int x, int y)
 	{
 		super();
 		this.grid = g;
 		this.value = val;
 		this.bool = false;
 		this.x = x;
 		this.y = y;
 		this.tmp = WHITE;
 		this.setOpaque(true);
 		this.setBackground(WHITE);
 	}

 	public int getValue()
 	{
 		return this.value;
 	}

 	public void setValue(int val)
 	{
 		this.value = val;
 	}

 	public void setHighlight(boolean b)
 	{
 		this.bool = b;
 		if (this.getMouseListeners().length == 0)
 		{
 			this.tmp = this.getBackground();
  			this.addMouseListener(this.carpetListener);
 			this.setBackground(new Color(this.tmp.getRGB()));
 		}
 	}

 	public void removeHighlight()
 	{
 		this.setBackground(this.tmp);
 		this.grid.repaint(this.grid.getRectangle(this.x, this.y));
 		this.removeMouseListener(this.carpetListener);
 	}

 	public void setColor(Color c)
 	{
 		this.tmp = new Color(c.getRed(), c.getGreen(), c.getBlue(), 180);
 		this.setText(this.value+"");
 	}

 	@Override
	public String toString()
	{
   		return "[x : "+this.x+"] [y : "+this.y+"] [value : "+this.value+"]";
	}
}