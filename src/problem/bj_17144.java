package problem;

import java.util.Scanner;

public class bj_17144 {
    static int r, c;
    static int map[][];
    static int rMap[][];
    static int copy[][];
    static int start1, start2;
    static boolean visit[][];
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        int t = sc.nextInt();
        map = new int[r][c];
        copy = new int[r][c];
        rMap = new int[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == -1) {
                    start1 = i;
                    start2 = j;
                }
                if (map[i][j] != 0) {
                    visit[i][j] = true;
                }
            }
        }
        soulution();
    }

    static void soulution() {
        spreadDust();
        copyMap();
        removeDust1();
        removeDust2();
        int ans = countDust();

        System.out.println(ans);
    }

    static void spreadDust() {
        int dust = 0, spread = 0, cnt;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cnt = 0;
                if (map[i][j] > 0 && visit[i][j]) {
                    dust = map[i][j];
                    spread = dust / 5;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + X[k];
                        int ny = j + Y[k];
                        if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] != -1) {
                            cnt++;
                            copy[nx][ny] += spread;
                        }
                    }
                    dust -= (spread * cnt);
                    map[i][j] = dust;
                }
            }
        }
    }

    static void copyMap() {
        for (int i = 0; i < r * c; i++) {
            int x = i / c;
            int y = i % c;
            map[x][y] += copy[x][y];
        }
    }

    static void removeDust1() {
        for (int i = 1; i < c; i++) ;
        for (int i = r - start1 - 2; i > 0; i--) ;
        for (int i = c - 1; i > 0; i--) ;
        for (int i = 0; i < start1; i++) ;
    }

    static void removeDust2() {

    }

    static int countDust() {
        int dustNum = 0;
        for (int i = 0; i < r * c; i++) {
            int x = i / c;
            int y = i % c;
            if (map[x][y] > 0) {
                dustNum += map[x][y];
            }
        }
        return dustNum;
    }
}
