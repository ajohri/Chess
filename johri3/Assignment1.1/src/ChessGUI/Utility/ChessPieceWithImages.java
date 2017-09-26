package ChessGUI.Utility;

import javafx.scene.image.*;

import java.util.ArrayList;

public class ChessPieceWithImages{
    public Image whiteRook;
    public Image blackRook;
    public Image whiteKnight;
    public Image blackKnight;
    public Image whiteBishop;
    public Image blackBishop;
    public Image whiteKing;
    public Image blackKing;
    public Image whiteQueen;
    public Image blackQueen;
    public Image whitePawn;
    public Image blackPawn;
    
    public ArrayList<Image> images;

    /**
     * This is the constructor that will get the images needed for each piece.
     */
    public ChessPieceWithImages() {
        whiteRook = new Image(getClass().getResourceAsStream("white_rook.png"), 80, 80, false, false);
        blackRook = new Image(getClass().getResourceAsStream("black_rook.png"), 80, 80, false, false);
        whiteKnight = new Image(getClass().getResourceAsStream("white_knight.png"), 80, 80, false, false);
        blackKnight = new Image(getClass().getResourceAsStream("black_knight.png"), 80, 80, false, false);
        whiteBishop = new Image(getClass().getResourceAsStream("white_bishop.png"), 80, 80, false, false);
        blackBishop = new Image(getClass().getResourceAsStream("black_bishop.png"), 80, 80, false, false);
        whiteKing = new Image(getClass().getResourceAsStream("white_king.png"), 80, 80, false, false);
        blackKing = new Image(getClass().getResourceAsStream("black_king.png"), 80, 80, false, false);
        whiteQueen = new Image(getClass().getResourceAsStream("white_queen.png"), 80, 80, false, false);
        blackQueen = new Image(getClass().getResourceAsStream("black_queen.png"), 80, 80, false, false);
        whitePawn = new Image(getClass().getResourceAsStream("white_pawn.png"), 80, 80, false, false);
        blackPawn = new Image(getClass().getResourceAsStream("black_pawn.png"), 80, 80, false, false);

        images = new ArrayList<Image>() {{
            add(whiteRook);
            add(blackRook);
            add(whiteKnight);
            add(blackKnight);
            add(whiteBishop);
            add(blackBishop);
            add(whiteKing);
            add(blackKing);
            add(whiteQueen);
            add(blackQueen);
            add(whitePawn);
            add(blackPawn);
        }};
    }
}
