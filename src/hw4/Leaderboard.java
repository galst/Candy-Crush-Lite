package hw4;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;


public class Leaderboard extends JFrame{
	
	
	public Leaderboard(JTable table){
		
		super("LeaderBoard");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		JTableHeader header = table.getTableHeader();	
		getContentPane().add(header,BorderLayout.NORTH);
		getContentPane().add(table,BorderLayout.CENTER);
		
		this.setSize(350, 200);
		setResizable(true);
		setVisible(true);

	}

}
