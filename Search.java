// Yi Guo
// CS 3368 Project 01
// 03/01/2013

import java.util.*;

//Holds the main method to navigate the program
public class Search {
	
	// Global data field
	public static int X;	// Represents the capacity of the 1st bucket
	public static int Y;	// Represents the capacity of the 2nd bucket
	public static int Z;	// Represents the amount of water inquired
	
	// Main method that navigates the whole program
	public static void main(String[] args) {
		promptInput();
		displayOutput(search());
	}
	
	// Traverse, search, and return the goal state if any
	public static Stack<State> search() {
		// Contains states to be expanded
		Queue<State> Q = new LinkedList<State>();
		// Contains states that have been visited
		ArrayList<State> visited = new ArrayList<State>();
		Q.add(new State());		// Start from the initial state
		// Quit searching when all possible states have been expanded
		while (!Q.isEmpty()) {
			// Retrieve and remove the first state for checking and expansion
			State current = Q.remove();
			// Return a stack containing all passing states if the goal state is found
			if (current.isGoal(Z)) {
				Stack<State> solution = new Stack<State>();
				// Trace back from the goal state
				while (current != null) {
					solution.push(current);
					current = current.getParent();
				}
				return solution;
			}
			// Mark the current state as visited
			visited.add(current);
			// Expand the current state by applying all applicable actions
			applyActions(current);
			// Define the lowest cost for comparison
			int lowestCost = 0;
			// Add those which cost the least back to the queue for further expansion
			for (State child : current.getChildren()) {
				// Estimate and set the future cost for all children within the same tier
				child.setFutureCost(heuristicEstimate(visited, child));
				// Consider only those which cost less than the defined cost
				if (child.getCost() <= lowestCost) {
					Q.add(child);
				}
			}
		}
		// Return NULL if no solution exists
		return null;
	}
	
	// Prompt the user input for the values of X, Y, and Z.
	public static void promptInput() {
		@SuppressWarnings("resource")
		Scanner console = new Scanner(System.in);
		System.out.print("Please enter the capacity in gallon of your 1st bucket: ");
		X = console.nextInt();
		System.out.print("Please enter the capacity in gallon of your 2nd bucket: ");
		Y = console.nextInt();
		System.out.print("Please enter the desired amount of water in gallon: ");
		Z = console.nextInt();
		System.out.println();
	}
	
	// Display the solution if any
	public static void displayOutput(Stack<State> solution) {
		if (solution != null) {
			System.out.println("Here is the solution:\n");
			int steps = solution.size() - 1;	// Count steps
			// Retrieve and display all passing states from the initial state to the goal state
			while (!solution.isEmpty()) {
				// Traverse the set of actions and locate the action that is applied to the current state
				for (Action action : solution.pop().getActions()) {
					if (action.getChild() == solution.peek()) {
						System.out.println(action.toString());
					}
				}
			}
			System.out.println(String.format("\nTotal steps consumed: %d\n", steps));
			System.out.println("Note: This is the BEST solution!");
		// Print out "No Solution!" otherwise
		} else {
			System.out.println("No Solution!");
		}
	}

	// Apply actions to the given state
	public static void applyActions(State current) {
		// A bucket can be filled only when it is not full
		if (current.getX() != X) {
			fillX(current);
			// Given the target is not full yet, the source must not be empty for water transfer
			if (current.getY() != 0) {
				fillXWithY(current);
			}
		}
		// A bucket can be filled only when it is not full
		if (current.getY() != Y) {
			fillY(current);
			// Given the target is not full yet, the source must not be empty for water transfer
			if (current.getX() != 0) {
				fillYWithX(current);
			}
		}
		// A bucket can be emptied only when it is not empty
		if (current.getX() != 0) {
			emptyX(current);
		}
		// A bucket can be emptied only when it is not empty
		if (current.getY() != 0) {
			emptyY(current);
		}
	}
	
	// Represents an action that fills the 1st bucket
	public static void fillX(State current) {
		State child = new State(X, current.getY());		// Represents the child state when this action is applied
		child.setParent(current);	// Set the current state as the parent of the child state
		// Define and set the past cost of the current state as one step farther than its parent
		child.setPastCost(current.getPastCost() + 1);
		current.addChild(child);	// Add the child state into the set of children of the current state
		// Create and add this action to the set of actions of the current state
		current.addAction(new Action(current, child, String.format("Fill the %d-gallon bucket", X)));
	}
	
