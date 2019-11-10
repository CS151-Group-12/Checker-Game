package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Main Class that store all Game Logic, and information
 */
public class Model {

    private ArrayList<Player> playerList;
    private ArrayList<Moves> moveList;
    private Tile[][] board;

    /**
     * Start the Game
     */
    public void start() {
        playerList = new ArrayList<>();
        moveList = new ArrayList<>();
        board = new Tile[8][8];
        setBoard(board);
        System.out.println("Model init");
    }

    /**
     * Move piece action
     */
    public void movePiece() {

    }

    public void setBoard(Tile[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Point p = new Point((75 * i) + 50, (75 * j) + 1);
                boolean b = (i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0);

                char col = (char) (i + 65);

                if (j >= 5 && b)
                    board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE); //true/false determines highlight
                else if (j < 3 && b)
                    board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
                else {
                    if (b)
                        board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.NONE); //true/false determines highlight
                    else
                        board[i][j] = new Tile(j, col, p, Color.WHITE, 75, false, PieceType.NONE);
                }
            }
        }
    }
    // will show the possible moves
    public void showHighlight() {
        System.out.println("Model Called showHighlight");
        board[1][4].setHighlight(!board[1][4].isHighlight());
        board[3][4].setHighlight(!board[3][4].isHighlight());
    }

    public ArrayList<Moves> getMoveList() {
        return moveList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Get The Board
     * @return the board
     */
    public Tile[][] getBoard() {
        return board;
    }
}
