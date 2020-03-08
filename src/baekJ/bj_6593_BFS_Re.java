package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_6593_BFS_Re {
    private static int[] dx;
    private static int[] dy;
    private static int[][] building;
    private static Queue<Dot> q;
    private static boolean[][] visit;

    private static final String EXIT = "E";
    private static final String PATH = ".";
    private static final String BLOCK = "#";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int L, R, C;
        while (true) {
            String s = br.readLine();
            if (s.equals("")) continue;

            String[] init = s.split(" ");
            L = Integer.parseInt(init[0]);
            R = Integer.parseInt(init[1]);
            C = Integer.parseInt(init[2]);

            if (isEndMessage(L, R, C)) return;

            dx = new int[]{-1, 1, 0, 0, R + 1, -(R + 1)};
            dy = new int[]{0, 0, -1, 1, 0, 0};
            R = R * L + L - 1;
            building = new int[R][C];
            visit = new boolean[R][C];
            q = new LinkedList<>();

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                if (line.equals("")) {
                    Arrays.fill(building[i], 1);
                    continue;
                }
                String[] split = line.split("");
                for (int j = 0; j < C; j++) {
                    changeBuilding(i, j, split[j]);
                }
            }

            int ans = findPath(R, C);

            if (ans == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + ans + " minute(s).");
            }
        }
    }

    private static boolean isEndMessage(int L, int R, int C) {
        return L == 0 && R == 0 && C == 0;
    }

    private static void changeBuilding(int x, int y, String s) {
        switch (s) {
            case PATH:
                building[x][y] = 0;
                return;
            case BLOCK:
                building[x][y] = 1;
                return;
            case EXIT:
                building[x][y] = 2;
                return;
            default:
                q.add(new Dot(x, y, 0));
                visit[x][y] = true;
                return;
        }
    }

    private static int findPath(int max_x, int max_y) {
        int ans = -1;

        while (!q.isEmpty()) {
            Dot now = q.poll();
            if (building[now.x][now.y] == 2) {
                ans = now.cnt;
                break;
            }

            for (int i = 0; i < 6; i++) {
                int next_x = now.x + dx[i];
                int next_y = now.y + dy[i];

                if (isRange(next_x, next_y, max_x, max_y)) {
                    if (!isBlock(building[next_x][next_y])) {
                        q.add(new Dot(next_x, next_y, now.cnt + 1));
                        visit[next_x][next_y] = true;
                    }
                }
            }
        }
        return ans;
    }

    private static boolean isRange(int nx, int ny, int ran_x, int ran_y) {
        return nx >= 0 && ny >= 0 && nx < ran_x && ny < ran_y && !visit[nx][ny];
    }

    private static boolean isBlock(int num) {
        return num == 1;
    }

    private static class Dot {
        private int x;
        private int y;
        private int cnt;

        public Dot(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
