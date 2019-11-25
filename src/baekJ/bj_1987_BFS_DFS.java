package baekJ;

import java.util.*;

public class bj_1987_BFS_DFS {
    private static String[][] map;
    private static boolean[][] visit;
    private static Queue<Node> q = new LinkedList<>();
    private static Set<String> set = new HashSet<>();
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int max = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        map = new String[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            map[i] = line.split("");
        }

        visit[0][0] = true;
        q.add(new Node(0, 0, 1));
        set.add(map[0][0]);

        goBFS(n, m);

        System.out.println(max);
    }

    private static void goBFS(int n, int m) {

        while (!q.isEmpty()) {
            Node now = q.poll();
            max = Math.max(max, now.cnt);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny] && !set.contains(map[nx][ny])) {
                    visit[nx][ny] = true;
                    set.add(map[nx][ny]);
                    q.add(new Node(nx, ny, now.cnt + 1));
                    goBFS(n, m);
                    visit[nx][ny] = false;
                    set.remove(map[nx][ny]);
                }
            }
        }
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
