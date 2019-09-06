package problem;

import java.util.Scanner;

public class bj_14889_me {
    static int n;
    static int map[][];
    static boolean visit[];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();
        solution(0, 0);
        System.out.println(min);
    }

    private static void solution(int idx, int depth) {
        if (depth == n / 2) {
            min = Math.min(min, getSum());
            return;
        }

        for (int i = idx; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                solution(i + 1, depth + 1);
                visit[i] = false;
            }
        }
    }

    private static int getSum() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i] && visit[j]) {
                    start += map[i][j];
                } else if (!visit[i] && !visit[j]) {
                    link += map[i][j];
                }
            }
        }
        return Math.abs(start - link);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
    }
}
