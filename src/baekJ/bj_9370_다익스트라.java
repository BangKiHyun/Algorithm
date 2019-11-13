package baekJ;

import java.util.Arrays;
import java.util.Scanner;

public class bj_9370_다익스트라 {
    private static Scanner sc = new Scanner(System.in);
    private static int n, m, k;
    private static int start, passStart, passEnd;
    private static int[][] distance;
    private static int[] candidate;

    public static void main(String[] args) {
        int t = sc.nextInt();

        for (int test_case = 0; test_case < t; test_case++) {
            init();
            findShortPath();
            for (int i : candidate) {
                if (distance[start][i] == (distance[passStart][i] + distance[start][passStart]) && distance[start][i] == (distance[passEnd][i] + distance[start][passEnd])) {
                    System.out.print(i + " ");
                }
            }
        }
    }

    private static void init() {
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        start = sc.nextInt();
        passStart = sc.nextInt();
        passEnd = sc.nextInt();

        distance = new int[n + 1][n + 1];
        candidate = new int[k];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                distance[i][j] = 99999999;
            }
        }

        for (int i = 0; i < m; i++) {
            int pathStart = sc.nextInt();
            int pathEnd = sc.nextInt();
            int dis = sc.nextInt();
            distance[pathStart][pathEnd] = dis;
            distance[pathEnd][pathStart] = dis;
        }

        for (int i = 0; i < k; i++) {
            candidate[i] = sc.nextInt();
        }
        Arrays.sort(candidate);
    }

    private static void findShortPath() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }
}
