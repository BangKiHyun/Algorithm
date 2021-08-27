package leetcode.problems.set;

import java.util.HashMap;
import java.util.Map;

public class LC_NumberaOfBoomerans {

    public static void main(String[] args) {
        final LC_NumberaOfBoomerans task = new LC_NumberaOfBoomerans();
        int[][] points = {{1, 1},
                {2, 2},
                {3, 3}};
        System.out.println(task.numberOfBoomerangs(points));
    }

    public int numberOfBoomerangs(int[][] points) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                final int distance = getDistance(i, j, points);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            for (int key : map.keySet()) {
                int count = map.get(key);
                answer += count * (count - 1);
            }
            map.clear();
        }
        return answer;
    }

    private int getDistance(int first, int second, int[][] points) {
        int x = points[first][0] - points[second][0];
        int y = points[first][1] - points[second][1];
        return x * x + y * y;
    }
}
