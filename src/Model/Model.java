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
    private int currentPlayerPosition = 0;

    private CheckersPiece selectedPiece;
    private Tile selectedTile;


    /**
     * Start the Game
     */
    public void start() {
        playerList = new ArrayList<>();
        moveList = new ArrayList<>();
        boardTiles = new Tile[8][8];
        setBoard(boardTiles);
    }

    public void setBoard(Tile[][] boardTiles) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if (row % 2 == col % 2) {
                    if (row < 3)
                        boardTiles[row][col] = new Tile(row, col, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE);
                    else if (row > 4)
                        boardTiles[row][col] = new Tile(row, col, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
                    else
                        boardTiles[row][col] = new Tile(row, col, Color.LIGHT_GRAY, 75, false, PieceType.NONE);
                } else {
                    boardTiles[row][col] = new Tile(row, col, Color.WHITE, 75, false, PieceType.NONE);
                }
            }
        }
    }


    // will show the possible moves
    public void showHighlight(CheckersPiece cp) {
        selectedTile = boardTiles[cp.getRow()][cp.getCol()];
        if (selectedTile.isHighlight()) {
            System.out.println("Turn off");
            selectedPiece = null;

        } else {
            System.out.println("Turn on");
            selectedPiece = cp;
            legalMoves = getLegalMoves(cp);
        }

        selectedTile.setHighlight(!selectedTile.isHighlight());
    }

    public void turnOffHighlight() {
        selectedPiece = null;
    }

    public void doClickSquare(Move move) {
        int row = move.getToRow();
        int col = move.getToCol();
//        for (int i = 0; i < legalMoves.length; i++) {
//            if (legalMoves[i].fromRow == selectedPiece.getRow()
//                    && legalMoves[i].fromCol == selectedPiece.getCol()) {
//                selectedRow = row;
//                selectedCol = col;
//                return;
//            }
//        }
//
//        if (selectedRow < 0) {
//            return;
//        }
        System.out.println("DoClickSquare: " + selectedPiece.getPieceType());

        for (int i = 0; i < legalMoves.length; i++) {
            if (
                legalMoves[i].fromRow == selectedPiece.getRow() &&
                legalMoves[i].fromCol == selectedPiece.getCol() &&
                legalMoves[i].toRow == row &&
                legalMoves[i].toCol == col
            ) {
                System.out.println("DoClickSquare: " + legalMoves[i]);
                doMakeMove(legalMoves[i]);
                return;
            }
        }
    }

    public void doMakeMove(Move move) {
        System.out.println("[doMakeMove]: " + move.toString());
        makeMove(move);
//        makeMove(move);
        System.out.println("[doMakeMove]: " + move.isJump());

        if (move.isJump()) {
            System.out.println("move is a jump");
            legalMoves = getLegalJumpsFrom(selectedPiece.getPieceType(), move.toRow, move.toCol);
            if (legalMoves != null) {
                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                selectedCol = move.toCol;
                return;
            }
        }

        if (currentPlayerPosition == 0) {
            currentPlayerPosition = 1;
            legalMoves = getLegalMoves(selectedPiece);
        }
        else {
            currentPlayerPosition = 0;
            legalMoves = getLegalMoves(selectedPiece);
        }
        System.out.println("Current Player" + currentPlayerPosition);

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

        // Add to history of Move
        moveList.add(new Move(move.getFromRow(), move.getFromCol(), move.getToRow(), move.getToCol()));

        // Unhighlight
        selectedTile.setHighlight(!selectedTile.isHighlight());
    }

    public void makeMove(Move move) {
        System.out.println("[MakeMove 1]: " + boardTiles[move.fromRow][move.fromCol].getCheckerPieceType());
        makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }

    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {

        Tile fromTile = boardTiles[fromRow][fromCol];

        System.out.println("[MakeMove 2]: " + boardTiles[fromRow][fromCol].getCheckerPieceType());

        // re-set toTile
        boardTiles[toRow][toCol] = new Tile(toRow, toCol, Color.LIGHT_GRAY, 75, false, fromTile.getCheckerPieceType());
        boardTiles[fromRow][fromCol] = new Tile(fromRow, fromCol, Color.LIGHT_GRAY, 75, false, PieceType.NONE);

        if (fromRow - toRow == 2 || fromRow - toRow == -2) {
            System.out.println("The move is a jump.  Remove the jumped piece from the board.");

            int jumpRow = (fromRow + toRow) / 2;  // Row of the jumped piece.
            int jumpCol = (fromCol + toCol) / 2;  // Column of the jumped piece.
            boardTiles[jumpRow][jumpCol] = new Tile(jumpRow, jumpCol, Color.LIGHT_GRAY, 75, false, PieceType.NONE);
        }
        // Convert to a King
        if (toRow == 0 &&
                boardTiles[toRow][toCol].getCheckerPieceType() == PieceType.REDPIECE) {
            System.out.println("Convert to Red King");
            boardTiles[toRow][toCol] = new Tile(toRow, toCol, Color.LIGHT_GRAY, 75, false, PieceType.REDKING);
        }
        if (toRow == 7 &&
                boardTiles[toRow][toCol].getCheckerPieceType() == PieceType.BLACKPIECE) {
            System.out.println("Convert to Black King");
            boardTiles[toRow][toCol] = new Tile(toRow, toCol, Color.LIGHT_GRAY, 75, false, PieceType.BLACKKING);
        }
    }


    public Move[] getLegalMoves(CheckersPiece checkersPiece) {

        PieceType pt = checkersPiece.getPieceType();

        if (pt != PieceType.REDPIECE && pt != PieceType.BLACKPIECE)
            return null;

        PieceType playerKing;  // The constant representing a King belonging to player.
        if (pt == PieceType.REDPIECE)
            playerKing = PieceType.REDKING;
        else
            playerKing = PieceType.BLACKKING;

        Vector<Move> moves = new Vector<>();  // Moves will be stored in this vector.


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if (boardTiles[row][col].getCheckerPieceType() == pt ||
                        boardTiles[row][col].getCheckerPieceType() == playerKing) {
                    if (canJump(pt, row, col, row + 1, col + 1, row + 2, col + 2))
                        moves.addElement(new Move(row, col, row + 2, col + 2));
                    if (canJump(pt, row, col, row - 1, col + 1, row - 2, col + 2))
                        moves.addElement(new Move(row, col, row - 2, col+2));
                    if (canJump(pt, row, col, row + 1, col - 1, row + 2, col - 2))
                        moves.addElement(new Move(row, col, row + 2, col - 2));
                    if (canJump(pt, row, col, row - 1, col - 1, row - 2, col - 2))
                        moves.addElement(new Move(row, col, row - 2, col - 2));
                }
            }
        }

        System.out.println("[Model-GetLegalMove]: " + pt.toString());
        if (moves.size() == 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {

                    if (boardTiles[row][col].getCheckerPieceType() == pt ||
                            boardTiles[row][col].getCheckerPieceType() == playerKing) {
                        if (canMove(pt, row, col,row + 1,col + 1))
                            moves.addElement(new Move(row, col,row + 1,col + 1));
                        if (canMove(pt, row, col,row - 1,col+1))
                            moves.addElement(new Move(row, col,row-1,col+1));
                        if (canMove(pt, row, col,row + 1,col - 1))
                            moves.addElement(new Move(row, col,row + 1,col - 1));
                        if (canMove(pt, row, col,row - 1,col-1))
                            moves.addElement(new Move(row, col,row - 1,col - 1));
                    }
                }
            }
        }

        if (moves.size() == 0)
            return null;
        else {
            Move[] moveArray = new Move[moves.size()];
            for (int i = 0; i < moves.size(); i++) {
                System.out.println(moveArray[i]);
                moveArray[i] = moves.elementAt(i);
            }
            return moveArray;
        }

    }  // end getLegalMoves


    public Move[] getLegalJumpsFrom(PieceType pieceType, int row, int col) {
        if (pieceType != PieceType.REDPIECE && pieceType != PieceType.BLACKPIECE)
            return null;
        PieceType playerKing;  // The constant representing a King belonging to player.
        if (pieceType == PieceType.REDPIECE)
            playerKing = PieceType.REDKING;
        else
            playerKing = PieceType.BLACKKING;
        Vector<Move> moves = new Vector<>();  // The legal jumps will be stored in this vector.
        if (boardTiles[row][col].getCheckerPieceType() == pieceType || boardTiles[row][col].getCheckerPieceType() == playerKing) {
            if (canJump(pieceType, row, col,
                    row + 1, col + 1,
                    row + 2, col + 2))
                moves.addElement(new Move(row, col, row + 2, col + 2));
            if (canJump(pieceType, row, col,
                    row - 1, col + 1,
                    row - 2, col + 2))
                moves.addElement(new Move(row, col, row-2, col+2));
            if (canJump(pieceType, row, col,
                    row + 1, col - 1,
                    row + 2, col - 2))
                moves.addElement(new Move(row, col, row+2, col-2));
            if (canJump(pieceType, row, col,
                    row - 1, col - 1,
                    row - 2, col - 2))
                moves.addElement(new Move(row, col, row-2, col-2));
        }
        if (moves.size() == 0)
            return null;
        else {
            Move[] moveArray = new Move[moves.size()];
            for (int i = 0; i < moves.size(); i++)
                moveArray[i] = moves.elementAt(i);
            return moveArray;
        }
    }  // end getLegalMovesFrom()


    private boolean canJump(PieceType pieceType, int r1, int c1, int r2, int c2, int r3, int c3) {

        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8) {
            System.out.println("[Model-CanJump] case 1: (" + r1 + ", " + c1 + ") - (" + r2 + ", " + c2 + ")");
            return false;  // (r3,c3) is off the board.
        }
        if (boardTiles[r3][c3].containsChecker()) {
            System.out.println("[Model-CanJump] case 2: (" + r1 + ", " + c1 + ") - (" + r2 + ", " + c2 + ") - (" + r3 + ", " + c3 + ")");
            return false;  // (r3,c3) already contains a piece.
        }
        if (pieceType == PieceType.REDPIECE) {
            if (boardTiles[r1][c1].getCheckerPieceType() == PieceType.REDPIECE &&
                    r3 < r1)
                return false;  // Regular red piece can only move  up.
            if (boardTiles[r2][c2].getCheckerPieceType() != PieceType.BLACKPIECE &&
                    boardTiles[r2][c2].getCheckerPieceType() != PieceType.BLACKKING)
                return false;  // There is no black piece to jump.
            System.out.println("[Model-CanJump] case 3: (" + r1 + ", " + c1 + ") - (" + r2 + ", " + c2 + ") - (" + r3 + ", " + c3 + ")");
            return true;  // The jump is legal.
        }
        else {
            if (boardTiles[r1][c1].getCheckerPieceType() == PieceType.BLACKPIECE &&
                    r3 > r1)
                return false;  // Regular black piece can only move downn.
            if (boardTiles[r2][c2].getCheckerPieceType() != PieceType.REDPIECE &&
                    boardTiles[r2][c2].getCheckerPieceType() != PieceType.REDKING)
                return false;  // There is no red piece to jump.
            System.out.println("[Model-CanJump] case 4: (" + r1 + ", " + c1 + ") - (" + r2 + ", " + c2 + ") - (" + r3 + ", " + c3 + ")");
            return true;  // The jump is legal.
        }

    }  // end canJump()


    private boolean canMove(PieceType pieceType, int r1, int c1, int r2, int c2) {

        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8) {
            return false;  // (r2,c2) is off the board.
        }

        if (boardTiles[r2][c2].containsChecker()) {
            return false;  // (r2,c2) already contains a piece.
        }

        if (pieceType == PieceType.REDPIECE) {

            if (boardTiles[r1][c1].getCheckerPieceType() == PieceType.REDPIECE && r2 > r1)
                return false;  // Regualr red piece can only move down.
            System.out.println("[Model-CanMove] case 3: (" + r1 + ", " + c1 + ") - (" + r2 + ", " + c2 + ")");
            return true;  // The move is legal.
        }else {
            if (boardTiles[r1][c1].getCheckerPieceType() == PieceType.BLACKPIECE && r2 < r1)
                return false;  // Regular black piece can only move up.
//            System.out.println("[Model-CanMove] case 4: (" + r1 + ", " + c1 + ") - (" + r2 + ", " + c2 + ")");

            return true;  // The move is legal.
        }
    }  // end canMove()

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
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
}