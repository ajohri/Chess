package ChessGUI.Utility;

/**
 * This class will help when we are using the position form the API library and
 * using it for the GUI
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
