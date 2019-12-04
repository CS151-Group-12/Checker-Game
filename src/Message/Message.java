package Message;

public class Message {

    /** The action's name. */
    private final String name;
    public String message;

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setText(String s) {
        message = s;
    }
    public void gameOver(String str) {

    }
}