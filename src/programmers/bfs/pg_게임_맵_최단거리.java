package programmers.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class pg_게임_맵_최단거리 {
    private static final int WALL = 0;
    private static final int DX[] = {-1, 1, 0, 0};
    private static final int DY[] = {0, 0, -1, 1};

    private static int n, m;
    private static boolean[][] visit;

    public static void main(String[] args) {
        int[][] maps = {{1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1},};
        System.out.println(solution(maps));
    }

    public static int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visit = new boolean[n][m];

        return getMoveCount(maps);
    }

    private static int getMoveCount(int[][] maps) {
        Queue<Robot> q = new LinkedList<>();
        q.offer(new Robot(0, 0, 1));
        visit[0][0] = true;

        while (!q.isEmpty()) {
            Robot curRobot = q.poll();
            if (curRobot.isArrived()) {
                return curRobot.count;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = curRobot.x + DX[dir];
                int ny = curRobot.y + DY[dir];

                if (isRange(nx, ny) && !visit[nx][ny] && maps[nx][ny] != WALL) {
                    visit[nx][ny] = true;
                    q.offer(new Robot(nx, ny, curRobot.count + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static class Robot {
        private int x;
        private int y;
        private int count;

        public Robot(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public boolean isArrived() {
            return x == n - 1 && y == m - 1;
        }
    }
}
