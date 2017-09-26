package ChessAPI.Pieces;

import ChessAPI.Game.PiecePosition;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceName;
import ChessAPI.PiecesUtils.ChessPieceSide;
import ChessAPI.PiecesUtils.BoardException;

public class Rook extends ChessPiece {

    /**
     * Constructor for the Rook piece.
     * 
     * @param name
     *            Gets overridden with a hard coded value.
     * @param color
     *            This helps determine which side of the board the pieces will be
     *            on.
     * @param side
     *            This helps differentiate the right and left side rook peices.
     * @param position
     *            Common Board that contains the information for all the positions
     *            of the pieces.
     * @throws BoardException
     *             Newly created to handle board issues.
     */
    public Rook(ChessPieceName name, ChessPieceColor color, ChessPieceSide side, PiecePosition position)
            throws BoardException {
        super(name, color, side, position);
        int size = position.getSize();
        if (color == ChessPieceColor.WHITE) {
            UtilityFunctions.setInitialPositionWithSide(this, 0, size - 1, size - 1, size - 1);
        } else {
            UtilityFunctions.setInitialPositionWithSide(this, 0, 0, size - 1, 0);
        }
        position.occupyPosition(this, xPosition, yPosition);
    }

    @Override
    /**
     * Overridden method from the abstract class that describes how a rook moves.
     */
    public void move(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);

        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - yPosition);
        boolean moveCondition1 = changeInX > 0 && changeInY == 0;
        boolean moveCondition2 = changeInX == 0 && changeInY > 0;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2, this, newXPosition, newYPosition, position);
        UtilityFunctions.makeValidMove(this, newXPosition, newYPosition, position);
    }

    @Override
    /**
     * Overridden method from the abstract class that describes how the rook moves.
     * Virtually similar to how it moves.
     */
    public ChessPiece attack(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);

        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - yPosition);
        boolean moveCondition1 = changeInX > 0 && changeInY == 0;
        boolean moveCondition2 = changeInX == 0 && changeInY > 0;
        ChessPiece captured;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2, this, newXPosition, newYPosition, position);
        captured = UtilityFunctions.moveOrCapture(this, newXPosition, newYPosition, position);
        return captured;
    }

}
