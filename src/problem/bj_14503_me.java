package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14503_me {
    static int map[][];
    static int N, M;
    static int cnt = 1;
    static boolean visit[][];
    static int[] X = {-1, 0, 1, 0}; //북 동 남 서
    static int[] Y = {0, 1, 0, -1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) {
        init();
        solution();
        System.out.println(cnt);
    }

    private static void solution() {
        while (!q.isEmpty()) {
            Node n = q.poll();
            visit[n.x][n.y] = true;
            boolean flag = false;
            int nd = nextD(n.d);
            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nd = nextD(nd);
                nx = n.x + X[nd];
                ny = n.y + Y[nd];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visit[nx][ny] && map[nx][ny] == 0) {
                    q.add(new Node(nx, ny, nd));
                    flag = true;
                    cnt++;
                    break;
                }
            }
            if (!flag) {
                nd = backD(n.d);
                nx = n.x + X[nd];
                ny = n.y + Y[nd];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                    q.add(new Node(nx, ny, n.d));
                }
            }
        }
    }

    private static int backD(int d) {
        if (d == 0) {
            return 2;
        } else if (d == 1) {
            return 3;
        } else if (d == 2) {
            return 0;
        } else
            return 1;
    }

    private static int nextD(int d) {
        if (d == 0) {
            return 3;
        } else if (d == 1) {
            return 0;
        } else if (d == 2) {
            return 1;
        } else
            return 2;
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visit = new boolean[N][M];
        q.add(new Node(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
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
