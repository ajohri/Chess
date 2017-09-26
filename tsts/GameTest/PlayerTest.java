package GameTest;

import org.junit.Assert;
import org.junit.Test;

import ChessAPI.Game.PiecePosition;
import ChessAPI.Game.Player;
import ChessAPI.Pieces.Pawn;
import ChessAPI.PiecesUtils.ChessPieceColor;

public class PlayerTest {

    PiecePosition position = new PiecePosition();

    /**
     * Tests that we can make Chess players
     */
    @Test
    public void shouldBeAbleToInitialize() {
        Player p = new Player(ChessPieceColor.BLACK, position);
        Assert.assertNotNull(p);
    }

    /**
     * Tests that we can initialize chess players and have all their pieces
     * initialized
     */
    @Test
    public void shouldHaveAllPiecesAndList() {
        Player p = new Player(ChessPieceColor.BLACK, position);
        Assert.assertNotNull(p.king);
        Assert.assertNotNull(p.queen);
        Assert.assertNotNull(p.leftBishop);
        Assert.assertNotNull(p.rightBishop);
        Assert.assertNotNull(p.leftKnight);
        Assert.assertNotNull(p.rightKnight);
        Assert.assertNotNull(p.leftRook);
        Assert.assertNotNull(p.rightRook);
        Assert.assertNotNull(p.pawns);
        for (Pawn pawn : p.pawns) {
            Assert.assertNotNull(pawn);
        }
    }

    /**
     * Tests that we can attack other players
     * 
     * @throws Exception
     */
    @Test
    public void beAbleToAttackOtherPlayers() throws Exception {
        Player p1 = new Player(ChessPieceColor.BLACK, position);
        Player p2 = new Player(ChessPieceColor.WHITE, position);
        p1.leftKnight.move(2, 2, position);
        p2.rightKnight.move(5, 5, position);
        p1.leftKnight.move(4, 3, position);
        p2.attack(p1.leftKnight, 4, 3, position, p1);
        Assert.assertTrue(p2.outOfPlayPieces.contains(p1.leftKnight));
        Assert.assertFalse(p1.inPlayPieces.contains(p1.leftKnight));
    }

}
