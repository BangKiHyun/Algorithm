package programmers.disjoinset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class pg_섬_연결하기 {

    private static int[] parent;
    private static List<Bridge> bridgeList;

    public static void main(String[] args) {
        int n = 6;
        int costs[][] = {{0, 1, 5}, {0, 3, 2}, {0, 4, 3}, {1, 4, 1}, {3, 4, 10}, {1, 2, 2}, {2, 5, 3}, {4, 5, 4}};
        int ans = solution(n, costs);
        System.out.println(ans);
    }

    public static int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int idx = 0; idx < n; idx++) {
            parent[idx] = idx;
        }

        bridgeList = new ArrayList<>();
        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            bridgeList.add(new Bridge(from, to, cost[2]));
        }
        bridgeList.sort(Comparator.comparingInt(bridge -> bridge.cost));

        int totalCost = 0;
        for (Bridge bridge : bridgeList) {
            int fromIdx = bridge.from;
            int toIdx = bridge.to;
            if (!isSameParent(fromIdx, toIdx)) {
                unionParent(fromIdx, toIdx);
                totalCost += bridge.cost;
            }
        }
        return totalCost;
    }

    private static boolean isSameParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        return a == b;
    }

    private static void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int getParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }

    private static class Bridge {
        private int from;
        private int to;
        private int cost;

        public Bridge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
