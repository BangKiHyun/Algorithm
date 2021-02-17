package programmers.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_합승_택시_요금 {

    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}};

        System.out.println(solution(n, s, a, b, fares));
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        List<Path>[] pathList = new ArrayList[n + 1];
        int[] startCosts = new int[n + 1];
        int[] aCosts = new int[n + 1];
        int[] bCosts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pathList[i] = new ArrayList<>();
            startCosts[i] = Integer.MAX_VALUE;
            aCosts[i] = Integer.MAX_VALUE;
            bCosts[i] = Integer.MAX_VALUE;
        }

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];
            pathList[start].add(new Path(end, cost));
            pathList[end].add(new Path(start, cost));
        }

        goDijkstra(pathList, startCosts, s, 0);
        goDijkstra(pathList, aCosts, a, 0);
        goDijkstra(pathList, bCosts, b, 0);

        int answer = startCosts[a] + startCosts[b];
        for (int connectionIdx = 1; connectionIdx <= n; connectionIdx++) {
            answer = Math.min(answer, startCosts[connectionIdx] + aCosts[connectionIdx] + bCosts[connectionIdx]);
        }
        return answer;
    }

    private static void goDijkstra(List<Path>[] pathList, int[] costs, int s, int cost) {
        Queue<Path> prq = new PriorityQueue<>();
        prq.add(new Path(s, costs[s] = cost));

        while (!prq.isEmpty()) {
            final Path curPath = prq.poll();
            for (Path nextPath : pathList[curPath.pos]) {
                if (costs[curPath.pos] + nextPath.cost < costs[nextPath.pos]) {
                    costs[nextPath.pos] = costs[curPath.pos] + nextPath.cost;
                    prq.add(new Path(nextPath.pos, costs[nextPath.pos]));
                }
            }
        }
    }

    private static class Path implements Comparable<Path> {
        private int pos;
        private int cost;

        public Path(int pos, int cost) {
            this.pos = pos;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path path) {
            return this.cost - path.cost;
        }
    }
}
