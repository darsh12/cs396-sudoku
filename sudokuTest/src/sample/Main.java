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
import java.lang.String;
import javafx.scene.input.InputEvent;

public class Main extends Application {

	private static final int NUM_PER_ROW = 9;


	SudokuGenerator board = new SudokuGenerator();

	private Parent createContent() {

		int[][] grid = board.generate();

		Pane root = new Pane();
		root.setPrefSize(450, 450);
		List<Tile> tiles = new ArrayList<>();

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				tiles.add(new Tile(grid[x][y], x, y));
			}
		}

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
		boolean editable = true;
		int row, col;

		public Tile(Integer value, int x, int y) {

			this.row = x;
			this.col = y;

			Rectangle border = new Rectangle(50, 50);
			border.setFill(null);
			border.setStroke(Color.BLACK);

			if (value > 0) {
				text.setText(String.valueOf(value));
				text.setEditable(false);
				this.editable = false;
				text.setStyle("-fx-background-color: ivory");
			} else
				text.setText("");
			text.setFont(Font.font(30));

			setAlignment(Pos.CENTER);
			getChildren().addAll(border, text);
			setOnKeyReleased(this::getUserInput);

		}

		public void getUserInput(InputEvent event) {

			int t = 0;
			boolean validInput = false;
			try {
				t = Integer.valueOf(this.text.getText());
			} catch (Exception e) {
			}

			if (this.editable) {
				if (t > 0 && t < 10) {
					validInput = board.checkConflict(this.row, this.col, t);

					if (!validInput) {
						text.setText("");
						board.delGrid(this.row, this.col);
						text.setStyle("-fx-background-color: red");
						AlertBox.display("Invalid Move", "This answer is not a valid move!");

					} else {
						board.addGrid(this.row, this.col, t);
						text.setStyle("-fx-background-color: green");
						if (board.userWin())
							AlertBox.display("You WIN!", "You completed the puzzle, great job!");
					}
				} else {
					text.setText("");
					board.delGrid(this.row, this.col);
				}

			}

		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}