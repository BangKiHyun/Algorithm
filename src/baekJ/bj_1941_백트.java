package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class bj_1941_백트 {
    private static final int LENGTH = 5;
    private static final int MAX = 7;

    private static int ans = 0;
    private static char[][] map = new char[LENGTH][LENGTH];
    private static boolean[] visit = new boolean[LENGTH * LENGTH];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < LENGTH; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }
        backtracking(0);

        System.out.println(ans);
    }

    private static void backtracking(int depth) {
        if (depth == MAX) {
            if (isSameLine()) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < LENGTH * LENGTH; i++) {
            if (!visit[i]) {
                visit[i] = true;
                backtracking(depth + 1);
                visit[i] = false;
            }
        }
    }

    private static boolean isSameLine() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Node> q = new LinkedList<>();

        boolean flag = false;
        for (int i = 0; i < LENGTH * LENGTH; i++) {
            int x = i / 5;
            int y = i % 5;
            if (visit[i]) {
                q.add(new Node(x, y));
                flag = true;
                break;
            }
            if (flag) break;
        }

        int SOM = 0;
        int cnt = 0;
        boolean[] copy = Arrays.copyOf(visit, 25);
        while (!q.isEmpty()) {
            cnt++;
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny, copy)) {
                    if (map[nx][ny] == 'S') SOM++;
                    q.add(new Node(nx, ny));
                    copy[nx * 5 + ny] = false;
                }
            }
        }
        return cnt == 7 && SOM >= 4;
    }

    private static boolean isRange(int x, int y, boolean copy[]) {
        return x >= 0 && y >= 0 && x < LENGTH && y < LENGTH && copy[x * 5 + y];
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
