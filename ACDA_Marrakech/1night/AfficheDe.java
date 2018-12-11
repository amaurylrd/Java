import java.awt.*;
import javax.swing.*;

public class AfficheDe extends JComponent {
	private static int TAILLE = 100;
	private int lance;
	private int num;
	private int[] nums;
	private Runnable test;
	public Thread thread;
	private Image img[];
	private Grid grid;
	private String pressed = "";

	public AfficheDe(Grid g) {
		this.num = 0;
		this.grid = g;
		this.nums = new int[]{1,2,2,3,3,4};
		this.thread = new Thread(new Affichage(this));
		this.img = new Image[4];
		for(int i = 0 ; i < this.img.length ; i++)
			this.img[i] = Toolkit.getDefaultToolkit().getImage("./assets/dice/"+(i+1)+"_de_babouche.png");
	}

	public void donnerBouton(String str)
	{
		this.pressed = str;
	}

	@Override
	public void paintComponent(Graphics pinceau)
	{
		Graphics pin = pinceau.create();
		Image m = this.img[this.nums[this.num]-1];
		pin.drawImage(m, 0, 0, (int) m.getWidth(this)/2, (int) m.getHeight(this)/2, this);
	}

	public void generer()
	{
        this.lance = (int) (Math.random() * TAILLE); //nbr de face de dé à apparaitre
        int _90 = TAILLE - (int)(TAILLE/10);
		
		for (int i=0;i<TAILLE;i++)
		{
	        try
	        {
	        	if (i < _90)
	        		Thread.sleep(10);
	        	else
	        		Thread.sleep((i - _90)*10);
	        }
	        catch(InterruptedException e)
	        {
	        	System.err.println(e.getMessage());
	        }
	        this.num = (this.lance + i)%6;
	        this.repaint();
	    }
	    this.grid.move(this.pressed, this.nums[this.num]);
    }
}

class Affichage implements Runnable {
	private AfficheDe affichage;

	public Affichage(AfficheDe ad)
	{
		this.affichage = ad;
	}

	@Override
	public void run()
	{
		this.affichage.generer();
	}
}