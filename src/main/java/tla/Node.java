package tla;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Node> children = new ArrayList<>();
	private NodeClass cl;
	private String value;

	public Node(NodeClass cl, String value) {
		this.cl = cl;
		this.value = value;
	}

	public Node(NodeClass cl) {
		this.cl = cl;
	}

	public void append(Node n) {
		children.add(n);
	}

	public Node getChild(int i) {
		return this.children.get(i);
	}

	public int getChildrenCount() {
		return this.children.size();
	}

	public NodeClass getCl() {
		return cl;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		String s = "<";
		if (cl != null) s = s + cl;
		if (value != null) s = s + ", " + value;
		return s + ">";
	}
}
