package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1051_Re {
    private static int[][] board;
    private static int n, m, max_length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new int[n][m];
        max_length = Math.max(n, m);

        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        if (n == m) {
            if (isShape(0, 0, max_length - 1)) {
                System.out.println(n * m);
                return;
            }
        }

        for (int k = max_length; k >= 1; k--) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (isRange(i, j, k)) {
                        if (isShape(i, j, k)) {
                            System.out.println((k + 1) * (k + 1));
                            return;
                        }
                    } else break;
                }
            }
        }

        System.out.println(1);
    }

    private static boolean isRange(int i, int j, int k) {
        return i + k < n && j + k < m;
    }

    private static boolean isShape(int i, int j, int k) {
        return (board[i][j] == board[i][j + k]) && (board[i][j + k] == board[i + k][j]) && (board[i + k][j] == board[i + k][j + k]);
    }
}

/*
3 3
122
221
112

5 5
40013
10077
88888
66661
91002
*/
