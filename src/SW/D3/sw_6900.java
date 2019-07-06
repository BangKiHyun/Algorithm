package SW.D3;

import java.util.Scanner;

public class sw_6900 {
    static String winNum[];
    static int winMoney[];
    static String myNum[];

    public static void result(int N, int M) {
        int money = 0;
        int idx = 0;
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++) {
            System.out.print("#" + test_case + " ");
            int N = sc.nextInt();
            int M = sc.nextInt();
            winMoney = new int[N];
            winNum = new String[N];
            myNum = new String[M];
            for (int i = 0; i < N; i++) {
                winNum[i] = sc.next();
                winMoney[i] = sc.nextInt();
            }
            for (int j = 0; j < M; j++) myNum[j] = sc.next();
            result(N,M);
        }
    }
}
