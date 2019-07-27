package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1227 {
    static int MAX = 100;
    static Queue q;
    static int map[][];
    static boolean visit[][];
    static int X[] = {-1, 1, 0, 0};
    static int Y[] = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            int T = sc.nextInt();
            map = new int[MAX][MAX];
            visit = new boolean[MAX][MAX];
            q = new LinkedList<>();
            for (int i = 0; i < MAX; i++) {
                String s = sc.next();
                for (int j = 0; j < MAX; j++) {
                    map[i][j] = Integer.parseInt(s.substring(j, j + 1));
                }
            }
            q.add(new node(1, 1));
            visit[1][1] = true;
            int ans = bfs();

            System.out.println("#" + test_case + " " + ans);
        }
    }

    public static int bfs() {
        int ans = 0;
        while (!q.isEmpty()) {
            node n = (node) q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + X[i];
                int ny = n.y + Y[i];

                if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX) continue;
                if (visit[nx][ny]) continue;

                if (map[nx][ny] == 3) {
                    ans = 1;
                    return ans;
                }

                if (map[nx][ny] == 0) {
                    q.add(new node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
        return ans;
    }

    static class node {
        int x, y;

        public node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
}
