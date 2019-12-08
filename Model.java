//package Model;
//
//import java.awt.*;
//import java.util.ArrayList;
//
///**
// * Main Class that store all Game Logic, and information
// */
//public class Model {
//
//    private ArrayList<Player> playerList;
//    private ArrayList<Move> moveList;
//    private gameData board;
//    gameData boardData;
//    public Tile[][] boardTiles;
//    Message.Message message;
//    CheckersMove[] legalMoves;
//    int selectedRow;
//    int selectedCol;
//    Player PlayerData = new Player();
//    int currentPlayer;
//    View.BoardPanel boardPanel;
//    boolean gameInProgress = true;
////    View.MainFrame mainFrame = new MainFrame();
////    public boolean gameActive = mainFrame.getGameStatus();
//
//
//    /**
//     * Start the Game
//     */
//    public void start() {
//        playerList = new ArrayList<>();
//        moveList = new ArrayList<>();
//        boardTiles = new Tile[8][8];
//        setBoard(boardTiles);
//        System.out.println("Model init");
//        Point p = new Point((75 * 3) + 50, (75 * 5) + 1);
////        CheckersPiece c1 = new CheckersPiece(5, 'D', p,false, false);
////        CheckersPiece c2 = new CheckersPiece(5, 'D', p,false, false);
////
////        CheckersPiece c3 = new CheckersPiece(5, 'D', p,false, false);
////        CheckersPiece c4 = new CheckersPiece(5, 'D', p,false, false);
//
////        Move a = new Move(c1, c2);
////        Move b = new Move(c2, c3);
////        moveList.add(a);
////        moveList.add(b);
//        System.out.println("Model initialized");
//        System.out.println(moveList.size());
//
//    }
//
//    /**
//     * Move piece action
//     */
//    public void movePiece() {
//
//    }
//
//    public void setBoard(Tile[][] board) {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                Point p = new Point((75 * i) + 50, (75 * j) + 1);
//                boolean b = (i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0);
//
//                char col = (char) (i + 65);
//
//                if (j >= 5 && b)
//                    board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE); //true/false determines highlight
//                else if (j < 3 && b)
//                    board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
//                else {
//                    if (b)
//                        board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.NONE); //true/false determines highlight
//                    else
//                        board[i][j] = new Tile(j, col, p, Color.WHITE, 75, false, PieceType.NONE);
//                }
//            }
//        }
//    }
//
//    public void makeMove(CheckersMove move) {
//        // Make the specified move.  It is assumed that move
//        // is non-null and that the move it represents is legal.
//        gameData.makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
//    }
//
////    // will show the possible moves
//    public void showHighlight(int i, int j) {
//
//        System.out.println("Model Called showHighlight");
//        //boardTiles[1][4].setHighlight(!boardTiles[1][4].isHighlight());
//        //boardTiles[3][4].setHighlight(!boardTiles[3][4].isHighlight());
////        if (i >= 0 && i < 8 && j >= 0 && j < 8)
////            doClickSquare(i, j);
//        if (gameInProgress) {
//            // highlight pieces that can be moved
//            for (int h = 0; h < legalMoves.length; h++) {
//                // g.drawRect(2 + legalMoves[i].fromCol*20, 2 + legalMoves[i].fromRow*20, 19, 19);
//                // access existing board drawing component and draw the highlight
//                //boardPanel.highlightSquares();
//            }
//            //show all places the piece can be moved to
//            if (selectedRow >= 0) {
//                for (int l = 0; l < legalMoves.length; l++) {
//                    if (legalMoves[l].fromCol == selectedCol && legalMoves[l].fromRow == selectedRow)
//                        System.out.print("showing possible positions");
//                        //g.drawRect(2 + legalMoves[i].toCol*20, 2 + legalMoves[i].toRow*20, 19, 19);
//                }
//            }
//        }
//    }
//
//    void doClickSquare(int row, int col) {
//
//        for (int i = 0; i < legalMoves.length; i++)
//            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
//                selectedRow = row;
//                selectedCol = col;
//
//                if (currentPlayer == gameData.RED)
//                    message.setText("RED:  Make your move.");
//                else
//                    message.setText("BLACK:  Make your move.");
////                boardPanel.repaint();
//                return;
//            }
//
//
//        if (selectedRow < 0) {
//            message.setText("Click the piece you want to move.");
//            return;
//        }
//
//
//      /* If the user clicked on a squre where the selected piece can be
//         legally moved, then make the move and return. */
//
//
//        for (int i = 0; i < legalMoves.length; i++)
//            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
//                    && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
//                doMakeMove(legalMoves[i]);
//                return;
//            }
//
//      /* If we get to this point, there is a piece selected, and the square where
//         the user just clicked is not one where that piece can be legally moved.
//         Show an error message. */
//
//        message.setText("Click the square you want to move to.");
//
//    }  // end doClickSquare()
//
//
//    void doMakeMove(CheckersMove move) {
//
//
//        board.makeMove(move);
//
//        // This is called when the current player has chosen the specified
//        // move.  Make the move, and then either end or continue the game
//        // appropriately.
//
//        board.makeMove(move);
//
//      /* If the move was a jump, it's possible that the player has another
//         jump.  Check for legal jumps starting from the square that the player
//         just moved to.  If there are any, the player must jump.  The same
//         player continues moving.
//      */
//
//
//        if (move.isJump()) {
//            legalMoves = board.getLegalJumpsFrom(currentPlayer,move.toRow,move.toCol);
//            if (legalMoves != null) {
//                if (currentPlayer == gameData.RED)
//                    message.setText("RED:  You must continue jumping.");
//                else
//                    message.setText("BLACK:  You must continue jumping.");
//                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
//                selectedCol = move.toCol;
////                boardPanel.repaint();
//                return;
//            }
//        }
//
//      /* The current player's turn is ended, so change to the other player.
//         Get that player's legal moves.  If the player has no legal moves,
//         then the game ends. */
//
//        if (currentPlayer == gameData.RED) {
//            currentPlayer = gameData.BLACK;
//            legalMoves = board.getLegalMoves(currentPlayer);
//            if (legalMoves == null)
//                message.gameOver("BLACK has no moves.  RED wins.");
//            else if (legalMoves[0].isJump())
//                message.setText("BLACK:  Make your move.  You must jump.");
//            else
//                message.setText("BLACK:  Make your move.");
//        }
//        else {
//            currentPlayer = gameData.RED;
//            legalMoves = board.getLegalMoves(currentPlayer);
//            if (legalMoves == null)
//                message.gameOver("RED has no moves.  BLACK wins.");
//            else if (legalMoves[0].isJump())
//                message.setText("RED:  Make your move.  You must jump.");
//            else
//                message.setText("RED:  Make your move.");
//        }
//
//      /* Set selectedRow = -1 to record that the player has not yet selected
//          a piece to move. */
//
//        selectedRow = -1;
//
//      /* As a courtesy to the user, if all legal moves use the same piece, then
//         select that piece automatically so the use won't have to click on it
//         to select it. */
//
//        if (legalMoves != null) {
//            boolean sameStartSquare = true;
//            for (int i = 1; i < legalMoves.length; i++)
//                if (legalMoves[i].fromRow != legalMoves[0].fromRow
//                        || legalMoves[i].fromCol != legalMoves[0].fromCol) {
//                    sameStartSquare = false;
//                    break;
//                }
//            if (sameStartSquare) {
//                selectedRow = legalMoves[0].fromRow;
//                selectedCol = legalMoves[0].fromCol;
//            }
//        }
//
//        /* Make sure the board is redrawn in its new state. */
//
//    }
//
//    public ArrayList<Move> getMoveList() {
//        return moveList;
//    }
//
//    public ArrayList<Player> getPlayerList() {
//        return playerList;
//    }
//
//    /**
//     * Get The Board
//     * @return the board
//     */
//    public Tile[][] getBoard() {
//        return boardTiles;
//    }
//}
package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Main Class that store all Game Logic, and information
 */
