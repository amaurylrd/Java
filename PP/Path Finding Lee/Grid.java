import javax.swing.JPanel;
import java.awt.Color;

public class Grid extends JPanel {
	private Cell[][] board;
	private Frame f;
	private int m;
	private int p;
	private int count;
	private boolean done;
	private final Color RED = new Color(139, 0, 0);
	private final Color PATH = new Color(179, 212, 212);
	private final Color GREY = new Color(212, 212, 212);

	public Grid(Frame frame) {
		this.f = frame;
		this.initParam(frame);
		this.boardGeneration(18, 23);
	}

	private void initParam(Frame f) {
        	this.setLocation(0, 0);
        	this.setSize(f.getWidth(), f.getHeight());
        	this.setBackground(new Color(246, 246, 246));
        	this.setLayout(null);
    	}

	private void boardGeneration(int m, int p) {
		Cell[][] tmp = new Cell[m][p];
		this.m = m;
		this.p = p;
		int border = 50;
		int space = border + 3;
		for (int i = 0 ; i < m ; i++) {
			for (int j = 0 ; j < p  ; j++) {
				Cell elem;
				if (i == 0 && j == 0)
					elem = new Cell(0);
				else if (i == m-1 && j == p-1)
					elem = new Cell(-2);
				else
					elem = new Cell();
				elem.setSize(border, border);
                		elem.setLocation(j*space+23, i*space+23);
                		elem.addMouseListener(new Clicked(this));
                		this.add(elem, BoderLayout.CENTER);
                		if ((i == 0 && j == 0) || (i == m-1 && j == p-1))
                			elem.setBackground(RED);
                		tmp[i][j] = elem;
			}
		}
		this.board = tmp;
	}

	public void initNodeChain() {
		this.count = 0;
		this.waveExpansion(0, 0, 0);
	}

	private void waveExpansion(int k, int i, int j) {
		if (!this.board[0][0].getState()) {
			for (int xoff = -1 ; xoff <= 1 ; xoff++) {
				for (int yoff = -1 ; yoff <= 1 ; yoff++) {
					int ioff = i + xoff;
					int joff = j + yoff;
					if (ioff > -1 && ioff < this.m && joff > -1 && joff < this.p && (ioff == i || joff == j) && !(ioff == i && joff == j)) {
						Cell elem = this.board[ioff][joff];
						if (!elem.isBlocked()) {
							if (elem.getValue() == -2) {
								elem.setState(true);
								System.out.println("TROUVE En "+k+" Etapes");
								this.backtrace(k);
							}
							else if (elem.getValue() == -1)
								this.render(k+1, elem, GREY);
						}
					}
				}
			}
		}
		if (this.count == 0)
			this.parse(k+1);
	}

	private void render(int k, Cell elem, Color c) {
		elem.setBackground(c);
		elem.setValue(k);
	}

	private void setCount(int k) {
		int cmp = 0;
		for (int i = 0 ; i < this.m ; i++) {
			for (int j = 0 ; j < this.p ; j++) {
				Cell elem = this.board[i][j];
				if (elem.getValue() == k) 
					cmp++;
			}
		}
		this.count = cmp;
	}

	private void parse(int k) {
		this.setCount(k);
		for (int i = 0 ; i < this.m ; i++) {
			for (int j = 0 ; j < this.p ; j++) {
				Cell elem = this.board[i][j];
				if (elem.getValue() == k) {
					this.count =  this.count - 1;
					this.waveExpansion(k, i, j);
				}
			}
		}
	}

    public void refreshComponents() {
    	f.getContentPane().removeAll();
		f.getContentPane().repaint();
		f.initComponents();
    }

    private void backtrace(int k) {
    	int scope = this.m + this.p - 2;
    	Cell[] list = new Cell[scope];
    	this.done = false;
    	for (int i = 0 ; i < this.m ; i++) {
			for (int j = 0 ; j < this.p ; j++) {
				Cell elem = this.board[i][j];
				if (elem.getValue() == -2)
					this.pathfinding(k, i, j, list);
			}
		}
	}

    private void pathfinding(int k, int i, int j, Cell[] list) {
    	list[k] = this.board[i][j];
    	if (k == 0)
    		this.clearance(list);
    	if (!this.done) {
    		for (int xoff = -1 ; xoff <= 1 ; xoff++) {
			for (int yoff = -1 ; yoff <= 1 ; yoff++) {
				int ioff = i + xoff;
				int joff = j + yoff;
				if (ioff > -1 && ioff < this.m && joff > -1 && joff < this.p && (ioff == i || joff == j) && !(ioff == i && joff == j)) {
					Cell elem = this.board[ioff][joff];
					if (elem.getValue() == k)
						pathfinding(k-1, ioff, joff, list);
					}
				}
			}
		}
		
    }

    private void show(Cell[] t) {
    	for (int i = 0 ; i < t.length ; i++)
    		System.out.print(t[i]+" ");
    	System.out.println();
    }

	private void clearance(Cell[] list) {
		this.done = true;
		for (int i = 0 ; i < list.length ; i++)
			if (list[i].getValue() != -2) {
				list[i].setBackground(PATH);
				list[i].setText(Integer.toString(list[i].getValue()));
			}
	}
}
