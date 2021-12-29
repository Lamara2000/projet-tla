package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Level {
	
	private char[] walls;
	private ArrayList<Ghost> ghosts;
	private Map<Case,ArrayList<Case>> adjustW;
	private int width;
	private int height;
	private int player_x;
	private int player_y;
	private ArrayList<Bouclier> boucliers;
	private ArrayList<Masse> masses;
	private Map<Case, Case> portes;
	
	public Level() {
		this.ghosts = new ArrayList<Ghost>();
		this.adjustW = new HashMap<Case,ArrayList<Case>>();
		this.boucliers = new ArrayList<Bouclier>();
		this.portes = new HashMap<Case, Case>();
		this.masses = new ArrayList<Masse>();
	}
	
	public void setWalls(String s) {
		this.walls = s.toCharArray();
	}
	
	public void setWalls(char[] walls) {
		this.walls = walls;
	}
	
    public char[] getWalls() {
    	return walls;
    }
    
    public void setGhosts(ArrayList<Ghost> g) {
    	for(Ghost ghost : g) {
    		this.ghosts.add(ghost);
    	}
    }
    
    public void setAdjustW(Case c, ArrayList<Case> m) {
		this.adjustW.put(c, m);
	}

	public  ArrayList<Ghost> getGhosts() {
        return ghosts;
    }
    
    public void adjustWalls(Game game) {
    	for(Map.Entry m : adjustW.entrySet()) {
    		boolean b = true;
    		Object o = m.getValue();
    		for(Case c : (ArrayList<Case>)o) {
    			b = b & (game.isVisited(c.getX(), c.getY())>0);
    		}
    		game.getTile(((Case) (m.getKey())).getX(), ((Case) (m.getKey())).getY()).setState(
    	            b ?
    	            TileState.WALL :
    	            TileState.EMPTY
    	        );
    	}
    }

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void addGhost(Ghost g) {
		this.ghosts.add(g);
	}

	public int getPlayer_x() {
		return player_x;
	}

	public void setPlayer_x(int player_x) {
		this.player_x = player_x;
	}

	public int getPlayer_y() {
		return player_y;
	}

	public void setPlayer_y(int player_y) {
		this.player_y = player_y;
	}
	
	public void addBouclier(Bouclier b) {
		this.boucliers.add(b);
	}
	
	public ArrayList<Bouclier> getBoucliers(){
		return this.boucliers;
	}

	public Map<Case, Case> getPortes() {
		return portes;
	}

	public void addPortes(Case c1, Case c2) {
		this.portes.put(c1, c2);
	}

	public ArrayList<Masse> getMasses() {
		return masses;
	}
	
	public void addMasse(Masse m) {
		this.masses.add(m);
	}
}
