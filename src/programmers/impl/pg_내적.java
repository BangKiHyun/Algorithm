package programmers.impl;

public class pg_내적 {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int[] b = {-3, -1, 0, 2};
        System.out.println(solution(a, b));
    }

    public static int solution(int[] a, int[] b) {
        int ans = 0;
        for (int idx = 0; idx < a.length; idx++) {
            ans += a[idx] * b[idx];
        }
        return ans;
    }
}
