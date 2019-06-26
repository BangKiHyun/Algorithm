package SW.D3;

import java.util.Scanner;

public class sw_7510 {
    static void result(int x) {
        int num = x / 2 + 1;
        int sum = 0;
        int count = 0;
        if (x == 1) {
            System.out.println("1");
            return;
        } else {
            for (int j = 1; j <= num; j++) {
                sum = 0;
                for (int i = j; i <= num; i++) {
                    sum += i;
                    if (sum == x) {
                        count++;
                        break;
                    } else if (x < sum) {
                        break;
                    }
                }
            }
        }
        System.out.println(count + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int num;
        for (int i = 1; i <= t; i++) {
            num = sc.nextInt();
            System.out.print("#" + i + " ");
            result(num);
        }
    }
}
