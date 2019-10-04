package programmers;

import java.util.Scanner;

public class pg_42842_완탐 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int brown = sc.nextInt();
        int red = sc.nextInt();

        int width = 0;
        int height = 0;

        for (int i = 1; i <= red; i++) {
            width = i;
            height = (red % i == 0) ? red / i : red / i + 1;

            if (width * 2 + height * 2 + 4 == brown) break;
        }
        System.out.println(Math.max(width, height) + 2 + " " + Math.min(width, height) + 2);
    }
}
