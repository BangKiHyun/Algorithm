package baekJ;

import java.util.Scanner;

public class bj_3109_백트랙킹 {
    private static int n, m;
    private static String[][] map;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1};

    public static void main(String[] args) {
        init();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (steelPipe(i, 0)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new String[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            map[i] = line.split("");
        }
    }

    private static boolean steelPipe(int x, int y) {
        if (y == m - 1) {
            return true;
        }

        for (int j = 0; j < 3; j++) {
            int nx = dx[j] + x;
            int ny = y + 1;

            if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && map[nx][ny].equals(".")) {
                visit[nx][ny] = true;
                if (steelPipe(nx, ny)) {
                    return true;
                }
            }
        }
        return false;
    }
}
