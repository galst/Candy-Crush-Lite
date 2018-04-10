package hw4;

public class Chocolate extends Candy {

	
	public Chocolate(Board board, String color, int row, int col) {
		super(board, color, row, col,"");
		// TODO Auto-generated constructor stub
	}

	public void crush() {
		this.board.crushColor(this.board.randomColor());
		board.crushSingle(this);
	}

	@Override
	public boolean visit(Regular regular) {
		this.board.crushColor(regular.color);
		board.crushSingle(this);
		return true;
	}

	@Override
	public boolean visit(Striped striped) {
		this.board.changeToStripes(striped);
		board.crushSingle(this);
		return true;
	}

	@Override
	public boolean visit(Wrapped wrapped) {
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
		this.board.crushTwoColors(wrapped.color, second);
		board.crushSingle(this);
		return true;
	}

	@Override
	public boolean visit(Chocolate chocolate) {
		this.board.crushAll();
		return true;
	}

	@Override
	public boolean accept(Candy candy) {
		return candy.visit(this);
	}

}
