package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1941_DFS_Re {
    private static final int N = 5;
    private static final int Y = 1;
    private static final int S = 2;

    private static int[][] map = new int[N][N];
    private static boolean[][] visit = new boolean[N][N];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        for (int i = 0; i < N; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                if ("Y".equals(input[j])) map[i][j] = Y;
                else map[i][j] = S;
            }
        }

        goDFS(0, 0, 0);

        System.out.println(ans);
    }

    private static void goDFS(int start, int som, int depth) {
        if (depth == 7) {
            if (som < 4) return;

            int x = start / N;
            int y = start % N;
            boolean[][] check = new boolean[N][N];
            check[x][y] = true;
            if (isConnected(x, y, 1, check)) ans++;
            return;
        }

        for (int i = start + 1; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            if (!visit[x][y]) {
                visit[x][y] = true;
                if (map[x][y] == S) goDFS(i, som + 1, depth + 1);
                else goDFS(i, som, depth + 1);
                visit[x][y] = false;
            }
        }
    }

    private static boolean isConnected(int x, int y, int depth, boolean[][] check) {
        if (depth == 7) return true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !check[nx][ny] && visit[nx][ny]) {
                check[nx][ny] = true;
                if (isConnected(nx, ny, depth + 1, check)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}