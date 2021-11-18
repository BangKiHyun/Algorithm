package leetcode.problems.dp;

public class LC_ArithmeticSlices {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        final LC_ArithmeticSlices task = new LC_ArithmeticSlices();
        System.out.println(task.numberOfArithmeticSlices(nums));
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0, sum = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] - nums[i - 1] == nums[i + 1] - nums[i])
                sum += ++count;
            else
                count = 0;
        }
        return sum;
    }
}
