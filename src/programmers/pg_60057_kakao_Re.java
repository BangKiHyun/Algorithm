package programmers;

public class pg_60057_kakao_Re {
    public static void main(String[] args) {
        String s = "ababcdcdababcdcd";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int length = s.length();
        int min = length;

        for (int i = 1; i < length / 2; i++) {
            String curAlpha = s.substring(0, i);
            int duplicateCnt = 0;
            int textLength = 0;
            for (int pos = i; pos + i < length; pos += i) {
                String alpha = s.substring(pos, pos + i);
                if (curAlpha.equals(alpha)) {
                    duplicateCnt++;
                } else {
                    textLength = textLength + i + getDuplicateLength(duplicateCnt);
                    curAlpha = alpha;
                }
            }
            min = Math.min(min, textLength);
        }
        return min;
    }

    private static int getDuplicateLength(final int duplicateCnt) {
        if (duplicateCnt == 0) return 0;
        if (duplicateCnt < 10) return 1;
        else if (duplicateCnt < 100) return 2;
        else if (duplicateCnt < 1000) return 3;

        return 4;
    }
}
