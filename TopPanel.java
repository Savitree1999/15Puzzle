package z15game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class TopPanel extends JPanel  implements MouseListener {
	
	public static int moves = 0;
    public static int bestScore = 10000;
    public static boolean start = false;
    
    static TextBox bestMoveText = new TextBox(10, 10, "BEST MOVE", "", new Color(255,204,203), 15);
	TextBox moveText = new TextBox(140, 10, "MOVES", String.valueOf(moves), new Color(231,231,231), 23);
	TextBox btn = new TextBox(270, 10, "RESTART", "", new Color(188,252,140), 20);
	
	// Constructor 
	// Setting Panel
	TopPanel() {
		this.setSize(420,100);
		this.setLocation(0, 0);
		this.setBackground(new Color(255,255,255));
		this.setLayout(null);
		
		MyFile myfile = new MyFile();
		String data = myfile.readFile();
		setScore(data);
		addMouseListener(this);
	}
	
	public void setScore(String data) {
		
		this.bestScore = Integer.parseInt(data);
		if(bestScore<10000) {
		    bestMoveText.number = data;
			
		} else {
			bestMoveText.number = "No Score";
		}
	}
	
	
	public void play() {
		
		repaint();
		this.moveText.number = String.valueOf(moves);
	}
	
	//Draw text boxes
	public void paint(Graphics g) {
		super.paint(g);
		moveText.draw(g);
		bestMoveText.draw(g);
		btn.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// Restart button
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int y = e.getY();
		int x = e.getX();
		
		if (x>270 & x<390 & y>10 & y<73 ) {
			start = true;
			System.out.print("work");
			MainPanel.reStart();
			moves = 0;
			start = false;
			
		}
		
		if (x>10 & x<130 & y>10 & y<73 ) {
			MyFile myfile = new MyFile();
			myfile.writeFile();
			setScore("10000");
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
