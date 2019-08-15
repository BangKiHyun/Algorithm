package SW.D4;

import java.util.Scanner;

public class sw_7699 {
    static char map[][];
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};
    static int max;
    static boolean visit[];
    static int R, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            R = sc.nextInt();
            C = sc.nextInt();
            map = new char[R][C];
            for (int i = 0; i < R; i++) {
                String s = sc.next();
                for (int j = 0; j < C; j++) {
                    map[i][j] = s.charAt(j);
                }
            }
            max = 0;
            visit = new boolean[R*C];
            dfs(0, 0, 1);

            System.out.println("#" + test_case + " " + max);
        }
        sc.close();
    }

    public static void dfs(int x, int y, int cnt) {
        visit[map[x][y] -'A'] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + X[i];
            int ny = y + Y[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visit[map[nx][ny] - 'A']){
                dfs(nx, ny, cnt + 1);
            }
        }

        visit[map[x][y] - 'A'] = false;
        if (cnt > max) {
            max = cnt;
        }
    }
}
