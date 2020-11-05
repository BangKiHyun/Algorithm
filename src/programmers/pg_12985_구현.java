package programmers;

public class pg_12985_구현 {
    public static void main(String[] args) {
        int n = 8;
        int a = 4;
        int b = 7;
        System.out.println(solution(n, a, b));
    }

    private static int solution(int n, int a, int b) {
        int ans = 0;
        double doubleA = a;
        double doubleB = b;
        while (doubleA != doubleB) {
            ans++;
            doubleA = (int) Math.ceil(doubleA / 2);
            doubleB = (int) Math.ceil(doubleB / 2);
        }

        return ans;
    }
}
