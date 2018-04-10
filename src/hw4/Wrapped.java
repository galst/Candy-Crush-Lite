package hw4;

public class Wrapped extends Candy{

	
	public Wrapped(Board board, String color, int row, int col) {
		super(board, color, row, col,"wrapped");
	}

	@Override
	public void crush() {
		this.board.crush3x3(this);	
	}

	@Override
	public boolean visit(Regular regular) {
		return false;		
	}

	@Override
	public boolean visit(Striped striped) {
		this.board.crush3xRowAndCol(striped);	
		return true;
	}

	@Override
	public boolean visit(Wrapped wrapped) {
		this.board.crush5x5(wrapped);
		return true;
	}

	@Override
	public boolean visit(Chocolate chocolate) {
		int two = (int)(Math.random()*6);
		two++;
		String second = "";
		switch(two){
			case 1: second = "blue";
			break;
			case 2: second = "green";
			break;
			case 3: second = "yellow";
			break;
			case 4: second = "red";
			break;
			case 5: second = "orange";
			break;
			case 6: second = "purple";
			break;
		}
		this.board.crushTwoColors(this.color, second);
		return true;
	}

	@Override
	public boolean accept(Candy candy) {
		return candy.visit(this);		
	}

}
