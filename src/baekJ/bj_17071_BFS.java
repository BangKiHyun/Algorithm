package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_17071_BFS {
    private static final int INF = 500000;

    private static int dn[] = {1, -1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int ans = goBFS(n, k);

        System.out.println(ans);
    }

    private static int goBFS(int n, int k) {
        int res = -1;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, k, 0, 1));

        while (!q.isEmpty()) {
            Node pos = q.poll();
            if (pos.brother > INF) {
                return res;
            }
            if (pos.me == pos.brother) {
                return pos.cnt;
            }

            for (int i = 0; i < 3; i++) {
                int next_me = pos.me;
                if (i == 2) next_me *= dn[i];
                else next_me += dn[i];

                if (next_me >= 0 && next_me <= INF) {
                    q.offer(new Node(next_me, pos.brother + pos.plus, pos.cnt + 1, pos.plus + 1));
                }
            }
        }
        return res;
    }

    private static class Node {
        private int me;
        private int brother;
        private int cnt;
        private int plus;

        public Node(int me, int brother, int cnt, int plus) {
            this.me = me;
            this.brother = brother;
            this.cnt = cnt;
            this.plus = plus;
        }
    }
}
