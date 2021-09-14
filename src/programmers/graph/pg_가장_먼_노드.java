package programmers.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pg_가장_먼_노드 {
    private List<Node> nodeList = new ArrayList<>();

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6,},
                {4, 3},
                {3, 2},
                {1, 3},
                {1, 2},
                {2, 4},
                {5, 2}};
        final pg_가장_먼_노드 task = new pg_가장_먼_노드();
        System.out.println(task.solution(n, edge));
    }

    public int solution(int n, int[][] edge) {
        List<Integer>[] connectedList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            connectedList[i] = new ArrayList<>();
        }
        boolean[] visit = new boolean[n + 1];
        for (int[] node : edge) {
            int from = node[0];
            int to = node[1];
            connectedList[from].add(to);
            connectedList[to].add(from);
        }
        int maxDepth = findMaxDepth(visit, connectedList);
        return (int) nodeList.stream()
                .filter(node -> node.depth == maxDepth)
                .count();
    }

    private int findMaxDepth(boolean[] visit, List<Integer>[] connectedList) {
        int depth = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 0));
        visit[1] = true;
        while (!q.isEmpty()) {
            Node curNode = q.poll();
            depth = curNode.depth;
            for (int to : connectedList[curNode.idx]) {
                if (!visit[to]) {
                    visit[to] = true;
                    final Node next = new Node(to, depth + 1);
                    q.add(next);
                    nodeList.add(next);
                }
            }
        }
        return depth;
    }

    private static class Node {
        private int idx;
        private int depth;

        public Node(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
}
