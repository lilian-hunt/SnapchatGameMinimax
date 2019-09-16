import java.util.*;
public class Tree {
    public Node root;
	
    public Tree(Node root){
		this.root = root;
	}
	public void constructTree(){
		constructTree(this.root, 0);
	}
		
	// Helper method (recursive)
	private void constructTree(Node parentNode, int level){
		List<Node> children = parentNode.getPossibleMoves();
		parentNode.addChildren(children);
		for (Node n : children){
			constructTree(n, level + 1);
		}
	}
	public void evalTree(){
		evaluateTree(this.root);
	}
    /*
	Helper method for the post order traversal to determin max/min
	*/
    private void evaluateTree(Node node) { 
        if (node == null) 
            return; 	
	  		// Recur on left subtree
			if (node.children.size() >= 1){
				evaluateTree(node.children.get(0));
			} 
			// Recur on right subtree
  			if (node.children.size() >= 2){
				evaluateTree(node.children.get(1));
			}
  
		// End of the tree, evaluate the score 
		if (node.children.size() == 0){
			// Maximiser
			if (node.level % 2 == 0){
				node.score = 1;
			}
			else {
				node.score = -1;
			}
		}
		else if (node.children.size() == 1){
			node.score = node.children.get(0).score;
		}
		else if (node.children.size() == 2){
			if (node.isMaximiser){
				node.score = Math.max(node.children.get(0).score, node.children.get(1).score);
			}
			else {
				node.score = Math.min(node.children.get(0).score, node.children.get(1).score);
			}
		}
    } 
	/* Function to line by line print level order traversal a tree*/
	public static void printLevelOrder(Node root) { 
		int h = root.maxCandles + 1;
		for (int i = 1; i <= h; i++) { 
			printGivenLevel(root, i); 
			System.out.println(); 
		} 
	} 
	/* Print nodes at a given level
	e.g. for total number of candles = 5
	0
	2 1
	2 1 2 1
	* 1 2 1 2 1 2 1
	* * * * * * 1 * * * 1 * 1 2 1
	* * * * * * * * * * * * * * * * * * * 1	
	*/
	
	public static void printGivenLevel(Node root, int level) 
	{ 
		if (root == null) {
			System.out.print("*"); 
			return; 
		}
		if (level == 1) 
			System.out.print(root.numCandles + "(" + root.score + ")"); 
		else if (level > 1) 
		{ 
			if (root.children.size()== 2){
				printGivenLevel(root.children.get(0), level-1); 
				printGivenLevel(root.children.get(1), level-1);
			}
			else if (root.children.size() == 1){
				if (root.children.get(0).numCandles == 1){
					printGivenLevel(null, level-1);
					printGivenLevel(root.children.get(0), level-1); 
				}
				else {
					
					printGivenLevel(root.children.get(0), level-1);
					printGivenLevel(null, level-1);
				}	
			}
			else {
				printGivenLevel(null, level-1);
				printGivenLevel(null, level-1);
			}
		} 
	} 
}
