package SW.D4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_8382_BFS {
    static Queue<Node> q;
    static int min;
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};
    static int map[][];
    static boolean visit[][];
    static int startX, startY, endX, endY;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            startX = sc.nextInt();
            startY = sc.nextInt();
            endX = sc.nextInt();
            endY = sc.nextInt();
            min = Integer.MAX_VALUE;
            visit = new boolean[200][200];
            q = new LinkedList<>();
            q.add(new Node(startX, startY, 0, 0));
            solution();
            q.clear();
            System.out.println(q.size());
            visit = new boolean[200][200];
            q.add(new Node(startX, startY, 1, 0));
            solution();

            System.out.println("#" + test_case + " " + min);
        }
    }

    static void solution() {
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.x == endX && n.y == endY) {
                min = Math.min(min, n.cnt);
                return;
            }
            int nx, ny;
            if (n.d == 0) {
                for (int i = 0; i < 2; i++) {
                    nx = n.x + X[i];
                    ny = n.y + Y[i];
                    if (nx >= 0 && ny >= 0 && nx < 200 && ny < 200 && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        q.add(new Node(nx, ny, 1, n.cnt + 1));
                    }
                }
            } else {
                for (int i = 2; i < 4; i++) {
                    nx = n.x + X[i];
                    ny = n.y + Y[i];
                    if (nx >= 0 && ny >= 0 && nx < 200 && ny < 200 && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        q.add(new Node(nx, ny, 0, n.cnt + 1));
                    }
                }
            }
        }
    }

    static class Node {
        int x, y, d, cnt;

        private Node(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }
}
