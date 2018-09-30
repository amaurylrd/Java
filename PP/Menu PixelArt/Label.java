import java.awt.Font;

/**
* Customizes <code>swing.JLabel</code> over <code>swing.JButton</code> 
*
* @author lerouxdu
* @version 0.1
*/
public class Label extends javax.swing.JLabel {
	public Label(String text, java.awt.Dimension dimension) {
		super(text, javax.swing.SwingConstants.CENTER);
		this.render((float) (dimension.width * dimension.height));
	}

	private void render(float pixels) {
		float vect = pixels / 54568.4210526f;
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("./Assets/Fonts/Pixellari.ttf").openStream());
			customFont = customFont.deriveFont(vect);
			this.setFont(customFont);
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}
}