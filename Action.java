// Yi Guo
// CS 3368 Project 01
// 03/01/2013

// Represents an action that is applied to a state
public class Action {
	
	// Data field
	private State child;	// Represents the state where the current action leads to
	private State parent;	// Represents the state where the current action is applied to
	private String action;	// Represents current action's name
	
	// Constructor that creates a default action
	public Action() {
		action = null;
		child = parent = null;
	}
	
	// Second constructor that creates a specified action
	public Action(State parent, State child, String action) {
		this.child = child;
		this.parent = parent;
		this.action = action;
	}
	
	// Set the child state
	public void setChild(State child) {
		this.child = child;
	}
	
	// Set the parent state
	public void setParent(State parent) {
		this.parent = parent;
	}
	
	// Set the action name
	public void setAction(String action) {
		this.action = action;
	}
	
	// Retrieve the child state
	public State getChild() {
		return child;
	}
	
	// Retrieve the parent state
	public State getParent() {
		return parent;
	}
	
	// Retrieve the action name
	public String getAction() {
		return action;
	}

	// Return a formatted output representing the whole action process
	public String toString() {
		return String.format("%s\t-->   %s\t%s", parent.toString(), child.toString(), action);
	}
	
}