public class Model {

    private ArrayList<Player> playerList;
    private ArrayList<Move> moveList;
    private Tile[][] boardTiles;
    private Move[] legalMoves;
    private int selectedRow;
    private int selectedCol;
    private Player currentPlayer;
    public int currentPlayerPosition;

    private CheckersPiece selectedPiece;
    public static final int
            EMPTY = 0,
            RED = 1,
            RED_KING = 2,
            BLACK = 3,
            BLACK_KING = 4;

    /**
     * Start the Game
     */
    public void start() {
        playerList = new ArrayList<>();
        moveList = new ArrayList<>();
        boardTiles = new Tile[8][8];
        setBoard(boardTiles);
        setUpGame();

//        System.out.println("Model init");
//        Point p = new Point((75 * 3) + 50, (75 * 5) + 1);
//        CheckersPiece c1 = new CheckersPiece(5, 'D', p,false, false);
//        CheckersPiece c2 = new CheckersPiece(5, 'D', p,false, false);
//
//        CheckersPiece c3 = new CheckersPiece(5, 'D', p,false, false);
//        CheckersPiece c4 = new CheckersPiece(5, 'D', p,false, false);
//        Move a = new Move(c1, c2);
//        Move b = new Move(c2, c3);
//        moveList.add(a);
//        moveList.add(b);
//        System.out.println("Model initialized");
//        System.out.println(moveList.size());
    }


