package leetcode.problems.dfs;

public class LC_TargetSum {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        final LC_TargetSum task = new LC_TargetSum();
        System.out.println(task.findTargetSumWays(nums, target));
    }

    public int findTargetSumWays(int[] nums, int target) {
        return findAnswer(nums, target, 0, 0, 0);
    }

    private int findAnswer(int[] nums, int target, int idx, int sum, int depth) {
        if (depth == nums.length) {
            return target == sum ? 1 : 0;
        }
        return findAnswer(nums, target, idx + 1, sum + nums[idx], depth + 1)
                + findAnswer(nums, target, idx + 1, sum - nums[idx], depth + 1);
    }
}
