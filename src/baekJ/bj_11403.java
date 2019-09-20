package baekJ;

import java.util.Scanner;

public class bj_11403 {
    static int n;
    static int[][] road;
    static boolean visit[];

    public static void main(String[] args) {
        init();
        solution();
        result();
    }

    static void result() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(road[i][j] == 999 ? 0 + " " : 1 + " ");
            }
            System.out.println();
        }
    }

    static void solution() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (road[i][j] > road[i][k] + road[k][j])
                        road[i][j] = road[i][k] + road[k][j];
                }
            }
        }
    }

    static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visit = new boolean[n];
        road = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                road[i][j] = sc.nextInt();
                if (road[i][j] != 1) {
                    road[i][j] = 999;
                }
            }
        }
    }
}
