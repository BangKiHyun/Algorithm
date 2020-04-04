package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_10282_Topology {
    private static final int INF = 1000000001;

    private static ArrayList<Node>[] lists;
    private static int[] distance;
    private static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            distance = new int[n + 1];
            inDegree = new int[n + 1];
            lists = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                distance[i] = INF;
                lists[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                lists[from].add(new Node(to, dist));
                inDegree[to]++;
            }

            findShortestPath(start);
            int time = 0, cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (distance[i] != INF) {
                    time = Math.max(time, distance[i]);
                    cnt++;
                }
            }

            System.out.println(cnt + " " + time);
        }
    }

    private static void findShortestPath(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        distance[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node next : lists[cur]) {
                distance[next.pos] = Math.min(distance[next.pos], distance[cur] + next.dist);

                if (--inDegree[next.pos] == 0) {
                    q.offer(next.pos);
                }
            }
        }
    }

    private static class Node {
        private int pos;
        private int dist;

        public Node(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }
    }
}
