package programmers.bfs;

import java.util.*;

public class pg_등산코스_정하기 {

    private List<Path>[] connectedList;
    private int minIntensity = Integer.MAX_VALUE;
    private int minSummit = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int n = 7;
        int[][] paths = {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}};
        int[] gates = {3, 7};
        int[] summits = {1, 5};
        final pg_등산코스_정하기 task = new pg_등산코스_정하기();
        for (int answer : task.solution(n, paths, gates, summits)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        connectedList = new ArrayList[n + 1];
        for (int cnt = 1; cnt <= n; cnt++) {
            connectedList[cnt] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int intensity = path[2];
            connectedList[from].add(new Path(to, intensity, equalsGate(gates, to), equalsSummit(summits, to)));
            connectedList[to].add(new Path(from, intensity, equalsGate(gates, from), equalsSummit(summits, from)));
        }

        for (int gate : gates) {
            findRoute(n + 1, gate);
        }

        return new int[]{minSummit, minIntensity};
    }

    private void findRoute(int maxLength, int gate) {
        boolean[] visited = new boolean[maxLength];
        int intensity = minIntensity;
        int summit = maxLength;

        Queue<Node> q = new LinkedList<>();
        for (Path path : connectedList[gate]) {
            q.offer(new Node(path.idx, path.intensity, path.summit));
        }
        visited[gate] = true;

        while (!q.isEmpty()) {
            final Node curNode = q.poll();
            if (curNode.isSummit()) {
                if (curNode.intensity < intensity) {
                    intensity = curNode.intensity;
                    summit = curNode.idx;
                } else if (curNode.intensity == intensity) {
                    summit = Math.min(summit, curNode.idx);
                }
                continue;
            }
            for (Path nextPath : connectedList[curNode.idx]) {
                if ((!visited[nextPath.idx] && !nextPath.isGate()) || nextPath.isSummit()) {
                    visited[nextPath.idx] = true;
                    q.offer(new Node(nextPath.idx, Math.max(curNode.intensity, nextPath.intensity), nextPath.isSummit()));
                }
            }
        }
        System.out.println();
        if (summit != maxLength) {
            minIntensity = Math.min(minIntensity, intensity);
            minSummit = summit;
        }
    }

    private boolean equalsGate(int[] gates, int number) {
        return Arrays.stream(gates)
                .anyMatch(gate -> gate == number);
    }

    private boolean equalsSummit(int[] summits, int number) {
        return Arrays.stream(summits)
                .anyMatch(summit -> summit == number);
    }

    private static class Path {
        private int idx;
        private int intensity;
        private boolean summit;
        private boolean gate;

        public Path(int idx, int intensity, boolean gate, boolean summit) {
            this.idx = idx;
            this.intensity = intensity;
            this.gate = gate;
            this.summit = summit;
        }

        private boolean isSummit() {
            return summit;
        }

        private boolean isGate() {
            return gate;
        }
    }

    private static class Node {
        private int idx;
        private int intensity;

        private boolean summit;

        public Node(int idx, int intensity, boolean summit) {
            this.idx = idx;
            this.intensity = intensity;
            this.summit = summit;
        }

        public boolean isSummit() {
            return summit;
        }
    }
}
