package ChessAPI.Pieces;

import ChessAPI.Game.PiecePosition;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceName;
import ChessAPI.PiecesUtils.ChessPieceSide;
import ChessAPI.PiecesUtils.BoardException;

public class King extends ChessPiece {

    /**
     * Constructor for the King piece. This will only need to use color and position
     * variables.
     * 
     * @param name
     *            Not needed / not used might make another constructor just for the
     *            king.
     * @param color
     *            Color of the king (BLACK or WHITE)
     * @param side
     *            Not needed / not used might make another constructor just for the
     *            king.
     * @param position
     *            Where on the board the king will be.
     * @throws BoardException
     *             Newly constructed exception to handle board cases.
     */
    public King(ChessPieceName name, ChessPieceColor color, ChessPieceSide side, PiecePosition position)
            throws BoardException {
        super(ChessPieceName.KING, color, side, position);
        int size = position.getSize();
        if (ChessPieceColor.WHITE == color) {
            this.setPostion(4, size - 1);
        } else {
            this.setPostion(4, 0);
        }
        position.occupyPosition(this, xPosition, yPosition);
    }

    @Override
    /**
     * Overridden method from the abstract to describe the moment of the King.
     */
    public void move(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(xPosition - newXPosition);
        int changeInY = Math.abs(yPosition - newYPosition);
        boolean moveCondition1 = changeInX == 1 && changeInY == 1;
        boolean moveCondition2 = changeInX == 1 && changeInY == 0;
        boolean moveCondition3 = changeInX == 0 && changeInY == 0;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2 || moveCondition3, this, newXPosition, newYPosition,
                position);
        UtilityFunctions.makeValidMove(this, newXPosition, newYPosition, position);

    }

    @Override
    /**
     * Overridden method from the abstract class to describe how the king will
     * attack. This is virtually the same as the move.
     */
    public ChessPiece attack(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(xPosition - newXPosition);
        int changeInY = Math.abs(yPosition - newYPosition);
        boolean moveCondition1 = changeInX == 1 && changeInY == 1;
        boolean moveCondition2 = changeInX == 1 && changeInY == 0;
        boolean moveCondition3 = changeInX == 0 && changeInY == 0;
        ChessPiece capturedPiece;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2 || moveCondition3, this, newXPosition, newYPosition,
                position);
        capturedPiece = UtilityFunctions.moveOrCapture(this, newXPosition, newYPosition, position);
        return capturedPiece;
    }

}
