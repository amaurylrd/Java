import java.awt.Color;

public class Player 
{
	private final static Color _a95934 = new Color(169, 89, 52, 180);
	private final static Color _898603 = new Color(137, 134, 3, 180);
	private final static Color _eB8e02 = new Color(235, 142, 2, 180);
	private final static Color _005859 = new Color(0, 88, 89, 180);
	public static Color[] colors = {_a95934, _898603, _eB8e02, _005859};
	private int index;
	private int rugs;
	private int coins;

	public Player(int i, int r, int c)
	{
		this.index = i;
		this.rugs = r;
		this.coins = c;
	}

	public Color getColor()
	{
		return this.colors[this.index];
	}

	public int getRugs()
	{
		return this.rugs;
	}

	public void minusRugs()
	{
		this.rugs--;
	}

	public int getIndex()
	{
		return this.index;
	}
	
	public int getCoins()
	{
		return this.coins;
	}

	public void setCoins(int coins)
	{
		this.coins += coins; 
	}
	
	@Override
	public String toString()
	{
   		return "[color : "+this.getColor()+"] [rugs : "+this.rugs+"] [coins : "+this.coins+"]";
	}
}