package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_1832_re {
    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = //{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                {{0,2,0,0,0,2},{0,0,2,0,1,0},{1,0,0,2,2,0}};
        System.out.println(solution(m, n, cityMap));
    }

    private static int solution(int m, int n, int[][] cityMap) {
        int MOD = 20170805;
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        Queue<Board> q = new LinkedList<>();
        q.add(new Board(0, 0));
        int cnt = 0;

        while (!q.isEmpty()) {
            Board board = q.poll();
            if (board.x == m - 1 && board.y == n - 1) cnt++;

            for (int i = 0; i < 2; i++) {
                int nx = dx[i] + board.x;
                int ny = dy[i] + board.y;

                if (isRange(nx, ny, m, n, cityMap)) {
                    if (isStraight(nx, ny, cityMap)) {
                        if (i == 0) nx += dx[i];
                        else ny += dy[i];
                        boolean valid = false;
                        while (true) {
                            if (isRange(nx, ny, m, n, cityMap) && !isStraight(nx, ny, cityMap)) {
                                valid = true;
                                break;
                            } else if (isRange(nx, ny, m, n, cityMap) && isStraight(nx, ny, cityMap)) {
                                if (i == 0) nx += dx[i];
                                else ny += dy[i];
                            }
                            if (!isRange(nx, ny, m, n, cityMap)) break;
                        }
                        if (valid) q.add(new Board(nx, ny));
                    } else {
                        q.add(new Board(nx, ny));
                    }
                }
            }
        }
        return cnt % MOD;
    }

    private static boolean isRange(int x, int y, int m, int n, int[][] board) {
        return x < m && y < n && board[x][y] != 1;
    }

    private static boolean isStraight(int x, int y, int[][] board) {
        return board[x][y] == 2;
    }

    private static class Board {
        private int x;
        private int y;

        public Board(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
