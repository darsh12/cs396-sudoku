package sudoku;

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

		// Add each element from generated array to the Tile list to use as input box
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				tiles.add(new Tile(grid[x][y], x, y));
			}
		}

		// Create a tile for each input box
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

			// 0 means a generated hole in the board for user to fill in.
			// So if its not 0, set it as the input box and do styling
			if (value > 0) {
				text.setText(String.valueOf(value));
				text.setEditable(false);
				this.editable = false; // make it non editable so user can't change set values
				text.setStyle("-fx-background-color: ivory");
				// If it is blank, set box as blank
			} else
				text.setText("");
			text.setFont(Font.font(30));

			setAlignment(Pos.CENTER);
			getChildren().addAll(border, text);
			// Detects when user types in the box
			setOnKeyReleased(this::getUserInput);

		}

		public void getUserInput(InputEvent event) {

			int t = 0;
			boolean validInput = false;
			// Has to use try to avoid errors with backspace for example
			try {
				t = Integer.valueOf(this.text.getText());
				// No need to bother catching it, will simply be ignored.
			} catch (Exception e) {
			}

			// Checks if block is a user editable one
			if (this.editable) {
				// Makes sure its in correct range
				if (t > 0 && t < 10) {
					// Checks if the move is valid
					validInput = board.checkConflict(this.row, this.col, t);

					// If its not, remove the user inputed text and make sure nothing is in the
					// grid.
					if (!validInput) {
						text.setText("");
						board.delGrid(this.row, this.col);
						text.setStyle("-fx-background-color: red");
						AlertBox.display("Invalid Move", "This answer is not a valid move!");
						// Otherwise add the spot to the grid
					} else {
						board.addGrid(this.row, this.col, t);
						text.setStyle("-fx-background-color: green");

						// If true, the user has completed board
						if (board.userWin()) {
							AlertBox.display("You WIN!", "You completed the puzzle, great job!");
							System.exit(1);
						}
					}
					// Used if the user enters invalid input such as -1
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