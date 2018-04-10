package hw4;

public class Regular extends Candy{
	
	
	public Regular(Board board, String color, int row, int col){
		super(board,color,row,col,"");
	}

	@Override
	public void crush() {
		this.board.crushSingle(this);
	}

	@Override
	public boolean visit(Regular regular) {
		return false;
	}

	@Override
	public boolean visit(Striped striped) {
		return false;		
	}

	@Override
	public boolean visit(Wrapped wrapped) {
		return false;		
	}

	@Override
	public boolean visit(Chocolate chocolate) {
		this.board.crushColor(this.color);
		return true;
	}

	@Override
	public boolean accept(Candy candy) {
		return candy.visit(this);
	}

}
