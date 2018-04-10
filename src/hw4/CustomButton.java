package hw4;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders.ButtonBorder;

public class CustomButton extends JButton {
	
	int row;
	int col;
	
	public CustomButton(String text, int x, int y, int width, int height, boolean color){
				
		super(text);
		try{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("CANDY.ttf")));
		}catch (Exception e){
		}
		
		this.setBounds(x, y, width, height);
		this.setForeground(Color.white);
		this.setOpaque(true);
		Color color1;
		
		if (color){
		color1 = new Color(255,111,157);
		this.setBackground(color1);
		}else{
		this.setBackground(Color.GRAY);
		}
		
		this.setFont(new Font("Candice",Font.ITALIC, 26));
		this.setBorder(BorderFactory.createLineBorder(getForeground(), 5));
		this.setRolloverEnabled(false);
		this.setFocusable(false);		
		this.setSelected(true);
	}

}
