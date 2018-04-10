package hw4;

public abstract class Candy implements Visitor, Visited {

	String color;
	Board board;
	int row;
	int col;
	String name;
	
	public Candy (Board board, String color, int row, int col, String name){
		this.board = board;
		this.color = color;
		this.row = row;
		this.col = col;
		this.name = name;
	}
	
	
	public boolean combine(Candy candy){
		return candy.accept(this);
	}
	
	public abstract void crush();
}
