package programmers;

public class pg_12914_DP {
    static long d[] = new long[2001];

    public static void main(String[] args) {
        long ans = solution(4);
        System.out.println(ans);
    }

    static public long solution(int n) {
        long answer = dp(n);
        return answer;
    }
    static public long dp(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (d[n] != 0) return d[n];
        return d[n] = (dp(n - 1) + dp(n - 2)) % 1234567;
    }
}
