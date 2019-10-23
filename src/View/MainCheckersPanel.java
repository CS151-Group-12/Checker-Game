import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author Justin Lo
 */
public class MainCheckersPanel extends JPanel  {

	int width;
	int height;
	BoardPanel bp;
	HistoryPanel hp;
	ArrayList <Point2D>squarehighlight=new ArrayList<>();
	
	public MainCheckersPanel(int Width, int Height) {
		
		//this.setBackground(Color.BLACK);
		width = Width;
		height = Height;

		this.setLayout(new BorderLayout());
		
		//Adding The HistoryPanel
		hp = new HistoryPanel();	
		
		//Adding The CenterPanel		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		BoxLayout by= new BoxLayout(center,BoxLayout.Y_AXIS);
		center.setLayout(by);	
		center.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		center.setBackground(Color.WHITE);
		
		
		//Adding The BoardPanel
		bp = new BoardPanel();
		//bp.setBorder(BorderFactory.createLoweredBevelBorder());

		//Adding The Header
		JLabel header = new JLabel("Checkers");	
		Font font = new Font("integerfont",Font.BOLD,25);
		header.setFont(font);
		header.setForeground(Color.BLACK);
		header.setBackground(Color.LIGHT_GRAY);		
		header.setPreferredSize(new Dimension(width, 50));
		
		center.add(header);
		center.add(bp);
		this.add(hp,BorderLayout.EAST);
		this.add(center,BorderLayout.CENTER);
		
		TestBoard();
		
		
	}

	private void TestBoard() {
		// TODO Auto-generated method stub
		ArrayList <Point> highlights = new ArrayList<Point>();
		highlights.add(new Point(3,4));
		highlights.add(new Point(4,5));
		highlights.add(new Point(5,4));
		highlights.add(new Point(2,1));
		
		bp.refresh();
		bp.changePiece(new Point(0,5));
		
		//bp.changePiece();
		bp.highlightSquares(highlights);
		
	}
	
	//draw pieces
	//draw highlight
	//draw board;
	
	//draw turnhistory;
	//-needs list of piece movements
	//-in list, needs location of pieces (b3, a2, ect)
	//-in list, state which piece is deleted from the array;
	//-in list, need color/player the piece is from
	
	
	
	
	
}
