import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Menu extends JPanel {
	private Frame f;

	public Menu(Frame frame) {
		this.f = frame;
		this.setLayout(null);
		
	}

	private void initComponents() {
        String[] table = {"NOUVELLE PARTIE", "QUITTER"};
        for (int i = 0 ; i < 2 ; i++) {
            JButton btn = new JButton(table[i]);
            btn.setSize(237, 60);
            btn.setLocation(230, 450+85*i);
            btn.addActionListener(new ListenerMenu(this.f, table));
            int k = i+i;
            btn.setIcon(new ImageIcon(getClass().getResource("./Asset/"+k+".jpg")));
            k++;
            btn.setRolloverIcon(new ImageIcon(getClass().getResource("./Asset/"+k+".jpg")));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
            this.add(btn, BorderLayout.CENTER);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        File path = new File("./Asset/title.jpg");
        Graphics g2 = g.create();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.getSize().width, this.getSize().height);
        try {
           Image img = ImageIO.read(path);
           g2.drawImage(img, 0, 0, this);
        } catch (IOException e) {
            e.getMessage();
        }
        this.initComponents();
    }
}