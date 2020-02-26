package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11404_플로이드 {
    private static final int INF = 1000000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] busCost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for(int j=1;j<=n;j++){
                if(i == j) busCost[i][j] = 0;
                else busCost[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (busCost[start][end] > cost) {
                busCost[start][end] = cost;
            }
        }

        goFloyd(n, busCost);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(busCost[i][j] == INF ? 0 : busCost[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void goFloyd(int n, int[][] busCost) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (busCost[i][j] > busCost[i][k] + busCost[k][j]) {
                        busCost[i][j] = busCost[i][k] + busCost[k][j];
                    }
                }
            }
        }
    }
}
