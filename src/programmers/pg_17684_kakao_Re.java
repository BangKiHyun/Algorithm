package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pg_17684_kakao_Re {
    public static void main(String[] args) {
        String msg = "A";
        // "TOBEORNOTTOBEORTOBEORNOT";
        for (int i : solution(msg)) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> ansList = new ArrayList<>();

        char alpabet = 'A';
        map.put("A", 1);
        for (int i = 2; i <= 26; i++) {
            alpabet += 1;
            map.put(String.valueOf(alpabet), i);
        }

        int idx = 26;
        int length = msg.length();
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= length; j++) {
                if (i + j <= length) {
                    String text = msg.substring(i, i + j);
                    if (!map.containsKey(text)) {
                        ansList.add(map.get(text.substring(0, text.length() -1)));
                        map.put(text, ++idx);
                        i += j;
                        i -= 2;
                        break;
                    }else if(i + j == length){
                        ansList.add(map.get(text));
                        i += j;
                        break;
                    }
                }
            }
        }

        int[] ret = new int[ansList.size()];
        int ret_idx = 0;
        for (int ans : ansList) {
            ret[ret_idx++] = ans;
        }

        return ret;
    }
}
