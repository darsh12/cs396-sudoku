package sample;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int NUM_OF_PAIRS = 81;
    private static final int NUM_PER_ROW = 9;

    private Parent createContent() {

        SudokuUtility board = new SudokuUtility();

        int[][] grid = board.getBoard();

        Pane root = new Pane();
        root.setPrefSize(450, 450);

        char c = 'A';
        List<Tile> tiles = new ArrayList<>();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                tiles.add(new Tile(grid[x][y]));
            }
        }

        /*
        for (int i = 0; i < NUM_OF_PAIRS; i++) {

            tiles.add(new Tile(String.valueOf(c)));
            c++;
        }
        */
        System.out.println();

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % NUM_PER_ROW));
            tile.setTranslateY(50 * (i / NUM_PER_ROW));
            root.getChildren().add(tile);
        }

        return root;
    }
    
 

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private class Tile extends StackPane {
        TextField text = new TextField();
        public Tile(Integer value) {


            Rectangle border = new Rectangle(50, 50);
            border.setFill(null);
            border.setStroke(Color.BLACK);

           // text.setText(String.valueOf(value));
           // text.setFont(Font.font(30));
            if (value > 0) {
                text.setText(String.valueOf(value));
                text.setEditable(false);
            }
            else
                text.setText("-");
            text.setFont(Font.font(30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}