/**
* Application's base node
*
* @author lerouxdu
* @version 0.1
*/
public class Main {
     public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              javax.swing.JFrame frame = new Frame(1600, 900);
              frame.setVisible(true);
            }
        });
    }
}