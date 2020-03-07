package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1238_다익 {
    private static int goal;
    private static int[][] map;
    private static int[] dist;
    private static int[][] shortestPath;

    private static final int INF = 99999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()) + 1;
        int m = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        dist = new int[n];
        map = new int[n][n];
        shortestPath = new int[n][n];
        for (int i = 1; i < n; i++) {
            dist[i] = INF;
            Arrays.fill(map[i], -1);
            Arrays.fill(shortestPath[i], INF);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            map[from][to] = dis;
        }

        for (int i = 1; i < n; i++) {
            Arrays.fill(dist, INF);
            findShortestPath(i, n);
            for (int j = 1; j < n; j++) {
                shortestPath[i][j] = dist[j];
            }
        }


        int max = -1;
        for (int i = 1; i < n; i++) {
            if (shortestPath[i][goal] == INF || shortestPath[goal][i] == INF) continue;
            max = Math.max(shortestPath[i][goal] + shortestPath[goal][i], max);
        }

        System.out.println(max);
    }

    private static void findShortestPath(int start, int n) {
        Queue<Path> q = new PriorityQueue<>();
        q.add(new Path(start, dist[start] = 0));

        while (!q.isEmpty()) {
            Path now = q.poll();
            for (int next = 1; next < n; next++) {
                if (map[now.pos][next] != -1 && dist[now.pos] + map[now.pos][next] < dist[next]) {
                    dist[next] = dist[now.pos] + map[now.pos][next];
                    q.add(new Path(next, dist[next]));
                }
            }
        }
    }

    private static class Path implements Comparable<Path> {
        private int pos;
        private int dist;

        public Path(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }

        @Override
        public int compareTo(Path o) {
            return this.dist - o.dist;
        }
    }
}
