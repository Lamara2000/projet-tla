package tla;

import java.util.List;

public class AnalyseSyntaxique {
	
	private int pos;
	private List<Token> tokens;
	
	public Node analyse(List<Token> tokens) throws Exception {
		pos = 0;
		this.tokens = tokens;
		Node res = S();
		if (pos != tokens.size()) {
			System.out.println("L'analyse syntaxique s'est terminÃ© avant l'examen de tous les tokens");
			throw new IncompleteParsingException();
		}
		return res;
	}
	
	private Node S() throws UnexpectedTokenException {
		Node l = new Node(NodeClass.statement);
		if(getTokenClass() == TokenClass.labyrinthe) {
			l.append(L());
			l.append(A());
		}
		else {
			throw new UnexpectedTokenException("'labyrnthe' attendu");
		}
		return l;
	}
	
	private Node A() throws UnexpectedTokenException {
		Node nodeStmt = new Node(NodeClass.statement);
		if(getTokenClass() == TokenClass.sortie ||
				getTokenClass() == TokenClass.joueur ||
				getTokenClass() == TokenClass.bouclier ||
				getTokenClass() == TokenClass.masse ||
				getTokenClass() == TokenClass.visibilite ||
				getTokenClass() == TokenClass.porte ||
				getTokenClass() == TokenClass.mur ||
				getTokenClass() == TokenClass.fantome) {
			nodeStmt.append(Statement());
		}
		else {
			if(isEOF()) {
				return null;
			}
			else {
				throw new UnexpectedTokenException("'sortie', 'joueur', 'bouclier', 'masse', 'visibilite', 'porte', 'mur', 'fantome', EOF attendu");
			}
		}
		Node next = A_prime();
		if (next != null) {
			nodeStmt.append(next);
		}

		return nodeStmt;
	}
	
