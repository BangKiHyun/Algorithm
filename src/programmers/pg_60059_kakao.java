package programmers;

public class pg_60059_kakao {
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        System.out.println(solution(key, lock));
    }

    private static boolean solution(int[][] key, int[][] lock) {
        int n = key.length;
        int m = lock.length;
        int len = n * 2 + m - 2;
        int[][] mergeArr = new int[len][len];
        merge(mergeArr, lock, n - 1, n + m - 1);

        for (int turnCnt = 0; turnCnt < 4; turnCnt++) {
            for (int i = 0; i <= len - n; i++) {
                for (int j = 0; j <= len - n; j++) {
                    if (isUnlock(i, j, m - 1, mergeArr, key)) {
                        return true;
                    }
                }
            }
            //print(key);
            key = turnRight(key);
        }

        return false;
    }

    private static void print(final int[][] key) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                System.out.print(key[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void merge(int[][] mergeArr, final int[][] lock, final int start, final int end) {
        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                mergeArr[i][j] = lock[i - start][j - start];
            }
        }
    }

    private static boolean isUnlock(int curX, int curY, final int start, final int[][] mergeArr, final int[][] key) {
        int end = start * 2 + 1;

        int tempX = curX;
        int tempY = curY;
        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                if (isRange(curX, curY, start, end)) {
                    if (mergeArr[curX][curY] + key[curX - tempX][curY - tempY] != 1) {
                        return false;
                    }
                } else {
                    if (mergeArr[i][j] != 1) {
                        return false;
                    }
                }
                curY++;
            }
            curX++;
            curY = tempY;
        }

        for (int i = curX; i < curX + key.length; i++) {
            for (int j = curY; j < curY + key.length; j++) {
                if (isRange(i, j, start - 1, start * 2 - 1)) {
                    if (mergeArr[i][j] + key[i - curX][j - curY] != 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isRange(final int i, final int j, final int min, final int max) {
        return i >= min && j >= min && i < max && j < max;
    }

    private static int[][] turnRight(int[][] key) {
        int length = key.length;
        int[][] turnKey = new int[length][length];

        int maxIdx = length - 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                turnKey[j][maxIdx] = key[i][j];
            }
            maxIdx--;
        }

        return turnKey;
    }
}
