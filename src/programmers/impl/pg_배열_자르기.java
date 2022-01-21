package programmers.impl;

import java.util.ArrayList;
import java.util.List;

public class pg_배열_자르기 {

    public static void main(String[] args) {
        final pg_배열_자르기 task = new pg_배열_자르기();
        int n = 3;
        long left = 2;
        long right = 5;
        for (int answer : task.solution(n, left, right)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int n, long left, long right) {
        List<Integer> answerList = new ArrayList<>();
        for (long idx = left; idx <= right; idx++) {
            answerList.add((int) (Math.max(idx / n, idx % n) + 1));
        }
        int[] answer = new int[answerList.size()];
        int idx = 0;
        for (int number : answerList) {
            answer[idx++] = number;
        }
        return answer;
    }
}
