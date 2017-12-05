package sample;

import java.util.Random;

public class SudokuUtility {

	final int max = 8;
	final int min = 0;

	final int digitMax = 9;
	final int digitMin = 1;
	int[][] grid = new int[digitMax][digitMax];

	SudokuUtility() {
		startUp();
	}

	public int[][] getBoard() {
		return grid;

	}

	private void startUp() {

		Random random = new Random();

		int row, col;

		int randomNumber;
		int noOfCellsToBeGenerated = 60;

		for (int i = 1; i <= noOfCellsToBeGenerated; i++) {
			row = random.nextInt((max - min) + 1) + min;
			col = random.nextInt((max - min) + 1) + min;
			randomNumber = random.nextInt((digitMax - digitMin) + 1) + digitMin;

			if (grid[row][col] == 0 && legalMove(row, col, randomNumber)) {
				grid[row][col] = randomNumber;
			} else {
				i--;
			}

		}
	}

	public boolean userWin() {
		boolean win = true;
		for (int x = 0; x < digitMax; x++) {
			for (int y = 0; y < digitMax; y++) {
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

	public boolean noConflict(int row, int col, int num) {

		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == num) {
				return false;
			}
			if (grid[i][col] == num) {
				return false;
			}
		}

		int gridRow = row - (row % 3);
		int gridColumn = col - (col % 3);

		for (int p = gridRow; p < gridRow + 3; p++) {
			for (int q = gridColumn; q < gridColumn + 3; q++) {
				if (grid[p][q] == num) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean legalMove(int x, int y, int current) {
		for (int i = 0; i < 9; i++) {
			if (current == grid[x][i])
				return false;
		}
		for (int i = 0; i < 9; i++) {
			if (current == grid[i][y])
				return false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if (x > 2)
			if (x > 5)
				cornerX = 6;
			else
				cornerX = 3;
		if (y > 2)
			if (y > 5)
				cornerY = 6;
			else
				cornerY = 3;
		for (int i = cornerX; i < 10 && i < cornerX + 3; i++)
			for (int j = cornerY; j < 10 && j < cornerY + 3; j++)
				if (current == grid[i][j])
					return false;
		return true;
	}

}