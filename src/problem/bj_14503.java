package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14503 {
    static int MAX = 50;
    static int X[] = {-1, 0, 1, 0};
    static int Y[] = {0, 1, 0, -1};
    static int map[][] = new int[MAX][MAX];
    static boolean visit[][] = new boolean[MAX][MAX];
    static Queue<Node> q = new LinkedList<>();
    static int r, c, cnt = 1;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();
        int d = sc.nextInt();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        q.add(new Node(x, y, d));
        visit[x][y] = true;
        bfs();
        System.out.println(cnt);

        sc.close();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node n = q.poll();

            int nextD = n.d;
            boolean flag = false;
            int nx, ny;

            for (int i = 0; i < 4; i++) {
                nextD = turnD(nextD);
                nx = n.x + X[nextD];
                ny = n.y + Y[nextD];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && visit[nx][ny] == false) {
                    if (map[nx][ny] == 0) {
                        q.add(new Node(nx, ny, nextD));
                        visit[nx][ny] = true;
                        cnt++;
                        flag = true;
                        break;
                    }
                }
            }

            if (flag == false) {
                nextD = backD(n.d);
                nx = n.x + X[nextD];
                ny = n.y + Y[nextD];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c ) {
                    if (map[nx][ny] == 0) {
                        q.add(new Node(nx, ny, n.d));
                    }
                }
            }
        }
    }

    static int turnD(int d) {
        if (d == 0) {
            return 3;
        } else if (d == 1) {
            return 0;
        } else if (d == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    static int backD(int d) {
        if (d == 0) {
            return 2;
        } else if (d == 1) {
            return 3;
        } else if (d == 2) {
            return 0;
        } else {
            return 1;
        }
    }

    static class Node {
        int x, y, d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
