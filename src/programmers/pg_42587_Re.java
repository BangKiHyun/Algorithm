package programmers;

import java.util.*;

public class pg_42587_Re {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        System.out.println(solution(priorities, location));
    }

    public static int solution(int[] priorities, int location) {

        int length = priorities.length;
        Deque<Printer> q = new LinkedList<>();
        List<Integer> priorityList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            q.offer(new Printer(i, priorities[i]));
            priorityList.add(priorities[i]);
        }

        priorityList.sort(Collections.reverseOrder());

        int cnt = 0;
        while (!q.isEmpty()) {
            Printer curPrinter = q.poll();

            if (priorityList.get(0) == curPrinter.priority) {
                cnt++;
                priorityList.remove(0);
                if (curPrinter.position == location) {
                    return cnt;
                }
            } else {
                q.offerLast(curPrinter);
            }
        }

        return cnt;
    }

    private static class Printer {
        private int position;
        private int priority;

        public Printer(final int position, final int priority) {
            this.position = position;
            this.priority = priority;
        }
    }
}
