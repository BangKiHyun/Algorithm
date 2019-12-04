package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_42629_Heap {
    public static void main(String[] args) {
        int stock = 4;
        int[] dates = {4, 10, 15};
        int[] supplies = {20, 5, 10};
        int k = 30;

        System.out.println(solution(stock, dates, supplies, k));
    }

    private static int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        Queue<Integer> prq = new PriorityQueue<>(Comparator.reverseOrder());
        int idx = 0;

        for (int i = 0; i < k; i++) {
            if (idx < dates.length && i == dates[idx]) {
                prq.add(supplies[idx]);
                idx++;
            }

            if (stock == i) {
                stock += prq.poll();
                answer++;
            }
        }

        return answer;
    }
}


