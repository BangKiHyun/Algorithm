package problem;

import java.util.Scanner;

public class bj_14888 {
    static int n;
    static int[] arrNum;
    static int[] op;
    static int max = -987654321;
    static int min = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arrNum = new int[n];
        op = new int[4];
        for (int i = 0; i < n; i++) {
            arrNum[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            op[i] = sc.nextInt();
        }
        solution(1, arrNum[0]); //DFS

        System.out.println(max);
        System.out.println(min);
    }

    private static void solution(int x, int sum) {
        for (int i = 0; i < 4; i++) {
            if (op[i] != 0) {
                op[i]--;
                if (i == 0) {
                    solution(x + 1, sum + arrNum[x]);
                } else if (i == 1) {
                    solution(x + 1, sum - arrNum[x]);
                } else if (i == 2) {
                    solution(x + 1, sum * arrNum[x]);
                } else if (i == 3) {
                    solution(x + 1, sum / arrNum[x]);
                }
                op[i]++;
            }
        }
        if (x == n) {
            if (sum > max) max = sum;
            if (sum < min) min = sum;
        }
    }
}
