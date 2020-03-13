package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_9370_다익 {
    private static final int INF = 99999999;

    private static int need_path1, need_path2;
    private static int[][] map;
    private static int[] distance;
    private static ArrayList<Integer>[] trace;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            map = new int[n + 1][n + 1];
            distance = new int[n + 1];
            trace = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                Arrays.fill(map[i], -1);
                distance[i] = INF;
                trace[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            need_path1 = Integer.parseInt(st.nextToken());
            need_path2 = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dis = Integer.parseInt(st.nextToken());
                map[from][to] = map[to][from] = dis;
            }

            searchShortestPath(start, n);

            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int finish = Integer.parseInt(br.readLine());
                if ((goByPath(finish))) {
                    ans.add(finish);
                }
            }

            Collections.sort(ans);
            for (int i : ans) {
                System.out.print(i + " ");
            }
        }

    }

    private static void searchShortestPath(int start, int n) {
        Queue<Path> paths = new PriorityQueue<>();
        paths.offer(new Path(start, distance[start] = 0));

        while (!paths.isEmpty()) {
            Path now = paths.poll();
            for (int next = 1; next <= n; next++) {
                if (map[now.pos][next] != -1 && distance[next] >= now.dis + map[now.pos][next]) {
                    distance[next] = now.dis + map[now.pos][next];
                    paths.offer(new Path(next, distance[next]));
                    trace[next].add(now.pos);
                }
            }
        }
    }

    private static boolean goByPath(int finish) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(finish);
        int cnt = 0;
        while (!q.isEmpty()) {
            int next = q.poll();
            if (next == need_path1 || next == need_path2) {
                if (++cnt == 2) return true;
            }

            for (int now : trace[next]) {
                if (map[now][next] != -1 && distance[next] == distance[now] + map[now][next]) {
                    q.offer(now);
                }
            }
        }
        return false;
    }

    private static class Path implements Comparable<Path> {
        private int pos;
        private int dis;

        public Path(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }

        @Override
        public int compareTo(Path o) {
            return this.dis - o.dis;
        }
    }
}
