package PiecesTest;

import org.junit.Before;
import org.junit.Test;

import ChessAPI.Game.PiecePosition;
import ChessAPI.Game.Player;
import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.BoardException;
import ChessAPI.PiecesUtils.ChessPieceColor;
import ChessAPI.PiecesUtils.ChessPieceSide;
import junit.framework.Assert;

public class PieceTest {

    PiecePosition position;

    @Before
    /**
     * This sets up the initial board layout.
     */
    public void setUp() {
        position = new PiecePosition();
    }

    @Test
    /**
     * Testing bishop initialization.
     */
    public void bishopTest() {
        try {
            Bishop b = new Bishop(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
            Assert.assertEquals(b.xValue(), 2);
            Assert.assertEquals(b.yValue(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Testing queen initialization.
     */
    public void queenTest() {
        try {
            Queen q = new Queen(null, ChessPieceColor.BLACK, null, position);
            Assert.assertEquals(q.xValue(), 3);
            Assert.assertEquals(q.yValue(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Testing king initialization.
     */
    public void kingTest() {
        try {
            King king = new King(null, ChessPieceColor.BLACK, null, position);
            Assert.assertEquals(king.xValue(), 4);
            Assert.assertEquals(king.yValue(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Testing rook initialization.
     */
    public void rookTest() {
        try {
            Rook rook = new Rook(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
            Assert.assertEquals(rook.xValue(), 0);
            Assert.assertEquals(rook.yValue(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Testing pawn initialization.
     */
    public void pawnTest() {
        try {
            Pawn pawn = new Pawn(null, ChessPieceColor.BLACK, null, position);
            pawn.setPawnStartingPosition(4, position);
            Assert.assertEquals(pawn.xValue(), 4);
            Assert.assertEquals(pawn.yValue(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Testing knight initialization.
     */
    public void knightTest() {
        try {
            Knight knight = new Knight(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
            Assert.assertEquals(knight.xValue(), 1);
            Assert.assertEquals(knight.yValue(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = BoardException.class)
    /**
     * This is testing to see if there is a piece at a given location
     * 
     * @throws Exception
     */
    public void occupiedPositionTest() throws Exception {
        Bishop b = new Bishop(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
        b.move(1, 3, position);
        Pawn p = new Pawn(null, ChessPieceColor.BLACK, null, position);
        p.setPawnStartingPosition(3, position);
        position.occupyPosition(p, p.xValue(), p.yValue());
    }

    @Test(expected = BoardException.class)
    /**
     * This tests if we are going to make a
     * 
     * @throws Exception
     */
    public void badMoveTest() throws Exception {
        Bishop b = new Bishop(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
        b.move(0, 0, position);
    }

    @Test
    public void beAbleToMakeManyPawns() {
        try {
            Pawn[] pawns = new Pawn[8];
            for (int i = 0; i < pawns.length; i++) {
                pawns[i] = new Pawn(null, ChessPieceColor.BLACK, null, position);
                pawns[i].setPawnStartingPosition(i, position);
                Assert.assertNotNull(pawns[i]);
            }
            Assert.assertNotNull(pawns);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = BoardException.class)
    public void badInitialPosition() throws Exception {
        Bishop b = new Bishop(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
        b.move(48, 49, position);
    }

    @Test
    public void testRookAttackingOthers() throws Exception {
        Rook r1 = new Rook(null, ChessPieceColor.WHITE, ChessPieceSide.LEFT, position);
        Rook r2 = new Rook(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
        ChessPiece returned = r1.attack(r2.xValue(), r2.yValue(), position);
        Assert.assertSame(returned, r2);
        Assert.assertSame(r1, position.getPieceAtPosition(0, 0));
    }

    @Test(expected = NullPointerException.class)
    public void attackWithNoPiece() throws Exception {
        Knight k1 = new Knight(null, ChessPieceColor.BLACK, ChessPieceSide.RIGHT, position);
        ChessPiece returned = k1.attack(4, 5, position);
        Assert.assertSame(k1, position.getPieceAtPosition(4, 5));
        Assert.assertNull(returned);
    }

    @Test
    public void attackOtherPieces() throws Exception {
        Knight k1 = new Knight(null, ChessPieceColor.BLACK, ChessPieceSide.RIGHT, position);
        Knight k2 = new Knight(null, ChessPieceColor.WHITE, ChessPieceSide.LEFT, position);
        k1.move(4, 5, position);
        k2.move(2, 2, position);
        k2.move(4, 3, position);
        ChessPiece returned = k1.attack(4, 3, position);
        Assert.assertSame(k1, position.getPieceAtPosition(4, 3));
        Assert.assertSame(k2, returned);
    }

    @Test
    public void testBishopCanAttackOthers() throws Exception {
        Bishop b1 = new Bishop(null, ChessPieceColor.BLACK, ChessPieceSide.LEFT, position);
        Pawn p1 = new Pawn(null, ChessPieceColor.WHITE, null, position);
        p1.setPawnStartingPosition(4, position);
        b1.move(6, 4, position);
        ChessPiece returned = b1.attack(p1.xValue(), p1.yValue(), position);
        Assert.assertSame(returned, p1);
        Assert.assertSame(b1, position.getPieceAtPosition(4, 6));
    }

    @Test
    public void testKingCanAttackOthers() throws Exception {
        King k1 = new King(null, ChessPieceColor.WHITE, null, position);
        Bishop other = new Bishop(null, ChessPieceColor.BLACK, ChessPieceSide.RIGHT, position);
        other.move(1, 4, position);
        other.move(3, 6, position);
        ChessPiece returned = k1.attack(other.xValue(), other.yValue(), position);
        Assert.assertSame(returned, other);
        Assert.assertSame(k1, position.getPieceAtPosition(3, 6));
    }

    @Test(expected = BoardException.class)
    public void testCannotMoveBishopWhenPiecesInWay() throws Exception {
        Player p1 = new Player(ChessPieceColor.BLACK, position);
        Bishop b = p1.leftBishop;
        b.move(4, 1, position);
        b.move(2, 1, position);
    }

    @Test(expected = BoardException.class)
    public void testCannotMoveRookWhenPiecesInWay() throws Exception {
        Player p1 = new Player(ChessPieceColor.BLACK, position);
        Rook r = p1.leftRook;
        r.move(0, 4, position);
        r.move(4, 0, position);
    }
}
