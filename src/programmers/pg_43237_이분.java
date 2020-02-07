package programmers;

public class pg_43237_이분 {
    //국가의 역할 중 하나는 여러 지방의 예산요청을 심사하여 국가의 예산을 분배하는 것입니다. 국가예산의 총액은 미리 정해져 있어서 모든 예산요청을 배정해 주기는 어려울 수도 있습니다. 그래서 정해진 총액 이하에서 가능한 한 최대의 총 예산을 다음과 같은 방법으로 배정합니다.
    //1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정합니다.
    //2. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액을 배정합니다.
    //   상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정합니다.
    public static void main(String[] args) {
        int[] budgets = {120, 110, 140, 150};
        int m = 485;

        System.out.println(solution(budgets, m));
    }

    private static int solution(int[] budgets, int M) {
        int max = M;
        int min = (int) Math.floor(M / budgets.length);
        int ans = 0;
        long budget = 0;

        for (int i : budgets) {
            budget += i;
            max = Math.max(min, i);
        }

        if (budget <= M) {
            return max;
        }

        int preMid = 0;
        while (true) {
            int mid = (int) Math.ceil((min + max) / 2);
            if (preMid == mid) {
                ans = mid;
                break;
            }

            budget = getBudget(budgets, mid);

            if (budget > M) {
                max = mid;
            } else {
                min = mid;
            }
            preMid = mid;
        }

        return ans;
    }

    private static long getBudget(int[] budgets, int money) {
        long sum = 0;

        for (int i : budgets) {
            sum += i <= money ? i : money;
        }

        return sum;
    }
}

//Test Case
//120, 110, 140, 150
//485
//1,2,3,4,5,6,7,8,9,10
//56
//9,8,6,5,7
//5