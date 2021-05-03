package programmers.sort;

import java.util.Arrays;

public class pg_H_Index {

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(solution(citations));
    }

    public static int solution(int[] citations) {
        Arrays.sort(citations);
        for (int idx = 0; idx < citations.length; idx++) {
            int h_idx = citations.length - idx;
            if (citations[idx] >= h_idx) {
                return h_idx;
            }
        }
        return 0;
    }
}
