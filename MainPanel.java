package z15game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener, MouseListener {
	
	static ArrayList<BoxNumber> boxs = new ArrayList<>();     // Array
	static int emptyBoxX = 240;
	static int emptyBoxY = 240;
	static int emptyIndex = 15;
	static int moveIndex = 0;
	static int randomMove = 0;
	static int levelMove = 100;
	static boolean stop = true;
	
	// Constructor 
	// Setting Panel 
	MainPanel() {
		this.setSize(320,320);
		this.setLocation(40, 100);
		
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(this);
		
		startGame();
	}
	
	
	public void play() {
		
		repaint();
		boolean ch = check();
		
		if(ch & stop & TopPanel.moves!=0) {
			System.out.print("Win!!!!!!!!!!!!!!!!!!!!!!");
			
			if (TopPanel.moves<TopPanel.bestScore) {
				TopPanel.bestMoveText.number = String.valueOf(TopPanel.moves);
				TopPanel.bestScore=TopPanel.moves;
				MyFile myfile = new MyFile();
				String data = String.valueOf(TopPanel.moves);
				myfile.writeFile(data);
			}
			
			JFrame frame = new JFrame("Win");
			frame.getContentPane().setBackground(new Color(255,255,255));
			frame.setSize(250,250);
			
			JLabel label1 = new JLabel("You Win !!!");
			label1.setFont(new Font("SansSerif", Font.BOLD, 30));
			label1.setLocation(40,50);
			label1.setSize(250,50);
			
			JLabel label2 = new JLabel("Your Moves :");
			label2.setFont(new Font("SansSerif", Font.BOLD, 20));
			label2.setLocation(30,100);
			label2.setSize(250,50);
			
			JLabel label3 = new JLabel(String.valueOf(TopPanel.moves));
			label3.setFont(new Font("SansSerif", Font.BOLD, 25));
			label3.setLocation(170,100);
			label3.setSize(250,50);
			
			frame.add(label1);
			frame.add(label2);
			frame.add(label3);
			
			frame.setLayout(null);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			reStart();
			TopPanel.moves = 0;
			TopPanel.start = false;
			
			ch = false;
		}
	}
	
	// Start game 
	public static void startGame() {
		
		// Create 16 Boxes of number
		for(int i = 0; i < 4; i++) {			
			for(int j = 0; j < 4; j++) {			
				int n = i*4 +j;   
		        if(n !=15) {
		        	BoxNumber box = new BoxNumber(j*80, i*80, n+1, new Color(255,204,102));
					boxs.add(box);
		        }else {
		        	BoxNumber box = new BoxNumber(j*80, i*80, 16, new Color(255,255,255));
					boxs.add(box);
		        }
			}
		}
		
		// Random Move
		while(randomMove<levelMove) {
			randomBox();
			if(randomMove == levelMove  & boxs.get(15).number != 16) {
				randomMove = randomMove-1;
			}
			
		}
		randomMove = 0;
	}
	
	// Restart game
	public static void reStart() {
		randomMove = 0;
		while(randomMove<levelMove) {
			randomBox();
			
			if(randomMove == levelMove  & boxs.get(15).number != 16) {
				randomMove = randomMove-1;
			}
		}
		emptyBoxX = 240;
		emptyBoxY = 240;
		randomMove = 0;
	}
	
	// Function for random box's move
	public static void randomBox() {
		
		Random rand = new Random();
		int targetX = 0;
		int targetY = 0;
		
		while(targetX<=0 ) {
			targetX = rand.nextInt(emptyBoxX-70, emptyBoxX+150);
		}
		
		while(targetY<=0 ) {
			targetY = rand.nextInt(emptyBoxY-70, emptyBoxY+150);
		}

		moveBox(targetX, targetY);
		TopPanel.moves = 0;
	}
	
	// Check answer
	public static boolean check() {
		boolean checkStatus = false;
		int checkNumber = 0;
		for(BoxNumber box: boxs) {
			checkNumber = checkNumber+1;
			if(box.number == checkNumber) {
				checkStatus = true;
			}else {
				checkStatus = false;
				break;
			}
		}
		return checkStatus;
	}
	
	// Draw Boxes
	public void paint(Graphics g) {
		
		super.paint(g);
		for(BoxNumber box : boxs) {
			box.draw(g);
		}	
	}
	
	// Function for moving boxes
	public static void moveBox (int targetX, int targetY) {
		int xAxis = 0;
		int yAxis = 0;
		boolean check1 = targetX>emptyBoxX-80 & targetX<emptyBoxX & targetY>emptyBoxY & targetY<emptyBoxY+80;
		boolean check2 = targetX>emptyBoxX & targetX<emptyBoxX+80 & targetY>emptyBoxY-80 & targetY<emptyBoxY;
		boolean check3 = targetX>emptyBoxX & targetX<emptyBoxX+80 & targetY>emptyBoxY+80 & targetY<emptyBoxY+160;
		boolean check4 = targetX>emptyBoxX+80 & targetX<emptyBoxX+160 & targetY>emptyBoxY & targetY<emptyBoxY+80;
		boolean check5 = targetX>0 & targetX<320 & targetY>0 & targetY<320;
		
		if (((check1) || (check2) || (check3) || (check4)) & check5) {
			if(targetX<80) {
				xAxis = 0;
			}else if(targetX<160) {
				xAxis = 1;
			}else if(targetX<240) {
				xAxis = 2;
			}else {
				xAxis = 3;
			}
			
			if(targetY<80) {
				yAxis = 0;
			}else if(targetY<160) {
				yAxis = 1;
			}else if(targetY<240) {
				yAxis = 2;
			}else {
				yAxis = 3;
			}
			
			moveIndex = (4*yAxis)+xAxis;
			boxs.get(moveIndex).move(boxs.get(emptyIndex).x, boxs.get(emptyIndex).y);

			emptyBoxX = xAxis*80;
			emptyBoxY = yAxis*80;

			boxs.get(emptyIndex).move(emptyBoxX, emptyBoxY);
			
			BoxNumber boxmove = boxs.get(moveIndex);
			BoxNumber boxempty = boxs.get(emptyIndex);
			
			boxs.set(emptyIndex, boxmove);
			boxs.set(moveIndex, boxempty);
			
			emptyIndex = moveIndex;
			randomMove = randomMove+1;
			TopPanel.moves = TopPanel.moves+1;   

		} else {
			System.out.println("Can not move");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int targetY = e.getY();
		int targetX = e.getX();
		moveBox(targetX, targetY);
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() ==KeyEvent.VK_W || e.getKeyCode() ==KeyEvent.VK_UP) {
			moveBox(emptyBoxX+40, emptyBoxY+120);
		}
		
		if(e.getKeyCode() ==KeyEvent.VK_S || e.getKeyCode() ==KeyEvent.VK_DOWN) {
			moveBox(emptyBoxX+40, emptyBoxY-40);
		}
		
		if(e.getKeyCode() ==KeyEvent.VK_A|| e.getKeyCode() ==KeyEvent.VK_LEFT) {
			moveBox(emptyBoxX+120, emptyBoxY+40);
		}
		
		if(e.getKeyCode() ==KeyEvent.VK_D|| e.getKeyCode() ==KeyEvent.VK_RIGHT) {
			moveBox(emptyBoxX-40, emptyBoxY+40);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
