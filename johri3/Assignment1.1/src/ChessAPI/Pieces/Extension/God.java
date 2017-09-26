package ChessAPI.Pieces.Extension;
import ChessAPI.Game.PiecePosition;
import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.BoardException;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceName;
import ChessAPI.PiecesUtils.ChessPieceSide;

public class God extends ChessPiece {
    /**
     * The constructor for the God piece that can move anywhere
     * @param color Color of God
     * @param side The side for God (Right or Left)
     * @param position The board position manager
     * @throws exception thrown if there is a board error
     */
    public God(ChessPieceColor color, ChessPieceSide side, PiecePosition position) throws Exception {
        super(ChessPieceName.GOD, color, side, position);

        int size = position.getSize();
        if (color == ChessPieceColor.WHITE) {
            UtilityFunctions.setInitialPositionWithSide(this, 4, size - 5, size - 4, size - 5);
        } else {
            UtilityFunctions.setInitialPositionWithSide(this, 4, size - 2, size - 4, size - 2);
        }
        position.occupyPosition(this, yPosition, xPosition);
    }

    /**
     * How the move piece works for the God class
     * @param newX - the new x-position of the piece, checked for validity
     * @param newY - the new y-position of the piece, also checked
     * @param position The board position manager
     * @throws exception thrown if there is a board error
     */
    public void move(int newX, int newY, PiecePosition position) throws BoardException {
        UtilityFunctions.checkingInitialBounds(newX, newY);
        
        UtilityFunctions.checkMove(true, this, newX, newY, position);
        UtilityFunctions.makeValidMove(this, newX, newY, position);
    }

    /**
     * How the God piece attacks
     * @param newX The new x-position of the piece
     * @param newY The new y-position of the piece
     * @param position the board position manager
     * @throws Exception if invalid position given
     * @return The newly taken chess piece
     */
    public ChessPiece attack(int newX, int newY, PiecePosition position) throws BoardException {
        if (position.getPieceAtPosition(newX, newY).getName() == ChessPieceName.GOD) {
            return UtilityFunctions.moveOrCapture(this, newX, newY, position);
        }
        return null;
    }
}
