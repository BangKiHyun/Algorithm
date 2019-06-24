/*
package SW;

import java.util.Scanner;

public class sw_2581 {
    static int MAX = 7001;
    static String a[][] = new String[MAX][37];
    static int c, r;

    static void get_arr(int x) {
        if (x == 1) {
            a[0][0] = "*";
            return;
        }
        get_arr(x - 1);

        if (x % 3 == 2) {
            for(int i=0;i<x/3;i++)
                c++;
            a[r][c] = " ";
            a[r][c+1] = "*";
        }
        if(x % 3 == 1){
            int temp = r;
            for(int i=)
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {

        }
    }
}
*/
