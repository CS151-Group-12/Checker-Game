
package Message;

import Model.CheckersPiece;

public class ShowHighlightMessage extends Message {

    private CheckersPiece checkersPiece;

    public ShowHighlightMessage(CheckersPiece cp) {
        super("ShowHighLight", cp);
        this.checkersPiece = cp;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public CheckersPiece getCp1() {
        return checkersPiece;
    }
}