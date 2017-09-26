package ChessAPI.Game;

import java.util.ArrayList;

import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.*;

public class Player {
    public Rook leftRook;
    public Rook rightRook;
    public Bishop leftBishop;
    public Bishop rightBishop;
    public Knight leftKnight;
    public Knight rightKnight;
    public King king;
    public Pawn[] pawns;
    public Queen queen;

    public ArrayList<ChessPiece> inPlayPieces;
    public ArrayList<ChessPiece> outOfPlayPieces;

    public ChessPieceColor color;
    
    /**
     * Constructor for the player and it will add all the necessary pieces to the board that a player can have.
     * @param color
     * @param position
     */
    public Player(ChessPieceColor color, PiecePosition position) {
        this.color = color;
        inPlayPieces = new ArrayList<>();
        outOfPlayPieces = new ArrayList<>();

        try {
            leftRook = new Rook(ChessPieceName.ROOK, color, ChessPieceSide.LEFT, position);
            rightRook = new Rook(ChessPieceName.ROOK, color, ChessPieceSide.RIGHT, position);
            leftKnight = new Knight(ChessPieceName.KNIGHT, color, ChessPieceSide.LEFT, position);
            rightKnight = new Knight(ChessPieceName.KNIGHT, color, ChessPieceSide.RIGHT, position);
            leftBishop = new Bishop(ChessPieceName.BISHOP, color, ChessPieceSide.LEFT, position);
            rightBishop = new Bishop(ChessPieceName.BISHOP, color, ChessPieceSide.RIGHT, position);
            king = new King(ChessPieceName.KING, color, null, position);
            queen = new Queen(ChessPieceName.QUEEN, color, null, position);

            inPlayPieces.add(leftRook);
            inPlayPieces.add(rightRook);
            inPlayPieces.add(leftKnight);
            inPlayPieces.add(rightKnight);
            inPlayPieces.add(leftBishop);
            inPlayPieces.add(rightBishop);
            inPlayPieces.add(king);
            inPlayPieces.add(queen);

            pawns = new Pawn[8];
            for (int i = 0; i < pawns.length; i++) {
                pawns[i] = new Pawn(ChessPieceName.PAWN, color, null, position);
                pawns[i].setPawnStartingPosition(i, position);
                inPlayPieces.add(pawns[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attack(ChessPiece currentPiece, int newXPosition, int newYPosition, PiecePosition position,
            Player opponent) {
        try {
            ChessPiece captured = currentPiece.attack(newXPosition, newYPosition, position);
            opponent.inPlayPieces.remove(captured);
            outOfPlayPieces.add(captured);
        } catch (Exception e) {
            System.out.println(newXPosition + "," + newYPosition);
            e.printStackTrace();
        }
    }

}