    public void setBoard(Tile[][] boardTiles) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Point p = new Point((75 * i) + 50, (75 * j) + 1);
                boolean b = (i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0);

                char col = (char) (j + 65);

                if (j >= 5 && b)
                    this.boardTiles[i][j] = new Tile(i, col, p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE); //true/false determines highlight
                else if (j < 3 && b)
                    this.boardTiles[i][j] = new Tile(i, col, p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
                else {
                    if (b)
                        this.boardTiles[i][j] = new Tile(i, col, p, Color.LIGHT_GRAY, 75, false, PieceType.NONE); //true/false determines highlight
                    else
                        this.boardTiles[i][j] = new Tile(i, col, p, Color.WHITE, 75, false, PieceType.NONE);
                }
            }
        }
    }


    // will show the possible moves
    public void showHighlight(CheckersPiece cp) {

        System.out.println("Model Called showHighlight");
        System.out.println(cp.getRow());
        System.out.println(cp.getCol() -65);
        Tile currentTile = boardTiles[cp.getRow()][(cp.getCol() - 65)];
        currentTile.setHighlight(!currentTile.isHighlight());
    }

    public void doClickSquare(int row, int col) {
        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                selectedRow = row;
                selectedCol = col;
                return;
            }

        if (selectedRow < 0) {
            return;
        }

        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
                    && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                doMakeMove(legalMoves[i]);
                return;
            }
    }

    public void doMakeMove(Move move) {
        makeMove(move);
        makeMove(move);

        if (move.isJump()) {
            legalMoves = getLegalJumpsFrom(currentPlayerPosition, move.toRow, move.toCol);
            if (legalMoves != null) {
//                if (currentPlayerPosition == gameData.RED)
//                    message.setText("RED:  You must continue jumping.");
//                else
//                    message.setText("BLACK:  You must continue jumping.");
                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                selectedCol = move.toCol;

                return;
            }
        }

        if (currentPlayerPosition == RED) {
            currentPlayerPosition = BLACK;
            legalMoves = getLegalMoves(currentPlayerPosition);
//            if (legalMoves == null)
//                message.gameOver("BLACK has no moves.  RED wins.");
//            else if (legalMoves[0].isJump())
//                message.setText("BLACK:  Make your move.  You must jump.");
//            else
//                message.setText("BLACK:  Make your move.");
        }
        else {
            currentPlayerPosition = RED;
            legalMoves = getLegalMoves(currentPlayerPosition);
//            if (legalMoves == null)
//                message.gameOver("RED has no moves.  BLACK wins.");
//            else if (legalMoves[0].isJump())
//                message.setText("RED:  Make your move.  You must jump.");
//            else
//                message.setText("RED:  Make your move.");
        }

        selectedRow = -1;

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
    }

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public CheckersPiece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(CheckersPiece selectedPiece) {
        this.selectedPiece = selectedPiece;
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

//    private int[][] board;  // board[r][c] is the contents of row r, column c.

    public void setUpGame() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ( row % 2 == col % 2 ) {
                    if (row < 3)
                        boardTiles[row][col].setCurrentCheckerPiece(RED) ;
                    else if (row > 4)
                        boardTiles[row][col].setCurrentCheckerPiece(BLACK);
                    else
                        boardTiles[row][col].setCurrentCheckerPiece(EMPTY);
                }
                else {
                    boardTiles[row][col].setCurrentCheckerPiece(EMPTY);
                }
            }
        }
    }  // end setUpGame()


