package programmers.sort;

import java.util.Arrays;

public class pg_인사고과 {

    public static void main(String[] args) {
        final pg_인사고과 task = new pg_인사고과();
        int[][] scores = {{1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}};
//                {{2, 2}, {2, 3}, {3, 2}, {2, 2}};
//                {{100, 1}, {100, 2}, {50, 1}};
//                {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}};
        System.out.println(task.solution(scores));
    }

    public int solution(int[][] scores) {
        int myTotalScore = scores[0][0] + scores[0][1];
        int[] myScore = scores[0];
        Arrays.sort(scores, (a, b) ->
                a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int rank = 1;
        int maxScore = 0;
        for (int[] score : scores) {
            int curTotalScore = score[0] + score[1];
            if (score[1] < maxScore) {
                if (Arrays.equals(myScore, score)) {
                    rank = -1;
                    break;
                }
            } else {
                maxScore = score[1];
                if (myTotalScore < curTotalScore) {
                    rank++;
                }
            }
        }
        return rank;
    }
}
