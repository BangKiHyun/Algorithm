package programmers.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class pg_12978_배달 {

    private static final int MAX = Integer.MAX_VALUE;
    private static List<Path>[] pathList;
    private static int[] dist;

    public static void main(String[] args) {
        int n = 5;
        int[][] road = {{1, 2, 1},
                {2, 3, 3},
                {5, 2, 2},
                {1, 4, 2},
                {5, 3, 1},
                {5, 4, 2}};
        int k = 3;
        System.out.println(solution(n, road, k));
    }

    public static int solution(int N, int[][] road, int K) {
        dist = new int[N + 1];
        pathList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            dist[i] = MAX;
            pathList[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int dist = road[i][2];

            pathList[from].add(new Path(to, dist));
            pathList[to].add(new Path(from, dist));
        }

        goDijkstra();
        return getAnswer(N, K);
    }

    private static void goDijkstra() {
        Queue<Path> prq = new PriorityQueue<>();
        prq.add(new Path(1, dist[1] = 0));

        while (!prq.isEmpty()) {
            Path curPath = prq.poll();

            for (Path next : pathList[curPath.idx]) {
                if (next.dist + dist[curPath.idx] < dist[next.idx]) {
                    dist[next.idx] = next.dist + dist[curPath.idx];
                    prq.add(new Path(next.idx, dist[next.idx]));
                }
            }
        }
    }

    private static int getAnswer(int n, int k) {
        return (int) IntStream.rangeClosed(1, n)
                .filter(i -> dist[i] <= k)
                .count();
    }

    private static class Path implements Comparable<Path> {
        private int idx;
        private int dist;

        public Path(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Path o) {
            return this.dist - o.dist;
        }
    }
}
