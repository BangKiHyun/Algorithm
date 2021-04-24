package programmers.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class pg_게임_맵_최단거리_Re {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    private static int n;
    private static int m;

    public static int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1));
        maps[0][0] = 0;
        while (!q.isEmpty()) {
            Node curNode = q.poll();
            if (curNode.isEnd(n, m)) {
                return curNode.distance;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = curNode.x + DX[dir];
                int ny = curNode.y + DY[dir];

                if (isRange(nx, ny, n, m) && maps[nx][ny] == 1) {
                    maps[nx][ny] = 0;
                    q.add(new Node(nx, ny, curNode.distance + 1));
                }
            }
        }

        return -1;
    }

    private static boolean isRange(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static class Node {
        private int x;
        private int y;
        private int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public boolean isEnd(int n, int m) {
            return this.x == n - 1 && this.y == m - 1;
        }
    }
}
