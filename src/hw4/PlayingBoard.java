package hw4;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

public class PlayingBoard extends JPanel implements ActionListener{

	Map <String, ImageIcon> images;
	Board board;
	int counter;
	Candy source;
	CandyCrush candyCrush;

	
	public PlayingBoard(){	
		super();
		//link to logic
		board = new Board(this);
		// Set the attributes for this panel.
		this.setBackground(new Color(0,0,0,0));
		this.setSize(480, 480);
		GridLayout layout = new GridLayout(9,9,2,2);		
		this.setLayout(layout);
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY,5);
		this.setBorder(border);
		this.setBackground(new Color (139,137,137,180));
		this.board = board;
		loadTheme();
		redraw(true);
		this.setVisible(true);
		this.counter = 0;
	}
	
	private void loadTheme (){	
		String foldername = "pictures/";
		
		images = new HashMap<String, ImageIcon>();
		images.put("blue", new ImageIcon(foldername+"blue.png"));
		images.put("yellow", new ImageIcon(foldername+"yellow.png"));
		images.put("orange", new ImageIcon(foldername+"orange.png"));
		images.put("green", new ImageIcon(foldername+"green.png"));
		images.put("red", new ImageIcon(foldername+"red.png"));
		images.put("purple", new ImageIcon(foldername+"purple.png"));
		images.put("horizontalblue", new ImageIcon(foldername+"horizontalblue.png"));
		images.put("horizontalred", new ImageIcon(foldername+"horizontalred.png"));
		images.put("horizontalgreen", new ImageIcon(foldername+"horizontalgreen.png"));
		images.put("horizontalpurple", new ImageIcon(foldername+"horizontalpurple.png"));
		images.put("horizontalorange", new ImageIcon(foldername+"horizontalorange.png"));
		images.put("horizontalyellow", new ImageIcon(foldername+"horizontalyellow.png"));
		images.put("verticalblue", new ImageIcon(foldername+"verticalblue.png"));
		images.put("verticalred", new ImageIcon(foldername+"verticalred.png"));
		images.put("verticalgreen", new ImageIcon(foldername+"verticalgreen.png"));
		images.put("verticalpurple", new ImageIcon(foldername+"verticalpurple.png"));
		images.put("verticalorange", new ImageIcon(foldername+"verticalorange.png"));
		images.put("verticalyellow", new ImageIcon(foldername+"verticalyellow.png"));
		images.put("wrappedblue", new ImageIcon(foldername+"wrappedblue.png"));
		images.put("wrappedred", new ImageIcon(foldername+"wrappedred.png"));
		images.put("wrappedgreen", new ImageIcon(foldername+"wrappedgreen.png"));
		images.put("wrappedpurple", new ImageIcon(foldername+"wrappedpurple.png"));
		images.put("wrappedorange", new ImageIcon(foldername+"wrappedorange.png"));
		images.put("wrappedyellow", new ImageIcon(foldername+"wrappedyellow.png"));
		images.put("brown", new ImageIcon(foldername+"chocolate.png"));
	}
	
	public void redraw(boolean firstTime){
		if (!firstTime) candyCrush.redraw();
		this.removeAll();
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				Candy name = board.board[i][j];
				ImageIcon icon = null;
				if (name != null) icon = images.get(name.name+name.color);
				GridButton t = new GridButton(icon,i,j);
				t.addActionListener(this);
				Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
				t.setBorder(border);
				t.setSelected(isEnabled());
				this.add(t);
			}
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		GridButton temp = (GridButton)e.getSource();
		int row = temp.row;
		int col = temp.col;
		Border border = BorderFactory.createLineBorder(Color.RED, 3);
		boolean isZero = false;
		
		if(this.counter == 1 && isNeighbour(row,col,source.row,source.col)){
			this.counter = 0;
			isZero = true;
			temp.setBorder(border);
			Candy exchanger = board.board[row][col];
			board.newMove(source,exchanger);
		}	
		if (this.counter == 0 && !isZero){
			temp.setBorder(border);
			this.source = board.board[row][col];
			this.counter = 1;
		}
		isZero = false;
	}	
	
	private boolean isNeighbour(int row, int col, int otherRow, int otherCol){
		return (row == otherRow+1 && col == otherCol)|| (row == otherRow-1 && col == otherCol)|| (col == otherCol-1 && row == otherRow)|| (row == otherRow && col == otherCol+1);
	}
}
