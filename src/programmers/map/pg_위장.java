package programmers.map;

import java.util.HashMap;
import java.util.Map;

public class pg_위장 {
    public static void main(String[] args) {
        String[][] clothes = {
                {"yellow_hat", "a"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "a"},
                {"yellow_hat", "a"},
                {"yellow_hat", "a"}};

        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        for (String[] c : clothes) {
            if (!map.containsKey(c[1])) map.put(c[1], 1);
            else map.put(c[1], map.get(c[1]) + 1);
        }

        return getAnswer(map);
    }

    private static int getAnswer(Map<String, Integer> map) {
        int ans = 1;
        for (int value : map.values()) {
            ans *= (value + 1);
        }

        return --ans;
    }
}
