package programmers.bfs;

import java.util.*;

public class pg_부대복귀 {

    private static boolean[] visit;
    private static List<Integer>[] connectedList;

    private static Map<Integer, Integer> map = new TreeMap<>();

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
        visit = new boolean[n + 1];
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

        goBFS(sources, destination);

        int[] answer = new int[sources.length];
        int answerIdx = 0;
        for (int source : sources) {
            answer[answerIdx++] = map.get(source) != null ? map.get(source) : -1;
        }
        return answer;
    }

    private void goBFS(int[] sources, int destination) {
        final Queue<Node> q = new LinkedList<>();
        q.add(new Node(destination, 0));

        while (!q.isEmpty()) {
            if(map.size() == sources.length) break;

            final Node curNode = q.poll();
            for (int source : sources) {
                if (source == curNode.idx && !map.containsKey(source)) {
                    map.put(curNode.idx, curNode.depth);
                }
            }
            for (int nextIdx : connectedList[curNode.idx]) {
                if (!visit[nextIdx]) {
                    visit[nextIdx] = true;
                    q.add(new Node(nextIdx, curNode.depth + 1));
                }
            }
        }
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
