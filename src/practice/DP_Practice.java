package practice;

import java.util.Scanner;

public class DP_Practice {
    static int d[] = new int[1001];
    static int d2[] = new int[1001];
    static int d3[] = new int[1001];
    static int d4[][] = new int[1000001][2];

    //2xN타일 문제 -> 1x2, 2x1 -> 2개로
    static int dp1(int x) {
        if (x == 1) return 1;
        if (x == 2) return 2;
        if (d[x] != 0) return d[x];
        return d[x] = (dp1(x - 1) + dp1(x - 2)) % 10007;
    }

    //2xN타일 문제 -> 2x1, 1x2, 2x2 3개로
    static int dp2(int x) {
        if (x == 1) return 1;
        if (x == 2) return 3;
        if (d2[x] != 0) return d2[x];
        return d2[x] = (dp2(x - 1) + 2 * dp2(x - 2)) % 10007;
    }

    //3xN타일 문제 -> 2x1, 1x2 2개로 백준2133
    static int dp3(int x) {
        if (x == 0) return 1;
        if (x == 1) return 0;
        if (x == 2) return 3;
        if (d3[x] != 0) return d3[x];
        int result = 3 * dp3(x - 2);
        for (int i = 3; i <= x; i++) {
            if (i % 2 == 0) {
                result += 2 * d3[x - i];
            }
        }
        return d3[x] = result;
    }

    //2xN타일 문제 -> 2x1, 1x2, 1x1 3개로 백준14852 -> 2차원 dp사용해야 시간초과 안남 14852
    static int dp4(int x) {
        d4[0][0] = 0;
        d4[1][0] = 2;
        d4[2][0] = 7;
        d4[2][1] = 1;
        for (int i = 3; i <= x; i++) {
            d4[i][1] = d4[i - 1][1] + d4[i - 3][0] % 100000007;
            d4[i][0] = 2 * d4[i - 1][0] + 3 * d4[i - 2][0] + 2 * d4[i][1];
        }
        return d4[x][0];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int result1 = dp1(x);
        int result2 = dp2(x);
        int result3 = dp3(x);
        int result4 = dp4(x);
        System.out.print(result1 + " " + result2 + " " + result3 + " " + result4);
    }
}
