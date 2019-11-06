package baekJ;

import java.util.Scanner;

public class bj_1018_브루트포스 {
    private static int r, c;
    private static String map[][];
    private static String WHITE = "W", BLACK = "B";

    public static void main(String[] args) {
        init();
        solution();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new String[r][c];

        for (int i = 0; i < r; i++) {
            String line = sc.next();
            map[i] = line.split("");
        }
    }

    private static void solution() {
        int min = 64;

        for (int i = 0; i < r - 7; i++) {
            for (int j = 0; j < c - 7; j++) {
                int cntBlack = findCnt(i, j, BLACK, WHITE);
                int cntWhite = findCnt(i, j, WHITE, BLACK);

                int minCnt = Math.min(cntBlack, cntWhite);
                min = Math.min(min, minCnt);
            }
        }

        System.out.println(min);
    }

    private static int findCnt(int position_i, int position_j, String correctColor, String incorrectColor) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String color = map[i + position_i][j + position_j];
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        if (!color.equals(correctColor)) {
                            cnt++;
                        }
                    } else {
                        if (!color.equals(incorrectColor)) {
                            cnt++;
                        }
                    }
                } else {
                    if (j % 2 == 0) {
                        if (!color.equals(incorrectColor)) {
                            cnt++;
                        }
                    } else {
                        if (!color.equals(correctColor)) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

}
