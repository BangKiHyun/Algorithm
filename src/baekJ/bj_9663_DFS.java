package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
//
//N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
public class bj_9663_DFS {
    private static int n;
    private static int[][] board;
    private static final int EXIST = 1;
    private static final int EMPTY = 0;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            board[0][i] = EXIST;
            goBrute(1, 1);
            board[0][i] = EMPTY;
        }

        System.out.println(ans);
    }

    private static void goBrute(int x, int depth) {
        if (depth == n) {
            ans++;
            return;
        }

        for (int y = 0; y < n; y++) {
            if (board[x][y] == EMPTY && putQueen(x, y)) {
                board[x][y] = EXIST;
                goBrute(x + 1, depth + 1);
                board[x][y] = EMPTY;
            }
        }
    }

    private static boolean putQueen(int x, int y) {
        return checkVertical(x) && checkHorizontal(y) && checkDiagonal(x, y);
    }

    private static boolean checkVertical(int x) {
        for (int y = 0; y < n; y++) {
            if (board[x][y] == EXIST) return false;
        }
        return true;
    }

    private static boolean checkHorizontal(int y) {
        for (int x = 0; x < n; x++) {
            if (board[x][y] == EXIST) return false;
        }
        return true;
    }

    private static boolean checkDiagonal(int x, int y) {
        int temp_x = x;
        int temp_y = y;

        //좌상
        for (int i = x; i >= 0 && temp_y >= 0; i--) {
            if (board[i][temp_y] == EXIST) return false;
            temp_y--;
        }

        temp_y = y;
        //우상
        for (int i = x; i >= 0 && temp_y < n; i--) {
            if (board[i][temp_y] == EXIST) return false;
            temp_y++;
        }

        //좌하
        for (int j = y; j >= 0 && temp_x < n; j--) {
            if (board[temp_x][j] == EXIST) return false;
            temp_x++;
        }

        //우하
        for (int j = y; j < n && temp_x < n; j++) {
            if (board[temp_x][j] == EXIST) return false;
            temp_x++;
        }

        return true;
    }
}