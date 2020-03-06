package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.
//
//한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.
//
//두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다.
//지도는 바다로 둘러쌓여 있으며, 지도 밖으로 나갈 수 없다.
public class bj_4963_BFS {
    private static int[][] map;
    private static boolean[][] visit;
    private static Queue<Island> q = new LinkedList<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int dx[] = {-1, 1, 0, 0, 1, 1, -1 ,-1};
    private static int dy[] = {0, 0, -1, 1, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {

        while (true) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if (TerminateSignal(x, y)) break;
            initMap(x, y);

            int ans = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        ans++;
                        checkConnectedIsland(i, j, x, y);
                    }
                }
            }
            System.out.println(ans);
        }
    }

    private static boolean TerminateSignal(int x, int y) {
        return x == 0 && y == 0;
    }

    private static void initMap(int x, int y) throws IOException {
        map = new int[x][y];
        visit = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void checkConnectedIsland(int x, int y, int n, int m) {
        q.add(new Island(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Island now = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && map[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    q.add(new Island(nx, ny));
                }
            }
        }
    }

    private static class Island {
        private int x;
        private int y;

        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
