package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_7699 {
    static String map[][];
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};
    static int max;
    static String compare[];
    static boolean visit[][];
    static int idx;
    static Queue<Node> q = new LinkedList<>();
    static int x, y;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            x = sc.nextInt();
            y = sc.nextInt();
            map = new String[x][y];
            for (int i = 0; i < x; i++) {
                String s = sc.next();
                for (int j = 0; j < y; j++) {
                    map[i][j] = s.substring(j, j + 1);
                }
            }
            max = 0;
            idx = 0;
            compare = new String[x * y];
            visit = new boolean[x][y];
            compare[idx] = map[0][0];
            visit[0][0] = true;
            q.add(new Node(0, 0));
            int cnt = bfs();
            System.out.println("#" + test_case + " " + cnt);
        }
        sc.close();
    }

    public static int bfs() {
        int cnt = 1;
        while (!q.isEmpty()) {
            Node n = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + X[i];
                int ny = n.y + Y[i];

                if (nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if (visit[nx][ny]) continue;

                if (Compare(map[nx][ny])) {
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                    idx++;
                    compare[idx] = map[nx][ny];
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static boolean Compare(String s) {
        for (int i = 0; i < compare.length; i++) {
            if (s.equals(compare[i])) {
                return false;
            }
        }
        return true;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
