package ChessGUI;

import ChessGUI.Utility.ChessConstants;
import ChessGUI.Utility.ChessPieceWithImages;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

import ChessAPI.PiecesUtils.ChessPieceColor;

/**
 * This will be the GUI for the chess board. Using the javafx library for all
 * the application services
 */

public class EntireBoard extends Application {
    private static final int BOARD_SIZE = ChessConstants.BOARD_SIZE;
    private static final int OFFSET = ChessConstants.OFFSET;

    private static ArrayList<ImageView> views;
    private HashMap<ImageView, ChessPieceColor> colorMap;

    public static Rectangle[][] boardSquares = new Rectangle[8][8];

    private static StackPane board;

    /**
     * This is the actual main method that is needed in order JAVAFX to run the application. This will help with future setup of the baord with the API.
     * 
     * @param primaryStage
     *            This is required by JAVAFX to populate with nodes.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        
        board = new StackPane();
        Canvas gameCanvas = new Canvas(BOARD_SIZE, BOARD_SIZE);
        views = new ArrayList<>();
        colorMap = new HashMap<>();

        setUpBoard();
        ChessPieceWithImages images = new ChessPieceWithImages();
        addPiecesToBoard(images, board, gameCanvas);

        StackPane dashboard = new StackPane();

        SplitPane root = new SplitPane();
        root.getItems().addAll(board, dashboard);
        primaryStage.setScene(new Scene(root, 3 * BOARD_SIZE / 2, BOARD_SIZE));

        primaryStage.show();
    }

    /**
     * Helper to set up the entire board. Mainly the color and the checker effect
     */
    private void setUpBoard() {
        double origin = -BOARD_SIZE / 2 + OFFSET / 2;
        double currX = origin;
        double currY = origin;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardSquares[i][j] = new Rectangle(currX, currY, OFFSET, OFFSET);
                if (((i + j) % 2) == 0) {
                    boardSquares[i][j] = new Rectangle(OFFSET, OFFSET, Color.WHITE);
                } else {
                    boardSquares[i][j] = new Rectangle(OFFSET, OFFSET, Color.GREEN);
                }
                boardSquares[i][j].setTranslateX(currX);
                boardSquares[i][j].setTranslateY(currY);
                currX += OFFSET;
            }
            currX = origin;
            currY += OFFSET;
        }
    }

    /**
     * This will help with adding all the pieces to the board with the pngs for all of them.
     *
     * @param images
     *            This has all the images for each of the pieces
     */
    private void addPiecesToBoard(ChessPieceWithImages images, StackPane board, Canvas gameCanvas) {
        initializeImageView(images.blackRook, ChessConstants.LEFT_ROOK_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        initializeImageView(images.blackRook, ChessConstants.RIGHT_ROOK_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        initializeImageView(images.blackKnight, ChessConstants.LEFT_KNIGHT_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        initializeImageView(images.blackKnight, ChessConstants.RIGHT_KNIGHT_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        initializeImageView(images.blackBishop, ChessConstants.LEFT_BISHOP_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        initializeImageView(images.blackBishop, ChessConstants.RIGHT_BISHOP_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        initializeImageView(images.blackQueen, ChessConstants.QUEEN_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        initializeImageView(images.blackKing, ChessConstants.KING_X_POS, ChessConstants.BLACK_Y_POS,
                ChessPieceColor.BLACK);
        for (int i = 0; i < 8; i++) {
            initializeImageView(images.blackPawn, -BOARD_SIZE / 2 + (2 * i + 1) * OFFSET / 2,
                    ChessConstants.BLACK_PAWN_Y_POS, ChessPieceColor.BLACK);
        }

        initializeImageView(images.whiteRook, ChessConstants.LEFT_ROOK_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        initializeImageView(images.whiteRook, ChessConstants.RIGHT_ROOK_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        initializeImageView(images.whiteKnight, ChessConstants.LEFT_KNIGHT_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        initializeImageView(images.whiteKnight, ChessConstants.RIGHT_KNIGHT_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        initializeImageView(images.whiteBishop, ChessConstants.LEFT_BISHOP_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        initializeImageView(images.whiteBishop, ChessConstants.RIGHT_BISHOP_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        initializeImageView(images.whiteQueen, ChessConstants.QUEEN_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        initializeImageView(images.whiteKing, ChessConstants.KING_X_POS, ChessConstants.WHITE_Y_POS,
                ChessPieceColor.WHITE);
        for (int i = 0; i < 8; i++) {
            initializeImageView(images.whitePawn, -BOARD_SIZE / 2 + (2 * i + 1) * OFFSET / 2,
                    ChessConstants.WHITE_PAWN_Y_POS, ChessPieceColor.WHITE);
        }

        board.getChildren().add(gameCanvas);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.getChildren().add(boardSquares[i][j]);
            }
        }
        for (ImageView i : views) {
            board.getChildren().add(i);
        }
    }

    /**
     * Helper function to create an ImageView around the image object, set its x and
     * y position. This will allow us to overlay all the images.
     *
     * @param currImage
     *            The image we are trying to initialize
     * @param xPos
     *            The x-position of the chess piece
     * @param yPos
     *            The y-position of the chess piece
     */
    private void initializeImageView(Image currImage, double xPos, double yPos, ChessPieceColor color) {
        ImageView imageView = new ImageView(currImage);
        imageView.setTranslateX(xPos);
        imageView.setTranslateY(yPos);
        views.add(imageView);
        colorMap.put(imageView, color);
    }

    /**
     * Getter for ImageViews list
     *
     * @return Our ChessPieces in ImageView form!
     */
    public static ArrayList<ImageView> getViews() {
        return views;
    }

    /**
     * This will launch our application and this is needed for JAVAFX when we call it from main to launch this class.
     * @param args
     *            Standard arguements for a main function
     */
    public static void main(String[] args) {
        launch(args);
    }
}
