package ChessAPI.Game;

import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.*;

/**
 * This function checks all the possible pieces that can put the King in a check
 * 
 * @author abhishekjohri
 *
 */
public class CheckConditionFunctions {

    /**
     * This is the base condition to see if you are checking a king.
     * 
     * @param currentPiece
     * @param player
     * @return
     */
    private static boolean checkingKing(ChessPiece currentPiece, Player player) {
        if (currentPiece != null && currentPiece.getName() == ChessPieceName.KING && currentPiece != player.king) {
            return true;
        }
        return false;
    }

    /**
     * This is when a pawn is checking a king.
     * 
     * @param pawn
     *            piece
     * @param position
     *            board position needs to be maintained.
     * @param player
     *            which player is giving the check
     * @return true or false if you put the opponent's king in check.
     * @throws BoardException
     *             Exception to handle board issues.
     */
    public static boolean pawnCheckingKing(ChessPiece pawn, PiecePosition position, Player player)
            throws BoardException {
        int currentX = pawn.xValue();
        int currentY = pawn.yValue();

        if (currentX - 1 >= 0 && currentY - 1 >= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX - 1, currentY - 1);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }
        if (currentX + 1 < position.getSize() && currentY - 1 >= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX + 1, currentY - 1);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This is when a knight is checking a king.
     * 
     * @param knight
     *            piece
     * @param position
     *            board position needs to be maintained
     * @param player
     *            which player is giving the check.
     * @return true or false if you put the opponent's king in check
     * @throws BoardException
     *             Exception to handle the board issues.
     */
    public static boolean knightCheckingKing(ChessPiece knight, PiecePosition position, Player player)
            throws BoardException {
        int currentX = knight.xValue();
        int currentY = knight.yValue();

        if (currentX + 2 < position.getSize() && currentY + 1 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX + 2, currentY + 1);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }

        if (currentX + 2 < position.getSize() && currentY - 1 >= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX + 2, currentY - 1);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }

        if (currentX - 2 >= 0 && currentY - 1 >= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX - 2, currentY - 1);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }

        if (currentX - 2 >= 0 && currentY + 1 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX - 2, currentY + 1);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }

        if (currentX + 1 < position.getSize() && currentY + 2 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX + 1, currentY + 2);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }

        if (currentX + 1 < position.getSize() && currentY - 2 >= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX + 1, currentY - 2);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }

        if (currentX - 1 >= 0 && currentY + 2 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX - 1, currentY + 2);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }

        if (currentX - 1 >= 0 && currentY - 2 >= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX - 1, currentY - 2);
            if (checkingKing(chosen, player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This is when a bishop is checking a king.
     * 
     * @param bishop
     *            piece
     * @param position
     *            board position needs to be maintained
     * @param player
     *            which player is giving the check.
     * @return true or false if you put the opponent's king in check
     * @throws BoardException
     *             Exception to handle the board issues.
     */
    public static boolean bishopCheckingKing(ChessPiece bishop, PiecePosition position, Player player)
            throws BoardException {
        int currentX = bishop.xValue();
        int currentY = bishop.yValue();

        int i = currentX;
        int j = currentY;
        while (i >= 0 && j >= 0) {
            ChessPiece captured = position.getPieceAtPosition(i--, j--);
            if (captured != null && captured != bishop) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        i = currentX;
        j = currentY;
        while (i >= 0 && j < position.getSize()) {
            ChessPiece captured = position.getPieceAtPosition(i--, j++);
            if (captured != null && captured != bishop) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        i = currentX;
        j = currentY;
        while (i < position.getSize() && j < position.getSize()) {
            ChessPiece captured = position.getPieceAtPosition(i++, j++);
            if (captured != null && captured != bishop) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        i = currentX;
        j = currentY;
        while (i < position.getSize() && j >= 0) {
            ChessPiece captured = position.getPieceAtPosition(i++, j--);
            if (captured != null && captured != bishop) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * This is when a rook is checking a king.
     * 
     * @param rook
     *            piece
     * @param position
     *            board position needs to be maintained
     * @param player
     *            which player is giving the check.
     * @return true or false if you put the opponent's king in check
     * @throws BoardException
     *             Exception to handle the board issues.
     */
    public static boolean rookCheckingKing(ChessPiece rook, PiecePosition position, Player player)
            throws BoardException {
        int currentX = rook.xValue();
        int currentY = rook.yValue();

        for (int i = currentX; i >= 0; i--) {
            ChessPiece captured = position.getPieceAtPosition(i, currentY);
            if (captured != null && captured != rook) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = currentX; i < position.getSize(); i++) {
            ChessPiece captured = position.getPieceAtPosition(i, currentY);
            if (captured != null && captured != rook) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = currentY; i >= 0; i--) {
            ChessPiece captured = position.getPieceAtPosition(currentX, i);
            if (captured != null && captured != rook) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = currentY; i < position.getSize(); i++) {
            ChessPiece captured = position.getPieceAtPosition(currentX, i);
            if (captured != null && captured != rook) {
                if (checkingKing(captured, player)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * This is when a king is checking a king.
     * 
     * @param king
     *            piece
     * @param position
     *            board position needs to be maintained
     * @param player
     *            which player is giving the check.
     * @return true or false if you put the opponent's king in check
     * @throws BoardException
     *             Exception to handle the board issues.
     */
    public static boolean kingCheckingKing(ChessPiece king, PiecePosition position, Player player)
            throws BoardException {
        int currentX = king.xValue();
        int currentY = king.yValue();

        if (currentY - 1 >= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX, currentY - 1);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }

        if (currentY + 1 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX, currentY + 1);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }

        if (currentX - 1 <= 0) {
            ChessPiece chosen = position.getPieceAtPosition(currentX - 1, currentY);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }

        if (currentX + 1 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX + 1, currentY);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }

        if (currentX - 1 >= 0 && currentY + 1 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX - 1, currentY + 1);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }

        if (currentX + 1 < position.getSize() && currentY + 1 < position.getSize()) {
            ChessPiece chosen = position.getPieceAtPosition(currentX + 1, currentY + 1);
            if (chosen != null) {
                if (checkingKing(chosen, player)) {
                    return true;
                }
            }
        }

        return pawnCheckingKing(king, position, player);
    }

    /**
     * This is when a queen is checking a king.
     * 
     * @param queen
     *            piece
     * @param position
     *            board position needs to be maintained
     * @param player
     *            which player is giving the check.
     * @return true or false if you put the opponent's king in check
     * @throws BoardException
     *             Exception to handle the board issues.
     */
    public static boolean queenCheckingKing(ChessPiece queen, PiecePosition position, Player player)
            throws BoardException {
        return bishopCheckingKing(queen, position, player) || rookCheckingKing(queen, position, player);
    }
}
