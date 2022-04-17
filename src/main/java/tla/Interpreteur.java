package tla;

import java.util.ArrayList;
import java.util.Map;

import game.Bouclier;
import game.Case;
import game.Ghost;
import game.GhostAction;
import game.Level;
import game.Masse;

public class Interpreteur {
	
	private Level l;
	private char[] murs;
	boolean sortie = false;
	ArrayList<Case> f = new ArrayList<Case>();
	
	public Interpreteur() {
		this.l = new Level();
		
	}
	
	void interpreter(Node n, NodeClass c) throws Exception {
		switch(n.getCl()) {
			case statement:
				interpreter(n.getChild(0), null);
				if (n.getChildrenCount()>1) {
					interpreter(n.getChild(1), null);
				}
				break;
			case labyrinthe:
				int x = Integer.valueOf(n.getChild(0).getChild(0).getValue());
				int y = Integer.valueOf(n.getChild(0).getChild(1).getValue());
				this.l.setWidth(x);
				this.l.setHeight(y);
				this.murs = new char[x*y];
				for(int i=0; i<x*y; i++) {
					this.murs[i] = ' ';
				}
				break;
			case mur:
				interpreter(n.getChild(0), NodeClass.mur);
				break;
			case sortie:
				interpreter(n.getChild(0), NodeClass.sortie);
				break;
			case visibilite:
				ArrayList<Case> ar = new ArrayList<Case>();
				ar.add(new Case(Integer.valueOf(n.getChild(1).getChild(0).getValue()), Integer.valueOf(n.getChild(1).getChild(1).getValue())));
				this.l.setAdjustW(new Case(Integer.valueOf(n.getChild(0).getChild(0).getValue()), Integer.valueOf(n.getChild(0).getChild(1).getValue())), ar);
				break;
			case fantome:
				interpreter(n.getChild(0), NodeClass.fantome);
				l.addGhost(fantome());
				this.f.clear();
				break;
			case joueur:
				interpreter(n.getChild(0), NodeClass.joueur);
				break;
			case bouclier:
				this.l.addBouclier(new Bouclier(Integer.valueOf(n.getChild(0).getChild(0).getChild(0).getValue()), Integer.valueOf(n.getChild(0).getChild(0).getChild(1).getValue()), Integer.valueOf(n.getChild(0).getValue())));
				break;
			case porte: 
				this.l.addPortes(new Case(Integer.valueOf(n.getChild(0).getChild(0).getValue()),Integer.valueOf(n.getChild(0).getChild(1).getValue())), new Case(Integer.valueOf(n.getChild(1).getChild(0).getValue()),Integer.valueOf(n.getChild(1).getChild(1).getValue())));
				break;
			case masse:
				this.l.addMasse(new Masse(Integer.valueOf(n.getChild(0).getChild(0).getChild(0).getValue()), Integer.valueOf(n.getChild(0).getChild(0).getChild(1).getValue()), Integer.valueOf(n.getChild(0).getValue())));
				break;	
			case point:
				if(c == NodeClass.mur) {
					int a = Integer.valueOf(n.getChild(0).getValue());
					int b = Integer.valueOf(n.getChild(1).getValue());
					this.murs[b*this.l.getWidth() + a] = '#';
					if (n.getChildrenCount()>2) {
						interpreter(n.getChild(2), NodeClass.mur);
					}
				}
				if(c == NodeClass.sortie) {
					if(!sortie) {
						int a = Integer.valueOf(n.getChild(0).getValue());
						int b = Integer.valueOf(n.getChild(1).getValue());
						this.murs[b*this.l.getWidth() + a] = '*';
						sortie = true;
					}
					else {
						throw new Exception("il n'est pas possible d'avoir plusieur sortie");
					}
				}
				if(c == NodeClass.fantome) {
					int a = Integer.valueOf(n.getChild(0).getValue());
					int b = Integer.valueOf(n.getChild(1).getValue());
					f.add(new Case(a,b));
					if (n.getChildrenCount()>2) {
						interpreter(n.getChild(2), NodeClass.fantome);
					}
				}
				if(c == NodeClass.joueur) {
					int a = Integer.valueOf(n.getChild(0).getValue());
					int b = Integer.valueOf(n.getChild(1).getValue());
					l.setPlayer_x(a);
					l.setPlayer_y(b);
				}
				break;
				
		}
	}
	
