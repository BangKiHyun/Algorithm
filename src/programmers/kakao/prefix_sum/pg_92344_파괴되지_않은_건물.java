package programmers.kakao.prefix_sum;

public class pg_92344_파괴되지_않은_건물 {

    private static final int DESTROY = 1;

    private static int maxRow;
    private static int maxCol;

    public static void main(String[] args) {
        final pg_92344_파괴되지_않은_건물 task = new pg_92344_파괴되지_않은_건물();
        int[][] borad = {{5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4},
                {1, 2, 0, 2, 3, 2},
                {2, 1, 0, 3, 1, 2},
                {1, 0, 1, 3, 3, 1}};
        System.out.println(task.solution(borad, skill));
    }

    public int solution(int[][] board, int[][] skills) {
        maxRow = board.length;
        maxCol = board[0].length;
        int[][] prefixSum = new int[maxRow + 1][maxCol + 1];
        for (int[] skill : skills) {
            int sign = skill[0] == DESTROY ? -1 : 1;
            int startRow = skill[1];
            int startCol = skill[2];
            int endRow = skill[3];
            int endCol = skill[4];
            int degree = skill[5] * sign;

            prefixSum[startRow][startCol] += degree;
            prefixSum[startRow][endCol + 1] += (degree * -1);
            prefixSum[endRow + 1][startCol] += (degree * -1);
            prefixSum[endRow + 1][endCol + 1] += degree;
        }
        calculatePrefixSum(prefixSum);

        return calculateNotDestroyBuilding(board, prefixSum);
    }

    private int calculateNotDestroyBuilding(int[][] board, int[][] prefixSum) {
        int notDestroyCnt = 0;
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                if (board[row][col] + prefixSum[row][col] > 0) {
                    notDestroyCnt++;
                }
            }
        }
        return notDestroyCnt;
    }

    private void calculatePrefixSum(int[][] prefixSum) {
        calculatePrefixSumOfUpToDown(prefixSum);
        calculatePrefixSumOfLeftToRight(prefixSum);
    }

    private void calculatePrefixSumOfUpToDown(int[][] prefixSum) {
        for (int row = 1; row <= maxRow; row++) {
            for (int col = 0; col <= maxCol; col++) {
                prefixSum[row][col] += prefixSum[row - 1][col];
            }
        }
    }

    private void calculatePrefixSumOfLeftToRight(int[][] prefixSum) {
        for (int col = 1; col <= maxCol; col++) {
            for (int row = 0; row <= maxRow; row++) {
                prefixSum[row][col] += prefixSum[row][col - 1];
            }
        }
    }
}