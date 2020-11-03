package programmers;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class pg_12951_구현 {
    private static final char EMPTY = ' ';

    public static void main(String[] args) {
        String s = "3people unFollowed me";
        System.out.println(solution(s));
    }

    private static String solution(String s) {
        char[] cArr = s.toCharArray();
        char preChar = EMPTY;
        StringBuilder sb = new StringBuilder();

        for (char curChar : cArr) {
            sb.append(getChangeCharacter(curChar, preChar));
            preChar = curChar;
        }

        return sb.toString();
    }

    private static char getChangeCharacter(char curChar, char preChar) {
        return preChar == EMPTY ? toUpperCase(curChar) : toLowerCase(curChar);
    }
}
