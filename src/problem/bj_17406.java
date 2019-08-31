package problem;

import java.util.*;

public class bj_17406 {
    static int n, m, k;
    static int map[][];
    static int copy[][];
    static int copy2[][];
    static List<Node> q = new LinkedList<>();
    static List<Node> info = new ArrayList<>();
    static int min = 987654321;
    static boolean visit[];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        map = new int[n + 1][m + 1];
        copy = new int[n + 1][m + 1];
        visit = new boolean[k];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < k; i++) {
            q.add(new Node(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        solution(0);

        System.out.println(min);
    }

    static void solution(int len) {
        if (len == k) {
            copyMap();
            for (int i = 0; i < k; i++) {
                int r, c, s;
                r = info.get(i).r;
                c = info.get(i).c;
                s = info.get(i).s;
                for (int j = s; j > 0; j--) {
                    rotate(r, c, j);
                }
            }
            minNum();
            return;
        }
        for (int i = 0; i < k; i++) {
            if (!visit[i]) {
                visit[i] = true;
                info.add(q.get(i));
                solution(len + 1);
                visit[i] = false;
                info.remove(q.get(i));
            }
        }
    }

    static void minNum() {
        int ans;
        for (int i = 1; i <= n; i++) {
            ans = 0;
            for (int j = 1; j <= m; j++) {
                ans += copy[i][j];
            }
            min = Math.min(min, ans);
        }
        return;
    }

    static void rotate(int r, int c, int s) {
        copyMap2();

        for (int i = c - s + 1; i <= c + s; i++) copy[r - s][i] = copy2[r - s][i - 1];
        for (int i = r - s + 1; i <= r + s; i++) copy[i][c + s] = copy2[i - 1][c + s];
        for (int i = c + s - 1; i >= c - s; i--) copy[r + s][i] = copy2[r + s][i + 1];
        for (int i = r + s - 1; i >= r - s; i--) copy[i][c - s] = copy2[i + 1][c - s];
    }

    static void copyMap() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    static void copyMap2() {
        copy2 = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                copy2[i][j] = copy[i][j];
            }
        }
    }

    static class Node {
        int r, c, s;

        public Node(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
