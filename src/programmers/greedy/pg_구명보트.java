package programmers.greedy;

import java.util.Arrays;

public class pg_구명보트 {
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;

        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);

        int cnt = 0;
        int startIdx = 0;
        for (int endIdx = people.length - 1; startIdx <= endIdx; endIdx--) {
            int totalWeight = people[startIdx] + people[endIdx];
            if (totalWeight <= limit) {
                startIdx++;
            }
            cnt++;
        }
        return cnt;
    }
}
