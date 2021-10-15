package leetcode.problems.dp;

public class LC_WiggleSubsequence {

    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        final LC_WiggleSubsequence task = new LC_WiggleSubsequence();
        System.out.println(task.wiggleMaxLength(nums));
    }

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n <= 1) return nums.length;

        int[] dp = new int[n];
        int lastUpIdx = 0;
        int lastDownIdx = 0;
        dp[0] = 1;
        int diff;
        for (int idx = 1; idx < n; idx++) {
            diff = nums[idx] - nums[idx - 1];
            if (diff == 0) dp[idx] = dp[idx - 1];
            else if (diff < 0) {
                dp[idx] = Math.max(dp[lastUpIdx] + 1, dp[lastDownIdx]);
                lastDownIdx = idx;
            } else {
                dp[idx] = Math.max(dp[lastDownIdx] + 1, dp[lastUpIdx]);
                lastUpIdx = idx;
            }
        }
        return dp[n - 1];
    }
}
