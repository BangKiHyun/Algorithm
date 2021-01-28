package programmers.twopointer;

import java.util.*;

public class pg_보석쇼핑 {

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        for (int ans : solution(gems)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String[] gems) {
        int gemSize = getGemSize(gems);
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();

        int startPos = 0;
        int size = gems.length + 1;
        int gemStartPos = 1;
        for (String gem : gems) {
            q.offer(gem);
            if (!map.containsKey(gem)) {
                map.put(gem, 1);
            } else {
                map.put(gem, map.get(gem) + 1);
            }

            while (true) {
                final String firstGem = q.peek();
                if (map.get(firstGem) > 1) {
                    q.poll();
                    map.put(firstGem, map.get(firstGem) - 1);
                    gemStartPos++;
                } else {
                    break;
                }
            }

            if (map.size() == gemSize && q.size() < size) {
                size = q.size();
                startPos = gemStartPos;
            }
        }

        return new int[]{startPos, startPos + size - 1};
    }

    private static int getGemSize(String[] gems) {
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
        return gemSet.size();
    }

}
