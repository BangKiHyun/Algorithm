package leetcode.problems.recursion;

public class LC_JumpGame2_TimeOut {
    private int length;
    private int minimumJumpCount = Integer.MAX_VALUE;

    public static void main(String[] args) {
        final LC_JumpGame2_TimeOut problem = new LC_JumpGame2_TimeOut();
        int[] nums = {5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
        System.out.println(problem.jump(nums));
    }

    public int jump(int[] nums) {
        length = nums.length;
        if (length == 0) return 0;
        findMinimumJumpCount(0, 0, nums);
        return minimumJumpCount;
    }

    private void findMinimumJumpCount(int idx, int jumpCount, int[] nums) {
        if (idx == length - 1) {
            minimumJumpCount = Math.min(minimumJumpCount, jumpCount);
            return;
        }

        for (int addCount = 1; addCount <= nums[idx]; addCount++) {
            if (idx + addCount >= length) break;
            findMinimumJumpCount(idx + addCount, jumpCount + 1, nums);
        }
    }
}
