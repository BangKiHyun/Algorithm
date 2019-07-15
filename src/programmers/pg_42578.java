package programmers;

import java.util.HashMap;

public class pg_42578 {
    static public int solution(String[][] clothes) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        for (int i = 0; i < clothes.length; i++) {
            if (hm.containsKey(clothes[i][1]))
                hm.replace(clothes[i][1], hm.get(clothes[i][1]) + 1);
            else
                hm.put(clothes[i][1], 1);
        }

        int answer = 1;
        for (int value : hm.values()) {
            answer *= (value + 1);
        }
        answer -= 1;

        return answer;
    }

    static public void main(String[] args) {
        String c[][] = {{"yellow_hat", "a"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "a"}, {"yellow_hat", "a"}, {"yellow_hat", "a"}};
        int ans = solution(c);
        System.out.println(ans);
    }
}
