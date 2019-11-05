package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_42584_Queue {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        int[] ans = solution(prices);

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(int[] prices) {
        int len = prices.length;
        int ans[] = new int[len];

        Queue<Integer> queue = new LinkedList<>();
        initQueue(prices, queue);
        findSecond(queue, ans);

        return ans;
    }

    private static void initQueue(int[] prices, Queue<Integer> queue) {
        for (int i : prices) {
            queue.add(i);
        }
    }

    private static void findSecond(Queue<Integer> queue, int[] ans) {
        int idx = 0;

        while (!queue.isEmpty()) {
            int now_price = queue.poll();
            int second = 0;

            for (int i : queue) {
                if (now_price <= i) {
                    second++;
                } else {
                    second++;
                    break;
                }
            }
            ans[idx] = second;
            idx++;
        }
    }
}
