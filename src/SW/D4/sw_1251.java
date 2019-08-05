/*
package SW.D4;

import java.util.Scanner;

public class sw_1251 {
    static int matrix[][];
    static int dis[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            matrix = new int[n][2];
            dis = new int[n][n];
            for (int i = 0; i < n; i++) {
                matrix[i][0] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                matrix[i][1] = sc.nextInt();
            }

            double tax = sc.nextDouble();

            for (int i = 0; i < n; i++) {
                double nowi = matrix[i][0];
                double nowj = matrix[i][1];

                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    double nexti = matrix[j][0];
                    double nextj = matrix[j][1];

                    double d = tax * ((nowi - nexti) * (nowj - nextj));
                }
            }
        }
    }
}
*/
