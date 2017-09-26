package ChessAPI.Game;

import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.*;

/**
 * The board position manager, used to manage the positions of all of the Chess
 * pieces
 */
public class PiecePosition {

    private ChessPiece[][] positions;

    private int boardSize;

    /**
     * Default constructor for board manager, will default to a size of N = 8
     * Might need to add pieces for testing purpose.
     */
    public PiecePosition() {
        positions = new ChessPiece[8][8];
        boardSize = 8;
    }

    /**
     * Determines if a particular position is occupied by a piece
     * 
     * @param xPos
     *            The x-position of the piece
     * @param yPos
     *            The y-position of the piece
     * @return Whether or not the position is occupied
     */
    public Boolean isOccupied(int xPos, int yPos) {
        return positions[xPos][yPos] != null;
    }

    /**
     * Occupies a position in the position manager
     * 
     * @param piece
     *            The desired piece to occupy the position
     * @param yPos
     *            The desired y-position in the manager
     * @param xPos
     *            The desired x-position in the manager
     * @throws InvalidPositionException
     *             thrown if the position is currently occupied
     */
    public void occupyPosition(ChessPiece piece, int yPos, int xPos) throws BoardException {
        if (isOccupied(xPos, yPos)) {
            throw new BoardException("Position is currently occupied by a piece");
        } else {
            positions[xPos][yPos] = piece;
        }
    }

    /**
     * Allows a piece to leave
     * 
     * @param piece
     *            The desired piece to leave
     */
    public void leavePosition(ChessPiece piece) {
        positions[piece.xValue()][piece.yValue()] = null;
    }

    /**
     * Getter for the size of the board
     * 
     * @return The size of the board
     */
    public int getSize() {
        return boardSize;
    }

    /**
     * Getter for getting piece at particular position
     * 
     * @param xPos
     *            X-position we are reading from
     * @param yPos
     *            Y-position we are reading from
     * @return The piece at position (x,y) in the board
     */
    public ChessPiece getPieceAtPosition(int xPos, int yPos) {
        return positions[xPos][yPos];
    }
}