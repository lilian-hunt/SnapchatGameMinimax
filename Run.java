import java.util.*;
public class Run {
	public static final int NUM_CANDLES = 10;

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.print("Enter 0 to start first, otherwise the computer will start: ");
		boolean userStart = false;
		try {
			if (s.nextInt() == 0){
				userStart = true;
				System.out.println("\nYou will start!");
			}
			else {
				System.out.println("\nComputer will start!");
			}
		}
		catch (InputMismatchException e){
			System.out.println("\nComputer will start!");
		}
		if (userStart){
			userFirst();
		}
		else {
			computerFirst();
		}	
	}
	
	public static void computerFirst(){
		Node rootNode = new Node(true, NUM_CANDLES, 0);
		Tree t = new Tree(rootNode);
		t.constructTree();
		t.evalTree();
		int i = 0;
		Scanner s = new Scanner(System.in);
		
		Node currentNode = rootNode;
		while (i < NUM_CANDLES) {
			System.out.println("The Computer's Turn!");
			currentNode = currentNode.getBestChild();
			System.out.println("Computer selected " + currentNode.numCandles + " candles");
			i += currentNode.numCandles;
			System.out.println("\n** Total candles remaining: " + (NUM_CANDLES-i) +"\n");
			if (i >= NUM_CANDLES){
				System.out.println("You win!");
				break;
			}
			System.out.print("Light 1 or 2 candles: ");
			try {
				int val = s.nextInt();
				if (val < 1 || val > 2){
					System.out.print("Try again! ");
					continue;
				}
				i+= val;
				if (i >= NUM_CANDLES){
					System.out.println("You lose!");
					break;
				}
				currentNode = currentNode.getNextChildGivenMove(val);
				System.out.println("\n** Total candles remaining: " + (NUM_CANDLES-i) +"\n");
				
			}
			catch (InputMismatchException e){
				s.nextLine();
				System.out.print("Try again!! ");
			}

		}
	}
	public static void userFirst(){
		Node rootNode = new Node(true, NUM_CANDLES, 0);
		Tree t = new Tree(rootNode);
		t.constructTree();
		t.evalTree();
		int i = 0;
		Scanner s = new Scanner(System.in);
		
		Node currentNode = rootNode;
		while (i < NUM_CANDLES) {
			System.out.print("Light 1 or 2 candles: ");
			try {
				int val = s.nextInt();
				if (val < 1 || val > 2){
					System.out.print("Try again! ");
					continue;
				}
				i+= val;
				if (i >= NUM_CANDLES){
					System.out.println("You lose!");
					break;
				}
				currentNode = currentNode.getNextChildGivenMove(val);
				System.out.println("\n** Total candles remaining: " + (NUM_CANDLES-i) +"\n");
				System.out.println("The Computer's Turn!");
				currentNode = currentNode.getBestChild();
				System.out.println("Computer selected " + currentNode.numCandles + " candles");
				i += currentNode.numCandles;
				System.out.println("\n** Total candles remaining: " + (NUM_CANDLES-i) +"\n");
				if (i >= NUM_CANDLES){
					System.out.println("You win!");
					break;
				}
			}
			catch (InputMismatchException e){
				s.nextLine();
				System.out.print("Try again!! ");
			}

		}
	}
}
