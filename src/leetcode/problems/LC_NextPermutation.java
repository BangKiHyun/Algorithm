package leetcode.problems;

public class LC_NextPermutation {

    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        final LC_NextPermutation problem = new LC_NextPermutation();
        problem.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        int lastChangeIdx = length;
        for (int idx = length - 1; idx > 0; idx--) {
            if (nums[idx] > nums[idx - 1]) {
                lastChangeIdx = idx;
                break;
            }
        }

        if (isNotPossibleLowest(lastChangeIdx, length)) {
            reverse(0, length - 1, nums);
            return;
        }

        int prev = lastChangeIdx;
        for (int idx = lastChangeIdx + 1; idx < length; idx++) {
            if (nums[idx] > nums[lastChangeIdx - 1] && nums[idx] <= nums[prev]) {
                prev = idx;
            }
        }

        swapNumber(lastChangeIdx - 1, prev, nums);
        reverse(lastChangeIdx, length - 1, nums);
    }

    private boolean isNotPossibleLowest(int lastChangeIdx, int length) {
        return lastChangeIdx == length;
    }

    private void reverse(int startIdx, int endIdx, int[] nums) {
        int temp;
        while (startIdx <= endIdx) {
            temp = nums[startIdx];
            nums[startIdx] = nums[endIdx];
            nums[endIdx] = temp;
            startIdx++;
            endIdx--;
        }
    }

    private void swapNumber(int firstIdx, int secondIdx, int[] nums) {
        int temp = nums[firstIdx];
        nums[firstIdx] = nums[secondIdx];
        nums[secondIdx] = temp;
    }
}
