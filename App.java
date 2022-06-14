package z15game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App {
	
	public static void main(String args[]) {
		
		// Create Frame
		JFrame frame = new JFrame("15game");
		MainPanel.emptyBoxX = 0;
		MainPanel panel = new MainPanel();
		
		frame.add(panel);
		
		TopPanel topPanel = new TopPanel();
		frame.add(topPanel);
		
		frame.getContentPane().setBackground(new Color(255,255,255));
		frame.setSize(430,500);
		
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {
			panel.play();
			topPanel.play();
			
			// Exception handling 
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
