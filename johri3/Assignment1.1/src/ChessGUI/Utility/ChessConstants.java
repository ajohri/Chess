package ChessGUI.Utility;

public class ChessConstants {
    /**
     * Each piece is 80x80 and since we have 8 pieces we will need the board size to
     * be 640. The offset will represent a tile.
     */
    public static final int BOARD_SIZE = 640;
    public static final int OFFSET = BOARD_SIZE / 8;

    /**
     * The x-positions for each of the pieces. The y position will be determined
     * based on the side of the board that it will be on.
     */
    public static final double LEFT_ROOK_X_POS = -BOARD_SIZE / 2 + OFFSET / 2;
    public static final double RIGHT_ROOK_X_POS = BOARD_SIZE / 2 - OFFSET / 2;
    public static final double LEFT_KNIGHT_X_POS = -BOARD_SIZE / 2 + 3 * OFFSET / 2;
    public static final double RIGHT_KNIGHT_X_POS = BOARD_SIZE / 2 - 3 * OFFSET / 2;
    public static final double LEFT_BISHOP_X_POS = -BOARD_SIZE / 2 + 5 * OFFSET / 2;
    public static final double RIGHT_BISHOP_X_POS = BOARD_SIZE / 2 - 5 * OFFSET / 2;
    public static final double QUEEN_X_POS = -BOARD_SIZE / 2 + 7 * OFFSET / 2;
    public static final double KING_X_POS = BOARD_SIZE / 2 - 7 * OFFSET / 2;

    /**
     * The y-positions for each piece. This will help determine the side. The reason
     * the pawns are different is because they are one row above / below the rest of
     * the other pieces
     */
    public static final double BLACK_Y_POS = -BOARD_SIZE / 2 + OFFSET / 2;
    public static final double WHITE_Y_POS = BOARD_SIZE / 2 - OFFSET / 2;
    public static final double BLACK_PAWN_Y_POS = -BOARD_SIZE / 2 + 3 * OFFSET / 2;
    public static final double WHITE_PAWN_Y_POS = BOARD_SIZE / 2 - 3 * OFFSET / 2;
}
