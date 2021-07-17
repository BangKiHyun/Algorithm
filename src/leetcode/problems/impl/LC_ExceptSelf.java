package leetcode.problems.impl;

public class LC_ExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        final LC_ExceptSelf task = new LC_ExceptSelf();
        for (int answer : task.productExceptSelf(nums)) {
            System.out.print(answer + " ");
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        answer[0] = nums[0];
        for (int idx = 1; idx < n - 1; idx++) {
            answer[idx] = nums[idx] * answer[idx - 1];
        }

        int product = 1;
        for (int idx = n - 1; idx > 0; idx--) {
            answer[idx] = answer[idx - 1] * product;
            product *= nums[idx];
        }
        answer[0] = product;
        return answer;
    }
}
