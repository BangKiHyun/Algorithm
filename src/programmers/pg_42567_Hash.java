package programmers;

import java.util.HashMap;
import java.util.Map;

public class pg_42567_Hash {
    private static String[] participant = {"mislav", "stanko", "mislav", "ana"};
    private static String[] completion = {"stanko", "ana", "mislav"};
    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        initMap();
        solution();
        result();
    }

    private static void initMap() {
        for (int i = 0; i < participant.length; i++) {
            map.put(i, participant[i]);
        }
    }

    private static void solution() {
        for (int i = 0; i < participant.length; i++) {
            for (int j = 0; j < completion.length; j++) {
                if (isCorrect(i, j)) {
                    deleteMapAndCompletion(i, j);
                    break;
                }
            }
        }
    }

    private static boolean isCorrect(int i, int j) {
        if (map.containsKey(i) && map.get(i).equals(completion[j])) return true;
        return false;
    }

    private static void deleteMapAndCompletion(int i, int j) {
        map.remove(i);
        completion[j] = "";
    }

    private static void result() {
        for (int i = 0; i < participant.length; i++) {
            if (map.containsKey(i)) {
                System.out.print(map.get(i) + " ");
            }
        }
    }
}