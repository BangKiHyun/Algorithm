package SW.D4;

import java.util.Scanner;

public class sw_7088 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.println("#" + test_case + " ");
            int N = sc.nextInt();
            int Q = sc.nextInt();
            int arr_Q[][] = new int[Q][2];
            int arr[] = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = sc.nextInt();
            }
            for (int i = 0; i < Q; i++) {
                arr_Q[i][0] = sc.nextInt();
                arr_Q[i][1] = sc.nextInt();
            }
            ans(N, Q, arr, arr_Q);
        }
    }

    public static void ans(int N, int Q, int arr[], int arr_Q[][]) {
        int result[][] = new int[Q][Q];
        for (int i = 0; i < Q; i++) {
            for (int k = arr_Q[i][0]; k <= arr_Q[i][1]; k++) {
                if (arr[k] == 1) {
                    result[i][0]++;
                } else if (arr[k] == 2) {
                    result[i][1]++;
                } else
                    result[i][2]++;
            }
        }

        for (int i = 0; i < Q; i++) {
            for (int j = 0; j < Q; j++) {
                System.out.print(result[i][j]);
                if (j != 2) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
