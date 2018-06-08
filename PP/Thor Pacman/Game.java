import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel {
    private boolean running;
    private Color[][] board;
    private JLabel[][] grid;
    private int[] pac = new int[2];
    private int count;
    private int[][] dot;
    private int[][] ghost;
    private boolean edible;
    private int[] red_prev;
    private int[] pink_prev;
    private java.util.List<Node> open;
    private java.util.List<Node> nodes;
    private int turn;
    private final int MAX_LOOP = 30;
    private int[] lock;

    public Game(Dimension dim) {
    	this.setSize(dim);
        this.setPreferredSize(dim);
        this.running = true;
        this.edible = false;
        this.board = null;
        this.red_prev = new int[2];
        this.pink_prev = new int[2];
        this.lock = new int[3];
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.read();
        JPanel p = new JPanel();
        p.setSize(680, 774);
        p.setLocation(0, 50);
        p.setBackground(Color.BLACK);
        this.gen(p);
        this.add(p, BorderLayout.CENTER);
    }

    private void gen(JPanel wrap) {
        int range = this.board.length;
        int border = 29;
        int space = border + 3;
        int cmp = 0;
        int id =  0;
        JLabel[][] tmp_label = new JLabel[range][range];
        int[][] tmp_dot = new int[range][range];
        int[][] tmp_ghost = new int[3][2];
        for (int i = 0 ; i < tmp_dot.length ; i++) {
        	for (int j = 0 ; j < tmp_dot.length ; j++) {
        		tmp_dot[i][j] = 0;
        	}
        }
        for (int i = 0 ; i < range ; i++) {
            for (int j = 0 ; j < range ; j++) {
                Color c = this.board[i][j];
                JLabel elem = new JLabel();
                tmp_dot[i][j] = 0;
                if (c.getRGB() == new Color(0, 38, 255).getRGB() || c.getRGB() == new Color(71, 117, 255).getRGB())
                	elem.setBackground(c);
                else if (c.getRGB() == new Color(255, 216, 0).getRGB()) {
                	this.pac[0] = i;
                	this.pac[1] = j;
                	elem.setIcon(this.load("pac"));
                }
                else if (c.getRGB() == Color.RED.getRGB()) {
                    elem.setIcon(this.load("apple"));
                    tmp_dot[i][j] = 1;
                    cmp++;
                }
                else {
                    elem.setIcon(this.load("dot"));
                    tmp_dot[i][j] = 1;
                    cmp++;
                }
                if (c.getRGB() == new Color(76, 255, 0).getRGB()) {
                	tmp_ghost[id][0] = i;
                	tmp_ghost[id][1] = j;
                	elem.setIcon(this.load("ghost"+id));
                	id++;
                }
                elem.setOpaque(true);
                elem.setSize(border, border);
                elem.setLocation(j*space+3, i*space+30);
                tmp_label[i][j] = elem;
                wrap.add(elem, BorderLayout.CENTER);
            }
        }
        this.ghost = tmp_ghost;
        this.dot = tmp_dot;
        this.grid = tmp_label;
        this.count = cmp;
        this.red_prev[0] = tmp_ghost[0][0];
        this.red_prev[1] = tmp_ghost[0][1];
        this.pink_prev[0] = tmp_ghost[2][0];
        this.pink_prev[1] = tmp_ghost[2][1];
        this.open = new ArrayList<>();
        this.init(this.grid.length);
    }

    private void init(int range) {
        this.nodes = new ArrayList<>();
        for (int i = 0 ; i < range ; i++) {
            for (int j = 0 ; j < range ; j++) {
                if (this.grid[i][j].getBackground().getRGB() != new Color(0, 38, 255).getRGB())
                    if (this.neighbours(i, j) > 2)
                        this.nodes.add(new Node(i, j));
            }
        }
    }

    private int neighbours(int i, int j) {
        int cmp = 0;
        for (int xoff = -1 ; xoff <= 1 ; xoff++) {
            for (int yoff = -1 ; yoff <= 1 ; yoff++) {
                int ioff = i + xoff;
                int joff = j + yoff;
                if (ioff > -1 && ioff < this.board.length && joff > -1 && joff < this.board.length)
                    if (this.grid[ioff][joff].getBackground().getRGB() != new Color(0, 38, 255).getRGB())
                        if ((ioff == i || joff == j) && !(ioff == i && joff == j))
                            cmp++;
            }
        }
        return cmp;
    }

    public void render() {
    	this.grid[this.pac[0]][this.pac[1]].setIcon(this.load("pac"));
    }

    public void move(char c) {
    	int x = this.pac[0];
    	int y = this.pac[1];
    	if (c == 'L') {
    		if (x == 9 && y == 0) {
    			if (this.dot[9][20] == 1) {
    				this.count = this.count - 1;
    				this.dot[9][20] = 0;
    			}
    			this.grid[x][y].setIcon(this.load("black"));
    			this.grid[9][20].setIcon(this.load("left"));
    			this.pac[0] = 9;
    			this.pac[1] = 20;
    		}
    		else if (this.grid[x][y-1].getBackground().getRGB() != new Color(0, 38, 255).getRGB()) {
    			if (this.dot[x][y-1] == 1) {
    				this.count = this.count - 1;
    				this.dot[x][y-1] = 0;
                    if (this.board[x][y-1].getRGB() == Color.RED.getRGB()) {
                        this.edible = true;
                        this.turn = MAX_LOOP;
                    }
    			}
    			this.grid[x][y].setIcon(this.load("black"));
    			this.grid[x][y-1].setIcon(this.load("left"));
    			this.pac[1] = y-1;
    		}
    	}
    	else if (c == 'R') {
    		if (x == 9 && y == 20) {
    			if (this.dot[9][0] == 1) {
    				this.count = this.count - 1;
    				this.dot[9][0] = 0;
    			}
    			this.grid[x][y].setIcon(this.load("black"));
    			this.grid[9][0].setIcon(this.load("right"));
    			this.pac[0] = 9;
    			this.pac[1] = 0;
    		}
    		else if (this.grid[x][y+1].getBackground().getRGB() != new Color(0, 38, 255).getRGB()) {
    			if (this.dot[x][y+1] == 1) {
    				this.count = this.count - 1;
    				this.dot[x][y+1] = 0;
                    if (this.board[x][y+1].getRGB() == Color.RED.getRGB()) {
                        this.edible = true;
                        this.turn = MAX_LOOP;
                    }
    			}
    			this.grid[x][y].setIcon(this.load("black"));
    			this.grid[x][y+1].setIcon(this.load("right"));
    			this.pac[1] = y+1;
    		}
    	}
    	else if (c == 'U') {
    		if (this.grid[x-1][y].getBackground().getRGB() != new Color(0, 38, 255).getRGB()) {
    			if (this.dot[x-1][y] == 1) {
    				this.count = this.count - 1;
    				this.dot[x-1][y] = 0;
                    if (this.board[x-1][y].getRGB() == Color.RED.getRGB()) {
                        this.edible = true;
                        this.turn = MAX_LOOP;
                    }
    			}
    			this.grid[x][y].setIcon(this.load("black"));
    			this.grid[x-1][y].setIcon(this.load("up"));
    			this.pac[0] = x-1;
    		}
    	}
    	else {
    		if (this.grid[x+1][y].getBackground().getRGB() != new Color(0, 38, 255).getRGB()) {
    			if (this.dot[x+1][y] == 1) {
    				this.count = this.count - 1;
    				this.dot[x+1][y] = 0;
                    if (this.board[x+1][y].getRGB() == Color.RED.getRGB()) {
                        this.edible = true;
                        this.turn = MAX_LOOP;
                    }
    			}
    			this.grid[x][y].setIcon(this.load("black"));
    			this.grid[x+1][y].setIcon(this.load("down"));
    			this.pac[0] = x+1;
    		}
    	}
    	if (this.count == 0)
            this.end();	
    	if (!this.edible)
            for (int i = 0 ; i < 3 ; i++)
                this.ia(i);
        else {
            this.turn = this.turn - 1;
            for (int i = 0 ; i < 3 ; i++)
                this.ia_chased(i);
        }
        if (this.turn == 0) {
            for (int i = 0 ; i < 3 ; i++)
                this.lock[i] = 0;
            this.edible = false;
        }
    }

    private void end() {
        this.render();
        for (int i = 0 ; i < 3 ; i++) { //problème d'affichage
           int x = this.ghost[i][0];
           int y = this.ghost[i][1];
           this.grid[x][y].setIcon(this.load("dead"));
           System.out.println(this.grid[x][y].getIcon());
        }
        this.running =  false;
        System.out.println("victoire");
    }

    private Icon load(String file) {
        Icon img = null;
        try {
            img = new ImageIcon(this.getClass().getResource("./Asset/"+file+".png"));
        } catch (Exception e) {
            e.getMessage();
        }
        return img;
    }

    private void read() {
        File imageFile = new File("./Asset/layout.png");
        try {
            BufferedImage map = ImageIO.read(imageFile);
            int range = map.getWidth();
            Color[][] tmp_label = new Color[range][range];
            for (int i = 0 ; i < range ; i++) {
                for (int j = 0 ; j < range ; j++) {
                    Color c = new Color(map.getRGB(j, i));
                    tmp_label[i][j] = c;
                }
            }
            this.board = tmp_label;
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean getState() {
        return this.running;
    }

    private void ia_chased(int id) {
        int x = this.ghost[id][0];
        int y = this.ghost[id][1];
        int xx = this.pac[0];
        int yy = this.pac[1];
        if (x == xx && y == yy)
            this.lock[id] = 1;
        int[] coord = this.setChaoticLocation(x, y);
        if (this.lock[id] != 1) {
            this.grid[coord[0]][coord[1]].setIcon(this.load("panic"));
            if (this.dot[x][y] == 1) {
                this.grid[x][y].setIcon(this.load("dot"));
                if (this.board[x][y].getRGB() == Color.RED.getRGB())
                    this.grid[x][y].setIcon(this.load("apple"));
            }
            else
                this.grid[x][y].setIcon(this.load("black"));
            if (coord[0] == xx && coord[1] == yy)
                this.lock[id] = 1;
        }
        else {
            this.grid[coord[0]][coord[1]].setIcon(this.load("eye_only"));
            if (this.dot[x][y] == 1) {
                this.grid[x][y].setIcon(this.load("dot"));
                if (this.board[x][y].getRGB() == Color.RED.getRGB())
                    this.grid[x][y].setIcon(this.load("apple"));
            }
            else
                this.grid[x][y].setIcon(this.load("black"));
        }
        this.ghost[id][0] = coord[0];
        this.ghost[id][1] = coord[1];
    }

	private void ia(int id) {
		int x = this.ghost[id][0];
		int y = this.ghost[id][1];
		int xx = this.pac[0];
		int yy = this.pac[1];
		if (x == xx && y == yy) {
			this.running =  false;
			System.out.println("defaite");
		}
		int coord[] = new int[2];
		if (id == 0) {
            coord = this.setRedGhostLocation(x, y);
            this.red_prev[0] = this.ghost[id][0];
            this.red_prev[1] = this.ghost[id][1];
        }
        else if (id == 1)
            coord = this.setGreenGhostLocation(x, y);
        else if (id == 2) {
            coord = this.setPinkGhostLocation(x, y);
            this.pink_prev[0] = this.ghost[id][0];
            this.pink_prev[1] = this.ghost[id][1];
        }
		this.grid[coord[0]][coord[1]].setIcon(this.load("ghost"+id));
		if (this.dot[x][y] == 1) {
    		this.grid[x][y].setIcon(this.load("dot"));
            if (this.board[x][y].getRGB() == Color.RED.getRGB())
                this.grid[x][y].setIcon(this.load("apple"));
        }
    	else
            this.grid[x][y].setIcon(this.load("black"));
		if (coord[0] == xx && coord[1] == yy) {
			this.running =  false;
			System.out.println("defaite");
		}
		this.ghost[id][0] = coord[0];
		this.ghost[id][1] = coord[1];
	}

    private int[] setChaoticLocation(int i, int j) {
        this.open.clear();
        int coord[] = new int[2];
        for (int xoff = -1 ; xoff <= 1 ; xoff++) {
            for (int yoff = -1 ; yoff <= 1 ; yoff++) {
                int ioff = i + xoff;
                int joff = j + yoff;
                if (ioff > -1 && ioff < this.board.length && joff > -1 && joff < this.board.length)
                    if (this.grid[ioff][joff].getBackground().getRGB() != new Color(0, 38, 255).getRGB())
                        if ((ioff == i || joff == j) && !(ioff == i && joff == j)) {
                            Node e = new Node(ioff, joff);
                            this.open.add(e);
                        }
            }
        }
        int rand = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, this.open.size());
        Node e = this.open.get(rand);
        coord[0] = e.getX();
        coord[1] = e.getY();
        return coord;
    }

    // Le fantôme rouge connait en permanence la position du joueur et s'y dirige
	private int[] setRedGhostLocation(int i, int j) {
        int val = 99;
		int coord[] = new int[2];
		for (int xoff = -1 ; xoff <= 1 ; xoff++) {
			for (int yoff = -1 ; yoff <= 1 ; yoff++) {
				int ioff = i + xoff;
				int joff = j + yoff;
				if (ioff > -1 && ioff < this.board.length && joff > -1 && joff < this.board.length)
					if (this.grid[ioff][joff].getBackground().getRGB() != new Color(0, 38, 255).getRGB())
                        if ((ioff == i || joff == j) && !(ioff == i && joff == j)) {
                            int f = this.heuristic(ioff, joff);
                            if (!(this.red_prev[0] == ioff && this.red_prev[1] == joff))
                                if (f < val) {
                                    val = f;
                                    coord[0] = ioff;
                                    coord[1] = joff;
                                }
                        }
			}
		}
        if (i == 9 && j == 0) {
            int y = 20;
            int f = this.heuristic(i, y);;
            if (f < val) {
                val = f;
                coord[0] = i;
                coord[1] = y;
            }
        }
        else if (i == 9 && j == 20) {
            int y = 0;
            int f = this.heuristic(i, y);;
            if (f < val) {
                val = f;
                coord[0] = i;
                coord[1] = y;
            }
        }
        if (coord[0] == 0)
            System.exit(1);
		return coord;
	}

    // Le fantôme vert est informé de la position du joueur quand il le voit
    private int[] setGreenGhostLocation(int i, int j) {
        int[] coord = new int[2];
        if (this.pac[0] == i || this.pac[1] == j) {
            int val = 99;
            for (int xoff = -1 ; xoff <= 1 ; xoff++) {
                for (int yoff = -1 ; yoff <= 1 ; yoff++) {
                    int ioff = i + xoff;
                    int joff = j + yoff;
                    if (ioff > -1 && ioff < this.board.length && joff > -1 && joff < this.board.length)
                        if (this.grid[ioff][joff].getBackground().getRGB() != new Color(0, 38, 255).getRGB())
                            if ((ioff == i || joff == j) && !(ioff == i && joff == j)) {
                                int f = this.heuristic(ioff, joff);
                                if (f < val) {
                                    val = f;
                                    coord[0] = ioff;
                                    coord[1] = joff;
                                }
                            }
                }
                if (i == 9 && j == 0) {
                    int y = 20;
                    int f = this.heuristic(i, y);;
                    if (f < val) {
                        val = f;
                        coord[0] = i;
                        coord[1] = y;
                    }
                }
                else if (i == 9 && j == 20) {
                    int y = 0;
                    int f = this.heuristic(i, y);;
                    if (f < val) {
                        val = f;
                        coord[0] = i;
                        coord[1] = y;
                    }
                }
            }
        }
        else {
            coord = this.setChaoticLocation(i, j);
        }
        if (coord[0] == 0)
            System.exit(1);
        return coord;
    }

    // le fantôme rose prepare une embuscade
    private int[] setPinkGhostLocation(int x, int y) {
        int[] coord = new int[2];
        Node e = this.closest();
        int xx = e.getX();
        int yy = e.getY();
        int val = 99;
        for (int xoff = -1 ; xoff <= 1 ; xoff++) {
            for (int yoff = -1 ; yoff <= 1 ; yoff++) {
                int ioff = x + xoff;
                int joff = y + yoff;
                if (ioff > -1 && ioff < this.board.length && joff > -1 && joff < this.board.length)
                    if (this.grid[ioff][joff].getBackground().getRGB() != new Color(0, 38, 255).getRGB())
                        if ((ioff == x || joff == y) && !(ioff == x && joff == y)) {
                            int f = (int) Math.sqrt((ioff-xx)*(ioff-xx) + (joff-yy)*(joff-yy)); //la distance entre le rose et le noeux
                            if (!(this.pink_prev[0] == ioff && this.pink_prev[1] == joff)) 
                                if (f < val) {
                                    val = f;
                                    coord[0] = ioff;
                                    coord[1] = joff;
                                }
                        }
            }
        }
        if (coord[0] == 0)
            System.exit(1);
        return coord;
    }

    // le noeux le plus proche du pacman
    private Node closest() {
        Node e = this.nodes.get(0);
        int h = this.heuristic(e.getX(), e.getY());
        for (int i = 0 ; i < this.nodes.size() ; i++) {
            Node e2 = this.nodes.get(i);
             int h2 = this.heuristic(e2.getX(), e2.getY());
             if (h2 < h) {
                h = h2;
                e = e2;
             }
        }
        return e;
    }

	private int heuristic(int i, int j) {
		int xx = this.pac[0];
		int yy = this.pac[1];
		return (int) Math.sqrt((i-xx)*(i-xx) + (j-yy)*(j-yy));
	}
}