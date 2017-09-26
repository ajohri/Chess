package ChessAPI.Pieces;

import ChessAPI.Game.*;
import ChessAPI.PiecesUtils.*;

public class Pawn extends ChessPiece {

    boolean moved = false;
    int starting_position;

    /**
     * Constructor for the Pawn piece.
     * 
     * @param name
     *            This is put in with a hard coded value.
     * @param color
     *            Helps to determine which side of the board the Pawns will be on.
     * @param side
     *            Not needed / Not used in the constructor since there are multiple
     *            pawns in the same line.
     * @param position
     *            Board that keeps track of all the positions of the pieces.
     */
    public Pawn(ChessPieceName name, ChessPieceColor color, ChessPieceSide side, PiecePosition position) {
        super(name, color, side, position);
        int size = position.getSize();
        if (color == ChessPieceColor.WHITE) {
            starting_position = size - 2;
        } else {
            starting_position = 1;
        }
    }

    /**
     * This helps with determining which side the pawns belong to.
     * 
     * @param startingX
     *            The only changing coordinate with the pawns are the x ones.
     * @param position
     *            Board that keeps track of all the positions.
     * @throws BoardException
     *             Newly created to handle board issues.
     */
    public void setPawnStartingPosition(int startingX, PiecePosition position) throws BoardException {
        xPosition = startingX;
        yPosition = starting_position;
        position.occupyPosition(this, xPosition, yPosition);
    }

    @Override
    /**
     * Overridden from the abstract class. This handles the movement for the pawn.
     */
    public void move(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        int changeInX = Math.abs(xPosition - newXPosition);
        int changeInY = Math.abs(yPosition - newYPosition);
        boolean moveCondition1 = (changeInX == 0 && changeInY == 1);
        boolean moveCondition2 = (changeInX == 0 && changeInY == 2);

        if (moved) {
            UtilityFunctions.checkMove(moveCondition1 || moveCondition2, this, newXPosition, newYPosition, position);
        } else {
            UtilityFunctions.checkMove(moveCondition1 || moveCondition2, this, newXPosition, newYPosition, position);
            moved = true;
        }
        UtilityFunctions.makeValidMove(this, newXPosition, newYPosition, position);

    }

    @Override
    /**
     * Overridden from the abstract class. This is the only piece that has a
     * different attack method than the rest.
     */
    public ChessPiece attack(int newXPosition, int newYPosition, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newXPosition, newYPosition);
        ChessPiece capturedPiece;
        int changeInX = Math.abs(xPosition - newXPosition);
        int changeInY = Math.abs(yPosition - newYPosition);
        boolean moveCondition = (changeInX == 1 && changeInY == 1);

        UtilityFunctions.checkMove(moveCondition, this, newXPosition, newYPosition, position);
        capturedPiece = UtilityFunctions.moveOrCapture(this, newXPosition, newYPosition, position);
        return capturedPiece;
    }

}
