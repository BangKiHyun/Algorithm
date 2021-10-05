package programmers.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pg_9주차 {
    public static void main(String[] args) {
        int n = 4;
        int[][] wires = {{1, 2},
                {2, 3},
                {3, 4}};
        final pg_9주차 task = new pg_9주차();
        System.out.println(task.solution(n, wires));
    }

    public int solution(int n, int[][] wires) {
        List[] connectedList = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            connectedList[idx] = new ArrayList<Integer>();
        }
        for (int[] wire : wires) {
            int from = wire[0];
            int to = wire[1];
            connectedList[from].add(to);
            connectedList[to].add(from);
        }

        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int from = wire[0];
            int to = wire[1];
            connectedList[from].remove(Integer.valueOf(to));
            connectedList[to].remove(Integer.valueOf(from));
            boolean[] visit = new boolean[n + 1];
            int first = 0;
            int second = 0;
            for (int idx = 1; idx <= n; idx++) {
                if (!visit[idx] && first == 0) {
                    first = findAnswer(idx, n, connectedList, visit);
                } else if (!visit[idx] && first != 0) {
                    second = findAnswer(idx, n, connectedList, visit);
                }
            }
            answer = Math.min(answer, Math.abs(first - second));
            connectedList[from].add(to);
            connectedList[to].add(from);
        }
        return answer;
    }

    private int findAnswer(int idx, int n, List<Integer>[] connectedList, boolean[] visit) {
        int cnt = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        visit[idx] = true;
        while (!q.isEmpty()) {
            final Integer number = q.poll();
            for (int next : connectedList[number]) {
                if (!visit[next]) {
                    visit[next] = true;
                    q.add(next);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