//    public int pieceAt(int row, int col) {
//        // Return the contents of the square in the specified row and column.
//        return boardTiles[row][col];
//    }


//    public void setPieceAt(int row, int col, int piece) {
//        // Set the contents of the square in the specified row and column.
//        // piece must be one of the constants EMPTY, RED, BLACK, RED_KING,
//        // BLACK_KING.
//        boardTiles[row][col].getPiece() = piece;
//    }


    public void makeMove(Move move) {
        // Make the specified move.  It is assumed that move
        // is non-null and that the move it represents is legal.
        makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }


    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {

        boardTiles[toRow][toCol] = boardTiles[fromRow][fromCol];
        boardTiles[fromRow][fromCol].setCurrentCheckerPiece(EMPTY);
        if (fromRow - toRow == 2 || fromRow - toRow == -2) {
            // The move is a jump.  Remove the jumped piece from the board.
            int jumpRow = (fromRow + toRow) / 2;  // Row of the jumped piece.
            int jumpCol = (fromCol + toCol) / 2;  // Column of the jumped piece.
            boardTiles[jumpRow][jumpCol].setCurrentCheckerPiece(EMPTY);
        }
        if (toRow == 0 && boardTiles[toRow][toCol].getCurrentCheckerPiece() == BLACK)
            boardTiles[toRow][toCol].setCurrentCheckerPiece(BLACK_KING);
        if (toRow == 7 && boardTiles[toRow][toCol].getCurrentCheckerPiece() == RED)
            boardTiles[toRow][toCol].setCurrentCheckerPiece(RED_KING);
    }


    public Move[] getLegalMoves(int player) {


        if (player != RED && player != BLACK)
            return null;

        int playerKing;  // The constant representing a King belonging to player.
        if (player == RED)
            playerKing = RED_KING;
        else
            playerKing = BLACK_KING;

        Vector moves = new Vector();  // Moves will be stored in this vector.


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (boardTiles[row][col].getCurrentCheckerPiece() == player|| boardTiles[row][col].getCurrentCheckerPiece() == playerKing) {
                    if (canJump(player, row, col, row+1, col+1, row+2, col+2))
                        moves.addElement(new Move(row, col, row+2, col+2));
                    if (canJump(player, row, col, row-1, col+1, row-2, col+2))
                        moves.addElement(new Move(row, col, row-2, col+2));
                    if (canJump(player, row, col, row+1, col-1, row+2, col-2))
                        moves.addElement(new Move(row, col, row+2, col-2));
                    if (canJump(player, row, col, row-1, col-1, row-2, col-2))
                        moves.addElement(new Move(row, col, row-2, col-2));
                }
            }
        }

      /*  If any jump moves were found, then the user must jump, so we don't
          add any regular moves.  However, if no jumps were found, check for
          any legal regualar moves.  Look at each square on the board.
          If that square contains one of the player's pieces, look at a possible
          move in each of the four directions from that square.  If there is
          a legal move in that direction, put it in the moves vector.
      */

        if (moves.size() == 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (boardTiles[row][col].getCurrentCheckerPiece() == player || boardTiles[row][col].getCurrentCheckerPiece() == playerKing) {
                        if (canMove(player,row,col,row+1,col+1))
                            moves.addElement(new Move(row,col,row+1,col+1));
                        if (canMove(player,row,col,row-1,col+1))
                            moves.addElement(new Move(row,col,row-1,col+1));
                        if (canMove(player,row,col,row+1,col-1))
                            moves.addElement(new Move(row,col,row+1,col-1));
                        if (canMove(player,row,col,row-1,col-1))
                            moves.addElement(new Move(row,col,row-1,col-1));
                    }
                }
            }
        }

      /* If no legal moves have been found, return null.  Otherwise, create
         an array just big enough to hold all the legal moves, copy the
         legal moves from the vector into the array, and return the array. */

        if (moves.size() == 0)
            return null;
        else {
            Move[] moveArray = new Move[moves.size()];
            for (int i = 0; i < moves.size(); i++)
                moveArray[i] = (Move)moves.elementAt(i);
            return moveArray;
        }

    }  // end getLegalMoves


    public Move[] getLegalJumpsFrom(int player, int row, int col) {
        // Return a list of the legal jumps that the specified player can
        // make starting from the specified row and column.  If no such
        // jumps are possible, null is returned.  The logic is similar
        // to the logic of the getLegalMoves() method.
        if (player != RED && player != BLACK)
            return null;
        int playerKing;  // The constant representing a King belonging to player.
        if (player == RED)
            playerKing = RED_KING;
        else
            playerKing = BLACK_KING;
        Vector moves = new Vector();  // The legal jumps will be stored in this vector.
        if (boardTiles[row][col].getCurrentCheckerPiece() == player || boardTiles[row][col].getCurrentCheckerPiece() == playerKing) {
            if (canJump(player, row, col, row+1, col+1, row+2, col+2))
                moves.addElement(new Move(row, col, row+2, col+2));
            if (canJump(player, row, col, row-1, col+1, row-2, col+2))
                moves.addElement(new Move(row, col, row-2, col+2));
            if (canJump(player, row, col, row+1, col-1, row+2, col-2))
                moves.addElement(new Move(row, col, row+2, col-2));
            if (canJump(player, row, col, row-1, col-1, row-2, col-2))
                moves.addElement(new Move(row, col, row-2, col-2));
        }
        if (moves.size() == 0)
            return null;
        else {
            Move[] moveArray = new Move[moves.size()];
            for (int i = 0; i < moves.size(); i++)
                moveArray[i] = (Move)moves.elementAt(i);
            return moveArray;
        }
    }  // end getLegalMovesFrom()


    private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {
        // This is called by the two previous methods to check whether the
        // player can legally jump from (r1,c1) to (r3,c3).  It is assumed
        // that the player has a piece at (r1,c1), that (r3,c3) is a position
        // that is 2 rows and 2 columns distant from (r1,c1) and that
        // (r2,c2) is the square between (r1,c1) and (r3,c3).

        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
            return false;  // (r3,c3) is off the board.

        if (boardTiles[r3][c3].getCurrentCheckerPiece() != EMPTY)
            return false;  // (r3,c3) already contains a piece.

        if (player == RED) {
            if (boardTiles[r1][c1].getCurrentCheckerPiece() == RED && r3 > r1)
                return false;  // Regular red piece can only move  up.
            if (boardTiles[r2][c2].getCurrentCheckerPiece() != BLACK && boardTiles[r2][c2].getCurrentCheckerPiece() != BLACK_KING)
                return false;  // There is no black piece to jump.
            return true;  // The jump is legal.
        }
        else {
            if (boardTiles[r1][c1].getCurrentCheckerPiece() == BLACK && r3 < r1)
                return false;  // Regular black piece can only move downn.
            if (boardTiles[r2][c2].getCurrentCheckerPiece() != RED && boardTiles[r2][c2].getCurrentCheckerPiece() != RED_KING)
                return false;  // There is no red piece to jump.
            return true;  // The jump is legal.
        }

    }  // end canJump()


    private boolean canMove(int player, int r1, int c1, int r2, int c2) {
        // This is called by the getLegalMoves() method to determine whether
        // the player can legally move from (r1,c1) to (r2,c2).  It is
        // assumed that (r1,r2) contains one of the player's pieces and
        // that (r2,c2) is a neighboring square.

        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
            return false;  // (r2,c2) is off the board.

        if (boardTiles[r2][c2].getCurrentCheckerPiece() != EMPTY)
            return false;  // (r2,c2) already contains a piece.

        if (player == RED) {
            if (boardTiles[r1][c1].getCurrentCheckerPiece() == RED && r2 > r1)
                return false;  // Regualr red piece can only move down.
            return true;  // The move is legal.
        }
        else {
            if (boardTiles[r1][c1].getCurrentCheckerPiece() == BLACK && r2 < r1)
                return false;  // Regular black piece can only move up.
            return true;  // The move is legal.
        }

    }  // end canMove()
}