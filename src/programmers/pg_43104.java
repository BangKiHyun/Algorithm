package programmers;

public class pg_43104 {
    static long d[] = new long[81];

    static public long solution(int N) {
        long answer = 0;
        if(N == 1) return 2;
        if(N == 2) return 6;
        dp(N);
        answer = (d[N-1] + d[N]) * 2 + d[N] * 2;
        return answer;
    }

    static public long dp(int x) {
        if (x == 1) return d[x] = 1;
        if (x == 2) return d[x] = 1;
        if(d[x] != 0 ) return d[x];
        return d[x] = dp(x - 1) + dp(x - 2);
    }

    public static void main(String[] args){
        int n = 6;
        long ans = solution(n);
        System.out.println(ans);
    }
}
