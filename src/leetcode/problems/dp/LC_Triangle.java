package leetcode.problems.dp;

import java.util.Arrays;
import java.util.List;

public class LC_Triangle {
    private int maxSize;

    public static void main(String[] args) {
        final LC_Triangle task = new LC_Triangle();
        List<List<Integer>> triangle = Arrays.asList(Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3));
        System.out.println(task.minimumTotal(triangle));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        maxSize = triangle.size();
        int[][] cache = new int[maxSize][maxSize];
        return findMinimumTotal(triangle, cache, 0, 0);
    }

    private int findMinimumTotal(List<List<Integer>> triangle, int[][] cache, int raw, int col) {
        if (raw == maxSize) {
            return 0;
        }
        if (cache[raw][col] != 0) {
            return cache[raw][col];
        }

        return cache[raw][col] = triangle.get(raw).get(col) +
                Math.min(findMinimumTotal(triangle, cache, raw + 1, col),
                        findMinimumTotal(triangle, cache, raw + 1, col + 1));
    }
}
