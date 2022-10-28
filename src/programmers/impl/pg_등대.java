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

        List<Lighthouse> lighthouseList = new ArrayList<>();
        for (int idx = 1; idx <= max; idx++) {
            final Lighthouse house = new Lighthouse(idx, inDegree[idx], false);
            lighthouseList.add(house);
            lighthouseMap.put(idx, house);
        }

        int count = 0;
        while (!lighthouseList.isEmpty()) {
            if (isSafe(max)) {
                break;
            }
            count++;
            lighthouseList.sort(((o1, o2) -> o2.inDegree - o1.inDegree));
            final Lighthouse curLighthouse = lighthouseList.remove(0);
            curLighthouse.visit = true;
            for (int next : connectedList[curLighthouse.idx]) {
                final Lighthouse nextLighthouse = lighthouseMap.get(next);
                nextLighthouse.inDegree--;
            }
        }
        return count;
    }

    private boolean isSafe(int n) {
        for (int idx = 1; idx <= n; idx++) {
            final Lighthouse lighthouse = lighthouseMap.get(idx);
            if (!lighthouse.isSafe()) {
                return false;
            }
        }
        return true;
    }

    private static class Lighthouse{
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

        public boolean isSafe() {
            if (isVisit()) return true;
            for (int next : connectedList[idx]) {
                if (lighthouseMap.get(next).isVisit()) {
                    return true;
                }
            }
            return false;
        }
    }
}
