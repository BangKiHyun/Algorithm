package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1249 {
    static int map[][];
    static Queue q;
    static int X[] = {-1, 1, 0, 0};
    static int Y[] = {0, 0, -1, 1};
    static int d[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            map = new int[n][n];
            q = new LinkedList();
            d = new int[n][n];
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(s.substring(j, j + 1));
                    d[i][j] = map[i][j];
                }
            }
            q.add(new Node(0, 0));
            int ans = floyd(n);
            System.out.println("#" + test_case + " " + ans);
        }
    }

    public static int floyd(int num) {
        while (!q.isEmpty()) {
            Node n = (Node) q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = n.x + X[k];
                int ny = n.y + Y[k];
                if (nx < 0 || ny < 0 || nx >= num || ny >= num) continue;

                if (d[nx][ny] > map[nx][ny] + d[n.x][n.y]) {
                    d[nx][ny] = d[n.x][n.y] + map[nx][ny];
                    q.add(new Node(nx, ny));
                }
            }
        }
        return d[num - 1][num - 1];
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
