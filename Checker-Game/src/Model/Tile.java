package Model;

import Model.CheckersPiece;


import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// will contain UI and logic for each tile square
public class Tile extends JComponent {
	private Point coords;
	private Color tileColor;
	private int size;
	private boolean highlight;
	private CheckersPiece cp;
	private PieceType s2;

	public Tile(Point p, Color c, int Size, boolean Highlight, PieceType s) {
		coords = p;
		tileColor = c;
		size = Size;
		highlight = Highlight;
		setStatus(s);
	}

	public void setStatus(PieceType s) {
		s2 = s;
	}

//	public CheckersPiece getPieceImage() {
//		try {
//			return cp.clone();
//		} catch (CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	private class MousePressedListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("test");

		}
	}

	public void setHighlight(Boolean b) {
		highlight = b;
	}

	public void makeMove(CheckersMove move) {
		// Make the specified move.  It is assumed that move
		// is non-null and that the move it represents is legal.
		makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
	}
	public boolean containsChecker() {
		return cp != null;
	}

	public void draw(Graphics2D g2) {

		g2.setColor(tileColor);
//		System.out.println(tilerColor);x
		g2.fillRect(coords.x, coords.y, size, size);

		if (highlight) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(coords.x + 5, coords.y + 5, size - 10, size - 10);
		}

		switch (s2) {
			case BLACKPIECE:
				cp = new CheckersPiece(coords, false, false);
				cp.draw(g2);
				break;
			case BLACKKING:
				cp = new CheckersPiece(coords, false, true);
				cp.draw(g2);
				break;
			case REDPIECE:
				cp = new CheckersPiece(coords, true, false);
				cp.draw(g2);
				break;
			case REDKING:
				cp = new CheckersPiece(coords, true, true);
				cp.draw(g2);
				break;
			case NONE:
				break;

		}

	}
	// used to find the location of pieces on the board
	public void getStatus(){

	}
	// used to find the possible moves
	public boolean getHighlight(){

		return false;
	}
	// used to find out whether the tile has an existing checker piece in it
	// can handle killing here
	public void containsPiece(){

	}
	// used to set the color of the tile depending on whether a checker piece can be moved there or not
	public void tileColor(){

	}

//	   public boolean contains(Point2D p)
//	   {
//	      return x <= p.getX() && p.getX() <= x + width 
//	         && y <= p.getY() && p.getY() <= y + width / 2;
//	   }
	//need coordinates

	//need highlight/black/black king/red king/none/
}
