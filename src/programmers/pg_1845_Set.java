package programmers;

import java.util.HashSet;
import java.util.Set;

public class pg_1845_Set {
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3,};
        System.out.println(solution(nums));
    }

    private static int solution(int[] nums) {
        int max = nums.length / 2;

        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        if (max <= set.size()) {
            return max;
        } else return set.size();
    }
}
