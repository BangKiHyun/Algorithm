package programmers;

import java.util.Arrays;

public class pg_42747_Sort {
    public static void main(String[] args) {
        int[] citations = {3, 1, 6, 1, 5};
        int ans = solution(citations);
        System.out.println(ans);
    }

    public static int solution(int[] citations) {
        Arrays.sort(citations);
        int h_idx = 0;
        for (int i = 0; i < citations.length; i++) {
            h_idx = citations[i];
            if (i <= h_idx && citations.length - i >= h_idx) {
                return h_idx;
            }
        }
        return h_idx;
    }
}
