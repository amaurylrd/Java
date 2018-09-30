public class ListenerMenu implements java.awt.event.ActionListener {
    private Frame frame;
    private Menu menu;
    private String[] table;

    public ListenerMenu(Frame frame, Menu menu, String[] table) {
        this.frame = frame;
        this.menu = menu;
        this.table = table;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        String string = event.getActionCommand();
        if (string == this.table[1] || string == this.table[0])
            this.menu.transition.start();
        else if (string == this.table[2])
            this.menu.optionsMenu();
        else if (string == this.table[3]) {
            this.frame.dispose();
            System.exit(0);
        }
    }
}