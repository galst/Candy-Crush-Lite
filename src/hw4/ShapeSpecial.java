package hw4;

public class ShapeSpecial {

	private Candy base;
	private Candy[] rest;
	private Board board;
	private String color;
	private String type;
	
	public static final String SPECIAL_4_SHAPE = "4shape";
	public static final String SPECIAL_5_SHAPE = "5shape";
	public static final String SPECIAL_L_OR_T_SHAPE = "LTshape";
	
	public ShapeSpecial (Board board, Candy base, Candy[] rest, String type){
		this.board = board;
		this.base = base;
		this.rest = rest;
		this.color = base.color;
		if (type != SPECIAL_4_SHAPE & type != SPECIAL_5_SHAPE & type != SPECIAL_L_OR_T_SHAPE) throw new IllegalArgumentException ("wrong argument. type must be one of the constants");
		this.type = type;
	}
	
	public Candy crushAndCreateTheNewSpecialCandy(){
		for (int i=0; i<rest.length; i++){
			rest[i].crush();
		}
		base.crush();
		return createSpecialCandy();
	}
	
	private Candy createSpecialCandy(){
		Candy returnCandy = null;
		switch (type) {
			case SPECIAL_4_SHAPE:
				boolean isVertical;
				if (base.row==rest[0].row){
					isVertical = true;
				} else {
					isVertical = false;
				}
				Striped striped = new Striped (board, color, base.row, base.col, isVertical);
				returnCandy = striped;
				break;
			case SPECIAL_5_SHAPE:
				Chocolate chocolate = new Chocolate (board, "brown", base.row, base.col);
				returnCandy = chocolate;
				break;
			case SPECIAL_L_OR_T_SHAPE:
				Wrapped wrapped = new Wrapped(board, color, base.row, base.col);
				returnCandy = wrapped;
				break;
			default:
				break;
		}
		
		return returnCandy;
	}
	
}
