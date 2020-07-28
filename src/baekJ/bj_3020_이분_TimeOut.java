package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//개똥벌레 한 마리가 장애물(석순과 종유석)로 가득찬 동굴에 들어갔다.
//동굴의 길이는 N미터이고, 높이는 H미터이다. (N은 짝수) 첫 번째 장애물은 항상 석순이고, 그 다음에는 종유석과 석순이 번갈아가면서 등장한다.
//이 개똥벌레는 장애물을 피하지 않는다. 자신이 지나갈 구간을 정한 다음 일직선으로 지나가면서 만나는 모든 장애물을 파괴한다.
//동굴의 크기와 높이, 모든 장애물의 크기가 주어진다.
//이때, 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간이 총 몇 개 있는지 구하는 프로그램을 작성하시오.
public class bj_3020_이분_TimeOut {
    private static int[] pillar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 N과 H가 주어진다. N은 항상 짝수이다. (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000)
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int h = Integer.parseInt(input[1]);

        pillar = new int[h + 1];

        //다음 N개 줄에는 장애물의 크기가 순서대로 주어진다. 장애물의 크기는 H보다 작은 양수이다.
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                addBottom(height);
            } else {
                addTop(h, height);
            }
        }

        Arrays.sort(pillar);
        int sameCount = findSameCountOf(pillar[1]);

        System.out.println(String.format("%d %d", pillar[1], sameCount));
    }

    private static void addBottom(final int height) {
        for (int i = 1; i <= height; i++) {
            pillar[i]++;
        }
    }

    private static void addTop(final int top, final int height) {
        for (int i = top; i >= height; i--) {
            pillar[i]++;
        }
    }

    private static int findSameCountOf(final int minCount) {
        return (int) Arrays.stream(pillar)
                .filter(count -> count == minCount)
                .count();
    }
}
