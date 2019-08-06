package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1861 {
    static int map[][];
    static int X[] = {-1, 1, 0, 0};
    static int Y[] = {0, 0, -1, 1};
    static Queue<Node> q;
    static boolean visit[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            map = new int[n][n];
            q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int max = -1;
            int ans = 0;
            int temp_i = 0;
            int temp_j = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visit = new boolean[n][n];
                    q.add(new Node(i, j));
                    visit[i][j] = true;
                    int cnt = bfs(n, 1);
                    if (cnt == max) {
                        if (map[i][j] < map[temp_i][temp_j]) {
                            ans = map[i][j];
                            temp_i = i;
                            temp_j = j;
                        }
                    }
                    if (cnt > max) {
                        temp_i = i;
                        temp_j = j;
                        max = cnt;
                        ans = map[i][j];
                    }
                }
            }
            System.out.println("#" + test_case + " " + ans + " " + max);
        }
    }

    public static int bfs(int num, int cnt) {
        while (!q.isEmpty()) {
            Node n = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + X[i];
                int ny = n.y + Y[i];

                if (nx < 0 || ny < 0 || nx >= num || ny >= num) continue;
                if (visit[nx][ny]) continue;
                if (map[nx][ny] <= map[n.x][n.y]) continue;
                if (map[nx][ny] == map[n.x][n.y] + 1) {
                    q.offer(new Node(nx, ny));
                    visit[nx][ny] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
