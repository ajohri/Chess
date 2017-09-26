package GameTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ChessAPI.Game.PiecePosition;
import ChessAPI.Game.Player;
import ChessAPI.Pieces.*;
import ChessAPI.PiecesUtils.ChessPieceColor;

public class PiecePositionTest {
    PiecePosition position;

    @Before
    public void setUp() {
        position = new PiecePosition();
    }

    /**
     * Tests that we can create the board position manager
     */
    @Test
    public void shouldBeAbleToCreatePiecePosition() {
        Assert.assertNotNull(position);
    }

    /**
     * Tests that we can initialize the board position manager with two players and
     * pieces
     */
    @Test
    public void shouldBeAbleToInitializeWithPieces() {
        Player p1 = new Player(ChessPieceColor.WHITE, position);
        Player p2 = new Player(ChessPieceColor.BLACK, position);
        for (int i = 0; i < position.getSize(); i++) {
            Assert.assertNotNull(position.getPieceAtPosition(i, 0)); // Regular pieces
            Assert.assertNotNull(position.getPieceAtPosition(i, 1)); // Pawns
            Assert.assertNotNull(position.getPieceAtPosition(i, 7)); // Regular pieces
            Assert.assertNotNull(position.getPieceAtPosition(i, 6)); // Pawns
        }
    }

    /**
     * Tests that the board position manager updates upon pieces moving
     */
    @Test
    public void shouldChangeWhenPieceMoves() throws Exception {
        Player p1 = new Player(ChessPieceColor.BLACK, position);
        Pawn p = p1.pawns[0];
        Assert.assertSame(p, position.getPieceAtPosition(0, 1));
        p.move(0, 3, position);
        Assert.assertSame(p, position.getPieceAtPosition(0, 3));
    }
}
