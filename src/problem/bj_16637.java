package problem;

import java.util.Scanner;

public class bj_16637 {
    static int n;
    static String op[];
    static int arrNum[];
    static int max = -987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        String s = sc.next();
        op = new String[n / 2];
        arrNum = new int[n / 2 + 1];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                arrNum[i / 2] = Integer.parseInt(s.substring(i, i + 1));
            } else {
                op[i / 2] = s.substring(i, i + 1);
            }
        }
        solution(arrNum[0], 0);
        System.out.println(max);
    }

    static void solution(int sum, int opIdx) {
        int nextResult = 0, nowResult = 0;

        if (opIdx > op.length - 1) {
            max = Math.max(max, sum);
            return;
        }
        nowResult = cal(sum, arrNum[opIdx + 1], op[opIdx]);
        solution(nowResult, opIdx + 1);
        System.out.println(sum);

        if (opIdx + 2 < arrNum.length) {
            nextResult = cal(arrNum[opIdx + 1], arrNum[opIdx + 2], op[opIdx + 1]);
            nowResult = cal(sum, nextResult, op[opIdx]);
            System.out.println(sum);
            solution(nowResult, opIdx + 2);
        }
    }

    static int cal(int a, int b, String s) {
        if (s.equals("+")) {
            return a + b;
        } else if (s.equals("-")) {
            return a - b;
        } else {
            return a * b;
        }
    }
}
