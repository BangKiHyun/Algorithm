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
        int r = sc.nextInt();
        int c = sc.nextInt();
        map = new String[r][c];

        for (int i = 0; i < r; i++) {
            String line = sc.next();
            map[i] = line.split("");
        }
    }

    private static void solution() {
        for (int i = 0; i < r - 7; i++) {
            for (int j = 0; j < c - 7; j++) {
                if (map[0][0].equals(WHITE)) {
                    changeMap(i, j, BLACK);
                } else {
                    changeMap(i, j, WHITE);
                }
            }
        }
    }

    private static void changeMap(int position_i, int position_j, String mapColor) {
        boolean check = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    if (!map[position_i + i][position_j + j].equals(mapColor)) {
                        check = true;
                    }
                } else if (i % 2 != 0 && j % 2 != 0) {

                }
            }
        }
    }


}
