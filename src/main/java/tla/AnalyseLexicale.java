package tla;

import java.util.ArrayList;
import java.util.List;

public class AnalyseLexicale {
	
	private static final int ETAT_INITIAL = 0;
	
	private static Integer TRANSITIONS[][] = {
			//             espace lettre chiffre    (     )     ,
			/*  0 */    {      0,     1,      2,  103,  104,  105  },
			/*  1 */    {    101,     1,      1,  101,  101,  101  },
			/*  2 */    {    102,   102,      2,  102,  102,  102  },

	};
	
	private static int indiceSymbole(Character c) throws IllegalCharacterException {
		if (c == null) return 0;
		if (Character.isWhitespace(c)) return 0;
		if (Character.isLetter(c)) return 1;
		if (Character.isDigit(c)) return 2;
		if (c == '(') return 3;
		if (c == ')') return 4;
		if (c == ',') return 5;
		System.out.println("Symbole inconnu : " + c);
		throw new IllegalCharacterException(c.toString());
	}
	
	public static List<Token> analyse(String entree) throws Exception {
		List<Token> tokens = new ArrayList<>();
		String buf = "";

		Integer etat = ETAT_INITIAL;
		SourceReader sr = new SourceReader(entree);

		Character c;
		do {
			c = sr.lectureSymbole();
			Integer e = TRANSITIONS[etat][indiceSymbole(c)];
			if (e == null) {
				System.out.println("pas de transition depuis Ã©tat " + etat + " avec symbole " + c);
				throw new LexicalErrorException("pas de transition depuis Ã©tat " + etat + " avec symbole " + c);
			}
			if (e >= 100) {
				if (e == 103) {
					tokens.add(new Token(TokenClass.leftPar));
				} else if (e == 104) {
					tokens.add(new Token(TokenClass.rightPar));
				} else if (e == 105) {
					tokens.add(new Token(TokenClass.comma));
				} else if (e == 101) {
					if (buf.equals("labyrinthe")) {
						tokens.add(new Token(TokenClass.labyrinthe));
					} else if (buf.equals("mur")) {
						tokens.add(new Token(TokenClass.mur));
					} else if (buf.equals("fantome")) {
						tokens.add(new Token(TokenClass.fantome));
					} else if (buf.equals("sortie")) {
						tokens.add(new Token(TokenClass.sortie));
					} else if (buf.equals("visibilite")) {
						tokens.add(new Token(TokenClass.visibilite));
					} else if (buf.equals("joueur")) {
						tokens.add(new Token(TokenClass.joueur));
					} else if (buf.equals("bouclier")) {
						tokens.add(new Token(TokenClass.bouclier));
					} else if (buf.equals("porte")) {
						tokens.add(new Token(TokenClass.porte));
					} else if (buf.equals("masse")) {
						tokens.add(new Token(TokenClass.masse));
					} else {
						System.out.println("le token " + buf + " n'existe pas");
						throw new UnexistingTokenException("le token " + buf + " n'existe pas");
					}
					sr.goBack();
				} else if (e == 102) {
					tokens.add(new Token(TokenClass.intVal, buf));
					sr.goBack();
				}
				etat = 0;
				buf = "";
			} else {
				etat = e;
				if (etat>0) buf = buf + c;
			}

		} while (c != null);

		return tokens;
	}

}
