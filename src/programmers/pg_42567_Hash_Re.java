package programmers;

import java.util.HashMap;
import java.util.Map;

public class pg_42567_Hash_Re {
    private static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        solution(participant, completion);
    }

    private static String solution(String[] participant, String[] completion) {
        initHashMap(participant, completion);
        deleteHashMap(completion);
        String ans = findLoser(participant);

        System.out.println(ans);
        return ans;
    }

    private static void initHashMap(String[] participant, String[] completion) {
        for (String x : participant) {
            if (map.get(x) == null) {
                map.put(x, 1);
            } else {
                int val = map.get(x) + 1;
                map.put(x, val);
            }
        }
    }

    private static void deleteHashMap(String[] completion) {
        for (String x : completion) {
            int val = map.get(x) - 1;
            map.put(x, val);
        }
    }

    private static String findLoser(String[] participant) {
        for (String x : participant) {
            if (map.get(x) == 1) {
                return x;
            }
        }
        return "";
    }
}
