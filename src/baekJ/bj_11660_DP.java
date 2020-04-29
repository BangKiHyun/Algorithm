package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11660_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + board[i][j];
            }
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int min_x = Integer.parseInt(st.nextToken());
            int min_y = Integer.parseInt(st.nextToken());
            int max_x = Integer.parseInt(st.nextToken());
            int max_y = Integer.parseInt(st.nextToken());
            System.out.println(dp[max_x][max_y] - dp[max_x][min_y - 1] - dp[min_x - 1][max_y] + dp[min_x - 1][min_y - 1]);
        }
    }
}
