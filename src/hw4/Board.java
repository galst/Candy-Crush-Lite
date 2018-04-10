package hw4;


public class Board {

	public Candy[][] board;
	public PlayingBoard playBoard;
	int movesLeft = 20;
	int score;
	private Move move;
	
	
	public Board(PlayingBoard playBoard){
		this.playBoard = playBoard;
		this.board = new Candy[9][9];
		newGame();
	}
	
	public void newGame(){
		this.score = 0;
		this.movesLeft = 20;
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				board[i][j] = null;
			}
		}		
		
		//Tester tester = new Tester(this);
		//tester.loadLShapeToBoard();
		//tester.loadTwo4s();
		
		
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if (board[i][j] == null) board[i][j] =  new Regular(this,randomColor(),i,j);
			}
		}
		newGameScan();
	}
	
	public void insert (Candy candy){
		board[candy.row][candy.col] = candy;
	}
	
	public void newMove(Candy source, Candy exchange){
		switchCandy(source, exchange);
		playBoard.redraw(false);
		move = new Move(source, exchange,this,playBoard);
		move.makeMove();
		playBoard.redraw(false);
		if (!move.validMove) switchCandy(source, exchange);
		else movesLeft--;
		playBoard.redraw(false);
		move = null;
	}
	
	private void newGameScan(){
		move = new Move(board[0][0], board[0][1],this,playBoard);
		move.makeMove();
		this.score = 0;
		//if (move.validMove) playBoard.redraw(false);
		move = null;
	}
	
	public String randomColor(){
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
		return second;
	}
	
	public void crushSingle(Candy candy){
		board[candy.row][candy.col] = null;
		score += move.crushValue;
	}
	
	public void crushRow(Candy candy){
		int row = candy.row;
		crushSingle(candy);
		for (int i = 0; i < 9; i++){
			if (board[row][i] != null) board[row][i].crush();
		}
	}
	
	public void crushCol(Candy candy){
		int col = candy.col;
		crushSingle(candy);
		for (int i = 0; i < 9 ;i++){
			if (board[i][col] != null) board[i][col].crush();;
		}
	}
	
	public void crush3xRowAndCol(Candy candy){
		int row = candy.row;
		int col = candy.col;
		crushSingle(candy);
		for (int i = row-1; i <= row+1 && i < 9; i++){
			if (i >= 0){
				for (int j = 0; j < 9; j++){
					if (board[i][j] != null) board[i][j].crush();
				}
			}	
		}
		for (int i = col-1; i <= col+1 && i < 9; i++){
			if(i >= 0){
				for (int j = 0; j < 9; j++){
					if (board[j][i] != null) board[j][i].crush();
				}
			}	
		}
	}
	
	public void changeToStripes(Candy candy){
		String color = candy.color;
		crushSingle(candy);
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if(board[i][j] != null && board[i][j].color == color) board[i][j] = new Striped(this,color,i,j, ((Striped)candy).isVertical);
			}
		}
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if(board[i][j] != null && board[i][j].color == color && board[i][j] instanceof Striped) board[i][j].crush();
			}
		}
	}
	
	public void crush3x3(Candy candy){
		int row = candy.row;
		int col = candy.col;
		crushSingle(candy);
		for (int i = row-1; i <= row+1 && i< 9; i++){
			if(i >= 0){
				for (int j = col-1  ; i<= col+1 && j < 9; j++){
					if (board[i][j] != null) board[i][j].crush();
				}
			}
		}
	}
	
	public void crush5x5(Candy candy){
		int row = candy.row;
		int col = candy.col;
		crushSingle(candy);
		for (int i = row-2; i <= row+2 && i< 9; i++){
			if(i >= 0){
				for (int j = col-2  ; i<= col+2 && j < 9; j++){
					if (board[i][j] != null) board[i][j].crush();
				}
			}	
		}

	}
	
	public void crushColor(String color){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if(board[i][j] != null && board[i][j].color == color) board[i][j].crush();
			}
		}
	}
	
	public void crushTwoColors(String one, String two){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if(board[i][j] != null && (board[i][j].color == one || board[i][j].color == two)) board[i][j].crush();
			}
		}
	}
	
	public void crushRowAndCol(Candy candy){
		int row = candy.row;
		int col = candy.col;
		crushSingle(candy);
		for (int i = 0; i < 9; i++){
			if (board[row][i] != null && i != col) board[row][i].crush();
		}
		for (int i = 0; i < 9; i++){
			if (board[i][col] != null && i != row) board[i][col].crush();
		}

	}
	
	public void crushAll(){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if(board[i][j] != null) board[i][j].crush();
			}
		}
	}
	
	public boolean gameOver(){
		return this.movesLeft == 0;
	}
	
	public void switchCandy (Candy c1, Candy c2){
		int c1row = c1.row;
		int c1col = c1.col;
		int c2row = c2.row;
		int c2col = c2.col;
		board[c1row][c1col] = c2;
		board[c2row][c2col] = c1;
		c2.row = c1row; c2.col = c1col;
		c1.row = c2row; c1.col = c2col;
	}
	
}
