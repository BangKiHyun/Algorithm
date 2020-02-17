package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bj_4991_BFS_DFS {
    private static char[][] map;
    private static boolean[][] visit;
    private static int[][] dist;
    private static boolean[] idx_dirty;
    private static final char DIRTY = '*';
    private static final char FURNITURE = 'x';
    private static final char START = 'o';
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] line = br.readLine().split(" ");
            int m = Integer.parseInt(line[0]);
            int n = Integer.parseInt(line[1]);
            if (TerminateSignal(n, m)) return;

            map = new char[n][m];
            visit = new boolean[n][m];
            dist = new int[11][11];

            int dirty_cnt = 0;
            int[][] check = new int[n][m];
            ArrayList<Robot> list = new ArrayList<>();
            int start_x = 0, start_y = 0;

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                map[i] = input.toCharArray();
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == START) {
                        list.add(new Robot(i, j));
                        start_x = i;
                        start_y = j;
                        check[i][j] = 0;
                    } else if (map[i][j] == DIRTY) {
                        list.add(new Robot(i, j));
                        check[i][j] = ++dirty_cnt;
                    }
                }
            }
            idx_dirty = new boolean[dirty_cnt + 1];

            for (int i = 0; i < list.size(); i++) {
                Robot robot = list.get(i);
                findDistance(robot.x, robot.y, n, m, check);
                visit = new boolean[n][m];
            }

            min = Integer.MAX_VALUE;
            idx_dirty[check[start_x][start_y]] = true;
            findMinimum(check[start_x][start_y], 0, 0, dirty_cnt);

            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        }
    }

    private static boolean TerminateSignal(int n, int m) {
        return n == 0 && m == 0;
    }

    //BFS
    private static void findDistance(int x, int y, int max_x, int max_y, int[][] check) {
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, 0));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Robot now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isValid(nx, ny, max_x, max_y)) {
                    if (isNotVisit(check[x][y], check[nx][ny])) {
                        int from = check[x][y];
                        int to = check[nx][ny];
                        dist[from][to] = dist[to][from] = now.moveCnt + 1;
                    }
                    visit[nx][ny] = true;
                    q.add(new Robot(nx, ny, now.moveCnt + 1));
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int max_x, int max_y) {
        return x >= 0 && y >= 0 && x < max_x && y < max_y && !visit[x][y] && map[x][y] != FURNITURE;
    }

    private static boolean isNotVisit(int from, int to) {
        return from < to;
    }

    //DFS
    private static void findMinimum(int start, int depth, int sum, int dirty_cnt) {
        if (depth == dirty_cnt) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i <= dirty_cnt; i++) {
            if (!idx_dirty[i]) {
                if (dist[start][i] == 0) continue;
                idx_dirty[i] = true;
                findMinimum(i, depth + 1, sum + dist[start][i], dirty_cnt);
                idx_dirty[i] = false;
            }
        }
    }

    private static class Robot {
        private int x;
        private int y;
        private int moveCnt;

        public Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Robot(int x, int y, int moveCnt) {
            this.x = x;
            this.y = y;
            this.moveCnt = moveCnt;
        }
    }
}
