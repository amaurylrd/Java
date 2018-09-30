public class ListenerOptions implements java.awt.event.ActionListener {
    private Frame frame;
    private Menu menu;
    private String[] table;
    private static Button resolution;
    private static Button difficulty;

    public ListenerOptions(Frame frame, Menu menu, String[] table) {
        this.frame = frame;
        this.menu = menu;
        this.table = table;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent event) {
        String string = event.getActionCommand();
        Button source = (Button) event.getSource();
        if (string == this.table[0]) {
            String[] resolution = {"1920x1080", "1600x900", "1280x720"};
            this.seekAndPrint(source, resolution);
            this.resolution = source;
        }
        else if (string == this.table[1]) {
            String[] difficulty = {"Normal", "Cauchemar"};
            this.seekAndPrint(source, difficulty);
            this.difficulty = source;
        }
        else if (string == this.table[2]) {
            this.frame.setDifficulty(this.table[1]);
            this.menu.resizeComponents();
        }
        else if (string == this.table[3]) {
            if (this.resolution != null) {
                int[] values = this.parseString();
                this.frame.setDimension(values[0], values[1]);
            }
            if (this.difficulty != null)
                this.frame.setDifficulty(this.difficulty.getTextLayout());
            this.menu.resizeComponents();
        }
        else if (string == this.table[4]) {
            if (this.resolution != null)
                this.resolution.setTextLayout(this.table[0]);
            if (this.difficulty != null)
                this.difficulty.setTextLayout(this.table[1]);
        }
    }

    private void seekAndPrint(Button button, String[] grid) {
        for (int i = 0 ; i < grid.length ; i++) {
            if (grid[i].equals(button.getTextLayout())) {
                int index = i + 1 ;
                if (index == grid.length)
                    index = 0;
                button.setTextLayout(grid[index]);
                return;
            }
        }
    }

    private int[] parseString() {
        String string = this.resolution.getTextLayout();
        int[] tmp = new int[2];
        tmp[0] = Integer.parseInt(string.substring(0, 4));
        tmp[1] = Integer.parseInt(string.substring(5));
        return tmp;
    }
}