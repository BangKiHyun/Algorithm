package leetcode.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC_CombinationSum {
    private int size;
    private List<List<Integer>> answers = new ArrayList<>();

    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        int target = 8;
        final LC_CombinationSum problem = new LC_CombinationSum();
        for (List<Integer> answers : problem.combinationSum(candidates, target)) {
            for (int answer : answers) {
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        size = candidates.length;
        findAnswers(candidates, target, 0, new ArrayList<>());
        return answers;
    }

    private void findAnswers(int[] candidates, int target, int startIdx, List<Integer> candiateList) {
        if (target == 0) {
            answers.add(new ArrayList<>(candiateList));
            return;
        }

        if (target < 0 || startIdx == size) return;
        for (int idx = startIdx; idx < size; idx++) {
            candiateList.add(candidates[idx]);
            findAnswers(candidates, target - candidates[idx], idx, candiateList);
            candiateList.remove(candiateList.size() - 1);
        }
    }
}
