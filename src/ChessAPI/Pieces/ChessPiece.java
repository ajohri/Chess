package ChessAPI.Pieces;

import ChessAPI.PiecesUtils.*;
import ChessAPI.Game.*;

public abstract class ChessPiece {
    ChessPieceName name;
    ChessPieceColor color;
    ChessPieceSide side;
    PiecePosition position;
    protected static int xPosition;
    protected static int yPosition;

    /**
     * This is the constructor for the ChessPiece superclass
     * 
     * @param name
     *            Name of the piece.
     * @param color
     *            Color of the piece.
     * @param side
     *            This is used for the pieces where there are two of them.
     * @param position
     *            Maintains the location of all the pieces.
     */
    protected ChessPiece(ChessPieceName name, ChessPieceColor color, ChessPieceSide side, PiecePosition position) {
        this.name = name;
        this.color = color;
        this.side = side;
        this.position = position;
    }

    /**
     * Simple getter function
     * 
     * @return The x position of the piece
     */
    public int xValue() {
        return xPosition;
    }

    /**
     * Simple getter function
     * 
     * @return The y position of the piece
     */
    public int yValue() {
        return yPosition;
    }

    /**
     * Simple getter function
     * 
     * @return The name of the piece i.e. BISHOP, KING, QUEEN...
     */
    public ChessPieceName getName() {
        return name;
    }

    /**
     * Simple getter function
     * 
     * @return The color of the piece
     */
    public ChessPieceColor getColor() {
        return color;
    }

    /**
     * Simple getter function
     * 
     * @return The side that the piece started on for the board
     */
    public ChessPieceSide getSide() {
        return side;
    }

    /**
     * This changes the position of the current piece based on (x,y) coordinates
     * 
     * @param newXPosition
     *            The new x position that the piece needs to go to.
     * @param newYPosition
     *            The new y position that the piece needs to go to.
     */
    public void setPostion(int newXPosition, int newYPosition) {
        xPosition = newXPosition;
        yPosition = newYPosition;
    }

    /**
     * Move is different for each piece which is why this will be an abstract
     * method. This will handle the moment of the piece
     * 
     * @param newXPosition
     *            The new x position that the piece needs to go to.
     * @param newYPosition
     *            The new y position that the piece needs to go to.
     * @throws BoardException
     *             Newly created exception to handle board conditions.
     */
    public abstract void move(int newXPosition, int newYPosition, PiecePosition position) throws BoardException;

    /**
     * Attack is different for each piece which is why this will be an abstract
     * method. This will handle any sort of attacking that the piece needs to do.
     * 
     * @param newXPostion
     *            The new x position that the piece needs to go to.
     * @param newYPosition
     *            The new y position that the piece needs to go to.
     * @return The Piece that was captured when the attack was made.
     * @throws BoardException
     *             Newly created exception to handle board conditions
     */
    public abstract ChessPiece attack(int newXPosition, int newYPosition, PiecePosition position) throws BoardException;
}
