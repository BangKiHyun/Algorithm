package practice;

import java.util.Scanner;

public class DP_exFibonacci {
    static int d[] = new int[100];

    //원래 fibonacci
    static int fibonacci(int x) {
        if (x == 1) return 1;
        if (x == 2) return 1;
        return fibonacci(x - 1) + fibonacci(x - 2);
    }

    //개선된 fibonacci
    static int dp(int x) {
        if (x == 1) return 1;
        if (x == 2) return 1;
        if (d[x] != 0) return d[x];
        return d[x] = dp(x - 1) + dp(x - 2);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("구하고싶은 수 입력 :");
        int input = sc.nextInt();
        int num = dp(input);
        System.out.println(num);
    }
}
