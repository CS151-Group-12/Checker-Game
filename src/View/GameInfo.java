package View;

import Model.*;

import java.util.ArrayList;

/**
 * Class that stores all information of the game
 */
public class GameInfo {

    private ArrayList<Move> moveList;
    private Tile[][] board;
    private CheckersPiece selectedPiece;
    private int playerTurn, winner;

    /**
     * View will display the gameInfo based on the updated Model
     *
     * @param model
     */
    public GameInfo(Model model) {
        moveList = model.getMoveList();
        board = model.getBoard();
        selectedPiece = model.getSelectedPiece();
        playerTurn = model.getCurrentPlayerPosition();
        winner = model.isWinner();
    }

    ArrayList<Move> getMoveList() {
        return moveList;
    }
    Tile[][] getBoard() {
        return board;
    }
    CheckersPiece getSelectedPiece() {
        return selectedPiece;
    }
    int getPlayerTurn() {
        return playerTurn;
    }
    int getWinner() {
        return winner;
    }
}
