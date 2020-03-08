package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2211_다익 {
    private static int[] distance;
    private static int[][] link;
    private static int[] trace;

    private static final int INF = 99999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()) + 1;
        int m = Integer.parseInt(st.nextToken());

        distance = new int[n];
        link = new int[n][n];
        trace = new int[n];

        for (int i = 1; i < n; i++) {
            distance[i] = INF;
            Arrays.fill(link[i], -1);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            link[from][to] = link[to][from] = dis;
        }

        findShortestPath(1, n);

        System.out.println(n - 2);
        for (int i = 2; i < n; i++) {
            System.out.println(trace[i] + " " + i);
        }
    }

    private static void findShortestPath(int start, int n) {
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, distance[start] = 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int next = 1; next < n; next++) {
                if (link[now.pos][next] != -1) {
                    int via_dist = distance[now.pos] + link[now.pos][next];
                    if (distance[next] > via_dist) {
                        distance[next] = via_dist;
                        q.add(new Node(next, distance[next]));
                        trace[next] = now.pos;
                    }
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private int pos;
        private int dis;

        public Node(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }
}
