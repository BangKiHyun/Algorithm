package baekJ;

import java.util.*;

public class bj_1339_수학 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] alpha = new char[10];
        int[] weight = new int[10];

        Arrays.fill(alpha, '0');

        for (int i = 0; i < n; i++) {
            int w = 1;
            String line = sc.next();

            for (int j = line.length() - 1; j >= 0; j--) {
                char numChar = line.charAt(j);

                for (int k = 0; k < 10; k++) {
                    if (alpha[k] == numChar) {
                        weight[k] += w;
                        break;
                    } else if (alpha[k] == '0') {
                        alpha[k] = numChar;
                        weight[k] = w;
                        break;
                    }
                }
                w *= 10;
            }
        }

        Arrays.sort(weight);
        int sum = 0;

        for (int i = 9; i >= 0; i--) {
            sum += i * weight[i];
        }

        System.out.println(sum);
    }
}
