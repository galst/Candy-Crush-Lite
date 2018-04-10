package hw4;

public interface Visitor {

	public boolean visit (Regular regular);
	public boolean visit (Striped striped);
	public boolean visit (Wrapped wrapped);
	public boolean visit (Chocolate chocolate);
	
}
