package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2573_BFS {
    private static int r, c;
    private static int map[][];
    private static int copy[][];
    private static int dx[] = {-1, 1, 0, 0};
    private static int dy[] = {0, 0, -1, 1};
    private static Queue<Iceberg> q = new LinkedList<>();
    private static boolean visit[][];
    private static int ans = 1;

    public static void main(String[] args) {
        init();
        System.out.println(solution() == 0 ? 0 : ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new int[r][c];
        copy = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
                copy[i][j] = map[i][j];
            }
        }
    }

    private static int solution() {
        while (true) {
            findIce();
            int divideCnt = getDivideCnt();
            if (divideCnt != 1) {
                return divideCnt;
            }
            copyMap();
            ans++;
        }
    }

    private static int getDivideCnt() {
        int cnt = 0;
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != 0 && !visit[i][j]) {
                    q.add(new Iceberg(i, j));
                    checkVisit();
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void checkVisit() {
        while (!q.isEmpty()) {
            Iceberg now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] != 0 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    q.add(new Iceberg(nx, ny));
                }
            }
        }
    }

    private static void findIce() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != 0) {
                    meltIce(i, j);
                }
            }
        }
    }

    private static void meltIce(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < r && ny < c && copy[nx][ny] == 0) {
                if (--map[x][y] == 0) return;

            }
        }
    }

    private static void copyMap() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    private static class Iceberg {
        int x, y;

        Iceberg(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
