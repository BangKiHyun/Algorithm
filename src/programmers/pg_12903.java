package programmers;

public class pg_12903 {
    public static void main(String[] args) {
        String s = "abce";
        String ans = solution(s);
        System.out.println(ans);
    }

    private static String solution(String s) {
        int length = s.length();
        int divide = length / 2;

        if (length % 2 == 1) {
            return s.substring(divide, divide + 1);
        } else {
            return s.substring(divide - 1, divide + 1);
        }
    }
}
