package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_10026_DFS {
    private static int n;
    private static String[][] map;
    private static boolean[][] visit1;
    private static boolean[][] visit2;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input;
        n = Integer.parseInt(br.readLine());

        map = new String[n][n];
        visit1 = new boolean[n][n];
        visit2 = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = input[j];
            }
        }

        int ans1 = 0, ans2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit1[i][j]) {
                    visit1[i][j] = true;
                    goDFS1(i, j);
                    ans1++;
                }
                if (!visit2[i][j]) {
                    visit2[i][j] = true;
                    goDFS2(i, j);
                    ans2++;
                }
            }
        }

        System.out.println(ans1 + " " + ans2);
    }

    private static void goDFS1(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !visit1[nx][ny] && map[nx][ny].equals(map[x][y])) {
                visit1[nx][ny] = true;
                goDFS1(nx, ny);
            }
        }
    }

    private static void goDFS2(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !visit2[nx][ny]) {
                if (map[x][y].equals("B") && map[x][y].equals(map[nx][ny])) {
                    visit2[nx][ny] = true;
                    goDFS2(nx, ny);
                } else if (map[x][y].equals("R") || map[x][y].equals("G")) {
                    if ((map[nx][ny].equals("R") || map[nx][ny].equals("G"))) {
                        visit2[nx][ny] = true;
                        goDFS2(nx, ny);
                    }
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}
