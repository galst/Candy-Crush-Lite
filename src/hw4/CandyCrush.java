package hw4;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class CandyCrush extends JFrame implements ActionListener{

	public static void main(String args[]){
		CandyCrush Game = new CandyCrush();
	}
	
	CustomButton restart;
	CustomButton score;
	CustomButton highScore;
	CustomButton leaderBoard;
	CustomButton moves;
	CustomButton instructions;
	
	FileManager file;
	String[][] leaders;

	PlayingBoard playBoard;
	
	JTable table;
	
	public CandyCrush(){
		//set the attributes for the main window
		super("Candy Crush");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(894, 800);
		
		//background image
		JLabel background = new JLabel(new ImageIcon("pictures/background.jpg"));
		background.setBounds(0, 0, 894, 894);
		
		//leaderboard initializtion
		file = new FileManager();
		this.leaders = new String[11][3];
		file.readFile(leaders);
		String[] columnNames = {"Number","Name", "Score"};
		table = new JTable(this.leaders, columnNames);
		
		//create the playing board
		playBoard = new PlayingBoard();
		playBoard.setLocation(385, 285);
		
		
		//buttons
		restart = new CustomButton("Restart",125,390,200,70,true);
		restart.addActionListener(this);
		score = new CustomButton("Score: "+playBoard.board.score,125,520,200,70,true);
		score.addActionListener(this);
		moves = new CustomButton("Moves: "+playBoard.board.movesLeft,125,650,200,70,true);
		moves.addActionListener(this);
		highScore = new CustomButton("High Score: " + leaders[0][2],380,180,250,70,true);
		highScore.addActionListener(this);
		leaderBoard = new CustomButton("Leaderboard",640,180,240,70,true);
		leaderBoard.addActionListener(this);
		instructions = new CustomButton("To play, just click a candy and select one of it's neighbours!",10,10,800,40,false);
		
		
		
		//add components to the content pane
		getContentPane().add(playBoard,0);
		getContentPane().add(restart,1);
		getContentPane().add(score,2);
		getContentPane().add(highScore,3);
		getContentPane().add(leaderBoard,4);
		getContentPane().add(moves,5);
		getContentPane().add(instructions,6);
		getContentPane().add(background,7);
				
		this.playBoard.candyCrush = this;
				
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==leaderBoard){
			showLeaderboard();
		}	
		if(e.getSource()==restart){
			restart();
		}
	}
	
	private void showLeaderboard(){
		file.readFile(leaders);
		String[] columnNames = {"Number","Name", "Score"};
		table = new JTable(this.leaders, columnNames);
        table.setFillsViewportHeight(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setBackground(new Color (255,111,157,230));
		table.setFont(new Font("Candice",Font.ITALIC, 16));
		table.setForeground(Color.white);
        table.setGridColor(Color.white);
		Border border = BorderFactory.createLineBorder(Color.white, 2);
        table.setBorder(border);
   		Leaderboard lb = new Leaderboard(table);
	}

	public void redraw(){
		updateScore();
		if(playBoard.board.gameOver()){
			JOptionPane.showMessageDialog(null, "Sorry! Game Over, but you can try again");
			if(this.leaders[10][0] == null || playBoard.board.score >= Integer.parseInt((String) this.leaders[10][0])) insertScore();
			restart();
		}
	}
	
	private void restart(){
		if(this.playBoard.board.score ==0 && this.playBoard.board.movesLeft ==20){
			JOptionPane.showMessageDialog(null, "Game is already reseted, click OK to keep playing!");
		}else{	
			if (this.leaders[10][0] == null || playBoard.board.score >= Integer.parseInt((String) this.leaders[10][0])) insertScore();
			this.playBoard.board.newGame();
			this.playBoard.redraw(false);
		}
	}
	
	private void updateScore(){
		score.setText("Score: " + playBoard.board.score);
		file.readFile(leaders);
		if(this.leaders[0][2] != null){
			if(Integer.parseInt(this.leaders[0][2]) < playBoard.board.score)
			highScore.setText("High Score: " + playBoard.board.score);
		}	
		moves.setText("Moves: "+playBoard.board.movesLeft);
		score.repaint();
		highScore.repaint();
		moves.repaint();
	}
	
	public void insertScore(){
		String name = JOptionPane.showInputDialog("You got a new High Score! You are in the top 10!","Enter name here");
		if(name != null && name != "Enter name here"){
			String write = name + "," + playBoard.board.score+"";
			try{
				this.file.writeToFile(write);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Could not insert high score");
			}
		}
	}
}
