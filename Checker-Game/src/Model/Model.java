package Model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

/**
 * Main Class that store all Game Logic, and information
 */
public class Model {

    private ArrayList<Player> playerList;
    private ArrayList<Moves> moveList;
    private Tile[][] board;
    Message.Message message;
    CheckersMove[] legalMoves;
    int selectedRow;
    int selectedCol;

    /**
     * Start the Game
     */
    public void start() {
        playerList = new ArrayList<>();
        moveList = new ArrayList<>();
        board = new Tile[8][8];
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

                if (j >= 5 && b)
                    board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE); //true/false determines highlight
                else if (j < 3 && b)
                    board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
                else {
                    if (b)
                        board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.NONE); //true/false determines highlight
                    else
                        board[i][j] = new Tile(p, Color.WHITE, 75, false, PieceType.NONE);
                }
            }
        }
    }
    // will show the possible moves
    public void showHighlight() {
        if (gameInProgress == false)
            message.setText("Click \"New Game\" to start a new game.");
        else {
            int col = (evt.getX() - 2) / 20;
            int row = (evt.getY() - 2) / 20;
            if (col >= 0 && col < 8 && row >= 0 && row < 8)
                doClickSquare(row,col);
        }

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
                if (currentPlayer == CheckersData.RED)
                    message.setText("RED:  Make your move.");
                else
                    message.setText("BLACK:  Make your move.");
                repaint();
                return;
            }

      /* If no piece has been selected to be moved, the user must first
         select a piece.  Show an error message and return. */

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
                if (currentPlayer == CheckersData.RED)
                    message.setText("RED:  You must continue jumping.");
                else
                    message.setText("BLACK:  You must continue jumping.");
                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                selectedCol = move.toCol;
                repaint();
                return;
            }
        }

      /* The current player's turn is ended, so change to the other player.
         Get that player's legal moves.  If the player has no legal moves,
         then the game ends. */

        if (currentPlayer == CheckersData.RED) {
            currentPlayer = CheckersData.BLACK;
            legalMoves = board.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                gameOver("BLACK has no moves.  RED wins.");
            else if (legalMoves[0].isJump())
                message.setText("BLACK:  Make your move.  You must jump.");
            else
                message.setText("BLACK:  Make your move.");
        }
        else {
            currentPlayer = CheckersData.RED;
            legalMoves = board.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                gameOver("RED has no moves.  BLACK wins.");
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

        repaint();

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
