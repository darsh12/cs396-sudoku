package sample;
import java.util.ArrayList;
import java.util.Collections;
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
        Pane root = new Pane();
        root.setPrefSize(475, 475);

        char c = 'A';
        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < NUM_OF_PAIRS; i++) {

            tiles.add(new Tile(String.valueOf(c)));
            c++;
        }
        Collections.shuffle(tiles);

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
        private TextField text = new TextField();

        public Tile(String value) {
            Rectangle border = new Rectangle(50, 50);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setText(value);
            text.setFont(Font.font(30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}