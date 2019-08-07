package problem;

import java.util.Scanner;

public class bj_17070 {
    static int N;
    static int[][] map;
    static int result;

    static boolean isRange(int r, int c) {
        if (r <= 0 || r > N || c <= 0 || c > N) {
            return false;
        }
        return true;
    }

    static void dfs(int r, int c, int d) {
        if (r == N && c == N) {
            result++;
            return;
        }
        if (d != 2 && isRange(r, c + 1) && map[r][c + 1] == 0) {
            dfs(r, c + 1, 0);
        }
        if (d != 0 && isRange(r + 1, c) && map[r + 1][c] == 0) {
            dfs(r + 1, c, 2);
        }
        if (isRange(r + 1, c + 1) && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0 && map[r][c + 1] == 0) {
            dfs(r + 1, c + 1, 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        result = 0;
        dfs(1, 2, 0);
        System.out.println(result);
    }
}