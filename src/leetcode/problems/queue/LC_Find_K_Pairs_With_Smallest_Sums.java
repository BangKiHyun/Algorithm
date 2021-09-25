package leetcode.problems.queue;

import java.util.*;

public class LC_Find_K_Pairs_With_Smallest_Sums {

    public static void main(String[] args) {
        int[] num1 = {1, 1, 2};
        int[] num2 = {1, 2, 3};
        int k = 2;

        final LC_Find_K_Pairs_With_Smallest_Sums task = new LC_Find_K_Pairs_With_Smallest_Sums();
        System.out.println(task.kSmallestPairs(num1, num2, k));
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<Pair> prq = new PriorityQueue<>();
        List<List<Integer>> answer = new ArrayList<>();
        for (int first : nums1) {
            for (int second : nums2) {
                if (prq.size() < k) {
                    prq.add(new Pair(first, second));
                } else {
                    final Pair pair = new Pair(first, second);
                    if (prq.peek() != null && prq.peek().sum > pair.sum) {
                        prq.poll();
                        prq.add(pair);
                    } else break;
                }
            }
        }

        while (!prq.isEmpty()) {
            final Pair pair = prq.poll();
            answer.add(Arrays.asList(pair.first, pair.second));
        }
        return answer;
    }

    private static class Pair implements Comparable<Pair> {
        private int first;
        private int second;
        private int sum;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
            this.sum = first + second;
        }


        @Override
        public int compareTo(Pair target) {
            return target.sum - this.sum;
        }
    }
}
