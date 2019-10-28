package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2468_BFS {
    private static int[][] map;
    private static int n;
    private static int maxSafeArea = -1;
    private static int maxAmountOfWater = 0;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static boolean visit[][];
    private static Queue<Path> q = new LinkedList<>();

    public static void main(String[] args) {
        init();
        increasedWaterAmount(maxAmountOfWater);
        System.out.println(maxSafeArea);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                maxAmountOfWater = Math.max(maxAmountOfWater, map[i][j]);
            }
        }
    }

    private static void increasedWaterAmount(int max) {
        for (int i = 0; i <= max; i++) {
            findSafeArea(i);
            visit = new boolean[n][n];
        }
    }

    private static void findSafeArea(int amountOfWater) {
        changeMap(amountOfWater);
        maxSafeArea = Math.max(maxSafeArea, getSafeArea());
    }

    private static void changeMap(int amountOfWater) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= amountOfWater) {
                    visit[i][j] = true;
                }
            }
        }
    }

    private static int getSafeArea() {
        int numberOfSafeArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    q.add(new Path(i, j));
                    visit[i][j] = true;
                    visitCheck();
                    numberOfSafeArea++;
                }
            }
        }
        return numberOfSafeArea;
    }

    private static void visitCheck() {
        while (!q.isEmpty()) {
            Path now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    q.add(new Path(nx, ny));
                }
            }
        }
    }

    private static class Path {
        int x, y;

        Path(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
