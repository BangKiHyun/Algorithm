package leetcode.problems.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC_Insert_Intervals {

    public static void main(String[] args) {
        final LC_Insert_Intervals problem = new LC_Insert_Intervals();
        int[][] intervals = {{1, 5}};
        int[] newInterval = {0, 0};
        for (int[] line : problem.insert(intervals, newInterval)) {
            for (int answer : line) {
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }

        Arrays.sort(intervals, Comparator.comparingInt(number -> number[0]));
        List<List<Integer>> answerList = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        boolean flag = false;
        if (newInterval[0] == 0 && newInterval[1] == 0) {
            answerList.add(Arrays.asList(0, 0));
            flag = true;
        }
        if (!flag && start > newInterval[0]) {
            start = newInterval[0];
            if (end > newInterval[1]) {
                end = newInterval[1];
                flag = true;
            } else {
                end = Math.max(end, newInterval[1]);
                flag = true;
            }
        }
        for (int[] startEnd : intervals) {
            if (newInterval[0] <= end) {
                end = Math.max(end, newInterval[1]);
                flag = true;
            }

            if (startEnd[0] <= end) {
                end = Math.max(end, startEnd[1]);
            } else {
                answerList.add(Arrays.asList(start, end));
                start = startEnd[0];
                end = startEnd[1];
            }
        }
        answerList.add(Arrays.asList(start, end));
        if (!flag) {
            answerList.add(Arrays.asList(newInterval[0], newInterval[1]));
        }
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
