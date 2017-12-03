package sample;
import java.util.Random;

public class SudokuUtility {

    static final int max = 8;
    static final int min = 0;

    static final int digitMax = 9;
    static final int digitMin = 1;

    static final int easyMin = 36;
    static final int easyMax = 49;

    static final int mediumMin = 32;
    static final int mediumMax = 35;

    static final int hardMin = 22;
    static final int hardMax = 27;


    public static void main(String[] args) {

        int[][] grid = new int[9][9];

        String option = "easy";

        Random random = new Random();

        int row = 0;
        int col = 0;

        int randomNumber = 0;
        int noOfCellsToBeGenerated = 0;

        if ("easy".equals(option)) {
            noOfCellsToBeGenerated = random.nextInt((easyMax - easyMin) + 1) + easyMin;
        } else if ("medium".equals(option)) {
            noOfCellsToBeGenerated = random.nextInt((mediumMax - mediumMin) + 1) + mediumMin;
        } else {
            noOfCellsToBeGenerated = random.nextInt((hardMax - hardMin) + 1) + hardMin;
        }

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

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }

    }

    public static boolean noConflict(int[][] array, int row, int col, int num) {

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
        return true;
    }
}