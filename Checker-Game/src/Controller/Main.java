package Controller;

import Message.Message;
import Model.Model;
import View.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    /**
     * MessageQueue: a communication between Controller and View
     */
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    /**
     * GUI of the app
     */
    private static View view;

    /**
     * Model of the app
     */
    private static Model model;

    /**
     * Main function to run the program
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        view = View.init(queue);
        model = new Model();
        Controller game = new Controller(view, model, queue);
        game.mainLoop();
        view.dispose();
        queue.clear();
    }
}
