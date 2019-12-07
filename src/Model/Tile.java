package Model;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

// will contain UI and logic for each tile square
public class Tile extends JComponent {
	private static final String NONE = "NONE";
	private int row;
	private char col;
	private Point coords;
	private Color tileColor;
	private int size;
	private boolean highlight;
	private CheckersPiece cp;
	private PieceType s;

	public Tile(int row, char col, Point p, Color c, int Size, boolean Highlight, PieceType s) {
		coords = p;
		tileColor = c;
		size = Size;
		highlight = Highlight;
		this.row = row;
		this.col = col;
		this.s = s;
		setCurrentCheckerPiece();
	}

	void setCurrentCheckerPiece() {
		switch (s) {
			case BLACKPIECE:
				cp = new CheckersPiece(row, col, coords, false, false);
				break;
			case BLACKKING:
				cp = new CheckersPiece(row, col, coords, false, true);
				break;
			case REDPIECE:
				cp = new CheckersPiece(row, col, coords, true, false);
				break;
			case REDKING:
				cp = new CheckersPiece(row, col, coords, true, true);
				break;
			case NONE:
				break;
		}
	}

	public void setHighlight(Boolean b) {
		highlight = b;
	}


	public boolean containsChecker() {
		return cp != null;
	}

	public void draw(Graphics2D g2) {

		g2.setColor(tileColor);
		g2.fillRect(coords.x, coords.y, size, size);

		if (highlight) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(coords.x + 5, coords.y + 5, size - 10, size - 10);
		}

		if (s != PieceType.NONE)
			cp.draw(g2);

	}

	// used to find the location of pieces on the board
	public void getStatus(){

	}

	// used to find the possible moves
	public boolean getHighlight() {
		return false;
	}

	// used to find out whether the tile has an existing checker piece in it
	// can handle killing here
	public void containsPiece(){

	}

	// used to set the color of the tile depending on whether a checker piece can be moved there or not
	public void tileColor(){

	}

	public CheckersPiece getCp() {
		return cp;
	}

	public Color getTileColor() {
		return tileColor;
	}

	public PieceType getS() {
		return s;
	}

	public Point getCoords() {
		return coords;
	}

	public boolean isHighlight() {
		return highlight;
	}

	@Override
	public String toString() {

		int newRow = row + 1;
		return "Pos: " + col + newRow + " - "
				+ "Highlight: " + highlight + " - "
				+ "Piece: " + cp + " - "
				+ "Type: " + s;
	}

	//	   public boolean contains(Point2D p)
//	   {
//	      return x <= p.getX() && p.getX() <= x + width
//	         && y <= p.getY() && p.getY() <= y + width / 2;
//	   }
	//need coordinates

	//need highlight/black/black king/red king/none/
}
