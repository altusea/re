package org.example.codewar;

public class SudokuValidator {
    public static boolean check(int[][] sudoku) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subBoxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int cur = sudoku[i][j];
                if (cur == 0) {
                    return false;
                }
                int index = cur - 1;
                rows[i][index]++;
                columns[j][index]++;
                subBoxes[i / 3][j / 3][index]++;
                if (rows[i][index] > 1 || columns[j][index] > 1 || subBoxes[i / 3][j / 3][index] > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
