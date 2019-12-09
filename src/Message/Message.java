package Message;

import Model.CheckersPiece;
import Model.Tile;

public class Message {

    /** The action's name. */
    private final String name;
    public String message;
    private CheckersPiece cp1;
    private Tile tileToMove;

    public Message(String name) {
        this.name = name;
    }

    public Message(String name, CheckersPiece cp1, Tile tileToMove) {
        this.name = name;
        this.tileToMove = tileToMove;
        this.cp1 = cp1;
    }

    public Message(String name, CheckersPiece cp) {
        this.name = name;
        this.cp1 = cp;
    }

    public Message(String name, Tile tileToMove) {
        this.name = name;
        this.tileToMove = tileToMove;
    }

    public String getName() {
        return name;
    }

    public Tile getTileToMove() {
        return tileToMove;
    }

    public CheckersPiece getCp1() {
        return cp1;
    }
}