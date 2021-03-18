package programmers.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class pg_무지의_먹방_라이브 {

    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        long k = 5;
        System.out.println(solution(food_times, k));
    }

    public static int solution(int[] food_times, long k) {
        long totalCount = getTotalCount(food_times);
        if (totalCount <= k) {
            return -1;
        }

        PriorityQueue<Food> priorityQueueOfFoodCount = new PriorityQueue<>();
        initPriorityQueueOfFoodCount(priorityQueueOfFoodCount, food_times);

        totalCount = 0;
        long prevCount = 0;
        long foodSize = food_times.length;
        while (totalCount + (priorityQueueOfFoodCount.peek().remainCount - prevCount) * foodSize <= k) {
            int curRemainCountOfFood = priorityQueueOfFoodCount.poll().remainCount;
            totalCount += (curRemainCountOfFood - prevCount) * foodSize;
            foodSize--;
            prevCount = curRemainCountOfFood;
        }

        List<Food> foodList = new ArrayList<>();
        while (!priorityQueueOfFoodCount.isEmpty()) {
            foodList.add(priorityQueueOfFoodCount.poll());
        }
        foodList.sort(new FoodComparatorOfIdx());

        int answerIdx = (int) ((k - totalCount) % foodSize);
        return foodList.get(answerIdx).idx;
    }

    private static void initPriorityQueueOfFoodCount(PriorityQueue<Food> priorityQueueOfFoodCount, int[] foodCounts) {
        int foodIdx = 1;
        for (int foodCount : foodCounts) {
            priorityQueueOfFoodCount.add(new Food(foodIdx++, foodCount));
        }
    }

    private static long getTotalCount(int[] foodCounts) {
        long totalCount = 0;
        for (int foodCount : foodCounts) {
            totalCount += foodCount;
        }
        return totalCount;
    }

    private static class Food implements Comparable<Food> {
        private int idx;
        private int remainCount;

        public Food(int idx, int remainCount) {
            this.idx = idx;
            this.remainCount = remainCount;
        }

        @Override
        public int compareTo(Food target) {
            return this.remainCount - target.remainCount;
        }
    }

    private static class FoodComparatorOfIdx implements Comparator<Food> {
        @Override
        public int compare(Food first, Food second) {
            return first.idx - second.idx;
        }
    }
}
