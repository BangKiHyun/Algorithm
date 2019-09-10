package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class pg_42884 {
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int[][] routes = {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int ans = solution(routes);
        System.out.println(ans);
    }

    private static int solution(int[][] routes) {
        int ans = 0;
        sort(routes);

        for (int i = 0; i < routes.length; i++) {
            int temp = routes[i][0];
            if (temp > max) {
                ans++;
                max = routes[i][1];
            }
        }
        return ans;
    }

    private static void sort(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else return 0;
            }
        });
    }
}
