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
	
	public static void testParser(String entree) {
		try {
			System.out.println("début d'analyse lexicale");
			List<Token> tokens = AnalyseLexicale.analyse(entree);
			for (Token t : tokens) {
				System.out.println(t);
			}
			System.out.println("fin d'analyse lexicale");
			System.out.println("début d'analyse syntaxique");
			Node root = new AnalyseSyntaxique().analyse(tokens);
			dumpNode(root, 0);
			System.out.println("fin d'analyse syntaxique");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void main(String[] args) {
		//testLexer("labyrinthe(12,12) mur(1,0) joueur(0,0)");
		testParser("labyrinthe(12,12) mur(1,0)(4,5)(7,9) joueur(0,0) sortie(7,8) fantome(1,2)(4,7)(2,8) masse 4 (0,6) bouclier 5 (1,8)");
	}
	
	static void dumpNode(Node n, int depth) {
		String s = "";
		for(int i=0;i<depth;i++) s = s + "  ";
		int nbNoeudsEnfants =  n.getChildrenCount();
		s = s + n.toString() + (" (" + nbNoeudsEnfants + " noeuds enfants)");
		System.out.println(s);
		for(int i=0;i<nbNoeudsEnfants;i++) {
			dumpNode(n.getChild(i), depth + 1);
		}
	}

}
