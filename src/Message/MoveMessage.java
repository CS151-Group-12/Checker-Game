package Message;

import Model.CheckersPiece;

public class MoveMessage extends Message {

    public MoveMessage(CheckersPiece cp1, CheckersPiece cp2) {
        super("Move", cp1, cp2);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
