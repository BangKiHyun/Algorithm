package programmers.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Math.min;

public class pg_합승_택시_요금_Timeout {
    private static List<Path>[] pathList;

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
        pathList = new ArrayList[n + 1];
        int[] pays = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pathList[i] = new ArrayList<>();
            pays[i] = Integer.MAX_VALUE;
        }

        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int pay = fare[2];
            pathList[from].add(new Path(to, pay));
            pathList[to].add(new Path(from, pay));
        }
        goDijkstra(s, 0, pays);

        int answer = Integer.MAX_VALUE;
        answer = min(answer, pays[a] + pays[b]);

        for (int i = 1; i <= n; i++) {
            if (i == s) continue;
            int[] tempPays = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                tempPays[j] = Integer.MAX_VALUE;
            }
            goDijkstra(i, pays[i], tempPays);
            answer = min(answer, tempPays[a] + tempPays[b] - pays[i]);
        }

        return answer;
    }

    private static void goDijkstra(int start, int startPay, int[] pays) {
        Queue<Path> prq = new PriorityQueue<>();
        prq.add(new Path(start, pays[start] = startPay));

        while (!prq.isEmpty()) {
            final Path curPath = prq.poll();
            for (Path nextPath : pathList[curPath.idx]) {
                if (pays[curPath.idx] + nextPath.pay < pays[nextPath.idx]) {
                    pays[nextPath.idx] = pays[curPath.idx] + nextPath.pay;
                    prq.add(new Path(nextPath.idx, pays[nextPath.idx]));
                }
            }
        }
    }

    private static class Path implements Comparable<Path> {
        private int idx;
        private int pay;

        public Path(int idx, int pay) {
            this.idx = idx;
            this.pay = pay;
        }

        @Override
        public int compareTo(Path o) {
            return this.pay - o.pay;
        }
    }
}
