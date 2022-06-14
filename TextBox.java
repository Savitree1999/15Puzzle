package z15game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextBox {
	
	int x = 0;
	int y = 0;
	int w = 120;
	int h = 63;
	int frontSize = 0;
	String text = "";
	String number = "";
	Color c1 = new Color(255,204,204);
	Color c2 = new Color(255,255,255);
	
	TextBox(int x,int y, String text, String number, Color c, int frontSize){
		this.x = x; 
		this.y = y;
		this.text = text;
		this.number = number;
		this.c1 = c;
		this.frontSize = frontSize;
	}
	
	void draw(Graphics g) {
		
		g.setColor(c1);
		g.fillRoundRect(x, y, w, h, 10, 10);
		g.setColor(c2);
		g.setFont(new Font("SansSerif", Font.BOLD, frontSize));
		
		g.drawString(text, x+12,y+25);
		g.drawString(number, x+12,y+55);
	}
	

}
