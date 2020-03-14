package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1504_다익 {
    private static final int INF = 100000000;

    private static int n;
    private static int[][] map;
    private static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        distance = new int[3][n + 1];

        for (int i = 0; i < 3; i++) {
            Arrays.fill(distance[i], INF);
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            map[from][to] = map[to][from] = dis;
        }

        st = new StringTokenizer(br.readLine());
        int need_path1 = Integer.parseInt(st.nextToken());
        int need_path2 = Integer.parseInt(st.nextToken());

        searchShortestPath(0, 1);
        searchShortestPath(1, need_path1);
        searchShortestPath(2, need_path2);

        //1-path1-path2-n or 1-path2-path1-n
        int ans = Math.min(distance[0][need_path1] + distance[1][need_path2] + distance[2][n], distance[0][need_path2] + distance[2][need_path1] + distance[1][n]);

        System.out.println(ans >= INF ? -1 : ans);
    }

    private static void searchShortestPath(int idx, int start) {
        Queue<Path> paths = new PriorityQueue<>();
        paths.offer(new Path(start, distance[idx][start] = 0));

        while (!paths.isEmpty()) {
            Path current = paths.poll();

            for (int next = 1; next <= n; next++) {
                if (map[current.pos][next] != 0 && distance[idx][next] >= distance[idx][current.pos] + map[current.pos][next]) {
                    distance[idx][next] = distance[idx][current.pos] + map[current.pos][next];
                    paths.offer(new Path(next, distance[idx][next]));
                }
            }
        }
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

/*
3 3
1 3 20
1 2 15
2 3 6
1 3
*/