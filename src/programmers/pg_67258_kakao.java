package programmers;

import java.util.*;

public class pg_67258_kakao {
    private static final int MAX = 100001;

    public static void main(String[] args) {
        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        int[] ans = solution(gems);
        System.out.println(ans[0] + " " + ans[1]);
    }

    private static int[] solution(String[] gems) {
        Map<String, Integer> hashMap = new HashMap<>();
        Set<String> hashSet = new HashSet<>(Arrays.asList(gems));
        Queue<String> queue = new LinkedList<>();

        int curStartPos = 1, length = MAX;
        int startPos = 0;
        for (String gem : gems) {
            if (!hashMap.containsKey(gem)) {
                hashMap.put(gem, 1);
            } else {
                hashMap.put(gem, hashMap.get(gem) + 1);
            }
            queue.add(gem);

            while (true) {
                String firstGem = queue.peek();
                if (hashMap.get(firstGem) > 1) {
                    queue.poll();
                    hashMap.put(firstGem, hashMap.get(firstGem) - 1);
                    curStartPos++;
                } else {
                    break;
                }
            }

            if (hashMap.size() == hashSet.size() && queue.size() < length) {
                length = queue.size();
                startPos = curStartPos;
            }
        }

        return new int[]{startPos, startPos + length - 1};
    }
}
//"ZZZ", "YYY", "NNNN", "YYY", "BBB"