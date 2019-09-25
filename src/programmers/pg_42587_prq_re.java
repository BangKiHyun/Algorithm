package programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class pg_42587_prq_re {
    public static void main(String[] args) {
        int[] p = //{2, 1, 3, 2};
        {1, 1, 9, 1, 1, 1};
        int n = 0;
        int ans = solution(p, n);
        System.out.println(ans);
    }

    private static int solution(int[] priorities, int location) {
        int answer = 1;
        PriorityQueue<Integer> prq = new PriorityQueue<>(Collections.reverseOrder());
        for (int task : priorities) {
            prq.add(task);
        }

        while (!prq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                int task = prq.peek();
                if (priorities[i] == task) {
                    if (i == location) {
                        return answer;
                    }
                    prq.poll();
                    answer++;
                }
            }
        }
        return answer;
    }
}
