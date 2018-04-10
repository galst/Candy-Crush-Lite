package hw4;

public class Move {

	public Candy source;
	public Candy exchange;
	public int crushValue;
	public Board board;
	public PlayingBoard playBoard;
	public boolean validMove;
	
	
	public Move(Candy source, Candy exchange, Board board, PlayingBoard playboard){
		this.source = source;
		this.exchange = exchange;
		this.crushValue = 10;
		this.board = board;
		this.playBoard = playBoard;
		validMove = false;
	}
		
	public void makeMove(){
		validMove = source.combine(exchange);
		if (validMove){
			drop();
			printBoard();
			crushValue = crushValue * 2;
		}

		while(findShapesAndCrush()){
			drop();
			printBoard();
			crushValue = crushValue * 2;
			validMove = true;
		}
		System.out.println("afterMove: ");
		this.printBoard();
	}
	
	private void drop(){
		for (int i = 8; i >= 0; i--){
			for (int j = 8; j >= 0; j--){
				if(board.board[i][j] == null){
					int rowToSearch = i;
					while (rowToSearch >= 0 && board.board[rowToSearch][j] == null) rowToSearch--;
					if (rowToSearch != -1) {
						int rowToInsert = i;
						while (rowToSearch>=0){
							if (board.board[rowToSearch][j]!=null){
								board.board[rowToInsert][j] = board.board[rowToSearch][j];
								board.board[rowToSearch][j] = null;
								board.board[rowToInsert][j].row = rowToInsert;
								rowToInsert--;
							}
							rowToSearch--;
						}
					}
				}
			}
		}
		fill();
	}
	
	private void fill(){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if(board.board[i][j] == null){
					Candy newCandy = new Regular(this.board,this.board.randomColor(),i,j);
					board.board[i][j] = newCandy;
				}
			}
		}
	}
	
	
	private boolean findShapesAndCrush(){
		boolean shapesExist = false;
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 7; j++){
				Candy[] triplet = {board.board[i][j], board.board[i][j+1], board.board[i][j+2]};
				ShapeTriplet shape = new ShapeTriplet(board, triplet, this);
				if (shape.isValidTriplet){
					shapesExist = true;
					shape.expendAndCrush();
				}
			}
		}
		for (int i = 0; i < 7; i++){
			for (int j = 0; j < 9; j++){
				Candy[] triplet = {board.board[i][j], board.board[i+1][j], board.board[i+2][j]};
				ShapeTriplet shape = new ShapeTriplet(board, triplet, this);
				if (shape.isValidTriplet){
					shapesExist = true;
					shape.expendAndCrush();
				}
			}
		}
		return shapesExist;
	}
	private void printBoard(){
		for (int row=0; row<9; row++){
			System.out.print("row "+row + "\t");
			for (int col=0; col<9; col++){
				if (board.board[row][col] != null && (board.board[row][col].name)!= "")
					System.out.print((board.board[row][col].name) + "\t");
				else if (board.board[row][col] != null)
					System.out.print((board.board[row][col].color) + "\t");
				else
					System.out.print((board.board[row][col]) + "\t");
			}
			System.out.println();
		}
	}
	
}
