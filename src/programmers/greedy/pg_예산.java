package programmers.greedy;

import java.util.Arrays;

public class pg_예산 {

    public static void main(String[] args) {
        int[] d = {1, 3, 2, 5, 4};
        int budget = 9;
        System.out.println(solution(d, budget));
    }

    public static int solution(int[] d, int budget) {
        Arrays.sort(d);
        int cnt = 0;
        for (int requestMoney : d) {
            if (budget - requestMoney < 0) {
                break;
            }
            cnt++;
            budget -= requestMoney;
        }

        return cnt;
    }
}
