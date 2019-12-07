package Message;

import Model.CheckersPiece;

public class ShowHighlightMessage extends Message {

    public ShowHighlightMessage(CheckersPiece cp) {
        super("ShowHighLight", cp);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
