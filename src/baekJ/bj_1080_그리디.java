package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.
//
//행렬을 변환하는 연산은 어떤 3*3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 -> 1, 1 -> 0)
public class bj_1080_그리디 {
    private static BufferedReader br;
    private static boolean[][] board;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 행렬의 크기 N M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다.
        int n, m;
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        board = new boolean[n][m];

//둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.
        char[][] board_a = new char[n][m];
        char[][] board_b = new char[n][m];
        initBoard(board_a, n);
        initBoard(board_b, n);
        int sameCnt = combineBoard(board_a, board_b);

        if (isSmall(n, m)) {
            if (sameCnt == n * m) System.out.println(0);
            else System.out.println(-1);
            return;
        }

        int ans = getAnswer(n, m);

        System.out.println(ans);
    }

    private static void initBoard(char[][] board, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
    }

    private static int combineBoard(char[][] board_a, char[][] board_b) {
        int cnt = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board_a[i][j] == board_b[i][j]) {
                    board[i][j] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static boolean isSmall(int n, int m) {
        return n < 3 || m < 3;
    }

    private static int getAnswer(int n, int m) {
        int max_row = n - 3;
        int max_col = m - 3;
        int cnt = 0;
        for (int i = 0; i <= max_row; i++) {
            for (int j = 0; j <= max_col; j++) {
                if (!board[i][j]) {
                    changeBoard(i, j);
                    cnt++;
                }

                if (i == max_row && (!board[i + 1][j] || !board[i + 2][j])) return -1;
                if (j == max_col && (!board[i][j + 1] || !board[i][j + 2])) return -1;
            }
        }

        if (!isSameBoard(n - 2, m - 2)) return -1;
        return cnt;
    }

    private static void changeBoard(int col, int row) {
        for (int i = col; i < col + 3; i++) {
            for (int j = row; j < row + 3; j++) {
                board[i][j] = !board[i][j];
            }
        }
    }

    private static boolean isSameBoard(int col, int row) {
        for (int i = col; i < col + 2; i++) {
            for (int j = row; j < row + 2; j++) {
                if (!board[i][j]) return false;
            }
        }
        return true;
    }
}
