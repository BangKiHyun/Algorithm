package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_7569_BFS {
    private static int n, m, h;
    private static int[][][] map;
    private static boolean[][][] visit;
    private static int[] dx = {-1, 1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, -1, 1, 0, 0};
    private static int[] dh = {0, 0, 0, 0, 1, -1};
    private static Queue<Node> q = new LinkedList<>();
    private static int countTomato = 0;
    private static int day = 0;

    private static final int RIPEN = 1;
    private static final int EMPTY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[n][m][h];
        visit = new boolean[n][m][h];

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == RIPEN) {
                        q.add(new Node(i, j, k, 0));
                        visit[i][j][k] = true;
                    } else if (map[i][j][k] == 0) countTomato++;
                }
            }
        }

        spreadTomato();

        System.out.println(countTomato == 0 ? day : -1);
    }

    private static void spreadTomato() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            day = now.day;

            for (int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nh = now.h + dh[i];

                if (isRange(nx, ny, nh)) {
                    countTomato--;
                    visit[nx][ny][nh] = true;
                    q.add(new Node(nx, ny, nh, now.day + 1));
                }
            }
        }
    }

    private static boolean isRange(int x, int y, int k) {
        return x >= 0 && y >= 0 && k >= 0 && x < n && y < m && k < h && !visit[x][y][k] && map[x][y][k] != RIPEN && map[x][y][k] != EMPTY;
    }

    private static class Node {
        private int x;
        private int y;
        private int h;
        private int day;

        public Node(int x, int y, int h, int day) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.day = day;
        }
    }
}
