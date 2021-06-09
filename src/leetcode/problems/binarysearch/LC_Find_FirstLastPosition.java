package leetcode.problems.binarysearch;

public class LC_Find_FirstLastPosition {
    public static void main(String[] args) {
        LC_Find_FirstLastPosition solution = new LC_Find_FirstLastPosition();
        int[] nums = {2, 2};
        int target = 3;
        for (int answer : solution.searchRange(nums, target)) {
            System.out.print(answer + " ");
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int startPos = 0;
        int endPos = nums.length - 1;
        while (startPos <= endPos) {
            int mid = (startPos + endPos) / 2;
            if (nums[mid] == target) {
                return findAnswer(nums, mid, target);
            }
            if (nums[mid] > target) {
                endPos = mid - 1;
            } else {
                startPos = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    private int[] findAnswer(int[] nums, int mid, int target) {
        int[] answer = new int[2];
        answer[0] = findStartingPositionAnswer(nums, mid, target);
        answer[1] = findEndingPositionAnswer(nums, mid, target);
        return answer;
    }

    private int findStartingPositionAnswer(int[] nums, int idx, int target) {
        int startingPos = idx;
        while (idx >= 0) {
            int curNumber = nums[idx];
            if (curNumber == target) startingPos = idx--;
            else break;
        }
        return startingPos;
    }

    private int findEndingPositionAnswer(int[] nums, int idx, int target) {
        int endingPos = idx;
        while (idx < nums.length) {
            int curNumber = nums[idx];
            if (curNumber == target) endingPos = idx++;
            else break;
        }
        return endingPos;
    }
}
