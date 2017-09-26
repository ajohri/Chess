package ChessAPI.Pieces;

import ChessAPI.Game.PiecePosition;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceName;
import ChessAPI.PiecesUtils.ChessPieceSide;
import ChessAPI.PiecesUtils.BoardException;

public class Bishop extends ChessPiece {

    /**
     * This is the constructor for the Bishop class. Everything will be used.
     * 
     * @param name
     *            Name of the piece for this class it will always be BISHOP.
     * @param color
     *            This will depend on the player and what color they are.
     * @param side
     *            This will depend if we are talking about the right or left size
     *            bishop.
     * @param position
     *            This is the shared board object that will keep track of where all
     *            the pices are going to be.
     * @throws BoardException
     */
    public Bishop(ChessPieceName name, ChessPieceColor color, ChessPieceSide side, PiecePosition position)
            throws BoardException {
        super(ChessPieceName.BISHOP, color, side, position);
        int size = position.getSize();
        if (color == ChessPieceColor.WHITE) {
            UtilityFunctions.setInitialPositionWithSide(this, 2, size - 1, size - 3, size - 1);
        } else {
            UtilityFunctions.setInitialPositionWithSide(this, 2, 0, size - 3, 0);
        }
        position.occupyPosition(this, xPosition, yPosition);
    }

    @Override
    /**
     * This is the overriden method for the moment of the bishop.
     */
    public void move(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - xPosition);
        boolean moveCondition = changeInX == changeInY;

        UtilityFunctions.checkMove(moveCondition, this, newXPosition, newYPosition, position);
        UtilityFunctions.makeValidMove(this, newXPosition, newYPosition, position);
    }

    @Override
    /**
     * This is the override method for the attack of the bishop. This will be
     * virtually the same as the move except the ending calls a different function.
     */
    public ChessPiece attack(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - xPosition);
        boolean moveCondition = changeInX == changeInY;
        ChessPiece capturedPiece;

        UtilityFunctions.checkMove(moveCondition, this, newXPosition, newYPosition, position);
        capturedPiece = UtilityFunctions.moveOrCapture(this, newXPosition, newYPosition, position);
        return capturedPiece;
    }

}
