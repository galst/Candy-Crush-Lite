package hw4;

public class ShapeTriplet {

	Board board;
	Move move;
	Candy[] triplet;
	boolean isHorizontalTriplet;
	boolean isValidTriplet;
	
	public ShapeTriplet (Board board, Candy[] triplet, Move move){
		//check if the triplet is indeed a triplet and set the isHorizontalTriplet field and isValidTriplet
		tripletIsValidAndSetHorizon(triplet);
		this.board = board;
		this.triplet = triplet;
		this.move = move;
	}
	
	public boolean expendAndCrush(){
		//CASE - not a valid triplet
		if (!isValidTriplet) return false;
		//CASE - valid triplet
		ShapeSpecial expended = expend();
		//CASE - can be expended
		if (expended!=null) {
			Candy newSpecialCandy = expended.crushAndCreateTheNewSpecialCandy();
			board.insert(newSpecialCandy);
		}
		//CASE - cant be expended
		else crush();
		return true;
	}
	
	// return special candy if can be expended or return null if not
	private ShapeSpecial expend (){
		//try to build a 5-shape
		if (isHorizontalTriplet & createAtripletRight(triplet[2]) != null && createAtripletRight(triplet[2]).isValidTriplet){
			Candy base = triplet[2];
			Candy[] otherTriplet = createAtripletRight(triplet[2]).triplet;
			Candy[] rest = {triplet[0], triplet[1], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_5_SHAPE);
		}
		if (!isHorizontalTriplet & createAtripletDown(triplet[2]) != null && createAtripletDown(triplet[2]).isValidTriplet){
			Candy base = triplet[2];
			Candy[] otherTriplet = createAtripletDown(triplet[2]).triplet;
			Candy[] rest = {triplet[0], triplet[1], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_5_SHAPE);
		}
		
		// try to build L-Shape
		if (isHorizontalTriplet & createAtripletDown(triplet[2]) != null && createAtripletDown(triplet[2]).isValidTriplet){
			Candy base = triplet[2];
			Candy[] otherTriplet = createAtripletDown(triplet[2]).triplet;
			Candy[] rest = {triplet[0], triplet[1], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		if (isHorizontalTriplet & createAtripletDown(triplet[0]) != null && createAtripletDown(triplet[0]).isValidTriplet){
			Candy base = triplet[0];
			Candy[] otherTriplet = createAtripletDown(triplet[0]).triplet;
			Candy[] rest = {triplet[2], triplet[1], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		if (isHorizontalTriplet & createAtripletUp(triplet[2]) != null && createAtripletUp(triplet[2]).isValidTriplet){
			Candy base = triplet[2];
			Candy[] otherTriplet = createAtripletUp(triplet[2]).triplet;
			Candy[] rest = {triplet[0], triplet[1], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		if (isHorizontalTriplet & createAtripletUp(triplet[0]) != null && createAtripletUp(triplet[0]).isValidTriplet){
			Candy base = triplet[0];
			Candy[] otherTriplet = createAtripletUp(triplet[0]).triplet;
			Candy[] rest = {triplet[2], triplet[1], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		
		//try to build T-Shape
		if (isHorizontalTriplet & createAtripletUp(triplet[1]) != null && createAtripletUp(triplet[1]).isValidTriplet){
			Candy base = triplet[1];
			Candy[] otherTriplet = createAtripletUp(triplet[1]).triplet;
			Candy[] rest = {triplet[0], triplet[2], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		if (isHorizontalTriplet & createAtripletDown(triplet[1]) != null && createAtripletDown(triplet[1]).isValidTriplet){
			Candy base = triplet[1];
			Candy[] otherTriplet = createAtripletDown(triplet[1]).triplet;
			Candy[] rest = {triplet[0], triplet[2], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		if (!isHorizontalTriplet & createAtripletRight(triplet[1]) != null && createAtripletRight(triplet[1]).isValidTriplet){
			Candy base = triplet[1];
			Candy[] otherTriplet = createAtripletRight(triplet[1]).triplet;
			Candy[] rest = {triplet[0], triplet[2], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		if (!isHorizontalTriplet & createAtripletLeft(triplet[1]) != null && createAtripletLeft(triplet[1]).isValidTriplet){
			Candy base = triplet[1];
			Candy[] otherTriplet = createAtripletLeft(triplet[1]).triplet;
			Candy[] rest = {triplet[0], triplet[2], otherTriplet[1], otherTriplet[2]};
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_L_OR_T_SHAPE);
		}	
		
		//try to build a 4-shape
		if (isHorizontalTriplet & createAtripletRight(triplet[1]) != null && createAtripletRight(triplet[1]).isValidTriplet){
			Candy base = null;
			Candy[] otherTriplet = createAtripletRight(triplet[1]).triplet;
			Candy[] rest = {triplet[0], null, otherTriplet[2]};
			if (move.source == triplet[1] | move.exchange == triplet[1]) {
				base = triplet[1];
				rest[1] = triplet[2];
			} else {
				base = triplet[2];
				rest[1] = triplet[1];
			}
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_4_SHAPE);
		}
		if (!isHorizontalTriplet & createAtripletDown(triplet[1]) != null && createAtripletDown(triplet[1]).isValidTriplet){
			Candy base = null;
			Candy[] otherTriplet = createAtripletDown(triplet[1]).triplet;
			Candy[] rest = {triplet[0], null, otherTriplet[2]};
			if (move.source == triplet[1] | move.exchange == triplet[1]) {
				base = triplet[1];
				rest[1] = triplet[2];
			} else {
				base = triplet[2];
				rest[1] = triplet[1];
			}
			return new ShapeSpecial(board, base, rest, ShapeSpecial.SPECIAL_4_SHAPE);
		}
		
		
		
		return null;
	}
	
	private void crush() {
		for (int i = 0; i<triplet.length; i++)
			triplet[i].crush();
	}
	
	//also sets horizon and valid triplet
	private void tripletIsValidAndSetHorizon (Candy[] triplet){
		//check for nulls
		if (triplet[0] == null | triplet[1] == null | triplet[2] == null) {
			this.isValidTriplet = false;
			return;
		} else {
			this.isValidTriplet = true;
		}
		//check same color
		if (triplet[0].color != triplet[1].color | triplet[1].color != triplet[2].color){
			this.isValidTriplet = false;
			return;
		} else {
			this.isValidTriplet = true;
		}
		//check neighbors and set isHorizontalTriplet
		if (triplet[0].row == triplet[1].row & triplet[1].row == triplet[2].row) {
			this.isHorizontalTriplet = true;
		} else {
			this.isHorizontalTriplet = false;
		}
	}
	
	private ShapeTriplet createAtripletRight (Candy candy){
		int baseRow = candy.row;
		int baseCol = candy.col;
		if (candy.col > 6) return null;
		Candy[] triplet = new Candy[3];
		triplet[0] = candy;
		triplet[1] = board.board[baseRow][baseCol+1];
		triplet[2] = board.board[baseRow][baseCol+2];
		return new ShapeTriplet(board, triplet, move);
	}
	
	private ShapeTriplet createAtripletDown (Candy candy){
		int baseRow = candy.row;
		int baseCol = candy.col;
		if (candy.row > 6) return null;
		Candy[] triplet = new Candy[3];
		triplet[0] = candy;
		triplet[1] = board.board[baseRow+1][baseCol];
		triplet[2] = board.board[baseRow+2][baseCol];
		return new ShapeTriplet(board, triplet, move);
	}
	
	private ShapeTriplet createAtripletLeft (Candy candy){
		int baseRow = candy.row;
		int baseCol = candy.col;
		if (candy.col < 2) return null;
		Candy[] triplet = new Candy[3];
		triplet[0] = candy;
		triplet[1] = board.board[baseRow][baseCol-1];
		triplet[2] = board.board[baseRow][baseCol-2];
		return new ShapeTriplet(board, triplet, move);
	}
	
	private ShapeTriplet createAtripletUp (Candy candy){
		int baseRow = candy.row;
		int baseCol = candy.col;
		if (candy.row < 2) return null;
		Candy[] triplet = new Candy[3];
		triplet[0] = candy;
		triplet[1] = board.board[baseRow-1][baseCol];
		triplet[2] = board.board[baseRow-2][baseCol];
		return new ShapeTriplet(board, triplet, move);
	}
	

}
