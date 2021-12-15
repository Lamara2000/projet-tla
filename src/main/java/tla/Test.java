package tla;

import java.util.List;

public class Test {

	private static void testLexer(String entree) {
		try {
			System.out.println();
			List<Token> tokens = AnalyseLexicale.analyse(entree);
			for (Token t : tokens) {
				System.out.println(t);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void main(String[] args) {
		testLexer("labyrinthe(12,12) mur(1,0) joueur(0,0)");
	}

}
