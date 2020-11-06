package test;

public class WTest1 {

    public static void main(String[] args) {
        int[] restaurant = {0, 0};
        int[][] rider = {{-700, 0}, {150, 180}, {500, 500}, {150, -800}, {800, 800}, {-900, 500}, {-1100, 900}};
        int k = 1000;

        System.out.println(solution(restaurant, rider, k));
    }

    public static int solution(int[] restaurant, int[][] riders, int k) {
        double maxRange = k * k;

        int ans = 0;
        for (int[] rider : riders) {
            int xRange = rider[0] - restaurant[0];
            int yRange = rider[1] - restaurant[1];

            int range = xRange * xRange + yRange * yRange;

            if (maxRange > range) {
                ans++;
            }
        }

        return ans;
    }

}
