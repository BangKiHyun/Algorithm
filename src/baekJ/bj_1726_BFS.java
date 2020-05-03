package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1726_BFS {
    private static final int[] dx = {0, 0, 0, 1, -1};
    private static final int[] dy = {0, 1, -1, 0, 0};

    private static int n, m;
    private static int[][] map;
    private static boolean[][][] visit;
    private static Robot finish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visit = new boolean[5][n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int finish_x = Integer.parseInt(st.nextToken());
        int finish_y = Integer.parseInt(st.nextToken());
        int finish_d = Integer.parseInt(st.nextToken());
        finish = new Robot(finish_x, finish_y, finish_d, 0);

        goBFS(x, y, d);
    }

    private static void goBFS(int start_x, int start_y, int start_d) {
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(start_x, start_y, start_d, 0));
        visit[start_d][start_x][start_y] = true;

        while (!q.isEmpty()) {
            Robot cur = q.poll();

            if (cur.x == finish.x && cur.y == finish.y && cur.d == finish.d) {
                System.out.println(cur.cnt);
                return;
            }

            for (int distance = 1; distance <= 3; distance++) {
                int nx = dx[cur.d] * distance + cur.x;
                int ny = dy[cur.d] * distance + cur.y;

                if (isValid(nx, ny, cur.d)) {
                    visit[cur.d][nx][ny] = true;
                    q.add(new Robot(nx, ny, cur.d, cur.cnt + 1));
                }
            }

            for (int dir = 1; dir <= 4; dir++) {
                if (cur.d != dir && !visit[dir][cur.x][cur.y]) {
                    int moveCnt = 1;
                    if (cur.d == 1 && dir == 2) ++moveCnt;
                    else if (cur.d == 2 && dir == 1) ++moveCnt;
                    else if (cur.d == 3 && dir == 4) ++moveCnt;
                    else if (cur.d == 4 && dir == 3) ++moveCnt;
                    visit[dir][cur.x][cur.y] = true;
                    q.add(new Robot(cur.x, cur.y, dir, cur.cnt + moveCnt));
                }
            }
        }

    }

    private static boolean isValid(int x, int y, int d) {
        return x > 0 && y > 0 && x <= n && y <= m && map[x][y] == 0 && !visit[d][x][y];
    }

    private static class Robot {
        private int x;
        private int y;
        private int d;
        private int cnt;


        private Robot(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }
}