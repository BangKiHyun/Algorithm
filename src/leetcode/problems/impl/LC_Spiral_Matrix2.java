package leetcode.problems.impl;

public class LC_Spiral_Matrix2 {
    private int number = 1;

    public static void main(String[] args) {
        final LC_Spiral_Matrix2 problem = new LC_Spiral_Matrix2();
        int n = 3;
        for (int[] line : problem.generateMatrix(n)) {
            for (int answer : line) {
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        while (left <= right || top <= bottom) {
            spiralLeftToRight(left, right, top, matrix);
            top++;

            spiralTopToBottom(top, bottom, right, matrix);
            right--;

            spiralRightToLeft(right, left, bottom, matrix);
            bottom--;

            spiralBottomToTop(bottom, top, left, matrix);
            left++;
        }
        return matrix;
    }

    private void spiralLeftToRight(int left, int right, int raw, int[][] matrix) {
        for (int colIdx = left; colIdx <= right; colIdx++) {
            matrix[raw][colIdx] = number++;
        }
    }

    private void spiralTopToBottom(int top, int bottom, int col, int[][] matrix) {
        for (int rawIdx = top; rawIdx <= bottom; rawIdx++) {
            matrix[rawIdx][col] = number++;
        }
    }

    private void spiralRightToLeft(int right, int left, int raw, int[][] matrix) {
        for (int colIdx = right; colIdx >= left; colIdx--) {
            matrix[raw][colIdx] = number++;
        }
    }

    private void spiralBottomToTop(int bottom, int top, int col, int[][] matrix) {
        for (int rawIdx = bottom; rawIdx >= top; rawIdx--) {
            matrix[rawIdx][col] = number++;
        }
    }
}
