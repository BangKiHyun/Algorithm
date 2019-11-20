package baekJ;

import java.util.Scanner;

public class bj_1051_브루트포스 {
    private static int n, m;
    private static int map[][];
    private static int max = 1;

    public static void main(String[] args) {
        init();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = map[i][j];
                findShape(num, i, j);
            }
        }
        System.out.println(max);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            String temp[] = line.split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
    }

    private static void findShape(int num, int row, int col) {
        int pre_r = getRow(num, row, col);
        int pre_c = getCol(num, col, row);

        if (pre_r == -1 || pre_c == -1) {
            return;
        } else {
            int post_r = pre_r - row;
            int post_c = pre_c - col;

            if (post_c != post_r || map[pre_r][pre_c] != num) {
                return;
            }

            int mul = (post_r + 1) * (post_c + 1);
            max = Math.max(max, mul);
        }
    }

    private static int getRow(int num, int row, int col) {
        for (int i = n - 1; i > row; i--) {
            if (map[i][col] == num) {
                return i;
            }
        }
        return -1;
    }

    private static int getCol(int num, int col, int row) {
        for (int i = m - 1; i > col; i--) {
            if (map[row][i] == num) {
                return i;
            }
        }
        return -1;
    }
}
