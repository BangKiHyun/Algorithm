package programmers.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class pg_카카오프렌즈_컬러링북 {
    private static final int EMPTY = 0;

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {
                {1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};

        for (int ans : solution(m, n, picture)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(int m, int n, int[][] picture) {
        // 원본 picture를 수정하면 코드 통과가 안됨
        int[][] copyPicture = new int[m][n];
        initCopyPicture(m, n, picture, copyPicture);

        int cntOfArea = 0;
        int maxSizeOfArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (copyPicture[i][j] != EMPTY) {
                    cntOfArea++;
                    maxSizeOfArea = Math.max(maxSizeOfArea, getAreaSize(copyPicture, i, j));
                }
            }
        }

        return new int[]{cntOfArea, maxSizeOfArea};
    }

    private static void initCopyPicture(int m, int n, int[][] picture, int[][] copyPicture) {
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                copyPicture[x][y] = picture[x][y];
            }
        }
    }

    private static int getAreaSize(int[][] picture, int startX, int startY) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, startY));

        int areaIdx = picture[startX][startY];
        int areaSize = 0;
        while (!q.isEmpty()) {
            final Node node = q.poll();
            int curX = node.x;
            int curY = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = DX[i] + curX;
                int ny = DY[i] + curY;

                if (isRange(nx, ny, picture.length, picture[0].length) && picture[nx][ny] == areaIdx) {
                    areaSize++;
                    q.offer(new Node(nx, ny));
                    picture[nx][ny] = EMPTY;
                }
            }
        }

        return areaSize;
    }

    private static boolean isRange(int x, int y, int maxX, int maxY) {
        return x >= 0 && y >= 0 && x < maxX && y < maxY;
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}