package org.example.codewar;

public class Matrix {

    public static int determinant(int[][] a) {
        if (a.length == 1) {
            return a[0][0];
        } else {
            int sign = 1;
            int sum = 0;
            for (int i = 0; i < a.length; i++) {
                sum += sign * a[0][i] * determinant(minor(a, 0, i));
                sign *= -1;
            }
            return sum;
        }
    }

    private static int[][] minor(int[][] a, int x, int y) {
        int length = a.length - 1;
        int[][] result = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++) {
                if (i < x && j < y) {
                    result[i][j] = a[i][j];
                } else if (i >= x && j < y) {
                    result[i][j] = a[i + 1][j];
                } else if (i < x && j >= y) {
                    result[i][j] = a[i][j + 1];
                } else {
                    result[i][j] = a[i + 1][j + 1];
                }
            }
        return result;
    }
}
