package leetcode.problems.impl;

import java.util.Arrays;
import java.util.Random;

public class LC_ShuffleAnArray {
    private int[] orig;
    private int[] copy;
    private int size;
    private Random rand = new Random();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        final LC_ShuffleAnArray task = new LC_ShuffleAnArray();
        task.solution(nums);
        int[] t = task.shuffle();
        System.out.println(t[0] + " " + t[1] + " " + t[2]);
    }

    public void solution(int[] nums) {
        size = nums.length;
        copy = Arrays.copyOf(nums, size);
        orig = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return orig;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = size; i > 1; i--) {
            int randIdx = rand.nextInt(i);
            int temp = copy[i - 1];
            copy[i - 1] = copy[randIdx];
            copy[randIdx] = temp;
        }
        return copy;
    }
}
