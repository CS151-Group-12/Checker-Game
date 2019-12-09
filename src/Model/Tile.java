package Model;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

// will contain UI and logic for each tile square
public class Tile extends JComponent {
	private int row, col;
	private Point coords;
	private Color tileColor;
	private int size;
	private boolean highlight;
	private CheckersPiece cp;
	private PieceType s;

	public Tile(int row, int col, Color c, int Size, boolean Highlight, PieceType s) {
		coords = new Point((75 * col) + 50, (75 * row) + 1);;
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
				cp = new CheckersPiece(row, col, coords, false, false, PieceType.BLACKPIECE);
				break;
			case BLACKKING:
				cp = new CheckersPiece(row, col, coords, false, true, PieceType.BLACKKING);
				break;
			case REDPIECE:
				cp = new CheckersPiece(row, col, coords, true, false, PieceType.REDPIECE);
				break;
			case REDKING:
				cp = new CheckersPiece(row, col, coords, true, true, PieceType.REDKING);
				break;
			case NONE:
				break;
		}
	}

	public void setCheckerPiece(CheckersPiece cp) {
		this.cp = cp;
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

	public CheckersPiece getCp() {
		return cp;
	}

	public PieceType getCheckerPieceType() {
		if (cp != null)
			return cp.getPieceType();
		return null;
	}

	public void setCheckerPieceType(PieceType pt) {
		cp.setPieceType(pt);
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

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isHighlight() {
		return highlight;
	}

	@Override
	public String toString() {
		return "[Tile Object] Col = " + col + " Row = " + row
				+ " - "
				+ "Highlight: " + highlight + " - "
				+ "Piece: " + cp + " - "
				+ "Type: " + s;
	}
}
