package GameTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ChessAPI.Game.ChessConditions;
import ChessAPI.Game.PiecePosition;
import ChessAPI.Game.Player;
import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.ChessPieceColor;

public class ChessConditionsTest {

    PiecePosition position;
    Player p1;
    Player p2;

    @Before
    public void setUp() {
        position = new PiecePosition();
        p1 = new Player(ChessPieceColor.WHITE, position);
        p2 = new Player(ChessPieceColor.BLACK, position);
    }

    /**
     * Tests that Bishop can check King
     */
    @Test
    public void bishopAbleToCheckKing() {
        try {
            Bishop myBishop = p2.rightBishop;
            Pawn myPawn = p2.pawns[4];
            myPawn.move(4, 3, position);
            myBishop.move(4, 1, position);
            myBishop.move(7, 4, position);
            Assert.assertFalse(ChessConditions.inCheck(p1, position));
            p1.pawns[5].move(5, 5, position);
            Assert.assertTrue(ChessConditions.inCheck(p2, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that Knight can check King
     */
    @Test
    public void knightAbleToCheckKing() {
        try {
            Knight myKnight = p1.leftKnight;
            myKnight.move(2, 5, position);
            myKnight.move(3, 3, position);
            myKnight.move(5, 2, position);
            p2.pawns[3].move(3, 2, position);
            p2.king.move(3, 1, position);
            Assert.assertTrue(ChessConditions.inCheck(p1, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that Rook can check King
     */
    @Test
    public void rookAbleToCheckKing() {
        try {
            Rook myRook = p1.leftRook;
            p1.pawns[0].move(0, 4, position);
            myRook.move(0, 5, position);
            myRook.move(3, 5, position);
            p2.pawns[4].move(4, 3, position);
            myRook.move(3, 2, position);
            Assert.assertFalse(ChessConditions.inCheck(p1, position));
            myRook.move(4, 2, position);
            Assert.assertTrue(ChessConditions.inCheck(p1, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that Pawn can check King
     */
    @Test
    public void pawnAbleToCheckKing() {
        try {
            Pawn myPawn = p1.pawns[3];
            myPawn.move(3, 4, position);
            myPawn.move(3, 3, position);
            myPawn.move(3, 2, position);
            p2.pawns[4].move(4, 3, position);
            Assert.assertFalse(ChessConditions.inCheck(p1, position));
            p2.king.move(4, 1, position);
            Assert.assertTrue(ChessConditions.inCheck(p1, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that Queen can check King
     */
    @Test
    public void queenAbleToCheckKing() {
        try {
            Queen myQueen = p1.queen;
            Pawn myPawn = p1.pawns[3];
            myPawn.move(3, 4, position);
            myQueen.move(3, 5, position);
            myQueen.move(0, 5, position);
            myQueen.move(0, 4, position);
            Assert.assertFalse(ChessConditions.inCheck(p1, position));
            p2.pawns[3].move(3, 3, position);
            Assert.assertTrue(ChessConditions.inCheck(p1, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that King can check King
     */
    @Test
    public void kingAbleToCheckKing() {
        try {
            King myKing = p1.king;
            p1.pawns[3].move(3, 4, position);
            myKing.move(3, 6, position);
            myKing.move(3, 5, position);
            myKing.move(4, 4, position);
            myKing.move(4, 3, position);
            myKing.move(4, 2, position);
            p2.pawns[3].move(3, 3, position);
            Assert.assertFalse(ChessConditions.inCheck(p1, position));
            p2.king.move(3, 1, position);
            Assert.assertTrue(ChessConditions.inCheck(p1, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
