package practice;

import java.util.Scanner;

public class Prime_Number {
    static int number = 100000;
    static int a[] = new int[100001];

    static boolean isPrimeNumber(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    static boolean isPrimeNumber2(int x) {
        int end = (int) Math.sqrt(x);
        for (int i = 2; i < end; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    static void isPrimeNumberSieve() {
        for (int i = 2; i <= number; i++) {
            a[i] = i;
        }
        for (int i = 2; i <= number; i++) {
            if (a[i] == 0) continue;
            for (int j = i + i; j <= number; j = j + i) {
                a[j] = 0;
            }
        }
        for(int i=2;i<=number;i++){
            if(a[i] != 0)
                System.out.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(isPrimeNumber(num) + " " + isPrimeNumber2(num));
        isPrimeNumberSieve();
    }
}
