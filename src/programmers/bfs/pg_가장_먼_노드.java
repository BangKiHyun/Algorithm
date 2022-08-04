package programmers.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pg_가장_먼_노드 {
    private static boolean[] visit;
    private static List<Node> nodeList = new ArrayList<>();

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        final pg_가장_먼_노드 task = new pg_가장_먼_노드();
        System.out.println(task.solution(n, edge));
    }

    public int solution(int n, int[][] edge) {
        visit = new boolean[n + 1];
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

        int maxDepth = findMaxDepth(edgeList);
        return (int) nodeList.stream()
                .filter(node -> node.depth == maxDepth)
                .count();
    }

    private int findMaxDepth(List<Integer>[] edge) {
        int maxDepth = 0;
        Queue<Node> q = new LinkedList();
        visit[1] = true;
        nodeList.add(new Node(1, 0));
        for (int to : edge[1]) {
            final Node node = new Node(to, 1);
            q.add(node);
            nodeList.add(node);
            visit[to] = true;
        }

        while (!q.isEmpty()) {
            final Node curNode = q.poll();
            maxDepth = curNode.depth;
            for (int next : edge[curNode.idx]) {
                if (!visit[next]) {
                    final Node node = new Node(next, curNode.depth + 1);
                    q.add(node);
                    nodeList.add(node);
                    visit[next] = true;
                }
            }
        }
        return maxDepth;
    }

    private static class Node {
        int idx;
        int depth;

        public Node(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
}
