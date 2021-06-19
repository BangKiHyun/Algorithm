package leetcode.problems.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC_Merge_Intervals {

    public static void main(String[] args) {
        final LC_Merge_Intervals problem = new LC_Merge_Intervals();
        int[][] intervals = {{1, 6},
                {2, 6},
                {8, 10},
                {15, 18}};
        for (int[] line : problem.merge(intervals)) {
            for (int answer : line) {
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(number -> number[0]));
        List<List<Integer>> answerList = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int[] startEnd : intervals) {
            if (startEnd[0] <= end) {
                end = Math.max(end, startEnd[1]);
            } else {
                answerList.add(Arrays.asList(start, end));
                start = startEnd[0];
                end = startEnd[1];
            }
        }
        answerList.add(Arrays.asList(start, end));
        return makeAnswer(answerList);
    }

    private int[][] makeAnswer(List<List<Integer>> answerList) {
        int[][] answer = new int[answerList.size()][2];
        int answerIdx = 0;
        for (List<Integer> list : answerList) {
            answer[answerIdx][0] = list.get(0);
            answer[answerIdx][1] = list.get(1);
            answerIdx++;
        }
        return answer;
    }
}
