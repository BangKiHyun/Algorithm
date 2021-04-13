package leetcode.problems.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_ThreeSum {
    private static int length;

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        for (List<Integer> list : threeSum(nums)) {
            for (int number : list) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answerList = new ArrayList<>();
        length = nums.length;
        if (length < 3) {
            return answerList;
        }

        Arrays.sort(nums); // -4 -1 -1 0 1 2
        for (int idx = 0; idx < length; idx++) {
            if (idx > 0 && nums[idx] == nums[idx - 1]) continue;
            int targetNumber = nums[idx];
            findTwoNumber(nums, idx + 1, targetNumber * -1, answerList);
        }
        return answerList;
    }

    private static void findTwoNumber(int[] nums, int startIdx, int targetNumber, List<List<Integer>> answerList) {
        int left = startIdx;
        int right = length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < targetNumber) {
                left++;
            } else if (sum > targetNumber) {
                right--;
            } else {
                answerList.add(Arrays.asList(targetNumber * -1, nums[left], nums[right]));
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }
    }
}
