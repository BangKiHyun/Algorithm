package problem;

import java.util.Scanner;

public class bj_17471_브루트포스 {
    private static int n;
    private static int people[];
    private static boolean visit[];
    private static int[][] link;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();
        permucomb(1);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        people = new int[n + 1];
        visit = new boolean[n + 1];
        link = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            people[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            int cnt = sc.nextInt();
            for (int j = 1; j <= cnt; j++) {
                int adj = sc.nextInt();
                link[i][adj] = 1;
            }
        }
    }

    private static void permucomb(int start) {
        if (isValid()) {
            System.out.println(start);
            min = Math.min(min, getPopulation());
        }

        for (int i = start; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                permucomb(i + 1);
                visit[i] = false;
            }
        }
    }

    private static boolean isValid() {
        int area1 = 0, area2 = 0;
        boolean check[] = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (visit[i]) {
                area1++;
                check[i] = true;
                for (int j = 1; j <= n; j++) {
                    if (visit[j] && link[i][j] == 1 && !check[j]) {
                        check[j] = true;
                    }
                }
            } else {
                area2++;
                check[i] = true;
                for (int j = 1; j <= n; j++) {
                    if (!visit[j] && link[i][j] == 1 && !check[j]) {
                        check[j] = true;
                    }
                }
            }
        }

        if (area1 == 0 || area2 == 0) return false;

        for (int i = 1; i <= n; i++) {
            if (!check[i]) return false;
        }

        return true;
    }

    private static int getPopulation() {
        int area1 = 0, area2 = 0;

        for (int i = 1; i <= n; i++) {
            if (visit[i]) {
                area1 += people[i];
            } else {
                area2 += people[i];
            }
        }
        return Math.abs(area1 - area2);
    }
}
