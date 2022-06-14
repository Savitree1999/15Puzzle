package z15game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BoxNumber {
	
	int x = 0;
	int y = 0;
	int w = 80;
	int h = 80;
	int number = 0;
	Color c1 = new Color(255,204,204);
	Color c2 = new Color(255,255,255);
	
	BoxNumber(int x,int y, int number, Color c){
		this.x = x; 
		this.y = y;
		this.number = number;
		this.c1 = c;
	}
	 
	void draw(Graphics g) {
		
		g.setColor(c1);
		g.fillRoundRect(x, y, w, h, 10, 10);
		g.setColor(c2);
		g.drawRoundRect(x, y, w, h, 10, 10);
		g.setFont(new Font("SansSerif", Font.BOLD, 35));
		
		int x1 = x+28;
		if(number<10) {
			x1 = x+30;
		}else {
			x1 = x+20;
		}
		g.drawString((String.valueOf(number)), x1,y+54);
		
	}
	
	void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
