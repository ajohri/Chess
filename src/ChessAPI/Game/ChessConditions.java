package ChessAPI.Game;

import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.*;

public class ChessConditions {

    /**
     * This check to see if a player's King is in check or not.
     * @param player The player who's king we are checking.
     * @param position The position object that is shared and knows where all the pieces are.
     * @return
     * @throws BoardException
     */
    public static boolean inCheck(Player player, PiecePosition position) throws BoardException {
        for (ChessPiece piece : player.inPlayPieces) {
            if (isKingInLineOfPiece(player, piece, position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This checks to see if the player is in a stalemate with the other player.
     * @param player The player whose turn it is when the stalemate occurs
     * @param opposing The player whose's turn it was before the stalemate occurred.
     * @param position The position object that is shared and knows where all the pieces are.
     * @return
     * @throws BoardException
     */
    public static boolean inStalemate(Player player, Player opposing, PiecePosition position) throws BoardException {
        return !inCheck(player, position) && !anyLegalMoves(player, opposing, position);
    }

    /**
     * This checks if the king is on the path of another piece.
     * @param player The player whose turn it is when the king is in line of another piece
     * @param position The position object that is shared and knows where all the pieces are.
     * @return
     * @throws BoardException
     */
    private static boolean isKingInLineOfPiece(Player player, ChessPiece piece, PiecePosition position)
            throws BoardException {
        if (piece.getName() == ChessPieceName.BISHOP) {
            return CheckConditionFunctions.bishopCheckingKing(piece, position, player);

        } else if (piece.getName() == ChessPieceName.KNIGHT) {
            return CheckConditionFunctions.knightCheckingKing(piece, position, player);

        } else if (piece.getName() == ChessPieceName.ROOK) {
            return CheckConditionFunctions.rookCheckingKing(piece, position, player);

        } else if (piece.getName() == ChessPieceName.KING) {
            return CheckConditionFunctions.kingCheckingKing(piece, position, player);

        } else if (piece.getName() == ChessPieceName.PAWN) {
            return CheckConditionFunctions.pawnCheckingKing(piece, position, player);

        } else {
            return CheckConditionFunctions.queenCheckingKing(piece, position, player);
        }
    }

    /**
     * This checks to see if we can make any moves with the king.
     * @param myPlayer current player that has to make the move
     * @param opposing the player that has put myPlayer in this situation.
     * @param position The position object that is shared and knows where all the pieces are.
     * @return
     */
    private static boolean anyLegalMoves(Player myPlayer, Player opposing, PiecePosition position) {
        ChessPiece myKing = myPlayer.king;
        int currX = myKing.xValue();
        int currY = myKing.yValue();

        if (isValidMove(opposing, position, myKing, currX - 1, currY, currX, currY))
            return true;

        if (isValidMove(opposing, position, myKing, currX + 1, currY, currX, currY))
            return true;

        if (isValidMove(opposing, position, myKing, currX, currY - 1, currX, currY))
            return true;

        if (isValidMove(opposing, position, myKing, currX, currY + 1, currX, currY))
            return true;

        if (isValidMove(opposing, position, myKing, currX - 1, currY - 1, currX, currY))
            return true;

        if (isValidMove(opposing, position, myKing, currX + 1, currY - 1, currX, currY))
            return true;

        if (isValidMove(opposing, position, myKing, currX - 1, currY + 1, currX, currY))
            return true;

        return (!isValidMove(opposing, position, myKing, currX + 1, currY + 1, currX, currY));
        // If this returns false that means we are in a checkmate
    }

    /**
     * This sees if we can make a valid move or if we are in a check or not.
     * @param player current player that has to make a move or deal with the check
     * @param position The position object that is shared and knows where all the pieces are.
     * @param myKing the current king that has an issue.
     * @param currX the current x position
     * @param currY the current y position
     * @param nextX the new x position
     * @param nextY the new y position
     * @return
     */
    private static boolean isValidMove(Player player, PiecePosition position, ChessPiece myKing, int currX, int currY,
            int nextX, int nextY) {
        try {
            myKing.move(nextX, nextY, position);
            if (!inCheck(player, position))
                return true;
            myKing.move(currX, currY, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}