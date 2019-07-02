/*
package SW.D4;

import java.util.ArrayList;
import java.util.Scanner;

public class sw_7699 {
    static int MAX = 401;
    static ArrayList<Integer> a[] = new ArrayList[MAX];
    static boolean c[] = new boolean[MAX];

    static void dfs(int x) {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++) {
            int R = sc.nextInt();
            int C = sc.nextInt();
            char map[][] = new char[R][C];
            for(int i=0;i<R;i++){
                String text = sc.next();
                for(int j=0;j<C;j++){
                    map[i][j] = text.charAt(j);
                }
            }
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    a[i].add(map[i][j]);
                }
            }
        }
    }
}
*/
