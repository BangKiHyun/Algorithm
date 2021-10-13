package programmers.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pg_압축 {

    private Map<String, Integer> wordAndNumberMap = new HashMap<>();
    char[] wordChar;
    private int startIdx = 0;
    private int lastNumber = 26;
    private int n;

    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";

        final pg_압축 task = new pg_압축();

        for (int value : task.solution(msg)) {
            System.out.print(value + " ");
        }
    }

    public int[] solution(String msg) {
        wordChar = msg.toCharArray();
        n = msg.length();
        initMap();

        List<Integer> answerList = new ArrayList<>();
        while (startIdx != n) {
            String w = findNextKey();
            answerList.add(wordAndNumberMap.get(w));
        }

        int[] answer = new int[answerList.size()];
        for (int idx = 0; idx < answer.length; idx++) {
            answer[idx] = answerList.get(idx);
        }
        return answer;
    }

    private String findNextKey() {
        StringBuilder sb = new StringBuilder();
        boolean isNotContains = false;
        for (int idx = startIdx; idx < n; idx++) {
            sb.append(wordChar[idx]);
            if (!wordAndNumberMap.containsKey(sb.toString())) {
                wordAndNumberMap.put(sb.toString(), ++lastNumber);
                isNotContains = true;
                break;
            }
        }

        if (isNotContains) {
            startIdx += (sb.length() - 1);
        } else startIdx += sb.length();

        return isNotContains ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    private void initMap() {
        char c = 'A';
        for (int idx = 0; idx < 26; idx++) {
            wordAndNumberMap.put(String.valueOf(c++), idx + 1);
        }
    }
}
