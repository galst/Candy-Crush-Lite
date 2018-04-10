package hw4;

public class Tester {
	Board board;
	
	public Tester (Board board){
		this.board = board;
	}
	
	
	
	public void loadLShapeToBoard(){
		board.board[0][0] = new Regular (board, "blue", 0,0);
		board.board[0][1] = new Regular (board, "blue", 0,1);
		board.board[0][3] = new Regular (board, "blue", 0,3);
		board.board[1][2] = new Regular (board, "blue", 1,2);
		board.board[2][2] = new Regular (board, "blue", 2,2);
	}
	
	public void loadTwo4s(){
		board.board[0][0] = new Regular (board, "blue", 0,0);
		board.board[0][1] = new Regular (board, "blue", 0,1);
		board.board[0][2] = new Regular (board, "blue", 0,2);
		board.board[0][3] = new Regular (board, "blue", 0,3);
		board.board[0][4] = new Regular (board, "blue", 0,4);
	}

	
}
