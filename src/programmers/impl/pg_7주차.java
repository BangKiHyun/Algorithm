package programmers.impl;

import java.util.*;

public class pg_7주차 {

    public static void main(String[] args) {
        int[] enter = {1, 4, 2, 3};
        int[] leave = {2, 1, 4, 3};
        final pg_7주차 task = new pg_7주차();
        for (int answer : task.solution(enter, leave)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int[] enter, int[] leave) {
        int n = enter.length;
        int[] answer = new int[n];
        Map<Integer, Integer> personMap = new HashMap<>();
        int enterIdx = 0;
        int leaveIdx = 0;
        while (enterIdx != n && leaveIdx != n) {
            personMap.put(enter[enterIdx], enter[enterIdx]);
            if (personMap.size() > 1) {
                for (int key : personMap.keySet()) {
                    answer[key - 1]++;
                }
                answer[enter[enterIdx] - 1] += personMap.size() - 2;
            }

            while (true) {
                if (leaveIdx < n && personMap.containsKey(leave[leaveIdx])) {
                    personMap.remove(leave[leaveIdx]);
                    leaveIdx++;
                } else break;
            }
            enterIdx++;
        }
        return answer;
    }
}
