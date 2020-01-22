package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2512_이분 {
    //국가의 역할 중 하나는 여러 지방의 예산요청을 심사하여 국가의 예산을 분배하는 것이다. 국가예산의 총액은 미리 정해져 있어서 모든 예산요청을 배정해 주기는 어려울 수도 있다. 그래서 정해진 총액 이하에서 가능한 한 최대의 총 예산을 다음과 같은 방법으로 배정한다.
    //
    //1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
    //2. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액을 배정한다. 상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에는 지방의 수를 의미하는 정수 N이 주어진다. N은 3 이상 10,000 이하이다.
        int n = Integer.parseInt(br.readLine());

        //다음 줄에는 각 지방의 예산요청을 표현하는 N개의 정수가 빈칸을 사이에 두고 주어진다. 이 값들은 모두 1 이상 100,000 이하이다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] req_budget = new int[n];
        int i = 0;
        int max = -1;
        while (st.hasMoreElements()) {
            req_budget[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, req_budget[i]);
            i++;
        }

        //그 다음 줄에는 총 예산을 나타내는 정수 M이 주어진다. M은 N 이상 1,000,000,000 이하이다.
        int m = Integer.parseInt(br.readLine());

        int min = 1;
        int mid;

        while (max >= min) {
            mid = (min + max) / 2;

            int sum_budget = getSumOfBudget(mid, req_budget);

            if (sum_budget <= m) min = mid + 1;
            else max = mid - 1;
        }

        System.out.println(max);
    }

    private static int getSumOfBudget(int offer_budget, int[] req_budget) {
        int sum = 0;

        for (int i = 0; i < req_budget.length; i++) {
            int budget = getBudget(offer_budget, req_budget[i]);
            sum += budget;
        }

        return sum;
    }

    private static int getBudget(int offer_budget, int req_budget) {
        return offer_budget <= req_budget ? offer_budget : req_budget;
    }
}
