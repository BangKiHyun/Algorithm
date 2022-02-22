package programmers.kakao.dfs;

public class pg_92345_사라지는_발판 {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1,};
    private static final int EMPTY = 0;

    private static int maxRow;
    private static int maxCol;

    private static int maxRound = 0;

    public static void main(String[] args) {
        int[][] board = {{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};
        final pg_92345_사라지는_발판 task = new pg_92345_사라지는_발판();
        System.out.println(task.solution(board, aloc, bloc));
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        maxRow = board[0].length;
        maxCol = board.length;
        goDFS(board, new Pos(aloc[0], aloc[1]), new Pos(bloc[0], bloc[1]), 0);
        return maxRound;
    }

    private void goDFS(int[][] board, Pos winner, Pos loser, int curRound) {
        if (winner.canNotMove(board) || loser.canNotMove(board)) {
            if (loserTurnToMove(curRound)) {
                maxRound = Math.max(maxRound, curRound);
            }
            return;
        }

        if (loserTurnToMove(curRound)) {
            for (int dir = 0; dir < 4; dir++) {
                int nx = loser.x + DX[dir];
                int ny = loser.y + DY[dir];

                if (!isRange(nx, ny) || board[nx][ny] == EMPTY) continue;

                board[nx][ny] = 0;
                goDFS(board, new Pos(winner.x, winner.y), new Pos(nx, ny), curRound + 1);
                board[nx][ny] = 1;
            }
        } else {
            for (int dir = 0; dir < 4; dir++) {
                int nx = winner.x + DX[dir];
                int ny = winner.y + DY[dir];

                if (!isRange(nx, ny) || board[nx][ny] == EMPTY) continue;

                board[nx][ny] = 0;
                goDFS(board, new Pos(nx, ny), new Pos(loser.x, loser.y), curRound + 1);
                board[nx][ny] = 1;
            }
        }
    }

    private boolean isRange(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < maxRow && ny < maxCol;
    }

    private boolean loserTurnToMove(int curRound) {
        return curRound % 2 != 0;
    }

    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean canNotMove(int[][] board) {
            return !canMove(board);
        }

        private boolean canMove(int[][] board) {
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + DX[dir];
                int ny = y + DY[dir];
                if (isRange(nx, ny) && board[nx][ny] != EMPTY) {
                    return true;
                }
            }
            return false;
        }

        private boolean isRange(int nx, int ny) {
            return nx >= 0 && ny >= 0 && nx < maxRow && ny < maxCol;
        }
    }
}
