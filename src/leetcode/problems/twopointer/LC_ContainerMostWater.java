package leetcode.problems.twopointer;

public class LC_ContainerMostWater {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, getArea(minHeight, left, right));

            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

    private static int getArea(int height, int left, int right) {
        return height * (right - left);
    }
}
