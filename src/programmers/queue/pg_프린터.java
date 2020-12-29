package programmers.queue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class pg_프린터 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        System.out.println(solution(priorities, location));
    }

    public static int solution(int[] priorities, int location) {
        int len = priorities.length;
        Deque<PrinterInfo> printerInfoQueue = IntStream.range(0, len)
                .mapToObj(i -> new PrinterInfo(i, priorities[i]))
                .collect(Collectors.toCollection(LinkedList::new));

        List<Integer> priorityList = new LinkedList<>();
        for (int priority : priorities) {
            priorityList.add(priority);
        }
        priorityList.sort(Collections.reverseOrder());

        int cnt = 0;
        while (!priorityList.isEmpty()) {
            final PrinterInfo curPrinter = printerInfoQueue.poll();

            if (curPrinter.priority == priorityList.get(0)) {
                cnt++;
                priorityList.remove(0);

                if (curPrinter.pos == location) {
                    return cnt;
                }
            } else {
                printerInfoQueue.offerLast(curPrinter);
            }
        }

        return -1;
    }

    private static class PrinterInfo {
        private int pos;
        private int priority;

        public PrinterInfo(int pos, int priority) {
            this.pos = pos;
            this.priority = priority;
        }
    }
}
