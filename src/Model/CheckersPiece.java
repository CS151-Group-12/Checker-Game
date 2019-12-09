package Model;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CheckersPiece {
	private Point p;
	private boolean king;
	private Color outer;
	private Color inner;
	private int row, col;
	private PieceType pieceType;

	CheckersPiece(int row, int col, Point p, Boolean red, Boolean king, PieceType pieceType) {
		this.p = p;
		this.king = king;
		this.row = row;
		this.col = col;

		if (red) {
			outer = Color.RED;
			inner = new Color(196, 46, 20);
		} else {
			outer = Color.BLACK;
			inner = new Color(74, 72, 72);
		}

		this.pieceType = pieceType;
	}

	public int getRow() {
		return row;
	}

	PieceType getPieceType() {
		if (king) {
			if (outer == Color.RED) {
				pieceType = PieceType.REDKING;
			} else {
				pieceType =  PieceType.BLACKKING;
			}
		} else {
			if (outer == Color.RED) {
				pieceType = PieceType.REDPIECE;
			} else {
				pieceType = PieceType.BLACKPIECE;
			}
		}
		return pieceType;
	}

	void setPieceType(PieceType pt) {
		pieceType = pt;
	}

	public int getCol() {
		return col;
	}

	void draw(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		Ellipse2D.Double Circle = new Ellipse2D.Double(p.x + 2, p.y + 2, 71, 71);
		Ellipse2D.Double Circle2 = new Ellipse2D.Double(p.x + 7, p.y + 7, 61, 61);

		g2.setColor(outer);
		g2.fill(Circle);
		g2.setColor(Color.BLACK);
		g2.draw(Circle);
		g2.setColor(inner);

		g2.fill(Circle2);
		g2.setColor(Color.BLACK);
		g2.draw(Circle2);

		if (king) {
			Image i = drawCrown();
			g2.drawImage(i, p.x + 9, p.y + 9, Circle2.getBounds().width - 5, Circle2.getBounds().height - 5, null);
		}
	}

	Color getOuter() {
		return outer;
	}

	private Image drawCrown() {
		Image image = null;
		BufferedImage Bimage = null;

		try {
			Bimage = ImageIO.read(new File("resources/crown.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		image = Bimage;

		assert image != null;
		image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		return image;
	}

	@Override
	public String toString() {
		return "Row: " + row + " - Col " + col + " - piecetype: " +
				pieceType;
	}
}


