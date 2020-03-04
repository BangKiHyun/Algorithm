package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//스도쿠 문제
public class bj_2580_DFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Dot> list = new ArrayList<>();
        int[][] board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    list.add(new Dot(i, j));
                }
            }
        }

        searchNumber(list, board, 0);
    }

    private static void searchNumber(ArrayList<Dot> list, int[][] board, int depth) {
        if (depth == list.size()) {
            printBoard(board);
            System.exit(0);
        }

        for (int i = 1; i <= 9; i++) {
            Dot now = list.get(depth);
            if (isValue(now.x, now.y, i, board)) {
                board[now.x][now.y] = i;
                searchNumber(list, board, depth + 1);
                board[now.x][now.y] = 0;
            }
        }
    }

    private static boolean isValue(int x, int y, int input, int[][] board) {
        return checkRow(x, input, board) && checkCol(y, input, board) && checkRectangle(x, y, input, board);
    }

    private static boolean checkRow(int x, int input, int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == input) return false;
        }
        return true;
    }

    private static boolean checkCol(int y, int input, int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == input) return false;
        }
        return true;
    }

    private static boolean checkRectangle(int x, int y, int input, int[][] board) {
        int start_x = (x / 3) * 3;
        int start_y = (y / 3) * 3;

        for (int i = start_x; i < start_x + 3; i++) {
            for (int j = start_y; j < start_y + 3; j++) {
                if (board[i][j] == input) return false;
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class Dot {
        private int x;
        private int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
