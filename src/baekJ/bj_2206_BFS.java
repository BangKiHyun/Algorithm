package baekJ;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2206_BFS {
    private static int map[][];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<Node> q = new LinkedList<>();
    private static boolean visit[][];
    private static int[][] copy;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        map = new int[n][m];
        visit = new boolean[n][m];
        copy = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        q.add(new Node(0, 0));
        visit[0][0] = true;
        int path = findPath(n, m);
        min = path == 0 ? min : path;

        solution(n, m, 0, 0);
    }

    private static void solution(int n, int m, int start, int depth) {

        if (depth == 1) {
            Copy(n, m);
            Arrays.fill(visit, false);
            q.add(new Node(0, 0));
            visit[0][0] = true;
            int path = findPath(n, m);
            if (path != 0) {
                min = Math.min(min, path);
                return;
            } else return;
        }

        for (int i = start; i < n * m; i++) {
            int x = i / n;
            int y = i % m;
            if (map[x][y] == 1) {
                map[x][y] = 0;
                solution(n, m, start + 1, depth + 1);
                map[x][y] = 1;
            }
        }
    }

    private static int findPath(int n, int m) {
        int cnt = 0;
        boolean flag = false;
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == n - 1 && now.y == m - 1) {
                flag = true;
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && copy[nx][ny] == 0) {
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
            cnt++;
        }
        return cnt;
    }

    private static void Copy(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
