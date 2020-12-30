package programmers.impl;

public class pg_삼각_달팽이 {
    private static final int[] DX = {1, 0, -1};
    private static final int[] DY = {0, 1, -1};

    public static void main(String[] args) {
        int n = 5;
        for (int ans : solution(n)) {
            System.out.print(ans + ",");
        }
    }

    public static int[] solution(int n) {
        int[][] map = new int[n][n];
        int curX = 0;
        int curY = 0;

        int max = getMax(n);

        int number = 1;
        map[curX][curY] = number++;

        int curDir = 0;
        while (number <= max) {
            int nx = curX + DX[curDir];
            int ny = curY + DY[curDir];

            if (isRange(nx, ny, n)) {
                if (map[nx][ny] == 0) {
                    map[nx][ny] = number++;

                    curX = nx;
                    curY = ny;
                } else {
                    curDir++;
                }
            } else {
                curDir++;
            }

            if (curDir == 3) curDir = 0;
        }

        int[] ans = new int[max];
        convertTwoDimensionToOneDimension(map, ans);

        return ans;
    }

    private static int getMax(int n) {
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max += i;
        }

        return max;
    }

    private static boolean isRange(int x, int y, int maxSize) {
        return x >= 0 && y >= 0 && x < maxSize && y < maxSize;
    }

    private static void convertTwoDimensionToOneDimension(int[][] map, int[] ans) {
        int len = map.length;
        int idx = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map[i][j] != 0) {
                    ans[idx++] = map[i][j];
                }
            }
        }
    }
}
