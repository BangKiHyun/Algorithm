package programmers.impl;

public class pg_자물쇠와_열쇠 {
    private static int lockCnt;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}};
        int[][] lock = {{1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}};
        System.out.println(solution(key, lock));
    }

    public static boolean solution(int[][] key, int[][] lock) {
        int lenOfKey = key.length;
        int lenOfLock = lock.length;
        int lenOfBoard = (lenOfKey * 2) + lenOfLock - 2;
        lockCnt = getLockCnt(lock);
        if (lockCnt == 0) return true;

        int[][] board = new int[lenOfBoard][lenOfBoard];
        mergeBoard(board, lock, lenOfKey);

        int minOfRange = lenOfBoard - (lenOfKey + lenOfLock) + 1;
        int maxOfRange = lenOfBoard - lenOfKey;
        for (int turnCnt = 0; turnCnt < 4; turnCnt++) {
            for (int i = 0; i <= lenOfBoard - lenOfKey; i++) {
                for (int j = 0; j <= lenOfBoard - lenOfKey; j++) {
                    if (isUnLock(i, j, board, key, minOfRange, maxOfRange)) {
                        return true;
                    }
                }
            }
            if (turnCnt < 3) {
                key = rotateRight(key);
            }
        }
        return false;
    }

    private static int getLockCnt(int[][] lock) {
        int lockCnt = 0;
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) {
                    lockCnt++;
                }
            }
        }
        return lockCnt;
    }

    private static void mergeBoard(int[][] board, int[][] lock, int lenOfKey) {
        int lenOfLock = lock.length;
        int lockI = 0;
        for (int i = lenOfKey - 1; i < lenOfKey + lenOfLock - 1; i++) {
            int lockJ = 0;
            for (int j = lenOfKey - 1; j < lenOfKey + lenOfLock - 1; j++) {
                board[i][j] = lock[lockI][lockJ++];
            }
            lockI++;
        }
    }

    private static boolean isUnLock(int startX, int startY, int[][] board, int[][] key, int minOfRange, int maxOfRange) {
        int lenOfKey = key.length;
        int unLockCnt = 0;

        for (int i = startX; i < startX + lenOfKey; i++) {
            for (int j = startY; j < startY + lenOfKey; j++) {
                if (isRange(i, j, minOfRange, maxOfRange)) {
                    if (board[i][j] == 1 && key[i - lenOfKey + 1][j - lenOfKey + 1] == 1) return false;
                    if (board[i][j] == 0) {
                        if (board[i][j] + key[i - lenOfKey + 1][j - lenOfKey + 1] != 1) return false;
                        else unLockCnt++;
                    }
                }
            }
        }
        return lockCnt == unLockCnt;
    }

    private static boolean isRange(int startX, int startY, int minOfRange, int maxOfRange) {
        return startX >= minOfRange && startY >= minOfRange && startX <= maxOfRange && startY <= maxOfRange;
    }

    private static int[][] rotateRight(int[][] map) {
        int length = map.length;
        int[][] rotateMap = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                rotateMap[i][j] = map[length - 1 - j][i];
            }
        }
        return rotateMap;
    }
}
