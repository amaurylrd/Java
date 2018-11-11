import java.awt.Point;

public class Rectangle
{
	private int x;
	private int y;
	private int width;
	private int height;

	public Rectangle(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void setX(int x)
	{
		this.x = x;;
	}

	public void getY(int y)
	{
		this.y = y;
	}

	public void getWidth(int width)
	{
		this.width = width;
	}

	public void getHeight(int height)
	{
		this.height = height;
	}

	public boolean isInside(int xx, int yy)
	{
		return (xx >= this.x && yy >= this.y && xx <= this.width && yy <= this.height) ? true : false;
	}

	@Override
	public String toString() {
		String str = "[x : "+this.x+", y : "+this.y+", width :"+this.width+ ", height : " +this.height+"]";
    	return str;
	}
}