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
    	
    	levels.add(Test.testParser("labyrinthe(12,12) joueur(1,1) mur(0,0)(0,1)(0,2)(0,3)(0,4)(0,5)(0,6)(0,7)(0,8)(0,9)(0,10)(0,11)(1,0)(2,0)(3,0)(4,0)(5,0)(6,0)(7,0)(8,0)(9,0)(10,0)(11,0)(11,1)(11,2)(11,3)(11,4)(11,5)(11,6)(11,7)(11,8)(11,9)(11,10)(11,11)(1,11)(2,11)(3,11)(4,11)(5,11)(6,11)(7,11)(8,11)(9,11)(10,11) mur(2,2)(2,3)(2,4)(2,5)(2,6)(2,7)(2,8)(2,9)(9,2)(9,3)(9,4)(9,6)(9,7)(9,8)(9,9)(3,2)(4,2)(5,2)(6,2)(7,2)(8,2)(3,9)(4,9)(5,9)(6,9)(7,9)(8,9) mur(4,4)(4,5)(4,7)(7,4)(7,5)(7,6)(7,7)(5,4)(6,4)(5,7)(6,7) "
    			+ "visibilite(4,6)(9,5) fantome(8,3)(8,8) fantome(3,3)(3,8) sortie(5,5) masse 1 (10,10)"));
    	levels.add(Test.testParser("labyrinthe(20,14) fantome(10,12)(12,12) fantome(10,8)(10,11) porte(19,8)(10,0) bouclier 3 (19,10) masse 2 (14,8) porte(10,2)(18,8) fantome(0,2)(0,4)(3,4) visibilite(5,6)(4,6) sortie(4,12) mur(0,1)(0,12)(1,1)(1,3)(1,5)(1,6)(1,7)(1,8)(1,9)(1,10)(1,12)(2,1)(2,3)(2,5)(3,1)(3,3)(3,5)(3,7)(3,8)(3,9)(3,10)(3,11)(3,12)(3,13)(4,3)(4,5)(4,7)(4,11)(4,13)(5,0)(5,1)(5,2)(5,3)(5,5)(5,7)(5,9)(5,11)(5,13)(6,3)(6,7)(6,9)(6,11)(6,13)(7,1)(7,3)(7,4)(7,5)(7,7)(7,9)(7,11)(7,13)(8,1)(8,7)(8,9)(8,13)(9,1)(9,2)(9,3)(9,4)(9,5)(9,6)(9,7)(9,9)(9,10)(9,11)(9,12)(9,13)(10,1)(10,7)(10,13)(11,1)(11,2)(11,3)(11,4)(11,5)(11,7)(11,8)(11,9)(11,10)(11,11)(11,13)(12,5)(12,13)(13,0)(13,1)(13,2)(13,3)(13,5)(13,7)(13,8)(13,9)(13,10)(13,11)(13,12)(13,13)(14,3)(14,5)(14,7)(14,13)(15,1)(15,5)(15,7)(15,8)(15,9)(15,10)(15,11)(15,13)(16,1)(16,3)(16,4)(16,5)(16,7)(16,11)(16,13)(17,1)(17,7)(17,9)(17,11)(17,13)(18,1)(18,2)(18,3)(18,4)(18,5)(18,6)(18,7)(18,9)(18,11)(18,13)(19,9)(19,13)(19,7)"));
    	
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
