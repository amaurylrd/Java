public class Game
{
	private int width;
	private int height;
	private CFrame frame;

	public Game(CFrame f)
	{
		java.awt.Dimension dim = f.getSize();
		this.width = dim.width;
		this.height = dim.height;
		this.frame = f;
		this.start();
	}

	private void start()
	{
		this.frame.add(new Content(this), java.awt.BorderLayout.CENTER);
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}
}