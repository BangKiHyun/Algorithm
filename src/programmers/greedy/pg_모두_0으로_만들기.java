package programmers.greedy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pg_모두_0으로_만들기 {
    private int[] inDegree;

    public static void main(String[] args) {
        final pg_모두_0으로_만들기 app = new pg_모두_0으로_만들기();
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1},
                {3, 4},
                {2, 3},
                {0, 3}};
        System.out.println(app.solution(a, edges));
    }

    public long solution(int[] a, int[][] edges) {
        int size = a.length;
        long[] numbers = new long[a.length];
        List<Integer>[] connectList = new ArrayList[size];
        inDegree = new int[size];

        long sum = 0;
        for (int idx = 0; idx < size; idx++) {
            connectList[idx] = new ArrayList<>();
            sum += a[idx];
            numbers[idx] = a[idx];
        }
        if (sum != 0) return -1;

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            connectList[from].add(to);
            connectList[to].add(from);
            inDegree[from]++;
            inDegree[to]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int idx = 0; idx < size; idx++) {
            if (inDegree[idx] == 1) {
                q.add(idx);
            }
        }
        return findAnswer(q, connectList, numbers);
    }

    private long findAnswer(Queue<Integer> q, List<Integer>[] connectList, long[] numbers) {
        long answer = 0;
        while (!q.isEmpty()) {
            int curIdx = q.poll();
            answer += Math.abs(numbers[curIdx]);
            inDegree[curIdx]--;

            for (int nextIdx : connectList[curIdx]) {
                if (inDegree[nextIdx] == 0) continue;
                numbers[nextIdx] += numbers[curIdx];
                if (--inDegree[nextIdx] == 1) {
                    q.add(nextIdx);
                }
            }
        }
        return answer;
    }
}
