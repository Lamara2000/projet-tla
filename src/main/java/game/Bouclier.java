package game;

import javafx.scene.image.ImageView;

public class Bouclier {
	
	private int x;
	private int y;
	private int valeur;
	private ImageView imageView;
	
	public Bouclier(int x, int y, int valeur) {
		this.x = x;
		this.y = y;
		this.valeur = valeur;
		imageView = new ImageView(SpritesLibrary.imgBouclier);
        imageView.setViewOrder(10);
        imageView.setTranslateX(x * Game.TILE_SIZE + 3);
        imageView.setTranslateY(y * Game.TILE_SIZE + 3);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	javafx.scene.Node getNode() {
        return imageView;
    }

}
