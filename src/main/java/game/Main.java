package game;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tla.Test;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	ArrayList<Level> levels = new ArrayList<>();
    	Level l1 = new Level();
    	l1.setWidth(20);
    	l1.setHeight(14);
    	String s =
                "     #       #      " +
                "#### # ##### # #### " +
                "     #   # # #    # " +
                " ####### # # ## # # " +
                "       # # #    # # " +
                " ##### # # ###### # " +
                " #       #        # " +
                " # ######### ###### " +
                " # #    *  # # #    " +
                " # # ##### # # # ###" +
                " # #     # # # #    " +
                "   ##### # # # #### " +
                "## #     #   #      " +
                "   #################";
    	l1.setWalls(s);
    	ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(
                new Ghost(
                        6,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        1,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        19,
                        2,
                        2,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        5,
                        12,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_LEFT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );
    	l1.setGhosts(ghosts);
    	
    	levels.add(l1);
    	
    	Level l2 = new Level();
    	l2.setWidth(20);
    	l2.setHeight(14);
    	String s2 =
    			"     #       #      " +
	            "#### # ##### # #### " +
	            "     #   # # #    # " +
	            " ####### # # ## # # " +
	            "       # # #    # # " +
	            " ##### # # ###### # " +
	            " #       #        # " +
	            " # ################ " +
	            " # #    *  # # #    " +
	            " # # ##### # # # ###" +
	            " # #     # # # #    " +
	            "   ##### # # # #### " +
	            "## #     #   #      " +
	            "   #################";
    	l2.setWalls(s2);
    	ArrayList<Ghost> ghosts2 = new ArrayList<>();
    	ghosts2.add(
                new Ghost(
                        6,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT
                        }
                )
        );

        ghosts2.add(
                new Ghost(
                        1,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );

        ghosts2.add(
                new Ghost(
                        19,
                        2,
                        2,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );
    	l2.setGhosts(ghosts2);
    	ArrayList<Case> c1 = new ArrayList<Case>();
    	c1.add(new Case(2, 10));
    	l2.setAdjustW(new Case(2, 9), c1);
    	ArrayList<Case> c2 = new ArrayList<Case>();
    	c2.add(new Case(2, 10));
    	c2.add(new Case(12, 4));
    	l2.setAdjustW(new Case(12, 7), c2);
    	
    	levels.add(l2);
    	
    	levels.add(Test.testParser("labyrinthe(12,12) mur(1,2) mur(1,3) fantome(5,5)(5,7)(6,7) visibilite(1,1)(0,1) joueur(10,10) bouclier 5 (10,1) porte(0,0)(10,10) masse 5 (10,3)"));
    	
        // fenêtre principale et panneau de menu

        GridPane menuPane = new GridPane();
        ArrayList<Button> btnLevel = new ArrayList<Button>();
        for(int i=0; i<levels.size(); i++) {
        	btnLevel.add(new Button("level"+i+1));
        	menuPane.add(btnLevel.get(i), 0, i+1);
        }
        ImageView imageView = new ImageView(SpritesLibrary.imgPlayerLarge);
        menuPane.add(imageView, 1, 0, 1, 4);

        Scene scene = new Scene(menuPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        // panneau racine du jeu

        BorderPane gamePane = new BorderPane();

        Game game = new Game(gamePane);
        
        int i = 0;
        for(Level lvl: levels) {
        	btnLevel.get(i).setOnAction(event -> {
                // affiche le panneau racine du jeu (à la place du panneau de menu)
                scene.setRoot(gamePane);

                // affecte un object correspondant au niveau choisi
                game.setLevel(lvl);

                // démarre le jeu
                game.start();

                // ajuste la taille de la fenêtre
                primaryStage.sizeToScene();
            });
            i++;
        }

        // gestion du clavier

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.Q) {
                // touche q : quitte le jeu et affiche le menu principal
                game.stop();
                scene.setRoot(menuPane);
                primaryStage.sizeToScene();
            }
            if (event.getCode() == KeyCode.R) {
                // touche r : redémarre le niveau en cours
                game.start();
            }
            if (event.getCode() == KeyCode.LEFT) {
                game.left();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                game.right();
            }
            if (event.getCode() == KeyCode.UP) {
                game.up();
            }
            if (event.getCode() == KeyCode.DOWN) {
                game.down();
            }
        });
    }
}
