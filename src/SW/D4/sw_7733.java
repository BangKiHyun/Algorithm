package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_7733 {
    static int map[][];
    static boolean visit[][];
    static int X[] = {-1, 1, 0, 0};
    static int Y[] = {0, 0, -1, 1};
    static int n;
    static Queue<Node> q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int max_d = -1;
            int max = -1;    n = sc.nextInt();
            map = new int[n][n];
            visit = new boolean[n][n];
            q = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    if (max_d < map[i][j]) max_d = map[i][j];
                }
            }
            for (int i = 0; i < max_d; i++) {
                int cnt = bfs(i);
                init_visit();
                if (max < cnt) max = cnt;
            }
            System.out.println("#" + test_case + " " + max);
        }
        sc.close();
    }

    public static int bfs(int day) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) continue;
                if (map[i][j] <= day) continue;

                cnt++;
                q.add(new Node(i, j));
                visit[i][j] = true;

                while (!q.isEmpty()) {
                    Node N = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int nx = N.x + X[k];
                        int ny = N.y + Y[k];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] <= day) continue;
                        if (visit[nx][ny]) continue;

                        q.add(new Node(nx, ny));
                        visit[nx][ny] = true;
                    }
                }
            }
        }
        return cnt;
    }

    static void init_visit() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visit[i][j] = false;
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
