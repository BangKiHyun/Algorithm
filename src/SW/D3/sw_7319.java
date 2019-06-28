package SW.D3;

import java.util.Scanner;

public class sw_7319 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            String X = sc.next();
            int result = 0;
            for (int j = 0; j < X.length(); j++) {
                result += (X.charAt(j) - '0');
            }
            result %= (N-1);
            System.out.println("#" + i + " " + result);
        }
    }
}
