package programmers.impl;

import java.util.*;

public class pg_튜플 {

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        for (int ans : solution(s)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String s) {
        StringTokenizer st = new StringTokenizer(s, "{}");
        List<String> tupleList = new ArrayList<>();
        while (st.hasMoreElements()) {
            String maybeTuple = st.nextToken();
            if (!maybeTuple.equals(",")) {
                tupleList.add(maybeTuple);
            }
        }

        tupleList.sort(Comparator.comparingInt(String::length));

        Set<String> answerSet = new LinkedHashSet<>();
        for (String tuple : tupleList) {
            String[] tupleValue = tuple.split(",");
            answerSet.addAll(Arrays.asList(tupleValue));
        }

        int[] answer = new int[answerSet.size()];
        int idx = 0;
        for (String tuple : answerSet) {
            answer[idx++] = Integer.parseInt(tuple);
        }
        return answer;
    }
}
