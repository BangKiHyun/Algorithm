package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class pg_42628_prq {

    public static void main(String[] args) {
        String[] op = {"I 16", "D 1"};
        //{"I 7", "I 5", "I -5", "D -1"};
        int res[] = solution(op);
        System.out.println(res[0] + " " + res[1]);
    }

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];
        ArrayList<Integer> list = new ArrayList<>();
        int opLen = operations.length;

        for (int i = 0; i < opLen; i++) {
            String op[] = operations[i].split(" ");
            if (op[0].equals("I")) {
                list.add(Integer.parseInt(op[1]));
                Collections.sort(list);
            } else {
                if (list.isEmpty()) {
                    continue;
                }
                if (op[1].equals("-1")) {
                    list.remove(0);
                } else {
                    list.remove(list.size() - 1);
                }
            }
        }
        if (list.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[1] = list.get(0);
            answer[0] = list.get(list.size() - 1);
        }
        return answer;
    }
}
