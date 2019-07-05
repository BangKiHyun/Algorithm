package programmers;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
        int[] arr = {0,0,1000,0};
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
            public int compare( String o1, String o2 ) {
                if (o1.length() == o2.length()){
                    return o2.compareTo(o1);
                }
                String artificial = o1+o2;
                String reverse = o2+o1;

                return reverse.compareTo(artificial);
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
