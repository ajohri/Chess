package ChessAPI.Pieces;

import ChessAPI.Game.PiecePosition;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceName;
import ChessAPI.PiecesUtils.ChessPieceSide;
import ChessAPI.PiecesUtils.BoardException;

public class Queen extends ChessPiece {

    /**
     * This is the constructor for the Queen
     * 
     * @param name
     *            This get a hard coded value
     * @param color
     *            This is just used to determine which side of the board the queen
     *            will be on.
     * @param side
     *            This is not used for the Queen as there is only one Queen.
     * @param position
     *            Board that handles all of the positions for the pieces
     * @throws BoardException
     *             newly created exception to handle board issues.
     */
    public Queen(ChessPieceName name, ChessPieceColor color, ChessPieceSide side, PiecePosition position)
            throws BoardException {
        super(name, color, side, position);
        int size = position.getSize();
        if (color == ChessPieceColor.WHITE) {
            this.setPostion(3, size - 1);
        } else {
            this.setPostion(3, 0);
        }
        position.occupyPosition(this, xPosition, yPosition);
    }

    @Override
    /**
     * Overridden from the abstract class and this is the definition to how the
     * Queen moves.
     */
    public void move(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - yPosition);
        boolean moveCondition1 = changeInX == changeInY;
        boolean moveCondition2 = changeInX == 0 && changeInY > 0;
        boolean moveCondition3 = changeInX > 0 && changeInY == 0;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2 || moveCondition3, this, newXPosition, newYPosition,
                position);
        UtilityFunctions.makeValidMove(this, newXPosition, newYPosition, position);
    }

    @Override
    /**
     * Overridden from the abstract class and this is the definition of how the
     * Queen attacks. Virtually similar to how the queen moves.
     */
    public ChessPiece attack(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(newXPosition - xPosition);
        int changeInY = Math.abs(newYPosition - yPosition);
        boolean moveCondition1 = changeInX == changeInY;
        boolean moveCondition2 = changeInX == 0 && changeInY > 0;
        boolean moveCondition3 = changeInX > 0 && changeInY == 0;
        ChessPiece captured;

        UtilityFunctions.checkMove(moveCondition1 || moveCondition2 || moveCondition3, this, newXPosition, newYPosition,
                position);
        captured = UtilityFunctions.captureAndMove(this, newXPosition, newYPosition, position);
        return captured;
    }

}
