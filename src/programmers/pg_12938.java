package programmers;

import java.util.Arrays;

public class pg_12938 {

    public static void main(String[] args) {
        int[] ans = solution(4, 15);
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }

    public static int[] solution(int n, int s) {
        int[] answer = new int[n];

        if (s < n) return new int[]{-1};
        for (int i = 0; i < n; i++) {
            answer[i] = s / n;
        }
        int remain = s % n;
        if (remain == 0) return answer;

        for (int i = 0; i < remain; i++) {
            answer[i] += 1;
        }

        Arrays.sort(answer);

        return answer;
    }
}
