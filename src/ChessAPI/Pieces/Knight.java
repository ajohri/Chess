package ChessAPI.Pieces;

import ChessAPI.Game.PiecePosition;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceName;
import ChessAPI.PiecesUtils.ChessPieceSide;
import ChessAPI.PiecesUtils.BoardException;

public class Knight extends ChessPiece {

    /**
     * This is the constructor of the Knight class
     * 
     * @param name
     *            Gets manually put in so it doesn't matter.
     * @param color
     *            Color of the piece will determine which side of the board it is
     *            on.
     * @param side
     *            This will determine if we are dealing with the left of or the
     *            right Knight.
     * @param position
     *            This is the common board that will maintain all the positions of
     *            the all the pieces.
     * @throws BoardException
     *             Newly created exception to handle board issues.
     */
    public Knight(ChessPieceName name, ChessPieceColor color, ChessPieceSide side, PiecePosition position)
            throws BoardException {
        super(ChessPieceName.KNIGHT, color, side, position);
        int size = position.getSize();
        if (color == ChessPieceColor.WHITE) {
            UtilityFunctions.setInitialPositionWithSide(this, 1, size - 1, size - 2, size - 1);
        } else {
            UtilityFunctions.setInitialPositionWithSide(this, 1, 0, size - 2, 0);
        }
        position.occupyPosition(this, xPosition, yPosition);
    }

    @Override
    /**
     * Overridden method for how the Knight will move.
     */
    public void move(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - yPosition);
        boolean moveCondition1 = changeInX != 2 && changeInY != 1;
        boolean moveCondition2 = changeInX != 1 && changeInY != 2;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2, this, newXPosition, newYPosition, position);
        UtilityFunctions.makeValidMove(this, newXPosition, newYPosition, position);
    }

    @Override
    /**
     * Overridden method for how the Knight will attack. This is virtually the same
     * as the move but with an additional step.
     */
    public ChessPiece attack(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - yPosition);
        boolean moveCondition1 = changeInX != 2 && changeInY != 1;
        boolean moveCondition2 = changeInX != 1 && changeInY != 2;
        ChessPiece captured;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2, this, newXPosition, newYPosition, position);
        captured = UtilityFunctions.captureAndMove(this, newXPosition, newYPosition, position);
        return captured;
    }

}
