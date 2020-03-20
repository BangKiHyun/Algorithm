package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_10282_다익 {
    private static int n;
    private static int[][] board;
    private static int[] dist;

    private static final int INF = 999999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board = new int[n + 1][n + 1];
            dist = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                Arrays.fill(board[i], -1);
                dist[i] = INF;
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                board[from][to] = time;
            }

            searchShortestPath(c);
            int cnt = 0, time = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != INF) {
                    time = Math.max(time, dist[i]);
                    cnt++;
                }
            }
            System.out.println(cnt + " " + time);
        }
    }

    private static void searchShortestPath(int start) {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, dist[start] = 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int next = 1; next <= n; next++) {
                if (board[now.pos][next] != -1 && dist[next] > now.dist + board[now.pos][next]) {
                    dist[next] = now.dist + board[now.pos][next];
                    q.offer(new Node(next, dist[next]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private int pos;
        private int dist;

        public Node(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
