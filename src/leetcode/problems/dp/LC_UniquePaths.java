package leetcode.problems.dp;

import java.util.Arrays;

public class LC_UniquePaths {

    public static void main(String[] args) {
        final LC_UniquePaths task = new LC_UniquePaths();
        int m = 3;
        int n = 7;
        System.out.println(task.uniquePaths(m, n));
    }

    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];
        for (int[] line : cache) {
            Arrays.fill(line, -1);
        }
        return findUniquePaths(0, 0, m, n, cache);
    }

    private int findUniquePaths(int raw, int col, int m, int n, int[][] cache) {
        if (raw == m || col == n) {
            return 0;
        }
        if (raw == m - 1 && col == n - 1) {
            return 1;
        }
        if (cache[raw][col] != -1) {
            return cache[raw][col];
        }

        return cache[raw][col] = findUniquePaths(raw + 1, col, m, n, cache)
                + findUniquePaths(raw, col + 1, m, n, cache);
    }
}
