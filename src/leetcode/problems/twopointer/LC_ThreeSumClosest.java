package leetcode.problems.twopointer;

import java.util.Arrays;

public class LC_ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestNumber = 0;
        boolean isFirst = true;
        for (int idx = 0; idx < nums.length - 2; idx++) {
            int left = idx + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[idx] + nums[left] + nums[right];

                if (Math.abs(target - sum) < Math.abs(target - closestNumber) || isFirst) {
                    closestNumber = sum;
                    isFirst = false;
                }

                if (sum == target) return sum;
                if (sum < target) left++;
                else right--;
            }
        }
        return closestNumber;
    }
}
