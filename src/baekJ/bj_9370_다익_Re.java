package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_9370_다익_Re {
    private static final int INF = 987654321;

    private static ArrayList<Node>[] lists;
    private static int[] dist;
    private static ArrayList<Node>[] trace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            lists = new ArrayList[n + 1];
            dist = new int[n + 1];
            trace = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                lists[i] = new ArrayList<>();
                trace[i] = new ArrayList<>();
                dist[i] = INF;
            }

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int need_path1 = Integer.parseInt(st.nextToken());
            int need_path2 = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dis = Integer.parseInt(st.nextToken());
                lists[from].add(new Node(to, dis));
            }

            findShortestPath(start);

            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int finish = Integer.parseInt(br.readLine());
                if ((goTrace(finish, need_path1, need_path2))) {
                    ans.add(finish);
                }
            }

            Collections.sort(ans);
            for (int i : ans) {
                System.out.print(i + " ");
            }
        }
    }

    private static void findShortestPath(int start) {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, dist[start] = 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.dist > dist[cur.pos]) continue;

            for (Node next : lists[cur.pos]) {
                if (dist[next.pos] >= dist[cur.pos] + next.dist) {
                    dist[next.pos] = dist[cur.pos] + next.dist;
                    q.offer(new Node(next.pos, dist[next.pos]));
                    trace[next.pos].add(new Node(cur.pos, next.dist));
                }
            }
        }
    }

    private static boolean goTrace(int start, int need_path1, int need_path2) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, dist[start]));

        int cnt = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.pos == need_path1 || cur.pos == need_path2) {
                if (++cnt == 2) return true;
            }

            for (Node next : trace[cur.pos]) {
                if (dist[cur.pos] == dist[next.pos] + next.dist)
                    q.offer(next);
            }
        }

        return false;
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
