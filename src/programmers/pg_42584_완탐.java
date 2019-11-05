package programmers;

public class pg_42584_완탐 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        int[] ans = solution(prices);

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(int[] prices) {
        int len = prices.length;
        int ans[] = new int[len];

        for (int i = 0; i < len - 1; i++) {
            int cnt = 0;
            for (int j = i + 1; j < len; j++) {
                if (prices[i] <= prices[j]) {
                    cnt++;
                } else {
                    cnt++;
                    break;
                }
            }
            ans[i] = cnt;
        }
        return ans;
    }
}
