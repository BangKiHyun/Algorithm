package programmers;

public class pg_12922 {
    public static void main(String[] args) {
        int n = 1000;
        System.out.println(solution(n));
    }

    private static String solution(int n) {
        StringBuffer sb = new StringBuffer();
        String pattern = "수박";

        for (int i = 0; i < n / 2; i++) sb.append(pattern);

        if (n % 2 != 0) sb.append(pattern.charAt(0));

        return String.valueOf(sb);
    }
}