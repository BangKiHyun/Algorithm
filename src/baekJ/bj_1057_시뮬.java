package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1057_시뮬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int kim = Integer.parseInt(st.nextToken());
        int lim = Integer.parseInt(st.nextToken());

        int count = 1;
        while (true) {
            if (n % 2 == 0) {
                kim = getNextNumber(kim);
                lim = getNextNumber(lim);
                if (kim == lim) {
                    break;
                }
                n /= 2;
            } else {
                if (kim == n) {
                    kim = n / 2 + 1;
                    lim = getNextNumber(lim);
                } else if (lim == n) {
                    lim = n / 2 + 1;
                    kim = getNextNumber(kim);
                } else {
                    kim = getNextNumber(kim);
                    lim = getNextNumber(lim);
                    if (kim == lim) {
                        break;
                    }
                }
                n /= 2 + 1;
            }
            count++;
        }

        System.out.println(count);
    }

    private static int getNextNumber(final int number) {
        if (number % 2 == 0) {
            return number / 2;
        } else {
            return number / 2 + 1;
        }
    }
}