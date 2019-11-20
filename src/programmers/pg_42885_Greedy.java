package programmers;

import java.util.Arrays;

public class pg_42885_Greedy {

    public static void main(String[] args) {
        int[] people = {70, 80, 50, 50};
        int limit = 100;

        int ans = solution(people, limit);
        System.out.println(ans);
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        int cnt = 0;
        int len = people.length;
        int i = 0, j = 0;

        for (i = len - 1; i > j; i--) {
            if (people[i] + people[j] <= limit) {
                j++;
                cnt++;
            } else {
                cnt++;
            }
        }
        if(i == j){
            cnt++;
        }
        return cnt;
    }
}
