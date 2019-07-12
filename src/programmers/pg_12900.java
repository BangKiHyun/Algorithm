package programmers;

import java.math.BigInteger;

public class pg_12900 {
    static int d[] = new int[60001];

    public static int solution(int n) {
        d[1] = 1;
        d[2] = 2;
        return dp(n);
    }

    public static int dp(int x) {
        if(d[x] != 0 ) return d[x];
        return d[x] = (dp(x - 1) + dp(x - 2)) % 1000000007;
    }

    public static void main(String[] args) {
        int n = 4;
        int ans = solution(n);
        System.out.println(ans);
    }
}
