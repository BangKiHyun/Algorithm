package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_16234 {
    static int n;
    static int map[][];
    static int L, R;
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};
    static boolean visit[][];
    static int ans = 0;
    static int cnt, sum;
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) {
        init();
        solution();
        System.out.println(ans);
    }

    private static void solution() {
        while (true) {
            cnt = 1;
            sum = 0;
            int flag = 0;
            for (int i = 0; i < n * n; i++) {
                int x = i / n;
                int y = i % n;
                if (!visit[x][y]) {
                    sum = map[x][y];
                    q.add(new Node(x, y));
                    move(x, y);
                    if (cnt != 1) {
                        movePeople(cnt);
                        flag++;
                    }
                    cnt = 1;
                    sum = 0;
                    q.clear();
                }
            }
            if (flag != 0) {
                ans++;
                visit = new boolean[n][n];
            } else return;
        }
    }

    private static void movePeople(int cnt) {
        int num = sum / cnt;
        for (Node n : q) {
            map[n.x][n.y] = num;
        }
    }

    private static void move(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + X[i];
            int ny = y + Y[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {
                int num = Math.abs(map[x][y] - map[nx][ny]);
                if (num >= L && num <= R) {
                    visit[x][y] = true;
                    visit[nx][ny] = true;
                    cnt++;
                    sum += map[nx][ny];
                    q.add(new Node(nx, ny));
                    move(nx, ny);
                }
            }
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[n][n];
        visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
    }

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