	public Ghost fantome() {
		ArrayList<GhostAction> ga = new ArrayList<GhostAction>();
		if(f.size()==1) {
			return new Ghost(f.get(0).getX(), f.get(0).getY(), 1, new GhostAction[] {GhostAction.REINIT});
		}
		else {
			int or = 0;
			if(f.get(0).getX()>f.get(1).getX()) or = 3; 
			if(f.get(0).getX()<f.get(1).getX()) or = 1;
			if(f.get(0).getX()==f.get(1).getX() & f.get(0).getY()>f.get(1).getY()) or = 0;
			if(f.get(0).getX()==f.get(1).getX() & f.get(0).getY()<=f.get(1).getY()) or = 2;
			int ori = or;
			Case crnt = new Case(f.get(0).getX(), f.get(0).getY());
			for(int i=0; i<f.size()-1; i++) {
				switch(or){
				case 0:
					for(int a=f.get(i).getY(); a<f.get(i+1).getY(); a--) {
						ga.add(GhostAction.FORWARD);
					}
					if(f.get(i).getX()<f.get(i+1).getX()) {
						ga.add(GhostAction.TURN_RIGHT);
						or = 1;
						for(int b=f.get(i).getX(); b<f.get(i+1).getX(); b++) {
							ga.add(GhostAction.FORWARD);
						}
					}
					if(f.get(i).getX()>f.get(i+1).getX()) {
						ga.add(GhostAction.TURN_LEFT);
						or = 3;
						for(int b=f.get(i).getX(); b<f.get(i+1).getX(); b--) {
							ga.add(GhostAction.FORWARD);
						}
					}
					break;
				case 1:
					for(int a=f.get(i).getX(); a<f.get(i+1).getX(); a++) {
						ga.add(GhostAction.FORWARD);
					}
					if(f.get(i).getY()>f.get(i+1).getY()) {
						ga.add(GhostAction.TURN_RIGHT);
						or = 2;
						for(int b=f.get(i).getY(); b<f.get(i+1).getY(); b++) {
							ga.add(GhostAction.FORWARD);
						}
					}
					if(f.get(i).getY()<f.get(i+1).getY()) {
						ga.add(GhostAction.TURN_LEFT);
						or = 0;
						for(int b=f.get(i).getY(); b<f.get(i+1).getY(); b--) {
							ga.add(GhostAction.FORWARD);
						}
					}
					break;
				case 2:
					for(int a=f.get(i).getY(); a<f.get(i+1).getY(); a++) {
						ga.add(GhostAction.FORWARD);
					}
					if(f.get(i).getX()>f.get(i+1).getX()) {
						ga.add(GhostAction.TURN_RIGHT);
						or = 3;
						for(int b=f.get(i).getX(); b<f.get(i+1).getX(); b--) {
							ga.add(GhostAction.FORWARD);
						}
					}
					if(f.get(i).getX()<f.get(i+1).getX()) {
						ga.add(GhostAction.TURN_LEFT);
						or = 1;
						for(int b=f.get(i).getX(); b<f.get(i+1).getX(); b++) {
							ga.add(GhostAction.FORWARD);
						}
					}
					break;
				case 3:
					for(int a=f.get(i).getX(); a<f.get(i+1).getX(); a--) {
						ga.add(GhostAction.FORWARD);
					}
					if(f.get(i).getY()<f.get(i+1).getY()) {
						ga.add(GhostAction.TURN_RIGHT);
						or = 0;
						for(int b=f.get(i).getY(); b<f.get(i+1).getY(); b++) {
							ga.add(GhostAction.FORWARD);
						}
					}
					if(f.get(i).getY()>f.get(i+1).getY()) {
						ga.add(GhostAction.TURN_LEFT);
						or = 2;
						for(int b=f.get(i).getY(); b<f.get(i+1).getY(); b--) {
							ga.add(GhostAction.FORWARD);
						}
					}
					break;
				}
				if(f.get(i).getX()>f.get(i+1).getX()) or = 3; 
				if(f.get(i).getX()<f.get(i+1).getX()) or = 1;
				if(f.get(i).getX()==f.get(i+1).getX() & f.get(i).getY()>f.get(i+1).getY()) or = 0;
				if(f.get(i).getX()==f.get(i+1).getX() & f.get(i).getY()<=f.get(i+1).getY()) or = 2;
			}
			GhostAction[] tab = new GhostAction[ga.size()+1];
			ga.add(GhostAction.REINIT);
			for(int j=0; j<tab.length; j++) {
				tab[j] = ga.get(j);
			}
			return (new Ghost(f.get(0).getX(), f.get(0).getY(),ori,tab));
		}
	}
	
	public Level getLevel() {
		this.l.setWalls(murs);
		return this.l;
	}
	
}
