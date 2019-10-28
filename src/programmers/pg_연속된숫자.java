package programmers;

public class pg_연속된숫자 {
    public static void main(String[] args) {
        int n = 15;
        int ans = 1;

        for (int i = 1; i <= n / 2 + 1; i++) {
            if (isFind(i, n)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean isFind(int start, int num) {
        int sum = 0;
        for (int i = start; i <= num; i++) {
            sum += i;
            if (sum == num) {
                return true;
            } else if (sum > num) {
                return false;
            }
        }
        return false;
    }
}
