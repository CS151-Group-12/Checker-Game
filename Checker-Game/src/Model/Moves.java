package Model;

import Model.Piece;

import java.awt.Point;

/**
 * Contain the piece and ist previous and the Current position
 */
public class Moves {
	//needs the piece deleted
	//needs the position of old piece

	//starting Move(Game just started)
	Piece deleted;
	Piece moved;
	Point newPos;

	public Moves(Piece Moved, Piece Deleted, Point NewPos, Piece[][] saved) {
		moved = Moved;
		deleted = Deleted;
		newPos = NewPos;
	}

	public Moves(Piece Moved, Point NewPos, Piece[][] saved) {
		moved = Moved;
		deleted = null;
		newPos = NewPos;
	}
}
