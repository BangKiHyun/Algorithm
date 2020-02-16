package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//슈퍼 마리오 앞에 10개의 버섯이 일렬로 놓여져 있다. 이 버섯을 먹으면 점수를 받는다.
//슈퍼 마리오는 버섯을 처음부터 나온 순서대로 집으려고 한다. 하지만, 모든 버섯을 집을 필요는 없고 중간에 중단할 수 있다.
//중간에 버섯을 먹는 것을 중단했다면, 그 이후에 나온 버섯은 모두 먹을 수 없다. 따라서 첫 버섯을 먹지 않았다면, 그 이후 버섯도 모두 먹을 수 없다.
//마리오는 받은 점수의 합을 최대한 100에 가깝게 만들려고 한다.
//버섯의 점수가 주어졌을 때, 마리오가 받는 점수를 출력하는 프로그램을 작성하시오.

//첫째 줄에 마리오가 받는 점수를 출력한다.
//만약 100에 가까운 수가 2개라면 (예: 98, 102) 마리오는 큰 값을 선택한다.
public class bj_2851_구현 {
    private static final int MAX = 10;
    private static final int GOAL = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //총 10개의 줄에 각각의 버섯의 점수가 주어진다. 이 값은 100보다 작거나 같은 양의 정수이다. 버섯이 나온 순서대로 점수가 주어진다.
        int[] mushroom = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            mushroom[i] = Integer.parseInt(br.readLine());
        }

        int ans = eatMushroom(mushroom);

        System.out.println(ans);
    }

    private static int eatMushroom(int[] mushroom) {
        int sum = 0;
        for (int i = 0; i < MAX; i++) {
            sum += mushroom[i];
            if (isEqual(sum)) break;
            if (isBigger(sum)) {
                sum = getSimilaritySum(sum, sum - mushroom[i]);
                break;
            }
        }

        return sum;
    }

    private static boolean isEqual(int sum) {
        return sum == GOAL;
    }

    private static boolean isBigger(int sum) {
        return sum > GOAL;
    }

    private static int getSimilaritySum(int big, int small) {
        int diff_big = big - GOAL;
        int diff_small = GOAL - small;

        return diff_big > diff_small ? small : big;
    }
}
