import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author Justin Lo
 */
public class BoardPanel extends JPanel implements MouseListener  {
	Tile[][] testboard ;

	public BoardPanel (){
		testboard = new Tile [8][8];

		for(int i=0;i<testboard.length;i++) {
			for(int j=0;j<testboard.length;j++) {
				Point p= new Point ((75*i)+50,(75*j)+1);


				if(i%2==0 &&j%2!=0) 
					testboard[i][j]= new Tile(p, Color.LIGHT_GRAY, 75,false,"NONE");

				else if(i%2!=0&&j%2==0) 
					testboard[i][j]= new Tile(p, Color.LIGHT_GRAY, 75,false,"NONE"); //true/false determines highlight

				else 
					testboard[i][j]= new Tile(p, Color.WHITE, 75,false,"NONE");
				
			}
			
		}

	}

	public void highlightSquares(ArrayList <Point> squares) {
		for(Point p:squares) {
			testboard[p.x][p.y].setHighlight(true);
		}
	}

	public Point getCenter(Point p) {
		int x = p.x-(25/2);
		int y = p.y-(25/2);

		return new Point(x,y);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600,700);
	}

	@Override
	public void paintComponent(Graphics g){

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);

		g2.fillRect(0, 0, 3000, 3000);

		g2.setColor(Color.BLACK);
		// g2.drawLine(0, 1, 3000,1);

		//Drawing the Board
		for(int i=0;i<testboard.length;i++) {
			for(int j=0;j<testboard.length;j++) {
				//				System.out.println(i);
				//				System.out.println(j);
				testboard[i][j].draw(g2);
			}
		}

		//Drawing the Integers
		g2.setColor(Color.DARK_GRAY);
		Font font = new Font("integerfont",Font.BOLD,24);
		g2.setFont(font);
		g2.drawRect(50, 1, 600, 600);
		for(int x=1;x<9;x++) {
			g2.setColor(Color.BLACK);
			g2.drawString(Integer.toString(x), 15, (x*75)-25);

		}

		//Drawing the Letters
		int ascciHvalue =64;
		for(int x=1;x<9;x++) {
			g2.setColor(Color.BLACK);
			g2.drawString( ""+(char) (ascciHvalue+x), (x*75)+5, 635);
		}

		//Drawing the pieces

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void changePiece(Point p) {
		testboard[p.x][p.y].setStatus("BLACKKING");
		this.repaint();
	}
	public void refresh() {
		
		for(int i=0;i<testboard.length;i++) {
			for(int j=0;j<testboard.length;j++) {
				
				if(j<3) {
					if(i%2==0 &&j%2!=0 ||i%2!=0&&j%2==0) {
						testboard[i][j].setStatus("REDPIECE");
					}
				}
				if(j>4) {
					if(i%2==0 &&j%2!=0 ||i%2!=0&&j%2==0) {
						testboard[i][j].setStatus("BLACKPIECE");
					}
				}

					
			}
		}
		this.repaint();
	}


}
