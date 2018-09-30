/**
* Customizes <code>swing.JButton</code> design
*
* @author lerouxdu
* @version 0.1
*/
import javax.sound.sampled.*;

public class Button extends javax.swing.JButton {
	private Label label;

	public Button(Menu menu, String text, int x, int y) {
		super(text);
		this.setLocation(x, y);
		int width = menu.getSize().width / 5;
		int height = menu.getSize().height / 9;
		this.setSize(width, height);
		this.label = new Label(text, menu.getSize());
		this.label.setSize(width, height);
		int offset = text.length() / 2;
		this.label.setLocation(x+offset, y);
		this.gen(menu);
	}

	private void gen(Menu menu) {
		menu.add(this.label, java.awt.BorderLayout.CENTER);
        this.render(menu);
        this.addActionListener(new java.awt.event.ActionListener() {
			@Override
    		public void actionPerformed(java.awt.event.ActionEvent event) {
    			//playSound(); //pour son à la pression	
    		}

    		public void playSound() {
    			try {
        			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new java.io.File("./Assets/bruh.wav").getAbsoluteFile());
        			Clip clip = AudioSystem.getClip();
        			clip.open(audioInputStream);
        			clip.start();
    			} catch(Exception exception) {
        			System.err.println(exception.getMessage());
    			}
    		}
        });
        menu.add(this, java.awt.BorderLayout.CENTER);
	}

	private void render(Menu menu) {
		this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        String file = menu.getSize().width+"x"+menu.getSize().height;
		this.setIcon(this.load("Default/"+file));
		this.setRolloverIcon(this.load("Hover/"+file));
	}

	private javax.swing.Icon load(String file) {
        javax.swing.Icon image = null;
        try {
            image = new javax.swing.ImageIcon(this.getClass().getResource("./Assets/Icons/Button/"+file+".png"));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return image;
    }

    public void setColor(java.awt.Color color) {
    	this.label.setForeground(color);
    }

    public void setTextLayout(String text) {
    	this.label.setText(text);
    }

    public String getTextLayout() {
    	return this.label.getText();
    }
}