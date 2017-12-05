package sample;

import java.util.Random;

public class SudokuGenerator {
	final int boardSize = 9;

	int[][] grid = new int[boardSize][boardSize];

	public SudokuGenerator() {
		generate();
	}

	public int[][] generate() {	
		grid = new int[9][9];
		boardGenerator(0, 0);
		deleteNumbers(45);	//Delete number of numbers from the board
		return grid;
	}

	public boolean boardGenerator(int row, int col) {
		int nextRow = row;
		int nextCol = col;
		int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random random = new Random();
		int tmp = 0;
		int current = 0;
		int top = num.length;

		for (int i = top - 1; i > 0; i--) { // Swap method to get a random number from the array
			current = random.nextInt(i);
			tmp = num[current];
			num[current] = num[i];
			num[i] = tmp;

		}

		for (int i = 0; i < num.length; i++) {
			if (checkConflict(row, col, num[i])) { // If there is no conflict in either the col, row or square
				grid[row][col] = num[i];
				if (row == 8) { // If both the col and row have reached 8 then the board is complete
					if (col == 8) {
						return true;
					} else { // Reset the row and start from column
						nextRow = 0;
						nextCol = col + 1;
					}
				} else {
					nextRow = row + 1;
				}
				if (boardGenerator(nextRow, nextCol)) // Recursive call back to boardGenerator to check if the
														// number can be valid
					return true;
			}
		}
		grid[row][col] = 0; // The next half of the board
		return false;
	}

	public boolean checkConflict(int row, int col, int num) {
		for (int i = 0; i < 9; i++) { // Check if row has the same number
			if (num == grid[row][i])
				return false;
		}
		for (int i = 0; i < 9; i++) { // Check if column has the same number
			if (num == grid[i][col])
				return false;
		}
		int rowNum = 0;
		int colNum = 0;
		if (row > 2) {
			if (row > 5) {
				rowNum = 6;
			} else {
				rowNum = 3;
			}
		}

		if (col > 2) {
			if (col > 5) {
				colNum = 6;
			} else {
				colNum = 3;
			}
		}
		for (int i = rowNum; i < 10 && i < rowNum + 3; i++) // Check if the square in the row has the same number
			for (int j = colNum; j < 10 && j < colNum + 3; j++) // Check if the square in the column has the same number
				if (num == grid[i][j])
					return false;
		return true;
	}

	public void deleteNumbers(int num) {
		double square = 81;
		double delete = (double) num;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				double chance = delete / square;
				if (Math.random() <= chance) {	//Delete random holes
					grid[i][j] = 0;
					delete--;
				}
				square--;
			}
		}
	}

	public boolean userWin() {
		boolean win = true;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (grid[x][y] == 0)
					win = false;
			}
		}
		return win;
	}

	public void addGrid(int row, int col, int num) {
		grid[row][col] = num;
	}

	public void delGrid(int row, int col) {
		grid[row][col] = 0;
	}

	public void printBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(grid[i][j] + "  ");
			System.out.println();
		}
		System.out.println();
	}
}
