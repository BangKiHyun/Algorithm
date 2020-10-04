package programmers;

import java.util.PriorityQueue;
import java.util.Queue;

public class pg_42626_Queue {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;

        System.out.println(solution(scoville, k));
    }

    public static int solution(int[] scoville, int K) {
        Queue<Integer> prq = new PriorityQueue<>();
        for (int sco : scoville) {
            prq.offer(sco);
        }

        int cnt = 0;
        while (!prq.isEmpty()) {
            int firstScoville = prq.poll();
            if(firstScoville >= K) return cnt;
            if(prq.isEmpty()) return -1;

            cnt++;
            int secondScoville = prq.poll();
            int newSocville = getScoville(firstScoville, secondScoville);
            prq.add(newSocville);
        }

        return -1;
    }

    private static int getScoville(final int firstScoville, final int secondScoville) {
        return firstScoville + (secondScoville * 2);
    }
}
