package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2178_BFS {
    private static int X[] = {-1, 1, 0, 0};
    private static int Y[] = {0, 0, -1, 1};
    private static Queue<Node> q = new LinkedList<>();
    private static int min = 100001;
    private static boolean visit[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String map[][] = new String[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().split("");
        }
        q.add(new Node(0, 0, 1));
        visit[0][0] = true;
        bfs(n, m, map);
        System.out.println(min);
    }

    private static void bfs(int n, int m, String[][] map) {
        while (!q.isEmpty()) {
            Node nd = q.poll();

            if (nd.x == n - 1 && nd.y == m - 1) {
                if (nd.cnt < min) {
                    min = nd.cnt;
                }
                visit[nd.x][nd.y] = false;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + X[i];
                int ny = nd.y + Y[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && map[nx][ny].equals("1")) {
                    q.add(new Node(nx, ny, nd.cnt + 1));
                    visit[nx][ny] = true;
                }
            }
        }
    }

    private static class Node {
        int x, y, cnt;

        private Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
