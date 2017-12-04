package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuUtility {

    final int max = 8;
    final int min = 0;

    final int digitMax = 9;
    final int digitMin = 1;
    int[][] grid = new int[9][9];

    SudokuUtility() {
        startUp();
    }
    public int[][] getBoard() {
        return grid;

    }
    private void startUp() {

        Random random = new Random();

        int row = 0;
        int col = 0;

        int randomNumber = 0;
        int noOfCellsToBeGenerated = 40;

        for (int i = 1; i <= noOfCellsToBeGenerated; i++) {
            row = random.nextInt((max - min) + 1) + min;
            col = random.nextInt((max - min) + 1) + min;
            randomNumber = random.nextInt((digitMax - digitMin) + 1) + digitMin;

            if (grid[row][col] == 0 && noConflict(grid, row, col, randomNumber)) {
                grid[row][col] = randomNumber;
            } else {
                i--;
            }

        }


//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.print(grid[i][j] + "  ");
//            }
//            System.out.println();
//        }


    }

    private boolean noConflict(int[][] array, int row, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if (array[row][i] == num) {
                return false;
            }
            if (array[i][col] == num) {
                return false;
            }
        }

        int gridRow = row - (row % 3);
        int gridColumn = col - (col % 3);

        for (int p = gridRow; p < gridRow + 3; p++) {
            for (int q = gridColumn; q < gridColumn + 3; q++) {
                if (array[p][q] == num) {
                    return false;
                }
            }
        }
        System.out.println();
        return true;
    }
}