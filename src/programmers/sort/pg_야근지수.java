package programmers.sort;

import java.util.PriorityQueue;
import java.util.Queue;

public class pg_야근지수 {

    public static void main(String[] args) {
        int n = 4;
        int[] works = {4, 3, 3};
        System.out.println(solution(n, works));
    }

    public static long solution(int n, int[] works) {
        Queue<Work> prq = new PriorityQueue<>();
        for (int work : works) {
            prq.add(new Work(work));
        }

        while (n > 0) {
            final Work work = prq.peek();
            if (work.amount * prq.size() <= n) return 0;
            if (work.isEnd()) {
                prq.poll();
                continue;
            }
            work.reduceAmount();
            prq.add(prq.poll());
            n--;
        }

        return findFatigue(prq);
    }

    private static long findFatigue(Queue<Work> prq) {
        long fatigue = 0L;
        while (!prq.isEmpty()) {
            final Work work = prq.poll();
            fatigue += work.amount * work.amount;
        }
        return fatigue;
    }

    private static class Work implements Comparable<Work> {
        private int amount;

        public Work(int amount) {
            this.amount = amount;
        }

        public void reduceAmount() {
            this.amount--;
        }

        public boolean isEnd() {
            return this.amount == 0;
        }

        @Override
        public int compareTo(Work target) {
            return target.amount - this.amount;
        }
    }
}
