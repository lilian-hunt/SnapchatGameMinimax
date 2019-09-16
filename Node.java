import java.util.*;
public class Node {
	public boolean isMaximiser;
	public int numCandles;
	public int sumCandles;
	public int maxCandles;
	public int score;
	public List<Node> children;
	public int level;
	
	public Node(){
		children = new ArrayList<Node>();
	}
	public Node(boolean isMaximiser, int maxCandles, int level){
		this.isMaximiser = isMaximiser;
		this.maxCandles = maxCandles;
		children = new ArrayList<Node>();
		this.level = level;
	}
	
	public List<Node> getPossibleMoves(){
		List<Node> ret = new ArrayList<Node>();
		if (sumCandles < maxCandles - 1){
			Node n = this.copyNodeForChild(2, this.sumCandles+2);
			ret.add(n);
		}

		if (sumCandles < maxCandles){
			Node n = this.copyNodeForChild(1, this.sumCandles+1);
			ret.add(n);
		}
	
		return ret;
	}
	
	public Node copyNodeForChild(int numCandles, int sumCandles){
		Node n = new Node();
		n.isMaximiser = !this.isMaximiser;
		n.sumCandles = sumCandles;
		n.maxCandles = this.maxCandles;
		n.numCandles = numCandles;
		n.children = new ArrayList<Node>();
		n.level = this.level+1;
		return n;
	}
	public void addChildren(List<Node> nodes){
		for (Node n : nodes){
			this.children.add(n);
		}
	}

	public Node getBestChild(){
		if (children.size() == 1){
			return children.get(0);
		}
		else if (children.size() == 2){
			if (isMaximiser){
				return (children.get(0).score >= children.get(1).score) ? children.get(0) : children.get(1);
			}
			else {
				return (children.get(0).score < children.get(1).score) ? children.get(0) : children.get(1);
			}
		}
		else {
			return null;
		}
	}
	public Node getNextChildGivenMove(int candles){
		for (Node n : children){
			if (n.numCandles == candles){
				return n;
			}
		}
		return null;
	}
}
