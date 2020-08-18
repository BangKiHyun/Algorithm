package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//현우는 용돈을 효율적으로 활용하기 위해 계획을 짜기로 하였다.
//현우는 앞으로 N일 동안 자신이 사용할 금액을 계산하였고, 돈을 펑펑 쓰지 않기 위해 정확히 M번만 통장에서 돈을 빼서 쓰기로 하였다.
//현우는 통장에서 K원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출한다.
//다만 현우는 M이라는 숫자를 좋아하기 때문에, 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다.
//현우는 돈을 아끼기 위해 인출 금액 K를 최소화하기로 하였다. 현우가 필요한 최소 금액 K를 계산하는 프로그램을 작성하시오.
public class bj_6236_이분탐색 {
    private static final int MAX = 1_000_000_001;
    private static int n, k;
    private static int[] money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        money = new int[n];
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            money[i] = m;
        }

        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        int min = 0;
        int max = MAX;

        while (min <= max) {
            int mid = (min + max) / 2;
            int count = getCount(mid);

            if (count <= k) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    private static int getCount(int mid) {
        int count = 0;
        int amount = 0;

        for (int i = 0; i < n; i++) {
            if (mid < money[i]) {
                return MAX;
            }

            if (amount - money[i] < 0) {
                amount = mid;
                count++;
            }
            amount -= money[i];
        }

        return count;
    }
}
