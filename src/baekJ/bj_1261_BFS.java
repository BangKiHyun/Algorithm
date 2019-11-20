package baekJ;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class bj_1261_BFS {
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visit;
    private static int dx[] = {0, 1, 0, -1};
    private static int dy[] = {1, 0, -1, 0};
    private static Deque<Node> q = new LinkedList<>();
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();
        visit[0][0] = true;
        q.addLast(new Node(0, 0, 0));
        int ans = goBFS();
        System.out.println(ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[m][n];
        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String line = sc.next();
            String temp[] = line.split("");

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
    }

    private static int goBFS() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == m - 1 && now.y == n - 1) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isValid(nx, ny)) {
                    visit[nx][ny] = true;
                    if (isWall(nx, ny)) {
                        q.addLast(new Node(nx, ny, now.cnt + 1));
                    } else {
                        q.addFirst(new Node(nx, ny, now.cnt));
                    }
                }
            }
        }
        return min;
    }

    private static boolean isValid(int x, int y) {
        if (x >= 0 && y >= 0 && x < m && y < n && !visit[x][y]) {
            return true;
        }
        return false;
    }

    private static boolean isWall(int x, int y) {
        if (map[x][y] == 1) {
            return true;
        }
        return false;
    }

    private static class Node {
        int x, y, cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
