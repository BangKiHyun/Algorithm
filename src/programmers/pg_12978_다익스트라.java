package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_12978_다익스트라 {
    private static final int INF = Integer.MAX_VALUE;

    private static List<Node>[] list;
    private static int[] dist;

    public static void main(String[] args) {
        int n = 5;
        int[][] road = {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}};
        int k = 3;
        System.out.println(solution(n, road, k));
    }

    private static int solution(int N, int[][] road, int K) {
        list = new ArrayList[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            dist[i] = INF;
        }


        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int dist = road[i][2];

            list[a].add(new Node(b, dist));
            list[b].add(new Node(a, dist));
        }

        findShortestPath();
        return getAnswer(N, K);
    }

    private static void findShortestPath() {
        Queue<Node> prq = new PriorityQueue<>();
        prq.add(new Node(1, dist[1] = 0));

        while (!prq.isEmpty()) {
            Node cur = prq.poll();

            for (Node next : list[cur.idx]) {
                if (dist[next.idx] > dist[cur.idx] + next.dist) {
                    dist[next.idx] = dist[cur.idx] + next.dist;
                    prq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }

    private static int getAnswer(int n, int k) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= k) ans++;
        }

        return ans;
    }

    private static class Node implements Comparable<Node> {
        private int idx;
        private int dist;

        public Node(final int idx, final int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(final Node o) {
            return this.dist - o.dist;
        }
    }
}
