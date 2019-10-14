package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2206_BFS {
    private static int map[][];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<Node> q = new LinkedList<>();
    private static boolean visit[][];
    private static int min = Integer.MAX_VALUE;
    private static boolean flag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            String[] row = line.split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }
        solution(n, m);

        for (int i = 0; i < n * m; i++) {
            int x = i / n;
            int y = i % m;
            if (map[x][y] == 1) {
                map[x][y] = 0;
                solution(n, m);
                map[x][y] = 1;
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void solution(int n, int m) {
        visit = new boolean[n][m];
        q.add(new Node(0, 0, 1));
        visit[0][0] = true;

        int path = findPath(n, m);
        if (flag) {
            min = Math.min(min, path);
            q.clear();
        }
    }

    private static int findPath(int n, int m) {
        flag = false;
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == n - 1 && now.y == m - 1) {
                flag = true;
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && map[nx][ny] == 0) {
                    q.add(new Node(nx, ny, now.cnt + 1));
                    visit[nx][ny] = true;
                }
            }
        }
        return -1;
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
