package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Main Class that store all Game Logic, and information
 */
public class Model {
    private ArrayList<Move> moveList;
    private Tile[][] boardTiles;
    private Move[] legalMoves;
    private int currentPlayerPosition = 0;

    private CheckersPiece selectedPiece;
    private Tile selectedTile;

    private int totalBlackCount = 12;
    private int totalRedCount = 12;

    private int isWinner = -1;


    /**
     * Start the Game
     */
    public void start() {
        moveList = new ArrayList<>();
        boardTiles = new Tile[8][8];
        setBoard(boardTiles);
        totalBlackCount = 12;
        totalRedCount = 12;
        selectedPiece = null;
        selectedTile = null;
        currentPlayerPosition = 0;
    }

    private void setBoard(Tile[][] boardTiles) {
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
            selectedPiece = null;
        } else {
            selectedPiece = cp;
            legalMoves = getLegalMoves(cp);
        }

        selectedTile.setHighlight(!selectedTile.isHighlight());
    }

    public void doClickSquare(Move move) {
        int row = move.getToRow();
        int col = move.getToCol();

        for (Move legalMove : legalMoves) {
            if (
                    legalMove.fromRow == selectedPiece.getRow() &&
                            legalMove.fromCol == selectedPiece.getCol() &&
                            legalMove.toRow == row &&
                            legalMove.toCol == col
            ) {
                doMakeMove(legalMove);
                return;
            }
        }
    }

    public void doMakeMove(Move move) {
        makeMove(move);

        if (move.isJump()) {
            legalMoves = getLegalJumpsFrom(selectedPiece.getPieceType(), move.toRow, move.toCol);
        }

        if (currentPlayerPosition == 0) {
            currentPlayerPosition = 1;
            legalMoves = getLegalMoves(selectedPiece);
        }
        else {
            currentPlayerPosition = 0;
            legalMoves = getLegalMoves(selectedPiece);
        }

        // Add to history of Move
        moveList.add(new Move(move.getFromRow(), move.getFromCol(), move.getToRow(), move.getToCol()));

        // Unhighlight
        selectedTile.setHighlight(!selectedTile.isHighlight());

        if (totalRedCount <= 0)
            isWinner = 1;

        else if (totalBlackCount <= 0)
            isWinner = 0;
    }

    private void makeMove(Move move) {
        makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }

    private void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        Tile fromTile = boardTiles[fromRow][fromCol];

        // re-set toTile
        boardTiles[toRow][toCol] = new Tile(toRow, toCol, Color.LIGHT_GRAY, 75, false, fromTile.getCheckerPieceType());
        boardTiles[fromRow][fromCol] = new Tile(fromRow, fromCol, Color.LIGHT_GRAY, 75, false, PieceType.NONE);

        if (fromRow - toRow == 2 || fromRow - toRow == -2) {
            int jumpRow = (fromRow + toRow) / 2;  // Row of the jumped piece.
            int jumpCol = (fromCol + toCol) / 2;  // Column of the jumped piece.

            if (boardTiles[jumpRow][jumpCol].getCp().getOuter() == Color.RED)
                totalRedCount--;
            else
                totalBlackCount--;



            boardTiles[jumpRow][jumpCol] = new Tile(jumpRow, jumpCol, Color.LIGHT_GRAY, 75, false, PieceType.NONE);

        }
        // Convert to a King
        if (toRow == 0 &&
                boardTiles[toRow][toCol].getCheckerPieceType() == PieceType.REDPIECE) {
            boardTiles[toRow][toCol] = new Tile(toRow, toCol, Color.LIGHT_GRAY, 75, false, PieceType.REDKING);
        }
        if (toRow == 7 &&
                boardTiles[toRow][toCol].getCheckerPieceType() == PieceType.BLACKPIECE) {
            boardTiles[toRow][toCol] = new Tile(toRow, toCol, Color.LIGHT_GRAY, 75, false, PieceType.BLACKKING);
        }
    }


    private Move[] getLegalMoves(CheckersPiece checkersPiece) {

        PieceType pt = checkersPiece.getPieceType();

        Vector<Move> moves = new Vector<>();  // Moves will be stored in this vector.

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (boardTiles[row][col].getCheckerPieceType() == pt) {
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

        if (moves.size() == 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (boardTiles[row][col].getCheckerPieceType() == pt) {
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
                moveArray[i] = moves.elementAt(i);
            }
            return moveArray;
        }
    }


    private Move[] getLegalJumpsFrom(PieceType pieceType, int row, int col) {
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

        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
            return false;

        if (boardTiles[r3][c3].containsChecker())
            return false;  // (r3,c3) already contains a piece.

        if (pieceType == PieceType.REDPIECE || pieceType == PieceType.REDKING) {
            if (boardTiles[r1][c1].getCheckerPieceType() == PieceType.REDPIECE &&
                    r3 > r1)
                return false;  // Regular red piece can only move  up.
            return boardTiles[r2][c2].getCheckerPieceType() == PieceType.BLACKPIECE ||
                    boardTiles[r2][c2].getCheckerPieceType() == PieceType.BLACKKING;  // There is no black piece to jump.
// The jump is legal.
        }
        else {
            if (boardTiles[r1][c1].getCheckerPieceType() == PieceType.BLACKPIECE &&
                    r3 < r1)
                return false;  // Regular black piece can only move downn.
            return boardTiles[r2][c2].getCheckerPieceType() == PieceType.REDPIECE ||
                    boardTiles[r2][c2].getCheckerPieceType() == PieceType.REDKING;  // There is no red piece to jump.
// The jump is legal.
        }

    }  // end canJump()


    private boolean canMove(PieceType pieceType, int r1, int c1, int r2, int c2) {
        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
            return false;  // (r2,c2) is off the board.


        if (boardTiles[r2][c2].containsChecker())
            return false;  // (r2,c2) already contains a piece.


        if (pieceType == PieceType.REDPIECE)
            return boardTiles[r1][c1].getCheckerPieceType() != PieceType.REDPIECE || r2 <= r1;  // Regualr red piece can only move down.
// The move is legal.
        else
            return boardTiles[r1][c1].getCheckerPieceType() != PieceType.BLACKPIECE || r2 >= r1;  // Regular black piece can only move up.
// The move is legal.
    }  // end canMove()

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public CheckersPiece getSelectedPiece() {
        return selectedPiece;
    }

    public ArrayList<Move> getMoveList() {
        return moveList;
    }

    public int isWinner() {
        return isWinner;
    }

    public Tile[][] getBoard() {
        return boardTiles;
    }
}