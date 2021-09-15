package leetcode.problems.map;

import java.util.HashMap;
import java.util.Map;

public class LC_RabbitsInForest {

    public static void main(String[] args) {
        int[] answers = {1, 1, 2};
        final LC_RabbitsInForest task = new LC_RabbitsInForest();
        System.out.println(task.numRabbits(answers));
    }

    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : answers) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int res = 0;
        for (int key : map.keySet()) {
            if (key == 0) {
                res += map.get(key);
            } else {
                int groupCount = (map.get(key) / (key + 1)) + (map.get(key) % (key + 1) == 0 ? 0 : 1);
                res += groupCount * (key + 1);
            }
        }
        return res;
    }
}
