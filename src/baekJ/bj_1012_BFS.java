package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_1012_BFS {
    private static int map[][];
    private static int m, n;
    private static int[] X = {-1, 1, 0, 0};
    private static int[] Y = {0, 0, -1, 1};
    private static boolean visit[][];
    private static Queue<Farm> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 0; test_case < T; test_case++) {
            int ans = 0;
            m = sc.nextInt();
            n = sc.nextInt();
            int bug = sc.nextInt();
            map = new int[m][n];
            visit = new boolean[m][n];
            queue = new LinkedList<>();

            for (int i = 0; i < bug; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[x][y] = 1;
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        queue.add(new Farm(i, j));
                        visit[i][j] = true;
                        bfs();
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Farm tmpFarm = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmpFarm.x + X[i];
                int ny = tmpFarm.y + Y[i];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visit[nx][ny] && map[nx][ny] == 1) {
                    queue.add(new Farm(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
    }

    private static class Farm {
        int x, y;

        Farm(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
