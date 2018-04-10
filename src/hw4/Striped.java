package hw4;

public class Striped extends Candy{

	public boolean isVertical;

	public Striped(Board board, String color, int row, int col, boolean isVertical) {
		super(board, color, row, col,"horizontal");
		this.isVertical = isVertical;
		if(isVertical) this.name = "vertical";
	}

	@Override
	public void crush() {
		if(isVertical) this.board.crushCol(this);
		else this.board.crushRow(this);
	}

	@Override
	public boolean visit(Regular regular) {
		return false;		
	}

	@Override
	public boolean visit(Striped striped) {
		this.board.crushRowAndCol(striped);
		return true;
	}

	@Override
	public boolean visit(Wrapped wrapped) {
		this.board.crush3xRowAndCol(wrapped);
		return true;
	}

	@Override
	public boolean visit(Chocolate chocolate) {
		this.board.changeToStripes(this);
		return true;
	}

	@Override
	public boolean accept(Candy candy) {
		return candy.visit(this);
	}

}
