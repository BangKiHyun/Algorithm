package programmers;

public class pg_60058_kakao {

    private static final char RIGHT_BLOCK = ')';
    private static final char LEFT_BLOCK = '(';

    public static void main(String[] args) {
        String p = "()))((()";
        System.out.println(solution(p));
    }

    public static String solution(String p) {
        if (p.isEmpty()) return "";

        int divisionPos = getDivisionPos(p);
        String u = p.substring(0, divisionPos);
        String v = p.substring(divisionPos);

        if (isCorrect(u)) {
            return u + solution(v);
        }

        String temp = "(" + solution(v) + ")";
        u = u.substring(1, u.length() - 1);
        u = reverseBracket(u);
        return temp + u;
    }

    private static int getDivisionPos(final String p) {
        int leftCnt = 0, rightCnt = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == LEFT_BLOCK) leftCnt++;
            else rightCnt++;

            if (leftCnt == rightCnt) return i + 1;
        }

        return p.length();
    }

    private static boolean isCorrect(final String u) {
        int leftCnt = 0, rightCnt = 0;

        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == LEFT_BLOCK) {
                leftCnt++;
            } else {
                rightCnt++;
            }

            if (rightCnt > leftCnt) return false;
        }

        return rightCnt == leftCnt;
    }

    private static String reverseBracket(final String u) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == LEFT_BLOCK) {
                sb.append(RIGHT_BLOCK);
            } else {
                sb.append(LEFT_BLOCK);
            }
        }

        return String.valueOf(sb);
    }
}
