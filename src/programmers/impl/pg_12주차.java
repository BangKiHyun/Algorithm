package programmers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pg_12주차 {
    private int fatigue;
    private Map<Integer, Dungeon> dungeonMap = new HashMap<>();
    private List<Integer> orderList = new ArrayList<>();
    private boolean[] visit;
    private int n;
    private int answer;

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20},
                {50, 40},
                {30, 10}};
        final pg_12주차 task = new pg_12주차();
        System.out.println(task.solution(k, dungeons));
    }

    public int solution(int k, int[][] dungeons) {
        fatigue = k;
        n = dungeons.length;
        visit = new boolean[n];
        for (int idx = 0; idx < n; idx++) {
            dungeonMap.put(idx, new Dungeon(dungeons[idx][0], dungeons[idx][1]));
        }
        recursion(0);
        return answer;
    }

    private void recursion(int depth) {
        if (depth == n) {
            findAnswer();
            return;
        }

        for (int idx = 0; idx < n; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                orderList.add(idx);
                recursion(depth + 1);
                visit[idx] = false;
                orderList.remove(orderList.size() - 1);
            }
        }
    }

    private void findAnswer() {
        int curFatigue = fatigue;
        int count = 0;
        for (int idx : orderList) {
            final Dungeon dungeon = dungeonMap.get(idx);
            if (dungeon.canEnter(curFatigue)) {
                curFatigue -= dungeon.consumeFatigue;
                count++;
            } else break;
        }
        answer = Math.max(answer, count);
    }

    private class Dungeon {
        private final int requiredFatigue;
        private final int consumeFatigue;

        public Dungeon(int requiredFatigue, int consumeFatigue) {
            this.requiredFatigue = requiredFatigue;
            this.consumeFatigue = consumeFatigue;
        }

        public boolean canEnter(int inputFatigue) {
            return inputFatigue >= requiredFatigue;
        }
    }
}
