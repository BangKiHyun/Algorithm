package leetcode.problems.recursion;

import java.util.Arrays;

public class LC_JumpGame2 {

    public static void main(String[] args) {
        final LC_JumpGame2 problem = new LC_JumpGame2();
        int[] nums = {5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
        System.out.println(problem.jump(nums));
    }

    public int jump(int[] nums) {
        int[] cache = new int[nums.length];
        Arrays.fill(cache, -1);
        return findMinimumJumpCount(0, nums, cache);
    }

    private int findMinimumJumpCount(int idx, int[] nums, int[] cache) {
        if (idx == nums.length - 1) return 0;
        if (idx >= nums.length) return Integer.MAX_VALUE;
        if (cache[idx] != -1) return cache[idx];
        int minValue = Integer.MAX_VALUE;
        for (int addNumber = 1; addNumber <= nums[idx]; addNumber++) {
            minValue = Math.min(minValue, findMinimumJumpCount(idx + addNumber, nums, cache));
        }
        return minValue != Integer.MAX_VALUE ? minValue + 1 : minValue;
    }
}
