package baekJ;

import java.util.*;

public class bj_2583_브루트포스 {
    private static int n, m, k;
    private static int map[][];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        map = new int[101][101];

        for (int i = 0; i < k; i++) {
            int x1 = sc.nextInt(); //왼쪽아래
            int y1 = sc.nextInt();//왼쪽아래 n-y1;
            int x2 = sc.nextInt(); //오른쪽위 n-x2;
            int y2 = sc.nextInt(); //오른쪽위

            for (int j = x2; j <= y1; j++) {
                for (int k = x1; k <= x2; k++) {
                    if (map[j][k] != 1) map[j][k] = 1;
                }
            }
        }

        int ans = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 1) {
                    map[i][j] = 1;
                    q.add(new Node(i, j));
                    list.add(goBFS());
                    ans++;
                }
            }
        }

        System.out.println(ans);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    private static int goBFS() {
        int cnt = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny)) {
                    map[nx][ny] = 1;
                    q.add(new Node(nx, ny));
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isRange(int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < m && map[x][y] == 0) {
            return true;
        }
        return false;
    }

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
