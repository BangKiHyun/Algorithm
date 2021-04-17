package programmers.bfs;

import java.util.*;

public class pg_동굴_탐험 {

    public static void main(String[] args) {
        int n = 9;
        int[][] path = {{0, 1},
                {0, 3},
                {0, 7},
                {8, 1},
                {3, 6},
                {1, 2},
                {4, 7},
                {7, 5}};
        int[][] order = {{8, 5},
                {6, 7},
                {4, 1}};
        System.out.println(solution(n, path, order));
    }

    public static boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] pathList = new ArrayList[n];
        for (int idx = 0; idx < n; idx++) {
            pathList[idx] = new ArrayList<>();
        }
        for (int[] paths : path) {
            int from = paths[0];
            int to = paths[1];
            pathList[from].add(to);
            pathList[to].add(from);
        }

        int[] before = new int[n];
        for (int[] orders : order) {
            before[orders[1]] = orders[0];
        }
        if (before[0] != 0) return false;

        int[] after = new int[n];
        boolean[] visit = new boolean[n];
        Deque<Integer> dq = new LinkedList<>(pathList[0]);
        visit[0] = true;
        while (!dq.isEmpty()) {
            final int curPath = dq.pop();

            if (visit[curPath]) continue;
            if (!visit[before[curPath]]) {
                after[before[curPath]] = curPath;
                continue;
            }

            visit[curPath] = true;
            dq.addAll(pathList[curPath]);
            dq.add(after[curPath]);
        }

        for (int idx = 0; idx < n; idx++) {
            if (!visit[idx]) return false;
        }
        return true;
    }
}
