package programmers.impl;

import java.util.StringTokenizer;

public class pg_JadenCase문자열_만들기 {
    public static void main(String[] args) {
        String s = "a   a   a   a   a   a   a       a a a ";

        final pg_JadenCase문자열_만들기 task = new pg_JadenCase문자열_만들기();

        System.out.println(task.solution(s));
    }

    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        StringBuilder rtn = new StringBuilder();
        while (st.hasMoreElements()) {
            final String str = st.nextToken();
            System.out.println(str);
            rtn.append(str.substring(0, 1).toUpperCase())
                    .append(str.substring(1).toLowerCase())
                    .append(" ");
        }
        rtn.deleteCharAt(rtn.length() - 1);
        return rtn.toString();
    }
}
