package programmers.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class pg_배달_Re {
    private static final int MAX = 500_001;

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
        List<Path>[] pathList = new ArrayList[N + 1];
        int[] distances = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            pathList[idx] = new ArrayList<>();
            distances[idx] = MAX;
        }

        for (int[] pathInfo : road) {
            int from = pathInfo[0];
            int to = pathInfo[1];
            int distance = pathInfo[2];
            pathList[from].add(new Path(to, distance));
            pathList[to].add(new Path(from, distance));
        }

        findShortestPath(1, pathList, distances);
        return findCountOfMovablePath(distances, K);
    }

    private static void findShortestPath(int startPos, List<Path>[] pathList, int[] distances) {
        Queue<Path> prq = new PriorityQueue<>();
        distances[startPos] = 0;
        prq.add(new Path(startPos, distances[startPos]));

        while (!prq.isEmpty()) {
            final Path curPath = prq.poll();

            for (Path next : pathList[curPath.pos]) {
                if (distances[curPath.pos] + next.distance < distances[next.pos]) {
                    distances[next.pos] = distances[curPath.pos] + next.distance;
                    prq.add(new Path(next.pos, distances[next.pos]));
                }
            }
        }
    }

    private static int findCountOfMovablePath(int[] distances, int acceptableDistance) {
        return (int) IntStream.rangeClosed(1, distances.length - 1)
                .filter(idx -> distances[idx] <= acceptableDistance)
                .count();
    }

    private static class Path implements Comparable<Path> {
        private int pos;
        private int distance;

        public Path(int pos, int distance) {
            this.pos = pos;
            this.distance = distance;
        }

        @Override
        public int compareTo(Path target) {
            return this.distance - target.distance;
        }
    }
}
