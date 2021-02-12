package programmers.greedy;

public class pg_풍선_터트리기 {

    public static void main(String[] args) {
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        System.out.println(solution(a));
    }

    public static int solution(int[] a) {
        if (a.length <= 2) {
            return a.length;
        }

        int ans = 2;
        int minNumberOnRight = a[a.length - 1];
        int minNumberOnLeft = a[0];
        for (int i = 1; i < a.length - 1; i++) {
            int targetNumberOfLeft = a[i];
            int targetNumberOfRight = a[a.length - i - 1];

            if (targetNumberOfLeft < minNumberOnLeft) {
                ans++;
                minNumberOnLeft = targetNumberOfLeft;
            }

            if (targetNumberOfRight < minNumberOnRight) {
                ans++;
                minNumberOnRight = targetNumberOfRight;
            }
        }

        return minNumberOnLeft == minNumberOnRight ? ans - 1 : ans;
    }
}
