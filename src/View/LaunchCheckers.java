import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * @author Justin Lo
 */
public class LaunchCheckers extends JFrame {
	
	public static void main(String[] args) {
		new  LaunchCheckers();
	}
	
	public LaunchCheckers() {
		
		super("Checkers");
		final int WIDTH = 900, HEIGHT = 725;
		
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);
	
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//this.setLayout(new BorderLayout());	
		MainCheckersPanel p = new MainCheckersPanel(WIDTH,HEIGHT);
		
		this.add(p);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
