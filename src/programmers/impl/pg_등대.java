package programmers.impl;

import java.util.*;

public class pg_등대 {
    private static int[] inDegree;
    private static List<Integer>[] connectedList;
    private static Map<Integer, Lighthouse> lighthouseMap = new HashMap<>();

    public static void main(String[] args) {
        int n = 3;
        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}
                , {7, 6}, {1, 2}, {1, 3}
                , {6, 8}, {2, 9}, {9, 10}};

        final pg_등대 task = new pg_등대();
        System.out.println(task.solution(n, lighthouse));
    }

    public int solution(int n, int[][] lighthouse) {
        int max = lighthouse.length + 1;
        inDegree = new int[max + 1];
        connectedList = new ArrayList[max + 1];
        for (int idx = 1; idx <= max; idx++) {
            connectedList[idx] = new ArrayList<>();
        }

        for (int[] connected : lighthouse) {
            int from = connected[0];
            int to = connected[1];
            connectedList[from].add(to);
            connectedList[to].add(from);
            inDegree[from]++;
            inDegree[to]++;
        }

        Queue<Lighthouse> prq = new PriorityQueue<>();
        for (int idx = 1; idx <= max; idx++) {
            final Lighthouse house = new Lighthouse(idx, inDegree[idx], false);
            prq.offer(house);
            lighthouseMap.put(idx, house);
        }

        int count = 0;
        while (!prq.isEmpty()) {
            final Lighthouse curLighthouse = prq.poll();
            if (curLighthouse.isVisit()) {
                continue;
            }

            count++;
            for (int next : connectedList[curLighthouse.idx]) {
                final Lighthouse nextLighthouse = lighthouseMap.get(next);
                if (nextLighthouse == null || nextLighthouse.inDegree == curLighthouse.inDegree) continue;

                nextLighthouse.visit = true;
                nextLighthouse.inDegree--;
            }
        }
        return count;
    }

    private static class Lighthouse implements Comparable<Lighthouse> {
        private int idx;

        private int inDegree;

        private boolean visit;

        public Lighthouse(int idx, int inDegree, boolean visit) {
            this.idx = idx;
            this.inDegree = inDegree;
            this.visit = visit;
        }

        public boolean isVisit() {
            return this.visit;
        }

        @Override
        public int compareTo(Lighthouse o) {
            return o.inDegree - this.inDegree;
        }
    }
}
