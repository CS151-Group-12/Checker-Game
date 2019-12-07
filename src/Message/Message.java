package Message;

import Model.CheckersPiece;

public class Message {

    /** The action's name. */
    private final String name;
    public String message;
    private CheckersPiece cp1, cp2;

    public Message(String name) {
        this.name = name;
    }

    public Message(String name, CheckersPiece cp1, CheckersPiece cp2) {
        this.name = name;
        this.cp1 = cp1;
        this.cp2 = cp2;
    }

    public Message(String name, CheckersPiece cp) {
        this.name = name;
        this.cp1 = cp;
    }
    public String getName() {
        return name;
    }

    public void setText(String s) {
        message = s;
    }
    public void gameOver(String str) {

    }

    public CheckersPiece getCp2() {
        return cp2;
    }

    public CheckersPiece getCp1() {
        return cp1;
    }
}