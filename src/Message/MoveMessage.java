package Message;

import Model.CheckersPiece;
import Model.Tile;

public class MoveMessage extends Message {

    private Tile tile;
    private CheckersPiece cp1;

    public MoveMessage(CheckersPiece cp1, Tile tileToMove) {
        super("Move", cp1, tileToMove);
        this.cp1 = cp1;
        this.tile = tileToMove;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public CheckersPiece getCp1() {
        return cp1;
    }

    @Override
    public Tile getTileToMove() {
        return tile;
    }
}