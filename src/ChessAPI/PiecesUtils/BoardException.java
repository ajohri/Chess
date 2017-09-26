package ChessAPI.PiecesUtils;

/**
 * This exception will be used to handle issues that come up regarding the
 * board.
 * 
 * @author abhishekjohri
 *
 */
public class BoardException extends Exception {
    public BoardException(String errorMessage) {
        super("Wrong Move: " + errorMessage);
    }
}
