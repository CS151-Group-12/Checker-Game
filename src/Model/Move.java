package Model;

/**
 * Contain the piece and ist previous and the Current position
 */
public class Move {
	int fromRow, fromCol;  // Position of piece to be moved.
	int toRow, toCol;      // Square it is to move to.

	public Move(int r1, int c1, int r2, int c2) {
		fromRow = r1;
		fromCol = c1;
		toRow = r2;
		toCol = c2;
	}

	public int getFromCol() {
		return fromCol;
	}

	public int getFromRow() {
		return fromRow;
	}

	public int getToCol() {
		return toCol;
	}

	public int getToRow() {
		return toRow;
	}

	boolean isJump() {
		return (fromRow - toRow == 2 || fromRow - toRow == -2);
	}

	@Override
	public String toString() {
		return "[Move]: (" + fromRow + ", " + fromCol + ") -> " + "(" + toRow + ", " + toCol + ")";
	}
}
