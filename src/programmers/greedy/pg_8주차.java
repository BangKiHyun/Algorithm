package programmers.greedy;

public class pg_8주차 {

    public static void main(String[] args) {
        int[][] size = {{60, 50},
                {30, 70},
                {60, 30},
                {80, 40}};
        final pg_8주차 task = new pg_8주차();
        System.out.println(task.solution(size));
    }

    public int solution(int[][] sizes) {
        int maxWeight = -1;
        int maxHeigth = -1;
        for (int idx = 0; idx < sizes.length; idx++) {
            int weight = sizes[idx][0];
            int height = sizes[idx][1];
            if (weight < height) {
                int temp = weight;
                weight = height;
                height = temp;
                sizes[idx][0] = weight;
                sizes[idx][1] = height;
            }
            maxWeight = Math.max(maxWeight, weight);
            maxHeigth = Math.max(maxHeigth, height);
        }
        return maxWeight * maxHeigth;
    }
}
