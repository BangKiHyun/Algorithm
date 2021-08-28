package programmers.binarysearch;

import java.util.Arrays;

public class pg_징검다리 {

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        final pg_징검다리 task = new pg_징검다리();
        System.out.println(task.solution(distance, rocks, n));
    }

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int left = 1;
        int right = distance;
        Arrays.sort(rocks);
        while (left <= right) {
            int mid = (left + right) / 2;
            int preValue = 0;
            int removeCount = 0;
            int minValue = Integer.MAX_VALUE;
            for (int rock : rocks) {
                if (rock - preValue < mid) {
                    removeCount++;
                    if (removeCount > n) break;
                } else {
                    minValue = Math.min(minValue, rock - preValue);
                    preValue = rock;
                }
            }
            if (removeCount > n) right = mid - 1;
            else {
                answer = Math.max(answer, minValue);
                left = mid + 1;
            }
        }
        return answer;
    }
}
