package programmers.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_부대복귀 {

    private static final long MAX = Long.MAX_VALUE;
    private static long[] distance;
    private static List<Integer>[] connectedList;

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{1, 2}, {1, 4}, {2, 4}
                , {2, 5}, {4, 5}};
        int[] sources = {1, 3, 5};
        int destination = 5;
        final pg_부대복귀 task = new pg_부대복귀();
        for (int answer : task.solution(n, roads, sources, destination)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        connectedList = new ArrayList[n + 1];

        for (int idx = 1; idx <= n; idx++) {
            connectedList[idx] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            connectedList[from].add(to);
            connectedList[to].add(from);
        }

        int[] answer = new int[sources.length];
        int answerIdx = 0;
        for(int source: sources) {
            findMinPath(source, destination, n);
            answer[answerIdx++] = distance[destination] == MAX ? -1 : (int) distance[destination];
        }
        return answer;
    }

    private void findMinPath(int source, int destination, int n) {
        distance = new long[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            distance[idx] = MAX;
        }

        final Queue<Integer> prq = new PriorityQueue<>();
        prq.add(source);
        distance[source] = 0;

        while (!prq.isEmpty()) {
            final int idx = prq.poll();
            if(idx == destination) break;
            for(int nextIdx : connectedList[idx]) {
                if(distance[idx] + 1 < distance[nextIdx]) {
                    distance[nextIdx] = distance[idx] + 1;
                    prq.add(nextIdx);
                }
            }
        }
    }
}
