package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pg_17684_kakao {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        for (int i : solution(msg)) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(String msg) {
        ArrayList<Integer> ans_list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String alpabet = "A";
        int idx = 26;
        for (int i = 1; i < idx; i++) {
            map.put(alpabet, i);
            char convert = alpabet.charAt(0);
            convert++;
            alpabet = String.valueOf(convert);
        }

        for (int i = 0; i < msg.length(); i++) {
            for (int j = 1; i <= msg.length(); j++) {
                if (i + j <= msg.length()) {
                    String text = msg.substring(i, i + j);
                    if (!map.containsKey(text)) {
                        ans_list.add(map.get(text.substring(0, text.length() - 1)));
                        map.put(text, ++idx);
                        i += j;
                        i -= 2;
                        break;
                    } else if (i + j == msg.length()) {
                        ans_list.add(map.get(text));
                        i += j;
                        break;
                    } else continue;
                } else break;
            }
        }

        int[] ans = new int[ans_list.size()];
        for (int i = 0; i < ans_list.size(); i++) {
            ans[i] = ans_list.get(i);
        }
        return ans;
    }
}

//message를 1~msg 길이까지 map에 넣어(String, text)로
//만약에 짤랐는데 String값이 사전에 없는 문자면 map에 추가

//"KAKAO"
//"TOBEORNOTTOBEORTOBEORNOT"
//"ABABABABABABABAB"
//"AAAAAAAAA"
//"ABCABCABCABC"