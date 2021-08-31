package programmers.impl;

public class pg_블록_게임_Re {
    private int[][] board;
    private int n;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 1, 1, 1},
                {0, 0, 0, 1, 0},
                {3, 0, 0, 3, 0},
                {3, 2, 2, 2, 0},
                {3, 3, 0, 0, 0}};
        final pg_블록_게임_Re task = new pg_블록_게임_Re();
        System.out.println(task.solution(board));
    }

    public int solution(int[][] board) {
        int answer = 0;
        this.board = board;
        n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isA(i, j)) {
                    if (board[i][j] != 0 && canRemove(i, j + 1, i, j + 2, board[i][j])) {
                        remove(i, j, i + 1, j, i + 1, j + 1, i + 1, j + 2);
                        j = -1;
                        answer++;
                    }
                } else if (isB(i, j)) {
                    if (board[i][j] != 0 && canRemove(i, j - 1, i + 1, j - 1, board[i][j])) {
                        remove(i, j, i + 1, j, i + 2, j, i + 2, j - 1);
                        j = -1;
                        answer++;
                    }
                } else if (isC(i, j)) {
                    if (board[i][j] != 0 && canRemove(i, j + 1, i + 1, j + 1, board[i][j])) {
                        remove(i, j, i + 1, j, i + 2, j, i + 2, j + 1);
                        j = -1;
                        answer++;
                    }
                } else if (isD(i, j)) {
                    if (board[i][j] != 0 && canRemove(i, j - 2, i, j - 1, board[i][j])) {
                        remove(i, j, i + 1, j, i + 1, j - 1, i + 1, j - 2);
                        j = -1;
                        answer++;
                    }
                } else if (isE(i, j)) {
                    if (board[i][j] != 0 && canRemove(i, j - 1, i, j + 1, board[i][j])) {
                        remove(i, j, i + 1, j, i + 1, j - 1, i + 1, j + 1);
                        j = -1;
                        answer++;
                    }
                }
            }
        }
        return answer;
    }

    private boolean isA(int x, int y) {
        int target = board[x][y];
        if (x + 1 >= n || y + 1 >= n || y + 2 >= n) return false;
        return (target == board[x + 1][y]) &&
                (target == board[x + 1][y + 1]) &&
                (target == board[x + 1][y + 2]);
    }

    private boolean isB(int x, int y) {
        int target = board[x][y];
        if (x + 1 >= n || x + 2 >= n || y - 1 < 0) return false;
        return (target == board[x + 1][y]) &&
                (target == board[x + 2][y]) &&
                (target == board[x + 2][y - 1]);
    }

    private boolean isC(int x, int y) {
        int target = board[x][y];
        if (x + 1 >= n || x + 2 >= n || y + 1 >= n) return false;
        return (target == board[x + 1][y]) &&
                (target == board[x + 2][y]) &&
                (target == board[x + 2][y + 1]);
    }

    private boolean isD(int x, int y) {
        int target = board[x][y];
        if (x + 1 >= n || y - 1 < 0 || y - 2 < 0) return false;
        return (target == board[x + 1][y]) &&
                (target == board[x + 1][y - 1]) &&
                (target == board[x + 1][y - 2]);
    }

    private boolean isE(int x, int y) {
        int target = board[x][y];
        if (x + 1 >= n || y - 1 < 0 || y + 1 >= n) return false;
        return (target == board[x + 1][y]) &&
                (target == board[x + 1][y - 1]) &&
                (target == board[x + 1][y + 1]);
    }

    private boolean canRemove(int x1, int y1, int x2, int y2, int target) {
        for (int x = 0; x <= x1; x++) {
            if (board[x][y1] == 0) continue;
            if (board[x][y1] != target) return false;
        }

        for (int x = 0; x <= x2; x++) {
            if (board[x][y2] == 0) continue;
            if (board[x][y2] != target) return false;
        }
        return true;
    }

    private void remove(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        board[x1][y1] = 0;
        board[x2][y2] = 0;
        board[x3][y3] = 0;
        board[x4][y4] = 0;
    }
}
