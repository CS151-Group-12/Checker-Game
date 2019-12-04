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

	HashMap<CheckersPiece, CheckersPiece> moveMap = new HashMap<>();
	//starting Move(Game just started)
	CheckersPiece prevPosition;
	CheckersPiece currentPosition;

	public Move(CheckersPiece prevPosition, CheckersPiece currentPosition) {
		this.prevPosition = prevPosition;
		this.currentPosition = currentPosition;

		moveMap.put(prevPosition, currentPosition);
	}

	public CheckersPiece getCurrentPosition() {
		return currentPosition;
	}

	public CheckersPiece getPrevPosition() {
		return prevPosition;
	}

	public HashMap<CheckersPiece, CheckersPiece> getMoveMap() {
		return moveMap;
	}
}
