package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_1829_BFS {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static boolean[][] visit;

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {
                {1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};

        for (int ans : solution(m, n, picture)) {
            System.out.println(ans);
        }
    }

    public static int[] solution(int m, int n, int[][] picture) {
        Queue<Node> q = new LinkedList<>();
        visit = new boolean[m][n];
        int cnt = 0;
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visit[i][j]) {
                    q.offer(new Node(i, j, picture[i][j]));
                    visit[i][j] = true;
                    cnt++;
                    max = Math.max(max, getArea(q, picture));
                }
            }
        }
        return new int[]{cnt, max};
    }

    private static int getArea(final Queue<Node> q, final int[][] picture) {
        int n = picture.length;
        int m = picture[0].length;

        int area = 0;
        while (!q.isEmpty()) {
            area++;
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isRange(nx, ny, n, m) && cur.color == picture[nx][ny] && !visit[nx][ny]) {
                    q.offer(new Node(nx, ny, picture[nx][ny]));
                    visit[nx][ny] = true;
                }
            }
        }

        return area;
    }

    private static boolean isRange(final int nx, final int ny, final int n, final int m) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m;
    }

    private static class Node {
        private int x;
        private int y;
        private int color;

        public Node(final int x, final int y, final int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
