package programmers;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
        int[] arr = {0,0,1000};
        String result = solution(arr);
        System.out.println(result);
    }

    static public String solution(int[] numbers) {
        String answer = "";
        List<String> list = new ArrayList<>();
        for (int i : numbers) {
            list.add(Integer.toString(i));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (Integer.parseInt(s1 + s2) > Integer.parseInt(s2 + s1)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        if (list.get(0).equals("0")) {
            answer = "0";
        }else {
            for (String i : list) {
                answer += i;
            }
        }
        return answer;
    }
}
