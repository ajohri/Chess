package ChessAPI.Pieces;

import ChessAPI.Game.PiecePosition;
import ChessAPI.PiecesUtils.*;

public class UtilityFunctions {

    /**
     * This helps put the left and right side piece for some of the Chess Pieces.
     * 
     * @param piece
     *            This will be BISHOP, ROOK, or KNIGHT
     * @param leftXPosition
     *            left piece x position.
     * @param leftYPosition
     *            left piece y position.
     * @param rightXPosition
     *            right piece x position.
     * @param rightYPosition
     *            right piece y position.
     */
    public static void setInitialPositionWithSide(ChessPiece piece, int leftXPosition, int leftYPosition,
            int rightXPosition, int rightYPosition) {
        if (piece.side == ChessPieceSide.LEFT) {
            piece.setPostion(leftXPosition, leftYPosition);
        } else {
            piece.setPostion(rightXPosition, rightYPosition);
        }
    }

    /**
     * This is check to see to see if our piece is within the intial bounds of the
     * board.
     * 
     * @param newXPosition
     *            X position in question
     * @param newYPosition
     *            Y position in question
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static void checkingInitialBounds(int newXPosition, int newYPosition) throws BoardException {
        if (newXPosition > 7 || newXPosition < 0 || newYPosition > 7 || newYPosition < 0) {
            throw new BoardException("You are outside of the board");
        }
    }

    /**
     * Going to take care of the pieces that can traverse the board (Queen, Rook,
     * Bishop)
     * 
     * @param moveCondition
     *            Each piece has a move condition and based on that we will check if
     *            it's a valid move for the 3 most complex pieces. The other pieces
     *            handle it internally.
     * @param currentPiece
     *            The piece that the player wants to move.
     * @param newXPosition
     *            The new x position that they want to move it to.
     * @param newYPosition
     *            The new y position that they want to move it to.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static void checkMove(boolean moveCondition, ChessPiece currentPiece, int newXPosition, int newYPosition,
            PiecePosition position) throws BoardException {
        if (moveCondition == false) {
            throw new BoardException("Your move caused you to go out of bounds");
        }

        if (currentPiece.getName() == ChessPieceName.BISHOP) {
            if (isBishopPathClear(currentPiece, newXPosition, newYPosition, position)) {
                throw new BoardException("There is a piece in the way reconsider your move");
            }
        }

        if (currentPiece.getName() == ChessPieceName.QUEEN) {
            if (isQueenPathClear(currentPiece, newXPosition, newYPosition, position)) {
                throw new BoardException("There is a piece in the way reconsider your move");
            }
        }

        if (currentPiece.getName() == ChessPieceName.ROOK) {
            if (isRookPathClear(currentPiece, newXPosition, newYPosition, position)) {
                throw new BoardException("There is a piece in the way reconsider your move");
            }
        }

    }

    /**
     * This method will get called after the move has been validated from the above
     * method.
     * 
     * @param currentPiece
     *            Whatever piece is currently being moved.
     * @param newXPosition
     *            The new x position that they want to move it to.
     * @param newYPosition
     *            The new y position that they want to move it to.
     * @param position
     *            The board needs to be updated and maintain where all the other
     *            pieces are.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static void makeValidMove(ChessPiece currentPiece, int newXPosition, int newYPosition,
            PiecePosition position) throws BoardException {
        position.leavePosition(currentPiece);
        currentPiece.setPostion(newXPosition, newYPosition);
        position.occupyPosition(currentPiece, newXPosition, newYPosition);
    }

    /**
     * This will return a captured pieces have a move has been done.
     * 
     * @param currentPiece
     *            Whatever piece is currently being moved.
     * @param newXPosition
     *            The new x position that they want to move it to claim a piece
     * @param newYPosition
     *            The new y position that they want to move it to claim a piece
     * @param position
     *            Board needs to be updated and maintain where all the other pieces
     *            are.
     * @return The captured chess piece from the opponent.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static ChessPiece captureAndMove(ChessPiece currentPiece, int newXPosition, int newYPosition,
            PiecePosition position) throws BoardException {
        ChessPiece captured = position.getPieceAtPosition(newXPosition, newYPosition);
        position.leavePosition(captured);
        makeValidMove(currentPiece, newXPosition, newYPosition, position);
        return captured;
    }

    /**
     * This is always called and determines if the move that the player is doing is
     * just to move to the piece or to capture one.
     * 
     * @param currentPiece
     *            Whatever piece is currently being moved.
     * @param newXPosition
     *            The new x position that they want to move it to claim a piece
     * @param newYPosition
     *            The new y position that they want to move it to claim a piece
     * @param position
     *            Board needs to be updated and maintain where all the other pieces
     *            are.
     * @return The captured chess piece from the opponent.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static ChessPiece moveOrCapture(ChessPiece currentPiece, int newXPosition, int newYPosition,
            PiecePosition position) throws BoardException {
        if (position.isOccupied(newXPosition, newYPosition) == true) {
            makeValidMove(currentPiece, newXPosition, newYPosition, position);
            return null;
        } else {
            return captureAndMove(currentPiece, newXPosition, newYPosition, position);
        }
    }

    /**
     * This is checking if two pieces are allies or not. Or if it's the same piece.
     * Base function that will be used later on.
     * 
     * @param inQuestion
     *            The piece that comes on the path.
     * @param currentPiece
     *            The piece that we are figuring out the path for.
     * @return a boolean on whether or not the piece can move to the next piece.
     */
    public static boolean alliedPieceOnPath(ChessPiece inQuestion, ChessPiece currentPiece) {
        if (inQuestion != null && inQuestion != currentPiece && inQuestion.getColor() == currentPiece.getColor()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is specifically to see if the bishop's path will be clear.
     * 
     * @param currentPiece
     *            Whatever piece is currently being moved.
     * @param newXPosition
     *            The new x position that they want to move it to claim a piece
     * @param newYPosition
     *            The new y position that they want to move it to claim a piece
     * @param position
     *            Board needs to be updated and maintain where all the other pieces
     *            are.
     * @return a boolean on whether or not the piece can move to the desired
     *         location.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static boolean isBishopPathClear(ChessPiece currentPiece, int xPosition, int yPosition,
            PiecePosition position) throws BoardException {
        int currentX = xPosition;
        int currentY = yPosition;

        int i = currentX;
        int j = currentY;
        while (i >= xPosition && j >= yPosition) {
            // This is checking one spot to the left and above
            ChessPiece inQuestion = position.getPieceAtPosition(i--, j--);
            if (alliedPieceOnPath(inQuestion, currentPiece)) {
                return true;
            }
        }

        i = currentX;
        j = currentY;
        while (i >= xPosition && j <= yPosition) {
            // This is checking one spot to the left and below
            ChessPiece inQuestion = position.getPieceAtPosition(i--, j++);
            if (alliedPieceOnPath(inQuestion, currentPiece)) {
                return true;
            }
        }

        i = currentX;
        j = currentY;
        while (i <= xPosition && j <= yPosition) {
            // This is checking one spot to the right and below
            ChessPiece inQuestion = position.getPieceAtPosition(i++, j++);
            if (alliedPieceOnPath(inQuestion, currentPiece)) {
                return true;
            }
        }

        i = currentX;
        j = currentY;
        while (i <= xPosition && j >= yPosition) {
            ChessPiece inQuestion = position.getPieceAtPosition(i++, j++);
            if (alliedPieceOnPath(inQuestion, currentPiece)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is specifically to see if the rook's path will be clear.
     * 
     * @param currentPiece
     *            Whatever piece is currently being moved.
     * @param newXPosition
     *            The new x position that they want to move it to claim a piece
     * @param newYPosition
     *            The new y position that they want to move it to claim a piece
     * @param position
     *            Board needs to be updated and maintain where all the other pieces
     *            are.
     * @return a boolean on whether or not the piece can move to the desired
     *         location.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static boolean isRookPathClear(ChessPiece currentPiece, int xPosition, int yPosition, PiecePosition position)
            throws BoardException {
        int currentX = xPosition;
        int currentY = yPosition;

        if (currentX > xPosition && currentY == yPosition) {
            for (int i = currentX; i >= xPosition; i--) {
                ChessPiece inQuestion = position.getPieceAtPosition(i, currentY);
                if (alliedPieceOnPath(inQuestion, currentPiece)) {
                    return true;
                }
            }
        }

        if (currentX < xPosition && currentY == yPosition) {
            for (int i = currentX; i >= xPosition; i++) {
                ChessPiece inQuestion = position.getPieceAtPosition(i, currentY);
                if (alliedPieceOnPath(inQuestion, currentPiece)) {
                    return true;
                }
            }
        }

        if (currentX == xPosition && currentY > yPosition) {
            for (int i = currentY; i >= yPosition; i--) {
                ChessPiece inQuestion = position.getPieceAtPosition(currentX, i);
                if (alliedPieceOnPath(inQuestion, currentPiece)) {
                    return true;
                }
            }
        }

        if (currentX == xPosition && currentY > yPosition) {
            for (int i = currentY; i >= yPosition; i++) {
                ChessPiece inQuestion = position.getPieceAtPosition(currentX, i);
                if (alliedPieceOnPath(inQuestion, currentPiece)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method is specifically to see if the queen's path will be clear. Queen
     * moves just like a rook and a bishop in one piece.
     * 
     * @param currentPiece
     *            Whatever piece is currently being moved.
     * @param newXPosition
     *            The new x position that they want to move it to claim a piece
     * @param newYPosition
     *            The new y position that they want to move it to claim a piece
     * @param position
     *            Board needs to be updated and maintain where all the other pieces
     *            are.
     * @return a boolean on whether or not the piece can move to the desired
     *         location.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static boolean isQueenPathClear(ChessPiece currentPiece, int xPosition, int yPosition,
            PiecePosition position) throws BoardException {
        return isBishopPathClear(currentPiece, xPosition, yPosition, position)
                || isRookPathClear(currentPiece, xPosition, yPosition, position);
    }
}
