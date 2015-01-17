// Yi Guo
// CS 3368 Project 01
// 03/01/2013

import java.util.*;

//Represents a state of the amount of water in the two buckets
public class State {

	// Data field
	private int x;		// Represents the amount of water in the 1st bucket
	private int y;		// Represents the amount of water in the 2nd bucket
	
	private State parent;				// Represents the parent state of the current state
	private ArrayList<State> children;	// Contains child states of the current state
	private ArrayList<Action> actions;	// Contains actions that are applicable to the current state
	
	private int pastCost;		// Represents the cost from the initial state to the current state
	private int futureCost;		// Represents the estimated cost from the current state to the goal state
	
	public static int infinity = 99999;		// Defines the value of infinity
	
	// Constructor that creates a default state (initial state)
	public State() {
		x = y = 0;
		parent = null;
		pastCost = 0;
		futureCost = infinity;
		actions = new ArrayList<Action>();
		children = new ArrayList<State>();
	}
	
	// Second constructor that creates a specified state
	public State(int x, int y) {
		this.x = x;
		this.y = y;
		parent = null;
		pastCost = infinity;
		futureCost = infinity;
		actions = new ArrayList<Action>();
		children = new ArrayList<State>();
	}
	
	// Set the amount of water in the 1st bucket
	public void setX(int x) {
		this.x = x;
	}
		
	// Set the amount of water in the 2nd bucket
	public void setY(int y) {
		this.y = y;
	}
	
	// Set the cost from the initial state
	public void setPastCost(int pastCost) {
		this.pastCost = pastCost;
	}
	
	// Set the estimated cost to the goal state
	public void setFutureCost(int futureCost) {
		this.futureCost = futureCost;
	}
	
	// Set the parent state
	public void setParent(State parent) {
		this.parent = parent;
	}
	
	// Add a child to the set of children
	public void addChild(State child) {
		children.add(child);
	}
	
	// Add an action to the set of applicable actions
	public void addAction(Action action) {
		actions.add(action);
	}
	
	// Retrieve the amount of water in the 1st bucket
	public int getX() {
		return x;
	}
	
	// Retrieve the amount of water in the 2nd bucket
	public int getY() {
		return y;
	}
	
	// Retrieve the parent state
	public State getParent() {
		return parent;
	}
	
	// Retrieve the cost from the initial state
	public int getPastCost() {
		return pastCost;
	}
	
	// Retrieve the estimated cost to the goal state
	public int getFutureCost() {
		return futureCost;
	}
	
	// Calculate and return the total cost
	public int getCost() {
		return pastCost + futureCost - infinity;
	}
	
	// Retrieve the set of children
	public ArrayList<State> getChildren() {
		return children;
	}
	
	// Retrieve the set of applicable actions
	public ArrayList<Action> getActions() {
		return actions;
	}

	// Check if the current state is the goal state
	public boolean isGoal(int z) {
		return x == z || y == z || x + y == z;
	}
	
	// Check if two states are the same
	public boolean isEqual(State state) {
		return x == state.getX() && y == state.getY();
	}
	
	// Check if the given sequence contains the current state
	public boolean belongsTo(ArrayList<State> A) {
		// Traverse the sequence
		for (State S : A) {
			// Return true if a match is found
			if (x == S.getX() && y == S.getY()) {
				return true;
			}
		}
		// False otherwise
		return false;
	}
	
	// Return a formatted output representing the current amount of water in the two buckets
	public String toString() {
		return String.format("(%d,%d)", x, y);
	}
	
}