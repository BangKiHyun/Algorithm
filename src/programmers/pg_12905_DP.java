package programmers;

public class pg_12905_DP {
    private static int n, m;

    public static void main(String[] args) {
        int[][] board = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        System.out.println(solution(board));
    }

    private static int solution(int[][] board) {
        n = board.length;
        m = board[0].length;

        int max = 0;
        if (n == 1 || m == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) {
                        return 1;
                    }
                }
            }
        } else {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (board[i][j] != 0) {
                        int left = board[i][j - 1];
                        int up = board[i - 1][j];
                        int leftUp = board[i - 1][j - 1];
                        board[i][j] = getMin(left, up, leftUp) + 1;
                        max = Math.max(max, board[i][j]);
                    }
                }
            }
        }

        return max * max;
    }

    private static int getMin(final int left, final int up, final int leftUp) {
        int min = Math.min(left, up);
        return Math.min(min, leftUp);
    }
}
