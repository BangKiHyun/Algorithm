package baekJ;

import java.util.*;

public class bj_11559_브루트포스 {
    private static int n = 12, m = 6;
    private static String[][] map = new String[n][m];
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, 1, -1, 0};
    private static int[][] deleteCnt = new int[n][m];
    private static int[][] tempCnt = new int[n][m];
    private static boolean visit[][] = new boolean[n][m];
    private static Queue<Block> q = new LinkedList<>();
    private static int ans = -1;

    public static void main(String[] args) {
        init();
        solution();
        System.out.println(ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            map[i] = line.split("");
        }
    }

    private static void solution() {
        findPuyo();
    }

    private static void findPuyo() {
        boolean check = true;

        while (check) {
            ans++;
            visit = new boolean[n][m];
            deleteCnt = new int[n][m];
            check = false;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < m; j++) {
                    if (!map[i][j].equals(".") && !visit[i][j]) {
                        //미리 복사
                        copyDeleteCnt(true);
                        visit[i][j] = true;
                        q.add(new Block(i, j));
                        tempCnt[i][j]++;
                        int cnt = findSamePufo(map[i][j], i);
                        if (cnt >= 4) {
                            check = true;
                            copyDeleteCnt(false);
                        }
                    }
                }
            }
            if (check) {
                changeMap();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static void copyDeleteCnt(boolean check) {
        if (check) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tempCnt[i][j] = deleteCnt[i][j];
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    deleteCnt[i][j] = tempCnt[i][j];
                }
            }
        }
    }

    private static int findSamePufo(String color, int r) {
        int cnt = 1;
        while (!q.isEmpty()) {
            Block now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny, color)) {
                    tempCnt[r][ny]++;
                    visit[nx][ny] = true;
                    q.add(new Block(nx, ny));
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isRange(int x, int y, String color) {
        if (x >= 0 && y >= 0 && x < n && y < m && !visit[x][y] && map[x][y].equals(color)) {
            return true;
        }
        return false;
    }

    private static void changeMap() {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (deleteCnt[i][j] != 0) {
                    deletePuyoPuyo(deleteCnt[i][j], i, j);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (deleteCnt[i][j] != 0) {
                    changePuyoPuyo(deleteCnt[i][j], i, j);
                }
            }
        }
    }

    private static void deletePuyoPuyo(int cnt, int r, int c) {
        while (cnt > 0 && r >= 0) {
            map[r][c] = ".";
            r--;
            cnt--;
        }
    }

    private static void changePuyoPuyo(int cnt, int r, int c) {
        while (r >= 0) {
            if (r - cnt >= 0) {
                int nr = r - cnt;
                map[r][c] = map[nr][c];
                r--;
            } else {
                map[r][c] = ".";
                r--;
            }
        }
    }

    private static class Block {
        int x, y;

        Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
