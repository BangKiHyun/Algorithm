package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_17144_BFS {
    private static final int FRESH = -1;

    private static int r, c, t;
    private static int[][] map;
    private static int[][] copyMap;
    private static int[][] freshPos = new int[2][2];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        int idx = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == FRESH) {
                    freshPos[idx][0] = i;
                    freshPos[idx++][1] = j;
                }
            }
        }

        while (t != 0) {
            spreadAir();
            cleanAir();
            t--;
        }

        System.out.println(getAnswer());
    }

    private static void spreadAir() {
        copyMap = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                copyMap[i][j] += map[i][j];
                if (map[i][j] != FRESH && map[i][j] / 5 != 0) {
                    spread(i, j, map[i][j] / 5);
                }
            }
        }
        copySpread();
    }

    private static void spread(int x, int y, int cnt) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValue(nx, ny)) {
                copyMap[x][y] -= cnt;
                copyMap[nx][ny] += cnt;
            }
        }
    }

    private static boolean isValue(int x, int y) {
        return x >= 0 && y >= 0 && x < r && y < c && map[x][y] != FRESH;
    }

    private static void cleanAir() {
        copyMap = new int[r][c];
        for (int i = 0; i < 2; i++) {
            int nx = freshPos[i][0];
            int ny = freshPos[i][1] + 1;

            while (ny < c - 1) {
                copyMap[nx][ny + 1] = map[nx][ny++];
            }

            if (i == 0) {
                while (nx > 0) {
                    copyMap[nx - 1][ny] = map[nx--][ny];
                }
            } else {
                while (nx < r - 1) {
                    copyMap[nx + 1][ny] = map[nx++][ny];
                }
            }

            while (ny > 0) {
                copyMap[nx][ny - 1] = map[nx][ny--];
            }

            if (i == 0) {
                while (nx < freshPos[i][0] - 1) {
                    copyMap[nx + 1][ny] = map[nx++][ny];
                }
            } else {
                while (nx > freshPos[i][0] + 1) {
                    copyMap[nx - 1][ny] = map[nx--][ny];
                }
            }
        }

        copyClean();
    }

    private static void copySpread() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    private static void copyClean() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (isChange(i, j))
                    map[i][j] = copyMap[i][j];
            }
        }

        map[freshPos[0][0]][freshPos[0][1]] = FRESH;
        map[freshPos[1][0]][freshPos[1][1]] = FRESH;
    }

    private static boolean isChange(int x, int y) {
        return x == 0 || x == r - 1 || y == 0 || y == c - 1 || x == freshPos[0][0] || x == freshPos[1][0];
    }

    private static int getAnswer() {
        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != FRESH) answer += map[i][j];
            }
        }
        return answer;
    }
}
