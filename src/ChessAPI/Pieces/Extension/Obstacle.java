package ChessAPI.Pieces.Extension;

import ChessAPI.Game.PiecePosition;
import ChessAPI.Pieces.ChessPiece;
import ChessAPI.Pieces.UtilityFunctions;
import ChessAPI.PiecesUtils.BoardException;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceName;
import ChessAPI.PiecesUtils.ChessPieceSide;

public class Obstacle extends ChessPiece {
    /**
     * This is the constructor of the Obstacle piece that just wants to be annoying
     * @param color The desired color of the Obstacle
     * @param side The desired side of the Obstacle (left, right)
     * @param position The board position manager
     * @throws Exception thrown if invalid piece given
     */
    public Obstacle(ChessPieceColor color, ChessPieceSide side, PiecePosition position) throws Exception {
        super(ChessPieceName.OBSTACLE, color, side, position);

        int size = position.getSize();
        if (color == ChessPieceColor.WHITE) {
            UtilityFunctions.setInitialPositionWithSide(this, 4, size - 4, size - 3, size - 4);
        } else {
            UtilityFunctions.setInitialPositionWithSide(this, 4, 0, size - 3, 0);
        }
        position.occupyPosition(this, yPosition, xPosition);
    }

    /**
     * Makes sure that the play can not move the obstacle
     * @param newX - should be the same.
     * @param newY - should be the same
     * @param position The board position manager
     * @throws Exception thrown if invalid position given
     */
    public void move(int newX, int newY, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newX, newY);
        
        int changeInX = Math.abs(newX - xPosition);
        int changeInY = Math.abs(newY - yPosition);
        
        boolean moveCondition1 = changeInX == 0;
        boolean moveCondition2 = changeInY == 0;
        
        UtilityFunctions.checkMove(moveCondition1 && moveCondition2, this, newX, newY, position);
        UtilityFunctions.makeValidMove(this, newX, newY, position);
    }

    /**
     * Obstacle will not be able to capture any piece whatsoever
     * @param newX The new x-position of the piece
     * @param newY The new y-position of the piece
     * @param position the board position manager
     * @throws Exception if invalid position given
     * @return The newly taken chess piece
     */
    public ChessPiece attack(int newX, int newY, PiecePosition position) throws BoardException {
        return null;
    }
}
