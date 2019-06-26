package SW.D3;

import java.util.Scanner;

public class sw_7732 {
    static void result(String x, String y) {
        int x1, x2, x3;
        int y1, y2, y3;
        String c1, c2, c3;

        x1 = Integer.parseInt(x.substring(0, 2));
        x2 = Integer.parseInt(x.substring(3, 5));
        x3 = Integer.parseInt(x.substring(6, 8));

        y1 = Integer.parseInt(y.substring(0, 2));
        y2 = Integer.parseInt(y.substring(3, 5));
        y3 = Integer.parseInt(y.substring(6, 8));

        if (x3 > y3) {
            y3 += 60;
            y2--;
        }
        if (x2 > y2) {
            y2 += 60;
            y1--;
        }
        if (x1 > y1) {
            y1 += 24;
        }

        c1 = change(y1 - x1);
        c2 = change(y2 - x2);
        c3 = change(y3 - x3);

        System.out.println(c1 + ":" + c2 + ":" + c3);
    }

    static String change(int x){
        String c = null;
        if(x < 10){
            c = "0" + Integer.toString(x);
            return c;
        }else{
            return Integer.toString(x);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String d = null;
        String p = null;
        for (int i = 1; i <= t; i++) {
            d = sc.next();
            p = sc.next();
            System.out.print("#" + i + " ");
            result(d, p);
        }
    }
}
