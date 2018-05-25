import javax.swing.JLabel;
import java.awt.Color;

public class Cell extends JLabel {
	private static boolean running;
	private final Color GREY = new Color(184, 184, 184);
	private final Color BLACK = new Color(47, 79, 79);
	private int value;

	public Cell() {
		this.initParam(-1);
	}

	public Cell(int x) {
		this.initParam(x);
	}

	private void initParam(int x) {
		this.setOpaque(true);
		this.setBackground(GREY);
		this.running = false;
		this.value = x;
	}

	public boolean getState() {
		return this.running;
	}

	public void setState(boolean b) {
		this.running = b;
	}

	public void setRender() {
		if (this.getBackground() == GREY)
			this.setBackground(BLACK);
		else if (this.getBackground() == BLACK)
			this.setBackground(GREY);
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int iteration) {
		this.value = iteration;
	}

	public boolean isBlocked() {
		return this.getBackground() == BLACK ? true : false; //vrai si c'est un mur
	}

	@Override
    public String toString() {
        return Integer.toString(this.getValue());
    }
}