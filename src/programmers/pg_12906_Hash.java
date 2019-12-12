package programmers;

import java.util.*;

public class pg_12906_Hash {
    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 3, 0, 1, 1};
        for (int i : solution(arr)) {
            System.out.print(i+ " ");
        }
    }

    private static int[] solution(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        int now = arr[0];
        q.add(now);
        for (int i = 1; i < arr.length; i++) {
            int next = arr[i];
            if (now != next) {
                q.add(next);
                now = next;
            }
        }

        int[] answer = new int[q.size()];
        int idx = 0;
        while (!q.isEmpty()) {
            answer[idx] = q.poll();
            idx++;
        }
        return answer;
    }
}
