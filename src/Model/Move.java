package Model;

import com.sun.tools.javac.comp.Check;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contain the piece and ist previous and the Current position
 */
public class Move {
    //needs the piece deleted
    //needs the position of old piece

    private HashMap<CheckersPiece, CheckersPiece> moveMap = new HashMap<>();
    //starting Move(Game just started)
    private CheckersPiece cp1, cp2;

    public Move(CheckersPiece cp1, CheckersPiece cp2) {
        this.cp1 = cp1;
        this.cp2 = cp2;

        moveMap.put(cp1, cp2);
    }

    public CheckersPiece getCp1() {
        return cp1;
    }

    public CheckersPiece getCp2() {
        return cp2;
    }

    public HashMap<CheckersPiece, CheckersPiece> getMoveMap() {
        return moveMap;
    }

    int fromRow, fromCol;  // Position of piece to be moved.
    int toRow, toCol;      // Square it is to move to.

    /**
     *     A CheckersMove object represents a move in the game of Checkers.
     *     It holds the row and column of the piece that is to be moved
     *     and the row and column of the square to which it is to be moved.
     *     (This class makes no guarantee that the move is legal.)
     * @param r1
     * @param c1
     * @param r2
     * @param c2
     */
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

    /**
     * Test whether this move is a jump.  It is assumed that
     * the move is legal.  In a jump, the piece moves two
     * rows.  (In a regular move, it only moves one row.)
     * @return
     */
    boolean isJump() {
//		System.out.println("[isJump] = " + (fromRow - toRow));
        return (fromRow - toRow == 2 || fromRow - toRow == -2);
    }

    @Override
    public String toString() {
        return "[Move]: (" + fromRow + ", " + fromCol + ") -> " + "(" + toRow + ", " + toCol + ")";
    }
}