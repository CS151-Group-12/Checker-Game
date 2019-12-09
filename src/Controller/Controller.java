package Controller;

import Message.*;
import Model.*;
import View.*;

import java.util.*;
import java.util.concurrent.*;

public class Controller {
    private BlockingQueue<Message> queue;
    private List<Valve> valves = new LinkedList<>();
    private View view;
    private Model model;
    private GameInfo gameInfo;

    /**
     * Constructor: Connect the Model, View, and message Queue
     * @param view
     * @param model
     */
    public Controller(View view, Model model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        this.model.start();
        updateGame("INIT_BOARD");
        addAllValves();
    }

    /**
     * Main Loop to Execute the message from the View
     */
    void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;

        while (response != ValveResponse.FINISH) {
            try {
                message = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Valve valve : valves) {
                response = valve.execute(message);
                if (response != ValveResponse.MISS) break;
            }
        }
    }

    /**
     * @return the model of the App
     */
    public Model getModel() {
        return model;
    }

    /**
     * Update the new Model
     *
     * @return an updated Model wih changes
     */
    private GameInfo updateGameInfo() {
        gameInfo = new GameInfo(model);
        return gameInfo;
    }

    /**
     * Add all the Valve so the mainloop can execute the message
     */
    private void addAllValves() {
        valves.add(new StartNewGameValve());
        valves.add(new MovePieceValve());
        valves.add(new ShowHighlightValve());
    }

    /**
     * Update all the View Panel
     *
     * @param action: Current action of
     */
    private void updateGame(String action) {
        view.setBoardPanel(updateGameInfo(), action);
        view.setHistoryPanel(updateGameInfo(), action);
    }

    /**
     * Start a new Game
     */
    private class StartNewGameValve implements Valve {
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class) {
                return ValveResponse.MISS;
            }

            // Init the model
            model.start();

            // Update the View of the Game
            updateGame("NEW_GAME");

            return ValveResponse.EXECUTED;
        }
    }

    private class MovePieceValve implements Valve {
        public ValveResponse execute(Message message) {
            if (message.getClass() != MoveMessage.class) {
                return ValveResponse.MISS;
            }

            // Model
            Move move = new Move(message.getCp1().getRow(), message.getCp1().getCol(),
                    message.getTileToMove().getRow(), message.getTileToMove().getCol());

            model.doClickSquare(move);

            updateGame("MOVE");

            return ValveResponse.EXECUTED;
        }
    }

    private class ShowHighlightValve implements Valve {
        public ValveResponse execute(Message message) {
            if (message.getClass() != ShowHighlightMessage.class) {
                return ValveResponse.MISS;
            }

            model.showHighlight(message.getCp1());

            updateGame("HIGHLIGHT");

            return ValveResponse.EXECUTED;
        }
    }
}
