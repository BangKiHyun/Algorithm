package SW.D4;

import java.util.HashSet;
import java.util.Scanner;

public class sw_2819 {
    static int X[] = {-1, 1, 0, 0};
    static int Y[] = {0, 0, -1, 1};
    static HashSet ans;
    static int map[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            map = new int[4][4];
            ans = new HashSet();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i, j, 1, map[i][j]);
                }
            }
            System.out.println("#" + test_case + " " + ans.size());
        }
    }

    static void dfs(int x, int y, int cnt, int n) {
        if (cnt == 7) {
            ans.add(n);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + X[i];
            int ny = y + Y[i];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
            dfs(nx, ny, cnt + 1, n * 10 + map[nx][ny]);
        }
    }
}