	// Represents an action that fills the 2nd bucket
	public static void fillY(State current) {
		State child = new State(current.getX(), Y);		// Represents the child state when this action is applied
		child.setParent(current);	// Set the current state as the parent of the child state
		// Define and set the past cost of the current state as one step farther than its parent
		child.setPastCost(current.getPastCost() + 1);
		current.addChild(child);	// Add the child state into the set of children of the current state
		// Create and add this action to the set of actions of the current state
		current.addAction(new Action(current, child, String.format("Fill the %d-gallon bucket", Y)));
	}
	
	// Represents an action that empties the 1st bucket
	public static void emptyX(State current) {
		State child = new State(0, current.getY());		// Represents the child state when this action is applied
		child.setParent(current);	// Set the current state as the parent of the child state
		// Define and set the past cost of the current state as one step farther than its parent
		child.setPastCost(current.getPastCost() + 1);
		current.addChild(child);	// Add the child state into the set of children of the current state
		// Create and add this action to the set of actions of the current state
		current.addAction(new Action(current, child, String.format("Empty the %d-gallon bucket", X)));
	}
	
	// Represents an action that empties the 2nd bucket
	public static void emptyY(State current) {
		State child = new State(current.getX(), 0);		// Represents the child state when this action is applied
		child.setParent(current);	// Set the current state as the parent of the child state
		// Define and set the past cost of the current state as one step farther than its parent
		child.setPastCost(current.getPastCost() + 1);
		current.addChild(child);	// Add the child state into the set of children of the current state
		// Create and add this action to the set of actions of the current state
		current.addAction(new Action(current, child, String.format("Empty the %d-gallon bucket", Y)));
	}
	
	// Represents an action that fills the 1st bucket with the water in the 2nd bucket
	public static void fillXWithY(State current) {
		State child;	// Represents the child state when this action is applied
		// Situation in which the action of water transfer doesn't lead to any water leak
		if (current.getX() + current.getY() <= X) {
			child = new State(current.getX() + current.getY(), 0);
		} else {	// Situation in which the action of water transfer leads to water leak
			child = new State(X, current.getY() - (X - current.getX()));
		}
		child.setParent(current);	// Set the current state as the parent of the child state
		// Define and set the past cost of the current state as one step farther than its parent
		child.setPastCost(current.getPastCost() + 1);
		current.addChild(child);	// Add the child state into the set of children of the current state
		// Create and add this action to the set of actions of the current state
		current.addAction(new Action(current, child, String.format("Fill the %d-gallon bucket with the water in the %d-gallon bucket until full or the other is empty", X, Y)));
	}
	
	// Represents an action that fills the 2nd bucket with the water in the 1st bucket
	public static void fillYWithX(State current) {
		State child;	// Represents the child state when this action is applied
		// Situation in which the action of water transfer doesn't lead to any water leak
		if (current.getX() + current.getY() <= Y) {
			child = new State(0, current.getX() + current.getY());
		} else {	// Situation in which the action of water transfer leads to water leak
			child = new State(current.getX() - (Y - current.getY()), Y);
		}
		child.setParent(current);	// Set the current state as the parent of the child state
		// Define and set the past cost of the current state as one step farther than its parent
		child.setPastCost(current.getPastCost() + 1);
		current.addChild(child);	// Add the child state into the set of children of the current state
		// Create and add this action to the set of actions of the current state
		current.addAction(new Action(current, child, String.format("Fill the %d-gallon bucket with the water in the %d-gallon bucket until full or the other is empty", Y, X)));
	}

	// Estimate the future cost from the current state to the goal state
	public static int heuristicEstimate(ArrayList<State> A, State S) {
		// Correct and reset the cost of those which have been defined
		if (S.belongsTo(A)) {
			return S.getFutureCost();
		}
		// Otherwise, define the cost of the current state as one step closer to the goal state than its parent
		return S.getParent().getFutureCost() - 1;
	}
	
}