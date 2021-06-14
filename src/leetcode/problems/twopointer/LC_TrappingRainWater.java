package leetcode.problems.twopointer;

public class LC_TrappingRainWater {
    public static void main(String[] args) {
        final LC_TrappingRainWater problem = new LC_TrappingRainWater();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(problem.trap(height));
    }

    public int trap(int[] height) {
        if (height.length == 0) return 0;

        int leftPos = 0;
        int rightPos = height.length - 1;
        int leftMaxHeight = height[leftPos];
        int rightMaxHeight = height[rightPos];
        int trappingWaterSize = 0;
        while (leftPos <= rightPos) {
            if (leftMaxHeight < rightMaxHeight) {
                leftMaxHeight = Math.max(leftMaxHeight, height[leftPos]);
                trappingWaterSize += leftMaxHeight - height[leftPos++];
            } else {
                rightMaxHeight = Math.max(rightMaxHeight, height[rightPos]);
                trappingWaterSize += rightMaxHeight - height[rightPos--];
            }
        }
        return trappingWaterSize;
    }
}
