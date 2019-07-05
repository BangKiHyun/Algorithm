package programmers;

import java.util.Arrays;

public class pg_43238 {
    static public long solution(int n, int[] times) {
        long answer = 0;
        int p = times.length;
        Arrays.sort(times);
        if (p >= n) return times[p - 1];
        answer = times[0] - 1;
        while (n > 0) {
            answer++;
            for (int i = 0; i < p && n != 0; i++) {
                if (answer % times[i] == 0)
                    n--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int t[] = {7, 10};
        long result = solution(n, t);
        System.out.println(result);
    }
}
