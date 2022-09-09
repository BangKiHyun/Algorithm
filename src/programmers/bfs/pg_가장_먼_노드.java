package programmers.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pg_가장_먼_노드 {

    private static boolean[] visit;
    private static List<NodeInfo> nodeInfoList = new ArrayList<>();

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        final pg_가장_먼_노드 task = new pg_가장_먼_노드();
        System.out.println(task.solution(n, edge));
    }

    public int solution(int n, int[][] edge) {
        List<Integer>[] edgeList = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            edgeList[idx] = new ArrayList();
        }

        for (int[] fromTo : edge) {
            int from = fromTo[0];
            int to = fromTo[1];
            edgeList[from].add(to);
            edgeList[to].add(from);
        }

        visit = new boolean[n + 1];
        final int maxDepth = findMaxDepthOfStartNode(edgeList);

        return (int) nodeInfoList.stream()
                .filter(nodeInfo -> nodeInfo.depth == maxDepth)
                .count();
    }

    private int findMaxDepthOfStartNode(List<Integer>[] edgeList) {
        int maxDepth = -1;
        Queue<NodeInfo> q = new LinkedList<>();
        visit[1] = true;
        for (int to : edgeList[1]) {
            final NodeInfo nodeInfo = new NodeInfo(to, 1);
            q.add(nodeInfo);
            nodeInfoList.add(nodeInfo);
            visit[to] = true;
        }

        while (!q.isEmpty()) {
            final NodeInfo from = q.poll();
            for (int to : edgeList[from.idx]) {
                if (!visit[to]) {
                    final NodeInfo nodeInfo = new NodeInfo(to, from.depth + 1);
                    q.add(nodeInfo);
                    nodeInfoList.add(nodeInfo);
                    visit[to] = true;
                }
            }

            if (q.isEmpty()) {
                maxDepth = from.depth;
            }
        }
        return maxDepth;
    }

    private static class NodeInfo {
        private int idx;
        private int depth;

        public NodeInfo(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
}