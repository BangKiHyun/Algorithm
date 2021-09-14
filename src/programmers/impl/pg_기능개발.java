package programmers.impl;

import java.util.*;

public class pg_기능개발 {
    public static void main(String[] args) {
        int[] progressed = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        final pg_기능개발 task = new pg_기능개발();
        for (int answer : task.solution(progressed, speeds)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>();
        int curDay = calculateEndDays(progresses[0], speeds[0]);
        int count = 0;
        for (int idx = 0; idx < progresses.length; idx++) {
            int nextDay = calculateEndDays(progresses[idx], speeds[idx]);
            if (curDay < nextDay) {
                curDay = nextDay;
                answerList.add(count);
                count = 0;
            }
            count++;
        }
        if (count != 0) {
            answerList.add(count);
        }
        int[] answer = new int[answerList.size()];
        for (int idx = 0; idx < answer.length; idx++) {
            answer[idx] = answerList.get(idx);
        }
        return answer;
    }

    private int calculateEndDays(int progress, int speed) {
        int remainProgress = 100 - progress;
        int day = remainProgress / speed;
        return remainProgress % speed != 0 ? day + 1 : day;
    }
}
