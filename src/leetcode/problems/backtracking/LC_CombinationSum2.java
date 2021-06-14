package leetcode.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_CombinationSum2 {
    private List<List<Integer>> answer = new ArrayList<>();

    public static void main(String[] args) {
        int[] candidates = {4, 4, 2, 1, 4, 2, 2, 1, 3};
        int target = 6;
        final LC_CombinationSum2 problem = new LC_CombinationSum2();
        for (List<Integer> answers : problem.combinationSum2(candidates, target)) {
            for (int answer : answers) {
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        findAnswer(candidates, target, 0, new ArrayList<>());
        return answer;
    }

    private void findAnswer(int[] candidates, int target, int startIdx, ArrayList<Integer> maybeAnswer) {
        if (target == 0 && isNotContains(maybeAnswer)) {
            answer.add(new ArrayList<>(maybeAnswer));
            return;
        }
        if (target < 0 || startIdx == candidates.length) return;

        for (int idx = startIdx; idx < candidates.length; idx++) {
            if (candidates[idx] > target) break;
            maybeAnswer.add(candidates[idx]);
            findAnswer(candidates, target - candidates[idx], idx + 1, maybeAnswer);
            maybeAnswer.remove(maybeAnswer.size() - 1);
        }
    }

    private boolean isNotContains(ArrayList<Integer> maybeAnswer) {
        for (List<Integer> answerList : answer) {
            if (answerList.size() == maybeAnswer.size()) {
                int matchCount = 0;
                for (int idx = 0; idx < answerList.size(); idx++) {
                    if (answerList.get(idx).equals(maybeAnswer.get(idx))) {
                        matchCount++;
                    }
                }
                if(matchCount == answerList.size()) return false;
            }
        }
        return true;
    }
}
