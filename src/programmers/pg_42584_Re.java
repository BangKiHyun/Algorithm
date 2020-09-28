package programmers;

public class pg_42584_Re {
    public static void main(String[] args) {
        int[] price = {1, 2, 3, 2, 3};
        for (int i : solution(price)) {
            System.out.println("ans = " + i);
        }
    }

    public static int[] solution(int[] prices) {
        int length = prices.length;
        int[] ans = new int[length];

        for (int i = 0; i < length; i++) {
            int cnt = 1;
            for (int j = i + 1; j < length; j++) {
                if (prices[i] <= prices[j]) {
                    ans[i] = cnt;
                } else{
                    ans[i] = cnt;
                    break;
                }
                cnt++;
            }
        }

        return ans;
    }
}
