package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_67259_kakao {
    private static final int STRAIGHT = 100;
    private static final int CORNER = 600;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int n;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//                {{0, 0, 0, 0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 1, 0, 0, 0},
//                {0, 0, 0, 1, 0, 0, 0, 1},
//                {0, 0, 1, 0, 0, 0, 1, 0},
//                {0, 1, 0, 0, 0, 1, 0, 0},
//                {1, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    private static int solution(int[][] board) {
        n = board.length;
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, -1, 0));

        int answer = Integer.MAX_VALUE;
        int[][] map = new int[n][n];
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int cost = cur.cost;

            System.out.println(String.format("x: %d, y: %d, cost: %d", x, y, cost));

            if (x == n - 1 && y == n - 1) {
                answer = Math.min(answer, cost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValid(nx, ny, board)) {
                    int newCost = 0;

                    if (cur.dir == -1 || cur.dir == i) {
                        newCost = cost + STRAIGHT;
                    } else {
                        newCost = cost + CORNER;
                    }

                    if (map[nx][ny] == 0 || map[nx][ny] >= newCost) {
                        map[nx][ny] = newCost;
                        q.add(new Pos(nx, ny, i, newCost));
                    }
                }
            }
        }

        return answer;
    }

    private static boolean isValid(final int nx, final int ny, final int[][] board) {
        return nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1;
    }

    private static class Pos {
        private final int x;
        private final int y;
        private final int dir;
        private final int cost;

        public Pos(final int x, final int y, final int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
}
