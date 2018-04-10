package hw4;
import javax.swing.*;

public class GridButton extends JButton {

	int row;
	int col;
	
	public GridButton(ImageIcon icon, int row, int col){
		super(icon);
		this.row = row;
		this.col = col;
	}
}
