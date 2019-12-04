package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Main Class that store all Game Logic, and information
 */
public class Model {

    private ArrayList<Player> playerList;
    private ArrayList<Move> moveList;
    private gameData board;
    gameData boardData;
    public Tile[][] boardTiles;
    Message.Message message;
    CheckersMove[] legalMoves;
    int selectedRow;
    int selectedCol;
    Player PlayerData = new Player();
    int currentPlayer;
//    View.BoardPanel boardPanel;
//    View.MainFrame mainFrame = new MainFrame();
//    public boolean gameActive = mainFrame.getGameStatus();


    /**
     * Start the Game
     */
    public void start() {
        playerList = new ArrayList<>();
        moveList = new ArrayList<>();
        boardTiles = new Tile[8][8];
        setBoard(boardTiles);
        System.out.println("Model init");
        Point p = new Point((75 * 3) + 50, (75 * 5) + 1);
        CheckersPiece c1 = new CheckersPiece(5, 'D', p,false, false);
        CheckersPiece c2 = new CheckersPiece(5, 'D', p,false, false);

        CheckersPiece c3 = new CheckersPiece(5, 'D', p,false, false);
        CheckersPiece c4 = new CheckersPiece(5, 'D', p,false, false);

        Move a = new Move(c1, c2);
        Move b = new Move(c2, c3);
        moveList.add(a);
        moveList.add(b);
        System.out.println("Model initialized");
        System.out.println(moveList.size());

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

    public void makeMove(CheckersMove move) {
        // Make the specified move.  It is assumed that move
        // is non-null and that the move it represents is legal.
        gameData.makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }

//    // will show the possible moves
    public void showHighlight(int i, int j) {

        System.out.println("Model Called showHighlight");
        boardTiles[1][4].setHighlight(!boardTiles[1][4].isHighlight());
        boardTiles[3][4].setHighlight(!boardTiles[3][4].isHighlight());
    }

    void doClickSquare(int row, int col) {

        // This is called by mousePressed() when a player clicks on the
        // square in the specified row and col.  It has already been checked
        // that a game is, in fact, in progress.

      /* If the player clicked on one of the pieces that the player
         can move, mark this row and col as selected and return.  (This
         might change a previous selection.)  Reset the message, in
         case it was previously displaying an error message. */



        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                selectedRow = row;
                selectedCol = col;

                if (currentPlayer == gameData.RED)
                    message.setText("RED:  Make your move.");
                else
                    message.setText("BLACK:  Make your move.");
//                boardPanel.repaint();
                return;
            }


        if (selectedRow < 0) {
            message.setText("Click the piece you want to move.");
            return;
        }


      /* If the user clicked on a squre where the selected piece can be
         legally moved, then make the move and return. */


        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
                    && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                doMakeMove(legalMoves[i]);
                return;
            }

      /* If we get to this point, there is a piece selected, and the square where
         the user just clicked is not one where that piece can be legally moved.
         Show an error message. */

        message.setText("Click the square you want to move to.");

    }  // end doClickSquare()


    void doMakeMove(CheckersMove move) {


        board.makeMove(move);

        // Thiis is called when the current player has chosen the specified
        // move.  Make the move, and then either end or continue the game
        // appropriately.

        board.makeMove(move);

      /* If the move was a jump, it's possible that the player has another
         jump.  Check for legal jumps starting from the square that the player
         just moved to.  If there are any, the player must jump.  The same
         player continues moving.
      */


        if (move.isJump()) {
            legalMoves = board.getLegalJumpsFrom(currentPlayer,move.toRow,move.toCol);
            if (legalMoves != null) {
                if (currentPlayer == gameData.RED)
                    message.setText("RED:  You must continue jumping.");
                else
                    message.setText("BLACK:  You must continue jumping.");
                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                selectedCol = move.toCol;
//                boardPanel.repaint();
                return;
            }
        }

      /* The current player's turn is ended, so change to the other player.
         Get that player's legal moves.  If the player has no legal moves,
         then the game ends. */

        if (currentPlayer == gameData.RED) {
            currentPlayer = gameData.BLACK;
            legalMoves = board.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                message.gameOver("BLACK has no moves.  RED wins.");
            else if (legalMoves[0].isJump())
                message.setText("BLACK:  Make your move.  You must jump.");
            else
                message.setText("BLACK:  Make your move.");
        }
        else {
            currentPlayer = gameData.RED;
            legalMoves = board.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                message.gameOver("RED has no moves.  BLACK wins.");
            else if (legalMoves[0].isJump())
                message.setText("RED:  Make your move.  You must jump.");
            else
                message.setText("RED:  Make your move.");
        }

      /* Set selectedRow = -1 to record that the player has not yet selected
          a piece to move. */

        selectedRow = -1;

      /* As a courtesy to the user, if all legal moves use the same piece, then
         select that piece automatically so the use won't have to click on it
         to select it. */

        if (legalMoves != null) {
            boolean sameStartSquare = true;
            for (int i = 1; i < legalMoves.length; i++)
                if (legalMoves[i].fromRow != legalMoves[0].fromRow
                        || legalMoves[i].fromCol != legalMoves[0].fromCol) {
                    sameStartSquare = false;
                    break;
                }
            if (sameStartSquare) {
                selectedRow = legalMoves[0].fromRow;
                selectedCol = legalMoves[0].fromCol;
            }
        }

        /* Make sure the board is redrawn in its new state. */

    }

    public ArrayList<Move> getMoveList() {
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
        return boardTiles;
    }
}