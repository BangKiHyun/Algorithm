package programmers.dfs;

import java.util.Deque;
import java.util.LinkedList;

public class pg_두_큐_합_같게만들기 {

    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};
        final pg_두_큐_합_같게만들기 task = new pg_두_큐_합_같게만들기();
        System.out.println(task.solution(queue1, queue2));
    }

    public int solution(int[] queue1, int[] queue2) {
        int length = queue1.length;
        LinkedList<Integer> deque1 = new LinkedList<>();
        LinkedList<Integer> deque2 = new LinkedList<>();

        long sum1 = 0;
        long sum2 = 0;
        for (int idx = 0; idx < length; idx++) {
            final int first = queue1[idx];
            final int second = queue2[idx];
            sum1 += first;
            sum2 += second;
            deque1.offer(first);
            deque2.offer(second);
        }

        int cnt = 0;
        int maxCnt = (deque1.size() + deque2. size()) * 2;
        while (sum1 != sum2) {
            if (sum1 < sum2) {
                final int num = deque2.pop();
                sum2 -= num;

                deque1.offer(num);
                sum1 += num;
            } else {
                final int num = deque1.pop();
                sum1 -= num;

                deque2.offer(num);
                sum2 += num;
            }
            if(++cnt == maxCnt) {
                cnt = -1;
                break;
            }
        }
        return cnt;
    }
}
