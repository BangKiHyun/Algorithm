package programmers.dfs;

import java.util.Arrays;

public class pg_징검다리_Timeout {
    private int distance;
    private int[] rocks;
    private int maxValueOfMinDistance = 0;
    private boolean[] visit;

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        final pg_징검다리_Timeout task = new pg_징검다리_Timeout();
        System.out.println(task.solution(distance, rocks, n));
    }

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        this.distance = distance;
        this.rocks = rocks;
        visit = new boolean[rocks.length];
        dfs(0, 0, n);
        return maxValueOfMinDistance;
    }

    private void dfs(int startIdx, int depth, int size) {
        if (depth == size) {
            findAnswer();
            return;
        }

        for (int idx = startIdx; idx < rocks.length; idx++) {
            if (!this.visit[idx]) {
                this.visit[idx] = true;
                dfs(startIdx++, depth + 1, size);
                this.visit[idx] = false;
            }
        }
    }

    private void findAnswer() {
        int minValue = Integer.MAX_VALUE;
        int prePos = 0;
        for (int idx = 0; idx < rocks.length - 1; idx++) {
            if (visit[idx]) continue;
            minValue = Math.min(minValue, rocks[idx] - prePos);
            prePos = rocks[idx];
        }
        minValue = Math.min(minValue, distance - rocks[rocks.length - 1]);
        maxValueOfMinDistance = Math.max(maxValueOfMinDistance, minValue);
    }
}
