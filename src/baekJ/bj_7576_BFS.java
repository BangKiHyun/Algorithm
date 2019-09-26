package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_7576_BFS {
    private static int map[][];
    private static boolean visit[][];
    private static int[] X = {-1, 1, 0, 0};
    private static int[] Y = {0, 0, -1, 1};
    private static Queue<Tomato> q = new LinkedList<>();
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        map = new int[m][n];
        visit = new boolean[m][n];
        int total = n * m;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    q.add(new Tomato(i, j, 0));
                    visit[i][j] = true;
                    total -= map[i][j];
                } else {
                    total += map[i][j];
                }
            }
        }
        total = bfs(n, m, total);

        if (total != 0) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    private static int bfs(int n, int m, int total) {
        while (!q.isEmpty()) {
            Tomato tmpTomato = q.poll();

            if (tmpTomato.time > max) {
                max = tmpTomato.time;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tmpTomato.x + X[i];
                int ny = tmpTomato.y + Y[i];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visit[nx][ny] && map[nx][ny] == 0) {
                    total--;
                    q.add(new Tomato(nx, ny, tmpTomato.time + 1));
                    visit[nx][ny] = true;
                }
            }
        }
        return total;
    }

    private static class Tomato {
        int x, y, time;

        Tomato(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