	private Node A_prime() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.sortie ||
				getTokenClass() == TokenClass.joueur ||
				getTokenClass() == TokenClass.bouclier ||
				getTokenClass() == TokenClass.masse ||
				getTokenClass() == TokenClass.visibilite ||
				getTokenClass() == TokenClass.porte ||
				getTokenClass() == TokenClass.mur ||
				getTokenClass() == TokenClass.fantome) {
			return A();
		}
		else {
			if(isEOF()) {
				return null;
			}
			else {
				throw new UnexpectedTokenException("'sortie', 'joueur', 'bouclier', 'masse', 'visibilite', 'porte', 'mur', 'fantome', EOF attendu");
			}
		}
	}
	
	private Node L() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.labyrinthe) {
			getToken();
			Node l = new Node(NodeClass.labyrinthe);
			l.append(P());
			return l;		
		}
		else {
			throw new UnexpectedTokenException("'labyrnthe' attendu");
		}
	}
	
	private Node Statement() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.sortie || getTokenClass() == TokenClass.joueur) {
			Node n = B();
			n.append(P());
			return n;
		}
		if(getTokenClass() == TokenClass.bouclier || getTokenClass() == TokenClass.masse) {
			Node n = C();
			n.append(C_prime());
			return n;
		}
		if(getTokenClass() == TokenClass.visibilite || getTokenClass() == TokenClass.porte) {
			Node n = D();
			n.append(P());
			n.append(P());
			return n;
		}
		if(getTokenClass() == TokenClass.mur || getTokenClass() == TokenClass.fantome) {
			Node n = E();
			n.append(F());
			return n;
		}
		throw new UnexpectedTokenException("'sortie', 'joueur', 'bouclier', 'masse', 'visibilite', 'porte', 'mur', 'fantome' attendu");
	}
	
	private Node P() throws UnexpectedTokenException {
		Node p = new Node(NodeClass.point);
		if(getTokenClass() == TokenClass.leftPar) {
			getToken();
			if(getTokenClass() != TokenClass.intVal) {
				throw new UnexpectedTokenException("'intVal' attendu");
			}
			Token t1 = getToken(); 
			Node n1 = new Node (NodeClass.intVal, t1.getValue());
			p.append(n1);
			if(getTokenClass() != TokenClass.comma) {
				throw new UnexpectedTokenException(", attendu");
			}
			getToken();
			if(getTokenClass() != TokenClass.intVal) {
				throw new UnexpectedTokenException("'intVal' attendu");
			}
			Token t2 = getToken(); 
			Node n2 = new Node (NodeClass.intVal, t2.getValue());
			p.append(n2);
			if(getTokenClass() != TokenClass.rightPar) {
				throw new UnexpectedTokenException(") attendu");
			}
			getToken();
			return p;
		}
		throw new UnexpectedTokenException("( attendu");
	}
	
	private Node B() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.sortie) {
			getToken();
			Node n = new Node(NodeClass.sortie);
			return n;
		}
		if(getTokenClass() == TokenClass.joueur) {
			getToken();
			Node n = new Node(NodeClass.joueur);
			return n;
		}
		throw new UnexpectedTokenException("'sortie', 'joueur' attendu");
	}
	
	private Node C() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.bouclier) {
			getToken();
			Node n = new Node(NodeClass.bouclier);
			return n;
		}
		if(getTokenClass() == TokenClass.masse) {
			getToken();
			Node n = new Node(NodeClass.masse);
			return n;
		}
		throw new UnexpectedTokenException("'bouclier', 'masse' attendu");
	}
	
	private Node C_prime() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.intVal) {
			Token t = getToken();
			Node n = new Node(NodeClass.intVal, t.getValue());
			n.append(P());
			return n;
		}
		throw new UnexpectedTokenException("intVal attendu");
	}
	
	private Node D() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.visibilite) {
			getToken();
			Node n = new Node(NodeClass.visibilite);
			return n;
		}
		if(getTokenClass() == TokenClass.porte) {
			getToken();
			Node n = new Node(NodeClass.porte);
			return n;
		}
		throw new UnexpectedTokenException("'porte', 'visibilite' attendu");
	}
	
	private Node E() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.mur) {
			getToken();
			Node n = new Node(NodeClass.mur);
			return n;
		}
		if(getTokenClass() == TokenClass.fantome) {
			getToken();
			Node n = new Node(NodeClass.fantome);
			return n;
		}
		throw new UnexpectedTokenException("'mur', 'fantome' attendu");
	}
	
	private Node F() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.leftPar) {
			Node p = P();
			Node next = F_prime();
			if (next != null) {
				p.append(next);
			}
			return p;
		}
		else {
			throw new UnexpectedTokenException("( attendu");
		}
	}
	
	private Node F_prime() throws UnexpectedTokenException {
		if(getTokenClass() == TokenClass.leftPar) {
			return F();
		}
		if(getTokenClass() == TokenClass.sortie ||
				getTokenClass() == TokenClass.joueur ||
				getTokenClass() == TokenClass.bouclier ||
				getTokenClass() == TokenClass.masse ||
				getTokenClass() == TokenClass.visibilite ||
				getTokenClass() == TokenClass.porte ||
				getTokenClass() == TokenClass.mur ||
				getTokenClass() == TokenClass.fantome || isEOF()) {
			return null;
		}
		throw new UnexpectedTokenException("'sortie', 'joueur', 'bouclier', 'masse', 'visibilite', 'porte', 'mur', 'fantome', (, EOF attendu");
	}
	
	private boolean isEOF() {
		return pos >= tokens.size();
	}
	
	private TokenClass getTokenClass() {
		if (pos >= tokens.size()) {
			return null;
		} else {
			return tokens.get(pos).getCl();
		}
	}
	
	private Token getToken() {
		if (pos >= tokens.size()) {
			return null;
		} else {
			Token current = tokens.get(pos);
			pos++;
			return current;
		}
	}

}
