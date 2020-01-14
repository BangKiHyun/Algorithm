package programmers;

public class pg_12904_구현_re {
    public static void main(String[] args) {
        String s = "abcdcba";
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        int len = s.length();
        for (int i = len; i > 1; i--) {
            int start = 0;
            int end = i - 1;

            while (end < len) {
                if (isEquals(s, start, end)) return i;
                start++;
                end++;
            }
        }
        return 1;
    }

    private static boolean isEquals(String str, int start, int end) {
        int len = (end - start + 1) / 2 - 1;

        for (int i = 0; i < len; i++) {
            if (str.charAt(start + i) != str.charAt(end - i)) return false;
        }
        return true;
    }
}
