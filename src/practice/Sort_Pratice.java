package practice;


import java.util.Scanner;

public class Sort_Pratice {
    static int n, m;
    static int a[] = new int[10001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            m = sc.nextInt();
            a[m]++;
        }
        for (int i = 0; i < a.length; i++) {
            while (a[i] != 0) {
                System.out.println(i);
                a[i]--;
            }
        }
    }
}
