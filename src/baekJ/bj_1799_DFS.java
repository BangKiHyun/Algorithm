package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1799_DFS {
    private static int n;
    private static int[][] board;
    private static int max = -1;
    private static int[] dx = {1, 1, -1, -1};
    private static int[] dy = {1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        deployBishop(-1, 0);
        System.out.println(max);
    }

    private static void deployBishop(int start, int cnt) {
        max = Math.max(max, cnt);

        for (int i = start + 1; i < n * n; i++) {
            int x = i / n;
            int y = i % n;
            if (isValue(x, y)) {
                board[x][y] = 2;
                deployBishop(i, cnt + 1);
                board[x][y] = 1;
            }
        }
    }

    private static boolean isValue(int x, int y) {
        if (board[x][y] == 0) return false;

        for (int i = 0; i < 4; i++) {
            int current_x = x + dx[i];
            int current_y = y + dy[i];

            while (true) {
                if (isRange(current_x, current_y)) {
                    if (board[current_x][current_y] == 2) return false;
                    current_x += dx[i];
                    current_y += dy[i];
                } else break;
            }
        }
        return true;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}
