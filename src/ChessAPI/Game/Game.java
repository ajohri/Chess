package ChessAPI.Game;

import java.util.Scanner;

import ChessAPI.Pieces.ChessPiece;
import ChessAPI.PiecesUtils.ChessPieceColor;

public class Game {
    Player blackPlayer;

    Player whitePlayer;

    PiecePosition position;

    /**
     * The default constructor for a chess game.
     */
    public Game() {
        position = new PiecePosition();
        blackPlayer = new Player(ChessPieceColor.BLACK, position);
        whitePlayer = new Player(ChessPieceColor.WHITE, position);
    }

    /**
     * This will the be the console output as to which player's turn it is etc.
     */
    public void play() {
        System.out.println("Welcome to Chess! Which player would you like to be (Black or White) ?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String color = scanner.next();
            if (color.equalsIgnoreCase("Black")) {
                System.out.println("You have selected Black! your opponent must be white.");
                break;
            } else if (color.equalsIgnoreCase("White")) {
                System.out.println("You have selected White! your opponent must be black.");
                break;
            } else {
                System.out.println("Bro I told you there are only 2 colors.");
            }
        }

        while (true) {
            // What the current situations is
            displayHelp();

            // White player turn
            takeTurn(scanner, "White player", whitePlayer);

            // Black player turn
            takeTurn(scanner, "black player", blackPlayer);
        }
    }

    /**
     * This is a helper function that plays the games turn by turn when there is no GUI involved
     * 
     * @param scanner
     *            The Scanner object used to grab input from the user with regards
     *            to what piece they want to move if they are not using the GUI
     * @param playerName
     *            The "name" of the player
     * @param player
     *            The player object who is taking their turn
     */
    void takeTurn(Scanner scanner, String playerName, Player player) {
        System.out.println(playerName + "'s turn! Which piece would you like to move?");

        ChessPiece piece;
        while (true) {
            String input = scanner.next();
            if ((piece = parsePieceFromInput(player, input)) != null) {
                break;
            }
        }

        System.out.println("Where would you like to move it? It is currently at this location: " + "(" + piece.xValue()
                + ", " + piece.yValue() + ")");

        while (true) {
            System.out.println("Enter the x-position");
            int x = scanner.nextInt();
            System.out.println("Enter the y-position");
            int y = scanner.nextInt();
            try {
                piece.move(x, y, position);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This helps the players between turns and tells them how to play the turn accordingly.
     */
    private void displayHelp() {
        System.out.println("\nTo choose a piece, type in a keyword to match the piece and the side, if necessary.\n"
                + "So for example, if you want to move the left Knight, you would type in \'LKnight\'\n"
                + "For pawns, type in \'p3\' to move the third pawn from the left. See below for help.");

        System.out.println("Left/Right Rook: (L/R)Rook\nLeft/Right Knight: (L/R)Knight\n"
                + "Left/Right Bishop: (L/R)Bishop\nKing: King\nQueen: Queen\n1st-8th Pawn: p(1-8)\n\n");
    }

    /**
     * Helps moves the pieces based on the input given from the player and check to see if it's a valid move or not.
     * 
     * @param player
     *            The player taking the turn
     * @param input
     *            The user-input from the player
     * @return The chosen chess piece
     */
    private ChessPiece parsePieceFromInput(Player player, String input) {
        switch (input) {
        case "LRook":
            input.equalsIgnoreCase("LRook");
            return player.leftRook;
        case "LKnight":
            input.equalsIgnoreCase("LKnight");
            return player.leftKnight;
        case "LBishop":
            input.equalsIgnoreCase("LBishop");
            return player.leftBishop;
        case "RRook":
            input.equalsIgnoreCase("RRook");
            return player.rightRook;
        case "RKnight":
            input.equalsIgnoreCase("RKnight");
            return player.rightKnight;
        case "RBishop":
            input.equalsIgnoreCase("RBishop");
            return player.rightBishop;
        case "King":
            input.equalsIgnoreCase("King");
            return player.king;
        case "Queen":
            input.equalsIgnoreCase("Queen");
            return player.queen;
        default:
            System.out.println("Invalid piece mate. Try again");
            return null;

        }
    }
}