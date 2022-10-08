package baekJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2839_설탕배달 {
    private static final int FIVE = 5;
    private static final int THREE = 3;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int weight = Integer.parseInt(br.readLine());

        int remain = weight % FIVE;
        int fiveCnt = weight / FIVE;
        int threeCnt = 0;

        while (true) {
            if (remain % THREE == 0) {
                threeCnt = remain / 3;
                break;
            }
            if (fiveCnt == 0) break;

            fiveCnt--;
            remain += FIVE;
        }
        System.out.println(remain % THREE != 0 ? -1 : fiveCnt + threeCnt);
    }
}
