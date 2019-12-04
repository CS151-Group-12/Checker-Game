package View;

import Model.*;

import java.util.ArrayList;

/**
 * Class that stores all information of the game
 */
public class GameInfo {


    private ArrayList<Player> playerList;
    private ArrayList<Move> moveList;
    private Tile[][] board;

    /**
     * View will display the gameInfo based on the updated Model
     *
     * @param model
     */
    public GameInfo(Model model) {
        playerList = model.getPlayerList();
        moveList = model.getMoveList();
        board = model.getBoard();
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public ArrayList<Move> getMoveList() {
        return moveList;
    }
    public Tile[][] getBoard() {
        return board;
    }

}
