/**
* This class <code>Button</code> extends javax.swing.JButton that it overrides to custom button.
*
* @version 0.1
*/
public class Button extends javax.swing.JButton
{
	/**
    * Constructor <code>Button</code>.
    *
    * @param text the text to set in the button
    */
	public Button(String text)
	{
		super(text);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}
}