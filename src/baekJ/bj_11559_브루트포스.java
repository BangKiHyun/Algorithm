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
        boolean check = false;

        while (!check) {
            ans++;
            visit = new boolean[n][m];
            deleteCnt = new int[n][m];
            for (int i = n - 1; i >= 0; i--) {
                check = false;
                for (int j = 0; j < m; j++) {
                    if (!map[i][j].equals(".") && !visit[i][j]) {
                        //미리 복사
                        copyDeleteCnt(true);
                        visit[i][j] = true;
                        q.add(new Block(i, j));
                        int cnt = findSamePufo(map[i][j], i);
                        if (cnt >= 4) {
                            check = true;
                        } else {
                            copyDeleteCnt(false);
                        }
                    }
                }
            }
            if (check) {
                changeMap();
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
        int cnt = 0;
        while (!q.isEmpty()) {
            Block now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny, color)) {
                    changeDeleteCnt(i, ny, r);
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

    private static void changeDeleteCnt(int i, int y, int r) {
        switch (i) {
            case 0:
            case 3:
                deleteCnt[r][y]++;
                break;
            case 1:
                deleteCnt[r][y + 1]++;
                break;
            case 2:
                deleteCnt[r][y - 1]++;
                break;
        }
    }

    private static void changeMap() {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (deleteCnt[i][j] != 0) {

                }
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
