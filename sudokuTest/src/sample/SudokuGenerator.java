package sample;

import java.util.Random;

public class SudokuGenerator {

	public static boolean boardGenerator(int[][] board, int row, int col) {
		int nextRow = row;
		int nextCol = col;
		int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random random = new Random();
		int tmp = 0;
		int current = 0;
		int top = num.length;

		for (int i = top - 1; i > 0; i--) {	//Swap method to get a random number from the array
			current = random.nextInt(i);
			tmp = num[current];
			num[current] = num[i];
			num[i] = tmp;
			
		}

		for (int i = 0; i < num.length; i++) {
			if (checkConflict(board, row, col, num[i])) {	//If there is no conflict in either the col, row or square
				board[row][col] = num[i];
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
				if (boardGenerator(board, nextRow, nextCol)) // Recursive call back to boardGenerator to check if the
																// number can be valid
					return true;
			}
		}
		board[row][col] = 0; // The next half of the board
		return false;
	}

	private static boolean checkConflict(int[][] board, int row, int col, int num) {
		for (int i = 0; i < 9; i++) { // Check if row has the same number
			if (num == board[row][i])
				return false;
		}
		for (int i = 0; i < 9; i++) { // Check if column has the same number
			if (num == board[i][col])
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
				if (num == board[i][j])
					return false;
		return true;
	}

	// Print the board
	public static void printBoard(int[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(board[i][j] + "  ");
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {

		int[][] x = new int[9][9];
		boardGenerator(x, 0, 0);
		printBoard(x);
	}

}