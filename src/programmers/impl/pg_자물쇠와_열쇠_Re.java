package programmers.impl;

public class pg_자물쇠와_열쇠_Re {
    private int[][] key;
    private int[][] lock;
    private int[][] board;
    private int lockStartIdxOfBoard;
    private int lockEndIdxOfBoard;
    private int lockCount;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}};
        int[][] lock = {{1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}};
        final pg_자물쇠와_열쇠_Re task = new pg_자물쇠와_열쇠_Re();
        System.out.println(task.solution(key, lock));
    }

    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        int boardLength = (key.length * 2) + lock.length - 2;
        this.board = new int[boardLength][boardLength];
        this.lockStartIdxOfBoard = key.length - 1;
        this.lockEndIdxOfBoard = lockStartIdxOfBoard + lock.length;
        initLockCount();
        initBoard();
        for (int count = 0; count < 4; count++) {
            if (canSolved()) {
                return true;
            }
            if (count != 3) turnKeyToRight();
        }
        return false;
    }

    private void initLockCount() {
        for (int rawIdx = 0; rawIdx < lock.length; rawIdx++) {
            for (int colIdx = 0; colIdx < lock.length; colIdx++) {
                if (lock[rawIdx][colIdx] == 0) {
                    lockCount++;
                }
            }
        }
    }

    private boolean canSolved() {
        for (int rawIdx = 0; rawIdx <= board.length - key.length + 1; rawIdx++) {
            for (int colIdx = 0; colIdx <= board.length - key.length + 1; colIdx++) {
                if (canSolvedOf(rawIdx, colIdx)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canSolvedOf(int startRawIdx, int startColIdx) {
        int solvedCount = 0;
        for (int rawIdx = 0; rawIdx < key.length; rawIdx++) {
            for (int colIdx = 0; colIdx < key.length; colIdx++) {
                int boardRawIdx = rawIdx + startRawIdx;
                int boardColIdx = colIdx + startColIdx;
                if (!isRange(boardRawIdx, boardColIdx)) continue;
                if (board[boardRawIdx][boardColIdx] == 1 && key[rawIdx][colIdx] == 1) return false;
                if ((board[boardRawIdx][boardColIdx] == 0 && key[rawIdx][colIdx] == 1)) {
                    solvedCount++;
                }
            }
        }
        return lockCount == solvedCount;
    }

    private boolean isRange(int rawIdx, int colIdx) {
        return rawIdx >= lockStartIdxOfBoard && colIdx >= lockStartIdxOfBoard && rawIdx < lockEndIdxOfBoard && colIdx < lockEndIdxOfBoard;
    }

    private void turnKeyToRight() {
        int[][] newKey = new int[key.length][key.length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                newKey[i][j] = key[key.length - 1 - j][i];
            }
        }
        this.key = newKey;
    }

    private void initBoard() {
        for (int rawIdx = lockStartIdxOfBoard; rawIdx < lockEndIdxOfBoard; rawIdx++) {
            for (int colIdx = lockStartIdxOfBoard; colIdx < lockEndIdxOfBoard; colIdx++) {
                board[rawIdx][colIdx] = lock[rawIdx - lockStartIdxOfBoard][colIdx - lockStartIdxOfBoard];
            }
        }
    }
}
