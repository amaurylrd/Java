import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

class Background extends JPanel {
    private Image img;
    private int[] coord = new int[2];

    Background(File path, int x, int y, int height, int width) throws IOException {
        this.img = ImageIO.read(path);
        this.setOpaque(false);
        this.setLocation(x, y);
        this.setSize(height, width);
        this.coord[0] = x;
        this.coord[1] = y;
    }

    @Override
  	public void paintComponent(Graphics pinceau) {
      	Graphics secondPinceau = pinceau.create();
      	secondPinceau.drawImage(this.img, this.coord[0], this.coord[1], this);
    }
}