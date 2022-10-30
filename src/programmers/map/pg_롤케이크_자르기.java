package programmers.map;

import java.util.HashMap;
import java.util.Map;

public class pg_롤케이크_자르기 {

    public static void main(String[] args) {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        final pg_롤케이크_자르기 task = new pg_롤케이크_자르기();
        System.out.println(task.solution(topping));
    }

    public int solution(int[] topping) {
        final HashMap<Integer, Integer> frontMap = new HashMap<>();
        final HashMap<Integer, Integer> lastMap = new HashMap<>();

        int point = 0;
        int max = topping.length;

        for (int idx = 0; idx <= point; idx++) {
            if(frontMap.containsKey(topping[idx])){
                frontMap.put(topping[idx], frontMap.get(topping[idx]) + 1);
                continue;
            }
            frontMap.put(topping[idx], 1);
        }
        for (int idx = max - 1; idx > point; idx--) {
            if(lastMap.containsKey(topping[idx])){
                lastMap.put(topping[idx], lastMap.get(topping[idx]) + 1);
                continue;
            }
            lastMap.put(topping[idx], 1);
        }

        int matchingCount = 0;
        if (isSameSize(frontMap, lastMap)) {
            matchingCount++;
        }
        while (++point < topping.length) {
            if (isSameSize(frontMap, lastMap)) {
                matchingCount++;
            }

            int key = topping[point];
            if(frontMap.containsKey(key)){
                frontMap.put(key, frontMap.get(key) + 1);
            }else {
                frontMap.put(key, 0);
            }

            if (lastMap.containsKey(key)) {
                int value = lastMap.get(key);
                if(--value == 0) lastMap.remove(key);
                else lastMap.put(key, value);
            }
        }
        return matchingCount;
    }

    private boolean isSameSize(Map first, Map second) {
        return first.size() == second.size();
    }
}
